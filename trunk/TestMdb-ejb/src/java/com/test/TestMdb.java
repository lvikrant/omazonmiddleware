/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;

/**
 *
 * @author vikaram
 */
@MessageDriven(mappedName = "jms/dest", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class TestMdb implements MessageListener {

    String arr[] = null;
    @EJB
    private OmazonOrderEJBRemote omazonOrderEJB;
    @EJB
    private OmazonCarrierEJBRemote omazonCarrierEJB;
    @EJB
    private OmazonCustomerEJBRemote omazonCustomerEJB;
    static final Logger logger = Logger.getLogger("OmazonMDB");

    public TestMdb() {
    }

    //@Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        if (message instanceof TextMessage) {
            msg = (TextMessage) message;
            String strMsg = null;
            try {
                strMsg = msg.getText();
            } catch (JMSException ex) {
                Logger.getLogger(TestMdb.class.getName()).log(Level.SEVERE, null, ex);
            }
            logger.info("String msg ===============" + strMsg);
            arr = strMsg.split(",");
            String updateType = arr[0];
            logger.info("Update type ===============" + updateType);
            switch (updateType.charAt(0)) {
                case 'P':
                    updPosition(arr);
                    break;

                case 'S':
                    updateStatus(arr);
                    break;

                case 'E':
                    sendException(arr);
                    break;
                default:
                    logger.info("Invalid update---------------------------->");
                    break;
            }
        } else {
            logger.warning(
                    "Message of wrong type: "
                    + message.getClass().getName());
        }
    }

    public void updPosition(String arr[]) {


        logger.info("No. of updates received---------------------> " + arr.length);
        for (int i = 0; i < arr.length; i++) {
            logger.info("+++++For Elements================" + arr[i]);
        }
        try {
            OmazonOrder order = new OmazonOrder();
            order.setOrderCarrierId(Integer.parseInt(arr[1]));
            order.setOrderStatusLat(new BigDecimal(arr[2]));
            order.setOrderStatusLong(new BigDecimal(arr[3]));
            omazonOrderEJB.updateAutomaticOrderInfo(order);
            OmazonCarrier carrier = new OmazonCarrier();
//                logger.info("+++++Try Elements================" + arr[1]);
//                logger.info("+++++Try Elements================" + new BigDecimal(arr[2]));
//                logger.info("+++++Try Elements================" + new BigDecimal(arr[3]));
            carrier.setCarrierId(Integer.parseInt(arr[1]));
            carrier.setCarrierLat(new BigDecimal(arr[2]));
            carrier.setCarrierLong(new BigDecimal(arr[3]));
            omazonCarrierEJB.updateAutomaticCarrierInfo(carrier);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(String arr[]) {
        OmazonOrder order = new OmazonOrder();
        for (int i = 0; i < arr.length; i++) {
            logger.info("++++++Elements================" + arr[i]);
        }
        try {
            order.setOrderId(Integer.parseInt(arr[1]));
            omazonOrderEJB.updateAutomaticDelOrderInfo(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendException(String arr[]) {
        String msg = arr[2];
        SendEmail send;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        send = new SendEmail();
        OmazonOrder order = new OmazonOrder();
        order.setOrderCarrierId(Integer.parseInt(arr[1]));
        order.setOrderExceptionDesc(arr[2]);
        sb = omazonOrderEJB.updateAutomaticOrderExcInfo(order);
        logger.info("Customer ID List=============> " + sb.toString());
        sb2 = omazonCustomerEJB.returnCustomerEmail(sb.toString());
        logger.info("Customer EMAIL List=============> " + sb2.toString());
        try {
            send.Send("omazoninc", "middleware", "Problem with your Shipment", msg, sb2.toString());
        } catch (AddressException ex) {
            Logger.getLogger(TestMdb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(TestMdb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}