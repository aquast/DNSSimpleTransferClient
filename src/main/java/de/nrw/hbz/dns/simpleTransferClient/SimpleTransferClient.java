/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import de.nrw.hbz.dns.simpleTransferClient.apiUtil.JsonClient;
import de.nrw.hbz.dns.simpleTransferClient.util.Configuration;

/**
 * @author aquast
 *
 */
public class SimpleTransferClient {

	public SimpleTransferClient(){
		SimpleTransferClient.loadLoggingProperties();
	}
	
	// Initialize logger object 
	private static Logger log = Logger.getLogger(SimpleTransferClient.class);
	private static void loadLoggingProperties() {
			PropertyConfigurator.configure("log4j.properties");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration.loadConfigurationFile();
		log.info(Configuration.getApiEndpoint());
		log.info(System.getProperty("user.dir"));
		
		JsonClient jClient = new JsonClient();

		jClient.setSipUrn("urn:nbn:de:hbz:5:1-37225");
		//jClient.setSipUrn("urn:nbn:de:hbz:5");

		
		try {
			jClient.performApiPostRequest();
			log.info(jClient.getSipStatus());
			log.info(jClient.getSipLastPackage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		//log.info(jClient.getSipStatus());
	}

}
