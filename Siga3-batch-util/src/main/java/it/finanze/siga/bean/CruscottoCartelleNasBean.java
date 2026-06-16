package it.finanze.siga.bean;

import java.io.Serializable;

public class CruscottoCartelleNasBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6731361238707770668L;
	
	private String nomeMacchina;
	private String utente;
	private String data;
	private String path;
	private String nomeFile;
	private String controllo;
	private String operazione;
	
	
	public String getNomeMacchina() {
		return nomeMacchina;
	}
	public void setNomeMacchina(String nomeMacchina) {
		this.nomeMacchina = nomeMacchina;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public String getControllo() {
		return controllo;
	}
	public void setControllo(String controllo) {
		this.controllo = controllo;
	}
	public String getOperazione() {
		return operazione;
	}
	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}
	
	

}
