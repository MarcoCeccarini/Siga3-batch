package it.finanze.scheduler.bean;

import java.util.Date;

public class RichiestaInserimentoRevocaVisibilitaBean extends RichiestaAbilitazioneDisabilitazioneBean{


	private static final long serialVersionUID = -7621031427259342992L;
	private Date dataEvento;

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
	
	
}
