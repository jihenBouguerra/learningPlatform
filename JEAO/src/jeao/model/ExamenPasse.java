/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeao.model;

import java.util.ArrayList;

/**
 *
 * @author bouguerra
 */
public class ExamenPasse extends Examen  {
    private int cin ;
    private int note ;
    
    public ExamenPasse(String titre, int codeModule, int codeEnseignant) {
        super(titre, codeModule, codeEnseignant);
    }
    
     
}
