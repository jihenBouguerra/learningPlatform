package jeao.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import jeao.Controler.CWlcPage;
import static jeao.Controler.CWlcPage.connected;

public class Utilisateur {

    private int cin;
    private String nom;
    private String prenom;
    private String mDP;
    private String pseudo;

    /**
     * *******
     *
     * constructeur
     *
     *
     */
    public Utilisateur(String pseudo, String mDP) {
        this.mDP = mDP;
        this.pseudo = pseudo;
    }

    public Utilisateur(int cin, String nom, String penom, String mDP, String pseudo) {
        this.cin = cin;

        this.nom = nom;
        this.prenom = penom;
        this.mDP = mDP;
        this.pseudo = pseudo;
    }

    /**
     * *******
     *
     * geters
     *
     *
     */
    public int getCodeNivEtude() {
        return 0;
    }

    public int getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPenom() {
        return prenom;
    }

    public String getmDP() {
        return mDP;
    }

    public String getPseudo() {
        return pseudo;
    }

    /**
     * *******
     *
     * seters
     *
     *
     */
    public void setCin(int cin) {
        this.cin = cin;

    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPenom(String penom) {
        this.prenom = penom;
    }

    public void setmDP(String mDP) {
        this.mDP = mDP;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * *******
     *
     * metodes
     *
     *
     */
    public boolean existeCompte() throws SQLException {

        String query = "select * from utilisateur where pseudo= '"
                + pseudo + "' and mdp = '" + mDP + "' ;";
        ResultSet rs = ConnectionBD.executeSelectQuery(query);

        return (rs.next());

    }

    public Utilisateur infoCompte() throws SQLException {
        String query = "select * from utilisateur where pseudo= '"
                + pseudo + "' and mdp = '" + mDP + "' ;";
        ResultSet rest = ConnectionBD.executeSelectQuery(query);
        rest.next();
        switch (rest.getString("statue")) {
            case "Etudiant":
                connected = new Etudiant(rest.getInt("codeNivEtude"), rest.getInt("cin"), rest.getString("nom"), rest.getString("prenom"), rest.getString("mdp"), rest.getString("pseudo"));
                break;
            case "Enseignant":
                connected = new Enseignant(rest.getInt("cin"), rest.getString("nom"), rest.getString("prenom"), rest.getString("mdp"), rest.getString("pseudo"));
                break;
        }
        return connected;

    }

    public ArrayList<Examen> getExamens() throws SQLException {
        ArrayList<Examen> listeExamen = new ArrayList<Examen>();

        return listeExamen;

    }

    public void modifier() throws SQLException {
        String query = "UPDATE utilisateur SET nom = '"
                + nom + "' , prenom = '"
                + prenom + "' , mdp = '"
                + mDP + "' "
                + "WHERE cin = " + cin;
        ConnectionBD.executeUpdateQuery(query);

    }

    public void supprime() throws SQLException {
        String query = "DELETE FROM  Utilisateur "
                + "WHERE cin = " + cin;
        ConnectionBD.executeUpdateQuery(query);
        for (int i = 0; i < 5; i++) {
            ConnectionBD.nathef();
        }
    }

    public boolean estEtudiant() {
        return false ;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "cin=" + cin + ", dateDeNaissance=" + ", nom=" + nom + ", penom=" + prenom + ", mDP=" + mDP + ", pseudo=" + pseudo + '}';
    }

    public boolean peseudoUtilise() throws SQLException {
        return existePseudo();
    }

    private boolean existePseudo() throws SQLException {
        String quary = "select * from utilisateur where pseudo='" + pseudo + "';";
        ResultSet result = ConnectionBD.executeSelectQuery(quary);

        if (result.next()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean cinUtilise() throws SQLException {
        return existeCin();
    }

    private boolean existeCin() throws SQLException {
        String quary = "select * from utilisateur where cin='" + cin + "';";
        ResultSet result = ConnectionBD.executeSelectQuery(quary);

        if (result.next()) {
            return true;
        } else {
            return false;
        }

    }

    public Utilisateur() {
    }

    public ArrayList<Module> getModules() throws SQLException {

        ArrayList<Module> listeModule = new ArrayList<Module>();

        return listeModule;
    }

    public int ajouter() throws SQLException {
        return 0;
    }

    public ArrayList<Source> getSources() throws SQLException {
        ArrayList<Source> listeSource = new ArrayList<Source>();

        return listeSource;
    }

    public ArrayList<Examen> getExamensTotal() throws SQLException {
        ArrayList<Examen> listeExamen = new ArrayList<Examen>();
        String query = "SELECT * from examen  ";
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

    public ArrayList<NivEtude> getNivEtudeTotal() throws SQLException {

        ArrayList<NivEtude> listeNivEtude = new ArrayList<NivEtude>();
        String query = " select * from nivEtude ";
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        while (result.next()) {
            NivEtude n = new NivEtude(result.getInt("codenivEtude"), result.getString("niv"));
            listeNivEtude.add(n);

        }
        return listeNivEtude;
    }

    public ArrayList<Module> getModulesTotal() throws SQLException {
        ArrayList<Module> listeModule = new ArrayList<Module>();
        String query = "select  * from module ";
        ResultSet result = ConnectionBD.executeSelectQuery(query);

        while (result.next()) {
            Module m = new Module(Integer.parseInt(result.getString("codeModule")),
                    result.getString("nomComplet"),
                    Integer.parseInt(result.getString("codeNivEtude")));
            listeModule.add(m);
        }

        return listeModule;
    }

    public ArrayList<Source> getSourcesTotal() throws SQLException {
        ArrayList<Source> listeSource = new ArrayList<Source>();
        String query = "select * from source";
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

    public void getQusetionCorriger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
