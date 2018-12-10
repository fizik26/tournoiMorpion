/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournoimorpion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import static java.awt.Component.LEFT_ALIGNMENT;
import static java.awt.Component.RIGHT_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Haruka
 */
public class VuePartie extends Observable {
    
    private JFrame fenetre ;
    private JPanel pGrille;
 
    
    public VuePartie(Joueur j1 , Joueur j2,Grille g){
    fenetre = new JFrame();
    fenetre.setSize(400, 400);
    Color couleurfond =fenetre.getBackground();
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
    fenetre.setBackground(Color.LIGHT_GRAY);
    JPanel mainpanel = new JPanel();
    mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));
    fenetre.add(mainpanel);
    
            
    JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
   
    
    
    JPanel ij1 = new JPanel();
    ij1.setLayout(new BoxLayout(ij1, BoxLayout.PAGE_AXIS));
    JLabel nomj1= new JLabel("nom:"+j1.getNom());
    JLabel signej1= new JLabel("signe:"+j1.getSigne());
    JLabel coul1= new JLabel("couleur:"+j1.getCouleur());
    ij1.add(nomj1);
    ij1.add(signej1);
    ij1.add(coul1);
    header.add(ij1);
    header.add(new JLabel("          "));
    
    JPanel head = new JPanel();
    head.setLayout(new BoxLayout(head, BoxLayout.PAGE_AXIS));
    JLabel titre = new JLabel("Manche X");
    JButton valider = new JButton("Valider");
    head.add(titre);
    head.add(new JLabel(" "));
    head.add(valider);
    header.add(head);
    header.add(new JLabel("          "));
    
    
    
    
    JPanel ij2 = new JPanel();
    ij2.setLayout(new BoxLayout(ij2, BoxLayout.PAGE_AXIS));
    JLabel nomj2= new JLabel("nom:"+j2.getNom());
    JLabel signej2= new JLabel("signe:"+j2.getSigne());
    JLabel coul2= new JLabel("couleur:"+j2.getCouleur());
    ij2.add(nomj2);
    ij2.add(signej2);
    ij2.add(coul2);
    header.add(ij2);
    
    mainpanel.add(header);
    
    Font fontcochee= new Font("Arial", 2, 30);
    
    pGrille = new JPanel(new GridLayout(4, 3));
    mainpanel.add(pGrille);
    Border lightgrayline = BorderFactory.createLineBorder(Color.LIGHT_GRAY,1);
        for (int x=0; x<3;x++){
            for (int y=0;y<3;y++){
            if (g.getLescases()[x][y].getEtat()==EtatCase.NON_COCHEE){
//                JLabel casE = new JLabel();
                vCase casE = new vCase("Jouer ici",x,y) ;
                casE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(casE);
                clearChanged();
            }
        });
                casE.setBackground(new Color(222,222,222));
                casE.setBorder(lightgrayline);
                pGrille.add(casE);
            }else if (g.getLescases()[x][y].getEtat()==EtatCase.X){
                JLabel casE = new JLabel("X");
                casE.setFont(fontcochee);
                casE.setBorder(lightgrayline);
                pGrille.add(casE);   
            }else if (g.getLescases()[x][y].getEtat()==EtatCase.O){
                JLabel casE = new JLabel("O");
                casE.setFont(fontcochee);
                casE.setBorder(lightgrayline);
                pGrille.add(casE);   
            
            }
            }
        }
    mainpanel.add(new JLabel(" "));  
    
        
    }
    public void afficher() {
        this.fenetre.setVisible(true);
    }
    
    public void rafraichir(Grille grilleJeu , Joueur j){
         pGrille.removeAll();
         Border lightgrayline = BorderFactory.createLineBorder(Color.LIGHT_GRAY,1);
         Font fontcochee= new Font("Arial", 2, 30);
         for (int x=0; x<3;x++){
            for (int y=0;y<3;y++){
            if (grilleJeu.getLescases()[x][y].getEtat()==EtatCase.NON_COCHEE){
//                JLabel casE = new JLabel();
                vCase casE = new vCase("Jouer ici",x,y) ;
                casE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(casE);
                clearChanged();
            }
        });
                casE.setBackground(new Color(222,222,222));
                casE.setBorder(lightgrayline);
                pGrille.add(casE);
            }else if (grilleJeu.getLescases()[x][y].getEtat()==EtatCase.X){
                JLabel casE = new JLabel("X");
                casE.setFont(fontcochee);
                casE.setForeground(j.getCouleur().getcouleur());
                casE.setBorder(lightgrayline);
                pGrille.add(casE);   
            }else if (grilleJeu.getLescases()[x][y].getEtat()==EtatCase.O){
                JLabel casE = new JLabel("O");
                casE.setFont(fontcochee);
                casE.setForeground(j.getCouleur().getcouleur());
                casE.setBorder(lightgrayline);
                pGrille.add(casE);   
            
            }
            }
        }
         pGrille.validate();
         pGrille.revalidate();
         
         
     }     
}
