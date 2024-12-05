package com.atoudeft.controleur;

import com.atoudeft.client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;

    public EcouteurOperationsCompte(Client client) {
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //à compléter :
        Object source = e.getSource();
        String nomAction;
        if(source instanceof JButton){
            nomAction = ((JButton)source).getActionCommand();
            switch (nomAction){
                case "EPARGNE":
                    client.envoyer("EPARGNE");
                    break;
            }
        }


    }
}
