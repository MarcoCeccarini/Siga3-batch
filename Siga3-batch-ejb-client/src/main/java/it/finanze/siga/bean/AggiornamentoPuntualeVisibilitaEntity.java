package it.finanze.siga.bean;

import it.finanze.scheduler.bean.ProfiloVisibilitaTracciamentoBean;
import it.finanze.siga.util.bean.UtenteBean;

import java.io.Serializable;
import java.util.List;

public class AggiornamentoPuntualeVisibilitaEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2063430616598984675L;
	
	UtenteBean utente;
	List<ProfiloVisibilitaTracciamentoBean> listaAggiornamenti;
	List<FileBean> listaFiles;
	
	public UtenteBean getUtente() {
		return utente;
	}
	public void setUtente(UtenteBean utente) {
		this.utente = utente;
	}
	public List<ProfiloVisibilitaTracciamentoBean> getListaAggiornamenti() {
		return listaAggiornamenti;
	}
	public void setListaAggiornamenti(
			List<ProfiloVisibilitaTracciamentoBean> listaAggiornamenti) {
		this.listaAggiornamenti = listaAggiornamenti;
	}
	public List<FileBean> getListaFiles() {
		return listaFiles;
	}
	public void setListaFiles(List<FileBean> listaFiles) {
		this.listaFiles = listaFiles;
	}
	
	
}
