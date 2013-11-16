/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author dell
 */
@Stateful
public class RemoteDBSessionBean implements RemoteDBSessionBeanRemote {
     @EJB
    private OmazonOrderEJBRemote omazonOrderEJB;
    @EJB
    private OmazonProductEJBRemote omazonProductEJB;
    @EJB
    private OmazonCustomerEJBRemote omazonCustomerEJB;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void UpdateTables(int param) {
        int index;
        for (index = 1; index <= param; index++) {
            String par = null;
            //par.valueOf(index);
            par = "locdb" + index;
            updateProductTable(par);
            updateCustomerTable(par);
            updateOrderTable(par);
        }
    }

    private void updateCustomerTable(String URL) {
        int index = 0;
        List<OmazonCustomer> omazonCustomerList = null;
        OmazonCustomer customer = null;
        Connection connect = null;
        PreparedStatement ps;
        int rowCount;
        //OmazonCustomerEJB omazonCustomerEJB = null;

        omazonCustomerList = omazonCustomerEJB.findAllCustomers();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/" + URL + "?" + "user=dbuser&password=dbpass");
            String query = "delete from omazon_customer";
            ps = connect.prepareStatement(query);
            rowCount = ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

        while (index != omazonCustomerList.size()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    customer = omazonCustomerList.get(index);
                    index++;
                    connect = DriverManager.getConnection("jdbc:mysql://localhost/" + URL + "?" + "user=dbuser&password=dbpass");
                    String sql = "INSERT INTO omazon_customer (customer_id, customer_name, customer_addr, customer_phone, customer_email)" + "VALUES(?,?,?,?,?)";
                    ps = connect.prepareStatement(sql);
                    ps.setInt(1, customer.getCustomerId());
                    ps.setString(2, customer.getCustomerName());
                    ps.setString(3, customer.getCustomerAddr());
                    ps.setInt(4, customer.getCustomerPhone());
                    ps.setString(5, customer.getCustomerEmail());
                    rowCount = ps.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    try {
                        connect.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } finally {
                try {
                    connect.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    private void updateOrderTable(String URL) {
        int index = 0;
        List<OmazonOrder> omazonOrderList = null;
        OmazonOrder order = null;
        Connection connect = null;
        PreparedStatement ps;
        int rowCount;
      //  OmazonOrderEJB omazonOrderEJB = null;

        omazonOrderList = omazonOrderEJB.findAllOrders();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/" + URL + "?" + "user=dbuser&password=dbpass");
            String query = "delete from omazon_order";
            ps = connect.prepareStatement(query);
            rowCount = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

        while (index != omazonOrderList.size()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    order = omazonOrderList.get(index);
                    index++;
                    connect = DriverManager.getConnection("jdbc:mysql://localhost/" + URL + "?" + "user=dbuser&password=dbpass");
                    String sql = "INSERT INTO omazon_order (order_id, product_id, product_name, customer_id, order_date, order_start_lat, order_start_long, order_end_lat, "
                            + "order_end_long, order_carrier, order_carrier_id, order_status_lat, order_status_long, order_expected_deldate, order_status, order_exception_desc)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    ps = connect.prepareStatement(sql);
                    ps.setInt(1, order.getOrderId());
                    ps.setInt(2, order.getProductId());
                    ps.setString(3, order.getProductName());
                    ps.setInt(4, order.getCustomerId());
                    java.sql.Date sqlDate = new java.sql.Date(order.getOrderDate().getTime());
                    ps.setDate(5, sqlDate);
                    ps.setBigDecimal(6, order.getOrderStartLat());
                    ps.setBigDecimal(7, order.getOrderStartLong());
                    ps.setBigDecimal(8, order.getOrderEndLat());
                    ps.setBigDecimal(9, order.getOrderEndLong());
                    ps.setString(10, order.getOrderCarrier());
                    ps.setInt(11, order.getOrderCarrierId());
                    ps.setBigDecimal(12, order.getOrderStatusLat());
                    ps.setBigDecimal(13, order.getOrderStatusLong());
                    sqlDate = new java.sql.Date(order.getOrderExpectedDeldate().getTime());
                    ps.setDate(14, sqlDate);
                    ps.setString(15, order.getOrderStatus().toString());
                    ps.setString(16, order.getOrderExceptionDesc());
                    rowCount = ps.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    try {
                        connect.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } finally {
                try {
                    connect.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    private void updateProductTable(String URL) {
        int index = 0;
        List<OmazonProduct> omazonProductList = null;
        OmazonProduct product = null;
        Connection connect = null;
        PreparedStatement ps;
        int rowCount;
        //OmazonProductEJB omazonProductEJB = null;
        omazonProductList = omazonProductEJB.findAllProducts();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/" + URL + "?" + "user=dbuser&password=dbpass");
            String query = "delete from "+URL+".omazon_product";
            ps = connect.prepareStatement(query);
            rowCount = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

        while (index != omazonProductList.size()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    product = omazonProductList.get(index);
                    index++;
                    connect = DriverManager.getConnection("jdbc:mysql://localhost/" + URL + "?" + "user=dbuser&password=dbpass");
                    String sql = "INSERT INTO omazon_product (product_id, product_name, product_type, product_price) " + "VALUES(?,?,?,?)";
                    ps = connect.prepareStatement(sql);
                    ps.setInt(1, product.getProductId());
                    ps.setString(2, product.getProductName());
                    ps.setString(3, product.getProductType());
                    ps.setInt(4, product.getProductPrice());
                    rowCount = ps.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    try {
                        connect.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } finally {
                try {
                    connect.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}