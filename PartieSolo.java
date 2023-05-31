import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe représentant une partie solo.
 */
public class PartieSolo extends JFrame {
    /**
     * Chaine de caractères privée correspondant au thème de la partie.
     */
    private String theme; 
    /**
     * Chaine de caractères privée correspondant au niveau de difficulté de la partie.
     */
    private String difficulte; 
    /**
     * Entier privé correspondant au nombre de questions de la partie.
     */
    private int nbQuestions; 
    /**
     * Tableau dynamique pivé de tableaux statiques de chaines de caractères.
     * Correspond à la liste des questions de la partie, ainsi que toutes leurs caractéristiques.
     */
    private ArrayList<String[]> tableauQuestions = new ArrayList<>(); 
    /**
     * Entier privé correspondant au nombre de questions déjà répondues.
     */
    private int questionsRepondues = 0; 
    /**
     * Entier privé correspondant au score rélisé.
     */
    private int score = 0; 
    /**
     * Générateur de nombres aléatoires
     */
    private Random random = new Random(); 
    /**
     * Tableau statique privé de chaines de caractères permettant de stocker une ligne de question.
     */
    private String[] ligne; 
    /**
     * Panneau principal de l'interface
     */
    private JPanel panel; 
    /**
     * Entier privé correspondant au nombre de secondes restantes pour répondre à la question.
     */
    private int secondesRestantes; 
    /**
     * Timer pour le compte à rebours.
     */
    private Timer questionTimer; 
    /**
     * Entier privé correspondant au score maximal atteignable si toutes les questions sont répondues avec succés.
     */
    private int scoreMax = 0; 
    /**
     * Chaine de caractere privée correspondant à l'identifiant du joueur connecté.
     */
    private String identifiant; 

    /**
     * Constructeur de la classe PartieSolo.
     *
     * @param theme       Thème de la partie.
     * @param difficulte  Niveau de difficulté de la partie.
     * @param nbQuestions Nombre de questions dans la partie.
     * @param id          Identifiant du joueur.
     * @throws UnsupportedAudioFileException En cas d'erreur lors de la lecture du fichier audio.
     * @throws IOException                   En cas d'erreur d'entrée/sortie lors de la lecture du fichier.
     */
    public PartieSolo(String theme, String difficulte, int nbQuestions, String id) throws UnsupportedAudioFileException, IOException {
        this.identifiant = id;
        this.theme = theme;
        this.difficulte = difficulte;
        this.nbQuestions = nbQuestions;

        // Paramètres de la fenêtre
        setTitle("Partie Solo"); // Titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Action de fermeture de la fenêtre
        setSize(1000, 300); // Taille de la fenêtre
        setResizable(true); // Redimensionnement de la fenêtre autorisé
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran

        // Désérialisation des questions qui appartiennent au thème et à la difficulté choisis
        try (BufferedReader reader = new BufferedReader(new FileReader("questions.csv"))) {
            String ligne;
            ligne = reader.readLine(); // Lecture de la première ligne (entête)
            while (ligne != null) {
                String[] ligneTableau = ligne.split(","); // Séparation des données par ","
                if (bonneQuestion(ligneTableau)) {
                    tableauQuestions.add(ligneTableau); // Ajout de la question au tableau
                }
                ligne = reader.readLine(); // Lecture de la ligne suivante
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel = new JPanel(new GridBagLayout()); // Création du panel avec une disposition en grille
        add(panel); // Ajout du panel à la fenêtre

        poserQuestionSuivante(); // Affichage de la première question

        setVisible(true); // Rendre la fenêtre visible
    }

    /**
     * Vérifie si une question correspond aux critères de thème et de difficulté.
     *
     * @param question  La question à vérifier.
     * @return true si la question correspond aux critères, false sinon.
     */
    public boolean bonneQuestion(String[] question) {
        if (question.length >= 7 && question[6].equals(this.theme)) { // Vérifie si la question a au moins 7 éléments et si le thème correspond
            switch (difficulte) { // Vérifie la difficulté choisie
                case "Facile":
                    if (question[5].equals("1") || question[5].equals("2") || question[5].equals("3") || question[5].equals("4")) { // Vérifie si la difficulté de la question correspond aux niveaux "Facile"
                        return true; // La question est valide
                    }
                    break;
                case "Moyen":
                    if (question[5].equals("4") || question[5].equals("5") || question[5].equals("6") || question[5].equals("7")) { // Vérifie si la difficulté de la question correspond aux niveaux "Moyen"
                        return true; // La question est valide
                    }
                    break;
                case "Difficile":
                    if (question[5].equals("7") || question[5].equals("8") || question[5].equals("9") || question[5].equals("10")) { // Vérifie si la difficulté de la question correspond aux niveaux "Difficile"
                        return true; // La question est valide
                    }
                    break;
                default:
                    return false; // La difficulté n'est pas valide, la question est invalide
            }
        }
        return false; // La question ne correspond pas aux critères, elle est invalide
    }

    /**
     * Pose la question suivante.
     *
     * @throws UnsupportedAudioFileException En cas d'erreur lors de la lecture du fichier audio.
     * @throws IOException                   En cas d'erreur d'entrée/sortie lors de la lecture du fichier.
     */
    public void poserQuestionSuivante() throws UnsupportedAudioFileException, IOException {
        if (questionsRepondues < nbQuestions && !tableauQuestions.isEmpty()) { // Vérifie s'il reste des questions à poser et si la liste de questions n'est pas vide
            panel.removeAll(); // Supprime tous les composants du panel
            panel.revalidate(); // Actualise la mise en page du panel
            panel.repaint(); // Redessine le panel

            int indexAleatoire = random.nextInt(tableauQuestions.size()); // Sélectionne un index aléatoire dans la liste des questions
            ligne = tableauQuestions.get(indexAleatoire); // Récupère la question correspondante à l'index
            tableauQuestions.remove(indexAleatoire); // Supprime la question de la liste
            poseQuestion(ligne, questionsRepondues); // Affiche la question
        } else {

            panel.removeAll(); // Supprime tous les composants du panel
            panel.revalidate(); // Actualise la mise en page du panel
            panel.repaint(); // Redessine le panel

            GridBagConstraints constraints = new GridBagConstraints(); // Définit les contraintes pour la disposition des composants dans le panel

            if (score > meilleurScore()) { // Vérifie si le score actuel est supérieur au meilleur score enregistré
                changementMeilleurScore(); // Met à jour le meilleur score

            }

            JLabel scoreLabel = new JLabel("Score : " + score + "/" + scoreMax); // Crée un label pour afficher le score
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.ipady = 1;
            constraints.ipadx = 1;
            constraints.gridwidth = 2;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = new Insets(5, 5, 5, 5);
            panel.add(scoreLabel, constraints); // Ajoute le label du score au panel selon les contraintes

            JButton nouvellePartieButton = new JButton("Nouvelle partie"); // Crée un bouton pour commencer une nouvelle partie
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            panel.add(nouvellePartieButton, constraints); // Ajoute le bouton de nouvelle partie au panel selon les contraintes

            JButton paramButton = new JButton("Paramètres de partie"); // Crée un bouton pour accéder aux paramètres de la partie
            constraints.gridx = 1;
            constraints.gridwidth = 1;
            panel.add(paramButton, constraints); // Ajoute le bouton des paramètres de partie au panel selon les contraintes

            nouvellePartieButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    try {
                        new PartieSolo(theme, difficulte, nbQuestions, identifiant); // Lance une nouvelle partie solo avec les paramètres actuels
                    } catch (UnsupportedAudioFileException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });

            paramButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Ferme la fenêtre actuelle
                }
            });

            panel.revalidate(); // Actualise la mise en page du panel
            panel.repaint(); // Redessine le panel

            AudioInputStream audio;
            if (score == 0) { // Vérifie si le score est égal à zéro
                audio = AudioSystem.getAudioInputStream(new File("PasOuf.wav")); // Charge le fichier audio pour la défaite
            } else {
                audio = AudioSystem.getAudioInputStream(new File("Victoire.wav")); // Charge le fichier audio pour la victoire
            }
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();

            } catch (LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Affiche une question.
     *
     * @param ligne La ligne de données de la question.
     * @param k     Le numéro de la question.
     */
    public void poseQuestion(String[] ligne, int k) {
        GridBagConstraints constraints = new GridBagConstraints(); // Définit les contraintes pour la disposition des composants dans le panel
        secondesRestantes = 20; // Initialise le nombre de secondes restantes pour répondre à la question

        ButtonGroup buttonGroup = new ButtonGroup(); // Crée un groupe de boutons radio pour les options de réponse

        JLabel compteAReboursLabel = new JLabel("Temps restant : " + secondesRestantes + " secondes"); // Crée un label pour afficher le compte à rebours
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(compteAReboursLabel, constraints); // Ajoute le label du compte à rebours au panel selon les contraintes

        JLabel enonceText = new JLabel(ligne[0]); // Crée un label pour afficher l'énoncé de la question
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.ipady = 1;
        constraints.ipadx = 1;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(enonceText, constraints); // Ajoute le label de l'énoncé au panel selon les contraintes

        String kString = "Question " + (k + 1) + " : ";
        JLabel numQuestionText = new JLabel(kString); // Crée un label pour afficher le numéro de la question
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(numQuestionText, constraints); // Ajoute le label du numéro de la question au panel selon les contraintes

        Integer[] indices = {1, 2, 3, 4};
        Collections.shuffle(Arrays.asList(indices)); // Mélange les indices pour afficher les options de réponse dans un ordre aléatoire
        JRadioButton prop1Box = new JRadioButton(ligne[indices[0]]); // Crée un bouton radio pour la première option de réponse
        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(prop1Box, constraints); // Ajoute le bouton radio de la première option de réponse au panel selon les contraintes
        buttonGroup.add(prop1Box); // Ajoute le bouton radio à groupe de boutons

        JRadioButton prop2Box = new JRadioButton(ligne[indices[1]]); // Crée un bouton radio pour la deuxième option de réponse
        constraints.gridx = 1;
        panel.add(prop2Box, constraints); // Ajoute le bouton radio de la deuxième option de réponse au panel selon les contraintes
        buttonGroup.add(prop2Box); // Ajoute le bouton radio à groupe de boutons

        JRadioButton prop3Box = new JRadioButton(ligne[indices[2]]); // Crée un bouton radio pour la troisième option de réponse
        constraints.gridx = 2;
        panel.add(prop3Box, constraints); // Ajoute le bouton radio de la troisième option de réponse au panel selon les contraintes
        buttonGroup.add(prop3Box); // Ajoute le bouton radio à groupe de boutons

        JRadioButton prop4Box = new JRadioButton(ligne[indices[3]]); // Crée un bouton radio pour la quatrième option de réponse
        constraints.gridx = 3;
        panel.add(prop4Box, constraints); // Ajoute le bouton radio de la quatrième option de réponse au panel selon les contraintes
        buttonGroup.add(prop4Box); // Ajoute le bouton radio à groupe de boutons

        JButton validerButton = new JButton("Valider"); // Crée un bouton pour valider la réponse
        constraints.gridy = 3;
        panel.add(validerButton, constraints); // Ajoute le bouton de validation au panel selon les contraintes

        questionTimer = new Timer(1000, new ActionListener() { // Crée un timer pour le compte à rebours de la question
            public void actionPerformed(ActionEvent e) {
                mettreAJourCompteARebours(compteAReboursLabel, questionTimer);
            }
        });

        questionTimer.setInitialDelay(0);
        questionTimer.start(); // Démarre le timer

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ajoutScore;
                if (difficulte.equals("Facile")) {
                    ajoutScore = Integer.parseInt(ligne[5]); // Ajoute le score de la question en fonction de la difficulté
                } else if (difficulte.equals("Moyen")) {
                    ajoutScore = Integer.parseInt(ligne[5]) - 3; // Ajoute le score de la question en fonction de la difficulté
                } else {
                    ajoutScore = Integer.parseInt(ligne[5]) - 3 * 2; // Ajoute le score de la question en fonction de la difficulté
                }
                if (prop1Box.isSelected() && ligne[indices[0]].equals(ligne[1])) { // Vérifie si la première option de réponse est sélectionnée et correspond à la bonne réponse
                    score = score + ajoutScore; // Ajoute le score à la réponse
                } else if (prop2Box.isSelected() && ligne[indices[1]].equals(ligne[1])) { // Vérifie si la deuxième option de réponse est sélectionnée et correspond à la bonne réponse
                    score = score + ajoutScore; // Ajoute le score à la réponse
                } else if (prop3Box.isSelected() && ligne[indices[2]].equals(ligne[1])) { // Vérifie si la troisième option de réponse est sélectionnée et correspond à la bonne réponse
                    score = score + ajoutScore; // Ajoute le score à la réponse
                } else if (prop4Box.isSelected() && ligne[indices[3]].equals(ligne[1])) { // Vérifie si la quatrième option de réponse est sélectionnée et correspond à la bonne réponse
                    score = score + ajoutScore; // Ajoute le score à la réponse
                }

                scoreMax = scoreMax + ajoutScore; // Ajoute le score maximum possible pour cette question
                questionsRepondues++; // Incrémente le nombre de questions répondues

                questionTimer.stop(); // Arrête le timer actuel

                try {
                    poserQuestionSuivante(); // Passe à la question suivante
                } catch (UnsupportedAudioFileException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        panel.revalidate(); // Actualise la mise en page du panel
        panel.repaint(); // Redessine le panel
    }
   
    /**
     * Met à jour le compte à rebours de la question.
     *
     * @param compteAReboursLabel Le label du compte à rebours.
     * @param questionTimer      Le timer de la question.
     */
    private void mettreAJourCompteARebours(JLabel compteAReboursLabel, Timer questionTimer) {
        secondesRestantes--; // Décrémente le compteur des secondes restantes
    
        if (secondesRestantes >= 0) { // Vérifie s'il reste du temps
            compteAReboursLabel.setText("Temps restant : " + secondesRestantes + " secondes"); // Met à jour l'affichage du compte à rebours
        } else {
            compteAReboursLabel.setText("Temps écoulé !"); // Affiche "Temps écoulé !" lorsque le temps est écoulé
            questionTimer.stop(); // Arrête le timer de la question
            questionsRepondues++; // Incrémente le nombre de questions répondues
    
            try {
                poserQuestionSuivante(); // Passe à la question suivante
            } catch (UnsupportedAudioFileException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Calcule le meilleur score.
     *
     * @return Le meilleur score.
     */
    public int meilleurScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader("scores.csv"))) { // Ouverture du fichier qui contient les logs
            String ligne; // Ligne du document qui est en train d'être parcourue
            while ((ligne = reader.readLine()) != null) { // Balayage du document ligne par ligne jusqu'à la fin
                String[] ligneTableau = ligne.split(","); // Séparation de la ligne en cases avec le caractère ','
                if (ligneTableau[0].equals(this.identifiant)) { // Vérifie si l'identifiant correspond à la ligne actuelle
                    return Integer.parseInt(ligneTableau[1]); // Retourne le meilleur score correspondant à l'identifiant
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return 0; // Retourne 0 si aucun meilleur score n'a été trouvé pour l'identifiant
    }
    
    /**
     * Met à jour le meilleur score du joueur dans le fichier "scores.csv".
     * 
     * La méthode effectue une désérialisation du fichier, puis parcourt les scores existants.
     * Si l'identifiant du joueur correspond à une ligne existante et que son nouveau score est supérieur à l'ancien score,
     * le score est mis à jour dans le tableau des scores.
     * Ensuite, une nouvelle ligne contenant l'identifiant du joueur et son score est ajoutée au tableau des scores
     * si le joueur n'avait pas de score enregistré auparavant.
     * 
     * Après la mise à jour du tableau des scores, la méthode effectue une sérialisation pour écrire les scores
     * dans le fichier "scores.csv".
     * Chaque ligne du tableau des scores est convertie en une ligne de texte avec des virgules comme séparateurs,
     * puis écrite dans le fichier. Enfin, la méthode ferme la fenêtre d'inscription pour retourner à l'écran de connexion.
     * 
     * En cas d'erreur d'entrée/sortie lors de la lecture ou de l'écriture du fichier, une exception est lancée
     * et l'erreur est affichée.
     */
    public void changementMeilleurScore() {
        // Désérialisation
        ArrayList<String[]> tableauScores = new ArrayList<>(); // Crée une liste pour stocker les scores
    
        try (BufferedReader reader = new BufferedReader(new FileReader("scores.csv"))) { // Ouverture du fichier qui contient les scores
            String ligne; // Ligne du document qui est en train d'être parcourue
            while ((ligne = reader.readLine()) != null) { // Balayage du document ligne par ligne jusqu'à la fin
                String[] ligneTableau = ligne.split(","); // Séparation de la ligne en cases avec le caractère ','
                if (ligneTableau[0].equals(identifiant) && score > Integer.parseInt(ligneTableau[1])) {
                    ligneTableau[1] = String.valueOf(score); // Met à jour le score si l'identifiant correspond et le score est supérieur à l'ancien score
                }
                tableauScores.add(ligneTableau); // Ajoute la ligne au tableau de String[]
            }
    
            String[] nouvelleLigne = {identifiant, String.valueOf(score)};
            tableauScores.add(nouvelleLigne); // Ajoute une nouvelle ligne dans le cas où l'utilisateur n'avait pas de score enregistré auparavant
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    
        // Sérialisation
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.csv"))) { // Ouverture du fichier pour écrire les scores
            for (String[] ligneTableau : tableauScores) { // Parcourt chaque ligne du tableau de scores
                String ligne = String.join(",", ligneTableau); // Convertit le tableau en une ligne de texte avec des virgules comme séparateurs
                writer.write(ligne); // Écrit la ligne dans le document
                writer.newLine(); // Passe à la ligne suivante
            }
            writer.flush(); // Force l'écriture des données dans le fichier
            dispose(); // Ferme la fenêtre d'inscription pour retourner à l'écran de connexion
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}    
 
