/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.log4j.Logger;

import de.nrw.hbz.dns.simpleTransferClient.apiUtil.JsonClient;
import de.nrw.hbz.dns.simpleTransferClient.fileUtil.SourceDirUtil;
import de.nrw.hbz.dns.simpleTransferClient.sipController.Sip;
import de.nrw.hbz.dns.simpleTransferClient.sipController.Sip.SipFactory;

/**
 * @author aquast
 *
 */
public class TransferController {
	
	private static Logger log = Logger.getLogger(TransferController.class);

	public void createSipObjectPresentation(){
		SourceDirUtil fUtil = new SourceDirUtil();
		ArrayList<String> sipList = fUtil.findSips();
		ArrayList<ControllerFields> sipState = new ArrayList<ControllerFields>();
		
		// collect information for each SIP 
		Iterator<String> it = sipList.iterator();
		while(it.hasNext()){
			String sipFileName = it.next();
			sipState.add(collectSipControllerFields(sipFileName));
			
		}
		
		// delegate Sip object creation to SipFactory
		Iterator<ControllerFields> sit = sipState.iterator();
		while (sit.hasNext()){
			ControllerFields contField = sit.next();
			String className = "Sip" + contField.getSipType();
			Sip sip = SipFactory.getInstance(className);
			log.info(className);
			sip.setControllerFields(contField);
			log.info(sip.getControllerFields().getSipUrn());
		}
				
	}
	

	/**
	 * Collects all information that characterize a SIP and its processing 
	 * state within the transfer logic
	 *  
	 * @param sipFileName
	 * @return
	 */
	private ControllerFields collectSipControllerFields(String sipFileName){
		ControllerFields cField = new ControllerFields();
		String sipUrn = null;
		String pattern = "gen\\d+";
		String packageName = sipFileName.replaceAll(".tar|.zip|.tgz", "");
		log.debug(packageName);
		String[] sipSlices = packageName.split("_");
		
		if (sipSlices[0].startsWith("urn")){
			sipUrn = sipSlices[0].replaceAll("\\+", ":");
			cField.setSipUrn(sipUrn);
		}

		// collect remote information for the urn
		JsonClient jClient = new JsonClient();
		jClient.setSipUrn(cField.getSipUrn());
		try {
			jClient.performApiPostRequest();
		} catch (IOException e) {
			log.error(e);
		}
		

		for (int i=0; i<sipSlices.length; i++){
			
			// determine if Master or Delta 
			if (sipSlices[i].matches(pattern)){
				cField.setSipType(ControllerFields.DELTA);
				cField.setSipSequenceNumber(Integer.parseInt(sipSlices[i].replace("gen", "")));
				log.debug(sipSlices[i]);
			}
			if (sipSlices[i].matches("master")){
				cField.setSipType(ControllerFields.MASTER);
				cField.setSipSequenceNumber(0);
				log.debug(sipSlices[i]);
			}
			
			
		}
		return cField;
	}

}
