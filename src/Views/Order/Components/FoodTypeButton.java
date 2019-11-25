/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Order.Components;

import Utils.Settings;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author PPuarat
 */
public class FoodTypeButton extends JButton {

    public FoodTypeButton(String text) {
        super(text);
        setFont(new Font(Settings.FONT, 1, 15));

    }

}
