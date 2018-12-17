/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournoimorpion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author duttod
 */
public class VueInitialisation extends Observable{
    
   
    private JFrame fenetre ;
    private JButton ajouterJ;
    private JButton supprimerJ;
    private  JTextField champnom;
    private JPanel listeJ ;
    public VueInitialisation(ArrayList<Joueur> lesJoueurs ) {
        
        fenetre = new JFrame();
        fenetre.setSize(500, 500);
        
        Color couleurfond =fenetre.getBackground();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
        fenetre.setBackground(Color.LIGHT_GRAY);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        JLabel titre = new JLabel("Tournoi de Morpion");
        fenetre.add(mainPanel);
        JPanel titrePanel = new JPanel();
        titrePanel.add(titre);
        
        mainPanel.add(titrePanel);
        JPanel panelcontent = new JPanel(new BorderLayout());
        
        JPanel panelJoueurs = new JPanel();
        panelcontent.add(panelJoueurs,BorderLayout.WEST);
        mainPanel.add(panelcontent);
        
        panelJoueurs.setLayout(new BoxLayout(panelJoueurs, BoxLayout.PAGE_AXIS));
        panelJoueurs.setPreferredSize(new Dimension(200, 200));
  
        
        JLabel labelJ = new JLabel("Liste des joueurs");
        panelJoueurs.add(labelJ);
        
        JPanel panelListeJ = new JPanel();
        listeJ = new JPanel();
        listeJ.setLayout(new BoxLayout(listeJ, BoxLayout.PAGE_AXIS));
        panelListeJ.add(listeJ);
         if (lesJoueurs.size()!=0){
             for (Joueur j : lesJoueurs){
            
            listeJ.add(new JLabel(j.getNom()));
            
        }
         }
        
        
        
        
        panelListeJ.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
        
        
        panelJoueurs.add(panelListeJ);
        ajouterJ = new JButton("+");
        ajouterJ.setBackground(couleurfond);
        
        ajouterJ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Actions.AJOUTE,champnom.getText()));
                clearChanged();
            }
        });
        
        supprimerJ = new JButton("-");
        supprimerJ.setBackground(couleurfond);
        
        supprimerJ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Actions.SUPPRIME,champnom.getText()));
                clearChanged();
            }
        });
        champnom = new JTextField("Entrez nom joueur");
        champnom.setPreferredSize( new Dimension( 94, 24 ) );
        champnom.setBackground(couleurfond);
        JLabel vide = new JLabel(" ");
        
        JPanel panelButton = new JPanel();
        panelButton.add(ajouterJ);
        panelButton.add(supprimerJ);
        panelButton.add(champnom);
        panelButton.setAlignmentX(0);
        
        panelJoueurs.add(panelButton); 
        
        JButton tutoriel = new JButton("tutoriel");
        tutoriel.setBackground(couleurfond);
        
        tutoriel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Actions.TUTORIEL,""));
                clearChanged();
            }
        });
        
        JButton reinitialiser = new JButton("Réinitialiser tournoi");
        reinitialiser.setBackground(couleurfond);
        
        reinitialiser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Actions.REINITIALISER,""));
                clearChanged();
            }
        });
        
        JButton start = new JButton("Lancer tournoi");
        start.setBackground(couleurfond);
        
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Actions.LANCERTOURNOI,""));
                clearChanged();
            }
        });
        
        
        JPanel panelButtonFonctionalite = new JPanel();
        panelButtonFonctionalite.setLayout(new BoxLayout(panelButtonFonctionalite, BoxLayout.PAGE_AXIS));
        panelButtonFonctionalite.add(new JLabel(" "));
        panelButtonFonctionalite.add(reinitialiser);
        panelButtonFonctionalite.add(new JLabel(" "));
        panelButtonFonctionalite.add(tutoriel);
        panelButtonFonctionalite.add(new JLabel(" "));
        panelButtonFonctionalite.add(start);
        
        panelcontent.add(panelButtonFonctionalite,BorderLayout.EAST);
        
    }
    
     public void afficher() {
        this.fenetre.setVisible(true);
    }
     
     public void close(){
         this.fenetre.dispose();
         
     }
    
     public void rafraichir(ArrayList<Joueur> lesJoueurs){
         listeJ.removeAll();
         if (lesJoueurs.size()!=0){
             for (Joueur j : lesJoueurs){
            
            listeJ.add(new JLabel(j.getNom()));
            
        }
         }
         listeJ.validate();
         listeJ.revalidate();

     }     
}
