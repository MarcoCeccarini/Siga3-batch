package it.finanze.scheduler.bean;

import java.io.Serializable;

public class EventiSistemaBean implements Serializable{
	
	 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String autorizzatore;
	private String richiedente;
	private String notaRichiesta;
	private String codiceEvento;
	private String descrizione;
	private String presaVisione;
	private String archiviazione;
	private String aggSistAutoriz;
	
	
	public String getAutorizzatore() {
		return autorizzatore;
	}
	public void setAutorizzatore(String autorizzatore) {
		this.autorizzatore = autorizzatore;
	}
	public String getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}
	public String getNotaRichiesta() {
		return notaRichiesta;
	}
	public void setNotaRichiesta(String notaRichiesta) {
		this.notaRichiesta = notaRichiesta;
	}
	public String getCodiceEvento() {
		return codiceEvento;
	}
	public void setCodiceEvento(String codiceEvento) {
		this.codiceEvento = codiceEvento;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getPresaVisione() {
		return presaVisione;
	}
	public void setPresaVisione(String presaVisione) {
		this.presaVisione = presaVisione;
	}
	public String getArchiviazione() {
		return archiviazione;
	}
	public void setArchiviazione(String archiviazione) {
		this.archiviazione = archiviazione;
	}
	public String getAggSistAutoriz() {
		return aggSistAutoriz;
	}
	public void setAggSistAutoriz(String aggSistAutoriz) {
		this.aggSistAutoriz = aggSistAutoriz;
	}
 
	 
	
}