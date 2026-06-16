package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class RichiestaBatchDaElaborareBean implements Serializable{
	
	private static final long serialVersionUID = 696066554079571012L;
	private long idRichiesta;
	private String priorita;
	private String codiceEvento;
	private String ambienteDestinazione;
	private String aggAmbienteDestinazione;
	private Date dataCreazione;
	private int numeroProfili;
	private String orarioInizioElab;
	private String orarioFineElab;
	private long sequence;
	private int numeroTentativi;
	private String inLavorazione;

	
	public long getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	public String getPriorita() {
		return priorita;
	}
	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}
	public String getCodiceEvento() {
		return codiceEvento;
	}
	public void setCodiceEvento(String codiceEvento) {
		this.codiceEvento = codiceEvento;
	}
	public String getAmbienteDestinazione() {
		return ambienteDestinazione;
	}
	public void setAmbienteDestinazione(String ambienteDestinazione) {
		this.ambienteDestinazione = ambienteDestinazione;
	}
	public String getAggAmbienteDestinazione() {
		return aggAmbienteDestinazione;
	}
	public void setAggAmbienteDestinazione(String aggAmbienteDestinazione) {
		this.aggAmbienteDestinazione = aggAmbienteDestinazione;
	}
	public Date getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public int getNumeroProfili() {
		return numeroProfili;
	}
	public void setNumeroProfili(int numeroProfili) {
		this.numeroProfili = numeroProfili;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	public String getOrarioInizioElab() {
		return orarioInizioElab;
	}
	public void setOrarioInizioElab(String orarioInizioElab) {
		this.orarioInizioElab = orarioInizioElab;
	}
	public String getOrarioFineElab() {
		return orarioFineElab;
	}
	public void setOrarioFineElab(String orarioFineElab) {
		this.orarioFineElab = orarioFineElab;
	}
	public int getNumeroTentativi() {
		return numeroTentativi;
	}
	public void setNumeroTentativi(int numeroTentativi) {
		this.numeroTentativi = numeroTentativi;
	}
	public String getInLavorazione() {
		return inLavorazione;
	}
	public void setInLavorazione(String inLavorazione) {
		this.inLavorazione = inLavorazione;
	}
 
 
}
