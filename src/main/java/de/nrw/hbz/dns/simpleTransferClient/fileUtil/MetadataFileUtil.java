/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.fileUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.nrw.hbz.dns.simpleTransferClient.controller.ControllerFields;
import de.nrw.hbz.dns.simpleTransferClient.util.Configuration;

/**
 * @author aquast
 *
 */
public class MetadataFileUtil {
	
	private static Logger log = Logger.getLogger(MetadataFileUtil.class);
	
	private String suffix = ".properties";

	public boolean mdFileExists(String urn){
		boolean mdFileExists = false;
		String fileName = Configuration.getMetadataDir() + urn + suffix;
		if (new File(fileName).exists() && new File(fileName).canRead()){
			mdFileExists = true;
		}
		return mdFileExists;
		
	}
	
	public void writeMdFile(ControllerFields cFields){
		persistMdFile(cFields.getControllerFieldsProperties());
	}
	
	private void persistMdFile(Properties sipProps){
		try{
			File mdFile = new File(Configuration.getMetadataDir() + sipProps.getProperty("urn") + suffix);
			FileOutputStream fos;
			fos = new FileOutputStream(mdFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			sipProps.store(bos, "MD File for Package related to " + sipProps.getProperty("urn"));

		} catch (Exception e) {
			log.error(e);
		}

	}
}
