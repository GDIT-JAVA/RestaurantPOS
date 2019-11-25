/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Order.Components;

import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author PPuarat
 */
public class FoodButton extends JButton{

    public FoodButton(String text) {
        super("<HTML><body><p>"+text+"</HTML></body></p>");
        setPreferredSize(new Dimension(158, 80));
    }
    
}
