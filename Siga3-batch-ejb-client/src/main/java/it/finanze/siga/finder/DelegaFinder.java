package it.finanze.siga.finder;

import java.util.Date;

public class DelegaFinder extends BasePaginateFinder {

	private static final long serialVersionUID = 6430267358866330799L;
	private String codiceFiscaleDelegante;
	private String codiceFiscaleDelegato;
	private String cdrDelegante;
	private String cdrDelegato;
	private String ruoloDelegato;
	private Date dataScadenza;
	private String motivoRevoca; 
	private Date dataInizioVal;
	private Date dataFineVal;
	private Integer idAuditInizio;
	private String cfInizio;
	private Integer idAuditFine;
	private Integer giorniValiditaRestanti;
	private Integer minOre;	
	private String cfFine;
	private String inviataMailInScadenza = "";
	
	private String nota;
	
	

	public String getInviataMailInScadenza() {
		return inviataMailInScadenza;
	}

	public void setInviataMailInScadenza(String inviataMailInScadenza) {
		this.inviataMailInScadenza = inviataMailInScadenza;
	}

	public String getCodiceFiscaleDelegante() {
		return codiceFiscaleDelegante;
	}

	public void setCodiceFiscaleDelegante(String codiceFiscaleDelegante) {
		this.codiceFiscaleDelegante = codiceFiscaleDelegante;
	}

	public String getCodiceFiscaleDelegato() {
		return codiceFiscaleDelegato;
	}

	public void setCodiceFiscaleDelegato(String codiceFiscaleDelegato) {
		this.codiceFiscaleDelegato = codiceFiscaleDelegato;
	}

	public String getRuoloDelegato() {
		return ruoloDelegato;
	}

	public void setRuoloDelegato(String ruoloDelegato) {
		this.ruoloDelegato = ruoloDelegato;
	}

	public Date getDataInizioVal() {
		return dataInizioVal;
	}

	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}

	public String getMotivoRevoca() {
		return motivoRevoca;
	}

	public void setMotivoRevoca(String motivoRevoca) {
		this.motivoRevoca = motivoRevoca;
	}

	public String getCdrDelegante() {
		return cdrDelegante;
	}

	public void setCdrDelegante(String cdrDelegante) {
		this.cdrDelegante = cdrDelegante;
	}

	public String getCdrDelegato() {
		return cdrDelegato;
	}

	public void setCdrDelegato(String cdrDelegato) {
		this.cdrDelegato = cdrDelegato;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public Date getDataFineVal() {
		return dataFineVal;
	}

	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}

	public Integer getIdAuditInizio() {
		return idAuditInizio;
	}

	public void setIdAuditInizio(Integer idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}

	public String getCfInizio() {
		return cfInizio;
	}

	public void setCfInizio(String cfInizio) {
		this.cfInizio = cfInizio;
	}

	public Integer getIdAuditFine() {
		return idAuditFine;
	}

	public void setIdAuditFine(Integer idAuditFine) {
		this.idAuditFine = idAuditFine;
	}

	public String getCfFine() {
		return cfFine;
	}

	public void setCfFine(String cfFine) {
		this.cfFine = cfFine;
	}

	public Integer getGiorniValiditaRestanti() {
		return giorniValiditaRestanti;
	}

	public void setGiorniValiditaRestanti(Integer giorniValiditaRestanti) {
		this.giorniValiditaRestanti = giorniValiditaRestanti;
	}

	public Integer getMinOre() {
		return minOre;
	}

	public void setMinOre(Integer minOre) {
		this.minOre = minOre;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}


	
}
