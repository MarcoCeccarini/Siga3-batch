package it.finanze.siga.bean;

import it.finanze.siga.util.Utils;

import java.io.Serializable;
import java.util.Date;

public class InterrProfiliOperatoreBean extends BaseBean implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 381029850077911931L;
	
	private String applicazione; 
	private String raggruppamento_profili;
	private String profilo;
	private String descrizione_cdr;
	private String descrizione_ufficio; 
	private String data_scadenzaStr;
	private Date data_scadenza;
	private String tipo_abilitazione;
	private String cdrUffValiditaProf;
	//campi per il raggruppamento
	private String codUfficio;
	private String codCdr;
	private String codRuolo;
	private String cdrUffValiditaProfVisibile;
	private String codiceProfilo;
	
	
	
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getApplicazione() {
		return applicazione;
	}
	public void setApplicazione(String applicazione) {
		this.applicazione = applicazione;
	}
	public String getRaggruppamento_profili() {
		return raggruppamento_profili;
	}
	public void setRaggruppamento_profili(String raggruppamento_profili) {
		this.raggruppamento_profili = raggruppamento_profili;
	}
	public String getProfilo() {
		return profilo;
	}
	public void setProfilo(String profilo) {
		this.profilo = profilo;
	}
	public String getDescrizione_cdr() {
		return descrizione_cdr;
	}
	public void setDescrizione_cdr(String descrizione_cdr) {
		this.descrizione_cdr = descrizione_cdr;
	}
	public String getDescrizione_ufficio() {
		return descrizione_ufficio;
	}
	public void setDescrizione_ufficio(String descrizione_ufficio) {
		this.descrizione_ufficio = descrizione_ufficio;
	}

	public String getTipo_abilitazione() {
		return tipo_abilitazione;
	}
	public void setTipo_abilitazione(String tipo_abilitazione) {
		this.tipo_abilitazione = tipo_abilitazione;
	}
	public String getCdrUffValiditaProf() {
		return cdrUffValiditaProf;
	}
	public void setCdrUffValiditaProf(String cdrUffValiditaProf) {
		this.cdrUffValiditaProf = cdrUffValiditaProf;
	}
	public String getData_scadenzaStr() {
		if(data_scadenza!=null)
			return Utils.dateStringFromDate(data_scadenza);
		else
			return "";
	}

	public Date getData_scadenza() {
		return data_scadenza;
	}
	public void setData_scadenza(Date data_scadenza) {
		this.data_scadenza = data_scadenza;
	}
	public String getCodUfficio() {
		return codUfficio;
	}
	public void setCodUfficio(String codUfficio) {
		this.codUfficio = codUfficio;
	}
	public String getCodCdr() {
		return codCdr;
	}
	public void setCodCdr(String codCdr) {
		this.codCdr = codCdr;
	}
	public String getCodRuolo() {
		return codRuolo;
	}
	public void setCodRuolo(String codRuolo) {
		this.codRuolo = codRuolo;
	}
	public String getCdrUffValiditaProfVisibile() {
		return cdrUffValiditaProfVisibile;
	}
	public void setCdrUffValiditaProfVisibile(String cdrUffValiditaProfVisibile) {
		this.cdrUffValiditaProfVisibile = cdrUffValiditaProfVisibile;
	}


}
