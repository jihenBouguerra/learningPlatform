package jeao.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import jeao.Controler.CWlcPage;
import java.lang.String;

public class Enseignant extends Utilisateur {

    /**
     * *******
     *
     * constructeur
     *
     *
     */
    public Enseignant(int cin, String nom, String penom, String mDP, String pseudo) {
        super(cin, nom, penom, mDP, pseudo);
    }

    /**
     * *******
     *
     * methodes
     *
     *
     */
    public boolean estEeudiant() {
        return false;
    }

    @Override
    public ArrayList<Module> getModules() throws SQLException {
        ArrayList<Module> listeModule = new ArrayList<Module>();
        String query = "select * "
                + "from Module "
                + " where codeModule in (select codeModule "
                + "from enseignermodule where  "
                + " enseignantCin = " + super.getCin() + ")";
        ResultSet result = ConnectionBD.executeSelectQuery(query);

        while (result.next()) {
            Module m = new Module(Integer.parseInt(result.getString("codeModule")),
                    result.getString("nomComplet"),
                    Integer.parseInt(result.getString("codeNivEtude")));
            listeModule.add(m);
        }

        return listeModule;
    }

    @Override
    public ArrayList<Examen> getExamens() throws SQLException {
        ArrayList<Examen> listeExamen = new ArrayList<Examen>();
        String query = "SELECT * from examen  "
                + "where enseignantCin= " + super.getCin();
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

    /*   public ArrayList<Examen> getExamens() throws SQLException {
        ArrayList<Examen> listeExamen = new ArrayList<Examen>();
        String query = "SELECT * from examen  "
                + "where enseignantCin= " + super.getCin();
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        while (result.next()) {
            Examen e = new Examen(result.getString("titre"),
                    Integer.parseInt(result.getString("codeModule")),
                    Integer.parseInt(result.getString("enseignantCin")),
                    Integer.parseInt(result.getString("codeExamen")));
            listeExamen.add(e);

        }
        return listeExamen;

    }*/
 /*   }
     public ArrayList<Examen> getExamensNCorriger() throws SQLException {
        ArrayList<Examen> listeExamen = new ArrayList<Examen>();
        String query = "select *  "
                + "from examen e  "
                + "where codeExamen in"
                + " (select codeExamen from Question ,repence "
                + "  where codeQuestion = id_question   "
                + " and cin_etudiant =" + super.getCin() + ") "
                + "and  codeExamen not in (select codeExamen  from examenValide )";

        ResultSet result = ConnectionBD.executeSelectQuery(query);
        while (result.next()) {
            Examen e = new Examen(result.getString("titre"),
                    Integer.parseInt(result.getString("codeModule")),
                    Integer.parseInt(result.getString("enseignantCin")),
                    Integer.parseInt(result.getString("codeExamen")));
            listeExamen.add(e);

        }
        ConnectionBD.disconnect();
        return listeExamen;

    }*/
    public void getQusetionACorriger() throws SQLException {
        ArrayList<Reponse> rep;
        ArrayList<Question> enance;
        rep = new ArrayList<Reponse>();
        enance = new ArrayList<Question>();

        String query = "SELECT * from examen,reponse ,Question  "
                + "where examen.codeExamen =Question.codeExamen "
                + "and  codeQuestion =id_Question "
                + "and  correction  = 0 "
                + "and enseignantCin= " + super.getCin();
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        while (result.next()) {

            Question q = new Question(result.getString("enance"),
                    result.getInt("repCorrect"), result.getString("choi1"),
                    result.getString("choi2"), result.getString("choi3"),
                    result.getString("choi4"), result.getInt("codeQuestion"),
                    result.getInt("codeExamen"));

            Reponse r = new Reponse(result.getInt("id"),
                    result.getInt("id_Question"),
                    result.getString("reponse_Etudiant"),
                    result.getInt("cin_Etudiant"), result.getInt("correction"));
            rep.add(r);
            enance.add(q);
        }
        Global.reponse = rep;
        Global.enance = enance;

    }

    public void supprimerModule(Module m) {
        boolean rest = true;
        String query = " delete from  enseignermodule where codeModule = " + m.getCode()
                + " and enseignantCin = " + super.getCin();

        try {
            ConnectionBD.executeUpdateQuery(query);
        } catch (SQLException ex) {
            rest = false;
            // JOptionPane.showMessageDialog(null, "cette relation existeDeja");
        }

    }

    public void ajouterModule(Module m) {
        boolean rest = true;
        String query = " insert into  enseignermodule (enseignantCin, codeModule)"
                + " values( " + super.getCin() + "," + m.getCode() + ")";
        try {
            ConnectionBD.executeUpdateQuery(query);
        } catch (SQLException ex) {
            rest = false;
            //  JOptionPane.showMessageDialog(null, "cette relation existeDeja");
        }
        //return rest;
    }
public boolean estEtudiant() {
        return false ;
    }
    @Override
    public int ajouter() throws SQLException {

        String quary = "INSERT INTO utilisateur(cin,mDP,nom,prenom, pseudo,statue)"
                + "VALUES (" + super.getCin()
                + ",'" + super.getmDP()
                + "','" + super.getNom()
                + "','" + super.getPenom()
                + "','" + super.getPseudo()
                + "','" + "Enseignant" + "');";

        int rest = ConnectionBD.executeUpdateQuery(quary);

        return rest;

    }

    public ArrayList<Source> getSources() throws SQLException {
        ArrayList<Source> listeSource = new ArrayList<Source>();
        String query = "select * from source where cinEnseignant = " + super.getCin();
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

}
