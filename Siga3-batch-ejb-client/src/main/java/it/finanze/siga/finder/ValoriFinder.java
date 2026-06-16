package it.finanze.siga.finder;

import it.finanze.siga.util.finder.PaginateFinder;

public class ValoriFinder extends PaginateFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6714271881354427733L;
	
	private String categoria;

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}

}
