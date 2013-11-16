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
public interface OmazonCarrierEJBRemote {
    public OmazonCarrier getCarrierInfoByCarrId(int carrId);    
    public void updateAutomaticCarrierInfo(OmazonCarrier carrier);

    List<OmazonCarrier> findAllCarriers();
}
