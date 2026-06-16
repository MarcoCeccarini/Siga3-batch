package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;

public class ProfiliTemplateBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4781893756758774004L;
	/**
	 * 
	 */
	
	private int templateID;
	private String codiceApplicazione;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String ambitoApplicativo;
	private Date dataInizioVal;
	private Date dataFineVal;
	
	public ProfiliTemplateBean(){}
	public ProfiliTemplateBean(ProfiloBean profiloBean){
		codiceApplicazione = profiloBean.getCodiceApplicazione();
		codiceRoleGroup = profiloBean.getCodiceRoleGroup();
		codiceProfilo = profiloBean.getCodiceProfilo();
		dataInizioVal = profiloBean.getData_inizio_val();
		dataFineVal = profiloBean.getData_fine_val();
	}
	
	public int getTemplateID() {
		return templateID;
	}
	public void setTemplateID(int templateID) {
		this.templateID = templateID;
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

	public String getAmbitoApplicativo() {
		return ambitoApplicativo;
	}

	public void setAmbitoApplicativo(String ambitoApplicativo) {
		this.ambitoApplicativo = ambitoApplicativo;
	}
	
	
	
	
}
