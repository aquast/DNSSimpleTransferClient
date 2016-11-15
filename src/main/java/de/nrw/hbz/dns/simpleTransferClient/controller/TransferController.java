/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.log4j.Logger;

import de.nrw.hbz.dns.simpleTransferClient.Sip;
import de.nrw.hbz.dns.simpleTransferClient.Sip.SipFactory;
import de.nrw.hbz.dns.simpleTransferClient.fileUtil.FileUtils;

/**
 * @author aquast
 *
 */
public class TransferController {
	
	private static Logger log = Logger.getLogger(TransferController.class);

	public void createSipObjects(){
		FileUtils fUtil = new FileUtils();
		ArrayList<String> sipList = fUtil.findSips();
		ArrayList<Hashtable<String,String>> sipPropsByFileName = new ArrayList<Hashtable<String,String>>();

		Iterator<String> it = sipList.iterator();
		while(it.hasNext()){
			String sipFileName = it.next();
			sipPropsByFileName.add(getSipNameFields(sipFileName));
		}
		
		
		Iterator<Hashtable<String,String>> sit = sipPropsByFileName.iterator();
		while (sit.hasNext()){
			/*Hashtable ht = sit.next();
			Enumeration<String> eNum = ht.keys();
			while (eNum.hasMoreElements()){
				log.info(eNum.nextElement());
			}
			log.info("Find Sequence: " + ht.get("sequence")); */
			Sip sip = SipFactory.getInstance(sit.next().get("sequence"));
		}
		
		
	}
	
	private Hashtable<String, String> getSipNameFields(String sipFileName){
		Hashtable<String,String> nameSplits = new Hashtable<String,String>();
		String sipUrn = null;
		String pattern = "gen\\d+";
		String packageName = sipFileName.replaceAll(".tar|.zip|.tgz", "");
		log.debug(packageName);
		String[] sipSlices = packageName.split("_");
		
		if (sipSlices[0].startsWith("urn")){
			sipUrn = sipSlices[0].replaceAll("\\+", ":");
			nameSplits.put("urn", sipUrn);
		}
		for (int i=0; i<sipSlices.length; i++){
			if (sipSlices[i].matches(pattern)){
				nameSplits.put("sequence", sipSlices[i]);
				nameSplits.put("sipType", "Delta");
				log.debug(sipSlices[i]);
			}
			if (sipSlices[i].matches("master")){
				nameSplits.put("sequence", sipSlices[i]);
				nameSplits.put("sipType", "Master");
				log.debug(sipSlices[i]);
			}
		}
		return nameSplits;
	}
}
