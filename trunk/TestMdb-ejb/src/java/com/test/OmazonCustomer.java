/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vikaram
 */
@Entity
@Table(name = "omazon_customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OmazonCustomer.findAll", query = "SELECT o FROM OmazonCustomer o"),
    @NamedQuery(name = "OmazonCustomer.findByCustomerId", query = "SELECT o FROM OmazonCustomer o WHERE o.customerId = :customerId"),
    @NamedQuery(name = "OmazonCustomer.findByCustomerName", query = "SELECT o FROM OmazonCustomer o WHERE o.customerName = :customerName"),
    @NamedQuery(name = "OmazonCustomer.findByCustomerAddr", query = "SELECT o FROM OmazonCustomer o WHERE o.customerAddr = :customerAddr"),
    @NamedQuery(name = "OmazonCustomer.findByCustomerPhone", query = "SELECT o FROM OmazonCustomer o WHERE o.customerPhone = :customerPhone"),
    @NamedQuery(name = "OmazonCustomer.findByCustomerEmail", query = "SELECT o FROM OmazonCustomer o WHERE o.customerEmail = :customerEmail"),
    @NamedQuery(name = "OmazonCustomer.findAllCustomerId", query = "SELECT o.customerId FROM OmazonCustomer o")})
public class OmazonCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private Integer customerId;
    @Size(max = 45)
    @Column(name = "customer_name")
    private String customerName;
    @Size(max = 45)
    @Column(name = "customer_addr")
    private String customerAddr;
    @Column(name = "customer_phone")
    private Integer customerPhone;
    @Size(max = 45)
    @Column(name = "customer_email")
    private String customerEmail;

    public OmazonCustomer() {
    }

    public OmazonCustomer(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddr() {
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr;
    }

    public Integer getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(Integer customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OmazonCustomer)) {
            return false;
        }
        OmazonCustomer other = (OmazonCustomer) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.OmazonCustomer[ customerId=" + customerId + " ]";
    }
    
}
