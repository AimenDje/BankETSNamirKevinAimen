package com.atoudeft.banque;

public class OperationFacture extends Operation {

    private double montant;
    private String numeroFacture, description;

    // constructeur
    public OperationFacture( String numeroFacture,double montant, String description) {
        super(TypeOperation.FACTURE);
        this.montant = montant;
        this.numeroFacture = numeroFacture;
        this.description = description;
    }

    public String getNumeroFacture() {

        return numeroFacture;
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
                "NUMERO FACTURE: " + numeroFacture +'\n'+
                "MONTANT: " + montant ;
    }
}
