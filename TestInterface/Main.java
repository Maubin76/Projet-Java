import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ConnexionInterface interfaceConnexion = new ConnexionInterface();
                interfaceConnexion.setVisible(true);
            }
        });
    }
} 
