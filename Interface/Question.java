import java.io.*;
import java.util.ArrayList;

public class Question {
    private String theme;
    private String enonce;
    private String p1;
    private String p2;
    private String p3;
    private String p4;
    private String niveau;
    private ArrayList<String[]> tableauQuestions = new ArrayList<>(); // Création du tableau qui va servir à désérialiser le fichier contenant les logs

    public Question(String enonce, String p1, String p2, String p3, String p4, String niveau, String theme) throws FileNotFoundException{
        this.enonce = enonce;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.theme = theme;
        this.niveau = niveau;

        try (BufferedReader reader = new BufferedReader(new FileReader("questions.csv"))) {
            String ligne; // Déplacer la déclaration de la variable 'ligne' ici
            ligne = reader.readLine(); // Lire la première ligne du fichier
            while (ligne != null) {
                String[] ligneTableau = ligne.split(",");
                tableauQuestions.add(ligneTableau);
                ligne = reader.readLine(); // Lire la ligne suivante
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void soumettre(){
        String[] questionAjoutee = {enonce, p1, p2, p3, p4, niveau, theme};
        tableauQuestions.add(questionAjoutee);
        serialisation();
    }

    public void serialisation(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("questions.csv"))) { // Ouverture du fichier connexion.csv
            for (String[] ligneTableau : tableauQuestions) { // Parcours du tableau ligne pas ligne
                String ligne = String.join(",", ligneTableau); // Convertit le tableau en une ligne de texte avec des virgules comme séparateurs
                writer.write(ligne); // Ecris la ligne dans le document
                writer.newLine(); // Retour à la ligne
            }
            writer.flush(); // Force l'écriture des données dans le fichier
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean valide(){
        if (enonce.length()!=0 &&
            p1.length()!=0 &&
            p2.length()!=0 &&
            p3.length()!=0 &&
            p4.length()!=0 ){
                return true;
            }
        else {
            return false;
        }
    }


    // Getters et setters pour tous les attributs de la classe
    public String getTheme() {
        return theme;}
    public void setTheme(String theme) {
        this.theme = theme;}
    public String getEnonce() {
        return enonce;}
    public void setEnonce(String enonce) {
        this.enonce = enonce;}
    public String getP1() {
        return p1;}
    public void setP1(String p1) {
        this.p1 = p1;}
    public String getP2() {
        return p2;}
    public void setP2(String p2) {
        this.p2 = p2;}
    public String getP3() {
        return p3;}
    public void setP3(String p3) {
        this.p3 = p3;}
    public String getP4() {
        return p4;}
    public void setP4(String p4) {
        this.p4 = p4;}
    public String getNiveau() {
        return niveau;}
    public void setNiveau(String niveau) {
        this.niveau = niveau;}
}
