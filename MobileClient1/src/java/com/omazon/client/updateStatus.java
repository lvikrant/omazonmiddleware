/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omazon.client;

import com.test.OmazonDelivery;
import com.test.OmazonDeliveryEJBRemote;
import com.test.OmazonLocation;
import com.test.OmazonLocationEJBRemote;
import com.test.OmazonOrder;
import com.test.OmazonOrderEJBRemote;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class updateStatus extends javax.swing.JFrame {
    @EJB
    private static OmazonLocationEJBRemote omazonLocationEJB;

    @EJB
    private static OmazonDeliveryEJBRemote omazonDeliveryEJB;
    @EJB
    private static OmazonOrderEJBRemote omazonOrderEJB;

    private int radioValue = 0;
    private Connection connect;
    private Statement statement;
    Common comm = new Common();
    /**
     * Creates new form updateStatus
     */
    public updateStatus() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        orderId = new javax.swing.JTextField();
        status = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        currentLocation = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        orderDate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        orderRemarks = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("OMAZON");

        jLabel3.setText("Order Id");

        orderId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderIdActionPerformed(evt);
            }
        });

        buttonGroup1.add(status);
        status.setText("Pending");
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Rejected");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Delivered");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Status");

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Location");

        currentLocation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Frankfurt", "Munich", "Hamburg", "Berlin", "Cologne", "Bonn" }));
        currentLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentLocationActionPerformed(evt);
            }
        });

        jLabel5.setText("Order Date (DD-MM-YYYY)");

        jLabel6.setText("Remarks");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButton3)
                            .addComponent(status)
                            .addComponent(jRadioButton2)
                            .addComponent(orderDate)
                            .addComponent(currentLocation, 0, 114, Short.MAX_VALUE)
                            .addComponent(orderId)
                            .addComponent(orderRemarks, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                        .addGap(53, 53, 53))))
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(orderDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderRemarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
        radioValue = 1;
    }//GEN-LAST:event_statusActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        radioValue = 3;
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        index ind = new index();
        ind.setVisible(true);
        close();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int flag = 0;
        String status = null;
        int del_id =0;
        BigDecimal start_lat = null;
        BigDecimal start_long = null;
        boolean b;
            List<OmazonLocation> location_list = new ArrayList<OmazonLocation>();
            if(radioValue == 1)
            {
                status = "Pending";
            }
            else if(radioValue == 2)
            {
                status = "Rejected";
            }
            else if(radioValue == 3)
            {
                status = "Delivered"; 
            }
            DateFormat formatter = null;
            Date convertedDate = null;
            formatter = new SimpleDateFormat("dd-MM-yyyy");
            char charstatus = status.charAt(0);
            OmazonOrder order = new OmazonOrder();
            order.setOrderStatus(charstatus);
            int ord_id = Integer.parseInt(orderId.getText());
            order.setOrderId(ord_id);
            String upd_loc = (String) currentLocation.getSelectedItem();
            b = comm.isUrlReachable("localhost");
            if(b == true) {
                location_list = Main.omazonLocationEJB.findAllLocationByCity(upd_loc);
                start_lat = location_list.get(0).getLocationLat();
                start_long = location_list.get(0).getLocationLong();
            }
            else {
                try 
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://localhost/locdb1?"+"user=dbuser&password=dbpass");
                    String query = "Select location_lat, location_long from omazon_location where location_city = ?";
                    PreparedStatement stmt = connect.prepareStatement(query);
                    stmt.setString(1, upd_loc);
                    ResultSet resultSet = stmt.executeQuery();
                    while(resultSet.next()) {
                        start_lat = resultSet.getBigDecimal(1);
                        start_long = resultSet.getBigDecimal(2);
                    }
                 }
                catch (Exception ex) {
                    System.out.println("1"+ex);
                }
                finally {
                try {
                    connect.close();
                } catch (SQLException ex) {
                    Logger.getLogger(addOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
            
            order.setOrderStatusLat(start_lat);
            order.setOrderStatusLong(start_long);
            String del_date = orderDate.getText();
        try {
            convertedDate = (Date) formatter.parse(del_date);
        } catch (ParseException ex) {
            Logger.getLogger(updateStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
            order.setOrderExpectedDeldate(convertedDate);
            if(b == true) {
                Main.omazonOrderEJB.updateOrderInfo(order);
            }
            else {
                try 
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://localhost/locdb1?"+"user=dbuser&password=dbpass");
                    String query = "update omazon_order set order_status =?, order_status_lat=?, order_status_long=?, order_expected_deldate=? where order_id= ?";
                    statement = connect.createStatement();
                    PreparedStatement ps = connect.prepareStatement(query);
                    ps.setString(1, status.valueOf(charstatus));
                    ps.setBigDecimal(2, start_lat);
                    ps.setBigDecimal(3, start_long);
                    java.sql.Date sqlDate = new java.sql.Date(convertedDate.getTime());
                    ps.setDate(4, sqlDate);
                    ps.setInt(5, ord_id);
                    ps.executeUpdate();
                }
                catch(Exception exce) {
                    System.out.println(exce);
                }
                finally {
                    try {
                        connect.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(addOrder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (charstatus == 'D') {
                OmazonDelivery delivery = new OmazonDelivery();
                delivery.setOrderId(ord_id);
                delivery.setDeliveryDate(convertedDate);
                delivery.setDeliveryRemarks(orderRemarks.getText());
                if(b == true) {
                    del_id = Main.omazonDeliveryEJB.addOrder(delivery);
                }
                else
                {
                    try 
                    {
                        Class.forName("com.mysql.jdbc.Driver");
                        connect = DriverManager.getConnection("jdbc:mysql://localhost/locdb1?"+"user=dbuser&password=dbpass");
                        String sql = "INSERT INTO omazon_delivery (delivery_id, order_id, delivery_date, delivery_remarks) VALUES(?,?,?,?) ";
                        PreparedStatement ps = connect.prepareStatement(sql);
                        int randomNumber = new Random().nextInt(9000) + 1000;
                        ps.setInt(1,randomNumber);
                        ps.setInt(2, ord_id);
                        java.sql.Date sqlDate = new java.sql.Date(convertedDate.getTime());
                        ps.setDate(3, sqlDate);
                        ps.setString(4, orderRemarks.getText());
                        del_id = randomNumber;
                    }
                    catch (Exception e2)
                    {
                        
                    }
                    finally {
                        try {
                            connect.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(addOrder.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Successfully Updated", "Confirmation", JOptionPane.OK_OPTION);
            clear();        
    }//GEN-LAST:event_jButton1ActionPerformed
    public void clear() {
        orderId.setText(null);
        currentLocation.setSelectedItem("Select");
        orderDate.setText(null);
        orderRemarks.setText(null);
    }
    private void currentLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentLocationActionPerformed

    private void orderIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderIdActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        radioValue = 2;
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    public void close() {
        WindowEvent winclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winclose);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(updateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(updateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(updateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(updateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new updateStatus().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox currentLocation;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JTextField orderDate;
    private javax.swing.JTextField orderId;
    private javax.swing.JTextField orderRemarks;
    private javax.swing.JRadioButton status;
    // End of variables declaration//GEN-END:variables
}
