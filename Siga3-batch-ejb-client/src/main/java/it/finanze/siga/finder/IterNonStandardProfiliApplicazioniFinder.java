package it.finanze.siga.finder;

public class IterNonStandardProfiliApplicazioniFinder extends BasePaginateFinder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6430492738920855196L;
	
	private String codice_ambito;
	private String codice_applicazione;
	private String descrizione_applicazione;
	private String cfUtente;
	private boolean isStampaIter;
	
	public String getCodice_ambito() {
		return codice_ambito;
	}
	public void setCodice_ambito(String codice_ambito) {
		this.codice_ambito = codice_ambito;
	}
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
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public boolean isStampaIter() {
		return isStampaIter;
	}
	public void setStampaIter(boolean isStampaIter) {
		this.isStampaIter = isStampaIter;
	}

	
}
