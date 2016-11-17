/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.sipController;

import de.nrw.hbz.dns.simpleTransferClient.controller.ControllerFields;

/**
 * @author aquast
 *
 */
public abstract class SipActionBehavior {
	
	public abstract void relatedAction(ControllerFields cFields);
	
	public void countTouches(ControllerFields cFields){
		if(cFields.getControllerFieldsProperties().containsKey("count_touches")){
			cFields.setNumberOfTouches(cFields.getNumberOfTouches() + 1 );
		} else {
			cFields.setNumberOfTouches(1);			
		}

	}
}
