package com.atoudeft.banque;

import java.io.Serializable;

public class PileChainee implements Serializable {
    Noeud sommet = null;
    int nbrElement =0;

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

    public String toString() {
        String s = "Liste (taille :"+this.nbrElement+")[";
        Noeud temp = sommet;
        int i = 0;
        while (temp!=null) {
            s += i+"=> "+sommet.donnes;
            if (i<nbrElement-1)
                s += "; ";
            temp = temp.suivant;
            i++;
        }
        s += "]";
        return s;
    }

}
