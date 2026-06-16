package it.finanze.siga.finder;

import it.finanze.siga.bean.IterBean;
import it.finanze.siga.bean.StrutturaIterBean;

public class IterAbilitazioniFinder extends BasePaginateFinder{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7107012834185664497L;
	
	private String cfACApplicativo;
	private int idIter;
	private int codiceAmbito;
//	private String tipoUfficioRichiedente;
//	private String tipoUfficioAutorizzatoreI;
	private IterBean iter;
	private String cfAmministratore;
	private int idAudit;
	
	private AuditFinder audit;
	
	private boolean isAbilitazioniModificate;
	private boolean isNotaModificata;
	private boolean isDescrizioneModificata;
	private boolean aggiornamento;
	private String tipoUfficioSelezionato;
	
	private boolean updateCostanteBatch;
	
	private StrutturaIterBean strutturaDaInserire;
	private StrutturaIterBean strutturaDaEliminare;
	
//	per attivit degli amministartori
	boolean inserimento;
	boolean attivitaAmm;

	public String getCfACApplicativo() {
		return cfACApplicativo;
	}

	public void setCfACApplicativo(String cfACApplicativo) {
		this.cfACApplicativo = cfACApplicativo;
	}

	public int getIdIter() {
		return idIter;
	}

	public void setIdIter(int idIter) {
		this.idIter = idIter;
	}

	public int getCodiceAmbito() {
		return codiceAmbito;
	}

	public void setCodiceAmbito(int codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}


	public IterBean getIter() {
		return iter;
	}

	public void setIter(IterBean iter) {
		this.iter = iter;
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

	public boolean isAbilitazioniModificate() {
		return isAbilitazioniModificate;
	}

	public void setAbilitazioniModificate(boolean isAbilitazioniModificate) {
		this.isAbilitazioniModificate = isAbilitazioniModificate;
	}

	public boolean isNotaModificata() {
		return isNotaModificata;
	}

	public void setNotaModificata(boolean isNotaModificata) {
		this.isNotaModificata = isNotaModificata;
	}

	public boolean isDescrizioneModificata() {
		return isDescrizioneModificata;
	}

	public void setDescrizioneModificata(boolean isDescrizioneModificata) {
		this.isDescrizioneModificata = isDescrizioneModificata;
	}

	public String getTipoUfficioSelezionato() {
		return tipoUfficioSelezionato;
	}

	public void setTipoUfficioSelezionato(String tipoUfficioSelezionato) {
		this.tipoUfficioSelezionato = tipoUfficioSelezionato;
	}

	public boolean isAggiornamento() {
		return aggiornamento;
	}

	public void setAggiornamento(boolean aggiornamento) {
		this.aggiornamento = aggiornamento;
	}

	public boolean isUpdateCostanteBatch() {
		return updateCostanteBatch;
	}

	public void setUpdateCostanteBatch(boolean updateCostanteBatch) {
		this.updateCostanteBatch = updateCostanteBatch;
	}

	public StrutturaIterBean getStrutturaDaInserire() {
		return strutturaDaInserire;
	}

	public void setStrutturaDaInserire(StrutturaIterBean strutturaDaInserire) {
		this.strutturaDaInserire = strutturaDaInserire;
	}

	public StrutturaIterBean getStrutturaDaEliminare() {
		return strutturaDaEliminare;
	}

	public void setStrutturaDaEliminare(StrutturaIterBean strutturaDaEliminare) {
		this.strutturaDaEliminare = strutturaDaEliminare;
	}

	public AuditFinder getAudit() {
		return audit;
	}

	public void setAudit(AuditFinder audit) {
		this.audit = audit;
	}

	public boolean isInserimento() {
		return inserimento;
	}

	public void setInserimento(boolean inserimento) {
		this.inserimento = inserimento;
	}

	public boolean isAttivitaAmm() {
		return attivitaAmm;
	}

	public void setAttivitaAmm(boolean attivitaAmm) {
		this.attivitaAmm = attivitaAmm;
	}

	
	
}
