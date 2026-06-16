package it.finanze.siga.util.forms;


public class PaginateForm extends BaseForm {


	private static final long serialVersionUID = -9159726020986407146L;

	private String pagina = "1";
	private String recordPerPagina = "10";
	
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


}
