/**
 * 
 */
package de.nrw.hbz.dns.simpleTransferClient;

/**
 * @author aquast
 *
 */
public class Sip {
	private Sip(){
		
	}
	
	public int getPackageNumber(){
		return 0;
	}
	
	public static class SipFactory{

		public static Sip getInstance(){
			Sip sip = new Sip();
			return sip;
		}

		public static Sip getInstance(String type){
			Sip sip = new Sip();
			return sip;
		}

	
	}
}
