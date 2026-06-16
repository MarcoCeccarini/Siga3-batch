package it.finanze.scheduler.bean;

import java.io.Serializable;

public class VisibilitaDaAggiornareBean implements Serializable{


	private static final long serialVersionUID = -3987768983388737919L;
	private String codiceCdRPartenza;
	private String codiceCdRDestinazione;
	private String descrCodiceCdRPartenza;
	private String descrCodiceCdRDestinazione;
	private String idRichiestaVisibilita;
	private String cfUtente;
	private String cognomeUtente;
	private String nomeUtente;
	private String idRichiesta;
	private String erroreWs;
	
	public VisibilitaDaAggiornareBean() {
		// TODO Stub di costruttore generato automaticamente
	}

	public String getCodiceCdRPartenza() {
		return codiceCdRPartenza;
	}

	public void setCodiceCdRPartenza(String codiceCdRPartenza) {
		this.codiceCdRPartenza = codiceCdRPartenza;
	}

	public String getCodiceCdRDestinazione() {
		return codiceCdRDestinazione;
	}

	public void setCodiceCdRDestinazione(String codiceCdRDestinazione) {
		this.codiceCdRDestinazione = codiceCdRDestinazione;
	}

	public String getDescrCodiceCdRPartenza() {
		return descrCodiceCdRPartenza;
	}

	public void setDescrCodiceCdRPartenza(String descrCodiceCdRPartenza) {
		this.descrCodiceCdRPartenza = descrCodiceCdRPartenza;
	}

	public String getDescrCodiceCdRDestinazione() {
		return descrCodiceCdRDestinazione;
	}

	public void setDescrCodiceCdRDestinazione(String descrCodiceCdRDestinazione) {
		this.descrCodiceCdRDestinazione = descrCodiceCdRDestinazione;
	}

	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}

	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}

	public String getCfUtente() {
		return cfUtente;
	}

	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}

	public String getCognomeUtente() {
		return cognomeUtente;
	}

	public void setCognomeUtente(String cognomeUtente) {
		this.cognomeUtente = cognomeUtente;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(String idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getErroreWs() {
		return erroreWs;
	}

	public void setErroreWs(String erroreWs) {
		this.erroreWs = erroreWs;
	}

	
	
}
