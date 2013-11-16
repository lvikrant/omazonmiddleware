/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vikaram
 */
@Entity
@Table(name = "omazon_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OmazonOrder.findAll", query = "SELECT o FROM OmazonOrder o"),
    @NamedQuery(name = "OmazonOrder.findByOrderId", query = "SELECT o FROM OmazonOrder o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "OmazonOrder.findByProductId", query = "SELECT o FROM OmazonOrder o WHERE o.productId = :productId"),
    @NamedQuery(name = "OmazonOrder.findByProductName", query = "SELECT o FROM OmazonOrder o WHERE o.productName = :productName"),
    @NamedQuery(name = "OmazonOrder.findByCustomerId", query = "SELECT o FROM OmazonOrder o WHERE o.customerId = :customerId"),
    @NamedQuery(name = "OmazonOrder.findByCustomerName", query = "SELECT o FROM OmazonOrder o WHERE o.customerName = :customerName"),
    @NamedQuery(name = "OmazonOrder.findByOrderDate", query = "SELECT o FROM OmazonOrder o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "OmazonOrder.findByOrderStartLat", query = "SELECT o FROM OmazonOrder o WHERE o.orderStartLat = :orderStartLat"),
    @NamedQuery(name = "OmazonOrder.findByOrderStartLong", query = "SELECT o FROM OmazonOrder o WHERE o.orderStartLong = :orderStartLong"),
    @NamedQuery(name = "OmazonOrder.findByOrderEndLat", query = "SELECT o FROM OmazonOrder o WHERE o.orderEndLat = :orderEndLat"),
    @NamedQuery(name = "OmazonOrder.findByOrderEndLong", query = "SELECT o FROM OmazonOrder o WHERE o.orderEndLong = :orderEndLong"),
    @NamedQuery(name = "OmazonOrder.findByCustomerPhone", query = "SELECT o FROM OmazonOrder o WHERE o.customerPhone = :customerPhone"),
    @NamedQuery(name = "OmazonOrder.findByCustomerEmail", query = "SELECT o FROM OmazonOrder o WHERE o.customerEmail = :customerEmail"),
    @NamedQuery(name = "OmazonOrder.findByOrderCarrier", query = "SELECT o FROM OmazonOrder o WHERE o.orderCarrier = :orderCarrier"),
    @NamedQuery(name = "OmazonOrder.findByOrderCarrierId", query = "SELECT o FROM OmazonOrder o WHERE o.orderCarrierId = :orderCarrierId"),
    @NamedQuery(name = "OmazonOrder.findByOrderStatusLat", query = "SELECT o FROM OmazonOrder o WHERE o.orderStatusLat = :orderStatusLat"),
    @NamedQuery(name = "OmazonOrder.findByOrderStatusLong", query = "SELECT o FROM OmazonOrder o WHERE o.orderStatusLong = :orderStatusLong"),
    @NamedQuery(name = "OmazonOrder.findByOrderExpectedDeldate", query = "SELECT o FROM OmazonOrder o WHERE o.orderExpectedDeldate = :orderExpectedDeldate"),
    @NamedQuery(name = "OmazonOrder.findByOrderStatus", query = "SELECT o FROM OmazonOrder o WHERE o.orderStatus = :orderStatus"),
    @NamedQuery(name = "OmazonOrder.findByOrderExceptionDesc", query = "SELECT o FROM OmazonOrder o WHERE o.orderExceptionDesc = :orderExceptionDesc"),
    @NamedQuery(name = "OmazonOrder.findAllOrderId", query = "SELECT o.orderId FROM OmazonOrder o"),
    @NamedQuery(name = "OmazonOrder.deleteByOrderId", query = "DELETE FROM OmazonOrder o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "OmazonOrder.updateOrderById", query = "UPDATE OmazonOrder o SET o.orderExpectedDeldate=:orderExpectedDeldate,"+
        "o.orderStatus = :orderStatus,o.orderStatusLat = :orderStatusLat,o.orderStatusLong = :orderStatusLong"
        + " WHERE o.orderId = :orderId")})

public class OmazonOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "product_id")
    private Integer productId;
    @Size(max = 45)
    @Column(name = "product_name")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private int customerId;
    @Size(max = 45)
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "order_start_lat")
    private BigDecimal orderStartLat;
    @Column(name = "order_start_long")
    private BigDecimal orderStartLong;
    @Column(name = "order_end_lat")
    private BigDecimal orderEndLat;
    @Column(name = "order_end_long")
    private BigDecimal orderEndLong;
    @Column(name = "customer_phone")
    private Integer customerPhone;
    @Size(max = 45)
    @Column(name = "customer_email")
    private String customerEmail;
    @Size(max = 45)
    @Column(name = "order_carrier")
    private String orderCarrier;
    @Column(name = "order_carrier_id")
    private Integer orderCarrierId;
    @Column(name = "order_status_lat")
    private BigDecimal orderStatusLat;
    @Column(name = "order_status_long")
    private BigDecimal orderStatusLong;
    @Column(name = "order_expected_deldate")
    @Temporal(TemporalType.DATE)
    private Date orderExpectedDeldate;
    @Column(name = "order_status")
    private Character orderStatus;
    @Size(max = 200)
    @Column(name = "order_exception_desc")
    private String orderExceptionDesc;

    public OmazonOrder() {
    }

    public OmazonOrder(Integer orderId) {
        this.orderId = orderId;
    }

    public OmazonOrder(Integer orderId, int customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOrderStartLat() {
        return orderStartLat;
    }

    public void setOrderStartLat(BigDecimal orderStartLat) {
        this.orderStartLat = orderStartLat;
    }

    public BigDecimal getOrderStartLong() {
        return orderStartLong;
    }

    public void setOrderStartLong(BigDecimal orderStartLong) {
        this.orderStartLong = orderStartLong;
    }

    public BigDecimal getOrderEndLat() {
        return orderEndLat;
    }

    public void setOrderEndLat(BigDecimal orderEndLat) {
        this.orderEndLat = orderEndLat;
    }

    public BigDecimal getOrderEndLong() {
        return orderEndLong;
    }

    public void setOrderEndLong(BigDecimal orderEndLong) {
        this.orderEndLong = orderEndLong;
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

    public String getOrderCarrier() {
        return orderCarrier;
    }

    public void setOrderCarrier(String orderCarrier) {
        this.orderCarrier = orderCarrier;
    }

    public Integer getOrderCarrierId() {
        return orderCarrierId;
    }

    public void setOrderCarrierId(Integer orderCarrierId) {
        this.orderCarrierId = orderCarrierId;
    }

    public BigDecimal getOrderStatusLat() {
        return orderStatusLat;
    }

    public void setOrderStatusLat(BigDecimal orderStatusLat) {
        this.orderStatusLat = orderStatusLat;
    }

    public BigDecimal getOrderStatusLong() {
        return orderStatusLong;
    }

    public void setOrderStatusLong(BigDecimal orderStatusLong) {
        this.orderStatusLong = orderStatusLong;
    }

    public Date getOrderExpectedDeldate() {
        return orderExpectedDeldate;
    }

    public void setOrderExpectedDeldate(Date orderExpectedDeldate) {
        this.orderExpectedDeldate = orderExpectedDeldate;
    }

    public Character getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Character orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderExceptionDesc() {
        return orderExceptionDesc;
    }

    public void setOrderExceptionDesc(String orderExceptionDesc) {
        this.orderExceptionDesc = orderExceptionDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OmazonOrder)) {
            return false;
        }
        OmazonOrder other = (OmazonOrder) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.OmazonOrder[ orderId=" + orderId + " ]";
    }
    
}
