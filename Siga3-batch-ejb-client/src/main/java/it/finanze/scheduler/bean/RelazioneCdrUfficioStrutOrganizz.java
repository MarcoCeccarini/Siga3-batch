package it.finanze.scheduler.bean;

public class RelazioneCdrUfficioStrutOrganizz extends RelazioneCdRUfficioStrut {


	private static final long serialVersionUID = -2655497044982762288L;
	private String flagEmail;
	private String descrizioneCdr;
	
	public String getFlagEmail() {
		return flagEmail;
	}
	public void setFlagEmail(String flagEmail) {
		this.flagEmail = flagEmail;
	}
	public String getDescrizioneCdr() {
		return descrizioneCdr;
	}
	public void setDescrizioneCdr(String descrizioneCdr) {
		this.descrizioneCdr = descrizioneCdr;
	}
	
}
