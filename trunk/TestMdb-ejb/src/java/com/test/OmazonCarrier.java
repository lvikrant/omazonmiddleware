/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vikaram
 */
@Entity
@Table(name = "omazon_carrier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OmazonCarrier.findAll", query = "SELECT o FROM OmazonCarrier o"),
    @NamedQuery(name = "OmazonCarrier.findByCarrierId", query = "SELECT o FROM OmazonCarrier o WHERE o.carrierId = :carrierId"),
    @NamedQuery(name = "OmazonCarrier.findByCarrierLat", query = "SELECT o FROM OmazonCarrier o WHERE o.carrierLat = :carrierLat"),
    @NamedQuery(name = "OmazonCarrier.findByCarrierLong", query = "SELECT o FROM OmazonCarrier o WHERE o.carrierLong = :carrierLong")})
public class OmazonCarrier implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Logger logger = Logger.getLogger("OmazonMDB");
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "carrier_id")
    private Integer carrierId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "carrier_lat")
    private BigDecimal carrierLat;
    @Column(name = "carrier_long")
    private BigDecimal carrierLong;

    public OmazonCarrier() {
        logger.info("Inside Carrier Constructor--------------------=========");
    }

    public OmazonCarrier(Integer carrierId) {
        this.carrierId = carrierId;
    }

    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }

    public BigDecimal getCarrierLat() {
        return carrierLat;
    }

    public void setCarrierLat(BigDecimal carrierLat) {
        this.carrierLat = carrierLat;
    }

    public BigDecimal getCarrierLong() {
        return carrierLong;
    }

    public void setCarrierLong(BigDecimal carrierLong) {
        this.carrierLong = carrierLong;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carrierId != null ? carrierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OmazonCarrier)) {
            return false;
        }
        OmazonCarrier other = (OmazonCarrier) object;
        if ((this.carrierId == null && other.carrierId != null) || (this.carrierId != null && !this.carrierId.equals(other.carrierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.OmazonCarrier[ carrierId=" + carrierId + " ]";
    }
    
}
