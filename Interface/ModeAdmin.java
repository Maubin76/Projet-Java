import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ModeAdmin extends JFrame{
    
    public ModeAdmin() {

        // Paramètres de la fenêtre
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

        JTextField banField = new JTextField(10); // Champ de saisie de l'utilisateur à bannir
        constraints.gridx = 1; // Deuxième colonne
        panel.add(banField, constraints); // Ajoute le champ de texte

        JButton banButton = new JButton("Bannir"); // Bouton de bannissement
        constraints.gridx = 2; // Troisième colonne
        panel.add(banButton, constraints); // Ajoute le bouton au panneau

        // Ligne qui traite le débannisement d'un joueur 
        JLabel débanir = new JLabel("Débanir un utilisateur :"); // Texte de débanissement
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 1; // Deuxième ligne
        panel.add(débanir, constraints); // Ajoute le texte au panneau

        JTextField debanField = new JTextField(10); // Champ de saisie de l'utilisateur à débannir
        constraints.gridx = 1; // Deuxième colonne
        panel.add(debanField, constraints); // Ajoute le champ de texte

        JButton debanButton = new JButton("Débannir"); // Bouton de débannissement
        constraints.gridx = 2; // Troisième colonne
        panel.add(debanButton, constraints); // Ajoute le bouton au panneau

        // Ligne qui traite la supression de compte
        JLabel supprimer = new JLabel("Supprimer un compte :"); // Texte de supression de compte
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 2; // Troisième ligne
        panel.add(supprimer, constraints); // Ajoute le texte au panneau

        JTextField suppField = new JTextField(10); // Champ de saisie du compte à supprimer
        constraints.gridx = 1; // Deuxième colonne
        panel.add(suppField, constraints); // Ajoute le champ de texte

        JButton suppButton = new JButton("Supprimer"); // Bouton de suppression de compte
        constraints.gridx = 2; // Troisième colonne
        panel.add(suppButton, constraints); // Ajoute le bouton au panneau

        // Bouton pour la création de question
        JButton ajQuestion = new JButton("Ajouter des questions"); // Bouton d'ajout de question dans la base de données
        constraints.gridx = 2; // Troisième colonne
        constraints.gridy = 3; // Quatrième ligne
        panel.add(ajQuestion, constraints); // Ajoute le bouton au panneau

        // Bouton de retour en arrière
        JButton retour = new JButton("Retour"); // Bouton de retour au menu précédent (choix du mode de jeu)
        constraints.gridx = 0; // Première colonne
        panel.add(retour, constraints); // Ajoute le bouton au panneau

        add(panel); // Ajoute le panneau
        setVisible(true); // Rend la fenêtre visible

        // Retour au menu précédent (choix du mode de jeu)
        retour.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Retour'
        @Override
            public void actionPerformed(ActionEvent e){
                dispose(); // Ferme la page pour revenir à la précédente
            }
        });
        
        // Ajout du gestionnaire d'événements pour le bouton d'ajout de question
        ajQuestion.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Ajouter des questions'
        @Override
            public void actionPerformed(ActionEvent e){
                new CreationQuestion(); // Lancement du constructeur qui permet d'ouvrir la fenêtre de création de questions
            }
        });

        // Ajout du gestionnaire d'événements pour le bouton de suppression de compte
        // Objectif : Supprimer le compte qui correspond à l'identifiant entré du fichier connexion.csv contenant les logs
        suppButton.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'S'inscrire'
        @Override
            public void actionPerformed(ActionEvent e){
                try {
                    GestionCompte supp = new GestionCompte(suppField); // Intanciation qui appelle le constructeur
                    supp.suppCompte(); // appel de la méthode qui supprime le compte saisi
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Ajout du gestionnaire d'événements pour le bouton de bannissement
        // Objectif : empêcher la connexion sur ce compte en changeant une case du fichier connexion.csv 
        banButton.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Bannir'
        @Override
            public void actionPerformed(ActionEvent e){
                try {
                    GestionCompte ban = new GestionCompte(banField); // Instanciation qui appelle le constructeur
                    ban.banCompte(); // Appel de la méthode qui bannit le compte saisi
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Ajout du gestionnaire d'événements pour le bouton de débannissement
        // Objectif : autorisé la connexion sur ce compte banni en changeant une case du fichier connexion.csv 
        debanButton.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Débannir'
        @Override
            public void actionPerformed(ActionEvent e){
                try {
                    GestionCompte deban = new GestionCompte(debanField); // Instanciation qui appelle le constructeur
                    deban.debanCompte(); // Appel de la méthode qui débannit le compte saisi
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
