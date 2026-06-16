package it.finanze.siga.bean;

import java.io.Serializable;

public class ExportOperatoreEsternoBean implements Serializable, Cloneable{
	
	private static final long serialVersionUID = 8769017867519036270L;
	
	private String cognome;
	private String nome;
	private String cf;
	private String dataInizioValStr;
	private String email;
	private String tel;
	private String fax;
	private String agenziaStanza;
	private String agenziaTel;
	private String agenziaFax;
	private String organizzazione;
	private String mansione;
	private String descrizioneCDR;
	private String codiceCDR;
	private String note;
	private String desc2Liv;
	private String descStruttura;
	private String descUfficio;
	private String codice2Liv;
	private String codiceStruttura;
	private String codiceUfficio;
	// tipologia
	private String tipologiaDesc;
	private String tipologiaDataInizioStr;
	private String tipologiaFlagSenzaLimitazione;
	// profilo attivo
	private String roleGroupDesc;
	private String profiloDesc;
	private String codiceRoleGroup;
	private String codiceProfilo;
	//data scadenza
	private String dataScadenza;
	
	
	
	public String getTipologiaDesc() {
		return tipologiaDesc;
	}
	public void setTipologiaDesc(String tipologiaDesc) {
		this.tipologiaDesc = tipologiaDesc;
	}
	public String getTipologiaDataInizioStr() {
		return tipologiaDataInizioStr;
	}
	public void setTipologiaDataInizioStr(String tipologiaDataInizioStr) {
		this.tipologiaDataInizioStr = tipologiaDataInizioStr;
	}
	public String getDesc2Liv() {
		return desc2Liv;
	}
	public void setDesc2Liv(String desc2Liv) {
		this.desc2Liv = desc2Liv;
	}
	public String getDescStruttura() {
		return descStruttura;
	}
	public void setDescStruttura(String descStruttura) {
		this.descStruttura = descStruttura;
	}
	public String getDescUfficio() {
		return descUfficio;
	}
	public void setDescUfficio(String descUfficio) {
		this.descUfficio = descUfficio;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getDataInizioValStr() {
		return dataInizioValStr;
	}
	public void setDataInizioValStr(String dataInizioValStr) {
		this.dataInizioValStr = dataInizioValStr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAgenziaStanza() {
		return agenziaStanza;
	}
	public void setAgenziaStanza(String agenziaStanza) {
		this.agenziaStanza = agenziaStanza;
	}
	public String getAgenziaTel() {
		return agenziaTel;
	}
	public void setAgenziaTel(String agenziaTel) {
		this.agenziaTel = agenziaTel;
	}
	public String getAgenziaFax() {
		return agenziaFax;
	}
	public void setAgenziaFax(String agenziaFax) {
		this.agenziaFax = agenziaFax;
	}
	public String getOrganizzazione() {
		return organizzazione;
	}
	public void setOrganizzazione(String organizzazione) {
		this.organizzazione = organizzazione;
	}
	public String getMansione() {
		return mansione;
	}
	public void setMansione(String mansione) {
		this.mansione = mansione;
	}
	public String getDescrizioneCDR() {
		return descrizioneCDR;
	}
	public void setDescrizioneCDR(String descrizioneCDR) {
		this.descrizioneCDR = descrizioneCDR;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getTipologiaFlagSenzaLimitazione() {
		return tipologiaFlagSenzaLimitazione;
	}
	public void setTipologiaFlagSenzaLimitazione(
			String tipologiaFlagSenzaLimitazione) {
		this.tipologiaFlagSenzaLimitazione = tipologiaFlagSenzaLimitazione;
	}
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
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
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public String getCodiceStruttura() {
		return codiceStruttura;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getCodice2Liv() {
		return codice2Liv;
	}
	public void setCodice2Liv(String codice2Liv) {
		this.codice2Liv = codice2Liv;
	}
	public String getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	
}