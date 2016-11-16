/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.*;

import de.nrw.hbz.dns.simpleTransferClient.controller.TransferController;
import de.nrw.hbz.dns.simpleTransferClient.fileUtil.SourceDirUtil;
import de.nrw.hbz.dns.simpleTransferClient.util.Configuration;

/**
 * @author aquast
 *
 */
public class TestTransferController {

	private static Logger log = Logger.getLogger(TestTransferController.class);

	private void setTestConfiguration(){
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
	public void testGetUrnFromFileName(){
		setTestConfiguration();
		TransferController controller = new TransferController();
		controller.createSipObjectPresentation();
		
	}
}
