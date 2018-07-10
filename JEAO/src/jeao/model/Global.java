/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.model;

import java.util.ArrayList;

/**
 *
 * @author bouguerra
 */
public class Global {
//int codeNivEtude, int cin, String nom, String penom, String mDP, String pseudo

   // public static Utilisateur connected = new Enseignant(44445555, "joujou1", "joujou1", "joujou1", "joujou1");
    public static Utilisateur connected = new Etudiant (2,44445555, "joujou1", "joujou1", "joujou1", "joujou1");
    public static Examen examen;
      public static ArrayList<String> rep = new ArrayList<String>();
    public static ArrayList<Reponse> reponse;
    public static ArrayList<Question> enance;

}
