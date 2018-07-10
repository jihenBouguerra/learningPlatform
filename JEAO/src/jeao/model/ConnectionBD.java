/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bouguerra
 */
public class ConnectionBD {

    private static final String dbname = "eao";
    private static final String dbadress = "localhost:3306";
    private static final String user = "root";
    private static final String pass = "";
    private static Connection myCon;

    public static void nathef() throws SQLException {
        String query = "  DELETE  FROM  examen  "
                + " WHERE examen.codeModule NOT IN (SELECT codeModule from module) "
                + " or examen.enseignantCin NOT in (SELECT cin FROM utilisateur )";
        executeUpdateQuery(query);
        query = "DELETE FROM utilisateur  "
                + "WHERE codenivEtude NOT IN (SELECT codenivEtude FROM nivetude)";

        executeUpdateQuery(query);
        query = "DELETE FROM module  "
                + "where codenivEtude not in (SELECT codenivEtude FROM nivetude) ";
        executeUpdateQuery(query);
        query = "DELETE FROM enseignermodule  "
                + "WHERE codeModule NOT in (SELECT codeModule FROM module) "
                + "or enseignantCin NOT in (SELECT cin FROM utilisateur) ";
        executeUpdateQuery(query);
        query = "DELETE FROM question "
                + "WHERE codeExamen not in ( SELECT codeExamen FROM examen)";
        executeUpdateQuery(query);
        query = "  DELETE FROM SOURCE   "
                + "WHERE codeModule  NOT IN (SELECT codeModule FROM module )"
                + "or cinEnseignant not in (SELECT cin FROM utilisateur)";
        executeUpdateQuery(query);
    }

    private static void connect() {
        myCon = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://" + dbadress + "/" + dbname, user, pass);
        } catch (Exception exc) {
            System.out.println("Failed to connect to the Database");
            myCon = null;
        }

    }

    public static void disconnect() {
        try {
            myCon.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBD.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println("catch error disconnect");
        }

    }

    public static void evalution(Etudiant e, Examen ex) throws SQLException {
        String query = "select codeExamen  ,"
                + " (  (( SELECT count(*) "
                + " FROM reponse, question "
                + " where reponse.id_question = question.codeQuestion "
                + " and question.codeExamen  = x.codeExamen "
                + " and reponse.cin_etudiant = " + e.getCin() + "  and correction=1  ) *100 )"
                + " / (SELECT count(*) "
                + " FROM question WHERE "
                + " question.codeExamen = x.codeExamen  ) )  "
                + "from examen x "
                + " where x.codeExamen = " + ex.getCode() + ";";
        ConnectionBD.executeSelectQuery(query);
        
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        while (result.next()) {
            int score = result.getInt(2);
           System.out.println(result.getInt(2) );
                    String queer = "insert into examenvalide "
                    + "(cinEtudiant , codeExamen , score ) "
                    + "values (" + e.getCin() + "," + ex.getCode() + "," + score + ")";
             ConnectionBD.executeUpdateQuery(queer);
        }

    }

    public static ResultSet executeSelectQuery(String quary) {
        Statement myStat = null;
        ResultSet myRes = null;
        connect();
        if (myCon != null) {
            try {
                myStat = myCon.createStatement();
                myRes = myStat.executeQuery(quary);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        // disconnect();
        return myRes;
    }

    public static int executeUpdateQuery(String quary) throws SQLException {
        Statement myStat = null;
        int rest = 0;
        connect();
        if (myCon != null) {
            myStat = myCon.createStatement();
            rest = myStat.executeUpdate(quary);
            disconnect();
        }
        return rest;
    }

}
