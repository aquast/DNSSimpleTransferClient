/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.sipController;

import org.apache.log4j.Logger;

import de.nrw.hbz.dns.simpleTransferClient.controller.ControllerFields;

/**
 * @author aquast
 *
 */
public class Sip {
	protected Sip(){
	
	}
	
	private static Logger log = Logger.getLogger(Sip.class);
	private ControllerFields controllerFields = null;
	
	private SipActionBehavior relatedActionBehavior = null;
	
	public void performRelatedAction(){
		relatedActionBehavior.relatedAction(controllerFields);
	}
	
	public void setRelatedActionBehavior(SipActionBehavior relatedBehavior){
		this.relatedActionBehavior = relatedBehavior; 
		
	}
	
	public static class SipFactory{

		public static Sip getInstance(){
			Sip sip = new Sip();
			return sip;
		}
				
		public static Sip getInstance(String type){
			Sip sip = null;
			try {
				Class<Sip> sipClass = (Class<Sip>) Class.forName("de.nrw.hbz.dns.simpleTransferClient.sipController.SipMasterTransferred");
				sip = sipClass.newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sip;
		}
	
	}


	/**
	 * @return the controllerFields
	 */
	public ControllerFields getControllerFields() {
		return controllerFields;
	}


	/**
	 * @param controllerFields the controllerFields to set
	 */
	public void setControllerFields(ControllerFields controllerFields) {
		this.controllerFields = controllerFields;
	}


}
