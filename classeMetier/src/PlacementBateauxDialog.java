import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlacementBateauxDialog extends JDialog {
    private JTextField porteAvionsTextField;
    private JComboBox<String> porteAvionsOrientationComboBox;
    private JTextField croiseurTextField;
    private JComboBox<String> croiseurOrientationComboBox;
    private JTextField torpilleurTextField;
    private JComboBox<String> torpilleurOrientationComboBox;
    private JTextField sousMarinTextField;
    private JComboBox<String> sousMarinOrientationComboBox;
    private JTextField contreTorpilleurTextField;
    private JComboBox<String> contreTorpilleurOrientationComboBox;

    private boolean confirmed = false;

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getPorteAvionsPosition() {
        return porteAvionsTextField.getText();
    }

    public String getPorteAvionsOrientation() {
        return (String) porteAvionsOrientationComboBox.getSelectedItem();
    }

    public String getCroiseurPosition() {
        return croiseurTextField.getText();
    }

    public String getCroiseurOrientation() {
        return (String) croiseurOrientationComboBox.getSelectedItem();
    }
    public String getTorpilleurPosition() {
        return torpilleurTextField.getText();
    }
    public String getTorpilleurOrientation() {
        return (String) torpilleurOrientationComboBox.getSelectedItem();
    }
    public String getSousMarinPosition() {
        return sousMarinTextField.getText();
    }
    public String getSousMarinOrientation() {
        return (String) sousMarinOrientationComboBox.getSelectedItem();
    }
    public String getContreTorpilleurPosition() {
        return contreTorpilleurTextField.getText();
    }
    public String getContreTorpilleurOrientation() {
        return (String) contreTorpilleurOrientationComboBox.getSelectedItem();
    }

    // Ajoutez les getters pour les autres types de bateaux ici

    public PlacementBateauxDialog(JFrame parent) {
        super(parent, "Placement des bateaux", true);
        setSize(500, 300);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        porteAvionsTextField = new JTextField();
        porteAvionsTextField.setColumns(5);
        porteAvionsOrientationComboBox = new JComboBox<>(new String[]{"Horizontal", "Vertical"});
        croiseurTextField = new JTextField();
        porteAvionsTextField.setColumns(5);
        croiseurOrientationComboBox = new JComboBox<>(new String[]{"Horizontal", "Vertical"});
        torpilleurTextField = new JTextField();
        porteAvionsTextField.setColumns(5);
        torpilleurOrientationComboBox = new JComboBox<>(new String[]{"Horizontal", "Vertical"});
        contreTorpilleurTextField = new JTextField();
        porteAvionsTextField.setColumns(5);
        contreTorpilleurOrientationComboBox = new JComboBox<>(new String[]{"Horizontal", "Vertical"});
        sousMarinTextField = new JTextField();
        porteAvionsTextField.setColumns(5);
        sousMarinOrientationComboBox = new JComboBox<>(new String[]{"Horizontal", "Vertical"});

        String[] labelsText = {
                "Porte-avions (position) :", "Porte-avions (orientation) :",
                "Croiseur (position) :", "Croiseur (orientation) :",
                "Torpilleur (position) :", "Torpilleur (orientation) :",
                "Contre-torpilleur (position) :", "Contre-torpilleur (orientation) :",
                "Sous-marin (position) :", "Sous-marin (orientation) :"
        };
        JTextField[] textFields = {
                porteAvionsTextField, croiseurTextField, torpilleurTextField,
                contreTorpilleurTextField, sousMarinTextField
        };
        JComboBox<String>[] comboBoxes = new JComboBox[]{
                porteAvionsOrientationComboBox, croiseurOrientationComboBox,
                torpilleurOrientationComboBox, contreTorpilleurOrientationComboBox,
                sousMarinOrientationComboBox
        };

        for (int i = 0; i < labelsText.length; i += 2) {
            int row = i / 2;

            gbc.gridx = 0;
            gbc.gridy = row;
            add(new JLabel(labelsText[i]), gbc);

            gbc.gridx = 1;
            gbc.gridy = row;
            gbc.weightx = 1;
            add(textFields[row], gbc);
            gbc.weightx = 0;

            gbc.gridx = 2;
            gbc.gridy = row;
            add(new JLabel(labelsText[i + 1]), gbc);

            gbc.gridx = 3;
            gbc.gridy = row;
            add(comboBoxes[row], gbc);
        }
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérez les positions et les orientations choisies et affectez-les aux bateaux
                String porteAvionsPosition = porteAvionsTextField.getText();
                String porteAvionsOrientation = (String) porteAvionsOrientationComboBox.getSelectedItem();
                String croiseurPosition = croiseurTextField.getText();
                String croiseurOrientation = (String) croiseurOrientationComboBox.getSelectedItem();
                String torpilleurPosition = torpilleurTextField.getText();
                String torpilleurOrientation = (String) torpilleurOrientationComboBox.getSelectedItem();
                String sousMarinPosition = sousMarinTextField.getText();
                String sousMarinOrientation = (String) sousMarinOrientationComboBox.getSelectedItem();
                String contreTorpilleurPosition = contreTorpilleurTextField.getText();
                String contreTorpilleurOrientation = (String) contreTorpilleurOrientationComboBox.getSelectedItem();

                confirmed = true; // Indique que l'utilisateur a confirmé l'entrée des positions et orientations des bateaux
                setVisible(false);
                dispose();
            }
        });
        gbc.gridx = 3;
        gbc.gridy = labelsText.length / 2;
        add(okButton, gbc);

    }
}