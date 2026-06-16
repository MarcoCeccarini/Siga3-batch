package it.finanze.siga.finder;

import java.io.Serializable;

public class GestoriFinder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String iterID;
	private String codiceCDR;
	private String cfRichiedente;
	private String cfAutorizzatore1;
	private String cfAutorizzatore2;
	private String codApp; 
	private String cdrAutorizzatore1;
	private String cdrAutorizzatore2;
	
	private String gestoreStruttura;
	private String gestoreCDR;
	private String richiestaAutorizzata;

	public String getIterID() {
		return iterID;
	}

	public void setIterID(String iterID) {
		this.iterID = iterID;
	}

	public String getCfRichiedente() {
		return cfRichiedente;
	}

	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}

	public String getCfAutorizzatore1() {
		return cfAutorizzatore1;
	}

	public void setCfAutorizzatore1(String cfAutorizzatore1) {
		this.cfAutorizzatore1 = cfAutorizzatore1;
	}

	public String getCfAutorizzatore2() {
		return cfAutorizzatore2;
	}

	public void setCfAutorizzatore2(String cfAutorizzatore2) {
		this.cfAutorizzatore2 = cfAutorizzatore2;
	}

	public String getCodiceCDR() {
		return codiceCDR;
	}

	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}

	public String getGestoreCDR() {
		return gestoreCDR;
	}

	public void setGestoreCDR(String gestoreCDR) {
		this.gestoreCDR = gestoreCDR;
	}

	public String getGestoreStruttura() {
		return gestoreStruttura;
	}

	public void setGestoreStruttura(String gestoreStruttura) {
		this.gestoreStruttura = gestoreStruttura;
	}

	public String getCodApp() {
		return codApp;
	}

	public void setCodApp(String codApp) {
		this.codApp = codApp;
	}

	public String getCdrAutorizzatore1() {
		return cdrAutorizzatore1;
	}

	public void setCdrAutorizzatore1(String cdrAutorizzatore1) {
		this.cdrAutorizzatore1 = cdrAutorizzatore1;
	}

	public String getCdrAutorizzatore2() {
		return cdrAutorizzatore2;
	}

	public void setCdrAutorizzatore2(String cdrAutorizzatore2) {
		this.cdrAutorizzatore2 = cdrAutorizzatore2;
	}

	public String getRichiestaAutorizzata() {
		return richiestaAutorizzata;
	}

	public void setRichiestaAutorizzata(String richiestaAutorizzata) {
		this.richiestaAutorizzata = richiestaAutorizzata;
	}
	
	
}
