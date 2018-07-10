package jeao.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Source {

    private String nomFichier;
    private String URL;
    private String format;
    private String presantation;
    private String type;
    private int code;
    private int enseignantcin;
    private int codeModule;

    /**
     * *******
     *
     * constructeurs
     *
     *
     */
    public Source(String nomFichier, String URL, int enseigantcin, int codeModule) {
        this.nomFichier = nomFichier;
        this.URL = URL;
        this.enseignantcin = enseigantcin;
        this.codeModule = codeModule;
    }

    public Source(String nomFichier, String URL, String format, String presantation, String type, int code, int enseigantcin, int codeModule) {
        this.nomFichier = nomFichier;
        this.URL = URL;
        this.format = format;
        this.presantation = presantation;
        this.type = type;
        this.code = code;
        this.enseignantcin = enseigantcin;
        this.codeModule = codeModule;
    }

    public Source(String nomFichier, String URL, String format, String presantation, String type, int enseigantcin, int codeModule) {
        this.nomFichier = nomFichier;
        this.URL = URL;
        this.format = format;
        this.presantation = presantation;
        this.type = type;
        this.enseignantcin = enseigantcin;
        this.codeModule = codeModule;
    }

    /**
     * *******
     *
     * getters
     *
     *
     */
    public int getCodeModule() {
        return codeModule;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public String getURL() {
        return URL;
    }

    public String getFormat() {
        return format;
    }

    public String getPresantation() {
        return presantation;
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    public int getEnseigantcin() {
        return enseignantcin;
    }

    /**
     * *******
     *
     * setters
     *
     *
     */
    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setPresantation(String presantation) {
        this.presantation = presantation;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setEnseigantcin(int enseigantcin) {
        this.enseignantcin = enseigantcin;
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
        return "Source{" + "nomFichier=" + nomFichier + ", URL=" + URL + ", format=" + format + ", presantation=" + presantation + ", type=" + type + ", code=" + code + ", enseigantcin=" + enseignantcin + ", codeModule=" + codeModule + '}';
    }

    public void ajouter() throws SQLException {
        String query = "insert into source"
                + " (nomFichier,URL, codeModule,cinEnseignant) "
                + " values ('" + nomFichier
                + "' ,'" + URL
                + "', " + codeModule
                + "," + enseignantcin + ")";
        ConnectionBD.executeUpdateQuery(query);
    }

    public Enseignant getEnseigant() throws SQLException {
        String query = "select * from utilisateur where cin = " + enseignantcin;
        Enseignant e= null ;
        ResultSet rest = ConnectionBD.executeSelectQuery(query);
        rest.next() ;
        
            e = new Enseignant(rest.getInt("cin"), rest.getString("nom"), rest.getString("prenom"), rest.getString("mdp"), rest.getString("pseudo"));
        
        return e;
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
        final Source other = (Source) obj;
        if (this.code != other.code) {
            return false;
        }
        return true;
    }
     public void modifier() throws SQLException {
        String query = "UPDATE source SET nomFichier = '"
                + nomFichier + "' , presantation = '"
                + presantation + "' "
               
                + "WHERE codeSource = " + code ;
        ConnectionBD.executeUpdateQuery(query);
       

    }

    public Module getModule() throws SQLException {
        String query = "select * from module where codeModule = " + codeModule;

        ResultSet result = ConnectionBD.executeSelectQuery(query);
        result.next();
        Module m = new Module(Integer.parseInt(result.getString("codeModule")),
                result.getString("nomComplet"),
                Integer.parseInt(result.getString("codeNivEtude")));
        return m;

    }
public void supprimer() throws SQLException {
        String query = "DELETE FROM  source "
                + "WHERE codeSource = " + code;
        ConnectionBD.executeUpdateQuery(query);
        for (int i = 0; i < 5; i++) {
            ConnectionBD.nathef();
        }
    }
}
