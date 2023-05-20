import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Serialisation {
    public static void main(String[] args) {
        Utilisateur prs1 = new Utilisateur("ut2", "mdp2");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("connexion.csv"))) {
            String ligne = prs1.getId() + "," + prs1.getMdp();
            writer.write(ligne);
            System.out.println("Les données ont été sérialisées avec succès dans le fichier CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
