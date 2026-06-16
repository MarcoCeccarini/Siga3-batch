package it.finanze.siga.bean;

import java.io.Serializable;

public class ApplicazioneUtenteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8445044993414586772L;
	
	private String codiceRoleGroup;
	private String roleGroupDesc;
	private String codiceApplicazioneUtente;
	private String descrizione;
	private int numeroFunzioni;
	private String almenoUnaAbilitazioneSenzaFunzioni;
	private String estesa;
	private String eliminabile;
	

	public String getAlmenoUnaAbilitazioneSenzaFunzioni() {
		return almenoUnaAbilitazioneSenzaFunzioni;
	}
	public void setAlmenoUnaAbilitazioneSenzaFunzioni(
			String almenoUnaAbilitazioneSenzaFunzioni) {
		this.almenoUnaAbilitazioneSenzaFunzioni = almenoUnaAbilitazioneSenzaFunzioni;
	}
	public String getCodiceApplicazioneUtente() {
		return codiceApplicazioneUtente;
	}
	public void setCodiceApplicazioneUtente(String codiceApplicazioneUtente) {
		this.codiceApplicazioneUtente = codiceApplicazioneUtente;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getRoleGroupDesc() {
		return roleGroupDesc;
	}
	public void setRoleGroupDesc(String roleGroupDesc) {
		this.roleGroupDesc = roleGroupDesc;
	}
	public int getNumeroFunzioni() {
		return numeroFunzioni;
	}
	public void setNumeroFunzioni(int numeroFunzioni) {
		this.numeroFunzioni = numeroFunzioni;
	}
	public String getEstesa() {
		return estesa;
	}
	public void setEstesa(String estesa) {
		this.estesa = estesa;
	}
	public String getEliminabile() {
		return eliminabile;
	}
	public void setEliminabile(String eliminabile) {
		this.eliminabile = eliminabile;
	}

	
	
	
	
	
	
}
