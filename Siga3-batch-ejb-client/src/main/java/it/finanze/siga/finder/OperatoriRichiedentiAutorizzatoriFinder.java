package it.finanze.siga.finder;

import java.util.Date;

public class OperatoriRichiedentiAutorizzatoriFinder extends BasePaginateFinder{
	
	private static final long serialVersionUID = -8919857598944603332L;
	private Date dataFineValMin;
	private Date dataFineValMax;
	private String cdrOperatore;
	private String cfRichiedente;
	private String cfAutorizzatore;
	private String cfOperatore;
	
	public Date getDataFineValMin() {
		return dataFineValMin;
	}
	public void setDataFineValMin(Date dataFineValMin) {
		this.dataFineValMin = dataFineValMin;
	}
	public Date getDataFineValMax() {
		return dataFineValMax;
	}
	public void setDataFineValMax(Date dataFineValMax) {
		this.dataFineValMax = dataFineValMax;
	}
	public String getCdrOperatore() {
		return cdrOperatore;
	}
	public void setCdrOperatore(String cdrOperatore) {
		this.cdrOperatore = cdrOperatore;
	}
	public String getCfRichiedente() {
		return cfRichiedente;
	}
	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}
	public String getCfAutorizzatore() {
		return cfAutorizzatore;
	}
	public void setCfAutorizzatore(String cfAutorizzatore) {
		this.cfAutorizzatore = cfAutorizzatore;
	}
	public String getCfOperatore() {
		return cfOperatore;
	}
	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}
	
	

}
