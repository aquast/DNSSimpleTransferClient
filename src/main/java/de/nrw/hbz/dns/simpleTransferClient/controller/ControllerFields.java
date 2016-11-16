/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.controller;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author aquast
 *
 */
/**
 * @author aquast
 *
 */
public class ControllerFields {

	private static Logger log = Logger.getLogger(ControllerFields.class);

	private String sipUrn = null; // The SIP associated URN
	private String sipType = null; // is SIP Master or Delta?
	private String sipState = null; // what is the SIP's current transfer state?
	private int sipSequenceNumber = 0; // which Version is the SIP? 
	private int remoteLastSequenceNumber = 0; // what is last SIP version transfered to DNS?
	
	Properties ControllerFieldsProps = new Properties();

	public static final String MASTER = "Master";
	public static final String DELTA = "Delta";

	public static final String TO_TRANSFER = "ToTransfer";
	public static final String TRANSFERRED = "Transferred";
	public static final String ARCHIVED = "Archived";
	public static final String ERROR_IN_PROGRESS = "ErrorInProgress";
	
	public Properties getControllerFieldsProperties(){
		return ControllerFieldsProps;
	}
	
		
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
		if (sipType.equals(ControllerFields.MASTER) 
				|| sipType.equals(ControllerFields.DELTA)){
			this.sipType = sipType;	
			this.ControllerFieldsProps.setProperty("type", sipType);
		} else{
			log.error("sipType must be Master or Delta, sipType is not setted");
		}

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
		this.ControllerFieldsProps.setProperty("local_sequence_number", Integer.toString(sipSequenceNumber));
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
		this.ControllerFieldsProps.setProperty("excpected_transfer_state", sipState);
	}
	/**
	 * @return the sipUrn
	 */
	public String getSipUrn() {
		return sipUrn;
	}
	/**
	 * @param sipUrn the sipUrn to set
	 */
	public void setSipUrn(String sipUrn) {
		this.sipUrn = sipUrn;
		this.ControllerFieldsProps.setProperty("urn", sipUrn);
	}
	/**
	 * @return the remoteLastSequenceNumber
	 */
	public int getRemoteLastSequenceNumber() {
		return remoteLastSequenceNumber;
	}
	/**
	 * @param remoteLastSequenceNumber the remoteLastSequenceNumber to set
	 */
	public void setRemoteLastSequenceNumber(int remoteLastSequenceNumber) {
		this.remoteLastSequenceNumber = remoteLastSequenceNumber;
		this.ControllerFieldsProps.setProperty("last_remote_sequence_number", Integer.toString(remoteLastSequenceNumber));
	}
	
	
	
}
