package it.finanze.scheduler.bean;

import java.io.Serializable;

public class SegnalazioneIncoerenzeBean extends SegnalazioneIncoerenzeAssegnazioneBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String cognome;
	private String roleGroup;
	private String descrRoleGroup;
	private String descrizioneApplicazione;
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getRoleGroup() {
		return roleGroup;
	}
	public String getDescrRoleGroup() {
		return descrRoleGroup;
	}
	public String getDescrizioneApplicazione() {
		return descrizioneApplicazione;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}
	public void setDescrRoleGroup(String descrRoleGroup) {
		this.descrRoleGroup = descrRoleGroup;
	}
	public void setDescrizioneApplicazione(String descrizioneApplicazione) {
		this.descrizioneApplicazione = descrizioneApplicazione;
	}
  
	
}
