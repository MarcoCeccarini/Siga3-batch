package it.finanze.siga.bean;

import java.io.Serializable;

public class AmbitoBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7566981725012675573L;
	
	private String codiceAmbito;
	private String descrizione;
	private String descrizioneConcettuale;
	private String sincronizzatoCAU;
	private String numRichieste;
	
	/**
	 * per visualizzazione
	 */
	private String selezionabile;
	
	public String getNumRichieste() {
		return numRichieste;
	}

	public void setNumRichieste(String numRichieste) {
		this.numRichieste = numRichieste;
	}
	
	public String getSincronizzatoCAU() {
		return sincronizzatoCAU;
	}

	public void setSincronizzatoCAU(String sincronizzatoCAU) {
		this.sincronizzatoCAU = sincronizzatoCAU;
	}

	public String getDescrizioneConcettuale() {
		return descrizioneConcettuale;
	}

	public void setDescrizioneConcettuale(String descrizioneConcettuale) {
		this.descrizioneConcettuale = descrizioneConcettuale;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodiceAmbito() {
		return codiceAmbito;
	}

	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}

	public String getSelezionabile() {
		return selezionabile;
	}

	public void setSelezionabile(String selezionabile) {
		this.selezionabile = selezionabile;
	}
	
}
