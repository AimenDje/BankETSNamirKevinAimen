package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauConfigServeur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2024-11-01
 */
public class EcouteurMenuPrincipal implements ActionListener {
    private Client client;
    private JFrame fenetre;

    public EcouteurMenuPrincipal(Client client, JFrame fenetre) {
        this.client = client;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        String action;
        String alias;
        int res;

        if (source instanceof JMenuItem) {
            action = ((JMenuItem)source).getActionCommand();
            switch (action) {
                case "CONNECTER":
                    if (!client.isConnecte()) {
                        if (!client.connecter()) {
                            JOptionPane.showMessageDialog(fenetre, "Le serveur ne répond pas");
                            break;
                        }

                    }
                    break;
                case "DECONNECTER":
                    if (!client.isConnecte())
                        break;
                    res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                            "Confirmation Déconnecter",
                            JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.deconnecter();
                    }
                    break;
                case "CONFIGURER":
                    if(!client.isConnecte()) {
                        PanneauConfigServeur panel = new PanneauConfigServeur("127.0.0.1", 8888);
                        boolean valide = false;
                        while (!valide) {
                            res = JOptionPane.showConfirmDialog(
                                    fenetre,
                                    panel,
                                    "Configuration serveur",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE
                            );

                            if (res == JOptionPane.OK_OPTION) {
                                String adresseServeur = panel.getAdresseServeur();
                                String portServeur = panel.getPortServeur();

                                try {
                                    int port = Integer.parseInt(portServeur);
                                    valide = true;

                                    if (adresseServeur.equals("127.0.0.1") && port == 8888) {
                                        JOptionPane.showMessageDialog(fenetre, "Connexion au serveur réussie.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                        client.connecter();
                                        //client.envoyer("Adresse du serveur: "+ adresseServeur+ " Port:" + portServeur);


                                    } else {
                                        JOptionPane.showMessageDialog(fenetre, "Connexion au serveur échouée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                                        break;

                                    }

                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(fenetre, "Le port doit être un entier valide.", "Erreur", JOptionPane.ERROR_MESSAGE);

                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(fenetre, "Échec de la connexion : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(fenetre, "Configuration annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                        }
                    }
                    break;
                case "QUITTER":
                    if (client.isConnecte()) {
                        res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                                "Confirmation Quitter",
                                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (res == JOptionPane.OK_OPTION){
                            client.deconnecter();
                            System.exit(0);
                        }
                    }
                    else
                        System.exit(0);
                    break;
            }
        }
    }
}