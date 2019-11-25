/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Views.Components.MenuButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author PPuarat
 */
public class Menu extends javax.swing.JPanel {

    private JPanel appPanel;
    private javax.swing.JButton btnNewOrder;
    private javax.swing.JButton btnOrders;
    private javax.swing.JButton btnReports;
    private javax.swing.JButton btnSettings;

    /**
     * Creates new form Menu
     */
    public Menu(JPanel inputJP) {
        appPanel = inputJP;
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(1, 0));
    }// </editor-fold>//GEN-END:initComponents
    private void btnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Menu bar Clicked!");
        
    }

    private void init() {
        btnNewOrder = new MenuButton("New Order");
        btnNewOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });
        
        btnOrders = new MenuButton("Orders");
        btnOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });
        
        btnReports = new MenuButton("Reports");
        btnReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });
        
        btnSettings = new MenuButton("Settings");
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });
        
        add(btnNewOrder);
        add(btnOrders);
        add(btnReports);
        add(btnSettings);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
