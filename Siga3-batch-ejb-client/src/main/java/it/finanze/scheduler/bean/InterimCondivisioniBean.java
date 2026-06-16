package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class InterimCondivisioniBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cfUtente;
	private Integer progr;
	private String codiceCdr;
	private String tipoCdr;
	private String codiceCdrResp;
	private String tipoCdrResp;
	private Date dataElab;
	
	private String cognome;
	private String nome;
	private String descrizioneCdr;
	private String descrizioneCdrResp;
	
	public InterimCondivisioniBean() {
		// TODO Stub di costruttore generato automaticamente
	}

	public String getCfUtente() {
		return cfUtente;
	}

	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}

	public Integer getProgr() {
		return progr;
	}

	public void setProgr(Integer progr) {
		this.progr = progr;
	}

	public String getCodiceCdr() {
		return codiceCdr;
	}

	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}

	public String getTipoCdr() {
		return tipoCdr;
	}

	public void setTipoCdr(String tipoCdr) {
		this.tipoCdr = tipoCdr;
	}

	public String getCodiceCdrResp() {
		return codiceCdrResp;
	}

	public void setCodiceCdrResp(String codiceCdrResp) {
		this.codiceCdrResp = codiceCdrResp;
	}

	public String getTipoCdrResp() {
		return tipoCdrResp;
	}

	public void setTipoCdrResp(String tipoCdrResp) {
		this.tipoCdrResp = tipoCdrResp;
	}

	public Date getDataElab() {
		return dataElab;
	}

	public void setDataElab(Date dataElab) {
		this.dataElab = dataElab;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizioneCdr() {
		return descrizioneCdr;
	}

	public void setDescrizioneCdr(String descrizioneCdr) {
		this.descrizioneCdr = descrizioneCdr;
	}

	public String getDescrizioneCdrResp() {
		return descrizioneCdrResp;
	}

	public void setDescrizioneCdrResp(String descrizioneCdrResp) {
		this.descrizioneCdrResp = descrizioneCdrResp;
	}

	
}


	