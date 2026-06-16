package it.finanze.scheduler.bean;

import java.io.Serializable;

public class UtenteEsternoDaAggiornareBean extends VisibilitaDaAggiornareBean implements Serializable {
	
	private static final long serialVersionUID = 4414811270725851180L;
	private String codiceUfficioPartenza;
	private String codiceUfficioDestinazione;
	
	
	public String getCodiceUfficioPartenza() {
		return codiceUfficioPartenza;
	}
	public void setCodiceUfficioPartenza(String codiceUfficioPartenza) {
		this.codiceUfficioPartenza = codiceUfficioPartenza;
	}
	public String getCodiceUfficioDestinazione() {
		return codiceUfficioDestinazione;
	}
	public void setCodiceUfficioDestinazione(String codiceUfficioDestinazione) {
		this.codiceUfficioDestinazione = codiceUfficioDestinazione;
	}
	
	

}
