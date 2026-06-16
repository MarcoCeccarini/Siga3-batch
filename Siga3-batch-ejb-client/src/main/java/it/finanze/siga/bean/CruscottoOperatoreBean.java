package it.finanze.siga.bean;

import it.finanze.siga.util.bean.UtenteBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CruscottoOperatoreBean implements Serializable{

	private static final long serialVersionUID = -3433772263988015688L;
	private String codiceFiscale;
	private String cognome;
	private String nome;
	private String cdrAppartenenza;
	private String descrCdrAppartenenza;
	private String codiceStruttura;
	private String codiceUfficio;
	private String tipoUtente;
	private List<String> cdrVisibilita;
	private String descCdrVisibilita;
	private int numeroAbilitazioni;
	private int numeroCasellePostaElettr;
	private int numeroCartelleNas;
	private int numeroActiveDirectory;
	private Date dataFineVal;
	private String dataInserimentoCartellaNas;
	private String nomeCasella;
	private String indirizzoCasella;
	private String nomeServer;
	private String percorsoServer;
	private String nomeCartellaServer;
	private String owa;
	private String activeSync;
	private String navigazioneInternet;
	private String internetVip;
	private String gesNas;
	private String gesVoip;
	private String vpnExtranet;
	
	public String getOwa() {
		return owa;
	}
	public void setOwa(String owa) {
		this.owa = owa;
	}
	public String getActiveSync() {
		return activeSync;
	}
	public void setActiveSync(String activeSync) {
		this.activeSync = activeSync;
	}
	public String getNavigazioneInternet() {
		return navigazioneInternet;
	}
	public void setNavigazioneInternet(String navigazioneInternet) {
		this.navigazioneInternet = navigazioneInternet;
	}
	public String getInternetVip() {
		return internetVip;
	}
	public void setInternetVip(String internetVip) {
		this.internetVip = internetVip;
	}
	public String getGesNas() {
		return gesNas;
	}
	public void setGesNas(String gesNas) {
		this.gesNas = gesNas;
	}
	public String getGesVoip() {
		return gesVoip;
	}
	public void setGesVoip(String gesVoip) {
		this.gesVoip = gesVoip;
	}
	public String getVpnExtranet() {
		return vpnExtranet;
	}
	public void setVpnExtranet(String vpnExtranet) {
		this.vpnExtranet = vpnExtranet;
	}
		//campo per inserire utente "Richiedente" che fa  richiesta del report in excel del Cruscotto Monitoraggio
		private UtenteBean utente;
	
	
	
	public String getNomeServer() {
			return nomeServer;
		}
		public void setNomeServer(String nomeServer) {
			this.nomeServer = nomeServer;
		}
		public String getPercorsoServer() {
			return percorsoServer;
		}
		public void setPercorsoServer(String percorsoServer) {
			this.percorsoServer = percorsoServer;
		}
		public String getNomeCartellaServer() {
			return nomeCartellaServer;
		}
		public void setNomeCartellaServer(String nomeCartellaServer) {
			this.nomeCartellaServer = nomeCartellaServer;
		}
	public UtenteBean getUtente() {
			return utente;
		}
		public void setUtente(UtenteBean utente) {
			this.utente = utente;
		}
	public String getNomeCasella() {
			return nomeCasella;
		}
		public void setNomeCasella(String nomeCasella) {
			this.nomeCasella = nomeCasella;
		}
		public String getIndirizzoCasella() {
			return indirizzoCasella;
		}
		public void setIndirizzoCasella(String indirizzoCasella) {
			this.indirizzoCasella = indirizzoCasella;
		}
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
	public String getCdrAppartenenza() {
		return cdrAppartenenza;
	}
	public void setCdrAppartenenza(String cdrAppartenenza) {
		this.cdrAppartenenza = cdrAppartenenza;
	}
	public String getDescrCdrAppartenenza() {
		return descrCdrAppartenenza;
	}
	public void setDescrCdrAppartenenza(String descrCdrAppartenenza) {
		this.descrCdrAppartenenza = descrCdrAppartenenza;
	}
	public String getCodiceStruttura() {
		return codiceStruttura;
	}
	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getTipoUtente() {
		return tipoUtente;
	}
	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}
	public List<String> getCdrVisibilita() {
		return cdrVisibilita;
	}
	public void setCdrVisibilita(List<String> cdrVisibilita) {
		this.cdrVisibilita = cdrVisibilita;
	}
	public String getDescCdrVisibilita() {
		return descCdrVisibilita;
	}
	public void setDescCdrVisibilita(String descCdrVisibilita) {
		this.descCdrVisibilita = descCdrVisibilita;
	}
	public int getNumeroAbilitazioni() {
		return numeroAbilitazioni;
	}
	public void setNumeroAbilitazioni(int numeroAbilitazioni) {
		this.numeroAbilitazioni = numeroAbilitazioni;
	}
	public int getNumeroCasellePostaElettr() {
		return numeroCasellePostaElettr;
	}
	public void setNumeroCasellePostaElettr(int numeroCasellePostaElettr) {
		this.numeroCasellePostaElettr = numeroCasellePostaElettr;
	}
	public int getNumeroCartelleNas() {
		return numeroCartelleNas;
	}
	public void setNumeroCartelleNas(int numeroCartelleNas) {
		this.numeroCartelleNas = numeroCartelleNas;
	}
	public int getNumeroActiveDirectory() {
		return numeroActiveDirectory;
	}
	public void setNumeroActiveDirectory(int numeroActiveDirectory) {
		this.numeroActiveDirectory = numeroActiveDirectory;
	}
	public Date getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	public String getDataInserimentoCartellaNas() {
		return dataInserimentoCartellaNas;
	}
	public void setDataInserimentoCartellaNas(String dataInserimentoCartellaNas) {
		this.dataInserimentoCartellaNas = dataInserimentoCartellaNas;
	}

}
