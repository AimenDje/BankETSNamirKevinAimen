package com.atoudeft.banque;

import com.atoudeft.commun.net.Connexion;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Banque implements Serializable {
    private String nom;
    private List<CompteClient> comptes;

    public Banque(String nom) {
        this.nom = nom;
        this.comptes = new ArrayList<>();
    }

    /**
     * Recherche un compte-client à partir de son numéro.
     *
     * @param numeroCompteClient le numéro du compte-client
     * @return le compte-client s'il a été trouvé. Sinon, retourne null
     */
    public CompteClient getCompteClient(String numeroCompteClient) {
        CompteClient cpt = new CompteClient(numeroCompteClient,"");
        int index = this.comptes.indexOf(cpt);
        if (index != -1)
            return this.comptes.get(index);
        else
            return null;
    }

    public List<CompteClient> getComptes() {
        return comptes;
    }

    /**
     * Vérifier qu'un compte-bancaire appartient bien au compte-client.
     *
     * @param numeroCompteBancaire numéro du compte-bancaire
     * @param numeroCompteClient    numéro du compte-client
     * @return  true si le compte-bancaire appartient au compte-client
     */
    public boolean appartientA(String numeroCompteBancaire, String numeroCompteClient) {
        for (CompteClient compteClient:comptes){
            if(compteClient.getNumero().equals(numeroCompteClient)){
                List<CompteBancaire> comptesBancaires = compteClient.getComptes();
                for(CompteBancaire compteBancaire:comptesBancaires){
                    if(compteBancaire.getNumero().equals(numeroCompteBancaire)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Vérifier qu'un compte-client détient un compte chèque.
     *
     * @param numeroCompteClient    numéro du compte-client
     * @return  true si le compte-client détient un compte client
     */
    public boolean detientCompteEpargne(String numeroCompteClient){
        for (CompteClient compteClient : comptes) {
            if (compteClient.getNumero().equals(numeroCompteClient)) {
                for (CompteBancaire compteBancaire : compteClient.getComptes()) {
                    if (TypeCompte.EPARGNE.equals(compteBancaire.getType())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Effectue un dépot d'argent dans un compte-bancaire
     *
     * @param montant montant à déposer
     * @param numeroCompte numéro du compte
     * @return true si le dépot s'est effectué correctement
     */
    public boolean deposer(double montant, String numeroCompte) {

        CompteBancaire compteBancaireClient = null;
        for (CompteClient compteClient : this.comptes) {
            for (CompteBancaire compteBancaire : compteClient.getComptes()) {
                if (compteBancaire.getNumero().equals(numeroCompte)) {
                    compteBancaireClient = compteBancaire;
                }
            }
        }

        if (compteBancaireClient !=  null){


            return compteBancaireClient.crediter(montant);
        }

        return false;

    }

    /**
     * Effectue un retrait d'argent d'un compte-bancaire
     *
     * @param montant montant retiré
     * @param numeroCompte numéro du compte
     * @return true si le retrait s'est effectué correctement
     */
    public boolean retirer(double montant, String numeroCompte) {

        CompteBancaire compteBancaireClient = null;
        for (CompteClient compteClient : this.comptes) {
            for (CompteBancaire compteBancaire : compteClient.getComptes()) {
                if (compteBancaire.getNumero().equals(numeroCompte)) {
                    compteBancaireClient = compteBancaire;
                }
            }
        }

        if (compteBancaireClient !=  null){


            return compteBancaireClient.debiter(montant);
        }

        return false;

    }

    /**
     * Effectue un transfert d'argent d'un compte à un autre de la même banque
     * @param montant montant à transférer
     * @param numeroCompteInitial   numéro du compte d'où sera prélevé l'argent
     * @param numeroCompteFinal numéro du compte où sera déposé l'argent
     * @return true si l'opération s'est déroulée correctement
     */
    public boolean transferer(double montant, String numeroCompteInitial, String numeroCompteFinal) {
        CompteBancaire compteInitial = null ;
        CompteBancaire compteFinal = null;
        for (CompteClient compteClient : this.comptes) {
            for (CompteBancaire compteBancaire : compteClient.getComptes()) {
            if (compteBancaire.getNumero().equals(numeroCompteFinal)) {
                compteFinal = compteBancaire;
            }
                if (compteBancaire.getNumero().equals(numeroCompteInitial)) {
                    compteInitial = compteBancaire;
                }
            }
        }

        if (compteInitial !=  null && compteFinal != null){


            return compteInitial.transferer(montant,compteFinal.getNumero()) && compteFinal.crediter(montant);
        }

        return false;
    }

    /**
     * Effectue un paiement de facture.
     * @param montant montant de la facture
     * @param numeroCompte numéro du compte bancaire d'où va se faire le paiement
     * @param numeroFacture numéro de la facture
     * @param description texte descriptif de la facture
     * @return true si le paiement s'est bien effectuée
     */
    public boolean payerFacture(double montant, String numeroCompte, String numeroFacture, String description) {
        for (CompteClient compteClient : this.comptes) {
            for (CompteBancaire compteBancaire : compteClient.getComptes()) {
                if (compteBancaire.getNumero().equals(numeroCompte)) {
                    if( compteBancaire.payerFacture(numeroFacture, montant, description)){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Crée un nouveau compte-client avec un numéro et un nip et l'ajoute à la liste des comptes.
     *
     * @param numCompteClient numéro du compte-client à créer
     * @param nip nip du compte-client à créer
     * @return true si le compte a été créé correctement
     */
    public boolean ajouter(String numCompteClient, String nip) {
        /*À compléter et modifier :
            - Vérifier que le numéro a entre 6 et 8 caractères et ne contient que des lettres majuscules et des chiffres.
              Sinon, retourner false.
            - Vérifier que le nip a entre 4 et 5 caractères et ne contient que des chiffres. Sinon,
              retourner false.
            - Vérifier s'il y a déjà un compte-client avec le numéro, retourner false.
            - Sinon :
                . Créer un compte-client avec le numéro et le nip;
                . Générer (avec CompteBancaire.genereNouveauNumero()) un nouveau numéro de compte bancaire qui n'est
                  pas déjà utilisé;
                . Créer un compte-chèque avec ce numéro et l'ajouter au compte-client;
                . Ajouter le compte-client à la liste des comptes et retourner true.
         */
        for (int i = 0; i < numCompteClient.length(); i++) {
            char c = numCompteClient.charAt(i);
            if (!Character.isUpperCase(c) && !Character.isDigit(c)) {
                return false;
            }
        }
        for (int i = 0; i < nip.length(); i++) {
            char c = nip.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        if(numCompteClient.length() < 6 || numCompteClient.length() > 8){
            return false;
        }
        else if(nip.length() < 4 || nip.length() > 5){
            return false;
        }
        for (CompteClient compteCLient:comptes){
            if(compteCLient.getNumero().equals(numCompteClient)){
                return false;
            }
        }
        CompteClient compteClient = new CompteClient( numCompteClient,nip);
        CompteCheque compteCheque = new CompteCheque(CompteBancaire.genereNouveauNumero(), TypeCompte.CHEQUE);
        compteClient.ajouter(compteCheque);
        this.comptes.add(compteClient);
        return true;
    }

    /**
     * Retourne le numéro du compte-chèque d'un client à partir de son numéro de compte-client.
     *
     * @param numCompteClient numéro de compte-client
     * @return numéro du compte-chèque du client ayant le numéro de compte-client
     */
    public String getNumeroCompteParDefaut(String numCompteClient) {
        for (CompteClient compteCLient:comptes){
            if(compteCLient.getNumero().equals(numCompteClient)){
                List<CompteBancaire> comptesDuClient = compteCLient.getComptes();
                for (CompteBancaire compteBancaire:comptesDuClient){
                    if(compteBancaire.getType().equals(TypeCompte.CHEQUE)){
                        return compteBancaire.getNumero();

                    }
                }
            }
        }
        return null;
    }
}