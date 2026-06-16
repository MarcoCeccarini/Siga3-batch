package it.finanze.scheduler.bean;

import java.io.Serializable;

public class ProfiloRichiestaVisibilitaBean extends ProfiloRichiestaBean implements Serializable{

	private static final long serialVersionUID = -5165970110979804389L;
	
	private String codiceFiscale;
	private String idCaricamento;
	private String azione;

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIdCaricamento() {
		return idCaricamento;
	}

	public void setIdCaricamento(String idCaricamento) {
		this.idCaricamento = idCaricamento;
	}

	public String getAzione() {
		return azione;
	}

	public void setAzione(String azione) {
		this.azione = azione;
	}
	
	
}
