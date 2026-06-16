package it.finanze.scheduler.bean;

import java.io.Serializable;

public class ProfiliAttiviDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codiceProfilo;
	private String codiceRoleGroup;

	/**
	 * 
	 */
	

	public ProfiliAttiviDAO() {
		// TODO Stub di costruttore generato automaticamente
	}

	public String getCodiceProfilo() {
		return codiceProfilo;
	}

	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}

	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}

	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}

}
