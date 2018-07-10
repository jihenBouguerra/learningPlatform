/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jeao.model.ConnectionBD;
import jeao.model.Module;
import jeao.model.NivEtude;

/**
 *
 * @author bouguerra
 */
public class CAjouterModule {

    private static ArrayList<NivEtude> listeNivEtude;
    private static Module module;

    public static void getInformations(String titre, int index) throws SQLException {
        module = new Module(titre, listeNivEtude.get(index).getCode());
        addModule();

    }

    private static void addModule() throws SQLException {
        module.ajouter();
    }

    public static ArrayList<NivEtude> getListeNivEtude() {
        return listeNivEtude;
    }

    public static void loadlisteNivEtude() throws SQLException {

        listeNivEtude = new ArrayList<NivEtude>();
        String query = " select * from NivEtude ";
        ResultSet result = ConnectionBD.executeSelectQuery(query);

        while (result.next()) {
            NivEtude n = new NivEtude(result.getInt("codenivEtude"),
                    result.getString("niv"));
            listeNivEtude.add(n);
        }

    }

}
