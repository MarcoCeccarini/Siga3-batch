package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class SegnalazioneIncoerenzeVisibilitaBean implements Serializable{
	
	private static final long serialVersionUID = 1420650593820591494L;
	private String codiceFiscale;
	private Date dataRilevazioneIncongruenza;
	private String codiceCdrVisibilita;
	private String descrCdrVisibilita;
	private String codiceCdr;
	private String descrizioneCdr;
	private String codiceAmbito;
	private String descrizioneAmbito;
	private String roleGroup;
	private String descrRoleGroup;
	private String codiceProfilo;
	private String descrizioneProfilo;
	private String esistenteCau;
	private String esistenteHr;
	private String esistenteCA;
	private String esistenteSiga;
	private String descrIncoerenza;
	private String incongruenzaSegnalata;
	
	
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public Date getDataRilevazioneIncongruenza() {
		return dataRilevazioneIncongruenza;
	}
	public void setDataRilevazioneIncongruenza(Date dataRilevazioneIncongruenza) {
		this.dataRilevazioneIncongruenza = dataRilevazioneIncongruenza;
	}
	public String getCodiceCdrVisibilita() {
		return codiceCdrVisibilita;
	}
	public void setCodiceCdrVisibilita(String codiceCdrVisibilita) {
		this.codiceCdrVisibilita = codiceCdrVisibilita;
	}
	public String getDescrCdrVisibilita() {
		return descrCdrVisibilita;
	}
	public void setDescrCdrVisibilita(String descrCdrVisibilita) {
		this.descrCdrVisibilita = descrCdrVisibilita;
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
	public String getDescrRoleGroup() {
		return descrRoleGroup;
	}
	public void setDescrRoleGroup(String descrRoleGroup) {
		this.descrRoleGroup = descrRoleGroup;
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
	public String getEsistenteCau() {
		return esistenteCau;
	}
	public void setEsistenteCau(String esistenteCau) {
		this.esistenteCau = esistenteCau;
	}
	public String getEsistenteHr() {
		return esistenteHr;
	}
	public void setEsistenteHr(String esistenteHr) {
		this.esistenteHr = esistenteHr;
	}
	public String getEsistenteCA() {
		return esistenteCA;
	}
	public void setEsistenteCA(String esistenteCA) {
		this.esistenteCA = esistenteCA;
	}
	public String getEsistenteSiga() {
		return esistenteSiga;
	}
	public void setEsistenteSiga(String esistenteSiga) {
		this.esistenteSiga = esistenteSiga;
	}
	public String getDescrIncoerenza() {
		return descrIncoerenza;
	}
	public void setDescrIncoerenza(String descrIncoerenza) {
		this.descrIncoerenza = descrIncoerenza;
	}
	
	@Override
	public String toString() {
		return "[codiceFiscale=" + codiceFiscale
				+ ", dataRilevazioneIncongruenza=" + dataRilevazioneIncongruenza + 
				", codiceCdrVisibilita=" + codiceCdrVisibilita + 
				", descrCdrVisibilita=" + descrCdrVisibilita + 
				", codiceCdr=" + codiceCdr + 
				", descrizioneCdr=" + descrizioneCdr + 
				", codiceAmbito=" + codiceAmbito + 
				", descrizioneAmbito=" + descrizioneAmbito + 
				", roleGroup=" + roleGroup + 
				", descrRoleGroup=" + descrRoleGroup + 
				", codiceProfilo=" + codiceProfilo + 
				", descrizioneProfilo=" + descrizioneProfilo + 
				", esistenteCau=" + esistenteCau + 
				", esistenteHr=" + esistenteHr + 
				", esistenteCA=" + esistenteCA + 
				", esistenteSiga=" + esistenteSiga + 
				", descrIncoerenza=" + descrIncoerenza + "]";
	}
	public String getIncongruenzaSegnalata() {
		return incongruenzaSegnalata;
	}
	public void setIncongruenzaSegnalata(String incongruenzaSegnalata) {
		this.incongruenzaSegnalata = incongruenzaSegnalata;
	}
	public String getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	
}
