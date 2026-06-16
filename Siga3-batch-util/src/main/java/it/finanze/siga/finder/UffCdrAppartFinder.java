package it.finanze.siga.finder;


public class UffCdrAppartFinder extends BaseFinder {
	public UffCdrAppartFinder(String codiceCDR) {
		super();
		this.codiceCDR = codiceCDR;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2281976558865885261L;
	/**
	 * 
	 */

	private String codiceCDR ;

	public String getCodiceCdr() {
		return codiceCDR;
	}

	public void setCodiceCdr(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	

}
