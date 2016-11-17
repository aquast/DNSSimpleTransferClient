/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.sipController;

import org.apache.log4j.Logger;

/**
 * @author aquast
 *
 */
public class SipMasterTransferred extends Sip{

	public SipMasterTransferred(){

		this.setRelatedActionBehavior(new TransferCompleted());
	}
	private static Logger log = Logger.getLogger(SipMasterTransferred.class);

	
}
