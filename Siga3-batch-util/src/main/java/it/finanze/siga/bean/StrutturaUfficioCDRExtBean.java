package it.finanze.siga.bean;

public class StrutturaUfficioCDRExtBean extends StrutturaUfficioCDRBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3308969852350491893L;
	
	private int livello;
	private int progressivo;
	private boolean unselectable = false;
	private boolean selected = false;
	private String campoOrdinamento;
	
	
	@Override
	public String toString() {
		return super.toString() + 
				"StrutturaUfficioCDRExtBean [livello=" + livello + ", progressivo=" + progressivo + "]";
	}
	
	
	/** GETTERS AND SETTERS **/
	public int getLivello() {
		return livello;
	}
	public void setLivello(int livello) {
		this.livello = livello;
	}
	public int getProgressivo() {
		return progressivo;
	}
	public void setProgressivo(int progressivo) {
		this.progressivo = progressivo;
	}


	public boolean isUnselectable() {
		return unselectable;
	}


	public void setUnselectable(boolean unselectable) {
		this.unselectable = unselectable;
	}


	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	public String getCampoOrdinamento() {
		return campoOrdinamento;
	}


	public void setCampoOrdinamento(String campoOrdinamento) {
		this.campoOrdinamento = campoOrdinamento;
	}

}
