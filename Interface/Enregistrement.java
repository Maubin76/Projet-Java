import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enregistrement extends JFrame{

    public Enregistrement() {
        dispose();
        // Configuration de la fenêtre
        setTitle("Inscription"); // Définit le titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Définit l'action de fermeture de la fenêtre
        setSize(500, 400); // Définit la taille de la fenêtre (largeur, hauteur)
        setResizable(true); // Autorise le redimensionnement de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        JPanel panel = new JPanel(new GridBagLayout()); // Crée un panneau avec un gestionnaire de disposition GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints(); // Crée des contraintes pour le placement des composants dans la grille

        JLabel id = new JLabel("Identifiant : "); // Composant qui demande l'identifiant
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 0; // Première ligne
        constraints.ipady = 1; // Largeur de la case
        constraints.ipadx = 1; // Longueur de la case
        constraints.gridwidth = 1; // Étend pas le composant sur plusieurs colonnes
        constraints.anchor = GridBagConstraints.WEST; // Centre le composant
        constraints.gridwidth = 1; // Étend le composant sur deux colonnes
        constraints.insets = new Insets(5, 5, 5, 5); // Espacement entre les composants
        panel.add(id, constraints); // Ajoute le bouton de connexion au panneau

        JTextField idField = new JTextField(20);
        constraints.gridx = 1; // Deuxième colonne
        panel.add(idField, constraints); // Ajoute le bouton de connexion au panneau

        JLabel mdp = new JLabel("Mot de passe : "); // Composant qui demande le mot de passe
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 1; // Deuxième ligne
        panel.add(mdp, constraints); // Ajoute le bouton de connexion au panneau

        JPasswordField passwordField = new JPasswordField(20);
        constraints.gridx = 1; // Deuxième colonne
        panel.add(passwordField, constraints); // Ajoute le bouton de connexion au panneau

        JLabel mdpConfirmation = new JLabel("Confirmation du mot de passe : "); // Composant qui demande le mot de passe
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 2; // Troisième ligne
        panel.add(mdpConfirmation, constraints); // Ajoute le bouton de connexion au panneau

        JPasswordField passwordFieldConfirmation = new JPasswordField(20);
        constraints.gridx = 1; // Deuxième colonne
        panel.add(passwordFieldConfirmation, constraints); // Ajoute le bouton de connexion au panneau

        JLabel code = new JLabel("Code administrateur : "); // Composant qui demande le mot de passe
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 3; // Quatrième ligne
        panel.add(code, constraints); // Ajoute le bouton de connexion au panneau

        JTextField codeField = new JTextField(20);
        constraints.gridx = 1; // Deuxième colonne
        panel.add(codeField, constraints); // Ajoute le bouton de connexion au panneau

        JButton annulerButton = new JButton("Annuler");
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 4; // Cinquième ligne
        panel.add(annulerButton, constraints);

        JButton enregistrerButton = new JButton("Enregistrer");
        constraints.gridx = 1; // Deuxième colonne
        panel.add(enregistrerButton, constraints);

        add(panel);
        setVisible(true);

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        enregistrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
            }
        });
    }
}
