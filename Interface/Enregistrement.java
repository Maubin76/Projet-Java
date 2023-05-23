import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enregistrement extends JFrame{

    public Enregistrement() {
        dispose();
        // Configuration de la fenêtre
        setTitle("Inscription"); // Définit le titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Définit l'action de fermeture de la fenêtre
        setSize(500, 400); // Définit la taille de la fenêtre (largeur, hauteur)
        setResizable(true); // Autorise le redimensionnement de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        JPanel panel = new JPanel(new GridBagLayout()); // Crée un panneau avec un gestionnaire de disposition GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints(); // Crée des contraintes pour le placement des composants dans la grille

        JLabel id = new JLabel("Identifiant : "); // Composant qui demande l'identifiant
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 0; // Première ligne
        constraints.ipady = 1; // Largeur de la case
        constraints.ipadx = 1; // Longueur de la case
        constraints.gridwidth = 1; // Étend pas le composant sur plusieurs colonnes
        constraints.anchor = GridBagConstraints.WEST; // Aligne le composant à gauche de sa case
        constraints.insets = new Insets(5, 5, 5, 5); // Espacement entre les composants
        panel.add(id, constraints); // Ajoute le bouton de connexion au panneau

        JTextField idField = new JTextField(20);
        constraints.gridx = 1; // Deuxième colonne
        panel.add(idField, constraints); // Ajoute le bouton de connexion au panneau

        JLabel mdp = new JLabel("Mot de passe : "); // Composant qui demande le mot de passe
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 1; // Deuxième ligne
        panel.add(mdp, constraints); // Ajoute le bouton de connexion au panneau

        JPasswordField passwordField = new JPasswordField(20);
        constraints.gridx = 1; // Deuxième colonne
        panel.add(passwordField, constraints); // Ajoute le bouton de connexion au panneau

        JLabel mdpConfirmation = new JLabel("Confirmation du mot de passe : "); // Composant qui demande le mot de passe
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 2; // Troisième ligne
        panel.add(mdpConfirmation, constraints); // Ajoute le bouton de connexion au panneau

        JPasswordField passwordFieldConfirmation = new JPasswordField(20);
        constraints.gridx = 1; // Deuxième colonne
        panel.add(passwordFieldConfirmation, constraints); // Ajoute le bouton de connexion au panneau

        JLabel code = new JLabel("Code administrateur : "); // Composant qui demande le mot de passe
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 3; // Quatrième ligne
        panel.add(code, constraints); // Ajoute le bouton de connexion au panneau

        JTextField codeField = new JTextField(20);
        constraints.gridx = 1; // Deuxième colonne
        panel.add(codeField, constraints); // Ajoute le bouton de connexion au panneau

        JButton annulerButton = new JButton("Annuler");
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 4; // Cinquième ligne
        panel.add(annulerButton, constraints);

        JButton enregistrerButton = new JButton("Enregistrer");
        constraints.gridx = 1; // Deuxième colonne
        panel.add(enregistrerButton, constraints);

        add(panel); // Ajoute le panneau
        setVisible(true); // Rend la fenêtre visible

        annulerButton.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Annuler'
            @Override
            public void actionPerformed(ActionEvent e){
                dispose(); // Ferme la fenêtre pour revenir à l'écran de connexion
            }
        });

        enregistrerButton.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Enregistrer'
            @Override
            public void actionPerformed(ActionEvent e){

                if (Arrays.equals(passwordFieldConfirmation.getPassword(), passwordField.getPassword())) { // Vérification de la concordance des mots de passe saisis

                    // Désérialisation : On stocke toutes les données des utilisateurs dans un arrayList de String[]
                    ArrayList<String[]> tableauLogs = new ArrayList<>(); // Création du tableau
                    try (BufferedReader reader = new BufferedReader(new FileReader("connexion.csv"))) { // Ouverture fichier qui contient les logs
                        String ligne; // Ligne du document qui est en train d'être parcourue
                        while ((ligne = reader.readLine()) != null) { // Balayage du document ligne par ligne jusqu'à la fin
                            String[] ligneTableau = ligne.split(","); // Séparation de la ligne en cases avec le caractère ','
                            tableauLogs.add(ligneTableau); // Ajoute une ligne au tableau de String[]
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(Enregistrement.this, "Erreur de la lecture !");
                    }
                    String[] nouveauCompte = new String[3]; // Tableau de String pour les données en cours d'enregistrement
                    nouveauCompte[0] = idField.getText(); // Case 1 : identifiant
                    nouveauCompte[1] = new String(passwordField.getPassword()); // Case 2 : Mot de passe
                    if (codeField.getText().equals("AdminQUIZZ")){ // Code pour pouvoir créer un compte administrateur
                        nouveauCompte[2] = "true";} // Case 3 : administrateur
                    else {
                        nouveauCompte[2] = "false";} // Case 3 : pas administrateur 
                    tableauLogs.add(nouveauCompte); // Ajout du nouveau compte dans le tableau dynamique de String[]

                    // Sérialisation : On réécrit tout le tableau dynamique dans le fichier connexion.csv en écrasant les données déjà présentes
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("connexion.csv"))) {
                        for (String[] ligneTableau : tableauLogs) {
                            String ligne = String.join(",", ligneTableau); // Convertit le tableau en une ligne de texte avec des virgules comme séparateurs
                            writer.write(ligne); // Ecris la ligne dans le document
                            writer.newLine(); // Retour à la ligne
                        }
                        writer.flush(); // Force l'écriture des données dans le fichier
                        dispose(); // Ferme la fenêtre d'inscription pour retourner à l'écran de connexion
                        JOptionPane.showMessageDialog(Enregistrement.this, "Enregistrement réussi !");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Enregistrement.this, "Erreur lors de l'enregistrement !");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(Enregistrement.this, "Erreur dans la saisie du mot de passe !");
                }
            }
        });
    }
}
