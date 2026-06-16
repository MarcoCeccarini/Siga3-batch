package it.finanze.siga.finder;

import java.util.Date;


public class AssociazOperRichiAutorFinder extends BaseFinder {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4846754119881663259L;
	private String cfOperatore;
	private String codiceCdr;
	private String cfRichiedente;
	private String cfRichiedenteCdr;
	private String cdrArchiviazione;
	private String cdrRichiedente;
	private String cfAutorizzatore; 
	private String cfArchiviazione; 
	private String cdrAutorizzatore;
	private Date dataInizioVal; 
	private Date dataFineVal; 
	private int idAuditInizio; 
	private String cfInizio; 
	private int idAuditFine; 
	private String cfFine;
	private Date dataChiusura; 
	private String operazione;
	
	private boolean inserimento;
	
	
	public Date getDataChiusura() {
		return dataChiusura;
	}
	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}
	private String cfNONRichiedente;
	
	public String getCfOperatore() {
		return cfOperatore;
	}
	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getCfRichiedente() {
		return cfRichiedente;
	}
	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}
	public String getCdrRichiedente() {
		return cdrRichiedente;
	}
	public void setCdrRichiedente(String cdrRichiedente) {
		this.cdrRichiedente = cdrRichiedente;
	}
	public String getCfAutorizzatore() {
		return cfAutorizzatore;
	}
	public void setCfAutorizzatore(String cfAutorizzatore) {
		this.cfAutorizzatore = cfAutorizzatore;
	}
	public String getCodiceCdrAutorizzatore() {
		return cdrAutorizzatore;
	}
	public void setCdrAutorizzatore(String cdrAutorizzatore) {
		this.cdrAutorizzatore = cdrAutorizzatore;
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
	public int getIdAuditInizio() {
		return idAuditInizio;
	}
	public void setIdAuditInizio(int idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}
	public String getCfInizio() {
		return cfInizio;
	}
	public void setCfInizio(String cfInizio) {
		this.cfInizio = cfInizio;
	}
	public int getIdAuditFine() {
		return idAuditFine;
	}
	public void setIdAuditFine(int idAuditFine) {
		this.idAuditFine = idAuditFine;
	}
	public String getCfFine() {
		return cfFine;
	}
	public void setCfFine(String cfFine) {
		this.cfFine = cfFine;
	}
	public String getCfNONRichiedente() {
		return cfNONRichiedente;
	}
	public void setCfNONRichiedente(String cfNONRichiedente) {
		this.cfNONRichiedente = cfNONRichiedente;
	} 
	
	public String getCfRichiedenteCdr() {
		return cfRichiedenteCdr;
	}
	public void setCfRichiedenteCdr(String cfRichiedenteCdr) {
		this.cfRichiedenteCdr = cfRichiedenteCdr;
	}
	public String getCdrArchiviazione() {
		return cdrArchiviazione;
	}
	public void setCdrArchiviazione(String cdrArchiviazione) {
		this.cdrArchiviazione = cdrArchiviazione;
	}
	public String getCfArchiviazione() {
		return cfArchiviazione;
	}
	public void setCfArchiviazione(String cfArchiviazione) {
		this.cfArchiviazione = cfArchiviazione;
	}
	public String getCdrAutorizzatore() {
		return cdrAutorizzatore;
	}
	public String getOperazione() {
		return operazione;
	}
	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}
	public boolean isInserimento() {
		return inserimento;
	}
	public void setInserimento(boolean inserimento) {
		this.inserimento = inserimento;
	}
	
	
	
}
