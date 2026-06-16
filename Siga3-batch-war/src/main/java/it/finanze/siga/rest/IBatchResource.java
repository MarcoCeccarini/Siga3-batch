package it.finanze.siga.rest;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirements;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/batch")
@Tag(name = "batch", description = "batch")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface IBatchResource {

	@POST
	@Path("/batchAbilitazioniDisponibiliNeiCdR")
	@Operation(summary = "batchAbilitazioniDisponibiliNeiCdR")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchAbilitazioniDisponibiliNeiCdR();
		
	@POST
	@Path("/batchAbilitazioniOperatoriPerCdR")
	@Operation(summary = "batchAbilitazioniOperatoriPerCdR")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchAbilitazioniOperatoriPerCdR();
	
	
	@POST
	@Path("/batchAbilitazioniOperatoriPerCdRGeneraExport")
	@Operation(summary = "batchAbilitazioniOperatoriPerCdRGeneraExport")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchAbilitazioniOperatoriPerCdRGeneraExport();
	
	@POST
	@Path("/batchAllineamentoAmministratori")
	@Operation(summary = "batchAllineamentoAmministratori")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchAllineamentoAmministratori();
	
	@POST
	@Path("/batchCaricamentoCsvCruscotto")
	@Operation(summary = "batchCaricamentoCsvCruscotto")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchCaricamentoCsvCruscotto();
	
	@POST
	@Path("/batchCessazioneMobilitaNew")
	@Operation(summary = "batchCessazioneMobilitaNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchCessazioneMobilitaNew();
	
	@POST
	@Path("/batchCoerenzaNewJpa")
	@Operation(summary = "batchCoerenzaNewJpa")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchBatchCoerenzaNew();
	
	
	@POST
	@Path("/batchExportRuoliDiUnCdrNew")
	@Operation(summary = "batchExportRuoliDiUnCdrNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchExportRuoliDiUnCdrNew();
	
	
	@POST
	@Path("/batchGeneraRichiesteNew")
	@Operation(summary = "batchGeneraRichiesteNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchGeneraRichiesteNew();
	
	
	@POST
	@Path("/batchGenerazioneRichiesteScadenzeNew")
	@Operation(summary = "batchGenerazioneRichiesteScadenzeNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchGenerazioneRichiesteScadenzeNew();
	
	
	@POST
	@Path("/batchHrEmailVariazioneDatiUtentiNew")
	@Operation(summary = "batchHrEmailVariazioneDatiUtentiNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchHrEmailVariazioneDatiUtentiNew();
	
	
	@POST
	@Path("/batchHrEmailVariazioniRuoliCdrNew")
	@Operation(summary = "batchHrEmailVariazioniRuoliCdrNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchHrEmailVariazioniRuoliCdrNew();
	
	@POST
	@Path("/batchHrResponsabileCdRNew")
	@Operation(summary = "batchHrResponsabileCdRNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchHrResponsabileCdRNew();
	
	
	@POST
	@Path("/batchHrVariazioneDatiUtenteNew")
	@Operation(summary = "batchHrVariazioneDatiUtenteNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchHrVariazioneDatiUtenteNew(@QueryParam("verifica") @Parameter(name = "verifica", description = "verifica") String verifica);
	
	
	@POST
	@Path("/batchImportResponsabilitaNew")
	@Operation(summary = "batchImportResponsabilitaNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchImportResponsabilitaNew(
			@QueryParam("dataResponsabilita") @Parameter(name = "dataResponsabilita", description = "dataResponsabilita") String dataResponsabilita,
			@QueryParam("criterioResponsabilita") @Parameter(name = "criterioResponsabilita", description = "criterioResponsabilita") String criterioResponsabilita,
			@QueryParam("tipoElaborazioneResponsabilita") @Parameter(name = "tipoElaborazioneResponsabilita", description = "tipoElaborazioneResponsabilita") String tipoElaborazioneResponsabilita
			);
	
	@POST
	@Path("/batchMailRichiesteNew")
	@Operation(summary = "batchMailRichiesteNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchMailRichiesteNew();
	
	
	@POST
	@Path("/batchMailScadenzeOperatoriEsterniNew")
	@Operation(summary = "batchMailScadenzeOperatoriEsterniNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchMailScadenzeOperatoriEsterniNew();
	
	
	@POST
	@Path("/batchOrganizzazioneNew")
	@Operation(summary = "batchOrganizzazioneNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchOrganizzazioneNew();
	
	@POST
	@Path("/batchProfiliAssegnatiNonPrevistiAGNew")
	@Operation(summary = "batchProfiliAssegnatiNonPrevistiAGNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchProfiliAssegnatiNonPrevistiAGNew(
			@QueryParam("segnalazioneAgenzia") @Parameter(name = "segnalazioneAgenzia", description = "segnalazioneAgenzia") String segnalazioneAgenzia
			);
	
	
	
	@POST
	@Path("/batchResponsabilitaNew")
	@Operation(summary = "batchResponsabilitaNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchResponsabilitaNew(
			@QueryParam("type") @Parameter(name = "type", description = "type") String type,
			@QueryParam("all") @Parameter(name = "all", description = "all") String all,
			@QueryParam("dataUltimoAggioranmento") @Parameter(name = "dataUltimoAggioranmento", description = "dataUltimoAggioranmento") String dataUltimoAggioranmento
			);
	
	@POST
	@Path("/batchRichiesteCaricamentiMassiviMappatureNew")
	@Operation(summary = "batchRichiesteCaricamentiMassiviMappatureNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchRichiesteCaricamentiMassiviMappatureNew(
			@QueryParam("idCaricamento") @Parameter(name = "idCaricamento", description = "idCaricamento") String idCaricamento);
	
	
	@POST
	@Path("/batchRichiesteCaricamentiMassiviAggVisNew")
	@Operation(summary = "batchRichiesteCaricamentiMassiviAggVisNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchRichiesteCaricamentiMassiviAggVisNew(
			@QueryParam("idCaricamento") @Parameter(name = "idCaricamento", description = "idCaricamento") String idCaricamento);
	
	
	@POST
	@Path("/batchRichiesteCaricamentiMassiviNew")
	@Operation(summary = "batchRichiesteCaricamentiMassiviNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchRichiesteCaricamentiMassiviNew(
			@QueryParam("idCaricamento") @Parameter(name = "idCaricamento", description = "idCaricamento") String idCaricamento);
	
	
	@POST
	@Path("/batchRichiesteCaricamentiMassiviVisibilitaNew")
	@Operation(summary = "batchRichiesteCaricamentiMassiviVisibilitaNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchRichiesteCaricamentiMassiviVisibilitaNew(
			@QueryParam("idCaricamento") @Parameter(name = "idCaricamento", description = "idCaricamento") String idCaricamento);
	
	
	@POST
	@Path("/batchRiempimentoCatalogoNew")
	@Operation(summary = "batchRiempimentoCatalogoNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchRiempimentoCatalogoNew();
	
	
	@POST
	@Path("/batchRiorganizzazioneDisabilitazioniCdrNew")
	@Operation(summary = "batchRiorganizzazioneDisabilitazioniCdrNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchRiorganizzazioneDisabilitazioniCdrNew();
	
	@POST
	@Path("/batchRiorganizzazioneReportDisabilitazioniNew")
	@Operation(summary = "batchRiorganizzazioneReportDisabilitazioniNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchRiorganizzazioneReportDisabilitazioniNew();
	
	@POST
	@Path("/batchRiorganizzazioniAggiornaCdrNew")
	@Operation(summary = "batchRiorganizzazioniAggiornaCdrNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchRiorganizzazioniAggiornaCdrNew();
	
	@POST
	@Path("/batchRuoliDegliOperatoriNew")
	@Operation(summary = "batchRuoliDegliOperatoriNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchRuoliDegliOperatoriNew();
	
	@POST
	@Path("/batchScadenzaDelegheImplNew")
	@Operation(summary = "batchScadenzaDelegheImplNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchScadenzaDelegheImplNew();
	
	@POST
	@Path("/batchScadenzeNew")
	@Operation(summary = "batchScadenzeNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchScadenzeNew();
/*	
	@POST
	@Path("/batchPopolamentoNew")
	@Operation(summary = "batchPopolamentoNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response batchPopolamentoNew(); 
	
	@POST
	@Path("/allineamentoApplicazioniNew")
	@Operation(summary = "batchPopolamentoNew.allineamentoApplicazioniNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response allineamentoApplicazioniNew(); 
	
	@POST
	@Path("/allineamentoRoleGroupNew")
	@Operation(summary = "batchPopolamentoNew.allineamentoRoleGroupNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response allineamentoRoleGroupNew(); 
	
	@POST
	@Path("/allineamentoProfiloNew")
	@Operation(summary = "batchPopolamentoNew.allineamentoProfiloNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response allineamentoProfiloNew(); 
	
	@POST
	@Path("/allineamentoRelApplProfRoleGroupNew")
	@Operation(summary = "batchPopolamentoNew.allineamentoRelApplProfRoleGroupNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response allineamentoRelApplProfRoleGroupNew(); 
	
	@POST
	@Path("/aggiornamentoRoleGroupNew")
	@Operation(summary = "batchPopolamentoNew.aggiornamentoRoleGroupNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response aggiornamentoRoleGroupNew(); 
	
	@POST
	@Path("/aggiornamentoProfiloNew")
	@Operation(summary = "batchPopolamentoNew.aggiornamentoProfiloNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response aggiornamentoProfiloNew(); 
	
	@POST
	@Path("/segnalazioneTupleChiuseNew")
	@Operation(summary = "batchPopolamentoNew.segnalazioneTupleChiuseNew")	
	@Produces({ MediaType.APPLICATION_JSON})
	@APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "start batch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Esito.class))),
	            @APIResponse(responseCode = "400", description = "Invalid name supplied"),
	            @APIResponse(responseCode = "500", description = "Internal Server Error")})
	@SecurityRequirements(value = {@SecurityRequirement(name = "jakarta-auth")})
	public Response segnalazioneTupleChiuseNew(); 
	
*/	
}
