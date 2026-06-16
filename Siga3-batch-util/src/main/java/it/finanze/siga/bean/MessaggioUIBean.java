package it.finanze.siga.bean;

public class MessaggioUIBean extends BaseBean {
	
	private enum TipoMessaggio {
		INFO,
		ERROR,
		OK,
		WARNING
	}
	
	private enum Azione {
		TO_SHOW,
		SHOWED,
		TO_SHOW_AGAIN
	}
	
	private TipoMessaggio tipo;
	private int id;
	private String message;
	private Azione azione;
	
	public Azione getAzione() {
		return azione;
	}
	public void setAzione(Azione azione) {
		this.azione = azione;
	}
	public TipoMessaggio getTipo() {
		return tipo;
	}
	public void setTipo(TipoMessaggio tipo) {
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
