import java.io.Serializable;
import javax.swing.*;

/**
 * La classe Utilisateur représente un utilisateur du système.
 * Elle implémente l'interface Serializable pour permettre la sérialisation des objets.
 */
public class Utilisateur implements Serializable {
    /**
     * Chaine de caractère privée indiquant l'identifiant de l'utilisateur
     */
    private String id; 
    /**
     * Chaine de caractère privée indiquant le mot de passe de l'utilisateur
     */
    private String mdp; 
    /**
     * Booléen privé indiquant l'utilisateur est un admin (true) ou simplement un joueur (false).
     */
    private boolean admin; 
    /**
     * Bouléen privé indiquant si l'utilisateur (true si oui, false sinon).
     */
    private boolean banni;

    /**
     * Constructeur de la classe Utilisateur.
     *
     * @param id     L'identifiant de l'utilisateur.
     * @param mdp    Le mot de passe de l'utilisateur.
     * @param admin  Le statut d'administrateur de l'utilisateur.
     * @param banni  Le statut de banni de l'utilisateur.
     */
    public Utilisateur(String id, String mdp, boolean admin, boolean banni) {
        this.id = id; // Initialise l'identifiant avec la valeur passée en paramètre
        this.mdp = mdp; // Initialise le mot de passe avec la valeur passée en paramètre
        this.admin = admin; // Initialise le statut d'administrateur avec la valeur passée en paramètre
        this.banni = banni; // Initialise le statut de banni avec la valeur passée en paramètre
    }

    /**
     * Constructeur de la classe Utilisateur prenant des composants d'interface graphique.
     * 
     * @param id     Le champ de texte (JTextField) contenant l'identifiant de l'utilisateur.
     * @param mdp    Le champ de mot de passe (JPasswordField) contenant le mot de passe de l'utilisateur.
     * @param admin  Le statut d'administrateur de l'utilisateur.
     * @param banni  Le statut de banni de l'utilisateur.
     */
    public Utilisateur(JTextField id, JPasswordField mdp, boolean admin, boolean banni) {
        this.id = id.getText(); // Obtient le texte saisi dans le champ id et l'assigne à l'identifiant
        this.mdp = new String(mdp.getPassword()); // Obtient le mot de passe saisi dans le champ mdp et l'assigne au mot de passe
        this.admin = admin; // Initialise le statut d'administrateur avec la valeur passée en paramètre
        this.banni = banni; // Initialise le statut de banni avec la valeur passée en paramètre
    }

    /**
     * Getter de l'attribut id.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public String getId() {
        return id; // Renvoie l'identifiant de l'utilisateur
    }

    /**
     * Setter de l'attribut id.
     */
    public void setId(String id) {
        this.id = id; // Définit l'identifiant de l'utilisateur avec la valeur passée en paramètre
    }

    /**
     * Getter de l'attribut mdp.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getMdp() {
        return mdp; // Renvoie le mot de passe de l'utilisateur
    }

    /**
     * Setter de l'attribut mdp.
     */
    public void setMdp(String mdp) {
        this.mdp = mdp; // Définit le mot de passe de l'utilisateur avec la valeur passée en paramètre
    }

    /**
     * Getter de l'attribut admin.
     *
     * @return Le statut d'admin de l'utilisateur.
     */
    public boolean isAdmin() {
        return admin; // Renvoie true si l'utilisateur est un administrateur, false sinon
    }

    /**
     * Setter de l'attribut admin.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin; // Définit le statut d'administrateur de l'utilisateur avec la valeur passée en paramètre
    }

    /**
     * Getter de l'attribut banni.
     *
     * @return Le statut banni de l'utilisateur.
     */
    public boolean isBanni() {
        return banni; // Renvoie true si l'utilisateur est banni, false sinon
    }

    /**
     * Setter de l'attribut banni.
     */
    public void setBanni(boolean banni) {
        this.banni = banni; // Définit le statut de banni de l'utilisateur avec la valeur passée en paramètre
    }
}
