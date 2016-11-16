/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.junit.*;

import de.nrw.hbz.dns.simpleTransferClient.controller.ControllerFields;
import de.nrw.hbz.dns.simpleTransferClient.fileUtil.MetadataFileUtil;
import de.nrw.hbz.dns.simpleTransferClient.fileUtil.SourceDirUtil;
import de.nrw.hbz.dns.simpleTransferClient.util.Configuration;

/**
 * @author aquast
 *
 */
public class TestMetadataFileUtil {

	private static Logger log = Logger.getLogger(TestMetadataFileUtil.class);
	
	private void setTestConfiguration(){
		Configuration.setProperty("metadatadir", System.getProperty("user.dir") 
				+ "/target/");
		
		Configuration.setProperty("sourcedir", System.getProperty("user.dir") 
				+ "/src/test/resources/sourcedir/");

		// dummy Api-Config
		Configuration.setProperty("apiprotocol", "https");
		Configuration.setProperty("apihost", "www.q-terra.de");
		Configuration.setProperty("apiendpoint", "");
		Configuration.setProperty("apiuser", "user");
		Configuration.setProperty("apipasswd", "xxxxx");
	}


	@Test
	public void writeMdFile(){
		setTestConfiguration();
		ControllerFields cField = new ControllerFields();
		
		System.out.println(Configuration.getMetadataDir());
		SourceDirUtil fUtils = new SourceDirUtil();
		ArrayList<String> fileNameList = fUtils.findSips();
		
		MetadataFileUtil mdUtils = new MetadataFileUtil();

		Iterator<String> it = fileNameList.iterator();
		while(it.hasNext()){
			String urn = null;
			String pattern = "gen\\d+";
			String sipFileName = it.next();
			String packageName = sipFileName.replaceAll(".tar|.zip|.tgz", "");
			String[] sipSlices = packageName.split("_");
			
			if (sipSlices[0].startsWith("urn")){
				cField.setSipUrn(sipSlices[0].replaceAll("\\+", ":"));
				log.info(cField.getSipUrn());
				
			}

			for (int i=0; i<sipSlices.length; i++){
				log.info(sipSlices[i]);
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

			if(! mdUtils.mdFileExists(urn)){
				mdUtils.writeMdFile(cField);
			}
		}
		assertTrue("fails to find newly created properties file ", new File(Configuration.getSourceDir()).isDirectory());
	}
}
