/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vikaram
 */
@Stateful
public class OmazonOrderEJB implements OmazonOrderEJBRemote {
 static final Logger logger = Logger.getLogger("OmazonMDB");
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "TestMdb-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     public void persist(Object object) {
        em.persist(object);
    }
@Override
    public int addOrder(OmazonOrder order) {
    int randomNumber = new Random().nextInt(9000) + 1000;
    order.setOrderId(randomNumber);    
    persist(order);
        return randomNumber;
    }
    @Override
    public List<String> findAllOrderIds()
    {          
        return em.createNamedQuery("OmazonOrder.findAllOrderId").getResultList();
    }
    @Override
    public List<OmazonOrder> findAllOrdersById(int order_id)
    {   
        return em.createNamedQuery("OmazonOrder.findByOrderId").setParameter("orderId", order_id).getResultList();
    }
    @Override
    public int deleteOrder(int order_id) {
        return em.createNamedQuery("OmazonOrder.deleteByOrderId").setParameter("orderId", order_id).executeUpdate();
    }
    
    @Override
    public OmazonOrder getOrderInfoByOrderId(int orderId){
          logger.info("Session BEAN --------------------------------------------->");
        List<OmazonOrder> list = em.createNamedQuery("OmazonOrder.findByOrderId").setParameter("orderId", orderId).getResultList();
        return list.get(0);
    }
    
    @Override
    public void updateOrderInfo(OmazonOrder order){
    int ord_id=order.getOrderId();
    OmazonOrder ord = getOrderInfoByOrderId(ord_id);
    ord.setOrderStatusLat(order.getOrderStatusLat());
    ord.setOrderStatusLong(order.getOrderStatusLong());        
    ord.setOrderExpectedDeldate(order.getOrderExpectedDeldate());
    ord.setOrderStatus(order.getOrderStatus());
    }

    @Override
    public void updateAutomaticDelOrderInfo(OmazonOrder order){
    int ord_id=order.getOrderId();
    OmazonOrder ord = getOrderInfoByOrderId(ord_id);
    ord.setOrderStatus('D');        
    }    
    @Override
    public List<OmazonOrder> findAllOrders() {
        return em.createNamedQuery("OmazonOrder.findAll").getResultList();
    }
    @Override
    public List<OmazonOrder> getOrderStatus(int orderId) {
        return em.createNamedQuery("OmazonOrder.findByOrderId").setParameter("orderId",orderId).getResultList();
    }
    public List<OmazonOrder> getOrderByCarrId(int carrId){
        return em.createNamedQuery("OmazonOrder.findByOrderCarrierId").setParameter("orderCarrierId",carrId).getResultList();
    }
    @Override
    public void updateAutomaticOrderInfo(OmazonOrder order){
    //int ord_id=order.getOrderId();
    logger.info(" --updateAutomaticOrderInfo------------------------------------------->");
    List<OmazonOrder> list = getOrderByCarrId(order.getOrderCarrierId());
    logger.info(" List Size----------------------------------------->"+list.size());
	for (int i = 0; i < list.size(); i++) {
        OmazonOrder ord=list.get(i);       
        ord.setOrderStatusLat(order.getOrderStatusLat());
        ord.setOrderStatusLong(order.getOrderStatusLong());
	}
    }
    @Override
    public StringBuilder updateAutomaticOrderExcInfo(OmazonOrder order){
    StringBuilder sb = new StringBuilder();
    String prefix = "";
    logger.info(" --updateAutomaticOrderExcInfo------------------------------------------->");
    List<OmazonOrder> list = getOrderByCarrId(order.getOrderCarrierId());
    logger.info(" List Size----------------------------------------->"+list.size());
	for (int i = 0; i < list.size(); i++) {
        OmazonOrder ord=list.get(i);       
        ord.setOrderExceptionDesc(order.getOrderExceptionDesc());
        sb.append(prefix);
        prefix = ",";
        sb.append(ord.getCustomerId());
        //sb.append(",");
	}
        return sb;
    }
}
