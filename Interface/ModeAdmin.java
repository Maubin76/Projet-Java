import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeAdmin extends JFrame{
    
    public ModeAdmin() {
        setTitle("Administration"); // Définit le titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Définit l'action de fermeture de la fenêtre
        setSize(500, 300); // Définit la taille de la fenêtre (largeur, hauteur)
        setResizable(true); // Autorise le redimensionnement de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        JPanel panel = new JPanel(new GridBagLayout()); // Crée un panneau avec un gestionnaire de disposition GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints(); // Crée des contraintes pour le placement des composants dans la grille

        // Ligne qui traite le bannisement d'un joueur 
        JLabel banir = new JLabel("Banir un utilisateur :"); // Texte de banissement
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 0; // Première ligne
        constraints.ipady = 1; // Largeur de la case
        constraints.ipadx = 1; // Longueur de la case
        constraints.gridwidth = 1; // Étend pas le composant sur plusieurs colonnes
        constraints.anchor = GridBagConstraints.CENTER; // Centre le composant
        constraints.insets = new Insets(5, 5, 5, 5); // Espacement entre les composants
        panel.add(banir, constraints); // Ajoute le texte au panneau

        JTextField banField = new JTextField(10);
        constraints.gridx = 1; // Deuxième colonne
        panel.add(banField, constraints); // Ajoute le champ de texte

        JButton banButton = new JButton("Bannir");
        constraints.gridx = 2; // Troisième colonne
        panel.add(banButton, constraints); // Ajoute le bouton au panneau

        // Ligne qui traite le débannisement d'un joueur 
        JLabel débanir = new JLabel("Débanir un utilisateur :"); // Texte de débanissement
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 1; // Deuxième ligne
        panel.add(débanir, constraints); // Ajoute le texte au panneau

        JTextField debanField = new JTextField(10);
        constraints.gridx = 1; // Deuxième colonne
        panel.add(debanField, constraints); // Ajoute le champ de texte

        JButton debanButton = new JButton("Débannir");
        constraints.gridx = 2; // Troisième colonne
        panel.add(debanButton, constraints); // Ajoute le bouton au panneau

        // Ligne qui traite la supression de compte
        JLabel supprimer = new JLabel("Supprimer un compte :"); // Texte de supression
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 2; // Troisième ligne
        panel.add(supprimer, constraints); // Ajoute le texte au panneau

        JTextField suppField = new JTextField(10);
        constraints.gridx = 1; // Deuxième colonne
        panel.add(suppField, constraints); // Ajoute le champ de texte

        JButton suppButton = new JButton("Supprimer");
        constraints.gridx = 2; // Troisième colonne
        panel.add(suppButton, constraints); // Ajoute le bouton au panneau

        // Bouton pour la création de question
        JButton ajQuestion = new JButton("Ajouter des questions");
        constraints.gridx = 2; // Troisième colonne
        constraints.gridy = 3; // Quatrième ligne
        panel.add(ajQuestion, constraints);

        // Bouton de retour en arrière
        JButton retour = new JButton("Retour");
        constraints.gridx = 0; // Première colonne
        panel.add(retour, constraints);

        add(panel); // Ajoute le panneau
        setVisible(true); // Rend la fenêtre visible

    }
}
