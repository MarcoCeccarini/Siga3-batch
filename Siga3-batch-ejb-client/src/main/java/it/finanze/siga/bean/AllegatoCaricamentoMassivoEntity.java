package it.finanze.siga.bean;

import java.io.Serializable;

public class AllegatoCaricamentoMassivoEntity implements Serializable{

	private static final long serialVersionUID = 5891289246104274329L;
	public byte[] bFileAllegato;
	public String tipo;
	public String nomeFile;
	public String idAllegato;
	public String idCaricamento;
	
	
	public byte[] getbFileAllegato() {
		return bFileAllegato;
	}
	public void setbFileAllegato(byte[] bFileAllegato) {
		this.bFileAllegato = bFileAllegato;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public String getIdAllegato() {
		return idAllegato;
	}
	public void setIdAllegato(String idAllegato) {
		this.idAllegato = idAllegato;
	}
	public String getIdCaricamento() {
		return idCaricamento;
	}
	public void setIdCaricamento(String idCaricamento) {
		this.idCaricamento = idCaricamento;
	}
	
}
