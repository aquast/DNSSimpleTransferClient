/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.*;

import de.nrw.hbz.dns.simpleTransferClient.fileUtil.FileUtils;
import de.nrw.hbz.dns.simpleTransferClient.util.Configuration;

/**
 * @author aquast
 *
 */
public class TestFileUtils {

	private static Logger log = Logger.getLogger(TestFileUtils.class);

	private void setTestConfiguration(){
		Configuration.setProperty("sourcedir", System.getProperty("user.dir") 
				+ "/src/test/resources/sourcedir/");
		
	}
	
	@Test
	public void testFindSips(){
		setTestConfiguration();
		System.out.println(Configuration.getSourceDir());
		FileUtils fUtils = new FileUtils();
		ArrayList<String> fileNameList = fUtils.findSips();
		System.out.println(fileNameList.size());
		assertTrue("fails to find sourceDir for testing", new File(Configuration.getSourceDir()).isDirectory());
		assertTrue("findSips fails to recognize file names properly", fileNameList.size() == 3);
		
	}
}
