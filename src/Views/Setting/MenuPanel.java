/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Setting;

import DAOs.FoodDAO;
import DAOs.FoodTypeDAO;
import Models.Food;
import Models.FoodType;
import Utils.Utils;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author patricia.garcia
 */
public class MenuPanel extends javax.swing.JPanel {

    /**
     * Creates new form MenuPanel
     */
    public MenuPanel() {
        initComponents();
        init();
        initMenu();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableMenu = new javax.swing.JTable();
        BtnAddMenu = new javax.swing.JButton();
        BtnUpdateMenu = new javax.swing.JButton();
        BtnDeleteMenu = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 204));
        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setMinimumSize(new java.awt.Dimension(980, 523));
        setPreferredSize(new java.awt.Dimension(980, 523));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tableMenu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jScrollPane1.setViewportView(tableMenu);

        BtnAddMenu.setText("Add");
        BtnAddMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddMenuActionPerformed(evt);
            }
        });

        BtnUpdateMenu.setText("Update");

        BtnDeleteMenu.setText("Delete");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnAddMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnUpdateMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnDeleteMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnDeleteMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnUpdateMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAddMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAddMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddMenuActionPerformed
        // TODO add your handling code here:
        new MenuAddFrame().setVisible(true);
    }//GEN-LAST:event_BtnAddMenuActionPerformed

public void init(){
    defaultTableModel = new javax.swing.table.DefaultTableModel(
            
            
            new Object [][]{},
            new String[]{
                "ID", "Food Name", "Description", "Price", "Category"
            }
    ) 
            {
            Class[] types = new Class[]{
                java.lang.String.class,java.lang.String.class,java.lang.String.class,
                java.lang.String.class,java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false,false, false,false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        
        };
    tableMenu.setModel(defaultTableModel);
}

    public void initMenu(){
        
        FoodTypeDAO foodTypeDao = new FoodTypeDAO();
        ArrayList<FoodType> foodTypes = new ArrayList<>();
        foodTypes = foodTypeDao.searchAll();
        FoodDAO foodDao = new FoodDAO(foodTypes);
        ArrayList<Food> foods = new ArrayList<>();
        Object[] rowData = new Object[5];
        
        foods = foodDao.searchAll();
        
        
        for(int i = 0; i<foods.size();i++){
            
        rowData[0] = foods.get(i).getId();
        rowData[1] = foods.get(i).getFoodName();
        rowData[2] = foods.get(i).getDescription();
        rowData[3] = foods.get(i).getPrice();
        rowData[4] = foods.get(i).getFoodType().getTypeName();
        defaultTableModel.addRow(rowData);
        }
        
    }
    

DefaultTableModel defaultTableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAddMenu;
    private javax.swing.JButton BtnDeleteMenu;
    private javax.swing.JButton BtnUpdateMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableMenu;
    // End of variables declaration//GEN-END:variables
}