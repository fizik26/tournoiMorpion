/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournoimorpion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Haruka
 */
public class VueManche extends Observable{
    
    private JFrame fenetre ;
    private JComboBox choixcouleur1;
    private JComboBox choixcouleur2;
    private JComboBox choixsigne1;
    private JComboBox choixsigne2;
    
    
    public VueManche(Joueur j1 , Joueur j2){
    fenetre = new JFrame();
    fenetre.setSize(400, 300);
    Color couleurfond =fenetre.getBackground();
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
    fenetre.setBackground(Color.LIGHT_GRAY);
    
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JLabel titre = new JLabel("Manche n°");
    JLabel versus = new JLabel(j1.getNom() + " VS " + j2.getNom());
    JLabel info = new JLabel("Choissisez une couleur et un signe");
    fenetre.add(mainPanel);
    JPanel titrePanel = new JPanel();
    titrePanel.setLayout(new BoxLayout(titrePanel, BoxLayout.PAGE_AXIS));
    titre.setAlignmentX(Component.CENTER_ALIGNMENT);
    versus.setAlignmentX(Component.CENTER_ALIGNMENT);
    info.setAlignmentX(Component.CENTER_ALIGNMENT);
    titrePanel.add(titre);
    titrePanel.add(versus);
    titrePanel.add(info);
    mainPanel.add(titrePanel);
    mainPanel.add(new JLabel(" "));
    
    JPanel panelcontent = new JPanel(new BorderLayout());
        
    JPanel panelJoueur1 = new JPanel();

        
    panelJoueur1.setLayout(new BoxLayout(panelJoueur1, BoxLayout.PAGE_AXIS));
   
    JLabel J1 = new JLabel(j1.getNom());
    JLabel vide = new JLabel(" ");
    
    Couleur[] propositionCouleur = new Couleur[]{Couleur.ROUGE,Couleur.BLEU,Couleur.VERT,Couleur.JAUNE,Couleur.NOIR};
    
    JPanel couleur1 = new JPanel();
    JLabel couleur = new JLabel("Couleur:");
    choixcouleur1 = new JComboBox(propositionCouleur);
    couleur1.add(couleur);
    couleur1.add(choixcouleur1);
    couleur1.add(new JLabel("    "));
    
    Signe[] propositionSigne = new Signe[]{Signe.X,Signe.O};
    
    
    JPanel signe1 = new JPanel();
    JLabel signe = new JLabel("Signe:");
    choixsigne1 = new JComboBox(propositionSigne);
    signe1.add(signe);
    signe1.add(choixsigne1);
    
    
    
    
    panelJoueur1.add(J1);
    J1.setAlignmentX(Component.CENTER_ALIGNMENT);
    panelJoueur1.add(vide);
    panelJoueur1.add(couleur1); 
    panelJoueur1.add(signe1);  
    panelJoueur1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    panelcontent.add(panelJoueur1,BorderLayout.WEST);
    
    
    JPanel panelJoueur2 = new JPanel();

        
    panelJoueur2.setLayout(new BoxLayout(panelJoueur2, BoxLayout.PAGE_AXIS));
   
    JLabel J2 = new JLabel(j2.getNom());
    JLabel vide2 = new JLabel(" ");
    
    JPanel couleur2 = new JPanel();
    JLabel couleurbis = new JLabel("Couleur:");
    choixcouleur2 = new JComboBox(propositionCouleur);
    
    
    couleur2.add(couleurbis);
    couleur2.add(choixcouleur2);
    couleur2.add(new JLabel("    "));
    
    
    JPanel signe2 = new JPanel();
    JLabel signebis = new JLabel("Signe:");
    choixsigne2 = new JComboBox(propositionSigne);
    signe2.add(signebis);
    signe2.add(choixsigne2);
    
    
    
    
    
    panelJoueur2.add(J2);
    J2.setAlignmentX(Component.CENTER_ALIGNMENT);
    panelJoueur2.add(vide2);
    panelJoueur2.add(couleur2); 
    panelJoueur2.add(signe2); 
    
    
    
    panelJoueur2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    panelcontent.add(panelJoueur2,BorderLayout.EAST);
    
    mainPanel.add(panelcontent);
    
    JPanel valide = new JPanel();
    JButton bValider = new JButton("Valider");
    
    bValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new MessageManche(choixcoul1(),choixcoul2(),choixsigne1(),choixsigne2()));
                clearChanged();
            }
        });
    
    
    valide.add(bValider);
    mainPanel.add(valide);
    
 
 
    
    
        
        
        
        
    }
    
    
    public void afficher() {
        this.fenetre.setVisible(true);
    }
    
    public Couleur choixcoul1(){
       Couleur couleur1 = Couleur.NULL;
       for (Couleur coul : Couleur.values())
           if(choixcouleur1.getSelectedIndex()==coul.ordinal()){
             
           couleur1=coul;    
    
           }
       return couleur1;
    }
    
    public Couleur choixcoul2(){
       Couleur couleur1 = Couleur.NULL;
       for (Couleur coul : Couleur.values())
           if(choixcouleur2.getSelectedIndex()==coul.ordinal()){
             
           couleur1=coul;    
    
           }
       
       return couleur1;
    }
    
    public Signe choixsigne1(){
       Signe signe1 = Signe.NULL;
       for (Signe signe : Signe.values())
           if(choixsigne1.getSelectedIndex()==signe.ordinal()){
             
           signe1=signe;   
    
           }
       
       return signe1;
}
    public Signe choixsigne2(){
       Signe signe1 = Signe.NULL;
       for (Signe signe : Signe.values())
           if(choixsigne2.getSelectedIndex()==signe.ordinal()){
             
           signe1=signe;   
    
           }
       
       return signe1;
    }
      
   public void close(){
         this.fenetre.dispose();
         
     }
    
    
    
    
}
