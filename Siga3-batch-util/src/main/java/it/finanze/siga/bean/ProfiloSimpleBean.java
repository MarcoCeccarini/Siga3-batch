package it.finanze.siga.bean;

import java.io.Serializable;

public class ProfiloSimpleBean extends ProfiloBean implements Serializable {

	private static final long serialVersionUID = 7530891360251024535L;

	private String codiceRoleGroup;
	private String codiceProfilo;
	private String codiceTipologia;
	private String roleGroupDesc;
	private String profiloDesc;
	
	public String getRoleGroupDesc() {
		return roleGroupDesc;
	}

	public void setRoleGroupDesc(String roleGroupDesc) {
		this.roleGroupDesc = roleGroupDesc;
	}

	public String getProfiloDesc() {
		return profiloDesc;
	}

	public void setProfiloDesc(String profiloDesc) {
		this.profiloDesc = profiloDesc;
	}

	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}

	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}

	public String getCodiceProfilo() {
		return codiceProfilo;
	}

	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}

	public String getCodiceTipologia() {
		return codiceTipologia;
	}

	public void setCodiceTipologia(String codiceTipologia) {
		this.codiceTipologia = codiceTipologia;
	}

}