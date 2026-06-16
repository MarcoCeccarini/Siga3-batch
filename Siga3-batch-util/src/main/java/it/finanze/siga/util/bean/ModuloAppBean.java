package it.finanze.siga.util.bean;

import java.io.Serializable;

import it.finanze.siga.bean.BaseBean;

/**
 * Modulo trasversale dell'app che serve a tener traccia dei campi principali
 * che vengono riusati spesso in vari punti dell'app 
 * 
 * @author FA
 */
public class ModuloAppBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 666025519816762565L;
	
	/** CAMPI X UTENTE LOGGATO **/
	private String codFiscaleUtente;
	private String deleganteRichUtente; // delegante richiedente
	private String cdrDeleganteRichUtente; // cdr di dove il delegante e' richiedente
	/**
	 * uso questa dicitura per non confonderlo col codiceCDR dell'operatore
	 * che viene identificato proprio con codiceCDR, quando sono passati insieme.
	 */
	private String codiceCDRUser;
	
	private String codUfficioUser;
	
	
	/** CAMPI X OPERATORE SELEZIONATO **/
	private String cdr;
	private String cf;
	private String codiceUfficio;
	private String cdrAssegnazione;
	/**
	 * per gli 8 casi del CDR
	 */
	private String flag;
	
	/**
	 * per tenere memoria del codice ambito
	 */
	private String codiceAmbitoApplicativo;

	/**
	 * per tenere traccia della scelta abilitazioni: ORDINARIO o SPECIALE o ENTRAMBI
	 */
	private String abilitazioni;
	
	private String sincronizzataCAU;
	
	
	/*********** GETTERS AND SETTERS ************/
	public String getAbilitazioni() {
		return abilitazioni;
	}

	public void setAbilitazioni(String abilitazioni) {
		this.abilitazioni = abilitazioni;
	}
	
	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	
	public String getSincronizzataCAU() {
		return sincronizzataCAU;
	}

	public void setSincronizzataCAU(String sincronizzataCAU) {
		this.sincronizzataCAU = sincronizzataCAU;
	}

	/** GETTERS AND SETTERS **/
	public String getCodFiscaleUtente() {
		return codFiscaleUtente;
	}

	public void setCodFiscaleUtente(String codFiscaleUtente) {
		this.codFiscaleUtente = codFiscaleUtente;
	}

	public String getCodiceCDRUser() {
		return codiceCDRUser;
	}

	public void setCodiceCDRUser(String codiceCDRUser) {
		this.codiceCDRUser = codiceCDRUser;
	}

	public String getCodUfficioUser() {
		return codUfficioUser;
	}

	public void setCodUfficioUser(String codUfficioUser) {
		this.codUfficioUser = codUfficioUser;
	}

	public String getCdr() {
		return cdr;
	}

	public void setCdr(String cdr) {
		this.cdr = cdr;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCodiceAmbitoApplicativo() {
		return codiceAmbitoApplicativo;
	}

	public void setCodiceAmbitoApplicativo(String codiceAmbitoApplicativo) {
		this.codiceAmbitoApplicativo = codiceAmbitoApplicativo;
	}

	public String getDeleganteRichUtente() {
		return deleganteRichUtente;
	}

	public void setDeleganteRichUtente(String deleganteRichUtente) {
		this.deleganteRichUtente = deleganteRichUtente;
	}

	public String getCdrDeleganteRichUtente() {
		return cdrDeleganteRichUtente;
	}

	public void setCdrDeleganteRichUtente(String cdrDeleganteRichUtente) {
		this.cdrDeleganteRichUtente = cdrDeleganteRichUtente;
	}

	public String getCdrAssegnazione() {
		return cdrAssegnazione;
	}

	public void setCdrAssegnazione(String cdrAssegnazione) {
		this.cdrAssegnazione = cdrAssegnazione;
	}
	
	

}
