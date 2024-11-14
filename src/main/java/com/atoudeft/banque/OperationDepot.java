package com.atoudeft.banque;

public class OperationDepot extends Operation{
    private double montant;

    // constructeur
    public OperationDepot(double montant) {
        super(TypeOperation.DEPOT);
        this.montant = montant;
    }

    public double getMontant() {
        return montant;
    }



    // to string();
    @Override
    public String toString() {
        return "DATE: " + getDate() + " TYPE: " + getType() + " MONTANT: " + montant;
    }
}
