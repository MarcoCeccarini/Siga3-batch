package it.finanze.siga.ejb;

import java.sql.SQLException;
import java.text.ParseException;

public interface IBatchGeneraRichiesteNew {

	void startBatch() throws Exception;

	void elaboraRichieste(Integer tentativi, Integer intervallo) throws SQLException, ParseException;
}
