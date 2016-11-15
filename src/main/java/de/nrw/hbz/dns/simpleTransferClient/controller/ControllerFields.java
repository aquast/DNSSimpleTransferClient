/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.controller;

/**
 * @author aquast
 *
 */
public class ControllerFields {

	private String sipType = null; // is SIP Master or Delta?
	private int sipSequenceNumber = 0; // which Version is the SIP? 
	private String sipState = null; // what is the SIP's current transfer state?

	
	/**
	 * @return the sipType
	 */
	public String getSipType(){
		return sipType;
	}
	/**
	 * @param sipType the sipType to set
	 */
	public void setSipType(String sipType) {
		this.sipType = sipType;
	}
	/**
	 * @return the sipSequenceNumber
	 */
	public int getSipSequenceNumber() {
		return sipSequenceNumber;
	}
	/**
	 * @param sipSequenceNumber the sipSequenceNumber to set
	 */
	public void setSipSequenceNumber(int sipSequenceNumber) {
		this.sipSequenceNumber = sipSequenceNumber;
	}
	/**
	 * @return the sipState
	 */
	public String getSipState() {
		return sipState;
	}
	/**
	 * @param sipState the sipState to set
	 */
	public void setSipState(String sipState) {
		this.sipState = sipState;
	}
	
	
	
}
