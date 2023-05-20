import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Serialisation {
    public static void main(String[] args) {
        Utilisateur prs1 = new Utilisateur("ut1", "mdp1");
        try {
            FileOutputStream fileOut = new FileOutputStream("connexion.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(prs1);
            out.close();
            fileOut.close();
            System.out.println("La liste des identifiants a été sérialisée avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
