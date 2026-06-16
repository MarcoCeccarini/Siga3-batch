package it.finanze.siga.bean;

import java.io.Serializable;

public class AutorizzatoreBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 966064531640708070L;
	
	private String nome;
	private String cognome;
	private String cf;
	private String cdr;
	private String previsto_autorizzatore_ii_liv;
	
	public String getPrevisto_autorizzatore_ii_liv() {
		return previsto_autorizzatore_ii_liv;
	}
	public void setPrevisto_autorizzatore_ii_liv(
			String previsto_autorizzatore_ii_liv) {
		this.previsto_autorizzatore_ii_liv = previsto_autorizzatore_ii_liv;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getCdr() {
		return cdr;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}
	
	
}
