package it.finanze.scheduler.bean;

import java.io.Serializable;

public class SalvaguardiaBean implements Serializable{
	

	private static final long serialVersionUID = 4961713585964683081L;
	private String codiceUfficioPartenza;
	private String codiceUfficioDestinazione;
	private String codiceCdrDestinazione;
	private String codiceCdrPartenza;
	
	public String getCodiceUfficioPartenza() {
		return codiceUfficioPartenza;
	}
	public void setCodiceUfficioPartenza(String codiceUfficioPartenza) {
		this.codiceUfficioPartenza = codiceUfficioPartenza;
	}
	public String getCodiceUfficioDestinazione() {
		return codiceUfficioDestinazione;
	}
	public void setCodiceUfficioDestinazione(String codiceUfficioDestinazione) {
		this.codiceUfficioDestinazione = codiceUfficioDestinazione;
	}
	public String getCodiceCdrDestinazione() {
		return codiceCdrDestinazione;
	}
	public void setCodiceCdrDestinazione(String codiceCdrDestinazione) {
		this.codiceCdrDestinazione = codiceCdrDestinazione;
	}
	public String getCodiceCdrPartenza() {
		return codiceCdrPartenza;
	}
	public void setCodiceCdrPartenza(String codiceCdrPartenza) {
		this.codiceCdrPartenza = codiceCdrPartenza;
	}
	
}
