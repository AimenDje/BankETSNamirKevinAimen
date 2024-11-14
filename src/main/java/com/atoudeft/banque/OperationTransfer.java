package com.atoudeft.banque;

public class OperationTransfer extends Operation{
    private double montant;
    private String compteDestinataire;

    public OperationTransfer(double montant, String compteDestinataire) {
        super(TypeOperation.TRANSFER);
        this.montant = montant;
        this.compteDestinataire = compteDestinataire;

    }


    public double getMontant() {
        return montant;
    }


    @Override
    public String toString() {
        return "DATE: " + getDate() + " TYPE: " + getType() + " MONTANT: " + montant + " Compte Destinataire: " + compteDestinataire ;




    }
}
