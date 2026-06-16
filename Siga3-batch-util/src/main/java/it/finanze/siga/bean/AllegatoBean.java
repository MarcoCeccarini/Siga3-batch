package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;

public class AllegatoBean implements Serializable {

	private static final long serialVersionUID = -7018372020867431373L;
	
	private String codiceFiscale;
	private Date dataInizioVal; 
	byte[] bFile;
	String fileName;
	private Integer progAll;
	private String tipo;
	public Date getDataInizioVal() {
		return dataInizioVal;
	}
	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getbFile() {
		return bFile;
	}

	public void setbFile(byte[] bFile) {
		this.bFile = bFile;
	}
	public Integer getProgAll() {
		return progAll;
	}
	public void setProgAll(Integer progAll) {
		this.progAll = progAll;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
