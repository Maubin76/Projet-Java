import javax.swing.*;
import java.awt.event.*;

public class ParamPartie extends JFrame{
    /* 
    private JFrame frame;
    private String[] themes = {"Histoire","Géographie","Art","Sport","Maths","Musique","Jeux-vidéos","Cinéma"};
    private String[] difficultes = {"Facile", "Moyen", "Difficile"};
    private JLabel themeLabel;
    private JLabel difficulteLabel;
    private JLabel nombreLabel;
    private JComboBox<String> themeComboBox;
    private JComboBox<String> difficulteComboBox;
    private JSpinner nombreSpinner;
    private JButton validerButton;

    private String selectedTheme;
    private String selectedDifficulte;
    private int selectedNombre;

    
    */


    

    public ParamPartie(){
        /* 
        this.frame=new JFrame("Menu déroulant");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(400, 400);

        // Choix du thème
        this.themeLabel = new JLabel("Choix du thème :");
        this.themeComboBox = new JComboBox<>(themes);
        this.themeLabel.setBounds(50, 20, 200, 30);
        this.themeComboBox.setBounds(50, 50, 200, 30);

        // Choix de la difficulté
        this.difficulteLabel = new JLabel("Choix de la difficulté :");
        this.difficulteComboBox = new JComboBox<>(difficultes);
        this.difficulteLabel.setBounds(50, 80, 200, 30);
        this.difficulteComboBox.setBounds(50, 100, 200, 30);

        // Choix du nombre de questions
        this.nombreLabel = new JLabel("Nombre :");
        this.nombreSpinner = new JSpinner(new SpinnerNumberModel(5, 5, 10, 1)); //SpinnerNumberModel(initial_value, min, max, step)
        this.nombreLabel.setBounds(50, 130, 200, 30);
        this.nombreSpinner.setBounds(50, 150, 200, 30);

        // Ajout d'un gestionnaire d'événements aux menus déroulants
        this.validerButton = new JButton("Valider");
        this.validerButton.setBounds(100, 200, 100, 30);
        validerButton.addActionListener(this);

        frame.add(this.themeComboBox);
        frame.add(this.themeLabel);
        frame.add(this.difficulteComboBox);
        frame.add(this.difficulteLabel);
        frame.add(this.nombreSpinner);
        frame.add(this.nombreLabel);
        frame.add(this.validerButton);
        frame.setLayout(null);
        frame.setVisible(true);
        */
        // Création de la fenêtre
        JFrame frame = new JFrame("Choix paramêtres");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Choix du thème
        String[] themes = {"Histoire","Géographie","Art","Sport","Maths","Musique","Jeux-vidéos","Cinéma"};
        JLabel themeLabel = new JLabel("Choix du thème :");
        JComboBox<String> themeComboBox = new JComboBox<>(themes);
        themeLabel.setBounds(50, 20, 200, 30);
        themeComboBox.setBounds(50, 50, 200, 30);

        // Choix de la difficulté
        String[] difficultes = {"Facile", "Moyen", "Difficile"};
        JLabel difficulteLabel = new JLabel("Choix de la difficulté :");
        JComboBox<String> difficulteComboBox = new JComboBox<>(difficultes);
        difficulteLabel.setBounds(50, 80, 200, 30);
        difficulteComboBox.setBounds(50, 100, 200, 30);

        // Choix du nombre de questions
        JLabel nombreLabel = new JLabel("Nombre de questions :");
        JSpinner nombreSpinner = new JSpinner(new SpinnerNumberModel(5, 5, 10, 1)); //SpinnerNumberModel(initial_value, min, max, step)
        nombreLabel.setBounds(50, 130, 200, 30);
        nombreSpinner.setBounds(50, 150, 200, 30);
        

        // Ajout d'un gestionnaire d'événements aux menus déroulants
        JButton validerButton = new JButton("Valider");
        validerButton.setBounds(100, 200, 100, 30);
        validerButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                String selectedTheme = (String) themeComboBox.getSelectedItem();
                String selectedDifficulte = (String) difficulteComboBox.getSelectedItem();
                int selectedNombre = (int) nombreSpinner.getValue();
            }
        });

        // Ajout des composants à la fenêtre
        frame.add(themeComboBox);
        frame.add(themeLabel);
        frame.add(difficulteComboBox);
        frame.add(difficulteLabel);
        frame.add(nombreSpinner);
        frame.add(nombreLabel);
        frame.add(validerButton);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ParamPartie();
    }
}