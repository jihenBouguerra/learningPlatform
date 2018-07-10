/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import static javafx.beans.binding.Bindings.select;
import jeao.model.ConnectionBD;
import jeao.model.Enseignant;
import jeao.model.Etudiant;
import jeao.model.NivEtude;
import jeao.model.Utilisateur;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;

/**
 *
 * @author bouguerra
 */
public class CSignUp {
//int cin, String dateDeNaissance, String nom, String penom, String mDP, String pseudo
    public static Utilisateur compte ;
    private boolean etudiant;
    private ArrayList<NivEtude> listeNivEtude;
    
     private  int ajouterEtudiantBD(Etudiant e) throws SQLException {
     
        String quary ="INSERT INTO utilisateur(cin,dateDeNaissance,mDP,nom,prenom, pseudo,statue,codeNivEtude)"
                        + "VALUES (" + e.getCin()
                        + ",'" + e.getmDP()
                        + "','" + e.getNom()
                        + "','" + e.getPenom()
                        + "','" + e.getPseudo()
                        + "','" + "Etudiant"
                        +"',"+ e.getCodeNivEtude()+ ");";
              
       
        int rest = ConnectionBD.executeUpdateQuery(quary);

        return rest;

    }
     public int signUpEtudiant(Etudiant e) throws SQLException{
        return ajouterEtudiantBD(e);
    }
    
    public int signUpEnseigant(Enseignant e) throws SQLException{
        return ajouterEnseignantBD(e);
    }
    private int ajouterEnseignantBD(Enseignant e) throws SQLException {
        
        String quary = "INSERT INTO utilisateur(cin,dateDeNaissance,mDP,nom,prenom, pseudo,statue)"
                + "VALUES (" + e.getCin()
                + ",'" + e.getmDP()
                + "','" + e.getNom()
                + "','" + e.getPenom()
                + "','" + e.getPseudo()
                + "','" + "Enseignant"+ "');";
              
       
        int rest = ConnectionBD.executeUpdateQuery(quary);

        return rest;

    }
    
   public int idNivEtude(String niv){
       boolean tr = false;
       int i=0;
       while (!tr && i< listeNivEtude.size()){
           if (niv.equals(listeNivEtude.get(i)))
               tr= true; 
           i++;
       }
      return  listeNivEtude.get(i-1).getCode();
                
    }
   
public  boolean isParsable(String input){
    boolean parsable = true;
    try{
        Integer.parseInt(input);
    }catch(NumberFormatException e){
        parsable = false;
    }
    return parsable;
}
    public boolean valideCin(String cin) throws SQLException {
        boolean rest = false;
        if (isParsable(cin) &&(cin.length()==8)&& !existeCinBD(cin)){
            rest = true ;
        }
       
        return rest;
    }

    public void listNivEtude() throws SQLException {
        listNivEtude(0, 'T');
    }
    public boolean peseudoUtilise(String pseudo) throws SQLException {
        return existePseudo(pseudo);
    }

    private boolean existePseudo( String pseudo ) throws SQLException {
        String quary = "select * from utilisateur where pseudo='" + pseudo + "';";
        ResultSet result = ConnectionBD.executeSelectQuery(quary);
        return result.next();
     
    }

    private void listNivEtude(int id, char a) throws SQLException {
        String quary = "";
        switch (a) {
            case 'E':
                quary = "select * from nivEtude ,enseignermodule, module "
                        + " niEtude.codeNivEtude e= enseignermodule.codeModule  "
                        + " niEtude.codeNivEtude e= enseignermodule.codeModule  "
                        + "and enseignantCin=" + id + ";";
                break;
            case 'T':
                quary = "select * from nivEtude;";
                break;
        }
        ResultSet result = ConnectionBD.executeSelectQuery(quary);
        listeNivEtude = new ArrayList<NivEtude>();
        while (result.next()) {
            NivEtude n = new NivEtude(result.getInt("codenivEtude"),
                    result.getString("niv"));
            listeNivEtude.add(n);
        }

    }

    public void setCompte(Utilisateur compte) throws SQLException {
        this.compte = compte;
    }

    public String elementAt(int i) {
        return listeNivEtude.get(i).getNom();
    }

    public int totalNiv() throws SQLException {
        listNivEtude();
        return listeNivEtude.size();
    }

    private boolean existeCinBD(String cin) throws SQLException {
        String quary = "select * from utilisateur where cin='" + cin + "';";
        ResultSet result = ConnectionBD.executeSelectQuery(quary);
        // cin utilise 
       
        if (result.next()) {
            System.out.print("cin existe  ");
            return true;
        } else {
            return false;
        }

    }
}
