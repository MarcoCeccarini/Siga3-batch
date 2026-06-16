package it.finanze.siga.bean;

import java.io.Serializable;


public class AllegatoCarMassivoBean implements Serializable {

	private static final long serialVersionUID = -3811283404226148708L;
	
	private long idAllegato;
	private long idCaricamento;
	private byte[] bFile;
	private String fileName;
	private String tipo;

	
	public long getIdAllegato() {
		return idAllegato;
	}
	public void setIdAllegato(long idAllegato) {
		this.idAllegato = idAllegato;
	}
	public long getIdCaricamento() {
		return idCaricamento;
	}
	public void setIdCaricamento(long idCaricamento) {
		this.idCaricamento = idCaricamento;
	}
	public byte[] getbFile() {
		return bFile;
	}
	public void setbFile(byte[] bFile) {
		this.bFile = bFile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}
