package it.finanze.siga.bean;

import java.io.Serializable;

public class IterExportBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5402706684342879474L;

	private IterBean iter;
	private StrutturaIterBean percorsoAutorizzativo;
	private ProfiloBean abilitazione;
	private boolean tipoIter;
	private boolean tipoUffRicNonSpecificato;
	
	public IterExportBean(IterBean iter){
		this.iter = iter;
	};
	
	public IterBean getIter() {
		return iter;
	}
	public void setIter(IterBean iter) {
		this.iter = iter;
	}
	public StrutturaIterBean getPercorsoAutorizzativo() {
		return percorsoAutorizzativo;
	}
	public void setPercorsoAutorizzativo(StrutturaIterBean percorsoAutorizzativo) {
		this.percorsoAutorizzativo = percorsoAutorizzativo;
	}
	public ProfiloBean getAbilitazione() {
		return abilitazione;
	}
	public void setAbilitazione(ProfiloBean abilitazione) {
		this.abilitazione = abilitazione;
	}

	public boolean isTipoUffRicNonSpecificato() {
		return tipoUffRicNonSpecificato;
	}

	public void setTipoUffRicNonSpecificato(boolean tipoUffRicNonSpecificato) {
		this.tipoUffRicNonSpecificato = tipoUffRicNonSpecificato;
	}

	public boolean isTipoIter() {
		return tipoIter;
	}

	public void setTipoIter(boolean tipoIter) {
		this.tipoIter = tipoIter;
	}

	
	
	
}
