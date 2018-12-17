/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournoimorpion;

import javax.swing.JComboBox;

/**
 *
 * @author Haruka
 */
public class MessageManche {
    
    private Couleur choixcouleur1;
    private Couleur choixcouleur2;
    private Signe choixsigne1;
    private Signe choixsigne2;
    
    
    public MessageManche(Couleur choixcouleur1,Couleur choixcouleur2,Signe choixsigne1,Signe choixsigne2){
        this.choixcouleur1=choixcouleur1;
        this.choixcouleur2=choixcouleur2;
        this.choixsigne1=choixsigne1;
        this.choixsigne2=choixsigne2;
    }

    /**
     * @return the choixcouleur1
     */
    public Couleur getChoixcouleur1() {
        return choixcouleur1;
    }

    /**
     * @param choixcouleur1 the choixcouleur1 to set
     */
    public void setChoixcouleur1(Couleur choixcouleur1) {
        this.choixcouleur1 = choixcouleur1;
    }

    /**
     * @return the choixcouleur2
     */
    public Couleur getChoixcouleur2() {
        return choixcouleur2;
    }

    /**
     * @param choixcouleur2 the choixcouleur2 to set
     */
    public void setChoixcouleur2(Couleur choixcouleur2) {
        this.choixcouleur2 = choixcouleur2;
    }

    /**
     * @return the choixsigne1
     */
    public Signe getChoixsigne1() {
        return choixsigne1;
    }

    /**
     * @param choixsigne1 the choixsigne1 to set
     */
    public void setChoixsigne1(Signe choixsigne1) {
        this.choixsigne1 = choixsigne1;
    }

    /**
     * @return the choixsigne2
     */
    public Signe getChoixsigne2() {
        return choixsigne2;
    }

    /**
     * @param choixsigne2 the choixsigne2 to set
     */
    public void setChoixsigne2(Signe choixsigne2) {
        this.choixsigne2 = choixsigne2;
    }

}

    