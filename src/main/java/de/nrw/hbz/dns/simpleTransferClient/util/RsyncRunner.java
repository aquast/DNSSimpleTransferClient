/**
 *
 */
package de.nrw.hbz.dns.simpleTransferClient.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * Class RsyncRunner
 * 
 *
 */
public class RsyncRunner {

	// Initiate Logger for RsyncRunner
	private static Logger log = Logger.getLogger(RsyncRunner.class);
	
	private String exitStateStr= null; 	
	private String stoutStr = null;
	
	/**
	 * @param paramString
	 * @param fileName 
	 */
	public void executeRsync(String fileName){
		
		// Complete execute String 
		String programPath = new String("rsync"); 
		String defaultParams = new String("-e ssh");
		String executeString = new String(programPath + " " 
				+ defaultParams 
				+ " " + Configuration.getSourceDir() + fileName 
				+ " " + Configuration.getTransferUser() + '@' + Configuration.getTransferHost()
				+ "://" + Configuration.getTargetDir() + "/"); 

		log.info("The execute String: " + executeString);
		try{
			//Process proc = java.lang.Runtime.getRuntime().exec("echo " + executeString);
			Process proc = java.lang.Runtime.getRuntime().exec(executeString);
			int exitState = proc.waitFor();
			InputStream stout = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stout);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer lineBuffer = new StringBuffer();
            while ((line = br.readLine()) != null){
                lineBuffer.append(line + "\n");
            }
            log.debug("STOUT: " + lineBuffer.toString());
            log.info("Exit State: " + exitState);
            stoutStr = lineBuffer.toString();
            exitStateStr = Integer.toString(exitState);
		}catch(Exception Exc){
			log.error(Exc);
		}
		
	}

	public String getStoutStr(){
		return stoutStr;
	}

	public String getExitStateStr(){
		return exitStateStr;
	}
}
