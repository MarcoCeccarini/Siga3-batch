package it.finanze.siga.bean;


public class ProfiloAbilitazioneBatchBean extends ProfiloRichiestaBean  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3339532616658551300L;
	
 	private String codiceEvento;
	private String descrEvento;
	private String priorita;
	private String notaRichiesta;
	private String richiedente;
	private String oraInizio;
	private String oraFine;
	private String cdrUtente;
	
	
	
	public String getCodiceEvento() {
		return codiceEvento;
	}
	public void setCodiceEvento(String codiceEvento) {
		this.codiceEvento = codiceEvento;
	}
	 
	public String getPriorita() {
		return priorita;
	}
	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}
	public String getNotaRichiesta() {
		return notaRichiesta;
	}
	public void setNotaRichiesta(String notaRichiesta) {
		this.notaRichiesta = notaRichiesta;
	}
 
	public String getOraInizio() {
		return oraInizio;
	}
	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}
	public String getOraFine() {
		return oraFine;
	}
	public void setOraFine(String oraFine) {
		this.oraFine = oraFine;
	}
	public String getDescrEvento() {
		return descrEvento;
	}
	public void setDescrEvento(String descrEvento) {
		this.descrEvento = descrEvento;
	}
	public String getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}
	public String getCdrUtente() {
		return cdrUtente;
	}
	public void setCdrUtente(String cdrUtente) {
		this.cdrUtente = cdrUtente;
	}
 

}
