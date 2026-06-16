package it.finanze.siga.finder;

import java.io.Serializable;


public class UfficioFinder implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codiceCdr;
	private String codiceUfficio;
	private String tipoUfficio;
	private String regione;
	private String provincia;
	private String dataInizioVal;
	private String dataFineVal;
	
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getTipoUfficio() {
		return tipoUfficio;
	}
	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDataInizioVal() {
		return dataInizioVal;
	}
	public void setDataInizioVal(String dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	public String getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(String dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	
}
