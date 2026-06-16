package it.finanze.siga.bean;

public class ApplicazioneCatalogoBean extends ApplicazioneUtenteBean {

	private static final long serialVersionUID = 8158074031805872377L;
	private String codiceProfilo;
	private String descrizioneProfilo;
	
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getDescrizioneProfilo() {
		return descrizioneProfilo;
	}
	public void setDescrizioneProfilo(String descrizioneProfilo) {
		this.descrizioneProfilo = descrizioneProfilo;
	}
	
	
}
