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

public class PartieSolo extends JFrame {
    private String theme;
    private String difficulte;
    private int nbQuestions;
    private ArrayList<String[]> tableauQuestions = new ArrayList<>();
    private int questionsRepondues = 0;
    private int score = 0;
    private Random random = new Random();
    private String[] ligne;
    private JPanel panel;
    private int secondesRestantes;
    private Timer questionTimer;
    private int scoreMax = 0;
    private String identifiant;

    public PartieSolo(String theme, String difficulte, int nbQuestions, String id) throws UnsupportedAudioFileException, IOException {
        this.identifiant = id;
        this.theme = theme;
        this.difficulte = difficulte;
        this.nbQuestions = nbQuestions;

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

        poserQuestionSuivante();

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

    public void poserQuestionSuivante() throws UnsupportedAudioFileException, IOException {
        if (questionsRepondues < nbQuestions && !tableauQuestions.isEmpty()) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            int indexAleatoire = random.nextInt(tableauQuestions.size());
            ligne = tableauQuestions.get(indexAleatoire);
            tableauQuestions.remove(indexAleatoire);
            poseQuestion(ligne, questionsRepondues);
        } else {

            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            GridBagConstraints constraints = new GridBagConstraints();

            if (score>meilleurScore()){
                changementMeilleurScore();
            }

            JLabel scoreLabel = new JLabel("Score : " + score + "/" + scoreMax);
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.ipady = 1;
            constraints.ipadx = 1;
            constraints.gridwidth = 2;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = new Insets(5, 5, 5, 5);
            panel.add(scoreLabel, constraints);

            JButton nouvellePartieButton = new JButton("Nouvelle partie");
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            panel.add(nouvellePartieButton, constraints);

            JButton paramButton = new JButton("Paramètres de partie");
            constraints.gridx = 1;
            constraints.gridwidth = 1;
            panel.add(paramButton, constraints);

            nouvellePartieButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    try {
                        new PartieSolo(theme, difficulte, nbQuestions, identifiant);
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
                    dispose();
                }
            });

            panel.revalidate();
            panel.repaint();

            AudioInputStream audio;
            if (score==0) {
                audio = AudioSystem.getAudioInputStream(new File("PasOuf.wav"));}
            else {
                audio = AudioSystem.getAudioInputStream(new File("Victoire.wav"));}
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

    public void poseQuestion(String[] ligne, int k) {
        GridBagConstraints constraints = new GridBagConstraints();
        secondesRestantes = 20;

        ButtonGroup buttonGroup = new ButtonGroup();

        JLabel compteAReboursLabel = new JLabel("Temps restant : " + secondesRestantes + " secondes");
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(compteAReboursLabel, constraints);

        JLabel enonceText = new JLabel(ligne[0]);
        constraints.gridx = 1;
        constraints.gridy = 1;
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
        JRadioButton prop1Box = new JRadioButton(ligne[indices[0]]);
        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(prop1Box, constraints);
        buttonGroup.add(prop1Box);
        JRadioButton prop2Box = new JRadioButton(ligne[indices[1]]);
        constraints.gridx = 1;
        panel.add(prop2Box, constraints);
        buttonGroup.add(prop2Box);
        JRadioButton prop3Box = new JRadioButton(ligne[indices[2]]);
        constraints.gridx = 2;
        panel.add(prop3Box, constraints);
        buttonGroup.add(prop3Box);
        JRadioButton prop4Box = new JRadioButton(ligne[indices[3]]);
        constraints.gridx = 3;
        panel.add(prop4Box, constraints);
        buttonGroup.add(prop4Box);

        JButton validerButton = new JButton("Valider");
        constraints.gridy = 3;
        panel.add(validerButton, constraints);

        questionTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mettreAJourCompteARebours(compteAReboursLabel, questionTimer);
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
                    if (prop1Box.isSelected() && ligne[indices[0]].equals(ligne[1])) {
                        score = score + ajoutScore;
                    } else if (prop2Box.isSelected() && ligne[indices[1]].equals(ligne[1])) {
                        score = score + ajoutScore;
                    } else if (prop3Box.isSelected() && ligne[indices[2]].equals(ligne[1])) {
                        score = score + ajoutScore;
                    } else if (prop4Box.isSelected() && ligne[indices[3]].equals(ligne[1])) {
                        score = score + ajoutScore;
                    }

                    scoreMax = scoreMax + ajoutScore;
                    questionsRepondues++;
            
                    // Arrêter le Timer actuel
                    questionTimer.stop();
            
                    try {
                        poserQuestionSuivante();
                    } catch (UnsupportedAudioFileException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });

            panel.revalidate();
            panel.repaint();
        }   

    private void mettreAJourCompteARebours(JLabel compteAReboursLabel, Timer questionTimer) {
        secondesRestantes--;
    
        if (secondesRestantes >= 0) {
            compteAReboursLabel.setText("Temps restant : " + secondesRestantes + " secondes");
        } else {
            compteAReboursLabel.setText("Temps écoulé !");
            questionTimer.stop();
            questionsRepondues++;
            try {
                poserQuestionSuivante();
            } catch (UnsupportedAudioFileException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public int meilleurScore(){
        try (BufferedReader reader = new BufferedReader(new FileReader("score.csv"))) { // Ouverture fichier qui contient les logs
            String ligne; // Ligne du document qui est en train d'être parcourue
            while ((ligne = reader.readLine()) != null) { // Balayage du document ligne par ligne jusqu'à la fin
                String[] ligneTableau = ligne.split(","); // Séparation de la ligne en cases avec le caractère ','
                if (ligneTableau[0].equals(this.identifiant)){
                    return Integer.parseInt(ligneTableau[1]);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return 0;
    }

    public void changementMeilleurScore(){
        // Désérialisation
        ArrayList<String[]> tableauScores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("scores.csv"))) { // Ouverture fichier qui contient les scores
            String ligne; // Ligne du document qui est en train d'être parcourue
            while ((ligne = reader.readLine()) != null) { // Balayage du document ligne par ligne jusqu'à la fin
                String[] ligneTableau = ligne.split(","); // Séparation de la ligne en cases avec le caractère ','
                if (ligneTableau[0].equals(identifiant) && score>Integer.parseInt(ligneTableau[1])){
                    ligneTableau[1]=String.valueOf(score);
                }
                tableauScores.add(ligneTableau); // Ajoute une ligne au tableau de String[]
            }
            String[] nouvelleLigne = {identifiant,String.valueOf(score)};
            tableauScores.add(nouvelleLigne); // Ajoute une nouvelle ligne dans le cas où l'utilisateur n'avait pas de score enregistré auparavant
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // Sérialisation
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.csv"))) {
            for (String[] ligneTableau : tableauScores) {
                String ligne = String.join(",", ligneTableau); // Convertit le tableau en une ligne de texte avec des virgules comme séparateurs
                writer.write(ligne); // Ecris la ligne dans le document
                writer.newLine(); // Retour à la ligne
            }
            writer.flush(); // Force l'écriture des données dans le fichier
            dispose(); // Ferme la fenêtre d'inscription pour retourner à l'écran de connexion
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
 