package de.tudarmstadt.lt.teaching.nlp4web.project.internet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.List;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
 
@SuppressWarnings("deprecation")
public class ServerConnection {
 
    static JSONObject jObj = null;
 
    // constructor
    public ServerConnection() {
    }
    
	public static DefaultHttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            
            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }
	
	public String getJSONFromUrl(String url, List<NameValuePair> params, String apiKey) {
		try {
	    	DefaultHttpClient httpClient = getNewHttpClient();
	    	HttpGet httpGet = new HttpGet(url);
	    	
	    	byte[] accountKeyBytes = Base64.encodeBase64((":" + apiKey).getBytes());
	    	String accountKeyEnc = new String(accountKeyBytes);
	    	httpGet.setHeader("Authorization", "Basic" + " " + accountKeyEnc);
	        StringBuilder stringBuilder = new StringBuilder();
	        
	        HttpResponse response = httpClient.execute(httpGet);
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode == 200) {
	        	BufferedReader reader = new BufferedReader(
	        			new InputStreamReader(response.getEntity().getContent()));
	        	
	        	String line;
	        	while ((line = reader.readLine()) != null) {
	        			stringBuilder.append(line);
	        	}    
	        	return stringBuilder.toString();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bla";
	}
 
	public String getJSONFromUrl(String url, List<NameValuePair> params) {
		try {
	    	DefaultHttpClient httpClient = getNewHttpClient();
	    	HttpGet httpGet = new HttpGet(url);
	        StringBuilder stringBuilder = new StringBuilder();
	        
	        HttpResponse response = httpClient.execute(httpGet);
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode == 200) {
	        	BufferedReader reader = new BufferedReader(
	        			new InputStreamReader(response.getEntity().getContent()));
	        	
	        	String line;
	        	while ((line = reader.readLine()) != null) {
	        			stringBuilder.append(line);
	        	}    
	        	return stringBuilder.toString();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bla";
    }
}