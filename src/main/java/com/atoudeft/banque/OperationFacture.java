package com.atoudeft.banque;

public class OperationFacture extends Operation {

    private double montant;
    private String numeroCompte , description;

    // constructeur
    public OperationFacture( String numeroCompte,double montant, String description) {
        super(TypeOperation.FACTURE);
        this.montant = montant;
        this.numeroCompte = numeroCompte;
        this.description = description;
    }

    public String getNumeroCompte() {

        return numeroCompte;
    }

    public double getMontant() {

        return montant;
    }

    public String getDescription() {
        return description;
    }

    // toString();
    @Override
    public String toString() {
        return "DATE: " + getDate() +'\n'+
                "TYPE: " + getType() +'\n'+
                "DESCRIPTION: " + description +'\n'+
                "NUMERO COMPTE: " + numeroCompte +'\n'+
                "MONTANT: " + montant ;
    }
}
