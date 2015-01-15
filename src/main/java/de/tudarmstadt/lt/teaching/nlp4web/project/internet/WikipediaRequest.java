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
	private String result;
	
	public WikipediaRequest(String request) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		ServerConnection serverConnection = new ServerConnection();
		result = serverConnection.getJSONFromUrl(address + HelpFunctions.escapeWebRequest(request) + address2, params);
		
		//Look, if its a redirect
		Pattern pattern = Pattern.compile("#REDIRECT\\s\\[\\[(.*?)\\]\\]");
		Matcher matcher = pattern.matcher(result);
		
		while (matcher.find()) {
		    if(matcher.group(1) != null) {
		    	result = serverConnection.getJSONFromUrl(address + HelpFunctions.escapeWebRequest(matcher.group(1)) + address2, params);
		    }
		}
	}
	
	public int getAmountInText(String keyWord) {
		return HelpFunctions.countWord(result, keyWord);
	}
	
	
}
