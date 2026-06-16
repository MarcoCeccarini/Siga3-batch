package it.finanze.siga.bean;

import java.io.Serializable;

public class RecordCsvCruscottoBean implements Serializable{
	
	private static final long serialVersionUID = -1571385960858778583L;
	private String server;
	private String user;
	private String folder;
	private String file;
	private String control;
	private String permissions;
	
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getControl() {
		return control;
	}
	public void setControl(String control) {
		this.control = control;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	

}
