/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournoimorpion;

import java.awt.Color;

/**
 *
 * @author Haruka
 */
public enum Couleur {
    
    ROUGE("Rouge", new Color(255, 0, 0)),
    BLEU("Bleu", new Color(55,194,198)),
    VERT("Vert", new Color(0, 195, 0)),
    JAUNE("Jaune", new Color(255, 255, 0)),
    NOIR("NOIR", new Color(0, 0, 0)),
    NULL("NULL", new Color(255, 255, 255)) ;;
    
    private final String libelle ;
    private final Color couleur ;
    
    Couleur (String libelle, Color couleur) {
            this.libelle = libelle ;
            this.couleur = couleur ;
        }
        
    @Override
        public String toString(){
            
            return this.libelle;
        }
        
        public Color getcouleur(){
            
            return this.couleur;
        }
    
    
}
