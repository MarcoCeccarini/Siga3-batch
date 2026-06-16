package it.finanze.siga.util;

import java.util.Properties;

import it.finanze.siga.utility.properties.PropertiesReader;

public class SigaCache {
	
	private static SigaCache instance = new SigaCache();
	
	private SigaCache(){};
	
	public static SigaCache getInstance(){
		return instance;
	}

	private static boolean LOCAL = true;

	private static int RECORD_PER_PAGINA = 20;
	private static String MAX_VISIBLE_PAGE = "1";
	private static String MAX_RIEPILOGO_LISTE = "5";
	private static String MAX_RIEPILOGO_NO_PAGINATE = "500";
	private static String BATCH_DELEGHE_LOG_FILE = null;


	private static String MOD = null;
	private static String urlWSAnagrafica = null;

	private static String AGENZIA = null;
	private static String CT_AGENZIA = null;
	private static String CT_APPLICAZIONE = null;
	
	
	private static String CDR_UTENTE_LOGGATO = null;
	private static String CDR_DI_CUI_E_RESPONSABILE = null;
	private static String CDR_DI_CUI_E_RICHIEDENTE = null;
	private static String CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE = null;
	private static String CDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA = null;
	private static String CDR_DI_CUI_E_RICHIEDENTE_DELEGATO = null;
	private static String CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO = null;
	private static String CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO = null;
	
	static String SYS_AGENZIA = null;
	static String SYS_ADMIN = null;
	static String SYS_ADMIN_GROUP = null;
	static String SYS_ADMIN_GROUP_MIGRAZIONE= null;
	static String SYS_UFFICIO = null;
	static String SYS_PROFILE = null;
	
	private static String FLAG_CRUSCOTTO = null;
	
	private static String FLAG_AMM_CEN_APP = null;


	public static String getCDR_DI_CUI_E_RESPONSABILE() {
		return CDR_DI_CUI_E_RESPONSABILE;
	}
	public static void setCDR_DI_CUI_E_RESPONSABILE(String cDR_DI_CUI_E_RESPONSABILE) {
		CDR_DI_CUI_E_RESPONSABILE = cDR_DI_CUI_E_RESPONSABILE;
	}
	public static String getCDR_DI_CUI_E_RICHIEDENTE() {
		return CDR_DI_CUI_E_RICHIEDENTE;
	}
	public static void setCDR_DI_CUI_E_RICHIEDENTE(String cDR_DI_CUI_E_RICHIEDENTE) {
		CDR_DI_CUI_E_RICHIEDENTE = cDR_DI_CUI_E_RICHIEDENTE;
	}
	public static String getCDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE() {
		return CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE;
	}
	public static void setCDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE(
			String cDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE) {
		CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE = cDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE;
	}
	public static String getCDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA() {
		return CDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA;
	}
	public static void setCDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA(
			String cDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA) {
		CDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA = cDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA;
	}
	public static String getCDR_DI_CUI_E_RICHIEDENTE_DELEGATO() {
		return CDR_DI_CUI_E_RICHIEDENTE_DELEGATO;
	}
	public static void setCDR_DI_CUI_E_RICHIEDENTE_DELEGATO(
			String cDR_DI_CUI_E_RICHIEDENTE_DELEGATO) {
		CDR_DI_CUI_E_RICHIEDENTE_DELEGATO = cDR_DI_CUI_E_RICHIEDENTE_DELEGATO;
	}
	public static String getCDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO() {
		return CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO;
	}
	public static void setCDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO(
			String cDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO) {
		CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO = cDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO;
	}
	public static String getCDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO() {
		return CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO;
	}
	public static void setCDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO(
			String cDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO) {
		CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO = cDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO;
	}
	public static String getCDR_UTENTE_LOGGATO() {
		return CDR_UTENTE_LOGGATO;
	}
	public static void setCDR_UTENTE_LOGGATO(String string) {
		CDR_UTENTE_LOGGATO = string;
	}
	public static boolean isLOCAL() {
		return LOCAL;
	}
	public static void setLOCAL(boolean local) {
		LOCAL = local;
	}

	public static int getRECORD_PER_PAGINA() {
		return RECORD_PER_PAGINA;
	}

	public static void setRECORD_PER_PAGINA(int record_per_pagina) {
		RECORD_PER_PAGINA = record_per_pagina;
	}

	public static String getMAX_VISIBLE_PAGE() {
		return MAX_VISIBLE_PAGE;
	}

	public static void setMAX_VISIBLE_PAGE(String max_visible_page) {
		MAX_VISIBLE_PAGE = max_visible_page;
	}

	public static void setMAX_RIEPILOGO_LISTE(String mAX_RIEPILOGO_LISTE) {
		MAX_RIEPILOGO_LISTE = mAX_RIEPILOGO_LISTE;
	}

	public static String getMAX_RIEPILOGO_LISTE() {
		return MAX_RIEPILOGO_LISTE;
	}

	public static String getMAX_RIEPILOGO_NO_PAGINATE() {
		return MAX_RIEPILOGO_NO_PAGINATE;
	}

	public static void setMAX_RIEPILOGO_NO_PAGINATE(String max_riepilogo_no_paginate) {
		MAX_RIEPILOGO_NO_PAGINATE = max_riepilogo_no_paginate;
	}

	public static String getMOD() {
		return MOD;
	}

	public static void setMOD(String mod) {
		MOD = mod;
	}
	public static void setUrlWSAnagrafica(String urlWSAnagrafica) {
		SigaCache.urlWSAnagrafica = urlWSAnagrafica;
	}
	public static String getUrlWSAnagrafica() {
		return urlWSAnagrafica;
	}
	public static void setAGENZIA(String aGENZIA) {
		AGENZIA = aGENZIA;
	}
	public static String getAGENZIA() {
		return AGENZIA;
	}
	public static void setCT_AGENZIA(String cT_AGENZIA) {
		CT_AGENZIA = cT_AGENZIA;
	}
	public static String getCT_AGENZIA() {
		return CT_AGENZIA;
	}
	public static void setCT_APPLICAZIONE(String cT_APPLICAZIONE) {
		CT_APPLICAZIONE = cT_APPLICAZIONE;
	}
	public static String getCT_APPLICAZIONE() {
		return CT_APPLICAZIONE;
	}

	public static String getBATCH_DELEGHE_LOG_FILE() {
		return BATCH_DELEGHE_LOG_FILE;
	}

	public static void setBATCH_DELEGHE_LOG_FILE(String bATCH_DELEGHE_LOG_FILE) {
		BATCH_DELEGHE_LOG_FILE = bATCH_DELEGHE_LOG_FILE;
	}

	public static String getSYS_AGENZIA() {
		return SYS_AGENZIA;
	}

	public static void setSYS_AGENZIA(String sYS_AGENZIA) {
		SYS_AGENZIA = sYS_AGENZIA;
	}

	public static String getSYS_ADMIN() {
		return SYS_ADMIN;
	}

	public static void setSYS_ADMIN(String sYS_ADMIN) {
		SYS_ADMIN = sYS_ADMIN;
	}

	public static String getSYS_ADMIN_GROUP() {
		return SYS_ADMIN_GROUP;
	}

	public static void setSYS_ADMIN_GROUP(String sYS_ADMIN_GROUP) {
		SYS_ADMIN_GROUP = sYS_ADMIN_GROUP;
	}

	public static String getSYS_UFFICIO() {
		return SYS_UFFICIO;
	}

	public static void setSYS_UFFICIO(String sYS_UFFICIO) {
		SYS_UFFICIO = sYS_UFFICIO;
	}
	
	public static String getSYS_PROFILE() {
		return SYS_PROFILE;
	}
	
	public static void setSYS_PROFILE(String sYS_PROFILE) {
		SYS_PROFILE = sYS_PROFILE;
	}

	public static String getSYS_ADMIN_GROUP_MIGRAZIONE() {
		return SYS_ADMIN_GROUP_MIGRAZIONE;
	}

	public static void setSYS_ADMIN_GROUP_MIGRAZIONE(
			String sYS_ADMIN_GROUP_MIGRAZIONE) {
		SYS_ADMIN_GROUP_MIGRAZIONE = sYS_ADMIN_GROUP_MIGRAZIONE;
	}

	public static String getFLAG_CRUSCOTTO() {
		return FLAG_CRUSCOTTO;
	}

	public static void setFLAG_CRUSCOTTO(String fLAG_CRUSCOTTO) {
		FLAG_CRUSCOTTO = fLAG_CRUSCOTTO;
	}

	public static String getFLAG_AMM_CEN_APP() {
		return FLAG_AMM_CEN_APP;
	}

	public static void setFLAG_AMM_CEN_APP(String fLAG_AMM_CEN_APP) {
		FLAG_AMM_CEN_APP = fLAG_AMM_CEN_APP;
	}


	// TODO MCE STATIC INITIALIZER
	static 
	{
	PropertiesReader propertiesReader = new PropertiesReader(Constants.FILE_PROPERTY_PATH, Constants.FILE_PROPERTY_NAME);
	Properties fileProperties = propertiesReader.getProperties();
	
	SigaCache.setMOD(fileProperties.getProperty(Constants.MOD));
	SigaCache.setMAX_VISIBLE_PAGE(fileProperties.getProperty(Constants.MAX_VISIBLE_PAGE));
	SigaCache.setRECORD_PER_PAGINA(Integer.parseInt(fileProperties.getProperty(Constants.RECORD_PER_PAGINA)));
	SigaCache.setRECORD_PER_PAGINA(Integer.parseInt(fileProperties.getProperty(Constants.RECORD_PER_PAGINA)));
	
	SigaCache.setBATCH_DELEGHE_LOG_FILE(fileProperties.getProperty(Constants.BATCH_DELEGHE_LOG_FILE_PATH));
	
	SigaCache.setSYS_AGENZIA(fileProperties.getProperty(Constants.SYS_AGENZIA, "EE"));
	SigaCache.setSYS_ADMIN(fileProperties.getProperty(Constants.SYS_ADMIN, "SISTEMASIGA3"));
	SigaCache.setSYS_ADMIN_GROUP(fileProperties.getProperty(Constants.SYS_ADMIN_GROUP));
	SigaCache.setSYS_ADMIN_GROUP_MIGRAZIONE(fileProperties.getProperty(Constants.SYS_ADMIN_GROUP_MIGRAZIONE));
	SigaCache.setSYS_UFFICIO(fileProperties.getProperty(Constants.SYS_UFFICIO));
	SigaCache.setSYS_PROFILE(fileProperties.getProperty(Constants.SYS_PROFILE));
	
//	SigaCache.setCT_AGENZIA(fileProperties.getProperty("CT_AGENZIA"));
//	SigaCache.setCT_APPLICAZIONE(fileProperties.getProperty("CT_APPLICAZIONE"));
	SigaCache.setUrlWSAnagrafica(fileProperties.getProperty("urlWsVerificaAnagrafica"));
			
	SigaCache.setFLAG_CRUSCOTTO(fileProperties.getProperty(Constants.FLAG_CRUSCOTTO));
	
	SigaCache.setFLAG_AMM_CEN_APP(fileProperties.getProperty(Constants.FLAG_AMM_CEN_APP));
	
	SigaCache.setCDR_UTENTE_LOGGATO(fileProperties.getProperty(Constants.CDR_UTENTE_LOGGATO));
	SigaCache.setCDR_DI_CUI_E_RESPONSABILE(fileProperties.getProperty(Constants.CDR_DI_CUI_E_RESPONSABILE));
	SigaCache.setCDR_DI_CUI_E_RICHIEDENTE(fileProperties.getProperty(Constants.CDR_DI_CUI_E_RICHIEDENTE));
	SigaCache.setCDR_DI_CUI_E_RICHIEDENTE_DELEGATO(fileProperties.getProperty(Constants.CDR_DI_CUI_E_RICHIEDENTE_DELEGATO));
	SigaCache.setCDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE(fileProperties.getProperty(Constants.CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE));
	SigaCache.setCDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO(fileProperties.getProperty(Constants.CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO));
	SigaCache.setCDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO(fileProperties.getProperty(Constants.CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO));
	SigaCache.setCDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA(fileProperties.getProperty(Constants.CDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA));
}

}
