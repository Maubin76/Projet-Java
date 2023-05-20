import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Deserialisation {
    public static void main(String[] args) {
        Utilisateur prs1 = new Utilisateur(null, null);

        // Désérialisation de la liste
        try {
            FileInputStream fileIn = new FileInputStream("connexion.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            prs1 = (Utilisateur) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("La liste des identifiants a été désérialisée avec succès.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Utilisation de la liste désérialisée
        if (prs1 != null) {
            System.out.println("Identifiant : " + prs1.getId());
            System.out.println("Mot de passe : " + prs1.getMdp());
            System.out.println("--------------------");
        }
    }
}
