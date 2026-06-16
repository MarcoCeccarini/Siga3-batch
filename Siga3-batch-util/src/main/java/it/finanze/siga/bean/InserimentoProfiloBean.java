package it.finanze.siga.bean;

public class InserimentoProfiloBean {
	
	private String codiceProfilo;
	private String tipoAbilitazione;
	private String dataScadenzaAssegnatiString;
	private String codiceRoleGroup;
	private String roleGroupDesc;
	private String profiloDesc;
	
	
	public InserimentoProfiloBean() {
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getTipoAbilitazione() {
		return tipoAbilitazione;
	}
	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
	}
	public String getDataScadenzaAssegnatiString() {
		return dataScadenzaAssegnatiString;
	}
	public void setDataScadenzaAssegnatiString(String dataScadenzaAssegnatiString) {
		this.dataScadenzaAssegnatiString = dataScadenzaAssegnatiString;
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
	public String getProfiloDesc() {
		return profiloDesc;
	}
	public void setProfiloDesc(String profiloDesc) {
		this.profiloDesc = profiloDesc;
	}

}
