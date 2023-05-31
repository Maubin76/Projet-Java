import javax.swing.*; // Importe les classes nécessaires pour l'interface graphique Swing
import java.awt.*; // Importe les classes nécessaires pour la gestion de la fenêtre et des composants graphiques
import java.awt.event.*; // Importe les classes nécessaires pour la gestion des événements
import java.io.BufferedReader; // Importe la classe BufferedReader pour la lecture de fichiers
import java.io.FileReader; // Importe la classe FileReader pour la lecture de fichiers
import java.io.IOException; // Importe la classe IOException pour la gestion des exceptions liées à l'entrée/sortie

/**
 * La classe ModeDeJeu représente une fenêtre permettant de choisir le mode de jeu de la partie (Solo ou Multi).
 * Elle hérite de la classe JFrame de Swing.
 */
public class ModeDeJeu extends JFrame {
    private String identifiant; // Variable pour stocker l'identifiant de l'utilisateur connecté

    /**
     * Constructeur de la classe ModeDeJeu.
     *
     * @param personne1 l'objet Utilisateur représentant le joueur connecté
     */
    public ModeDeJeu(Utilisateur personne1){
        identifiant = personne1.getId(); // Récupère l'identifiant de l'utilisateur connecté à partir de l'objet Utilisateur
        
        // Paramètres de la fenêtre
        setTitle("Choix du mode"); // Définit le titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Définit l'action de fermeture de la fenêtre
        setSize(500, 300); // Définit la taille de la fenêtre (largeur, hauteur)
        setResizable(true); // Autorise le redimensionnement de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        JPanel panel = new JPanel(new GridBagLayout()); // Crée un panneau avec un gestionnaire de disposition GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints(); // Crée des contraintes pour le placement des composants dans la grille

        JButton jeuSolo = new JButton("Jouer en solo"); // Crée un bouton pour jouer en solo
        constraints.gridx = 0; // Positionne le bouton dans la première colonne
        constraints.gridy = 0; // Positionne le bouton dans la première ligne
        constraints.ipady = 1; // Définit la hauteur de la case
        constraints.ipadx = 1; // Définit la largeur de la case
        constraints.gridwidth = 1; // Permet au composant de s'étendre sur une seule colonne
        constraints.anchor = GridBagConstraints.CENTER; // Centre le composant dans sa cellule
        constraints.insets = new Insets(5, 5, 5, 5); // Définit les marges autour du bouton
        panel.add(jeuSolo, constraints); // Ajoute le bouton au panneau

        JButton jeuVersus = new JButton ("Jouer en multi"); // Crée un bouton pour jouer en multijoueur
        constraints.gridy = 1; // Positionne le bouton dans la deuxième ligne
        panel.add(jeuVersus, constraints); // Ajoute le bouton au panneau

        JButton adminButton = new JButton ("Administrer"); // Crée un bouton pour accéder à l'administration
        constraints.gridy = 2; // Positionne le bouton dans la troisième ligne
        if (personne1.isAdmin()){ // Vérifie si la personne connectée est un administrateur
            panel.add(adminButton, constraints); // Ajoute le bouton au panneau
        }

        int meilleurScore = affectationMeilleurScore(); // Appelle la méthode pour récupérer le meilleur score de l'utilisateur
        JLabel meilleurScoreLabel = new JLabel("Votre meilleur score est : " + meilleurScore); // Crée une étiquette affichant le meilleur score
        constraints.gridy = 3; // Positionne l'étiquette dans la quatrième ligne
        panel.add(meilleurScoreLabel, constraints); // Ajoute l'étiquette au panneau

        add(panel); // Ajoute le panneau à la fenêtre
        setVisible(true); // Rend la fenêtre visible

        // ActionListener pour le bouton "Administrer"
        adminButton.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e){
                new ModeAdmin(); // Lance la fenêtre d'administration
            }
        });

        // ActionListener pour le bouton "Jouer en solo"
        jeuSolo.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e){
                new ParamPartie(true, identifiant); // Lance la configuration de la partie en solo
            }
        });

        // ActionListener pour le bouton "Jouer en multi"
        jeuVersus.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e){
                new ParamPartie(false, identifiant); // Lance la configuration de la partie en multijoueur
            }
        });
    }

    /**
     * Méthode qui lit le fichier "scores.csv" et retourne le meilleur score de l'utilisateur connecté.
     *
     * @return le meilleur score de l'utilisateur connecté
     */
    public int affectationMeilleurScore(){
        try (BufferedReader reader = new BufferedReader(new FileReader("scores.csv"))) { // Ouvre le fichier "scores.csv" en lecture
            String ligne; // Variable pour stocker la ligne en cours de lecture
            while ((ligne = reader.readLine()) != null) { // Lit le fichier ligne par ligne jusqu'à la fin
                String[] ligneTableau = ligne.split(","); // Sépare la ligne en tableau de chaînes en utilisant la virgule comme séparateur
                if (ligneTableau[0].equals(this.identifiant)){ // Vérifie si l'identifiant correspond à celui de l'utilisateur connecté
                    return Integer.parseInt(ligneTableau[1]); // Retourne le meilleur score correspondant à cet identifiant
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace(); // Affiche la trace de l'exception en cas d'erreur d'entrée/sortie
        }
        return 0; // Retourne 0 si le meilleur score n'est pas trouvé
    }
}
