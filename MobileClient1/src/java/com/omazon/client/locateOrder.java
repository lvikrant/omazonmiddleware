/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omazon.client;

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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class locateOrder extends javax.swing.JFrame {
    @EJB
    private static OmazonOrderEJBRemote omazonOrderEJB;
    @EJB
    private static OmazonLocationEJBRemote omazonLocationEJB;

    Common comm = new Common();
    private Connection connect = null;
    /**
     * Creates new form locateOrder
     */
    public locateOrder() {
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

        jLabel1 = new javax.swing.JLabel();
        orderId = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("OMAZON");

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

        jLabel2.setText("Order Id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orderId, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(119, 119, 119))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        index ind = new index();
        ind.setVisible(true);
        close();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        char locSta = 0;
        String locStatus = null;
	String locLocation = null;
        String locReason = null;
        BigDecimal lat = null;
        boolean b;
        List<OmazonOrder> order_list = new ArrayList<OmazonOrder>();
        List<OmazonLocation> location_list = new ArrayList<OmazonLocation>();
        int order_id = Integer.parseInt(orderId.getText());
        b = comm.isUrlReachable("localhost");
        if(b == true) {
            order_list = Main.omazonOrderEJB.getOrderStatus(order_id);
            locSta  = order_list.get(0).getOrderStatus();
            locReason = order_list.get(0).getOrderExceptionDesc();
            lat = order_list.get(0).getOrderStatusLat();
            location_list = Main.omazonLocationEJB.findAllLocationByLat(lat);
            locLocation = location_list.get(0).getLocationCity();
        }
        else
        {
            try 
            {
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection("jdbc:mysql://localhost/locdb1?"+"user=dbuser&password=dbpass");
                String query = "Select order_status, order_exception_desc, order_status_lat from omazon_order where order_id = ?";
                PreparedStatement stmt;
                stmt = connect.prepareStatement(query);
                stmt.setInt(1, order_id);
                ResultSet resultSet = stmt.executeQuery();
                while(resultSet.next()) {
                
                    String status = resultSet.getString(1);
                    locSta = status.charAt(0);
                    locReason = resultSet.getString(2);
                    lat = resultSet.getBigDecimal(3);
                    query = "Select location_city from omazon_location where location_lat = ?";
                    stmt = connect.prepareStatement(query);
                    stmt.setBigDecimal(1, lat);
                    resultSet = stmt.executeQuery();
                    while(resultSet.next()) {
                        locLocation = resultSet.getString(1);
                    }
                }
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            }
            finally {
                try {
                    connect.close();
                } catch (SQLException ex) {
                    Logger.getLogger(addOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if((locSta=='A')) {
            locStatus = "Active";
	}
	else if((locSta=='P')) {
            locStatus = "Pending";
	}
	else if((locSta=='R')) {
            locStatus = "Rejected";
	}
	else if((locSta=='C')) {
            locStatus = "Cancelled";
            locLocation = "NA";
	}
        else if((locSta=='D')) {
            locStatus = "Delivered";
	}
        String Message;
        Message = "Status: " + locStatus + " Location: " + locLocation + " Delay Reason: " + locReason;
        JOptionPane.showMessageDialog(this, Message, "Confirmation", JOptionPane.OK_OPTION);
        clear();
    }//GEN-LAST:event_jButton1ActionPerformed

        public void clear() {
        orderId.setText(null);
    }
    
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
            java.util.logging.Logger.getLogger(locateOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(locateOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(locateOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(locateOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new locateOrder().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField orderId;
    // End of variables declaration//GEN-END:variables
}