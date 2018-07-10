/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.Controler;

import javax.swing.JFrame;

/**
 *
 * @author bouguerra
 */
public class CMenuEnseignant {

    public static String Action;

    public static void generate(JFrame j) {
        String query = "SELECT * FROM UTILISATEUR";
        switch (Action) {
            case "Vos Modules":
                query = "select distinct nomComplet,niv "
                        + "from Module , nivEtude, enseignermodule "
                        + "where Module.codeModule = enseignermodule.codeModule "
                        + "and module.codenivEtude =nivetude.codenivEtude "
                        + "and enseignermodule.enseignantCin = " + CWlcPage.connected.getCin();
                break;
            case "liste des Modules totale":
                query = "select distinct nomComplet,niv"
                        + " from Module , nivEtude "
                        + "where Module.codeNivEtude= NivEtude.codeNivEtude";
                break;
            case "Vos Examen":
                query = "select distinct date,titre,nom,prenom,pseudo "
                        + "from Utilisateur ,examen"
                        + " where enseignantCin= cin "
                        + "and codeModule in("
                        + "select codeModule from module where codeNivEtude ="
                        + CWlcPage.connected.getCodeNivEtude() + ")";

                break;
            case "liste des Examens totale":
                query = "SELECT  distinct titre  as titreExamen , date as dateExamen ,pseudo as pseudoEnseigant ,nomComplet as nomModule, niv as niveauEtude  "
                        + "from examen,module, utilisateur ,nivetude "
                        + "where utilisateur.cin = examen.enseignantCin "
                        + "and examen.codeModule =module.codeModule "
                        + "AND nivetude.codenivEtude = module.codenivEtude ";
                break;
            case "Enseignants":
                query = "select distinct nom, prenom, cin , pseudo ,statue from Utilisateur where statue = 'Enseignant'";
                break;
            case "Liste des niveaux d'etude que vous enseigner":
                query = "SELECT distinct niv "
                        + "from nivetude , enseignermodule ,module "
                        + "WHERE module.codeModule = enseignermodule.codeModule "
                        + "and module.codenivEtude= nivetude.codenivEtude "
                        + "and enseignermodule.enseignantCin = " + CWlcPage.connected.getCin();
                break;
            case "liste totale ":
                query = "select niv from nivetude";
                break;
            case "Liste Etudiants qui ont accès a vos cours":
                query = "select DISTINCT etudiant.pseudo ,etudiant.statue, etudiant.nom, etudiant.prenom ,niv "
                        + "from utilisateur etudiant,utilisateur enseignant , enseignermodule ,module ,nivetude "
                        + "WHERE enseignermodule.codeModule= module.codeModule "
                        + "AND enseignermodule.enseignantCin= enseignant.cin "
                        + "and nivEtude.codeNivEtude  = module.codeNivEtude "
                        + "AND etudiant.codeNivEtude = module.codenivEtude "
                        + " and etudiant.statue ='Etudiant'"
                        + "and enseignant.cin = " + CWlcPage.connected.getCin();

                break;
            case "Liste totale des Etudiants ":
                query = "SELECT distinct nom , prenom , pseudo , niv "
                        + "FROM utilisateur, nivetude "
                        + "WHERE utilisateur.codenivEtude = nivetude.codenivEtude "
                        + " and statue ='Etudiant'";
                break;

        }

        CcreatTab.create(query, j);
    }

}
