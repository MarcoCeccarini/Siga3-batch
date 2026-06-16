package it.finanze.siga.bean;

import it.finanze.siga.util.bean.UtenteBean;

import java.io.Serializable;
import java.util.Date;

public class StrutturaIterBean implements Serializable, Cloneable{
	
	private static final long serialVersionUID = 8065715441456797781L;
	
	private int id;
	private int idetificativoRecord;
	private String iterStandard;
	private String tipoUfficioRich;
	private String tipoUfficioRichDesc;
	
	private String tipoCdrAutorizIliv;
	private String tipoCdrAutorizIlivDesc;
	private String CdrAutorizIliv;
	private String CdrAutorizIlivDesc;
	private UtenteBean autorizzatoreI;
	
	private String previstoAutII;
	private String tipoCdrAutorizIIliv;
	private String tipoCdrAutorizIIlivDesc;
	private String CdrAutorizIIliv;
	private String CdrAutorizIIlivDesc;
	private UtenteBean autorizzatoreII;
	
	private String gestoriStruttRich;
	private String gestoriStruttAutI;
	private String gestoriStruttAutII;
	private String gruppoGestori;
	private String cdrGestori;
	private String cdrGestoriDesc;
	
	private Date dataInizioVal;
	private Date dataFineVal;
	private Integer idAuditInizio;
	private Integer idAuditfine;
	private String cfInizio;
	private String cfFine;
	
	private String ufficioRicElaborato;
	private String autILivElaborato;
	private String autIILivElaborato;
	private String gestoreRicElaborato;
	
	public StrutturaIterBean clone() throws CloneNotSupportedException {
        return (StrutturaIterBean) super.clone();
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIterStandard() {
		return iterStandard;
	}
	public void setIterStandard(String iterStandard) {
		this.iterStandard = iterStandard;
	}
	public String getTipoUfficioRich() {
		return tipoUfficioRich;
	}
	public void setTipoUfficioRich(String tipoUfficioRich) {
		this.tipoUfficioRich = tipoUfficioRich;
	}
	public String getTipoUfficioRichDesc() {
		return tipoUfficioRichDesc;
	}
	public void setTipoUfficioRichDesc(String tipoUfficioRichDesc) {
		this.tipoUfficioRichDesc = tipoUfficioRichDesc;
	}
	public String getTipoCdrAutorizIliv() {
		return tipoCdrAutorizIliv;
	}
	public void setTipoCdrAutorizIliv(String tipoCdrAutorizIliv) {
		this.tipoCdrAutorizIliv = tipoCdrAutorizIliv;
	}
	public String getTipoCdrAutorizIlivDesc() {
		return tipoCdrAutorizIlivDesc;
	}
	public void setTipoCdrAutorizIlivDesc(String tipoCdrAutorizIlivDesc) {
		this.tipoCdrAutorizIlivDesc = tipoCdrAutorizIlivDesc;
	}
	public String getCdrAutorizIliv() {
		return CdrAutorizIliv;
	}
	public void setCdrAutorizIliv(String cdrAutorizIliv) {
		CdrAutorizIliv = cdrAutorizIliv;
	}
	public String getCdrAutorizIlivDesc() {
		return CdrAutorizIlivDesc;
	}
	public void setCdrAutorizIlivDesc(String cdrAutorizIlivDesc) {
		CdrAutorizIlivDesc = cdrAutorizIlivDesc;
	}
	public UtenteBean getAutorizzatoreI() {
		return autorizzatoreI;
	}
	public void setAutorizzatoreI(UtenteBean autorizzatoreI) {
		this.autorizzatoreI = autorizzatoreI;
	}
	public String getPrevistoAutII() {
		return previstoAutII;
	}
	public void setPrevistoAutII(String previstoAutII) {
		this.previstoAutII = previstoAutII;
	}
	public String getTipoCdrAutorizIIliv() {
		return tipoCdrAutorizIIliv;
	}
	public void setTipoCdrAutorizIIliv(String tipoCdrAutorizIIliv) {
		this.tipoCdrAutorizIIliv = tipoCdrAutorizIIliv;
	}
	public String getTipoCdrAutorizIIlivDesc() {
		return tipoCdrAutorizIIlivDesc;
	}
	public void setTipoCdrAutorizIIlivDesc(String tipoCdrAutorizIIlivDesc) {
		this.tipoCdrAutorizIIlivDesc = tipoCdrAutorizIIlivDesc;
	}
	public String getCdrAutorizIIliv() {
		return CdrAutorizIIliv;
	}
	public void setCdrAutorizIIliv(String cdrAutorizIIliv) {
		CdrAutorizIIliv = cdrAutorizIIliv;
	}
	public String getCdrAutorizIIlivDesc() {
		return CdrAutorizIIlivDesc;
	}
	public void setCdrAutorizIIlivDesc(String cdrAutorizIIlivDesc) {
		CdrAutorizIIlivDesc = cdrAutorizIIlivDesc;
	}
	public UtenteBean getAutorizzatoreII() {
		return autorizzatoreII;
	}
	public void setAutorizzatoreII(UtenteBean autorizzatoreII) {
		this.autorizzatoreII = autorizzatoreII;
	}
	public String getGestoriStruttRich() {
		return gestoriStruttRich;
	}
	public void setGestoriStruttRich(String gestoriStruttRich) {
		this.gestoriStruttRich = gestoriStruttRich;
	}
	public String getGestoriStruttAutI() {
		return gestoriStruttAutI;
	}
	public void setGestoriStruttAutI(String gestoriStruttAutI) {
		this.gestoriStruttAutI = gestoriStruttAutI;
	}
	public String getGestoriStruttAutII() {
		return gestoriStruttAutII;
	}
	public void setGestoriStruttAutII(String gestoriStruttAutII) {
		this.gestoriStruttAutII = gestoriStruttAutII;
	}
	public String getGruppoGestori() {
		return gruppoGestori;
	}
	public void setGruppoGestori(String gruppoGestori) {
		this.gruppoGestori = gruppoGestori;
	}
	public String getCdrGestori() {
		return cdrGestori;
	}
	public void setCdrGestori(String cdrGestori) {
		this.cdrGestori = cdrGestori;
	}
	public String getCdrGestoriDesc() {
		return cdrGestoriDesc;
	}
	public void setCdrGestoriDesc(String cdrGestoriDesc) {
		this.cdrGestoriDesc = cdrGestoriDesc;
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
	public Integer getIdAuditInizio() {
		return idAuditInizio;
	}
	public void setIdAuditInizio(Integer idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}
	public Integer getIdAuditfine() {
		return idAuditfine;
	}
	public void setIdAuditfine(Integer idAuditfine) {
		this.idAuditfine = idAuditfine;
	}
	public String getCfInizio() {
		return cfInizio;
	}
	public void setCfInizio(String cfInizio) {
		this.cfInizio = cfInizio;
	}
	public String getCfFine() {
		return cfFine;
	}
	public void setCfFine(String cfFine) {
		this.cfFine = cfFine;
	}
	
	
	
	public int getIdetificativoRecord() {
		return idetificativoRecord;
	}
	public void setIdetificativoRecord(int idetificativoRecord) {
		this.idetificativoRecord = idetificativoRecord;
	}
	public String getUfficioRicElaborato() {
		if(tipoUfficioRich == null || tipoUfficioRich.equals(""))
			return "Tutte le tipologie non specificate";
		else
			return tipoUfficioRichDesc;
	}

	public String getAutILivElaborato() {
		String label = null;
		if(iterStandard != null && iterStandard.equalsIgnoreCase("SI"))
			label = "Iter standard";
		else{
			if(tipoCdrAutorizIlivDesc != null)
				label = tipoCdrAutorizIlivDesc;
			else if(CdrAutorizIlivDesc != null)
				label = CdrAutorizIlivDesc;
			else if(autorizzatoreI != null && autorizzatoreI.getCodFiscaleUtente() != null)
				label = autorizzatoreI.getCognome() + " " + autorizzatoreI.getNome();
		}
		return label;
	}

	public String getAutIILivElaborato() {
		String label = null;
		if(previstoAutII == null || !previstoAutII.equalsIgnoreCase("SI"))
			label = "Non previsto";
		else{
			if(tipoCdrAutorizIIlivDesc != null)
				label = tipoCdrAutorizIIlivDesc;
			else if(CdrAutorizIIlivDesc != null)
				label = CdrAutorizIIlivDesc;
			else if(autorizzatoreII != null && autorizzatoreII.getCodFiscaleUtente() != null)
				label = autorizzatoreII.getCognome() + " " + autorizzatoreII.getNome();
		}
		return label;
	}

	public String getGestoreRicElaborato() {
		String label = null;
		
		if(gestoriStruttRich != null)
			label = "Struttura richiedente";
		else if(gestoriStruttAutI != null)
			label = "Struttura autorizzatore I liv.";
		else if(gestoriStruttAutII != null)
			label = "Struttura autorizzatore II liv.";
		else if(gruppoGestori != null)
			label = "Gruppo gestori";
		else if(cdrGestoriDesc != null)
			label = cdrGestoriDesc;
		
		return label;
	}
	
	public int getTipoSceltaAutILiv() {
		int tipoScelta = 0;
		if(iterStandard != null && iterStandard.equalsIgnoreCase("SI"))
			tipoScelta = 4;
		else if(tipoCdrAutorizIliv != null)
			tipoScelta = 1;
		else if(CdrAutorizIliv != null)
			tipoScelta = 2;
		else if(autorizzatoreI != null && autorizzatoreI.getCodFiscaleUtente() != null)
			tipoScelta = 3;
		
		return tipoScelta;
	}

	public int getTipoSceltaAutIILiv() {
		int tipoScelta = 0;
		if(previstoAutII != null && previstoAutII.equalsIgnoreCase("SI")){
			if(tipoCdrAutorizIIliv != null)
				tipoScelta = 1;
			else if(CdrAutorizIIliv != null)
				tipoScelta = 2;
			else if(autorizzatoreII != null && autorizzatoreII.getCodFiscaleUtente() != null)
				tipoScelta = 3;
		}
	return tipoScelta;
	}

	public int getTipoSceltaGestore() {
		int tipoScelta = 0;
		
		if(gestoriStruttRich != null)
			tipoScelta = 1;
		else if(gestoriStruttAutI != null)
			tipoScelta = 3;
		else if(gestoriStruttAutII != null)
			tipoScelta = 4;
		else if(gruppoGestori != null)
			tipoScelta = 5;
		else if(cdrGestoriDesc != null)
			tipoScelta = 2;
		
		return tipoScelta;
	}
}
