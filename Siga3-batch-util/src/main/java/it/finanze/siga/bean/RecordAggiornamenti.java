package it.finanze.siga.bean;

import java.io.Serializable;

public class RecordAggiornamenti implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8108556188905641941L;
    
	private String applicazione = "";
	private String profilo = "";
	private String roleGroup = "";
	private String descrizione = "";
	private String azione = "";
	private String nota = "";
	public String getApplicazione() {
		return applicazione;
	}
	public void setApplicazione(String applicazione) {
		this.applicazione = applicazione;
	}
	public String getProfilo() {
		return profilo;
	}
	public void setProfilo(String profilo) {
		this.profilo = profilo;
	}
	public String getRoleGroup() {
		return roleGroup;
	}
	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	
}
