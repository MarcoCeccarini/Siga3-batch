package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AbilitazioneBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 926569165264484359L;
	private String codiceApplicazione;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String applicazioneDesc;
	private String roleGroupDesc;
	private String profiloDesc;
	private String explicitEntitlement;
	private Date dataInizioVal;
	private Date dataFineVal;
	private String nota;
	private String senzaFunzioni;
	private String codiceRaggrFunzionale;
	private String raggrFunzionaleDesc;
	private String funzioneDesc;
	private String idFunzione;
	private String assegnabile;
	private String notaRg;
	private String flagNotaRoleGroup;
	private String flagNotaProfilo;
	private String flagAllegato;
	private String flagNotaRichiedente;
	private String flagDataScadenza;
	private Integer numGiorniScadenza;
	private String estesa;
	private String eliminabile;
	
	
	List<ElementoCatalogoBean> catalogoProfili;
	
	
	
	public String getFlagNotaRichiedente() {
		return flagNotaRichiedente;
	}
	public void setFlagNotaRichiedente(String flagRichiedente) {
		this.flagNotaRichiedente = flagRichiedente;
	}
	
	public List<ElementoCatalogoBean> getCatalogoProfili() {
		return catalogoProfili;
	}
	public void setCatalogoProfili(List<ElementoCatalogoBean> catalogoProfili) {
		this.catalogoProfili = catalogoProfili;
	}
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
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
	
	public Date getDataInizioVal() {
		return dataInizioVal;
	}
	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	public Date getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getExplicitEntitlement() {
		return explicitEntitlement;
	}
	public void setExplicitEntitlement(String explicitEntitlement) {
		this.explicitEntitlement = explicitEntitlement;
	}
	public String getSenzaFunzioni() {
		return senzaFunzioni;
	}
	public void setSenzaFunzioni(String senzaFunzioni) {
		this.senzaFunzioni = senzaFunzioni;
	}
	public String getCodiceRaggrFunzionale() {
		return codiceRaggrFunzionale;
	}
	public void setCodiceRaggrFunzionale(String codiceRaggrFunzionale) {
		this.codiceRaggrFunzionale = codiceRaggrFunzionale;
	}
	public String getRaggrFunzionaleDesc() {
		return raggrFunzionaleDesc;
	}
	public void setRaggrFunzionaleDesc(String raggrFunzionaleDesc) {
		this.raggrFunzionaleDesc = raggrFunzionaleDesc;
	}
	public String getFunzioneDesc() {
		return funzioneDesc;
	}
	public void setFunzioneDesc(String funzioneDesc) {
		this.funzioneDesc = funzioneDesc;
	}
	public String getIdFunzione() {
		return idFunzione;
	}
	public void setIdFunzione(String idFunzione) {
		this.idFunzione = idFunzione;
	}
	public String getAssegnabile() {
		return assegnabile;
	}
	public void setAssegnabile(String assegnabile) {
		this.assegnabile = assegnabile;
	}
	public String getNotaRg() {
		return notaRg;
	}
	public void setNotaRg(String notaRg) {
		this.notaRg = notaRg;
	}
	public String getFlagNotaRoleGroup() {
		return flagNotaRoleGroup;
	}
	public void setFlagNotaRoleGroup(String flagNotaRoleGroup) {
		this.flagNotaRoleGroup = flagNotaRoleGroup;
	}
	public String getFlagNotaProfilo() {
		return flagNotaProfilo;
	}
	public void setFlagNotaProfilo(String flagNotaProfilo) {
		this.flagNotaProfilo = flagNotaProfilo;
	}
	public String getFlagAllegato() {
		return flagAllegato;
	}
	public void setFlagAllegato(String flagAllegato) {
		this.flagAllegato = flagAllegato;
	}
	
	public String getFlagDataScadenza() {
		return flagDataScadenza;
	}
	public void setFlagDataScadenza(String flagDataScadenza) {
		this.flagDataScadenza = flagDataScadenza;
	}
	public Integer getNumGiorniScadenza() {
		return numGiorniScadenza;
	}
	public void setNumGiorniScadenza(Integer numGiorniScadenza) {
		this.numGiorniScadenza = numGiorniScadenza;
	}
	public String getEstesa() {
		return estesa;
	}
	public void setEstesa(String estesa) {
		this.estesa = estesa;
	}
	public String getEliminabile() {
		return eliminabile;
	}
	public void setEliminabile(String eliminabile) {
		this.eliminabile = eliminabile;
	}

	
	
}
