package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class CoerenzaSegnalazioniBean implements Serializable{

	private static final long serialVersionUID = 582715063727151446L;
	private String codiceFiscale;
	private String cognome;
	private String nome;
	private String codiceCdrIILiv;
	private String descrizioneCdrIILiv;
	private String descrizioneStruttura;
	private String codiceCdrAssegnazione;
	private String descrizioneCdrAssegnazione;
	private String codiceUfficioAssegnazione;
	private String descrizioneUfficioAssegnazione;
	private String codiceAmbito;
	private String descrizioneAmbito;
	private String codiceRoleGroup;
	private String descrizioneRoleGroup;
	private String codiceProfilo;
	private String descrizioneProfilo;
	private String codiceCdrIncongruenza;
	private String descrizioneCdrIncongruenza;
	private String codiceUfficioIncongruenza;
	private String descrizioneUfficioIncongruenza;
	private String idRichiestaVisibilita;
	private String codiceCdrVisibilita;
	private String codiceUfficioVisibilita;
	private String descrizioneIncoerenza;
	private String incoerenzaExplEnt;
	private Date dataRivelazioneIncongruenza;
	private String incongruenzaDaSegnalare;
	private String incongruenzaSegnalata;
	
	// 2023 2.5 -->
	private String cfAmmAut;
	private String cognomeAmmAut;
	private String nomeAmmAut;
	private String cdrAmmAut;
	private Date dataEsecuzione;
	
	// 2023 2.5 <--
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodiceCdrIILiv() {
		return codiceCdrIILiv;
	}
	public void setCodiceCdrIILiv(String codiceCdrIILiv) {
		this.codiceCdrIILiv = codiceCdrIILiv;
	}
	public String getDescrizioneCdrIILiv() {
		return descrizioneCdrIILiv;
	}
	public void setDescrizioneCdrIILiv(String descrizioneCdrIILiv) {
		this.descrizioneCdrIILiv = descrizioneCdrIILiv;
	}
	public String getDescrizioneStruttura() {
		return descrizioneStruttura;
	}
	public void setDescrizioneStruttura(String descrizioneStruttura) {
		this.descrizioneStruttura = descrizioneStruttura;
	}
	public String getCodiceCdrAssegnazione() {
		return codiceCdrAssegnazione;
	}
	public void setCodiceCdrAssegnazione(String codiceCdrAssegnazione) {
		this.codiceCdrAssegnazione = codiceCdrAssegnazione;
	}
	public String getDescrizioneCdrAssegnazione() {
		return descrizioneCdrAssegnazione;
	}
	public void setDescrizioneCdrAssegnazione(String descrizioneCdrAssegnazione) {
		this.descrizioneCdrAssegnazione = descrizioneCdrAssegnazione;
	}
	public String getCodiceUfficioAssegnazione() {
		return codiceUfficioAssegnazione;
	}
	public void setCodiceUfficioAssegnazione(String codiceUfficioAssegnazione) {
		this.codiceUfficioAssegnazione = codiceUfficioAssegnazione;
	}
	public String getDescrizioneUfficioAssegnazione() {
		return descrizioneUfficioAssegnazione;
	}
	public void setDescrizioneUfficioAssegnazione(
			String descrizioneUfficioAssegnazione) {
		this.descrizioneUfficioAssegnazione = descrizioneUfficioAssegnazione;
	}
	public String getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	public String getDescrizioneAmbito() {
		return descrizioneAmbito;
	}
	public void setDescrizioneAmbito(String descrizioneAmbito) {
		this.descrizioneAmbito = descrizioneAmbito;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getDescrizioneRoleGroup() {
		return descrizioneRoleGroup;
	}
	public void setDescrizioneRoleGroup(String descrizioneRoleGroup) {
		this.descrizioneRoleGroup = descrizioneRoleGroup;
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
	public String getCodiceCdrIncongruenza() {
		return codiceCdrIncongruenza;
	}
	public void setCodiceCdrIncongruenza(String codiceCdrIncongruenza) {
		this.codiceCdrIncongruenza = codiceCdrIncongruenza;
	}
	public String getDescrizioneCdrIncongruenza() {
		return descrizioneCdrIncongruenza;
	}
	public void setDescrizioneCdrIncongruenza(String descrizioneCdrIncongruenza) {
		this.descrizioneCdrIncongruenza = descrizioneCdrIncongruenza;
	}
	public String getCodiceUfficioIncongruenza() {
		return codiceUfficioIncongruenza;
	}
	public void setCodiceUfficioIncongruenza(String codiceUfficioIncongruenza) {
		this.codiceUfficioIncongruenza = codiceUfficioIncongruenza;
	}
	public String getDescrizioneUfficioIncongruenza() {
		return descrizioneUfficioIncongruenza;
	}
	public void setDescrizioneUfficioIncongruenza(
			String descrizioneUfficioIncongruenza) {
		this.descrizioneUfficioIncongruenza = descrizioneUfficioIncongruenza;
	}
	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public String getCodiceCdrVisibilita() {
		return codiceCdrVisibilita;
	}
	public void setCodiceCdrVisibilita(String codiceCdrVisibilita) {
		this.codiceCdrVisibilita = codiceCdrVisibilita;
	}
	public String getCodiceUfficioVisibilita() {
		return codiceUfficioVisibilita;
	}
	public void setCodiceUfficioVisibilita(String codiceUfficioVisibilita) {
		this.codiceUfficioVisibilita = codiceUfficioVisibilita;
	}
	public String getDescrizioneIncoerenza() {
		return descrizioneIncoerenza;
	}
	public void setDescrizioneIncoerenza(String descrizioneIncoerenza) {
		this.descrizioneIncoerenza = descrizioneIncoerenza;
	}
	public String getIncoerenzaExplEnt() {
		return incoerenzaExplEnt;
	}
	public void setIncoerenzaExplEnt(String incoerenzaExplEnt) {
		this.incoerenzaExplEnt = incoerenzaExplEnt;
	}
	public String getIncongruenzaDaSegnalare() {
		return incongruenzaDaSegnalare;
	}
	public void setIncongruenzaDaSegnalare(String incongruenzaDaSegnalare) {
		this.incongruenzaDaSegnalare = incongruenzaDaSegnalare;
	}
	public String getIncongruenzaSegnalata() {
		return incongruenzaSegnalata;
	}
	public void setIncongruenzaSegnalata(String incongruenzaSegnalata) {
		this.incongruenzaSegnalata = incongruenzaSegnalata;
	}
	public Date getDataRivelazioneIncongruenza() {
		return dataRivelazioneIncongruenza;
	}
	public void setDataRivelazioneIncongruenza(Date dataRivelazioneIncongruenza) {
		this.dataRivelazioneIncongruenza = dataRivelazioneIncongruenza;
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
	
}
