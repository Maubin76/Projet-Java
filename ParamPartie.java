import javax.sound.sampled.UnsupportedAudioFileException; // Importe la classe UnsupportedAudioFileException pour la gestion des exceptions liées aux fichiers audio
import javax.swing.*; // Importe les classes nécessaires pour l'interface graphique Swing
import java.awt.*; // Importe les classes nécessaires pour la gestion de la fenêtre et des composants graphiques
import java.awt.event.*; // Importe les classes nécessaires pour la gestion des événements
import java.io.IOException; // Importe la classe IOException pour la gestion des exceptions liées à l'entrée/sortie

public class ParamPartie extends JFrame{
    private String selectedTheme; // Variable privée pour stocker le thème sélectionné pour la partie
    private String selectedDifficulte; // Variable privée pour stocker la difficulté sélectionnée pour la partie
    private int selectedNombre; // Variable privée pour stocker le nombre de questions sélectionné pour la partie
    private String identifiant; // Variable privée pour stocker l'identifiant de l'utilisateur connecté

    public ParamPartie(boolean solo, String id){
        identifiant = id; // Affecte l'identifiant de l'utilisateur connecté à la variable correspondante
        
        setTitle("Paramètres de partie"); // Définit le titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Définit l'action de fermeture de la fenêtre
        setSize(500, 300); // Définit la taille de la fenêtre (largeur, hauteur)
        setResizable(true); // Autorise le redimensionnement de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        JPanel panel = new JPanel(new GridBagLayout()); // Crée un panneau avec un gestionnaire de disposition GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints(); // Crée des contraintes pour le placement des composants dans la grille

        String[] themes = {"Histoire","Géographie","Art","Sport","Maths","Musique","Jeux-vidéos"}; // Tableau des thèmes possibles
        JLabel themeLabel = new JLabel("Choix du thème : "); // Crée un label pour le choix du thème
        constraints.gridx = 0; // Positionne le label dans la première colonne
        constraints.gridy = 0; // Positionne le label dans la première ligne
        constraints.ipady = 1; // Définit la hauteur de la case
        constraints.ipadx = 1; // Définit la largeur de la case
        constraints.gridwidth = 1; // Permet au composant de s'étendre sur une seule colonne
        constraints.anchor = GridBagConstraints.CENTER; // Centre le composant dans sa cellule
        constraints.insets = new Insets(5, 5, 5, 5); // Définit les marges autour du label
        panel.add(themeLabel, constraints); // Ajoute le label au panneau
        JComboBox<String> themeComboBox = new JComboBox<>(themes); // Crée une liste déroulante pour le choix du thème
        constraints.gridx = 1; // Positionne la liste déroulante dans la deuxième colonne
        panel.add(themeComboBox, constraints); // Ajoute la liste déroulante au panneau

        String[] difficultes = {"Facile", "Moyen", "Difficile"}; // Tableau des difficultés possibles
        JLabel difficulteLabel = new JLabel("Choix de la difficulté :"); // Crée un label pour le choix de la difficulté
        constraints.gridy = 1; // Positionne le label dans la deuxième ligne
        constraints.gridx = 0; // Positionne le label dans la première colonne
        panel.add(difficulteLabel, constraints); // Ajoute le label au panneau
        JComboBox<String> difficulteComboBox = new JComboBox<>(difficultes); // Crée une liste déroulante pour le choix de la difficulté
        constraints.gridx = 1; // Positionne la liste déroulante dans la deuxième colonne
        panel.add(difficulteComboBox, constraints); // Ajoute la liste déroulante au panneau

        JLabel nombreLabel = new JLabel("Nombre de questions :"); // Crée un label pour le choix du nombre de questions
        constraints.gridy = 2; // Positionne le label dans la troisième ligne
        constraints.gridx = 0; // Positionne le label dans la première colonne
        panel.add(nombreLabel, constraints); // Ajoute le label au panneau
        JSpinner nombreSpinner = new JSpinner(new SpinnerNumberModel(5, 5, 10, 1)); // Crée un spinner pour le choix du nombre de questions
        constraints.gridx = 1; // Positionne le spinner dans la deuxième colonne
        panel.add(nombreSpinner, constraints); // Ajoute le spinner au panneau

        JButton validerButton = new JButton("Valider"); // Crée un bouton "Valider"
        constraints.gridy = 3; // Positionne le bouton dans la quatrième ligne
        constraints.gridx = 1; // Positionne le bouton dans la deuxième colonne
        panel.add(validerButton, constraints); // Ajoute le bouton au panneau
        validerButton.addActionListener(new ActionListener() { // Ajoute un écouteur d'événements au bouton "Valider"
            public void actionPerformed(ActionEvent e) {
                ParamPartie.this.selectedTheme = (String) themeComboBox.getSelectedItem(); // Récupère le thème sélectionné
                ParamPartie.this.selectedDifficulte = (String) difficulteComboBox.getSelectedItem(); // Récupère la difficulté sélectionnée
                ParamPartie.this.selectedNombre = (int) nombreSpinner.getValue(); // Récupère le nombre de questions sélectionné
                if (solo) { // Si le mode de jeu est en solo
                    try {
                        new PartieSolo(ParamPartie.this.selectedTheme, ParamPartie.this.selectedDifficulte, ParamPartie.this.selectedNombre, identifiant); // Lance une partie solo avec les paramètres sélectionnés
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace(); // Affiche la trace de l'exception en cas d'erreur de fichier audio non pris en charge
                    } catch (IOException e1) {
                        e1.printStackTrace(); // Affiche la trace de l'exception en cas d'erreur d'entrée/sortie
                    }
                } else {
                    new PartieMulti(ParamPartie.this.selectedTheme, ParamPartie.this.selectedDifficulte, ParamPartie.this.selectedNombre); // Lance une partie multijoueur avec les paramètres sélectionnés
                }
            }
        });

        JButton retourButton = new JButton("Retour"); // Crée un bouton "Retour"
        constraints.gridy = 3; // Positionne le bouton dans la quatrième ligne
        constraints.gridx = 0; // Positionne le bouton dans la première colonne
        panel.add(retourButton, constraints); // Ajoute le bouton au panneau
        retourButton.addActionListener(new ActionListener() { // Ajoute un écouteur d'événements au bouton "Retour"
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre
            }
        });

        add(panel); // Ajoute le panneau à la fenêtre
        setVisible(true); // Rend la fenêtre visible
    }

    public String getSelectedTheme() {
        return selectedTheme; // Retourne le thème sélectionné
    }

    public String getSelectedDifficulte() {
        return selectedDifficulte; // Retourne la difficulté sélectionnée
    }

    public int getSelectedNombre() {
        return selectedNombre; // Retourne le nombre de questions sélectionné
    }
}
