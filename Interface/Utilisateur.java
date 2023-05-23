import java.io.Serializable;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Utilisateur implements Serializable{
    private String id;
    private String mdp;
    private boolean admin;
    private boolean banni;

    public Utilisateur(String id, String mdp, boolean admin, boolean banni){
        this.id = id;
        this.mdp = mdp;
        this.admin = admin;
        this.banni = banni;
    }

    public Utilisateur(JTextField id, JPasswordField mdp, boolean admin, boolean banni){
        this.id = id.getText();
        this.mdp = new String(mdp.getPassword());
        this.admin = admin;
        this.banni = banni;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isBanni() {
        return banni;
    }

    public void setBanni(boolean banni) {
        this.banni = banni;
    }

    
}
