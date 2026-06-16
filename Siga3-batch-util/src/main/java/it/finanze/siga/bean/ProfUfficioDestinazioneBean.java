package it.finanze.siga.bean;

import java.io.Serializable;


public class ProfUfficioDestinazioneBean extends ProfAttUte_x_Uff_di_provenienzBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2034713132804622338L;

	/**
	 * 
	 */



	@Override
	public String toString() {
		return "ProfAttUte_x_Uff_di_provenienzBean [ca_1=" + super.getCa_1() + ", crg_1="
				+ super.getCrg_1() + ", cp_1=" + super.getCp_1() + ", app_desc=" + super.getApp_desc()
				+ ", rg_desc=" + super.getRg_desc() + ", pr_desc=" + super.getPr_desc()
				+ ", tipo_profilazione=" + super.getTipo_profilazione()
				+ ", scadenza_profilo=" + super.getScadenza_profilo() + ", data_scadenza="
				+ super.getData_scadenza() + ", numeroElementi=" + super.getNumeroElementi() + ", tipo_abilitazione=" + tipo_abilitazione + "]";
	}
	
	/**
	 * 
	 */
	
	private String tipo_abilitazione;

	public String getTipo_abilitazione() {
		return tipo_abilitazione;
	}
	public void setTipo_abilitazione(String tipo_abilitazione) {
		this.tipo_abilitazione = tipo_abilitazione;
	}
	
}
