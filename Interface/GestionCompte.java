import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class GestionCompte extends JFrame{

    private ArrayList<String[]> tableauLogs = new ArrayList<>(); // Création du tableau
    private String identifiant;

    public GestionCompte(JTextField id) throws IOException{
        identifiant = id.getText();
        // Désérialisation : On stocke toutes les données des utilisateurs dans un arrayList de String[]
        try (BufferedReader reader = new BufferedReader(new FileReader("connexion.csv"))) { // Ouverture fichier qui contient les logs
            String ligne; // Ligne du document qui est en train d'être parcourue
            while ((ligne = reader.readLine()) != null) { // Balayage du document ligne par ligne jusqu'à la fin
                String[] ligneTableau = ligne.split(","); // Séparation de la ligne en cases avec le caractère ','
                tableauLogs.add(ligneTableau); // Ajoute une ligne au tableau de String[]
            }
        }
    }

    public void banCompte(){
        boolean dejaBanni = false;
        for (int i = 0; i<tableauLogs.size(); i++){
            String[] ligne = tableauLogs.get(i);
            if (ligne[3].equals("false") && ligne[0].equals(identifiant)){
                dejaBanni = false;
                ligne[3] = "true";
            }
            else if (ligne[3].equals("true") && ligne[0].equals(identifiant)){
                dejaBanni = true;
            }
        }
        serialisation();
        if (!dejaBanni) {
            JOptionPane.showMessageDialog(GestionCompte.this, identifiant + " a bien été banni");}
        else {
            JOptionPane.showMessageDialog(GestionCompte.this, identifiant + " est déja banni");}
    }

    public void debanCompte(){
        boolean dejaDebanni = false;
        for (int i = 0; i<tableauLogs.size(); i++){
            String[] ligne = tableauLogs.get(i);
            if (ligne[3].equals("true") && ligne[0].equals(identifiant)){
                dejaDebanni = false;
                ligne[3] = "false";
            }
            else if (ligne[3].equals("false") && ligne[0].equals(identifiant)){
                dejaDebanni = true;
            }
        }
        serialisation();
        if (!dejaDebanni) {
            JOptionPane.showMessageDialog(GestionCompte.this, identifiant + " a bien été débanni");}
        else {
            JOptionPane.showMessageDialog(GestionCompte.this, identifiant + " n'est pas banni");}
    }

    public void suppCompte(){
        for (int i = 0; i<tableauLogs.size(); i++){
            String[] ligne = tableauLogs.get(i);
            if (ligne[0].equals(identifiant)){
                tableauLogs.remove(i);
            }
        }
        JOptionPane.showMessageDialog(GestionCompte.this,"Le compte de " + identifiant + " a été supprimé");
        serialisation();
    }

    public void serialisation(){
        // Sérialisation : On réécrit tout le tableau dynamique dans le fichier connexion.csv en écrasant les données déjà présentes
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("connexion.csv"))) {
            for (String[] ligneTableau : tableauLogs) {
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

