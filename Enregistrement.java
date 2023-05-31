import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe représentant la fenêtre d'inscription.
 */
public class Enregistrement extends JFrame{
    /**
     * Tableau privé de chaines de caractère.
     * Création du tableau qui va servir à désérialiser le fichier contenant les logs
     */
    private ArrayList<String[]> tableauLogs = new ArrayList<>(); 
    /**
     * chaine de caractère privée correspondant à l'identifiant du compte en cours de création.
     */
    private String identifiant; 
    
    /**
     * Constructeur de la classe Enregistrement.
     * Initialise et configure la fenêtre d'inscription.
     */
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
        panel.add(id, constraints); // Ajoute le texte au panneau

        JTextField idField = new JTextField(20); // Composant pour saisir l'identifiant
        constraints.gridx = 1; // Deuxième colonne
        panel.add(idField, constraints); // Ajoute la zone de saisie au panneau

        JLabel mdp = new JLabel("Mot de passe : "); // Composant qui demande le mot de passe
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 1; // Deuxième ligne
        panel.add(mdp, constraints); // Ajoute le texte au panneau

        JPasswordField passwordField = new JPasswordField(20); // Composant pour saisir le mot de passe
        constraints.gridx = 1; // Deuxième colonne
        panel.add(passwordField, constraints); // Ajoute la zone de saisie au panneau

        JLabel mdpConfirmation = new JLabel("Confirmation du mot de passe : "); // Composant qui demande la confirmation de mot de passe
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 2; // Troisième ligne
        panel.add(mdpConfirmation, constraints); // Ajoute le texte au panneau

        JPasswordField passwordFieldConfirmation = new JPasswordField(20); // Composant de saisie de la confirmation de mot de passe
        constraints.gridx = 1; // Deuxième colonne
        panel.add(passwordFieldConfirmation, constraints); // Ajoute la zone de saisie au panneau

        JLabel code = new JLabel("Code administrateur : "); // Composant qui demande le code nécessaire pour s'inscrire en tant qu'administrateur
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 3; // Quatrième ligne
        panel.add(code, constraints); // Ajoute le texte au panneau

        JTextField codeField = new JTextField(20); // Composant de saisie du code d'administrateur
        constraints.gridx = 1; // Deuxième colonne
        panel.add(codeField, constraints); // Ajoute la zone de saisie au panneau

        JButton annulerButton = new JButton("Annuler"); // Bouton d'annulation d'inscription
        constraints.gridx = 0; // Première colonne
        constraints.gridy = 4; // Cinquième ligne
        panel.add(annulerButton, constraints); // Ajoute le bouton au panneau

        JButton enregistrerButton = new JButton("Enregistrer"); // Bouton d'enregistrement des informations pour créer le compte
        constraints.gridx = 1; // Deuxième colonne
        panel.add(enregistrerButton, constraints); //  Ajoute le bouton au panneau

        add(panel); // Ajoute le panneau
        setVisible(true); // Rend la fenêtre visible

        // Désérialisation : On stocke toutes les données des utilisateurs dans un arrayList de String[]
        try (BufferedReader reader = new BufferedReader(new FileReader("connexion.csv"))) { // Ouverture fichier qui contient les logs
            String ligne; // Ligne du document qui est en train d'être parcourue
            while ((ligne = reader.readLine()) != null) { // Balayage du document ligne par ligne jusqu'à la fin
                String[] ligneTableau = ligne.split(","); // Séparation de la ligne en cases avec le caractère ','
                tableauLogs.add(ligneTableau); // Ajoute une ligne au tableau de String[]
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // Retour à la page de connexion
        annulerButton.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Annuler'
            @Override
            public void actionPerformed(ActionEvent e){
                dispose(); // Ferme la fenêtre pour revenir à l'écran de connexion
            }
        });

        /* Ajout du gestionnaire d'événements pour le bouton d'enregistrement
        Objectif : vérifier que les informations saisies sont valides pour le compte soit créé (mdp assez long, identifiant inexistant),
                            la qualité d'administrateur du compte
                    Il faut ensuite créer le compte : l'ajouter au fichier .csv qui contient les logs */
        enregistrerButton.addActionListener(new ActionListener() { // Déclenche à l'appuie du bouton 'Enregistrer'
            @Override
            public void actionPerformed(ActionEvent e){
                identifiant = idField.getText();
                if (identifiantExistant()) {
                    JOptionPane.showMessageDialog(Enregistrement.this, "Cet identifiant existe déja");
                }
                else if (passwordField.getPassword().length<3){
                    JOptionPane.showMessageDialog(Enregistrement.this, "Mot de passe trop court");
                }
                else if (Arrays.equals(passwordFieldConfirmation.getPassword(), passwordField.getPassword()) && !identifiantExistant() && !(passwordField.getPassword().length<3)) { // Vérification de la concordance des mots de passe saisis
                    String[] nouveauCompte = new String[4]; // Tableau de String pour les données en cours d'enregistrement
                    nouveauCompte[0] = idField.getText(); // Case 1 : identifiant
                    nouveauCompte[1] = new String(passwordField.getPassword()); // Case 2 : Mot de passe
                    if (codeField.getText().equals("AdminQUIZZ")){ // Code pour pouvoir créer un compte administrateur
                        nouveauCompte[2] = "true";} // Case 3 : administrateur
                    else {
                        nouveauCompte[2] = "false";} // Case 3 : pas administrateur 
                    nouveauCompte[3] = "false";
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
    
    /**
     * Vérifie si un identifiant existe déjà.
     *
     * @return true si l'identifiant existe, false sinon.
     */
    public boolean identifiantExistant(){
        for (String[] ligneTableau : tableauLogs){ // Parcours le tableau qui contient les logs
            if (ligneTableau[0].equals(identifiant)){ // Test si l'identifiant est déja dans le tableau
                return true; // Renvoie true s'il est déjà dans le tableau
            }
        }
        return false; // Renvoie false sinon
    }
}
