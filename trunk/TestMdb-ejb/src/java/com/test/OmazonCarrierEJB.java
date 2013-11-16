/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vikaram
 */
@Stateful
public class OmazonCarrierEJB implements OmazonCarrierEJBRemote {
    
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
    public OmazonCarrier getCarrierInfoByCarrId(int carrId){
        logger.info("Session BEAN --------------------------------------------->");
        List<OmazonCarrier> list = em.createNamedQuery("OmazonCarrier.findByCarrierId").setParameter("carrierId", carrId).getResultList();
        return list.get(0);
    }
    @Override
    public void updateAutomaticCarrierInfo(OmazonCarrier carrier){
    logger.info(" --updateCarrierInfo------------------------------------------->");
    int carr_id=carrier.getCarrierId();    
    OmazonCarrier carr = getCarrierInfoByCarrId(carr_id);
    carr.setCarrierLat(carrier.getCarrierLat());
    carr.setCarrierLong(carrier.getCarrierLong());
    }

    @Override
    public List<OmazonCarrier> findAllCarriers() {
        return em.createNamedQuery("OmazonCarrier.findAll").getResultList();
    }
    
}
