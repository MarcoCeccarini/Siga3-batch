package it.finanze.siga.finder;

import java.util.List;

public class IterProfiliApplicazioniFinder extends BasePaginateFinder {	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2503387842032525152L;
	
	private String codici_applicazioni;
	private List<String> codiceApplicazList;
	private String data;
	
	public String getCodici_applicazioni() {
		return codici_applicazioni;
	}
	public void setCodici_applicazioni(String codici_applicazioni) {
		this.codici_applicazioni = codici_applicazioni;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public List<String> getCodiceApplicazList() {
		return codiceApplicazList;
	}
	public void setCodiceApplicazList(List<String> codiceApplicazList) {
		this.codiceApplicazList = codiceApplicazList;
	}

}
