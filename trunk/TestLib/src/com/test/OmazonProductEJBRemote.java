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
public interface OmazonProductEJBRemote {
    List<OmazonProduct> findAllProducts();
    public void updateProductInfo(OmazonProduct product);
    public OmazonProduct getProdInfoByProdId(int prod_id);
    List<String> findProductNames();
    int addProduct(OmazonProduct product) ;
  //  void addProduct(OmazonProduct product);
    List<OmazonProduct> findAllProductNames(String name);
}
