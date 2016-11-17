package de.nrw.hbz.dns.simpleTransferClient.sipController;

import de.nrw.hbz.dns.simpleTransferClient.controller.ControllerFields;
import de.nrw.hbz.dns.simpleTransferClient.util.RsyncRunner;

public class TransferToDns extends SipActionBehavior {

	public void relatedAction(ControllerFields cFields) {
		
		RsyncRunner rRunner = new RsyncRunner();
		rRunner.executeRsync(cFields.getSipFileName());
		if(rRunner.getExitStateStr().equals("0")){
			cFields.setSipState(ControllerFields.TRANSFERRED);			
		} else {
			cFields.setSipState(ControllerFields.ERROR_IN_TRANSFER);
		}
		
		this.countTouches(cFields);
	}

}
