package it.finanze.scheduler.bean;

import java.io.Serializable;

public class UtentiAggiornatiHRDAO implements Serializable {
	
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String descrCdr;
	private String codiceStruttura;
	private String codiceCdr;
	private String tipoEvento;
	private String codiceCdROld;
	private String codiceCdRNew;
	private String dataInserimento;
	private String dataElaborazione;
	private String dataInvioEmailRevoca;
	private String dataInvioEmailNew;
	private String dataInvioEmailOld;
	private String salvaguardia;
	private String emailNewCdr;
	private String emailOldCdr;
	private String dataIns;
	private String elabEmailCdrNew;
	private String elabEmailCdrOld;
	private String eMail;
	
 
	private static final long serialVersionUID = 1L;

	public UtentiAggiornatiHRDAO() {
		// TODO Stub di costruttore generato automaticamente
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public String getCodiceCdROld() {
		return codiceCdROld;
	}

	public void setCodiceCdROld(String codiceCdROld) {
		this.codiceCdROld = codiceCdROld;
	}

	public String getCodiceCdRNew() {
		return codiceCdRNew;
	}

	public void setCodiceCdRNew(String codiceCdRNew) {
		this.codiceCdRNew = codiceCdRNew;
	}

	public String getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(String dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public String getDataElaborazione() {
		return dataElaborazione;
	}

	public void setDataElaborazione(String dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}

	public String getDataInvioEmailRevoca() {
		return dataInvioEmailRevoca;
	}

	public void setDataInvioEmailRevoca(String dataInvioEmailRevoca) {
		this.dataInvioEmailRevoca = dataInvioEmailRevoca;
	}

	public String getSalvaguardia() {
		return salvaguardia;
	}

	public void setSalvaguardia(String salvaguardia) {
		this.salvaguardia = salvaguardia;
	}

	public String getEmailNewCdr() {
		return emailNewCdr;
	}

	public void setEmailNewCdr(String emailNewCdr) {
		this.emailNewCdr = emailNewCdr;
	}

	public String getEmailOldCdr() {
		return emailOldCdr;
	}

	public void setEmailOldCdr(String emailOldCdr) {
		this.emailOldCdr = emailOldCdr;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceStruttura() {
		return codiceStruttura;
	}

	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}

	public String getCodiceCdr() {
		return codiceCdr;
	}

	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}

	public String getDescrCdr() {
		return descrCdr;
	}

	public void setDescrCdr(String descrCdr) {
		this.descrCdr = descrCdr;
	}

	public String getDataInvioEmailNew() {
		return dataInvioEmailNew;
	}

	public void setDataInvioEmailNew(String dataInvioEmailNew) {
		this.dataInvioEmailNew = dataInvioEmailNew;
	}

	public String getDataInvioEmailOld() {
		return dataInvioEmailOld;
	}

	public void setDataInvioEmailOld(String dataInvioEmailOld) {
		this.dataInvioEmailOld = dataInvioEmailOld;
	}

	public String getDataIns() {
		return dataIns;
	}

	public void setDataIns(String dataIns) {
		this.dataIns = dataIns;
	}

	public String getElabEmailCdrNew() {
		return elabEmailCdrNew;
	}

	public void setElabEmailCdrNew(String elabEmailCdrNew) {
		this.elabEmailCdrNew = elabEmailCdrNew;
	}

	public String getElabEmailCdrOld() {
		return elabEmailCdrOld;
	}

	public void setElabEmailCdrOld(String elabEmailCdrOld) {
		this.elabEmailCdrOld = elabEmailCdrOld;
	}
	
	public String getEmail() {
		return eMail;
	}
	
	public void setEmail(String eMail) {
		this.eMail = eMail;
	}

}
