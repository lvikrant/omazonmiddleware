///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.test;
//
//import javax.annotation.Resource;
//import javax.ejb.Stateless;
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSException;
//import javax.jms.MessageProducer;
//import javax.jms.Queue;
//import javax.jms.QueueConnectionFactory;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//
///**
// *
// * @author vikaram
// */
//@Stateless
//public class NewSessionBean implements NewSessionBeanRemote {
//
//    @Resource(mappedName = "jms/ConnectionFactory")
//   // private static QueueConnectionFactory connectionFactory;
//    private static ConnectionFactory connectionFactory;
//    
//    @Resource(mappedName = "jms/mqTest")
//    private static Queue queue;
//
//    public static void main(String[] args) throws JMSException {
//        StringBuilder sb = new StringBuilder();
//
//        //====================================================PARSE XML =========================================================//
////        try {
////
////            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
////            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
////            Document doc = docBuilder.parse(new File("omazonStatus.xml"));
////            // normalize text representation
////            doc.getDocumentElement().normalize();
////            System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
////            NodeList listOfPositions = doc.getElementsByTagName("root");
////            int total = listOfPositions.getLength();
////            System.out.println("Total no of people : " + total);
////            for (int s = 0; s < listOfPositions.getLength(); s++) {
////
////                Node firstPersonNode = listOfPositions.item(s);
////                if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {
////
////                    Element firstPersonElement = (Element) firstPersonNode;
////                    NodeList firstNameList = firstPersonElement.getElementsByTagName("shipmentId");
////                    Element firstNameElement = (Element) firstNameList.item(0);
////                    NodeList textFNList = firstNameElement.getChildNodes();
////                    System.out.println("Shipment id : "+ ((Node) textFNList.item(0)).getNodeValue().trim());
////                    sb.append(((Node) textFNList.item(0)).getNodeValue().trim());
////                    sb.append(",");
////                    
////                    NodeList lastNameList = firstPersonElement.getElementsByTagName("truckId");
////                    Element lastNameElement = (Element) lastNameList.item(0);
////                    NodeList textLNList = lastNameElement.getChildNodes();
////                    System.out.println("Truck ID : " + ((Node)textLNList.item(0)).getNodeValue().trim());
////                    sb.append(((Node)textLNList.item(0)).getNodeValue().trim());
////                    sb.append(",");
////                    
////                    NodeList ageList = firstPersonElement.getElementsByTagName("lat");
////                    Element ageElement = (Element)ageList.item(0);
////                    NodeList textAgeList = ageElement.getChildNodes();
////                    System.out.println("Lat : " + ((Node)textAgeList.item(0)).getNodeValue().trim());
////                    sb.append(((Node)textAgeList.item(0)).getNodeValue().trim());
////                    sb.append(",");
////                    
////                    NodeList longList = firstPersonElement.getElementsByTagName("long");
////                    Element longElement = (Element)longList.item(0);
////                    NodeList textLongList = longElement.getChildNodes();
////                    System.out.println("Lat : " + ((Node)textLongList.item(0)).getNodeValue().trim());
////                    sb.append(((Node)textLongList.item(0)).getNodeValue().trim());
////                }//end of if clause
////            }//end of for loop with s var
////        } catch (SAXParseException err) {
////            System.out.println("** Parsing error" + ", line "
////                    + err.getLineNumber() + ", uri " + err.getSystemId());
////            System.out.println(" " + err.getMessage());
////
////        } catch (SAXException e) {
////            Exception x = e.getException();
////            ((x == null) ? e : x).printStackTrace();
////
////        } catch (Throwable t) {
////            t.printStackTrace();
////        }
////        System.out.println(sb.toString());
////        String sendMsg = sb.toString();
//
//        //===============================================QUEUE==============================================        
//         new NewSessionBean().produceMessages();
//    }
//    public void produceMessages()
//     {
//     MessageProducer messageProducer = null;
//     TextMessage textMessage;
//   Connection connection = null;
//   Session session = null;
//    try
//    {
//      connection = connectionFactory.createConnection();
//      session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//      messageProducer = session.createProducer(queue);
//      textMessage = session.createTextMessage();
//
//      textMessage.setText("Testing, 1, 2, 3. Can you hear me?");
//      System.out.println("Sending the following message: "
//        + textMessage.getText());
//      messageProducer.send(textMessage);
//
//      textMessage.setText("Do you copy?");
//      System.out.println("Sending the following message: "
//        + textMessage.getText());
//      messageProducer.send(textMessage);
//
//      textMessage.setText("Good bye!");
//      System.out.println("Sending the following message: "
//        + textMessage.getText());
//      messageProducer.send(textMessage);
//
//      messageProducer.close();
//      session.close();
//      connection.close();
//    }
//    catch (JMSException e)
//    {
//      e.printStackTrace();
//    }
//  }
//
//}
