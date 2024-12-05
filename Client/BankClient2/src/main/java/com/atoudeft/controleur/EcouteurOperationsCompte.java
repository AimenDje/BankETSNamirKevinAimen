package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauOperationsCompte;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private PanneauOperationsCompte panneauOperationsCompte;

    public EcouteurOperationsCompte(Client client, PanneauOperationsCompte panneauOperationsCompte) {
        this.client = client;
        this.panneauOperationsCompte = panneauOperationsCompte;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //à compléter :
        Object source = e.getSource();
        String nomAction;
        if (source instanceof JButton) {
            nomAction = ((JButton) source).getActionCommand();
            switch (nomAction) {
                case "DEPOT":
                    System.out.println("DEPOT");
                    break;
                case "RETRAIT":
                    System.out.println("RETRAIT");
                    break;
                case "TRANSFER":
                    System.out.println("TRANSFER");
                    break;
                case "FACTURE":
                    System.out.println("FACTURE");
                    break;
                case "HIST":
                    System.out.println("HIST");
                    break;
            }
        }


    }
}
