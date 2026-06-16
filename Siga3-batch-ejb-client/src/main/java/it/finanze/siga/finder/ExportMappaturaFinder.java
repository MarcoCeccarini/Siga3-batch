package it.finanze.siga.finder;

public class ExportMappaturaFinder extends BasePaginateFinder{

	private static final long serialVersionUID = 8169047442942534951L;
	private String codiceCdr;
	private String codiceUfficio;
	private String tipoCDR;
	private String tipoUfficio;
	private String regione;
	private String provincia;
	private String codiceRoleGroup;
	private String cfAmmCentraleAppl;
	
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getTipoCDR() {
		return tipoCDR;
	}
	public void setTipoCDR(String tipoCDR) {
		this.tipoCDR = tipoCDR;
	}
	public String getTipoUfficio() {
		return tipoUfficio;
	}
	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getCfAmmCentraleAppl() {
		return cfAmmCentraleAppl;
	}
	public void setCfAmmCentraleAppl(String cfAmmCentraleAppl) {
		this.cfAmmCentraleAppl = cfAmmCentraleAppl;
	}
	
}
