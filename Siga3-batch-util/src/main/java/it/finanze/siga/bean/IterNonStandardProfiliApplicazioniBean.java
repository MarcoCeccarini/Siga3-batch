package it.finanze.siga.bean;

import java.io.Serializable;

public class IterNonStandardProfiliApplicazioniBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8455187630905406410L;
	
	private String codice_applicazione;
	private String descrizione_applicazione;
	private String descrizione_ambito;
	
	public String getCodice_applicazione() {
		return codice_applicazione;
	}
	public void setCodice_applicazione(String codice_applicazione) {
		this.codice_applicazione = codice_applicazione;
	}
	public String getDescrizione_applicazione() {
		return descrizione_applicazione;
	}
	public void setDescrizione_applicazione(String descrizione_applicazione) {
		this.descrizione_applicazione = descrizione_applicazione;
	}
	public String getDescrizione_ambito() {
		return descrizione_ambito;
	}
	public void setDescrizione_ambito(String descrizione_ambito) {
		this.descrizione_ambito = descrizione_ambito;
	}

}
