/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.sipController;

import org.apache.log4j.Logger;

/**
 * @author aquast
 *
 */
public class SipMasterToTransfer extends Sip{

	public SipMasterToTransfer(){

		this.setRelatedActionBehavior(new TransferToDns());
	}
	private static Logger log = Logger.getLogger(SipMasterToTransfer.class);

	
}
