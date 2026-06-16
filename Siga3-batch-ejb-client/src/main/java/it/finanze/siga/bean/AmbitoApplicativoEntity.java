package it.finanze.siga.bean;

import java.io.Serializable;

public class AmbitoApplicativoEntity implements Serializable{


	private static final long serialVersionUID = -6305588347770241644L;
	
	
	public String codiceAmbito,sincronizzataCAU, descrizione,descrizioneConcettuale;


	public String getCodiceAmbito() {
		return codiceAmbito;
	}


	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}


	public String getSincronizzataCAU() {
		return sincronizzataCAU;
	}


	public void setSincronizzataCAU(String sincronizzataCAU) {
		this.sincronizzataCAU = sincronizzataCAU;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public String getDescrizioneConcettuale() {
		return descrizioneConcettuale;
	}


	public void setDescrizioneConcettuale(String descrizioneConcettuale) {
		this.descrizioneConcettuale = descrizioneConcettuale;
	}
	
	

}
