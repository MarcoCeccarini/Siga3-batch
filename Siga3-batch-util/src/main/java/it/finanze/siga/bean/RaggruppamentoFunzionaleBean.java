package it.finanze.siga.bean;

import java.io.Serializable;

public class RaggruppamentoFunzionaleBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5987560366310843736L;
	
	private String codiceApplicazioneUtente;
	private String codiceRaggruppamentoFunzionale;
	private String descrizione;
	private int assegnato = 1;
	
	public String getCodiceApplicazioneUtente() {
		return codiceApplicazioneUtente;
	}
	public void setCodiceApplicazioneUtente(String codiceApplicazioneUtente) {
		this.codiceApplicazioneUtente = codiceApplicazioneUtente;
	}
	public String getCodiceRaggruppamentoFunzionale() {
		return codiceRaggruppamentoFunzionale;
	}
	public void setCodiceRaggruppamentoFunzionale(
			String codiceRaggruppamentoFunzionale) {
		this.codiceRaggruppamentoFunzionale = codiceRaggruppamentoFunzionale;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getAssegnato() {
		return assegnato;
	}
	public void setAssegnato(int assegnato) {
		this.assegnato = assegnato;
	}
	
	
	
	
}
