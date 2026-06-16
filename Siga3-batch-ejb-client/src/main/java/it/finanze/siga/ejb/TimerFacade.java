package it.finanze.siga.ejb;

import java.util.HashMap;

import it.sogei.eafx.command.ejb3facade.EJBServiceFacadeInterface;

import jakarta.ejb.Remote;

@Remote
public interface TimerFacade    extends EJBServiceFacadeInterface{
	
	  public void startBatch(HashMap<String,String> input ) throws ClassNotFoundException;

}
