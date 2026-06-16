package it.finanze.siga.bean;

import java.util.Date;

public class UtenteInVisibilitaBean {
	
	private String cfUtente;
	private String cdrVisibilita;
	private Integer idRichiestaVisibilita;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private Date dataRevoca;
	
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public String getCdrVisibilita() {
		return cdrVisibilita;
	}
	public void setCdrVisibilita(String cdrVisibilita) {
		this.cdrVisibilita = cdrVisibilita;
	}
	public Date getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public Integer getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(Integer idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public Date getDataRevoca() {
		return dataRevoca;
	}
	public void setDataRevoca(Date dataRevoca) {
		this.dataRevoca = dataRevoca;
	}
	
	
}
