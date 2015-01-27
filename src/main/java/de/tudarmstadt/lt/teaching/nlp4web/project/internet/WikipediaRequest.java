package de.tudarmstadt.lt.teaching.nlp4web.project.internet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;

import de.tudarmstadt.lt.teaching.nlp4web.project.HelpFunctions;

public class WikipediaRequest {

	private final String address = "http://en.wikipedia.org/w/api.php?format=json&action=query&titles=";
	private final String address2 = "&prop=revisions&rvprop=content";
	private ArrayList<String> results;
	
	public WikipediaRequest(String request, boolean useDisambiguations) {
		results = new ArrayList<>();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		ServerConnection serverConnection = new ServerConnection();
		results.add(serverConnection.getJSONFromUrl(address + HelpFunctions.escapeWebRequest(request) + address2, params));
		
		//Look, if its a redirect
		Pattern pattern = Pattern.compile("#REDIRECT\\s\\[\\[(.*?)\\]\\]");
		Matcher matcher = pattern.matcher(results.get(0));
		
		//if so, get all (commonly only one) redirects
		while (matcher.find()) {
		    if(matcher.group(1) != null) {
		    	results = new ArrayList<>();
		    	results.add(serverConnection.getJSONFromUrl(address + HelpFunctions.escapeWebRequest(matcher.group(1)) + address2, params));
		    }
		}
		
		//Are there disambiguations?
		if(useDisambiguations && results.get(0).contains("may refer to:")) {
			pattern = Pattern.compile("\\[\\[(.*?)\\]\\]");
			matcher = pattern.matcher(results.get(0));
			
			results = new ArrayList<>();
			while (matcher.find()) {
			    if(matcher.group(1) != null) {
			    	results.add(serverConnection.getJSONFromUrl(address + HelpFunctions.escapeWebRequest(matcher.group(1)) + address2, params));
			    }
			}
		}
	}
	
	/**
	 * Counts the occurence of given word in all found textes
	 * @param keyWord
	 * @return
	 */
	public int getAmountInText(String keyWord) {
		int output = 0;
		for(int i = 0; i < results.size(); i++) {
			output += HelpFunctions.countWord(results.get(i), keyWord);
		}
		
		return output;
	}	
}
