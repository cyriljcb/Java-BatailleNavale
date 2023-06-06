import javax.swing.*;
import java.awt.*;


public class ImagePanel extends javax.swing.JPanel {
    private Image backgroundImage;
    String imagePath = "/Users/ateli/IdeaProjects/untitled/classeMetier/Ressources/ImageFond2.jpg";

    public ImagePanel(String imagePath) {
        try {
            backgroundImage = new ImageIcon(imagePath).getImage();
            //  backgroundImage = new ImageIcon(getClass().getClassLoader().getResource(imagePath)).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            System.out.println("Image chargée avec succès.");
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        } else {
            System.out.println("Erreur lors du chargement de l'image.");
        }

    }
}

