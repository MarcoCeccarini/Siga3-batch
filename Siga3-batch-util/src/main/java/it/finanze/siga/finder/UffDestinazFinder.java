package it.finanze.siga.finder;

import java.util.List;


public class UffDestinazFinder extends BasePaginateFinder  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1216717690250639746L;
	
	/**
	 * cf utente loggato in sessione
	 */
	private String codiceCdrOperatore;
	private String ufficioOperatore;
	private String strutturaSelezionata;
	private List<String> struttureScelte;
	
	public UffDestinazFinder(String codiceCdrOperatore) {
		super();
		this.codiceCdrOperatore = codiceCdrOperatore;
	}

	public String getCodiceCdrOperatore() {
		return codiceCdrOperatore;
	}

	public void setCodiceCdrOperatore(String codiceCdrOperatore) {
		this.codiceCdrOperatore = codiceCdrOperatore;
	}

	public String getUfficioOperatore() {
		return ufficioOperatore;
	}

	public void setUfficioOperatore(String ufficioOperatore) {
		this.ufficioOperatore = ufficioOperatore;
	}

	public List<String> getStruttureScelte() {
		return struttureScelte;
	}

	public void setStruttureScelte(List<String> struttureScelte) {
		this.struttureScelte = struttureScelte;
	}

	public String getStrutturaSelezionata() {
		return strutturaSelezionata;
	}

	public void setStrutturaSelezionata(String strutturaSelezionata) {
		this.strutturaSelezionata = strutturaSelezionata;
	}

}
