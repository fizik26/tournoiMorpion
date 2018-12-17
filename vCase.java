/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournoimorpion;

import javax.swing.JButton;

/**
 *
 * @author duttod
 */
public class vCase extends JButton {
    private int ligne ;
    private int colonne ;

    public vCase(String nom,int l ,int c) {
        super(nom);
        this.ligne=l;
        this.colonne=c;
        
        
    }

    /**
     * @return the ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * @param ligne the ligne to set
     */
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /**
     * @return the colonne
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * @param colonne the colonne to set
     */
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }
    
}
