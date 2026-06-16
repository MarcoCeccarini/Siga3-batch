package it.finanze.siga.finder;

public class VisibilitaCdrFinder extends BaseFinder{


	private static final long serialVersionUID = 3555221272278459457L;
	private String codiceFiscale;
	private String codiceCdr;
	private String codiceRoleGroup;
	private String cdrVisibEstUfficio;
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getCdrVisibEstUfficio() {
		return cdrVisibEstUfficio;
	}
	public void setCdrVisibEstUfficio(String cdrVisibEstUfficio) {
		this.cdrVisibEstUfficio = cdrVisibEstUfficio;
	}
	
	
}
