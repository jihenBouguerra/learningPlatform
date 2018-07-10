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
import jeao.model.Enseignant;
import jeao.model.Etudiant;
import jeao.model.Examen;
import jeao.model.Module;
import jeao.model.NivEtude;
import jeao.model.Utilisateur;

/**
 *
 * @author bouguerra
 */
public class CMesModules {
    private ArrayList<Module> vosModule ;
     private ArrayList<Module> totalModule ;
     private ArrayList<Examen> vosExamen;

    public ArrayList<Module> getTotalModule() {
        return totalModule;
    }
     

    public ArrayList<Examen> getVosExamen() {
        return vosExamen;
    }
    public void updateTotalModule () throws SQLException{
       String quary = "select * from Module";
        ResultSet result = ConnectionBD.executeSelectQuery(quary);
       totalModule= new ArrayList<Module> ();
        while (result.next()) {
            Module m = new Module(Integer.parseInt(result.getString("codeModule")),
                    result.getString("nomComplet"),
                    Integer.parseInt(result.getString("codeNivEtude")));
            totalModule.add(m);
        
    }}

    public CMesModules() {
        this.vosModule = new  ArrayList<Module>();
        
    }
    public void  listModuleDe(Utilisateur  e) throws SQLException {
        if (e.estEtudiant())
         listModule(e.getCin(), 'U');
        else 
            listModule(e.getCin(), 'E');
    }

    public ArrayList<Module> getVosModule() {
        return vosModule;
    }
    

    public void listModuleDe(NivEtude e) throws SQLException {
        listModule(e.getCode(), 'N');
    }

    private void listModule(int id, char a) throws SQLException {
        String quary = "";
        switch (a) {
            case 'N':
                quary = "select * from Module where nivEtude = "
                        + id + ";";
                break;
            case 'E':
                quary = "select * from Module ,enseignermodule"
                        + " where module.codeModule= enseignermodule.codeModule  "
                        + "and enseignermodule.enseignantCin=" + id + ";";
                break;
            case 'T':
                quary = "select * from Module";
                break;
            case 'U':
                quary = "SELECT * from module, utilisateur "
                        + "WHERE module.codenivEtude= utilisateur.codenivEtude "
                        + " and utilisateur.cin  = " + id + ";";
                break;

        }
        ResultSet result = ConnectionBD.executeSelectQuery(quary);
       vosModule= new ArrayList<Module> ();
        while (result.next()) {
            Module m = new Module(Integer.parseInt(result.getString("codeModule")),
                    result.getString("nomComplet"),
                    Integer.parseInt(result.getString("codeNivEtude")));
            vosModule.add(m);
        }
      
    }
         private void listExamen(int id, char a) throws SQLException {
        String quary = null;
        switch (a) {
            case 'M':
                quary = "select * from examen where codeModule  =" + id + ";";
                break;
            case 'E':
                quary = "select * from examen where enseignantCin  =" + id + ";";
            case 'T':
                quary = "select * from examen";
                break;
            case 'N': 
                quary = "select * from Examen,Module where "
                        + "examen.codemodule = module.codemodule and module.codeNivEtude ="+id+";";
            break;
        }
 vosExamen = new ArrayList<Examen> ();
        ResultSet result = ConnectionBD.executeSelectQuery(quary);
        
        while (result.next()) {
            Examen e = new Examen(result.getString("titre"),
                    Integer.parseInt(result.getString("codeModule")),
                    Integer.parseInt(result.getString("enseignantCin")),
                   
                    Integer.parseInt(result.getString("codeExamen")));
            vosExamen.add(e);

        }

    }
     
    public void listTotalExamen() throws SQLException  {
       listExamen(0,'T');
    }
    public void listTotalExamen(Enseignant prof) throws SQLException  {
       listExamen(prof.getCin(),'E');
    }
     public void listTotalExamen(NivEtude n) throws SQLException  {
       listExamen(n.getCode(),'N');
    }
    public void listTotalExamen(Module m) throws SQLException  {
       listExamen(m.getCode(),'M');
    }
    
}
