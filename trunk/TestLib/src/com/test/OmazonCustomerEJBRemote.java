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
public interface OmazonCustomerEJBRemote {
    public int addCustomer(OmazonCustomer cust);
    public StringBuilder returnCustomerEmail(String list);
    public List<OmazonCustomer> getCustomerById(int custId);
     public List<String> getAllCustomerId();
     public void updateCustomerInfo(OmazonCustomer customer);
     public OmazonCustomer getCustInfoByCustId(int custId);

    List<OmazonCustomer> findAllCustomers();
}
