package de.tudarmstadt.lt.teaching.nlp4web.project.internet;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import de.tudarmstadt.lt.teaching.nlp4web.project.HelpFunctions;

public class BingRequest {

	private final String address = "https://api.datamarket.azure.com/Data.ashx/Bing/Search/v1/Composite?Sources=%27web%27&Query=%27";
	private final String address2 = "%27&$top=10&$format=Json";
	private final String APIKEY = "XReXWi8bU6EYFwZjEjZq5M+Fkx2OBxcRfhJ06kM610A";
	private String result;
	
	public BingRequest(String request) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		ServerConnection serverConnection = new ServerConnection();
		result = serverConnection.getJSONFromUrl(address + HelpFunctions.escapeWebRequest(request) + address2, params, APIKEY);
	}
	
	public int getResultAmount() {
		JSONObject jsonData = new JSONObject(result);
		JSONObject responseData = jsonData.getJSONObject("d");
		JSONArray results = responseData.getJSONArray("results");
		return Integer.parseInt((String) results.getJSONObject(0).get("WebTotal"));
	}
	
	public ArrayList<String> getResultURLs(int maxAmount) {
		ArrayList<String> output = new ArrayList<>();
		
		JSONObject jsonData = new JSONObject(result);
		JSONObject responseData = jsonData.getJSONObject("d");
		JSONArray results = responseData.getJSONArray("results");
		JSONArray urls = results.getJSONObject(0).getJSONArray("Web");
		
		int maxLoop = 0;
		if(urls.length() > maxAmount) maxLoop = maxAmount;
		else maxLoop = urls.length();
		for(int i = 0; i < maxLoop; i++) {
			output.add(urls.getJSONObject(i).get("Url").toString());
		}
		
		return output;
	}
}
