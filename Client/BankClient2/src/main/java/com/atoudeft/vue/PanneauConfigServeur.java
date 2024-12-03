package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel {
    private JTextField txtAdrServeur, txtNumPort;

    public PanneauConfigServeur(String adr, int port) {


        //à compléter
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelIP = new JLabel("Adresse IP :");
        txtAdrServeur= new JTextField(15);

        JLabel labelPort = new JLabel("Port :");
        txtNumPort = new JTextField( 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelIP, gbc);

        gbc.gridx = 1;
        panel.add(txtAdrServeur, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelPort, gbc);

        gbc.gridx = 1;
        panel.add(txtNumPort, gbc);
        this.add(panel);


        txtAdrServeur.setText(adr);
        txtNumPort.setText(Integer.toString(port));
    }
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() {
        return txtNumPort.getText();
    }
}
