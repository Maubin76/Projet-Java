public class Question{

    private String enonce;
    private byte difficulte;
    private Theme theme;
    private String choix[]= String[4];
    private String reponse;
    private Boolean dejaposée = false;

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