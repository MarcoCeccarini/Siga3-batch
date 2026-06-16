package it.finanze.siga.util.finder;

import java.io.Serializable;


public class PaginateFinder implements Serializable{
	
	private static final long serialVersionUID = 6782610744008366067L;
	
	private String pagina;
    private String recordPerPagina;
    private Integer inizio;
    private Integer fine;
    
	public String getPagina() {
		return pagina;
	}
	
	public void setPagina(String pagina){
		this.pagina = pagina;	
	}

	public String getRecordPerPagina() {
		return recordPerPagina;
	}

	public void setRecordPerPagina(String recordPerPagina) {
		this.recordPerPagina = recordPerPagina;
	}

	
	private int getRecordPerPaginaNumber() {
		
		int recordPerPaginaNumber;
		
		try{
			//Uso il valore di recordPerPagina scelto dall'utente
			//Questo valore dovrebbe essere formalmente corretto poicha viene eseguito un controllo 
			//all'interno del validate dell'ActionForm.
			recordPerPaginaNumber = Integer.parseInt(recordPerPagina);
		
		} catch(NumberFormatException ne){
			throw ne;
		}
		
		return recordPerPaginaNumber;
	}
	
	
	public int getInizio() {
		int p = 1;
		if (pagina != null && !pagina.trim().equals("")) {
			try {
				p = Integer.parseInt(pagina);
			} catch (NumberFormatException ne) {
				p = 1;
			}
		}
		return (p-1)*getRecordPerPaginaNumber();
	}
	
	public int getFine() {
		int p = 1;
		if (pagina != null && !pagina.trim().equals("")) {
			try {
				p = Integer.parseInt(pagina);
			} catch (NumberFormatException ne) {
				p = 1;
			}
		}
		return (p*getRecordPerPaginaNumber());
	}

	public void setInizio(Integer inizio) {
		this.inizio = inizio;
	}

	public void setFine(Integer fine) {
		this.fine = fine;
	}
}
