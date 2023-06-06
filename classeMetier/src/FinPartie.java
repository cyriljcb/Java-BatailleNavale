import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinPartie extends JFrame {
    private String gagnant;

    public FinPartie(String gagnant) {
        super("Fin de la partie");
        this.gagnant = gagnant;

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des boutons
        JButton nouvellePartie = new JButton("Nouvelle partie");
        JButton quitter = new JButton("Quitter");

        // Ajout des listeners d'action
        nouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour démarrer une nouvelle partie
                // Par exemple, en ouvrant le MenuPrincipal :
                new MenuPrincipal().setVisible(true);
                dispose(); // ferme l'écran actuel
            }
        });

        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour quitter l'application
                System.exit(0);
            }
        });

        // Ajout des boutons au layout
        setLayout(new BorderLayout());

        // Ajout d'un JLabel pour afficher le nom du gagnant
        JLabel gagnantLabel = new JLabel(gagnant + " a gagné la partie!", SwingConstants.CENTER);
        gagnantLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(gagnantLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nouvellePartie);
        buttonPanel.add(quitter);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
