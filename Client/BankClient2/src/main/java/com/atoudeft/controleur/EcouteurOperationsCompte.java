package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private PanneauOperationsCompte panneauOperationsCompte;

    private PanneauPrincipal  panneauPrincipal;

    public EcouteurOperationsCompte(Client client, PanneauPrincipal panneauPrincipal) {
        this.client = client;
        this.panneauPrincipal = panneauPrincipal;
        this.panneauOperationsCompte = panneauOperationsCompte;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //à compléter :
        Object source = e.getSource();
        String nomAction;
        int res;
        String montant = "";
        String compteOrigine = "";
        String compteDestinataire = "";
        String numeroFacture = "";
        String description= "";

        if(source instanceof JButton){
            nomAction = ((JButton)source).getActionCommand();
            switch (nomAction){
                case "EPARGNE":
                    client.envoyer("EPARGNE");
                    break;
                case "DEPOT":
                    PanneauOperationDepotRetrait panel = new PanneauOperationDepotRetrait();
                    boolean valide = false;
                    while (!valide) {
                        res = JOptionPane.showConfirmDialog(
                                this.panneauPrincipal,
                                panel,
                                "Dépot",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );

                        if (res == JOptionPane.OK_OPTION) {
                             montant = panel.getMontant();

                            try {
                                int port = Integer.parseInt(montant);
                                valide = true;


                            } catch (NumberFormatException w) {
                                JOptionPane.showMessageDialog(this.panneauPrincipal, "Le montant doit être un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);

                            }
                        } else {
                            JOptionPane.showMessageDialog(this.panneauPrincipal, "Dépot annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }

                    client.envoyer("DEPOT "+ montant);
                    break;
                case "RETRAIT":

                    panel = new PanneauOperationDepotRetrait();
                    valide = false;
                    while (!valide) {
                        res = JOptionPane.showConfirmDialog(
                                this.panneauPrincipal,
                                panel,
                                "Retrait",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );

                        if (res == JOptionPane.OK_OPTION) {
                            montant = panel.getMontant();

                            try {
                                int port = Integer.parseInt(montant);
                                valide = true;


                            } catch (NumberFormatException w) {
                                JOptionPane.showMessageDialog(this.panneauPrincipal, "Le montant doit être un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);

                            }
                        } else {
                            JOptionPane.showMessageDialog(this.panneauPrincipal, "Dépot annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }

                    client.envoyer("RETRAIT "+ montant);
                    break;
                case "TRANSFER":
                    PanneauOperationTransfert panelTransfert = new PanneauOperationTransfert();
                    valide = false;
                    while (!valide) {
                        res = JOptionPane.showConfirmDialog(
                                this.panneauPrincipal,
                                panelTransfert,
                                "Transfer",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );

                        if (res == JOptionPane.OK_OPTION) {
                            montant = panelTransfert.getMontant();
                            compteOrigine = panelTransfert.getCompteOrigine();
                            compteDestinataire = panelTransfert.getCompteDestinataire();

                            try {
                                int port = Integer.parseInt(montant);
                                valide = true;


                            } catch (NumberFormatException w) {
                                JOptionPane.showMessageDialog(this.panneauPrincipal, "Le montant doit être un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);

                            }
                        } else {
                            JOptionPane.showMessageDialog(this.panneauPrincipal, "Transfert annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                    client.envoyer("TRANSFER "+ montant+" "+compteOrigine +" "+compteDestinataire);
                    break;

                case "HIST":
                    client.envoyer("HIST");

            }


        }


    }
}
