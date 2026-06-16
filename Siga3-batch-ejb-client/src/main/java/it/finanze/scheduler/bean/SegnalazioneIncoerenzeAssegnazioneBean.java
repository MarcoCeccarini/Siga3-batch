package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class SegnalazioneIncoerenzeAssegnazioneBean implements Serializable{

	private static final long serialVersionUID = 7454319303110031255L;
	private String codiceFiscale;
	private String codiceUfficio;
	private String descrizioneUfficio;
	private String codiceCdr;
	private String descrizioneCdr;
	private String codiceAmbito;
	private String descrizioneAmbito;
	private String roleGroup;
	private String codiceProfilo;
	private String descrizioneProfilo;
	private String descrizioneIncoerenza;
	private String incongruenzaDaSegnalare;
	private String incongruenzaSegnalata;
	private String codiceCdrAppartenenza;
	private String descrizioneCdrAppartenenza;
	private String nome;
	private String cognome;
	private String descrizioneIILiv;
	private String descrizioneStruttura;
	private String descrCdrAssegnazione;
	private String codCdrAssegnazione;
	private String descrUfficioIncongruenza;
	private String codUfficioIncongruenza;
	private String descrRoleGroup;
	private String idRichiestaVisibilita;
	private String codCdrVisibilita;
	private String codUfficioVisibilita;
	private String cdrAssegnazioneExcel;
	private String ufficioAssegnazioneExcel;
	private String roleGroupExcel;
	private String ufficioIncongruenzaExcel;
	
	// 2023 2.5 -->
	private String incoerenzaExplEnt;
	private String cfAmmAut;
	private String cognomeAmmAut;
	private String nomeAmmAut;
	private String cdrAmmAut;
	private Date dataEsecuzione;
	// 2023 2.5 <--
	//2023 1.8??? -->
	private Date dataScadenza;
	private Date dataRilevazioneIncongr;
	private String idRichiesta;
	// 2023 1.8??? <--
	
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getDescrizioneUfficio() {
		return descrizioneUfficio;
	}
	public void setDescrizioneUfficio(String descrizioneUfficio) {
		this.descrizioneUfficio = descrizioneUfficio;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getDescrizioneCdr() {
		return descrizioneCdr;
	}
	public void setDescrizioneCdr(String descrizioneCdr) {
		this.descrizioneCdr = descrizioneCdr;
	}
	public String getDescrizioneAmbito() {
		return descrizioneAmbito;
	}
	public void setDescrizioneAmbito(String descrizioneAmbito) {
		this.descrizioneAmbito = descrizioneAmbito;
	}
	public String getRoleGroup() {
		return roleGroup;
	}
	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getDescrizioneProfilo() {
		return descrizioneProfilo;
	}
	public void setDescrizioneProfilo(String descrizioneProfilo) {
		this.descrizioneProfilo = descrizioneProfilo;
	}
	public String getIncongruenzaDaSegnalare() {
		return incongruenzaDaSegnalare;
	}
	public void setIncongruenzaDaSegnalare(String incongruenzaDaSegnalare) {
		this.incongruenzaDaSegnalare = incongruenzaDaSegnalare;
	}
	public String getDescrizioneIncoerenza() {
		return descrizioneIncoerenza;
	}
	public void setDescrizioneIncoerenza(String descrizioneIncoerenza) {
		this.descrizioneIncoerenza = descrizioneIncoerenza;
	}
	public String getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
	@Override
	public String toString() {
		return "[codiceFiscale=" + codiceFiscale
				+ ", codiceUfficio=" + codiceUfficio + ", descrizioneUfficio="
				+ descrizioneUfficio + ", codiceCdr=" + codiceCdr
				+ ", descrizioneCdr=" + descrizioneCdr + ", codiceAmbito="
				+ codiceAmbito + ", descrizioneAmbito=" + descrizioneAmbito
				+ ", roleGroup=" + roleGroup + ", codiceProfilo="
				+ codiceProfilo + ", descrizioneProfilo=" + descrizioneProfilo
				+ ", descrizioneIncoerenza=" + descrizioneIncoerenza + "]";
	}
	public String getIncongruenzaSegnalata() {
		return incongruenzaSegnalata;
	}
	public void setIncongruenzaSegnalata(String incongruenzaSegnalata) {
		this.incongruenzaSegnalata = incongruenzaSegnalata;
	}
	public String getCodiceCdrAppartenenza() {
		return codiceCdrAppartenenza;
	}
	public void setCodiceCdrAppartenenza(String codiceCdrAppartenenza) {
		this.codiceCdrAppartenenza = codiceCdrAppartenenza;
	}
	public String getDescrizioneCdrAppartenenza() {
		return descrizioneCdrAppartenenza;
	}
	public void setDescrizioneCdrAppartenenza(String descrizioneCdrAppartenenza) {
		this.descrizioneCdrAppartenenza = descrizioneCdrAppartenenza;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getDescrizioneIILiv() {
		return descrizioneIILiv;
	}
	public void setDescrizioneIILiv(String descrizioneIILiv) {
		this.descrizioneIILiv = descrizioneIILiv;
	}
	public String getDescrizioneStruttura() {
		return descrizioneStruttura;
	}
	public void setDescrizioneStruttura(String descrizioneStruttura) {
		this.descrizioneStruttura = descrizioneStruttura;
	}
	public String getDescrCdrAssegnazione() {
		return descrCdrAssegnazione;
	}
	public void setDescrCdrAssegnazione(String descrCdrAssegnazione) {
		this.descrCdrAssegnazione = descrCdrAssegnazione;
	}
	public String getCodCdrAssegnazione() {
		return codCdrAssegnazione;
	}
	public void setCodCdrAssegnazione(String codCdrAssegnazione) {
		this.codCdrAssegnazione = codCdrAssegnazione;
	}
	public String getDescrUfficioIncongruenza() {
		return descrUfficioIncongruenza;
	}
	public void setDescrUfficioIncongruenza(String descrUfficioIncongruenza) {
		this.descrUfficioIncongruenza = descrUfficioIncongruenza;
	}
	public String getCodUfficioIncongruenza() {
		return codUfficioIncongruenza;
	}
	public void setCodUfficioIncongruenza(String codUfficioIncongruenza) {
		this.codUfficioIncongruenza = codUfficioIncongruenza;
	}
	public String getDescrRoleGroup() {
		return descrRoleGroup;
	}
	public void setDescrRoleGroup(String descrRoleGroup) {
		this.descrRoleGroup = descrRoleGroup;
	}
	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public String getCodCdrVisibilita() {
		return codCdrVisibilita;
	}
	public void setCodCdrVisibilita(String codCdrVisibilita) {
		this.codCdrVisibilita = codCdrVisibilita;
	}
	public String getCodUfficioVisibilita() {
		return codUfficioVisibilita;
	}
	public void setCodUfficioVisibilita(String codUfficioVisibilita) {
		this.codUfficioVisibilita = codUfficioVisibilita;
	}
	
	public String getCdrAssegnazioneExcel(){
		cdrAssegnazioneExcel = this.descrCdrAssegnazione + " ("+ this.codCdrAssegnazione + ")";
		return cdrAssegnazioneExcel;
	}
	
	public String getUfficioAssegnazioneExcel(){
		ufficioAssegnazioneExcel = this.descrizioneUfficio + " ("+ this.codiceUfficio + ")";
		return ufficioAssegnazioneExcel;
	}
	
	public String getRoleGroupExcel(){
		roleGroupExcel = "";
		if(this.roleGroup != null)
			roleGroupExcel = this.descrRoleGroup + " ("+ this.roleGroup + ")";
		else
			roleGroupExcel = this.descrRoleGroup;
		return roleGroupExcel;
	}
	
	public String getUfficioIncongruenzaExcel(){
		ufficioIncongruenzaExcel = this.descrUfficioIncongruenza + " ("+ this.codUfficioIncongruenza + ")";
		return ufficioIncongruenzaExcel;
	}
	public void setUfficioAssegnazioneExcel(String ufficioAssegnazioneExcel) {
		this.ufficioAssegnazioneExcel = ufficioAssegnazioneExcel;
	}
	public void setRoleGroupExcel(String roleGroupExcel) {
		this.roleGroupExcel = roleGroupExcel;
	}
	public void setUfficioIncongruenzaExcel(String ufficioIncongruenzaExcel) {
		this.ufficioIncongruenzaExcel = ufficioIncongruenzaExcel;
	}
	
	public void setCdrAssegnazioneExcel(String cdrAssegnazioneExcel) {
		this.cdrAssegnazioneExcel = cdrAssegnazioneExcel;
	}
	public String getIncoerenzaExplEnt() {
		return incoerenzaExplEnt;
	}
	public void setIncoerenzaExplEnt(String incoerenzaExplEnt) {
		this.incoerenzaExplEnt = incoerenzaExplEnt;
	}
	public String getCfAmmAut() {
		return cfAmmAut;
	}
	public void setCfAmmAut(String cfAmmAut) {
		this.cfAmmAut = cfAmmAut;
	}
	public String getCognomeAmmAut() {
		return cognomeAmmAut;
	}
	public void setCognomeAmmAut(String cognomeAmmAut) {
		this.cognomeAmmAut = cognomeAmmAut;
	}
	public String getNomeAmmAut() {
		return nomeAmmAut;
	}
	public void setNomeAmmAut(String nomeAmmAut) {
		this.nomeAmmAut = nomeAmmAut;
	}
	public String getCdrAmmAut() {
		return cdrAmmAut;
	}
	public void setCdrAmmAut(String cdrAmmAut) {
		this.cdrAmmAut = cdrAmmAut;
	}
	public Date getDataEsecuzione() {
		return dataEsecuzione;
	}
	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public Date getDataRilevazioneIncongr() {
		return dataRilevazioneIncongr;
	}
	public void setDataRilevazioneIncongr(Date dataRilevazioneIncongr) {
		this.dataRilevazioneIncongr = dataRilevazioneIncongr;
	}
	public String getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(String idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	
	
	
}
