/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.Controler;

import java.util.ArrayList;
import javax.swing.JFrame;
import jeao.model.Module;
import jeao.model.NivEtude;
import jeao.model.Utilisateur;
import jeao.View.old.Admin_1;

/**
 *
 * @author bouguerra
 */
public class CMenuAdmin {

    public static String Action;

    public static void generate(JFrame j) {
        String query = "";
        switch (Action) {
            case "Affiche liste Niveau d'etude":
                query = "select Niv from nivEtude";
                break;
            case "Affiche liste des examen ":
                query = "select  date,titre, pseudo , nomComplet "
                        + "from module ,examen, utilisateur "
                        + "where examen.codeModule = module.codeModule "
                        + "and cin = examen.enseignantCin ";
                break;
            case "Affiche liste de comptes Etudiant":
                query = "select nom, prenom, cin , pseudo ,statue, niv from Utilisateur , "
                        + "nivEtude  where statue = 'Etudiant' and Utilisateur.codeNivEtude = "
                        + "nivEtude.codeNivEtude";
                break;
            case "Affiche liste de comptes Enseignant":
                query = "select nom, prenom, cin , pseudo ,statue from Utilisateur where statue = 'Enseignant'";
                break;
            case "Affiche liste des modules":
                query = "select nomComplet,niv from Module , nivEtude where Module.codeNivEtude= NivEtude.codeNivEtude";
                break;

        }
        CcreatTab.create(query, j);
    }

}
