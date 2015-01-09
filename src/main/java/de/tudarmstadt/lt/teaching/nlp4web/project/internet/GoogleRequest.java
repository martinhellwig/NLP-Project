package de.tudarmstadt.lt.teaching.nlp4web.project.internet;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

public class GoogleRequest {

	private final String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	private String result;
	
	public GoogleRequest(String request) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		ServerConnection serverConnection = new ServerConnection();
		result = serverConnection.getJSONFromUrl(address + request.replaceAll(" ", "%20"), params);
	}
	
	public int getResultAmount() {
		JSONObject jsonData = new JSONObject(result);
		JSONObject responseData = jsonData.getJSONObject("responseData");
		return Integer.parseInt((String) responseData.getJSONObject("cursor").get("estimatedResultCount"));
	}
}
