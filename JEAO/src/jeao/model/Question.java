package jeao.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Question {

    private String enance;
    private int repCorrect;
    private ArrayList<String> choix;
    private int id;
    private int idExamen;

    public Question(String enance, int id, int idExamen) {
        choix = new ArrayList<String>();
        this.enance = enance;
        this.id = id;

        this.idExamen = idExamen;
    }

    /*
    public void getIdDB() throws SQLException {
        String query = "select * "
                + " from Question "
                + " where enance = '" + enance + "' AND codeExamen = "+idExamen +" ; ";
        ResultSet result = ConnectionBD.executeSelectQuery(query);
        if (result.next()) {
            id = result.getInt("codeQuestion");
        }
        System.out.print("*****" + id);

    }
     */
    public boolean existeQuestionDansUneListe(ArrayList<Question> lq) {
        boolean tr = false;
        int i = 0;
        while (tr == false && i < lq.size()) {
            if (lq.get(i).getEnance().equals(enance)) {
                tr = true;
            }
            i++;
        }
        return tr;
    }

    public Question(String enance) {
        this.enance = enance;
        choix = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            choix.add("");
        }

    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public void addQuestionBD() throws SQLException {

        String quary = "INSERT INTO Question(enance,codeExamen,repCorrect,choi1,choi2,choi3, choi4) "
                + "VALUES ( '" + enance
                + "' , " + idExamen
                + ", " + repCorrect
                + "  , '" + choix.get(0)
                + "' , '" + choix.get(1)
                + "' , '" + choix.get(2)
                + "' , '" + choix.get(3)
                + "' ) ;";
        ConnectionBD.executeUpdateQuery(quary);
        //  getIdDB();

    }

    public boolean QuestionExiste() throws SQLException {
        String quary = "select * from Question where enance='" + enance + "';";
        ResultSet result = ConnectionBD.executeSelectQuery(quary);
        return result.next();

    }

    public Question(String enance, int repCorrect, String choi1, String choi2, String choi3, String choi4, int id, int idExamen) {
        choix = new ArrayList<String>();
        this.enance = enance;
        this.repCorrect = repCorrect;
        choix.add(choi1);
        choix.add(choi2);
        choix.add(choi3);
        choix.add(choi4);
        this.idExamen = idExamen;
        this.id = id;
    }

    public Question(String enance, int repCorrect, String choi1, String choi2, String choi3, String choi4) {
        choix = new ArrayList<String>();
        this.enance = enance;
        this.repCorrect = repCorrect;
        choix.add(choi1);
        choix.add(choi2);
        choix.add(choi3);
        choix.add(choi4);

    }

    public Question(String enance, int repCorrect, String choi1, String choi2, String choi3, String choi4, int idExamen) {
        choix = new ArrayList<String>();
        this.idExamen = idExamen;
        this.enance = enance;
        this.repCorrect = repCorrect;
        choix.add(choi1);
        choix.add(choi2);
        choix.add(choi3);
        choix.add(choi4);

    }

    public void setEnance(String enance) {
        this.enance = enance;
    }

    public void setRepCorrect(int repCorrect) {
        this.repCorrect = repCorrect;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnance() {
        return enance;
    }

    public int getRepCorrect() {
        return repCorrect;
    }

    public ArrayList<String> getChoix() {
        return choix;

    }

    public String getChoix(int i) {
        return choix.get(i);
    }

    public int getId() {
        return id;
    }

    public boolean isQCM() {
        return choix.get(0).equals("") || choix.isEmpty();

    }
}
