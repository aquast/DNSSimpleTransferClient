/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.fileUtil;

import java.io.File;
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

	public boolean mdFileExists(String packageName){
		boolean mdFileExists = false;
		String fileName = Configuration.getMetadataDir() + packageName + suffix;
		if (new File(fileName).exists() && new File(fileName).canRead()){
			mdFileExists = true;
		}
		return mdFileExists;
		
	}
	
	//TODO: method not required anymore? only for junit: maybe change unit test?
	public void writeMdFile(ControllerFields cFields){
		cFields.writeControllerFields();
	}
	
}
