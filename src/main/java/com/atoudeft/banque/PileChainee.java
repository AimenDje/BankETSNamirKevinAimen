package com.atoudeft.banque;

import java.io.Serializable;

public class PileChainee implements Serializable {
    Noeud sommet;
    int nbrElement;

    void empiler(Object obj){
        Noeud nouveau = new Noeud(sommet,obj);
        this.sommet = nouveau;
        this.nbrElement++;
    }
    class Noeud implements Serializable{
        Object donnes; // contient les donnees
        Noeud suivant; // // element suivant

         Noeud( Noeud suivant, Object donnes) {
            this.donnes = donnes;
            this.suivant = suivant;
        }
    }

}
