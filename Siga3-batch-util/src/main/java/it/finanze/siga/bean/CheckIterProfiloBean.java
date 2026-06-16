package it.finanze.siga.bean;

public class CheckIterProfiloBean extends ProfiloBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8591143619762904025L;
	
	private String profiloRichiedibile;
	
	
	@Override
	public String toString() {
		return super.toString() + 
				"CheckIterProfiloBean [profiloRichiedibile="
					+ profiloRichiedibile + ", tipoIter=" + tipoIter + "]";
	}
	
	
	// added
	private String tipoIter;
	
	public String getTipoIter() {
		return tipoIter;
	}
	public void setTipoIter(String tipoIter) {
		this.tipoIter = tipoIter;
	}
	
	public String getProfiloRichiedibile() {
		return profiloRichiedibile;
	}
	public void setProfiloRichiedibile(String profiloRichiedibile) {
		this.profiloRichiedibile = profiloRichiedibile;
	}
	
	
}
