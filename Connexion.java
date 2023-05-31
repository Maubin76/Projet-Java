import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe Connexion représente une fenêtre de connexion avec des champs pour le nom d'utilisateur et le mot de passe.
 * Elle permet de vérifier les informations de connexion et de lancer la prochaine fenêtre du jeu.
 * 
 */
public class Connexion extends JFrame {
    /**
     * Champ de texte privé permettant d'inscrire le nom d'utilisateur.
     */
    private JTextField usernameField; 
    /**
     * Champ de texte privé permettant d'inscrire le mot de passe de l'utilisateur.
     */
    private JPasswordField passwordField; 
    /**
     * Utilisateur privé, correspondant au "compte" avec lequel on se connecte.
     */
    private Utilisateur personne1; 

    /**
     * Constructeur de la classe Connexion.
     * Initialise la fenêtre de connexion avec tous les composants nécessaires.
     */
    public Connexion() {
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
        JButton signinButton = new JButton("S'inscrire"); // Bouton d'inscription


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
        constraints.gridwidth = 1; // Étend le composant sur deux colonnes
        constraints.anchor = GridBagConstraints.CENTER; // Centre le composant
        panel.add(loginButton, constraints); // Ajoute le bouton de connexion au panneau
        constraints.gridx = 1; // Passe à deuxième colonne
        panel.add(signinButton, constraints); // Ajoute le bouton d'inscription au panneau

        setVisible(true); // Rends la fenêtre visible

        // Ajout du gestionnaire d'événements pour le bouton de connexion
        // Objectif : autoriser la connexion et lancer la suite ssi les informations entrées sont valides
        loginButton.addActionListener(new ActionListener() { // Déclenchement du bouton "se connecter"
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText(); // Récupère le contenu du champ de texte du nom d'utilisateur
                String password = new String(passwordField.getPassword()); // Récupère le contenu du champ de texte du mot de passe

                // Vérification des informations de connexion
                if (verifierIdentifiants(username, password)) {
                    JOptionPane.showMessageDialog(Connexion.this, "Connexion réussie !"); // Affiche un message de connexion réussie
                    dispose(); // Ferme la fenêtre de connexion pour passer au jeu
                    new ModeDeJeu(personne1); // Lance la prochaine fenêtre qui consiste à chosir le mode de jeu (ou le mode administration)
                }
            }
        });
        add(panel); // Ajoute le panneau à la fenêtre

        // Lance la fenêtre d'inscription
        signinButton.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'S'inscrire'
            @Override
            public void actionPerformed(ActionEvent e){
                new Enregistrement(); // Lance le constructeur de la classe Enregistrement
            }
        });
    }
    
    /**
     * Vérifie la validité des informations saisies en comparant avec un fichier de logs.
     *
     * @param nomUtilisateur Le nom d'utilisateur saisi.
     * @param motDePasse     Le mot de passe saisi.
     * @return true si les informations sont valides, false sinon.
     */
    private boolean verifierIdentifiants(String nomUtilisateur, String motDePasse) { // Vérifie dans connexion.csv si la combinaison identifiant/mdp est valide
        try (BufferedReader reader = new BufferedReader(new FileReader("connexion.csv"))) { // Ouvre le fichier de logs
            String ligne;
            while ((ligne = reader.readLine()) != null) { // Parcours le document ligne par ligne
                String[] attributs = ligne.split(","); // Range le contenu de la ligne du document dans un tableau de String
                if (attributs[0].equals(nomUtilisateur) && attributs[1].equals(motDePasse)) { // Cas où on trouve l'utilisateur dans les joueurs inscrits
                    if (attributs[3].equals("true")) { // Cas où le compte est banni
                        JOptionPane.showMessageDialog(Connexion.this, "Compte banni !"); // Message d'avertissement
                        return false; // Connexion non autorisée
                    }
                    else if (attributs[2].equals("true")){ // Intanciation dans le cas d'un administrateur
                        personne1 = new Utilisateur(attributs[0], attributs[1], true, false);
                    }
                    else { // Intanciation dans le cas d'un simple utilisateur
                        personne1 = new Utilisateur(attributs[0], attributs[1], false, false);
                    }
                    return true; // Identifiants valides, connexion autorisée
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(Connexion.this, "Identifiant ou mot de passe incorrect !"); // Affiche un message d'erreur d'identifiant ou de mot de passe incorrect
        return false; // Identifiants invalides
    }

    /**
     * Retourne le compte actuellement connecté.
     *
     * @return Le compte de l'utilisateur connecté.
     */
    public Utilisateur getPersonne1() {
        return personne1;
    }
}
