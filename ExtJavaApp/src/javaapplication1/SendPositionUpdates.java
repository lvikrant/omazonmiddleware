/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;
import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Message;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.jms.Connection;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.annotation.Resource;
import javax.jms.MessageConsumer;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author vikaram
 */
public class SendPositionUpdates {

    Connection connection = null;
    Session session = null;
    static SendPositionUpdates app = new SendPositionUpdates();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JMSException {
        // TODO code application logic here
        // String fileName = "omazonStatus";//args[0];
        //SendPositionUpdates app = new SendPositionUpdates();
        //String str = "Let there be ROCK";
        System.out.println("" + args[0]);
        String msg = app.parseXML(args[0], args[1].charAt(0));
       // System.out.println("Message to send" + msg);
    }

    private Message createJMSMessageForjmsDest(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        // System.out.println("Create JMS message ************  " + tm);
        return tm;
    }
    @Resource(mappedName = "jms/dest")
    private Queue dest;
    @Resource(mappedName = "jms/queue")
    private QueueConnectionFactory queue;

    private void sendJMSMessageToDest(Object messageData) throws JMSException, NamingException {

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
            queue = (QueueConnectionFactory) jndiContext.lookup("jms/queue");
            dest = (Queue) jndiContext.lookup("jms/dest");
            connection = queue.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(dest);
            //MessageConsumer msgCons = session.createConsumer(dest);
            System.out.println("Sending message ************  " + messageData);
            messageProducer.send(createJMSMessageForjmsDest(session, messageData));
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

    public String parseXML(String fileName, char type) {
        switch (type) {
            case 'P':
                return parsePosition(fileName, type);

            case 'S':
                return parseStatus(fileName, type);

            case 'E':
                return parseException(fileName, type);

            default:
                return null;
        }
    }

    public String parsePosition(String fileName, char type) {
        StringBuilder sb = null;
        //====================================================PARSE XML =========================================================//
        try {          
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(fileName + ".xml"));
            // normalize text representation
            doc.getDocumentElement().normalize();
            System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
            NodeList listOfPositions = doc.getElementsByTagName("positionEvent");
            int total = listOfPositions.getLength();
            System.out.println("Total no of Events : " + total);
            for (int s = 0; s < listOfPositions.getLength(); s++) {
                sb = new StringBuilder();
                sb.append(type);
                sb.append(",");
                Node firstPersonNode = listOfPositions.item(s);
                if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element firstPersonElement = (Element) firstPersonNode;

                    NodeList truckList = firstPersonElement.getElementsByTagName("truckId");
                    Element lastNameElement = (Element) truckList.item(0);
                    NodeList textLNList = lastNameElement.getChildNodes();
                    System.out.println("Truck ID : " + ((Node) textLNList.item(0)).getNodeValue().trim());
                    sb.append(((Node) textLNList.item(0)).getNodeValue().trim());
                    sb.append(",");

                    NodeList latList = firstPersonElement.getElementsByTagName("lat");
                    Element ageElement = (Element) latList.item(0);
                    NodeList textAgeList = ageElement.getChildNodes();
                    System.out.println("Lat : " + ((Node) textAgeList.item(0)).getNodeValue().trim());
                    sb.append(((Node) textAgeList.item(0)).getNodeValue().trim());
                    sb.append(",");

                    NodeList longList = firstPersonElement.getElementsByTagName("long");
                    Element longElement = (Element) longList.item(0);
                    NodeList textLongList = longElement.getChildNodes();
                    System.out.println("Long : " + ((Node) textLongList.item(0)).getNodeValue().trim());
                    sb.append(((Node) textLongList.item(0)).getNodeValue().trim());
                    //==========SEND================
                    try {
                        app.sendJMSMessageToDest(sb.toString());
                    } catch (NamingException ex) {
                        Logger.getLogger(SendPositionUpdates.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Message :" + s + "----------->" + sb.toString());
                }//end of if clause

            }//end of for loop with s var
        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());

        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();

        } catch (Throwable t) {
            t.printStackTrace();
        }
        //System.out.println(sb.toString());
        String sendMsg = sb.toString();
        return sendMsg;
    }

    public String parseStatus(String fileName, char type) {
        StringBuilder sb = null;
        //====================================================PARSE XML =========================================================//
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(fileName + ".xml"));
            // normalize text representation
            doc.getDocumentElement().normalize();
            System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
            NodeList listOfPositions = doc.getElementsByTagName("statusEvent");
            int total = listOfPositions.getLength();
            System.out.println("Total no of Events : " + total);
            for (int s = 0; s < listOfPositions.getLength(); s++) {
                sb = new StringBuilder();
                sb.append(type);
                sb.append(",");
                Node firstPersonNode = listOfPositions.item(s);
                if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element firstPersonElement = (Element) firstPersonNode;
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("shipmentId");
                    Element firstNameElement = (Element) firstNameList.item(0);
                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.println("Shipment id : " + ((Node) textFNList.item(0)).getNodeValue().trim());
                    sb.append(((Node) textFNList.item(0)).getNodeValue().trim());
                    //==========SEND================
                    try {
                        app.sendJMSMessageToDest(sb.toString());
                    } catch (NamingException ex) {
                        Logger.getLogger(SendPositionUpdates.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Message :" + s + "----------->" + sb.toString());
                }//end of if clause
            }//end of for loop with s var
        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());

        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();

        } catch (Throwable t) {
            t.printStackTrace();
        }
        //System.out.println(sb.toString());
        String sendMsg = sb.toString();
        return sendMsg;
    }

    public String parseException(String fileName, char type) {
        StringBuilder sb = new StringBuilder();
        //====================================================PARSE XML =========================================================//
        try {

            // pass filename to diffrentiate between kind of update
            
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(fileName + ".xml"));
            // normalize text representation
            doc.getDocumentElement().normalize();
            System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
            NodeList listOfPositions = doc.getElementsByTagName("exceptionEvent");
            int total = listOfPositions.getLength();
            System.out.println("Total no of Exceptions : " + total);
            for (int s = 0; s < listOfPositions.getLength(); s++) {
                sb = new StringBuilder();
                sb.append(type);
                sb.append(",");
                Node firstPersonNode = listOfPositions.item(s);
                if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element firstPersonElement = (Element) firstPersonNode;

                    NodeList lastNameList = firstPersonElement.getElementsByTagName("truckId");
                    Element lastNameElement = (Element) lastNameList.item(0);
                    NodeList textLNList = lastNameElement.getChildNodes();
                    System.out.println("Truck ID : " + ((Node) textLNList.item(0)).getNodeValue().trim());
                    sb.append(((Node) textLNList.item(0)).getNodeValue().trim());
                    sb.append(",");

                    NodeList longList = firstPersonElement.getElementsByTagName("exceptionDescription");
                    Element longElement = (Element) longList.item(0);
                    NodeList textLongList = longElement.getChildNodes();
                    System.out.println("exceptionDescription : " + ((Node) textLongList.item(0)).getNodeValue().trim());
                    sb.append(((Node) textLongList.item(0)).getNodeValue().trim());
                    //==========SEND================
                    try {
                        app.sendJMSMessageToDest(sb.toString());
                    } catch (NamingException ex) {
                        Logger.getLogger(SendPositionUpdates.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }//end of if clause
            }//end of for loop with s var
        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());

        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();

        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.out.println(sb.toString());
        String sendMsg = sb.toString();
        return sendMsg;
    }
}
