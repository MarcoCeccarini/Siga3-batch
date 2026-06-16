package it.finanze.siga.bean;

public class ExportProfiloMappaturaBean extends ExportProfiloBean{


	private static final long serialVersionUID = 2346708875606923280L;
	protected String bloccato;
	protected String codiceUfficio;
	protected String descrizioneUfficio;
	protected String descrizioneCodice;
	protected String codiceAbilitazione;
	protected String descrAbilitazione;
	protected StrutturaIterBean percorso;


	public String getDescrAbilitazione() {
		if(tipoAbilitazione==null)
			descrAbilitazione = "";
		else if(tipoAbilitazione.equals("O"))
			descrAbilitazione = "Ordinaria";
		else if(tipoAbilitazione.equals("S"))
			descrAbilitazione = "Extra-Ufficio";
		return descrAbilitazione;
	}

	public void setDescrAbilitazione(String descrAbilitazione) {
		this.descrAbilitazione = descrAbilitazione;
	}

	public String getBloccato() {
		return bloccato;
	}

	public void setBloccato(String bloccato) {
		this.bloccato = bloccato;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getDescrizioneUfficio() {
		if(descrizioneUfficio==null){
			switch (Integer.parseInt(modAss)) {
			case 1:
				if((provincia!=null && !provincia.trim().equals("")) || (regione!=null && !regione.trim().equals("")))
					descrizioneUfficio=tipoUfficioDesc + " - " + provincia + regione;
				else
					descrizioneUfficio=tipoUfficioDesc + " " + provincia + regione;
				break;
			case 2:
				if((provincia!=null && !provincia.trim().equals("")) || (regione!=null && !regione.trim().equals("")))
					descrizioneUfficio=tipoCDRDesc + " - " + provincia + regione;
				else
					descrizioneUfficio=tipoCDRDesc + " " + provincia + regione;
				break;
			case 3:
				descrizioneUfficio=ufficioDesc;
				break;
			case 4:
				descrizioneUfficio=cdrDesc;
				break;
			case 5:
				descrizioneUfficio=tipoUfficioDesc+ " della provincia "+ provincia;
				break;
			case 6:
				descrizioneUfficio=tipoUfficioDesc+ "della regione" +regione ;
				break;
			case 7:
				descrizioneUfficio=tipoCDRDesc + " della provincia "+ provincia;
				break;
			case 8:
				descrizioneUfficio=tipoCDRDesc + "della regione" +regione;
				break;	
			default:
				descrizioneUfficio="";
				break;
			}
		}
		return descrizioneUfficio;
	}

	public void setDescrizioneUfficio(String descrizioneUfficio) {
		this.descrizioneUfficio = descrizioneUfficio;
	}

	public String getDescrizioneCodice() {
		if(descrizioneCodice==null){
			switch (Integer.parseInt(modAss)) {
			case 1:
				descrizioneCodice="Tipologia generica uffici";
				break;
			case 2:
				descrizioneCodice="Tipologia generica CDR";
				break;
			case 3:
				descrizioneCodice=codiceUfficio;
				break;
			case 4:
				descrizioneCodice=codiceCdr;
				break;
			case 5:
				descrizioneCodice="Tipologia generica uffici";
				break;
			case 6:
				descrizioneCodice="Tipologia generica uffici";
				break;
			case 7:
				descrizioneCodice="Tipologia generica CDR";
				break;
			case 8:
				descrizioneCodice="Tipologia generica CDR";
				break;	
			default:
				descrizioneCodice="";
				break;
			}
		}
		return descrizioneCodice;
	}

	public void setDescrizioneCodice(String descrizioneCodice) {
		this.descrizioneCodice = descrizioneCodice;
	}

	public String getCodiceAbilitazione() {
		return codiceAbilitazione;
	}

	public void setCodiceAbilitazione(String codiceAbilitazione) {
		this.codiceAbilitazione = codiceAbilitazione;
	}

	public StrutturaIterBean getPercorso() {
		return percorso;
	}

	public void setPercorso(StrutturaIterBean percorso) {
		this.percorso = percorso;
	}
	
	
	
}
