/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.finanze.siga.workflow.core;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//import com.ibm.ws.util.Base64;

public class WorkFlowJMSClient {

    public static final String JMS_CONNECTIONFACTORY_NAME="jms.connectionfactory.name";
    public static final String JMS_CONNECTIONFACTORY_USER="jms.connectionfactory.username";
    public static final String JMS_CONNECTIONFACTORY_PASSWORD="jms.connectionfactory.password";

    public static final String JMS_DESTINATION_NAME="jms.destination.name";
    

	public static final String PATH_PROPERTY = "configFilePath";	
	public static final String FILE_CONFIG = "JMSParam.cfg";	
    protected static Properties _properties=null;
    
    
    protected Context _jndiContext = null;
    protected ConnectionFactory _connectionFactory = null;
    protected Connection _jmsConnection = null;
    protected Destination _jmsDestination = null;
    protected MessageProducer _jmsMessageProducer = null;
    protected Session _jmsSession=null;
    
    static{
    	_properties = new Properties();
    	try {
			_properties.load(new FileInputStream(((Context) new InitialContext().lookup("java:comp/env")).lookup(PATH_PROPERTY) + FILE_CONFIG));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public WorkFlowJMSClient(){
    }

    public boolean sendTextMessage(String message) throws JMSException{

        TextMessage msg=getSession().createTextMessage();
        msg.setText(message);
        MessageProducer msgProducer=getMessageProducer();

        msgProducer.send(msg);
        return true;
    }
    public boolean Ping() throws JMSException{

        getSession().createTextMessage();

        getMessageProducer();

        return true;
    }
    public void close() throws JMSException{
        if(_jmsMessageProducer!=null)
            _jmsMessageProducer.close();
        _jmsMessageProducer=null;
        if(_jmsSession!=null)
            _jmsSession.close();
        _jmsSession=null;
        if(_jmsConnection!=null)
            _jmsConnection.close();
        _jmsConnection=null;
    }
    protected Session getSession() throws JMSException{

        if(_jmsSession==null)
            _jmsSession = getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
        return _jmsSession;
    }
    protected Connection getConnection() throws JMSException{
        if(_jmsConnection==null){
            _jmsConnection = getConnectionFactory().createConnection(
                    _properties.getProperty(JMS_CONNECTIONFACTORY_USER,""),
                    _properties.getProperty(JMS_CONNECTIONFACTORY_PASSWORD,""));
        
        }
        return _jmsConnection;
    }
    
	protected ConnectionFactory getConnectionFactory()  throws JMSException {
        if(_connectionFactory==null){
            try{
                _jndiContext = new InitialContext();
                _connectionFactory = (ConnectionFactory)_jndiContext.lookup(_properties.getProperty(JMS_CONNECTIONFACTORY_NAME, "jms/QCFSiga"));
                _jmsDestination = (Destination)_jndiContext.lookup(_properties.getProperty(JMS_DESTINATION_NAME, "jms/QueueSiga"));
            }
            catch(NamingException e){
                throw new JMSException("Errore JNDI: " + e.getMessage());

            }

        }
        return _connectionFactory;
    }
    protected MessageProducer getMessageProducer() throws JMSException{
       if(_jmsMessageProducer==null){
            _jmsMessageProducer = getSession().createProducer(_jmsDestination);
        }
        return _jmsMessageProducer;
    }
    
    public void send(Integer idRichiesta,String agenziaOperatore, String cfOperatore){
        try{
			List<Object> param = new ArrayList<Object>();
			param.add(idRichiesta);			
			param.add(agenziaOperatore);
			param.add(cfOperatore);
            ByteArrayOutputStream baos =new ByteArrayOutputStream();
            ObjectOutputStream encoder = new ObjectOutputStream(baos);
            encoder.writeObject(param);
            encoder.close();
            this.sendTextMessage(new String(Base64.getEncoder().encode(baos.toByteArray())));
            this.close();
        } catch (Throwable e) {
			e.printStackTrace();
		}
    }
}
