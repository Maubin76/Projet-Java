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
    private ArrayList<String[]> tableauQuestions = new ArrayList<>();
    private int questionsRepondues = 0;
    private int[] score = new int[2];
    private Random random = new Random();
    private String[] ligne;
    private JPanel panel;
    private int secondesRestantes;
    private Timer questionTimer; // Déclarer questionTimer en tant que membre de classe
    private int[] scoreMax = new int[2];

    public PartieMulti(String theme, String difficulte, int nbQuestions) {
        this.theme = theme;
        this.difficulte = difficulte;
        this.nbQuestions = 2*nbQuestions;

        // Paramètres de la fenêtre
        setTitle("Partie Solo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 300);
        setResizable(true);
        setLocationRelativeTo(null);

        // Désérialisation des questions qui appartiennent au thème et à la difficulté choisis
        try (BufferedReader reader = new BufferedReader(new FileReader("questions.csv"))) {
            String ligne;
            ligne = reader.readLine();
            while (ligne != null) {
                String[] ligneTableau = ligne.split(",");
                if (bonneQuestion(ligneTableau)) {
                    tableauQuestions.add(ligneTableau);
                }
                ligne = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel = new JPanel(new GridBagLayout());
        add(panel);

        poserQuestionSuivante("A");

        setVisible(true);
    }

    public boolean bonneQuestion(String[] question) {
        if (question.length >= 7 && question[6].equals(this.theme)) {
            switch (difficulte) {
                case "Facile":
                    if (question[5].equals("1") || question[5].equals("2") || question[5].equals("3") || question[5].equals("4")) {
                        return true;
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
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            int indexAleatoire = random.nextInt(tableauQuestions.size());
            ligne = tableauQuestions.get(indexAleatoire);
            tableauQuestions.remove(indexAleatoire);
            poseQuestion(ligne, questionsRepondues, equipe);
        } else {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            GridBagConstraints constraints = new GridBagConstraints();

            String gagnant;
            if (score[0]<score[1]) gagnant = "B";
            else if (score[0]>score[1]) gagnant = "A";
            else gagnant = null;

            JLabel scoreALabel = new JLabel("Score équipe A : " + score[0] + "/" + scoreMax[0]);
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.ipady = 1;
            constraints.ipadx = 1;
            constraints.gridwidth = 2;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = new Insets(5, 5, 5, 5);
            panel.add(scoreALabel, constraints);

            JLabel scoreBALabel = new JLabel("Score équipe B : " + score[1] + "/" + scoreMax[1]);
            constraints.gridy = 2;
            panel.add(scoreBALabel, constraints);

            JLabel gagnantLabel;
            if (gagnant==null) gagnantLabel = new JLabel("Egalité");
            else if (gagnant.equals("A")) gagnantLabel = new JLabel("L'équipe A gagne !");
            else gagnantLabel = new JLabel("L'équipe B gagne !");
            constraints.gridy = 0;
            panel.add(gagnantLabel, constraints);

            JButton nouvellePartieButton = new JButton("Nouvelle partie");
            constraints.gridy = 3;
            panel.add(nouvellePartieButton, constraints);

            JButton paramButton = new JButton("Paramètres de partie");
            constraints.gridx = 0;
            constraints.gridy = 4;
            panel.add(paramButton, constraints);

            nouvellePartieButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new PartieMulti(theme, difficulte, nbQuestions);
                }
            });

            paramButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            panel.revalidate();
            panel.repaint();
        }
    }

    public void poseQuestion(String[] ligne, int k, String equipe) {
        GridBagConstraints constraints = new GridBagConstraints();
        secondesRestantes = 20;

        JLabel compteAReboursLabel = new JLabel("Temps restant : " + secondesRestantes + " secondes");
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(compteAReboursLabel, constraints);

        JLabel equipeLabel = new JLabel("Au tour de l'équipe " + equipe);
        constraints.gridy = 1;
        panel.add(equipeLabel, constraints);

        JLabel enonceText = new JLabel(ligne[0]);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.ipady = 1;
        constraints.ipadx = 1;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(enonceText, constraints);

        String kString = "Question " + (k + 1) + " : ";
        JLabel numQuestionText = new JLabel(kString);
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(numQuestionText, constraints);

        Integer[] indices = {1, 2, 3, 4};
        Collections.shuffle(Arrays.asList(indices));
        JCheckBox prop1Box = new JCheckBox(ligne[indices[0]]);
        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(prop1Box, constraints);
        JCheckBox prop2Box = new JCheckBox(ligne[indices[1]]);
        constraints.gridx = 1;
        panel.add(prop2Box, constraints);
        JCheckBox prop3Box = new JCheckBox(ligne[indices[2]]);
        constraints.gridx = 2;
        panel.add(prop3Box, constraints);
        JCheckBox prop4Box = new JCheckBox(ligne[indices[3]]);
        constraints.gridx = 3;
        panel.add(prop4Box, constraints);

        JButton validerButton = new JButton("Valider");
        constraints.gridy = 4;
        panel.add(validerButton, constraints);

        questionTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mettreAJourCompteARebours(compteAReboursLabel, questionTimer, equipe);
            }
        });

        questionTimer.setInitialDelay(0);
        questionTimer.start();
            

            validerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int ajoutScore;

                    if (difficulte.equals("Facile")){
                        ajoutScore = Integer.parseInt(ligne[5]);
                    }
                    else if (difficulte.equals("Moyen")){
                        ajoutScore = Integer.parseInt(ligne[5]) - 3;
                    }
                    else{
                        ajoutScore = Integer.parseInt(ligne[5]) - 3*2;
                    }

                    int x;
                    if (equipe.equals("A")) x = 0;
                    else x = 1;

                    if (prop1Box.isSelected() && ligne[indices[0]].equals(ligne[1])) {
                        score[x] = score[x] + ajoutScore;
                    } else if (prop2Box.isSelected() && ligne[indices[1]].equals(ligne[1])) {
                        score[x] = score[x] + ajoutScore;
                    } else if (prop3Box.isSelected() && ligne[indices[2]].equals(ligne[1])) {
                        score[x] = score[x] + ajoutScore;
                    } else if (prop4Box.isSelected() && ligne[indices[3]].equals(ligne[1])) {
                        score[x] = score[x] + ajoutScore;
                    }

                    scoreMax[x] = scoreMax[x] + ajoutScore;
                    questionsRepondues++;
            
                    // Arrêter le Timer actuel
                    questionTimer.stop();
                    
                    if (equipe.equals("A")) poserQuestionSuivante("B");
                    else poserQuestionSuivante("A");
                }
            });

            panel.revalidate();
            panel.repaint();
        }   

    private void mettreAJourCompteARebours(JLabel compteAReboursLabel, Timer questionTimer, String equipe) {
        secondesRestantes--;
    
        if (secondesRestantes >= 0) {
            compteAReboursLabel.setText("Temps restant : " + secondesRestantes + " secondes");
        } else {
            compteAReboursLabel.setText("Temps écoulé !");
            questionTimer.stop();
            questionsRepondues++;
            if (equipe.equals("A")) poserQuestionSuivante("B");
            else poserQuestionSuivante("A");
        }
    }
}
 