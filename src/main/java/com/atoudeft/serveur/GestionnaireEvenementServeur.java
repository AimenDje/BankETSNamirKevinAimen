package com.atoudeft.serveur;

import com.atoudeft.banque.*;
import com.atoudeft.banque.serveur.ConnexionBanque;
import com.atoudeft.banque.serveur.ServeurBanque;
import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;

import java.util.List;

/**
 * Cette classe représente un gestionnaire d'événement d'un serveur. Lorsqu'un serveur reçoit un texte d'un client,
 * il crée un événement à partir du texte reçu et alerte ce gestionnaire qui réagit en gérant l'événement.
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-09-01
 */
public class GestionnaireEvenementServeur implements GestionnaireEvenement {
    private Serveur serveur;

    /**
     * Construit un gestionnaire d'événements pour un serveur.
     *
     * @param serveur Serveur Le serveur pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementServeur(Serveur serveur) {
        this.serveur = serveur;
    }

    /**
     * Méthode de gestion d'événements. Cette méthode contiendra le code qui gère les réponses obtenues d'un client.
     *
     * @param evenement L'événement à gérer.
     */
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        ServeurBanque serveurBanque = (ServeurBanque)serveur;
        Banque banque;
        ConnexionBanque cnx;
        String msg, typeEvenement, argument, numCompteClient, nip;
        String[] t;

        if (source instanceof Connexion) {
            cnx = (ConnexionBanque) source;
            System.out.println("SERVEUR: Recu : " + evenement.getType() + " " + evenement.getArgument());
            typeEvenement = evenement.getType();
            cnx.setTempsDerniereOperation(System.currentTimeMillis());
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "EXIT": //Ferme la connexion avec le client qui a envoyé "EXIT":
                    cnx.envoyer("END");
                    serveurBanque.enlever(cnx);
                    cnx.close();
                    break;
                case "LIST": //Envoie la liste des numéros de comptes-clients connectés :
                    cnx.envoyer("LIST " + serveurBanque.list());
                    break;
                /******************* COMMANDES DE GESTION DE COMPTES *******************/
                case "NOUVEAU": //Crée un nouveau compte-client :
                    if (cnx.getNumeroCompteClient()!=null) {
                        cnx.envoyer("NOUVEAU NO");
                        break;
                    }
                    argument = evenement.getArgument();
                    t = argument.split(":");
                    if (t.length<2) {
                        cnx.envoyer("NOUVEAU NO");
                    }
                    else {
                        numCompteClient = t[0];
                        nip = t[1];
                        banque = serveurBanque.getBanque();
                        if (banque.ajouter(numCompteClient,nip)) {
                            cnx.setNumeroCompteClient(numCompteClient);
                            cnx.setNumeroCompteActuel(banque.getNumeroCompteParDefaut(numCompteClient));
                            cnx.envoyer("NOUVEAU OK " + t[0] + " cree");
                        }
                        else
                            cnx.envoyer("NOUVEAU NO "+t[0]+" existe");
                    }
                    break;
                case "CONNECT":
                    argument = evenement.getArgument();
                    t = argument.split(":");
                    numCompteClient = t[0];
                    nip = t[1];
                    if(cnx.getNumeroCompteClient()!= null && cnx.getNumeroCompteClient().equals(numCompteClient)){
                        cnx.envoyer("CONNECT NO");
                        break;
                    }
                    banque = serveurBanque.getBanque();
                    CompteClient compteClient = banque.getCompteClient(numCompteClient);

                    if(compteClient == null || !nip.equals(compteClient.getNip())){
                        cnx.envoyer("CONNECT NO");
                        break;
                    }else{
                        cnx.setNumeroCompteClient(numCompteClient);
                        cnx.setNumeroCompteActuel(banque.getNumeroCompteParDefaut(numCompteClient));
                        cnx.envoyer("CONNECT OK");
                    }
                    break;
                case "EPARGNE":
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteClient();
                    compteClient = banque.getCompteClient(numCompteClient);
                    String numeroAleatoire = CompteBancaire.genereNouveauNumero();
                    boolean numeroExistant = true;

                    if(numCompteClient== null || banque.detientCompteEpargne(numCompteClient)){
                        cnx.envoyer("EPARGNE NO");
                        break;
                    }

                    while(numeroExistant){
                        numeroExistant = false;

                        for (CompteClient compteCLient2:banque.getComptes()){
                            if(compteCLient2.getNumero().equals(numeroAleatoire)) {
                                numeroExistant = true;
                                numeroAleatoire = CompteBancaire.genereNouveauNumero();
                                break;
                            }

                            List<CompteBancaire> comptesDuClient = compteCLient2.getComptes();
                            for (CompteBancaire compteBancaire:comptesDuClient){
                                if(compteBancaire.getNumero().equals(numeroAleatoire)){
                                    numeroExistant = true;
                                    numeroAleatoire = CompteBancaire.genereNouveauNumero();
                                      break;
                                    }
                                }
                            if(numeroExistant){
                                break;
                            }
                        }
                    }

                    CompteEpargne compteEpargne = new CompteEpargne(numeroAleatoire, TypeCompte.EPARGNE,5);
                    compteClient.ajouter(compteEpargne);
                    cnx.envoyer("EPARGNE OK");

                    break;

                case "SELECT":
                    argument = evenement.getArgument();
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteClient();
                    TypeCompte typeCompte= null;
                    compteClient = banque.getCompteClient(numCompteClient);
                    List<CompteBancaire> comptesDuClient = compteClient.getComptes();

                    if (argument.equals("cheque")){
                        typeCompte = TypeCompte.CHEQUE;
                    } else if (argument.equals("epargne")) {
                        typeCompte = TypeCompte.EPARGNE;
                    }

                    for (CompteBancaire compteBancaire : comptesDuClient) {
                        if (typeCompte == compteBancaire.getType()) {
                            cnx.setNumeroCompteActuel(compteBancaire.getNumero());
                            cnx.envoyer("SELECT OK");
                            break;
                        }

                    }
                    cnx.envoyer("SELECT NO");
                    break;

                case "DEPOT":
                    argument = evenement.getArgument();
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteClient();
                    compteClient = banque.getCompteClient(numCompteClient);
                    List<CompteBancaire> compteBancaires= compteClient.getComptes();
                    for (CompteBancaire compteBancaire : compteBancaires) {
                        if(compteBancaire.getNumero().equals(cnx.getNumeroCompteActuel())){
                            if(compteBancaire.crediter(Double.parseDouble(argument))){
                                cnx.envoyer("OK");
                                break;
                            }
                        }

                    }
                    cnx.envoyer("NO");
                    break;
                case "RETRAIT":
                    argument = evenement.getArgument();
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteClient();
                    compteClient = banque.getCompteClient(numCompteClient);
                    compteBancaires= compteClient.getComptes();
                    for (CompteBancaire compteBancaire : compteBancaires) {
                        if(compteBancaire.getNumero().equals(cnx.getNumeroCompteActuel())){
                            if(compteBancaire.debiter(Double.parseDouble(argument))){
                                cnx.envoyer("OK");
                                break;
                            }
                        }

                    }
                    cnx.envoyer("NO");
                    break;
                case "FACTURE":
                    argument = evenement.getArgument();
                    t = argument.split(" ");
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteClient();
                    compteClient = banque.getCompteClient(numCompteClient);
                    compteBancaires= compteClient.getComptes();
                    for (CompteBancaire compteBancaire : compteBancaires) {
                        if(compteBancaire.getNumero().equals(cnx.getNumeroCompteActuel())){
                            if(compteBancaire.payerFacture(t[1],Double.parseDouble(t[0]),t[2])){
                                cnx.envoyer("OK");
                                break;
                            }
                        }

                    }
                    cnx.envoyer("NO");
                    break;
                case "TRANSFER":
                    argument = evenement.getArgument();
                    t = argument.split(" ");
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteClient();
                    compteClient = banque.getCompteClient(numCompteClient);
                    compteBancaires= compteClient.getComptes();
                    CompteBancaire compteBancaireClient = null ;
                    for (CompteBancaire compteBancaire : compteBancaires) {
                        if(compteBancaire.getNumero().equals(cnx.getNumeroCompteActuel())){
                            compteBancaireClient = compteBancaire;
                            for (CompteBancaire compteBancaire1 : compteBancaires) {
                                if(compteBancaire1.getNumero().equals(t[1])){
                                    if(compteBancaireClient.transferer(Double.parseDouble(t[0]),t[1])){
                                        cnx.envoyer("NO");
                                        break;
                                    }
                                }

                            }

                        }

                    }
                    cnx.envoyer("NO");
                    break;





                    List<CompteClient> comptebanquaire=   banque.getComptes();
                    if(comptebanquaire.contains(banque.getCompteClient(t[1]))){
                        if(banque.transferer(Double.parseDouble(t[0]) , numeroCompteActuel,t[1])) {
                            cnx.envoyer("OK");
                            break;
                        }
                    }

                    cnx.envoyer("NO");
                    break;

                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default: //Renvoyer le texte recu convertit en majuscules :
                    msg = (evenement.getType() + " " + evenement.getArgument()).toUpperCase();
                    cnx.envoyer(msg);
            }
        }
    }
}