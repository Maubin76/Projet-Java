import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnexionInterface extends JFrame {
    private JTextField usernameField; // Champ de texte pour le nom d'utilisateur
    private JPasswordField passwordField; // Champ de texte pour le mot de passe

    public ConnexionInterface() {
        // Configuration de la fenêtre
        setTitle("Connexion"); // Définit le titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Définit l'action de fermeture de la fenêtre
        setSize(500, 300); // Définit la taille de la fenêtre (largeur, hauteur)
        setResizable(true); // Autorise le redimensionnement de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        // Création des composants
        JLabel usernameLabel = new JLabel("Nom d'utilisateur:"); // Étiquette pour le nom d'utilisateur
        JLabel passwordLabel = new JLabel("Mot de passe:"); // Étiquette pour le mot de passe
        usernameField = new JTextField(20); // Champ de texte pour le nom d'utilisateur avec une longueur initiale de 20 caractères
        passwordField = new JPasswordField(20); // Champ de texte pour le mot de passe avec une longueur initiale de 20 caractères
        JButton loginButton = new JButton("Se connecter"); // Bouton de connexion

        // Définir la taille personnalisée pour les champs de texte
        Dimension fieldSize = new Dimension(200, 30); // Définit la taille personnalisée pour les champs de texte (largeur, hauteur)
        usernameField.setPreferredSize(fieldSize); // Définit la taille personnalisée pour le champ de texte du nom d'utilisateur
        passwordField.setPreferredSize(fieldSize); // Définit la taille personnalisée pour le champ de texte du mot de passe

        // Création du gestionnaire de disposition
        JPanel panel = new JPanel(new GridBagLayout()); // Crée un panneau avec un gestionnaire de disposition GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints(); // Crée des contraintes pour le placement des composants dans la grille
        constraints.gridx = 0; // Position horizontale de la première colonne
        constraints.gridy = 0; // Position verticale de la première ligne
        constraints.anchor = GridBagConstraints.WEST; // Alignement des composants à gauche
        constraints.insets = new Insets(5, 5, 5, 5); // Espacement entre les composants
        panel.add(usernameLabel, constraints); // Ajoute l'étiquette du nom d'utilisateur au panneau

        constraints.gridy = 1; // Passe à la deuxième ligne
        panel.add(passwordLabel, constraints); // Ajoute l'étiquette du mot de passe au panneau

        constraints.gridx = 1; // Passe à la deuxième colonne
        constraints.gridy = 0; // Reviens à la première ligne
        constraints.ipady = 1; // Largeur de la case de saisie d'identifiant
        constraints.ipadx = 1; // Longueur de la case de saisie d'identifiant
        panel.add(usernameField, constraints); // Ajoute le champ de texte du nom d'utilisateur au panneau

        constraints.gridy = 1; // Passe à la deuxième ligne
        panel.add(passwordField, constraints); // Ajoute le champ de texte du mot de passe au panneau

        constraints.gridx = 0; // Reviens à la première colonne
        constraints.gridy = 2; // Passe à la troisième ligne
        constraints.ipady = 1; // Largeur de la case de saisie de mot de passe
        constraints.ipadx = 1; // Longueur de la case de saisie de mot de passe
        constraints.gridwidth = 2; // Étend le composant sur deux colonnes
        constraints.anchor = GridBagConstraints.CENTER; // Centre le composant
        panel.add(loginButton, constraints); // Ajoute le bouton de connexion au panneau
            // Ajout du gestionnaire d'événements pour le bouton de connexion
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText(); // Récupère le contenu du champ de texte du nom d'utilisateur
                String password = new String(passwordField.getPassword()); // Récupère le contenu du champ de texte du mot de passe

                // Vérification des informations de connexion
                if (username.equals("admin") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(ConnexionInterface.this, "Connexion réussie !"); // Affiche un message de connexion réussie
                } else {
                    JOptionPane.showMessageDialog(ConnexionInterface.this, "Identifiant ou mot de passe incorrect !"); // Affiche un message d'erreur d'identifiant ou de mot de passe incorrect
                }
            }
        });

        // Ajout du panneau à la fenêtre
        add(panel); // Ajoute le panneau à la fenêtre
    }
}

