package it.finanze.siga.bean;

import java.io.Serializable;

public class ComunicazioneEntity implements Serializable{

	private static final long serialVersionUID = -8967314309741091960L;
	public String idComunicazione;
	public String dataInizioValidita;
	public String dataFineValidita;
	public String ruoloDestinatario;
	public String titoloComunicazione;
	public String flagRichiedente;
	public String flagAutorizzatori;
	public String flagAmministratori;
	public String flagOperatori;
	public String flagDc;
	public String flagCop;
	public String flagCam;
	public String flagDr;
	public String flagDp;
	public String flagTutte;
	public String testo;
	public String nomeAllegato;
	public String attiva;
	
	//parametri per la gestione dell'invio delle email
	public String cfDestinatario;
	public String emailDestinatario;
		

	public String getIdComunicazione() {
		return idComunicazione;
	}
	public void setIdComunicazione(String idComunicazione) {
		this.idComunicazione = idComunicazione;
	}
	public String getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public String getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	public String getRuoloDestinatario() {
		return ruoloDestinatario;
	}
	public void setRuoloDestinatario(String ruoloDestinatario) {
		this.ruoloDestinatario = ruoloDestinatario;
	}

	public String getFlagRichiedente() {
		return flagRichiedente;
	}
	public void setFlagRichiedente(String flagRichiedente) {
		this.flagRichiedente = flagRichiedente;
	}
	public String getFlagAutorizzatori() {
		return flagAutorizzatori;
	}
	public void setFlagAutorizzatori(String flagAutorizzatori) {
		this.flagAutorizzatori = flagAutorizzatori;
	}
	public String getFlagAmministratori() {
		return flagAmministratori;
	}
	public void setFlagAmministratori(String flagAmministratori) {
		this.flagAmministratori = flagAmministratori;
	}
	public String getFlagOperatori() {
		return flagOperatori;
	}
	public void setFlagOperatori(String flagOperatori) {
		this.flagOperatori = flagOperatori;
	}
	public String getFlagTutte() {
		return flagTutte;
	}
	public void setFlagTutte(String flagTutte) {
		this.flagTutte = flagTutte;
	}
	public String getTitoloComunicazione() {
		return titoloComunicazione;
	}
	public void setTitoloComunicazione(String titoloComunicazione) {
		this.titoloComunicazione = titoloComunicazione;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public String getNomeAllegato() {
		return nomeAllegato;
	}
	public void setNomeAllegato(String nomeAllegato) {
		this.nomeAllegato = nomeAllegato;
	}
	public String getCfDestinatario() {
		return cfDestinatario;
	}
	public void setCfDestinatario(String cfDestinatario) {
		this.cfDestinatario = cfDestinatario;
	}
	public String getEmailDestinatario() {
		return emailDestinatario;
	}
	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}
	public String getFlagDc() {
		return flagDc;
	}
	public void setFlagDc(String flagDc) {
		this.flagDc = flagDc;
	}
	public String getFlagCop() {
		return flagCop;
	}
	public void setFlagCop(String flagCop) {
		this.flagCop = flagCop;
	}
	public String getFlagCam() {
		return flagCam;
	}
	public void setFlagCam(String flagCam) {
		this.flagCam = flagCam;
	}
	public String getFlagDr() {
		return flagDr;
	}
	public void setFlagDr(String flagDr) {
		this.flagDr = flagDr;
	}
	public String getFlagDp() {
		return flagDp;
	}
	public void setFlagDp(String flagDp) {
		this.flagDp = flagDp;
	}
	public String getAttiva() {
		return attiva;
	}
	public void setAttiva(String attiva) {
		this.attiva = attiva;
	}
	
	
	
}
