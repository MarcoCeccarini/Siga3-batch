package it.finanze.scheduler.bean;

import java.io.Serializable;

public class TipoUfficio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoUfficio;
	private String descrizioneTipoUfficio;
	private String ramo;
	private String campoOrdinamento;
	private String livelloGerarchico;
	private String richiedente;
	private String autorizzatoreILiv;
	private String autorizzatoreIILiv;
	private String gestoreOperatori;
	

	public TipoUfficio() {
		// TODO Stub di costruttore generato automaticamente
	}


	public String getTipoUfficio() {
		return tipoUfficio;
	}


	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}


	public String getDescrizioneTipoUfficio() {
		return descrizioneTipoUfficio;
	}


	public void setDescrizioneTipoUfficio(String descrizioneTipoUfficio) {
		this.descrizioneTipoUfficio = descrizioneTipoUfficio;
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


	public String getLivelloGerarchico() {
		return livelloGerarchico;
	}


	public void setLivelloGerarchico(String livelloGerarchico) {
		this.livelloGerarchico = livelloGerarchico;
	}


	public String getRichiedente() {
		return richiedente;
	}


	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}


	public String getAutorizzatoreILiv() {
		return autorizzatoreILiv;
	}


	public void setAutorizzatoreILiv(String autorizzatoreILiv) {
		this.autorizzatoreILiv = autorizzatoreILiv;
	}


	public String getAutorizzatoreIILiv() {
		return autorizzatoreIILiv;
	}


	public void setAutorizzatoreIILiv(String autorizzatoreIILiv) {
		this.autorizzatoreIILiv = autorizzatoreIILiv;
	}


	public String getGestoreOperatori() {
		return gestoreOperatori;
	}


	public void setGestoreOperatori(String gestoreOperatori) {
		this.gestoreOperatori = gestoreOperatori;
	}

}
