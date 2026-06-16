package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;

public class GestoreOperatoriBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1765040817059123115L;
	
	private String nome, cognome, cf, cdr, struttura, quale_caso;

	
	private String codiceApplicazione;
	private Date dataInizioVal;
	private Date dataFineVal;
	private long idAuditInizio;
	private long idAuditFine;
	private String codiceFiscaleInizio;
	private String codiceFiscaleFine;
	private String email;
	private String assegnato;
	private String codiceGestore;
	private String codiceUfficio;
	private String descrizione;
	private String idRichiesta;
	private String gruppoGestori;
//	aggiunti campi sotto per caso di richiesta autorizzata di un AC con ambito applicativo 5(modifica richiesta il 28/09/2018)
	private String  gestOperStrutAutorizzIlivello;
	private String  gestOperStrutAutorizzIIlivello;

	
	
	public String getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(String idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getQuale_caso() {
		return quale_caso;
	}

	public void setQuale_caso(String quale_caso) {
		this.quale_caso = quale_caso;
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

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getCdr() {
		return cdr;
	}

	public void setCdr(String cdr) {
		this.cdr = cdr;
	}

	public String getStruttura() {
		return struttura;
	}

	public void setStruttura(String struttura) {
		this.struttura = struttura;
	}

	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	public Date getDataInizioVal() {
		return dataInizioVal;
	}

	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}

	public Date getDataFineVal() {
		return dataFineVal;
	}

	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}

	public long getIdAuditInizio() {
		return idAuditInizio;
	}

	public void setIdAuditInizio(long idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}

	public long getIdAuditFine() {
		return idAuditFine;
	}

	public void setIdAuditFine(long idAuditFine) {
		this.idAuditFine = idAuditFine;
	}

	public String getCodiceFiscaleInizio() {
		return codiceFiscaleInizio;
	}

	public void setCodiceFiscaleInizio(String codiceFiscaleInizio) {
		this.codiceFiscaleInizio = codiceFiscaleInizio;
	}

	public String getCodiceFiscaleFine() {
		return codiceFiscaleFine;
	}

	public void setCodiceFiscaleFine(String codiceFiscaleFine) {
		this.codiceFiscaleFine = codiceFiscaleFine;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAssegnato() {
		return assegnato;
	}

	public void setAssegnato(String assegnato) {
		this.assegnato = assegnato;
	}

	
	public Object clone() {
	    try {
	      return super.clone();
	    }
	    catch(CloneNotSupportedException e) {
	      return null;
	    }
	}

	@Override
	public String toString() {
		return "GestoreOperatoriBean [nome=" + nome + ", cognome=" + cognome
				+ ", cf=" + cf + ", cdr=" + cdr + ", struttura=" + struttura
				+ ", quale_caso=" + quale_caso + ", codiceApplicazione="
				+ codiceApplicazione + ", dataInizioVal=" + dataInizioVal
				+ ", dataFineVal=" + dataFineVal + ", idAuditInizio="
				+ idAuditInizio + ", idAuditFine=" + idAuditFine
				+ ", codiceFiscaleInizio=" + codiceFiscaleInizio
				+ ", codiceFiscaleFine=" + codiceFiscaleFine + ", email="
				+ email + ", assegnato=" + assegnato + "]";
	}

	public String getCodiceGestore() {
		return codiceGestore;
	}

	public void setCodiceGestore(String codiceGestore) {
		this.codiceGestore = codiceGestore;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getGruppoGestori() {
		return gruppoGestori;
	}

	public void setGruppoGestori(String gruppoGestori) {
		this.gruppoGestori = gruppoGestori;
	}

	public String getGestOperStrutAutorizzIlivello() {
		return gestOperStrutAutorizzIlivello;
	}

	public void setGestOperStrutAutorizzIlivello(
			String gestOperStrutAutorizzIlivello) {
		this.gestOperStrutAutorizzIlivello = gestOperStrutAutorizzIlivello;
	}

	public String getGestOperStrutAutorizzIIlivello() {
		return gestOperStrutAutorizzIIlivello;
	}

	public void setGestOperStrutAutorizzIIlivello(
			String gestOperStrutAutorizzIIlivello) {
		this.gestOperStrutAutorizzIIlivello = gestOperStrutAutorizzIIlivello;
	}

	
	
	
}
