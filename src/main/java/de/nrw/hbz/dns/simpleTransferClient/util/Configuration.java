/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @author aquast
 *
 */
public abstract class Configuration {

	// Initialize logger object 
	private static Logger log = Logger.getLogger(Configuration.class);

	private static Properties confProp = new Properties();
	private static String configPropertiesFileName = System.getProperty("user.home") + "/etc/SimpleTransferClient.properties";

	public static void loadConfigurationFile(){
        confProp = new Properties();
        try {
            InputStream propStream = new FileInputStream(new File(configPropertiesFileName));
            confProp.load(propStream);
        } catch (Exception e) {
        	log.error("Error loading configuration: properties file not at " + System.getProperty("user.home") + "/etc/SimpleTransferClient.properties");
        	log.error(e);
        }

	}
	


	
	public static void setProperty(String key, String value){
		confProp.setProperty(key, value);
	}
	
	public static String getProperty(String key){
		return confProp.getProperty(key);
	}

	public static void loadProperties(){
		loadConfigurationFile();
	}

	public static String getApiProtocol(){
		return confProp.getProperty("apiprotocol");
	}

	public static String getApiHost(){
		return confProp.getProperty("apihost");
	}

	public static String getApiEndpoint(){
		return confProp.getProperty("apiendpoint");
	}
	
	public static String getApiUser(){
		return confProp.getProperty("apiuser");
	}
	
	public static String getApiPassWd(){
		return confProp.getProperty("apipasswd");
	}
	
	public static String getSourceDir(){
		return confProp.getProperty("sourcedir");
	}
	
	public static String getTargetDir(){
		return confProp.getProperty("targetdir");
	}

	
}
