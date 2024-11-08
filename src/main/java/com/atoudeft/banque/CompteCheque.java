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
        OperationDepot opeartiondepot = new OperationDepot(montant);
        if(montant>0){
            solde = getSolde() + montant;
            historique.empiler(opeartiondepot); // empiler opeation depot cmpt cheque
            return true;
        }
        return false;
    }
    @Override
    public boolean debiter(double montant) {
        OperationRetrait opeartionretrait = new OperationRetrait(montant);
        if(montant>0 && getSolde()>= montant) {
            solde = getSolde() - montant;
            historique.empiler(opeartionretrait); // empiler opeation retrait cmpt cheque
            return true;
        }
        return false;
    }

    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
        OperationFacture operationPayeracture = new OperationFacture(numeroFacture,montant,description);
        boolean isTrue = false;
        if(isTrue){
            historique.empiler(operationPayeracture);
            return true;
        }
        return false;
    }

    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {

        OperationTransfer operationTransfer = new OperationTransfer(montant,numeroCompteDestinataire);
        boolean isTrue = false;
        if(isTrue){
            historique.empiler(operationTransfer);
            return true;
        }
        return false;
    }
}