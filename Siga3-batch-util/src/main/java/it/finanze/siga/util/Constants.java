package it.finanze.siga.util;

import org.apache.struts.util.LabelValueBean;


public class Constants {
	
	public static final String RICHIESTA_PROFILAZIONE = "P";
	public static final String RICHIESTA_VISIBILITA = "V";
	public static final String RICHIESTA_REVOCA_VISIBILITA = "R";
	public static final String RICHIESTA_CAMBIO_UFFICIO = "C";
	
	
	public static final String SQL_MAP_CONFIG="it/finanze/siga/xml/SqlMapConfig.xml"; 
	//public static final String SQL_MAP_CONFIG_CAU="it/finanze/siga/xml/SqlMapConfigCAU.xml";
	/**
	 * ambiente app
	 */
	public static ENV ENVIRONMENT = ENV.DEV;
	
	/**
	 * casi di profilazione di default è 0
	 */
	public final static int profilazione_standard = 0;
	public final static int profilazione_carica_da_template = 1;
	public final static int profilazione_carica_da_operatore = 2;
	public final static int profilazione_solo_visualizzazione = 3;
	public final static int profilazione_carica_da_template_con_icone_rimossi_e_aggiunti = 4;
	public final static int profilazione_carica_da_operatore_con_icone_rimossi_e_aggiunti = 5;
	
	
	/***** SVILUPPO *****/
	public final static String TO_IMPLEMENT = "TO_IMPLEMENT";
	public final static String CASO_IMPREVISTO = "CASO_IMPREVISTO";
	public final static String NON_DEVO_MAI_STARE_QUI = "NON_DEVO_MAI_STARE_QUI";
	public final static String GENERA_XLS = "Generando XLS";

	
	public final static int modalita_sede = 0;
	public final static int modalita_sviluppo = 1;
	public final static int modalita_test = 2;
	public final static int modalita_produzione = 3;
	public final static int modalita_validazione = 4;
	
	/** per Explicit Entitlement **/
	public final static String CODUFFICIO = "CODUFFICIO";
	public final static String CDR = "CDR";
	
	
	/** PROFILI **/
	/**
	 * questa è la stringa che viene inserita nel DB
	 */
	public final static String ABILITA = "Abilitazione";
	/**
	 * questa è la stringa che viene inserita nel DB
	 */
	public final static String DISABILITA = "Disabilitazione";
	public final static int NUMERO_CARATTERI = 100;
	public final static String O = "O";
	public final static String S = "S";
	public final static String Ordinario = "Ordinaria";
	public final static String Ordinarie = "Ordinarie";
	public final static String Speciale = "Speciale";
	public final static String Speciali = "Speciali";
	public final static String ExtraUfficio = "Extra ufficio";
	public final static String Ordinarie_et_Speciali = "Ordinarie & Extra ufficio";
	public final static String CODICE_ROLE_GROUP_NO_ROLE_GROUP = "CODICE_ROLE_GROUP_NO_ROLE_GROUP"; 
	
	
	/** ICONE **/
	public final static String ICON_BLANK_POINT_PATH = "/SIGAWeb/images/blank_point.png";
	public final static String ICON_CROSS_RED_PATH = "/SIGAWeb/images/cross-red-2.png";
	public final static String ICON_CHECK_GREEN_PATH = "/SIGAWeb/images/check-green.png";
	
	/** OPERATORI **/
	public final static String INTERNO = "Interno";
	public final static String ESTERNO = "Esterno";
	public final static String VISIBILITA = "Visibilita'";
	public final static String TIPO_UTENTE_INTERNO = "I";
	public final static String TIPO_UTENTE_ESTERNO = "E";
	
	/** DELEGHE **/
	public static final String A1 = "Autorizzazione I liv.";
	public static final String A2 = "Autorizzazione II liv.";
	public static final String RI = "Richiedente";
	
	/** ABILITAZIONE OPER. - RUOLI RICHIESTA **/
	public static final String AMMINISTRATORE_CENTRALE_R = "AMMINISTRATORE CENTRALE";
	public static final String AMMINISTRATORE_CENTRALE_APPLICATIVO_R = "AMMINISTRATORE CENTRALE APPLICATIVO";
	public static final String AUTORIZZATORE_II_R = "AUTORIZZATORE II";
	public static final String RICHIEDENTE_R = "RICHIEDENTE";
	
	/** FILE DI PROPERTY **/
	public final static String FILE_PROPERTY_PATH = "/prod/installedApps/SIGA";
	public final static String FILE_PROPERTY_NAME = "ini.properties";
	
	public final static String MOD = "MOD";
	public final static String MAX_VISIBLE_PAGE = "MAX_VISIBLE_PAGE";
	public final static String RECORD_PER_PAGINA = "RECORD_PER_PAGINA";
	
	/** CRUSCOTTO MONITORAGGIO **/
	public final static String FLAG_CRUSCOTTO = "FLAG_CRUSCOTTO";
	
	/** GESTIONE AMMINISTRATORI CENTRALI APPLICATIVI **/
	public final static String FLAG_AMM_CEN_APP = "FLAG_AMM_CEN_APP";
	
	public final static String CDR_UTENTE_LOGGATO = "CDR_UTENTE_LOGGATO";
	public final static String CDR_DI_CUI_E_RESPONSABILE = "CDR_DI_CUI_E_RESPONSABILE";
	public final static String CDR_DI_CUI_E_RICHIEDENTE = "CDR_DI_CUI_E_RICHIEDENTE";
	public final static String CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE = "CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE";
	public final static String CDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA = "CDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA";
	public final static String CDR_DI_CUI_E_RICHIEDENTE_DELEGATO = "CDR_DI_CUI_E_RICHIEDENTE_DELEGATO";
	public final static String CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO = "CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO";
	public final static String CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO = "CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO";
	
	public final static String SYS_AGENZIA = "SYS_AGENZIA";
	public final static String AGENZIA = "EE";

	public final static String SYS_ADMIN = "SYS_ADMIN";
	public final static String SYS_ADMIN_GROUP = "SYS_ADMIN_GROUP";
	public final static String SYS_ADMIN_GROUP_MIGRAZIONE = "SYS_ADMIN_GROUP_MIGRAZIONE";
	public final static String SYS_UFFICIO = "SYS_UFFICIO";
	public final static String SYS_PROFILE = "SYS_PROFILE";
	
	// Template excel per i caricamenti massivi
	public final static String FILE_TEMPLATE_PATH = "/prod/installedApps/SIGA/AppProperties/template/";
	
	
	/** STRINGHE **/
	public final static String RG_TITLE_FOR_NO_RG_PROFILES = "Profili non associati a Role Group";
	public final static String PROFILO_NON_RICHIEDIBILE_X_CDR = "Profilo non richiedibile per il CDR selezionato";
	public static final String AMBITO_NON_RICHIEDIBILE = "Ambito non richiedibile";
	
	public static final String UFFICIO = "UFFICIO";
	public static final String TIPOLOGIA_UFF_CDR = "TIPOLOGIA_UFF_CDR";
	public static final String SPECIFICO_UFF_CDR = "SPECIFICO_UFF_CDR";
	public static final String IS_EXPORT_BY_TIPOLOGIA = "IS_EXPORT_BY_TIPOLOGIA";
	public static final String AMMINISTRATORE_AUDITOR = "amministratore_auditor";
	public static final String AUDITOR_REGIONALE = "auditor_regionale";
	public static final String RESPONSABILE_CDR_RICHIEDENTE = "responsabile";
	public static final String AMMINISTRATORE_CENTRALE_APPLICATIVO = "amministratore_centrale_applicativo";
	public static final String RICHIEDENTE = "richiedente";
	/* Metodi per Costanti Di Utilita */
	
	public final static String SELEZIONA_NOMINATIVO = "Seleziona Nominativo";
	
	/* tipi di file */
	public final static String PDF_TYPE = "application/pdf";	
	
	public final static String PLAIN_TEXT_TYPE = "text/plain";	
	
	public final static String IMAGE_TIFF_TYPE = "image/tiff";	
	public final static String IMAGE_BMP_TYPE = "image/bmp";
	public final static String IMAGE_PNG_TYPE = "image/png";
	public final static String IMAGE_JPEG_TYPE = "image/jpeg";
	public final static String IMAGE_GIF_TYPE = "image/gif";
	
	public final static String OFFICE_DOC_TYPE = "application/msword";
	public final static String OFFICE_DOCX_TYPE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	public final static String OFFICE_XLS_TYPE = "application/vnd.ms-excel";
	public final static String OFFICE_XSLX_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public final static String OFFICE_PPT_TYPE = "application/vnd.ms-powerpoint";
	public final static String OFFICE_PPTX_TYPE = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
	public final static String P7M ="application/pkcs7-mime";
	public final static String P7MX ="application/x-pkcs7-mime";
	public final static String CSV_TYPE= "text/csv";
	
	public final static String MSG_TYPE = "application/octet-stream";
	public final static String TIPO_ALLEGATO_INSERIMENTO = "INS";
	public final static String TIPO_ALLEGATO_CANCELLAZIONE = "CANC";
	
	// BATCH
	public static final String BATCH_DELEGHE_LOG_FILE_PATH = "BATCH_DELEGHE_LOG_FILE_PATH";
	public static final String FLAG_DESTINATARI_AMM_LOCALI_DELEGANTE = "1";
	public static final String FLAG_DESTINATARI_AMM_LOCALI_DELEGATO = "2";
	public static final String FLAG_DESTINATARI_AMM_LOCALI_DELEGANTE_E_DELEGATO = "3";
	
	// costante che indica il codice dell'ambito applicativo di default (1 indica il CAU)
	public static final String CODICE_CAU = "1";
	
	//costanti per codice profilo
	public static final String PREX_CODICE_PROFILO = "ECA";
	public static final String SUFFIX_CODICE_PROFILO = "00000";
	public static final String PREX_DESCR_CDRUFFICI = "Stessi uffici ";
	public static final String PREX_DESCR_MULTICDR = "Multi CDR ";
	public static final String PREX_DESCR_MULTIUFFICIO = "Multi Ufficio ";
	public static final String EXPLICIT_ENT_CDR = "CDR";
	public static final String EXPLICIT_ENT_COD_UFFICIO = "CODUFFICIO";
	public static final String CHIESTO_DA_AC = "chiesto da Amministratore Centrale";
	
	
	//COSTANTI VARIE
	public static final String APPLICAZIONE_ATTIVA = "applicazioneAttiva";
	public static final String MENU_RICHIESTE_ATTIVO = "menuRichiesteAttivo";


	public static final String ALLINEAMENTO_RUOLI_ITER_ABILITAZIONI = "batchAllineamentoRuoliSiga3IterAbilitazioni";
	//COSTANTI GESTIONE BATCH ALLINEMAMENTO AMMINISTRATORI
	public static final String ALLINEAMENTO_AMMINISTRATORI = "batchAllineamentoAmministratoriSiga3";
	public static final String AGGIORNAMENTO_AMMINISTRATORI_I_ELAB = "batchAllineamentoAmministratoriAggIAttivo";
	public static final String AGGIORNAMENTO_AMMINISTRATORI_II_ELAB = "batchAllineamentoAmministratoriAggIIAttivo";
	
	//COSTANTI GESTIONE BATCH IMPORTAZIONE RESPONSABILITA ORACLE APPLICATION
	public static final String IMPORT_RESPONSABILITA_OA = "batchResponsabilitaOA";
	public static final String IMPORT_RESPONSABILITA_OA_CRITERIO = "batchResponsabilitaOACriterio";
	public static final String IMPORT_RESPONSABILITA_OA_TIPO_ELAB = "batchResponsabilitaOATipoElab";

	public static final String BATCH_GESTORI_DI_RETE = "batchGestoriDiRete";

//	CODICI_ATTIVITA
	public static final String ATT_CM = "CM_RICHIESTA";
	public static final String ATT_CM_VIS = "CM_VISIBILITA_RICHIESTA";
	public static final String ATT_AGG_VIS = "PROFILI_UTENTE_IN_VISIBILITA";
	public static final String ATT_ITER = "ITER";
	public static final String ATT_GESTORI = "GESTORI_OPERATORI";
	public static final String ATT_RICHIESTE = "REGISTRO_RICHIESTE";
	public static final String ATT_DELEGHE = "DELEGHE";
	public static final String ATT_AORA = "ASSOCIAZ_OPER_RICHI_AUTOR";
	public static final String ATT_RUOLI = "RICHIEDENTE_CDR";
	public static final String ATT_OP_ESTERNI = "OPER_ESTERNI_TIPO_UTENTI";
	public static final String ATT_TIP_OP_ESTERNI = "TIPOLOGIE_UTENTI";
	
//	motivi revoca
	
	public static final String SCADUTA = "SCAD";
	public static final String REVOCATA_AORA = "REVORA";
	public static final String REVOCA_BATCH = "QUA";
	public static final String REVOCATA = "REVC";
	
	public static LabelValueBean standardIO(String value){
		
		LabelValueBean ioVB = new LabelValueBean();
		ioVB.setLabel("Io");
		ioVB.setValue(value);
		
		return ioVB;
	}
	public static LabelValueBean standardTUTTI(String value){
		
		LabelValueBean tuttiVB = new LabelValueBean();
		tuttiVB.setLabel("Tutti");
		tuttiVB.setValue(value);
		
		return tuttiVB;
	}
	
	/**
	 * Analizza una stringa passata e verifica che sia non vuota e non nulla
	 * */
	public static boolean checkNotEmptyAndNotNullProperty(String value){
		
		return (value!= null && !"".equals(value));
	}

}
