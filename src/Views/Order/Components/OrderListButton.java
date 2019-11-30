/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Order.Components;

import Models.Order;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author PPuarat
 */
public class OrderListButton extends JButton {
    
    public OrderListButton(Order order) {
        super("<HTML><body>"
                + "<p>" + "Order No: " + order.getId() + "</p>"
                + "<p>"+ order.getCreatedAt().substring(0,19) +"</p>"        
                + "</body></HTML>");

        setPreferredSize(new Dimension(158, 80));
    }
}
