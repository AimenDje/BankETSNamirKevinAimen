package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauOperationFacture extends JPanel {
    private JTextField txtMontant;
    private JTextField txtNumeroFacture;
    private JTextField txtDesciption;


    public PanneauOperationFacture() {

        //à compléter
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelMontant = new JLabel("Quel est le montant:");
        txtMontant= new JTextField(15);

        JLabel labelNumeroFacture = new JLabel("Quel est le numéro de facutre :");
        txtNumeroFacture = new JTextField( 15);

        JLabel labelDescription = new JLabel("Descrition:");
        txtDesciption = new JTextField( 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelMontant, gbc);

        gbc.gridx = 1;
        panel.add(txtMontant, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelNumeroFacture, gbc);

        gbc.gridx = 1;
        panel.add(txtNumeroFacture, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelDescription, gbc);

        gbc.gridx = 1;
        panel.add(txtDesciption, gbc);



        this.add(panel);



    }
    public String getMontant() {
        return txtMontant.getText();
    }

    public String getNumeroFacture() {
        return txtNumeroFacture.getText();
    }

    public String getDesciption() {
        return txtDesciption.getText();
    }
}
