/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vikaram
 */
@Remote
public interface OmazonLocationEJBRemote {
    /**
     *
     * @param city
     * @return
     */
    List<OmazonLocation> findAllLocationByCity(String city);
    List<OmazonLocation> findAllLocationByLat(BigDecimal lat);
}
