package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class ProfiloRoleBatchCoerenzaBean implements Serializable{

	private static final long serialVersionUID = 2896775982562887122L;
	private String codiceProfilo;
	private String descrizioneProfilo;
	private String explicitEntitlement;
	private Date dataUpd;
	
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getExplicitEntitlement() {
		return explicitEntitlement;
	}
	public void setExplicitEntitlement(String explicitEntitlement) {
		this.explicitEntitlement = explicitEntitlement;
	}
	public Date getDataUpd() {
		return dataUpd;
	}
	public void setDataUpd(Date dataUpd) {
		this.dataUpd = dataUpd;
	}
	public String getDescrizioneProfilo() {
		return descrizioneProfilo;
	}
	public void setDescrizioneProfilo(String descrizioneProfilo) {
		this.descrizioneProfilo = descrizioneProfilo;
	}
	
}
