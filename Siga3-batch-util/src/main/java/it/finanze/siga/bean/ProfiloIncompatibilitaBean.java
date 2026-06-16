package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;



public class ProfiloIncompatibilitaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String profiloA;
	private String profiloB;
	private String rgProfiloA;
	private String rgProfiloB;
	private Date dataInizioVal;
	private Date dataFineVal;
	
	public String getProfiloA() {
		return profiloA;
	}
	public void setProfiloA(String profiloA) {
		this.profiloA = profiloA;
	}
	public String getProfiloB() {
		return profiloB;
	}
	public void setProfiloB(String profiloB) {
		this.profiloB = profiloB;
	}
	public String getRgProfiloA() {
		return rgProfiloA;
	}
	public void setRgProfiloA(String rgProfiloA) {
		this.rgProfiloA = rgProfiloA;
	}
	public String getRgProfiloB() {
		return rgProfiloB;
	}
	public void setRgProfiloB(String rgProfiloB) {
		this.rgProfiloB = rgProfiloB;
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

	

}
