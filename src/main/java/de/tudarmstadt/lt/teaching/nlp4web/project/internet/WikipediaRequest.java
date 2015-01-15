package de.tudarmstadt.lt.teaching.nlp4web.project.internet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;

public class WikipediaRequest {

	private final String address = "http://en.wikipedia.org/w/api.php?format=json&action=query&titles=";
	private final String address2 = "&prop=revisions&rvprop=content";
	private String result;
	
	public WikipediaRequest(String request) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		ServerConnection serverConnection = new ServerConnection();
		result = serverConnection.getJSONFromUrl(address + escapeWebRequest(request) + address2, params);
		
		//Look, if its a redirect
		Pattern pattern = Pattern.compile("#REDIRECT\\s\\[\\[(.*?)\\]\\]");
		Matcher matcher = pattern.matcher(result);
		
		while (matcher.find()) {
		    if(matcher.group(1) != null) {
		    	result = serverConnection.getJSONFromUrl(address + escapeWebRequest(matcher.group(1)) + address2, params);
		    }
		}
	}
	
	private static String escapeWebRequest(String input) {
		input = input.replaceAll(" ", "%20");
//		input = input.replaceAll("$", "%24");
		input = input.replaceAll("&", "%26");
//		input = input.replaceAll("'", "%27");
		return input;
	}
	
	public int getAmountInText(String keyWord) {
		return countWord(result, keyWord);
	}
	
	private static int countWord(String text, String word){
        int count = 0;
        
        //count for every word in variable word
        String[] parts = word.split(" ");
        for(int i = 0; i < parts.length; i++) {
	        Pattern pat = Pattern.compile(Pattern.quote(parts[i].toLowerCase()));
	        Matcher m;
	        for(m = pat.matcher(text.toLowerCase()); m.find(); count++);
        }
        return count;
    }
}
