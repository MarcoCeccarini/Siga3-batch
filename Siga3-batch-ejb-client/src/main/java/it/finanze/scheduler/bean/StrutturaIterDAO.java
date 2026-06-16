package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.sql.Date;

public class StrutturaIterDAO implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int identificativoIter;
	private String tipoUfficioRichiedente;
	private String iterStandard;
	private String tipoCdrAutorizILiv;
	private String cdrAutorizILiv;
	private String cf_I;
	private String previstoAutorizzatoreIILiv;
	private String tipoCdrAutorizIILiv;
	private String cdrAutorizIILiv;
	private String cf_II;
	private String gestOperStrutRichiedente;
	private String gestOperStrutAutorILiv;
	private String gestOperStrutAutorIILiv;
	private String gruppoGestoriOperatori;
	private String cdrGestoriOperatori;
	//private String dataInizioVal;
	private Date dataInizioVal;
	//private String dataFineVal;
	private Date dataFineVal;
	private int idAuditInizio;
	private String codiceFiscaleInizio;
	private int idAuditFine;
	private String codiceFiscaleFine;
	
	
	public StrutturaIterDAO() {
		// TODO Stub di costruttore generato automaticamente
	}


	public int getIdentificativoIter() {
		return identificativoIter;
	}


	public void setIdentificativoIter(int identificativoIter) {
		this.identificativoIter = identificativoIter;
	}


	public String getTipoUfficioRichiedente() {
		return tipoUfficioRichiedente;
	}


	public void setTipoUfficioRichiedente(String tipoUfficioRichiedente) {
		this.tipoUfficioRichiedente = tipoUfficioRichiedente;
	}


	public String getIterStandard() {
		return iterStandard;
	}


	public void setIterStandard(String iterStandard) {
		this.iterStandard = iterStandard;
	}


	public String getTipoCdrAutorizILiv() {
		return tipoCdrAutorizILiv;
	}


	public void setTipoCdrAutorizILiv(String tipoCdrAutorizILiv) {
		this.tipoCdrAutorizILiv = tipoCdrAutorizILiv;
	}


	public String getCdrAutorizILiv() {
		return cdrAutorizILiv;
	}


	public void setCdrAutorizILiv(String cdrAutorizILiv) {
		this.cdrAutorizILiv = cdrAutorizILiv;
	}


	public String getCf_I() {
		return cf_I;
	}


	public void setCf_I(String cf_I) {
		this.cf_I = cf_I;
	}


	public String getPrevistoAutorizzatoreIILiv() {
		return previstoAutorizzatoreIILiv;
	}


	public void setPrevistoAutorizzatoreIILiv(String previstoAutorizzatoreIILiv) {
		this.previstoAutorizzatoreIILiv = previstoAutorizzatoreIILiv;
	}


	public String getTipoCdrAutorizIILiv() {
		return tipoCdrAutorizIILiv;
	}


	public void setTipoCdrAutorizIILiv(String tipoCdrAutorizIILiv) {
		this.tipoCdrAutorizIILiv = tipoCdrAutorizIILiv;
	}


	public String getCdrAutorizIILiv() {
		return cdrAutorizIILiv;
	}


	public void setCdrAutorizIILiv(String cdrAutorizIILiv) {
		this.cdrAutorizIILiv = cdrAutorizIILiv;
	}


	public String getCf_II() {
		return cf_II;
	}


	public void setCf_II(String cf_II) {
		this.cf_II = cf_II;
	}


	public String getGestOperStrutRichiedente() {
		return gestOperStrutRichiedente;
	}


	public void setGestOperStrutRichiedente(String gestOperStrutRichiedente) {
		this.gestOperStrutRichiedente = gestOperStrutRichiedente;
	}


	public String getGestOperStrutAutorILiv() {
		return gestOperStrutAutorILiv;
	}


	public void setGestOperStrutAutorILiv(String gestOperStrutAutorILiv) {
		this.gestOperStrutAutorILiv = gestOperStrutAutorILiv;
	}


	public String getGestOperStrutAutorIILiv() {
		return gestOperStrutAutorIILiv;
	}


	public void setGestOperStrutAutorIILiv(String gestOperStrutAutorIILiv) {
		this.gestOperStrutAutorIILiv = gestOperStrutAutorIILiv;
	}


	public String getGruppoGestoriOperatori() {
		return gruppoGestoriOperatori;
	}


	public void setGruppoGestoriOperatori(String gruppoGestoriOperatori) {
		this.gruppoGestoriOperatori = gruppoGestoriOperatori;
	}


	public String getCdrGestoriOperatori() {
		return cdrGestoriOperatori;
	}


	public void setCdrGestoriOperatori(String cdrGestoriOperatori) {
		this.cdrGestoriOperatori = cdrGestoriOperatori;
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


	public int getIdAuditInizio() {
		return idAuditInizio;
	}


	public void setIdAuditInizio(int idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}


	public String getCodiceFiscaleInizio() {
		return codiceFiscaleInizio;
	}


	public void setCodiceFiscaleInizio(String codiceFiscaleInizio) {
		this.codiceFiscaleInizio = codiceFiscaleInizio;
	}


	public int getIdAuditFine() {
		return idAuditFine;
	}


	public void setIdAuditFine(int idAuditFine) {
		this.idAuditFine = idAuditFine;
	}


	public String getCodiceFiscaleFine() {
		return codiceFiscaleFine;
	}


	public void setCodiceFiscaleFine(String codiceFiscaleFine) {
		this.codiceFiscaleFine = codiceFiscaleFine;
	}

	
}
