/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.sipController;

import org.apache.log4j.Logger;

/**
 * @author aquast
 *
 */
public class SipMasterArchived extends Sip{

	public SipMasterArchived(){

		this.setRelatedActionBehavior(new Archived());
	}
	private static Logger log = Logger.getLogger(SipMasterArchived.class);

	
}
