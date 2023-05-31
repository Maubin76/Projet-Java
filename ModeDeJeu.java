import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * La classe ModeDeJeu représente une fenêtre permettant de choisir le mode de jeu de la partie (Solo ou Multi).
 * Elle hérite de la classe JFrame de Swing.
 */
public class ModeDeJeu extends JFrame {
    /**
     * Chaine de caractère privée correspondant à l'identifiant de la personne connectée.
     */
    private String identifiant;

    /**
     * Constructeur de la classe ModeDeJeu.
     *
     * @param personne1 l'objet Utilisateur représentant le joueur connecté
     */
    public ModeDeJeu(Utilisateur personne1){
        identifiant = personne1.getId();
        
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

        int meilleurScore = affectationMeilleurScore();
        JLabel meilleurScoreLabel = new JLabel("Votre meilleur score est : " + meilleurScore);
        constraints.gridy = 3;
        panel.add(meilleurScoreLabel, constraints);

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
                new ParamPartie(true, identifiant);
            }
        });

        jeuVersus.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Jouer en solo'
        @Override
            public void actionPerformed(ActionEvent e){
                new ParamPartie(false, identifiant);
            }
        });
    }

    /**
     * Méthode permettant de récupérer le meilleur score d'un joueur à partir d'un fichier de scores (scores.csv).
     *
     * @return Le meilleur score du joueur, ou 0 s'il n'y a pas de score trouvé.
     */
    public int affectationMeilleurScore(){
        try (BufferedReader reader = new BufferedReader(new FileReader("scores.csv"))) { // Ouverture fichier qui contient les logs
            String ligne; // Ligne du document qui est en train d'être parcourue
            while ((ligne = reader.readLine()) != null) { // Balayage du document ligne par ligne jusqu'à la fin
                String[] ligneTableau = ligne.split(","); // Séparation de la ligne en cases avec le caractère ','
                if (ligneTableau[0].equals(this.identifiant)){
                    return Integer.parseInt(ligneTableau[1]);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return 0;
    }
}
