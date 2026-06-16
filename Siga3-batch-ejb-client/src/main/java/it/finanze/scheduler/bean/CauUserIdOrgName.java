package it.finanze.scheduler.bean;

import java.io.Serializable;

public class CauUserIdOrgName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String orgName;
	private String eMail;
	

	public CauUserIdOrgName() {
		// TODO Stub di costruttore generato automaticamente
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String geteMail() {
		return this.eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

}
