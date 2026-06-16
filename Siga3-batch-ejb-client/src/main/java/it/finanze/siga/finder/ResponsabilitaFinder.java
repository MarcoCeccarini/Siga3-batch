package it.finanze.siga.finder;

import java.sql.Date;

public class ResponsabilitaFinder extends BaseFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -917185527664381822L;
	
	private String all;
	private Date ultimoAggiornamento;
	private String codApplicazione;
	private String appId;
	private String codAmbito;
	private Date dataInserimenro;
	private Date dataElaborazione; 
	private String codiceOrgRegolamento; 
	private String codiceUfficio; 
	private String codiceStruttura; 


	
	public String getCodiceOrgRegolamento() {
		return codiceOrgRegolamento;
	}
	public void setCodiceOrgRegolamento(String codiceOrgRegolamento) {
		this.codiceOrgRegolamento = codiceOrgRegolamento;
	}
	
	public String getAll() {
		return all;
	}
	public void setAll(String all) {
		this.all = all;
	}
	public Date getUltimoAggiornamento() {
		return ultimoAggiornamento;
	}
	public void setUltimoAggiornamento(Date ultimoAggiornamento) {
		this.ultimoAggiornamento = ultimoAggiornamento;
	}
	public String getCodApplicazione() {
		return codApplicazione;
	}
	public void setCodApplicazione(String codApplicazione) {
		this.codApplicazione = codApplicazione;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCodAmbito() {
		return codAmbito;
	}
	public void setCodAmbito(String codAmbito) {
		this.codAmbito = codAmbito;
	}
	public Date getDataInserimenro() {
		return dataInserimenro;
	}
	public void setDataInserimenro(Date dataInserimenro) {
		this.dataInserimenro = dataInserimenro;
	}
	public Date getDataElaborazione() {
		return dataElaborazione;
	}
	public void setDataElaborazione(Date dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public String getCodiceStruttura() {
		return codiceStruttura;
	}
	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
  

}
