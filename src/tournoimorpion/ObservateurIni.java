/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournoimorpion;

import java.awt.Desktop;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author duttod
 */
public class ObservateurIni implements Observer{
    private ArrayList<Joueur> listeJ ;
    private ArrayList<Joueur> listeJVainqueurs ;
    private VueInitialisation vini;
    private VueManche vmanche;
    private VuePartie vpartie;
    private Joueur j1;
    private Joueur j2;
    private Grille g1;
    private int i=2;
    private int compteurjoueur=0;
    public ObservateurIni(){
     
    listeJ = new ArrayList();
    listeJVainqueurs = new ArrayList();
    vini = new VueInitialisation(listeJ);
    vini.afficher();
    vini.addObserver(this);
    
     
    
    
    
    }
    
    
    
    @Override
    public void update(Observable o, Object arg) {
        
        if(arg instanceof Message){
            Message message = (Message) arg ;
       
        if(message.getAction()==Actions.AJOUTE){
            Joueur j = new Joueur(message.getNomJ());
            listeJ.add(j);
            vini.rafraichir(listeJ);
        }else if (message.getAction()==Actions.SUPPRIME){
            
            int i = 0;
            while(i<listeJ.size()&& listeJ.get(i).getNom().equals(message.getNomJ())==false){
                i++;
            }
            if(i!=listeJ.size()){
            listeJ.remove(i);
            vini.rafraichir(listeJ);
            }
        }else if (message.getAction()==Actions.REINITIALISER){
            listeJ.clear();
            vini.rafraichir(listeJ);
        }else if (message.getAction()==Actions.TUTORIEL){
            
            openWebpage("https://www.regles-de-jeux.com/regle-du-morpion/");
        }else if (message.getAction()==Actions.LANCERTOURNOI){
            vini.close();
            

            
            vmanche = new VueManche(listeJ.get(compteurjoueur),listeJ.get(compteurjoueur+1));
            vmanche.afficher();
            vmanche.addObserver(this);
            
        }
        }else if (arg instanceof MessageManche){
            MessageManche messageM = (MessageManche) arg ;
           listeJ.get(compteurjoueur).setCouleur(messageM.getChoixcouleur1());
           listeJ.get(compteurjoueur).setSigne(messageM.getChoixsigne1());
           listeJ.get(compteurjoueur+1).setCouleur(messageM.getChoixcouleur2());
           
           // si les deux joueurs ont le m�me signe, on assigne au deuxi�me le signe que le premier n'a pas pris
           // sinon, faire comme avant et juste r�cup�rer le signe choisi
           if(messageM.getChoixsigne1().equals(messageM.getChoixsigne2())) {
        	   if(messageM.getChoixsigne1().equals(Signe.X)) {
        		   listeJ.get(compteurjoueur+1).setSigne(Signe.O);
        	   } else {
        		   listeJ.get(compteurjoueur+1).setSigne(Signe.X);
        	   }
           } else {
        	   listeJ.get(compteurjoueur+1).setSigne(messageM.getChoixsigne2());
           }
           
           vmanche.close();
           i=2;
           g1 = new Grille();
           
          
           
           vpartie = new VuePartie(listeJ.get(compteurjoueur),listeJ.get(compteurjoueur+1),g1);
           vpartie.afficher();
           vpartie.addObserver(this);
           
        }else if (arg instanceof vCase){
           vCase caseCoche = (vCase) arg ;
           
            if(i%2==0){
             g1.getLescases()[caseCoche.getLigne()][caseCoche.getColonne()].setEtat(EtatCase.X);
             vpartie.rafraichir(g1,listeJ.get(compteurjoueur) , listeJ.get(compteurjoueur+1));
             if(this.VerifVictoireX()){
                 System.out.println("Victoire de X");
                 if(listeJ.get(compteurjoueur).getSigne()==Signe.X){
                     System.out.println("Victoire de "+listeJ.get(compteurjoueur).getNom());
                     listeJVainqueurs.add(listeJ.get(compteurjoueur));
                     if(listeJ.size()%2!=0){
                         listeJ.remove(compteurjoueur+1);
                         listeJVainqueurs.remove(0);
                         compteurjoueur=compteurjoueur-2;
                     }
                     vpartie.close();
                     compteurjoueur=compteurjoueur+2;
                     if(compteurjoueur<listeJ.size()-1){
                        vmanche = new VueManche(listeJ.get(compteurjoueur),listeJ.get(compteurjoueur+1));
                        vmanche.afficher();
                        vmanche.addObserver(this);
                     }else{
                         if(listeJVainqueurs.size()!=1){
                         listeJ= new ArrayList(listeJVainqueurs);
                         listeJVainqueurs.clear();
                         compteurjoueur=0;
                         vmanche = new VueManche(listeJ.get(compteurjoueur),listeJ.get(compteurjoueur+1));
                         vmanche.afficher();
                         vmanche.addObserver(this);
                         }else{
                             System.out.println("Vainqueurs du tournoi :"+listeJVainqueurs.get(0).getNom());
                         }
                         
                     }
                 }else if(listeJ.get(compteurjoueur+1).getSigne()==Signe.X){
                     System.out.println("Victoire de "+listeJ.get(compteurjoueur+1).getNom());
                     listeJVainqueurs.add(listeJ.get(compteurjoueur+1));
                      if(listeJ.size()%2!=0){
                         listeJ.remove(compteurjoueur);
                         listeJVainqueurs.remove(0);
                         compteurjoueur=compteurjoueur-2;
                        }
                     vpartie.close();
                     compteurjoueur=compteurjoueur+2;
                     if(compteurjoueur<listeJ.size()-1){
                        vmanche = new VueManche(listeJ.get(compteurjoueur),listeJ.get(compteurjoueur+1));
                        vmanche.afficher();
                        vmanche.addObserver(this);
                     }else{
                         if(listeJVainqueurs.size()!=1){
                         listeJ= new ArrayList(listeJVainqueurs);
                         listeJVainqueurs.clear();
                         compteurjoueur=0;
                         vmanche = new VueManche(listeJ.get(compteurjoueur),listeJ.get(compteurjoueur+1));
                         vmanche.afficher();
                         vmanche.addObserver(this);
                         }else{
                             System.out.println("Vainqueurs du tournoi :"+listeJVainqueurs.get(0).getNom());
                         }
                     }
                 }
                 
             }
            }else{
             g1.getLescases()[caseCoche.getLigne()][caseCoche.getColonne()].setEtat(EtatCase.O);
             
             
             vpartie.rafraichir(g1,listeJ.get(compteurjoueur+1) , listeJ.get(compteurjoueur));  
             if(this.VerifVictoireO()){
                 System.out.println("Victoire de O");
                 if(listeJ.get(compteurjoueur).getSigne()==Signe.O){
                     System.out.println("Victoire de "+listeJ.get(compteurjoueur).getNom());
                     listeJVainqueurs.add(listeJ.get(compteurjoueur));
                      if(listeJ.size()%2!=0){
                         listeJ.remove(compteurjoueur+1);
                         listeJVainqueurs.remove(0);
                         compteurjoueur=compteurjoueur-2;
                     }
                     vpartie.close();
                     compteurjoueur=compteurjoueur+2;
                      if(compteurjoueur<listeJ.size()-1){
                        vmanche = new VueManche(listeJ.get(compteurjoueur),listeJ.get(compteurjoueur+1));
                        vmanche.afficher();
                        vmanche.addObserver(this);
                      }else{
                         if(listeJVainqueurs.size()!=1){
                         listeJ= new ArrayList(listeJVainqueurs);
                         listeJVainqueurs.clear();
                         compteurjoueur=0;
                         vmanche = new VueManche(listeJ.get(compteurjoueur),listeJ.get(compteurjoueur+1));
                         vmanche.afficher();
                         vmanche.addObserver(this);
                         }else{
                             System.out.println("Vainqueurs du tournoi :"+listeJVainqueurs.get(0));
                         }
                          
                      }
                 }else if(listeJ.get(compteurjoueur+1).getSigne()==Signe.O){
                     System.out.println("Victoire de "+listeJ.get(compteurjoueur+1).getNom());
                     listeJVainqueurs.add(listeJ.get(compteurjoueur+1));
                      if(listeJ.size()%2!=0){
                         listeJ.remove(compteurjoueur);
                         listeJVainqueurs.remove(0);
                         compteurjoueur=compteurjoueur-2;
                     }
                     vpartie.close();
                     compteurjoueur=compteurjoueur+2;
                      if(compteurjoueur<listeJ.size()-1){
                        vmanche = new VueManche(listeJ.get(compteurjoueur),listeJ.get(compteurjoueur+1));
                        vmanche.afficher();
                        vmanche.addObserver(this);
                      }else{
                         if(listeJVainqueurs.size()!=1){
                         listeJ= new ArrayList(listeJVainqueurs);
                         listeJVainqueurs.clear();
                         compteurjoueur=0;
                         vmanche = new VueManche(listeJ.get(compteurjoueur),listeJ.get(compteurjoueur+1));
                         vmanche.afficher();
                         vmanche.addObserver(this); 
                         }else{
                             System.out.println("Vainqueurs du tournoi :"+listeJVainqueurs.get(0).getNom());
                         }
                          
                      }
                 }
            }
            }
            i++;          
           
           
        }
        
        
        
        

    
}
    
    

public static void openWebpage(String urlString) {
    try {
        Desktop.getDesktop().browse(new URL(urlString).toURI());
    } catch (Exception e) {
        e.printStackTrace();
    }}
   
    
    public boolean VerifVictoireX(){
        boolean victoire = false;
        
        // conditions victoire colonne
        if (g1.getLescases()[0][0].getEtat()==EtatCase.X && g1.getLescases()[1][0].getEtat()==EtatCase.X && g1.getLescases()[2][0].getEtat()==EtatCase.X){
            victoire = true;
        }
        if(g1.getLescases()[0][1].getEtat()==EtatCase.X && g1.getLescases()[1][1].getEtat()==EtatCase.X && g1.getLescases()[2][1].getEtat()==EtatCase.X){
            victoire = true;
        }
        if(g1.getLescases()[0][2].getEtat()==EtatCase.X && g1.getLescases()[1][2].getEtat()==EtatCase.X && g1.getLescases()[2][2].getEtat()==EtatCase.X){
            victoire = true ;
        }
        
        // conditions victoire ligne
        
        if (g1.getLescases()[0][0].getEtat()==EtatCase.X && g1.getLescases()[0][1].getEtat()==EtatCase.X && g1.getLescases()[0][2].getEtat()==EtatCase.X){
            victoire = true;
        }
        if (g1.getLescases()[1][0].getEtat()==EtatCase.X && g1.getLescases()[1][1].getEtat()==EtatCase.X && g1.getLescases()[1][2].getEtat()==EtatCase.X){
            victoire = true;
        }
        if (g1.getLescases()[2][0].getEtat()==EtatCase.X && g1.getLescases()[2][1].getEtat()==EtatCase.X && g1.getLescases()[2][2].getEtat()==EtatCase.X){
            victoire = true;
        }
        
        // conditions victoire diagonale 
        
        if (g1.getLescases()[0][0].getEtat()==EtatCase.X && g1.getLescases()[1][1].getEtat()==EtatCase.X && g1.getLescases()[2][2].getEtat()==EtatCase.X){
            victoire = true;
        }
        if (g1.getLescases()[2][0].getEtat()==EtatCase.X && g1.getLescases()[1][1].getEtat()==EtatCase.X && g1.getLescases()[0][2].getEtat()==EtatCase.X){
            victoire = true;
        }
        
        
        return victoire ;
           
        
    }

  public boolean VerifVictoireO(){
        boolean victoire = false;
        
        // conditions victoire colonne
        if (g1.getLescases()[0][0].getEtat()==EtatCase.O && g1.getLescases()[1][0].getEtat()==EtatCase.O && g1.getLescases()[2][0].getEtat()==EtatCase.O){
            victoire = true;
        }
        if(g1.getLescases()[0][1].getEtat()==EtatCase.O && g1.getLescases()[1][1].getEtat()==EtatCase.O && g1.getLescases()[2][1].getEtat()==EtatCase.O){
            victoire = true;
        }
        if(g1.getLescases()[0][2].getEtat()==EtatCase.O && g1.getLescases()[1][2].getEtat()==EtatCase.O && g1.getLescases()[2][2].getEtat()==EtatCase.O){
            victoire = true ;
        }
        
        // conditions victoire ligne
        
        if (g1.getLescases()[0][0].getEtat()==EtatCase.O && g1.getLescases()[0][1].getEtat()==EtatCase.O && g1.getLescases()[0][2].getEtat()==EtatCase.O){
            victoire = true;
        }
        if (g1.getLescases()[1][0].getEtat()==EtatCase.O && g1.getLescases()[1][1].getEtat()==EtatCase.O && g1.getLescases()[1][2].getEtat()==EtatCase.O){
            victoire = true;
        }
        if (g1.getLescases()[2][0].getEtat()==EtatCase.O && g1.getLescases()[2][1].getEtat()==EtatCase.O && g1.getLescases()[2][2].getEtat()==EtatCase.O){
            victoire = true;
        }
        
        // conditions victoire diagonale 
        
        if (g1.getLescases()[0][0].getEtat()==EtatCase.O && g1.getLescases()[1][1].getEtat()==EtatCase.O && g1.getLescases()[2][2].getEtat()==EtatCase.O){
            victoire = true;
        }
        if (g1.getLescases()[2][0].getEtat()==EtatCase.O && g1.getLescases()[1][1].getEtat()==EtatCase.O && g1.getLescases()[0][2].getEtat()==EtatCase.O){
            victoire = true;
        }
        
        
        return victoire ;
           
        
    }

}
