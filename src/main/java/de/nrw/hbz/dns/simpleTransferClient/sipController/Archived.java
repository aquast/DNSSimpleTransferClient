package de.nrw.hbz.dns.simpleTransferClient.sipController;

import java.io.File;

import de.nrw.hbz.dns.simpleTransferClient.controller.ControllerFields;
import de.nrw.hbz.dns.simpleTransferClient.util.Configuration;
import de.nrw.hbz.dns.simpleTransferClient.util.RsyncRunner;

public class Archived extends SipActionBehavior {

	public void relatedAction(ControllerFields cFields) {
		
		File archivedPackage = new File(Configuration.getSourceDir() + cFields.getSipFileName());
		archivedPackage.delete();
		
		
		this.countTouches(cFields);
	}

}
