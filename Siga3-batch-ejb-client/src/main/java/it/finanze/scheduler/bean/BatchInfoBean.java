package it.finanze.scheduler.bean;

public class BatchInfoBean {
	
	private String id;
	private String nomeClasse;
	private String descrizioneBreve;
	private String descrizione;
	private String stato;
	private String scheduling;
	private String schedulingTime;
	private String schedulingProvvisorio;
	
	
	public String getId() {
		return id;
	}
	public String getNomeClasse() {
		return nomeClasse;
	}
	public String getDescrizioneBreve() {
		return descrizioneBreve;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public String getStato() {
		return stato;
	}
	public String getScheduling() {
		return scheduling;
	}
	public String getSchedulingTime() {
		return schedulingTime;
	}
	public String getSchedulingProvvisorio() {
		return schedulingProvvisorio;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}
	public void setDescrizioneBreve(String descrizioneBreve) {
		this.descrizioneBreve = descrizioneBreve;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public void setScheduling(String scheduling) {
		this.scheduling = scheduling;
	}
	public void setSchedulingTime(String schedulingTime) {
		this.schedulingTime = schedulingTime;
	}
	public void setSchedulingProvvisorio(String schedulingProvvisorio) {
		this.schedulingProvvisorio = schedulingProvvisorio;
	}
	
	
}