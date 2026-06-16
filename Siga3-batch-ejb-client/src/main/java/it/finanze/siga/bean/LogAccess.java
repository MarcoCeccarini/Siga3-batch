package it.finanze.siga.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class LogAccess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5873787059011886472L;
	
	private String operatore = "";
	private String ipAddress = "";
	private Timestamp data;
	
	public String getOperatore() {
		return operatore;
	}
	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	
	

}
