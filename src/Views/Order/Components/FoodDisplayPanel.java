/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Order.Components;

import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author PPuarat
 */
public class FoodDisplayPanel extends JPanel {

    public FoodDisplayPanel() {
        init();
    }
    
    private void init(){
        //this.setLayout(new GridLayout(0, 3));
        this.setLayout(new FlowLayout(3, 5, 10));
        
    }
}
