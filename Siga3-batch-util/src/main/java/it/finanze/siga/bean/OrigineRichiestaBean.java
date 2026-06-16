package it.finanze.siga.bean;

import java.io.Serializable;

public class OrigineRichiestaBean extends BaseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6424344993304858409L;
	private String origineRichiesta;
	private String descrizioneOrigine;
	
	
	public String getDescrizioneOrigine() {
		return descrizioneOrigine;
	}

	public void setDescrizioneOrigine(String descrizioneOrigine) {
		this.descrizioneOrigine = descrizioneOrigine;
	}

	public String getOrigineRichiesta() {
		return origineRichiesta;
	}
	
	public void setOrigineRichiesta(String origineRichiesta) {
		this.origineRichiesta = origineRichiesta;
	}
}
