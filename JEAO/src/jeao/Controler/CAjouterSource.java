/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.Controler;

import java.sql.SQLException;
import java.util.ArrayList;
import jeao.model.Module;
import jeao.model.Source;

/**
 *
 * @author bouguerra
 */
public class CAjouterSource {

    private static ArrayList<Module> listeModule;
    private static ArrayList<Source> listeSource;
    private static Source nouveauSource; 
    
    

    public static ArrayList<Module> getListeModule() {
        return listeModule;
    }

    public static ArrayList<Source> getListeSource() {
        return listeSource;
    }

    public static void loadListeModule() throws SQLException {
       listeModule=  CWlcPage.connected.getModules();

    }

    public static void loadListeSource(int index) throws SQLException {
        listeSource = listeModule.get(index).getSources();

    }

}
