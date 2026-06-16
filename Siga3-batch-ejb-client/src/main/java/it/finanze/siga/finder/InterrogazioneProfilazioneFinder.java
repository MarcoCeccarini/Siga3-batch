package it.finanze.siga.finder;

public class InterrogazioneProfilazioneFinder extends BasePaginateFinder {
	private static final long serialVersionUID = 1L;
	private String ambito;
	private String cfAmmCentraleAppl;
	private String[] codiciApplicazione;
	private String[] codiciRoleGroup;
	private boolean nazionale;
	private boolean regionale;
	private boolean provinciale;
	private String regione;
	private String provincia;
	private String[] tipiUfficio;
	private String[] tipiCdr;
	private String tipologia;
	private String[] codiciCdr ;
	private String[] codiciUfficio ;
	
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
	public boolean isNazionale() {
		return nazionale;
	}
	public void setNazionale(boolean nazionale) {
		this.nazionale = nazionale;
	}
	public boolean isRegionale() {
		return regionale;
	}
	public void setRegionale(boolean regionale) {
		this.regionale = regionale;
	}
	public boolean isProvinciale() {
		return provinciale;
	}
	public void setProvinciale(boolean provinciale) {
		this.provinciale = provinciale;
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
	public String[] getTipiUfficio() {
		return tipiUfficio;
	}
	public void setTipiUfficio(String[] tipiUfficio) {
		this.tipiUfficio = tipiUfficio;
	}
	public String[] getTipiCdr() {
		return tipiCdr;
	}
	public void setTipiCdr(String[] tipiCdr) {
		this.tipiCdr = tipiCdr;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String[] getCodiciCdr() {
		return codiciCdr;
	}
	public void setCodiciCdr(String[] codiciCdr) {
		this.codiciCdr = codiciCdr;
	}
	public String[] getCodiciRoleGroup() {
		return codiciRoleGroup;
	}
	public void setCodiciRoleGroup(String[] codiciRoleGroup) {
		this.codiciRoleGroup = codiciRoleGroup;
	}
	public String getCfAmmCentraleAppl() {
		return cfAmmCentraleAppl;
	}
	public void setCfAmmCentraleAppl(String cfAmmCentraleAppl) {
		this.cfAmmCentraleAppl = cfAmmCentraleAppl;
	}
	public String[] getCodiciUfficio() {
		return codiciUfficio;
	}
	public void setCodiciUfficio(String[] codiciUfficio) {
		this.codiciUfficio = codiciUfficio;
	}
	
}
