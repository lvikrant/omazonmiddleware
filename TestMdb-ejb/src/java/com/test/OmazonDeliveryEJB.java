/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.util.List;
import java.util.Random;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vikaram
 */
@Stateful
public class OmazonDeliveryEJB implements OmazonDeliveryEJBRemote {
 @PersistenceContext(unitName = "TestMdb-ejbPU")
    private EntityManager em;
public void persist(Object object) {
        em.persist(object);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public int addOrder(OmazonDelivery del) {
    int randomNumber = new Random().nextInt(9000) + 1000;
    del.setDeliveryId(randomNumber);    
    persist(del);
        return randomNumber;
    }
    @Override
    public List<OmazonDelivery> findAllDeliveriesByOrderId(int order_id ) {
        return em.createNamedQuery("OmazonDelivery.findByOrderId").setParameter("orderId",order_id).getResultList();
    }

    @Override
    public List<OmazonDelivery> findAllDeliveries() {
        return em.createNamedQuery("OmazonDelivery.findAll").getResultList();
    }

}
