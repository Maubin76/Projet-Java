import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class PartieMulti extends JFrame {
    private String theme;
    private String difficulte;
    private int nbQuestions;
    private ArrayList<String[]> tableauQuestions = new ArrayList<>(); // Liste pour stocker les questions
    private int questionsRepondues = 0; // Nombre de questions déjà répondues
    private int[] score = new int[2]; // Tableau pour stocker les scores des deux équipes
    private Random random = new Random(); // Générateur de nombres aléatoires
    private String[] ligne; // Tableau pour stocker une ligne de question
    private JPanel panel; // Panneau principal de l'interface
    private int secondesRestantes; // Nombre de secondes restantes pour répondre à la question
    private Timer questionTimer; // Timer pour le compte à rebours

    public PartieMulti(String theme, String difficulte, int nbQuestions) {
        this.theme = theme;
        this.difficulte = difficulte;
        this.nbQuestions = 2 * nbQuestions; // Le nombre de questions est multiplié par 2 pour prendre en compte les deux équipes

        // Configuration de la fenêtre principale
        setTitle("Partie Solo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 300);
        setResizable(true);
        setLocationRelativeTo(null);

        // Lecture des questions depuis le fichier "questions.csv"
        try (BufferedReader reader = new BufferedReader(new FileReader("questions.csv"))) {
            String ligne;
            ligne = reader.readLine();
            while (ligne != null) {
                String[] ligneTableau = ligne.split(",");
                if (bonneQuestion(ligneTableau)) {
                    tableauQuestions.add(ligneTableau); // Ajout de la question dans la liste si elle est valide
                }
                ligne = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel = new JPanel(new GridBagLayout()); // Création du panneau avec un gestionnaire de disposition
        add(panel); // Ajout du panneau à la fenêtre principale

        poserQuestionSuivante("A"); // Poser la première question pour l'équipe A

        setVisible(true); // Rendre la fenêtre visible
    }

    public boolean bonneQuestion(String[] question) {
        if (question.length >= 7 && question[6].equals(this.theme)) {
            switch (difficulte) {
                case "Facile":
                    if (question[5].equals("1") || question[5].equals("2") || question[5].equals("3") || question[5].equals("4")) {
                        return true; // La question est considérée comme valide si elle a le bon thème et la bonne difficulté
                    }
                    break;
                case "Moyen":
                    if (question[5].equals("4") || question[5].equals("5") || question[5].equals("6") || question[5].equals("7")) {
                        return true;
                    }
                    break;
                case "Difficile":
                    if (question[5].equals("7") || question[5].equals("8") || question[5].equals("9") || question[5].equals("10")) {
                        return true;
                    }
                    break;
                default:
                    return false;
            }
        }
        return false;
    }

    public void poserQuestionSuivante(String equipe) {
        if (questionsRepondues < nbQuestions && !tableauQuestions.isEmpty()) {
            panel.removeAll(); // Supprimer les composants du panneau
            panel.revalidate(); // Actualiser le panneau
            panel.repaint(); // Redessiner le panneau

            int indexAleatoire = random.nextInt(tableauQuestions.size()); // Générer un indice aléatoire
            ligne = tableauQuestions.get(indexAleatoire); // Récupérer la question correspondante
            tableauQuestions.remove(indexAleatoire); // Supprimer la question de la liste
            poseQuestion(ligne, questionsRepondues, equipe); // Poser la question sur le panneau
        } else {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            GridBagConstraints constraints = new GridBagConstraints();

            String gagnant;
            if (score[0] < score[1]) gagnant = "B"; // Déterminer l'équipe gagnante
            else if (score[0] > score[1]) gagnant = "A";
            else gagnant = null;

            JLabel scoreALabel = new JLabel("Score équipe A : " + score[0]); // Afficher le score de l'équipe A
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.ipady = 1;
            constraints.ipadx = 1;
            constraints.gridwidth = 2;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = new Insets(5, 5, 5, 5);
            panel.add(scoreALabel, constraints);

            JLabel scoreBALabel = new JLabel("Score équipe B : " + score[1]); // Afficher le score de l'équipe B
            constraints.gridy = 2;
            panel.add(scoreBALabel, constraints);

            JLabel gagnantLabel;
            if (gagnant == null) gagnantLabel = new JLabel("Egalité"); // Afficher le résultat de la partie
            else if (gagnant.equals("A")) gagnantLabel = new JLabel("L'équipe A gagne !");
            else gagnantLabel = new JLabel("L'équipe B gagne !");
            constraints.gridy = 0;
            panel.add(gagnantLabel, constraints);

            JButton nouvellePartieButton = new JButton("Nouvelle partie"); // Bouton pour commencer une nouvelle partie
            constraints.gridy = 3;
            panel.add(nouvellePartieButton, constraints);

            JButton paramButton = new JButton("Paramètres de partie"); // Bouton pour changer les paramètres de la partie
            constraints.gridx = 0;
            constraints.gridy = 4;
            panel.add(paramButton, constraints);

            nouvellePartieButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Fermer la fenêtre actuelle
                    new PartieMulti(theme, difficulte, nbQuestions); // Lancer une nouvelle partie avec les mêmes paramètres
                }
            });

            paramButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Fermer la fenêtre actuelle
                }
            });

            panel.revalidate();
            panel.repaint();
        }
    }

    public void poseQuestion(String[] ligne, int k, String equipe) {
        GridBagConstraints constraints = new GridBagConstraints();
        secondesRestantes = 20; // Définir le nombre de secondes restantes pour répondre à la question

        ButtonGroup buttonGroup = new ButtonGroup();

        JLabel compteAReboursLabel = new JLabel("Temps restant : " + secondesRestantes + " secondes"); // Afficher le compte à rebours
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(compteAReboursLabel, constraints);

        JLabel equipeLabel = new JLabel("Au tour de l'équipe " + equipe); // Afficher l'équipe en cours
        constraints.gridy = 1;
        panel.add(equipeLabel, constraints);

        JLabel enonceText = new JLabel(ligne[0]); // Afficher l'énoncé de la question
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.ipady = 1;
        constraints.ipadx = 1;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(enonceText, constraints);

        String kString = "Question " + (k + 1) + " : ";
        JLabel numQuestionText = new JLabel(kString); // Afficher le numéro de la question
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(numQuestionText, constraints);

        Integer[] indices = {1, 2, 3, 4};
        Collections.shuffle(Arrays.asList(indices)); // Mélanger les indices de réponse
        JRadioButton prop1Box = new JRadioButton(ligne[indices[0]]); // Afficher la première proposition de réponse
        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(prop1Box, constraints);
        buttonGroup.add(prop1Box);
        JRadioButton prop2Box = new JRadioButton(ligne[indices[1]]); // Afficher la deuxième proposition de réponse
        constraints.gridx = 1;
        panel.add(prop2Box, constraints);
        buttonGroup.add(prop2Box);
        JRadioButton prop3Box = new JRadioButton(ligne[indices[2]]); // Afficher la troisième proposition de réponse
        constraints.gridx = 2;
        panel.add(prop3Box, constraints);
        buttonGroup.add(prop3Box);
        JRadioButton prop4Box = new JRadioButton(ligne[indices[3]]); // Afficher la quatrième proposition de réponse
        constraints.gridx = 3;
        panel.add(prop4Box, constraints);
        buttonGroup.add(prop4Box);

        JButton validerButton = new JButton("Valider"); // Bouton pour valider la réponse
        constraints.gridy = 4;
        panel.add(validerButton, constraints);

        questionTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mettreAJourCompteARebours(compteAReboursLabel, questionTimer, equipe); // Mettre à jour le compte à rebours chaque seconde
            }
        });

        questionTimer.setInitialDelay(0);
        questionTimer.start();

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ajoutScore;

                if (difficulte.equals("Facile")) {
                    ajoutScore = Integer.parseInt(ligne[5]); // Déterminer le score à ajouter en fonction de la difficulté de la question
                } else if (difficulte.equals("Moyen")) {
                    ajoutScore = 2 * Integer.parseInt(ligne[5]);
                } else {
                    ajoutScore = 3 * Integer.parseInt(ligne[5]);
                }

                if (prop1Box.isSelected()) {
                    if (ligne[indices[0]].equals(ligne[1])) {
                        score[0] += ajoutScore; // Ajouter le score à l'équipe A si la réponse est correcte
                    } else {
                        score[1] += ajoutScore; // Ajouter le score à l'équipe B si la réponse est correcte
                    }
                } else if (prop2Box.isSelected()) {
                    if (ligne[indices[1]].equals(ligne[1])) {
                        score[0] += ajoutScore;
                    } else {
                        score[1] += ajoutScore;
                    }
                } else if (prop3Box.isSelected()) {
                    if (ligne[indices[2]].equals(ligne[1])) {
                        score[0] += ajoutScore;
                    } else {
                        score[1] += ajoutScore;
                    }
                } else if (prop4Box.isSelected()) {
                    if (ligne[indices[3]].equals(ligne[1])) {
                        score[0] += ajoutScore;
                    } else {
                        score[1] += ajoutScore;
                    }
                }


                questionTimer.stop(); // Arrêter le compte à rebours
                questionsRepondues++; // Augmenter le nombre de questions répondues

                poserQuestionSuivante(equipe); // Passer à la question suivante
            }
        });

        panel.revalidate();
        panel.repaint();
    }

    public void mettreAJourCompteARebours(JLabel compteAReboursLabel, Timer questionTimer, String equipe) {
        secondesRestantes--; // Réduire le nombre de secondes restantes
        compteAReboursLabel.setText("Temps restant : " + secondesRestantes + " secondes"); // Mettre à jour l'affichage du compte à rebours

        if (secondesRestantes <= 0) {
            questionTimer.stop(); // Arrêter le compte à rebours
            questionsRepondues++; // Augmenter le nombre de questions répondues

            poserQuestionSuivante(equipe); // Passer à la question suivante
        }
    }
}