package com.atoudeft.banque;

public class OperationTransfer extends Operation{
    private double montant;
    private String numeroCompte;

    public OperationTransfer(double montant, String numeroCompte){
        super(TypeOperation.TRANSFER);
        this.montant = montant;
        this.numeroCompte= numeroCompte;

    }


    public String getNumeroCompte() {
        return numeroCompte;
    }

    public double getMontant() {
        return montant;
    }


    @Override
    public String toString() {
        return "DATE: " + getDate()+'\n'+
                "TYPE: " + getType() +'\n'+
                "NUMERO DU COMPTE: " + numeroCompte+'\n'+
                "MONTANT: " + montant;

    }
}
