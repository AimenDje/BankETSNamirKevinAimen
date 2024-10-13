package com.atoudeft.banque;

public class CompteCheque extends CompteBancaire{

    /**
     * Crée un compte bancaire.
     *
     * @param numero numéro du compte
     * @param type   type du compte
     */
    public CompteCheque(String numero, TypeCompte type) {
        super(numero, type);

    }

    @Override
    public boolean crediter(double montant) {
        double nouveauSolde;
        if(montant>0){
            nouveauSolde = getSolde() + montant;
            setSolde(nouveauSolde);
            return true;
        }
        return false;
    }
    @Override
    public boolean debiter(double montant) {
        double nouveauSolde;
        if(montant>0 && getSolde()>= montant) {
            nouveauSolde = getSolde() - montant;
            setSolde(nouveauSolde);
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
}
