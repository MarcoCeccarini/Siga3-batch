package it.finanze.siga.bean;


import java.util.List;

public class RichiesteDaLavReportBean {
	
	private List<ProfiloRichiestaBean> profiloRichiesta;
	private List<InterrProfiliOperatoreBean> profiloRisultante;
	
	public List<ProfiloRichiestaBean> getProfiloRichiesta() {
		return profiloRichiesta;
	}
	public void setProfiloRichiesta(
			List<ProfiloRichiestaBean> profiloRichiesta) {
		this.profiloRichiesta = profiloRichiesta;
	}
	public List<InterrProfiliOperatoreBean> getProfiloRisultante() {
		return profiloRisultante;
	}
	public void setProfiloRisultante(
			List<InterrProfiliOperatoreBean> profiloRisultante) {
		this.profiloRisultante = profiloRisultante;
	}
	
	

}
