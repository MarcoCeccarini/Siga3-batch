package it.finanze.siga.finder;

import it.finanze.siga.util.finder.PaginateFinder;

import java.util.Date;
import java.util.List;

public class RichiestaFinder extends PaginateFinder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -464424815076003758L;
	
	
	public RichiestaFinder(int id){
		this.idRichiesta=id;	
	}
	
	public RichiestaFinder() {
		// TODO Auto-generated constructor stub
	}

	public String getOptRichiesta() {
		return optRichiesta;
	}
	public void setOptRichiesta(String optRichiesta) {
		this.optRichiesta = optRichiesta;
	}
	private Integer idRichiesta;
	private int cdrChiusi;
	private String codFisc;
	private List <String> listaRuoli;
	private List <String> elencoCDR;
	
	private Date dataRichiestaDal;
	private Date dataRichiestaAl;
	private String statoRichiesta;
	private String esitoRichiesta;

//	private String tipoRichiesta;
	private String cdr;
	
	private Date dataEseguitaDal;
	private Date dataEseguitaAl;
	
	private Date dataPresentataDal;
	private Date dataPresentataAl;
	
	private String operatore;
	
	private String richiedente;
	
	private String autLiv1;
	private String autLiv2;
	
	private String gestore;
 	private String archiviazione;
 	
	private String ambito;
	private String tipoRichiesta;
	
	private String codFiscOperatore;
	private String codFiscOperatoreDaEscludere;
	private List<String> codiceCDRList;
	
	private String evento;
	private String optRichiesta;
	private Integer tentativi;
	private Date dataElaborazione;
	private Integer intervallo;
	private long sequence;
	private Integer idRichiestaVisibilita;
	private List<String> origineRichiesta;
	private String origineRichiestaRichiedente;
	private List<String> codiceCDRListRegRicCdR;

	// 4.5.1 II -->
	private String ruoloScelto;
	//
	
	
	public List<String> getCodiceCDRListRegRicCdR() {
		return codiceCDRListRegRicCdR;
	}
	public String getRuoloScelto() {
		return ruoloScelto;
	}

	public void setRuoloScelto(String ruoloScelto) {
		this.ruoloScelto = ruoloScelto;
	}

	public void setCodiceCDRListRegRicCdR(List<String> codiceCDRListRegRicCdR) {
		this.codiceCDRListRegRicCdR = codiceCDRListRegRicCdR;
	}
	/**
	 * @return the gestore
	 */
	public String getGestore() {
		return gestore;
	}
	/**
	 * @param gestore the gestore to set
	 */
	public void setGestore(String gestore) {
		this.gestore = gestore;
	}
	/**
	 * @return the dataEseguitaDal
	 */
	public Date getDataEseguitaDal() {
		return dataEseguitaDal;
	}
	/**
	 * @param dataEseguitaDal the dataEseguitaDal to set
	 */
	public void setDataEseguitaDal(Date dataEseguitaDal) {
		this.dataEseguitaDal = dataEseguitaDal;
	}
	/**
	 * @return the dataEseguitaAl
	 */
	public Date getDataEseguitaAl() {
		return dataEseguitaAl;
	}
	/**
	 * @param dataEseguitaAl the dataEseguitaAl to set
	 */
	public void setDataEseguitaAl(Date dataEseguitaAl) {
		this.dataEseguitaAl = dataEseguitaAl;
	}
	/**
	 * @return the dataPresentataDal
	 */
	public Date getDataPresentataDal() {
		return dataPresentataDal;
	}
	/**
	 * @param dataPresentataDal the dataPresentataDal to set
	 */
	public void setDataPresentataDal(Date dataPresentataDal) {
		this.dataPresentataDal = dataPresentataDal;
	}
	/**
	 * @return the dataPresentataAl
	 */
	public Date getDataPresentataAl() {
		return dataPresentataAl;
	}
	/**
	 * @param dataPresentataAl the dataPresentataAl to set
	 */
	public void setDataPresentataAl(Date dataPresentataAl) {
		this.dataPresentataAl = dataPresentataAl;
	}
	/**
	 * @return the operatore
	 */
	public String getOperatore() {
		return operatore;
	}
	/**
	 * @param operatore the operatore to set
	 */
	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}
	/**
	 * @return the richiedente
	 */
	public String getRichiedente() {
		return richiedente;
	}
	/**
	 * @param richiedente the richiedente to set
	 */
	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}
	/**
	 * @return the autLiv1
	 */
	public String getAutLiv1() {
		return autLiv1;
	}
	/**
	 * @param autLiv1 the autLiv1 to set
	 */
	public void setAutLiv1(String autLiv1) {
		this.autLiv1 = autLiv1;
	}
	/**
	 * @return the autLiv2
	 */
	public String getAutLiv2() {
		return autLiv2;
	}
	/**
	 * @param autLiv2 the autLiv2 to set
	 */
	public void setAutLiv2(String autLiv2) {
		this.autLiv2 = autLiv2;
	}
	/**
	 * @return the ambito
	 */
	public String getAmbito() {
		return ambito;
	}
	/**
	 * @param ambito the ambito to set
	 */
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public String getTipoRichiesta() {
		return tipoRichiesta;
	}
    public void setTipoRichiesta(String tipoRichiesta) {
		this.tipoRichiesta = tipoRichiesta;
	}
	public String getCdr() {
		return cdr;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}
	public List<String> getElencoCDR() {
		return elencoCDR;
	}
	public void setElencoCDR(List<String> elencoCDR) {
		this.elencoCDR = elencoCDR;
	}
	public List<String> getListaRuoli() {
		return listaRuoli;
	}
	public void setListaRuoli(List<String> listaRuoli) {
		this.listaRuoli = listaRuoli;
	}
	public String getCodFisc() {
		return codFisc;
	}
	public void setCodFisc(String codFisc) {
		this.codFisc = codFisc;
	}
	public Integer getIdRichiesta() {
		return idRichiesta;
	}	
	public void setIdRichiesta(Integer idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	
	public int getCdrChiusi() {
		return cdrChiusi;
	}
	public void setCdrChiusi(int cdrChiusi) {
		this.cdrChiusi = cdrChiusi;
	}
	public Date getDataRichiestaDal() {
		return dataRichiestaDal;
	}
	public void setDataRichiestaDal(Date dataRichiestaDal) {
		this.dataRichiestaDal = dataRichiestaDal;
	}
	public Date getDataRichiestaAl() {
		return dataRichiestaAl;
	}
	public void setDataRichiestaAl(Date dataRichiestaAl) {
		this.dataRichiestaAl = dataRichiestaAl;
	}
	public String getStatoRichiesta() {
		return statoRichiesta;
	}
	public void setStatoRichiesta(String statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCodFiscOperatore() {
		return codFiscOperatore;
	}
	public void setCodFiscOperatore(String codFiscOperatore) {
		this.codFiscOperatore = codFiscOperatore;
	}
	public String getCodFiscOperatoreDaEscludere() {
		return codFiscOperatoreDaEscludere;
	}
	public void setCodFiscOperatoreDaEscludere(
			String codFiscOperatoreDaEscludere) {
		this.codFiscOperatoreDaEscludere = codFiscOperatoreDaEscludere;
	}
	public List<String> getCodiceCDRList() {
		return codiceCDRList;
	}
	public void setCodiceCDRList(List<String> codiceCDRList) {
		this.codiceCDRList = codiceCDRList;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getArchiviazione() {
		return archiviazione;
	}
	public void setArchiviazione(String archiviazione) {
		this.archiviazione = archiviazione;
	}
	public Date getDataElaborazione() {
		return dataElaborazione;
	}
	public void setDataElaborazione(Date dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}
	public Integer getTentativi() {
		return tentativi;
	}
	public void setTentativi(Integer tentativi) {
		this.tentativi = tentativi;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	public Integer getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(Integer idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public Integer getIntervallo() {
		return intervallo;
	}
	public void setIntervallo(Integer intervallo) {
		this.intervallo = intervallo;
	}
 
	public List<String> getOrigineRichiesta() {
		return origineRichiesta;
	}
	
	public void setOrigineRichiesta(List<String> origineRichiesta) {
		this.origineRichiesta = origineRichiesta;
	}

	public String getOrigineRichiestaRichiedente() {
		return origineRichiestaRichiedente;
	}
	public void setOrigineRichiestaRichiedente(String origineRichiestaRichiedente) {
		this.origineRichiestaRichiedente = origineRichiestaRichiedente;
	}
	public String getEsitoRichiesta() {
		return esitoRichiesta;
	}
	public void setEsitoRichiesta(String esitoRichiesta) {
		this.esitoRichiesta = esitoRichiesta;
	}

	
	
}
