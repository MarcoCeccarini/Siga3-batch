package it.finanze.siga.util;


public class CostantiSessione {
	
	public static final String UFFICIO_DESTINAZIONE_BEAN = "ufficioDestinazioneBean";
	public static final String UTENTE_BEAN = "utente";
	public static final String UTENTE_SOGEI_BEAN = "utenteSogei";
	public static final String UTENTE_AMM3_BEAN = "utenteAmm3";
	public static final String OPERATORE_BEAN = "operatore";
	public static final String OPERATORE_IN_VISIBILITA_BEAN = "operatoreInVisibilita";
	public static final String OPERATORE_RUOLI_DA_DELEGARE_BEAN = "operatoreBeanRuolDaDeleg";
	public static final String OPERATORE_TEMPLATE_BEAN = "operatore_template";
	public static final String MODULO_APP_BEAN = "modulo_app";
	public static final String TEMPLATE_BEAN = "template";
	public static final String AUTORIZZATORE_I_LIV = "autorizzatore_I_Liv";
	public static final String UFFICIO_CDR_APPARTENENZA = "uffCdrAppartBean";
	public static final String CDR_IN_VISIBILITA = "cdrInVisibilita";
	public static final String DETTAGLIO_VISIBILITA = "dettaglioVisibilita";
	public static final String PROFILI_OPERATORE = "profiliOperatore";
	public static final String DESCRIZIONE_RUOLI_UTENTE = "descrizioneRuoli";
	public static final String RICERCA_DA_ANNULARE = "ricercaDaAnnullare";
	public static final String CRUSCOTTO_MONITORAGGIO = "cruscotto";
	public static final String AMM_CENTRALI_APPLICATIVI = "ammCentApplicativi";
	
	
	/**
	 * Variabili  che inseriamo in sessione per discriminare il flusso, utilizzato in 
	 * per visulaizzare il registro delle variazioni ruoli, e registro deleghe 
	 */
	public static final String REGISTRO_MODALITA_ACCESSO		= "modalitaAccesso";
	public static final int REGISTRO_VARIAZIONI_RUOLI_CDR 		= 1;
	public static final int REGISTRO_DELEGHE 					= 2;
	public static final int REGISTRO_RICHIESTE_DI_UN_UFFICIO 	= 3;
	public static final int REGISTRO_ASSOCIAZIONI_ORA 			= 4;
	
	
	/**
	 * Costanti per la ricerca delle mail per i vari ruoli  
	 * 
	 */
	public static final String AMMINISTRATORE_CENTRALE			= "ESG_AMM_Centrale_3";
	public static final String AMMINISTRATORE_REGIONALE 		= "ESG_AMM_Regionale_3";
	public static final String AMMINISTRATORE_LOCALE			= "ESG_AMM_Locale_3";
	public static final String AUDIT_REGIONALE					= "ESG_AUDIT_Regionale_3";
	
//	public static final String CONVENZIONE = "convenzione";
//	public static final String AMMINISTRATORE = "datiAmministratore";
//	public static final String CFRICERCA = "codiceFiscale";
//	public static final String CATEGORIA_ORIGINALE = "originalCategoria";
//	public static final String DETTAGLIOACCOUNT = "dettaglioAccount";
//	
//	// TODO: fix me!!!!!!!!!!!!!!! ?????????????????
//	public static final String ADMINGROUP = "adminGroup";
	
	public static final String FILTRO_RICERCA=         "filtroRicerca";
	public static final String FILTRI_GEOGRAFICI=      "filtriGeografici";
	public static final String FILTRI_TIPOLOGIE=       "filtriTipologie";
	public static final String ALBERO_CDR_BY_CDR=        "alberoCdrByCDR";
	public static final String ALBERO_CDR_BY_UFFICIO=    "alberoCdrByUFFICIO";
	public static final String INTERR_PROFILAZIONE_FORM="interrProfilazioneForm";
	public static final String LISTA_APPLICAZIONI=     "listaApplicazioni";
	public static final String NUMERO_REGIONI=     "numero_regioni";
	public static final String NUMERO_PROVINCIE=     "numero_provincie";
	public static final String INTERR_MAP_PROF_UFF_FORM ="interrMappaturaProfiliUfficioForm";
	public static final String ASS_GES_OPER_FORM ="assegnazioneGestoriOperatoriForm";
	public static final String SINOTTICO_GESTORI_OPERATORI = "sinotticoGestoriOperatori";

	
	public static final String LISTA_PROFILI_APPLICAZIONI ="listaProfiliApplicazioni";
	public static final String NUMERO_UFFICI_NAZIONALI = "numero_uffici_nazionali";
	public static final String NUMERO_CDR_NAZIONALI = "numero_cdr_nazionali";
	public static final String TIPOLOGIE = "tipologie";
	public static final String LISTA_CARICAMENTI_MASSIVI="listaCaricamentiMassivi";
	public static final String DETTAGLIO_CARICAMENTO="dettaglioCaricamento";
	public static final String SUPERSIGA="SISTEMASIGA3";
	
	public static final String LISTA_COMUNICAZIONI="listaComunicazioni";
	public static final String COMUNICAZIONE_RECENTE="comunicazioneRecente";
	public static final String COMUNICAZIONE="comunicazione";
	public static final String DC_SEL="dcSelezionate";
	public static final String COP_SEL="copSelezionate";
	public static final String CAM_SEL="camSelezionate";
	public static final String DR_SEL="drSelezionate";
	public static final String DP_SEL="dpSelezionate";
}
