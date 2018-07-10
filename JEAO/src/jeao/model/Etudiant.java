package jeao.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import jeao.Controler.CWlcPage;
import jeao.model.Utilisateur;

public class Etudiant extends Utilisateur {

    private int codeNivEtude;

    /**
     * *******
     *
     * constructeurs
     *
     *
     */
    public Etudiant(int cin, String nom, String penom, String mDP, String pseudo) {
        super(cin, nom, penom, mDP, pseudo);
    }

    public Etudiant(int codeNivEtude, int cin, String nom, String penom, String mDP, String pseudo) {
        super(cin, nom, penom, mDP, pseudo);
        this.codeNivEtude = codeNivEtude;
    }

    /**
     * *******
     *
     * methodes
     *
     *
     */
    public boolean estEtudiant() {
        return true;
    }

    public void ajouterRep(Examen ex, ArrayList<String> rep) throws SQLException {
        for (int i = 0; i < ex.getListeQuestion().size(); i++) {
            String query = "INSERT INTO reponse( id_question, cin_etudiant, reponse_etudiant,correction )VALUES ("
                    + ex.getListeQuestion().get(i).getId()
                    + "," + super.getCin() + ",'" + rep.get(i) + "',"+0+");";
            ConnectionBD.executeUpdateQuery(query);

        }
    }

    public int getCodeNivEtude() {
        return codeNivEtude;
    }

    @Override
    public ArrayList<Module> getModules() throws SQLException {
        ArrayList<Module> listeModule = new ArrayList<Module>();
        String query = "select  * from module "
                + " where codenivEtude= " + codeNivEtude;
        ResultSet result = ConnectionBD.executeSelectQuery(query);

        while (result.next()) {
            Module m = new Module(Integer.parseInt(result.getString("codeModule")),
                    result.getString("nomComplet"),
                    Integer.parseInt(result.getString("codeNivEtude")));
            listeModule.add(m);
        }
        ConnectionBD.disconnect();
        return listeModule;
    }

    @Override
    public ArrayList<Examen> getExamens() throws SQLException {
        ArrayList<Examen> listeExamen = new ArrayList<Examen>();
        String query = "SELECT * from examen  "
                + "where codeModule in (SELECT codeModule  "
                + "  from module where codenivEtude = " + codeNivEtude + ")";
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

    }

    public ArrayList<Examen> getExamensValide() throws SQLException {
        ArrayList<Examen> listeExamen = new ArrayList<Examen>();
        String query = "select  examen.titre , examen.enseignantCin ,examen.codeExamen ,examen.codeModule, score "
                + "from module , examen ,  examenvalide "
                + "where module.codeModule = examen.codeModule "
                + " and examen.codeExamen  =  examenvalide.codeExamen "
                + " and examenvalide.cinEtudiant =" + super.getCin() + ""
                + " and examenvalide.score >= 50  ";

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

    }

    public ArrayList<Examen> getExamensRate() throws SQLException {
        ArrayList<Examen> listeExamen = new ArrayList<Examen>();
        String query = "select  examen.titre , examen.enseignantCin ,examen.codeExamen ,examen.codeModule, score "
                + "from module , examen ,  examenvalide "
                + "where module.codeModule = examen.codeModule "
                + " and examen.codeExamen  =  examenvalide.codeExamen "
                + " and examenvalide.cinEtudiant =" + super.getCin() + ""
                + " and examenvalide.score < 50  ";

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

    }

    public ArrayList<Examen> getExamensNpasse() throws SQLException {
        ArrayList<Examen> listeExamen = new ArrayList<Examen>();
        String query = "select *  "
                + "from examen e "
                + "where codeExamen not in"
                + " (select codeExamen from Question ,reponse "
                + "  where codeQuestion = id_question   "
                + " and cin_etudiant =" + super.getCin() + ") "
                + " and  codeExamen not in  ( select codeExamen from examenvalide "
                + "where cinEtudiant= "+super.getCin()+" )";

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

    }

    public void ecaluation() throws SQLException {
        ArrayList<Examen> l = getExamensPNC();
        for (int i = 0; i < l.size(); i++) {
            ConnectionBD.evalution(this, l.get(i));
        }
    }

    public ArrayList<Examen> getExamensPNC() throws SQLException {
        ArrayList<Examen> listeExamen = new ArrayList<Examen>();
        String query = "SELECT * from examen \n"
                + "WHERE codeExamen in (SELECT examen.codeExamen\n"
                + "                     from reponse, examen,question \n"
                + "                     where reponse.id_question = question.codeQuestion\n"
                + "                     and examen.codeExamen = question.codeExamen\n"
                + "                     and reponse.cin_etudiant =" +super.getCin()+ " )\n"
                + "and codeExamen not in (SELECT codeExamen \n"
                + "                       from examenvalide\n"
                + "                       where examenvalide.cinEtudiant="+super.getCin()+");";

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

    }

    @Override
    public int ajouter() throws SQLException {

        String quary = "INSERT INTO utilisateur(cin,mDP,nom,prenom, pseudo,statue,codeNivEtude)"
                + "VALUES (" + super.getCin()
                + ",'" + super.getmDP()
                + "','" + super.getNom()
                + "','" + super.getPenom()
                + "','" + super.getPseudo()
                + "','" + "Etudiant"
                + "'," + codeNivEtude + ");";

        int rest = ConnectionBD.executeUpdateQuery(quary);
        ConnectionBD.disconnect();

        return rest;

    }

}
