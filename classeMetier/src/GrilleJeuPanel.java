import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class GrilleJeuPanel extends JPanel {
    private final Image backgroundImage;
    private final Image boatFragmentImage;

    public GrilleJeuPanel(Image backgroundImage, Image boatFragmentImage) {
        this.backgroundImage = backgroundImage;
        this.boatFragmentImage = boatFragmentImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);

        // Dessinez les fragments de bateaux en fonction de leurs positions sur la grille
        // Exemple: g.drawImage(boatFragmentImage, x, y, this);
    }
}