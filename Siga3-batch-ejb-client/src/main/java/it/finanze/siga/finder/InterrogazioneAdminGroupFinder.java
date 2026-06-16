package it.finanze.siga.finder;

public class InterrogazioneAdminGroupFinder extends BasePaginateFinder {
	private static final long serialVersionUID = 1L;
	private String codiceCdr ;
	private String ambito;
	private String[] codiciApplicazione;
	private String tipoAbilitazione;
	private String cfAmmCentraleAppl;
	
	// Variabile utilizzata per Recuperare i Profili associati ai Role Groups di una Applicazione
	private String[] codiciRoleGroups;
	
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public String[] getCodiciApplicazione() {
		return codiciApplicazione;
	}
	public void setCodiciApplicazione(String[] codiciApplicazione) {
		this.codiciApplicazione = codiciApplicazione;
	}
	public String getTipoAbilitazione() {
		return tipoAbilitazione;
	}
	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
	}
	public String getCfAmmCentraleAppl() {
		return cfAmmCentraleAppl;
	}
	public void setCfAmmCentraleAppl(String cfAmmCentraleAppl) {
		this.cfAmmCentraleAppl = cfAmmCentraleAppl;
	}
	public String[] getCodiciRoleGroups() {
		return codiciRoleGroups;
	}
	public void setCodiciRoleGroups(String[] codiciRoleGroups) {
		this.codiciRoleGroups = codiciRoleGroups;
	}

}
