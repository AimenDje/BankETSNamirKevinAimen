package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.commun.thread.Lecteur;
import com.atoudeft.vue.PanneauOperationsCompte;
import com.atoudeft.vue.PanneauPrincipal;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurListeComptes extends MouseAdapter {

    private Client client;
    private PanneauPrincipal  panneauPrincipal;
    public EcouteurListeComptes(Client client, PanneauPrincipal panneauPrincipal) {
        this.client = client;
        this.panneauPrincipal = panneauPrincipal;


    }


    @Override
    public void mouseClicked(MouseEvent evt) {

        Object source = evt.getSource();
        String nomAction;
        String accountType = "";
        if (evt.getClickCount() == 2) {
            nomAction = ((JList) source).getSelectedValue().toString();
            int start = nomAction.indexOf('[');
            int end = nomAction.indexOf(']');

            if (start != -1 && end != -1) {
                accountType  = nomAction.substring(start + 1, end);

            }
            client.envoyer("SELECT " + accountType.toLowerCase());

        }
    }
}
