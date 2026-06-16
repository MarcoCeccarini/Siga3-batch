package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;

public class GestoreOperatoreBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9211338171676017851L;
	
	
	
	private String codiceApplicazione;
	private String codiceStruttura;
	private String codiceCDR;
	private String codiceFiscale;
	private Date dataInizioVal;
	private Date dataFineVal;
	private Long idAuditInizio;
	private Long idAuditFine;
	private String codiceFiscaleInizio;
	private String codiceFiscaleFine;
	
//	private UtenteBean utente;
	
	private String nome;
	private String cognome;
	private String cdrAssDesc;
	
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}
	public String getCodiceStruttura() {
		return codiceStruttura;
	}
	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public Date getDataInizioVal() {
		return dataInizioVal;
	}
	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	public Date getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	public String getCodiceFiscaleInizio() {
		return codiceFiscaleInizio;
	}
	public void setCodiceFiscaleInizio(String codiceFiscaleInizio) {
		this.codiceFiscaleInizio = codiceFiscaleInizio;
	}
	public String getCodiceFiscaleFine() {
		return codiceFiscaleFine;
	}
	public void setCodiceFiscaleFine(String codiceFiscaleFine) {
		this.codiceFiscaleFine = codiceFiscaleFine;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCdrAssDesc() {
		return cdrAssDesc;
	}
	public void setCdrAssDesc(String cdrAssDesc) {
		this.cdrAssDesc = cdrAssDesc;
	}
	public Long getIdAuditInizio() {
		return idAuditInizio;
	}
	public void setIdAuditInizio(Long idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}
	public Long getIdAuditFine() {
		return idAuditFine;
	}
	public void setIdAuditFine(Long idAuditFine) {
		this.idAuditFine = idAuditFine;
	}
	
}
