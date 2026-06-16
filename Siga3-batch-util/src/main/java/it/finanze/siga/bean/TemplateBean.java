package it.finanze.siga.bean;

import it.finanze.siga.util.Utils;

import java.io.Serializable;
import java.util.Date;

public class TemplateBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6334373437273096904L;
	
	private int templateID;
	private String descrizione;
	private String CDRValidita;
	private String ambitoApplicativo;
	private Date dataInizioVal;
	private Date dataFineVal;
	private Date dataUltimaModifica;
	private String codiceFiscale;
	private String dataFineValConvert;
	private String dataInizioValString;
	private String dataUltimaModificaString;
	
	
	public String getDataInizioValString() {
		return dataInizioValString;
	}

	public void setDataInizioValString(String dataInizioValString) {
		this.dataInizioValString = dataInizioValString;
	}

	
	public String getDataUltimaModificaString() {
		return dataUltimaModificaString;
	}

	public void setDataUltimaModificaString(String dataUltimaModificaString) {
		this.dataUltimaModificaString = dataUltimaModificaString;
	}




	/**
	 * per UI
	 */
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTemplateID() {
		return templateID;
	}

	public void setTemplateID(int templateID) {
		this.templateID = templateID;
	}

	public String getCDRValidita() {
		return CDRValidita;
	}

	public void setCDRValidita(String cDRValidita) {
		CDRValidita = cDRValidita;
	}

	public String getAmbitoApplicativo() {
		return ambitoApplicativo;
	}

	public void setAmbitoApplicativo(String ambitoApplicativo) {
		this.ambitoApplicativo = ambitoApplicativo;
	}

	public String getDataInizioVal() {
		return Utils.dateStringFromDate(dataInizioVal);
	}

	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}

	public String getDataFineVal() {
		return Utils.dateStringFromDate(dataFineVal);
	}

	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDataFineValConvert() {
		if(dataFineVal!=null){
			return Utils.dateStringFromDate(dataFineVal);
		}
		return dataFineValConvert;
	}

	public void setDataFineValConvert(String dataFineValConvert) {
		this.dataFineValConvert = dataFineValConvert;
	}

	public String getDataUltimaModifica() {
		return Utils.dateStringFromDate(dataUltimaModifica);	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}
	
	
}
