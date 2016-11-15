/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.fileUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import de.nrw.hbz.dns.simpleTransferClient.util.Configuration;

/**
 * @author aquast
 *
 */
public class FileUtils {
	
	private static Logger log = Logger.getLogger(FileUtils.class);

	private String[] suffixes = {".zip", ".tar", ".tgz"}; 
	
	private FilenameFilter sipFilter = new FilenameFilter(){

		public boolean accept(File dir, String name) {
			// TODO Auto-generated method stub
			boolean checkSuffix = false;
			for (int i = 0; i<suffixes.length; i++){
				if(name.endsWith(suffixes[i])){
					checkSuffix = true;
				}
			}
			return checkSuffix;
		}
		
	};

	public ArrayList<String> findSips(){

		File sourceDir = new File(Configuration.getSourceDir());
		String[] sipsInDir = sourceDir.list(sipFilter);
		ArrayList<String> sipsList = new ArrayList<String>();
		for (int i = 0; i<sipsInDir.length ; i++){
			if (! new File(sipsInDir[i]).isDirectory()){
				sipsList.add(sipsInDir[i]);
			}
		}
		
		return sipsList;
		
	}
	
}
