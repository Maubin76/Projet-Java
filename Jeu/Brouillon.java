import java.util.Scanner;

public enum Niveau{
    Facile, Normal, Difficile
}
public enum Theme{
    Histoire, Geographie
}


public class Question{
    private String enonce;
    private byte difficulte;
    private Theme theme;
    private String choix[]= String[4];
    private String reponse;
    //constructeur
    public Question(int difficulte, Theme theme){
        this.difficulte=difficulte;
        this.theme=theme;
        try (Scanner scanner=new Scanner(System.in)){
            System.out.println("Ennonce de la question : ");
            enonce = scanner.nextLine();
            System.out.println("Reponse : ");
            reponse = scanner.nextLine();
            choix[0]=reponse;
            System.out.println("2eme proposition : ");
            choix[1] = scanner.nextLine();
            System.out.println("3eme proposition : ");
            choix[2] = scanner.nextLine();
            System.out.println("4eme proposition : ");
            choix[3] = scanner.nextLine();
        }
    }
}

public class Partie{
    protected int nbParticipant;
    protected Niveau niveau;
    protected Theme theme[];
    protected int nbrQuestion; 
    protected Question listeQuestion[]= Question[nbrQuestion];
    public Partie(Niveau niveau, int nbrQuestion){
        //utiliser random parmie les questions, selon le niveau de difficulté
    }

    public void choixTheme(){}
}

public class PartieSolo extends Partie{
    
    public PartieSolo(Niveau niveau, int nbrQuestion) {
        super(niveau, nbrQuestion);
        //TODO Auto-generated constructor stub
    }
}

public class PartieMulti extends Partie{
    
    public PartieMulti(Niveau niveau, int nbrQuestion) {
        super(niveau, nbrQuestion);
        //TODO Auto-generated constructor stub
    }
}

public class Personne{
    private String login;
    private String mdp;
    private bool banni;
    public Personne(){
        try (Scanner scanner=new Scanner(System.in)){
            System.out.println("Entrez un identifiant :");
            login = scanner.nextLine();
            System.out.println("Entrez un mot de passe :");
            mdp = scanner.nextLine();
        }
    }

    public void setBanni(bool banni){this.banni=banni;}
}

public class Participant extends Personne{
    private String pseudo;
    static int score;
    private bool equipe;
}

public class Administrateur extends Personne{
    public void creerQuestion(){
        final byte difficulte;
        final Theme theme;
        Question(difficulte,theme);
    }
    public void suspendreCompte(){}
    public void supprimerCompte(){}
    public void afficherInformation(Personne joueur){}
}

/* 
histoire-geo
10 niveau de difficulte
1-4=facile 4-7=normal 7-10=difficile

solo = seul contre le quizz
par equipe= affrontement de 2 équipes
*/