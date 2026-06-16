package it.finanze.siga.ejb;

import java.sql.Date;

public interface IBatchResponsabilitaNew {
	
	void startBatch(String type, String all, Date dataUltimoAggiornamento) throws Exception;

}
