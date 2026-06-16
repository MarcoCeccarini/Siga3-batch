package it.finanze.siga.finder;

import java.util.List;

public class ApplicazioniFinder extends BasePaginateFinder {
	private static final long serialVersionUID = 1L;
	private String[] codici;
	private String codice;
	private String descrizione;
	private String ambito;
	private String tipoProfilo;
	private String cfAmmCentrale;
	private String ruoloLocale;
	private String ruoloAmmCentrAppl;
	private String ruoloRegionale;
	private String cfUtente;
	private String noRuolo;
	private List<String> verticiStruttureList;
	
	
	public String getRuoloLocale() {
		return ruoloLocale;
	}
	public void setRuoloLocale(String ruoloLocale) {
		this.ruoloLocale = ruoloLocale;
	}
	public String getRuoloAmmCentrAppl() {
		return ruoloAmmCentrAppl;
	}
	public void setRuoloAmmCentrAppl(String ruoloAmmCentrAppl) {
		this.ruoloAmmCentrAppl = ruoloAmmCentrAppl;
	}
	public String getRuoloRegionale() {
		return ruoloRegionale;
	}
	public void setRuoloRegionale(String ruoloRegionale) {
		this.ruoloRegionale = ruoloRegionale;
	}
	public String[] getCodici() {
		return codici;
	}
	public void setCodici(String[] codici) {
		this.codici = codici;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getTipoProfilo() {
		return tipoProfilo;
	}
	public void setTipoProfilo(String tipoProfilo) {
		this.tipoProfilo = tipoProfilo;
	}
	public String getCfAmmCentrale() {
		return cfAmmCentrale;
	}
	public void setCfAmmCentrale(String cfAmmCentrale) {
		this.cfAmmCentrale = cfAmmCentrale;
	}
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public String getNoRuolo() {
		return noRuolo;
	}
	public void setNoRuolo(String noRuolo) {
		this.noRuolo = noRuolo;
	}
	public List<String> getVerticiStruttureList() {
		return verticiStruttureList;
	}
	public void setVerticiStruttureList(List<String> verticiStruttureList) {
		this.verticiStruttureList = verticiStruttureList;
	}
	
	
}
