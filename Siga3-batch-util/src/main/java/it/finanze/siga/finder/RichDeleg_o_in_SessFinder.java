package it.finanze.siga.finder;


public class RichDeleg_o_in_SessFinder extends BasePaginateFinder  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1216717690250639746L;
	
	/**
	 * cf utente loggato in sessione
	 */
	private String codiceCdr;
	private String codiceFiscaleDelegato;
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getCodiceFiscaleDelegato() {
		return codiceFiscaleDelegato;
	}
	public void setCodiceFiscaleDelegato(String codiceFiscaleDelegato) {
		this.codiceFiscaleDelegato = codiceFiscaleDelegato;
	}

}
