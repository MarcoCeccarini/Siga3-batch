package it.finanze.siga.ejb;

import it.finanze.scheduler.bean.*;
import it.finanze.siga.bean.*;
import it.finanze.siga.bean.ProfiloRichiestaBean;
import it.finanze.siga.finder.*;
import it.finanze.siga.util.bean.*;
import it.finanze.siga.util.tree.ProfiloTreeBean;
import it.sogei.eaf.util.CheckException;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import java.sql.SQLException;
import java.util.*;

public interface SigaDao {

	/************ Inserimento Richiesta Profilazione ************/
	public void destroy();

	public ElencoBean getElencoUtenti(BaseFinder in);
	public ElencoBean getElencoUtentiLike(BaseFinder in);	
	public UffCdrAppartBean getUffCdrAppart(UffCdrAppartFinder finder );
	public ElencoBean getProfiliOperatoreInVisibilita(ProfiliOperatVisibFinder finder );
	public List<ProfiloUtenteInVisibilitaBean> getProfiliRichiestaVisibilita(RichiestaBean richiesta);
	public ElencoBean getElencoOperatoriInVisibilita(OperatoreFinder finder );
	public ElencoBean getElencoOperatori( OperatoreFinder finder );
	public ElencoBean getElencoOperatoriTemplate( ProfiloFinder finder );
	public RichDlgSessBean getRichDlgSess( RichDlgSessFinder finder );
	public RichiesteDiProfilazioneBean getRichiesteDiProfilazione( RichiesteProfilazioneFinder finder );
	public GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv( GetAutorizzatore_I_LivFinder finder );
	public GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv_Iter( GetAutorizzatore_I_LivFinder finder );
	public GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv_Ass( GetAutorizzatore_I_LivFinder finder );
	public GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv_Per_Ripristino(GetAutorizzatore_I_LivFinder finder); 
	public GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv_profili_CAT( GetAutorizzatore_I_LivFinder finder );
	public List<ProfiloRichiestaBean> getProfAttUtenteXUfficioProvenienza(ProfAttUte_x_Uff_di_provenienzFinder finder );
	public List<ProfiloRichiestaBean> getIntersezProfAmmissProfAttUtenteXUfficio(ProfAttUte_x_Uff_di_provenienzFinder finder );
	public List<ProfUfficioDestinazioneBean> getProfiliUfficioDestinazione(ProfAttUte_x_Uff_di_provenienzFinder finder );
	public List<String> getElencoRichiedentiTotaliDaRichieste(RichiestaFinder in) ;
	public List<String> getElencoAutorizzatori1LivelloTotaliDaRichieste(RichiestaFinder in) ;
	public List<String> getElencoAutorizzatori2LivelloTotaliDaRichieste(RichiestaFinder in) ;
	public List<String> getElencoGestoriOperatoriDaRichieste(RichiestaFinder in) ;
	public List<String> getElencoCfUtentiDaRichieste(RichiestaFinder in) ;
	public List<String> getElencoRichiedentiTotaliDaRichiesteRR(RichiestaFinder in) ;
	public List<String> getElencoAutorizzatori1LivelloTotaliDaRichiesteRR(RichiestaFinder in) ;
	public List<String> getElencoAutorizzatori2LivelloTotaliDaRichiesteRR(RichiestaFinder in) ;
	public List<String> getElencoGestoriOperatoriDaRichiesteRR(RichiestaFinder in) ;
	public List<String> getElencoCfUtentiDaRichiesteRR(RichiestaFinder in) ;
	public ElencoBean getOperatoreByCF( OperatoreFinder finder );
	public List<String> getElencoArchiviazioneDaRichiesteRR(RichiestaFinder in) ;
	public String getUfficioByCdr( String descrizione );
	
	
	//REVOCA
	public ElencoBean getElencoOperatoriRevocaVisibilitaAC(OperatoreFinder finder );
	
	public List<CDRBean> getElencoCDR(CDRFinder finder);
	public List<CDRBean> getElencoCDRRichesteDiUnUffio(CDRFinder finder);
	public List<CDRBean> getElencoCDRResp(CDRFinder finder);
	public List<CDRBean> getElencoCDROperatoriVis(CDRFinder finder);
	public List<InterrProfiliOperatoreBean> getElencoProfiliOperatore(InterrProfiliOperatoreFinder finder);
	public Collection <IterProfiliApplicazioniBean> getIterProfiliApplicazione(IterProfiliApplicazioniFinder finder);
	public Collection <IterNonStandardProfiliApplicazioniBean> getIterNonStandardProfiliApplicazioni(IterNonStandardProfiliApplicazioniFinder finder);
	public Collection <RegistroDelleMieAssociazioniPuntualiBean> getElencoRegistroDelleMieAssociazioniPuntuali(RegistroDelleMieAssociazioniPuntualiFinder finder);
	public Collection <RegistroDelleMieAssociazioniPuntualiBean> getElencoRegistroAssociazioniORA(RegistroDelleMieAssociazioniPuntualiFinder finder);
	public Date getDataOraDB();
	public Collection <LeMieVariazioniRuoloCDRBean> getElencoLeMieVariazioniRuoloCDR(LeMieVariazioniRuoloCDRFinder finder);
	public Collection<DelegaBean> getElencoRegistroDeleghe(LeMieVariazioniRuoloCDRFinder finder);
	public Collection<DelegaBean> getElencoRegistroDelegheDelegatoDelegante(LeMieVariazioniRuoloCDRFinder finder);
	public List<CDRBean> getElencoCDRNonInVisibilita(CDRFinder finder);
	public List<UffDestinazBean> getUfficiDestinazione(UffDestinazFinder finder);
	public List<AmbitoBean> getElencoAmbito(BasePaginateFinder finder);
	public List<AmbitoBean> getElencoAmbitoBis(BasePaginateFinder finder);
	public List<AmbitoBean> getElencoAmbitoApplCDR(BasePaginateFinder finder);
	
	/** VALORI LABEL **/
	public List<LabelValueBean> getValoriLabel(ValoriFinder finder);
	public List<ProfiloBean> getElencoProfilo(BasePaginateFinder finder);
	public List<ProfiloBean> getElencoProfiliVisibilita(BasePaginateFinder finder);
	public List<ProfiloBean> getElencoProfiliStruttura(BasePaginateFinder finder);
	public List<ProfiloBean> getElencoProfiliVisibilitaAC(BasePaginateFinder finder);
	public List<ProfiloRichiestaBean> getElencoProfiloProfilazioneUfficioCdR(BasePaginateFinder finder);
	public List<ProfiloRichiestaBean> getElencoProfiloProfilazioneUfficioCdR_AmmCentraleApp(BasePaginateFinder finder);
	
	
	public List<RichiestaBean> getRichiesteByElencoCDR ( RichiestaFinder finder );
	public List<RichiestaBean> getRichiesteRichiedente ( RichiestaFinder finder );
	public List<RichiestaBean>  getRichiesteAutorizzatoreI ( RichiestaFinder finder );
	public List<RichiestaBean>  getRichiesteAutorizzatoreII( RichiestaFinder finder );
	public List<RichiestaBean>  getRichiesteGestore( RichiestaFinder finder );
	public List<RichiestaBean>  getRichiesteOperatore( RichiestaFinder finder );
	public List<RichiestaBean> getInfoRichiesteInCorso(RegistroRichiesteFinder finder);
	public RichiestaBean getRichiesta( RichiestaFinder finder );
	public RichiestaBean getRichiestaWF( RichiestaFinder finder );
	public RichiestaBean getRichiestaRR( RichiestaFinder finder );
	public List<ProfiloRichiestaBean> getProfiliByIdAndTipoRichiesta( RichiestaFinder finder );
	public List<CDRBean> getResponsabileByCF( OperatoreFinder finder );	
	public ElencoBean getRichieste( RichiestaFinder finder );
	public ElencoBean getRichiesteRR( RichiestaFinder finder );
	public OperatoreBean getDatiUtente( OperatoreFinder finder );
	public OperatoreBean getDatiUtente2(OperatoreFinder operatoreFinder);
	public OperatoreBean getDatiUtenteCdrResponsabile(OperatoreFinder operatoreFinder);
	public String getCDRUtenteInVisibilita( RichiestaFinder finder );
	public List<CDRBean> getCDRAlberoEsclusi( String cdr );
	public List<ProfiloRichiestaBean> getProfiliAlberoEsclusi( ProfiloFinder profilo );
	public List<ProfiloRichiestaBean> getProfiliAlberoEsclusi_inVisibilita( ProfiloFinder profilo );
	public Integer controlloVisibilitaFaseDue( ProfiloFinder profilo );
	public OrigineRichiestaBean getOrigineAbilitazione(String codiceOrigine);
	
	public RichiestaBean getDescrizioneCdr( RichiestaBean finder );
	public void aggiornaRichiesta( RichiestaBean input );
	public void aggiornaProfiloRichiesta(ProfiloRichiestaBean input);
	public void aggiornaVisibilitaUtente(RichiestaFinder input);
	
	public boolean isUfficioDaPreservare(String codUfficio);
	
	public boolean presaInCarico( RichiestaFinder input );
	public boolean rilasciaRichiesta( RichiestaFinder input );
	public String getDescrizioneStato( String finder );
	public List<FileBean> getFilesRichiesta( Integer finder );
	public String getCDR_UfficioPassato(CDRUfficioPassatoFinder finder);
	public String getExplicit_Entitlement(ProfiloFinder finder);
	public String getCf_Richiedente(CF_RichiedenteFinder finder);
	public 	UffDestinazBean getDescrCdrByCodUff (String input) ;
	public List<AttoreBean> getCfAttoreFaseAU_D(Integer idRichiesta);

	/**
	 * elenco profili dopo verifica iter
	 */
	public List<ProfiloTreeBean> getElencoProfiloVerificaIter(BaseFinder finder);
	
	/**
	 * elenco profili dopo verifica autorizzazioni
	 */
	public ElencoBean getElencoIterVerificaAutorizzazioni(BaseFinder finder);
	public ElencoBean getElencoIterVerificaGestori(CheckAutorizzatoriProfiloFinder finder);	
	public RichiestaInCorsoBean isRichiestaInCorso(BasePaginateFinder finder);
	public boolean inserisciRichiesta(BaseFinder finder);
	public boolean inserisciProfiliCambioUfficioCAT(List<ProfiloRichiestaBean> elencoProfiliAttivi,
			List<ProfiloRichiestaBean> elencoProfiliAssegnati,
			RichDlgSessBean richDlgSessBean, GetAutorizzatore_I_LivBean autorizzatore_I_LivBean,
			OperatoreBean operatoreBean, UtenteBean utente,
			String ufficioCATProvenienza, String codiceUfficioDestinazione, List<RichiestaBean> listRichiesta);
	public int inserisciRichiesta2(BaseFinder finder);
	public boolean inserisciRichieste(BaseFinder finder);
	public boolean inserisciProfili(BaseFinder finder);
	public List<TemplateBean> getElencoTemplate(BasePaginateFinder finder);
	public List<ProfiloBean> getElencoProfiloTemplate(BaseFinder finder);
	public List<ProfiloBean> getElencoProfiloAttivoUtente(BasePaginateFinder finder);
	public List<ProfiloBean> getProfiliAttiviUtentePerReport(ProfiloFinder finder);
	public List<ProfiloBean> getProfiliAttiviUtenteInVisibilitaPerReport(ProfiloFinder finder);
	public List<ProfiloBean> getElencoProfiloAttivoUtenteCompetenza(BasePaginateFinder finder);
	public void updateAuditRichiesta(RichiestaBean richiestaBean); 
	public boolean isCdrFiglio(String codiceCDR, String CDRPadre);
	public List<RichiestaBean> getRichiesteDelegato(RichiestaFinder finder);

	/** Inserimento File **/
	public boolean inserisciFile(BaseFinder finder);
	public int inserisciFile2(BaseFinder finder);
	public List<OperatoreBean> getOperatoriPerNominativo(OperatoreFinder finder);
	public List<OperatoreBean> getOperatoriPerDelegato(OperatoreFinder finder);
	
	/** ALBERO **/
	public List<StrutturaUfficioCDRBean> getElencoStruttUffCDRAmministratore(BaseFinder finder);
	public List<StrutturaUfficioCDRExtBean> getElencoStruttUffCDRPerSottoAlberoByCDR(BaseFinder finder);
	public List<StrutturaUfficioCDRExtBean> getElencoStruttUffCDRPerAlberoByElencoCDR(BaseFinder finder);
	
	/** DELEGHE **/
	public void inserisciDelega(DelegaFinder delegaFinder);
	public void inserisciDelegaBatch(DelegaFinder delegaFinder);
	public void rimozioneDelega(DelegaFinder delegaFinder);
	public List<DelegaBean> getElencoDeleghe(DelegaFinder finder);
	public void aggiornaRichiesteDeleghe(String cfDelegato);
	public String getStrutturaByCDR(String cdr);
	public String getIILivByCdr(String input);
	public List<OperatoreBean> getDelegantiByCDR(String cdr);
	public List<String> getElencoStruttRegionali(String strutturaIILiv);
	public List<OperatoreBean> getDelegabiliByCdr(Map input);
	public List<OperatoreBean> getDelegabiliByCdrORA(String cdr);
	// gestione allegati
	public int keyIdDelega();
	public void insIdAllegatiDelega(DelegaBean delega);
	public void inserisciAllegatiDelega(DocumentoFinder docFinder);
	public List<AllegatoBean> getAllegatiDelega(Integer id);
	//<--
	
	/** AUDIT **/
	public int inserisciAUDIT(AuditFinder auditFinder);
	
	/** RICHIEDENTE_CDR **/
	
	public Integer verificaIsRichiedente(RichiedenteCDRFinder finderRichiedente);
	public Integer countRichiedenteCDR(String input);

	public List<RichiedenteCDRBean> getRichiedenteCDR(RichiedenteCDRFinder richiedenteCDRFinder);
	public List<RichiedenteCDRBean> getRichiedentePuntualeCDR(RichiedenteCDRFinder richiedenteCDRFinder);
	public List<RichiedenteCDRBean> getRichiedentiAC(RichiedenteCDRFinder richiedenteCDRFinder);
	public List<RichiedenteCDRBean> getCdrAutorizzatoreIliv(RichiedenteCDRFinder richiedenteCDRFinder);
	
	
	public boolean inserisciProfiliCambioUfficio(BaseFinder finder);
	public boolean inserisciRichiesteCambioUfficioCAT(BaseFinder finder, UtenteBean utente);

	/** ASSOCIAZ_OPER_RICHI_AUTOR **/
	public Integer getNumeroAssociazioni(AssociazOperRichiAutorFinder finderAssocia) ;
	public Integer getNumeroAssociazioniRichOrAut(AssociazOperRichiAutorFinder finderAssocia) ;

	public Integer verificaAssociazioni(AssociazOperRichiAutorFinder finderAssocia) ;
	public Integer getNumeroRichieste(AssociazOperRichiAutorFinder finderAssocia);
	public Integer verificaResponsabileCdrFigli(String cfRichiedente);
	/** Metodi comuni**/
	public void modificaoperatoreRichiedenteAutorizzatori(RichiedenteCDRFinder richiedenteCDRFinder, String cfNuovo);
	public void revocaRichiedentiDiUnGruppoDiOperatori(String cfPrecedente,	String cfAltro);
	public void sostituzioneRichiedenteDiUnGruppoDiOperatori(String cfPrecedente, String cfNuovo, String cfAltro);
	public void ripristinoResponsabileHR(String cfPrecedente, String cdr,String motivoRevoca, UtenteBean utente);
	public void sostituzioneDellAutorizzatoreDiUnCDR(String cfPrecedente,String cfNuovo, String cdr, String motivoRevoca,UtenteBean utente);
	public void ripristinoAutorizzatoreDaHR(String cfPrecedente, String cdr,String motivoRevoca, UtenteBean utente);
	public void modificaEntrambi(String cfPrecedenteRichiedente, String cfNuovoRichiedente, String cfPrecedenteAutorizzatore, String cfNuovoAutorizzatore, String cdr, UtenteBean utente);
	public void ripristinaEntrambi(String cfPrecedenteRichiedente,String cfPrecedenteAutorizzatore, String cfNuovoRichiedente,String cdr, UtenteBean utente);
	
	/** Utente **/
	public void aggiornaFlagRuolo(OperatoreFinder operatoreFinder);
	public boolean abilitatoCambioUfficio(String cf);
	public List<String> getProfiliAttivi(String cf);
	public String getStrutturaIILiv(String cf);
	public List<String> getCodiceAmmLocale(String cf);
	public List<String> getAmmCentraleApp(String cf);	
	public List<String> getRuoliGestoriOperatori(String cf);
	public List<String> getEmailRichiedenteVisibilita(RichiestaFinder input);
	public List<String> getEmailALVisibilita(RichiestaFinder input);
	public String getCFRichiedenteWFM(RichiestaBean input);
	public String getCFRichiedenteVisibilitaWFM(RichiestaBean input);
	public List<String> getCFRichiedentiECAAltriCDRWFM(RichiestaBean input);
	public List<String> getEmailAutorizzatoreI(RichiestaBean input);
	public List<String> getEmailAltroUfficioInteressato(RichiestaBean input);
	public List<String> getEmailAmmCentrali();
	public String getStrutturaIILivByCdr(String input);
	public List<String> getEmailStrutturaIILiv(String input);
	public String verificaStrutture(OperatoreBean input);
	
	public List<String> getServiziUtente(String ruolo);

	
	
	/** UFFICIO**/
	public UfficioFinder getUfficoByCodUff(UfficioFinder ufficioFinder);
	
	/** STRUTTURA_ITER **/
	public List<StrutturaPerIterFinder> getStrutturaIter( StrutturaPerIterFinder strutturaFinder);
	public List<StrutturaPerIterFinder> getStrutturaIter2( StrutturaPerIterFinder strutturaFinder);
	
	/**  REGISTRO_RICHIESTE **/
	public void annullamentoRichiesteNonAutorizzate(RegistroRichiesteFinder registroFinder);
	public Integer countRichiesteNonAutorizzate(RegistroRichiesteFinder registroFinder);
	public void ripristinaRichiesteNonAutorizzate(AssociazOperRichiAutorFinder finder );
 	public void sostituisciAutorizzatore(RegistroRichiesteFinder finder );
	public void ripristinaRichiesteAutorizzate(AssociazOperRichiAutorFinder finder);
	public Integer countRichiesteAttiveGeneral(RegistroRichiesteFinder registroFinder);

	public void sostituzioneRichiedente(RichiedenteCDRFinder richiedenteFinder);
	public void revocaAutorizzatoreDiUnGruppoDiOperatori(String cfPrecedente, String cfAltro, String motivoRevoca, String cfUtenteInSessione);
	public void sostituzioneAutorizzatoreDiUnGruppoDiOperatori(String cfPrecedente, String cfNuovo, String cfAltro, String cfUtenteInSessione);
	public void sostituzioneDelRichiedenteDiUnCDR(String cfPrecedente,String cfNuovo, String cdr, String motivoRevoca,UtenteBean utente);
	
	public boolean inserisciRichiedenteCDR(RichiedenteCDRFinder richiedenteFinder );
	public void aggiornaRichiedenteCDR(RichiedenteCDRFinder richiedenteFinder);
	
	public void aggiornaSoloRichiedenteCDR(RichiedenteCDRFinder richiedenteFinder);

	/** FUNZIONE per la  verifica del mantenimento del ruolo di AUTORIZZATORE **/
	public int step1(String cdrResponsabile);

	/** Richiesta Visibilita **/
	public int inserisciRichiestaVisibilita(RichiestaBean richiestaBean);
	public int inserisciVisibilita(RichiestaBean richiestaBean,List<ProfiloBean> listProfiliSelezionati);
	void inserisciProfiloInVisibilita(ProfiloRichiestaBean profiloRichiestaBean);
	void inserisciProfiloBatch(ProfiloRichiestaBean profiloRichiestaBean);
	public List<OperatoreBean> getOperatoreByCDR(OperatoreFinder operatoriFinder);
	public List<OperatoreBean> getOperatoreByNominativo(OperatoreFinder operatoriFinder);
	public List<OperatoreBean> getDeleganteOdelegatoByCdr(OperatoreFinder operatoriFinder);
	public CDRBean getCDR(String codiceCDRVis);
	public Integer revocaProfiliVisibilita(RichiestaBean richiestaBean,List<ProfiloRichiestaBean> listaProfiliOpInVisi);
	public List<UtenteInVisibilitaBean> getUtentiInVisibilitaBean(ProfiloFinder finder);
	public List<RegistroRichiesteFinder> getStatoRegistroRischiestaByID(RegistroRichiesteFinder finderRegistroRichieste);
	
	
	
	
	public List<ProfiloRichiestaBean> getProfAttUtente(ProfAttUte_x_Uff_di_provenienzFinder mProfAttUte_x_Uff_di_provenienzFinder);
	
	/** FUNZIONALITA' MONITORAGGIO **/
	public ElencoBean getElencoOperatoriMonitoraggio(OperatoreFinder finder);
	public ElencoBean getElencoOperatoriInterimCondUtente(String cf);
	public ElencoBean getElencoOperatoriInterimCond(OperatoreFinder finder);
	
	/** Ambito Applicativo**/
	public AmbitoBean getAmbitoApplicativo(AmbitoFinder ambito);
	
								/** 	MAPPATURA APPLICAZIONI	 **/
	
	
	public List<AmbitoApplicativoEntity> getAmbitoByCodice(List<String> codici);
	public List<ApplicazioniEntity> getApp(List<String> codici);
	public List<ApplicazioniEntity> getAppMappatura(List<String> codici);
	public List<ApplicazioniEntity> getApplicazioni(ApplicazioniFinder finder);
	public List<String> getProfiliAmmApp(ProfiliFinder finder);
	public List<String> getProfiliAmministratore(String app);
	public List<MappaturaUfficiBean> getMappaturaUffici(ProfiliUfficiFinder finder);
	/* GESTIONE TEMPLATE */
	public int inserisciTemplate(BasePaginateFinder finder);
	public boolean inserisciProfiliTemplate(BaseFinder finder);
	public boolean nuovoTemplate(BasePaginateFinder finder);
	public List<ProfiloRichiestaBean> getProfiliXAmbitoeUfficio(ProfAttUte_x_Uff_di_provenienzFinder finder);
	
	
	public int updateTemplate(BasePaginateFinder finder);
	public int cancellaProfiliTemplate(BaseFinder finder);
	public boolean eliminaTemplate(BasePaginateFinder finder);
	public ElencoBean getElencoRicercaTemplate( TemplateFinder finder );
	public ElencoBean getElencoProfiliTemplate( TemplateFinder finder );
	public boolean updateProfiliTemplate(BasePaginateFinder finder);
	public TemplateBean getTemplate( TemplateFinder finder );
	public List<StrutturaUfficioCDRExtBean> getElencoStruttUffCDRVisPerSottoAlberoByCDR(BaseFinder finder); 
	public List<StrutturaUfficioCDRExtBean> getElencoStruttUffCDRVisPerAlberoByElencoCDR(BaseFinder finder);
	public List<StrutturaUfficioCDRExtBean> getElencoStruttUffCDRInVisibilita(CDRBaseFinder finder);
	
	//ASSOCIAZIONE OPERATORE RICHIEDENTE AUTORIZZATORE
	public void setFlagRichAutUtente(OperatoreFinder in); 
	public void updateFlagUtenteCdrDisallienato(OperatoreFinder in); 
	public OperatoreBean getFlagRichAutUtente(OperatoreFinder operatoreFinder);
	public List<OperatoreBean> getRichiedentiByCDR(OperatoreFinder operatoriFinder);
	public List<OperatoreBean> getAutorizzatoriByCDR(OperatoreFinder operatoriFinder);
	
	public Integer getStrutturaIterByCdr(String cdr);
	public Integer getStrutturaIterByCdrIILivello(String cdr);

	public void aggiornaAssociazioni(AssociazOperRichiAutorFinder finderAssocia);
	public void rimuoviAssociazione(AssociazOperRichiAutorFinder finderAssocia);
	public void ripristinaRichieste(AssociazOperRichiAutorFinder finder);
	public void inserisciAssociazioni(AssociazOperRichiAutorFinder finderAssocia);
	
	/** REGISTRO VARIAZIONI DI RUOLO **/
	public ElencoBean getVariazioniDiRuoloUtenti(LeMieVariazioniRuoloCDRFinder finder);
	public List<AmbitoBean> getAmbitoApplicativo();	
	Integer getCountRichieste(RichiestaFinder finder);
	Integer getCountRichiesteRR(RichiestaFinder finder);
	public List<RegioneBean> getRegioniByCodApp(InterrogazioneProfilazioneFinder finder) ;
	public List<ProvinciaBean> getProvinceByCodApp(InterrogazioneProfilazioneFinder finder) ;
	public List<RegioneBean> getRegioniByCodAppMappatura(InterrogazioneProfilazioneFinder finder) ;
	public List<ProvinciaBean> getProvinceByCodAppMappatura(InterrogazioneProfilazioneFinder finder) ;
	public List<TipoUfficioBean> getTipiUfficio(InterrogazioneProfilazioneFinder finder) ;
	public List<TipoCdrBean> getTipiCdr(InterrogazioneProfilazioneFinder finder) ;
	public List<TipoUfficioBean> getTipiUfficioMappatura(InterrogazioneMappaturaProfiliUfficioFinder finder) ;
	public List<TipoCdrBean> getTipiCdrMappatura(InterrogazioneMappaturaProfiliUfficioFinder finder) ;
	
	public List<StrutturaUfficioCDRExtBean> getAlberoCdr(InterrogazioneProfilazioneFinder finder);
	public List<StrutturaUfficioCDRExtBean> getAlberoCdrByRoulGroup(InterrogazioneProfilazioneFinder finder);
	public List<StrutturaUfficioCDRExtBean> getAlberoCdrByRoulGroupMappatura(InterrogazioneProfilazioneFinder finder);
	public List<StrutturaUfficioCDRExtBean> getAlberoUfficioByRoulGroup(InterrogazioneProfilazioneFinder finder);
	public List<StrutturaUfficioCDRExtBean> getAlberoUfficioByRoulGroupMappatura(InterrogazioneProfilazioneFinder finder);
	public List<StrutturaUfficioCDRExtBean> getAlberoUfficioByRoulGroupMappaturaNew();
	public List<ProfiloBean> getListaProfili(InterrogazioneProfilazioneFinder finder);
	public List<ProfiloBean> getListaProfiliAssegnati(InterrogazioneProfilazioneFinder finder);
	public List<ProfiloBean> getListaDescrizioniProfRiepilogo(ProfiloFinder finder);
	public List<ProfiloBean> getListaDescrizioniProfRichRiepilogo(ProfiloFinder finder);
	public List<ProfiloBean> getListaDescrizioniProfRichAggiunti(ProfiloFinder finder);
	public List<ProfiloBean> getListaDescrizioniProfRichRimossi(ProfiloFinder finder);
	public List<ProfiloBean> profiliCompetenzaNewConDesc(ProfiloFinder finder);
	public List<ProfiloBean> profiliCompetenzaNewConDescVisibilita(ProfiloFinder finder);
	public int numProfiliCompSpec(ProfiloFinder finder);
	public String profiloCompetenzaDescrDivisione(String cdr);
	public int getNumeroProfiliCompetenza(ProfiloFinder finder);
	public int getNumeroProfiliCdrVisibilita(ProfiloFinder finder);
	
	public List<String> getEmailPerRuolo(String ruolo);	
	public List<String> getEmailRichiedenteAndAmmLocaliByCdr(String cdr);
	public List<String> ricavaMailSovraordinati(String cdr);

	public List<ExportProfiloBean> getProfiliByTipologia(InterrogazioneProfilazioneFinder finder);
	public List<ExportProfiloBean> getProfiliDisponibiliByAmbitoApp(String codAmbito);	
	public List<ExportProfiloBean> getProfiliBySpecificoUff(InterrogazioneProfilazioneFinder finder);
	public List<ExportProfiloBean> getProfiliBySpecificoCdr(InterrogazioneProfilazioneFinder finder);
	public List<StrutturaUfficioCDRBean> getStruttureCdr(BaseFinder finder);
	public List<StrutturaUfficioCDRBean> getUfficiCdr(BaseFinder finder);
	public List<ExportProfiloBean> getProfiliByAdminGroup(InterrogazioneAdminGroupFinder finder);
	public List<ExportProfiloMappaturaBean> getProfiliByRoleGroup(InterrogazioneAdminGroupFinder finder);
	public List<ExportProfiloMappaturaBean> getProfiliByRoleGroupMainExport(InterrogazioneAdminGroupFinder finder);
	public List<ExportProfiloMappaturaBean> getProfiliByCodiciApplicazioneExport(InterrogazioneAdminGroupFinder finder);
	public List<ExportProfiloMappaturaBean> getProfiliExportSinottico(InterrogazioneAdminGroupFinder finder);
	public List<ExportProfiloMappaturaBean> getProfiliMappaturaByRoleGroupCdrUfficio(ExportMappaturaFinder finder);
	public List<ExportProfiloMappaturaBean> getProfiliMappaturaByRoleGroupTipoCdrTipoUfficio(ExportMappaturaFinder finder);
	public int richiesteRevocaInCorso(OperatoreFinder operatoreFinder);
	public int richiesteInCorsoPerLaCoppiaCFCDR(RegistroRichiesteFinder registroRichiesteFinder);
	public int verificaRichiesteInCorso(RegistroRichiesteFinder registroRichiesteFinder);

	public String getDescrCdrByCdr(String cdr);
	public String getDescrCdrByCdrAncheChiuso(String cdr);
	public String getDescrTipoUff(String codiceTipoUff);
	public String getDescrTipoCdr(String codiceTipoCdr);
	public String getFlagCdrBloccato(String codiceCdr);
	public List<String> getListaCdrByCDRPerRegistri(String cdr);
	public List<String> getListaCdrDiUnUfficioByCDR(String cdr);
	public List<String> getListaCdrDiUnaStrutturaByCDR(String cdr);
	public List<String> getListaCdrDiUnaStrutturaByCDRConChiusi(String cdr);
	public List<String> getRichiedentiCdrSottostanti(String codiceCdr);
	
	
	/**FUNZIONI BATCH 1 Aggiornamento Profili**/ 
 	public List<RichiestaBean> verificaRichiesteInCorso(String cf);
 	public RichiedenteCdR selectCFRichiedenteAndResponsabileHR(String cdr);
 	public Utenti selectEmailUtenteByCF(String cfGenerico);
 	public List<ProfiliAttiviDAO>  selectProfiliAttivi(	String cf); 
 	public void  updateUtentiAggiornatiHR(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
 	public void  updateUtentiAggiornatiHRNoMail(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
 	public void  deleteProfiliAttiviUtente(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
 	public void  updateStoricoProfiliOperatore(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
 	public void  updateProfiliUtenteInVisibilita(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
	public List<UtenteInVisibilita>  selectCdRVisibilitaAndIdRchiestaVisibilita(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
	public AssociazOperRichiAutor  selectCFRichiedenteIstituzionale(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
	public RichiedenteCdR  selectCFIstituzionaleFromRichiedenteCdR(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
 	public String selectEmailByCF(String cfGenerico);
	public AssociazOperRichiAutor  selectCFRichiedenteDiVisibilita(HashMap input); 
	public RichiedenteCdR  selectCFVisibilitaFromRichiedenteCdR(HashMap input); 
 	public void  updateUtentiAggiornatiHRMailRevoca(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
 	public List<String>  selectCodiceCdROfStrutturaAppartenenzaCF(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
 	public Integer  verificaOperatoriAssPuntRichiedente(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
 	public Utenti  selectCdRUtenteCessato(UtentiAggiornatiHRDAO utentiAggiornatiHR); 
	public List<ProfiliAttiviUtente> selectCFProfiliAttiviUtenteByCdRAndProfile(String cdROfStrutturaAppartenenzaCF); 
	public void insertNotificaOperatori  (NotificaOperatoriDAO notificaOperatori); 
	public List <UtentiAggiornatiHRDAO>  selectUtentiAggiornatiHR (); 
	public Integer  countNotificaOperatori(); 
	public void  deleteNotificaOperatori(); 
	public void  updateDataElaUtentiAggiornatiHR(); 
	public List <NotificaOperatoriDAO>  selectDistinctStrutturaAndOggettoFronNotificaOperatori (); 
	public List <NotificaOperatoriDAO>  selectDistinctEmailFronNotificaOperatori (String codiceStruttura); 
	public List <NotificaOperatoriDAO>  selectDistinctAnagraficaUtente (String codiceStruttura); 
	public void deleteStrutturaFromNotificaOperatori (String codiceStruttura); 
	public void  updateUtenteInVisibilita ( UtenteInVisibilita utenteInVisibilita);
	
	
	/**FUNZIONI BATCH 1 AllineamentoResponsabileCdR**/ 
	
	public Integer selectMaxIDAudit (); 
	public void closeCFResponsabileHR(RichiedenteCdR richiedenteCdR);
	public void insertNewCFResponsabileHR(RichiedenteCdR richiedenteCdR);
	public List <CdR> selectCdRAperti (); 
	public RichiedenteCdR selectCFResponsabileHR (String codiceCdR);
	public Utenti selectUtenteByCFLdap (String responsabile);
	public void insertUtenti(HashMap <String,String> input);
	public void insertStoricoCdRUtenti(HashMap <String,String> input);
	public void insertStoricoCdRUtentiWithStartDate(HashMap <String,String> input);	
	public void insertStoricoCdRUtenti_Gen(HashMap <String,String> input);
	public void insertUtentiAggiornatiHR(HashMap <String,String> input);
	public void setFlagRichiedenteSI(String responsabile);
	public void setFlagAutorizzatoreIIlivelloSI(String responsabile);
	public void setFlagAutorizzatoreIlivelloSI(String responsabile);
	public Utenti verifyAutorizzatoreByOldCFResponsabile(String cfResponsabileHR);
	public void setFlagAutorizzatoreNO(Utenti oldCFUtente);
	public Utenti verifyAutorizzatoreByNewCFResponsabile (String responsabile);
	public Integer  verifyRuoloAutorizzatoreILivByCdR (String codiceCdR); 
	public Integer  verifyRuoloAutorizzatoreILivByCdRorTipoCdR (String codiceCdR);
	public Integer  verifyRuoloStrutturaIter(String codiceCdR);
	public Integer  verifyRuoloAutorizzatoreIILivByCdRorTipoCdR (HashMap <String,String> hashMapForQuery);
	public Integer  verifyRuoloAutorizzatoreIIStrutIter(HashMap <String,String> hashMapForQuery);
	public void  setCFAutorizzatoreIIlivelloRegistroRichieste(HashMap <String,String> hashMapForQuery);
	public void setFlagAutorizzatoreSI(Utenti newCFUtente);
	public List<RelazioneCdRUfficioStrut>  selectCodiceCdROfStrutturaAppartenenzaCF(String codiceCdR);
	public void setCFAutorizzatoreIlivelloRegistroRichieste (HashMap <String,String> hashMapForQuery);

	
	/**FUNZIONI BATCH 1 VariazioneDatiUtenteVersioneRidotta**/ 
	public List<RichiedenteCdR> selectCdRFromRichiedenteCdRByCFRichiedente(HashMap<String , String> input) ;
	public List<RichiedenteCdR> selectCdRFromRichiedenteCdRByCFAutorizzatore(HashMap <String,String> input);
	public List<AssociazOperRichiAutor> selectCFAutorizzatoreByCFRichiedente(HashMap <String,String> input);
	public List<AssociazOperRichiAutor> selectCFRichiedenteByCFAutorizzatore(HashMap <String,String> input);
	public List<StrutturaIterDAO> verifyAutorizzatoreIORIILivello(HashMap <String,String> input);
	public void updateStrutturaIter(HashMap <String,String> input);
	public void insertStrutturaIter( StrutturaIterDAO strutturaIter);
	public List<RegistroRichieste> selectCdRAndCFUtenteFromRegistroRichiesteByCFAutorizzatore (HashMap <String,String> input);
	public Integer  verifyDelegato ( String cfDelegato);
	public Integer  countAssociazOperRichiAutor  (HashMap <String,String> input);
	public void updateUtentiForVerificaOperatore(Utenti utentiForOperatore); 
	public void  annullamentoRichieste(HashMap <String,String> input);
	public List<RegistroRichieste> selectCFPresaINCaricoFromRegistroRichieste (HashMap <String,String> input); 
	public Utenti  selectCFbyCFPresaInCaricoFromUtenti(String cfPresaInCarico); 
	public void  insertAuditOperazioni(HashMap <String,String> input);
	public void  insertAuditOperazioni(String testo);
	public void updateGestoreOperatore (HashMap <String,String> input);
	public List<CauUserIdOrgName> selectAllUtentiCTSA ();
	public Integer countUtenteByCF (String userId);
	public Integer countUtenteByCFClosed (String userId);
	public void insertUtentiSiga (HashMap <String,String> input);
	public Utenti selectUtenteByCF(String cfGenerico);
	public void  updateStoricoCdrUtenti (Utenti utentiSiga);
	public void updateUtenti (Utenti utentiSiga);
	public List<Utenti> selectAllCFUtenti ();
	public void  updateUtentiPerCessazione (String codiceFiscaleUtenti);
	public void  updateUtentiPerRipristino (HashMap<String , String> input );
	public void updateStoricoCdrUtentiPerCessazione(Utenti utentiSiga);
	public AssociazOperRichiAutor selectCFRichiedenteEAutorizzatoreByCFOperatore (HashMap <String,String> input);
	public void closeAssociazOperRichiAutorByCFOperatore(HashMap <String,String> input);
	// 1.6 2023 -->
	public List<String> selectUtentiByCdrDisall();
	// 1.6 2023 <--
	public RelazioneCdRUfficioStrut strutturaNew(HashMap <String,String> input);
	public RelazioneCdRUfficioStrut  strutturaOld(HashMap <String,String> input);
	public Integer countUtenteCTSAByCF(String codiceFiscaleUtenti);
	public void insertUtentiAggiornatiHR_Var(HashMap <String,String> input);
	public List<RegistroRichieste> selectIdRichiestaFromRegistroRichieste (RegistroRichieste registroRichieste);
	public int inserisciAllegatoOperatoreEsterno(AllegatoBean allegatoBean);
	public List<NotificaOperatoriDAO> utentiRichiesteDaSegnalare(HashMap<String, String> input);
	
	/**FUNZIONI BATCH 1 AllineamentoOrganizzazioni**/ 
	
	public String selectRegione(String provincia); 
	public Integer countUfficio(HashMap<String , String> inputUfficio); 
	public Integer countTipoUfficio(HashMap<String , String> hashMapVerticeStrutturaEUfficio); 
	public Integer countTipoCdR (CdR cdrVerticeUfficio); 
	public void insertTipoCdR(CdR cdrVerticeUfficioEStruttura); 
	public Integer countCDRSiga (String orgName); 
	public void insertTipoUfficio(HashMap<String , String> hashMapVerticeStrutturaEUfficio ); 
 	public CdR selectCDRFatherSiga (String orgName); 
	public void insertRelazioneCdRUfficioStrut(RelazioneCdRUfficioStrut relazioneCdRVerticeUffStrutturaUfficioStrutt); 
	public Integer countStruttura(HashMap<String , String> hashMapVerticeUfficioEStruttura); 
	public Integer countTipoStruttura(HashMap<String , String> hashMapVerticeUfficioEStruttura); 
	public void insertTipoStruttura(HashMap<String , String> hashMapVerticeUfficioEStruttura); 
	public void insertCdR (CdR cdrVerticeStruttura); 
	public void insertStruttura (HashMap<String , String> inputStruttura); 
	public void closeRelazioneCdRUfficioStrut (RelazioneCdRUfficioStrut relazioneCdRUfficioStr); 
	public void insertRelazioneCdRUfficioStrutPerVariazione (RelazioneCdRUfficioStrut relazioneCdRUfficioStr); 
	public void  updateCdR(CdR  updateCdrWithNewFather);
	public void  closeCdr(  HashMap <String,String> hashMapCodiceCdR );
	public List<RelazioneCdRUfficioStrut> selectAllCdRFromRelazioneCdrUfficioStrut (OrganizzazioniFinder orgFinder);
	public Integer countCdrCau(CdR  cdr);
  	public void  closeUfficio( HashMap <String,String> hashMapCodiceCdR);
	public void  updateTemplate(  HashMap <String,String> hashMapCodiceCdR);
	public void  insertUfficio(  HashMap <String,String> inputUfficio);
	public RelazioneCdRUfficioStrut  selectCdRFromRelazioneCdrUfficioStrut(String orgName);
	public void closeRichiedenteCdr( HashMap <String,String> hashMapCodiceCdR);
	public void closeStruttura( HashMap <String,String> hashMapCodiceCdR);
	public List<RichiedenteCdR> getAllAutI(String input);
	public List<RichiedenteCdR> getAllAutII(String input);
	public List<RichiedenteCdR> getAllRich(String input);

	public List<String> selectAllUtentiRichiAutResp();
	public void aggiornaFlagUtente( Utenti ute);
	
	public List<Utenti> countRegistroRichieste (HashMap<String, String> input); 
	public  Struttura verificaAmministratori (String codiceCdR);
	public List<String> selectEmailUtenteByCdRIILivGerarchico(String  struttura); 
	public List<String> selectEmailAmministratoriCentrali (); 
	
	public List<RelazioneCdrUfficioStrutOrganizz> getListaRelazCdrUffStrut();
	public void updateRelazioneCdrUfficioStruttura(RelazioneCdrUfficioStrutOrganizz rel);
	public void  closeItenteInVisibilita_Org(  HashMap <String,String> hashMapCodiceCdR);
	public void  closeAdminGroupCdrUff_Org(  HashMap <String,String> hashMapCodiceCdR);	
	
	/** OPERATORI ESTERNI **/
	public Integer countPresenzaOperatoreCancellato(OperatoreBean operatoreBean);
	public void aggiornaUtente(OperatoreBean operatoreBean);
	public void inserisciUtente(OperatoreBean operatoreBean);
	public void inserisciOperatoreEsterno(OperatoreBean operatoreBean);
	public void inserisciRelazioniTipologieOperatoreEsterno(OperatoreBean operatoreBean);
	public void inserisciAllegatiOperatoreEsterno(OperatoreBean operatoreBean,Set<FormFile> allegati);	
	public List<TipologiaUtenteBean> getTipologieUtenti(TipologiaUtenteBean tipologiaUtenteBean);
	public boolean inserisciOperatoreEsternoTipoUtente(OperatoreEsternoTipoUtenteBean operatoreEsternoTipoUtente); 
	public List<TipologiaUtenteBean> getProfiliDisponibiliByTipoOperatoreEsterno(OperatoreFinder operatore);
	public List<TipologiaUtenteBean> getProfiliNonAmmessiByTipoOperatoreEsterno(OperatoreFinder operatore);
	public List<OperatoreBean> getOperatoriEsterni(OperatoreBean operatore);
	public List<TipologiaUtenteBean> getTipologieAttiveOperatoreEsterno(OperatoreBean operatore);
	public List<AllegatoBean> getAllegatiOperatoriEsterni(AllegatoBean allegatoBean);
	public AllegatoBean getAllegatoOperatoreEsterno(Integer id);
	public void aggiornaOperatoreEsterno(OperatoreBean operatore);
	public void aggiornaEmailUtente(OperatoreBean operatore);
	public void cancellaRelazioneTipologiaOperatoreEsterno(OperatoreEsternoTipoUtenteBean operatoreEsternoTipoUtenteBean);
	public void inserisciRelazioneTipologieOperatoreEsterno(OperatoreEsternoTipoUtenteBean operatoreEsternoTipoUtenteBean);
	public void inserisciNoteCancellazioneOperatoreEsterno(OperatoreBean operatore);
	public void cancellaUtente(OperatoreBean operatore);	
	public List<ProfiloSimpleBean> getProfiliAttiviByCFAndCodiceTipologiaLimitata(OperatoreBean operatoreBean);
	public List<ProfiloSimpleBean> getProfiliAttiviByCF(OperatoreBean operatoreBean);
	public List<CDRBean> getCdrInfo(CDRFinder finder);	
	public List<String> getCdrAmmLocaleDaDisabilitare(CDRFinder finder);	
	public List<ProfiloBean> getProfiliAmmessiOperatoreEsterno(OperatoreBean operatore);
	public List<ProfiloBean> getElencoProfiliCDR(CDRFinder finder);
	public List<TipologiaUtenteBean> getElencoTipologieUtenti();
	public List<ProfiloSimpleBean> getProfiliAttiviPerTipologiaByCF(OperatoreBean operatore);
	public void chiudiStoricoCdrUtente(OperatoreBean operatore);
	public List<ProfiloSimpleBean> getProfiliScopertiByCFAndTipologieBloccate(OperatoreBean operatore);
	
	
	
	/** MAPPATURA PROFILI UFFICIO **/
	public void insertMappaturaCdrUff (MappaturaProfiloUfficioBean mappaturaBean);
	public void insertMappaturaTipoUffCdr (MappaturaProfiloUfficioBean mappaturaBean);
	public void cancellaMappaturaTipoUffCdr (MappaturaProfiloUfficioBean mappaturaBean);
	public void updateMappatura(MappaturaProfiloUfficioBean mappaturaBean);
	public String getDescrizioneRegione(InterrogazioneRegioniProvinceFinder finder);
	public String getDescrizioneProvincia(InterrogazioneRegioniProvinceFinder finder);
	public String getCodiceRegione(InterrogazioneRegioniProvinceFinder finder);
	public String getCodiceProvincia(InterrogazioneRegioniProvinceFinder finder);
	public List<ApplicazioniEntity> getRoleGroupsByCodiceApplicazione(InterrogazioneAdminGroupFinder finder);
	
	/** BATCH **/
	public List<String> getElencoAmministratoriLocali(String cdr);
	
	/** BATCH COERENZA **/
	public Integer getNumeroUtenti();
	public void inserisciRichiestaBatchOE(RichiestaAbilitazioneDisabilitazioneBean richiestaBean);
	public void incrementaTentativoRichiestaBatchDaElab(String idRichiesta);
	public CaricamentoMassivoEntity cancellaRichiestaBatchDaElab(String idRichiesta);
	public Integer getTentativiRichiestaBatchDaElab(String idRichiesta);
	public List<String> getListaCodiciIILiv(Map<String,Integer> queryMap);
	public ResponsabilitaBatch getResponsabilitaDaElaborare();
	public ResponsabilitaOA getCodeApplAndRoleA(ResponsabilitaFinder finder);
	public void  insertResponsabilita(ResponsabilitaOA input);
	public void  cancellaProfilo(ResponsabilitaOA input);
	public void  cancellaAppProfRoleGroup(ResponsabilitaOA input);
	public void  updateProfilo(ResponsabilitaOA input);
	public void  updateResponsabilita(ResponsabilitaOA input);
	public void  cancellaAgCdrUff(ResponsabilitaOA input);
	public void  insertProfilo(ResponsabilitaOA input);
	public void  insertAppProfRole(ResponsabilitaOA input);
	public Integer countProfilo(ResponsabilitaOA input);
	public Integer countProfiliRichiesta(ResponsabilitaOA input);
	public Integer countAppProfRole(ResponsabilitaOA input);
	public Integer countAGCdrUff(ResponsabilitaOA input);
	public Integer countCdrUff(ResponsabilitaOA input);
	public void  insertAGCdrUff(ResponsabilitaOA input);	
 	public List <ResponsabilitaOA> getRelCdrUffStrutt(ResponsabilitaFinder input);
 	public List <ResponsabilitaOA> getResponsabilitaOA(ResponsabilitaFinder input);
 	public long getSequenceFromRichiesteBatch();
 	public long getCurrSequenceFromRichieste();
 	public List<String> getCodiciStrutturaByCodUff(String codiceUfficio) ;
 	public List<Struttura> getCodCdrCodStrutturaByCodUff(String codiceUfficio) ;
  
 	/** BATCH CESSAZIONE MOBILITA **/
 	public List<RichiestaBatchBean> getRichiesteBatchCessazioneUtentiRiorg(List<String> eventiList);
 	public List<RichiestaBatchBean> getRichiesteBatchCessazioneUtenti(List<String> eventiList);
 	public CDRBean getCdrInfoByCodice(String cdr);
 	public EventoBean getEvento(String codiceEvento);
 	public void cessaProfili(ProfiloRichiestaBean input, boolean mobilita);
 	public List<String> getAmbitiByCf(String cfProfilo);
 	public String getCodCdrByCf(String codiceFiscale);
 	public String getCodCdrByCfToday(String codiceFiscale);
 	public void inserisciRegistroRichiesta(RegistroRichiesteBatchBean regRichBetchBean);
 	public List<ProfiliAttiviUtente> getListaProfiliByCfAmbito(Map queryMap);
 	public void inserimentoProfiloRichiesta(it.finanze.scheduler.bean.ProfiloRichiestaBean profRichBetchBean);
 	public void cancellaRecordRichiesteBatch(Map queryMap);
 	public void annullamentoRichieste(String cfUtente);
 	public void aggiornaUtentiVisibilitaMobilita(ProfiloRichiestaBean richMob);
 	public void aggiornaProfiliUtenteVisibilita(ProfiloRichiestaBean richMob);
 	
 	public void updateUtente(String cfUtente);
 	public void updateOperatoreEsterno(String cfUtente);
 	public String getCodiceCdrPerCancellazioniCau(String cfUtente);
 	public  List<RichiestaBean> getRichiesteDaAnnullare(Map queryMap);
 	public List<RichiestaBean> getRichiesteNonEseguite(Map queryMap);
 	public List<RichiestaBean> getRichiesteNonIntegrateGestore(Map queryMap);
 	
	public List<ProfiliAttiviUtenteOperazioneBean> getProfiliAttiviUtenteByCdr(HashMap<String,String> queryMap);
	public List<ProfiliAttiviUtente> getProfiliAttiviUtenteByCdrGestRete(HashMap<String,String> queryMap);
	public void cancellaUtenteDaTabellaRiorganizzazione(String codFisc);
	public void insertUtenteInTabellaRiorganizzazione(String codFisc);


 	//batch richieste
 	
	// lista CDR per Amm Centrale
 	public List<OperatoreBean> getListaCDRDisponibiliByCf(String cfOperatore);
	 
	//BATCH GENERA RICHIESTE
 	public EventiSistemaBean getEventiSistema(String in);
	public  ProfiloRichiestaBean  getCodApplicazione(String profilo);
	public  String  getCdr(String cf);
	public 	List<RichiestaBatchBean> getRichiesteBatch (RichiestaFinder input ) ;
	public 	List<RichiestaBatchBean> getRichiesteBatchDistinct (RichiestaFinder input ) ;
	public 	List<RichiestaBatchBean> getRichiesteBatchToRichieste (RichiestaFinder input ) ;
	public 	Integer countRichieste (Integer input ) ;
	public 	void inserisciRichiestaBatch( RichiestaBean in ) ;
	public 	void aggiornaRichBatchToElab( RichiestaFinder input ) ;
	public 	void eliminaRichesteBatch( RichiestaFinder input ) ;
	public List<RichiestaBatchBean> getRichiesteToElab(RichiestaFinder input);
	public List<ProfiloAbilitazioneBatchBean> getAbilitazioniBacth();
	// mio profilo
	public List<InterrProfiliOperatoreBean> getElencoProfiliOperatoreGroupCdr(InterrProfiliOperatoreFinder finder);

	/** Caricamenti Massivi **/
	public List<String> getAnniCaricamento(String codiceFiscale);
	public List<CaricamentoMassivoEntity> getElencoCaricamentiUtentiPerAnno(InterrogazioneCaricamentiFinder finder);
	public CaricamentoMassivoEntity getDettaglioCaricamento(InterrogazioneCaricamentiFinder finder);
	public long getSequenceFromRichiesteCaricamentoMassivo();
	public void inserisciRichiestaCaricamentoMassivo(CaricamentoMassivoEntity cmEntity);
	public List<FileAbilitazioneBean> getRecordsFileAbilitazioni(InterrogazioneCaricamentiFinder finder);
	public List<FileAbilitazioneBean> getRecordsCmVisibilitaFile(InterrogazioneCaricamentiFinder finder);
	public void avvioImmediatoControlli(String idCaricamento);
	public ControlliRecCaricMassiviBean getLimitiRecordControlliMassivi();
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamento(InterrogazioneCaricamentiFinder finder);
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamentoNoBlob(InterrogazioneCaricamentiFinder finder);
	public AllegatoCaricamentoMassivoEntity getAllegato(InterrogazioneCaricamentiFinder finder);
	public void annullaRichiestaCaricamentoMassivo(CaricamentoMassivoEntity cmEntity);
	public void updateControlloRichiestaCaricamentoMassivo(CaricamentoMassivoEntity cmEntity);
	public EventoBean getEventoCM(String codiceEvento);
//	public CoerenzaPropertiesBean selectPropertiesBatchCoerenza();
	public CoerenzaPropertiesBean selectCostantiSigaBatchCoerenza();
	public void updateStatoRichiestaCaricamento(InterrogazioneCaricamentiFinder finder);
	public void updateStatoRichiestaCarMassVis(InterrogazioneCaricamentiFinder finder);
	public List<FileMappaturaBean> getRecordsCmMappatureFile(InterrogazioneCaricamentiFinder finder);
	
	//GESTIONE BATCH UFFICI PILOTA
	public List<String> getListaUtentiByCdr(String codiceCdr);
	public List<ProfiliAttiviUtente> getProfiliAttiviUtenteByCdrProfCf(String codiceProfilo, String codiceCdr, String codiceFiscale);	
	public List<UtentiAggiornatiHRDAO> getStruttureORAUteCessMob();
	public List<UtentiAggiornatiHRDAO> getStruttureORAsi();
	public List<UtentiAggiornatiHRDAO> getStruttureORAno();
	public void updateEmailUtentiAggiornatiHR(UtentiAggiornatiHRDAO input) ;
	public String selectTrattamento(String input);
	
	public List<UtentiAggiornatiHRDAO> selectUtentiDaAvvisare();
	public List<String> getEmailAmmLocaleByCdr(String input);
	public void inserisciAllegatiCaricamentoMassivo(Set<FormFile> allegati, String tipo, long idCaricamento);

	public Struttura getCentralePeriferico(String input);
	public String isCentralePeriferico(String input);

  	public List<String> getCodUffByCodCrdIILiv(String input);
	public void  annullamentoRichieste2(HashMap <String,String> input);

	public Integer verificaEsistenzaCf(String cf);
	public Integer verificaEsistenzaRG(String codRg);
	public List<ProfiloCM> getProfiliCM(String codiceProfilo);
	public List<String> getCodiciAppCM(HashMap input);
	public Integer verificaEsistenzaCdr(String cdr);
	
	//Caricamento Massivo Mappatura Abilitazioni Ufficio
	public Integer verificaEsistenzaUfficio(String ufficio);
	public Integer verificaEsistenzaRegione(String regione);
	public Integer verificaEsistenzaProvincia(String provincia);
	public Integer verificaEsistenzaCodUfficio(String codUfficio);
	
	//Assegnazione gestori operatori
	public List<AssegnazioneGestoriOperatoriEntity> getListaApplAssegnazioniOperatori(ApplicazioniFinder finder);
	public List<GestoreOperatoriBean> getListaGestoriOperatori(GestoreOperatoriFinder finder);
	public List<ApplicazioniEntity> getListaTotaleApplicazioni(ApplicazioniFinder finder);
	public List<ApplicazioniEntity> getListaTotaleApplicazioniAL(ApplicazioniFinder finder);
	public void cancellaGestoreOperatori(GestoreOperatoriBean gestore);
	public void insertGestoreOperatoriGruppo(GestoreOperatoriBean gestore);
	public void insertGestoreOperatori(GestoreOperatoriBean gestore);
	public List<GestoreOperatoriBean> getListaGestoriOperatoriByCodStr(GestoreOperatoriFinder finder);
	public List<GestoreOperatoriBean> getListaGestoriOperatoriByCodCdr(GestoreOperatoriFinder finder);
	public void aggiornaPresaInCaricoGestoreOperatori(GestoreOperatoriBean gestore);
	public Integer getNumeroGestori(GestoreOperatoriFinder finder);
	public List<OperatoreBean> getListaUtentiNuovoGestore(OperatoreFinder finder);
	public List<CDRBean> getCdrListByStr(GestoreOperatoriFinder codiceStr);
	public List<OrigineRichiestaBean> getListaOrigineRichiesta ();
	
	//gestione start batch
	public int getStatoBatch(String nomeBatch);
	public  String  getEmailBatch();
	public void aggiornaStatoBatch(String nomeBatch);
	public String  getTipoCdrHR(String cdr);
	
	public void updateUfficioXTipoCdrHR(HashMap input);
	public void updateStrutturaXTipoCdrHR(HashMap input);
	public String selectCdRApertiByCdr(String cdr);
	public Integer countTipoUtente(String cdr);
	public List<Utenti> getDatiUtenteByCdr(String cdr);
	//gestione utenti sogei
	public ElencoBean getElencoUtentiSogei(BaseFinder in);
	public ElencoBean getElencoUtentiAmm3(BaseFinder in);
	public ElencoBean getAmmRegLoc(String in);
	
	public boolean isCdRVerticeStruttura(String codiceCdR);
	public String getCodiceCdRByCodiceStruttura(String codiceStrutt);
	public List<String> getVerticeStrutturaAmmLoc(CDRFinder finder);
	Integer countCdrPadre(String orgName);
	public void inserisciPresaInCaricoGestoreOperatori(GestoreOperatoriBean gestore);
	public void cancellaPresaInCaricoGestoreOperatori(String cf);
	public String getCodApplByIdRichiesta(Integer finder);
	
	List<ProfiloRichiestaBean> getProfiliRichiesta(RichiestaFinder finder);
	
	public String getVerticeStrutturaFromCdr(String codiceCdR);
	public List<StatoRichiestaBean> getStatiRichiesta();
	public String getCdrPadreGerarchia(String cdrOperatore);
	public List<InterrProfiliOperatoreBean> getElencoProfiliOperatoreGroupCdrRichiesta(InterrProfiliOperatoreFinder finder);
	public List<InterrProfiliOperatoreBean> getElencoProfiliOperatoreGroupCdrRichiestaDisabilitati(
			InterrProfiliOperatoreFinder finder);
	public List<TipoAbilitazioneBean> getTipoAbilitazione();
	public List<StrutturaUfficioCDRBean> getElencoCdrFromStruttureList(BaseFinder finder);
	public List<StrutturaUfficioCDRBean> getElencoStruttureStrutturaIIliv(String strutturaIILiv);
	public List<StrutturaUfficioCDRBean> getElencoCdrDiUnaStruttura(BaseFinder finder);
	
	
	public List<String> getElencoArchiviazioneDaRichiesteRA(RichiestaFinder in);
	public List<String> getElencoAutorizzatori1LivelloTotaliDaRichiesteRA(RichiestaFinder in) ;
	public List<String> getElencoGestoriOperatoriDaRichiesteRA(RichiestaFinder in)	;
	public List<String> getElencoCfUtentiDaRichiesteRA(RichiestaFinder in) ;
	public List<String> getElencoRichiedentiTotaliDaRichiesteRA(RichiestaFinder in) ;
	public List<String> getElencoAutorizzatori2LivelloTotaliDaRichiesteRA(	RichiestaFinder in) ;
	public List<StrutturaUfficioCDRExtBean> getElencoPerAlberoStrutture(
			BaseFinder finder);
	
	public List<BatchAbilitazioniRichiestaFinder> getRichiesteAbilitazioniVis(BatchAbilitazioniRichiestaFinder input);
	public int  cancellaRichestaAbilitazioniVis(BatchAbilitazioniRichiestaFinder input);
	public int inserisciRichiestaAbilitazioniVis(BatchAbilitazioniRichiestaFinder input);
	public List<CDRBean> getElencoSecondiLivelli();
	public List<ProfiloRichiestaBean> getElencoProfiloProfilazioneUfficioDaCdRList(ProfiloFinder input);
	public List<ProfiloRichiestaBean> getElencoProfiloProfilazioneUfficioIILiv(ProfiloFinder input);
	public String getValoreCostante(String input);
	public List<ProfiloRichiestaBean> getElencoProfiliUfficioDaCdRList(ProfiloFinder input);
	public List<ProfiloRichiestaBean> getElencoProfiliUfficioIILiv(ProfiloFinder input);
	public int getIdBatchAbilitazioniRichiesta();
	public boolean inserisciFiltriRicerca(BatchAbilitazioniRichiestaFinder input);
	public AllegatoBean getFileBatchAbilitazioni(BatchAbilitazioniRichiestaFinder finder);
	public List<ExportProfiloBean> getElencoProfiliBatch(BatchAbilitazioniRichiestaFinder finder);
	public String getDescrizioneRG(String codice);
	public String getDescrizioneAbilitazione(String codice);
	public List<Profilo> getApplicazioniProfiliAmmCenAppl(String codFiscale);

	
	// Aggiornamento Massivo Richieste Visibilite'
	public void inserisciRichiestaAggiornamentoMassivoVisibilita(CaricamentoMassivoEntity cmEntity);
	public long getSequenceFromRichiesteAggiornamentoMassivoVisibilita();
	public List<CaricamentoMassivoEntity> getElencoCaricamentiAggiornamentiVisibilitaUtenti(InterrogazioneCaricamentiFinder finder);
	public ControlliRecCaricMassiviBean getLimitiRecordControlliMassiviPerEvento(HashMap<String, String> input);
	public CaricamentoMassivoEntity getDettaglioCaricamentoAggVis(InterrogazioneCaricamentiFinder finder);
	public void annullaRichiestaCaricamentoMassivoAggVis(CaricamentoMassivoEntity cmEntity);
	public void updateStatoRichiestaCaricamentoAggVis(InterrogazioneCaricamentiFinder finder);
	public void avvioImmediatoControlliAggVis(String idCaricamento);
	public void updateControlloRichiestaCaricamentoMassivoVisib(CaricamentoMassivoEntity cmEntity);
	public List<FileAbilitazioneBean> getRecordsVisibilitaFile(InterrogazioneCaricamentiFinder finder);
	public List<ExportProfiloBean> getProfiliDisponibiliVisibByAmbitoApp(String codAmbito);
	public void inserisciAllegatiAggiornamentoMassivoVisibilita(Set<FormFile> allegati, String tipo, long idCaricamento);
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamentoAggVisNoBlob(InterrogazioneCaricamentiFinder finder);
	public AllegatoCaricamentoMassivoEntity getAllegatoAggVis(InterrogazioneCaricamentiFinder finder);
	public List<ExportProfiloBean> getProfiliDisponibiliVisibAmmCentrApp(ProfiloFinder finder);
	public List<ExportRegioneBean> getElencoRegioni();
	public List<ExportProvinceBean> getElencoProvincie();
	public List<ExportTipologiaUfficioBean> getElencoTipologieUfficio();
	
	// Aggiornamento Puntuale Visibilite'
	public List<String> getElencoFigliAreaEca(String codice);
	public List <ProfiloUtenteInVisibilitaBean> getListDateScadenzaVisibilita(ProfiloFinder finder);
	public void inserisciProfiloUtenteInVisibilita(ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilita) ;
	public void deleteProfiloUtenteInVisibilita(ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilita);
	public void inserisciProfiliVisibilitaTracciamento(ProfiloVisibilitaTracciamentoBean profVisTracBean) ;
	public void updateDataFineValInVisibilita(ProfiloUtenteInVisibilitaBean finder) ;
	public void updateDataFineValCdr(ProfiloUtenteInVisibilitaBean finder);
	public void setDataFineValUtenteVis(ProfiloUtenteInVisibilitaBean finder);
	public String get_codiceUfficio_uffStrut(ProfiloFinder finder);

	
	//	Abilitazioni Degli operatori
	public List<ProfiloRichiestaBean> getElencoRGWeb(ProfiloFinder input);
	public List<ProfiloRichiestaBean> getElencoProfiliWeb(ProfiloFinder input);
	public List<AmbitoBean> getElencoAmbito(ProfiloFinder input);
	
	public String getDescrizioneStrutturaFromCodiceStruttura(BatchAbilitazioniRichiestaFinder finder);
	public List<StrutturaUfficioCDRBean> getStruttureAL(String cf);
	
	public List<StrutturaUfficioCDRBean> getElencoStruttureDaIILiv(String codiceIILiv);
	public List<StrutturaUfficioCDRBean> getElencoCdRDaStrutt(CDRBaseFinder finderCdr);
	public List<ExportProfiloBean> getProfiliExport(BatchAbilitazioniRichiestaFinder finder);
	// 4.5.3
	public List<ExportProfiloBean> getProfiliExportOp(String cf);
	//<--
	public ElencoBean getElencoOperatoriInVisibilitaAggornamento(OperatoreFinder finder);
	
	public  List<BatchInfoBean>  selectBatchInfo (BatchInfoBean input);
	public void updateBatchInfo (BatchInfoBean input);
	
	public  void updateElaborazioneRichiestaBatch(RichiestaBatchDaElaborareBean input)  ;
	public  void updateElaborazioneRichiesteBatch( Integer input)  ;
 

	public String getEmailAssistenza();
	public String isElaborabileSIGA3(String codiceCdR);
	
	
	public ArrayList <MappaturaProfiloUfficioBean> getProfiliNonPrevistiAG_Query1( );
	public Integer getProfiliNonPrevistiAG_Query2(HashMap<String, String> input);
	public Integer getProfiliNonPrevistiAG_Query3(HashMap<String, String> input);
	
	public StrutturaUfficioCDRExtBean verificaCdRPadreUfficio(String cdr);
	public int countAltreDeleghe(DelegaFinder finder);
	
	public List<ElementoCatalogoBean> getElencoCatalogoFromProfilo(String codiceProfilo);
	public AbilitazioneBean getProfiloInfo(String codiceProfilo);
	public List<AbilitazioneBean> getProfiloInfo2(String codiceProfilo);
	public List<ApplicazioneUtenteBean> getElencoRGApplicazioneUtente(CatalogoAbilitazioniFinder finder);
	public List<AbilitazioneBean> getElencoAbilitazioniCatalogo(CatalogoAbilitazioniFinder finder);
	public List<RoleGroupBean> getRoleGroup();
	public List<AbilitazioneBean> getElencoAbilitazioniCatalogo2(CatalogoAbilitazioniFinder finder);
	public List<AbilitazioneBean> getElencoAbilitazioniCatalogo3(CatalogoAbilitazioniFinder finder);
	public AbilitazioneBean getAbilitazione(CatalogoAbilitazioniFinder finder);
	public AbilitazioneBean getAbilitazione2(CatalogoAbilitazioniFinder finder);
	public List<FunzioneBean> getElencoFunzioneBean(CatalogoAbilitazioniFinder finder);
	public List<ApplicazioneUtenteBean> getElencoAppUtenteFromRoleGroup(CatalogoAbilitazioniFinder finder);
	public List<RaggruppamentoFunzionaleBean> getElencoRaggrFunzFromApp(CatalogoAbilitazioniFinder finder);
	public int updateAppUtenteAbilitazione(CatalogoAbilitazioniFinder finder);
	public int updateNotaAbilitazione(CatalogoAbilitazioniFinder finder);
	public int updateProfiloCatalogo(CatalogoAbilitazioniFinder finder);
	public int cancellaFunzione(CatalogoAbilitazioniFinder finder);
	public int modificaFunzione(CatalogoAbilitazioniFinder finder);
	public int inserisciFunzione(CatalogoAbilitazioniFinder finder);
	public List<AbilitazioneBean> getElencoAbilitazioniCatalogoExport(CatalogoAbilitazioniFinder finder);
	public List<ApplicazioneUtenteBean> getElencoApplicazioniCatalogo(CatalogoAbilitazioniFinder finder);
	public List<AbilitazioneBean> getElencoFunzioniCatalogo(CatalogoAbilitazioniFinder finder);
	public List<AbilitazioneBean> getElencoFunzioniCatalogoExport(CatalogoAbilitazioniFinder finder);
	public int countRoleGroupNoFunzioni(CatalogoAbilitazioniFinder finder);
	public int countRoleGroupModificati(CatalogoAbilitazioniFinder finder);
	public int cancellaRaggruppamentoFunzionaleProfilo(CatalogoAbilitazioniFinder finder);
	public int updateNotaFlagRoleGroup(CatalogoAbilitazioniFinder finder);
	public int updateFlagsNotaAllegatoProfilo(CatalogoAbilitazioniFinder finder);
	public int inserisciRaggruppamentoFunzionaleProfilo(CatalogoAbilitazioniFinder finder);
	public int countAbilitazioneAppUtente(CatalogoAbilitazioniFinder finder);
	public List<ApplicazioneCatalogoBean> getElencoApplicazioniCatalogoAllExport();
	
	public List<ProfiloBean> getElencoAbilitazioniAC(ProfiloFinder finder);
	public List<ProfiloBean> getElencoAbilitazioniVisAC(ProfiloFinder finder);
	public List<ProfiloIncompatibilitaBean> getElencoProfiliIncompatibili(ProfiliIncompatibiliFinder finder);
	// 1.3 2023 -->
	public Integer getProfiloVisibilitaStruttura(String codiceCdr);
	// 1.3 2023 <--
	// 4.5.9 II -->
	public List<ProfiloRichiestaBean> getElencoProfili();
	public List<ProfiloIncompatibilitaBean> getElencoProfiliIncompatibiliGest(ProfiliIncompatibiliFinder finder);
	public int numProfiliIncompatibilita(ProfiliIncompatibiliFinder finder);
	public int updateProfiloIncompatibilita(ProfiliIncompatibiliFinder finder);
	public int insertProfiloIncompatibilita(ProfiliIncompatibiliFinder finder);
	// 4.5.9 II <--
	public List<Integer> getTipologiaUtenteEsterno(String cf);
	public List<ProfiloBean> getElencoAbilitazioniRichiedente(ProfiloFinder finder);
	public List<ProfiloBean> getElencoAbilitazioniVisRichiedente(ProfiloFinder finder);
	public String getIdIter(ProfiloBean finder);
	public int verificaIter(CheckAutorizzatoriSingoloProfiloFinder finder);
	public AutorizzatoreBean getAutorizzatoreIlivelloVerticeStrutt(CheckAutorizzatoriSingoloProfiloFinder finder);
	public AutorizzatoreBean getAutorizzatoreIlivello(CheckAutorizzatoriSingoloProfiloFinder finder);
	public AutorizzatoreBean getAutorizzatoreIIlivello(CheckAutorizzatoriSingoloProfiloFinder finder);
	public GestoreOperatoriBean getGestoreCf(GestoriFinder gestoriFinder);
	public boolean isRichiedenteVerticeStruttura(HashMap<String, String> queryMap);
	public boolean isResponsabileHrRichiesta(HashMap<String, String> queryMap);
	public List<CDRBean> getGerarchiaCdR(String codiceCdR);
	public String getRoleGroupDescConcat(String profilo);
	public List<RoleGroupBean> getRoleGroupByCodRg(List<String> codiceRg);
	public List<AbilitazioneBean> getProfiliByCodProfilo(List<String> codiceProfilo);
	public int countRichiesteInCorso(String cfOperatore);
	public List<TipologiaOperatoriEsterni> getElencoTipologieOpEsterno(TipologiaOperatoreEsternoFinder tipologia);
	public List<ProfiloBean> getElencoAilitazioniTipologia(TipologiaOperatoreEsternoFinder tipologia);
	public List<ProfiloBean> getElencoAbilitazioniAgenzia(TipologiaOperatoreEsternoFinder tipologia);
	public List<ProfiloBean> getElencoAilitazioniTipologiaAgenzia(TipologiaOperatoreEsternoFinder tipologia);
//	public int aggiungiAbilitazioneTipologia(TipologiaOperatoreEsternoFinder tipologia);
//	public int rimuoviAbilitazioneTipologia(TipologiaOperatoreEsternoFinder tipologia);
	public boolean inserisciTipologia(TipologiaOperatoreEsternoFinder tipologia);
	public boolean modificaTipologia(TipologiaOperatoreEsternoFinder tipologia);
	public boolean eliminaTipologia(TipologiaOperatoreEsternoFinder tipologia);
//	public int modificaDatiTipologia(TipologiaOperatoreEsternoFinder tipologia);
	public int getIdTipologia();
	public int eliminazioneLogicaTipologia(TipologiaOperatoreEsternoFinder tipologia);
	public int countAbilitazioniAttive(TipologiaOperatoreEsternoFinder tipologia);
	public int countOperatoriEsterni(TipologiaOperatoreEsternoFinder tipologia);
	public String getUfficioPrimarioCATTest(String cfOperatore);
	List<RichiestaBean> getRichiesteDaArchiviare();
	List<RichiestaBean> getRichiesteDaAutorizzare();
	List<RichiestaBean> getRichiesteDaEseguire();
	List<RichiestaBean> getRichiesteDaPrendereInCarico();
	List<RichiestaBean> getSollecitoPresaVisione();
	String getIndirizzoSegnalazioni();
	List<SegnalazioneIncoerenzeBean> getIncongruenzeAssegnazioni();
	List<String> getProfiliAttiviUtenteWF(HashMap <String,String> input );	
	public List<ProfiloRichiestaBean> getVisibilitaCambioUfficio(ProfAttUte_x_Uff_di_provenienzFinder finder);
	public IterBean getIterInfo(String id);	
	public List<IterBean> getElencoIter(IterAbilitazioniFinder finder);
	public List<ProfiloBean> getAbilitazioniIter(IterAbilitazioniFinder finder);
	public List<StrutturaIterBean> getRuoliAutorizzativi(IterAbilitazioniFinder finder);
	public List<ProfiloBean> getElencoAbilitazioniNonAssociate(IterAbilitazioniFinder finder);
	public List<LabelValueBean> getTipoUfficioPerAbilitazione(ProfiloBean profilo);
	public int inserisciIter(IterBean iter);
	public int inserisciStrutturaIter(IterAbilitazioniFinder finder);
	public int getIdAudit();
	public int inserisciAuditSenzaId(AuditFinder finder);
	public int getIdIter();
	public boolean inserisciNuovoIter(IterAbilitazioniFinder finder);
	public List<ProfiloBean> getAbilitazioniIterAggiornamento(IterAbilitazioniFinder finder);
	public boolean aggiornaIter(IterAbilitazioniFinder finder);
	public List<CDRBean> getUfficiCATProfiliAssegnati(CDRFinder finder);
	public List<CDRBean> getUfficiCATTotali(CDRFinder finder);
	public List<ProfiloBean> getListaDescrizioniLineaCAT(ProfiloFinder finder);
	public StrutturaIterBean getIterStandard(CheckAutorizzatoriSingoloProfiloFinder finder);
	public CDRBean getInformazioniCdR(ProfiloFinder finder);
	public List<LabelValueBean> getTipiUfficioSovraordinati(IterAbilitazioniFinder finder);
	public int eliminaStrutturaIter(IterAbilitazioniFinder finder);
	public boolean aggiornaStrutturaIter(IterAbilitazioniFinder finder);
	public int isRichiesteInCorsoIter(IterAbilitazioniFinder finder);
	public boolean eliminaIter(IterAbilitazioniFinder finder);
	public GestoreOperatoriBean getGestoreAbilitazioni(GestoriFinder gestoriFinder);

	public List<LabelValueBean> getElencoAttivita(OperatoreFinder finder);
	public List<AttivitaBean> getElencoAttivitaAudit(AttivitaAmministratoriFinder finder);
	public List<AttivitaBean> getElencoAttivitaOperatori(AttivitaAmministratoriFinder finder);
	public List<OperatoreBean> getOperatoriDiCompetenza(OperatoreFinder finder);
	public List<ProfiloVisibilitaTracciamentoBean> getAggiornamentoVisibilita(Integer idAudit);
	public List<DelegaBean> getDelegheAmministratore(Integer idAudit);
	public AssociazioniOraBean getAssPuntuale(AssociazOperRichiAutorFinder finder);
	public RichiedenteCDRBean getRuoliCdR(RichiedenteCDRFinder finder);
	public List<GestoreOperatoreBean> getGestoriAggiornati(GestoreOperatoriFinder finder);
	public Integer selectIdRichiestaFromIdAudit(Integer id);
	public IterBean getIterByIdAudit(IterAbilitazioniFinder finder);
	public List<ProfiloBean> getProfiliIter(IterAbilitazioniFinder finder);
	public void updateIdAuditRichiesta(RichiestaBean finder);
	public void updateIdAuditOpEsterno(OperatoreBean finder);
	public void updateIdAuditOpEsternoTipologia(OperatoreEsternoTipoUtenteBean finder);
	
	public List<OperatoreEsternoTipoUtenteBean> getDettaglioOperatoreEsterno(OperatoreEsternoTipoUtenteBean finder);
//	public List<FileAbilitazioneBean> getElencoRecordCaricamentoMassivo(InterrogazioneCaricamentiFinder finder);
	public Integer isFileDisponibile(InterrogazioneCaricamentiFinder finder);
	

	public StrutturaIterBean getRuoliAutorizzativiExport(IterAbilitazioniFinder finder);
	public String getTipoUfficioMappatura(ExportProfiloMappaturaBean bean);
	public CostantiSiga getCostanteSiga(CostantiSiga finder);
	public ProfiloBean getIterAssociato(MappaturaProfiloUfficioBean bean);

	public int countAbilitazioniNonACApplicativo(IterAbilitazioniFinder finder);
	public int isDescrizioneTipoOEPresente(String descrizione);
	public int isDescrizioneIterPresente(String descrizione);
	public List<ProfiloBean> getAbilitazioniFromRg(String codiceRg);
	public  UtenteBean  getRevocheUtente(String codFisc);
	public List<String> getAdminCauByGroupName(HashMap<String, String> input) ;
	public String getAdminCAU(String input);
	public Integer verificaAmministratore_1(String input);
	public Integer verificaAmministratore_2(String input);
	public List<String> verificaTitolariAndDelegati();
	public Integer countRichestAsRichiedenteToElab(HashMap <String,String> input);
	public RichiedenteCDRBean getCfResponsabile (CF_RichiedenteFinder input);
	public void setDataElaborazioneBatch(CostantiSigaPT costante);

	public List<ProfiloCompetenzaBean> listaStoricoProfiliOperatore(ProfiloFinder finder);
	public boolean inserisciRevoca(RevocaBean revoca);
	public boolean aggiornaRevoca(RevocaBean revoca);
	public ElencoBean getOperatoriStoricoAbilitazioni(OperatoreFinder finder );
	public String getResponsabileCdR(RichiedenteCDRFinder finder);
	public void updateOwnerEjbTimer(String input);
	public List<RichiestaBean> getElencoGestoriRichiestaAmbito5 ( RichiestaFinder finder );
	
	public Integer countProfiliAsGestoreDiRete(Integer idRichiesta);
	public int countEsisteRevocaPrecedente(Integer idRichiesta);	
		
	/** Caricamenti Massivi Richieste di Visibilite'**/
	public List<CDRBean> getListaCdrAgenzia();
	public long getSequenceFromRichiesteCaricamentoMassivoVisibilita();
	public void inserisciRichiestaCaricamentoMassivoVisibilita(CaricamentoMassivoEntity cmEntity);
	public void inserisciAllegatiCaricamentoMassivoVisibilita(Set<FormFile> allegati, String tipo, long idCaricamento);
	public void inserisciAllegatiCaricamentoMassivoMappatura(Set<FormFile> allegati, String tipo, long idCaricamento);
	public List<CaricamentoMassivoEntity> getElencoCaricamentiMassiviVisibilitaUtenti(InterrogazioneCaricamentiFinder finder);
	public CaricamentoMassivoEntity getDettaglioCaricamentoRichVis(InterrogazioneCaricamentiFinder finder);
	public void annullaRichiestaCaricamentoMassivoRichVis(CaricamentoMassivoEntity cmEntity);
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamentoRichVis(InterrogazioneCaricamentiFinder finder);
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamentoRichVisNoBlob(InterrogazioneCaricamentiFinder finder);
	public AllegatoCaricamentoMassivoEntity getAllegatoRichVis(InterrogazioneCaricamentiFinder finder);
	public void avvioImmediatoControlliRichVis(String idCaricamento);
	public void deleteCmVisibilitaFile(String idCaricamento);
	public int getCountCodiciFiscaliAbilitati(UtenteBean utente);
	public List<BatchRuoliDegliOperatoriFinder> getRichiesteRuoliDegliOperatori(BatchRuoliDegliOperatoriFinder input);
	
	//Configurazione Caricamenti Massivi
	public List<EventiSistemaBean> getElencoCaricamentiMassivi();
	public void updateCaricamentiMassivi(ConfigCaricamentiMassiviFinder finder);

	
	/** Caricamenti Massivi Mappature abilitazioni uffici**/
	public long getSequenceFromRichiesteCaricamentoMassivoMappature();
	public void inserisciRichiestaCaricamentoMassivoMappature(CaricamentoMassivoEntity cmEntity);
	public List<CaricamentoMassivoEntity> getElencoCaricamentiMassiviMappatureUtenti(InterrogazioneCaricamentiFinder finder);
	public CaricamentoMassivoEntity getDettaglioCaricamentoMappature(InterrogazioneCaricamentiFinder finder);
	public void annullaRichiestaCaricamentoMassivoMappature(CaricamentoMassivoEntity cmEntity);
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamentoMappatureNoBlob (InterrogazioneCaricamentiFinder finder);
	public AllegatoCaricamentoMassivoEntity getAllegatoCaricamentoMappature (InterrogazioneCaricamentiFinder finder);
	

	public void avvioImmediatoControlliMappature(String idCaricamento);
	
	public void deleteMappaturaTipoUffCdr(MappaturaProfiloUfficioBean mappaturaBean);
	public void deleteMappaturaCdrUff(MappaturaProfiloUfficioBean mappaturaBean);

	
	// verifica visibilite'
	
	public int countNVerificaVisibilita(UtenteInVisibilita utenteInVisibilita);
	public int countNotNVerificaVisibilita(UtenteInVisibilita utenteInVisibilita);
	
	// cambio ufficio cat ac
	public List<StrutturaUfficioCDRBean> selectListaUfficiDaStruttura(UffDestinazFinder uffDestinazFinder);
	public boolean inserisciProfiliCambioUfficioCATAC(List<ProfiloRichiestaBean> elencoProfiliAttivi,
			List<ProfiloRichiestaBean> elencoProfiliAssegnati,
			RichDlgSessBean richDlgSessBean, GetAutorizzatore_I_LivBean autorizzatore_I_LivBean,
			OperatoreBean operatoreBean, UtenteBean utente,
			String ufficioCATProvenienza, String codiceUfficioDestinazione, List<RichiestaBean> listRichiesta) throws CheckException, SQLException;
	boolean inserisciRichiesteCambioUfficioCATAC(BaseFinder finder, UtenteBean utente);	

	// get info ufficio operatore
	OperatoreBean getInfoUfficioOperatore(OperatoreFinder operatoreFinder);	
	public List<RichiestaBean> getInfoRichiesteInCorsoAC(RegistroRichiesteFinder finder);
	public List<ProfiloRichiestaBean> getProfiliRichiestaByID(RichiestaFinder finder);
	
	
	//Cruscotto Cartelle condivise NAS
	public int getCartelleCondiviseCount(String codFiscale);
	public List<CruscottoCartelleNasBean> getCartelleCondiviseNas(String codFiscale);
	
	//Amministratori Applicativi
	public List<OperatoreBean> getAmministratoriCentraliByRoleGroup(CatalogoAbilitazioniFinder finder);
	public List<OperatoreBean> getAmministratoriCentraliByCf(CatalogoAbilitazioniFinder finder);
	public List<OperatoreBean> getAmministratoriCentraliByCfRoleGroup(CatalogoAbilitazioniFinder finder);
	public List<ProfiliAttiviUtente> getProfiliAttiviUtente(OperatoreFinder finder);
	public boolean inserisciAbilitazioniAmmCentraleApplicativo(CatalogoAbilitazioniFinder finder);
	public boolean aggiornaAbilitazioniAmmCentraleApplicativo(CatalogoAbilitazioniFinder finder);
	public boolean cancellaAbilitazioniAmmCentraleApplicativo(CatalogoAbilitazioniFinder finder);
	
	//Batch Cruscotto CSV
	public boolean batchCancellaCartelleCondiviseNas(String server);
	public boolean batchInserisciCartelleCondiviseNas(List<RecordCsvCruscottoBean> records);
	
	//Gestione catalogo Abilitazione
	public int updateNomeApplicazione(CatalogoAbilitazioniFinder finder) ;
	public boolean inserisciAbilitazioneFunzProf(CatalogoAbilitazioniFinder finder);
	public boolean deleteApplicazioneFunzProf(CatalogoAbilitazioniFinder finder);
	public boolean deleteApplicazioneUtente(CatalogoAbilitazioniFinder finder);
    public boolean deleteProfiliApplicazioneEstesa(CatalogoAbilitazioniFinder finder);
	public boolean inserisciAbilitazioneFunzRg(CatalogoAbilitazioniFinder finder);
	public boolean deleteApplicazioneFunzRg(CatalogoAbilitazioniFinder finder);
	public boolean insertApplicazioneEstesa(CatalogoAbilitazioniFinder finder);
	public List<ApplicazioneUtenteBean> getApplicazioneByCodice(CatalogoAbilitazioniFinder finder);
	public List<ApplicazioneUtenteBean> getCoppiaApplicazioneRoleGroup(CatalogoAbilitazioniFinder finder);
	public List<String> getEstesa(CatalogoAbilitazioniFinder finder);
	public List<String> getEliminabile(CatalogoAbilitazioniFinder finder);
	
	
	// Comunicazioni News 
	public List<ComunicazioneEntity> getElencoComunicazioni(ComunicazioniFinder finder);
	public List<ComunicazioneEntity> getElencoComunicazioniPerOperatori(ComunicazioniFinder finder);
	public List<String> selectCdrValidiPerVisualizzazione(String cdrUser);
	public List<ComunicazioneTipoStrutturaBean> getElencoDirezioniProvinciali();
	public List<ComunicazioneTipoStrutturaBean> getElencoDirezioniRegionali();
	public List<ComunicazioneTipoStrutturaBean> getElencoDcDivisioni();
	public UfficioUnicoBean getUfficioUnico(String codiceUfficio);
	public List<ComunicazioneTipoStrutturaBean> getElencoCop();
	public List<ComunicazioneTipoStrutturaBean> getElencoCam();
	public int inserisciMessaggio(ComunicazioniFinder finder);
	public int eliminaMessaggio(ComunicazioniFinder finder);
	public void inserisciRuoliDestinatari(ComunicazioniFinder finder);
	public List<AllegatoCaricamentoMassivoEntity> getAllegatoComunicazione(ComunicazioniFinder finder);
	public List<ComunicazioneTipoStrutturaBean> getElencoGerarchiaDC(String cdr);
	public List<ComunicazioneTipoStrutturaBean> getElencoGerarchiaDR(String cdr);
	public List<ComunicazioneTipoStrutturaBean> getElencoGerarchiaDP(String cdr);
	public void inserisciTipologiaStruttura(ComunicazioniFinder finder);
	public ComunicazioneEntity getComunicazioneById(String id);
	public void inserisciTipoSelezionato(ComunicazioniFinder finder);
	public List<ComunicazioneTipoStrutturaBean> getTipoSelezionatoById(String id);
	public List<ComunicazioneEntity> getEmailRichiedenti(ComunicazioniFinder finder);
	public List<ComunicazioneEntity> getEmailAutorizzatori(	ComunicazioniFinder finder);
	public List<ComunicazioneEntity> getEmailAmministratori(ComunicazioniFinder finder);
	
	//I Miei Ruoli e Ruoli degli Operatori
	public List<RuoloRichiedenteBean> getRuoliRichiedente(String cfRichiedente);
	public List<RuoloAutorizzatore_II_LivelloBean> getRuoliAutorizzatoreIILiv(String cfAutorizzatore);
	public List<RuoloAmministratoreAuditorBean> getRuoliAmministratoreAuditor(String cfAmministratoreAuditor);
	public List<RuoloDelegheBean> getRuoliDeleghe(String cfDelegatoDelegante);
	public int inserisciRichiestaRuoliOperatoriPerCdr(BatchRuoliDegliOperatoriFinder input);
	public int getIdBatchRuoliOperatoriPerCdrRichiesta();
	public List<String> getFiltriCdR(BatchRuoliDegliOperatoriFinder input);
	public List<String> getFiltriRG(BatchRuoliDegliOperatoriFinder input);
	public List<String> getFiltriProfili(BatchRuoliDegliOperatoriFinder input);
	public int elaborazioneFallitaBatch(BatchRuoliDegliOperatoriFinder input);
	public int insertFileRichiestaRuoliBatch(BatchRuoliDegliOperatoriFinder input);
	public int elaboraRichiestaRuoliBatch(BatchRuoliDegliOperatoriFinder input);
	public void setFlagElaborazioneRuoliOperatori(String flag);
	public List<ExportRuoliDiUnCdrBean> getRuoliExport(BatchRuoliDegliOperatoriFinder finder);
	public AllegatoBean getFileBatchRuoliOperatori(BatchRuoliDegliOperatoriFinder finder);
	public int cancellaRichestaRuoliOperatori(BatchRuoliDegliOperatoriFinder input);
	
	// batch monitoraggio abilitazioni 
	public ConfigBatchMonitAbilBean getConfReminderMonit();
	public Integer insertAttivitaChecked();
	public Integer aggiungiDataInvioReport(Date dt);
	public List<Utenti> getRichiedentiReminderMonit();
	public AttivitaCheckedBean getAttivitaChecked(String cf);
	public List<AttivitaCheckedReportBean> getAttivitaCheckedReport();
	public List<AttivitaCheckedReportBean> getAttivitaCheckedReportResp(AttivitaCheckedReportBean param) ;
	public void updateAttivitaChecked(AttivitaCheckedBean ac);
	public List<AttivitaCheckedReportBean> getAttivitaCheckedExportReport(AttivitaCheckedReportBean par);
	public List<Date> getPeriodiRifAttCheck();
	public List<String> getElencoUtentiIns();
	public List<String> getElencoUtentiDel();
	void cleanVariazioni();
	void insertUtentiVariazioniCfRuoliOTP();
	void insertUtentiGruppoOTP(String codiceFiscale, String operazione);
	void setValoreCostanteBatchAggiornamentoRuoloOTP(String val);
	void setValoreCostanteBatchMailMonitoraggioAbilitazioni(String val);
	

}

