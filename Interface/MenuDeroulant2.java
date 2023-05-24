import javax.swing.*;
import java.awt.event.*;

public class MenuDeroulant2 extends JFrame implements ActionListener{
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
    

    public MenuDeroulant2(){
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
    }

    public void actionPerformed(ActionEvent e) {
        this.selectedTheme = (String) themeComboBox.getSelectedItem();
        this.selectedDifficulte = (String) difficulteComboBox.getSelectedItem();
        this.selectedNombre = (int) nombreSpinner.getValue();
    }

    public static void main(String[] args) {
        new MenuDeroulant2();
    }
}