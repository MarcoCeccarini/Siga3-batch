package it.finanze.siga.finder;




public class CheckIterBaseProfiloFinder extends BaseFinder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3198276255149995679L;
	
	private String codiceCDR;
	private String codiceAmbitoApplicativo;
	private String tipoAbilitazione;
	
	public String getTipoAbilitazione() {
		return tipoAbilitazione;
	}
	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
	}
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public String getCodiceAmbitoApplicativo() {
		return codiceAmbitoApplicativo;
	}
	public void setCodiceAmbitoApplicativo(String codiceAmbitoApplicazione) {
		this.codiceAmbitoApplicativo = codiceAmbitoApplicazione;
	}
	
}
