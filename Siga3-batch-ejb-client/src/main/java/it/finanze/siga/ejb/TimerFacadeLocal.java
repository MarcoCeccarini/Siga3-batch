package it.finanze.siga.ejb;

 
import java.util.HashMap;

import it.sogei.eafx.command.ejb3facade.EJBServiceFacadeInterface;

import jakarta.ejb.Local;

 
@Local
public interface TimerFacadeLocal extends EJBServiceFacadeInterface{
	
	  public void startBatch(HashMap<String,String> input) throws ClassNotFoundException;
	
	
}
