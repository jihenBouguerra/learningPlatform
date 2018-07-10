/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jeao.model.ConnectionBD;
import static jeao.Controler.CWlcPage.connected;
import jeao.model.Etudiant;
import jeao.model.Examen;
import jeao.model.Module;
import jeao.model.NivEtude;
import jeao.model.Utilisateur;
import jeao.View.old.EnseignantMenu_1;

/**
 *
 * @author bouguerra
 */
public class CSupprimer {

    private static ArrayList<Utilisateur> listeUtilisateur;
    private static ArrayList<Module> listeModule;
    private static ArrayList<NivEtude> listeNivEtude;
    private static ArrayList<Examen> listeExamen;
    private static String action;

    public static ArrayList<Utilisateur> getListeUtilisateur() {
        return listeUtilisateur;
    }

    public static ArrayList<Module> getListeModule() {
        return listeModule;
    }

    public static ArrayList<NivEtude> getListeNivEtude() {
        return listeNivEtude;
    }

    public static ArrayList<Examen> getListeExamen() {
        return listeExamen;
    }

    public static void setAction(String action) {
        CSupprimer.action = action;
    }

    public static String getAction() {
        return action;
    }

    public static void loadlisteExamen() throws SQLException {
        listeExamen = new ArrayList<Examen>();
        String query = " select * from Examen ";
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        while (result.next()) {
            Examen e = new Examen(result.getString("titre"),
                    Integer.parseInt(result.getString("codeModule")),
                    Integer.parseInt(result.getString("enseignantCin")),
                    Integer.parseInt(result.getString("codeExamen")));
            listeExamen.add(e);

        }

    }

    public static void loadlisteUtilisateur() throws SQLException {

        listeUtilisateur = new ArrayList<Utilisateur>();
        String query = " select * from Utilisateur ";
        ResultSet rest = ConnectionBD.executeSelectQuery(query);
        while (rest.next()) {
            Utilisateur u;
            if (rest.getString("statue").equals("Enseignant")) {
                //int cin, String nom, String penom, String mDP, String pseudo
                u = new jeao.model.Enseignant(rest.getInt("cin"), rest.getString("nom"), rest.getString("prenom"), rest.getString("mdp"), rest.getString("pseudo"));
            } else {
              //int codeNivEtude, int cin, String nom, String penom, String mDP, String pseudo
                u = new Etudiant(rest.getInt("codeNivEtude"), rest.getInt("cin"), rest.getString("nom"), rest.getString("prenom"), rest.getString("mdp"), rest.getString("pseudo"));
            }
            listeUtilisateur.add(u);

        }

    }

    public static void loadListModule() throws SQLException {

        listeModule = new ArrayList<Module>();
        String query = "select * from module";
        ResultSet result = ConnectionBD.executeSelectQuery(query);

        while (result.next()) {
            Module m = new Module(Integer.parseInt(result.getString("codeModule")),
                    result.getString("nomComplet"),
                    Integer.parseInt(result.getString("codeNivEtude")));
            listeModule.add(m);
        }

    }

    public static void loadlisteNivEtude() throws SQLException {

        listeNivEtude = new ArrayList<NivEtude>();
        String query = " select * from NivEtude ";
        ResultSet result = ConnectionBD.executeSelectQuery(query);

        while (result.next()) {
            NivEtude n = new NivEtude(result.getInt("codenivEtude"),
                    result.getString("niv"));
            listeNivEtude.add(n);
        }

    }

}
