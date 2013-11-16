/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vikaram
 */
@Entity
@Table(name = "omazon_delivery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OmazonDelivery.findAll", query = "SELECT o FROM OmazonDelivery o"),
    @NamedQuery(name = "OmazonDelivery.findByDeliveryId", query = "SELECT o FROM OmazonDelivery o WHERE o.deliveryId = :deliveryId"),
    @NamedQuery(name = "OmazonDelivery.findByOrderId", query = "SELECT o FROM OmazonDelivery o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "OmazonDelivery.findByDeliveryDate", query = "SELECT o FROM OmazonDelivery o WHERE o.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "OmazonDelivery.findByDeliveryRemarks", query = "SELECT o FROM OmazonDelivery o WHERE o.deliveryRemarks = :deliveryRemarks")})
public class OmazonDelivery implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "delivery_id")
    private Integer deliveryId;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    @Size(max = 45)
    @Column(name = "delivery_remarks")
    private String deliveryRemarks;

    public OmazonDelivery() {
    }

    public OmazonDelivery(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryRemarks() {
        return deliveryRemarks;
    }

    public void setDeliveryRemarks(String deliveryRemarks) {
        this.deliveryRemarks = deliveryRemarks;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deliveryId != null ? deliveryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OmazonDelivery)) {
            return false;
        }
        OmazonDelivery other = (OmazonDelivery) object;
        if ((this.deliveryId == null && other.deliveryId != null) || (this.deliveryId != null && !this.deliveryId.equals(other.deliveryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.OmazonDelivery[ deliveryId=" + deliveryId + " ]";
    }
    
}
