package it.finanze.siga.bean;

import java.sql.Timestamp;


public class ProfiloRichiestaBean extends ProfiloBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3339532616658551300L;
	
	private Integer idRichiesta;
	private String idRichiestaVisibilita;
	private String cfUtente;
	private String cfUtenteLoggato;
	private String operazioneEseguita;
	private Integer codiceAmbito;
	private String descrAmbitoAppl;
	private String codProfilo;

	
	/**
	 * Aggiunto per inserimento - dati relativi a operatore
	 */
	private String codiceCDR;
	private String cdrDescrizione;
	private String regione;
	private String provincia;
	private String profiloDataFine;
	private String profiloDataInizio;
	private String modalitaAssegnazione;
	private String operazioniProfilo;
	private String cfRichiedente;
	private String cfResponsabile;
	
	//Aggiunti per stampa profili richiesta
	private String descrAbilitazione;
	private String descrUffCdr;
	
	
	private String codRuolo;
	
//   private Date scadenza;
   
   private int numeroProfiliPerRG;
   private int numeroOperatoriPerRG;
   private int numeroOperatoriPerProfilo;
   private Timestamp dataElaborazione;
   
   private String evento;
   

public int getNumeroOperatoriPerRG() {
	return numeroOperatoriPerRG;
}
public void setNumeroOperatoriPerRG(int numeroOperatoriPerRG) {
	this.numeroOperatoriPerRG = numeroOperatoriPerRG;
}
	public int getNumeroOperatoriPerProfilo() {
	return numeroOperatoriPerProfilo;
}
public void setNumeroOperatoriPerProfilo(int numeroOperatoriPerProfilo) {
	this.numeroOperatoriPerProfilo = numeroOperatoriPerProfilo;
}
public Timestamp getDataElaborazione() {
	return dataElaborazione;
}
public void setDataElaborazione(Timestamp dataElaborazione) {
	this.dataElaborazione = dataElaborazione;
}
	public int getNumeroProfiliPerRG() {
	return numeroProfiliPerRG;
}
public void setNumeroProfiliPerRG(int numeroProfiliPerRG) {
	this.numeroProfiliPerRG = numeroProfiliPerRG;
}
	

	
	public String getCodRuolo() {
		return codRuolo;
	}
	public void setCodRuolo(String codRuolo) {
		this.codRuolo = codRuolo;
	}
	
	
	private String motivazioneFine;
 
	/** GETTERS AND SETTERS **/
	
	
	public String getIdRichiestaVisibilitaNOTNULL() {
		return idRichiestaVisibilita==null? "" : idRichiestaVisibilita;
	}
	
	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public String getTipoCDRNOTNULL() {
		return getTipoCDR()==null? "" : getTipoCDR();
	}	
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public String getTipoUfficioNOTNULL() {
		return getTipoUfficio()==null? "" : getTipoUfficio();
	}
	
	public String getOperazioneEseguita() {
		return operazioneEseguita;
	}
	public void setOperazioneEseguita(String operazioneEseguita) {
		this.operazioneEseguita = operazioneEseguita;
	}
	public Integer getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(Integer idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public String getCfUtenteLoggato() {
		return cfUtenteLoggato;
	}
	public void setCfUtenteLoggato(String cfUtenteLoggato) {
		this.cfUtenteLoggato = cfUtenteLoggato;
	}
	public Integer getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(Integer codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	public String getDescrAmbitoAppl() {
		return descrAmbitoAppl;
	}
	public void setDescrAmbitoAppl(String descrAmbitoAppl) {
		this.descrAmbitoAppl = descrAmbitoAppl;
	}
	public String getCdrDescrizione() {
		return cdrDescrizione;
	}
	public void setCdrDescrizione(String cdrDescrizione) {
		this.cdrDescrizione = cdrDescrizione;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getProfiloDataFine() {
		return profiloDataFine;
	}
	public void setProfiloDataFine(String profiloDataFine) {
		this.profiloDataFine = profiloDataFine;
	}
	public String getProfiloDataInizio() {
		return profiloDataInizio;
	}
	public void setProfiloDataInizio(String profiloDataInizio) {
		this.profiloDataInizio = profiloDataInizio;
	}
	public String getModalitaAssegnazione() {
		return modalitaAssegnazione;
	}
	public void setModalitaAssegnazione(String modalitaAssegnazione) {
		this.modalitaAssegnazione = modalitaAssegnazione;
	}
	public String getOperazioniProfilo() {
		return operazioniProfilo;
	}
	public void setOperazioniProfilo(String operazioniProfilo) {
		this.operazioniProfilo = operazioniProfilo;
	}
	
 
	public String getDescrAbilitazione() {
		return descrAbilitazione;
	}
	public void setDescrAbilitazione(String descrAbilitazione) {
		this.descrAbilitazione = descrAbilitazione;
	}
	public String getDescrUffCdr() {
		return descrUffCdr;
	}
	public void setDescrUffCdr(String descrUffCdr) {
		this.descrUffCdr = descrUffCdr;
	}
	public String getMotivazioneFine() {
		return motivazioneFine;
	}
	public void setMotivazioneFine(String motivazioneFine) {
		this.motivazioneFine = motivazioneFine;
	}
	public String getCfRichiedente() {
		return cfRichiedente;
	}
	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}
	public String getCfResponsabile() {
		return cfResponsabile;
	}
	public void setCfResponsabile(String cfResponsabile) {
		this.cfResponsabile = cfResponsabile;
	}
	public String getCodProfilo() {
		return codProfilo;
	}
	public void setCodProfilo(String codProfilo) {
		this.codProfilo = codProfilo;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}

	
}
