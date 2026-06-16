package it.finanze.siga.finder;

public class InterrogazioneMappaturaProfiliUfficioFinder extends InterrogazioneProfilazioneFinder{


	private static final long serialVersionUID = 2576998383280793534L;
	private String[] provinceMappatura;
	private String[] regioniMappatura;
	
	public String[] getProvinceMappatura() {
		return provinceMappatura;
	}
	public void setProvinceMappatura(String[] provinceMappatura) {
		this.provinceMappatura = provinceMappatura;
	}
	public String[] getRegioniMappatura() {
		return regioniMappatura;
	}
	public void setRegioniMappatura(String[] regioniMappatura) {
		this.regioniMappatura = regioniMappatura;
	}
	
	
}
