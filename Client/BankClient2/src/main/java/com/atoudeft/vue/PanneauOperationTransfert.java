package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauOperationTransfert extends JPanel {
    private JTextField txtMontant;
    private JTextField txtNumeroCompteInitial;
    private JTextField txtNumeroCompteFinal;


    public PanneauOperationTransfert() {

        //à compléter
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelMontant = new JLabel("Quel est le montant:");
        txtMontant= new JTextField(15);

        JLabel labelCompteInitial = new JLabel("De quel compte :");
        txtNumeroCompteInitial = new JTextField( 15);

        JLabel labelCompteFinal = new JLabel("Vers quel compte :");
        txtNumeroCompteFinal = new JTextField( 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelMontant, gbc);

        gbc.gridx = 1;
        panel.add(txtMontant, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelCompteInitial, gbc);

        gbc.gridx = 1;
        panel.add(txtNumeroCompteInitial, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelCompteFinal, gbc);

        gbc.gridx = 1;
        panel.add(txtNumeroCompteFinal, gbc);



        this.add(panel);



    }
    public String getMontant() {
        return txtMontant.getText();
    }

    public String getCompteOrigine() {
        return txtNumeroCompteInitial.getText();
    }

    public String getCompteDestinataire() {
        return txtNumeroCompteFinal.getText();
    }
}
