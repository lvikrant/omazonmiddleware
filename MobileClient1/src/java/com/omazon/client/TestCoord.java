/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omazon.client;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
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
public class TestCoord {
    Connection connection = null;
    Session session = null;
    
    public static void main(String[] args) throws JMSException {
        // TODO code application logic here
        // String fileName = "omazonStatus";//args[0];
        //SendPositionUpdates app = new SendPositionUpdates();
        //String str = "Let there be ROCK";
        //System.out.println("" + args[0]);
        //String msg = app.parseXML(args[0], args[1].charAt(0));
       // System.out.println("Message to send" + msg);
        
        String msgs = null;
        TestCoord coord = new TestCoord();
        msgs = "test";
        try {
            coord.sendJMSMessageToDest(msgs);
        } catch (NamingException ex) {
            Logger.getLogger(TestCoord.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
    
    
    private Message createJMSMessageForjmsDest(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        // System.out.println("Create JMS message ************  " + tm);
        return tm;
    }
   //  @Resource(mappedName = "jms/mqTest")
    private Queue dest;
  //  @Resource(mappedName = "jms/ConnectionFactory")
    private QueueConnectionFactory queue;
    
    
    
    
    
    
    public boolean sendJMSMessageToDest(Object messageData) throws JMSException, NamingException {
        int flag =0;
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
            queue = (QueueConnectionFactory) jndiContext.lookup("jms/ConnectionFactory");
            dest = (Queue) jndiContext.lookup("jms/mqTest");
            connection = queue.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(dest);
            System.out.println("Sending message ************  " + messageData);
            messageProducer.send(createJMSMessageForjmsDest(session, messageData));
            flag = 1;
            return true;
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

   
    
    
    
    
    
}
