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
import jeao.model.Examen;
import jeao.model.Module;

/**
 *
 * @author bouguerra
 */
public class CAjouterExamen {
     
    private static int typeExamen ; //0= QCM 1= classique
    private static Examen examen;
    private static ArrayList<Module> listeModule;
    private static int nbQuestion;
     private static Module  moduleSelectionne; 

    public static void setTypeExamen(int typeExamen) {
        CAjouterExamen.typeExamen = typeExamen;
    }

    public static int getTypeExamen() {
        return typeExamen;
    }


    public static Examen getExamen() {
        return examen;
    }

   
    public CAjouterExamen() throws SQLException {
   listeModule = CWlcPage.connected.getModules();
    }

    public void getInformations(String titre, int nbQuestion, Module m) {
        examen = new Examen(titre,m.getCode(), CWlcPage.connected.getCin()); 
        this.nbQuestion = nbQuestion;

    }

    public static ArrayList<Module> getListeModule() {
        return listeModule;
    }


    public static int getNbQuestion() {
        return nbQuestion;
    }

  
    
}
