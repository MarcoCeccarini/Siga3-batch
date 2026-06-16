package it.finanze.siga.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import it.finanze.scheduler.bean.AssociazOperRichiAutor;
import it.finanze.scheduler.bean.AttivitaCheckedBean;
import it.finanze.scheduler.bean.AttivitaCheckedReportBean;
import it.finanze.scheduler.bean.BatchInfoBean;
import it.finanze.scheduler.bean.CauUserIdOrgName;
import it.finanze.scheduler.bean.CdR;
import it.finanze.scheduler.bean.CoerenzaPropertiesBean;
import it.finanze.scheduler.bean.ConfigBatchMonitAbilBean;
import it.finanze.scheduler.bean.EventiSistemaBean;
import it.finanze.scheduler.bean.EventoBean;
import it.finanze.scheduler.bean.NotificaOperatoriDAO;
import it.finanze.scheduler.bean.ProfiliAttiviDAO;
import it.finanze.scheduler.bean.ProfiliAttiviUtente;
import it.finanze.scheduler.bean.ProfiliAttiviUtenteOperazioneBean;
import it.finanze.scheduler.bean.Profilo;
import it.finanze.scheduler.bean.ProfiloCM;
import it.finanze.scheduler.bean.ProfiloUtenteInVisibilitaBean;
import it.finanze.scheduler.bean.ProfiloVisibilitaTracciamentoBean;
import it.finanze.scheduler.bean.RegistroRichieste;
import it.finanze.scheduler.bean.RegistroRichiesteBatchBean;
import it.finanze.scheduler.bean.RelazioneCdRUfficioStrut;
import it.finanze.scheduler.bean.RelazioneCdrUfficioStrutOrganizz;
import it.finanze.scheduler.bean.ResponsabilitaBatch;
import it.finanze.scheduler.bean.ResponsabilitaOA;
import it.finanze.scheduler.bean.RichiedenteCdR;
import it.finanze.scheduler.bean.RichiestaAbilitazioneDisabilitazioneBean;
import it.finanze.scheduler.bean.RichiestaBatchBean;
import it.finanze.scheduler.bean.RichiestaBatchDaElaborareBean;
import it.finanze.scheduler.bean.RoleGroupBean;
import it.finanze.scheduler.bean.SegnalazioneIncoerenzeBean;
import it.finanze.scheduler.bean.Struttura;
import it.finanze.scheduler.bean.StrutturaIterDAO;
import it.finanze.scheduler.bean.UtenteInVisibilita;
import it.finanze.scheduler.bean.Utenti;
import it.finanze.scheduler.bean.UtentiAggiornatiHRDAO;
import it.finanze.siga.bean.AbilitazioneBean;
import it.finanze.siga.bean.AllegatoBean;
import it.finanze.siga.bean.AllegatoCaricamentoMassivoEntity;
import it.finanze.siga.bean.AmbitoApplicativoEntity;
import it.finanze.siga.bean.AmbitoBean;
import it.finanze.siga.bean.ApplicazioneCatalogoBean;
import it.finanze.siga.bean.ApplicazioneUtenteBean;
import it.finanze.siga.bean.ApplicazioniEntity;
import it.finanze.siga.bean.AssegnazioneGestoriOperatoriEntity;
import it.finanze.siga.bean.AssociazioniOraBean;
import it.finanze.siga.bean.AttivitaBean;
import it.finanze.siga.bean.AttoreBean;
import it.finanze.siga.bean.AutorizzatoreBean;
import it.finanze.siga.bean.CDRBean;
import it.finanze.siga.bean.CaricamentoMassivoEntity;
import it.finanze.siga.bean.ComunicazioneEntity;
import it.finanze.siga.bean.ComunicazioneTipoStrutturaBean;
import it.finanze.siga.bean.ControlliRecCaricMassiviBean;
import it.finanze.siga.bean.CostantiSiga;
import it.finanze.siga.bean.CostantiSigaPT;
import it.finanze.siga.bean.CruscottoCartelleNasBean;
import it.finanze.siga.bean.DelegaBean;
import it.finanze.siga.bean.ElementoCatalogoBean;
import it.finanze.siga.bean.ExportProfiloBean;
import it.finanze.siga.bean.ExportProfiloMappaturaBean;
import it.finanze.siga.bean.ExportRuoliDiUnCdrBean;
import it.finanze.siga.bean.FileAbilitazioneBean;
import it.finanze.siga.bean.FileBean;
import it.finanze.siga.bean.FileMappaturaBean;
import it.finanze.siga.bean.FunzioneBean;
import it.finanze.siga.bean.GestoreOperatoreBean;
import it.finanze.siga.bean.GestoreOperatoriBean;
import it.finanze.siga.bean.GetAutorizzatore_I_LivBean;
import it.finanze.siga.bean.InterrProfiliOperatoreBean;
import it.finanze.siga.bean.IterBean;
import it.finanze.siga.bean.IterNonStandardProfiliApplicazioniBean;
import it.finanze.siga.bean.IterProfiliApplicazioniBean;
import it.finanze.siga.bean.LeMieVariazioniRuoloCDRBean;
import it.finanze.siga.bean.MappaturaProfiloUfficioBean;
import it.finanze.siga.bean.MappaturaUfficiBean;
import it.finanze.siga.bean.OperatoreBean;
import it.finanze.siga.bean.OperatoreEsternoTipoUtenteBean;
import it.finanze.siga.bean.OrigineRichiestaBean;
import it.finanze.siga.bean.ProfUfficioDestinazioneBean;
import it.finanze.siga.bean.ProfiloAbilitazioneBatchBean;
import it.finanze.siga.bean.ProfiloBean;
import it.finanze.siga.bean.ProfiloCompetenzaBean;
import it.finanze.siga.bean.ProfiloIncompatibilitaBean;
import it.finanze.siga.bean.ProfiloRichiestaBean;
import it.finanze.siga.bean.ProfiloSimpleBean;
import it.finanze.siga.bean.ProvinciaBean;
import it.finanze.siga.bean.RaggruppamentoFunzionaleBean;
import it.finanze.siga.bean.RecordCsvCruscottoBean;
import it.finanze.siga.bean.RegioneBean;
import it.finanze.siga.bean.RegistroDelleMieAssociazioniPuntualiBean;
import it.finanze.siga.bean.RevocaBean;
import it.finanze.siga.bean.RichDlgSessBean;
import it.finanze.siga.bean.RichiedenteCDRBean;
import it.finanze.siga.bean.RichiestaBean;
import it.finanze.siga.bean.RichiestaInCorsoBean;
import it.finanze.siga.bean.RichiesteDiProfilazioneBean;
import it.finanze.siga.bean.RuoloAmministratoreAuditorBean;
import it.finanze.siga.bean.RuoloAutorizzatore_II_LivelloBean;
import it.finanze.siga.bean.RuoloDelegheBean;
import it.finanze.siga.bean.RuoloRichiedenteBean;
import it.finanze.siga.bean.StatoRichiestaBean;
import it.finanze.siga.bean.StrutturaIterBean;
import it.finanze.siga.bean.StrutturaIterEntityBean;
import it.finanze.siga.bean.StrutturaUfficioCDRBean;
import it.finanze.siga.bean.StrutturaUfficioCDRExtBean;
import it.finanze.siga.bean.TemplateBean;
import it.finanze.siga.bean.TipoAbilitazioneBean;
import it.finanze.siga.bean.TipoCdrBean;
import it.finanze.siga.bean.TipoUfficioBean;
import it.finanze.siga.bean.TipologiaOperatoriEsterni;
import it.finanze.siga.bean.TipologiaUtenteBean;
import it.finanze.siga.bean.UffCdrAppartBean;
import it.finanze.siga.bean.UffDestinazBean;
import it.finanze.siga.bean.UfficioUnicoBean;
import it.finanze.siga.bean.UtenteInVisibilitaBean;
import it.finanze.siga.finder.AmbitoFinder;
import it.finanze.siga.finder.ApplicazioniFinder;
import it.finanze.siga.finder.AssociazOperRichiAutorFinder;
import it.finanze.siga.finder.AttivitaAmministratoriFinder;
import it.finanze.siga.finder.AuditFinder;
import it.finanze.siga.finder.BaseFinder;
import it.finanze.siga.finder.BasePaginateFinder;
import it.finanze.siga.finder.BatchAbilitazioniRichiestaFinder;
import it.finanze.siga.finder.BatchRuoliDegliOperatoriFinder;
import it.finanze.siga.finder.CDRBaseFinder;
import it.finanze.siga.finder.CDRFinder;
import it.finanze.siga.finder.CDRUfficioPassatoFinder;
import it.finanze.siga.finder.CF_RichiedenteFinder;
import it.finanze.siga.finder.CatalogoAbilitazioniFinder;
import it.finanze.siga.finder.CheckAutorizzatoriProfiloFinder;
import it.finanze.siga.finder.CheckAutorizzatoriSingoloProfiloFinder;
import it.finanze.siga.finder.ComunicazioniFinder;
import it.finanze.siga.finder.ConfigCaricamentiMassiviFinder;
import it.finanze.siga.finder.DelegaFinder;
import it.finanze.siga.finder.DocumentoFinder;
import it.finanze.siga.finder.ExportMappaturaFinder;
import it.finanze.siga.finder.GestoreOperatoriFinder;
import it.finanze.siga.finder.GestoriFinder;
import it.finanze.siga.finder.GetAutorizzatore_I_LivFinder;
import it.finanze.siga.finder.InterrProfiliOperatoreFinder;
import it.finanze.siga.finder.InterrogazioneAdminGroupFinder;
import it.finanze.siga.finder.InterrogazioneCaricamentiFinder;
import it.finanze.siga.finder.InterrogazioneMappaturaProfiliUfficioFinder;
import it.finanze.siga.finder.InterrogazioneProfilazioneFinder;
import it.finanze.siga.finder.InterrogazioneRegioniProvinceFinder;
import it.finanze.siga.finder.IterAbilitazioniFinder;
import it.finanze.siga.finder.IterNonStandardProfiliApplicazioniFinder;
import it.finanze.siga.finder.IterProfiliApplicazioniFinder;
import it.finanze.siga.finder.LeMieVariazioniRuoloCDRFinder;
import it.finanze.siga.finder.OperatoreFinder;
import it.finanze.siga.finder.OrganizzazioniFinder;
import it.finanze.siga.finder.ProfAttUte_x_Uff_di_provenienzFinder;
import it.finanze.siga.finder.ProfiliFinder;
import it.finanze.siga.finder.ProfiliIncompatibiliFinder;
import it.finanze.siga.finder.ProfiliOperatVisibFinder;
import it.finanze.siga.finder.ProfiliUfficiFinder;
import it.finanze.siga.finder.ProfiloFinder;
import it.finanze.siga.finder.RegistroDelleMieAssociazioniPuntualiFinder;
import it.finanze.siga.finder.RegistroRichiesteFinder;
import it.finanze.siga.finder.ResponsabilitaFinder;
import it.finanze.siga.finder.RichDlgSessFinder;
import it.finanze.siga.finder.RichiedenteCDRFinder;
import it.finanze.siga.finder.RichiestaFinder;
import it.finanze.siga.finder.RichiesteProfilazioneFinder;
import it.finanze.siga.finder.StrutturaPerIterFinder;
import it.finanze.siga.finder.TemplateFinder;
import it.finanze.siga.finder.TipologiaOperatoreEsternoFinder;
import it.finanze.siga.finder.UffCdrAppartFinder;
import it.finanze.siga.finder.UffDestinazFinder;
import it.finanze.siga.finder.UfficioFinder;
import it.finanze.siga.finder.ValoriFinder;
import it.finanze.siga.util.bean.ElencoBean;
import it.finanze.siga.util.bean.ExportProvinceBean;
import it.finanze.siga.util.bean.ExportRegioneBean;
import it.finanze.siga.util.bean.ExportTipologiaUfficioBean;
import it.finanze.siga.util.bean.UtenteBean;
import it.finanze.siga.util.tree.ProfiloTreeBean;
import it.sogei.eaf.util.CheckException;

public interface SigaDao {

	/************ Inserimento Richiesta Profilazione ************/
	public void destroy();

	public ElencoBean getElencoUtenti(BaseFinder in) throws SQLException;
	public ElencoBean getElencoUtentiLike(BaseFinder in) throws SQLException;	
	public UffCdrAppartBean getUffCdrAppart(UffCdrAppartFinder finder ) throws SQLException;
	public ElencoBean getProfiliOperatoreInVisibilita(ProfiliOperatVisibFinder finder ) throws SQLException;
	public List<ProfiloUtenteInVisibilitaBean> getProfiliRichiestaVisibilita(RichiestaBean richiesta) throws SQLException;
	public ElencoBean getElencoOperatoriInVisibilita(OperatoreFinder finder ) throws SQLException;
	public ElencoBean getElencoOperatori( OperatoreFinder finder ) throws SQLException;
	public ElencoBean getElencoOperatoriTemplate( ProfiloFinder finder ) throws SQLException;
	public RichDlgSessBean getRichDlgSess( RichDlgSessFinder finder ) throws SQLException;
	public RichiesteDiProfilazioneBean getRichiesteDiProfilazione( RichiesteProfilazioneFinder finder ) throws SQLException;
	public GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv( GetAutorizzatore_I_LivFinder finder ) throws SQLException;
	public GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv_Iter( GetAutorizzatore_I_LivFinder finder ) throws SQLException;
	public GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv_Ass( GetAutorizzatore_I_LivFinder finder ) throws SQLException;
	public GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv_Per_Ripristino(GetAutorizzatore_I_LivFinder finder)throws SQLException; 
	public GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv_profili_CAT( GetAutorizzatore_I_LivFinder finder ) throws SQLException;
	public List<ProfiloRichiestaBean> getProfAttUtenteXUfficioProvenienza(ProfAttUte_x_Uff_di_provenienzFinder finder ) throws SQLException;
	public List<ProfiloRichiestaBean> getIntersezProfAmmissProfAttUtenteXUfficio(ProfAttUte_x_Uff_di_provenienzFinder finder ) throws SQLException;
	public List<ProfUfficioDestinazioneBean> getProfiliUfficioDestinazione(ProfAttUte_x_Uff_di_provenienzFinder finder ) throws SQLException;
	public List<String> getElencoRichiedentiTotaliDaRichieste(RichiestaFinder in) throws Exception;
	public List<String> getElencoAutorizzatori1LivelloTotaliDaRichieste(RichiestaFinder in) throws Exception;
	public List<String> getElencoAutorizzatori2LivelloTotaliDaRichieste(RichiestaFinder in) throws Exception;
	public List<String> getElencoGestoriOperatoriDaRichieste(RichiestaFinder in) throws Exception;
	public List<String> getElencoCfUtentiDaRichieste(RichiestaFinder in) throws Exception;
	public List<String> getElencoRichiedentiTotaliDaRichiesteRR(RichiestaFinder in) throws Exception;
	public List<String> getElencoAutorizzatori1LivelloTotaliDaRichiesteRR(RichiestaFinder in) throws Exception;
	public List<String> getElencoAutorizzatori2LivelloTotaliDaRichiesteRR(RichiestaFinder in) throws Exception;
	public List<String> getElencoGestoriOperatoriDaRichiesteRR(RichiestaFinder in) throws Exception;
	public List<String> getElencoCfUtentiDaRichiesteRR(RichiestaFinder in) throws Exception;
	public ElencoBean getOperatoreByCF( OperatoreFinder finder ) throws SQLException;
	public List<String> getElencoArchiviazioneDaRichiesteRR(RichiestaFinder in) throws Exception;
	public String getUfficioByCdr( String descrizione ) throws SQLException;
	
	
	//REVOCA
	public ElencoBean getElencoOperatoriRevocaVisibilitaAC(OperatoreFinder finder ) throws SQLException;
	
	public List<CDRBean> getElencoCDR(CDRFinder finder) throws SQLException;
	public List<CDRBean> getElencoCDRRichesteDiUnUffio(CDRFinder finder) throws SQLException;
	public List<CDRBean> getElencoCDRResp(CDRFinder finder) throws SQLException;
	public List<CDRBean> getElencoCDROperatoriVis(CDRFinder finder) throws SQLException;
	public List<InterrProfiliOperatoreBean> getElencoProfiliOperatore(InterrProfiliOperatoreFinder finder) throws SQLException;
	public Collection <IterProfiliApplicazioniBean> getIterProfiliApplicazione(IterProfiliApplicazioniFinder finder) throws SQLException;
	public Collection <IterNonStandardProfiliApplicazioniBean> getIterNonStandardProfiliApplicazioni(IterNonStandardProfiliApplicazioniFinder finder) throws SQLException;
	public Collection <RegistroDelleMieAssociazioniPuntualiBean> getElencoRegistroDelleMieAssociazioniPuntuali(RegistroDelleMieAssociazioniPuntualiFinder finder) throws SQLException;
	public Collection <RegistroDelleMieAssociazioniPuntualiBean> getElencoRegistroAssociazioniORA(RegistroDelleMieAssociazioniPuntualiFinder finder) throws SQLException;
	public Date getDataOraDB() throws SQLException;
	public Collection <LeMieVariazioniRuoloCDRBean> getElencoLeMieVariazioniRuoloCDR(LeMieVariazioniRuoloCDRFinder finder) throws SQLException;
	public Collection<DelegaBean> getElencoRegistroDeleghe(LeMieVariazioniRuoloCDRFinder finder)throws SQLException;
	public Collection<DelegaBean> getElencoRegistroDelegheDelegatoDelegante(LeMieVariazioniRuoloCDRFinder finder)throws SQLException;
	public List<CDRBean> getElencoCDRNonInVisibilita(CDRFinder finder) throws SQLException;
	public List<UffDestinazBean> getUfficiDestinazione(UffDestinazFinder finder) throws SQLException;
	public List<AmbitoBean> getElencoAmbito(BasePaginateFinder finder) throws SQLException;
	public List<AmbitoBean> getElencoAmbitoBis(BasePaginateFinder finder) throws SQLException;
	public List<AmbitoBean> getElencoAmbitoApplCDR(BasePaginateFinder finder) throws SQLException;
	
	/** VALORI LABEL **/
	public List<LabelValueBean> getValoriLabel(ValoriFinder finder) throws SQLException;
	public List<ProfiloBean> getElencoProfilo(BasePaginateFinder finder) throws SQLException;
	public List<ProfiloBean> getElencoProfiliVisibilita(BasePaginateFinder finder) throws SQLException;
	public List<ProfiloBean> getElencoProfiliStruttura(BasePaginateFinder finder) throws SQLException;
	public List<ProfiloBean> getElencoProfiliVisibilitaAC(BasePaginateFinder finder) throws SQLException;
	public List<ProfiloRichiestaBean> getElencoProfiloProfilazioneUfficioCdR(BasePaginateFinder finder) throws SQLException;
	public List<ProfiloRichiestaBean> getElencoProfiloProfilazioneUfficioCdR_AmmCentraleApp(BasePaginateFinder finder) throws SQLException;
	
	
	public List<RichiestaBean> getRichiesteByElencoCDR ( RichiestaFinder finder ) throws SQLException;
	public List<RichiestaBean> getRichiesteRichiedente ( RichiestaFinder finder ) throws SQLException;
	public List<RichiestaBean>  getRichiesteAutorizzatoreI ( RichiestaFinder finder ) throws SQLException;
	public List<RichiestaBean>  getRichiesteAutorizzatoreII( RichiestaFinder finder ) throws SQLException;
	public List<RichiestaBean>  getRichiesteGestore( RichiestaFinder finder ) throws SQLException;
	public List<RichiestaBean>  getRichiesteOperatore( RichiestaFinder finder ) throws SQLException;
	public List<RichiestaBean> getInfoRichiesteInCorso(RegistroRichiesteFinder finder) throws SQLException;
	public RichiestaBean getRichiesta( RichiestaFinder finder ) throws SQLException;
	public RichiestaBean getRichiestaWF( RichiestaFinder finder ) throws SQLException;
	public RichiestaBean getRichiestaRR( RichiestaFinder finder ) throws SQLException;
	public List<ProfiloRichiestaBean> getProfiliByIdAndTipoRichiesta( RichiestaFinder finder ) throws SQLException;
	public List<CDRBean> getResponsabileByCF( OperatoreFinder finder ) throws SQLException;	
	public ElencoBean getRichieste( RichiestaFinder finder ) throws SQLException;
	public ElencoBean getRichiesteRR( RichiestaFinder finder ) throws SQLException;
	public OperatoreBean getDatiUtente( OperatoreFinder finder ) throws SQLException;
	public OperatoreBean getDatiUtente2(OperatoreFinder operatoreFinder)throws SQLException;
	public OperatoreBean getDatiUtenteCdrResponsabile(OperatoreFinder operatoreFinder)throws SQLException;
	public String getCDRUtenteInVisibilita( RichiestaFinder finder ) throws SQLException;
	public List<CDRBean> getCDRAlberoEsclusi( String cdr ) throws SQLException;
	public List<ProfiloRichiestaBean> getProfiliAlberoEsclusi( ProfiloFinder profilo ) throws SQLException;
	public List<ProfiloRichiestaBean> getProfiliAlberoEsclusi_inVisibilita( ProfiloFinder profilo ) throws SQLException;
	public Integer controlloVisibilitaFaseDue( ProfiloFinder profilo ) throws SQLException;
	public OrigineRichiestaBean getOrigineAbilitazione(String codiceOrigine)throws SQLException;
	
	public RichiestaBean getDescrizioneCdr( RichiestaBean finder ) throws SQLException;
	public void aggiornaRichiesta( RichiestaBean input ) throws SQLException;
	public void aggiornaProfiloRichiesta(ProfiloRichiestaBean input) throws SQLException;
	public void aggiornaVisibilitaUtente(RichiestaFinder input) throws SQLException;
	
	public boolean isUfficioDaPreservare(String codUfficio) throws SQLException;
	
	public boolean presaInCarico( RichiestaFinder input ) throws SQLException;
	public boolean rilasciaRichiesta( RichiestaFinder input ) throws SQLException;
	public String getDescrizioneStato( String finder ) throws SQLException;
	public List<FileBean> getFilesRichiesta( Integer finder ) throws SQLException;
	public String getCDR_UfficioPassato(CDRUfficioPassatoFinder finder) throws SQLException;
	public String getExplicit_Entitlement(ProfiloFinder finder) throws SQLException;
	public String getCf_Richiedente(CF_RichiedenteFinder finder) throws SQLException;
	public 	UffDestinazBean getDescrCdrByCodUff (String input) throws SQLException ;
	public List<AttoreBean> getCfAttoreFaseAU_D(Integer idRichiesta) throws SQLException;

	/**
	 * elenco profili dopo verifica iter
	 */
	public List<ProfiloTreeBean> getElencoProfiloVerificaIter(BaseFinder finder) throws SQLException;
	
	/**
	 * elenco profili dopo verifica autorizzazioni
	 */
	public ElencoBean getElencoIterVerificaAutorizzazioni(BaseFinder finder) throws SQLException;
	public ElencoBean getElencoIterVerificaGestori(CheckAutorizzatoriProfiloFinder finder)throws SQLException;	
	public RichiestaInCorsoBean isRichiestaInCorso(BasePaginateFinder finder) throws SQLException;
	public boolean inserisciRichiesta(BaseFinder finder) throws SQLException;
	public boolean inserisciProfiliCambioUfficioCAT(List<ProfiloRichiestaBean> elencoProfiliAttivi,
			List<ProfiloRichiestaBean> elencoProfiliAssegnati,
			RichDlgSessBean richDlgSessBean, GetAutorizzatore_I_LivBean autorizzatore_I_LivBean,
			OperatoreBean operatoreBean, UtenteBean utente,
			String ufficioCATProvenienza, String codiceUfficioDestinazione, List<RichiestaBean> listRichiesta) throws CheckException, SQLException;
	public int inserisciRichiesta2(BaseFinder finder) throws SQLException;
	public boolean inserisciRichieste(BaseFinder finder) throws SQLException;
	public boolean inserisciProfili(BaseFinder finder) throws SQLException;
	public List<TemplateBean> getElencoTemplate(BasePaginateFinder finder) throws SQLException;
	public List<ProfiloBean> getElencoProfiloTemplate(BaseFinder finder) throws SQLException;
	public List<ProfiloBean> getElencoProfiloAttivoUtente(BasePaginateFinder finder) throws SQLException;
	public List<ProfiloBean> getProfiliAttiviUtentePerReport(ProfiloFinder finder)throws SQLException;
	public List<ProfiloBean> getProfiliAttiviUtenteInVisibilitaPerReport(ProfiloFinder finder)throws SQLException;
	public List<ProfiloBean> getElencoProfiloAttivoUtenteCompetenza(BasePaginateFinder finder) throws SQLException;
	public void updateAuditRichiesta(RichiestaBean richiestaBean) throws SQLException; 
	public boolean isCdrFiglio(String codiceCDR, String CDRPadre) throws SQLException;
	public List<RichiestaBean> getRichiesteDelegato(RichiestaFinder finder) throws SQLException;

	/** Inserimento File **/
	public boolean inserisciFile(BaseFinder finder) throws SQLException;
	public int inserisciFile2(BaseFinder finder) throws SQLException;
	public List<OperatoreBean> getOperatoriPerNominativo(OperatoreFinder finder)throws SQLException;
	public List<OperatoreBean> getOperatoriPerDelegato(OperatoreFinder finder)throws SQLException;
	
	/** ALBERO **/
	public List<StrutturaUfficioCDRBean> getElencoStruttUffCDRAmministratore(BaseFinder finder) throws SQLException;
	public List<StrutturaUfficioCDRExtBean> getElencoStruttUffCDRPerSottoAlberoByCDR(BaseFinder finder) throws SQLException;
	public List<StrutturaUfficioCDRExtBean> getElencoStruttUffCDRPerAlberoByElencoCDR(BaseFinder finder) throws SQLException;
	
	/** DELEGHE **/
	public void inserisciDelega(DelegaFinder delegaFinder)throws SQLException;
	public void inserisciDelegaBatch(DelegaFinder delegaFinder)throws SQLException;
	public void rimozioneDelega(DelegaFinder delegaFinder)throws SQLException;
	public List<DelegaBean> getElencoDeleghe(DelegaFinder finder)throws SQLException;
	public void aggiornaRichiesteDeleghe(String cfDelegato) throws SQLException;
	public String getStrutturaByCDR(String cdr)throws SQLException;
	public String getIILivByCdr(String input)throws SQLException;
	public List<OperatoreBean> getDelegantiByCDR(String cdr)throws SQLException;
	public List<String> getElencoStruttRegionali(String strutturaIILiv)throws SQLException;
	public List<OperatoreBean> getDelegabiliByCdr(Map input)throws SQLException;
	public List<OperatoreBean> getDelegabiliByCdrORA(String cdr)throws SQLException;
	// gestione allegati
	public int keyIdDelega() throws SQLException;
	public void insIdAllegatiDelega(DelegaBean delega) throws SQLException;
	public void inserisciAllegatiDelega(DocumentoFinder docFinder) throws SQLException;
	public List<AllegatoBean> getAllegatiDelega(Integer id)throws SQLException;
	//<--
	
	/** AUDIT **/
	public int inserisciAUDIT(AuditFinder auditFinder)throws SQLException;
	
	/** RICHIEDENTE_CDR **/
	
	public Integer verificaIsRichiedente(RichiedenteCDRFinder finderRichiedente)throws SQLException;
	public Integer countRichiedenteCDR(String input)throws SQLException;

	public List<RichiedenteCDRBean> getRichiedenteCDR(RichiedenteCDRFinder richiedenteCDRFinder)throws SQLException;
	public List<RichiedenteCDRBean> getRichiedentePuntualeCDR(RichiedenteCDRFinder richiedenteCDRFinder)throws SQLException;
	public List<RichiedenteCDRBean> getRichiedentiAC(RichiedenteCDRFinder richiedenteCDRFinder)throws SQLException;
	public List<RichiedenteCDRBean> getCdrAutorizzatoreIliv(RichiedenteCDRFinder richiedenteCDRFinder)throws SQLException;
	
	
	public boolean inserisciProfiliCambioUfficio(BaseFinder finder) throws SQLException;
	public boolean inserisciRichiesteCambioUfficioCAT(BaseFinder finder, UtenteBean utente) throws SQLException;

	/** ASSOCIAZ_OPER_RICHI_AUTOR **/
	public Integer getNumeroAssociazioni(AssociazOperRichiAutorFinder finderAssocia)  throws SQLException;
	public Integer getNumeroAssociazioniRichOrAut(AssociazOperRichiAutorFinder finderAssocia)  throws SQLException;

	public Integer verificaAssociazioni(AssociazOperRichiAutorFinder finderAssocia)  throws SQLException;
	public Integer getNumeroRichieste(AssociazOperRichiAutorFinder finderAssocia) throws SQLException;
	public Integer verificaResponsabileCdrFigli(String cfRichiedente) throws SQLException;
	/** Metodi comuni**/
	public void modificaoperatoreRichiedenteAutorizzatori(RichiedenteCDRFinder richiedenteCDRFinder, String cfNuovo) throws SQLException;
	public void revocaRichiedentiDiUnGruppoDiOperatori(String cfPrecedente,	String cfAltro)throws SQLException;
	public void sostituzioneRichiedenteDiUnGruppoDiOperatori(String cfPrecedente, String cfNuovo, String cfAltro)throws SQLException;
	public void ripristinoResponsabileHR(String cfPrecedente, String cdr,String motivoRevoca, UtenteBean utente)throws SQLException;
	public void sostituzioneDellAutorizzatoreDiUnCDR(String cfPrecedente,String cfNuovo, String cdr, String motivoRevoca,UtenteBean utente) throws SQLException;
	public void ripristinoAutorizzatoreDaHR(String cfPrecedente, String cdr,String motivoRevoca, UtenteBean utente)throws SQLException;
	public void modificaEntrambi(String cfPrecedenteRichiedente, String cfNuovoRichiedente, String cfPrecedenteAutorizzatore, String cfNuovoAutorizzatore, String cdr, UtenteBean utente)throws SQLException;
	public void ripristinaEntrambi(String cfPrecedenteRichiedente,String cfPrecedenteAutorizzatore, String cfNuovoRichiedente,String cdr, UtenteBean utente) throws SQLException;
	
	/** Utente **/
	public void aggiornaFlagRuolo(OperatoreFinder operatoreFinder) throws SQLException;
	public boolean abilitatoCambioUfficio(String cf)throws SQLException;
	public List<String> getProfiliAttivi(String cf)throws SQLException;
	public String getStrutturaIILiv(String cf)throws SQLException;
	public List<String> getCodiceAmmLocale(String cf) throws SQLException;
	public List<String> getAmmCentraleApp(String cf)throws SQLException;	
	public List<String> getRuoliGestoriOperatori(String cf)throws SQLException;
	public List<String> getEmailRichiedenteVisibilita(RichiestaFinder input)throws SQLException;
	public List<String> getEmailALVisibilita(RichiestaFinder input)throws SQLException;
	public String getCFRichiedenteWFM(RichiestaBean input)throws SQLException;
	public String getCFRichiedenteVisibilitaWFM(RichiestaBean input)throws SQLException;
	public List<String> getCFRichiedentiECAAltriCDRWFM(RichiestaBean input)throws SQLException;
	public List<String> getEmailAutorizzatoreI(RichiestaBean input)throws SQLException;
	public List<String> getEmailAltroUfficioInteressato(RichiestaBean input)throws SQLException;
	public List<String> getEmailAmmCentrali()throws SQLException;
	public String getStrutturaIILivByCdr(String input)throws SQLException;
	public List<String> getEmailStrutturaIILiv(String input)throws SQLException;
	public String verificaStrutture(OperatoreBean input)throws SQLException;
	
	public List<String> getServiziUtente(String ruolo)throws SQLException;

	
	
	/** UFFICIO**/
	public UfficioFinder getUfficoByCodUff(UfficioFinder ufficioFinder)throws SQLException;
	
	/** STRUTTURA_ITER **/
	public List<StrutturaPerIterFinder> getStrutturaIter( StrutturaPerIterFinder strutturaFinder)throws SQLException;
	public List<StrutturaPerIterFinder> getStrutturaIter2( StrutturaPerIterFinder strutturaFinder)throws SQLException;
	
	/**  REGISTRO_RICHIESTE **/
	public void annullamentoRichiesteNonAutorizzate(RegistroRichiesteFinder registroFinder)throws SQLException;
	public Integer countRichiesteNonAutorizzate(RegistroRichiesteFinder registroFinder)throws SQLException;
	public void ripristinaRichiesteNonAutorizzate(AssociazOperRichiAutorFinder finder )throws SQLException;
 	public void sostituisciAutorizzatore(RegistroRichiesteFinder finder )throws SQLException;
	public void ripristinaRichiesteAutorizzate(AssociazOperRichiAutorFinder finder)throws SQLException;
	public Integer countRichiesteAttiveGeneral(RegistroRichiesteFinder registroFinder)throws SQLException;

	public void sostituzioneRichiedente(RichiedenteCDRFinder richiedenteFinder)throws SQLException;
	public void revocaAutorizzatoreDiUnGruppoDiOperatori(String cfPrecedente, String cfAltro, String motivoRevoca, String cfUtenteInSessione)throws SQLException;
	public void sostituzioneAutorizzatoreDiUnGruppoDiOperatori(String cfPrecedente, String cfNuovo, String cfAltro, String cfUtenteInSessione)throws SQLException;
	public void sostituzioneDelRichiedenteDiUnCDR(String cfPrecedente,String cfNuovo, String cdr, String motivoRevoca,UtenteBean utente)throws SQLException;
	
	public boolean inserisciRichiedenteCDR(RichiedenteCDRFinder richiedenteFinder )throws SQLException;
	public void aggiornaRichiedenteCDR(RichiedenteCDRFinder richiedenteFinder) throws SQLException;
	
	public void aggiornaSoloRichiedenteCDR(RichiedenteCDRFinder richiedenteFinder) throws SQLException;

	/** FUNZIONE per la  verifica del mantenimento del ruolo di AUTORIZZATORE **/
	public int step1(String cdrResponsabile) throws SQLException;

	/** Richiesta Visibilita **/
	public int inserisciRichiestaVisibilita(RichiestaBean richiestaBean) throws SQLException;
	public int inserisciVisibilita(RichiestaBean richiestaBean,List<ProfiloBean> listProfiliSelezionati) throws SQLException;
	void inserisciProfiloInVisibilita(ProfiloRichiestaBean profiloRichiestaBean) throws SQLException;
	void inserisciProfiloBatch(ProfiloRichiestaBean profiloRichiestaBean) throws SQLException;
	public List<OperatoreBean> getOperatoreByCDR(OperatoreFinder operatoriFinder) throws SQLException;
	public List<OperatoreBean> getOperatoreByNominativo(OperatoreFinder operatoriFinder) throws SQLException;
	public List<OperatoreBean> getDeleganteOdelegatoByCdr(OperatoreFinder operatoriFinder) throws SQLException;
	public CDRBean getCDR(String codiceCDRVis)throws SQLException;
	public Integer revocaProfiliVisibilita(RichiestaBean richiestaBean,List<ProfiloRichiestaBean> listaProfiliOpInVisi)throws SQLException;
	public List<UtenteInVisibilitaBean> getUtentiInVisibilitaBean(ProfiloFinder finder) throws SQLException;
	public List<RegistroRichiesteFinder> getStatoRegistroRischiestaByID(RegistroRichiesteFinder finderRegistroRichieste) throws SQLException;
	
	
	
	
	public List<ProfiloRichiestaBean> getProfAttUtente(ProfAttUte_x_Uff_di_provenienzFinder mProfAttUte_x_Uff_di_provenienzFinder)throws SQLException;
	
	/** FUNZIONALITA' MONITORAGGIO **/
	public ElencoBean getElencoOperatoriMonitoraggio(OperatoreFinder finder) throws SQLException;
	public ElencoBean getElencoOperatoriInterimCondUtente(String cf) throws SQLException;
	public ElencoBean getElencoOperatoriInterimCond(OperatoreFinder finder) throws SQLException;
	
	/** Ambito Applicativo**/
	public AmbitoBean getAmbitoApplicativo(AmbitoFinder ambito)throws SQLException;
	
								/** 	MAPPATURA APPLICAZIONI	 **/
	
	
	public List<AmbitoApplicativoEntity> getAmbitoByCodice(List<String> codici) throws SQLException;
	public List<ApplicazioniEntity> getApp(List<String> codici)throws SQLException;
	public List<ApplicazioniEntity> getAppMappatura(List<String> codici)throws SQLException;
	public List<ApplicazioniEntity> getApplicazioni(ApplicazioniFinder finder) throws SQLException;
	public List<String> getProfiliAmmApp(ProfiliFinder finder)throws SQLException;
	public List<String> getProfiliAmministratore(String app) throws SQLException;
	public List<MappaturaUfficiBean> getMappaturaUffici(ProfiliUfficiFinder finder)throws SQLException;
	/* GESTIONE TEMPLATE */
	public int inserisciTemplate(BasePaginateFinder finder) throws SQLException;
	public boolean inserisciProfiliTemplate(BaseFinder finder) throws SQLException;
	public boolean nuovoTemplate(BasePaginateFinder finder) throws SQLException;
	public List<ProfiloRichiestaBean> getProfiliXAmbitoeUfficio(ProfAttUte_x_Uff_di_provenienzFinder finder)throws SQLException;
	
	
	public int updateTemplate(BasePaginateFinder finder) throws SQLException;
	public int cancellaProfiliTemplate(BaseFinder finder) throws SQLException;
	public boolean eliminaTemplate(BasePaginateFinder finder) throws SQLException;
	public ElencoBean getElencoRicercaTemplate( TemplateFinder finder ) throws SQLException;
	public ElencoBean getElencoProfiliTemplate( TemplateFinder finder ) throws SQLException;
	public boolean updateProfiliTemplate(BasePaginateFinder finder)throws SQLException;
	public TemplateBean getTemplate( TemplateFinder finder ) throws SQLException;
	public List<StrutturaUfficioCDRExtBean> getElencoStruttUffCDRVisPerSottoAlberoByCDR(BaseFinder finder) throws SQLException; 
	public List<StrutturaUfficioCDRExtBean> getElencoStruttUffCDRVisPerAlberoByElencoCDR(BaseFinder finder) throws SQLException;
	public List<StrutturaUfficioCDRExtBean> getElencoStruttUffCDRInVisibilita(CDRBaseFinder finder) throws SQLException;
	
	//ASSOCIAZIONE OPERATORE RICHIEDENTE AUTORIZZATORE
	public void setFlagRichAutUtente(OperatoreFinder in)throws SQLException; 
	public void updateFlagUtenteCdrDisallienato(OperatoreFinder in)throws SQLException; 
	public OperatoreBean getFlagRichAutUtente(OperatoreFinder operatoreFinder)throws SQLException;
	public List<OperatoreBean> getRichiedentiByCDR(OperatoreFinder operatoriFinder) throws SQLException;
	public List<OperatoreBean> getAutorizzatoriByCDR(OperatoreFinder operatoriFinder) throws SQLException;
	
	public Integer getStrutturaIterByCdr(String cdr)throws SQLException;
	public Integer getStrutturaIterByCdrIILivello(String cdr)throws SQLException;

	public void aggiornaAssociazioni(AssociazOperRichiAutorFinder finderAssocia) throws SQLException;
	public void rimuoviAssociazione(AssociazOperRichiAutorFinder finderAssocia) throws SQLException;
	public void ripristinaRichieste(AssociazOperRichiAutorFinder finder) throws SQLException;
	public void inserisciAssociazioni(AssociazOperRichiAutorFinder finderAssocia) throws SQLException;
	
	/** REGISTRO VARIAZIONI DI RUOLO **/
	public ElencoBean getVariazioniDiRuoloUtenti(LeMieVariazioniRuoloCDRFinder finder)throws SQLException;
	public List<AmbitoBean> getAmbitoApplicativo() throws SQLException;	
	Integer getCountRichieste(RichiestaFinder finder) throws SQLException;
	Integer getCountRichiesteRR(RichiestaFinder finder) throws SQLException;
	public List<RegioneBean> getRegioniByCodApp(InterrogazioneProfilazioneFinder finder) throws  SQLException;
	public List<ProvinciaBean> getProvinceByCodApp(InterrogazioneProfilazioneFinder finder) throws  SQLException;
	public List<RegioneBean> getRegioniByCodAppMappatura(InterrogazioneProfilazioneFinder finder) throws  SQLException;
	public List<ProvinciaBean> getProvinceByCodAppMappatura(InterrogazioneProfilazioneFinder finder) throws  SQLException;
	public List<TipoUfficioBean> getTipiUfficio(InterrogazioneProfilazioneFinder finder) throws  SQLException;
	public List<TipoCdrBean> getTipiCdr(InterrogazioneProfilazioneFinder finder) throws  SQLException;
	public List<TipoUfficioBean> getTipiUfficioMappatura(InterrogazioneMappaturaProfiliUfficioFinder finder) throws  SQLException;
	public List<TipoCdrBean> getTipiCdrMappatura(InterrogazioneMappaturaProfiliUfficioFinder finder) throws  SQLException;
	
	public List<StrutturaUfficioCDRExtBean> getAlberoCdr(InterrogazioneProfilazioneFinder finder) throws SQLException;
	public List<StrutturaUfficioCDRExtBean> getAlberoCdrByRoulGroup(InterrogazioneProfilazioneFinder finder)throws SQLException;
	public List<StrutturaUfficioCDRExtBean> getAlberoCdrByRoulGroupMappatura(InterrogazioneProfilazioneFinder finder)throws SQLException;
	public List<StrutturaUfficioCDRExtBean> getAlberoUfficioByRoulGroup(InterrogazioneProfilazioneFinder finder)throws SQLException;
	public List<StrutturaUfficioCDRExtBean> getAlberoUfficioByRoulGroupMappatura(InterrogazioneProfilazioneFinder finder)throws SQLException;
	public List<StrutturaUfficioCDRExtBean> getAlberoUfficioByRoulGroupMappaturaNew()throws SQLException;
	public List<ProfiloBean> getListaProfili(InterrogazioneProfilazioneFinder finder)throws SQLException;
	public List<ProfiloBean> getListaProfiliAssegnati(InterrogazioneProfilazioneFinder finder)throws SQLException;
	public List<ProfiloBean> getListaDescrizioniProfRiepilogo(ProfiloFinder finder)throws SQLException;
	public List<ProfiloBean> getListaDescrizioniProfRichRiepilogo(ProfiloFinder finder)throws SQLException;
	public List<ProfiloBean> getListaDescrizioniProfRichAggiunti(ProfiloFinder finder) throws SQLException;
	public List<ProfiloBean> getListaDescrizioniProfRichRimossi(ProfiloFinder finder) throws SQLException;
	public List<ProfiloBean> profiliCompetenzaNewConDesc(ProfiloFinder finder)throws SQLException;
	public List<ProfiloBean> profiliCompetenzaNewConDescVisibilita(ProfiloFinder finder)throws SQLException;
	public int numProfiliCompSpec(ProfiloFinder finder)throws SQLException;
	public String profiloCompetenzaDescrDivisione(String cdr)throws SQLException;
	public int getNumeroProfiliCompetenza(ProfiloFinder finder)throws SQLException;
	public int getNumeroProfiliCdrVisibilita(ProfiloFinder finder)throws SQLException;
	
	public List<String> getEmailPerRuolo(String ruolo)throws SQLException;	
	public List<String> getEmailRichiedenteAndAmmLocaliByCdr(String cdr) throws SQLException;
	public List<String> ricavaMailSovraordinati(String cdr)throws SQLException;

	public List<ExportProfiloBean> getProfiliByTipologia(InterrogazioneProfilazioneFinder finder) throws SQLException;
	public List<ExportProfiloBean> getProfiliDisponibiliByAmbitoApp(String codAmbito) throws SQLException;	
	public List<ExportProfiloBean> getProfiliBySpecificoUff(InterrogazioneProfilazioneFinder finder) throws SQLException;
	public List<ExportProfiloBean> getProfiliBySpecificoCdr(InterrogazioneProfilazioneFinder finder) throws SQLException;
	public List<StrutturaUfficioCDRBean> getStruttureCdr(BaseFinder finder) throws SQLException;
	public List<StrutturaUfficioCDRBean> getUfficiCdr(BaseFinder finder) throws SQLException;
	public List<ExportProfiloBean> getProfiliByAdminGroup(InterrogazioneAdminGroupFinder finder) throws SQLException;
	public List<ExportProfiloMappaturaBean> getProfiliByRoleGroup(InterrogazioneAdminGroupFinder finder) throws SQLException;
	public List<ExportProfiloMappaturaBean> getProfiliByRoleGroupMainExport(InterrogazioneAdminGroupFinder finder) throws SQLException;
	public List<ExportProfiloMappaturaBean> getProfiliByCodiciApplicazioneExport(InterrogazioneAdminGroupFinder finder) throws SQLException;
	public List<ExportProfiloMappaturaBean> getProfiliExportSinottico(InterrogazioneAdminGroupFinder finder) throws SQLException;
	public List<ExportProfiloMappaturaBean> getProfiliMappaturaByRoleGroupCdrUfficio(ExportMappaturaFinder finder) throws SQLException;
	public List<ExportProfiloMappaturaBean> getProfiliMappaturaByRoleGroupTipoCdrTipoUfficio(ExportMappaturaFinder finder) throws SQLException;
	public int richiesteRevocaInCorso(OperatoreFinder operatoreFinder)throws SQLException;
	public int richiesteInCorsoPerLaCoppiaCFCDR(RegistroRichiesteFinder registroRichiesteFinder)throws SQLException;
	public int verificaRichiesteInCorso(RegistroRichiesteFinder registroRichiesteFinder)throws SQLException;

	public String getDescrCdrByCdr(String cdr) throws SQLException;
	public String getDescrCdrByCdrAncheChiuso(String cdr) throws SQLException;
	public String getDescrTipoUff(String codiceTipoUff) throws SQLException;
	public String getDescrTipoCdr(String codiceTipoCdr) throws SQLException;
	public String getFlagCdrBloccato(String codiceCdr) throws SQLException;
	public List<String> getListaCdrByCDRPerRegistri(String cdr)throws SQLException;
	public List<String> getListaCdrDiUnUfficioByCDR(String cdr)throws SQLException;
	public List<String> getListaCdrDiUnaStrutturaByCDR(String cdr)throws SQLException;
	public List<String> getListaCdrDiUnaStrutturaByCDRConChiusi(String cdr)throws SQLException;
	public List<String> getRichiedentiCdrSottostanti(String codiceCdr) throws SQLException;
	
	
	/**FUNZIONI BATCH 1 Aggiornamento Profili**/ 
 	public List<RichiestaBean> verificaRichiesteInCorso(String cf)throws SQLException;
 	public RichiedenteCdR selectCFRichiedenteAndResponsabileHR(String cdr)throws SQLException;
 	public Utenti selectEmailUtenteByCF(String cfGenerico)throws SQLException;
 	public List<ProfiliAttiviDAO>  selectProfiliAttivi(	String cf)throws SQLException; 
 	public void  updateUtentiAggiornatiHR(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
 	public void  updateUtentiAggiornatiHRNoMail(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
 	public void  deleteProfiliAttiviUtente(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
 	public void  updateStoricoProfiliOperatore(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
 	public void  updateProfiliUtenteInVisibilita(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
	public List<UtenteInVisibilita>  selectCdRVisibilitaAndIdRchiestaVisibilita(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
	public AssociazOperRichiAutor  selectCFRichiedenteIstituzionale(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
	public RichiedenteCdR  selectCFIstituzionaleFromRichiedenteCdR(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
 	public String selectEmailByCF(String cfGenerico)throws SQLException;
	public AssociazOperRichiAutor  selectCFRichiedenteDiVisibilita(HashMap input)throws SQLException; 
	public RichiedenteCdR  selectCFVisibilitaFromRichiedenteCdR(HashMap input)throws SQLException; 
 	public void  updateUtentiAggiornatiHRMailRevoca(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
 	public List<String>  selectCodiceCdROfStrutturaAppartenenzaCF(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
 	public Integer  verificaOperatoriAssPuntRichiedente(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
 	public Utenti  selectCdRUtenteCessato(UtentiAggiornatiHRDAO utentiAggiornatiHR)throws SQLException; 
	public List<ProfiliAttiviUtente> selectCFProfiliAttiviUtenteByCdRAndProfile(String cdROfStrutturaAppartenenzaCF)throws SQLException; 
	public void insertNotificaOperatori  (NotificaOperatoriDAO notificaOperatori)throws SQLException; 
	public List <UtentiAggiornatiHRDAO>  selectUtentiAggiornatiHR ()throws SQLException; 
	public Integer  countNotificaOperatori()throws SQLException; 
	public void  deleteNotificaOperatori()throws SQLException; 
	public void  updateDataElaUtentiAggiornatiHR()throws SQLException; 
	public List <NotificaOperatoriDAO>  selectDistinctStrutturaAndOggettoFronNotificaOperatori ()throws SQLException; 
	public List <NotificaOperatoriDAO>  selectDistinctEmailFronNotificaOperatori (String codiceStruttura)throws SQLException; 
	public List <NotificaOperatoriDAO>  selectDistinctAnagraficaUtente (String codiceStruttura)throws SQLException; 
	public void deleteStrutturaFromNotificaOperatori (String codiceStruttura)throws SQLException; 
	public void  updateUtenteInVisibilita ( UtenteInVisibilita utenteInVisibilita)throws SQLException;
	
	
	/**FUNZIONI BATCH 1 AllineamentoResponsabileCdR**/ 
	
	public Integer selectMaxIDAudit ()throws SQLException; 
	public void closeCFResponsabileHR(RichiedenteCdR richiedenteCdR) throws SQLException;
	public void insertNewCFResponsabileHR(RichiedenteCdR richiedenteCdR) throws SQLException;
	public List <CdR> selectCdRAperti ()throws SQLException; 
	public RichiedenteCdR selectCFResponsabileHR (String codiceCdR)throws SQLException;
	public Utenti selectUtenteByCFLdap (String responsabile)throws SQLException;
	public void insertUtenti(HashMap <String,String> input)throws SQLException;
	public void insertStoricoCdRUtenti(HashMap <String,String> input)throws SQLException;
	public void insertStoricoCdRUtentiWithStartDate(HashMap <String,String> input)throws SQLException;	
	public void insertStoricoCdRUtenti_Gen(HashMap <String,String> input)throws SQLException;
	public void insertUtentiAggiornatiHR(HashMap <String,String> input)throws SQLException;
	public void setFlagRichiedenteSI(String responsabile)throws SQLException;
	public void setFlagAutorizzatoreIIlivelloSI(String responsabile)throws SQLException;
	public void setFlagAutorizzatoreIlivelloSI(String responsabile)throws SQLException;
	public Utenti verifyAutorizzatoreByOldCFResponsabile(String cfResponsabileHR)throws SQLException;
	public void setFlagAutorizzatoreNO(Utenti oldCFUtente)throws SQLException;
	public Utenti verifyAutorizzatoreByNewCFResponsabile (String responsabile)throws SQLException;
	public Integer  verifyRuoloAutorizzatoreILivByCdR (String codiceCdR)throws SQLException; 
	public Integer  verifyRuoloAutorizzatoreILivByCdRorTipoCdR (String codiceCdR)throws SQLException;
	public Integer  verifyRuoloStrutturaIter(String codiceCdR)throws SQLException;
	public Integer  verifyRuoloAutorizzatoreIILivByCdRorTipoCdR (HashMap <String,String> hashMapForQuery)throws SQLException;
	public Integer  verifyRuoloAutorizzatoreIIStrutIter(HashMap <String,String> hashMapForQuery)throws SQLException;
	public void  setCFAutorizzatoreIIlivelloRegistroRichieste(HashMap <String,String> hashMapForQuery)throws SQLException;
	public void setFlagAutorizzatoreSI(Utenti newCFUtente)throws SQLException;
	public List<RelazioneCdRUfficioStrut>  selectCodiceCdROfStrutturaAppartenenzaCF(String codiceCdR)throws SQLException;
	public void setCFAutorizzatoreIlivelloRegistroRichieste (HashMap <String,String> hashMapForQuery)throws SQLException;

	
	/**FUNZIONI BATCH 1 VariazioneDatiUtenteVersioneRidotta**/ 
	public List<RichiedenteCdR> selectCdRFromRichiedenteCdRByCFRichiedente(HashMap<String , String> input)throws SQLException ;
	public List<RichiedenteCdR> selectCdRFromRichiedenteCdRByCFAutorizzatore(HashMap <String,String> input)throws SQLException;
	public List<AssociazOperRichiAutor> selectCFAutorizzatoreByCFRichiedente(HashMap <String,String> input)throws SQLException;
	public List<AssociazOperRichiAutor> selectCFRichiedenteByCFAutorizzatore(HashMap <String,String> input)throws SQLException;
	public List<StrutturaIterDAO> verifyAutorizzatoreIORIILivello(HashMap <String,String> input)throws SQLException;
	public void updateStrutturaIter(HashMap <String,String> input)throws SQLException;
	public void insertStrutturaIter( StrutturaIterDAO strutturaIter)throws SQLException;
	public List<RegistroRichieste> selectCdRAndCFUtenteFromRegistroRichiesteByCFAutorizzatore (HashMap <String,String> input)throws SQLException;
	public Integer  verifyDelegato ( String cfDelegato)throws SQLException;
	public Integer  countAssociazOperRichiAutor  (HashMap <String,String> input)throws SQLException;
	public void updateUtentiForVerificaOperatore(Utenti utentiForOperatore)throws SQLException; 
	public void  annullamentoRichieste(HashMap <String,String> input)throws SQLException;
	public List<RegistroRichieste> selectCFPresaINCaricoFromRegistroRichieste (HashMap <String,String> input)throws SQLException; 
	public Utenti  selectCFbyCFPresaInCaricoFromUtenti(String cfPresaInCarico)throws SQLException; 
	public void  insertAuditOperazioni(HashMap <String,String> input)throws SQLException;
	public void  insertAuditOperazioni(String testo)throws SQLException;
	public void updateGestoreOperatore (HashMap <String,String> input)throws SQLException;
	public List<CauUserIdOrgName> selectAllUtentiCTSA () throws SQLException;
	public Integer countUtenteByCF (String userId) throws SQLException;
	public Integer countUtenteByCFClosed (String userId) throws SQLException;
	public void insertUtentiSiga (HashMap <String,String> input)throws SQLException;
	public Utenti selectUtenteByCF(String cfGenerico)throws SQLException;
	public void  updateStoricoCdrUtenti (Utenti utentiSiga)throws SQLException;
	public void updateUtenti (Utenti utentiSiga)throws SQLException;
	public List<Utenti> selectAllCFUtenti ()throws SQLException;
	public void  updateUtentiPerCessazione (String codiceFiscaleUtenti)throws SQLException;
	public void  updateUtentiPerRipristino (HashMap<String , String> input )throws SQLException;
	public void updateStoricoCdrUtentiPerCessazione(Utenti utentiSiga)throws SQLException;
	public AssociazOperRichiAutor selectCFRichiedenteEAutorizzatoreByCFOperatore (HashMap <String,String> input)throws SQLException;
	public void closeAssociazOperRichiAutorByCFOperatore(HashMap <String,String> input)throws SQLException;
	// 1.6 2023 -->
	public List<String> selectUtentiByCdrDisall() throws SQLException;
	// 1.6 2023 <--
	public RelazioneCdRUfficioStrut strutturaNew(HashMap <String,String> input)throws SQLException;
	public RelazioneCdRUfficioStrut  strutturaOld(HashMap <String,String> input)throws SQLException;
	public Integer countUtenteCTSAByCF(String codiceFiscaleUtenti)throws SQLException;
	public void insertUtentiAggiornatiHR_Var(HashMap <String,String> input)throws SQLException;
	public List<RegistroRichieste> selectIdRichiestaFromRegistroRichieste (RegistroRichieste registroRichieste)throws SQLException;
	public int inserisciAllegatoOperatoreEsterno(AllegatoBean allegatoBean) throws SQLException;
	public List<NotificaOperatoriDAO> utentiRichiesteDaSegnalare(HashMap<String, String> input) throws SQLException;
	
	/**FUNZIONI BATCH 1 AllineamentoOrganizzazioni**/ 
	
	public String selectRegione(String provincia)throws SQLException; 
	public Integer countUfficio(HashMap<String , String> inputUfficio)throws SQLException; 
	public Integer countTipoUfficio(HashMap<String , String> hashMapVerticeStrutturaEUfficio)throws SQLException; 
	public Integer countTipoCdR (CdR cdrVerticeUfficio)throws SQLException; 
	public void insertTipoCdR(CdR cdrVerticeUfficioEStruttura)throws SQLException; 
	public Integer countCDRSiga (String orgName)throws SQLException; 
	public void insertTipoUfficio(HashMap<String , String> hashMapVerticeStrutturaEUfficio )throws SQLException; 
 	public CdR selectCDRFatherSiga (String orgName)throws SQLException; 
	public void insertRelazioneCdRUfficioStrut(RelazioneCdRUfficioStrut relazioneCdRVerticeUffStrutturaUfficioStrutt)throws SQLException; 
	public Integer countStruttura(HashMap<String , String> hashMapVerticeUfficioEStruttura)throws SQLException; 
	public Integer countTipoStruttura(HashMap<String , String> hashMapVerticeUfficioEStruttura)throws SQLException; 
	public void insertTipoStruttura(HashMap<String , String> hashMapVerticeUfficioEStruttura)throws SQLException; 
	public void insertCdR (CdR cdrVerticeStruttura)throws SQLException; 
	public void insertStruttura (HashMap<String , String> inputStruttura)throws SQLException; 
	public void closeRelazioneCdRUfficioStrut (RelazioneCdRUfficioStrut relazioneCdRUfficioStr)throws SQLException; 
	public void insertRelazioneCdRUfficioStrutPerVariazione (RelazioneCdRUfficioStrut relazioneCdRUfficioStr)throws SQLException; 
	public void  updateCdR(CdR  updateCdrWithNewFather)throws SQLException;
	public void  closeCdr(  HashMap <String,String> hashMapCodiceCdR )throws SQLException;
	public List<RelazioneCdRUfficioStrut> selectAllCdRFromRelazioneCdrUfficioStrut (OrganizzazioniFinder orgFinder)throws SQLException;
	public Integer countCdrCau(CdR  cdr)throws SQLException;
  	public void  closeUfficio( HashMap <String,String> hashMapCodiceCdR)throws SQLException;
	public void  updateTemplate(  HashMap <String,String> hashMapCodiceCdR)throws SQLException;
	public void  insertUfficio(  HashMap <String,String> inputUfficio)throws SQLException;
	public RelazioneCdRUfficioStrut  selectCdRFromRelazioneCdrUfficioStrut(String orgName)throws SQLException;
	public void closeRichiedenteCdr( HashMap <String,String> hashMapCodiceCdR)throws SQLException;
	public void closeStruttura( HashMap <String,String> hashMapCodiceCdR)throws SQLException;
	public List<RichiedenteCdR> getAllAutI(String input)throws SQLException;
	public List<RichiedenteCdR> getAllAutII(String input)throws SQLException;
	public List<RichiedenteCdR> getAllRich(String input)throws SQLException;

	public List<String> selectAllUtentiRichiAutResp()throws SQLException;
	public void aggiornaFlagUtente( Utenti ute)throws SQLException;
	
	public List<Utenti> countRegistroRichieste (HashMap<String, String> input)throws SQLException; 
	public  Struttura verificaAmministratori (String codiceCdR)throws SQLException;
	public List<String> selectEmailUtenteByCdRIILivGerarchico(String  struttura)throws SQLException; 
	public List<String> selectEmailAmministratoriCentrali () throws SQLException; 
	
	public List<RelazioneCdrUfficioStrutOrganizz> getListaRelazCdrUffStrut()throws SQLException;
	public void updateRelazioneCdrUfficioStruttura(RelazioneCdrUfficioStrutOrganizz rel) throws SQLException;
	public void  closeItenteInVisibilita_Org(  HashMap <String,String> hashMapCodiceCdR)throws SQLException;
	public void  closeAdminGroupCdrUff_Org(  HashMap <String,String> hashMapCodiceCdR)throws SQLException;	
	
	/** OPERATORI ESTERNI **/
	public Integer countPresenzaOperatoreCancellato(OperatoreBean operatoreBean) throws SQLException;
	public void aggiornaUtente(OperatoreBean operatoreBean) throws SQLException;
	public void inserisciUtente(OperatoreBean operatoreBean) throws SQLException;
	public void inserisciOperatoreEsterno(OperatoreBean operatoreBean) throws SQLException;
	public void inserisciRelazioniTipologieOperatoreEsterno(OperatoreBean operatoreBean) throws SQLException;
	public void inserisciAllegatiOperatoreEsterno(OperatoreBean operatoreBean,Set<FormFile> allegati) throws SQLException;	
	public List<TipologiaUtenteBean> getTipologieUtenti(TipologiaUtenteBean tipologiaUtenteBean) throws SQLException;
	public boolean inserisciOperatoreEsternoTipoUtente(OperatoreEsternoTipoUtenteBean operatoreEsternoTipoUtente) throws SQLException; 
	public List<TipologiaUtenteBean> getProfiliDisponibiliByTipoOperatoreEsterno(OperatoreFinder operatore) throws SQLException;
	public List<TipologiaUtenteBean> getProfiliNonAmmessiByTipoOperatoreEsterno(OperatoreFinder operatore) throws SQLException;
	public List<OperatoreBean> getOperatoriEsterni(OperatoreBean operatore) throws SQLException;
	public List<TipologiaUtenteBean> getTipologieAttiveOperatoreEsterno(OperatoreBean operatore) throws SQLException;
	public List<AllegatoBean> getAllegatiOperatoriEsterni(AllegatoBean allegatoBean) throws SQLException;
	public AllegatoBean getAllegatoOperatoreEsterno(Integer id) throws SQLException;
	public void aggiornaOperatoreEsterno(OperatoreBean operatore) throws SQLException;
	public void aggiornaEmailUtente(OperatoreBean operatore) throws SQLException;
	public void cancellaRelazioneTipologiaOperatoreEsterno(OperatoreEsternoTipoUtenteBean operatoreEsternoTipoUtenteBean) throws SQLException;
	public void inserisciRelazioneTipologieOperatoreEsterno(OperatoreEsternoTipoUtenteBean operatoreEsternoTipoUtenteBean) throws SQLException;
	public void inserisciNoteCancellazioneOperatoreEsterno(OperatoreBean operatore) throws SQLException;
	public void cancellaUtente(OperatoreBean operatore) throws SQLException;	
	public List<ProfiloSimpleBean> getProfiliAttiviByCFAndCodiceTipologiaLimitata(OperatoreBean operatoreBean) throws SQLException;
	public List<ProfiloSimpleBean> getProfiliAttiviByCF(OperatoreBean operatoreBean) throws SQLException;
	public List<CDRBean> getCdrInfo(CDRFinder finder) throws SQLException;	
	public List<String> getCdrAmmLocaleDaDisabilitare(CDRFinder finder) throws SQLException;	
	public List<ProfiloBean> getProfiliAmmessiOperatoreEsterno(OperatoreBean operatore) throws SQLException;
	public List<ProfiloBean> getElencoProfiliCDR(CDRFinder finder) throws SQLException;
	public List<TipologiaUtenteBean> getElencoTipologieUtenti() throws SQLException;
	public List<ProfiloSimpleBean> getProfiliAttiviPerTipologiaByCF(OperatoreBean operatore) throws SQLException;
	public void chiudiStoricoCdrUtente(OperatoreBean operatore) throws SQLException;
	public List<ProfiloSimpleBean> getProfiliScopertiByCFAndTipologieBloccate(OperatoreBean operatore) throws SQLException;
	
	
	
	/** MAPPATURA PROFILI UFFICIO **/
	public void insertMappaturaCdrUff (MappaturaProfiloUfficioBean mappaturaBean)throws SQLException;
	public void insertMappaturaTipoUffCdr (MappaturaProfiloUfficioBean mappaturaBean)throws SQLException;
	public void cancellaMappaturaTipoUffCdr (MappaturaProfiloUfficioBean mappaturaBean)throws SQLException;
	public void updateMappatura(MappaturaProfiloUfficioBean mappaturaBean)throws SQLException;
	public String getDescrizioneRegione(InterrogazioneRegioniProvinceFinder finder)throws SQLException;
	public String getDescrizioneProvincia(InterrogazioneRegioniProvinceFinder finder)throws SQLException;
	public String getCodiceRegione(InterrogazioneRegioniProvinceFinder finder)throws SQLException;
	public String getCodiceProvincia(InterrogazioneRegioniProvinceFinder finder)throws SQLException;
	public List<ApplicazioniEntity> getRoleGroupsByCodiceApplicazione(InterrogazioneAdminGroupFinder finder) throws SQLException;
	
	/** BATCH **/
	public List<String> getElencoAmministratoriLocali(String cdr)throws SQLException;
	
	/** BATCH COERENZA **/
	public Integer getNumeroUtenti() throws SQLException;
	public void inserisciRichiestaBatchOE(RichiestaAbilitazioneDisabilitazioneBean richiestaBean) throws SQLException;
	public void incrementaTentativoRichiestaBatchDaElab(String idRichiesta) throws SQLException;
	public CaricamentoMassivoEntity cancellaRichiestaBatchDaElab(String idRichiesta) throws SQLException;
	public Integer getTentativiRichiestaBatchDaElab(String idRichiesta) throws SQLException;
	public List<String> getListaCodiciIILiv(Map<String,Integer> queryMap)throws SQLException;
	public ResponsabilitaBatch getResponsabilitaDaElaborare() throws SQLException;
	public ResponsabilitaOA getCodeApplAndRoleA(ResponsabilitaFinder finder) throws SQLException;
	public void  insertResponsabilita(ResponsabilitaOA input) throws SQLException;
	public void  cancellaProfilo(ResponsabilitaOA input) throws SQLException;
	public void  cancellaAppProfRoleGroup(ResponsabilitaOA input) throws SQLException;
	public void  updateProfilo(ResponsabilitaOA input) throws SQLException;
	public void  updateResponsabilita(ResponsabilitaOA input) throws SQLException;
	public void  cancellaAgCdrUff(ResponsabilitaOA input) throws SQLException;
	public void  insertProfilo(ResponsabilitaOA input) throws SQLException;
	public void  insertAppProfRole(ResponsabilitaOA input) throws SQLException;
	public Integer countProfilo(ResponsabilitaOA input) throws SQLException;
	public Integer countProfiliRichiesta(ResponsabilitaOA input) throws SQLException;
	public Integer countAppProfRole(ResponsabilitaOA input) throws SQLException;
	public Integer countAGCdrUff(ResponsabilitaOA input) throws SQLException;
	public Integer countCdrUff(ResponsabilitaOA input) throws SQLException;
	public void  insertAGCdrUff(ResponsabilitaOA input) throws SQLException;	
 	public List <ResponsabilitaOA> getRelCdrUffStrutt(ResponsabilitaFinder input) throws SQLException;
 	public List <ResponsabilitaOA> getResponsabilitaOA(ResponsabilitaFinder input) throws SQLException;
 	public long getSequenceFromRichiesteBatch() throws SQLException;
 	public long getCurrSequenceFromRichieste() throws SQLException;
 	public List<String> getCodiciStrutturaByCodUff(String codiceUfficio) throws Exception;
 	public List<Struttura> getCodCdrCodStrutturaByCodUff(String codiceUfficio) throws Exception;
  
 	/** BATCH CESSAZIONE MOBILITA **/
 	public List<RichiestaBatchBean> getRichiesteBatchCessazioneUtentiRiorg(List<String> eventiList)throws SQLException;
 	public List<RichiestaBatchBean> getRichiesteBatchCessazioneUtenti(List<String> eventiList)throws SQLException;
 	public CDRBean getCdrInfoByCodice(String cdr) throws SQLException;
 	public EventoBean getEvento(String codiceEvento)throws SQLException;
 	public void cessaProfili(ProfiloRichiestaBean input, boolean mobilita)throws SQLException;
 	public List<String> getAmbitiByCf(String cfProfilo)throws SQLException;
 	public String getCodCdrByCf(String codiceFiscale)throws SQLException;
 	public String getCodCdrByCfToday(String codiceFiscale)throws SQLException;
 	public void inserisciRegistroRichiesta(RegistroRichiesteBatchBean regRichBetchBean)throws SQLException;
 	public List<ProfiliAttiviUtente> getListaProfiliByCfAmbito(Map queryMap) throws SQLException;
 	public void inserimentoProfiloRichiesta(it.finanze.scheduler.bean.ProfiloRichiestaBean profRichBetchBean)throws SQLException;
 	public void cancellaRecordRichiesteBatch(Map queryMap)throws SQLException;
 	public void annullamentoRichieste(String cfUtente)throws SQLException;
 	public void aggiornaUtentiVisibilitaMobilita(ProfiloRichiestaBean richMob)throws SQLException;
 	public void aggiornaProfiliUtenteVisibilita(ProfiloRichiestaBean richMob)throws SQLException;
 	
 	public void updateUtente(String cfUtente)throws SQLException;
 	public void updateOperatoreEsterno(String cfUtente)throws SQLException;
 	public String getCodiceCdrPerCancellazioniCau(String cfUtente)throws SQLException;
 	public  List<RichiestaBean> getRichiesteDaAnnullare(Map queryMap) throws SQLException;
 	public List<RichiestaBean> getRichiesteNonEseguite(Map queryMap) throws SQLException;
 	public List<RichiestaBean> getRichiesteNonIntegrateGestore(Map queryMap) throws SQLException;
 	
	public List<ProfiliAttiviUtenteOperazioneBean> getProfiliAttiviUtenteByCdr(HashMap<String,String> queryMap) throws SQLException;
	public List<ProfiliAttiviUtente> getProfiliAttiviUtenteByCdrGestRete(HashMap<String,String> queryMap) throws SQLException;
	public void cancellaUtenteDaTabellaRiorganizzazione(String codFisc) throws SQLException;
	public void insertUtenteInTabellaRiorganizzazione(String codFisc) throws SQLException;


 	//batch richieste
 	
	// lista CDR per Amm Centrale
 	public List<OperatoreBean> getListaCDRDisponibiliByCf(String cfOperatore) throws SQLException;
	 
	//BATCH GENERA RICHIESTE
 	public EventiSistemaBean getEventiSistema(String in) throws SQLException;
	public  ProfiloRichiestaBean  getCodApplicazione(String profilo) throws SQLException;
	public  String  getCdr(String cf) throws SQLException;
	public 	List<RichiestaBatchBean> getRichiesteBatch (RichiestaFinder input ) throws SQLException ;
	public 	List<RichiestaBatchBean> getRichiesteBatchDistinct (RichiestaFinder input ) throws SQLException ;
	public 	List<RichiestaBatchBean> getRichiesteBatchToRichieste (RichiestaFinder input ) throws SQLException ;
	public 	Integer countRichieste (Integer input ) throws SQLException ;
	public 	void inserisciRichiestaBatch( RichiestaBean in ) throws SQLException ;
	public 	void aggiornaRichBatchToElab( RichiestaFinder input ) throws SQLException ;
	public 	void eliminaRichesteBatch( RichiestaFinder input ) throws SQLException ;
	public List<RichiestaBatchBean> getRichiesteToElab(RichiestaFinder input) throws SQLException;
	public List<ProfiloAbilitazioneBatchBean> getAbilitazioniBacth() throws SQLException;
	// mio profilo
	public List<InterrProfiliOperatoreBean> getElencoProfiliOperatoreGroupCdr(InterrProfiliOperatoreFinder finder) throws SQLException;

	/** Caricamenti Massivi **/
	public List<String> getAnniCaricamento(String codiceFiscale) throws SQLException;
	public List<CaricamentoMassivoEntity> getElencoCaricamentiUtentiPerAnno(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public CaricamentoMassivoEntity getDettaglioCaricamento(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public long getSequenceFromRichiesteCaricamentoMassivo() throws SQLException;
	public void inserisciRichiestaCaricamentoMassivo(CaricamentoMassivoEntity cmEntity) throws SQLException;
	public List<FileAbilitazioneBean> getRecordsFileAbilitazioni(InterrogazioneCaricamentiFinder finder) throws SQLException;
	public List<FileAbilitazioneBean> getRecordsCmVisibilitaFile(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public void avvioImmediatoControlli(String idCaricamento) throws SQLException;
	public ControlliRecCaricMassiviBean getLimitiRecordControlliMassivi()throws SQLException;
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamento(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamentoNoBlob(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public AllegatoCaricamentoMassivoEntity getAllegato(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public void annullaRichiestaCaricamentoMassivo(CaricamentoMassivoEntity cmEntity) throws SQLException;
	public void updateControlloRichiestaCaricamentoMassivo(CaricamentoMassivoEntity cmEntity) throws SQLException;
	public EventoBean getEventoCM(String codiceEvento) throws SQLException;
//	public CoerenzaPropertiesBean selectPropertiesBatchCoerenza() throws SQLException;
	public CoerenzaPropertiesBean selectCostantiSigaBatchCoerenza() throws SQLException;
	public void updateStatoRichiestaCaricamento(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public void updateStatoRichiestaCarMassVis(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public List<FileMappaturaBean> getRecordsCmMappatureFile(InterrogazioneCaricamentiFinder finder)throws SQLException;
	
	//GESTIONE BATCH UFFICI PILOTA
	public List<String> getListaUtentiByCdr(String codiceCdr) throws SQLException;
	public List<ProfiliAttiviUtente> getProfiliAttiviUtenteByCdrProfCf(String codiceProfilo, String codiceCdr, String codiceFiscale) throws SQLException;	
	public List<UtentiAggiornatiHRDAO> getStruttureORAUteCessMob() throws SQLException;
	public List<UtentiAggiornatiHRDAO> getStruttureORAsi() throws SQLException;
	public List<UtentiAggiornatiHRDAO> getStruttureORAno() throws SQLException;
	public void updateEmailUtentiAggiornatiHR(UtentiAggiornatiHRDAO input) throws SQLException ;
	public String selectTrattamento(String input) throws SQLException;
	
	public List<UtentiAggiornatiHRDAO> selectUtentiDaAvvisare() throws SQLException;
	public List<String> getEmailAmmLocaleByCdr(String input) throws SQLException;
	public void inserisciAllegatiCaricamentoMassivo(Set<FormFile> allegati, String tipo, long idCaricamento) throws SQLException;

	public Struttura getCentralePeriferico(String input) throws SQLException;
	public String isCentralePeriferico(String input) throws SQLException;

  	public List<String> getCodUffByCodCrdIILiv(String input) throws SQLException;
	public void  annullamentoRichieste2(HashMap <String,String> input)throws SQLException;

	public Integer verificaEsistenzaCf(String cf) throws SQLException;
	public Integer verificaEsistenzaRG(String codRg) throws SQLException;
	public List<ProfiloCM> getProfiliCM(String codiceProfilo) throws SQLException;
	public List<String> getCodiciAppCM(HashMap input) throws SQLException;
	public Integer verificaEsistenzaCdr(String cdr) throws SQLException;
	
	//Caricamento Massivo Mappatura Abilitazioni Ufficio
	public Integer verificaEsistenzaUfficio(String ufficio) throws SQLException;
	public Integer verificaEsistenzaRegione(String regione)throws SQLException;
	public Integer verificaEsistenzaProvincia(String provincia)throws SQLException;
	public Integer verificaEsistenzaCodUfficio(String codUfficio)throws SQLException;
	
	//Assegnazione gestori operatori
	public List<AssegnazioneGestoriOperatoriEntity> getListaApplAssegnazioniOperatori(ApplicazioniFinder finder)throws SQLException;
	public List<GestoreOperatoriBean> getListaGestoriOperatori(GestoreOperatoriFinder finder)throws SQLException;
	public List<ApplicazioniEntity> getListaTotaleApplicazioni(ApplicazioniFinder finder)throws SQLException;
	public List<ApplicazioniEntity> getListaTotaleApplicazioniAL(ApplicazioniFinder finder)throws SQLException;
	public void cancellaGestoreOperatori(GestoreOperatoriBean gestore)throws SQLException;
	public void insertGestoreOperatoriGruppo(GestoreOperatoriBean gestore) throws SQLException;
	public void insertGestoreOperatori(GestoreOperatoriBean gestore) throws SQLException;
	public List<GestoreOperatoriBean> getListaGestoriOperatoriByCodStr(GestoreOperatoriFinder finder)throws SQLException;
	public List<GestoreOperatoriBean> getListaGestoriOperatoriByCodCdr(GestoreOperatoriFinder finder)throws SQLException;
	public void aggiornaPresaInCaricoGestoreOperatori(GestoreOperatoriBean gestore) throws SQLException;
	public Integer getNumeroGestori(GestoreOperatoriFinder finder) throws SQLException;
	public List<OperatoreBean> getListaUtentiNuovoGestore(OperatoreFinder finder) throws SQLException;
	public List<CDRBean> getCdrListByStr(GestoreOperatoriFinder codiceStr) throws SQLException;
	public List<OrigineRichiestaBean> getListaOrigineRichiesta () throws SQLException;
	
	//gestione start batch
	public int getStatoBatch(String nomeBatch) throws SQLException;
	public  String  getEmailBatch() throws SQLException;
	public void aggiornaStatoBatch(String nomeBatch) throws SQLException;
	public String  getTipoCdrHR(String cdr) throws SQLException;
	
	public void updateUfficioXTipoCdrHR(HashMap input) throws SQLException;
	public void updateStrutturaXTipoCdrHR(HashMap input) throws SQLException;
	public String selectCdRApertiByCdr(String cdr) throws SQLException;
	public Integer countTipoUtente(String cdr) throws SQLException;
	public List<Utenti> getDatiUtenteByCdr(String cdr) throws SQLException;
	//gestione utenti sogei
	public ElencoBean getElencoUtentiSogei(BaseFinder in) throws SQLException;
	public ElencoBean getElencoUtentiAmm3(BaseFinder in) throws SQLException;
	public ElencoBean getAmmRegLoc(String in) throws SQLException;
	
	public boolean isCdRVerticeStruttura(String codiceCdR)throws SQLException;
	public String getCodiceCdRByCodiceStruttura(String codiceStrutt)throws SQLException;
	public List<String> getVerticeStrutturaAmmLoc(CDRFinder finder)throws SQLException;
	Integer countCdrPadre(String orgName) throws SQLException;
	public void inserisciPresaInCaricoGestoreOperatori(GestoreOperatoriBean gestore) throws SQLException;
	public void cancellaPresaInCaricoGestoreOperatori(String cf) throws SQLException;
	public String getCodApplByIdRichiesta(Integer finder) throws SQLException;
	
	List<ProfiloRichiestaBean> getProfiliRichiesta(RichiestaFinder finder) throws SQLException;
	
	public String getVerticeStrutturaFromCdr(String codiceCdR)throws SQLException;
	public List<StatoRichiestaBean> getStatiRichiesta() throws SQLException;
	public String getCdrPadreGerarchia(String cdrOperatore) throws SQLException;
	public List<InterrProfiliOperatoreBean> getElencoProfiliOperatoreGroupCdrRichiesta(InterrProfiliOperatoreFinder finder) throws SQLException;
	public List<InterrProfiliOperatoreBean> getElencoProfiliOperatoreGroupCdrRichiestaDisabilitati(
			InterrProfiliOperatoreFinder finder) throws SQLException;
	public List<TipoAbilitazioneBean> getTipoAbilitazione()throws SQLException;
	public List<StrutturaUfficioCDRBean> getElencoCdrFromStruttureList(BaseFinder finder) throws SQLException;
	public List<StrutturaUfficioCDRBean> getElencoStruttureStrutturaIIliv(String strutturaIILiv) throws SQLException;
	public List<StrutturaUfficioCDRBean> getElencoCdrDiUnaStruttura(BaseFinder finder) throws SQLException;
	
	
	public List<String> getElencoArchiviazioneDaRichiesteRA(RichiestaFinder in)throws Exception;
	public List<String> getElencoAutorizzatori1LivelloTotaliDaRichiesteRA(RichiestaFinder in) throws Exception;
	public List<String> getElencoGestoriOperatoriDaRichiesteRA(RichiestaFinder in)	throws Exception;
	public List<String> getElencoCfUtentiDaRichiesteRA(RichiestaFinder in) throws Exception;
	public List<String> getElencoRichiedentiTotaliDaRichiesteRA(RichiestaFinder in) throws Exception;
	public List<String> getElencoAutorizzatori2LivelloTotaliDaRichiesteRA(	RichiestaFinder in) throws Exception;
	public List<StrutturaUfficioCDRExtBean> getElencoPerAlberoStrutture(
			BaseFinder finder) throws SQLException;
	
	public List<BatchAbilitazioniRichiestaFinder> getRichiesteAbilitazioniVis(BatchAbilitazioniRichiestaFinder input) throws SQLException;
	public int  cancellaRichestaAbilitazioniVis(BatchAbilitazioniRichiestaFinder input) throws SQLException;
	public int inserisciRichiestaAbilitazioniVis(BatchAbilitazioniRichiestaFinder input) throws SQLException;
	public List<CDRBean> getElencoSecondiLivelli() throws SQLException;
	public List<ProfiloRichiestaBean> getElencoProfiloProfilazioneUfficioDaCdRList(ProfiloFinder input) throws SQLException;
	public List<ProfiloRichiestaBean> getElencoProfiloProfilazioneUfficioIILiv(ProfiloFinder input) throws SQLException;
	public String getValoreCostante(String input) throws SQLException;
	public List<ProfiloRichiestaBean> getElencoProfiliUfficioDaCdRList(ProfiloFinder input) throws SQLException;
	public List<ProfiloRichiestaBean> getElencoProfiliUfficioIILiv(ProfiloFinder input) throws SQLException;
	public int getIdBatchAbilitazioniRichiesta() throws SQLException;
	public boolean inserisciFiltriRicerca(BatchAbilitazioniRichiestaFinder input) throws SQLException;
	public AllegatoBean getFileBatchAbilitazioni(BatchAbilitazioniRichiestaFinder finder) throws SQLException;
	public List<ExportProfiloBean> getElencoProfiliBatch(BatchAbilitazioniRichiestaFinder finder)throws SQLException;
	public String getDescrizioneRG(String codice) throws SQLException;
	public String getDescrizioneAbilitazione(String codice) throws SQLException;
	public List<Profilo> getApplicazioniProfiliAmmCenAppl(String codFiscale) throws SQLException;

	
	// Aggiornamento Massivo Richieste Visibilite'
	public void inserisciRichiestaAggiornamentoMassivoVisibilita(CaricamentoMassivoEntity cmEntity) throws SQLException;
	public long getSequenceFromRichiesteAggiornamentoMassivoVisibilita() throws SQLException;
	public List<CaricamentoMassivoEntity> getElencoCaricamentiAggiornamentiVisibilitaUtenti(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public ControlliRecCaricMassiviBean getLimitiRecordControlliMassiviPerEvento(HashMap<String, String> input)throws SQLException;
	public CaricamentoMassivoEntity getDettaglioCaricamentoAggVis(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public void annullaRichiestaCaricamentoMassivoAggVis(CaricamentoMassivoEntity cmEntity) throws SQLException;
	public void updateStatoRichiestaCaricamentoAggVis(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public void avvioImmediatoControlliAggVis(String idCaricamento) throws SQLException;
	public void updateControlloRichiestaCaricamentoMassivoVisib(CaricamentoMassivoEntity cmEntity) throws SQLException;
	public List<FileAbilitazioneBean> getRecordsVisibilitaFile(InterrogazioneCaricamentiFinder finder) throws SQLException;
	public List<ExportProfiloBean> getProfiliDisponibiliVisibByAmbitoApp(String codAmbito) throws SQLException;
	public void inserisciAllegatiAggiornamentoMassivoVisibilita(Set<FormFile> allegati, String tipo, long idCaricamento) throws SQLException;
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamentoAggVisNoBlob(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public AllegatoCaricamentoMassivoEntity getAllegatoAggVis(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public List<ExportProfiloBean> getProfiliDisponibiliVisibAmmCentrApp(ProfiloFinder finder) throws SQLException;
	public List<ExportRegioneBean> getElencoRegioni() throws SQLException;
	public List<ExportProvinceBean> getElencoProvincie() throws SQLException;
	public List<ExportTipologiaUfficioBean> getElencoTipologieUfficio() throws SQLException;
	
	// Aggiornamento Puntuale Visibilite'
	public List<String> getElencoFigliAreaEca(String codice) throws SQLException;
	public List <ProfiloUtenteInVisibilitaBean> getListDateScadenzaVisibilita(ProfiloFinder finder) throws SQLException;
	public void inserisciProfiloUtenteInVisibilita(ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilita) throws SQLException ;
	public void deleteProfiloUtenteInVisibilita(ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilita) throws SQLException;
	public void inserisciProfiliVisibilitaTracciamento(ProfiloVisibilitaTracciamentoBean profVisTracBean) throws SQLException ;
	public void updateDataFineValInVisibilita(ProfiloUtenteInVisibilitaBean finder)throws SQLException ;
	public void updateDataFineValCdr(ProfiloUtenteInVisibilitaBean finder) throws SQLException;
	public void setDataFineValUtenteVis(ProfiloUtenteInVisibilitaBean finder) throws SQLException;
	public String get_codiceUfficio_uffStrut(ProfiloFinder finder) throws SQLException;

	
	//	Abilitazioni Degli operatori
	public List<ProfiloRichiestaBean> getElencoRGWeb(ProfiloFinder input) throws SQLException;
	public List<ProfiloRichiestaBean> getElencoProfiliWeb(ProfiloFinder input) throws SQLException;
	public List<AmbitoBean> getElencoAmbito(ProfiloFinder input) throws SQLException;
	
	public String getDescrizioneStrutturaFromCodiceStruttura(BatchAbilitazioniRichiestaFinder finder)throws SQLException;
	public List<StrutturaUfficioCDRBean> getStruttureAL(String cf)throws SQLException;
	
	public List<StrutturaUfficioCDRBean> getElencoStruttureDaIILiv(String codiceIILiv)throws SQLException;
	public List<StrutturaUfficioCDRBean> getElencoCdRDaStrutt(CDRBaseFinder finderCdr)throws SQLException;
	public List<ExportProfiloBean> getProfiliExport(BatchAbilitazioniRichiestaFinder finder)throws SQLException;
	// 4.5.3
	public List<ExportProfiloBean> getProfiliExportOp(String cf)throws SQLException;
	//<--
	public ElencoBean getElencoOperatoriInVisibilitaAggornamento(OperatoreFinder finder) throws SQLException;
	
	public  List<BatchInfoBean>  selectBatchInfo (BatchInfoBean input) throws SQLException;
	public void updateBatchInfo (BatchInfoBean input) throws SQLException;
	
	public  void updateElaborazioneRichiestaBatch(RichiestaBatchDaElaborareBean input)   throws SQLException;
	public  void updateElaborazioneRichiesteBatch( Integer input)   throws SQLException;
 

	public String getEmailAssistenza()throws SQLException;
	public String isElaborabileSIGA3(String codiceCdR)throws SQLException;
	
	
	public ArrayList <MappaturaProfiloUfficioBean> getProfiliNonPrevistiAG_Query1( ) throws SQLException;
	public Integer getProfiliNonPrevistiAG_Query2(HashMap<String, String> input)throws SQLException;
	public Integer getProfiliNonPrevistiAG_Query3(HashMap<String, String> input)throws SQLException;
	
	public StrutturaUfficioCDRExtBean verificaCdRPadreUfficio(String cdr)throws SQLException;
	public int countAltreDeleghe(DelegaFinder finder)throws SQLException;
	
	public List<ElementoCatalogoBean> getElencoCatalogoFromProfilo(String codiceProfilo)throws SQLException;
	public AbilitazioneBean getProfiloInfo(String codiceProfilo)throws SQLException;
	public List<AbilitazioneBean> getProfiloInfo2(String codiceProfilo)throws SQLException;
	public List<ApplicazioneUtenteBean> getElencoRGApplicazioneUtente(CatalogoAbilitazioniFinder finder)throws SQLException;
	public List<AbilitazioneBean> getElencoAbilitazioniCatalogo(CatalogoAbilitazioniFinder finder)throws SQLException;
	public List<RoleGroupBean> getRoleGroup()throws SQLException;
	public List<AbilitazioneBean> getElencoAbilitazioniCatalogo2(CatalogoAbilitazioniFinder finder)throws SQLException;
	public List<AbilitazioneBean> getElencoAbilitazioniCatalogo3(CatalogoAbilitazioniFinder finder) throws SQLException;
	public AbilitazioneBean getAbilitazione(CatalogoAbilitazioniFinder finder)throws SQLException;
	public AbilitazioneBean getAbilitazione2(CatalogoAbilitazioniFinder finder)throws SQLException;
	public List<FunzioneBean> getElencoFunzioneBean(CatalogoAbilitazioniFinder finder) throws SQLException;
	public List<ApplicazioneUtenteBean> getElencoAppUtenteFromRoleGroup(CatalogoAbilitazioniFinder finder) throws SQLException;
	public List<RaggruppamentoFunzionaleBean> getElencoRaggrFunzFromApp(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int updateAppUtenteAbilitazione(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int updateNotaAbilitazione(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int updateProfiloCatalogo(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int cancellaFunzione(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int modificaFunzione(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int inserisciFunzione(CatalogoAbilitazioniFinder finder) throws SQLException;
	public List<AbilitazioneBean> getElencoAbilitazioniCatalogoExport(CatalogoAbilitazioniFinder finder)throws SQLException;
	public List<ApplicazioneUtenteBean> getElencoApplicazioniCatalogo(CatalogoAbilitazioniFinder finder) throws SQLException;
	public List<AbilitazioneBean> getElencoFunzioniCatalogo(CatalogoAbilitazioniFinder finder)throws SQLException;
	public List<AbilitazioneBean> getElencoFunzioniCatalogoExport(CatalogoAbilitazioniFinder finder)throws SQLException;
	public int countRoleGroupNoFunzioni(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int countRoleGroupModificati(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int cancellaRaggruppamentoFunzionaleProfilo(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int updateNotaFlagRoleGroup(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int updateFlagsNotaAllegatoProfilo(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int inserisciRaggruppamentoFunzionaleProfilo(CatalogoAbilitazioniFinder finder) throws SQLException;
	public int countAbilitazioneAppUtente(CatalogoAbilitazioniFinder finder) throws SQLException;
	public List<ApplicazioneCatalogoBean> getElencoApplicazioniCatalogoAllExport()throws SQLException;
	
	public List<ProfiloBean> getElencoAbilitazioniAC(ProfiloFinder finder)throws SQLException;
	public List<ProfiloBean> getElencoAbilitazioniVisAC(ProfiloFinder finder)throws SQLException;
	public List<ProfiloIncompatibilitaBean> getElencoProfiliIncompatibili(ProfiliIncompatibiliFinder finder)throws SQLException;
	// 1.3 2023 -->
	public Integer getProfiloVisibilitaStruttura(String codiceCdr) throws SQLException;
	// 1.3 2023 <--
	// 4.5.9 II -->
	public List<ProfiloRichiestaBean> getElencoProfili() throws SQLException;
	public List<ProfiloIncompatibilitaBean> getElencoProfiliIncompatibiliGest(ProfiliIncompatibiliFinder finder)throws SQLException;
	public int numProfiliIncompatibilita(ProfiliIncompatibiliFinder finder)throws SQLException;
	public int updateProfiloIncompatibilita(ProfiliIncompatibiliFinder finder)throws SQLException;
	public int insertProfiloIncompatibilita(ProfiliIncompatibiliFinder finder)throws SQLException;
	// 4.5.9 II <--
	public List<Integer> getTipologiaUtenteEsterno(String cf)throws SQLException;
	public List<ProfiloBean> getElencoAbilitazioniRichiedente(ProfiloFinder finder)throws SQLException;
	public List<ProfiloBean> getElencoAbilitazioniVisRichiedente(ProfiloFinder finder)throws SQLException;
	public String getIdIter(ProfiloBean finder) throws SQLException;
	public int verificaIter(CheckAutorizzatoriSingoloProfiloFinder finder) throws SQLException;
	public AutorizzatoreBean getAutorizzatoreIlivelloVerticeStrutt(CheckAutorizzatoriSingoloProfiloFinder finder)throws SQLException;
	public AutorizzatoreBean getAutorizzatoreIlivello(CheckAutorizzatoriSingoloProfiloFinder finder)throws SQLException;
	public AutorizzatoreBean getAutorizzatoreIIlivello(CheckAutorizzatoriSingoloProfiloFinder finder)throws SQLException;
	public GestoreOperatoriBean getGestoreCf(GestoriFinder gestoriFinder)throws SQLException;
	public boolean isRichiedenteVerticeStruttura(HashMap<String, String> queryMap) throws SQLException;
	public boolean isResponsabileHrRichiesta(HashMap<String, String> queryMap) throws SQLException;
	public List<CDRBean> getGerarchiaCdR(String codiceCdR)throws SQLException;
	public String getRoleGroupDescConcat(String profilo)throws SQLException;
	public List<RoleGroupBean> getRoleGroupByCodRg(List<String> codiceRg) throws SQLException;
	public List<AbilitazioneBean> getProfiliByCodProfilo(List<String> codiceProfilo) throws SQLException;
	public int countRichiesteInCorso(String cfOperatore)throws SQLException;
	public List<TipologiaOperatoriEsterni> getElencoTipologieOpEsterno(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
	public List<ProfiloBean> getElencoAilitazioniTipologia(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
	public List<ProfiloBean> getElencoAbilitazioniAgenzia(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
	public List<ProfiloBean> getElencoAilitazioniTipologiaAgenzia(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
//	public int aggiungiAbilitazioneTipologia(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
//	public int rimuoviAbilitazioneTipologia(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
	public boolean inserisciTipologia(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
	public boolean modificaTipologia(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
	public boolean eliminaTipologia(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
//	public int modificaDatiTipologia(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
	public int getIdTipologia()throws SQLException;
	public int eliminazioneLogicaTipologia(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
	public int countAbilitazioniAttive(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
	public int countOperatoriEsterni(TipologiaOperatoreEsternoFinder tipologia)throws SQLException;
	public String getUfficioPrimarioCATTest(String cfOperatore)throws SQLException;
	List<RichiestaBean> getRichiesteDaArchiviare() throws SQLException;
	List<RichiestaBean> getRichiesteDaAutorizzare() throws SQLException;
	List<RichiestaBean> getRichiesteDaEseguire() throws SQLException;
	List<RichiestaBean> getRichiesteDaPrendereInCarico() throws SQLException;
	List<RichiestaBean> getSollecitoPresaVisione() throws SQLException;
	String getIndirizzoSegnalazioni() throws SQLException;
	List<SegnalazioneIncoerenzeBean> getIncongruenzeAssegnazioni() throws SQLException;
	List<String> getProfiliAttiviUtenteWF(HashMap <String,String> input ) throws SQLException;	
	public List<ProfiloRichiestaBean> getVisibilitaCambioUfficio(ProfAttUte_x_Uff_di_provenienzFinder finder)throws SQLException;
	public IterBean getIterInfo(String id) throws SQLException;	
	public List<IterBean> getElencoIter(IterAbilitazioniFinder finder) throws SQLException;
	public List<ProfiloBean> getAbilitazioniIter(IterAbilitazioniFinder finder) throws SQLException;
	public List<StrutturaIterBean> getRuoliAutorizzativi(IterAbilitazioniFinder finder) throws SQLException;
	public List<ProfiloBean> getElencoAbilitazioniNonAssociate(IterAbilitazioniFinder finder)throws SQLException;
	public List<LabelValueBean> getTipoUfficioPerAbilitazione(ProfiloBean profilo)throws SQLException;
	public int inserisciIter(IterBean iter)throws SQLException;
	public int inserisciStrutturaIter(IterAbilitazioniFinder finder)throws SQLException;
	public int getIdAudit()throws SQLException;
	public int inserisciAuditSenzaId(AuditFinder finder)throws SQLException;
	public int getIdIter()throws SQLException;
	public boolean inserisciNuovoIter(IterAbilitazioniFinder finder)throws SQLException;
	public List<ProfiloBean> getAbilitazioniIterAggiornamento(IterAbilitazioniFinder finder)throws SQLException;
	public boolean aggiornaIter(IterAbilitazioniFinder finder)throws SQLException;
	public List<CDRBean> getUfficiCATProfiliAssegnati(CDRFinder finder) throws SQLException;
	public List<CDRBean> getUfficiCATTotali(CDRFinder finder) throws SQLException;
	public List<ProfiloBean> getListaDescrizioniLineaCAT(ProfiloFinder finder) throws SQLException;
	public StrutturaIterBean getIterStandard(CheckAutorizzatoriSingoloProfiloFinder finder) throws SQLException;
	public CDRBean getInformazioniCdR(ProfiloFinder finder) throws SQLException;
	public List<LabelValueBean> getTipiUfficioSovraordinati(IterAbilitazioniFinder finder)throws SQLException;
	public int eliminaStrutturaIter(IterAbilitazioniFinder finder)throws SQLException;
	public boolean aggiornaStrutturaIter(IterAbilitazioniFinder finder)throws SQLException;
	public int isRichiesteInCorsoIter(IterAbilitazioniFinder finder)throws SQLException;
	public boolean eliminaIter(IterAbilitazioniFinder finder)throws SQLException;
	public GestoreOperatoriBean getGestoreAbilitazioni(GestoriFinder gestoriFinder)throws SQLException;

	public List<LabelValueBean> getElencoAttivita(OperatoreFinder finder)throws SQLException;
	public List<AttivitaBean> getElencoAttivitaAudit(AttivitaAmministratoriFinder finder)throws SQLException;
	public List<AttivitaBean> getElencoAttivitaOperatori(AttivitaAmministratoriFinder finder)throws SQLException;
	public List<OperatoreBean> getOperatoriDiCompetenza(OperatoreFinder finder)throws SQLException;
	public List<ProfiloVisibilitaTracciamentoBean> getAggiornamentoVisibilita(Integer idAudit)throws SQLException;
	public List<DelegaBean> getDelegheAmministratore(Integer idAudit)throws SQLException;
	public AssociazioniOraBean getAssPuntuale(AssociazOperRichiAutorFinder finder)throws SQLException;
	public RichiedenteCDRBean getRuoliCdR(RichiedenteCDRFinder finder)throws SQLException;
	public List<GestoreOperatoreBean> getGestoriAggiornati(GestoreOperatoriFinder finder)throws SQLException;
	public Integer selectIdRichiestaFromIdAudit(Integer id)throws SQLException;
	public IterBean getIterByIdAudit(IterAbilitazioniFinder finder)throws SQLException;
	public List<ProfiloBean> getProfiliIter(IterAbilitazioniFinder finder)throws SQLException;
	public void updateIdAuditRichiesta(RichiestaBean finder)throws SQLException;
	public void updateIdAuditOpEsterno(OperatoreBean finder)throws SQLException;
	public void updateIdAuditOpEsternoTipologia(OperatoreEsternoTipoUtenteBean finder)throws SQLException;
	
	public List<OperatoreEsternoTipoUtenteBean> getDettaglioOperatoreEsterno(OperatoreEsternoTipoUtenteBean finder)throws SQLException;
//	public List<FileAbilitazioneBean> getElencoRecordCaricamentoMassivo(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public Integer isFileDisponibile(InterrogazioneCaricamentiFinder finder)throws SQLException;
	

	public StrutturaIterBean getRuoliAutorizzativiExport(IterAbilitazioniFinder finder) throws SQLException;
	public String getTipoUfficioMappatura(ExportProfiloMappaturaBean bean) throws SQLException;
	public CostantiSiga getCostanteSiga(CostantiSiga finder)throws SQLException;
	public ProfiloBean getIterAssociato(MappaturaProfiloUfficioBean bean) throws SQLException;

	public int countAbilitazioniNonACApplicativo(IterAbilitazioniFinder finder) throws SQLException;
	public int isDescrizioneTipoOEPresente(String descrizione) throws SQLException;
	public int isDescrizioneIterPresente(String descrizione) throws SQLException;
	public List<ProfiloBean> getAbilitazioniFromRg(String codiceRg)throws SQLException;
	public  UtenteBean  getRevocheUtente(String codFisc) throws SQLException;
	public List<String> getAdminCauByGroupName(HashMap<String, String> input)throws SQLException ;
	public String getAdminCAU(String input)throws SQLException;
	public Integer verificaAmministratore_1(String input)throws SQLException;
	public Integer verificaAmministratore_2(String input)throws SQLException;
	public List<String> verificaTitolariAndDelegati()throws SQLException;
	public Integer countRichestAsRichiedenteToElab(HashMap <String,String> input)throws SQLException;
	public RichiedenteCDRBean getCfResponsabile (CF_RichiedenteFinder input) throws SQLException;
	public void setDataElaborazioneBatch(CostantiSigaPT costante) throws SQLException;

	public List<ProfiloCompetenzaBean> listaStoricoProfiliOperatore(ProfiloFinder finder)throws SQLException;
	public boolean inserisciRevoca(RevocaBean revoca) throws SQLException;
	public boolean aggiornaRevoca(RevocaBean revoca) throws SQLException;
	public ElencoBean getOperatoriStoricoAbilitazioni(OperatoreFinder finder ) throws SQLException;
	public String getResponsabileCdR(RichiedenteCDRFinder finder)throws SQLException;
	public void updateOwnerEjbTimer(String input)throws SQLException;
	public List<RichiestaBean> getElencoGestoriRichiestaAmbito5 ( RichiestaFinder finder ) throws SQLException;
	
	public Integer countProfiliAsGestoreDiRete(Integer idRichiesta)throws SQLException;
	public int countEsisteRevocaPrecedente(Integer idRichiesta)throws SQLException;	
		
	/** Caricamenti Massivi Richieste di Visibilite'**/
	public List<CDRBean> getListaCdrAgenzia() throws SQLException;
	public long getSequenceFromRichiesteCaricamentoMassivoVisibilita() throws SQLException;
	public void inserisciRichiestaCaricamentoMassivoVisibilita(CaricamentoMassivoEntity cmEntity) throws SQLException;
	public void inserisciAllegatiCaricamentoMassivoVisibilita(Set<FormFile> allegati, String tipo, long idCaricamento) throws SQLException;
	public void inserisciAllegatiCaricamentoMassivoMappatura(Set<FormFile> allegati, String tipo, long idCaricamento) throws SQLException;
	public List<CaricamentoMassivoEntity> getElencoCaricamentiMassiviVisibilitaUtenti(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public CaricamentoMassivoEntity getDettaglioCaricamentoRichVis(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public void annullaRichiestaCaricamentoMassivoRichVis(CaricamentoMassivoEntity cmEntity) throws SQLException;
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamentoRichVis(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamentoRichVisNoBlob(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public AllegatoCaricamentoMassivoEntity getAllegatoRichVis(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public void avvioImmediatoControlliRichVis(String idCaricamento) throws SQLException;
	public void deleteCmVisibilitaFile(String idCaricamento)throws SQLException;
	public int getCountCodiciFiscaliAbilitati(UtenteBean utente)throws SQLException;
	public List<BatchRuoliDegliOperatoriFinder> getRichiesteRuoliDegliOperatori(BatchRuoliDegliOperatoriFinder input) throws SQLException;
	
	//Configurazione Caricamenti Massivi
	public List<EventiSistemaBean> getElencoCaricamentiMassivi() throws SQLException;
	public void updateCaricamentiMassivi(ConfigCaricamentiMassiviFinder finder) throws SQLException;

	
	/** Caricamenti Massivi Mappature abilitazioni uffici**/
	public long getSequenceFromRichiesteCaricamentoMassivoMappature() throws SQLException;
	public void inserisciRichiestaCaricamentoMassivoMappature(CaricamentoMassivoEntity cmEntity) throws SQLException;
	public List<CaricamentoMassivoEntity> getElencoCaricamentiMassiviMappatureUtenti(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public CaricamentoMassivoEntity getDettaglioCaricamentoMappature(InterrogazioneCaricamentiFinder finder)throws SQLException;
	public void annullaRichiestaCaricamentoMassivoMappature(CaricamentoMassivoEntity cmEntity) throws SQLException;
	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamentoMappatureNoBlob (InterrogazioneCaricamentiFinder finder)throws SQLException;
	public AllegatoCaricamentoMassivoEntity getAllegatoCaricamentoMappature (InterrogazioneCaricamentiFinder finder)throws SQLException;
	

	public void avvioImmediatoControlliMappature(String idCaricamento) throws SQLException;
	
	public void deleteMappaturaTipoUffCdr(MappaturaProfiloUfficioBean mappaturaBean)throws SQLException;
	public void deleteMappaturaCdrUff(MappaturaProfiloUfficioBean mappaturaBean)throws SQLException;

	
	// verifica visibilite'
	
	public int countNVerificaVisibilita(UtenteInVisibilita utenteInVisibilita) throws SQLException;
	public int countNotNVerificaVisibilita(UtenteInVisibilita utenteInVisibilita) throws SQLException;
	
	// cambio ufficio cat ac
	public List<StrutturaUfficioCDRBean> selectListaUfficiDaStruttura(UffDestinazFinder uffDestinazFinder) throws SQLException;
	public boolean inserisciProfiliCambioUfficioCATAC(List<ProfiloRichiestaBean> elencoProfiliAttivi,
			List<ProfiloRichiestaBean> elencoProfiliAssegnati,
			RichDlgSessBean richDlgSessBean, GetAutorizzatore_I_LivBean autorizzatore_I_LivBean,
			OperatoreBean operatoreBean, UtenteBean utente,
			String ufficioCATProvenienza, String codiceUfficioDestinazione, List<RichiestaBean> listRichiesta) throws CheckException, SQLException;
	boolean inserisciRichiesteCambioUfficioCATAC(BaseFinder finder, UtenteBean utente) throws SQLException;	

	// get info ufficio operatore
	OperatoreBean getInfoUfficioOperatore(OperatoreFinder operatoreFinder) throws SQLException;	
	public List<RichiestaBean> getInfoRichiesteInCorsoAC(RegistroRichiesteFinder finder) throws SQLException;
	public List<ProfiloRichiestaBean> getProfiliRichiestaByID(RichiestaFinder finder) throws SQLException;
	
	
	//Cruscotto Cartelle condivise NAS
	public int getCartelleCondiviseCount(String codFiscale) throws SQLException;
	public List<CruscottoCartelleNasBean> getCartelleCondiviseNas(String codFiscale) throws SQLException;
	
	//Amministratori Applicativi
	public List<OperatoreBean> getAmministratoriCentraliByRoleGroup(CatalogoAbilitazioniFinder finder)throws SQLException;
	public List<OperatoreBean> getAmministratoriCentraliByCf(CatalogoAbilitazioniFinder finder) throws SQLException;
	public List<OperatoreBean> getAmministratoriCentraliByCfRoleGroup(CatalogoAbilitazioniFinder finder) throws SQLException;
	public List<ProfiliAttiviUtente> getProfiliAttiviUtente(OperatoreFinder finder) throws SQLException;
	public boolean inserisciAbilitazioniAmmCentraleApplicativo(CatalogoAbilitazioniFinder finder) throws SQLException;
	public boolean aggiornaAbilitazioniAmmCentraleApplicativo(CatalogoAbilitazioniFinder finder) throws SQLException;
	public boolean cancellaAbilitazioniAmmCentraleApplicativo(CatalogoAbilitazioniFinder finder) throws SQLException;
	
	//Batch Cruscotto CSV
	public boolean batchCancellaCartelleCondiviseNas(String server) throws SQLException;
	public boolean batchInserisciCartelleCondiviseNas(List<RecordCsvCruscottoBean> records) throws SQLException;
	
	//Gestione catalogo Abilitazione
	public int updateNomeApplicazione(CatalogoAbilitazioniFinder finder) throws SQLException ;
	public boolean inserisciAbilitazioneFunzProf(CatalogoAbilitazioniFinder finder) throws SQLException;
	public boolean deleteApplicazioneFunzProf(CatalogoAbilitazioniFinder finder) throws SQLException;
	public boolean deleteApplicazioneUtente(CatalogoAbilitazioniFinder finder) throws SQLException;
    public boolean deleteProfiliApplicazioneEstesa(CatalogoAbilitazioniFinder finder) throws SQLException;
	public boolean inserisciAbilitazioneFunzRg(CatalogoAbilitazioniFinder finder) throws SQLException;
	public boolean deleteApplicazioneFunzRg(CatalogoAbilitazioniFinder finder) throws SQLException;
	public boolean insertApplicazioneEstesa(CatalogoAbilitazioniFinder finder) throws SQLException;
	public List<ApplicazioneUtenteBean> getApplicazioneByCodice(CatalogoAbilitazioniFinder finder) throws SQLException;
	public List<ApplicazioneUtenteBean> getCoppiaApplicazioneRoleGroup(CatalogoAbilitazioniFinder finder) throws SQLException;
	public List<String> getEstesa(CatalogoAbilitazioniFinder finder) throws SQLException;
	public List<String> getEliminabile(CatalogoAbilitazioniFinder finder) throws SQLException;
	
	
	// Comunicazioni News 
	public List<ComunicazioneEntity> getElencoComunicazioni(ComunicazioniFinder finder) throws SQLException;
	public List<ComunicazioneEntity> getElencoComunicazioniPerOperatori(ComunicazioniFinder finder) throws SQLException;
	public List<String> selectCdrValidiPerVisualizzazione(String cdrUser)throws SQLException;
	public List<ComunicazioneTipoStrutturaBean> getElencoDirezioniProvinciali()throws SQLException;
	public List<ComunicazioneTipoStrutturaBean> getElencoDirezioniRegionali()throws SQLException;
	public List<ComunicazioneTipoStrutturaBean> getElencoDcDivisioni() throws SQLException;
	public UfficioUnicoBean getUfficioUnico(String codiceUfficio) throws SQLException;
	public List<ComunicazioneTipoStrutturaBean> getElencoCop() throws SQLException;
	public List<ComunicazioneTipoStrutturaBean> getElencoCam() throws SQLException;
	public int inserisciMessaggio(ComunicazioniFinder finder) throws SQLException;
	public int eliminaMessaggio(ComunicazioniFinder finder) throws SQLException;
	public void inserisciRuoliDestinatari(ComunicazioniFinder finder) throws SQLException;
	public List<AllegatoCaricamentoMassivoEntity> getAllegatoComunicazione(ComunicazioniFinder finder)throws SQLException;
	public List<ComunicazioneTipoStrutturaBean> getElencoGerarchiaDC(String cdr) throws SQLException;
	public List<ComunicazioneTipoStrutturaBean> getElencoGerarchiaDR(String cdr) throws SQLException;
	public List<ComunicazioneTipoStrutturaBean> getElencoGerarchiaDP(String cdr) throws SQLException;
	public void inserisciTipologiaStruttura(ComunicazioniFinder finder) throws SQLException;
	public ComunicazioneEntity getComunicazioneById(String id)throws SQLException;
	public void inserisciTipoSelezionato(ComunicazioniFinder finder) throws SQLException;
	public List<ComunicazioneTipoStrutturaBean> getTipoSelezionatoById(String id) throws SQLException;
	public List<ComunicazioneEntity> getEmailRichiedenti(ComunicazioniFinder finder) throws SQLException;
	public List<ComunicazioneEntity> getEmailAutorizzatori(	ComunicazioniFinder finder)throws SQLException;
	public List<ComunicazioneEntity> getEmailAmministratori(ComunicazioniFinder finder)throws SQLException;
	
	//I Miei Ruoli e Ruoli degli Operatori
	public List<RuoloRichiedenteBean> getRuoliRichiedente(String cfRichiedente)throws SQLException;
	public List<RuoloAutorizzatore_II_LivelloBean> getRuoliAutorizzatoreIILiv(String cfAutorizzatore)throws SQLException;
	public List<RuoloAmministratoreAuditorBean> getRuoliAmministratoreAuditor(String cfAmministratoreAuditor)throws SQLException;
	public List<RuoloDelegheBean> getRuoliDeleghe(String cfDelegatoDelegante)throws SQLException;
	public int inserisciRichiestaRuoliOperatoriPerCdr(BatchRuoliDegliOperatoriFinder input) throws SQLException;
	public int getIdBatchRuoliOperatoriPerCdrRichiesta() throws SQLException;
	public List<String> getFiltriCdR(BatchRuoliDegliOperatoriFinder input) throws SQLException;
	public List<String> getFiltriRG(BatchRuoliDegliOperatoriFinder input) throws SQLException;
	public List<String> getFiltriProfili(BatchRuoliDegliOperatoriFinder input) throws SQLException;
	public int elaborazioneFallitaBatch(BatchRuoliDegliOperatoriFinder input) throws SQLException;
	public int insertFileRichiestaRuoliBatch(BatchRuoliDegliOperatoriFinder input) throws SQLException;
	public int elaboraRichiestaRuoliBatch(BatchRuoliDegliOperatoriFinder input) throws SQLException;
	public void setFlagElaborazioneRuoliOperatori(String flag) throws SQLException;
	public List<ExportRuoliDiUnCdrBean> getRuoliExport(BatchRuoliDegliOperatoriFinder finder) throws SQLException;
	public AllegatoBean getFileBatchRuoliOperatori(BatchRuoliDegliOperatoriFinder finder) throws SQLException;
	public int cancellaRichestaRuoliOperatori(BatchRuoliDegliOperatoriFinder input)throws SQLException;
	
	// batch monitoraggio abilitazioni 
	public ConfigBatchMonitAbilBean getConfReminderMonit() throws SQLException;
	public Integer insertAttivitaChecked() throws SQLException;
	public Integer aggiungiDataInvioReport(Date dt) throws SQLException;
	public List<Utenti> getRichiedentiReminderMonit() throws SQLException;
	public AttivitaCheckedBean getAttivitaChecked(String cf) throws SQLException;
	public List<AttivitaCheckedReportBean> getAttivitaCheckedReport() throws SQLException;
	public List<AttivitaCheckedReportBean> getAttivitaCheckedReportResp(AttivitaCheckedReportBean param)  throws SQLException;
	public void updateAttivitaChecked(AttivitaCheckedBean ac) throws SQLException;
	public List<AttivitaCheckedReportBean> getAttivitaCheckedExportReport(AttivitaCheckedReportBean par) throws SQLException;
	public List<Date> getPeriodiRifAttCheck() throws SQLException;
	public List<String> getElencoUtentiIns() throws SQLException;
	public List<String> getElencoUtentiDel() throws SQLException;
	void cleanVariazioni() throws SQLException;
	void insertUtentiVariazioniCfRuoliOTP() throws SQLException;
	void insertUtentiGruppoOTP(String codiceFiscale, String operazione) throws SQLException;
	void setValoreCostanteBatchAggiornamentoRuoloOTP(String val) throws SQLException;
	void setValoreCostanteBatchMailMonitoraggioAbilitazioni(String val) throws SQLException;
	

}

