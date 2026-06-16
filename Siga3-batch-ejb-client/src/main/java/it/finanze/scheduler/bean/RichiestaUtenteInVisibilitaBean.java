package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class RichiestaUtenteInVisibilitaBean implements Serializable{

	private static final long serialVersionUID = -4427500272443585503L;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String codiceCdr;
	private String cfResponsabileRevoca;
	private long idRichiestaVisibilita;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private Date dataRevoca;
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getCfResponsabileRevoca() {
		return cfResponsabileRevoca;
	}
	public void setCfResponsabileRevoca(String cfResponsabileRevoca) {
		this.cfResponsabileRevoca = cfResponsabileRevoca;
	}
	public long getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(long idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public Date getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	public Date getDataRevoca() {
		return dataRevoca;
	}
	public void setDataRevoca(Date dataRevoca) {
		this.dataRevoca = dataRevoca;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	
}
