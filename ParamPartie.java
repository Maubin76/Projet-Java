import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ParamPartie extends JFrame{
    /**
     * Chaine de caractère privée indiquand le thème sélectionné pour la partie.
     * Peut prendre sa valeur parmi {"Histoire","Géographie","Art","Sport","Maths","Musique","Jeux-vidéos"}.
     */
    private String selectedTheme;
    /**
     * Chaine de caractère privée indiquand le niveau de difficulté sélectionné pour la partie.
     * Peut prendre sa valeur parmi {"Facile","Moyen","Difficile"}.
     */
    private String selectedDifficulte;
    /**
     * Entier privé indiquand le nombre de questions sélectionné pour la partie.
     * Peut prendre une valeur entre 5 et 10.
     */
    private int selectedNombre;
    private String identifiant;

    public ParamPartie(boolean solo, String id){
        identifiant = id;
        // Création de la fenêtre
        setTitle("Paramètres de partie"); // Définit le titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Définit l'action de fermeture de la fenêtre
        setSize(500, 300); // Définit la taille de la fenêtre (largeur, hauteur)
        setResizable(true); // Autorise le redimensionnement de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        JPanel panel = new JPanel(new GridBagLayout()); // Crée un panneau avec un gestionnaire de disposition GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints(); // Crée des contraintes pour le placement des composants dans la grille

        // Choix du thème
        String[] themes = {"Histoire","Géographie","Art","Sport","Maths","Musique","Jeux-vidéos"};
        JLabel themeLabel = new JLabel("Choix du thème : ");
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 0; // Première ligne
        constraints.ipady = 1; // Largeur de la case
        constraints.ipadx = 1; // Longueur de la case
        constraints.gridwidth = 1; // Étend pas le composant sur plusieurs colonnes
        constraints.anchor = GridBagConstraints.CENTER; // Aligne le composant à gauche de sa case
        constraints.insets = new Insets(5, 5, 5, 5); // Espacement entre les composants
        panel.add(themeLabel, constraints); // Ajoute le texte au panneau
        JComboBox<String> themeComboBox = new JComboBox<>(themes);
        constraints.gridx = 1;
        panel.add(themeComboBox, constraints); // Ajoute le texte au panneau

        // Choix de la difficulté
        String[] difficultes = {"Facile", "Moyen", "Difficile"};
        JLabel difficulteLabel = new JLabel("Choix de la difficulté :");
        constraints.gridy = 1;
        constraints.gridx = 0;
        panel.add(difficulteLabel, constraints); // Ajoute le texte au panneau
        JComboBox<String> difficulteComboBox = new JComboBox<>(difficultes);
        constraints.gridx = 1;
        panel.add(difficulteComboBox, constraints);

        // Choix du nombre de questions
        JLabel nombreLabel = new JLabel("Nombre de questions :");
        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(nombreLabel, constraints);
        JSpinner nombreSpinner = new JSpinner(new SpinnerNumberModel(5, 5, 10, 1)); 
        constraints.gridx = 1;
        panel.add(nombreSpinner, constraints);

        // Ajout d'un gestionnaire d'événements aux menus déroulants
        JButton validerButton = new JButton("Valider");
        constraints.gridy = 3;
        constraints.gridx = 1;
        panel.add(validerButton, constraints);
        validerButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                ParamPartie.this.selectedTheme = (String) themeComboBox.getSelectedItem();
                ParamPartie.this.selectedDifficulte = (String) difficulteComboBox.getSelectedItem();
                ParamPartie.this.selectedNombre = (int) nombreSpinner.getValue();
                if (solo)
                    try {
                        new PartieSolo(ParamPartie.this.selectedTheme, ParamPartie.this.selectedDifficulte, ParamPartie.this.selectedNombre, identifiant);
                    } catch (UnsupportedAudioFileException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                else new PartieMulti(ParamPartie.this.selectedTheme, ParamPartie.this.selectedDifficulte, ParamPartie.this.selectedNombre);
            }
        });

        JButton retourButton = new JButton("Retour");
        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(retourButton, constraints);
        retourButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });   

        add(panel); // Ajoute le panneau
        setVisible(true); // Rend la fenêtre visible     
    }

    /**
     * Getter de l'attribut selectedTheme.
     *
     * @return Le thème qui a été sélectionné.
     */
    public String getSelectedTheme() {
        return selectedTheme;}
    /**
     * Getter de l'attribut selectedDifficulte.
     *
     * @return Le niveau de difficulté qui a été sélectionné.
     */
    public String getSelectedDifficulte() {
        return selectedDifficulte;}
    /**
     * Getter de l'attribut selectedNombre.
     *
     * @return Le nombre de question qui a été sélectionné.
     */
    public int getSelectedNombre() {
        return selectedNombre;}
} 
