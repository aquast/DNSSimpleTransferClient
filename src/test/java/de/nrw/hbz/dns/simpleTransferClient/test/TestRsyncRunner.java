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
import de.nrw.hbz.dns.simpleTransferClient.util.RsyncRunner;

/**
 * @author aquast
 *
 */
public class TestRsyncRunner {

	private static Logger log = Logger.getLogger(TestRsyncRunner.class);
	
	private void setTestConfiguration(){
		Configuration.setProperty("targetdir", System.getProperty("user.dir") 
				+ "/target/");
		
		Configuration.setProperty("sourcedir", System.getProperty("user.dir") 
				+ "/src/test/resources/sourcedir/");


		// dummy Transfer-Config
		Configuration.setProperty("transferhost", "");
		Configuration.setProperty("transferuser", "");
	}


	@Test
	public void startRsyncRunner(){
		setTestConfiguration();

		RsyncRunner rRunner = new RsyncRunner();
		rRunner.executeRsync("test.txt");
		log.info(rRunner.getExitStateStr());
		assertTrue("Exit State of rsync must be 255 ", rRunner.getExitStateStr().equals("255"));
	}
}
