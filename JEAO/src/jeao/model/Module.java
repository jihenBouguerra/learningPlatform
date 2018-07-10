package jeao.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Module {

    private int code;
    private String nomComplet;
    private int codeNivEtude;

    /**
     * *******
     *
     * constructeurs
     *
     *
     */
    public Module(int code) {
        this.code = code;
    }

    public Module(int code, String nomComplet, int codeNivEtude) {
        this.code = code;
        this.nomComplet = nomComplet;
        this.codeNivEtude = codeNivEtude;
    }

    public Module(String nomComplet, int codeNivEtude) {
        this.nomComplet = nomComplet;
        this.codeNivEtude = codeNivEtude;
    }

    public Module(int code, String nomComplet) {
        this.code = code;
        this.nomComplet = nomComplet;
    }

    /**
     * *******
     *
     * getters
     *
     *
     */
    public int getCodeNivEtude() {
        return codeNivEtude;
    }

    public int getCode() {
        return code;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    /**
     * *******
     *
     * methode
     *
     *
     */
    @Override
    public String toString() {
        return "Module{" + "code=" + code + ", nomComplet=" + nomComplet + ", codeNivEtude=" + codeNivEtude + '}';

    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final Module other = (Module) obj;
        if (this.code != other.code) {
            return false;
        }
        return true;
    }

    public boolean exiteNomModule() throws SQLException {
        String query = "select * from module where nomComplet = '" + nomComplet+"' and codeNivEtude ="+codeNivEtude+";";
        ResultSet rest = ConnectionBD.executeSelectQuery(query);
        if (rest.next()) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Source> getSources() throws SQLException {
        ArrayList<Source> listeSource = new ArrayList<Source>();
        String query = "select * from source where codeModule = " + code;
        ResultSet rest = ConnectionBD.executeSelectQuery(query);
        while (rest.next()) {
//String nomFichier, String URL, String format, String presantation, String type, int code, int enseigantcin, int codeModule
            Source s = new Source(rest.getString("nomFichier"),
                    rest.getString("URL"),
                    rest.getString("format"),
                    rest.getString("presantation"),
                    rest.getString("type"),
                    rest.getInt("codeSource"),
                    rest.getInt("cinEnseignant"),
                    rest.getInt("codeModule")
            );
            listeSource.add(s);

        }
        return listeSource;
    }

    public void supprimer() throws SQLException {
        String query = "DELETE FROM  module  "
                + "WHERE codeModule = " + code;
        ConnectionBD.executeUpdateQuery(query);
        for (int i = 0; i < 5; i++) {
            ConnectionBD.nathef();
        }
    }
     public void modifier() throws SQLException {
        String query = "UPDATE module SET nomComplet = '"
                + nomComplet+ "' , codeNivEtude = "
                + codeNivEtude + " WHERE codeModule = " + code;
        ConnectionBD.executeUpdateQuery(query);

    }

    public ArrayList<Question> getListQuetion() throws SQLException {
        String query = "SELECT * FROM question, questiondansexamen  "
                + "where question.codeQuestion= questiondansexamen.codeQuestion "
                + "and questiondansexamen.codeExamen IN  "
                + " (SELECT codeExamen FROM examen where codemodule = " + code + " ) ;";
        ConnectionBD.executeUpdateQuery(query);
        ArrayList<Question> listeQuestionTotal = new ArrayList<Question>();
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        while (result.next()) {
            Question q = new Question(result.getString("enance"),
                    result.getInt("repCorrect"),
                    result.getString("choi1"),
                    result.getString("choi2"),
                    result.getString("choi3"),
                    result.getString("choi4"),
                    result.getInt("codeQuestion"));
            listeQuestionTotal.add(q);
        }
        return listeQuestionTotal;
    }

    public int ajouter() throws SQLException {
        int rest=0;
        if (exiteNomModule()) {
            JOptionPane.showMessageDialog(null, "Ce module existe deja");
        } else {
            String quary = "INSERT INTO module (nomComplet,codeNivEtude)"
                    + "VALUES ('" + nomComplet + "'," + codeNivEtude + ");";
           rest  = ConnectionBD.executeUpdateQuery(quary);
        }

        return rest;

    }

    public ArrayList<Examen> getExamens() throws SQLException {
        ArrayList<Examen> listeExamen = new ArrayList<Examen>();
        String query = "SELECT * from examen  "
                + "where codeModule  = " + code + " ;";
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        while (result.next()) {
            Examen e = new Examen(result.getString("titre"),
                    Integer.parseInt(result.getString("codeModule")),
                    Integer.parseInt(result.getString("enseignantCin")),
                    Integer.parseInt(result.getString("codeExamen")));
            listeExamen.add(e);

        }
        return listeExamen;

    }

    public NivEtude getNivEtude() throws SQLException {
        NivEtude n = null;
        String query = " select * from nivEtude where codeNivEtude =" + codeNivEtude;
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        result.next();
        n = new NivEtude(result.getInt("codenivEtude"), result.getString("niv"));
        return n;

    }
}
