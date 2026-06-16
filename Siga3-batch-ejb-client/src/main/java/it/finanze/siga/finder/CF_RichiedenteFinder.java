package it.finanze.siga.finder;

public class CF_RichiedenteFinder extends BaseFinder {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6352223658897933970L;
	
	private String codiceCDR;
	private String cfOperatore;

	public String getCdr_operatore() {
		return codiceCDR;
	}

	public void setCdr_operatore(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}

	public String getCfOperatore() {
		return cfOperatore;
	}

	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}
	
	

}
