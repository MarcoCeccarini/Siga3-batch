package it.finanze.siga.rest;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.finanze.siga.ejb.IBatchAbilitazioniDisponibiliNeiCdR;
import it.finanze.siga.ejb.IBatchAbilitazioniOperatoriPerCdR;
import it.finanze.siga.ejb.IBatchAbilitazioniOperatoriPerCdRGeneraExport;
import it.finanze.siga.ejb.IBatchAllineamentoAmministratori;
import it.finanze.siga.ejb.IBatchCaricamentoCsvCruscotto;
import it.finanze.siga.ejb.IBatchCessazioneMobilitaNew;
import it.finanze.siga.ejb.IBatchCoerenzaNewJpa;
import it.finanze.siga.ejb.IBatchExportRuoliDiUnCdrNew;
import it.finanze.siga.ejb.IBatchGeneraRichiesteNew;
import it.finanze.siga.ejb.IBatchGenerazioneRichiesteScadenzeNew;
import it.finanze.siga.ejb.IBatchHrEmailVariazioneDatiUtentiNew;
import it.finanze.siga.ejb.IBatchHrEmailVariazioniRuoliCdrNew;
import it.finanze.siga.ejb.IBatchHrResponsabileCdRNew;
import it.finanze.siga.ejb.IBatchHrVariazioneDatiUtenteNew;
import it.finanze.siga.ejb.IBatchImportResponsabilitaNew;
import it.finanze.siga.ejb.IBatchMailRichiesteNew;
import it.finanze.siga.ejb.IBatchMailScadenzeOperatoriEsterniNew;
import it.finanze.siga.ejb.IBatchOrganizzazioneNew;
import it.finanze.siga.ejb.IBatchProfiliAssegnatiNonPrevistiAGNew;
import it.finanze.siga.ejb.IBatchResponsabilitaNew;
import it.finanze.siga.ejb.IBatchRichiesteCaricamentiMassiviAggVisNew;
import it.finanze.siga.ejb.IBatchRichiesteCaricamentiMassiviMappatureNew;
import it.finanze.siga.ejb.IBatchRichiesteCaricamentiMassiviNew;
import it.finanze.siga.ejb.IBatchRichiesteCaricamentiMassiviVisibilitaNew;
import it.finanze.siga.ejb.IBatchRiempimentoCatalogoNew;
import it.finanze.siga.ejb.IBatchRiorganizzazioneDisabilitazioniCdrNew;
import it.finanze.siga.ejb.IBatchRiorganizzazioneReportDisabilitazioniNew;
import it.finanze.siga.ejb.IBatchRiorganizzazioniAggiornaCdrNew;
import it.finanze.siga.ejb.IBatchRuoliDegliOperatoriNew;
import it.finanze.siga.ejb.IBatchScadenzaDelegheImplNew;
import it.finanze.siga.ejb.IBatchScadenzeNew;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestScoped
@Path("/batch")
//@RolesAllowed("ALL_AUTHENTICATED_ROLE")
public class BatchResource implements IBatchResource {

	// OK, set ins,c,no @ BATCH_ABILITAZIONI_RICHIESTA, @costanti_siga Valore_costate=SI when ElaborazioneAbilitazioniCdr; RICHIESTA ID 270 ELABORATA 
	IBatchAbilitazioniDisponibiliNeiCdR batchAbilitazioniDisponibiliNeiCdR;
	
	//OK: INSERIMENTO EFFETTUATO CON SUCCESSO @ ABILITAZIONI_OPERATORI_PER_CDR
	IBatchAbilitazioniOperatoriPerCdR batchAbilitazioniOperatoriPerCdR; 
	
	// ok  SET INS,O x 269 @ BATCH_ABILITAZIONI_RICHIESTA: BATCH ABILITAZIONI DEGLI OPERATORI END
	IBatchAbilitazioniOperatoriPerCdRGeneraExport batchAbilitazioniOperatoriPerCdRGeneraExport;

	// TODO LOW PRIORITY to review named parameter
	IBatchAllineamentoAmministratori batchAllineamentoAmministratori; 
	
	 // TODO LOW PRIORITY
	IBatchCaricamentoCsvCruscotto batchCaricamentoCsvCruscotto;
	
	//ok but no data found inser incorenze
	IBatchCoerenzaNewJpa batchCoerenzaNewJpa;
	
	// ok ?and cdr veccho e enuovo in richieste_batch  delete cf from RIORG_CF_IN_ELAB
	IBatchCessazioneMobilitaNew batchCessazioneMobilitaNew;
	
	// KO problmi a query getElencoPerAlberoStrutture (deleghe_sql map select id="elenco_per _albero_strutture" ) runs on editor ma nn a runtime
	IBatchExportRuoliDiUnCdrNew batchExportRuoliDiUnCdrNew;
	
	 // TODO WORKFLOW, NO DATA FOUND
	IBatchGeneraRichiesteNew batchGeneraRichiesteNew;
	
	// NO DATA FOUND
	IBatchGenerazioneRichiesteScadenzeNew batchGenerazioneRichiesteScadenzeNew;
	
	// TODO uses Database.java and jdbc, and cauws
	//BatchGestoriDiRete.java
	
	
	 // no data found
	IBatchHrEmailVariazioneDatiUtentiNew batchHrEmailVariazioneDatiUtentiNew;
	
	 // no data found
	IBatchHrEmailVariazioniRuoliCdrNew batchHrEmailVariazioniRuoliCdrNew;
	
	// KO MISSIG LDAP calbback handler ibm websphere 
	IBatchHrResponsabileCdRNew batchHrResponsabileCdRNew;
	
	 // KO MISSIG LDAP calbback handler ibm websphere 
	@EJB
	IBatchHrVariazioneDatiUtenteNew batchHrVariazioneDatiUtenteNew;
	
	// KO MISSING ServiceOracleCAGetResponsabilita
	IBatchImportResponsabilitaNew batchImportResponsabilitaNew;
	
	// to test
	IBatchMailRichiesteNew batchMailRichiesteNew;
	
	 // to test
	IBatchMailScadenzeOperatoriEsterniNew batchMailScadenzeOperatoriEsterniNew;
	
	// KO MISSING LDAP to review dao methods
	IBatchOrganizzazioneNew batchOrganizzazioneNew;
	
	// ok 
	IBatchProfiliAssegnatiNonPrevistiAGNew batchProfiliAssegnatiNonPrevistiAGNew; 
	
	// review queries uses ws
	IBatchResponsabilitaNew batchResponsabilitaNew;
	
	// TO reTEST no data found
	IBatchRichiesteCaricamentiMassiviAggVisNew batchRichiesteCaricamentiMassiviAggVisNew;
	
	 // to test
	@EJB
	IBatchRichiesteCaricamentiMassiviNew batchRichiesteCaricamentiMassiviNew;
	
	 // to test
	IBatchRichiesteCaricamentiMassiviVisibilitaNew batchRichiesteCaricamentiMassiviVisibilitaNew;
	
	 // OK
	IBatchRichiesteCaricamentiMassiviMappatureNew batchRichiesteCaricamentiMassiviMappatureNew;
	
	// OK
	IBatchRiempimentoCatalogoNew batchRiempimentoCatalogoNew;
	
	// to test
	IBatchRiorganizzazioneDisabilitazioniCdrNew batchRiorganizzazioneDisabilitazioniCdrNew;
	
	//to test
	IBatchRiorganizzazioneReportDisabilitazioniNew batchRiorganizzazioneReportDisabilitazioniNew;
	
	// to test
	IBatchRiorganizzazioniAggiornaCdrNew batchRiorganizzazioniAggiornaCdrNew;
	
	//to test
	IBatchRuoliDegliOperatoriNew batchRuoliDegliOperatoriNew;
	
	//to test
	IBatchScadenzaDelegheImplNew batchScadenzaDelegheImplNew;
	
	// to test
	IBatchScadenzeNew batchScadenzeNew;
	/*
	
	IPopolamentoTabelleNew batchPopolamentoNew;
	
	@EJB
	private IAllineamentoApplicazioniNew allineamentoApplicazioniNew; 
	@EJB
	private IAllineamentoRoleGroupNew allineamentoRoleGroupNew;
	@EJB
	private IAllineamentoProfiloNew allineamentoProfiloNew; 
	@EJB
	private IAllineamentoRelApplProfRoleGroupNew allineamentoRelApplProfRoleGroupNew;
	@EJB
	private IAggiornamentoRoleGroupNew aggiornamentoRoleGroupNew;
	@EJB
	private IAggiornamentoProfiloNew aggiornamentoProfiloNew;
	@EJB
	private ISegnalazioneTupleChiuseNew segnalazioneTupleChiuseNew;
	*/
	@Override
	public Response batchAbilitazioniDisponibiliNeiCdR() {
		
		try {
			batchAbilitazioniDisponibiliNeiCdR.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchAbilitazioniOperatoriPerCdR() {
		
		try {
			batchAbilitazioniOperatoriPerCdR.startBatch();
			
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	
	@Override
	public Response batchAbilitazioniOperatoriPerCdRGeneraExport() {
		
		try {
			batchAbilitazioniOperatoriPerCdRGeneraExport.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchAllineamentoAmministratori() {
		
		try {
			//batchAllineamentoAmministratori.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchCaricamentoCsvCruscotto() {
		
		try {
			batchCaricamentoCsvCruscotto.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchCessazioneMobilitaNew() {
		
		try {
			batchCessazioneMobilitaNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchBatchCoerenzaNew() {
		
		try {
			batchCoerenzaNewJpa.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchExportRuoliDiUnCdrNew() {
		try {
			batchExportRuoliDiUnCdrNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	
	@Override
	public Response batchGeneraRichiesteNew() {
		try {
			batchGeneraRichiesteNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchGenerazioneRichiesteScadenzeNew() {
		try {
			batchGenerazioneRichiesteScadenzeNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchHrEmailVariazioneDatiUtentiNew() {
		try {
			batchHrEmailVariazioneDatiUtentiNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	
	@Override
	public Response batchHrEmailVariazioniRuoliCdrNew() {
		try {
			batchHrEmailVariazioniRuoliCdrNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	
	@Override
	public Response batchHrResponsabileCdRNew() {
		try {
			batchHrResponsabileCdRNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchHrVariazioneDatiUtenteNew(String verifica) {
		try {
			batchHrVariazioneDatiUtenteNew.startBatch(verifica);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchImportResponsabilitaNew(String dataResponsabilita, String criterioResponsabilita, String tipoElaborazioneResponsabilita) {
		try {
			batchImportResponsabilitaNew.startBatch(dataResponsabilita, criterioResponsabilita, tipoElaborazioneResponsabilita);
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	
	@Override
	public Response batchMailRichiesteNew() {
		try {
			batchMailRichiesteNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	
	@Override
	public Response batchMailScadenzeOperatoriEsterniNew() {
		try {
			batchMailScadenzeOperatoriEsterniNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	
	@Override
	public Response batchOrganizzazioneNew() {
		try {
			batchOrganizzazioneNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	
	@Override
	public Response batchProfiliAssegnatiNonPrevistiAGNew(String segnalazioneAgenzia) {
		try {
			batchProfiliAssegnatiNonPrevistiAGNew.startBatch(segnalazioneAgenzia);
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchResponsabilitaNew(String type, String all, String dataUltimoAggioranmento) {
		try {
			DateFormat frmt = new SimpleDateFormat("dd/MM/yyyy");
			Date dataUlt = frmt.parse(dataUltimoAggioranmento);
			batchResponsabilitaNew.startBatch(type,all, new java.sql.Date(dataUlt.getTime()));
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchRichiesteCaricamentiMassiviMappatureNew(String idCaricamento) {
		try {
			batchRichiesteCaricamentiMassiviMappatureNew.startBatch(idCaricamento);
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	
	@Override
	public Response batchRichiesteCaricamentiMassiviAggVisNew(String idCaricamento) {
		try {
			batchRichiesteCaricamentiMassiviAggVisNew.startBatch(idCaricamento);
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchRichiesteCaricamentiMassiviNew(String idCaricamento) {
		try {
			batchRichiesteCaricamentiMassiviNew.startBatch(idCaricamento);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchRichiesteCaricamentiMassiviVisibilitaNew(String idCaricamento) {
		try {
			batchRichiesteCaricamentiMassiviVisibilitaNew.startBatch(idCaricamento);
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchRiempimentoCatalogoNew() {
		try {
			batchRiempimentoCatalogoNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchRiorganizzazioneDisabilitazioniCdrNew() {
		try {
			batchRiorganizzazioneDisabilitazioniCdrNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	
	@Override
	public Response batchRiorganizzazioneReportDisabilitazioniNew() {
		try {
			batchRiorganizzazioneReportDisabilitazioniNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchRiorganizzazioniAggiornaCdrNew() {
		try {
			batchRiorganizzazioniAggiornaCdrNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchRuoliDegliOperatoriNew() {
		try {
			batchRuoliDegliOperatoriNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchScadenzaDelegheImplNew() {
		try {
			batchScadenzaDelegheImplNew.startBatch(1,"");
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response batchScadenzeNew() {
		try {
			batchScadenzeNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
/*	
	@Override
	public Response batchPopolamentoNew() {
		try {
			batchPopolamentoNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response allineamentoApplicazioniNew() {
		try {
			allineamentoApplicazioniNew.startBatch(null);
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response allineamentoRoleGroupNew() {
		try {
			allineamentoRoleGroupNew.startBatch(null);
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response allineamentoProfiloNew() {
		try {
			allineamentoProfiloNew.startBatch(null);
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response allineamentoRelApplProfRoleGroupNew() {
		try {
			allineamentoRelApplProfRoleGroupNew.startBatch(null);
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response aggiornamentoRoleGroupNew() {
		try {
			aggiornamentoRoleGroupNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response aggiornamentoProfiloNew() {
		try {
			aggiornamentoProfiloNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
	
	@Override
	public Response segnalazioneTupleChiuseNew() {
		try {
			segnalazioneTupleChiuseNew.startBatch();
		} catch (Exception e) {
			Response.status(500, e.getMessage()).build();
		}
		
		return Response.ok().build();
	}
*/
}
