/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournoimorpion;

import java.util.HashMap;

/**
 *
 * @author duttod
 */
public class Grille {
    
    private Case lescases[][] ;
    
    
    
    public Grille () {
        
         lescases=new Case[3][3];
         int i=0;
         for (int x=0; x<=2;x++){
            for (int y=0;y<=2;y++){
                Case c = new Case(i);
                lescases[x][y]=c;
                i++;
               
                }
            }
         
    }

    /**
     * @return the lescases
     */
    public Case[][] getLescases() {
        return lescases;
    }
    
    
    
    
}
