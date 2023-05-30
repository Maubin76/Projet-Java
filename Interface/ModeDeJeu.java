import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe ModeDeJeu représente une fenêtre permettant de choisir le mode de jeu de la partie (Solo ou Multi).
 * Elle hérite de la classe JFrame de Swing.
 */
public class ModeDeJeu extends JFrame {
    /**
     * Constructeur de la classe ModeDeJeu.
     *
     * @param personne1 l'objet Utilisateur représentant le joueur connecté
     */
    public ModeDeJeu(Utilisateur personne1){
        
        // Paramètres de la fenêtre
        setTitle("Choix du mode"); // Définit le titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Définit l'action de fermeture de la fenêtre
        setSize(500, 300); // Définit la taille de la fenêtre (largeur, hauteur)
        setResizable(true); // Autorise le redimensionnement de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        JPanel panel = new JPanel(new GridBagLayout()); // Crée un panneau avec un gestionnaire de disposition GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints(); // Crée des contraintes pour le placement des composants dans la grille

        JButton jeuSolo = new JButton("Jouer en solo"); // Bouton pour jouer en solo
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 0; // Première ligne
        constraints.ipady = 1; // Largeur de la case
        constraints.ipadx = 1; // Longueur de la case
        constraints.gridwidth = 1; // Étend pas le composant sur plusieurs colonnes
        constraints.anchor = GridBagConstraints.CENTER; // Centre le composant
        constraints.insets = new Insets(5, 5, 5, 5); // Espacement entre les composants
        panel.add(jeuSolo, constraints); // Ajoute le bouton au panneau

        JButton jeuVersus = new JButton ("Jouer en multi"); // Bouton pour jouer en multi
        constraints.gridy = 1; // Deuxième ligne
        panel.add(jeuVersus, constraints); // Ajoute le bouton au panneau

        JButton adminButton = new JButton ("Administrer"); // Bouton pour jouer en multi
        constraints.gridy = 2; // Troisième ligne
        if (personne1.isAdmin()){ // Cas où la personne connectée est un administrateur
            panel.add(adminButton, constraints); // Ajoute le bouton au panneau
        }

        add(panel); // Ajoute le panneau
        setVisible(true); // Rend la fenêtre visible

        // Lancement de la fenêtre qui gère l'administration
        adminButton.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Administrer'
        @Override
            public void actionPerformed(ActionEvent e){
                new ModeAdmin(); // Lance le constructeur de la classe Enregistrement
            }
        });

        jeuSolo.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Jouer en solo'
        @Override
            public void actionPerformed(ActionEvent e){
                new ParamPartie(true);
            }
        });

        jeuVersus.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Jouer en solo'
        @Override
            public void actionPerformed(ActionEvent e){
                new ParamPartie(false);
            }
        });
    }
}
