import java.lang.Boolean;

public class Utilisateur {
    protected String login;
    protected String mdp;
    protected Boolean banni;

    public utilisateur(){
        try (Scanner scanner=new Scanner(System.in)){
            System.out.println("Entrez un identifiant :");
            login = scanner.nextLine();
            System.out.println("Entrez un mot de passe :");
            mdp = scanner.nextLine();
        }
    }
}
