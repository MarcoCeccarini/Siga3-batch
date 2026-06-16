package it.finanze.siga.bean;

import java.io.Serializable;

public class ElementoCatalogoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1237341292828685548L;

	private FunzioneBean funzione;
	private ApplicazioneUtenteBean appUtente;
	private RaggruppamentoFunzionaleBean raggruppamentoFunzionale;
	private AbilitazioneBean profilo;

	
	public FunzioneBean getFunzione() {
		return funzione;
	}
	public void setFunzione(FunzioneBean funzione) {
		this.funzione = funzione;
	}
	public ApplicazioneUtenteBean getAppUtente() {
		return appUtente;
	}
	public void setAppUtente(ApplicazioneUtenteBean appUtente) {
		this.appUtente = appUtente;
	}
	public RaggruppamentoFunzionaleBean getRaggruppamentoFunzionale() {
		return raggruppamentoFunzionale;
	}
	public void setRaggruppamentoFunzionale(
			RaggruppamentoFunzionaleBean raggruppamentoFunzionale) {
		this.raggruppamentoFunzionale = raggruppamentoFunzionale;
	}
	public AbilitazioneBean getProfilo() {
		return profilo;
	}
	public void setProfilo(AbilitazioneBean profilo) {
		this.profilo = profilo;
	}
	
	
	
	
	
}
