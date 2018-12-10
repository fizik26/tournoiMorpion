/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournoimorpion;

/**
 *
 * @author duttod
 */
public class Case {
    
    private int numcase ;
    private EtatCase etat ;
    
    
    public Case(int i){
        
        this.numcase=i;
        this.etat=EtatCase.NON_COCHEE;
    }

    /**
     * @return the numcase
     */
    public int getNumcase() {
        return numcase;
    }

    /**
     * @param numcase the numcase to set
     */
    public void setNumcase(int numcase) {
        this.numcase = numcase;
    }

    /**
     * @return the etat
     */
    public EtatCase getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(EtatCase etat) {
        this.etat = etat;
    }
    
    
    
    
}
