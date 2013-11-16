/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.Schedule;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.NamingException;

/**
 *
 * @author vikaram
 */
@MessageDriven(mappedName = "jms/mqTest", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MobileMdb implements MessageListener {

    @EJB
    MobileSessionBeanRemote mobile;
    @EJB
    RemoteDBSessionBeanRemote remoteDB;
    static final Logger logger = Logger.getLogger("OmazonMDB");

    public MobileMdb() {
    }

    @Schedule(second = "10", minute = "*", hour = "*", persistent = false)
    public void process() {
        //process queue here. 
    }

    @Override
    public void onMessage(Message message) {
        int param = 2;
        int count = 1;

        TextMessage msg = null;
        if (message instanceof TextMessage) {
            msg = (TextMessage) message;
            String strMsg = null;
            try {
                strMsg = msg.getText();

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
            if (strMsg.charAt(0) == 'C') {
                count++;
            }
            logger.info("String msg at MDB ===============" + strMsg);
            switch (strMsg.charAt(0)) {

                case 'R':
                    sendReadyMessage();
                    break;

                case 'C':
                    if (count == param) {
                        sendCommitMessage(param);
                    }
                    break;
                case 'E':
                    logger.info("No Commit as all participant did not reply!!!!!!!");
                    break;
                default:
                    break;
            }


        }
    }

    public void sendReadyMessage() {
        logger.info("Inside Ready message method ===============");
        try {
            mobile.sendMessage("R", "RC", "RCDest");
        } catch (JMSException ex) {
            Logger.getLogger(MobileMdb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(MobileMdb.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendCommitMessage(int param) {
        //mobile.commit(msg);
        remoteDB.UpdateTables(param);

    }
}
