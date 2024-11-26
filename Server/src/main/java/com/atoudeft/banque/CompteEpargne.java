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
        OperationDepot operationdepot = new OperationDepot(montant);
        if(montant>0){
            solde = getSolde() + montant;
            historique.empiler(operationdepot); // empiler historique credit
            return true;
        }
        return false;
    }

    @Override
    public boolean debiter(double montant) {
        OperationRetrait operationretrait = new OperationRetrait(montant);
        if(montant>0 && getSolde()>= montant) {
            double nouveauSolde = getSolde() - montant;
            if(nouveauSolde<limite){
                nouveauSolde -=frais;
            }
            solde = nouveauSolde;
            historique.empiler(operationretrait); // empiler operation retrait client
            return true;
        }
        return false;
    }

    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
       OperationFacture operationfacture = new OperationFacture(numeroFacture, montant, description);
        if(montant>0 && getSolde()>= montant) {
            double nouveauSolde = getSolde() - montant;
            if(nouveauSolde<limite){
                nouveauSolde -=frais;
            }
            solde = nouveauSolde;
            historique.empiler(operationfacture); // empiler operation retrait client
            return true;
        }
        return false;
    }

    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        OperationTransfer operationtransfer = new OperationTransfer(montant, numeroCompteDestinataire);
        if(montant>0 && getSolde()>= montant) {
            double nouveauSolde = getSolde() - montant;
            if(nouveauSolde<limite){
                nouveauSolde -=frais;
            }
            solde = nouveauSolde;
            historique.empiler(operationtransfer); // empiler operation retrait client
            return true;
        }
        return false;
    }

    public void ajouterInterets(){
        double interets = (tauxInterets/100)*getSolde();
        crediter(interets);
    }

}
