package it.finanze.siga.bean;

import java.io.Serializable;

public class ControlliRecCaricMassiviBean implements Serializable{

	private static final long serialVersionUID = -3417354043913678173L;
	private int numMaxRecordFile;
	private int numMaxRecordContImm;
	private int numMaxRecordElabImm;
	
	public int getNumMaxRecordFile() {
		return numMaxRecordFile;
	}
	public void setNumMaxRecordFile(int numMaxRecordFile) {
		this.numMaxRecordFile = numMaxRecordFile;
	}
	public int getNumMaxRecordContImm() {
		return numMaxRecordContImm;
	}
	public void setNumMaxRecordContImm(int numMaxRecordContImm) {
		this.numMaxRecordContImm = numMaxRecordContImm;
	}
	public int getNumMaxRecordElabImm() {
		return numMaxRecordElabImm;
	}
	public void setNumMaxRecordElabImm(int numMaxRecordElabImm) {
		this.numMaxRecordElabImm = numMaxRecordElabImm;
	}
	
	
}
