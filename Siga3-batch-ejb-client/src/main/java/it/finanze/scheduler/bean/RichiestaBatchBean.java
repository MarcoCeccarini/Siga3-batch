package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class RichiestaBatchBean implements Serializable{
	
	 
	private static final long serialVersionUID = 1L;
	private String cf;
	private Integer idRichiestaGenerata;
	private String evento;
	private Date dataCreazione;
	private Date dataEvento;
	private String cdrVisibilita;
	private String cdrVecchio;
	private String cdrNuovo;
	private String codRoleGroup;
	private String codProfilo;
	private Date inizioProfilo;
	private String codUfficio;
	private String codCdr;
	private String idRichiestaProfAttiviUte;
	private String azione;
	private long sequence;
	private Integer idRichiestaVisibilita;
	private String cancUteEsterno;
	private String tipoUtente;
	private String priorita;
	private String autI;
	private String autII;
	private String autIEff;
	private String autIIEff;
	private String richiedenteAC;
    private String profiliCancellatiCau;
    private Date dataScadenza;
    //private String dataScadenza;

	
	public String getPriorita() {
		return priorita;
	}
	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
 	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public Date getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public Date getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
	public String getCdrVisibilita() {
		return cdrVisibilita;
	}
	public void setCdrVisibilita(String cdrVisibilita) {
		this.cdrVisibilita = cdrVisibilita;
	}
	public String getCdrVecchio() {
		return cdrVecchio;
	}
	public void setCdrVecchio(String cdrVecchio) {
		this.cdrVecchio = cdrVecchio;
	}
	public String getCdrNuovo() {
		return cdrNuovo;
	}
	public void setCdrNuovo(String cdrNuovo) {
		this.cdrNuovo = cdrNuovo;
	}
	public String getCodRoleGroup() {
		return codRoleGroup;
	}
	public void setCodRoleGroup(String codRoleGroup) {
		this.codRoleGroup = codRoleGroup;
	}
	public String getCodProfilo() {
		return codProfilo;
	}
	public void setCodProfilo(String codProfilo) {
		this.codProfilo = codProfilo;
	}
	public Date getInizioProfilo() {
		return inizioProfilo;
	}
	public void setInizioProfilo(Date inizioProfilo) {
		this.inizioProfilo = inizioProfilo;
	}
	public String getCodUfficio() {
		return codUfficio;
	}
	public void setCodUfficio(String codUfficio) {
		this.codUfficio = codUfficio;
	}
	public String getCodCdr() {
		return codCdr;
	}
	public void setCodCdr(String codCdr) {
		this.codCdr = codCdr;
	}
	public String getIdRichiestaProfAttiviUte() {
		return idRichiestaProfAttiviUte;
	}
	public void setIdRichiestaProfAttiviUte(String idRichiestaProfAttiviUte) {
		this.idRichiestaProfAttiviUte = idRichiestaProfAttiviUte;
	}
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	public Integer getIdRichiestaGenerata() {
		return idRichiestaGenerata;
	}
	public void setIdRichiestaGenerata(Integer idRichiestaGenerata) {
		this.idRichiestaGenerata = idRichiestaGenerata;
	}
	public Integer getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(Integer idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public String getCancUteEsterno() {
		return cancUteEsterno;
	}
	public void setCancUteEsterno(String cancUteEsterno) {
		this.cancUteEsterno = cancUteEsterno;
	}
	public String getTipoUtente() {
		return tipoUtente;
	}
	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}
	public String getAutI() {
		return autI;
	}
	public void setAutI(String autI) {
		this.autI = autI;
	}
	public String getAutII() {
		return autII;
	}
	public void setAutII(String autII) {
		this.autII = autII;
	}
	public String getRichiedenteAC() {
		return richiedenteAC;
	}
	public void setRichiedenteAC(String richiedenteAC) {
		this.richiedenteAC = richiedenteAC;
	}
	public String getProfiliCancellatiCau() {
		return profiliCancellatiCau;
	}
	public void setProfiliCancellatiCau(String profiliCancellatiCau) {
		this.profiliCancellatiCau = profiliCancellatiCau;
	}
	public String getAutIEff() {
		return autIEff;
	}
	public String getAutIIEff() {
		return autIIEff;
	}
	public void setAutIEff(String autIEff) {
		this.autIEff = autIEff;
	}
	public void setAutIIEff(String autIIEff) {
		this.autIIEff = autIIEff;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
		
}