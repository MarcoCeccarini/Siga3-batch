package it.finanze.siga.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ApplicazioniEntity implements Serializable {

	private static final long serialVersionUID = 625294272138276807L;
	
	public String codiceApplicazione,codiceAmbito,sincornizzatoHR,descrizione; 
	public String descrizioneRG;
	private String codiceRG;
	private String tipoProfilo;
	private String gestoreAmmRegionale; 	
	private String gestoreAmmLocale;	
	private String gestoreAmmCentrAppl;	
	private String descRuoli;
	private int numeroProfiliPerRG;
	private int numeroOperatoriPerRg;
	private Timestamp dataElaborazione;
	
	

	public Timestamp getDataElaborazione() {
		return dataElaborazione;
	}

	public void setDataElaborazione(Timestamp dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}

	public int getNumeroOperatoriPerRg() {
		return numeroOperatoriPerRg;
	}

	public void setNumeroOperatoriPerRg(int numeroOperatoriPerRg) {
		this.numeroOperatoriPerRg = numeroOperatoriPerRg;
	}

	public int getNumeroProfiliPerRG() {
		return numeroProfiliPerRG;
	}

	public void setNumeroProfiliPerRG(int numeroProfiliPerRG) {
		this.numeroProfiliPerRG = numeroProfiliPerRG;
	}

	public String getGestoreAmmRegionale() {
		return gestoreAmmRegionale;
	}

	public void setGestoreAmmRegionale(String gestoreAmmRegionale) {
		this.gestoreAmmRegionale = gestoreAmmRegionale;
	}

	public String getGestoreAmmLocale() {
		return gestoreAmmLocale;
	}

	public void setGestoreAmmLocale(String gestoreAmmLocale) {
		this.gestoreAmmLocale = gestoreAmmLocale;
	}

	public String getGestoreAmmCentrAppl() {
		return gestoreAmmCentrAppl;
	}

	public void setGestoreAmmCentrAppl(String gestoreAmmCentrAppl) {
		this.gestoreAmmCentrAppl = gestoreAmmCentrAppl;
	}

	public Date dataInizioVal,dataFineVal;

	
	
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	public String getCodiceAmbito() {
		return codiceAmbito;
	}

	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}

	public String getSincornizzatoHR() {
		return sincornizzatoHR;
	}

	public void setSincornizzatoHR(String sincornizzatoHR) {
		this.sincornizzatoHR = sincornizzatoHR;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
	
	public String getDescrizioneRG() {
		return descrizioneRG;
	}

	public void setDescrizioneRG(String descrizioneRG) {
		this.descrizioneRG = descrizioneRG;
	}

	public String getCodiceRG() {
		return codiceRG;
	}

	public void setCodiceRG(String codiceRG) {
		this.codiceRG = codiceRG;
	}

	public String getTipoProfilo() {
		return tipoProfilo;
	}

	public void setTipoProfilo(String tipoProfilo) {
		this.tipoProfilo = tipoProfilo;
	}

	public String getDescRuoli() {
		return descRuoli;
	}

	public void setDescRuoli(String descRuoli) {
		this.descRuoli = descRuoli;
	}
}