import java.io.*;
import java.util.ArrayList;

/**
 * Classe représentant une question.
 */
public class Question {
    /**
     * Chaine de caractère indiquant le thème de la question.
     * prend sa valeur parmis {"Histoire","Géographie","Art","Sport","Maths","Musique","Jeux-vidéos"}.
     */
    private String theme;

    /**
     * Chaine de caractère indiquant l'énoncé de la question.
     */
    private String enonce;

    /**
     * Chaine de caractère indiquant la première proposition de la question.
     * La première proposition correspond toujours à la bonne réponse.
     */
    private String p1;

    /**
     * Chaine de caractère indiquant la deuxième proposition de la question.
     */
    private String p2;

    /**
     * Chaine de caractère indiquant la troisième proposition de la question.
     */
    private String p3;

    /**
     * Chaine de caractère indiquant la quatrième proposition de la question.
     */
    private String p4;

    /**
     * Chaine de caractère indiquant le niveau de difficulté de la question.
     * Peut prendre une valeur entre 1 et 10 inclus.
     */
    private String niveau;

    private ArrayList<String[]> tableauQuestions = new ArrayList<>(); // Création du tableau qui va servir à désérialiser le fichier contenant les logs

    /**
     * Constructeur de la classe Question.
     * Initialise une question avec son énoncé, les propositions de réponses, le niveau de difficulté et le thème.
     *
     * @param enonce  L'énoncé de la question.
     * @param p1      La première proposition de réponse (soit la bonne réponse).
     * @param p2      La deuxième proposition de réponse.
     * @param p3      La troisième proposition de réponse.
     * @param p4      La quatrième proposition de réponse.
     * @param niveau  Le niveau de difficulté de la question.
     * @param theme   Le thème de la question.
     * @throws FileNotFoundException Si le fichier de questions n'est pas trouvé.
     */
    public Question(String enonce, String p1, String p2, String p3, String p4, String niveau, String theme) throws FileNotFoundException {
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

    /**
     * Méthode permettant de soumettre une question.
     * Ajoute la question au tableau de questions et effectue la sérialisation.
     */
    public void soumettre() {
        String[] questionAjoutee = {enonce, p1, p2, p3, p4, niveau, theme};
        tableauQuestions.add(questionAjoutee);
        serialisation();
    }

    /**
     * Méthode de sérialisation du tableau de questions dans le fichier de questions.
     * Les modifications apportées au tableau de questions sont enregistrées dans le fichier de questions (questions.csv).
     */
    public void serialisation() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("questions.csv"))) { // Ouverture du fichier connexion.csv
            for (String[] ligneTableau : tableauQuestions) { // Parcours du tableau ligne par ligne
                String ligne = String.join(",", ligneTableau); // Convertit le tableau en une ligne de texte avec des virgules comme séparateurs
                writer.write(ligne); // Écrit la ligne dans le document
                writer.newLine(); // Passe à la ligne suivante
            }
            writer.flush(); // Force l'écriture des données dans le fichier
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Méthode permettant de vérifier si la question est valide.
     * Une question est valide si tous les attributs de la question sont non vides.
     *
     * @return true si la question est valide, false sinon.
     */
    public boolean valide() {
        if (enonce.length() != 0 &&
                p1.length() != 0 &&
                p2.length() != 0 &&
                p3.length() != 0 &&
                p4.length() != 0) {
            return true;
        } else {
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
