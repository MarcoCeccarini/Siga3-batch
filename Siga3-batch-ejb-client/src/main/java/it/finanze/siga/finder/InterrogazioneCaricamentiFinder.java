package it.finanze.siga.finder;

import java.util.Date;

public class InterrogazioneCaricamentiFinder extends BasePaginateFinder{

	private static final long serialVersionUID = 8851664918813281762L;
	private String codiceFiscaleUtente;
	private String annoCaricamento;
	private String idCaricamento;
	private String idAllegato;
	private String tipoAllegato;
	private String statoCaricamento;
	private Integer idRichiesta;
	private Long sequence;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String codiceCdr;
	private String tipoUfficio;
	private String regione;
	private String provincia;
	private boolean caricamentoVisibilita;
	private String azione;
	private String codiceUfficio;
	
	private Date dataScadenza;
	private Date dataFineVal;
//	private String dataScadenza;
	
	private int idAudit;
	
	public String getCodiceFiscaleUtente() {
		return codiceFiscaleUtente;
	}
	public void setCodiceFiscaleUtente(String codiceFiscaleUtente) {
		this.codiceFiscaleUtente = codiceFiscaleUtente;
	}
	public String getAnnoCaricamento() {
		return annoCaricamento;
	}
	public void setAnnoCaricamento(String annoCaricamento) {
		this.annoCaricamento = annoCaricamento;
	}
	public String getIdCaricamento() {
		return idCaricamento;
	}
	public void setIdCaricamento(String idCaricamento) {
		this.idCaricamento = idCaricamento;
	}
	public String getIdAllegato() {
		return idAllegato;
	}
	public void setIdAllegato(String idAllegato) {
		this.idAllegato = idAllegato;
	}
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	public Integer getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(Integer idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	public String getTipoAllegato() {
		return tipoAllegato;
	}
	public void setTipoAllegato(String tipoAllegato) {
		this.tipoAllegato = tipoAllegato;
	}
	public String getStatoCaricamento() {
		return statoCaricamento;
	}
	public void setStatoCaricamento(String statoCaricamento) {
		this.statoCaricamento = statoCaricamento;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public int getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(int idAudit) {
		this.idAudit = idAudit;
	}
	public boolean isCaricamentoVisibilita() {
		return caricamentoVisibilita;
	}
	public void setCaricamentoVisibilita(boolean caricamentoVisibilita) {
		this.caricamentoVisibilita = caricamentoVisibilita;
	}
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getTipoUfficio() {
		return tipoUfficio;
	}
	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	
	public Date getDataFineVal() {
		return dataFineVal = dataFineVal;
	}
	public void setDataFineVal(Date dataScadenza) {
		this.dataFineVal = dataFineVal;
	}
	
	
	
}
