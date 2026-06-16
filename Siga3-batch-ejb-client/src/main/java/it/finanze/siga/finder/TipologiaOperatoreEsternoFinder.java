package it.finanze.siga.finder;

import java.util.List;

import it.finanze.siga.util.tree.ProfiloTreeBean;

public class TipologiaOperatoreEsternoFinder extends BasePaginateFinder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2817578535904154877L;
	
	private String cfACApplicativo;
	private int codiceAmbito;
	private String codice;
	private String descrizione;
	private String codiceRg;
	private String codiceProfilo;
	private List<ProfiloTreeBean> listaAbilitazioni;
	private boolean modificaDati;
	private String cfOpEsterno;
	
	private String cfAmministratore;
	private int idAudit;
	private boolean inserimento;
	
	private AuditFinder audit;
	
	public String getCfACApplicativo() {
		return cfACApplicativo;
	}
	public void setCfACApplicativo(String cfACApplicativo) {
		this.cfACApplicativo = cfACApplicativo;
	}
	public int getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(int codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCodiceRg() {
		return codiceRg;
	}
	public void setCodiceRg(String codiceRg) {
		this.codiceRg = codiceRg;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public List<ProfiloTreeBean> getListaAbilitazioni() {
		return listaAbilitazioni;
	}
	public void setListaAbilitazioni(List<ProfiloTreeBean> listaAbilitazioni) {
		this.listaAbilitazioni = listaAbilitazioni;
	}
	public boolean isModificaDati() {
		return modificaDati;
	}
	public void setModificaDati(boolean modificaDati) {
		this.modificaDati = modificaDati;
	}
	public String getCfOpEsterno() {
		return cfOpEsterno;
	}
	public void setCfOpEsterno(String cfOpEsterno) {
		this.cfOpEsterno = cfOpEsterno;
	}
	public AuditFinder getAudit() {
		return audit;
	}
	public void setAudit(AuditFinder audit) {
		this.audit = audit;
	}
	public String getCfAmministratore() {
		return cfAmministratore;
	}
	public void setCfAmministratore(String cfAmministratore) {
		this.cfAmministratore = cfAmministratore;
	}
	public int getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(int idAudit) {
		this.idAudit = idAudit;
	}
	public boolean isInserimento() {
		return inserimento;
	}
	public void setInserimento(boolean inserimento) {
		this.inserimento = inserimento;
	}
	
	
}
