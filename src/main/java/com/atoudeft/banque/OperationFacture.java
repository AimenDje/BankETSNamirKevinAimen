package com.atoudeft.banque;

public class OperationFacture extends Operation {

    private double montant;
    private String numeroCompte;

    // constructeur
    public OperationFacture(TypeOperation type, double montant, String numeroCompte) {
        super(TypeOperation.FACTURE);
        this.montant = montant;
        this.numeroCompte = numeroCompte;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public double getMontant() {
        return montant;
    }

    // toString();

    @Override
    public String toString() {
        return "DATE: " + getDate() +'\n'+
                "TYPE: " + getType() +'\n'+
                "NUMERO COMPTE: " + numeroCompte +'\n'+
                "MONTANT: " + montant ;
    }
}
