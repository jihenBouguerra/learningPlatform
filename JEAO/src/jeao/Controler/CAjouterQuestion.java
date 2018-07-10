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
import static jdk.nashorn.internal.objects.NativeString.trim;
import jeao.model.ConnectionBD;
import jeao.model.Module;
import jeao.model.Question;
import jeao.View.AjouterQuestion_1;

/**
 *
 * @author bouguerra
 */
public class CAjouterQuestion {

    private static ArrayList<Question> listeQuestionAjouter = new ArrayList<Question>();
   
    
    public static ArrayList<Question> getListeQuestionAjouter() {
        return listeQuestionAjouter;
    }

  

    public static void upDateDB() throws SQLException {
        CAjouterExamen.getExamen().ajouter();
        for (int i = 0; i < listeQuestionAjouter.size(); i++) {
            listeQuestionAjouter.get(i).setIdExamen( CAjouterExamen.getExamen().getCode());
            listeQuestionAjouter.get(i).addQuestionBD();
      
        }

    }

 

    public static void AjoutEffectue(Question q) throws SQLException {
        if (listeQuestionAjouter.size() != CAjouterExamen.getNbQuestion() - 1) {
            listeQuestionAjouter.add(q);
        } else {
            listeQuestionAjouter.add(q);
            upDateDB();
            JOptionPane.showMessageDialog(null, "Ajouté avec succée");

        }

    }

}
