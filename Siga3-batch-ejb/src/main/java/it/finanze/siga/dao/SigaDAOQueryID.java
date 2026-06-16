package it.finanze.siga.dao;

public interface SigaDAOQueryID {
	
	/** SVILUPPO **/
	public static final String GET_ELENCO_UTENTI = "getElencoUtenti";
	public static final String GET_ELENCO_UTENTI_LIKE = "getElencoUtenti_Like";
	public static final String GET_ELENCO_UTENTI_SOGEI = "getElencoUtentiSogei";
	public static final String GET_ELENCO_UTENTI_AMM_3 = "getElencoUtentiAmm3";
	public static final String GET_ELENCO_AMM_REG_LOC = "getAmmRegLoc";
	
	public static final String GET_OPERATORI_PER_NOMINATIVO = "getOperatoriPerNominativo";
	public static final String GET_OPERATORI_PER_DELEGATO = "getOperatoriPerDelegato";

	/** INSERIMENTO RICHIESTA **/
	public static final String GET_ELENCO_OPERATORI_3 = "getElencoOperatori_3";
	public static final String GET_ELENCO_OPERATORI_3_OLD = "getElencoOperatori_3_old";
	public static final String GET_ELENCO_OPERATORI_IN_VISIBILITA_2 = "getElencoOperatoriInVisibilita2";
	public static final String GET_ELENCO_OPERATORI_IN_VISIBILITA_OLD = "getElencoOperatoriInVisibilitaOld";
	public static final String COUNT_OPERATORI_AORA = "countElencoOperatoriAORA";
	public static final String GET_ELENCO_OPERATORI_TEMPLATE = "getElencoOperatoriTemplate";
	public static final String GET_ELENCO_PROFILI_OPERATORE_IN_VISIBILITA = "getProfiliOperatoreInVisibilita";
	public static final String GET_ELENCO_PROFILI_OPERATORE_IN_VISIBILITA_2 = "getProfiliOperatoreInVisibilita2";	
	public static final String GET_RICH_DELEGATO_O_IN_SESS = "getRichDlgSess";
	public static final String GET_UFFICIO_CDR_DI_APPARTENENZA = "getUffCdrAppart";
	public static final String REVOCA_VISIBILITA = "revocaProfiliVisibilita";
	public static final String GET_UTENTI_IN_VISIBILITA = "getUtentiInVisibilita";
	public static final String GET_ELENCO_OPERATORI_AORA = "getElencoOperatoriAORA";
	public static final String GET_ELENCO_OPERATORI_DI_UNO_O_PIU_CDR = "getElencoOperatoriDiUnoOPiuCDR";
	public static final String GET_OPERATORE_BY_CF = "getOperatoreByCF";
	public static final String GET_ELENCO_OPERATORI_REVOCA_VISIBILITA_AC = "getElencoOperatoriRevocaVisibilitaAC";
	
	public static final String GET_NUMERO_RICHIESTE_PROFILAZIONE = "getNumRichiesteProfilaz";
	public static final String GET_AUTORIZZATORE_I_LIV 						= "getAutorizzatore_I_Liv";
	public static final String GET_AUTORIZZATORE_I_LIV_AC 					= "getAutorizzatore_I_Liv_AC";
	public static final String GET_AUTORIZZATORE_I_LIV_ASS					= "getAutorizzatore_I_Liv_Ass";
	public static final String GET_AUTORIZZATORE_I_LIV_ITER 				= "getAutorizzatore_I_Liv_Iter";
	public static final String GET_AUTORIZZATORE_I_LIV_PER_RIPRISTINO	 	= "getAutorizzatore_I_Liv_Per_Ripristino";
	public static final String GET_AUTORIZZATORE_I_LIV_PROFILI_CAT			= "getAutorizzatore_I_Liv_profili_CAT";
	public static final String PROFAUTUTE_X_UFF_DI_PROVENIENZ = "getProfAttUtenteXUfficioProvenienza";
	public static final String INTERSEZ_X_UFF_DI_ASSEGNAZ = "getIntersezProfAmmissProfAttUtenteXUfficio";
	
	public static final String GET_RICHIEDENTI_CDR_SOTTOSTANTI = "getRichiedentiCdrSottostanti";
	
	
	public static final String GET_PROFILI_UFFICIO_DESTINAZIONE = "getProfiliUfficioDestinazione";
	
 	public static final String GET_DATAORA_DB = "getDataOraDB";
	public static final String GET_ELENCO_PROFILI_OPERATORE = "getElencoProfiliOperatore";
 	public static final String GET_ELENCO_LE_MIE_VARIAZIONI_RUOLO_CDR 				= "getElencoLeMieVariazioniRuoloCDR";
	public static final String GET_ELENCO_REGISTRO_DELEGHE 							= "getElencoRegistroDeleghe";
	public static final String GET_ELENCO_REGISTRO_DELEGHE_DELEGATO_DELEGANTE 		= "getElencoRegistroDelegheDelgatoDelegante";
	public static final String GET_ELENCO_REGISTRO_DELLE_MIE_ASSOCIAZIONI_PUNTUALI 	= "getRegistroDelleMieAssociazioniPuntuali";
	public static final String GET_ELENCO_REGISTRO_ASSOCIAZIONI_ORA 	= "getRegistroAssociazioniORA";
	public static final String GET_ELENCO_ITER_NON_STANDARD_PROFILI_APPLICAZIONI = "getIterNonStandardProfiliApplicazioni";
	public static final String GET_ELENCO_ITER_NON_STANDARD_PROFILI_APPLICAZIONI_AMM_CENTRALE_APPL = "getIterNonStandardProfiliApplicazioniAmmCentraleApplicativo";
	
	public static final String GET_ELENCO_ITER_PROFILI_APPLICAZIONI = "getIterProfiliApplicazione";

	
	public static final String GET_ELENCO_CDR_2 = "getElencoCDR_nuova";
	public static final String GET_ELENCO_CDR_RICHIIESTE_DI_UN_UFFICIO = "getElencoCDRRichesteDiUnUffio";
	public static final String GET_ELENCO_CDR_RESPONSABILE = "getElencoCDR_responsabile";
	public static final String GET_ELENCO_CDR_OPERATORI_IN_VISIBILITA = "getElencoCDROperatoriVis";
	public static final String GET_ELENCO_CDR_OPERATORI_IN_VISIBILITA_ORA = "getElencoCDROperatoriVisORA";
	public static final String GET_ELENCO_CDR_2_OLD = "getElencoCDR_2_old";
	public static final String GET_ELENCO_CDR_NON_IN_VISIBILITA_2 = "getElencoCDRNonInVisibilita_2";
	
	public static final String GET_ELENCO_UFFICI_DESTINAZIONE = "getUfficiDestinazione";
	public static final String ELENCO_VALORI = "elencoValori";
	public static final String GET_ELENCO_AMBITO_5 = "getElencoAmbito_5";
	public static final String GET_ELENCO_AMBITO_5_BIS = "getElencoAmbito_5_bis";
	public static final String GET_ELENCO_AMBITO_APPL_CDR = "getElencoAmbitoTemplate";
	public static final String GET_ELENCO_PROFILO_2 = "getElencoProfilo_2";
	public static final String GET_ELENCO_PROFILO_OLD = "getElencoProfiloOld";
	public static final String GET_ELENCO_PROFILO_AC = "getElencoProfilo_AC";
	public static final String GET_ELENCO_PROFILI_DA_AC = "getElencoProfili_DA_AC";
	public static final String GET_ELENCO_PROFILO_AC_OP_VIS = "getElencoProfilo_AC_OP_VIS";
	public static final String GET_ELENCO_PROFILI_INCOMPATIBILI = "getProfiliIncompatibilita";
	// 1.3 2023 -->
	public static final String GET_PROFILO_VISIBILITA_STRUTTURA = "getProfiloVisibilitaStruttura";
	// 1.3 2023 <--
	// 4.5.9 II -->
	public static final String GET_ELENCO_PROFILI = "getElencoProfili";
	public static final String GET_ELENCO_PROFILI_INCOMPATIBILI_GEST = "getProfiliIncompatibilitaGest";
	public static final String UPDATE_PROFILO_INCOMPATIBILITA = "updateProfiloIncompatibilita";
	public static final String NUM_PROFILI_INCOMPATIBILITA = "getNumProfiliIncompatibilitaGest";
	public static final String INSERT_PROFILO_INCOMPATIBILITA = "insertProfiloIncompatibilita";
	// 4.5.9 II <--
	
	
	public static final String GET_ELENCO_PROFILI_IN_VISIBILITA = "getElencoProfiliInVisibilita";
	public static final String GET_ELENCO_PROFILI_STRUTTURA = "getElencoProfiliStruttura39";
	public static final String GET_ELENCO_PROFILI_IN_VISIBILITA_AC = "getElencoProfiliInVisibilitaAC";
	
	public static final String GET_PROFILI_RICHIESTA_VISIBILITA = "getProfiliRichiestaVisibilita";
	public static final String GET_ELENCO_PROFILO_PROFILAZIONE_UFFICIO_CDR 	= "getElencoProfiloProfilazioneUfficioCdR";
	public static final String GET_ELENCO_PROFILO_PROFILAZIONE_UFFICIO_CDR_AMM_CENTR_APP 	= "getElencoProfiloProfilazioneUfficioCdrAmmCentrApp";
	
	
	public static final String GET_ELENCO_PROFILO_VISIBILITA_2 = "getElencoProfiloVisibilita_2";
	public static final String GET_ELENCO_PROFILO_VISIBILITA_CAT = "getElencoProfiloVisibilita_SpecialeCatalogo";
	public static final String GET_PROFILO_VERIFICAITER = "getProfiloVerificaIter";
	public static final String IS_RICHIESTA_IN_CORSO = "isRichiestaInCorso";
	public static final String INSERISCI_RICHIESTA = "inserisciRichiesta";
	public static final String UPDATE_AUDIT_RICHIESTA = "updateAuditRichiesta";
	public static final String INSERISCI_PROFILO = "inserisciProfilo";
	public static final String INSERISCI_PROFILO_BATCH = "inserisciProfiloBatch";
	public static final String GET_PROFILO_VERIFICA_AUTORIZZATORE_I_LIV 					= "getProfiloVerificaAutorizzatore_I_Liv";
	public static final String GET_STRUTTURA_PER_ITER_CDR 									= "getStrutturaPerIterCDR";
//	public static final String GET_CDR_AUTORIZZATORE_I_LIVELLO = "getCDRAutorizzatore_I_livello";
	public static final String GET_PROFILO_VERIFICA_AUTORIZZATORE_II_LIV = "getProfiloVerificaAutorizzatore_II_Liv";
	public static final String GET_GESTORE_OPERATORI = "getGestoreOperatori";
	public static final String GET_ELENCO_TEMPLATE = "getElencoTemplate";
	public static final String GET_ELENCO_PROFILO_TEMPLATE = "getElencoProfiloTemplate";
	public static final String GET_ELENCO_PROFILO_ATTIVO_UTENTE 							= "getElencoProfiloAttivoUtente";
	public static final String GET_ELENCO_PROFILO_ATTIVO_UTENTE_PER_REPORT 					= "getProfiliAttiviUtentePerReport";
	public static final String GET_ELENCO_PROFILO_ATTIVO_UTENTE_IN_VISIBILITA_PER_REPORT 	= "getProfiliAttiviUtenteInVisibilitaPerReport";
	public static final String GET_STRUTTURA_ITER_ENTITY_BEAN 								= "getStrutturaIterEntityBean";
	
	public static final String GET_RICHIESTE_X_RICHIEDENTE= "getRichiesteRichiedente";  
	public static final String GET_RICHIESTE_X_AUTORIZZATORE_I="getRichiesteAutorizzatoreI"; 
	public static final String GET_RICHIESTE_X_AUTORIZZATORE_II="getRichiesteAutorizzatoreII";
	public static final String GET_RICHIESTE_X_GESTORE="getRichiesteGestore"; 
	public static final String GET_RICHIESTE_X_OPERATORE="getRichiesteOperatore";
	public static final String GET_RICHIESTE_X_ELENCO_CDR="getRichiesteByElencoCDR";
	public static final String GET_INFO_RICHIESTE_IN_CORSO="getInfoRichiesteInCorso";
	public static final String GET_INFO_RICHIESTE_IN_CORSO_AC="getInfoRichiesteInCorsoAC";	
	public static final String GET_ELENCO_RICHIEDENTI_DA_RICHIESTE_RA="getElencoRichiedentiDaRichiesteRA";
	public static final String GET_ELENCO_AUTORIZZATORI_1_LIV_DA_RICHIESTE_RA="getElencoAutorizzatori1LivDaRichiesteRA";
	public static final String GET_ELENCO_AUTORIZZATORI_2_LIV_DA_RICHIESTE_RA="getElencoAutorizzatori2LivDaRichiesteRA";
	public static final String GET_ELENCO_GESTORI_OPERATORI_DA_RICHIESTE_RA="getElencoGestoriOperatoriDaRichiesteRA";	
	public static final String GET_ELENCO_CF_UTENTI_DA_RICHIESTE_RA="getElencoCfUtentiRA";
	public static final String GET_ELENCO_ARCHIVIAZIONE_DA_RICHIESTE_RA="getElencoArchiviazioneDaRichiesteRA";
	    
	
	public static final String GET_RICHIESTE="getElencoRichieste";
	public static final String GET_COUNT_RICHIESTE="getCountElencoRichieste";
	public static final String GET_ELENCO_RICHIEDENTI_DA_RICHIESTE="getElencoRichiedentiDaRichieste";
	public static final String GET_ELENCO_AUTORIZZATORI_1_LIV_DA_RICHIESTE="getElencoAutorizzatori1LivDaRichieste";
	public static final String GET_ELENCO_AUTORIZZATORI_2_LIV_DA_RICHIESTE="getElencoAutorizzatori2LivDaRichieste";
	public static final String GET_ELENCO_GESTORI_OPERATORI_DA_RICHIESTE="getElencoGestoriOperatoriDaRichieste";	
	public static final String GET_ELENCO_UFFICI_DA_RICHIESTE="getElencoCfUtenti";
	
	public static final String GET_UFFICIO_BY_CDR="getUfficioByCdr";
	
	/**
	 * REGISTRO MIE RICHIESTE
	 */
	public static final String GET_RICHIESTE_RR="getElencoRichiesteRR";
	public static final String GET_COUNT_RICHIESTE_RR="getCountElencoRichiesteRR";
	public static final String GET_ELENCO_RICHIEDENTI_DA_RICHIESTE_RR="getElencoRichiedentiDaRichiesteRR";
	public static final String GET_ELENCO_AUTORIZZATORI_1_LIV_DA_RICHIESTE_RR="getElencoAutorizzatori1LivDaRichiesteRR";
	public static final String GET_ELENCO_AUTORIZZATORI_2_LIV_DA_RICHIESTE_RR="getElencoAutorizzatori2LivDaRichiesteRR";
	public static final String GET_ELENCO_GESTORI_OPERATORI_DA_RICHIESTE_RR="getElencoGestoriOperatoriDaRichiesteRR";	
	public static final String GET_ELENCO_CF_UTENTI_DA_RICHIESTE_RR="getElencoCfUtentiRR";
	public static final String GET_ELENCO_ARCHIVIAZIONE_DA_RICHIESTE_RR="getElencoArchiviazioneDaRichiesteRR";
	public static final String GET_ELENCO_COD_STRUTT_BY_COD_UFF="getCodiciStrutturaByCodUff";
	//
	
	
	public static final String GET_RICHIESTA="getRichiesta";
	public static final String GET_RICHIESTA_WF="getRichiestaWF";
	public static final String GET_RICHIESTA_RR="getRichiestaRR";
	public static final String GET_PROFILI_RICHIESTA="getProfiliRichiesta";
	public static final String GET_COD_APPL="getCodApplByIdRichiesta";
	public static final String AGGIORNA_RICHIESTA="aggiornaRichiesta";
	public static final String AGGIORNA_VISIBILITA_UTENTE="aggiornaVisibilitaUtente";
	public static final String AGGIORNA_PROFILO_RICHIESTA="aggiornaProfiloRichiesta";
	public static final String CANCELLA_PROFILO_ATTIVO_UTENTE="cancellaProfiloAttivoUtente";
	public static final String INSERISCI_PROFILO_ATTIVO_UTENTE="inserisciProfiloAttivoUtente";
	public static final String CANCELLA_PROFILO_STORICO_UTENTE="cancellaProfiloStoricoUtente";
	public static final String INSERISCI_PROFILO_STORICO_UTENTE="inserisciProfiloStoricoUtente";
	public static final String AGGIORNA_PROFILO_VISIBILITA_UTENTE="aggiornaProfiliVisibilitaUtente";
	public static final String GET_ORIGINE_ABILITAZIONE = "getOrigineAbilitazione";
	
	public static final String GET_UFFICIO_DA_PRESERVARE = "getUfficioDaPreservare";
	
	public static final String PRESA_IN_CARICO					="presaInCarico";
	public static final String RILASCIA_RICHIESTA				="rilasciaRichiesta";
	public static final String FILE_RICHIESTA					="getFilesRichiesta";
	public static final String GET_DESCR_CDR_BY_COD_UFF			="getDescrCdrByCodUff";
	public static final String GET_DESCR_CDR_BY_COD_CDR			="getDescrCdrByCodCDR";
	public static final String GET_DESCR_CDR_BY_COD_CDR_CHIUSO	="getDescrCdrByCodCDRAncheChiuso";
	public static final String GET_DESCR_TIPO_UFF				="getDescrTipoUff";
	public static final String GET_DESCR_TIPO_CDR				="getDescrTipoCdr";
	public static final String GET_FLAG_CDR_BLOCCATO			="getFlagCdrBloccato";

	
	public static final String GET_DATI_UTENTE="getDatiUtente";
	public static final String DATI_UTENTE2 = "getDatiUtente2";
	public static final String DATI_UTENTE_CDR_RESPONSABILE = "getDatiUtenteCdrResponsabile";
	public static final String GET_CDR_UTENTE_IN_VISIBILITA="getCDRUtenteInVisibilita";
	public static final String GET_DESCRIZIONE_CDR="getDescrizioneCdr";
	public static final String GET_DESCRIZIONE_STATO="getDescrizioneStato";
	public static final String GET_CDR_UFFICIO_PASSATO="getCDR_UfficioPassato";
	public static final String GET_CF_RICHIEDENTE		="getCf_Richiedente";
	public static final String GET_CF_RESPONSABILE		="getCf_Responsabile";
	public static final String GET_CF_RESPONSABILE_BY_CF ="get_ResponsabileByCF";
	public static final String GET_EXPLICIT_ENTITLEMENT="getExplicit_Entitlement";
	public static final String GET_CDR_ALBERO_ESCLUSI="getCdrAlberoEsclusi";
	public static final String GET_PROFILI_ALBERO_ESCLUSI="getProfiliAlberoEsclusi";
	public static final String GET_PROFILI_ALBERO_ESCLUSI_IN_VISIBILITA="getProfiliAlberoEsclusi_inVisibilita";
	public static final String CONTROLLO_VISIBILITA_FASE_DUE="controlloVisibilitaFaseDue";
	
	public static final String GET_CDR_PADRE="getCdrPadre";

	public static final String INSERISCI_FILE = "inserisciFile";
	public static final String RIMOZIONE_DELEGA = "rimozioneDelega";
	public static final String INSERISCI_AUDIT = "inserisciAudit";
	
	public static final String KEY_ID_DELEGA = "keyIdDelega";
	public static final String INS_ID_ALLEGATI_DELEGA = "insIdAllegatiDelega";
	public static final String INS_ALLEGATI_DELEGA = "inserisciAllegatiDelega";
	public static final String GET_ALLEGATI_DELEGA = "getAllegatiDelega";
	
	/** DELEGHE **/
	public static final String GET_ELENCO_STRUTTURA_UFFICIO_CDR_ADMIN_2 = "elenco_struttura_ufficio_cdr_admin_2";
	public static final String GET_ELENCO_CDR_FROM_STRUTTURELIST = "getElencoCdrFromStruttureList";
	public static final String GET_ELENCO_CDR_DI_UNA_STRUTTURA = "getElencoCdrDiUnaStruttura";
	public static final String GET_ELENCO_STRUTTURA_UFFICIO_CDR = "elenco_struttura_ufficio_cdr";
	public static final String GET_ELENCO_STRUTTURA_UFFICIO_CDR_VIS = "elenco_struttura_ufficio_cdr_visibilita";
	public static final String GET_ELENCO_STRUTTURA_UFFICIO_CDR_IN_VIS = "elenco_struttura_ufficio_cdr_in_visibilita";
	public static final String GET_OPERATORI_BY_CDR = "getOperatoriByCDR";
	public static final String GET_OPERATORI_BY_NOMINATIVO = "getOperatoriByNominativo";
	public static final String GET_DELEGATO_O_DELEGANTE_BY_CDR = "getDelegatoOdeleganteByCDR";
	public static final String INSERISCI_DELEGA = "inserisciDelega";
	public static final String INSERISCI_DELEGA_BATCH = "inserisciDelegaBatch";
	public static final String GET_ELENCO_RICHIEDENTE_CDR = "getRichiedenteCdr";
	public static final String GET_ELENCO_RICHIEDENTE_PUNTUALE_CDR = "getRichiedentePuntualeCdr";
	public static final String GET_ELENCO_RICHIEDENTI_AC = "getRichiedentiAC";
	public static final String GET_CDR_DA_RICHIEDENTE_CDR = "getCdrDaRichiesteCDR";
	
	public static final String GET_ELENCO_DELEGHE = "getElencoDeleghe";
	public static final String AGGIORNA_RICHIESTE = "aggiornaRichiesteDeleghe";
	public static final String AGGIORNA_RICHIESTE2 ="aggiornaRichiesteDeleghe2";
	public static final String GET_STRUTTURA_CDR =	"getStrutturaByCDR";
	public static final String GET_DELEGANTI_BY_CDR ="getDelegantiByCDR";
	public static final String GET_ELENCO_STRUTT_R ="getElencoStruttRegionali";
	public static final String GET_ELENCO_STRUTT_STRUTT_II_LIV ="getElencoStruttureStrutturaIIliv";
	public static final String GET_DELEGABILI_BY_CDR = "getDelegabiliByCDR";
	public static final String GET_DELEGABILI_BY_CDR_ORA = "getDelegabiliByCdROra";
	/** RICHIEDENTE_CDR **/
	public static final String AGGIORNA_RICHIESTA_CDR 		= "aggiornaRichiedenteCdr";
	public static final String AGGIORNA_SOLO_RICHIESTA_CDR 		= "aggiornaSoloRichiedenteCdr";
	public static final String INSERISCI_RICHIESTA_CDR 		= "inserisciRichiedenteCdr";
	public static final String VERIFICA_IS_RICHIEDENTE 		= "verificaIsRichiedente";
	public static final String VERIFICA_RESPONSABILE_CDR_FIGLI 		= "verificaResponsabileCdrFigli";

	public static final String COUNT_RICHIEDENTE_CDR 		= "countRichiedenteCDR";
	public static final String GET_NUMERO_ASSOCIAZIONI 		= "getNumeroAssociazioni";
	public static final String GET_NUMERO_ASSOCIAZIONI_OPERATORE 		= "getNumeroAssociazioniOperatore";	
	public static final String GET_NUMERO_ASSOCIAZIONI_RICH_AUT 	= "getNumeroAssociazioniRichOrAut";
	public static final String VERIFICA_ASSOCIAZIONI 		= "verificaAssociazioni";
	/** UTENTE **/
	public static final String GET_CODICI_FISCALI_ABILITATI = "getCodiciFiscaliAbilitati";
	public static final String AGGIORNA_FLAG_RUOLO 			= "aggiornaFlagRuolo";
	public static final String UTENTE_ABILITATO 			= "abilitatoCambioUfficio";
	public static final String GET_PROFILI_ATTIVI 			= "getProfiliAttivi";
	public static final String GET_STRUTTURA_2 				= "getStrutturaIILiv";
	public static final String GET_CODICE_AMML 				= "getCodiceAmmLocale";
	public static final String GET_RUO_GEST_OPE 			= "getRuoliGestoriOperatori";
	public static final String GET_AMM_APP 					= "getAmmCentraleApp";	
	public static final String GET_EMAIL_RICH_VISIBILITA 	= "getEmailRichiedenteVisibilita";
	public static final String GET_CF_RICH_WFM 				= "getCFRichiedenteWFM";
	public static final String GET_CF_RICH_VISIBILITA_WFM	= "getCFRichiedenteVisibilitaWFM";
	public static final String GET_CF_RICH_ECA_ALTRI_CDR_WFM	= "getCFRichiedentiECAAltriCDRWFM";
	public static final String GET_EMAIL_AUTI			 	= "getEmailAutorizzatoreI";
	public static final String GET_EMAIL_ALTRO_UFF_INTERESSATO			 	= "getEmailAltroUfficioInteressato";
	public static final String GET_EMAIL_AL_VISIBILITA 		= "getEmailAmmLocaliVisibilita";
	public static final String GET_EMAIL_PER_RUOLO	 		= "getEmailPerRuolo";
	public static final String GET_EMAIL_RICH_AND_AMM_LOCALI_CDR = "getEmailRichiedenteAndAmmLocaliByCdr";	
	public static final String GET_EMAIL_SOVRAORDINATI	 	= "ricavaMailSovraordinati";
	
	public static final String GET_EMAIL_AMM_CENTRALI	 		= "getEmailAmmCentrali";
	public static final String GET_EMAIL_STRUTT_II_LIV	 		= "getEmailStrutturaIILiv";
	public static final String VERIFICA_STRUTTURE	 			= "verificaStrutture";
	public static final String 	GET_STRUTTURE_II_LIV_BY_CDR		= "getStrutturaIILivByCdr";
	public static final String 	GET_II_LIV_BY_CDR		= "getIILivByCdr";
	
	public static final String GET_CDR_AMM_LOC_DA_DISABILITARE = "getCdrAmmLocaleDaDisabilitare";
	
	
	//DELEGHE II Livello --> PIER
	public static final String ELENCO_ITER_II_LIVELLO = "elencoIdentIterSecondoLiv";
	public static final String TIPOLOGIA_UFFICIO_II_LIVELLO = "tipologieUfficioIterSecondoLiv";
	public static final String DESCRIZIONE_PROFILI_UFFICIO = "descrizioneProfiliUfficio";
	// <--
	
//	public static final String GET_SERVIZI_UTENTE 			= "getServiziUtente";

	/* 	Aggiornamento puntuale visibilita		*/
	public static final String GET_FIGLI_ECA ="getFigliEca";
	public static final String GET_LIST_DATE_SCADENZA_VISIBILITA= "getListDateScadenzaVisibilita";
	public static final String UPDATE_DATA_FINE_VAL_IN_VISIBILITA= "updateDataFineValInVisibilita";
	public static final String UPDATE_DATA_FINE_VAL_CDR= "updateDataFineValCdr";
	public static final String SET_DATA_FINE_VAL_UTENTE_VIS = "setDataFineValUtenteVis";
	public static final String GET_CODICE_UFFICIO_UFF_STRUT="get_codiceUfficio_uffStrut";
	
	/** UFFICO **/
	public static final String GET_UFFICO 					= "getUffico";
	
	/** FUNZIONE per la  verifica del mantenimento del ruolo di AUTORIZZATORE **/
	public static final String CONTROLLO_RUOLO_AMMINISTRATORE = "controlloRuoloAmministratore";

	/** STRUTTURA_ITER **/
	public static final String GET_STRUTTURA_ITER 					= "getStrutturaIter";
	public static final String GET_STRUTTURA_ITER_2 					= "getStrutturaIter2";
	
	/**  REGISTRO_RICHIESTE **/
	public static final String ANNULLAMENTO_RICHIESTE_NON_AUTORIZZATE 				= "annullamentoRichiesteNonAutorizzate";
	public static final String COUNT_RICHIESTE_NON_AUTORIZZATE 						= "countRichiesteNonAutorizzate";
	public static final String RIPRISTINA_RICHIESTE_NON_AUTORIZZATE 				= "ripristinaRichiesteNonAutorizzate";
	public static final String SOSTITUISCI_AUTORIZZATORE 							= "sostituisciAutorizzatore";

	public static final String RIPRISTINA_RICHIESTE_AUTORIZZATE 					= "ripristinaRichiesteAutorizzate";
	public static final String SOSTITUZIONE_RICHIEDENTE 							= "sostituzioneRichiedente";
	public static final String ANNULLAMENTO_RICHIESTE_NON_AUTORIZZATE_DA_METODO 	= "annullamentoRichiesteNonAutorizzateDaMetodo";
	public static final String AGGIORNAMENTO_REGISTRO_RICHIESTE 					= "aggiornamentoRegistroRichieste";
	public static final String GET_REGISTRO_RICHIESTE_PER_CASO2 					= "getRegistroRischiestaPerCaso2";
	public static final String COUNT_RICHIESTE_ATTIVE_GENERAL 						= "countRichiesteAttiveGeneral";
	public static final String COUNT_RICHIESTE_AS_RICHIEDENTE_TO_ELAB				= "countRichestAsRichiedenteToElab";
	public static final String COUNT_PROFILI_AS_GESTORE_DI_RETE						= "countProfiliAsGestoreDiRete";
	public static final String COUNT_ESISTE_PRECEDENTE_REVOCA						= "countEsistePrecedenteRevoca";
	public static final String SELECT_CF_ATTORE_FASE_AU_D 							= "selectAttoreFaseAU_D";
	public static final String GET_ELENCO_RICHIESTE_ITER_ID 						= "getElencoRichiesteIter";
	public static final String AGGIORNA_REGISTRO_RICHIESTE_STRUTTURA 				= "aggiornaRegistroRichiesteStruttura";
	
	/** RICHISTA VISIBILITA' **/
	public static final String INSERISCI_RICHIESTA_VISIBILITA 		= "inserisciRichiestaVisibilita";
	public static final String INSERISCI_UTENTE_IN_VISIBILITA 		= "inserisciUtenteInVisibilita";
	public static final String INSERISCI_PROFILO_IN_VISIBILITA 		= "inserisciProfiloInVisibilita";
	public static final String GET_STATO_REGISTRO_RICHIESTA = "getStatoRegistroRischiestaByID";
	
	/** ASSOCIAZ_OPER_RICHI_AUTOR **/
	public static final String GET_ASSOCIAZIONI_PER_RIMOZIONE 		= "getAssociazioniPerRimozione";
	public static final String RIMOZIONE_ASSOCIAZIONI				= "rimozioneAssociazione";
	public static final String INSERISCI_ASSOCIAZIONI 				= "inserisciAssociazioni";
	public static final String RIMOZIONE_ASSOCIAZIONE2 				= "rimozioneAssociazione2";
	public static final String RIMOZIONE_ASSOCIAZIONE3 				= "rimozioneAssociazione3";
	public static final String GET_CAMBIO_AUTORIZZATORI				= "cambioAutorizzatore";
	

	/** PROFILI OPERATORE COMPETENZA **/
	public static final String GET_ELENCO_PROFILO_ATTIVO_UTENTE_COMPETENZA= "getElencoProfiloAttivoUtenteCompetenza";
	
	
	/** MAPPATURA APPLICAZIONI 	**/
	
	public static String GET_AMBITO_BY_APP = "getAmbitoByApp";
	public static String GET_APP ="getApp";
	public static String GET_APP_MAPPATURA ="getAppMappatura";
	public static String GET_APPLICAZIONI ="getApplicazioni";
	/** GESTIONE TEMPLATE **/
	public static final String INSERISCI_TEMPLATE = "inserisciTemplate";
	public static final String INSERISCI_PROFILO_TEMPLATE = "inserisciProfiloTemplate";
	public static final String GET_PROFILI_X_AMBITO_COD_UFFICIO = "getProfiliXAmbitoUfficio";
	public static final String UPDATE_TEMPLATE = "updateTemplate";
	public static final String CANCELLA_PROFILI_TEMPLATE = "cancellaProfiliTemplate";
	public static final String GET_ELENCO_PROFILI_TEMPLATE = "getProfiliTemplate";
	public static final String GET_TEMPLATE 	= "getTemplateSingolo";
	public static final String INSERISCI_PROFILO_REVOCATO = "inserisciProfiloRevocato";
	
	/** GET_AMBITO_APPLICATIVO **/
	public static final String GET_AMBITO_APPLICATIVO 	= "getDatiAmbitoApplicativo";
	public static final String GET_CF_GESTORI 			= "getGestoriCF";
	
	/** REVOCA VISIBILITA **/
	
	
	/** ASSEGNA AUTORUIZZATORE RICHIEDENTE **/
	public static final String GET_FLAG_RICH_AUT  					= "getFlagRichAutUtente";
	public static final String SET_FLAG_RICH_AUT  					= "setFlagRichAutUtente";
	public static final String UPDATE_FLAG_UTE_CDR_DISALLINEATO  	= "updateFlagUtenteCdrDisallienato";
	public static final String GET_STRUTTURA_ITER_BY_CDR 			= "getStrutturaIterByCdr";
	public static final String GET_STRUTTURA_ITER_BY_CDR_II_LIVELLO = "getStrutturaIterByCdrIILivello";
	public static final String GET_RICHIEDENTI_BY_CDR = "getRichiedentiByCDR";
	public static final String GET_AUTORIZZATORI_BY_CDR = "getAutorizzatoriByCDR";
	public static final String AGGIORNA_ASSOCIAZIONI = "aggiornaAssociazioni";
	public static final String RIMUOVI_ASSOCIAZIONE = "rimuoviAssociazione";
	public static final String RIPRISTINA_RICHIESTE = "ripristinaRichieste";
	public static final String GET_REGIONI_BY_CODICI_APP = "getRegioniByCodiciApp";
	public static final String GET_PROVINCE_BY_CODICI_APP = "getProvinceByCodiciApp";
	public static final String GET_REGIONI_BY_CODICI_APP_MAP = "getRegioniByCodiciAppMappatura";
	public static final String GET_PROVINCE_BY_CODICI_APP_MAP = "getProvinceByCodiciAppMappatura";
	public static final String GET_TIPI_UFFICIO = "getTipiUfficio";
	public static final String GET_TIPI_CDR = "getTipiCdr";
	public static final String GET_TIPI_UFFICIO_MAP = "getTipiUfficioMappatura";
	public static final String GET_TIPI_CDR_MAP = "getTipiCdrMappatura";
	
	public static final String GET_ALBERO_CDR 					= "getAlberoCdr";
	public static final String GET_ALBERO_CDR_BY_ROUL_GROUP 	= "getAlberoCdrByRoulGroup";
	public static final String GET_ALBERO_CDR_BY_ROUL_GROUP_MAP 	= "getAlberoCdrByRoulGroupMappatura";
	public static final String GET_ALBERO_UFFICI_BY_ROUL_GROUP 	= "getAlberoUfficioByRoulGroup";
	public static final String GET_ALBERO_UFFICI_BY_ROUL_GROUP_MAP 	= "getAlberoUfficioByRoulGroupMappatura";
	public static final String GET_ALBERO_UFFICI_BY_ROUL_GROUP_NEW_MAP 	= "getAlberoUfficioByRoulGroupMappatura_new";
	public static final String GET_LISTA_PROFILI 	= "getListaProfili";
	public static final String GET_LISTA_PROFILI_ASSEGNATI 	= "getListaProfiliAssegnati";
	public static final String GET_LISTA_DESC_PROFILO_RIEPILOGO = "getDescrizioneProfiloRiepilogo";
	public static final String GET_LISTA_DESC_PROFILO_RICH_RIEPILOGO = "getDescrizioneProfiliRichRiepilogo";
	public static final String GET_LISTA_DESC_PROFILO_RICH_AGGIUNTI = "getDescrizioneProfiliRichAggiunti";
	public static final String GET_LISTA_DESC_PROFILO_RICH_RIMOSSI = "getDescrizioneProfiliRichRimossi";
	public static final String GET_ELENCO_GESTORI_RICH_AMBITO5 = "getElencoGestoriRichiestaAmbito5";

	
	public static final String PROFILO_COMPETENZA_NEW_CON_DESC = "profili_competenza_new_con_desc";
	public static final String PROFILO_COMPETENZA_NEW_CON_DESC_VISIBILTA = "profili_competenza_new_con_desc_visibilita";
	public static final String PROFILO_COMPETENZA_DIVISIONE = "profilo_competenza_divisione";
	public static final String COUNT_PROFILI_COMP_SPEC = "count_profili_spec";
	public static final String COUNT_PROFILI_COMPETENZA = "count_profili_competenza";
	public static final String COUNT_PROFILI_CDR_VISIBILITA = "count_profili_cdr_visibilita";
	
	public static final String GET_PROFILI_BY_TIPOLOGIA= "getProfiliByTipologia";
	public static final String GET_PROFILI_BY_SPECIFICO_UFF= "getProfiliBySpecificoUff";
	public static final String GET_PROFILI_BY_SPECIFICO_CDR= "getProfiliBySpecificoCdr";
	/** REGISTRO DELEGHE, REGISTRO LE MIE VARIAZIONI,REGISTRO ASSOCIAZIONI ORA ECC... **/
	public static final String GET_ELENCO_STRUTTURA_CDR = "select_dati_struttura";
	public static final String GET_ELENCO_UFFICI_CDR = "select_dati_ufficio";
	public static final String GET_PROFILI_BY_ADMIN_GROUP= "getProfiliByAdminGroup";
	public static final String GET_PROFILI_BY_ROLE_GROUP= "getProfiliMappaturaByRoleGroup";
	public static final String GET_PROFILI_BY_ROLE_GROUP_MAIN_EXPORT= "getProfiliMappaturaByRoleGroupMainExport";
	public static final String GET_PROFILI_BY_COD_APPL_EXPORT= "getProfiliByCodiciApplicazioneExport";
	public static final String GET_PROFILI_EXPORT_SINOTTICO = "getProfiliExportSinottico";
	public static final String GET_PROFILI_BY_ROLE_GROUP_CDR_UFFICIO= "getProfiliMappaturaByRoleGroupCdrUfficio";
	public static final String GET_PROFILI_BY_ROLE_GROUP_TIPO_CDR_TIPO_UFFICIO= "getProfiliMappaturaByRoleGroupTipoCdrTipoUfficio";

	public static final String RICHIESTE_REVOCA_IN_CORSO 			= "richiesteRevocaInCorso";
	public static final String RICHIESTE_IN_CORSO_PER_LA_COPPIA_CF_CDR = "richiesteInCorsoPerLaCoppiaCFCDR";
	public static final String VERIFICA_RICHIESTE_IN_CORSO = "verificaRichiesteInCorso";

	public static final String GET_LISTA_CDR = "getListaCDR";
	public static final String GET_LISTA_CDR_DI_UN_UFFICIO = "getListaCDRDiUnUfficio";
	public static final String GET_LISTA_CDR_DI_UNA_STRUTTURA = "getListaCDRDiUnaStruttura";
	public static final String GET_LISTA_CDR_DI_UNA_STRUTTURA_CON_CHIUSI = "getListaCDRDiUnaStrutturaConChiusi";
	
	/** OPERATORI ESTERNI **/
	public static final String ESISTE_UTENTE_CANCELLATO = "countPresenzaOperatoreCancellato";
	public static final String AGGIORNA_UTENTE = "aggiornaUtente";
	public static final String INSERISCI_UTENTE = "inserisciUtente";
	public static final String INSERISCI_OPERATORE_ESTERNO = "inserisciOperatoreEsterno";
	public static final String INSERISCI_ALLEGATO_OPERATORE_ESTERNO = "inserisciAllegatoOperatoreEsterno";
	public static final String GET_TIPOLOGIE_UTENTI = "getTipologieUtenti";
	public static final String INSERISCI_OPERATORE_ESTERNO_TIPO_UTENTE = "inserisciOperatoreEsternoTipoUtente";
	public static final String GET_PROFILI_DISPONIBILI_BY_TIPO_OPERATORE_ESTERNO = "getProfiliDisponibiliByTipoOperatoreEsternoAndCdr";
	public static final String GET_PROFILI_NON_AMMISSIBILI_BY_TIPO_OPERATORE_ESTERNO = "getProfiliNonAmmessiByTipoOperatoreEsternoAndCdr";
	public static final String GET_OPERATORI_ESTERNI = "getOperatoriEsterni";
	public static final String GET_TIPOLOGIE_ATTIVE_OPERATORE_ESTERNO = "getTipologieAttiveOperatoreEsterno";
	public static final String GET_ALLEGATI_OPERATORE_ESTERNO = "getAllegatiOperatoriEsterni";
	public static final String GET_ALLEGATO_BLOB_OPERATORE_ESTERNO = "getAllegatoBLOB";
	public static final String AGGIORNA_OPERATORE_ESTERNO = "aggiornaOperatoreEsterno";
	public static final String AGGIORNA_EMAIL_UTENTE = "aggiornaEmailUtente";
	public static final String CANCELLA_OPERATORE_ESTERNO_TIPO_UTENTE = "cancellaOperatoreEsternoTipoUtente";
	public static final String INSERISCI_NOTE_CANCELLAZIONE_OPERATORE_ESTERNO = "inserisciNoteCancellazioneOperatoreEsterno";
	public static final String CANCELLA_UTENTE = "cancellaUtente";
	public static final String GET_PROFILI_ATTIVI_BY_CF_AND_TIPO_UTENTE_LIMITATA = "getProfiliAttiviByCFAndCodiceTipologiaLimitata";
	public static final String GET_CDR_INFO = "getCdrInfo";
	public static final String GET_PROFILI_ATTIVI_BY_CF = "getProfiliAttiviByCF";
	public static final String GET_ELENCO_PROFILI_CDR = "getElencoProfiliCDR";
	public static final String GET_PROFILI_AMMESSI_OPERATORE_ESTERNO = "getProfiliAmmessiOperatoreEsterno";
	public static final String GET_ELENCO_TIPOLOGIE_UTENTI = "getElencoTipologieUtenti";
	public static final String GET_PROFILI_ATTIVI_PER_TIPOLOGIA_BY_CF = "getProfiliAttiviPerTipologiaByCF";
	public static final String GET_PROFILI_SCOPERTI_BY_CF_AND_TIPOLOGIE_BLOCCATE = "getProfiliScopertiByCFAndTipologieBloccate";
	public static final String INSERT_RICHIESTA_BATCH_OE = "inserisciRichiestaBatchOE";
	
	
	/** MAPPATURA PROFILI UFFICIO**/
	public static String GET_ROLE_GRUOUPS_BY_CODAPP ="getRoleGroupsByCodiceApplicazione";
	
	
	/**  BATCH **/
	public static final String GET_AMMINISTRATORI_LOCALI = "getElencoAmministratoriLocali";
	
	public static final String AGGIORNA_RICHIEDENTE_AUTORIZZATORE_BY_CDR = "aggiornaRichiedenteAutorizzatoreByCDR";
	public static final String GET_CF_RESPONSABILE_BY_CDR = "getCF_Responsabile_ByCdr";
	public static final String INSERT_MAPPATURA_CDR_UFF = "insertMappaturaCdrUff";
	public static final String INSERT_MAPPATURA_TIPO_UFF_CDR = "insertMappaturaTipoUffCdr";
	public static final String CANCELLA_MAPPATURA_TIPO_UFF_CDR = "cancellaMappaturaTipoUffCdr";
	public static final String CANCELLA_MAPPATURA_CDR_UFF = "cancellaMappaturaCdrUff";
	public static final String UPDATE_MAPPATURA_TIPO_UFF_CDR = "updateMappaturaTipoUffCdr";
	public static final String UPDATE_MAPPATURA_CDR_UFF = "updateMappaturaCdrUff";
	public static final String GET_CODICE_REGIONE = "getCodiceRegione";
	public static final String GET_CODICE_PROVINCIA = "getCodiceProvincia";
	public static final String GET_DESCR_REGIONE = "getDescrizioneRegione";
	public static final String GET_DESCR_PROVINCIA = "getDescrizioneProvincia";
	
	public static final String START_BATCH_VAR_DATI_UTE= "startBatchVariazioneDatiUte";
	public static final String END_BATCH_VAR_DATI_UTE= "endBatchVariazioneDatiUte";
	public static final String START_BATCH_RESP= "startBatchResponsabili";
	public static final String END_BATCH_RESP= "endBatchResponsabili";
	public static final String START_BATCH_MAIL_RUOLI= "startBatchEmailRuoli";
	public static final String END_BATCH_VAR_MAIL_RUOLI= "endBatchEmailRuoli";
	
	// COERENZA
	public static final String GET_COERENZA_PROPERTIES = "selectPropertiesBatchCoerenza";
	public static final String GET_COSTANTI_SIGA = "selectCostantiSigaBatchCoerenza";
	public static final String SET_FLAG_COERENZA_UTENTI = "setFlagCoerenzaUtenti";
	public static final String SET_FLAG_COERENZA_UTENTI_BY_CDR = "setFlagCoerenzaUtentiByCdr";
	public static final String SET_FLAG_COERENZA_HR_UTENTI = "setFlagCoerenzaHrUtenti";
	public static final String SET_FLAG_COERENZA_CEA_UTENTI = "setFlagCoerenzaCeAUtenti";
	public static final String SET_FLAG_COERENZA_UTENTI_BY_CF = "setFlagCoerenzaUtentiByCf";
	public static final String GET_UTENTI_DA_CONTROLLARE = "getUtentiDaControllare";
	public static final String GET_NUMERO_UTENTI = "getNumeroUtenti";
	public static final String GET_UTENTI_PER_WS = "getUtentiPerWS";
	public static final String GET_COD_UFF_BY_CDR = "getCodUffByCdr";
	public static final String GET_EXPL_ENT_COD_PROF = "getExplicitEntitlementByCodProf";
	public static final String GET_PROFILI_ATTIVI_UTENTE = "getProfiliAttiviUtente";
	public static final String GET_PROFILI_ATTIVI_UTENTE_WF = "getProfiliAttiviUtenteWF";

	public static final String GET_DATI_PROFILI_ATTIVI_UTENTE = "getDatiProfiliAttiviUtente";
	public static final String GET_COD_ROLE_GROUP_BY_OPERATORE = "getRoleGroupByOperatore";
	public static final String GET_COD_ROLE_GROUP_BY_PROFILO = "getRoleGroupByProfilo";
	public static final String GET_LIST_ROLE_GROUP_BY_CDR_PROF = "getListaRoleGroupByCdrProfilo";
	public static final String GET_LIST_ROLE_GROUP_BY_CDR_PROF_2 = "getListaRoleGroupByCdrProfilo2";
	public static final String GET_LIST_PROFILI_COERENZA = "getListaProfiliCoerenza";
	public static final String INSERT_RICHIESTA_BATCH = "inserisciRichiestaBatch";
	public static final String INSERT_RICHIESTA_BATCH_DA_ELAB = "inserisciRichiestaBatchDaElab";
	public static final String INSERT_UTENTE_IN_VISIBILITA = "insertUtenteInVisibilita";
	public static final String DELETE_RICHIESTA_BATCH_DA_ELAB = "cancellaRichiestaBatchDaElab";	
	public static final String INCREMENT_RICHIESTA_BATCH_DA_ELAB = "incrementaTentativoRichiestaBatchDaElab";
	public static final String GET_TENTATIVI_RICHIESTA_BATCH_DA_ELAB = "getTentativiRichiestaBatchDaElab";	
	public static final String GET_NUMERO_RICHIESTE_MASSIVE_RIMASTE = "getNumeroRichiesteMassiveBatchSeq";	
	public static final String GET_NUMERO_RICHIESTE_VISIBILITA_MASSIVE_RIMASTE = "getNumeroRichiesteVisibitaMassiveBatchSeq";	
	public static final String GET_CODICE_EVENTO = "getCodiceEventoBatch";	
	public static final String GET_CODICI_II_LIV = "getListaCodiciIILiv";
	
	public static final String INSERT_SEGN_INCOER_BATCH = "inserisciSegnalazioneIncoerenzaBatch";
	public static final String GET_CDR_VISIBILITA_PROFILO = "getCdrVisibilitaProfilo";
	public static final String GET_DESCRIZIONE_AMBITO = "getDescrizioneAmbito";
	public static final String GET_DESCRIZIONE_CDR_BATCH = "getDescrizioneCdrBatch";
	public static final String GET_COD_UFF_BY_ID_RICH_VIS = "getCodiceUfficioByIdRichVis";
	public static final String GET_COD_UFF_BY_CF = "getCodiceUfficioByCf";
	public static final String GET_CODICE_CDR_BY_CF = "getCodiceCdrByCf";
	public static final String GET_PROFILI_ATTIVI_UTENTE_BY_CF_AND_AMBITO = "getProfiliAttiviUtenteByCfAndAmbito";
	public static final String GET_DESCRIZIONE_UFFICIO = "getDescrizioneUfficio";
	public static final String GET_DESCRIZIONE_PROFILO = "getDescrizioneProfilo";
	public static final String GET_ADMIN_GROUP_CDR_UFF_BY_COD_PROFILO = "getAdminGroupCdrUffByCodProfilo";
	public static final String GET_CDR_MINIMO_BY_COD_UFFICIO = "getCdrMinimoByCodiceUfficio";
	public static final String GET_COUNT_RELAZ_CDR_UFF = "getCountRelazCdrUff";
	public static final String GET_SEQUENCE_FROM_RICHIESTE_BATCH = "getSequenceFromRichiesteBatch";
	public static final String GET_SEQUENCE_FROM_RICHIESTE = "getCurrSequenceFromRichieste";
	public static final String SET_INCOERENZE_VIS_SEGNALATE = "setIncoerenzeVisibilitaSegnalate";
	public static final String SET_INCOERENZE_ASS_SEGNALATE = "setIncoerenzeAssegnazioneSegnalate";
	public static final String GET_ELENCO_COD_CDR_COD_STRUTT_BY_COD_UFF = "getCodCdrCodStrutturaByCodUff";
	public static final String GET_DATI_VISIBILITA_EPS = "getDatiVisibilitaEps";
	public static final String GET_CODICI_UFFICIO_CAT="getCodiciUfficioCat";
	public static final String GET_CDR_TERRITORIO_SENZA_PROFILI_CAT="getCdrTerritorioSenzaProfiliCau";
	public static final String GET_DESCR_II_LIV = "getDescrIILiv";
	
	// BATCH CESSAZIONE MOBILITA
	public static final String GET_RICH_BATCH_UTENTI = "getRichiesteBatchCessazioneUtenti";
	public static final String GET_RICH_BATCH_UTENTI_RIORG = "getRichiesteBatchCessazioneUtentiRiorg";
	public static final String GET_CDR_INFO_BY_CODICE = "getCdrInfoByCodice";
	public static final String GET_EVENTO = "getEvento";
	public static final String CANCELLA_PAU_BY_CF = "cancellaProfiloAttivoUtenteByCf";
	public static final String AGGIORNA_SPO_BY_CF_AND_MOTIVAZIONE = "aggiornaStoricoProfiloByCfAndMotivazione";
	public static final String AGGIORNA_PUV_MOB_BY_CF_AND_AMBITO = "aggiornaProfiliUtentiVisibilitaMobByCfAndAmbito";	
	public static final String GET_AMBITI_BY_CF = "getAmbitiByCf";
	public static final String GET_CDR_BY_CF = "getCodCdrByCf";
	public static final String GET_CDR_BY_CF_TODAY = "getCodCdrByCfToday";
	public static final String INSERT_REGISTRO_RICHIESTA = "inserisciRegistroRichiesta";
	public static final String ANNULLAMENTO_RICHIESTE = "annullamentoRichieste";
	public static final String ARCHIVIA_RICHIESTE = "archiviazioneRichieste";
	public static final String INSERT_PROFILO_RICHIESTA = "inserimentoProfiloRichiesta";
	public static final String GET_PROFILI_BY_CF_AMBITO = "getListaProfiliByCfAmbito";
	public static final String DELETE_RICHIESTE_BATCH = "cancellaRecordRichiesteBatch";
	public static final String AGGIORNA_UTENTE_VISIBILITA = "aggiornaUtenteVisibilita";
	public static final String AGGIORNA_PROFILI_UTENTE_VISIBILITA = "aggiornaProfiliUtenteVisibilita";
	public static final String AGGIORNA_PROFILI_UTENTE_VISIBILITA_2 = "aggiornaProfiliUtenteVisibilita_2";
	public static final String DELETE_UTENTE_DA_TABELLA_RIORG = "cancellaUtenteDaTabellaRiorganizzaizone";
	public static final String INSERT_UTENTE_IN_TABELLA_RIORG = "insertUtenteInTabellaRiorganizzaizone";
	//public static final String COUNT_PROFILI_ECA_0000000000 = "verificaPresenzaProfiloECA_0000000000";
	
	
	
	public static final String UPDATE_REGISTRO_RICHIESTA_UTENTE_ESTERNO = "updateRegistroRichiesteUtenteEsterno";
	public static final String UPDATE_UTENTE = "updateUtente";
	public static final String UPDATE_OPERATORE_ESTERNO = "updateOperatoreEsterno";
	public static final String GET_CDR_PER_CANCELLAZIONI_CAU = "getCodiceCdrPerCancellazioniCau";
	public static final String GET_RICHIESTE_DA_ANN = "getRichiesteDaAnnullare";
	public static final String GET_RICHIESTE_NON_ESEGUITE = "getRichiesteNonEseguite";
	public static final String GET_RICHIESTE_NON_INTEGRATE_GESTORE = "getRichiesteNonIntegrateGestore";
	
	public static final String GET_LISTA_SALVAGUARDIA = "getListaSalvaguardia";
	public static final String GET_ID_RICH_VISIB_RIORG = "getIdRichiestaVisibilitaRiorg";
	public static final String GET_PROFILI_ASSEGNATI_RIORG = "getProfiliAssegnatiRiorg";
	public static final String GET_PROFILI_UFF_PARTENZA_RIORG = "getProfiliUfficioPartenzaRiorganizzazione";
	public static final String GET_LISTA_SALVAGUARDIA_CDR = "getListaSalvaguardiaCdr";
	public static final String INSERTI_RIORG_DIP_TRATTATI = "insertRiorganizzazioneDipendentiTrattati";
	public static final String GET_MAX_DATA_INIZIO_VAL = "getMaxDataInizioVal";

	
	public static final String GET_SEQUENCE_FROM_RICHIESTE_BATCH_DA_ELAB = "getSequenceFromRichiesteBatchDaElab";
	public static final String GET_ID_RICH_RICHIESTE_BATCH_DA_ELAB = "getIdRichFromSequenceIdRichiesta";
	public static final String GET_LIST_PROF_BY_CDR_ROLE_GROUP = "getListaProfiliByCdrRoleGroup";
	public static final String GET_LIST_PROF_BY_CDR_ROLE_GROUP_2 = "getListaProfiliByCdrRoleGroup2";
	public static final String INSERT_PROFILO_COERENZA_DA_AGGIORNARE = "inserisciProfiloCoerenzaDaAggiornare";
	public static final String GET_LIST_CF_PROFILI_DA_AGGIORNARE_FLAG_VIS_NO = "getListCfProfiliCoerenzaDaAggiornareFlagVisNO";
	public static final String GET_PROFILI_DA_AGGIORNARE_FLAG_VIS_NO = "getProfiliDaAggiornareFlagVisNO";
	public static final String GET_COUNT_PROFILI_BY_COD_PROFILO = "getCountProfiliByCodProf";
	public static final String SVUOTA_PROFILI_DA_AGGIORNARE = "svuotaProfiliCoerenzaDaAggiornare";
	public static final String INSERT_SEGN_INCOER_VISIB_BATCH = "inserisciSegnalazioneIncoerenzaVisibilitaBatch";
	public static final String GET_REL_UFFICIO_STRUTTURA = "getRelCdrUfficioStruttura";
	public static final String GET_VERTICE_UFFICIO = "getVerticeUfficio";
	public static final String GET_LISTA_CDR_BY_UFF_STRUTT_STRUTT2LIV = "getListaCdrByUffStruttStrutt2Liv";
	public static final String GET_PROFILI_DA_SEGNALARE_HR_FLAG_VIS_SI = "getProfiliDaSegnalareHRFlagVisSI";
	public static final String GET_PROFILI_DA_SEGNALARE_CEA_FLAG_VIS_SI = "getProfiliDaSegnalareCEAFlagVisSI";
	public static final String GET_LIST_COD_CDR_VIS_PROFILI_DA_AGGIORNARE = "getListCodCdrVisProfiliCoerenzaDaAggiornare";
	public static final String GET_LIST_CF_PROFILI_DA_AGGIORNARE_BY_CDR_VIS = "getProfiliDaAggiornareByCodCdrVis";
	public static final String GET_LIST_INCOERENZE_ABILITAZIONI = "getIncoerenzeAbilitazioni";
	public static final String GET_LIST_INCOERENZE_ABILITAZIONI_NEW = "getIncoerenzeAbilitazioniNew";
	public static final String GET_LIST_INCOERENZE_VISIBILITA = "getIncoerenzeVisibilita";
	
	// batch correzioni incoerenze -->
	public static final String VERIFICA_PROFILI_SIGA = "verificaProfiliSIGA";
	public static final String VERIFICA_PROFILI_NON_SIGA = "verificaProfiliNonSIGA";
	public static final String VERIFICA_PROFILI_NON_SIGA2 = "verificaProfiliNonSIGA2";
	public static final String INSERISCI_PROFILO_ATTIVO_UTENTE_CORREZIONE = "inserisciProfiloAttivoUtenteCorrezione";
	public static final String INSERISCI_STORICO_PROFILI_CORREZIONE = "inserisciStoricoProfiliCorrezione";
	public static final String SET_CORREZIONE_INCOERENZA = "setCorrezioneIncoerenza";
	// <--
	
	public static final String RICHIESTE_BATCH_NUM_TENTATIVI = "richiesteBatchNumTentativi";
	public static final String UPDATE_RICHIESTE_BATCH_TENTATIVI = "updateRichiesteBatchTentativi";
	public static final String GET_CF_RICHIESTA_DA_ELAB = "getCfRichiestaDaElab";
	
	public static final String RESP_TO_ELAB = "responsabilitaDaElaborare";
 	public static final String INSERT_RESPONSABILITA = "insertResponsabilita";
	public static final String CANC_PROFILO  = "responsabilitaDaElaborare";
	public static final String UPDATE_PROFILO  = "updateProfilo";
	public static final String UPDATE_PROFILO_CATALOGO  = "updateProfiloCatalogo";
	public static final String UPDATE_RESPONSABILITA  = "updateResponsabilita";
	public static final String CANC_APP_PRO_ROL_GROUP  = "cancellaAppProfRoleGroup";
	public static final String CANC_AG_CDR_UFF = "cancellaAgCdrUff";
	public static final String INS_PROFILO = "insertProfilo";
	public static final String INS_APP_PROF_ROLE = "insertAppProfRole";
	public static final String INS_AG_CDR_UFF = "insertAGCdrUff";
	public static final String COUNT_PROFILO = "countProfilo";
	public static final String COUNT_PROFILI_RIC = "countProfiliRichiesta";
	public static final String COUNT_APP_PROF_ROLE_RIC = "countAppProfRole";
	public static final String COUNT_AG_CDR_UFF = "countAGCdrUff";
	public static final String COUNT_CDR_UFF = "countCdrUff";
	public static final String GET_CODE_APP_ROLE_A = "getCodeApplAndRoleA";
	public static final String GET_RESPONSABILITA_OA = "getResponsabilitaOA";
	public static final String GET_REL_CDR_UFF_STRUTT = "getRelCdrUffStrutt";
	
	// modifiche richieste
	public static final String IS_RICHIEDENTE_CDR_VERTICE_STRUTTURA = "isRichiedenteVerticeStruttura";
	public static final String GET_AUTORIZZATORE_I_LIV_VER_STR = "getAutorizzatore_I_Liv_VerSTR";
	public static final String GET_AUTORIZZATORE_I_LIV_VER_STR_ITER = "getAutorizzatore_I_Liv_VerSTR_ITER";
	public static final String IS_RESPONSABILE_HR_RICHIESTA = "isResponsabileHrRichiesta";
	
	public static final String GET_LISTA_CDR_DISPONIBILI_CF		= "getListaCDRDisponibiliByCf";

	
	public static final String GET_EVENTI_SISTEMA = "getEventiSistema";
	public static final String GET_COD_APPLICAZIONE = "getCodApplicazione";
	public static final String GET_CDR = "getCdr";
	public static final String INSERISCI_RICHIESTA_VIS_BATCH = "inserisciRichiestaVisBatch";
	public static final String GET_RICHIESTE_BATCH = "getRichiesteBatch";
	public static final String GET_RICHIESTE_BATCH_DISTINCT = "getRichiesteBatchDistinct";

	
	public static final String GET_RICHIESTE_BATCH_TO_RICHIESTE = "getRichiesteBatchToRichieste";

	public static final String GET_RICHIESTE_TO_ELAB = "getRichiesteToElab";
	public static final String GET_ABILITAZIONI_BATCH = "getAbilitazioniBacth";

	public static final String ELIMINA_RICHIESTE_BATCH = "eliminaRichesteBatch";
	public static final String AGGIORNA_RICHIESTE_TO_ELAB = "aggiornaRichBatchToElab";
	public static final String COUNT_RICHIESTE = "countRichieste";
	
	//mio profilo
	public static final String GET_ELENCO_PROFILI_OPERATORE_GROUP_CDR = "getElencoProfiliOperatoreGroupCdr";
	
//	profilo risultante richieste da lavorare
	public static final String GET_ELENCO_PROFILI_OPERATORE_GROUP_CDR_RICHIESTA = "getElencoProfiliOperatoreGroupCdrRichiesta";
	public static final String GET_ELENCO_PROFILI_OPERATORE_GROUP_CDR_DISABILITATI = "getElencoProfiliOperatoreGroupCdrRichiestaDisabilitazione";
	
	
	// BATCH SCADENZE	
	public static final String GET_UTENTI_ESTERNI_SCADUTI = "getUtentiEsterniScaduti";
	public static final String GET_PROFILI_CANCELLATI = "getProfiliCancellati";
	public static final String GET_PROFILI_ATTIVI_UTENTE_BY_COD_PROF = "getProfiliAttiviUtenteByCodProfilo";
	public static final String GET_PROFILI_ATTIVI_UTENTE_BY_CDR = "getProfiliAttiviUtenteByCdr";
	public static final String GET_PROFILI_ATTIVI_UTENTE_GEST_RETE = "getProfiliAttiviUtenteByCdrGestRete";


	public static final String INSERT_SCADENZE_RICHIESTA_BATCH = "inserisciScadenzeRichiestaBatch";
	public static final String UPDATE_ESTERNI_ULT_ELAB = "setEsterniUltimaElab";
	public static final String UPDATE_DATA_ULT_ELAB_PROFILI = "updateDataUltimaElabProfili";
	public static final String UPDATE_DATA_ULT_ELAB_PROFILI_AG = "updateDataUltimaElabProfiliAG";
	public static final String GET_UTENTI_VISIBILITA_SCADUTI = "getUtentiVisibilitaScaduti";
	public static final String UPDATE_VISIBILITA_ULT_ELAB = "setVisibilitaUltimaElab";
	public static final String UPDATE_PROF_VISIBILITA_ULT_ELAB = "setProfVisibilitaUltimaElab";
	public static final String DELETE_PROF_UTENTE_VISIBILITA = "deleteProfUtenteVisibilita";
	public static final String GET_PROFILI_AG = "getProfiliDiAdminGroup";
	public static final String GET_LIST_CDR = "getListCdr";
	public static final String GET_LIST_UTENTI_BY_CDR = "getListUtentiByCdr";
	public static final String GET_PROFILI_AG_SCADUTI = "getProfiliDiAdminGroupCdrUffScaduti";
	public static final String GET_PROFILI_AG_COD_UFF_SCADUTI = "getProfiliDiAdminGroupCdrScaduti";
	public static final String GET_PROFILI_OPERATORI_SCADUTI = "getProfiliOperatoriScaduti";
	public static final String GET_PROFILI_ECA_NON_SCADUTI_BY_CF_AND_CODAPP = "getProfiliEcaNonScadutiByCfAndCodApp";
	public static final String UPDATE_PROFILI_OPERATORE_ULT_ELAB = "setProfiliOperatoreUltimaElab";
	public static final String GET_LIST_PROFILI_BY_CDR = "getListProfiliCdr";
	public static final String GET_PROFILI_ATTIVI_UTENTE_BY_PROF_CDR_CF = "getProfiliAttiviUtenteByCdrProfCf";
	public static final String GET_CDR_FROM_COD_UFF = "getCdrFromCodUff";
	public static final String GET_RICHIESETE_INSERITE = "getRichiesteInseriteByCfIdRichCodProf";
	public static final String GET_RICHIESETE_INSERITE_VIS = "getRichiesteInseriteVisByCfIdRich";
	public static final String GET_RICHIESETE_INSERITE_UT_EST = "getRichiesteInseriteUtEstByCf";
	public static final String GET_PROFILI_UTENTI_VISIBILITA_SCADUTI = "getProfiliUtentiVisibilitaScaduti";
	public static final String GET_REGISTRO_RICH_BATCHSCADENZE = "getRegistroRichiesteBatchScadenze";
	public static final String COUNT_REGISTRO_RICH_BATCHSCADENZE = "countRegistroRichiesteBatchScadenze";
	
	/** Caricamenti Massivi **/
	public static final String AGGIORNA_RICHIESTA_MASSIVA = "aggiornaRichiestaMassiva";
	public static final String GET_RICHIESTA_MASSIVA = "getDatiRichiestaMassiva";
	public static final String AGGIORNA_VISIBIITA_MASSIVA = "aggiornaVisibilitaMassiva";
	public static final String GET_VISIBIITA_MASSIVA = "getDatiVisibilitaMassiva";	
	public static final String GET_ANNI_CARICAMENTO_BY_CF = "getAnniCaricamento";
	public static final String GET_LISTA_CARICAMENTI_UTENTE_PER_ANNO = "getElencoCaricamentiUtentiPerAnno";
	public static final String GET_PROFILI_BY_CODICE_AMBITO_APP = "getProfiliByCodiceAmbitoApp";
	public static final String GET_DETTAGLIO_CARICAMENTO = "getDettaglioCaricamento";
	public static final String GET_SEQUENCE_FROM_RICHIESTE_CARICAMENTO_MASSIVO = "getSequenceFromRichiesteCaricamentoMassivo";
	public static final String INSERISCI_RICHIESTA_CARICAMENTO_MASSIVO = "inserisciRichiestaCaricamentoMassivo";
	public static final String ANNULLA_RICHIESTA_CARICAMENTO_MASSIVO = "annullaRichiestaCaricamentoMassivo";
	public static final String UPDATE_CONTROLLO_RICHIESTA_CARICAMENTO_MASSIVO = "updateControlloRichiestaCaricamentoMassivo";
	public static final String INSERISCI_ALLEGATO_CARICAMENTO_MASSIVO = "inserisciAllegatoCaricamentoMassivo";
	public static final String GET_LISTA_RECORD_CARICAMENTO = "getListaRecordCaricamento";
	public static final String GET_LISTA_RECORD_CARICAMENTO_VIS_FILE = "getListaRecordCaricamentoVis";
	public static final String GET_LIMITI_RECORD_CONTROLLI_MASSIVI = "getLimitiRecordControlliMassivi";
	public static final String GET_LISTA_ALLEGATI_CARICAMENTO = "getListaAllegatiCaricamento";
	public static final String GET_LISTA_ALLEGATI_CARICAMENTO_NO_BLOB = "getListaAllegatiCaricamentoNoBlob";
	public static final String GET_ALLEGATO = "getAllegato";
	public static final String GET_RESP_CDR_SUPERIORE = "getDatiResponsabileCdrSuperiore";
	public static final String GET_RESP_STRUTTURA = "getDatiResponsabileStruttura";
	
	
	
	/** Batch Controlli Caricamenti Massivi**/
	public static final String GET_LIST_CM_CONTROLLI = "getListaCaricamentiPerControlli";
	public static final String UPDATE_STATO_RICHIESTA_CARICAMENTO = "updateStatoRichiestaCaricamento";
	public static final String GET_EVENTO_CM = "getEventoCM";
	public static final String COUNT_CF = "countCf";
	public static final String COUNT_CF_DISALLINEAMENTO = "countCfDisallineamento";
	public static final String COUNT_ROLE_GROUP = "countRG";
	public static final String COUNT_CDR = "countCdr";
	public static final String COUNT_PROFILI_ECA = "countProfiliEca";
	public static final String GET_PROFILI_CM = "getProfiliCM";
	public static final String GET_COD_APP_CM = "getCodiciAppCM";
	public static final String GET_UFFICIO_CM = "getUfficioCM";
	public static final String GET_PROF_DA_PROFATT_CM = "getProfiloDaProfiloAttivoCM";
	public static final String GET_ID_REGISTRO_RICHIESTE = "getIdRegistroRichiestaCM";
	public static final String GET_UTENTE_VISIBILITA_CM = "getUtenteVisibilitaCM";
	public static final String GET_PROFILO_ASSEGNATO_CM = "getProfiloAssegnatoCM";
	public static final String GET_PROFILO_ATTIVO_UTENTE_CM = "getProfiloAttivoUtenteCM";
	public static final String INSERT_FILE_ABILITAZIONE = "inserisciFileAbilitazione";
	public static final String GET_LIST_CM_CONTROLLATI = "getListaCaricamentiControllati";
	public static final String GET_RICHIESTE_BATCH_BY_SEQUENCE = "getListaRichiesteBatchBySequence";
	public static final String GET_CF_RICH_AUTOR = "getCfRichAutor";
	public static final String GET_DATI_RICHIEDENTE = "getDatiRichiedenteCdr";
	public static final String GET_DATI_RICHIEDENTE_BY_CDR_VIS = "getDatiRichiedenteCdrByCdrVis";
	public static final String GET_PROFILI_RICHIESTA_BY_SEQ_ID_CF = "getProfiliRichiestaBySeqIdCf";
	public static final String UPDATE_GENERAZIONE_RICHIESTA_CARICAMENTO_MASSIVO = "updateGenerazioneRichiestaCaricamentoMassivo";
	public static final String DELETE_RICHIESTE_BATCH_CM = "eliminaRichiesteBatch";
	public static final String GET_EMAIL_AMMINISTR_CARICAMENTO = "getEmailAmminCaricamento";
	public static final String GET_LISTA_RECORD_SCARTATI = "getListaRecordScartati";
	public static final String GET_PROF_ECA_PADRE_IN_PAU = "getProfiloEcaPadreInPau";
	public static final String COUNT_PROF_ECA_FIGLI_IN_CAU = "countProfiliAttiviUtenteEca";
	public static final String GET_COD_PROF_DA_PAU_CAU = "getCodProfiloDaProfiliAttiviUtenteEca";
	public static final String GET_REVOCHE_UTENTE = "getRevocheUtente";
	
	
	/** Batch Controlli Gnerazione Richieste **/
	public static final String GET_LIST_GENERAZ_RICH = "getListaDatiGenerazioneRichieste";
	public static final String GET_LIST_RAGG_RICH_OPER = "getRaggruppamentiRichiesteOperatore";
	public static final String GET_MIN_SEQ_RAGGRUPPAMENTO = "getMinSequenceRaggruppamento";
	public static final String UPDATE_SEQUENCE_RICHIESTE_BATCH = "aggiornaSequenceRichiesteBatch";
	public static final String GET_LIST_GENERAZ_RICH_VIS = "getListaDatiGenRichScadVis";
	public static final String GET_RESPONSABILE_CDR_SUPERIORE = "getResponsabileCdrSuperiore";
	public static final String GET_RESPONSABILE_STRUTTURA = "getResponsabileStruttura";
	public static final String GET_ID_ITER_FROM_ID_RICHIESTA = "getIdIterFromIdRichiesta";
	
	/** Assegnazione gestori operatori **/
	public static final String GET_LIST_APPLICAZIONI_ASSEGNAZIONI = "getListaApplAssegnazioniOperatori";
	public static final String GET_LIST_GESTORI_OPERATORI = "getListaGestoriOperatori";
	public static final String CANCELLA_GESTORE_OPERATORI_GRUPPO = "cancellaGestoreOperatoriGruppo";
	public static final String CANCELLA_GESTORE_OPERATORI = "cancellaGestoreOperatori";
	public static final String INSERISCI_PRESA_IN_CARICO_GESTORE_OPERATORI = "inserisciPresaInCaricoGestoreOperatori";
	public static final String CANCELLA_PRESA_IN_CARICO_GESTORE_OPERATORI = "cancellaPresaInCaricoGestoreOperatori";
	public static final String UPDATE_PRESA_IN_CARICO_GESTORE_OPERATORI = "aggiornaPresaInCaricoGestoreOperatori";
	public static final String INSERT_GESTORE_OPERATORI_GRUPPO = "insertGestoreOperatori";
	
	public static final String INSERT_GESTORE_OPERATORI = "insertGestoreOperatori";
	public static final String GET_LIST_GESTORI_OPERATORI_BY_COD_APP = "getListaGestoriOperatoriByCodStr";
	public static final String GET_LIST_GESTORI_OPERATORI_BY_COD_CDR = "getListaGestoriOperatoriByCodCdr";
	public static final String GET_LIST_TOTALE_APPLICAZIONI = "getListaApplicazioni";
	public static final String GET_LIST_APPLICAZIONI_AL = "getListaApplicazioniAL";
	public static final String GET_NUMERO_GESTORI = "getNumeroGestori";
	public static final String GET_ELENCO_UTENTI_NUOVO_GESTORE = "getListaUtentiNuovoGestore";
	public static final String GET_ELENCO_CDR_BY_STR = "getCdrListByStr";

	/** GET_ORIGINE_ABILITAZIONE **/
	public static final String GET_DATI_ORIGINE_ABILITAZIONE 	= "getDatiOrigineRichiesta";
	
	//gestione avvio batch
	public static final String GET_STATO_BATCH = "getStatoBatch";
	public static final String GET_EMAIL_BATCH = "getEmailBatch";
	public static final String AGGIORNA_STATO_BATCH = "aggiornaStatoBatch";
	public static final String GET_TIPO_CDR_HR = "getTipoCdrHR";
	
	public static final String UPDATE_STRUTTURA_CDR_HR = "updateStrutturaXTipoCdrHR";
	public static final String UPDATE_UFFICIO_CDR_HR = "updateUfficioXTipoCdrHR";

	/** Batch Operatori HR **/
	public static final String GET_SETTINGS_BATCH = "selectSettingsBatch";
	public static final String UPDATE_DATA_FINE_EMAIL_RUOLI = "updateDataFineEmailRuoli";
	public static final String UPDATE_DATA_INIZIO_EMAIL_RUOLI = "updateDataInizioEmailRuoli";
	public static final String UPDATE_DATA_FINE_RESPONSABILI_CDR = "updateDataFineResponsabiliCdr";
	public static final String GET_DATI_CDR = "selectDatiCdr";
	public static final String GET_LISTA_OPER_RICH_AUTOR_ORA = "listaOperRichAutorORA";
	public static final String GET_LISTA_RICH_ORA = "listaRichORA";
	public static final String GET_LISTA_AUTOR_ORA = "listaAutorORA";
	public static final String GET_LISTA_AUTOR_CDR_NO_VERT_ORA = "listaAutorCdrNoVertORA";
	public static final String GET_LISTA_OPER_ASSOCIATI_RICH_AUTOR = "listaOperatoriAssociatiRichAutor";
	public static final String GET_DATI_RICHIEDENTE_ORA = "getDatiRichiedenteOra";
	public static final String GET_DATI_AUTORIZZATORE_ORA = "getDatiAutorizzatoreOra";
	public static final String GET_DATI_RICHIEDENTE_DEST_MAIL = "getDatiRichDestEmail";
	public static final String GET_DATI_AUTORIZZATORE_DEST_MAIL = "getDatiAutorDestEmail";
	public static final String GET_CDR_PADRE_GERARCHIA = "getCdrPadreGerarchia";
	public static final String GET_DATI_AUTOR_VERTICE_STRUT_DEST_MAIL = "getDatiAutorVerticeStrutDestEmail";
	public static final String GET_DATI_AUTOR_VERTICE_STRUT_DEST_MAIL_2 = "getDatiAutorVerticeStrutDestEmail2";
	public static final String GET_CDR_VERTICE_STRUTTURA = "getCdrVerticeStruttura";
	public static final String GET_EMAIL_AMM_LOCALE = "getIndirizzoEmailAmmLocale";
	public static final String GET_EMAIL_AMM_CENTRALE = "getIndirizzoEmailAmmCentrale";
	public static final String GET_LIST_RESPONSABILI_HR = "getResponsabiliHr";
	public static final String GET_LIST_RICHIEDENTI_ORA_PER_MAIL = "listaRichORAPerMail";
	public static final String GET_LISTA_RIM_DELEGHE_BEAN = "listRimozioneDelegheBean";
	
	public static final String IS_VERTICE_STRUTT= "isCdRVerticeStruttura";
	public static final String GET_CODICE_CDR_STRUTT = "getCodiceCdRByCodiceStruttura";
	public static final String GET_VERTICE_STRUTT_AMM_LOC = "getVerticeStrutturaAmmLoc";
	public static final String GET_VERTICE_STRUTTURA_FROM_CDR = "getVerticeStrutturaFromCdr";
	
	public static final String GET_STATI_RICHIESTA = "getStatiRichiesta";
	
	public static final String GET_TIPO_ABILITAZIONE = "getTipoAbilitazione";
	
	public static final String GET_ELENCO_PER_ALBERO_STRUTTURE = "elenco_per _albero_strutture";
	
	public static final String GET_RICHIESTE_ABILITAZIONI_WEB = "getRichiesteAbilitazioniWeb";
	public static final String INSERT_RICHIESTA_ABILITAZIONI_CDR = "inserisciRichiestaAbilitazioniCdR";
	public static final String DELETE_RICHIESTA_ABILITAZIONI_WEB = "cancellaRichestaAbilitazioniWeb";
	public static final String GET_ELENCO_SECONDI_LIVELLI = "getElencoSecondiLivelli";
	public static final String INSERT_FILTRI_ABILITAZIONI_VIS = "inserisciFiltriRicerca";
	
	public static final String GET_ELENCO_PROFILO_DA_CDR_LST = "getElencoProfiloProfilazioneUfficioDaCdRList";
	public static final String GET_ELENCO_PROFILO_II_LIV = "getElencoProfiloProfilazioneUfficioIILiv";
	public static final String GET_NUM_PROFILI_MAX_AMMISSIBILE = "getNumeroProfiliMaxAmmissibile";
	public static final String GET_VALORE_COSTANTE = "getValoreCostante";
	public static final String GET_ELENCO_PROFILI_UFF_DA_CDR_LST = "getElencoProfiliUfficioDaCdRList";
	public static final String GET_ELENCO_PROFILI_II_LIV_DA_CDR_LST = "getElencoProfiliUfficioIILiv";
	public static final String GET_RICHIESTE_ABILITAZIONI_BATCH = "getRichiesteAbilitazioniBatch";
	public static final String GET_ELENCO_FILTRI_CDR = "getFiltriCdR";
	public static final String GET_ELENCO_FILTRI_RG = "getFiltriRG";
	public static final String GET_ELENCO_FILTRI_PROFILI = "getFiltriProfili";
	public static final String GET_ELENCO_PROFILI_BATCH = "getElencoProfiliBatch";
	public static final String GET_ELENCO_PROFILI_BATCH_II_LIV = "getElencoProfiliBatchIILiv";
	
	public static final String ELABORA_RICHIESTA_BATCH = "elaboraRichestaAbilitazioniBatch";
	public static final String INSERT_FILE_BATCH = "insertFileRichestaAbilitazioniBatch";
	public static final String ELABORAZIONE_FALLITA_BATCH = "elaborazioneFallitaBatch";
	public static final String GET_RG_DESC = "getDescrizioneRG";
	public static final String GET_STRUTTURA_DESC = "getDescrizioneStrutturaFromCodiceStruttura";
	public static final String GET_ABILITAZIONE_DESC = "getDescrizioneAbilitazione";
	public static final String GET_FILE_BATCH_ABILITAZIONI = "getFileBatchAbilitazioni";
	
	
	
	
	
	/** Aggiornamento Massivo Richieste Visibilita  **/
	public static final String GET_SEQUENCE_FROM_AGGIORNAMENTO_MASSIVO_VISIB = "getSequenceFromAggiornamentoMassivoVisibilita";
	public static final String INSERISCI_RICHIESTA_AGGIORN_MASS_VISIB = "inserisciRichiestaAggiornamentoMassivoVisibilita";
	public static final String GET_LISTA_CARICAMENTI_AGGIORNAM_VISIB_UTENTE = "getElencoCaricamentiAggiornamentiVisibilitaUtenti";
	public static final String GET_LIMITI_RECORD_CONTROLLI_MASSIVI_PER_EVENTO = "getLimitiRecordControlliMassiviPerEvento";
	public static final String GET_DETTAGLIO_CARICAMENTO_AGG_VIS = "getDettaglioCaricamentoAggVis";
	public static final String ANNULLA_RICHIESTA_CARICAMENTO_MASSIVO_AGG_VIS = "annullaRichiestaCaricamentoMassivoAggVis";
	public static final String UPDATE_STATO_RICHIESTA_CARICAMENTO_AGG_VIS = "updateStatoRichiestaCaricamentoAggVis";
	public static final String GET_LIST_CM_AGG_VIS = "getListaCaricamentiAggVis";
	public static final String GET_DATI_ROLE_GROUP_BY_ID = "getDatiRoleGroupById";
	public static final String GET_DATI_PROFILO_BY_ID = "getDatiProfiloById";
	public static final String COUNT_ASS_PROF_RG = "countAssocProfRoleGroup";
	public static final String GET_DESCR_CDR_BY_CODICE = "getDescrizioneCdrByCodice";
	public static final String GET_RICH_VIS_BY_CF_CDR = "getDatiRichiestaVisibByCfCdr";
	public static final String COUNT_PROFILI_UTENTI_IN_VISIBILITA = "countProfiliUtentiInVisibilita";
	public static final String GET_PROFILI_UTENTI_IN_VISIBILITA = "getProfiliUtentiInVisibilita";
	public static final String GET_PROFILI_UTENTI_IN_VISIBILITA_RIM = "getProfiliUtentiInVisibilitaRimozione";
	public static final String CONTROLLA_PRESENZA_RICHIESTE_ABILITAZIONE = "controllaPresenzaRichiesteAbilitazione";
	public static final String INSERT_PROFILO_UTENTE_IN_VISIBILITA = "inserisciProfiloUtenteInVisibilita";
	public static final String DELETE_PROFILO_UTENTE_IN_VISIBILITA = "deleteProfiloUtenteInVisibilita";
	public static final String GET_ID_RICHIESTE_ABILITAZIONE = "getIdRichiesteAbilitazione";
	public static final String COUNT_PROFILI_FIGLI_ASSEGNATI_AL_CF_PER_RICH_VISIB = "countProfiliFigliAssegnatiAlCfPerRichVisib";
	public static final String INSERT_PROFILI_VISIBILITA_TRACCIAMENTO = "inserisciProfiliVisibilitaTracciamento";
	public static final String GET_PROFILI_ECA_FIGLI_VISIBILITA = "getProfiliEcaFigliVisibilita";
	public static final String UPDATE_CONTROLLO_RICHIESTA_CARICAMENTO_MASSIVO_VISIB = "updateControlloRichiestaCaricamentoMassivoVisib";
	public static final String GET_EMAIL_AMMINISTR_CARICAMENTO_AGG_VIS = "getEmailAmminCaricamentoAggVis";
	public static final String INSERT_FILE_VISIBILITA = "inserisciFileVisibilita";
	public static final String GET_LISTA_RECORD_CARICAMENTO_AGG_VIS = "getListaRecordCaricamentoAggVis";
	public static final String COUNT_VISIBILITA_PER_PROFILI = "countVisibilitaPerProfili";
	public static final String GET_LISTA_RECORD_SCARTATI_AGG_VIS = "getListaRecordScartatiAggVis";
	public static final String GET_PROFILI_VISIB_BY_CODICE_AMBITO_APP = "getProfiliDisponibiliVisibByAmbitoApp";
	public static final String IS_AMMINISTRATORE_CENTRALE = "isAmministratoreCentrale";
	public static final String IS_AMMINISTRATORE_CENTRALE_2 = "isAmministratoreCentrale2";
	public static final String IS_AMMINISTRATORE_CENTRALE_3 = "isAmministratoreCentrale3";
	public static final String INSERISCI_ALLEGATO_AGGIORNAMENTO_MASSIVO_VISIB = "inserisciAllegatiAggiornamentoMassivoVisibilita";
	public static final String GET_LISTA_ALLEGATI_CARICAMENTO_AGG_VIS_NO_BLOB = "getListaAllegatiCaricamentoAggVisNoBlob";
	public static final String GET_ALLEGATO_AGG_VIS = "getAllegatoAggVis";
	public static final String GET_APPL_PROF_AMM_CENTR_APPL = "getApplicazioniProfiliAmmCenAppl";
	public static final String GET_PROFILI_VISIB_AMM_CENTR_APP = "getProfiliDisponibiliVisibAmmCentrApp";
	public static final String GET_ELENCO_REGIONI = "getElencoRegioni";
	public static final String GET_ELENCO_PROVINCIE = "getElencoProvincie";
	public static final String GET_ELENCO_TIPOLOGIE_UFFICIO = "getElencoTipologieUfficio";
	public static final String GET_VERTICE_UFFICIO_FROM_CDR = "getVerticeUfficioFromCdr";
	
/*Inizio Batch abilitazioni operatore per CdR*/
	public static final String DELETE_ABILITAZIONI_OPERATORI_PER_CDR = "svuotaTabellaAbilitazioniOperatorePerCdR";
	public static final String GET_ABILITAZIONI_OPERATORI_CDR_ASS = "getAbilitazioniOperatoriCdRAssegnazione";
	public static final String GET_ABILITAZIONI_OPERATORI_CDR_VIS = "getAbilitazioniOperatoriCdRVisibilita";
	public static final String INSERT_ABILITAZIONI_OPERATORI_PER_CDR = "insertBatchAbilitazioniOperatoriPerCdR";
	public static final String GET_ELENCO_AMBITO_WEB = "getElencoAmbito";
	public static final String GET_ELENCO_RG_WEB = "getElencoRGWeb";
	public static final String GET_ELENCO_PROFILI_WEB = "getElencoProfiliWeb";
	public static final String GET_STRUTT_AL = "getStruttureAL";
	public static final String GET_ELENCO_PROFILI_EXPORT = "getProfiliExport";
	public static final String GET_ELENCO_PROFILI_EXPORT_OP = "getProfiliExportOp";
	/*Fine Batch abilitazioni operatore per CdR*/
	
	public static final String GET_ELENCO_STRUTT_DA_II_LIV = "getElencoStruttureDaIILiv";
	public static final String GET_ELENCO_CDR_DA_STRUTT = "getElencoCdRDaStrutt";
	public static final String GET_ELENCO_OPERATORI_IN_VISIBILITA_3 = "getElencoOperatoriInVisibilitaAggornamento";
	
	public static final String SET_FLAG_ELABORAZIONE_ABILITAZIONI_CDR = "setFlagElaborazioneAbilitazioniCdR";
	public static final String SET_FLAG_ELABORAZIONE_ABILITAZIONI_OPER = "setFlagElaborazioneAbilitazioniOperatori";
	
	public static final String UPDATE_BATCH_INFO = "updateBatchInfo";
	public static final String SELECT_BATCH_INFO = "selectBatchInfo";
	
	public static final String GET_EMAIL_ASSISTENZA = "getEmailAssistenza";
	
	public static final String GET_TIPOLOGIA_UTENTE_ESTERNO = "getTipologiaUtenteEsterno";
	public static final String IS_ELABORABILE_SIGA3 = "isElaborabileSIGA3";

	public static final String UPDATE_ELAB_RICHIESTA_BATCH = "updateElaborazioneRichiestaBatch";
	public static final String UPDATE_ELAB_RICHIESTE_BATCH = "updateElaborazioneRichiesteBatch";
	
	
	public static final String PROFILI_NON_PROFILI_AG_Q1 = "getProfiliNonPrevistiAG_Query1";
	public static final String PROFILI_NON_PROFILI_AG_Q2 = "getProfiliNonPrevistiAG_Query2";
	public static final String PROFILI_NON_PROFILI_AG_Q3 = "getProfiliNonPrevistiAG_Query3";
	
	public static final String VERIFICA_CDR_PADRE_UFFICIO = "verificaCdRPadreUfficio";
	
//	-------------batch scadenza deleghe nuovo inizio---------
	public static final String GET_DELEGHE_IN_SCADENZA = "getDelegheInScadenza";
	public static final String GET_DELEGHE_SCADUTE_NON_CHIUSE = "getDelegheScaduteNonChiuse";
	public static final String UPDATE_EMAIL_INVIATA_DELEGA_IN_SCADENZA = "updateEmailInviataDelegaInScadenza";
	public static final String AGGIORNA_DELEGA = "aggiornaDelega";
	public static final String COUNT_ALTRE_DELEGHE = "countAltreDeleghe";
	public static final String GET_COSTANTE_SIGA = "getCostanteSiga";
	public static final String SET_DATA_ELABORAZIONE_BATCH_SCADENZA_DELEGHE = "setDataElaborazioneBatch";
	public static final String GET_OPERATORE = "getDatiOperatore";
	
//	-------------batch scadenza deleghe nuovo fine-----------
	
	public static final String GET_ELENCO_CATALOGO_FROM_PROFILO = "getElencoCatalogoFromProfilo";
	public static final String GET_PROFILO_INFO = "getProfiloInfo";
	public static final String GET_PROFILO_INFO2 = "getProfiloInfo2";
	public static final String GET_RG_APPLICAZIONI_CATALOGO = "getElencoRGApplicazioneUtente";
	public static final String GET_RG_APPLICAZIONI_CATALOGO_AMM_CENTR_APPL = "getElencoRGApplicazioneUtenteAmmCentrAppl";
	public static final String GET_ABILITAZIONI_CATALOGO = "getElencoAbilitazioniCatalogo";
	public static final String GET_ABILITAZIONI_CATALOGO_2 = "getElencoAbilitazioniCatalogo2";
	public static final String GET_ABILITAZIONI_CATALOGO_3 = "getElencoAbilitazioniCatalogo3";
	public static final String GET_ROLE_GROUP = "getRoleGroup";
	public static final String GET_ABILITAZIONE = "getAbilitazione";
	public static final String GET_ABILITAZIONE2 = "getAbilitazione2";
	public static final String GET_ESTESA = "getEstesa";
	public static final String GET_ELIMINABILE = "getEliminabile";
	public static final String GET_FUNZIONI_CATALOGO = "getElencoFunzioneBean";
	public static final String GET_APP_UTENTE_FROM_ROLE_GROUP = "getElencoAppUtenteFromRoleGroup";
	public static final String GET_RAGGR_FUNZ_FROM_APP = "getElencoRaggrFunzFromApp";
	public static final String GET_ABILITAZIONI_CATALOGO_EXPORT = "getElencoAbilitazioniCatalogoExport";
	public static final String GET_APPLICAZIONI_CATALOGO_ALL_EXPORT = "getElencoApplicazioniCatalogoAllExport";
	public static final String UPDATE_APP_UTENTE_ABILITAZIONE = "updateAppUtenteAbilitazione";
	public static final String UPDATE_NOTA_ABILITAZIONE = "updateNotaAbilitazione";
	public static final String CANCELLA_FUNZIONE = "cancellaFunzione";
	public static final String MODIFICA_FUNZIONE = "modificaFunzione";
	public static final String INSERISCI_FUNZIONE = "inserisciFunzione";
	public static final String GET_ID_FUNZIONE = "getIdFunzione";
	public static final String GET_ELENCO_FUNZIONI_CATALOGO = "getElencoFunzioniCatalogo";
	public static final String GET_ELENCO_FUNZIONI_CATALOGO_EXPORT = "getElencoFunzioniCatalogoExport";
	public static final String COUNT_RG_NO_FUNZIONI = "countRoleGroupNoFunzioni";
	public static final String COUNT_RG_MODIFICATI = "countRoleGroupModificati";
	public static final String CANCELLA_RAGGR_FUNZ_PROFILO = "cancellaRaggruppamentoFunzionaleProfilo";
	public static final String UPDATE_NOTA_FLAG_ROLE_GROUP = "updateNotaFlagRoleGroup";
	public static final String UPDATE_FLAGS_NOTA_ALLEGATO_PROFILO ="updateNotaAllegatoProfilo";
	public static final String INSERISCI_RAGGR_FUNZ_PROFILO = "inserisciRaggruppamentoFunzionaleProfilo";
	public static final String COUNT_ABILITAZIONE_APP_UTENTE = "countAbilitazioneAppUtente";
	public static final String GET_APPLICAZIONI_CATALOGO = "getElencoApplicazioniCatalogo";
	public static final String GET_ELENCO_PROFILO_AC_ABILITAZIONI = "getElencoProfilo_AC_abilitazioni";
	public static final String GET_ID_ITER = "getIdIter";
	public static final String GET_GERARCHIA_CDR = "getGerarchiaCdR";
	public static final String GET_ROLE_GROUP_DESC_CONCAT= "getRoleGroupDescConcat";
	public static final String GET_ROLE_GROUP_BY_COD_RG= "getRoleGroupByCodRG";
	public static final String GET_PROFILI_BY_COD_PROFILO="getProfiliByCodProfilo";
	public static final String COUNT_RICHIESTE_IN_CORSO= "countRichiesteInCorso";
	public static final String GET_ELENCO_TIPOLOGIA_OPE= "getElencoTipologieOpEsterno";
	public static final String GET_ELENCO_ABILITAZIONI_TIPOLOGIA= "getElencoAilitazioniTipologia";
	public static final String GET_ELENCO_ABILITAZIONI_AGENZIA= "getElencoAbilitazioniAgenzia";
	public static final String GET_ELENCO_ABILITAZIONI_TIPOLOGIA_AGENZIA= "getElencoAilitazioniTipologiaAgenzia";
	public static final String AGGIUNGI_ABILITAZIONE_TIPOLOGIA= "aggiungiAbilitazioneTipologia";
	public static final String RIMUOVI_ABILITAZIONE_TIPOLOGIA= "rimuoviAbilitazioneTipologia";
	public static final String ELIMINAZIONE_LOGICA_TIPOLOGIA= "eliminazioneLogicaTipologia";
	public static final String ELIMINA_TIPOLOGIA= "eliminaTipologia";
	public static final String ELIMINA_AUDIT_TIPOLOGIA= "eliminaAuditTipologia";
	public static final String GET_ID_TIPOLOGIA= "getIdTipologia";
	public static final String MODIFICA_DATI_TIPOLOGIA= "modificaDatiTipologia";
	public static final String COUNT_ABILITAZIONI_ATTIVE= "countAbilitazioniAttive";
	public static final String COUNT_OPERATORI_ESTERNI= "countOperatoriEsterni";
	
	
//	----------------RIEMPIMENTO CATALOGO
	public static final String GET_DESCRIZIONE_APPLICAZIONE_CATALOGO= "getDescrizioneApplicazioneCatalogo";
	public static final String FILL_APPLICAZIONE_CATALOGO= "fillApplicazioneCatalogo";
	public static final String FILL_FUNZIONE_CATALOGO= "fillFunzioneCatalogo";
	public static final String FILL_RAGGR_FUNZ_CATALOGO= "fillRaggruppamentoFunzionale";
	public static final String FILL_APP_RF_RG_CATALOGO= "fillRelazioneApplRaggruppamentoFunzRoleGroup";
	public static final String FILL_APP_RF_PROFILO_CATALOGO= "fillRelazioneApplRaggruppamentoFunzProfilo";
	
	public static final String COUNT_APPLICAZIONE_CATALOGO= "countApplicazioneCatalogo";
	public static final String COUNT_RAGGR_FUNZ_CATALOGO= "countRaggruppamentoFunzionale";
	public static final String COUNT_APP_RF_RG_CATALOGO= "countRelazioneApplRaggruppamentoFunzRoleGroup";
	public static final String COUNT_APP_RF_PROFILO_CATALOGO= "countRelazioneApplRaggruppamentoFunzProfilo";
	
	public static final String DEL_APPLICAZIONE_CATALOGO= "deleteApplicazioneCatalogo";
	public static final String DEL_FUNZIONE_CATALOGO= "deleteFunzioneCatalogo";
	public static final String DEL_RAGGR_FUNZ_CATALOGO= "deleteRaggruppamentoFunzionale";
	public static final String DEL_APP_RF_RG_CATALOGO= "deleteRelazioneApplRaggruppamentoFunzRoleGroup";
	public static final String DEL_APP_RF_PROFILO_CATALOGO= "deleteRelazioneApplRaggruppamentoFunzProfilo";
	
	public static final String GET_UFF_PRIMARIO_CAT_TEST= "getUfficioPrimarioCATTest";
	public static final String GET_VISIBILITA_CAMBIO_UFFFICIO= "getVisibilitaCambioUfficio";
	
	
	// ---------------- BATCH ADEGUAMENTO UMBRIA
	public static final String CONTA_PROFILI_IN_VISIBILITA = "contaProfiliInVisibilita";
//	public static final String AGGIORNA_PROFILI_RICHIESTA_UMBRIA_NON_ATTIVI = "aggiornaProfiliRichiestaUmbriaNonAttivi";
//	public static final String AGGIORNA_STORICO_PROFILI_OPERATORE_UMBRIA_NON_ATTIVI = "aggiornaStoricoProfiliOperatoreUmbriaNonAttivi";
	
	public static final String GET_UFFICI_CAT_PROFILI_ASSEGNATI = "getUfficiCATProfiliAssegnati";
	public static final String GET_UFFICI_CAT_TOTALI = "getUfficiCATTotali";
	
	public static final String GET_LISTA_DESC_LINEE_CAT = "getListaDescrizioniLineaCAT";
	
	/*gestione iter abilitazioni*/
	public static final String GET_ITER_INFO = "getIterInfo";
	public static final String GET_ELENCO_ITER = "getElencoIter";
	public static final String GET_ABILITAZIONI_ITER = "getAbilitazioniIter";
	public static final String GET_RUOLI_AUTORIZZATIVI = "getRuoliAutorizzativi";
	public static final String GET_ELENCO_ABILITAZIONI_NON_ASSOCIATE = "getElencoAbilitazioniNonAssociate";
	public static final String GET_TIPO_UFF_ABILITAZIONE = "getTipoUfficioPerAbilitazione";
	public static final String INSERISCI_NUOVO_ITER = "inserisciIter";
	public static final String INSERISCI_STRUTTURA_ITER = "inserisciStrutturaIter";
	public static final String AGGIUNGI_ABILITAZIONE_ITER = "aggiungiAbilitazioneIter";
	public static final String GET_ID_ITER_SEQ = "getIdIterFromSeq";
	public static final String GET_ID_AUDIT = "getIdAudit";
	public static final String INSERISCI_AUDIT_SENZA_ID = "inserisciAuditSenzaId";
	public static final String GET_ELENCO_ABILITAZIONI_AGG = "getAbilitazioniIterAggiornamento";
	public static final String RIMUOVI_ABILITAZIONE_ITER = "rimuoviAbilitazioneIter";
	public static final String MODIFICA_NOTA_ITER = "modificaNotaIter";
	public static final String MODIFICA_DESCRIZIONE_ITER = "modificaDescrizioneIter";
	public static final String GET_ITER_STANDARD = "getIterStandard";
	public static final String GET_UFFICI_SOVRAORD = "getTipiUfficioSovraordinati";
	public static final String ELIMINA_STRUTTURA_ITER = "eliminaStrutturaIter";
	public static final String IS_RICHIESTE_IN_CORSO_ITER = "isRichiesteInCorsoIter";
	public static final String ELIMINA_ITER = "eliminaIter";
	public static final String UPDATE_COSTANTE_SIGA = "updateCostanteSiga";
	public static final String GET_RUOLI_AUTORIZZATIVI_EXPORT = "getRuoliAutorizzativiExport";
	public static final String GET_TIPO_UFF_PERCORSO_ABILITAZIONE = "getTipoUfficioPercorsoPerAbilitazione";
	public static final String COUNT_ABILITAZIONI_NON_AC_APP = "countAbilitazioniNonACApplicativo";
	/*gestione iter abilitazioni fine*/
	public static final String GET_INFORMAZIONI_CDR = "getInformazioniCdR";
	
	public static final String GET_GESTORE_ABILITAZIONI			= "getGestoreAbilitazioni";
	public static final String GET_CDR_UTENTE_IN_VISIBILITA_RICHIESTE = "getCDRUtenteInVisibilitaRichieste";

	
	public static final String GET_ELENCO_ATTIVITA			= "getElencoAttivita";
	public static final String GET_ELENCO_AUDIT     		= "getElencoAttivitaAudit";
	public static final String GET_OPER_COMPETENZA     		= "getOperatoriDiCompetenza";
	public static final String GET_AGG_VIS     				= "getAggiornamentoVisibilita";
	public static final String GET_DELEGHE_AMMINISTRATORE   = "getDelegheAmministratore";
	public static final String GET_ASS_PUNTUALE   			= "getAssPuntuale";
	public static final String GET_RUOLI_CDR	   			= "getRuoliCdR";
	public static final String GET_GESTORI_AGGIORNATI	   	= "getGestoriAggiornati";
	public static final String SELECT_ID_RICHIESTA_FROM_AUDIT	   	= "selectIdRichiestaFromIdAudit";
	public static final String GET_ITER_FROM_AUDIT		   	= "getIterByIdAudit";
	public static final String GET_PROFILI_ITER		   		= "getProfiliIter";
	public static final String UPDATE_ID_AUDIT_RICHIESTA	   		= "updateIdAuditRichiesta";
	public static final String UPDATE_ID_AUDIT_OE	   		= "updateIdAuditOpEsterno";
	public static final String UPDATE_ID_AUDIT_OE_TIP	   		= "updateIdAuditOpEsternoTipologia";
	public static final String GET_DETT_OE	   		= "getDettaglioOperatoreEsterno";
	public static final String GET_ELENCO_RECORD_CARICAMENTI	   		= "getElencoRecordCaricamentoMassivo";
	public static final String GET_ELENCO_ATTIVITA_OPERATORI             = "getElencoAttivitaOperatori";

	public static final String IS_FILE_DISPONIBILE	   		= "isFileDisponibile";

	public static final String GET_TIPO_UFFICIO_MAPPATURA = "getTipoUfficioMappatura";
	public static final String GET_ITER_ASSOCIATO = "getIterAssociato";
	public static final String VERIFICA_DESCRIZIONE_TIPOLOGIA = "isDescrizioneTipoOEPresente";
	public static final String VERIFICA_DESCRIZIONE_ITER = "isDescrizioneIterPresente";
	public static final String GET_ABILITAZIONI_FROM_RG = "getAbilitazioniFromRg";
	
	public static final String GET_ADMIN_CAU = "getAdminCAU";
	public static final String GET_ADMIN_CAU_BY_GROUP_NAME = "getAdminCauByGroupName";
	public static final String VERIFICA_ADMIN_1 = "verificaAmministratore_1";
	public static final String VERIFICA_ADMIN_2 = "verificaAmministratore_2";
	public static final String VERIFICA_TITOLARI_AND_DELEGATI = "verificaTitolariAndDelegati";
	public static final String UPDATE_DATA_ELABORAZIONE_BATCH = "setDataElaborazioneBatch";
	public static final String LISTA_STORICO_PROFILI_OPE = "listaStoricoProfiliOperatore";
	public static final String AGGIORNA_REVOCA = "aggiornaRevoca";
	public static final String AGGIORNA_FLAG_REVOCA = "aggiornaFlagRevoca";
	public static final String INSERISCI_REVOCA = "inserisciRevoca";
	
	public static final String GET_ELENCO_OPERATORI_STORICO = "getOperatoriStoricoAbilitazioni";
	public static final String GET_CF_RESP = "getResponsabileCdR";
	public static final String UPDATE_OWNER_EJBTIMER	   		= "updateOwnerEjbTimer";
	
	/** Caricamenti Massivi Richieste Visibilita **/
	public static final String LISTA_CDR_AGENZIA = "listaCdrAgenzia";
	public static final String GET_SEQUENCE_FROM_RICHIESTE_CARICAMENTO_MASSIVO_VISIBILITA = "getSequenceFromCaricamentoMassivoVisibilita";
	public static final String INSERISCI_RICHIESTA_CARICAMENTO_MASSIVO_VISIBILITA = "inserisciRichiestaCaricamentoMassivoVisibilita";
	public static final String INSERISCI_ALLEGATO_CARICAMENTO_MASSIVO_VISIBILITA = "inserisciAllegatoCaricamentoMassivoVisibilita";
	public static final String INSERISCI_ALLEGATO_CARICAMENTO_MASSIVO_MAPPATURA = "inserisciAllegatoCaricamentoMassivoMappature";
	public static final String GET_LISTA_CARICAMENTI_MASSIVI_VISIB_UTENTE = "getElencoCaricamentiMassiviVisibilitaUtenti";
	public static final String GET_LISTA_CARICAMENTI_MASSIVI = "getElencoCarMass";
	public static final String AGGIORNA_CARICAMENTO_MASSIVO = "modificaConfigCaricamentiMassivi";
	public static final String GET_DETTAGLIO_CARICAMENTO_RICH_VIS = "getDettaglioCaricamentoRichVis";
	public static final String ANNULLA_RICHIESTA_CARICAMENTO_MASSIVO_RICH_VIS = "annullaRichiestaCaricamentoMassivoRichVis";
	public static final String GET_LISTA_ALLEGATI_CARICAMENTO_RICH_VIS = "getListaAllegatiCaricamentoRichVis";
	public static final String GET_LISTA_ALLEGATI_CARICAMENTO_RICH_VIS_NO_BLOB = "getListaAllegatiCaricamentoRichVisNoBlob";
	public static final String GET_ALLEGATO_RICH_VIS = "getAllegatoRichVis";
	
	
	
	
	
	/** Batch Controlli Caricamenti Massivi Richieste Visibilita**/
	public static final String GET_LIST_CM_VIS_CONTROLLI = "getListaCaricamentiVisibilitaPerControlli";
	public static final String GET_SEQUENCE_FROM_RICHIESTE_VISIBILITA_BATCH = "getSequenceFromRichiesteVisibilitaBatch";
	public static final String GET_CDR_VISIBILITA_BY_CODICE = "getCdrVisibilitaByCodice";
	public static final String COUNT_VISIBILITA_UTENTE_CDR = "countVisibilitaPerUtenteNelCdr";
	public static final String GET_VISIBILITA_UTENTE_CDR = "getVisibilitaPerUtenteNelCdr";
	public static final String GET_AMBITO_BY_APPLICAZIONE = "getAmbitoByApplicazione";
	public static final String GET_PROFILI_ROLE_GROUP = "getProfiliRoleGroup";
	public static final String GET_ELENCO_PROFILI_IN_VISIBILITA_CM = "getElencoProfiliInVisibilitaCM";
	public static final String ID_VISIBILITA_UTENTE_CDR = "idVisibilitaPerUtenteNelCdr";
	public static final String GET_RICHIESTE_REVOCA_VISIBILITA = "getRichiesteRevocaVisibilita";
	public static final String COUNT_VISIBILITA_MASSIVE = "countVisibilitaMassive";
	public static final String INSERT_FILE_RICH_VISIBILITA = "inserisciFileRichVisibilita";
	public static final String COUNT_REVOCHE_IN_RICHIESTE_BATCH = "getNumeroRevocheInRichiesteBatch";
	public static final String COUNT_REVOCHE_IN_RICHIESTE_BATCH_2 = "getNumeroRevocheInRichiesteBatch_2";
	public static final String UPDATE_RICHIESTA_CARICAMENTO_MASSIVO_VIS = "updateRichiestaCaricamentoMassivoVisib";
	public static final String GET_ID_RICH_UTENTE_VISIBILITA = "getIdRichUtenteVisibilitaCM";
	public static final String GET_LISTA_RECORD_SCARTATI_VIS = "getListaRecordScartatiVis";
	public static final String GET_EMAIL_AMMINISTR_CARICAMENTO_VIS = "getEmailAmminCaricamentoVis";
	public static final String GET_LIST_CM_VIS_CONTROLLATI = "getListaCaricamentiVisControllati";
	public static final String GET_LISTA_ALLEGATI_CARICAMENTO_VIS = "getListaAllegatiCaricamentoVis";
	public static final String GET_RICHIESTE_BATCH_VIS_BY_SEQUENCE = "getListaRichiesteBatchVisBySequence";
	public static final String UPDATE_GENERAZIONE_RICHIESTA_CARICAMENTO_MASSIVO_VIS = "updateGenerazioneRichiestaCaricamentoMassivoVis";
	public static final String GET_PROFILI_UTENTE_VISIBILITA_PER_REVOCA = "getProfiliUtenteVisibilitaPerRevoca";
	public static final String GET_PROFILI_RICHIESTA_BY_SEQ_CDRVIS_CF_AZIONE = "getProfiliRichiestaBySeqCdrCfAzione";
	public static final String GET_CODICE_APPLIC_BY_PROFILO = "getCodiciAppByCodiceProfilo";
	public static final String COUNT_VERTICE_UFFICIO = "countVerticeUfficio";
	public static final String DELETE_CM_VISIBILITA_FILE = "cancellaRichiestaCmVisibilitaFile";
	public static final String UPDATE_STATO_RICHIESTA_CM_VIS = "updateStatoRichiestaCaricamentoMassVis";
	
	/** BATCH COERENZA NEW **/
	public static final String GET_UTENTI_DA_CONTROLLARE_COERENZA_NEW = "getUtentiDaControllareCoerenzaNew";
	public static final String GET_DATI_PROFILI_ATTIVI_UTENTE_ESTENSIONE = "getDatiProfiliAttiviUtenteEstensione";
	public static final String GET_COD_UFF_COD_CDR_BY_IDRICHVIS = "getCodUfficioCodCdrByIdRichVis";
	public static final String INSERT_COERENZA_SEGNALAZIONI = "inserisciCoerenzaSegnalazioni";
	public static final String COUNT_UFFICI_BY_CODUFF = "countUfficiByCodUff";
	public static final String COUNT_CDR_BY_CODCDR = "countCdrByCodCdr";
	public static final String SET_FLAG_COERENZA_CEA_UTENTI_NO = "setFlagCoerenzaCeaUtenti_No";
	public static final String GET_UTENTI_DA_CONTROLLARE_CEA = "getUtentiDaControllareCea";
	public static final String GET_PROFILI_ATTIVI_UTENTE_BY_CF_AND_AMBITO_COER_NEW = "getProfiliAttiviUtenteByCfAndAmbito_CoerNew";
	public static final String GET_DESCR_RG_BY_COD_PROFILO = "getDescrRoleGroupByCodProfilo";
	public static final String SET_INCOERENZE_ASS_SEGNALATE_NEW = "setIncoerenzeAssegnazioneNewSegnalate";
	public static final String UPDATE_DATA_ELABORAZIONE_IILIV_CAU = "updateDataElaborazioneIILivCau";
	public static final String UPDATE_DATA_ELABORAZIONE_IILIV_OA = "updateDataElaborazioneIILivOA";
	public static final String GET_CODICE_CDR_ASSEGNAZIONE_BY_CF = "getCdrAssegnazioneUtente";
	public static final String CHECK_PROFILO_ASSEGNABILE = "checkProfiloAssegnabile";
	
	/** BATCH RIORGANIZZAZIONE AGGIORNA CDR VISIBILITA **/
	public static final String GET_LISTA_VISIBILITA_DA_AGGIORNARE = "getListaVisibilitaDaAggiornare";
	public static final String GET_LISTA_VISIBILITA_NON_AGGIORNATE = "getListaVisibilitaNonAggiornate";
	public static final String GET_PROFILI_ORG = "getProfiliOrg";
	public static final String GET_PROFILI_ORG_EST = "getProfiliOrgEst";
	public static final String GET_PROFILI_ROLE = "getProfiliRole";
	public static final String RIORG_UPDATA_UTENTE_IN_VISIBILITA = "updateUtenteInVisibilita";
	public static final String RIORG_UPDATA_PROFILI_UTENTE_IN_VISIBILITA = "updateProfiliUtenteInVisibilita";
	public static final String RIORG_UPDATA_PROFILI_ATTIVI_UTENTE = "updateProfiliAttiviUtente";
	public static final String RIORG_UPDATA_STORICO_PROFILI_OPERATORE = "updateStoricoProfiliOperatore";
	public static final String RIORG_UPDATA_PROFILI_ROLE_CAU = "updateProfiliRoleCau";
	public static final String GET_LISTA_UTENTI_ESTERNI_DA_AGGIORNARE = "getListaUtentiEsterniDaAggiornare";
	public static final String RIORG_UPDATE_UTENTI = "updateUtentiRiorg";
	public static final String RIORG_UPDATE_STORICO_CDR_UTENTI = "updateStoricoCdrUtentiRiorg";
	public static final String RIORG_INSERT_STORICO_CDR_UTENTI = "insertStoricoCdrUtentiRiorg";
	public static final String RIORG_UPDATE_REGISTRO_RICHIESTE = "updateRegistroRichiesteRiorg";
	public static final String RIORG_ARCHIVIA_RICHIESTE_ESEGUITE = "archivioRichiesteEseguiteRiorg";
	public static final String VERIFICA_VISIBILITA_UTENTE = "verificaVisibilitaUtente";
	public static final String GET_LISTA_PAU_PER_CAMBIO_UFF_CDR = "getListaPauPerCambioUfficioCdr";
	public static final String GET_UTENTI_EST_CDR_CHIUSI = "getUtentiEsterniDiCdrChiusi";
	public static final String CHECK_VISIBILITA_CF = "checkVisibilitaCf";
	public static final String COUNT_CAMBIO_CDR_ATTIVI = "countCambioCdrAttivi";
	public static final String COUNT_PROF_UTE_IN_VISIBILITA_RIORG = "countProfUteInVisibilitaRiorg";
	
	/** BATCH RIORGANIZZAZIONE DISABILITAZIONI CDR **/
	public static final String UPDATE_UTENTI_DISABILITAZIONI_RIMOSSE = "updateUtentiDisabilitazioniRimosse";
	public static final String GET_LISTA_DISABILITAZIONI_CDR = "getListaDisabilitazioniCdr";
	public static final String COUNT_DIPENDENTI_CDR_PARTENZA = "countDipendentiCdrPartenza";
	
	/** BATCH INTERIM CONDIVISIONI **/
	public static final String GET_ELENCO_INTERIM_COND_UT = "getInterimCondivisioniUtente";
	public static final String GET_ELENCO_INTERIM_COND_CDR = "getInterimCondivisioniPerCdr";
	
	/** verifica visibilita **/
	public static final String COUNT_N_VERIFICA_VISIBILITA = "countDipendentiCdrPartenza";
	public static final String COUNT_NOT_N_VERIFICA_VISIBILITA = "countDipendentiCdrPartenza";
	
	
	// cambio ufficio cat AC
	public static final String SELECT_LISTA_UFFICI_DA_STRUTTURA = "selectListaUfficiDaStruttura";

	// get info ufficio
	public static final String GET_INFO_UFFICIO_OPERATORE = "getInfoUfficioOperatore";
	public static final String GET_PROFILI_RICHIESTA_BY_ID = "getProfiliRichiestaByID";
	
	// Cruscotto
	public static final String GET_CASELLE_CRUSCOTTO = "Cruscotto.getElencoCaselleCruscotto";
	public static final String GET_CASELLE_COUNT_CRUSCOTTO = "Cruscotto.getCaselleCount";
	public static final String GET_ACTIVE_DIRECTORY_DOMINIO_COUNT_CRUSCOTTO = "Cruscotto.getActiveDirectoryDominioCount";
	public static final String GET_ACTIVE_DIRECTORY_DOMINIO_CRUSCOTTO = "Cruscotto.getActiveDirectoryDominio";
	public static final String GET_CARTELLE_CONDIVISE_COUNT_CRUSCOTTO = "getCartelleNasCount";
	public static final String GET_CARTELLE_CONDIVISE_CRUSCOTTO = "getCartelleCondiviseNas";
	
	/***********************************************************************************
	 * BATCH CRUSCOTTO CSV
	 **********************************************************************************/
	
	public static final String BATCH_CANCELLA_CARTELLE_CONDIVISE_NAS = "batchDeleteCartelleCondiviseNas";
	public static final String BATCH_INSERISCI_CARTELLE_CONDIVISE_NAS = "batchInsertCartelleCondiviseNas";
	
	//Amministratori Applicativi
	public static final String GET_AMMINISTRATORI_CENTRALI_ROLE_GROUP = "getAmministratoriCentraliByRoleGroup";
	public static final String GET_AMMINISTRATORI_CENTRALI_CF = "getAmministratoriCentraliByCf";
	public static final String GET_AMMINISTRATORI_CENTRALI_CF_ROLE_GROUP = "getAmministratoriCentraliByCfRoleGroup";
	public static final String GET_PROFILI_ATTIVI_UTENTE_2 = "getProfiliAttiviUtenteAmmCentr";
	public static final String INSERT_ABILITAZIONI_AMM_CENTRALI = "insertAbilitazioniAmministratoreCentrale";
	public static final String UPDATE_ABILITAZIONI_AMM_CENTRALI = "aggiornaAmmCentrale";
	public static final String DELETE_ABILITAZIONI_AMM_CENTRALI = "cancellaAmmCentrale";
	
	/*********************************************************************************
	 * BATCH EMAIL SCADENZA OPERATORI ESTERNI
	 ********************************************************************************/
	
	public static final String GET_ELENCO_OPERATORI_ESTERNI_IN_SCADENZA = "getElencoOperatoriEsterniInScadenza";
	
	/*********************************************************************************
	 * GESTIONE CATALOGO ABILITAZINE
	 ********************************************************************************/
	
	public static final String UPDATE_NOME_APPLICAZIONE = "updateNomeApplicazione";
	public static final String INSERT_ABILITAZIONE_FUNZ_PROF = "inserisciAbilitazioneFunzProf";
	public static final String DELETE_ABILITAZIONE_FUNZ_PROF = "deleteApplicazioneFunzProf";
	public static final String DELETE_PROF_APPLICAZIONE_ESTESA = "deleteProfiliApplicazioneEstesa";
	public static final String DELETE_ABILITAZIONE_UTENTE = "deleteApplicazioneUtente";
	public static final String INSERT_ABILITAZIONE_FUNZ_RG = "inserisciAbilitazioneFunzRG";
	public static final String DELETE_ABILITAZIONE_FUNZ_RG = "deleteApplicazioneFunzRG";
	public static final String INSERT_APPLICAZIONE_ESTESA = "inserisciApplicazioneEliminabile";
	public static final String GET_APPLICAZIONE_BY_CODICE = "getApplicazioneByCodice";
	public static final String GET_COPPIA_APPLICAZIONE_RG = "getApplicazioneByCodRGAndCodApp";
	
	/***********************************************************************************
	 * COMUNICAZIONI NEWS
	 **********************************************************************************/
	public static final String GET_ELENCO_COMUNICAZIONI = "getElencoComunicazioni";
	public static final String GET_ELENCO_COMUNICAZIONI_OPERATORI = "getElencoComunicazioniPerOperatori";
	public static final String GET_ELENCO_COMUNICAZIONI_OPERATORI_DR = "getElencoComunicazioniPerOperatori_DR";
	public static final String GET_ELENCO_COMUNICAZIONI_OPERATORI_DC = "getElencoComunicazioniPerOperatori_DC";
	public static final String GET_ELENCO_COMUNICAZIONI_OPERATORI_COP = "getElencoComunicazioniPerOperatori_COP";
	public static final String GET_ELENCO_COMUNICAZIONI_OPERATORI_CAM = "getElencoComunicazioniPerOperatori_CAM";
	public static final String GET_ELENCO_CDR_COMUNICAZIONI = "getElencoCdrComunicazioni";
	public static final String GET_ELENCO_DP = "getElencoDP";
	public static final String GET_ELENCO_DR = "getElencoDR";
	public static final String GET_ELENCO_DC_DIVISIONI = "getElencoDCDivisioni";
	public static final String GET_UFFICIO_UNICO = "getUfficioUnico";
	public static final String GET_ELENCO_COP = "getElencoCop";
	public static final String GET_ELENCO_CAM = "getElencoCam";
	public static final String INSERISCI_MESSAGGIO = "inserisciMessaggio";
	public static final String ELIMINA_MESSAGGIO = "eliminaMessaggio";
	public static final String INSERISCI_RUOLI_DESTINATARI = "inserisciRuoliDestinatari";
	public static final String GET_ALLEGATI_COMUNICAZIONE = "getAllegatiComunicazione";
	public static final String GET_GERARCHIA_DC = "getElencoGerarchiaDC";
	public static final String GET_GERARCHIA_DP = "getElencoGerarchiaDP";
	public static final String GET_GERARCHIA_DR = "getElencoGerarchiaDR";
	public static final String INSERISCI_TIPOLOGIA_STRUTTURA = "inserisciTipologia";
	public static final String GET_COMUNICAZIONE_BY_ID = "getComunicazioneById";
	public static final String INSERISCI_TIPO_SELEZIONATO = "inserisciTipoSelezionato";
	public static final String GET_TIPO_SELEZIONATO = "getTipoSelezionatoById";
	public static final String GET_EMAIL_RICHIEDENTI = "getEmailRichidenti";
	public static final String GET_EMAIL_AUTORIZZATORI = "getEmailAutorizzatori";
	public static final String GET_EMAIL_AMMINISTRATORI = "getEmailAmministratori";
	
	
	/** Caricamenti Massivi Richieste Mappature abilitazioni uffici **/
	public static final String GET_SEQUENCE_FROM_RICHIESTE_CARICAMENTO_MASSIVO_MAPPATURE = "getSequenceFromCaricamentoMassivoMappature";
	public static final String INSERISCI_RICHIESTA_CARICAMENTO_MASSIVO_MAPPATURE = "inserisciRichiestaCaricamentoMassivoMappature";
	public static final String GET_LISTA_CARICAMENTI_MASSIVI_MAPP_UTENTE = "getElencoCaricamentiMassiviMappatureUtenti";
	public static final String GET_DETTAGLIO_CARICAMENTO_MAPPATURE = "getDettaglioCaricamentoMappature";
	public static final String ANNULLA_RICHIESTA_CARICAMENTO_MASSIVO_MAPPATURE = "annullaRichiestaCaricamentoMassivoMappature";
	public static final String COUNT_TIPO_UFFICO = "countTipoUfficio";
	public static final String COUNT_AMBITO_REG = "countAmbitoRegionale";
	public static final String COUNT_AMBITO_PROV = "countAmbitoProvinciale";
	public static final String COUNT_COD_UFF = "countCodUfficio";
	public static final String GET_LISTA_ALLEGATI_CARICAMENTO_MAPPATURE_NO_BLOB = "getListaAllegatiCaricamentoMappatureNoBlob";
	public static final String GET_ALLEGATO_CARICAMENTO_MAPPATURE = "getAllegatoCaricamentoMappature";
	public static final String GET_LIST_CM_MAP_CONTROLLI = "getListaCaricamentiMappaturePerControlli";
	public static final String GET_ROLE_GROUP_BY_CODICE = "getRoleGroupByCodice";
	public static final String GET_PROFILO_BY_CODICE = "getProfiloByCodice";
	public static final String COUNT_ADMIN_CDR_UFF = "getAdminGroupCdrCount";
	public static final String COUNT_ADMIN_CDR_TIPO_UFF = "getAdminGroupTipoUffCdrCount";
	public static final String DELETE_MAPPATURA_TIPO_UFF_CDR = "deleteMappaturaTipoUffCdr";
	public static final String DELETE_MAPPATURA_CDR_UFF = "deleteMappaturaCdrUff";
	public static final String UPDATE_RICHIESTA_CARICAMENTO_MASSIVO_MAPPATURA = "updateRichiestaCaricamentoMassivoMappatura";
	public static final String GET_LISTA_RECORD_CARICAMENTO_MAPPATURE_FILE = "getListaRecordCaricamentoMap";
	public static final String INSERT_FILE_RICH_MAPPATURA = "inserisciFileRichMappatura";
	
	/** I miei ruoli e Ruoli degli operatori**/
	public static final String GET_RUOLI_RICHIEDENTE = "getRuoliRichiedente";
	public static final String GET_RUOLI_AUTORIZZATORE = "getRuoliAutorizzatore";
	public static final String GET_RUOLI_AMM_AUDITOR = "getRuoliAmministratoreAuditor";
	public static final String GET_RUOLI_DELEGHE = "getRuoliDeleghe";
	public static final String INSERT_FILTRI_RUOLI_OPER_CDR = "inserisciFiltriRicercaRuoliOper";
	public static final String INSERT_RICHIESTA_RUOLI_OPERATORI_CDR = "inserisciRichiestaRuoliDegliOperatoriCdR";
	public static final String GET_RICHIESTE_RUOLI_DEGLI_OPERATORI = "getRichiesteRuoliDegliOperatori";
	public static final String GET_RICHIESTE_RUOLI_OPERATORI_BATCH = "getRichiesteRuoliOperatoriBatch";
	public static final String GET_ELENCO_FILTRI_RUOLI_CDR = "getElencoFiltriRuoliCdR";
	public static final String GET_ELENCO_FILTRI_RUOLI_RG = "getElencoFiltriRuoliRg";
	public static final String GET_ELENCO_FILTRI_RUOLI_PROFILI = "getElencoFiltriRuoliProfili";
	public static final String ELABORAZIONE_FALLITA_BATCH_RUOLI = "elaborazioneFallitaBatchRuoli";
	public static final String GET_ELENCO_PROFILI_EXPORT_RUOLI = "getProfiliExportRuoli";
	public static final String INSERT_FILE_RUOLI_BATCH = "insertFileRichiestaRuoliBatch";
	public static final String ELABORA_RICHIESTA_RUOLI_BATCH = "elaboraRichiestaRuoliBatch";
	public static final String SET_FLAG_ELABORAZIONE_RUOLI_OPER = "setFlagElaborazioneRuoliOperatori";
	public static final String GET_FILE_BATCH_RUOLI = "getFileRuoliDegliOperatori";
	public static final String DELETE_RICHIESTA_RUOLI_OPER = "cancellaRichestaRuoliOperatoriWeb";

	// batch monitoraggio abilitazioni / web conferma attivita - report attivita
	public static final String GET_CONF_MONITORAGGIO = "getConfMonitoraggio";
	public static final String INS_RICHIEDENTI_MONITORAGGIO = "insertAttivitaChecked";
	public static final String UPD_DATA_INVIO_REPORT = "updateDataInvioReport";
	public static final String GET_RICHIEDENTI_MONITORAGGIO = "getRichiedentiMonitoraggio";
	public static final String GET_ATTIVITA_CHECKED = "getAttivitaChecked";
	public static final String GET_ATTIVITA_CHECKED_REPORT = "getAttivitaCheckedReport";
	public static final String GET_ATTIVITA_CHECKED_REPORT_RESP = "getAttivitaCheckedReportResp";
	public static final String GET_EMAIL_RESP_STRUT = "getEmailResponsabileStruttura";
	public static final String UPDATE_ATTIVITA_CHECKED = "updateCheckEffettuato";
	public static final String GET_ATTIVITA_CHECKED_EXPORT_REPORT = "getAttivitaCheckedExportReport";
	public static final String GET_PERIODI_RIF_ATT_CHECK = "getPeriodiRifAttCheck";
	
	//  batch aggiornamento ruoli
	public static final String GET_UTENTI_INS = "getUtentiDaInserire";
	public static final String GET_UTENTI_DEL = "getUtentiDaEliminare";
	public static final String INSERT_UTENTI_GRUPPO_OTP = "insertUtentiGruppoOTP";
	public static final String INSERT_VARIAZIONI_CF_RUOLI_OTP = "insertVariazioniCfRuoliOTP";
	public static final String CLEAN_VARIAZIONI_CF_RUOLI_OTP = "cleanVariazioniCfRuoliOTP";
	public static final String SET_VALORE_COSTANTE_BATCH_AGGIORNAMENTO_RUOLO_OTP = "setValoreCostanteBatchAggiornamentoRuoloOTP";
	
	public static final String SET_VALORE_COSTANTE_BATCH_MAIL_MONITORAGGIO_APPLICAZIONI = "setValoreCostanteBatchMailMonitoraggioAbilitazioni";
}
