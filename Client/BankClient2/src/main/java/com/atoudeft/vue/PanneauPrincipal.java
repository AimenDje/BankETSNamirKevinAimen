package com.atoudeft.vue;

import com.atoudeft.client.Client;
import com.atoudeft.controleur.EcouteurConnexion;
import com.atoudeft.controleur.EcouteurListeComptes;
import com.atoudeft.controleur.EcouteurOperationsCompte;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2024-11-01
 */
public class PanneauPrincipal  extends JPanel {
    private Client client;
    private PanneauConnexion panneauConnexion;
    private JPanel panneauCompteClient;
    private PanneauOperationsCompte panneauOperationsCompte;
    private PanneauOperationDepotRetrait panneauOperationDepotRetrait;


    private DefaultListModel<String> numerosComptes;
    private JList<String> jlNumerosComptes;
    private JTextArea textAreaComptes;
    private JPanel panneauhistorique;
    private JDesktopPane bureau;


    public PanneauPrincipal(Client client) {
        this.client = client;
        EcouteurOperationsCompte ecouteurOperationsCompte = new EcouteurOperationsCompte(client,this);

        panneauConnexion = new PanneauConnexion();
        panneauConnexion.setEcouteur(new EcouteurConnexion(client,panneauConnexion));


        panneauOperationsCompte = new PanneauOperationsCompte();

        panneauCompteClient = new JPanel();
        panneauOperationDepotRetrait = new PanneauOperationDepotRetrait();

        panneauCompteClient.setLayout(new BorderLayout());
        panneauCompteClient.setBackground(Color.WHITE);
        panneauOperationsCompte.setBackground(Color.WHITE);

        panneauCompteClient.add(panneauOperationDepotRetrait, BorderLayout.CENTER); // Add PanneauOperationDepot to the main panel
        panneauOperationDepotRetrait.setVisible(false);

        numerosComptes = new DefaultListModel<>();

        jlNumerosComptes = new JList<>(numerosComptes);
        jlNumerosComptes.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        jlNumerosComptes.setBorder(BorderFactory.createTitledBorder("Comptes bancaires"));
        jlNumerosComptes.setPreferredSize(new Dimension(250,500));


        // jtextArea
        /*
        textAreaComptes = new JTextArea(10,30);
        textAreaComptes.setEditable(false);
        textAreaComptes.setBorder(new LineBorder(Color.black));
          */
        panneauOperationsCompte.setEcouteur(ecouteurOperationsCompte);


        panneauCompteClient.add(panneauOperationsCompte, BorderLayout.NORTH);
        panneauCompteClient.add(jlNumerosComptes, BorderLayout.WEST);
        //Enregistrement de l'écouteur de souris:
        jlNumerosComptes.addMouseListener(new EcouteurListeComptes(client,this));

        this.setLayout(new BorderLayout());
        //panneauCompteClient.add(textAreaComptes, BorderLayout.CENTER); // GRAND FENETRE
        this.add(panneauConnexion, BorderLayout.NORTH);
        this.add(panneauCompteClient, BorderLayout.CENTER);
        panneauCompteClient.setVisible(false);
    }

    /**
     * Vide les éléments d'interface du panneauPrincipal.
     */
    public void vider() {
        this.numerosComptes.clear();
        this.bureau.removeAll();
    }
    public void cacherPanneauConnexion() {
        panneauConnexion.effacer();
        panneauConnexion.setVisible(false);
    }
    public void montrerPanneauConnexion() {
        panneauConnexion.setVisible(true);
    }
    public void cacherPanneauCompteClient() {
        panneauCompteClient.setVisible(false);
        this.numerosComptes.clear();
    }
    public void montrerPanneauCompteClient() {
        panneauCompteClient.setVisible(true);
    }
    public PanneauOperationsCompte getPanneauOperationsCompte() {
        return panneauOperationsCompte;
    }
    public PanneauOperationDepotRetrait PanneauOperationDepot() {
        return panneauOperationDepotRetrait;
    }
    /**
     * Affiche un numéro de compte dans le JList des comptes.
     *
     * @param str chaine contenant le numéros de compte
     */
    public void ajouterCompte(String str) {
        numerosComptes.addElement(str);
    }
    public void miseAJourSolde(String str) {
        panneauOperationsCompte.seMettreAJour(str);
    }



}