package it.finanze.siga.workflow.core;

import it.finanze.siga.bean.ProfiloRichiestaBean;
import it.finanze.siga.bean.RichiestaBean;
import it.finanze.siga.ejb.SigaDao;
import it.finanze.siga.finder.RichiestaFinder;
import it.finanze.siga.service.SIGAServiceProxy;
import it.finanze.siga.util.Constants;
import it.finanze.siga.util.CostantiSessione;
import it.finanze.siga.util.Ruolo;
import it.finanze.siga.util.SigaCache;
import it.finanze.siga.util.StatoRichiesta;
import it.finanze.siga.util.bean.UtenteBean;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;

public class WorkFlowCore {	
	@Inject
	SigaDao service;
	private UtenteBean utente;
	private boolean avvia = false;
	
	public WorkFlowCore(UtenteBean utente) {
		this.utente = utente;
	}
	
	public WorkFlowCore(HttpServletRequest request) {
		utente = (UtenteBean) request.getSession().getAttribute(CostantiSessione.UTENTE_BEAN);
	}
	
	public static void sblocca(int idRichiesta, String agenziaOperatore, String operatore) throws SQLException, WorkFlowCoreException{	
        WorkFlowJMSClient WFJMSClient = new WorkFlowJMSClient();
        WFJMSClient.send(idRichiesta, agenziaOperatore, operatore);
	}
	
	public RichiestaBean avvia(int idRichiesta) throws SQLException, WorkFlowCoreException{
		avvia=true;
		RichiestaBean outRichiesta = processa(idRichiesta, true, "");
		avvia=false;
		return outRichiesta;
	}
	
	public RichiestaBean processa(int idRichiesta) throws SQLException, WorkFlowCoreException{
		return processa(idRichiesta, true, "");
	}
	
	public RichiestaBean processa(int idRichiesta, boolean operazione) throws SQLException, WorkFlowCoreException{
		return processa(idRichiesta, operazione, "");
	}
	
	public RichiestaBean processa(int idRichiesta, boolean operazione, String note) throws SQLException, WorkFlowCoreException{
		RichiestaFinder finder = new RichiestaFinder();
	 
		finder.setCodFisc(utente.getCodFiscaleUtente());
		finder.setIdRichiesta(idRichiesta);
		
		//codice rimosso in seguito alla eliminazione della presa in carico 
		/*if (!service.presaInCarico(finder))
			throw new WorkFlowCoreException(WorkFlowCoreException.ERR_PRESA_IN_CARICO);*/
		
		finder = new RichiestaFinder();
		finder.setIdRichiesta(idRichiesta);

		RichiestaBean outRichiesta = service.getRichiesta(finder);
		outRichiesta.setDataRichiesta(null);
		outRichiesta.setDataAutorizzazioneI(null);
		outRichiesta.setDataAutorizzazioneII(null);
		outRichiesta.setDataEsecuzione(null);
		outRichiesta.setDataChiusuraRichiesta(null);
		outRichiesta.setDataEsitoFinale(null);
		outRichiesta.setDataPresaVisione(null);
 		
		Date oggi = new Date(Calendar.getInstance().getTime().getTime());
		
		//Controllo se la richiesta e' stata presa in carico dall'utente in sessione
/*		if (outRichiesta.getCfPresaInCarico()==null)
			throw new WorkFlowCoreException(WorkFlowCoreException.ERR_NO_PRESA_IN_CARICO);
		if (!outRichiesta.getCfPresaInCarico().equals(utente.getCodFiscaleUtente()))
			throw new WorkFlowCoreException(WorkFlowCoreException.ERR_PRESA_IN_CARICO);		*/	
		boolean delegatoA1=false;
		if(utente.getListaDeleganti()!=null||utente.getListaDeleganti().size()>0)
		{
			if(outRichiesta.getCfAutorizzatoreI()!=null)
			{
				if(utente.getListaDeleganti().contains(outRichiesta.getCfAutorizzatoreI()))
				{
					delegatoA1=true;
				}
			}
		}
		
		if (outRichiesta.getStatoRichiesta().equals(StatoRichiesta.INSERITA)){
			if (( utente.getCustomProfile().get(Ruolo.AUTORIZZATORE_I)!=null &&  utente.getCustomProfile().get(Ruolo.AUTORIZZATORE_I).contains(outRichiesta.getCfAutorizzatoreI())) || 
					(  outRichiesta.getTipoRichiesta().equals(Constants.RICHIESTA_REVOCA_VISIBILITA))					
				||utente.isAmministratoreCentrale()) 
				// -- > 05/09/2023 aggiunta condizione isAmministratore Centrale 13/12/2023 verificato flag Richiesta Autorizzazione
			
			
			{	
				if(outRichiesta.getCfAutorizzatoreI()!=null)
				{									
					if(outRichiesta.getCfAutorizzatoreI().equals(utente.getCodFiscaleUtente())||delegatoA1)
					{
						outRichiesta.setCfAutorizzatoreIEffettivo(utente.getCodFiscaleUtente());
					}
				}
				if(operazione)
				{
				if ((outRichiesta.getCfAutorizzatoreI()!=null&&(outRichiesta.getCfAutorizzatoreI().equals(utente.getCodFiscaleUtente())))||delegatoA1){
					outRichiesta.setDataAutorizzazioneI(oggi);
					outRichiesta.setNoteAutorizzatoreI(note);
					outRichiesta.setStatoRichiesta(StatoRichiesta.AUTORIZZATA_I);
					if (	outRichiesta.getCfAutorizzatoreII()==null || 
							utente.isAutorizzatoreII() && utente.getCustomProfile().get(Ruolo.AUTORIZZATORE_II).contains(outRichiesta.getCfAutorizzatoreII())
						) {
						if (outRichiesta.getCfAutorizzatoreII()!=null){
							outRichiesta.setCfAutorizzatoreIIEffettivo(utente.getCodFiscaleUtente());						
							outRichiesta.setDataAutorizzazioneII(oggi);	
						}
						if (isAsincrona(outRichiesta)){
							outRichiesta.setDataEsitoFinale(oggi);	
							outRichiesta.setEsitoFinale(StatoRichiesta.AUTORIZZATA);
						}	
							autorizza(outRichiesta);					
						}
					}	
				}else{
					outRichiesta.setCfAutorizzatoreIEffettivo(utente.getCodFiscaleUtente());
					outRichiesta.setDataEsitoFinale(oggi);	
					outRichiesta.setEsitoFinale(StatoRichiesta.RIFIUTATA_AUT_I);				
					outRichiesta.setNoteAutorizzatoreI(note);				
					outRichiesta.setStatoRichiesta(StatoRichiesta.RIFIUTATA_AUT_I);
				}
			}else if (!avvia)
				throw new WorkFlowCoreException(WorkFlowCoreException.ERR_NO_AUTORIZZATO_AUT_I);
		}else if (outRichiesta.getStatoRichiesta().equals(StatoRichiesta.AUTORIZZATA_I)){
			if  (	(utente.isAutorizzatoreII() && utente.getCustomProfile().get(Ruolo.AUTORIZZATORE_II).contains(outRichiesta.getCfAutorizzatoreII()))){				
				outRichiesta.setCfAutorizzatoreIIEffettivo(utente.getCodFiscaleUtente());
				if (isAsincrona(outRichiesta)){
					outRichiesta.setDataEsitoFinale(oggi);
					outRichiesta.setEsitoFinale(operazione?StatoRichiesta.AUTORIZZATA:StatoRichiesta.RIFIUTATA_AUT_II);
				}				
				if (operazione){
					outRichiesta.setDataAutorizzazioneII(oggi);
					outRichiesta.setNoteAutorizzatoreII(note);
					autorizza(outRichiesta);					
				}
				else{
					outRichiesta.setDataEsitoFinale(oggi);
					outRichiesta.setEsitoFinale(StatoRichiesta.RIFIUTATA_AUT_II);
					outRichiesta.setNoteAutorizzatoreII(note);
					outRichiesta.setStatoRichiesta(StatoRichiesta.RIFIUTATA_AUT_II);
				}
			}else
				throw new WorkFlowCoreException(WorkFlowCoreException.ERR_NO_AUTORIZZATO_AUT_II);
		}else if (outRichiesta.getStatoRichiesta().equals(StatoRichiesta.AUTORIZZATA)){
			if (utente.isGestoreOp()){
				outRichiesta.setCfGestoreOperatoriEffettivo(utente.getCodFiscaleUtente());				
				outRichiesta.setDataEsecuzione(oggi);				
				outRichiesta.setDataEsitoFinale(oggi);
				outRichiesta.setNoteGestore(note);
				service.cancellaPresaInCaricoGestoreOperatori(outRichiesta.getIdRichiesta().toString());
				if (operazione){
					ProfiloRichiestaBean prb = new ProfiloRichiestaBean();
					prb.setCfUtenteLoggato(utente.getCodFiscaleUtente());
					prb.setCfUtente(outRichiesta.getCfUtente());
					prb.setIdRichiesta(outRichiesta.getIdRichiesta());					
					service.aggiornaProfiloRichiesta(prb);
					outRichiesta.setEsitoFinale(StatoRichiesta.ESEGUITA);
					outRichiesta.setStatoRichiesta(StatoRichiesta.ESEGUITA);
				}
				else{
					outRichiesta.setEsitoFinale(StatoRichiesta.ESEGUITA_NEG);
					outRichiesta.setStatoRichiesta(StatoRichiesta.ESEGUITA_NEG);
				}
			}else
				throw new WorkFlowCoreException(WorkFlowCoreException.ERR_NO_AUTORIZZATO_GESTORE);
		}else if (outRichiesta.getStatoRichiesta().equals(StatoRichiesta.ESEGUITA) && !outRichiesta.getTipoRichiesta().equalsIgnoreCase(Constants.RICHIESTA_VISIBILITA)){
			if  (utente.getCodFiscaleUtente().equals(outRichiesta.getCfUtente())){									
				outRichiesta.setDataPresaVisione(oggi);
				outRichiesta.setStatoRichiesta(StatoRichiesta.PRESA_VISIONE);
			}else
				throw new WorkFlowCoreException(WorkFlowCoreException.ERR_NO_OPERATORE);
			
		}else if (outRichiesta.getStatoRichiesta().equals(StatoRichiesta.PRESA_VISIONE)
			||outRichiesta.getStatoRichiesta().equals(StatoRichiesta.ESEGUITA_NEG)
				||outRichiesta.getStatoRichiesta().equals(StatoRichiesta.RIFIUTATA_AUT_I)
					||outRichiesta.getStatoRichiesta().equals(StatoRichiesta.RIFIUTATA_AUT_II)
						||(outRichiesta.getStatoRichiesta().equals(StatoRichiesta.ESEGUITA) && outRichiesta.getTipoRichiesta().equalsIgnoreCase(Constants.RICHIESTA_VISIBILITA))){			
			if  (	( utente.getCustomProfile().get(Ruolo.RICHIEDENTE)!=null && utente.getCustomProfile().get(Ruolo.RICHIEDENTE).contains(outRichiesta.getCfArchiviazione()))){				
				outRichiesta.setCfChiusuraRichiesta(utente.getCodFiscaleUtente());
				outRichiesta.setDataChiusuraRichiesta(oggi);
				outRichiesta.setCfArchiviazioneEffettivo(utente.getCodFiscaleUtente());
				outRichiesta.setStatoRichiesta(StatoRichiesta.ARCHIVIATA);
			}else
				throw new WorkFlowCoreException(WorkFlowCoreException.ERR_NO_RICHIEDENTE);
		}else if (outRichiesta.getStatoRichiesta().equals(StatoRichiesta.LAVORAZIONE)){
			return outRichiesta;
		}else
			throw new WorkFlowCoreException(WorkFlowCoreException.ERR_RICHIESTA);				

		outRichiesta.setCfPresaInCarico(null);
		
		service.aggiornaRichiesta(outRichiesta);
//		outRichiesta = service.getRichiesta(finder);
		if (	outRichiesta.getStatoRichiesta().equals(StatoRichiesta.RIFIUTATA_AUT_I)||
				outRichiesta.getStatoRichiesta().equals(StatoRichiesta.RIFIUTATA_AUT_II)||
				outRichiesta.getStatoRichiesta().equals(StatoRichiesta.ESEGUITA)||
				outRichiesta.getStatoRichiesta().equals(StatoRichiesta.ESEGUITA_NEG)){
			HashMap<String, String> args = new HashMap<String, String>();
			args.put("cfOperatore", utente.getCodFiscaleUtente());
			WorkFlowMail.send(outRichiesta, args);
		}
		else if (outRichiesta.getStatoRichiesta().equals(StatoRichiesta.LAVORAZIONE)){
	        WorkFlowJMSClient WFJMSClient = new WorkFlowJMSClient();
	        WFJMSClient.send(outRichiesta.getIdRichiesta(), utente.getCodiceAgenzia(), utente.getCodFiscaleUtente());         
		}
		return outRichiesta;
	}

	private void autorizza(RichiestaBean outRichiesta) throws WorkFlowCoreException{
		boolean isAsincrona = isAsincrona(outRichiesta);
		if (isAsincrona)	
			outRichiesta.setStatoRichiesta(StatoRichiesta.LAVORAZIONE);
		else
				outRichiesta.setStatoRichiesta(StatoRichiesta.AUTORIZZATA);
	}
	
	private boolean isAsincrona(RichiestaBean outRichiesta) throws WorkFlowCoreException{
		if ((outRichiesta.getTipoRichiesta().equalsIgnoreCase(Constants.RICHIESTA_PROFILAZIONE) && outRichiesta.isSincronizzata())
				||outRichiesta.getTipoRichiesta().equalsIgnoreCase(Constants.RICHIESTA_CAMBIO_UFFICIO)
				||outRichiesta.getTipoRichiesta().equalsIgnoreCase(Constants.RICHIESTA_VISIBILITA)
				||outRichiesta.getTipoRichiesta().equalsIgnoreCase(Constants.RICHIESTA_REVOCA_VISIBILITA))return true;
		else if (outRichiesta.getTipoRichiesta().equalsIgnoreCase("P") && !outRichiesta.isSincronizzata())
				return false;
		else
			throw new WorkFlowCoreException(WorkFlowCoreException.ERR_TIPO_RICHIESTA);
	}
 
}
