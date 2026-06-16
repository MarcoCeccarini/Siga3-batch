package it.finanze.siga.bean;

import java.io.Serializable;

import org.apache.struts.util.LabelValueBean;

public class TipoUfficioAbilitazione implements Serializable{

	private static final long serialVersionUID = 5351354842528669606L;
	
	private LabelValueBean tipoUfficio;
	private ProfiloBean abilitazione;
	
	public LabelValueBean getTipoUfficio() {
		return tipoUfficio;
	}
	public void setTipoUfficio(LabelValueBean tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}
	public ProfiloBean getAbilitazione() {
		return abilitazione;
	}
	public void setAbilitazione(ProfiloBean abilitazione) {
		this.abilitazione = abilitazione;
	}
	
	
	
}
