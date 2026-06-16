package it.finanze.siga.finder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComunicazioniFinder extends BasePaginateFinder{


	private static final long serialVersionUID = 2058228248073478283L;
	private Integer idMessaggio;
	private Integer idMessaggioAggiornato;
	private String codiceFiscaleUtente;
	private String titoloComunicazione;
	private String corpoComunicazione;
	private Date dataDa;
	private Date dataA;
	private String cfRedattore;
	private String cfRedattoreUpdate;
	private byte[] fileAllegato;
	private String isRichiedente;
	private String isAurotizzatore;
	private String isResponsabile;
	private String isAmministratore;
	private String isOperatore;
	private String isDivisioniDc;
	private String isCop;
	private String isCam;
	private String isDr;
	private String isDp;
	private String tutteLeTipologie;
	private String nomeFile;
	private List<String> codTipologieList = new ArrayList<String>();
	private String email;
	private String idComunicazione;
	private String regione;
	private String provincia;
	
	private String cdrDc;
	private String cdrDp;
	private String cdrDr;
	private String cdrCam;
	private String cdrCop;
	
	private String cdrSel;
	private String descrizione;
	private String tipologia;
	
	private String isUtenteDivisioniDc;
	private String isUtenteCop;
	private String isUtenteCam;
	private String isUtenteDr;
	private String isUtenteDp;
	
	// 2.2 2023 stato comunicazione
	private String stato;
	
	public Integer getIdMessaggio() {
		return idMessaggio;
	}
	public void setIdMessaggio(Integer idMessaggio) {
		this.idMessaggio = idMessaggio;
	}
	public String getCodiceFiscaleUtente() {
		return codiceFiscaleUtente;
	}
	public void setCodiceFiscaleUtente(String codiceFiscaleUtente) {
		this.codiceFiscaleUtente = codiceFiscaleUtente;
	}
	public String getTitoloComunicazione() {
		return titoloComunicazione;
	}
	public void setTitoloComunicazione(String titoloComunicazione) {
		this.titoloComunicazione = titoloComunicazione;
	}
	public String getCorpoComunicazione() {
		return corpoComunicazione;
	}
	public void setCorpoComunicazione(String corpoComunicazione) {
		this.corpoComunicazione = corpoComunicazione;
	}
	public Date getDataDa() {
		return dataDa;
	}
	public void setDataDa(Date dataDa) {
		this.dataDa = dataDa;
	}
	public Date getDataA() {
		return dataA;
	}
	public void setDataA(Date dataA) {
		this.dataA = dataA;
	}
	public String getCfRedattore() {
		return cfRedattore;
	}
	public void setCfRedattore(String cfRedattore) {
		this.cfRedattore = cfRedattore;
	}
	public byte[] getFileAllegato() {
		return fileAllegato;
	}
	public void setFileAllegato(byte[] fileAllegato) {
		this.fileAllegato = fileAllegato;
	}
	public String getIsRichiedente() {
		return isRichiedente;
	}
	public void setIsRichiedente(String isRichiedente) {
		this.isRichiedente = isRichiedente;
	}
	public String getIsAurotizzatore() {
		return isAurotizzatore;
	}
	public void setIsAurotizzatore(String isAurotizzatore) {
		this.isAurotizzatore = isAurotizzatore;
	}
	public String getIsResponsabile() {
		return isResponsabile;
	}
	public void setIsResponsabile(String isResponsabile) {
		this.isResponsabile = isResponsabile;
	}
	public String getIsAmministratore() {
		return isAmministratore;
	}
	public void setIsAmministratore(String isAmministratore) {
		this.isAmministratore = isAmministratore;
	}
	public String getIsOperatore() {
		return isOperatore;
	}
	public void setIsOperatore(String isOperatore) {
		this.isOperatore = isOperatore;
	}
	public String getIsDivisioniDc() {
		return isDivisioniDc;
	}
	public void setIsDivisioniDc(String isDivisioniDc) {
		this.isDivisioniDc = isDivisioniDc;
	}
	public String getIsCop() {
		return isCop;
	}
	public void setIsCop(String isCop) {
		this.isCop = isCop;
	}
	public String getIsCam() {
		return isCam;
	}
	public void setIsCam(String isCam) {
		this.isCam = isCam;
	}
	public String getIsDr() {
		return isDr;
	}
	public void setIsDr(String isDr) {
		this.isDr = isDr;
	}
	public String getIsDp() {
		return isDp;
	}
	public void setIsDp(String isDp) {
		this.isDp = isDp;
	}
	public String getTutteLeTipologie() {
		return tutteLeTipologie;
	}
	public void setTutteLeTipologie(String tutteLeTipologie) {
		this.tutteLeTipologie = tutteLeTipologie;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public List<String> getCodTipologieList() {
		return codTipologieList;
	}
	public void setCodTipologieList(List<String> codTipologieList) {
		this.codTipologieList = codTipologieList;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdComunicazione() {
		return idComunicazione;
	}
	public void setIdComunicazione(String idComunicazione) {
		this.idComunicazione = idComunicazione;
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
	public String getCdrDc() {
		return cdrDc;
	}
	public void setCdrDc(String cdrDc) {
		this.cdrDc = cdrDc;
	}
	public String getCdrDp() {
		return cdrDp;
	}
	public void setCdrDp(String cdrDp) {
		this.cdrDp = cdrDp;
	}
	public String getCdrDr() {
		return cdrDr;
	}
	public void setCdrDr(String cdrDr) {
		this.cdrDr = cdrDr;
	}
	public String getCdrCam() {
		return cdrCam;
	}
	public void setCdrCam(String cdrCam) {
		this.cdrCam = cdrCam;
	}
	public String getCdrCop() {
		return cdrCop;
	}
	public void setCdrCop(String cdrCop) {
		this.cdrCop = cdrCop;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	public String getCdrSel() {
		return cdrSel;
	}
	public void setCdrSel(String cdrSel) {
		this.cdrSel = cdrSel;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String getIsUtenteDivisioniDc() {
		return isUtenteDivisioniDc;
	}
	public void setIsUtenteDivisioniDc(String isUtenteDivisioniDc) {
		this.isUtenteDivisioniDc = isUtenteDivisioniDc;
	}
	public String getIsUtenteCop() {
		return isUtenteCop;
	}
	public void setIsUtenteCop(String isUtenteCop) {
		this.isUtenteCop = isUtenteCop;
	}
	public String getIsUtenteCam() {
		return isUtenteCam;
	}
	public void setIsUtenteCam(String isUtenteCam) {
		this.isUtenteCam = isUtenteCam;
	}
	public String getIsUtenteDr() {
		return isUtenteDr;
	}
	public void setIsUtenteDr(String isUtenteDr) {
		this.isUtenteDr = isUtenteDr;
	}
	public String getIsUtenteDp() {
		return isUtenteDp;
	}
	public void setIsUtenteDp(String isUtenteDp) {
		this.isUtenteDp = isUtenteDp;
	}
	public Integer getIdMessaggioAggiornato() {
		return idMessaggioAggiornato;
	}
	public void setIdMessaggioAggiornato(Integer idMessaggioAggiornato) {
		this.idMessaggioAggiornato = idMessaggioAggiornato;
	}
	public String getCfRedattoreUpdate() {
		return cfRedattoreUpdate;
	}
	public void setCfRedattoreUpdate(String cfRedattoreUpdate) {
		this.cfRedattoreUpdate = cfRedattoreUpdate;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
}