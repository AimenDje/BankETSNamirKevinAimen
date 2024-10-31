package com.atoudeft.banque;

public class OperationTransfer extends Operation{
    private double montant;
    private String numeroFacture;

    public OperationTransfer(double montant, String numeroFacture) {
        super(TypeOperation.TRANSFER);
        this.montant = montant;
        this.numeroFacture = numeroFacture;

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
                "MONTANT: " + montant;

    }
}
