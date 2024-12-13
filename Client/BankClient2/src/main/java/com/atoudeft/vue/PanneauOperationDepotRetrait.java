package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauOperationDepotRetrait extends JPanel {
    private JTextField txtMontant;


    public PanneauOperationDepotRetrait() {


        //à compléter
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelIP = new JLabel("Quel est le montant :");
        txtMontant = new JTextField(15);


        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelIP, gbc);

        gbc.gridx = 1;
        panel.add(txtMontant, gbc);

        this.add(panel);
        
    }
    public String getMontant() {
        return txtMontant.getText();
    }
}
