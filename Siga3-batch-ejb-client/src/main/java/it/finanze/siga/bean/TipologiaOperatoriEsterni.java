package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.List;

public class TipologiaOperatoriEsterni implements Serializable{

	private static final long serialVersionUID = -5910610774763010650L;
	private String codice;
	private String descrizione;
	private int codiceAmbito;
	private String ambitoDesc;
	private String senzaLimitazione;
	private List<ProfiloBean> abilitazioniList;
	private String dataStampa;
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(int codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	public String getAmbitoDesc() {
		return ambitoDesc;
	}
	public void setAmbitoDesc(String ambitoDesc) {
		this.ambitoDesc = ambitoDesc;
	}
	public String getSenzaLimitazione() {
		return senzaLimitazione;
	}
	public void setSenzaLimitazione(String senzaLimitazione) {
		this.senzaLimitazione = senzaLimitazione;
	}
	public List<ProfiloBean> getAbilitazioniList() {
		return abilitazioniList;
	}
	public void setAbilitazioniList(List<ProfiloBean> abilitazioniList) {
		this.abilitazioniList = abilitazioniList;
	}
	public String getDataStampa() {
		return dataStampa;
	}
	public void setDataStampa(String dataStampa) {
		this.dataStampa = dataStampa;
	}

	
	
	
	
}
