/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateful;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author vikaram
 */
@Stateful
public class MobileSessionBean implements MobileSessionBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   @EJB
    RemoteDBSessionBeanRemote updateLocal;
  static final Logger logger = Logger.getLogger("OmazonMDB");
    Connection connection = null;
    Session session = null;
 //   @Resource(mappedName = "jms/mqTest")
    private Queue dest;
  //  @Resource(mappedName = "jms/ConnectionFactory")
    private QueueConnectionFactory queue;
    
     private Message createJMSMessageForjmsDest(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        // System.out.println("Create JMS message ************  " + tm);
        return tm;
    }
    /**
     *
     * @param messageData
     * @param cf
     * @param qu
     * @throws JMSException
     * @throws NamingException
     */
     @Override
     public void sendMessage(Object messageData,String cf, String qu) throws JMSException, NamingException {
        
        try {
            
            Properties props = new Properties();
            props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            // Gets the JNDI context
            Context jndiContext = new InitialContext(props);
            // Looks up the administered objects
            queue = (QueueConnectionFactory) jndiContext.lookup(cf);
            dest = (Queue) jndiContext.lookup(qu);
            connection = queue.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(dest);
            System.out.println("Sending message ************  " + messageData);
            messageProducer.send(createJMSMessageForjmsDest(session, messageData));
            
            //TextMessage textMessage = recJMSMessageFromDest();
            //return textMessage.toString();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
     
     
//    @Override 
//    public String sendMessage(Object messageData,String cf, String qu) throws JMSException, NamingException {
//        
//        try {
//            
//            Properties props = new Properties();
//            props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
//            props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
//            props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
//            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
//            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
//            // Gets the JNDI context
//            Context jndiContext = new InitialContext(props);
//            // Looks up the administered objects
//            queue = (QueueConnectionFactory) jndiContext.lookup(cf);
//            dest = (Queue) jndiContext.lookup(qu);
//            connection = queue.createConnection();
//            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            MessageProducer messageProducer = session.createProducer(dest);
//            System.out.println("Sending message ************  " + messageData);
//            messageProducer.send(createJMSMessageForjmsDest(session, messageData));
//            
//            TextMessage textMessage = recJMSMessageFromDest();
//            return textMessage.toString();
//        } finally {
//            if (session != null) {
//                try {
//                    session.close();
//                } catch (JMSException e) {
//                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
//                }
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
    

    //@Schedule(second="10", minute="*", hour="*", persistent = false)
    public TextMessage recJMSMessageFromDest() throws JMSException, NamingException
    {
        logger.info("Listening=========================>");
        try {
            Properties props = new Properties();
            props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            // Gets the JNDI context
            Context jndiContext = new InitialContext(props);
            // Looks up the administered objects
            queue = (QueueConnectionFactory) jndiContext.lookup("RC");
            dest = (Queue) jndiContext.lookup("RCDest");
            
            connection = queue.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                        
            MessageConsumer consumer = session.createConsumer(dest);
            connection.start();
            //Object messageData ;
            TextMessage textMessage=null;
            while(textMessage == null)
            {
                textMessage = (TextMessage) consumer.receive();
            }
             System.out.println("Received message ************  " + textMessage.getText());
            //messageData;
             return textMessage;
            
        } 
       
        finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
            //consumer.close();
        }
        
        //return messageData;
    }
    
    
    
   
                   
    @Override
    public void commit(String msg)
    {
        if(msg.charAt(0)=='C')
        {
            //updateLocal.UpdateTables();
        }
       
    }
    
    
    
}
