package it.finanze.siga.bean;

import java.util.Date;
import java.util.List;

public class VisibilitaBean {
	
	private String codCdrOperatore;
	private String descrCdrOperatore;
	private String codCdrVisOperatore;
	private String descrCdrVisOperatore;
	private String cfOperatore;
	private String nomeOperatore;
	private String cognomeOperatore;
	private String dataScadenzaVis;
	private Date dataScadenza;
	private String tipoUtenteString;
	private String tipologiaEMansioneExport;
	
	List<ProfiloBean> listProfili;
	List<ProfiloCompetenzaBean> listProfiliCompetenza;

	
	public String getDescrCdrOperatore() {
		return descrCdrOperatore;
	}
	public void setDescrCdrOperatore(String descrCdrOperatore) {
		this.descrCdrOperatore = descrCdrOperatore;
	}
	public String getDescrCdrVisOperatore() {
		return descrCdrVisOperatore;
	}
	public void setDescrCdrVisOperatore(String descrCdrVisOperatore) {
		this.descrCdrVisOperatore = descrCdrVisOperatore;
	}
	public String getCfOperatore() {
		return cfOperatore;
	}
	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}
	public String getNomeOperatore() {
		return nomeOperatore;
	}
	public void setNomeOperatore(String nomeOperatore) {
		this.nomeOperatore = nomeOperatore;
	}
	public String getCognomeOperatore() {
		return cognomeOperatore;
	}
	public void setCognomeOperatore(String cognomeOperatore) {
		this.cognomeOperatore = cognomeOperatore;
	}
	public List<ProfiloBean> getListProfili() {
		return listProfili;
	}
	public void setListProfili(List<ProfiloBean> listProfili) {
		this.listProfili = listProfili;
	}
	public String getDataScadenzaVis() {
		return dataScadenzaVis;
	}
	public void setDataScadenzaVis(String dataScadenzaVis) {
		this.dataScadenzaVis = dataScadenzaVis;
	}
	public String getTipoUtenteString() {
		return tipoUtenteString;
	}
	public void setTipoUtenteString(String tipoUtenteString) {
		this.tipoUtenteString = tipoUtenteString;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public List<ProfiloCompetenzaBean> getListProfiliCompetenza() {
		return listProfiliCompetenza;
	}
	public void setListProfiliCompetenza(
			List<ProfiloCompetenzaBean> listProfiliCompetenza) {
		this.listProfiliCompetenza = listProfiliCompetenza;
	}
	public String getCodCdrOperatore() {
		return codCdrOperatore;
	}
	public void setCodCdrOperatore(String codCdrOperatore) {
		this.codCdrOperatore = codCdrOperatore;
	}
	public String getCodCdrVisOperatore() {
		return codCdrVisOperatore;
	}
	public void setCodCdrVisOperatore(String codCdrVisOperatore) {
		this.codCdrVisOperatore = codCdrVisOperatore;
	}
	public String getTipologiaEMansioneExport() {
		return tipologiaEMansioneExport;
	}
	public void setTipologiaEMansioneExport(String tipologiaEMansioneExport) {
		this.tipologiaEMansioneExport = tipologiaEMansioneExport;
	}

	
}
