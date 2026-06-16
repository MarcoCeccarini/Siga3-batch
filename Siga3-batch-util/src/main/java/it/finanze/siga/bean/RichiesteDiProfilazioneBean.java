package it.finanze.siga.bean;

import java.io.Serializable;

public class RichiesteDiProfilazioneBean extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3269294246018167566L;
	/**
	 * 
	 */
	
	private int num_richieste_di_profilazione;
	
	public int getNum_richieste_di_profilazione() {
		return num_richieste_di_profilazione;
	}
	
	public void setNum_richieste_di_profilazione(int num_richieste_di_profilazione) {
		this.num_richieste_di_profilazione = num_richieste_di_profilazione;
	}
	
	
	
}
