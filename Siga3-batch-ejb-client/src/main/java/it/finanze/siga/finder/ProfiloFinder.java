package it.finanze.siga.finder;

import java.util.Date;
import java.util.List;

import it.finanze.siga.finder.BasePaginateFinder;


public class ProfiloFinder extends BasePaginateFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2650847295657262425L;
	
	private String codiceCDR;
	private String codiceAmbitoApplicativo;
	private String descrizione;
	private String tipoAbilitazione;
	//Aggiunto da Pietro 11/09/2014
	private String codice_profilo;
	private String codice_ufficio;
	private String tipoUfficio;
	private String tipoCdR;
	private String regione;
	private String provincia;
	// ADDED
	private String codFisUser;
	// ADDED x caso visibilità profili
	private String flag;
	// ADDED
	private String codFis;
	private String tipoProfilazione;
	private String cfAmmCentraleAppl;
	private String tipoUtente;
	private String flagAC;
	
	private List<String> cdrLst;
	private List<String> roleGroupList;
	private List<String> struttList;
	private List<String> secondoLivList;
	private List<String> codiciACappl;
	
	private boolean isInVisibilita;
	private boolean flagAggiornamentoPuntualeVis;
	private boolean soloAbilitazioniCAT;
	
	private String cfACApplicativo;

	private boolean isOperatoreRevocato;
 	private String soloVisAltroUff;
 	private String escludiRoleGroupsVis;
 
	
	private String dataOdierna;
	private String dataInizio;
	private String dataFine;
	
	private Date dtOdierna;
	private Date dtInizio;
	private Date dtFine;
	
	private String cfLogin;
	private String cdrLogin;
	private boolean isResponsabileOrRichiedente;
	private boolean isAuditorRegionale;
 	
	private String ruoloScelto;
	
	public boolean isFlagAggiornamentoPuntualeVis() {
		return flagAggiornamentoPuntualeVis;
	}
	public void setFlagAggiornamentoPuntualeVis(boolean flagAggiornamentoPuntualeVis) {
		this.flagAggiornamentoPuntualeVis = flagAggiornamentoPuntualeVis;
	}
	public boolean isInVisibilita() {
		return isInVisibilita;
	}
	public void setInVisibilita(boolean isInVisibilita) {
		this.isInVisibilita = isInVisibilita;
	}
	public List<String> getCdrLst() {
		return cdrLst;
	}
	public void setCdrLst(List<String> cdrLst) {
		this.cdrLst = cdrLst;
	}
	public String getTipoProfilazione() {
		return tipoProfilazione;
	}
	public void setTipoProfilazione(String tipoProfilazione) {
		this.tipoProfilazione = tipoProfilazione;
	}
	// GETTERS AND SETTERS //
	public String getCodFis() {
		return codFis;
	}
	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCodFisUser() {
		return codFisUser;
	}
	public void setCodFisUser(String codFisUser) {
		this.codFisUser = codFisUser;
	}
	
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public String getCodiceAmbitoApplicativo() {
		return codiceAmbitoApplicativo;
	}
	public void setCodiceAmbitoApplicativo(String codiceAmbitoApplicativo) {
		this.codiceAmbitoApplicativo = codiceAmbitoApplicativo;
	}
	public String getTipoAbilitazione() {
		return tipoAbilitazione;
	}
	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
	}
	public String getCodice_profilo() {
		return codice_profilo;
	}
	public void setCodice_profilo(String codice_profilo) {
		this.codice_profilo = codice_profilo;
	}
	public String getCodice_ufficio() {
		return codice_ufficio;
	}
	public void setCodice_ufficio(String codice_ufficio) {
		this.codice_ufficio = codice_ufficio;
	}
	public String getCfAmmCentraleAppl() {
		return cfAmmCentraleAppl;
	}
	public void setCfAmmCentraleAppl(String cfAmmCentraleAppl) {
		this.cfAmmCentraleAppl = cfAmmCentraleAppl;
	}
	public String getTipoUtente() {
		return tipoUtente;
	}
	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}
	public String getFlagAC() {
		return flagAC;
	}
	public void setFlagAC(String flagAC) {
		this.flagAC = flagAC;
	}
	public List<String> getRoleGroupList() {
		return roleGroupList;
	}
	public void setRoleGroupList(List<String> roleGroupList) {
		this.roleGroupList = roleGroupList;
	}
	public List<String> getStruttList() {
		return struttList;
	}
	public void setStruttList(List<String> struttList) {
		this.struttList = struttList;
	}
	public List<String> getSecondoLivList() {
		return secondoLivList;
	}
	public void setSecondoLivList(List<String> secondoLivList) {
		this.secondoLivList = secondoLivList;
	}
	public List<String> getCodiciACappl() {
		return codiciACappl;
	}
	public void setCodiciACappl(List<String> codiciACappl) {
		this.codiciACappl = codiciACappl;
	}
	
	
	public boolean isSoloAbilitazioniCAT() {
		return soloAbilitazioniCAT;
	}
	public void setSoloAbilitazioniCAT(boolean soloAbilitazioniCAT) {
		this.soloAbilitazioniCAT = soloAbilitazioniCAT;
	}
	@Override
	public String toString() {
		return "ProfiloFinder [codiceCDR=" + codiceCDR
				+ ", codiceAmbitoApplicativo=" + codiceAmbitoApplicativo
				+ ", descrizione=" + descrizione + ", tipoAbilitazione="
				+ tipoAbilitazione + ", codice_profilo=" + codice_profilo
				+ ", codice_ufficio=" + codice_ufficio + ", codFisUser="
				+ codFisUser + ", flag=" + flag + ", codFis=" + codFis
				+ ", tipoProfilazione=" + tipoProfilazione
				+ ", cfAmmCentraleAppl=" + cfAmmCentraleAppl + ", tipoUtente="
				+ tipoUtente + ", flagAC=" + flagAC + ", cdrLst=" + cdrLst
				+ ", roleGroupList=" + roleGroupList + ", struttList="
				+ struttList + ", secondoLivList=" + secondoLivList
				+ ", codiciACappl=" + codiciACappl + ", isInVisibilita="
				+ isInVisibilita + ", flagAggiornamentoPuntualeVis="
				+ flagAggiornamentoPuntualeVis + "]";
	}
	public String getTipoUfficio() {
		return tipoUfficio;
	}
	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}
	public String getTipoCdR() {
		return tipoCdR;
	}
	public void setTipoCdR(String tipoCdR) {
		this.tipoCdR = tipoCdR;
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
	public String getCfACApplicativo() {
		return cfACApplicativo;
	}
	public void setCfACApplicativo(String cfACApplicativo) {
		this.cfACApplicativo = cfACApplicativo;
	}

	public boolean isOperatoreRevocato() {
		return isOperatoreRevocato;
	}
	public void setOperatoreRevocato(boolean isOperatoreRevocato) {
		this.isOperatoreRevocato = isOperatoreRevocato;
	}
	public String getDataOdierna() {
		return dataOdierna;
	}
	public void setDataOdierna(String dataOdierna) {
		this.dataOdierna = dataOdierna;
	}
	public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	public String getDataFine() {
		return dataFine;
	}
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;

	}
	public String getCfLogin() {
		return cfLogin;
	}
	public void setCfLogin(String cfLogin) {
		this.cfLogin = cfLogin;
	}
	public String getCdrLogin() {
		return cdrLogin;
	}
	public void setCdrLogin(String cdrLogin) {
		this.cdrLogin = cdrLogin;
	}
	public boolean isResponsabileOrRichiedente() {
		return isResponsabileOrRichiedente;
	}
	public void setResponsabileOrRichiedente(boolean isResponsabileOrRichiedente) {
		this.isResponsabileOrRichiedente = isResponsabileOrRichiedente;
	}
	public boolean isAuditorRegionale() {
		return isAuditorRegionale;
	}
	public void setAuditorRegionale(boolean isAuditorRegionale) {
		this.isAuditorRegionale = isAuditorRegionale;
	}
	public String getRuoloScelto() {
		return ruoloScelto;
	}
	public void setRuoloScelto(String ruoloScelto) {
		this.ruoloScelto = ruoloScelto;
	}
	public Date getDtOdierna() {
		return dtOdierna;
	}
	public void setDtOdierna(Date dtOdierna) {
		this.dtOdierna = dtOdierna;
	}
	public Date getDtInizio() {
		return dtInizio;
	}
	public void setDtInizio(Date dtInizio) {
		this.dtInizio = dtInizio;
	}
	public Date getDtFine() {
		return dtFine;
	}
	public void setDtFine(Date dtFine) {
		this.dtFine = dtFine;
	}
	public String getSoloVisAltroUff() {
		return soloVisAltroUff;
	}
	public void setSoloVisAltroUff(String soloVisAltroUff) {
		this.soloVisAltroUff = soloVisAltroUff;
	}
	public String getEscludiRoleGroupsVis() {
		return escludiRoleGroupsVis;
	}
	public void setEscludiRoleGroupsVis(String escludiRoleGroupsVis) {
		this.escludiRoleGroupsVis = escludiRoleGroupsVis;
	}
	
}
