import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

/**
 * Classe représentant une fenêtre pour ajouter une question avec ses options de réponse,
 * ainsi que sa difficulté et son thème.
 */
public class AjouterQuestion extends JFrame{
    /**
     * Constructeur de la classe AjouterQuestion.
     * Crée et configure la fenêtre graphique pour l'ajout de question.
     */
    public AjouterQuestion(){
        String[] themes = {"Histoire","Géographie","Maths","Jeux-vidéos","Art","Sport","Musique"};

        // Paramètres de la fenêtre
        setTitle("Ajout de questions"); // Définit le titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Définit l'action de fermeture de la fenêtre
        setSize(500, 600); // Définit la taille de la fenêtre (largeur, hauteur)
        setResizable(true); // Autorise le redimensionnement de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        JPanel panel = new JPanel(new GridBagLayout()); // Crée un panneau avec un gestionnaire de disposition GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints(); // Crée des contraintes pour le placement des composants dans la grille

        JLabel enonce = new JLabel("Enoncé");
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 0; // Première ligne
        constraints.ipady = 1; // Largeur de la case
        constraints.ipadx = 1; // Longueur de la case
        constraints.gridwidth = 1; // Étend pas le composant sur plusieurs colonnes
        constraints.anchor = GridBagConstraints.CENTER; // Centre le composant
        constraints.insets = new Insets(5, 5, 5, 5); // Espacement entre les composants
        panel.add(enonce, constraints); // Ajoute le texte au panneau

        JTextField enonceField = new JTextField(20); // Champ de saisie de l'énoncé
        constraints.gridx = 1; // Deuxième colonne
        panel.add(enonceField, constraints); // Ajoute le champ au panneau

        JLabel prop1 = new JLabel("Proposition 1 (bonne réponse) :"); // Composant qui demande de saisir la proposition
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 1; // Deuxième ligne
        panel.add(prop1, constraints); // Ajout du texte au panneau

        JTextField prop1Field = new JTextField(20); // Champ de saisie de la proposition
        constraints.gridx = 1; // Deuxième colonne
        panel.add(prop1Field, constraints); // Ajoute le champ au panneau

        JLabel prop2 = new JLabel("Proposition 2 :"); // Composant qui demande de saisir la proposition
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 2; // Troisième ligne
        panel.add(prop2, constraints); // Ajout du texte au panneau

        JTextField prop2Field = new JTextField(20); // Champ de saisie de la proposition
        constraints.gridx = 1; // Deuxième colonne
        panel.add(prop2Field, constraints); // Ajoute le champ au panneau

        JLabel prop3 = new JLabel("Proposition 3 :"); // Composant qui demande de saisir la proposition
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 3; // Quatrième ligne
        panel.add(prop3, constraints); // Ajout du texte au panneau

        JTextField prop3Field = new JTextField(20); // Champ de saisie de la proposition
        constraints.gridx = 1; // Deuxième colonne
        panel.add(prop3Field, constraints); // Ajoute le champ au panneau

        JLabel prop4 = new JLabel("Proposition 4 :"); // Composant qui demande de saisir la proposition
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 4; // Cinquième ligne
        panel.add(prop4, constraints); // Ajout du texte au panneau

        JTextField prop4Field = new JTextField(20); // Champ de saisie de la proposition
        constraints.gridx = 1; // Deuxième colonne
        panel.add(prop4Field, constraints); // Ajoute le champ au panneau

        JLabel niveau = new JLabel("Niveau de la question"); // Composant qui demande de saisir la proposition
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 5; // Sixième ligne
        panel.add(niveau, constraints); // Ajoute le texte au panneau

        Integer[] tabNiveau = {1,2,3,4,5,6,7,8,9,10}; // Tableau d'entiers qui stocke les niveaux des questions
        JComboBox<Integer> niveauBox = new JComboBox<>(tabNiveau); // Menu déroulant
        constraints.gridx = 1; // Deuxième colonne
        panel.add(niveauBox, constraints); // Ajoute le menu déroulant au tableau

        JLabel them = new JLabel("Choix du thème :"); // Composant qui demande de choisir le thème
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 6; // Septième ligne
        panel.add(them, constraints); // Ajout du texte au panneau

        JComboBox<String> themeBox = new JComboBox<>(themes); // Menu déroulant qui propose les thèmes
        constraints.gridx = 1; // Deuxième colonne
        panel.add(themeBox, constraints); // Ajout du menu déroulant au panneau

        JButton retour = new JButton("Retour"); // Bouton de retour
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 7; // Huitième ligne
        panel.add(retour, constraints); // Ajoute le bouton au panneau

        JButton soumettreQuestion = new JButton("Ajouter");
        constraints.gridx = 1; // Deuxième colonne
        panel.add(soumettreQuestion, constraints); // Ajout du bouton au panneau

        add(panel); // Ajoute le panneau
        setVisible(true); // Rend la fenêtre visible

        // Retour au menu précédent (l'administration)
        retour.addActionListener(new ActionListener() { // Déclenche à l'appui du bouton 'Retour'
            @Override
            public void actionPerformed(ActionEvent e){
                dispose(); // Ferme la page pour revenir à la précédente
            }
        });

        // Ajout du gestionnaire d'événements pour le bouton de soumission de question
        // Objectif : ajouter la question et ses propositions dans la base de données
        soumettreQuestion.addActionListener(new ActionListener() { // Déclenche à l'appui du bouton 'Ajouter'
            private String th; // Variable pour stocker le thème
            private String en; // Variable pour stocker l'énoncé
            private String p1; // Variable pour stocker la proposition 1
            private String p2; // Variable pour stocker la proposition 2
            private String p3; // Variable pour stocker la proposition 3
            private String p4; // Variable pour stocker la proposition 4
            private String nv; // Variable pour stocker le niveau
            @Override
            public void actionPerformed(ActionEvent e){
                Question Q; // Déclaration de la variable de type Question
                en = enonceField.getText(); // Récupère le texte saisi dans le champ d'énoncé
                p1 = prop1Field.getText(); // Récupère le texte saisi dans le champ de la proposition 1
                p2 = prop2Field.getText(); // Récupère le texte saisi dans le champ de la proposition 2
                p3 = prop3Field.getText(); // Récupère le texte saisi dans le champ de la proposition 3
                p4 = prop4Field.getText(); // Récupère le texte saisi dans le champ de la proposition 4
                Integer selectedNiveau = (Integer) niveauBox.getSelectedItem(); // Récupère le niveau sélectionné dans le menu déroulant
                nv = String.valueOf(selectedNiveau); // Convertit le niveau en chaîne de caractères
                th = (String) themeBox.getSelectedItem(); // Récupère le thème sélectionné dans le menu déroulant
                try {
                    Q = new Question(en, p1, p2, p3, p4, nv, th); // Crée une nouvelle instance de la classe Question
                    if (Q.valide()){ // Vérifie si la question est valide
                        Q.soumettre(); // Soumet la question à la base de données
                        JOptionPane.showMessageDialog(AjouterQuestion.this, "Question ajoutée"); // Affiche un message de succès
                    }
                    else {
                        JOptionPane.showMessageDialog(AjouterQuestion.this, "Erreur lors de l'ajout"); // Affiche un message d'erreur
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
