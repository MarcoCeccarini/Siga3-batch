package it.finanze.siga.utility.jndi;


import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public final class JNDIWrapper {
	
    private static JNDIWrapper singleton;
    private static HashMap<String,Object> wrapper;

    private JNDIWrapper() {}

    public static JNDIWrapper getInstance()
    {
        if(singleton == null)
        {
            singleton = new JNDIWrapper();
            wrapper = new HashMap<String,Object>();
        }
        return singleton;
    }

    protected Object clone()
        throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }
    public synchronized Object getJNDIEntry(String entry)
    throws NamingException
    {
    	return getJNDIEntry(entry,true);
    }

    public synchronized Object getJNDIEntry(String entry,boolean cache)
        throws NamingException
    {
    	Logger.getLogger(JNDIWrapper.class).debug("JNDI Search with key: " + entry);
        if(entry == null)
            return null;
        
        if(cache && wrapper.containsKey(entry))
            return wrapper.get(entry);
        
        Object obj = null;
        
        try {
            Context ic = new InitialContext();

            obj = ic.lookup(entry);
            wrapper.put(entry, obj);
        } catch(NamingException ne) {
        	Logger.getLogger(JNDIWrapper.class).debug(ne);
            throw ne;
        } catch(Exception e) {
        	Logger.getLogger(JNDIWrapper.class).debug(e);
        }
        
        return obj;
    }
}
