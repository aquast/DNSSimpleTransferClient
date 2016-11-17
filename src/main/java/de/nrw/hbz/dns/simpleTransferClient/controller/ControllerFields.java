/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.nrw.hbz.dns.simpleTransferClient.util.Configuration;

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

	private static String suffix = ".properties";
	
	Properties controllerFieldsProps = new Properties();

	public static final String MASTER = "Master";
	public static final String DELTA = "Delta";

	public static final String TO_TRANSFER = "ToTransfer";
	public static final String TRANSFERRED = "Transferred";
	public static final String ERROR_IN_TRANSFER = "ErrorInTransfer";
	public static final String ERROR_IN_PROGRESS = "ErrorInProgress";
	public static final String ARCHIVED = "Archived";
	
	public Properties getControllerFieldsProperties(){
		return controllerFieldsProps;
	}
	
		
	/**
	 * @return the sipType
	 */
	public String getSipType(){
		return controllerFieldsProps.getProperty("type");
	}
	/**
	 * @param sipType the sipType to set
	 */
	public void setSipType(String sipType) {
		if (sipType.equals(ControllerFields.MASTER) 
				|| sipType.equals(ControllerFields.DELTA)){
			this.controllerFieldsProps.setProperty("type", sipType);
		} else{
			log.error("sipType must be Master or Delta, sipType is not setted");
		}

	}
	
	public void writeControllerFields(){
		persistMdFile(this.getControllerFieldsProperties());
	}
	
	private void persistMdFile(Properties sipProps){
		try{
			File mdFile = new File(Configuration.getMetadataDir() + sipProps.getProperty("package") + suffix);
			FileOutputStream fos;
			fos = new FileOutputStream(mdFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			sipProps.store(bos, "MD File for Package  " + sipProps.getProperty("filename"));

		} catch (Exception e) {
			log.error(e);
		}

	}

	/**
	 * @return the sipSequenceNumber
	 */
	public int getSipSequenceNumber() {
		return Integer.parseInt(controllerFieldsProps.getProperty("local_sequence_number"));
	}
	/**
	 * @param sipSequenceNumber the sipSequenceNumber to set
	 */
	public void setSipSequenceNumber(int sipSequenceNumber) {
		this.controllerFieldsProps.setProperty("local_sequence_number", Integer.toString(sipSequenceNumber));
	}
	/**
	 * @return the sipState
	 */
	public String getSipState() {
		return controllerFieldsProps.getProperty("excpected_transfer_state");
	}
	/**
	 * @param sipState the sipState to set
	 */
	public void setSipState(String sipState) {
		this.controllerFieldsProps.setProperty("excpected_transfer_state", sipState);
	}
	/**
	 * @return the sipUrn
	 */
	public String getSipUrn() {
		return controllerFieldsProps.getProperty("urn");
	}
	/**
	 * @param sipUrn the sipUrn to set
	 */
	public void setSipUrn(String sipUrn) {
		this.controllerFieldsProps.setProperty("urn", sipUrn);
	}
	/**
	 * @return the remoteLastSequenceNumber
	 */
	public int getRemoteLastSequenceNumber() {
		return Integer.parseInt(controllerFieldsProps.getProperty("last_remote_sequence_number"));
	}
	/**
	 * @param remoteLastSequenceNumber the remoteLastSequenceNumber to set
	 */
	public void setRemoteLastSequenceNumber(int remoteLastSequenceNumber) {
		this.controllerFieldsProps.setProperty("last_remote_sequence_number", Integer.toString(remoteLastSequenceNumber));
	}
	/**
	 * @param sipFileName the sipFileName to set
	 */
	public void setSipFileName(String sipFileName) {
		this.controllerFieldsProps.setProperty("filename", sipFileName);
	}
	/**
	 * @return the sipFileName
	 */
	public String getSipFileName() {
		return controllerFieldsProps.getProperty("filename");
	}
	/**
	 * @param sipFileName the sipFileName to set
	 */
	public void setSipPackageName(String sipPackageName) {
		this.controllerFieldsProps.setProperty("package", sipPackageName);
	}
	/**
	 * @return the sipFileName
	 */
	public String getSipPackageName() {
		return controllerFieldsProps.getProperty("package");
	}
	/**
	 * @return the remoteLastSequenceNumber
	 */
	public int getNumberOfTouches() {
		return Integer.parseInt(controllerFieldsProps.getProperty("count_touches"));
	}
	/**
	 * @param remoteLastSequenceNumber the remoteLastSequenceNumber to set
	 */
	public void setNumberOfTouches(int numberOfTouches) {
		this.controllerFieldsProps.setProperty("count_touches", Integer.toString(numberOfTouches));
	}
	
	
	
}
