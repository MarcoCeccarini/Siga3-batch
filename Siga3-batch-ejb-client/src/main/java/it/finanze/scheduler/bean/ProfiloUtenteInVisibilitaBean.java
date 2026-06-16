package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class ProfiloUtenteInVisibilitaBean implements Serializable{

	
	private static final long serialVersionUID = 3284640671688252909L;
	private String cfUtente;
	private String codiceProfilo;
	private String codiceRoleGroup;
	private String codiceApplicazione;
	private String codiceCdr;
	private String codiceUfficio;
	private String profiloAssegnato;
	private String idRichiestaVisibilita;
	private Date dataInizioVal;
	private Date dataFineVal;
	private String tipoAbilitazione;
	private String origineAbilitazione;
	private String explicitEntitlement;
	private Integer idAudit;
	private Date dataFineValOld;
	private Integer nroProfili;
	
	
	
	public Date getDataFineValOld() {
		return dataFineValOld;
	}
	public void setDataFineValOld(Date dataFineValOld) {
		this.dataFineValOld = dataFineValOld;
	}
	public Integer getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}
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
	public String getProfiloAssegnato() {
		return profiloAssegnato;
	}
	public void setProfiloAssegnato(String profiloAssegnato) {
		this.profiloAssegnato = profiloAssegnato;
	}
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
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
	public String getTipoAbilitazione() {
		return tipoAbilitazione;
	}
	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
	}
	public String getOrigineAbilitazione() {
		return origineAbilitazione;
	}
	public void setOrigineAbilitazione(String origineAbilitazione) {
		this.origineAbilitazione = origineAbilitazione;
	}
	public String getExplicitEntitlement() {
		return explicitEntitlement;
	}
	public void setExplicitEntitlement(String explicitEntitlement) {
		this.explicitEntitlement = explicitEntitlement;
	}
	public Integer getNroProfili() {
		return nroProfili;
	}
	public void setNroProfili(Integer nroProfili) {
		this.nroProfili = nroProfili;
	}
	
	
	
}
