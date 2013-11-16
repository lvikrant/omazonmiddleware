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
@Table(name = "omazon_product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OmazonProduct.findAll", query = "SELECT o FROM OmazonProduct o"),
    @NamedQuery(name = "OmazonProduct.findByProductId", query = "SELECT o FROM OmazonProduct o WHERE o.productId = :productId"),
    @NamedQuery(name = "OmazonProduct.findByProductName", query = "SELECT o FROM OmazonProduct o WHERE o.productName = :productName"),
    @NamedQuery(name = "OmazonProduct.findByProductType", query = "SELECT o FROM OmazonProduct o WHERE o.productType = :productType"),
    @NamedQuery(name = "OmazonProduct.findByProductPrice", query = "SELECT o FROM OmazonProduct o WHERE o.productPrice = :productPrice"),
     @NamedQuery(name = "OmazonProduct.findIdByProductName", query = "SELECT o FROM OmazonProduct o WHERE o.productName = :productName")})
public class OmazonProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_id")
    private Integer productId;
    @Size(max = 45)
    @Column(name = "product_name")
    private String productName;
    @Size(max = 45)
    @Column(name = "product_type")
    private String productType;
    @Column(name = "product_price")
    private Integer productPrice;

    public OmazonProduct() {
    }

    public OmazonProduct(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OmazonProduct)) {
            return false;
        }
        OmazonProduct other = (OmazonProduct) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.OmazonProduct[ productId=" + productId + " ]";
    }
    
}
