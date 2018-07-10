/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Reponse {

    private int id;
    private int idQuestion;
    private String laReponse;
    private int cinEtudiant;

    public String getLaReponse() {
        return laReponse;
    }
    private int corriger;

    public int getCorriger() {
        return corriger;
    }

    /**
     * *******
     *
     * constructeurs
     *
     *
     */
    public Reponse(int id, int idQuestion, String reponseEtudiant, int cinEtudiant, int corriger) {
        this.cinEtudiant = cinEtudiant;
        this.id = id;
        this.idQuestion = idQuestion;
        this.laReponse = reponseEtudiant;
        this.corriger = corriger;

    }

    public void modifier() throws SQLException {
        String query = "UPDATE reponse "
                + " SET correction = " + corriger
                + "  WHERE id = " + id;
        ConnectionBD.executeUpdateQuery(query);

    }

    public void setCorriger(int corriger) {
        this.corriger = corriger;
    }

    /**
     * *******
     *
     * getters
     *
     *
     */
    public int getId() {
        return id;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public int getCinEtudiant() {
        return cinEtudiant;
    }

    /**
     * *******
     *
     * setters
     *
     *
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public void setReponseEtudiant(String reponseEtudiant) {
        this.laReponse = reponseEtudiant;
    }

    public void setCinEtudiant(int cinEtudiant) {
        this.cinEtudiant = cinEtudiant;
    }

    /**
     * *******
     *
     * methodes
     *
     *
     */
    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", idQuestion=" + idQuestion + ", ReponseEtudiant=" + laReponse + ", cinEtudiant=" + cinEtudiant + '}';
    }

    public void ajouter() throws SQLException {
        String query = "insert into reponse"
                + " (id_question,reponse_etudiant, cin_etudiant) "
                + " values (" + idQuestion
                + " ,'" + laReponse
                + "', " + cinEtudiant
                + ")";
        ConnectionBD.executeUpdateQuery(query);
        query = "select id from reponse where cin_etudiant = " + cinEtudiant + " AND id_question = " + idQuestion;
        ResultSet res = ConnectionBD.executeSelectQuery(query);
        res.next();
        this.id = res.getInt(1);

    }

    public Reponse getReponse() throws SQLException {
        String query = "select * from reponse where id = " + id;
        Reponse r = null;
        ResultSet rest = ConnectionBD.executeSelectQuery(query);
        rest.next();

        // r = new Reponse(rest.getInt("id"), rest.getInt("id_question"), rest.getString("reponse_etudiant"), rest.getInt("cin_etudiant"));
        return r;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reponse other = (Reponse) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Question getQuestion() throws SQLException {
        String query = "select * from question where codeQuestion = " + idQuestion;

        ResultSet result = ConnectionBD.executeSelectQuery(query);
        result.next();
        //String enance, int repCorrect, String choi1, String choi2, String choi3, String choi4
        Question q = new Question(result.getString("enance"), result.getInt("repCorrect"), result.getString("choi1"), result.getString("choi2"), result.getString("choi3"), result.getString("choi4"));
        return q;
    }

}
