package it.finanze.siga.bean;

import java.io.Serializable;

public class StatoRichiestaBean extends BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1473150994312617547L;

	private String stato,
	               descrizione,
	               campoRegRichieste;
	private boolean cancellabile,
	                archiviabile,
	                emailDelegante,
	                emailDelegato;
	
	

	public String getCampoRegRichieste() {
		return campoRegRichieste;
	}

	public void setCampoRegRichieste(String campoRegRichieste) {
		this.campoRegRichieste = campoRegRichieste;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public boolean isCancellabile() {
		return cancellabile;
	}

	public void setCancellabile(boolean cancellabile) {
		this.cancellabile = cancellabile;
	}

	public boolean isArchiviabile() {
		return archiviabile;
	}

	public void setArchiviabile(boolean archiviabile) {
		this.archiviabile = archiviabile;
	}

	public boolean isEmailDelegante() {
		return emailDelegante;
	}

	public void setEmailDelegante(boolean emailDelegante) {
		this.emailDelegante = emailDelegante;
	}

	public boolean isEmailDelegato() {
		return emailDelegato;
	}

	public void setEmailDelegato(boolean emailDelegato) {
		this.emailDelegato = emailDelegato;
	}

	
}
