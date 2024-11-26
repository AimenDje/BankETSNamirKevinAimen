package com.atoudeft.banque;

import java.io.Serializable;

public class PileChainee implements Serializable {
    Noeud sommet;
    int nbElements;

    void empiler(Object obj){
        Noeud nouveau = new Noeud(sommet,obj);
        this.sommet = nouveau;
        this.nbElements++;
    }
    class Noeud implements Serializable{
        Object donnes; // contient les donnees
        Noeud suivant; // // element suivant

         Noeud( Noeud suivant, Object donnes) {
            this.donnes = donnes;
            this.suivant = suivant;
        }
    }
    public String toString() {

        String s = "HIST";
        Noeud temp = sommet;

        for (int i = 0; i < this.nbElements; i++) {
            s += "\n"+ temp.donnes.toString()  ;

            temp = temp.suivant;
        }

        return s;
    }

}
