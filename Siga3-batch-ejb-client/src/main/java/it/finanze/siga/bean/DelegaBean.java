package it.finanze.siga.bean;

import it.finanze.siga.util.Constants;

import java.io.Serializable;
import java.util.Date;

public class DelegaBean implements Serializable {

	private static final long serialVersionUID = 5286553371273590932L;
	
	private String codiceFiscaleDelegante;
	private String nomeDelegante;
	private String cognomeDelegante;
	private String cdrDelegante;
	private String descrStrutturaDelegante;
	private String descrUfficioDelegante;
	private String descrCDRDelegante;	
	
	private String codiceFiscaleDelegato;
	private String nomeDelegato;
	private String cognomeDelegato;
	private String cdrDelegato;
	private String descrCdrDelegato;	
	
	private String ruoloDelegato;
	private Date dataScadenza;
	private String dataScadenzaStr; 
	
	private Date dataCreazione;
	private String dataCreazioneStr;
	


	private String motivoRevoca;
	private String motivoRevocaDesc;
	
	private Date dataInizioVal;
	private String dataInizioValStr;
	
	private Date dataFineVal;
	private String dataFineValStr;
	
	private Integer idAuditInizio;
	private String cfInizio;
	private String cognomeInizio;
	private String nomeInizio;
	
	private Integer idAuditFine;
	private String cfFine;
	private String cognomeFine;
	private String nomeFine;
	
	private String dataOraEstrazione;
	private String intestazioneExport;
	
	private String ruoloAmministratoreInizio;
	private String ruoloAmministratoreFine;
	private String cdrRuoloInizio;
	private String cdrRuoloFine;

	private String nota;
	private Integer idDelega; // per tabella documenti allegati;
	
	
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

	public String getNomeDelegante() {
		return nomeDelegante;
	}

	public void setNomeDelegante(String nomeDelegante) {
		this.nomeDelegante = nomeDelegante;
	}

	public String getCognomeDelegante() {
		return cognomeDelegante;
	}

	public void setCognomeDelegante(String cognomeDelegante) {
		this.cognomeDelegante = cognomeDelegante;
	}

	public String getNomeDelegato() {
		return nomeDelegato;
	}

	public void setNomeDelegato(String nomeDelegato) {
		this.nomeDelegato = nomeDelegato;
	}

	public String getCognomeDelegato() {
		return cognomeDelegato;
	}

	public void setCognomeDelegato(String cognomeDelegato) {
		this.cognomeDelegato = cognomeDelegato;
	}

	public String getDataOraEstrazione() {
		return dataOraEstrazione;
	}

	public void setDataOraEstrazione(String dataOraEstrazione) {
		this.dataOraEstrazione = dataOraEstrazione;
	}

	public String getDataInizioValStr() {
		return dataInizioValStr;
	}

	public void setDataInizioValStr(String dataInizioValStr) {
		this.dataInizioValStr = dataInizioValStr;
	}

	public String getDataFineValStr() {
		return dataFineValStr;
	}

	public void setDataFineValStr(String dataFineValStr) {
		this.dataFineValStr = dataFineValStr;
	}

	public String getDataScadenzaStr() {
		return dataScadenzaStr;
	}

	public void setDataScadenzaStr(String dataScadenzaStr) {
		this.dataScadenzaStr = dataScadenzaStr;
	}

	public String getIntestazioneExport() {
		return intestazioneExport;
	}

	public void setIntestazioneExport(String intestazioneExport) {
		this.intestazioneExport = intestazioneExport;
	}

	public String getDescrStrutturaDelegante() {
		return descrStrutturaDelegante;
	}

	public void setDescrStrutturaDelegante(String descrStrutturaDelegante) {
		this.descrStrutturaDelegante = descrStrutturaDelegante;
	}

	public String getDescrUfficioDelegante() {
		return descrUfficioDelegante;
	}

	public void setDescrUfficioDelegante(String descrUfficioDelegante) {
		this.descrUfficioDelegante = descrUfficioDelegante;
	}

	public String getDescrCDRDelegante() {
		return descrCDRDelegante;
	}

	public void setDescrCDRDelegante(String descrCDRDelegante) {
		this.descrCDRDelegante = descrCDRDelegante;
	}

	public String getDescrCdrDelegato() {
		return descrCdrDelegato;
	}

	public void setDescrCdrDelegato(String descrCdrDelegato) {
		this.descrCdrDelegato = descrCdrDelegato;
	}

	public String getCognomeInizio() {
		return cognomeInizio;
	}

	public void setCognomeInizio(String cognomeInizio) {
		this.cognomeInizio = cognomeInizio;
	}

	public String getNomeInizio() {
		return nomeInizio;
	}

	public void setNomeInizio(String nomeInizio) {
		this.nomeInizio = nomeInizio;
	}

	public String getCognomeFine() {
		return cognomeFine;
	}

	public void setCognomeFine(String cognomeFine) {
		this.cognomeFine = cognomeFine;
	}

	public String getNomeFine() {
		return nomeFine;
	}

	public void setNomeFine(String nomeFine) {
		this.nomeFine = nomeFine;
	}
	
	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getDataCreazioneStr() {
		return dataCreazioneStr;
	}

	public void setDataCreazioneStr(String dataCreazioneStr) {
		this.dataCreazioneStr = dataCreazioneStr;
	}

	public String getMotivoRevocaDesc() {
		String descrizione = null;
		if(motivoRevoca != null){
			if(motivoRevoca.equals(Constants.REVOCATA))
				descrizione = "Revocata";
			else if(motivoRevoca.equals(Constants.SCADUTA))
				descrizione = "Scaduta";
		}
		return descrizione;
	}

	public void setMotivoRevocaDesc(String motivoRevocaDesc) {
		this.motivoRevocaDesc = motivoRevocaDesc;
	}

	public String getRuoloAmministratoreInizio() {
		String output = null;
		if(ruoloAmministratoreInizio.equals("AMMINISTRATORE_CENTRALE"))
			output = "Amministratore centrale";
		else if(ruoloAmministratoreInizio.equals("AMMINISTRATORE_REGIONALE"))
			output = "Amministratore regionale";
		if(ruoloAmministratoreInizio.equals("AMMINISTRATORE_LOCALE"))
			output = "Amministratore locale";
		return output;
	
	}

	public void setRuoloAmministratoreInizio(String ruoloAmministratoreInizio) {
		this.ruoloAmministratoreInizio = ruoloAmministratoreInizio;
	}

	public String getRuoloAmministratoreFine() {
		String output = null;
		if(ruoloAmministratoreFine.equals("AMMINISTRATORE_CENTRALE"))
			output = "Amministratore centrale";
		else if(ruoloAmministratoreFine.equals("AMMINISTRATORE_REGIONALE"))
			output = "Amministratore regionale";
		if(ruoloAmministratoreFine.equals("AMMINISTRATORE_LOCALE"))
			output = "Amministratore locale";
		return output;
	}

	public void setRuoloAmministratoreFine(String ruoloAmministratoreFine) {
		this.ruoloAmministratoreFine = ruoloAmministratoreFine;
	}

	public String getCdrRuoloInizio() {
		return cdrRuoloInizio;
	}

	public void setCdrRuoloInizio(String cdrRuoloInizio) {
		this.cdrRuoloInizio = cdrRuoloInizio;
	}

	public String getCdrRuoloFine() {
		return cdrRuoloFine;
	}

	public void setCdrRuoloFine(String cdrRuoloFine) {
		this.cdrRuoloFine = cdrRuoloFine;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Integer getIdDelega() {
		return idDelega;
	}

	public void setIdDelega(Integer idDelega) {
		this.idDelega = idDelega;
	}
	
	

}



