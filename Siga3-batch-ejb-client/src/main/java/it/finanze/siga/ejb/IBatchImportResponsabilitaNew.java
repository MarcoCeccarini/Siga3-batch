package it.finanze.siga.ejb;

public interface IBatchImportResponsabilitaNew {

	void startBatch(String dataResponsabilita, String criterioResponsabilita, String tipoElaborazioneResponsabilita) throws Exception ;
	void startOA( String dataResponsabilita, String criterioResponsabilita, String tipoElaborazioneResponsabilita ) throws Exception ;
}
