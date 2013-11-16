/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omazon.client;

import com.test.OmazonCarrierEJBRemote;
import com.test.OmazonCustomerEJBRemote;
import com.test.OmazonDeliveryEJBRemote;
import com.test.OmazonLocationEJBRemote;
import com.test.OmazonOrderEJBRemote;
import com.test.OmazonProductEJBRemote;
import javax.ejb.EJB;

/**
 *
 * @author dell
 */
public class Main {
    @EJB
    public static OmazonCarrierEJBRemote omazonCarrierEJB;
    @EJB
    public static OmazonCustomerEJBRemote omazonCustomerEJB;
    @EJB
    public static OmazonLocationEJBRemote omazonLocationEJB;
    @EJB
    public static OmazonDeliveryEJBRemote omazonDeliveryEJB;
    @EJB
    public static OmazonOrderEJBRemote omazonOrderEJB;
    @EJB
    public static OmazonProductEJBRemote omazonProductEJB;
 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        index ind = new index();
        ind.setVisible(true);
    }
}
