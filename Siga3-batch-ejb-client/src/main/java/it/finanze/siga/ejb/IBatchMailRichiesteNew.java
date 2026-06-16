package it.finanze.siga.ejb;

import java.sql.SQLException;

public interface IBatchMailRichiesteNew {

	void startBatch() throws Exception;

	void elaboraRichiesteDaArchiviare() throws SQLException, Exception;

	void elaboraRichiesteDaAutorizzare() throws SQLException, Exception;

	void elaboraRichiesteDaEseguire() throws SQLException, Exception;

	void elaboraRichiesteDaPrendereInCarico() throws SQLException, Exception;

	void elaboraSollecitoPresaVisione() throws SQLException, Exception;

}