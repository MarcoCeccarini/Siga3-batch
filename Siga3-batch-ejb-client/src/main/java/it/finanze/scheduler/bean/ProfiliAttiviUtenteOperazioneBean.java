package it.finanze.scheduler.bean;

import java.io.Serializable;

public class ProfiliAttiviUtenteOperazioneBean extends ProfiliAttiviUtente implements Serializable {


	private static final long serialVersionUID = -2347914557956825066L;
	
	private String operazioneSuProfilo;

	public String getOperazioneSuProfilo() {
		return operazioneSuProfilo;
	}

	public void setOperazioneSuProfilo(String operazioneSuProfilo) {
		this.operazioneSuProfilo = operazioneSuProfilo;
	}
	
	
}
