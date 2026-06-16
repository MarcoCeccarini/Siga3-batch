package it.finanze.scheduler.bean;
import java.io.Serializable;

public class AssociazOperRichiAutor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cfOperatore;
	private String codiceCdROperatore;
	private String cfRichiedente;
	private String codiceCdRRichiedente;
	private String cfAutorizzatore;
	private String codiceCdRAutorizzatore;
	private String dataIniValidita;
	private String dataFineValidita;
	private String idAuditInizio;
	private String codiceFiscaleInizio;
	private String idAuditFine;
	private String codiceFiscaleFine;
	

	public AssociazOperRichiAutor() {
		// TODO Stub di costruttore generato automaticamente
	}


	public String getCfOperatore() {
		return cfOperatore;
	}


	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}


	public String getCodiceCdROperatore() {
		return codiceCdROperatore;
	}


	public void setCodiceCdROperatore(String codiceCdROperatore) {
		this.codiceCdROperatore = codiceCdROperatore;
	}


	public String getCfRichiedente() {
		return cfRichiedente;
	}


	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}


	public String getCodiceCdRRichiedente() {
		return codiceCdRRichiedente;
	}


	public void setCodiceCdRRichiedente(String codiceCdRRichiedente) {
		this.codiceCdRRichiedente = codiceCdRRichiedente;
	}


	public String getCfAutorizzatore() {
		return cfAutorizzatore;
	}


	public void setCfAutorizzatore(String cfAutorizzatore) {
		this.cfAutorizzatore = cfAutorizzatore;
	}


	public String getCodiceCdRAutorizzatore() {
		return codiceCdRAutorizzatore;
	}


	public void setCodiceCdRAutorizzatore(String codiceCdRAutorizzatore) {
		this.codiceCdRAutorizzatore = codiceCdRAutorizzatore;
	}


	public String getDataIniValidita() {
		return dataIniValidita;
	}


	public void setDataIniValidita(String dataIniValidita) {
		this.dataIniValidita = dataIniValidita;
	}


	public String getDataFineValidita() {
		return dataFineValidita;
	}


	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}


	public String getIdAuditInizio() {
		return idAuditInizio;
	}


	public void setIdAuditInizio(String idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}


	public String getCodiceFiscaleInizio() {
		return codiceFiscaleInizio;
	}


	public void setCodiceFiscaleInizio(String codiceFiscaleInizio) {
		this.codiceFiscaleInizio = codiceFiscaleInizio;
	}


	public String getIdAuditFine() {
		return idAuditFine;
	}


	public void setIdAuditFine(String idAuditFine) {
		this.idAuditFine = idAuditFine;
	}


	public String getCodiceFiscaleFine() {
		return codiceFiscaleFine;
	}


	public void setCodiceFiscaleFine(String codiceFiscaleFine) {
		this.codiceFiscaleFine = codiceFiscaleFine;
	}

}
