/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.Controler;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.J;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static jeao.Controler.CPasserExamen.choix;
import jeao.View.old.EtudiantMenu_1;
import jeao.model.ConnectionBD;
import jeao.model.Examen;
import jeao.model.Question;

/**
 *
 * @author bouguerra
 */
public class CPasserExamen {

    private static ArrayList<Examen> listExamen;
    private static ArrayList<Question> listQuestion;
    private static int indiceExamen;
    public static JFrame frame;
    public static JComboBox choix[];
    public static JLabel enance[];
        public static JTextField rep[];

    public static ArrayList<Examen> getListExamen() {
        return listExamen;
    }

    public static ArrayList<Question> getListQuestion() {
        return listQuestion;
    }

    public static void loadListExamen() throws SQLException {
        listExamen = CWlcPage.connected.getExamens();

    }
    
    
    

    public static void loadListeQuestion(int index) throws SQLException {
        indiceExamen = index;
        listExamen.get(index).getListeQuestionBD();
        listQuestion = listExamen.get(index).getListeQuestion();

    }
    
 public static void afficher() { 
     if (listQuestion.get(0).getChoix(0).equals(""))
         afficherClassique();
     else
         afficherQCM() ;
         
         
 }
  

    public static void afficherClassique() {
        frame = new JFrame();
   
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, (listQuestion.size() + 1) * 70);
        frame.setLayout(new GridLayout(listQuestion.size() * 2 + 1, 1));

        rep= new JTextField[listQuestion.size()];
        enance = new JLabel[listQuestion.size()];

        for (int i = 0; i < listQuestion.size(); i++) {
            JPanel panel1 = new JPanel();
            enance[i] = new JLabel();
            enance[i].setSize(500, 50);//[106, 14]

            enance[i].setText(listQuestion.get(i).getEnance() + ":");
            panel1.add(enance[i]);
            rep[i] = new JTextField();
           
            rep[i].setSize(500, 35);
            JPanel panel2 = new JPanel();
            panel2.add(rep[i]);
            frame.getContentPane().add(enance[i]);
            frame.getContentPane().add(rep[i]);

        }
        JPanel panel = new JPanel();
        JButton lancer = new JButton();
        lancer.setText("lancer ");
        lancer.addActionListener(new Corriger());

        panel.add(lancer);
        frame.getContentPane().add(panel);

        //frame.setContentPane(panelGlobal);
          frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    
    }
    

    public static void afficherQCM() {
        frame = new JFrame();
   
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, (listQuestion.size() + 1) * 100);
        frame.setLayout(new GridLayout(listQuestion.size() * 2 + 1, 1));

        choix = new JComboBox[listQuestion.size()];
        enance = new JLabel[listQuestion.size()];

        for (int i = 0; i < listQuestion.size(); i++) {
            JPanel panel1 = new JPanel();
            enance[i] = new JLabel();
            enance[i].setSize(500, 50);//[106, 14]

            enance[i].setText(listQuestion.get(i).getEnance() + ":");
            panel1.add(enance[i]);
            choix[i] = new JComboBox();
            for (int j = 0; j < 4; j++) {
                choix[i].addItem(listQuestion.get(i).getChoix(j));
            }
            choix[i].setSize(1000, 35);
            JPanel panel2 = new JPanel();
            panel2.add(choix[i]);
            frame.getContentPane().add( enance[i]);
            frame.getContentPane().add(panel2);

        }
        JPanel panel = new JPanel();
        JButton lancer = new JButton();
        lancer.setText("lancer ");
        lancer.addActionListener(new Corriger());

        panel.add(lancer);
        frame.getContentPane().add(panel);

        //frame.setContentPane(panelGlobal);
          frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        /*
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JTable table = new JTable(data,column);
        JScrollPane jsp = new JScrollPane(table);
        panel.setLayout(new BorderLayout());
        panel.add(jsp,BorderLayout.CENTER);
        frame.setContentPane(panel);
        frame.setVisible(true);*/
    }

    private static class Corriger implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int result = corr();
            try {
                addResultBD( result);
            } catch (SQLException ex) {
                Logger.getLogger(CPasserExamen.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Votre resultat = " + (100 / listQuestion.size()) *  result); 
           frame.dispose();

        }

        public int corr() {
            int rest = 0;
            for (int i = 0; i < listQuestion.size(); i++) {
                if (listQuestion.get(i).getRepCorrect() - 1 == choix[i].getSelectedIndex()) {
                    rest++;

                }
            }

            return rest;
        }

        public void addResultBD(int result) throws SQLException {
            String query = " insert into  examenvalide "
                    + "(cinEtudiant,codeExamen,score) "
                    + "values (" + CWlcPage.connected.getCin()
                    + "," + listExamen.get(indiceExamen).getCode()
                    + "," + result + ")";
            ConnectionBD.executeUpdateQuery(query);

        }

    }
}
