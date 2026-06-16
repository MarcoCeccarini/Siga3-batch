package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class ProfiliCau implements Serializable{


	private static final long serialVersionUID = -1311949452645691593L;
	private String codiceFiscale;
	private String codiceCdr;
	private String codiceUfficio;
	private String profilo;
	private String descrizioneProfilo;
	private String ambito;
	private String descrizioneAmbito;
	private Date dataAssociazioneProfiloUtente;
	private String profiloVisibilita;
	private String profiloInRichiesteVisibilita;
	private String profiloPresenteInCau;
	private String profiloPresenteInSiga;
	private String profiloPresenteInHR;
	private String profiloPresenteInCEA;
	private String cdrVisibilita;
	private String azione;
	private String roleGroup;
	private String profiloCatUfficioAssegnazione;
	
	private int idRichiesta; 
	private String idRichiestaVisibilita; 
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getProfilo() {
		return profilo;
	}
	public void setProfilo(String profilo) {
		this.profilo = profilo;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public Date getDataAssociazioneProfiloUtente() {
		return dataAssociazioneProfiloUtente;
	}
	public void setDataAssociazioneProfiloUtente(Date dataAssociazioneProfiloUtente) {
		this.dataAssociazioneProfiloUtente = dataAssociazioneProfiloUtente;
	}
	public String getProfiloVisibilita() {
		return profiloVisibilita;
	}
	public void setProfiloVisibilita(String profiloVisibilita) {
		this.profiloVisibilita = profiloVisibilita;
	}
	public String getProfiloInRichiesteVisibilita() {
		return profiloInRichiesteVisibilita;
	}
	public void setProfiloInRichiesteVisibilita(String profiloInRichiesteVisibilita) {
		this.profiloInRichiesteVisibilita = profiloInRichiesteVisibilita;
	}
	public String getProfiloPresenteInCau() {
		return profiloPresenteInCau;
	}
	public void setProfiloPresenteInCau(String profiloPresenteInCau) {
		this.profiloPresenteInCau = profiloPresenteInCau;
	}
	public String getProfiloPresenteInSiga() {
		return profiloPresenteInSiga;
	}
	public void setProfiloPresenteInSiga(String profiloPresenteInSiga) {
		this.profiloPresenteInSiga = profiloPresenteInSiga;
	}
	public String getCdrVisibilita() {
		return cdrVisibilita;
	}
	public void setCdrVisibilita(String cdrVisibilita) {
		this.cdrVisibilita = cdrVisibilita;
	}
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public String getProfiloPresenteInHR() {
		return profiloPresenteInHR;
	}
	public void setProfiloPresenteInHR(String profiloPresenteInHR) {
		this.profiloPresenteInHR = profiloPresenteInHR;
	}
	public String getProfiloPresenteInCEA() {
		return profiloPresenteInCEA;
	}
	public void setProfiloPresenteInCEA(String profiloPresenteInCEA) {
		this.profiloPresenteInCEA = profiloPresenteInCEA;
	}
	public String getRoleGroup() {
		return roleGroup;
	}
	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}
	public String getDescrizioneAmbito() {
		return descrizioneAmbito;
	}
	public void setDescrizioneAmbito(String descrizioneAmbito) {
		this.descrizioneAmbito = descrizioneAmbito;
	}
	public String getProfiloCatUfficioAssegnazione() {
		return profiloCatUfficioAssegnazione;
	}
	public void setProfiloCatUfficioAssegnazione(
			String profiloCatUfficioAssegnazione) {
		this.profiloCatUfficioAssegnazione = profiloCatUfficioAssegnazione;
	}
	public String getDescrizioneProfilo() {
		return descrizioneProfilo;
	}
	public void setDescrizioneProfilo(String descrizioneProfilo) {
		this.descrizioneProfilo = descrizioneProfilo;
	}
	public int getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(int idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	
	
}
