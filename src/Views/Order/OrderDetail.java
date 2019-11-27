/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Order;

import Controller.OrderDetailController;
import Models.Food;
import Utils.Utils;
import Views.Order.Components.FoodButton;
import Views.Order.Components.FoodDisplayPanel;
import Views.Order.Components.FoodTypeButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PPuarat
 */
public class OrderDetail extends javax.swing.JPanel {

    /**
     * Creates new form OrderDetail
     */
    public OrderDetail() {
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

        detailPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderDetailTable = new javax.swing.JTable();
        paymentBtn = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        foodTypePanel = new javax.swing.JPanel();
        foodPanel = new javax.swing.JPanel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

        detailPanel.setBackground(new java.awt.Color(255, 255, 204));
        detailPanel.setPreferredSize(new java.awt.Dimension(500, 675));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        orderDetailTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        orderDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        orderDetailTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        orderDetailTable.setAutoscrolls(false);
        orderDetailTable.setMaximumSize(new java.awt.Dimension(0, 2000));
        orderDetailTable.setRowHeight(25);
        jScrollPane1.setViewportView(orderDetailTable);

        paymentBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        paymentBtn.setText("Pay");
        paymentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentBtnActionPerformed(evt);
            }
        });

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTotal.setText("Total:");

        javax.swing.GroupLayout detailPanelLayout = new javax.swing.GroupLayout(detailPanel);
        detailPanel.setLayout(detailPanelLayout);
        detailPanelLayout.setHorizontalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addGap(124, 124, 124)
                        .addComponent(paymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(728, Short.MAX_VALUE))
        );
        detailPanelLayout.setVerticalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(detailPanel);

        foodTypePanel.setBackground(new java.awt.Color(255, 255, 255));
        foodTypePanel.setPreferredSize(new java.awt.Dimension(200, 0));
        foodTypePanel.setLayout(new java.awt.GridLayout(0, 1, 5, 10));
        add(foodTypePanel);

        foodPanel.setBackground(new java.awt.Color(204, 204, 204));
        foodPanel.setPreferredSize(new java.awt.Dimension(500, 0));
        foodPanel.setLayout(new java.awt.GridLayout(1, 1));
        add(foodPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void paymentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentBtnActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_paymentBtnActionPerformed

    private void init() {
        OrderDetailController orderDetailCon = new OrderDetailController();
        displayFoods(orderDetailCon.loadFoods());
        orderdFood = new ArrayList<Food>();
        //init table
        orderDetailTableModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Name", "Price"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        orderDetailTable.setModel(orderDetailTableModel);
    }

    private void displayFoods(Map<String, ArrayList<Food>> map) {
        foodBtnMap = new HashMap<>();
        foodPanelMap = new HashMap<>();
        foodTypesBTNList = new ArrayList<>();

        boolean isExecuted = false;

        for (String key : map.keySet()) {
            JButton btnFoodType = new FoodTypeButton(key);

            btnFoodType.addActionListener((java.awt.event.ActionEvent evt) -> {
                switchFoodPanel(evt, key);
            });

            foodTypesBTNList.add(btnFoodType);
            foodTypePanel.add(btnFoodType);

            //Food's Panel for each foodType
            JPanel foodDisplayPanel = new FoodDisplayPanel();

            for (Food food : map.get(key)) {

                //System.out.println(key + ":" + food.getFoodName());
                JButton btnFood = new FoodButton(food.getFoodName());
                btnFood.addActionListener((java.awt.event.ActionEvent evt) -> {
                    orderFood(evt, food);
                });
                //Use Later
                foodBtnMap.put(food.getId(), btnFood);

                foodDisplayPanel.add(btnFood);//TODO Switch between these panels

            }

            //Food pane for each type of food
            foodPanelMap.put(key, foodDisplayPanel);
            //Initial first food panel with first food type
            if (!isExecuted) {
                switchFoodPanel(null, key);
                isExecuted = true;
            }

        }

    }

    public void switchFoodPanel(java.awt.event.ActionEvent evt, String key) {

        foodPanel.removeAll();

        foodPanel.add(foodPanelMap.get(key));
        foodPanel.repaint();
        foodPanel.revalidate();

    }
    //This method will triggered after you clicked on food button
    public void orderFood(java.awt.event.ActionEvent evt, Food food) {
        Object[] rowData = new Object[2];
        rowData[0] = food.getFoodName();
        rowData[1] = food.getPrice();
        orderdFood.add(food);
        lblTotal.setText("Total: " + Utils.calculateTotalPrice(orderdFood));
        orderDetailTableModel.addRow(rowData);
    }

    ArrayList<JButton> foodTypesBTNList;
    Map<Long, JButton> foodBtnMap;
    Map<String, JPanel> foodPanelMap;
    DefaultTableModel orderDetailTableModel;
    ArrayList<Food> orderdFood;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel detailPanel;
    private javax.swing.JPanel foodPanel;
    private javax.swing.JPanel foodTypePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable orderDetailTable;
    private javax.swing.JButton paymentBtn;
    // End of variables declaration//GEN-END:variables
}
