package it.finanze.siga.bean;

import it.finanze.siga.util.Constants;

import java.io.Serializable;
import java.util.Date;

public class ExportProfiloBean extends ProfiloBean implements Serializable, Comparable<ProfiloBean> {
	private static final long serialVersionUID = 7288574435618761882L; 
	protected String codiceCdr;
	protected String cdrDesc;
	protected String modAss;
	protected String modAssDesc;
	protected String ambitoDesc;
	protected String applicazioneDesc;
	protected String roleGroupDesc;
	protected String profiloDesc;
	protected Date dataScadenza;
	protected String tipoAbilitazione;
	protected String tipoUfficioDesc;
	protected String tipoCDRDesc;
	protected String iterDesc;
	protected String tipologia;
	protected String regione;
	protected String provincia;
	protected String ufficioDesc;
	protected String dataOra_Estrazione;
	protected String strutturaDesc;
	protected String secondoLivDesc;
	protected String cdrInputDesc;
	protected String cdrValidita;
	protected String cdrValiditaCodice;
	protected String codFisc;
	protected String nome;
	protected String cognome;
	protected Date dataAttribuzione;
	protected String explicitEntitlement;
	protected String explicitEntitlementDesc;
	protected String strutturaCod;
	protected String secondoLivCod;	
	protected String tipologiaUtente;
	protected String tipologiaUtenteDesc;
	protected String mansioneUtente;
	
	
	protected String explicitEntitlementCDR;
	
	public String getCdrValidita() {
		return cdrValidita;
	}
	public void setCdrValidita(String cdrValidita) {
		this.cdrValidita = cdrValidita;
	}
	public String getCodFisc() {
		return codFisc;
	}
	public void setCodFisc(String codFisc) {
		this.codFisc = codFisc;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDataAttribuzione() {
		return dataAttribuzione;
	}
	public void setDataAttribuzione(Date dataAttribuzione) {
		this.dataAttribuzione = dataAttribuzione;
	}
	public String getExplicitEntitlement() {
		return explicitEntitlement;
	}
	public void setExplicitEntitlement(String explicitEntitlement) {
		this.explicitEntitlement = explicitEntitlement;
	}
	public String getExplicitEntitlementDesc() {
		return explicitEntitlementDesc;
	}
	public void setExplicitEntitlementDesc(String explicitEntitlementDesc) {
		this.explicitEntitlementDesc = explicitEntitlementDesc;
	}
	public String getCdrInputDesc() {
		return cdrInputDesc;
	}
	public void setCdrInputDesc(String cdrInputDesc) {
		this.cdrInputDesc = cdrInputDesc;
	}
	public String getStrutturaDesc() {
		return strutturaDesc;
	}
	public void setStrutturaDesc(String strutturaDesc) {
		this.strutturaDesc = strutturaDesc;
	}
	public String getSecondoLivDesc() {
		return secondoLivDesc;
	}
	public void setSecondoLivDesc(String secondoLivDesc) {
		this.secondoLivDesc = secondoLivDesc;
	}
	public String getAmbitoDesc() {
		return ambitoDesc;
	}
	public void setAmbitoDesc(String ambitoDesc) {
		this.ambitoDesc = ambitoDesc;
	}
	public String getApplicazioneDesc() {
		return applicazioneDesc;
	}
	public void setApplicazioneDesc(String applicazioneDesc) {
		this.applicazioneDesc = applicazioneDesc;
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
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getTipoAbilitazione() {
		return (tipoAbilitazione != null && tipoAbilitazione.equals(Constants.Ordinario)?"":tipoAbilitazione);
	}
	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
	}
	public String getTipoUfficioDesc() {
		return tipoUfficioDesc;
	}
	public void setTipoUfficioDesc(String tipoUfficioDesc) {
		this.tipoUfficioDesc = tipoUfficioDesc;
	}
	public String getTipoCDRDesc() {
		return tipoCDRDesc;
	}
	public void setTipoCDRDesc(String tipoCDRDesc) {
		this.tipoCDRDesc = tipoCDRDesc;
	}
	public String getIterDesc() {
		return iterDesc;
	}
	public void setIterDesc(String iterDesc) {
		this.iterDesc = iterDesc;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
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
	public String getUfficioDesc() {
		return ufficioDesc;
	}
	public void setUfficioDesc(String ufficioDesc) {
		this.ufficioDesc = ufficioDesc;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getCdrDesc() {
		return cdrDesc;
	}
	public void setCdrDesc(String cdrDesc) {
		this.cdrDesc = cdrDesc;
	}
	public String getModAss() {
		return modAss;
	}
	public void setModAss(String modAss) {
		this.modAss = modAss;
	}
	public String getModAssDesc() {
		//TODO esternalizzare su file di properties
		if(modAss != null){
			if(modAssDesc == null){
				switch (Integer.parseInt(modAss)) {
				case 1:
					modAssDesc="Disponibile a tutti gli uffici di tipologia " +tipoUfficioDesc;
					break;
				case 2:
					modAssDesc="Disponibile a tutti i CdR di tipologia " +tipoCDRDesc;
					break;
				case 3:
					modAssDesc="Disponibile al CdR perche' assegnato specificatamente alle'ufficio "+  cdrDesc;
					break;
				case 4:
					modAssDesc="Disponibile al CdR perche' assegnato specificatamente al Cdr " + cdrDesc;
					break;
				case 5:
					modAssDesc="Disponibile a tutti gli uffici di tipologia "+ tipoUfficioDesc+ " della provincia "+ provincia;
					break;
				case 6:
					modAssDesc="Disponibile a tutti gli uffici di tipologia " + tipoUfficioDesc + " della regione " + regione ;
					break;
				case 7:
					modAssDesc="Disponibile a tutti i CdR di tipologia " +tipoUfficioDesc + " della provincia "+ provincia;
					break;
				case 8:
					modAssDesc="Disponibile a tutti gli uffici di tipologia "+ ufficioDesc + "della regione" +regione;
					break;	
				default:
					modAssDesc="";
					break;
				}
			}
		}else{
			modAssDesc = "";
		}
		
		return modAssDesc;
	}
	public void setModAssDesc(String modAssDesc) {
		this.modAssDesc = modAssDesc;
	}
	public String getDataOra_Estrazione() {
		return dataOra_Estrazione;
	}
	public void setDataOra_Estrazione(String dataOra_Estrazione) {
		this.dataOra_Estrazione = dataOra_Estrazione;
	}
	public String getCdrValiditaCodice() {
		return cdrValiditaCodice;
	}
	public void setCdrValiditaCodice(String cdrValiditaCodice) {
		this.cdrValiditaCodice = cdrValiditaCodice;
	}
	public String getStrutturaCod() {
		return strutturaCod;
	}
	public void setStrutturaCod(String strutturaCod) {
		this.strutturaCod = strutturaCod;
	}
	public String getSecondoLivCod() {
		return secondoLivCod;
	}
	public void setSecondoLivCod(String secondoLivCod) {
		this.secondoLivCod = secondoLivCod;
	}
	public String getExplicitEntitlementCDR() {
		return explicitEntitlementCDR;
	}
	public void setExplicitEntitlementCDR(String explicitEntitlementCDR) {
		this.explicitEntitlementCDR = explicitEntitlementCDR;
	}
	public String getTipologiaUtente() {
		return tipologiaUtente;
	}
	public void setTipologiaUtente(String tipologiaUtente) {
		this.tipologiaUtente = tipologiaUtente;
	}
	public String getMansioneUtente() {
		return mansioneUtente;
	}
	public void setMansioneUtente(String mansioneUtente) {
		this.mansioneUtente = mansioneUtente;
	}
	public String getTipologiaUtenteDesc() {
		if(this.tipologiaUtente!=null && this.tipologiaUtente.trim().equalsIgnoreCase("I"))
			tipologiaUtenteDesc = "INTERNO";
		else if (this.tipologiaUtente!=null && this.tipologiaUtente.trim().equalsIgnoreCase("E")){
			tipologiaUtenteDesc = "ESTERNO";
			if(mansioneUtente!=null && !mansioneUtente.trim().equals(""))
				tipologiaUtenteDesc = tipologiaUtenteDesc + " - " + mansioneUtente;
		}else
			tipologiaUtenteDesc = "";
		return tipologiaUtenteDesc;
	}
	public void setTipologiaUtenteDesc(String tipologiaUtenteDesc) {
		this.tipologiaUtenteDesc = tipologiaUtenteDesc;
	}

}
