import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PlateauJeuAdversaire extends JFrame {
    private int[][] grilleAdversaire;
    private JPanel gridPanel;
    String chemin = "/Users/ateli/OneDrive/Documents/cours supérieur/B2/C#/labo final/Naval Battle Assets/Naval Battle Assets/";
    private String porteAvionsPosition;
    private String porteAvionsOrientation;
    private String croiseurPosition;
    private String croiseurOrientation;
    private String torpilleurPosition;
    private String torpilleurOrientation;
    private String sousMarinPosition;
    private String sousMarinOrientation;
    private String contreTorpilleurPosition;
    private String contreTorpilleurOrientation;
    ImageIcon explosionIcon = new ImageIcon(chemin + "explosion.png");
    ImageIcon porteAvionIcon = new ImageIcon(chemin + "PorteAvion-gauche.png");
    ImageIcon torpilleurIcon = new ImageIcon(chemin + "Torpilleur-gauche.png");
    ImageIcon croiseurIcon = new ImageIcon(chemin + "Croiseur-gauche.png");
    ImageIcon contreTorpilleurIcon = new ImageIcon(chemin + "ContreTorpilleur-gauche.png");

    ImageIcon sousMarinIcon = new ImageIcon(chemin + "SousMarin-gauche.png");
    ImageIcon porteAvionIconV = new ImageIcon(chemin + "PorteAvion-vertical.png");
    ImageIcon torpilleurIconV = new ImageIcon(chemin + "Torpilleur-vertical.png");
    ImageIcon croiseurIconV = new ImageIcon(chemin + "Croiseur-vertical.png");
    ImageIcon contreTorpilleurIconV = new ImageIcon(chemin + "ContreTorpilleur-vertical.png");

    ImageIcon sousMarinIconV = new ImageIcon(chemin + "SousMarin-vertical.png");
    ImageIcon missedShotIcon = new ImageIcon(chemin + "Rate.png");


    public PlateauJeuAdversaire() {
        setTitle("Bataille Navale - Plateau de l'adversaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);

        grilleAdversaire = new int[10][10];
        for (int i = 0; i < grilleAdversaire.length; i++) {
            for (int j = 0; j < grilleAdversaire[i].length; j++) {
                grilleAdversaire[i][j] = 0;
            }
        }
        // Placement automatique des bateaux
        placerBateau(5); // porte-avions
        placerBateau(4); // croiseur
        placerBateau(3); // contre-torpilleur
        placerBateau(3); // sous-marin
        placerBateau(2); // torpilleur

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

        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // Créez un JPanel pour contenir les grilles
        JPanel gridsPanel = new JPanel(new GridLayout(1, 2));

        // Créer un JPanel pour la grille de gauche (bateaux et explosions pour les tirs ennemis)
        JPanel leftGrid = createGridPanel(chemin + "oceangrid_final.png", true);
        gridsPanel.add(leftGrid);

        // Créer un JPanel pour la grille de droite (explosions pour les tirs du joueur)
        JPanel rightGrid = createGridPanel(chemin + "radargrid_final.png", false);
        gridsPanel.add(rightGrid);

        // Ajouter les grilles au centre
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        contentPane.add(gridsPanel, gridBagConstraints);

        // Ajoutez une JList à droite de l'écran
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Element A");
        listModel.addElement("Element B");
        listModel.addElement("Element C");
        JList<String> myList = new JList<>(listModel);

        // Définir la largeur préférée de la JList
        int listWidth = 200; // Remplacez cette valeur par la largeur souhaitée
        myList.setPreferredSize(new Dimension(listWidth, myList.getPreferredSize().height));

        // Ajouter la JList à droite
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        contentPane.add(myList, gridBagConstraints);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void genererPositionEtOrientation() {
        Random random = new Random();
        boolean positionsValides = false;

        while (!positionsValides) {
            porteAvionsPosition = (char) (random.nextInt(10) + 'A') + Integer.toString(random.nextInt(10) + 1);
            porteAvionsOrientation = random.nextBoolean() ? "vertical" : "horizontal";
            croiseurPosition = (char) (random.nextInt(10) + 'A') + Integer.toString(random.nextInt(10) + 1);
            croiseurOrientation = random.nextBoolean() ? "vertical" : "horizontal";
            torpilleurPosition = (char) (random.nextInt(10) + 'A') + Integer.toString(random.nextInt(10) + 1);
            torpilleurOrientation = random.nextBoolean() ? "vertical" : "horizontal";
            sousMarinPosition = (char) (random.nextInt(10) + 'A') + Integer.toString(random.nextInt(10) + 1);
            sousMarinOrientation = random.nextBoolean() ? "vertical" : "horizontal";
            contreTorpilleurPosition = (char) (random.nextInt(10) + 'A') + Integer.toString(random.nextInt(10) + 1);
            contreTorpilleurOrientation = random.nextBoolean() ? "vertical" : "horizontal";

            positionsValides = verifierPositions();
        }
    }
    private boolean verifierPositions() {
        if (!estPositionValide(porteAvionsPosition, porteAvionsOrientation, 5)) {
            return false;
        }
        if (!estPositionValide(croiseurPosition, croiseurOrientation, 4)) {
            return false;
        }
        if (!estPositionValide(torpilleurPosition, torpilleurOrientation, 3)) {
            return false;
        }
        if (!estPositionValide(sousMarinPosition, sousMarinOrientation, 3)) {
            return false;
        }
        if (!estPositionValide(contreTorpilleurPosition, contreTorpilleurOrientation, 2)) {
            return false;
        }
        return true;
    }
    private boolean estPositionValide(String position, String orientation, int longueur) {
        char lettrePosition = position.charAt(0);
        int chiffrePosition = Integer.parseInt(position.substring(1));

        // Transforme la lettre en indice de tableau
        int lettreIndice = lettrePosition - 'A';

        if (orientation.equals("horizontal")) {
            for (int i = 0; i < longueur; i++) {
                if (chiffrePosition + i > 9 || grilleAdversaire[lettreIndice][chiffrePosition + i] != 0) {
                    return false;
                }
            }
        } else { // orientation verticale
            for (int i = 0; i < longueur; i++) {
                if (lettreIndice + i > 9 || grilleAdversaire[lettreIndice + i][chiffrePosition] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private String genererPosition() {
        Random random = new Random();
        char lettre = (char) ('A' + random.nextInt(10));
        int chiffre = random.nextInt(10);
        return String.valueOf(lettre) + chiffre;
    }

    private String genererOrientation() {
        Random random = new Random();
        return random.nextBoolean() ? "horizontal" : "vertical";
    }

    private void placerBateau(int longueur) {
        String position;
        String orientation;
        do {
            position = genererPosition();
            orientation = genererOrientation();
        } while (!estPositionValide(position, orientation, longueur));
        switch (longueur) {
            case 5: // porte-avions
                porteAvionsPosition = position;
                porteAvionsOrientation = orientation;
                break;
            case 4: // croiseur
                croiseurPosition = position;
                croiseurOrientation = orientation;
                break;
            case 3: // contre-torpilleur ou sous-marin
                if (sousMarinPosition == null) {
                    sousMarinPosition = position;
                    sousMarinOrientation = orientation;
                } else {
                    contreTorpilleurPosition = position;
                    contreTorpilleurOrientation = orientation;
                }
                break;
            case 2: // torpilleur
                torpilleurPosition = position;
                torpilleurOrientation = orientation;
                break;
        }

        char lettrePosition = position.charAt(0);
        int chiffrePosition = Integer.parseInt(position.substring(1));
        int lettreIndice = lettrePosition - 'A';

        if (orientation.equals("horizontal")) {
            for (int i = 0; i < longueur; i++) {
                grilleAdversaire[lettreIndice][chiffrePosition + i] = 1;
            }
        } else {
            for (int i = 0; i < longueur; i++) {
                grilleAdversaire[lettreIndice + i][chiffrePosition] = 1;
            }
        }
    }




    private JPanel createGridPanel(String imagePath, boolean isLeftGrid) {
        ImageIcon backgroundImage = new ImageIcon(imagePath);
        gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, this);
                char lettreAvion = porteAvionsPosition.charAt(0);
                String chiAvion = porteAvionsPosition.substring(1);
                int chiffreAvion = Integer.parseInt(chiAvion);

                char lettreCroiseur = croiseurPosition.charAt(0);
                String chiCroiseur = croiseurPosition.substring(1);
                int chiffreCroiseur = Integer.parseInt(chiCroiseur);

                char lettreTorpilleur = torpilleurPosition.charAt(0);
                String chiTorpilleur = torpilleurPosition.substring(1);
                int chiffreTorpilleur = Integer.parseInt(chiTorpilleur);

                char lettreContreTorpilleur = contreTorpilleurPosition.charAt(0);
                String chiContreTorpilleur = contreTorpilleurPosition.substring(1);
                int chiffreContreTorpilleur = Integer.parseInt(chiContreTorpilleur);

                char lettreSousMarin = sousMarinPosition.charAt(0);
                String chiSousMarin = sousMarinPosition.substring(1);
                int chiffreSousMarin = Integer.parseInt(chiSousMarin);
                int test;

                if (isLeftGrid) {
                    test = lettreAvion - 'A'+1;
                    if(porteAvionsOrientation=="Horizontal")
                        switchchiffre(chiffreAvion,test, g, porteAvionIcon);
                    else
                        switchchiffre(chiffreAvion,test, g, porteAvionIconV);

                    test = lettreCroiseur - 'A'+1;
                    if(croiseurOrientation=="Horizontal")
                        switchchiffre(chiffreCroiseur,test, g, croiseurIcon);
                    else
                        switchchiffre(chiffreCroiseur,test, g, croiseurIconV);

                    test = lettreContreTorpilleur - 'A'+1;
                    if(contreTorpilleurOrientation=="Horizontal")
                        switchchiffre(chiffreContreTorpilleur,test, g, contreTorpilleurIcon);
                    else
                        switchchiffre(chiffreContreTorpilleur,test, g, contreTorpilleurIconV);

                    test = lettreTorpilleur - 'A'+1;
                    if(torpilleurOrientation=="Horizontal")
                        switchchiffre(chiffreTorpilleur,test, g, torpilleurIcon);
                    else
                        switchchiffre(chiffreTorpilleur,test, g, torpilleurIconV);

                    test = lettreSousMarin - 'A'+1;
                    if(sousMarinOrientation=="Horizontal")
                        switchchiffre(chiffreSousMarin,test, g, sousMarinIcon);
                    else
                        switchchiffre(chiffreSousMarin,test, g, sousMarinIconV);


                }


//                    g.drawImage(ContreTorpilleurIcon.getImage(), 250, 310, this);
//                    // Dessiner l'explosion sur la grille de gauche pour un tir ennemi réussi (exemple)
//                    g.drawImage(explosionIcon.getImage(), 31, 62, this);
//
//                    g.drawImage(explosionIcon.getImage(), 280, 310, this); //une case fait 31 pixel de heut et de large
//                    g.drawImage(explosionIcon.getImage(),125 , 125, this);
//                    g.drawImage(explosionIcon.getImage(),310 , 31, this);

                else {
                    // Dessiner l'explosion sur un bateau ennemi sur la grille de droite (exemple)
                    g.drawImage(explosionIcon.getImage(), 150, 150, this);

                    // Dessiner l'explosion dans l'eau pour un tir raté sur la grille de droite (exemple)
                    g.drawImage(missedShotIcon.getImage(), 200, 200, this);
                }
            }
        };

        gridPanel.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));

        return gridPanel;
    }

    private void switchchiffre(int chiffre, int lettre,Graphics g,ImageIcon bateau )
    {
        g.drawImage(bateau.getImage(), lettre*31, chiffre*31, gridPanel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlateauJeu());
    }
}
