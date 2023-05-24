import java.io.Serializable;
import javax.swing.*;

public class Utilisateur implements Serializable {
    private String id; // Identifiant de l'utilisateur
    private String mdp; // Mot de passe de l'utilisateur
    private boolean admin; // Indique si l'utilisateur est un administrateur
    private boolean banni; // Indique si l'utilisateur est banni

    public Utilisateur(String id, String mdp, boolean admin, boolean banni) {
        this.id = id; // Initialise l'identifiant avec la valeur passée en paramètre
        this.mdp = mdp; // Initialise le mot de passe avec la valeur passée en paramètre
        this.admin = admin; // Initialise le statut d'administrateur avec la valeur passée en paramètre
        this.banni = banni; // Initialise le statut de banni avec la valeur passée en paramètre
    }

    public Utilisateur(JTextField id, JPasswordField mdp, boolean admin, boolean banni) {
        this.id = id.getText(); // Obtient le texte saisi dans le champ id et l'assigne à l'identifiant
        this.mdp = new String(mdp.getPassword()); // Obtient le mot de passe saisi dans le champ mdp et l'assigne au mot de passe
        this.admin = admin; // Initialise le statut d'administrateur avec la valeur passée en paramètre
        this.banni = banni; // Initialise le statut de banni avec la valeur passée en paramètre
    }

    public String getId() {
        return id; // Renvoie l'identifiant de l'utilisateur
    }

    public void setId(String id) {
        this.id = id; // Définit l'identifiant de l'utilisateur avec la valeur passée en paramètre
    }

    public String getMdp() {
        return mdp; // Renvoie le mot de passe de l'utilisateur
    }

    public void setMdp(String mdp) {
        this.mdp = mdp; // Définit le mot de passe de l'utilisateur avec la valeur passée en paramètre
    }

    public boolean isAdmin() {
        return admin; // Renvoie true si l'utilisateur est un administrateur, false sinon
    }

    public void setAdmin(boolean admin) {
        this.admin = admin; // Définit le statut d'administrateur de l'utilisateur avec la valeur passée en paramètre
    }

    public boolean isBanni() {
        return banni; // Renvoie true si l'utilisateur est banni, false sinon
    }

    public void setBanni(boolean banni) {
        this.banni = banni; // Définit le statut de banni de l'utilisateur avec la valeur passée en paramètre
    }
}
