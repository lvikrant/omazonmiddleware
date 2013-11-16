/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import javax.ejb.Remote;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author vikaram
 */
@Remote
public interface MobileSessionBeanRemote {
     public void sendMessage(Object messageData,String cf, String qu) throws JMSException, NamingException;
      public void commit(String msg);
}
