package it.finanze.scheduler.bean;

import java.io.Serializable;

public class TipoCdR implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoCdR;
	private String descrizioneTipoCdR;
	private String ramo;
	private String campoOrdinamento;
	private String cambioUfficio;
	
	

	public TipoCdR() {
		// TODO Stub di costruttore generato automaticamente
	}



	public String getTipoCdR() {
		return tipoCdR;
	}



	public void setTipoCdR(String tipoCdR) {
		this.tipoCdR = tipoCdR;
	}



	public String getDescrizioneTipoCdR() {
		return descrizioneTipoCdR;
	}



	public void setDescrizioneTipoCdR(String descrizioneTipoCdR) {
		this.descrizioneTipoCdR = descrizioneTipoCdR;
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
