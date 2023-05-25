import Tir.IA;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MenuPrincipal extends JFrame {
    private Tir.IA.Difficulte difficulte;
    PlacementBateauxDialog placementBateauxDialog;
    public MenuPrincipal() {
        setTitle("Bataille Navale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Utilisez BackgroundImagePanel comme contentPane
        String imagePath = "/Users/ateli/IdeaProjects/untitled/classeMetier/Ressources/ImageFond2.jpg";
        ImagePanel ImagePanel = new ImagePanel(imagePath);
        setContentPane(ImagePanel);
        // Utilisez GridBagLayout pour positionner les éléments
        GridBagLayout layout = new GridBagLayout();
        ImagePanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        // composants ici

        // Créez une barre de menus
        JMenuBar menuBar = new JMenuBar();

        // Ajoutez un menu "Quitter"
        JMenu optionsMenu = new JMenu("Quitter");
        menuBar.add(optionsMenu);

        // Ajoutez un menu "Option"
        JMenu quitMenu = new JMenu("Option");
        menuBar.add(quitMenu);

        // Ajoutez la barre de menus à la fenêtre
        setJMenuBar(menuBar);

        // Ajoutez un bouton avec une taille 1,5 fois plus grande
        JButton myButton = new JButton("Cliquez ici");
        myButton.setFont(myButton.getFont().deriveFont(myButton.getFont().getSize() * 1.5f));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        ImagePanel.add(myButton, constraints);

        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Continuez avec l'affichage du plateau de jeu
                PlateauJeu plateauJeu = new PlateauJeu();
                plateauJeu.setVisible(true);

                // Cachez le menu principal
                MenuPrincipal.this.setVisible(false);
            }
        });

        // Ajoutez un menu déroulant
        JComboBox<IA.Difficulte> myComboBox1 = new JComboBox<>(IA.Difficulte.values());
        // Doubler la taille du JComboBox
        Dimension dimension = myComboBox1.getPreferredSize();
        dimension.width *= 2;
        myComboBox1.setPreferredSize(dimension);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        ImagePanel.add(myComboBox1, constraints);

        myComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulte = (IA.Difficulte) myComboBox1.getSelectedItem();
            }
        });
        // Ajoutez une Jlist à droite de l'écran
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Element A");
        listModel.addElement("Element B");
        listModel.addElement("Element C");
        JList<String> myList = new JList<>(listModel);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 1.0; // Utilisez un poids pour pousser la JList vers la droite
        constraints.anchor = GridBagConstraints.EAST;
        ImagePanel.add(myList, constraints);

        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menuPrincipal = new MenuPrincipal();
        });

    }
}