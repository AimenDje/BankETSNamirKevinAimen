package com.atoudeft.banque;

public class CompteEpargne extends CompteBancaire{
    private static final double limite = 1000;
    private static final double frais = 2;
    private double tauxInterets;

    /**
     * Crée un compte bancaire.
     *
     * @param numero numéro du compte
     * @param type   type du compte
     * @param tauxInterets taux d'intérêts du compte
     */
    public CompteEpargne(String numero, TypeCompte type, double tauxInterets) {
        super(numero, type);
        this.tauxInterets = tauxInterets;
    }

    @Override
    public boolean crediter(double montant) {
        if(montant>0){
            solde = getSolde() + montant;
            return true;
        }
        return false;
    }

    @Override
    public boolean debiter(double montant) {
        if(montant>0 && getSolde()>= montant) {
            double nouveauSolde = getSolde() - montant;
            if(nouveauSolde<limite){
                nouveauSolde -=frais;
            }
            solde = nouveauSolde;
            return true;
        }
        return false;
    }

    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
        return false;
    }

    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        return false;
    }

    public void ajouterInterets(){
        double interets = (tauxInterets/100)*getSolde();
        crediter(interets);
    }

}
