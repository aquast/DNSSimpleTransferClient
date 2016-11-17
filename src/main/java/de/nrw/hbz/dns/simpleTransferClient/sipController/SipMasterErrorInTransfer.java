/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.sipController;

import org.apache.log4j.Logger;

/**
 * @author aquast
 *
 */
public class SipMasterErrorInTransfer extends Sip{

	public SipMasterErrorInTransfer(){

		this.setRelatedActionBehavior(new TransferToDns());
	}
	private static Logger log = Logger.getLogger(SipMasterErrorInTransfer.class);

	
}
