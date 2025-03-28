package com.atoudeft.client;

import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;
import com.atoudeft.vue.PanneauPrincipal;
import com.programmes.MainFrame;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;

public class GestionnaireEvenementClient2 implements GestionnaireEvenement {
    private Client client;
    private PanneauPrincipal panneauPrincipal;

    /**
     * Construit un gestionnaire d'événements pour un client.
     *
     * @param client Client Le client pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementClient2(Client client, PanneauPrincipal panneauPrincipal) {

        this.client = client;
        this.panneauPrincipal = panneauPrincipal;
        this.client.setGestionnaireEvenement(this);
    }
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        Connexion cnx ;
        String typeEvenement, arg, str;
        int i;
        String[] t;
        MainFrame fenetre;

        if (source instanceof Connexion) {
            cnx = (Connexion) source;
            typeEvenement = evenement.getType();
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "END": //Le serveur demande de fermer la connexion
                    client.deconnecter(); //On ferme la connexion
                    break;
                /******************* CREATION et CONNEXION *******************/
              case "HIST": //Le serveur a renvoyé
                  arg = evenement.getArgument();
                  panneauPrincipal.setVisible(true);
                  JOptionPane.showMessageDialog(null,arg);
                  cnx.envoyer("LIST");
                 break;
                case "OK":
                    panneauPrincipal.setVisible(true);
                    fenetre = (MainFrame)panneauPrincipal.getTopLevelAncestor();
                    fenetre.setTitle(MainFrame.TITRE);//+" - Connecté"
                    break;
                case "NOUVEAU":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Nouveau refusé");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        panneauPrincipal.ajouterCompte(str);
                    }
                    break;
                case "CONNECT":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Connexion refusée");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(":");
                        for (String s:t) {
                            panneauPrincipal.ajouterCompte(s.substring(0,s.indexOf("]")+1));
                        }
                    }
                    break;
                /******************* SÉLECTION DE COMPTES *******************/
                case "EPARGNE" :
                    arg = evenement.getArgument();
                    if(arg.trim().startsWith("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal, "Épargne refusé" );
                    }
                    else {
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(":");
                        panneauPrincipal.ajouterCompte(str+"[EPARGNE]");

                    }
                    JOptionPane.showMessageDialog(panneauPrincipal,"EPARGNE "+arg);
                    break;
                case "SELECT" :
                    arg = evenement.getArgument();
                    if(arg.trim().startsWith("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal, "Erreur lors du choix de compte" );
                    }
                    else {
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(" ");
                        panneauPrincipal.miseAJourSolde(t[1]);

                    }
                    break;
                /******************* OPÉRATIONS BANCAIRES *******************/
                case "DEPOT" :
                    arg = evenement.getArgument();
                    if(arg.trim().startsWith("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal, "Dépot impossible" );
                    }
                    else {
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        panneauPrincipal.miseAJourSolde(str);

                    }
                    JOptionPane.showMessageDialog(panneauPrincipal,"Dépot effectué");
                    break;
                case "RETRAIT" :
                    arg = evenement.getArgument();
                    if(arg.trim().startsWith("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal, "Dépot impossible" );
                    }
                    else {
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        panneauPrincipal.miseAJourSolde(str);

                    }
                    JOptionPane.showMessageDialog(panneauPrincipal,"Retirer effectué");
                    break;
                case "FACTURE" :
                    arg = evenement.getArgument();
                    if(arg.trim().startsWith("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal, "Facture impossible" );
                    }
                    else {
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        panneauPrincipal.miseAJourSolde(str);

                    }
                    JOptionPane.showMessageDialog(panneauPrincipal,"Paiment EFFECTUÉ" );
                    break;
                case "TRANSFER" :
                    arg = evenement.getArgument();
                    if(arg.trim().startsWith("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal, "Transfert impossible" );
                    }
                    else {
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(" ");
                        panneauPrincipal.miseAJourSolde(str);

                    }
                    JOptionPane.showMessageDialog(panneauPrincipal,"TRANSFER EFFECTUÉ" );
                    break;
                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default:
                    System.out.println("RECU : "+evenement.getType()+" "+evenement.getArgument());
            }
        }
    }
}