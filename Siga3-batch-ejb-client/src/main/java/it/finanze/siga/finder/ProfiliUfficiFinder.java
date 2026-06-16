package it.finanze.siga.finder;

import java.util.List;

public class ProfiliUfficiFinder extends BaseFinder{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> profili;
	
	private String applicazione,abilitazione;

	
	
	
	
	public List<String> getProfili() {
		return profili;
	}

	public void setProfili(List<String> profili) {
		this.profili = profili;
	}

	public String getApplicazione() {
		return applicazione;
	}

	public void setApplicazione(String applicazione) {
		this.applicazione = applicazione;
	}

	public String getAbilitazione() {
		return abilitazione;
	}

	public void setAbilitazione(String abilitazione) {
		this.abilitazione = abilitazione;
	}
	
	
	
	

}
