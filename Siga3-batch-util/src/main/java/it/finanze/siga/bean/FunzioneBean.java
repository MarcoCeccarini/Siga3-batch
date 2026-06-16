package it.finanze.siga.bean;

import java.io.Serializable;

public class FunzioneBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2438721257743064217L;
	
	private String codiceFunzione;
	private String descrizione;
	private String codiceProfilo;
	
	public String getCodiceFunzione() {
		return codiceFunzione;
	}
	public void setCodiceFunzione(String codiceFunzione) {
		this.codiceFunzione = codiceFunzione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	
	
}
