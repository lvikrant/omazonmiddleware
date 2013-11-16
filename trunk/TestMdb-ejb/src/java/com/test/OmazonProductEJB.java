/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author vikaram
 */
@Stateful
public class OmazonProductEJB implements OmazonProductEJBRemote {
    static final Logger logger = Logger.getLogger("OmazonMDB");
    @PersistenceContext(unitName = "TestMdb-ejbPU")
    private EntityManager em;
   // private Object entityManager;
    public int addProduct(OmazonProduct product) {
        int randomNumber = new Random().nextInt(9000) + 1000;
        product.setProductId(randomNumber);
        persist(product);
        return randomNumber;
    }
    @Override
    public List<OmazonProduct> findAllProducts() {
        return em.createNamedQuery("OmazonProduct.findAll").getResultList();
    }
    @Override
    public List<OmazonProduct> findAllProductNames(String name) {
        return em.createNamedQuery("OmazonProduct.findByProductName").setParameter("productName",name).getResultList();        
    }
    @Override
    public List<String> findProductNames() {
        return em.createNamedQuery("OmazonProduct.findProductNames").getResultList();
        //return em.createNativeQuery("OmazonProduct.findProductNames").getResultList();
    }
    @Override
    public OmazonProduct getProdInfoByProdId(int prod_id){
          logger.info("Session BEAN --------------------------------------------->");
        List<OmazonProduct> list = em.createNamedQuery("OmazonProduct.findByProductId").setParameter("productId", prod_id).getResultList();
        return list.get(0);
    }
    @Override
    public void updateProductInfo(OmazonProduct product){
    
    int prod_id=product.getProductId();
    OmazonProduct prod = getProdInfoByProdId(prod_id);
    prod.setProductId(product.getProductId());
    prod.setProductName(product.getProductName());
    prod.setProductPrice(product.getProductPrice());
    prod.setProductType(product.getProductType());
    }
    
    public void persist(Object object) {
        em.persist(object);
    }  
}
