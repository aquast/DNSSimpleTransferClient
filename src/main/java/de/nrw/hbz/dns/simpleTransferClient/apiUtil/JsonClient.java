/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.apiUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import de.nrw.hbz.dns.simpleTransferClient.util.Configuration;


/**
 * @author aquast
 *
 */
public class JsonClient {

	private static Logger log = Logger.getLogger(JsonClient.class);

	private JSONObject apiJsonResponse = null;
	private String apiUrl = null;
	private String apiRequest = null;

	String sipUrn = null;
	String sipStatus = null;
	int sipLastPackage = 0;
	String sipLastPackageName = null; // required for next API version
	

	/**
	 * Parses DNS API response for the fields required  
	 */
	private void parseApiResponse(){
		
		JSONArray jArray = (JSONArray)apiJsonResponse.get("result");
		JSONObject jObj = (JSONObject)jArray.get(0);
		sipStatus = jObj.get("status").toString();

		if (jObj.containsKey("packages")){
			jArray = (JSONArray)jObj.get("packages");			
			if (checkPackageCounter(jArray)){
				sipLastPackage = jArray.size();
			} else{
				sipLastPackage = -1;
			}
		}

	}
	
	/**
	 * Not really required, this method double checks if the package sequence submitted to DNS system 
	 * was correctly produced.
	 * If the test fails we have a great problem and to alert the service provider!  
	 * @param jArray
	 * @return
	 */
	private boolean checkPackageCounter(JSONArray jArray){
		Comparator<String> comparator = new Comparator<String>(){
			public int compare(String arg0, String arg1) {
				int c0 = Integer.parseInt(arg0);
				int c1 = Integer.parseInt(arg1);
				
				return c0 -c1;
			}
		};

		Collections.sort(jArray, comparator);
		return jArray.size() == Integer.parseInt((String) jArray.get(jArray.size()-1));
	}
		
		
	/**
	 * Prepares and performs API request via Apache httpComponents.
	 * @throws IOException 
	 */
	public void performApiPostRequest() throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String apiUri = Configuration.getApiProtocol() 
				+ "://" 
				+ Configuration.getApiHost() 
				+ "/" 
				+ Configuration.getApiEndpoint();
		log.debug(apiUri);
		
		UsernamePasswordCredentials creds = 
				new UsernamePasswordCredentials(Configuration.getApiUser(), Configuration.getApiPassWd());
		CredentialsProvider credProvider = new BasicCredentialsProvider();
		AuthScope aScope = new AuthScope(Configuration.getApiHost(), 443);
		credProvider.setCredentials(aScope, creds);
		HttpClientContext httpContext = new HttpClientContext();
		httpContext.setCredentialsProvider(credProvider);

		HttpPost postRequest = new HttpPost(apiUri + sipUrn);
		
		try {
				response = httpClient.execute(postRequest, httpContext);
				//response.close();
				log.info(response.getStatusLine());
				if (response.getStatusLine().getStatusCode() < 300){
					HttpEntity httpEntity = response.getEntity();
					if (httpEntity != null){
						InputStream isj = httpEntity.getContent();
						JSONParser jParser = new JSONParser();
						InputStreamReader iReader = new InputStreamReader(isj);
						apiJsonResponse = (JSONObject)jParser.parse(iReader);
						
						parseApiResponse();
					}
					
				} else {
					sipStatus = response.getStatusLine().getReasonPhrase();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e);
				e.printStackTrace();
			} finally {
				response.close();
			}

	}
	
	public String getSipStatus(){
		return sipStatus;	
	}

	public int getSipLastPackage(){
		return sipLastPackage;	
	}
	
	public void setSipUrn(String SipUrn){
		sipUrn = SipUrn;
	}

}
