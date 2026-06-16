package it.finanze.siga.bean;

import java.io.Serializable;

public class StrutturaIterEntityBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -567892742395413330L;
	
	private String iter_standard;
	private String tipo_cdr_autoriz_i_liv;
	private String cdr_autoriz_i_liv;
	private String cf_i;
	
	
	public String getIter_standard() {
		return iter_standard;
	}
	public void setIter_standard(String iter_standard) {
		this.iter_standard = iter_standard;
	}
	public String getTipo_cdr_autoriz_i_liv() {
		return tipo_cdr_autoriz_i_liv;
	}
	public void setTipo_cdr_autoriz_i_liv(String tipo_cdr_autoriz_i_liv) {
		this.tipo_cdr_autoriz_i_liv = tipo_cdr_autoriz_i_liv;
	}
	public String getCdr_autoriz_i_liv() {
		return cdr_autoriz_i_liv;
	}
	public void setCdr_autoriz_i_liv(String cdr_autoriz_i_liv) {
		this.cdr_autoriz_i_liv = cdr_autoriz_i_liv;
	}
	public String getCf_i() {
		return cf_i;
	}
	public void setCf_i(String cf_i) {
		this.cf_i = cf_i;
	}
	

}
