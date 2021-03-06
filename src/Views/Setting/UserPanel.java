/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Setting;

import DAOs.FoodTypeDAO;
import DAOs.UserDAO;
import Models.FoodType;
import Models.User;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author patricia.garcia
 */
public class UserPanel extends javax.swing.JPanel {

    /**
     * Creates new form StaffPanel
     */
    public UserPanel() {
        initComponents();
        init();
        initUsers();
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
        tableUser = new javax.swing.JTable();
        javax.swing.JButton BtnAddUSer = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 204));
        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setPreferredSize(new java.awt.Dimension(1500, 1500));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tableUser.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jScrollPane1.setViewportView(tableUser);

        BtnAddUSer.setText("Add");
        BtnAddUSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddUSerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnAddUSer, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(621, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(BtnAddUSer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(957, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAddUSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddUSerActionPerformed
        // TODO add your handling code here:
        new UserAddFrame().setVisible(true);
    }//GEN-LAST:event_BtnAddUSerActionPerformed

    
        //Init the table with a default table
    public void init(){
    defaultTableModel = new javax.swing.table.DefaultTableModel(
            
            //Default table configurations
            new Object [][]{},
            new String[]{
                "User id", "User Name", "Password", "First Name", "Last Name","Phone","Email", "Created at", "Is Active"
            }
    ) 
            {
            Class[] types = new Class[]{
                java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,
                java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,
                java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false,false,false,false,false,false,false,false,false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        
        };
    //Add vaues to the panel table
    tableUser.setModel(defaultTableModel);
}
    
    
    
    
    
    public void initUsers(){
        
        
        UserDAO userDao = new UserDAO();
        ArrayList<User> users = new ArrayList<>();
        Object[] rowData = new Object[9];
        
        users = userDao.searchAll();
        
       
        
        for(int i = 0; i<users.size();i++){
        rowData[0] = users.get(i).getId();
        rowData[1] = users.get(i).getUserName();
        rowData[2] = users.get(i).getPassword();
        rowData[3] = users.get(i).getFirstName();
        rowData[4] = users.get(i).getLastName();
        rowData[5] = users.get(i).getPhone();
        rowData[6] = users.get(i).getEmail();
        rowData[7] = users.get(i).getCreatedAt();
        rowData[8] = users.get(i).isIsActive();
        defaultTableModel.addRow(rowData);
        }
        

    
    }
    

DefaultTableModel defaultTableModel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUser;
    // End of variables declaration//GEN-END:variables
}
