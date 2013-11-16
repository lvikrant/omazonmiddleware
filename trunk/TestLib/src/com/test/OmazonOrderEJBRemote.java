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
public interface OmazonOrderEJBRemote {

   int addOrder(OmazonOrder order);  
    List<OmazonOrder> getOrderStatus(int orderId);
    List<OmazonOrder> findAllOrders();
    List<String> findAllOrderIds();
    int deleteOrder(int order_id);
    List<OmazonOrder> findAllOrdersById(int order_id);
   void updateOrderInfo(OmazonOrder order);
   OmazonOrder getOrderInfoByOrderId(int orderId);
   public void updateAutomaticOrderInfo(OmazonOrder order);
   public void updateAutomaticDelOrderInfo(OmazonOrder order);
   public StringBuilder updateAutomaticOrderExcInfo(OmazonOrder order);    
}
