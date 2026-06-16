package it.finanze.scheduler.bean;

import it.finanze.siga.finder.AuditFinder;
import it.finanze.siga.util.Constants;

public class AuditRegistroRichiesteBean {

	private int idRichiesta;
	private String tipoRichiesta, cfOperatore;
	private AuditFinder auditFinder;
	private String nome;
	private String cognome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public int getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(int idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	public String getTipoRichiesta() {
		return tipoRichiesta;
	}
	public void setTipoRichiesta(String tipoRichiesta) {
		this.tipoRichiesta = tipoRichiesta;
	}
	public String getCfOperatore() {
		return cfOperatore;
	}
	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}
	public AuditFinder getAuditFinder() {
		
		auditFinder = new AuditFinder();
		auditFinder.setTabAggiornata("REGISTRO_RICHIESTE");	
		String descrizioneRichiesta = "";
		
		if(tipoRichiesta.equals(Constants.RICHIESTA_PROFILAZIONE))
			descrizioneRichiesta = "profilazione";

		else if(tipoRichiesta.equals(Constants.RICHIESTA_VISIBILITA))
			descrizioneRichiesta = "visibilita";

		else if(tipoRichiesta.equals(Constants.RICHIESTA_REVOCA_VISIBILITA))
			descrizioneRichiesta = "revoca visibilita";
		
		else if(tipoRichiesta.equals(Constants.RICHIESTA_CAMBIO_UFFICIO))
			descrizioneRichiesta = "cambio ufficio";

			
			auditFinder.setTesto("Inserita richiesta " + idRichiesta + " di " 
		            + descrizioneRichiesta + " per " + cognome+" "+nome+" (CF "+ cfOperatore + "). ");
			
			return auditFinder;
	}
	
	public void setAuditFinder(AuditFinder auditFinder) {
		this.auditFinder = auditFinder;
	}
	
	
}
