/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.Controler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import jeao.model.ConnectionBD;
import jeao.model.Enseignant;
import jeao.model.Etudiant;
import jeao.model.Utilisateur;

/**
 *
 * @author bouguerra
 */
public class CWlcPage {

    public static Utilisateur connected = new Enseignant(44445555,"jo","jo","jo","jo");

   //int cin, String nom, String penom, String mDP, String pseudo

    public CWlcPage(String pseudo, String mdp) {
        connected = new Utilisateur( pseudo,mdp);
    }

    public CWlcPage(Utilisateur connected) {
        this.connected = connected;
    }

    public Utilisateur getConnected() {
        return connected;
    }

    public void setConnected(Utilisateur connected) {
        this.connected = connected;
    }
    

    private ResultSet existeCompte() throws SQLException {
        String query = "select * from utilisateur where pseudo='"
                + connected.getPseudo() + "' and mdp ='" + connected.getmDP() + "';";
        return ConnectionBD.executeSelectQuery(query);
    }

    public void infoCompte() throws SQLException {
        ResultSet rest = existeCompte();
        switch (rest.getString("statue")) {
            case "Etudiant":
                connected = new Etudiant(rest.getInt("codeNivEtude"), rest.getInt("cin"), rest.getString("nom"), rest.getString("prenom"), rest.getString("mdp"), rest.getString("pseudo"));
                break;
            case "Enseignant":
                connected = new Enseignant(rest.getInt("cin"), rest.getString("nom"), rest.getString("prenom"), rest.getString("mdp"), rest.getString("pseudo"));
                break;
        }

    }

    public boolean connecter() throws SQLException {
        if (existeCompte().next())
             System.out.println (existeCompte().getString("pseudo")+""+existeCompte().getString("mdp"));
        return existeCompte().next() ;
    }

}
