package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.List;

public class IMieiRuoliBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6057872897839394770L;
	
	/* TAB Ruoli di un CDR */
	private List<RuoloRichiedenteBean> ruoliRichiedente;
	
	/* TAB Autorizzatore II livello */
	private List<RuoloAutorizzatore_II_LivelloBean> ruoliAutorizzatore_II_Livello;
	
	/* TAB Amministratore Auditor */
	private List<RuoloAmministratoreAuditorBean> ruoliAmministratoreAuditor;
	
	/* TAB Deleghe */
	private List<RuoloDelegheBean> ruoliDeleghe;

	public List<RuoloRichiedenteBean> getRuoliRichiedente() {
		return ruoliRichiedente;
	}

	public void setRuoliRichiedente(List<RuoloRichiedenteBean> ruoliRichiedente) {
		this.ruoliRichiedente = ruoliRichiedente;
	}

	public List<RuoloAutorizzatore_II_LivelloBean> getRuoliAutorizzatore_II_Livello() {
		return ruoliAutorizzatore_II_Livello;
	}

	public void setRuoliAutorizzatore_II_Livello(
			List<RuoloAutorizzatore_II_LivelloBean> ruoliAutorizzatore_II_Livello) {
		this.ruoliAutorizzatore_II_Livello = ruoliAutorizzatore_II_Livello;
	}

	public List<RuoloAmministratoreAuditorBean> getRuoliAmministratoreAuditor() {
		return ruoliAmministratoreAuditor;
	}

	public void setRuoliAmministratoreAuditor(
			List<RuoloAmministratoreAuditorBean> ruoliAmministratoreAuditor) {
		this.ruoliAmministratoreAuditor = ruoliAmministratoreAuditor;
	}

	public List<RuoloDelegheBean> getRuoliDeleghe() {
		return ruoliDeleghe;
	}

	public void setRuoliDeleghe(List<RuoloDelegheBean> ruoliDeleghe) {
		this.ruoliDeleghe = ruoliDeleghe;
	}


}
