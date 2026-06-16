package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.struts.upload.FormFile;

public class IterBean extends BaseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9187171859323912336L;
	
	private String id;
	private String codApplicazione; 
	
	private AutorizzatoreBean autorizzatore_I_Liv;
	private AutorizzatoreBean autorizzatore_II_Liv;
	
	
	private String autorizzatorePrimoLivello;
	private String autorizzatoreSecondoLivello;
	private boolean respinta;
	private List<ProfiloBean> profiloList;
	private Set<String> file;
	private Set<FormFile> myFormFiles; 
	
	private String nota = "";
	
	private String descrizione;
	private String codiceAmbito;
	private String ambitoDesc;
	private int motivoScarto;
	private String notaDescrittiva;
	private List<StrutturaIterBean> percorsiAutorizzativi;
	
//	per attivite' amministratori
	private List<StrutturaIterBean> percorsiAutorizzativiChiusi;
	
	private Date dataInizioVal;
	private Date dataFineVal;
	private Integer idAuditInizio;
	private Integer idAuditfine;
	private String cfInizio;
	private String cfFine;
	/**
	 * Caso GESTORE OPERATORI: considerare anche i campi:
	 * STRUTTURA_GESTORE_OPERATORI, CDR_GESTORE_OPERATORI, CF_GESTORE_OPERATORI
	 */
	private GestoreOperatoriBean gestoreOperatori;
	
	public GestoreOperatoriBean getGestoreOperatori() {
		return gestoreOperatori;
	}
	public void setGestoreOperatori(GestoreOperatoriBean gestoreOperatori) {
		this.gestoreOperatori = gestoreOperatori;
	}
	/**
	 * per distinguere se iter ha solo autorizzazione I liv o no
	 * mi serve per sapere se la richiesta potrebbe nascere già autorizzata
	 */
	private boolean iterHaSoloAutorizzatore_I_Liv;
	
	/**
	 * se la richiesta potrebbe nascere già autorizzata
	 */
	private boolean richiestaNasceGiaAutorizzata;
	
	
	public boolean isRichiestaNasceGiaAutorizzata() {
		return richiestaNasceGiaAutorizzata;
	}
	public void setRichiestaNasceGiaAutorizzata(boolean richiestaNasceGiaAutorizzata) {
		this.richiestaNasceGiaAutorizzata = richiestaNasceGiaAutorizzata;
	}
	public boolean isIterHaSoloAutorizzatore_I_Liv() {
		return iterHaSoloAutorizzatore_I_Liv;
	}
	/* QUANDO DEVE ESSERE VALORIZZATO ????? */
	public void setIterHaSoloAutorizzatore_I_Liv(
			boolean iterHaSoloAutorizzatore_I_Liv) {
		this.iterHaSoloAutorizzatore_I_Liv = iterHaSoloAutorizzatore_I_Liv;
	}
	public AutorizzatoreBean getAutorizzatore_I_Liv() {
		return autorizzatore_I_Liv;
	}
	public void setAutorizzatore_I_Liv(AutorizzatoreBean autorizzatore_I_Liv) {
		this.autorizzatore_I_Liv = autorizzatore_I_Liv;
	}
	public AutorizzatoreBean getAutorizzatore_II_Liv() {
		return autorizzatore_II_Liv;
	}
	public void setAutorizzatore_II_Liv(AutorizzatoreBean autorizzatore_II_Liv) {
		this.autorizzatore_II_Liv = autorizzatore_II_Liv;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAutorizzatorePrimoLivello() {
		return autorizzatorePrimoLivello;
	}
	public void setAutorizzatorePrimoLivello(String autorizzatorePrimoLivello) {
		this.autorizzatorePrimoLivello = autorizzatorePrimoLivello;
	}
	public String getAutorizzatoreSecondoLivello() {
		return autorizzatoreSecondoLivello;
	}
	public void setAutorizzatoreSecondoLivello(String autorizzatoreSecondoLivello) {
		this.autorizzatoreSecondoLivello = autorizzatoreSecondoLivello;
	}
	public boolean isRespinta() {
		return respinta;
	}
	public void setRespinta(boolean respinta) {
		this.respinta = respinta;
	}
	public List<ProfiloBean> getProfiloList() {
		return profiloList;
	}
	public void setProfiloList(List<ProfiloBean> profiloList) {
		this.profiloList = profiloList;
	}
	public Set<String> getFile() {
		return file;
	}
	public void setFile(Set<String> file) {
		this.file = file;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	public Set<FormFile> getMyFormFiles() {
		return myFormFiles;
	}
	public void setMyFormFiles(Set<FormFile> myFormFiles) {
		this.myFormFiles = myFormFiles;
	}
	public String getCodApplicazione() {
		return codApplicazione;
	}
	public void setCodApplicazione(String codApplicazione) {
		this.codApplicazione = codApplicazione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	
	public String getMotivoScarto() {
		String motivoDesc = "";
		switch (motivoScarto) {
		case 1:
			motivoDesc = "non e' stato trovato l'Autorizzatore di I livello previsto per l'iter autorizzativo <i>" + this.getDescrizione() + "</i> (id = "
					+ this.getId() + ")";
			break;
		case 2:
			motivoDesc = "non e' stato trovato l'Autorizzatore di II livello previsto per l'iter autorizzativo <i>" + this.getDescrizione() + "</i> (id = "
					+ this.getId() + ")";
			break;
		case 3:
			motivoDesc = "non sono stati trovati i gestori operatori previsti per l'iter autorizzativo <i>" + this.getDescrizione() + "</i> (id = "
					+ this.getId() + ")";
			break;
		default:
			motivoDesc = "";
			break;
		}
		return motivoDesc;
	}

	public void setMotivoScarto(int motivoScarto) {
		this.motivoScarto = motivoScarto;
	}
	public String getAmbitoDesc() {
		return ambitoDesc;
	}
	public void setAmbitoDesc(String ambitoDesc) {
		this.ambitoDesc = ambitoDesc;
	}
	public String getNotaDescrittiva() {
		return notaDescrittiva;
	}
	public void setNotaDescrittiva(String notaDescrittiva) {
		this.notaDescrittiva = notaDescrittiva;
	}
	public List<StrutturaIterBean> getPercorsiAutorizzativi() {
		return percorsiAutorizzativi;
	}
	public void setPercorsiAutorizzativi(
			List<StrutturaIterBean> percorsiAutorizzativi) {
		this.percorsiAutorizzativi = percorsiAutorizzativi;
	}
	public Date getDataInizioVal() {
		return dataInizioVal;
	}
	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	public Date getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	public Integer getIdAuditInizio() {
		return idAuditInizio;
	}
	public void setIdAuditInizio(Integer idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}
	public Integer getIdAuditfine() {
		return idAuditfine;
	}
	public void setIdAuditfine(Integer idAuditfine) {
		this.idAuditfine = idAuditfine;
	}
	public String getCfInizio() {
		return cfInizio;
	}
	public void setCfInizio(String cfInizio) {
		this.cfInizio = cfInizio;
	}
	public String getCfFine() {
		return cfFine;
	}
	public void setCfFine(String cfFine) {
		this.cfFine = cfFine;
	}
	public List<StrutturaIterBean> getPercorsiAutorizzativiChiusi() {
		return percorsiAutorizzativiChiusi;
	}
	public void setPercorsiAutorizzativiChiusi(
			List<StrutturaIterBean> percorsiAutorizzativiChiusi) {
		this.percorsiAutorizzativiChiusi = percorsiAutorizzativiChiusi;
	}
	
	
	

}
