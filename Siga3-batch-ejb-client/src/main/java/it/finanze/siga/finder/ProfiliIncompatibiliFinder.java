package it.finanze.siga.finder;

import java.util.Date;

public class ProfiliIncompatibiliFinder extends BasePaginateFinder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1571770529260186519L;
	
	private String profiloA,
				   profiloB;
	private Date dataInizioVal,
	             dataFineVal;

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
