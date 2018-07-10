package jeao.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import jeao.Controler.CWlcPage;

public class Examen {

    private String titre;
    private int codeModule;
    private int codeEnseignant;
    private ArrayList<Question> listeQuestion;
    private int code;

    public Examen(String titre, int codeModule, int codeEnseignant) {
        this.titre = titre;
        this.codeModule = codeModule;
        this.codeEnseignant = codeEnseignant;
    }

    public Examen() {

    }

   /* public ArrayList<Reponse> getRep() throws SQLException {
        ArrayList<Reponse> listeRep = new ArrayList<Reponse>();
        String query = "SELECT *from question, reponse r, examen "
                + " where question.codeQuestion = r.id_question "
                + "and question.codeExamen = examen.codeExamen "
                + "and examen.codeExamen ="+code 
                +"  and examen.codeExamen not in "
                + "(SELECT codeExamen from examenvalide  WHERE examenvalide.cinEtudiant = r.cin_etudiant );";
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        while (result.next()) {
              Question q = new Question(result.getString("id"), result.getInt("id_Question"), result.getString("reponseEtudiant"), result.getString("cin_Etudiant"), result.getString("choi3"), result.getString("choi4"), result.getInt("codeQuestion"), result.getInt("codeExamen"));
            //int id, int idQuestion, String reponseEtudiant, int cinEtudiant, int corriger, Question question
              Reponse r=  Reponse( result.getInt("repCorrect"),  
                     result.getInt("repCorrect"), 
                     result.getString("choi3"), 
                     result.getInt("repCorrect"), 
                     result.getInt("repCorrect"),q);
       
          

        }
        ConnectionBD.disconnect();
        return listeExamen;

    }*/

    public Examen(String titre, int codeModule, int codeEnseignant, int code) {
        this.titre = titre;
        this.codeModule = codeModule;
        this.codeEnseignant = codeEnseignant;

        this.code = code;
    }

    public void getListeQuestionBD() throws SQLException {
        listeQuestion = new ArrayList<Question>();
        String query = "Select * from Question where  codeExamen = " + code;
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        while (result.next()) {
            Question q = new Question(result.getString("enance"), result.getInt("repCorrect"), result.getString("choi1"), result.getString("choi2"), result.getString("choi3"), result.getString("choi4"), result.getInt("codeQuestion"), result.getInt("codeExamen"));
            listeQuestion.add(q);
        }
        System.out.print(listeQuestion.size());
        ConnectionBD.disconnect();

    }

    @Override
    public String toString() {
        return "Examen{" + "titre=" + titre + ", codeModule=" + codeModule + ", codeEnseignant=" + codeEnseignant + ", listeQuestion=" + listeQuestion + ", code=" + code + '}';
    }

    public void supprimer() throws SQLException {
        String query = "DELETE FROM  examen   "
                + "WHERE codeExamen = " + code;
        ConnectionBD.executeUpdateQuery(query);
        for (int i = 0; i < 5; i++) {
            ConnectionBD.nathef();
        }
    }

    public int ajouterExamen() throws SQLException {
        return ajouter();
    }

    public int ajouter() throws SQLException {
        String quary = "INSERT INTO examen (titre,codeModule,enseignantCin)"
                + "  VALUES ( '" + titre
                + "' ," + codeModule
                + " , " + codeEnseignant + ");";
        int rest = ConnectionBD.executeUpdateQuery(quary);
        getIdDB();
        return rest;

    }

    public int getCodeModule() {
        return codeModule;
    }

    public int getCodeEnseignant() {
        return codeEnseignant;
    }

    public String getTitre() {
        return titre;
    }

    public void getIdDB() throws SQLException {
        String query = "select * "
                + "from examen"
                + " where titre = '" + titre + "' and enseignantCin = " + codeEnseignant;
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        result.next();
        code = result.getInt("codeExamen");

    }

    public void ajouterQuestion(Question q) {
        listeQuestion.add(q);
    }

    public ArrayList<Question> getListeQuestion() {
        return listeQuestion;
    }

    public void setListeQuestion(ArrayList<Question> listeQuestion) {
        this.listeQuestion = listeQuestion;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String toString(int note) {
        return titre + " score : " + note;
    }

    public void supprimerQuestion(Question q) {
        listeQuestion.remove((Question) q);
    }

    public void supprimerQuestion(int i) {
        listeQuestion.remove(i);
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCodeModule(int codeModule) {
        this.codeModule = codeModule;
    }

    public void setCodeEnseignant(int codeEnseignant) {
        this.codeEnseignant = codeEnseignant;
    }

    public boolean estQCM() {
        return listeQuestion.get(0).getRepCorrect() != 0;
    }

}
