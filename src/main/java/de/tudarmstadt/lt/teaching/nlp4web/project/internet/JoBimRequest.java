package de.tudarmstadt.lt.teaching.nlp4web.project.internet;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import de.tudarmstadt.lt.teaching.nlp4web.project.HelpFunctions;

public class JoBimRequest {

	private final String address = "http://maggie.lt.informatik.tu-darmstadt.de:10080/jobim/ws/holing//?s=";
	private final String address2 = "&format=json";
	private String result;
	
	public JoBimRequest(String request) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		ServerConnection serverConnection = new ServerConnection();
		result = serverConnection.getJSONFromUrl(address + HelpFunctions.escapeWebRequest(request) + address2, params);
	}
	
	/**
	 * Says if the whole sentence is negotiated (if there is a negotiation and this is not bound to a noun
	 * @return
	 */
	public boolean isWholeSentenceNegotiated() {
		JSONObject jsonData = new JSONObject(result);
		JSONArray words = jsonData.getJSONArray("holings");
		for(int i = 0; i < words.length(); i++) {
			String word = words.getJSONObject(i).getJSONObject("key").getString("key");
			if(word.contains("not") || word.contains("no")) {
				//There was a negotiation, now look the negotiated values
				JSONArray values = words.getJSONObject(i).getJSONArray("values");
				for(int j = 0; j < values.length(); j++) {
					String value = values.getJSONObject(j).getString("value");
					if(value.contains("VB")) return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Returns all negotiated nouns (stemmed version of them)
	 * @return
	 */
	public ArrayList<String> getNegotiatedNouns() {
		ArrayList<String> output = new ArrayList<>();
		
		JSONObject jsonData = new JSONObject(result);
		JSONArray words = jsonData.getJSONArray("holings");
		for(int i = 0; i < words.length(); i++) {
			String word = words.getJSONObject(i).getJSONObject("key").getString("key");
			if(word.contains("not") || word.contains("no")) {
				//There was a negotiation, now look the negotiated values
				JSONArray values = words.getJSONObject(i).getJSONArray("values");
				for(int j = 0; j < values.length(); j++) {
					String value = values.getJSONObject(j).getString("value");
					if(value.contains("NN")) {
						value = value.replaceAll("#NN", "");
						output.add(value);
					}
				}
			}
		}
		
		return output;
	}
}

