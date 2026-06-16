package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;

public class CostantiSiga implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String valore;
	private String nota;
	private Date dataUltimaElaborazione;
	private String data;
	
	
	public Date getDataUltimaElaborazione() {
		return dataUltimaElaborazione;
	}
	public void setDataUltimaElaborazione(Date dataUltimaElaborazione) {
		this.dataUltimaElaborazione = dataUltimaElaborazione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValore() {
		return valore;
	}
	public void setValore(String valore) {
		this.valore = valore;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
