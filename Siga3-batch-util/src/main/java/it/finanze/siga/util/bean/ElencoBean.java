package it.finanze.siga.util.bean;


import java.io.Serializable;
import java.util.List;


public class ElencoBean implements Serializable{


	private static final long serialVersionUID = -7134201745619363331L;

	protected Integer numeroElementi = null;
	protected List<Serializable> elenco = null;
	

	public List<Serializable> getElenco() {
		return elenco;
	}
	
	public void setElenco(List<Serializable> elenco) {
		this.elenco = elenco;
	}
	
	public Integer getNumeroElementi() {
		if(this.numeroElementi == null)
		{
			if(this.elenco == null)
			{
				return new Integer(0);
			}else
			{
				return new Integer(this.elenco.size());
			}
		}else
		{
			return this.numeroElementi;
		}
	}
	
	public void setNumeroElementi(Integer numeroElementi) {
		this.numeroElementi = numeroElementi;
	}
	
}
