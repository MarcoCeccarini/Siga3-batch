package it.finanze.siga.bean;

import java.io.Serializable;

public class CruscottoActiveDirectoryDominioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4845763339334096355L;
	
	private String cf;
	private String nome;
	private String cognome;
	private boolean  owa;
	private boolean activeSync;
	private String internetBase;
	private String internetVip;
	
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
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
	public boolean isOwa() {
		return owa;
	}
	public void setOwa(boolean owa) {
		this.owa = owa;
	}
	public boolean isActiveSync() {
		return activeSync;
	}
	public void setActiveSync(boolean activeSync) {
		this.activeSync = activeSync;
	}
	public String getInternetBase() {
		return internetBase;
	}
	public void setInternetBase(String internetBase) {
		this.internetBase = internetBase;
	}
	public String getInternetVip() {
		return internetVip;
	}
	public void setInternetVip(String internetVip) {
		this.internetVip = internetVip;
	}
	
	

}
