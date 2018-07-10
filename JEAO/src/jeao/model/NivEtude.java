package jeao.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static jeao.Controler.CWlcPage.connected;

public class NivEtude {

    private String nom;
    private int code;

    /**
     * *******
     *
     * constructeurs
     *
     *
     */
    public NivEtude(String nom) {
        this.nom = nom;
    }

    public NivEtude(int code, String nom) {
        this.nom = nom;
        this.code = code;
    }

    /**
     * *******
     *
     * getters
     *
     *
     */
    public String getNom() {
        return nom;
    }

    public int getCode() {
        return code;
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
        return "NivEtude{" + "nom=" + nom + ", code=" + code + '}';
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
        final NivEtude other = (NivEtude) obj;
        if (this.code != other.code) {
            return false;
        }
        return true;
    }

    public void supprimer() throws SQLException {
        String query = "DELETE FROM nivEtude  "
                + "WHERE codeNivEtude = " + code;
        ConnectionBD.executeUpdateQuery(query);
        for (int i = 0; i < 5; i++) {
            ConnectionBD.nathef();
        }

    }

    public boolean ajouter() throws SQLException {
        boolean rest = true;
        if (existe()) {
            JOptionPane.showMessageDialog(null, "Ce module existe deja");
            rest = false;
        } else {
            String quary = "INSERT INTO NivEtude (niv)"
                    + "VALUES ('" + nom + "');";
            ConnectionBD.executeUpdateQuery(quary);
        }

        return rest;

    }

    public boolean existe() throws SQLException {
        String query = "select * from nivEtude  where niv = '" + nom +"'";
        ResultSet rest = ConnectionBD.executeSelectQuery(query);
        return rest.next();

    }

    public ArrayList<Module> getModules() throws SQLException {
        ArrayList<Module> listeModule = new ArrayList<Module>();
        String query = "select  * from module "
                + " where codenivEtude= " + code;
        ResultSet result = ConnectionBD.executeSelectQuery(query);

        while (result.next()) {
            Module m = new Module(Integer.parseInt(result.getString("codeModule")),
                    result.getString("nomComplet"),
                    Integer.parseInt(result.getString("codeNivEtude")));
            listeModule.add(m);
        }

        return listeModule;
    }

    public ArrayList<Source> getSources() throws SQLException {

        ArrayList<Source> listeSource = new ArrayList<Source>();
        ArrayList<Source> listeSource2;

        ArrayList<Module> listeModule = this.getModules();
        for (int i = 0; i < listeModule.size(); i++) {
            listeSource2 = listeModule.get(i).getSources();
            for (int j = 0; j < listeSource2.size(); j++) {
                listeSource.add(listeSource2.get(j));
            }
        }
        return listeSource;
    }

    public ArrayList<Etudiant> getEtudiants() throws SQLException {

        ArrayList<Etudiant> listeEtudiant = new ArrayList<Etudiant>();
        String query = "select  * from Utilisateur where codenivEtude= " + code;
        ResultSet rest = ConnectionBD.executeSelectQuery(query);

        while (rest.next()) {
            Etudiant e = new Etudiant(rest.getInt("codeNivEtude"),
                    rest.getInt("cin"), rest.getString("nom"),
                    rest.getString("prenom"), rest.getString("mdp"),
                    rest.getString("pseudo"));
            listeEtudiant.add(e);
        }

        return listeEtudiant;
    }

    public ArrayList<Enseignant> getEnseignants() throws SQLException {

        ArrayList<Enseignant> listeEnseignant = new ArrayList<Enseignant>();
        String query = "select * from utilisateur "
                + "where cin in  "
                + "(select enseignantCin "
                + " FROM enseignermodule, module "
                + " WHERE enseignermodule.codeModule = module.codeModule "
                + " AND module.codenivEtude=" + code + ")";
        ResultSet rest = ConnectionBD.executeSelectQuery(query);

        while (rest.next()) {
            Enseignant e = new Enseignant(rest.getInt("cin"), rest.getString("nom"),
                    rest.getString("prenom"), rest.getString("mdp"),
                    rest.getString("pseudo"));
            listeEnseignant.add(e);
        }

        return listeEnseignant;
    }

}
