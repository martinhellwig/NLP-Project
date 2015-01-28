package de.tudarmstadt.lt.teaching.nlp4web.project.internet;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import de.tudarmstadt.lt.teaching.nlp4web.project.HelpFunctions;

public class JoRequest {

	private final String address = "http://maggie.lt.informatik.tu-darmstadt.de:10080/jobim/ws/api/stanford/jo/similar/";
	private final String address2 = "%23NN?numberOfEntries=50&format=json";
	private String result;
	
	public JoRequest(String request) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		ServerConnection serverConnection = new ServerConnection();
		result = serverConnection.getJSONFromUrl(address + HelpFunctions.escapeWebRequest(request) + address2, params);
	}
	
	/**
	 * Returns similar words (nouns) to the given one
	 * @param maxAmount
	 * @return
	 */
	public ArrayList<String> getSimilarWords(int maxAmount) {
		ArrayList<String> output = new ArrayList<>();
		
		JSONObject jsonData = new JSONObject(result);
		JSONArray words = jsonData.getJSONArray("results");
		
		int maxLength = 0;
		if(words.length() > maxAmount) maxLength = maxAmount;
		else maxLength = words.length();
		for(int i = 0; i < maxLength; i++) {
			String word = words.getJSONObject(i).getString("key");
			word = word.replaceAll("#NN", "");
			word = word.replaceAll("#NP", "");
			output.add(word);
		}
		
		return output;
	}
}

