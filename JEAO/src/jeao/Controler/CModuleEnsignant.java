/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jeao.model.ConnectionBD;
import jeao.model.Etudiant;
import jeao.model.Module;
import jeao.model.Utilisateur;

/**
 *
 * @author bouguerra
 */
public class CModuleEnsignant {

    private static ArrayList<Utilisateur> listeUtilisateur;
    private static ArrayList<Module> listeModule;
     private static ArrayList<Module> listeModulePerEnseigant;

    public static ArrayList<Module> getListeModulePerEnseigant() {
        return listeModulePerEnseigant;
    }


    public static ArrayList<Utilisateur> getListeUtilisateur() {
        return listeUtilisateur;
    }

    public static ArrayList<Module> getListeModule() {
        return listeModule;
    }
     public static void loadListModulePerEnseignant ( int index ) throws SQLException {

      listeModulePerEnseigant= new ArrayList<Module>();
        String query = "select * from module "
                + "where codeModule  in "
                + " (select codeModule from enseignermodule where enseignantCin = "
                +listeUtilisateur.get(index).getCin()+" ) ";
        ResultSet result = ConnectionBD.executeSelectQuery(query);

        while (result.next()) {
            Module m = new Module(Integer.parseInt(result.getString("codeModule")),
                    result.getString("nomComplet"),
                    Integer.parseInt(result.getString("codeNivEtude")));
           listeModulePerEnseigant.add(m);
        }

    }
    
    
    
      public static boolean deleteRelation(int indexModule, int indexEnseignant) {
        boolean rest = true;
        String query = " delete from  enseignermodule where codeModule = "
                + listeModulePerEnseigant.get(indexModule).getCode()+
                " and enseignantCin = "  + listeUtilisateur.get(indexEnseignant).getCin();
            
        try {
            ConnectionBD.executeUpdateQuery(query);
        } catch (SQLException ex) {
            rest = false;
            JOptionPane.showMessageDialog(null, "cette relation existeDeja");
        }
        return rest;
    }

    public static boolean addRelation(int indexModule, int indexEnseignant) {
        boolean rest = true;
        String query = " insert into  enseignermodule (enseignantCin, codeModule)"
                + " values( " + listeUtilisateur.get(indexEnseignant).getCin() + "," + listeModule.get(indexModule).getCode() + ")";
        try {
            ConnectionBD.executeUpdateQuery(query);
        } catch (SQLException ex) {
            rest = false;
            JOptionPane.showMessageDialog(null, "cette relation existeDeja");
        }
        return rest;
    }

    public static void loadlisteUtilisateur() throws SQLException {

        listeUtilisateur = new ArrayList<Utilisateur>();
        String query = " select * from Utilisateur WHERE statue = 'Enseignant' ";
        ResultSet rest = ConnectionBD.executeSelectQuery(query);
        while (rest.next()) {
            Utilisateur u;
            if (rest.getString("statue").equals("Enseignant")) {
                u = new jeao.model.Enseignant(rest.getInt("cin"), rest.getString("nom"), rest.getString("prenom"), rest.getString("mdp"), rest.getString("pseudo"));
            } else {
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

}
