/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.util.ArrayList;
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
public class OmazonCustomerEJB implements OmazonCustomerEJBRemote {

    static final Logger logger = Logger.getLogger("OmazonMDB");
    @PersistenceContext(unitName = "TestMdb-ejbPU")
    private EntityManager em;
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public int addCustomer(OmazonCustomer cust) {
        logger.info("Inside Add=============================>");
        int randomNumber = new Random().nextInt(9000) + 1000;
        cust.setCustomerId(randomNumber);
        persist(cust);
        return randomNumber;
    }  
    @Override
    public List<OmazonCustomer> getCustomerById(int custId)
    {
        return ( em.createNamedQuery("OmazonCustomer.findByCustomerId").setParameter("customerId",custId).getResultList());
    }
    @Override
    public List<String> getAllCustomerId()
    {
        return em.createNamedQuery("OmazonCustomer.findAllCustomerId").getResultList();
    }
    @Override
    public OmazonCustomer getCustInfoByCustId(int custId){
          logger.info("Session BEAN --------------------------------------------->");
        List<OmazonCustomer> list = em.createNamedQuery("OmazonCustomer.findByCustomerId").setParameter("customerId", custId).getResultList();
        return list.get(0);
    }
    @Override
    public void updateCustomerInfo(OmazonCustomer customer){
    int cust_id=customer.getCustomerId();
    OmazonCustomer cust = getCustInfoByCustId(cust_id);
    cust.setCustomerName(customer.getCustomerName());
    cust.setCustomerAddr(customer.getCustomerAddr());
    cust.setCustomerEmail(customer.getCustomerEmail());
    cust.setCustomerPhone(customer.getCustomerPhone());
    }
    @Override
    public StringBuilder returnCustomerEmail(String list)
    {
        StringBuilder emailList = new StringBuilder();
        String[] arr = list.split(",");
        List<OmazonCustomer> custList;
        custList = new ArrayList<OmazonCustomer>();
        int i =0;
        String prefix = "";
        logger.info("!!!!!!!!!!!!!!!!Array length: "+arr.length);
        while(i<arr.length)
        {   
            logger.info("!!!!!!!!!!!!!!!!For Cust id: "+arr[i]);
            custList=getCustomerById(Integer.parseInt(arr[i]));
            OmazonCustomer cust = custList.get(0);
            emailList.append(prefix);
            prefix = ",";
            emailList.append(cust.getCustomerEmail());
            i++;
            logger.info("!!Email list===============: "+emailList.toString());
        }     
        return emailList;
    }

    @Override
    public List<OmazonCustomer> findAllCustomers() {
        return em.createNamedQuery("OmazonCustomer.findAll").getResultList();
    }
}
