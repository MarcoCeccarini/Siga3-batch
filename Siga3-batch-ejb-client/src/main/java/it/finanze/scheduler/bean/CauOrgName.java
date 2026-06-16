package it.finanze.scheduler.bean;

import java.io.Serializable;

public class CauOrgName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orgName;
	private String fatherOrgName;

	/**
	 * 
	 */
	

	public CauOrgName() {
		// TODO Stub di costruttore generato automaticamente
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getFatherOrgName() {
		return fatherOrgName;
	}

	public void setFatherOrgName(String fatherOrgName) {
		this.fatherOrgName = fatherOrgName;
	}

}
