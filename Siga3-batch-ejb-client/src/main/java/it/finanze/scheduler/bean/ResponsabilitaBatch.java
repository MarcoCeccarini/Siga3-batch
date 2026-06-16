package it.finanze.scheduler.bean;

import java.sql.Date;

public class ResponsabilitaBatch {
	
	private Date lastElabResponsabilitaOaHr; 
	private String elabResponsabilitaOaHr ;
	private Date lastElabResponsabilitaOaCa;
	private String elabResponsabilitaOaCa ;
	private String criterioRespHR ;
	private String criterioRespOA;
 
	
	public Date getLastElabResponsabilitaOaHr() {
		return lastElabResponsabilitaOaHr;
	}
	public void setLastElabResponsabilitaOaHr(Date lastElabResponsabilitaOaHr) {
		this.lastElabResponsabilitaOaHr = lastElabResponsabilitaOaHr;
	}
	public String getElabResponsabilitaOaHr() {
		return elabResponsabilitaOaHr;
	}
	public void setElabResponsabilitaOaHr(String elabResponsabilitaOaHr) {
		this.elabResponsabilitaOaHr = elabResponsabilitaOaHr;
	}
	public Date getLastElabResponsabilitaOaCa() {
		return lastElabResponsabilitaOaCa;
	}
	public void setLastElabResponsabilitaOaCa(Date lastElabResponsabilitaOaCa) {
		this.lastElabResponsabilitaOaCa = lastElabResponsabilitaOaCa;
	}
	public String getElabResponsabilitaOaCa() {
		return elabResponsabilitaOaCa;
	}
	public void setElabResponsabilitaOaCa(String elabResponsabilitaOaCa) {
		this.elabResponsabilitaOaCa = elabResponsabilitaOaCa;
	}
	public String getCriterioRespHR() {
		return criterioRespHR;
	}
	public void setCriterioRespHR(String criterioRespHR) {
		this.criterioRespHR = criterioRespHR;
	}
	public String getCriterioRespOA() {
		return criterioRespOA;
	}
	public void setCriterioRespOA(String criterioRespOA) {
		this.criterioRespOA = criterioRespOA;
	}
 


}
