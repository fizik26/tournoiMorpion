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
public class Joueur {
    
    private String nom ;
    private Signe signe ;
    private Couleur couleur;
   
    
    public Joueur(String n){
        
        this.setNom(n);
        this.couleur=Couleur.NULL;
        this.signe=Signe.NULL;
        
        
    }    

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the signe
     */
    public Signe getSigne() {
        return signe;
    }

    /**
     * @param signe the signe to set
     */
    public void setSigne(Signe signe) {
        this.signe = signe;
    }

    /**
     * @return the couleur
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     * @return the signe
     */
    
}
