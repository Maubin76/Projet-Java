import java.io.Serializable;

public class Utilisateur implements Serializable{
    private String id;
    private String mdp;

    public Utilisateur(String id, String mdp){
        this.id = id;
        this.mdp = mdp;

        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
