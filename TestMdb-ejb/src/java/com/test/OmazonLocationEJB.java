/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vikaram
 */
@Stateful
public class OmazonLocationEJB implements OmazonLocationEJBRemote {
 @PersistenceContext(unitName = "TestMdb-ejbPU")
    private EntityManager em;
   
    @Override
    public List<OmazonLocation> findAllLocationByCity(String city) {
        return em.createNamedQuery("OmazonLocation.findByLocationCity").setParameter("locationCity",city).getResultList();
    }
    @Override
    public List<OmazonLocation> findAllLocationByLat(BigDecimal lat){
        return em.createNamedQuery("OmazonLocation.findByLocationLat").setParameter("locationLat",lat).getResultList();
    }
}
