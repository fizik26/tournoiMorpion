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
    private VueInitialisation vini;
    private VueManche vmanche;
    private VuePartie vpartie;
    private Joueur j1;
    private Joueur j2;
    private Grille g1;
    private int i=2;
    public ObservateurIni(){
     
    listeJ = new ArrayList();
    vini = new VueInitialisation(listeJ);
    vini.afficher();
    vini.addObserver(this);
    
     j1 = new Joueur("Jean");
     j2 = new Joueur("Pierre");
    
    
    
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
            
            ArrayList<Joueur> JoueurNayantPasJouer = new ArrayList<Joueur>(listeJ);
            
            vmanche = new VueManche(JoueurNayantPasJouer.get(0),JoueurNayantPasJouer.get(1));
            vmanche.afficher();
            vmanche.addObserver(this);
            
        }
        }else if (arg instanceof MessageManche){
            MessageManche messageM = (MessageManche) arg ;
           listeJ.get(0).setCouleur(messageM.getChoixcouleur1());
           listeJ.get(0).setSigne(messageM.getChoixsigne1());
           listeJ.get(1).setCouleur(messageM.getChoixcouleur2());
           listeJ.get(1).setSigne(messageM.getChoixsigne2());
           vmanche.close();
           
           g1 = new Grille();
           vpartie = new VuePartie(listeJ.get(0),listeJ.get(1),g1);
           vpartie.afficher();
           vpartie.addObserver(this);
           
        }else if (arg instanceof vCase){
           vCase caseCoche = (vCase) arg ;
        
            if(i%2==0){
             g1.getLescases()[caseCoche.getLigne()][caseCoche.getColonne()].setEtat(EtatCase.X);
             vpartie.rafraichir(g1,listeJ.get(0));
            }else{
             g1.getLescases()[caseCoche.getLigne()][caseCoche.getColonne()].setEtat(EtatCase.O);
             vpartie.rafraichir(g1,listeJ.get(1));    
            }
            i++;          
           
           
        }
        
        
        
        

    
}
    
    

public static void openWebpage(String urlString) {
    try {
        Desktop.getDesktop().browse(new URL(urlString).toURI());
    } catch (Exception e) {
        e.printStackTrace();
    }
}



}
