package de.nrw.hbz.dns.simpleTransferClient.sipController;

import de.nrw.hbz.dns.simpleTransferClient.controller.ControllerFields;
import de.nrw.hbz.dns.simpleTransferClient.util.RsyncRunner;

public class TransferCompleted extends SipActionBehavior {

	public void relatedAction(ControllerFields cFields) {
		
		this.countTouches(cFields);
		
	}

}
