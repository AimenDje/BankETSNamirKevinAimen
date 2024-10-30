package com.atoudeft.banque;

public class OperationTransfer extends Operation{
    private double montant;
    private String numeroFacture, description;

    public OperationTransfer(TypeOperation type, double montant, String numeroFacture, String description) {
        super(TypeOperation.TRANSFER);
        this.montant = montant;
        this.numeroFacture = numeroFacture;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public double getMontant() {
        return montant;
    }


    @Override
    public String toString() {
        return "DATE: " + getDate()+'\n'+
                "TYPE: " + getType() +'\n'+
                "NUMERO DE FACTURE: " + numeroFacture +'\n'+
                "DESCRIPTIONN: " + description +'\n'+
                "MONTANT: " + montant;

    }
}
