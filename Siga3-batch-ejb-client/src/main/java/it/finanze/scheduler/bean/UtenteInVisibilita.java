package it.finanze.scheduler.bean;

import java.io.Serializable;

public class UtenteInVisibilita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codiceCdR;
	private String idRichiestaVisibilita;
	private String cfUtente;

	public UtenteInVisibilita() {
		// TODO Stub di costruttore generato automaticamente
	}
	
	
	
	public String getCodiceCdR() {
		return codiceCdR;
	}
	public void setCodiceCdR(String codiceCdR) {
		this.codiceCdR = codiceCdR;
	}
	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}



	public String getCfUtente() {
		return cfUtente;
	}



	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}

}
