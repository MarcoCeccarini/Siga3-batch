package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AttivitaBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9214092899874783555L;

	private String tabAggiornata,
				   testoAudit,
				   ruoloAmministratore,
				   descrizioneDettaglio,
				   cfAmministratore,
				   cdrRuolo,
				   cdrRuoloDesc,
				   attivitaDesc;
	
	private Date dataAttivita;
	
	private int idAudit;
	
	private List<Object> dettagli;
	private List<Object> dettagli2;
	private List<StrutturaIterBean>	percorsi,
						 			percorsiChiusi;
	private List<ProfiloBean>	abilitazioni;
	
	public String getTabAggiornata() {
		return tabAggiornata;
	}

	public void setTabAggiornata(String tabAggiornata) {
		this.tabAggiornata = tabAggiornata;
	}

	public String getTestoAudit() {
		return testoAudit;
	}

	public void setTestoAudit(String testoAudit) {
		this.testoAudit = testoAudit;
	}

	public String getDescrizioneDettaglio() {
		return descrizioneDettaglio;
	}

	public void setDescrizioneDettaglio(String descrizioneDettaglio) {
		this.descrizioneDettaglio = descrizioneDettaglio;
	}

	public String getRuoloAmministratore() {
		String output = null;
		if(ruoloAmministratore.equals("AMMINISTRATORE_CENTRALE"))
			output = "Amministratore centrale";
		else if(ruoloAmministratore.equals("AMMINISTRATORE_REGIONALE"))
			output = "Amministratore regionale";
		if(ruoloAmministratore.equals("AMMINISTRATORE_LOCALE"))
			output = "Amministratore locale";
		return output;
	}

	public void setRuoloAmministratore(String ruoloAmministratore) {
		this.ruoloAmministratore = ruoloAmministratore;
	}

	public Date getDataAttivita() {
		return dataAttivita;
	}

	public void setDataAttivita(Date dataAttivita) {
		this.dataAttivita = dataAttivita;
	}

	public String getCfAmministratore() {
		return cfAmministratore;
	}

	public void setCfAmministratore(String cfAmministratore) {
		this.cfAmministratore = cfAmministratore;
	}

	public String getCdrRuolo() {
		return cdrRuolo;
	}

	public void setCdrRuolo(String cdrRuolo) {
		this.cdrRuolo = cdrRuolo;
	}

	public String getCdrRuoloDesc() {
		return cdrRuoloDesc != null ? cdrRuoloDesc : "";
	}

	public void setCdrRuoloDesc(String cdrRuoloDesc) {
		this.cdrRuoloDesc = cdrRuoloDesc;
	}

	public int getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(int idAudit) {
		this.idAudit = idAudit;
	}

	public String getAttivitaDesc() {
		return attivitaDesc;
	}

	public void setAttivitaDesc(String attivitaDesc) {
		this.attivitaDesc = attivitaDesc;
	}

	public List<Object> getDettagli() {
		return dettagli;
	}

	public void setDettagli(List<Object> dettagli) {
		this.dettagli = dettagli;
	}

	public List<StrutturaIterBean> getPercorsi() {
		return percorsi;
	}

	public void setPercorsi(List<StrutturaIterBean> percorsi) {
		this.percorsi = percorsi;
	}

	public List<StrutturaIterBean> getPercorsiChiusi() {
		return percorsiChiusi;
	}

	public void setPercorsiChiusi(List<StrutturaIterBean> percorsiChiusi) {
		this.percorsiChiusi = percorsiChiusi;
	}

	public List<ProfiloBean> getAbilitazioni() {
		return abilitazioni;
	}

	public void setAbilitazioni(List<ProfiloBean> abilitazioni) {
		this.abilitazioni = abilitazioni;
	}

	public List<Object> getDettagli2() {
		return dettagli2;
	}

	public void setDettagli2(List<Object> dettagli2) {
		this.dettagli2 = dettagli2;
	}

	

	
	
}
