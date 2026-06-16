package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.sql.Date;

public class ResponsabilitaOA implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer codAmbito ;

	private String idResponsabilita;
	private String idApplicazione;
	private String descrResponsabilita ;
	private String orgId ;
	private String codiceCics ;
	
	private String cdrOrg ;
	private String idOrgPrevistaDaRegolamento;
	private String codCdrOrgPrevistaDaRegolamento;
	private String nomeApplicazione; 
	private String cdrOrgAssociata; 
	
	private String codCicsOrgPrevistaDaRegolamento;
	private String codCdrOrgCapoCics;
	
	private Date dataInizio  ;
	private Date dataFine;
	
	private String codApplicazione;
	private String codiceRoleGroup;
	private  String criterioAg;
	private String codProfilo;
	
	private Date dataIns;
	private Date dataElab;
	private String codUfficio;
	private String cdr;
	private String codStruttura;
	

	public String getCdr() {
		return cdr;
	}
	public String getIdOrgPrevistaDaRegolamento() {
		return idOrgPrevistaDaRegolamento;
	}
	public void setIdOrgPrevistaDaRegolamento(String idOrgPrevistaDaRegolamento) {
		this.idOrgPrevistaDaRegolamento = idOrgPrevistaDaRegolamento;
	}
	public String getCodCdrOrgPrevistaDaRegolamento() {
		return codCdrOrgPrevistaDaRegolamento;
	}
	public void setCodCdrOrgPrevistaDaRegolamento(
			String codCdrOrgPrevistaDaRegolamento) {
		this.codCdrOrgPrevistaDaRegolamento = codCdrOrgPrevistaDaRegolamento;
	}
	public String getNomeApplicazione() {
		return nomeApplicazione;
	}
	public void setNomeApplicazione(String nomeApplicazione) {
		this.nomeApplicazione = nomeApplicazione;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}
	public String getCodUfficio() {
		return codUfficio;
	}
	public void setCodUfficio(String codUfficio) {
		this.codUfficio = codUfficio;
	}
	
	public String getIdResponsabilita() {
		return idResponsabilita;
	}
	public void setIdResponsabilita(String idResponsabilita) {
		this.idResponsabilita = idResponsabilita;
	}
	public Integer getCodAmbito() {
		return codAmbito;
	}
	public void setCodAmbito(Integer codAmbito) {
		this.codAmbito = codAmbito;
	}
	public String getDescrResponsabilita() {
		return descrResponsabilita;
	}
	public void setDescrResponsabilita(String descrResponsabilita) {
		this.descrResponsabilita = descrResponsabilita;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getCodiceCics() {
		return codiceCics;
	}
	public void setCodiceCics(String codiceCics) {
		this.codiceCics = codiceCics;
	}
	 
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public String getCodApplicazione() {
		return codApplicazione;
	}
	public void setCodApplicazione(String codApplicazione) {
		this.codApplicazione = codApplicazione;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getCriterioAg() {
		return criterioAg;
	}
	public void setCriterioAg(String criterioAg) {
		this.criterioAg = criterioAg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCodProfilo() {
		return codProfilo;
	}
	public void setCodProfilo(String codProfilo) {
		this.codProfilo = codProfilo;
	}
	
 
	public Date getDataIns() {
		return dataIns;
	}
	public void setDataIns(Date dataIns) {
		this.dataIns = dataIns;
	}
	public Date getDataElab() {
		return dataElab;
	}
	public void setDataElab(Date dataElab) {
		this.dataElab = dataElab;
	}
	public String getIdApplicazione() {
		return idApplicazione;
	}
	public void setIdApplicazione(String idApplicazione) {
		this.idApplicazione = idApplicazione;
	}
	public String getCdrOrg() {
		return cdrOrg;
	}
	public void setCdrOrg(String cdrOrg) {
		this.cdrOrg = cdrOrg;
	}
	public String getCodCicsOrgPrevistaDaRegolamento() {
		return codCicsOrgPrevistaDaRegolamento;
	}
	public void setCodCicsOrgPrevistaDaRegolamento(
			String codCicsOrgPrevistaDaRegolamento) {
		this.codCicsOrgPrevistaDaRegolamento = codCicsOrgPrevistaDaRegolamento;
	}
	public String getCodCdrOrgCapoCics() {
		return codCdrOrgCapoCics;
	}
	public void setCodCdrOrgCapoCics(String codCdrOrgCapoCics) {
		this.codCdrOrgCapoCics = codCdrOrgCapoCics;
	}
	public String getCdrOrgAssociata() {
		return cdrOrgAssociata;
	}
	public void setCdrOrgAssociata(String cdrOrgAssociata) {
		this.cdrOrgAssociata = cdrOrgAssociata;
	}
	public String getCodStruttura() {
		return codStruttura;
	}
	public void setCodStruttura(String codStruttura) {
		this.codStruttura = codStruttura;
	}
	
	 
	
	 


}
