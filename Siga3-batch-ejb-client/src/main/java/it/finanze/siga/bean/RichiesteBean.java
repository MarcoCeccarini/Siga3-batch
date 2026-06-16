package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RichiesteBean   implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	 private List <RichiestaBean> richiesteAsGestore= new ArrayList<RichiestaBean>();
	 private List <RichiestaBean> richiesteAsAutorizzatoreI= new ArrayList<RichiestaBean>();
	 private List <RichiestaBean> richiesteAsAutorizzatoreII= new ArrayList<RichiestaBean>();
	 private List <RichiestaBean> richiesteAsRichiedente= new ArrayList<RichiestaBean>();

	 private List <RichiestaBean> richiesteAsOperatore= new ArrayList<RichiestaBean>();
	 private List <RichiestaBean> richiesteAsDelegato= new ArrayList<RichiestaBean>();
	 private List <RichiestaBean> richiesteDaArchiviare= new ArrayList<RichiestaBean>();
	 
	 
	 public List<RichiestaBean> getRichiesteAsDelegato() {
		return richiesteAsDelegato;
	}
	public void setRichiesteAsDelegato(List<RichiestaBean> richiesteAsDelegato) {
		this.richiesteAsDelegato = richiesteAsDelegato;
	}
	public List<RichiestaBean> getRichiesteAsGestore() {
		return richiesteAsGestore;
	}
	public void setRichiesteAsGestore(List<RichiestaBean> richiesteAsGestore) {
		this.richiesteAsGestore = richiesteAsGestore;
	}
	public List<RichiestaBean> getRichiesteAsAutorizzatoreI() {
		return richiesteAsAutorizzatoreI;
	}
	public void setRichiesteAsAutorizzatoreI(
			List<RichiestaBean> richiesteAsAutorizzatoreI) {
		this.richiesteAsAutorizzatoreI = richiesteAsAutorizzatoreI;
	}
	public List<RichiestaBean> getRichiesteAsAutorizzatoreII() {
		return richiesteAsAutorizzatoreII;
	}
	public void setRichiesteAsAutorizzatoreII(
			List<RichiestaBean> richiesteAsAutorizzatoreII) {
		this.richiesteAsAutorizzatoreII = richiesteAsAutorizzatoreII;
	}
	public List<RichiestaBean> getRichiesteAsRichiedente() {
		return richiesteAsRichiedente;
	}
	public void setRichiesteAsRichiedente(
			List<RichiestaBean> richiesteAsRichiedente) {
		this.richiesteAsRichiedente = richiesteAsRichiedente;
	}
	public List<RichiestaBean> getRichiesteAsOperatore() {
		return richiesteAsOperatore;
	}
	public void setRichiesteAsOperatore(
			List<RichiestaBean> richiesteAsOperatore) {
		this.richiesteAsOperatore = richiesteAsOperatore;
	}
	
	 public List<RichiestaBean> getRichiesteDaArchiviare() {
		return richiesteDaArchiviare;
	}
	public void setRichiesteDaArchiviare(List<RichiestaBean> richiesteDaArchiviare) {
		this.richiesteDaArchiviare = richiesteDaArchiviare;
	}

	 
}
