import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class GestionCompte extends JFrame{

    private ArrayList<String[]> tableauLogs = new ArrayList<>(); // Création du tableau qui sert à désérialiser le fichier connexion.csv contenant les logs
    private String identifiant; // Identifiant de l'utilisateur dont on cherche à gérer le compte

    public GestionCompte(JTextField id) throws IOException{
        identifiant = id.getText(); // Affectation de l'identifiant à l'attribut

        // Désérialisation : On stocke toutes les données des utilisateurs dans un arrayList de String[]
        try (BufferedReader reader = new BufferedReader(new FileReader("connexion.csv"))) { // Ouverture fichier qui contient les logs
            String ligne; // Ligne du document qui est en train d'être parcourue
            while ((ligne = reader.readLine()) != null) { // Balayage du document ligne par ligne jusqu'à la fin
                String[] ligneTableau = ligne.split(","); // Séparation de la ligne en cases avec le caractère ','
                tableauLogs.add(ligneTableau); // Ajoute une ligne au tableau de String[]
            }
        }
    }

    // Méthode qui permet de bannir un utilisateur
    public void banCompte(){
        boolean dejaBanni = false; // Booléen qui sert à savoir si le joueur était déja banni
        for (int i = 0; i<tableauLogs.size(); i++){ // Parcours du tableau ligne par ligne
            String[] ligne = tableauLogs.get(i); // Récupération de la ligne en cours de parcours
            if (ligne[3].equals("false") && ligne[0].equals(identifiant)){ // Cas où l'utilisateur cherché n'est pas banni
                dejaBanni = false; 
                ligne[3] = "true"; // Il devient banni dans le tableau
            }
            else if (ligne[3].equals("true") && ligne[0].equals(identifiant)){ // Cas où l'utilisateur cherché est déjà banni
                dejaBanni = true;
            }
        }
        serialisation(); // Sérialisation du tableau modifié dans le fichier connexion.csv

        // Gestion du pop-up en fonction de si le joueur était déjà banni ou non
        if (!dejaBanni) {
            JOptionPane.showMessageDialog(GestionCompte.this, identifiant + " a bien été banni");}
        else {
            JOptionPane.showMessageDialog(GestionCompte.this, identifiant + " est déja banni");}
    }

    // Méthode qui permet de débannir un utilisateur
    public void debanCompte(){
        boolean dejaDebanni = false; // Booléen qui sert à savoir si le joueur était déja débanni
        for (int i = 0; i<tableauLogs.size(); i++){ // Parcours du tableau ligne par ligne
            String[] ligne = tableauLogs.get(i); // Récupération de la ligne en cours de parcours
            if (ligne[3].equals("true") && ligne[0].equals(identifiant)){ // Cas où l'utilisateur cherché est banni
                dejaDebanni = false;
                ligne[3] = "false"; // Il est débanni
            }
            else if (ligne[3].equals("false") && ligne[0].equals(identifiant)){ // Cas où l'utilisateur cherché est déjà débanni
                dejaDebanni = true;
            }
        }
        serialisation(); // Sérialisation du tableau modifié dans le fichier connexion.csv

        // Gestion du pop-up en fonction de si le joueur était déjà débanni ou non
        if (!dejaDebanni) {
            JOptionPane.showMessageDialog(GestionCompte.this, identifiant + " a bien été débanni");}
        else {
            JOptionPane.showMessageDialog(GestionCompte.this, identifiant + " n'est pas banni");}
    }

    // Méthode qui permet de supprimer un compte 
    public void suppCompte(){
        for (int i = 0; i<tableauLogs.size(); i++){  // Parcours du tableau ligne par ligne
            String[] ligne = tableauLogs.get(i); // Récupération de la ligne en cours de parcours
            if (ligne[0].equals(identifiant)){ // Cas où on trouve la ligne où ce trouve le compte cherché
                tableauLogs.remove(i); // Suppression de la ligne (et donc du compte dans le tableau)
            }
        }
        JOptionPane.showMessageDialog(GestionCompte.this,"Le compte de " + identifiant + " a été supprimé"); // Pop-up de confirmation de suppression
        serialisation(); // Sérialisation du tableau modifié dans le fichier connexion.csv
    }

    // Méthode de sérialisation du tableau dans le fichier connexion.csv contenant les logs
    public void serialisation(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("connexion.csv"))) { // Ouverture du fichier connexion.csv
            for (String[] ligneTableau : tableauLogs) { // Parcours du tableau ligne pas ligne
                String ligne = String.join(",", ligneTableau); // Convertit le tableau en une ligne de texte avec des virgules comme séparateurs
                writer.write(ligne); // Ecris la ligne dans le document
                writer.newLine(); // Retour à la ligne
            }
            writer.flush(); // Force l'écriture des données dans le fichier
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

