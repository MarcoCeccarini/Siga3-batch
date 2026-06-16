package it.finanze.siga.finder;


public class CheckIterSingoloProfiloFinder extends CheckIterBaseProfiloFinder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6038140169132194656L;
	
	/**
	 * per campi profilo
	 */
	private String codiceRoleGroup;
	private String codiceProfilo;
	
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

}
