import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Deserialisation {
    public static void main(String[] args) {
        Utilisateur prs1 = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("connexion.csv"))) {
            String ligne = reader.readLine();
            if (ligne != null) {
                String[] attributs = ligne.split(",");
                if (attributs.length == 2) {
                    String nom = attributs[0];
                    String motDePasse = attributs[1];
                    prs1 = new Utilisateur(nom, motDePasse);
                    System.out.println("Les données ont été désérialisées avec succès à partir du fichier CSV.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (prs1 != null) {
            System.out.println("Identifiant : " + prs1.getId());
            System.out.println("Mot de passe : " + prs1.getMdp());
            System.out.println("--------------------");
        }
    }
}
