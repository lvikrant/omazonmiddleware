/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vikaram
 */
@Remote
public interface OmazonDeliveryEJBRemote {
    int addOrder(OmazonDelivery del);
    List<OmazonDelivery> findAllDeliveriesByOrderId(int order_id );

    List<OmazonDelivery> findAllDeliveries();
}
