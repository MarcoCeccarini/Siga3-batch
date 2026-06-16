package it.finanze.scheduler.bean;

import java.io.Serializable;

public class RaggruppamentoRichiesteOperatoreBean implements Serializable{

	
	private static final long serialVersionUID = 1985061476076736571L;
	public String codiceFiscale;
	public String evento;
	public String codiceAmbitoApplicativo;
	public String idRichiestaVisibilita;
	public int numero;
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getCodiceAmbitoApplicativo() {
		return codiceAmbitoApplicativo;
	}
	public void setCodiceAmbitoApplicativo(String codiceAmbitoApplicativo) {
		this.codiceAmbitoApplicativo = codiceAmbitoApplicativo;
	}
	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
