package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class RimozioneDelegheBean implements Serializable{

	private static final long serialVersionUID = 4040947242932748200L;
	private String codiceCdrDelegante;
	private String ruoloDelegato;
	private String motivazioneDescrizione;
	private String cognomeDelegante;
	private String nomeDelegante;
	private String emailDelegante;
	private Date datafineValDelegante;
	private String cognomeDelegato;
	private String nomeDelegato;
	private String emailDelegato;
	private Date datafineValDelegato;
	
	public String getCodiceCdrDelegante() {
		return codiceCdrDelegante;
	}
	public void setCodiceCdrDelegante(String codiceCdrDelegante) {
		this.codiceCdrDelegante = codiceCdrDelegante;
	}
	public String getRuoloDelegato() {
		return ruoloDelegato;
	}
	public void setRuoloDelegato(String ruoloDelegato) {
		this.ruoloDelegato = ruoloDelegato;
	}
	public String getMotivazioneDescrizione() {
		return motivazioneDescrizione;
	}
	public void setMotivazioneDescrizione(String motivazioneDescrizione) {
		this.motivazioneDescrizione = motivazioneDescrizione;
	}
	public String getCognomeDelegante() {
		return cognomeDelegante;
	}
	public void setCognomeDelegante(String cognomeDelegante) {
		this.cognomeDelegante = cognomeDelegante;
	}
	public String getNomeDelegante() {
		return nomeDelegante;
	}
	public void setNomeDelegante(String nomeDelegante) {
		this.nomeDelegante = nomeDelegante;
	}
	public String getEmailDelegante() {
		return emailDelegante;
	}
	public void setEmailDelegante(String emailDelegante) {
		this.emailDelegante = emailDelegante;
	}
	public Date getDatafineValDelegante() {
		return datafineValDelegante;
	}
	public void setDatafineValDelegante(Date datafineValDelegante) {
		this.datafineValDelegante = datafineValDelegante;
	}
	public String getCognomeDelegato() {
		return cognomeDelegato;
	}
	public void setCognomeDelegato(String cognomeDelegato) {
		this.cognomeDelegato = cognomeDelegato;
	}
	public String getNomeDelegato() {
		return nomeDelegato;
	}
	public void setNomeDelegato(String nomeDelegato) {
		this.nomeDelegato = nomeDelegato;
	}
	public String getEmailDelegato() {
		return emailDelegato;
	}
	public void setEmailDelegato(String emailDelegato) {
		this.emailDelegato = emailDelegato;
	}
	public Date getDatafineValDelegato() {
		return datafineValDelegato;
	}
	public void setDatafineValDelegato(Date datafineValDelegato) {
		this.datafineValDelegato = datafineValDelegato;
	}
	
}
