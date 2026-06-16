package it.finanze.scheduler.bean;

import it.finanze.siga.bean.FileAbilitazioneBean;

import java.io.Serializable;
import java.util.List;

public class CaricamentoMassivoInserimentoBean implements Serializable{

	private static final long serialVersionUID = -1966694995176103657L;
	List<RichiestaAbilitazioneDisabilitazioneBean> listaRichiesteCaricamento;
	List<FileAbilitazioneBean> listaFileAbilitazione;
	
	public List<RichiestaAbilitazioneDisabilitazioneBean> getListaRichiesteCaricamento() {
		return listaRichiesteCaricamento;
	}
	public void setListaRichiesteCaricamento(
			List<RichiestaAbilitazioneDisabilitazioneBean> listaRichiesteCaricamento) {
		this.listaRichiesteCaricamento = listaRichiesteCaricamento;
	}
	public List<FileAbilitazioneBean> getListaFileAbilitazione() {
		return listaFileAbilitazione;
	}
	public void setListaFileAbilitazione(
			List<FileAbilitazioneBean> listaFileAbilitazione) {
		this.listaFileAbilitazione = listaFileAbilitazione;
	}
	
	
	
}
