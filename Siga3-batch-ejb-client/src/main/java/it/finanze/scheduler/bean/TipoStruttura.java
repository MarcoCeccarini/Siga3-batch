package it.finanze.scheduler.bean;

import java.io.Serializable;

public class TipoStruttura implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoStruttura;
	private String descrizioneTipoStruttura;
	private String ramo;
	private String campoOrdinamento;
	private String cambioUfficio;
	

	public TipoStruttura() {
		// TODO Stub di costruttore generato automaticamente
	}


	public String getTipoStruttura() {
		return tipoStruttura;
	}


	public void setTipoStruttura(String tipoStruttura) {
		this.tipoStruttura = tipoStruttura;
	}


	public String getDescrizioneTipoStruttura() {
		return descrizioneTipoStruttura;
	}


	public void setDescrizioneTipoStruttura(String descrizioneTipoStruttura) {
		this.descrizioneTipoStruttura = descrizioneTipoStruttura;
	}


	public String getRamo() {
		return ramo;
	}


	public void setRamo(String ramo) {
		this.ramo = ramo;
	}


	public String getCampoOrdinamento() {
		return campoOrdinamento;
	}


	public void setCampoOrdinamento(String campoOrdinamento) {
		this.campoOrdinamento = campoOrdinamento;
	}


	public String getCambioUfficio() {
		return cambioUfficio;
	}


	public void setCambioUfficio(String cambioUfficio) {
		this.cambioUfficio = cambioUfficio;
	}

}
