/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.Controler;

import javax.swing.JFrame;
import jeao.View.old.EtudiantMenu_1;

/**
 *
 * @author bouguerra
 */
public class CMenuEtudiant {

    public static String Action;

    public static void generate(JFrame j) {
        String query = "SELECT * FROM UTILISATEUR";
        switch (Action) {
            case "Liste totale des modules":
                query = "select nomComplet from module where codenivEtude= " + CWlcPage.connected.getCodeNivEtude();
                break;
            case "Mes Enseignants ":
                query = "select nom,prenom,pseudo "
                        + "from Utilisateur ,enseignermodule "
                        + "where cin= enseignantCin "
                        + "and codeModule in("
                        + "select codeModule from module where  codeNivEtude ="
                        + CWlcPage.connected.getCodeNivEtude() + ")";
                break;
            case "Liste des modules validés ":
                query = "select nomComplet "
                        + "from module  "
                        + "where codemodule in (select codeModule from examen , examenvalide "
                        + " where examen.codeExamen  =  examenvalide.codeExamen and score > 50)";
                break;

            case "Liste des modules ratés ":
                 query = "select nomComplet "
                        + "from module  "
                        + "where codemodule in (select codeModule from examen , examenvalide "
                        + " where examen.codeExamen  =  examenvalide.codeExamen and score < 50 )";
                break;
            case "Liste Etudiants de votre niveau":
                query = "select nom,prenom,pseudo ,niv "
                        + "from Utilisateur ,nivEtude "
                        + "where Utilisateur.codeNivEtude= nivEtude.codeNivEtude "
                        + "and statue = 'Etudiant'"
                        + "and nivEtude.codeNivEtude ="
                        + CWlcPage.connected.getCodeNivEtude();
                break;
            case "Liste totale des Etudiants":
                query = "select distinct nom,prenom,pseudo ,niv "
                        + "from Utilisateur ,nivEtude "
                        + "where Utilisateur.codeNivEtude= nivEtude.codeNivEtude "
                        + "and statue = 'Etudiant' ";
                break;
            case "Vos Examens(liste Totale)":
                query = "select distinct titre, pseudo, nomComplet "
                        + "from module ,examen, utilisateur "
                        + "where examen.codeModule = module.codeModule "
                        + "and cin = examen.enseignantCin "
                        + "and module.codeModule in ( "
                        + "select codeModule from module where codeNivEtude = "
                        + CWlcPage.connected.getCodeNivEtude() + " ) ";

                break;
            case "Liste des Examens  non validé":
                query = "select distinct titre, pseudo, nomComplet "
                        + "from module ,examen, utilisateur "
                        + "where examen.codeModule = module.codeModule "
                        + "and cin = examen.enseignantCin "
                        + "and module.codeModule in ( "
                        + "select codeModule from module where codeNivEtude = "
                        + CWlcPage.connected.getCodeNivEtude() + " )"
                        + " and examen.codeExamen not in "
                        + " ( select codeExamen "
                        + "from  examenvalide "
                        + "where  cinEtudiant =" + CWlcPage.connected.getCodeNivEtude() + " )";
                break;
            case "Examen réussis":
                query = "select  titre , nomComplet , score "
                        + "from module , examen ,  examenvalide "
                        + "where module.codeModule = examen.codeModule "
                        + " and examen.codeExamen  =  examenvalide.codeExamen "
                        + " and examenvalide.cinEtudiant =" + CWlcPage.connected.getCin() + ""
                        + " and examenvalide.score > 50  ";
                break;

            case "Examen  non réussis":
                query = "select  titre , nomComplet , score "
                        + "from module , examen ,  examenvalide "
                        + "where module.codeModule = examen.codeModule "
                        + " and examen.codeExamen  =  examenvalide.codeExamen "
                        + " and examenvalide.cinEtudiant =" + CWlcPage.connected.getCin() + ""
                        + " and examenvalide.score < 50  ";
                break;
        }
        CcreatTab.create(query, j);
    }

}
