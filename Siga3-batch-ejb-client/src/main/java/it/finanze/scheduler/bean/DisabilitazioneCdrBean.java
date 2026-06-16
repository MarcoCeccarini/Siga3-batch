package it.finanze.scheduler.bean;

import java.io.Serializable;

public class DisabilitazioneCdrBean extends VisibilitaDaAggiornareBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7725040312148350148L;

	private String codiceRoleGroup;
	private String descrizioneRoleGroup;
	private String codiceProfilo;
	private String descrizioneProfilo;
	private String codiceCdrAbilitazione;
	private String descrizioneCdr;
	
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getDescrizioneRoleGroup() {
		return descrizioneRoleGroup;
	}
	public void setDescrizioneRoleGroup(String descrizioneRoleGroup) {
		this.descrizioneRoleGroup = descrizioneRoleGroup;
	}
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
	public String getCodiceCdrAbilitazione() {
		return codiceCdrAbilitazione;
	}
	public void setCodiceCdrAbilitazione(String codiceCdrAbilitazione) {
		this.codiceCdrAbilitazione = codiceCdrAbilitazione;
	}
	public String getDescrizioneCdr() {
		return descrizioneCdr;
	}
	public void setDescrizioneCdr(String descrizioneCdr) {
		this.descrizioneCdr = descrizioneCdr;
	}
	
	
}
