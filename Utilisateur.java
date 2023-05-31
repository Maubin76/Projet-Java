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
     * Booléen privé indiquant si l'utilisateur (true si oui, false sinon).
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
    public String getId() {return id;}
    /**
     * Setter de l'attribut id.
     *
     * @param id. Chaine de caractère indiquant le nouvel identifiant de l'utilisateur.
     */
    public void setId(String id) {this.id = id;}
    /**
     * Getter de l'attribut mdp.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getMdp() {return mdp;}
    /**
     * Setter de l'attribut mdp.
     *
     * @param mdp. Chaine de caractère indiquant le nouveau mot de passe de l'utilisateur.
     */
    public void setMdp(String mdp) {this.mdp = mdp;}
    /**
     * Getter de l'attribut admin.
     *
     * @return Le statut d'admin de l'utilisateur.
     */
    public boolean isAdmin() {return admin;}
    /**
     * Setter de l'attribut admin.
     *
     * @param admin. Booléen indiquant le nouveau statur de l'utilisateur (joueur si admin=false, administrateur sinon).
     */
    public void setAdmin(boolean admin) {this.admin = admin;}
    /**
     * Getter de l'attribut banni.
     *
     * @return Le statut banni de l'utilisateur.
     */
    public boolean isBanni() {return banni;}
    /**
     * Setter de l'attribut banni.
     *
     * @param banni. Booléen indiquant le nouveau statut banni de l'utilisateur (ban si banni=true, unban sinonS).
     */
    public void setBanni(boolean banni) {this.banni = banni;}
}
