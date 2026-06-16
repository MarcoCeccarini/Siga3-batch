package it.finanze.siga.bean;

import java.util.Date;

public class ProfiloCompetenzaBean extends ProfiloBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2486895685265521964L;
	
	private String tipoUfficio,
				   tipoUfficioDesc,
				   tipoCDR,
				   tipoCDRDesc,
				   ambitoDesc,
				   CDRDesc,
				   ufficioDesc;
	
	private String idRichiestaInizio;
	private String idRichiestaFine;


	private String cdrUffValiditaProfCod;
	private Date  cdrDataFineVal;
	
	/** GETTERS AND SETTERS */
	public String getTipoUfficio() {
		return tipoUfficio;
	}

	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}

	public String getTipoUfficioDesc() {
		return tipoUfficioDesc;
	}

	public void setTipoUfficioDesc(String tipoUfficioDesc) {
		this.tipoUfficioDesc = tipoUfficioDesc;
	}

	public String getTipoCDR() {
		return tipoCDR;
	}

	public void setTipoCDR(String tipoCDR) {
		this.tipoCDR = tipoCDR;
	}

	public String getTipoCDRDesc() {
		return tipoCDRDesc;
	}

	public void setTipoCDRDesc(String tipoCDRDesc) {
		this.tipoCDRDesc = tipoCDRDesc;
	}

	public String getAmbitoDesc() {
		return ambitoDesc;
	}

	public void setAmbitoDesc(String ambitoDesc) {
		this.ambitoDesc = ambitoDesc;
	}

	public String getCDRDesc() {
		return CDRDesc;
	}

	public void setCDRDesc(String cDRDesc) {
		CDRDesc = cDRDesc;
	}

	public String getUfficioDesc() {
		return ufficioDesc;
	}

	public void setUfficioDesc(String ufficioDesc) {
		this.ufficioDesc = ufficioDesc;
	}

	public String getIdRichiestaInizio() {
		return idRichiestaInizio;
	}

	public void setIdRichiestaInizio(String idRichiestaInizio) {
		this.idRichiestaInizio = idRichiestaInizio;
	}

	public String getIdRichiestaFine() {
		return idRichiestaFine;
	}

	public void setIdRichiestaFine(String idRichiestaFine) {
		this.idRichiestaFine = idRichiestaFine;
	}

	public String getCdrUffValiditaProfCod() {
		return cdrUffValiditaProfCod;
	}

	public void setCdrUffValiditaProfCod(String cdrUffValiditaProfCod) {
		this.cdrUffValiditaProfCod = cdrUffValiditaProfCod;
	}

	public Date getCdrDataFineVal() {
		return cdrDataFineVal;
	}

	public void setCdrDataFineVal(Date cdrDataFineVal) {
		this.cdrDataFineVal = cdrDataFineVal;
	}
	
	
	
}
