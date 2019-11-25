/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import Utils.Settings;
import java.awt.Color;

/**
 *
 * @author PPuarat
 */
public class MenuButton extends javax.swing.JButton {

    public MenuButton(String text) {
        super(text);
        this.setBorder(null);
        //this.setBackground(new Color(100, 100, 241));
        this.setPreferredSize(new java.awt.Dimension(110, 70));
        this.setFont(new java.awt.Font(Settings.FONT, 0, 30));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        //this.setContentAreaFilled(false);
    }

}
