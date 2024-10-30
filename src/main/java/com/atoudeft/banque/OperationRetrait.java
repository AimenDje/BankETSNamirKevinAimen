package com.atoudeft.banque;

public class OperationRetrait extends Operation {
    private double montant;

    // constructeur
    public OperationRetrait(double montant) {
        super(TypeOperation.RETRAIT);
        this.montant = montant;
    }

    public double getMontant() {
        return montant;
    }

    // tostring()

    @Override
    public String toString() {
        return "DATE: " + getDate()+'\n'+
                "TYPE: " + getType() +'\n'+
                "MONTANT: " + montant;
    }
}
