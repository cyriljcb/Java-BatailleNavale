import Joueur.*;
import Singleton.Partie;
import Bateau.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import Bateau.Bateau;
public class PlateauJeu extends JFrame {
    private List<BateauToucheListener> bateauToucheListeners = new ArrayList<>();

    public void addBateauToucheListener(BateauToucheListener listener) {
        bateauToucheListeners.add(listener);
    }
    Graphics g;
    int[][] grille = new int[10][10];
    private Partie partie;
    private List<Bateau> bateaux;
    //String chemin = "/Naval Battle Assets/";
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
    private PlateauJeuAdversaire plateauAdversaire;
    ImageIcon explosionIcon = new ImageIcon(chemin + "explosion.png");
    ImageIcon porteAvionIcon = new ImageIcon(chemin + "PorteAvion-gauche.png");
    ImageIcon torpilleurIcon = new ImageIcon(chemin + "Torpilleur-gauche.png");
    ImageIcon croiseurIcon = new ImageIcon(chemin + "Croiseur-gauche.png");
    ImageIcon contreTorpilleurIcon = new ImageIcon(chemin + "ContreTorpilleur-gauche.png");
    PorteAvion porteAvions = new PorteAvion("A1",true);
    Croiseur croiseur = new Croiseur("A2",true);
    ContreTorpilleur contreTorpilleur = new ContreTorpilleur("A3",true);
    SousMarin sousMarin = new SousMarin("A4",true);
    Torpilleur torpilleur = new Torpilleur("A5",true);
    ImageIcon sousMarinIcon = new ImageIcon(chemin + "SousMarin-gauche.png");
    ImageIcon porteAvionIconV = new ImageIcon(chemin + "PorteAvion-vertical.png");
    ImageIcon torpilleurIconV = new ImageIcon(chemin + "Torpilleur-vertical.png");
    ImageIcon croiseurIconV = new ImageIcon(chemin + "Croiseur-vertical.png");
    ImageIcon contreTorpilleurIconV = new ImageIcon(chemin + "ContreTorpilleur-vertical.png");

    ImageIcon sousMarinIconV = new ImageIcon(chemin + "SousMarin-vertical.png");
    ImageIcon missedShotIcon = new ImageIcon(chemin + "Rate.png");
    private JPanel gridPanel;
    private JTextField tirTextField;

    public PlateauJeu() {
        this.partie = Partie.getInstance();
        setTitle("Bataille Navale - Plateau de jeu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600); // Ajustez la taille de la fenêtre pour qu'elle soit suffisamment grande
//        int[][] grille = this.partie.getGrille(1);
//
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                grille[i][j] = 0;
//            }
//        }
        //ajouterBateauxALaPartie();
        tirTextField = new JTextField();
        // Affichez la boîte de dialogue pour la saisie des positions et de l'orientation des bateaux
        PlacementBateauxDialog placementDialog = new PlacementBateauxDialog(this);
        placementDialog.setVisible(true);
        plateauAdversaire = new PlateauJeuAdversaire((JoueurHumain) Partie.getInstance().getJoueur(), (JoueurIA) Partie.getInstance().getIA(),this);
        plateauAdversaire.setVisible(false);

        // Si l'utilisateur a confirmé, stockez les positions et les orientations des bateaux
        if (placementDialog.isConfirmed()) {
            porteAvionsPosition = placementDialog.getPorteAvionsPosition();
            porteAvionsOrientation = placementDialog.getPorteAvionsOrientation();
            croiseurPosition = placementDialog.getCroiseurPosition();
            croiseurOrientation = placementDialog.getCroiseurOrientation();
            torpilleurPosition = placementDialog.getTorpilleurPosition();
            torpilleurOrientation = placementDialog.getTorpilleurOrientation();
            sousMarinPosition = placementDialog.getSousMarinPosition();
            sousMarinOrientation = placementDialog.getSousMarinOrientation();
            contreTorpilleurPosition = placementDialog.getContreTorpilleurPosition();
            contreTorpilleurOrientation = placementDialog.getContreTorpilleurOrientation();
            placerBateau(porteAvions, porteAvionsPosition, porteAvionsOrientation);
            placerBateau(croiseur, croiseurPosition, croiseurOrientation);
            placerBateau(torpilleur, torpilleurPosition, torpilleurOrientation);
            placerBateau(sousMarin, sousMarinPosition, sousMarinOrientation);
            placerBateau(contreTorpilleur, contreTorpilleurPosition, contreTorpilleurOrientation);
        } else {
            System.exit(0);
        }
        // Créez une barre de menus
        JMenuBar menuBar = new JMenuBar();
// Ajoutez un menu "Option"
        JMenu optionsMenu = new JMenu("Option");
        menuBar.add(optionsMenu);

// Ajoutez un menu "Quitter"
        JMenu quitMenu = new JMenu("Quitter");
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
        JPanel tirPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

// Créer le JLabel
        JLabel tirLabel = new JLabel("Position du Tir :");

// Configurer le JTextField
        tirTextField.setPreferredSize(new Dimension(100, 20)); // Réglez la taille du JTextField selon vos besoins

// Ajouter le JLabel et le JTextField au JPanel
        tirPanel.add(tirLabel);
        tirPanel.add(tirTextField);

        JButton tirButton = new JButton("TIRER");
        tirPanel.add(tirButton); // Ajouter le bouton à tirPanel

        // Ajouter le JPanel à la contentPane
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(tirPanel, gridBagConstraints);
        bateaux = this.partie.getBateaux(1);

//        for (Bateau bateau : bateaux) {
//            System.out.println("position du bateau : " +bateau.getPosition()+" taille du bateau : "+bateau.getTaille());
//            int colonneDebut = bateau.getPosition().charAt(0) - 'A';
//            int ligneDebut = bateau.getPosition().charAt(1) - '1';
//
//            for (int i = 0; i < bateau.getTaille(); i++) {
//                if (bateau.estHorizontal()) {
//                    grille[ligneDebut][colonneDebut + i] = bateau.getBoatId();
//                } else {
//                    grille[ligneDebut + i][colonneDebut] = bateau.getBoatId();
//                }
//            }
//        }
        ajouterBateauxALaPartie();
        setContentPane(contentPane);
        setVisible(true);

        tirButton.addActionListener(e -> {
            String tirPosition = tirTextField.getText();
            boolean tirResultat = this.partie.verifierTir(tirPosition,true);
            // Mettre à jour grilleTirs en fonction du résultat du tir
            int colonneTir = tirPosition.charAt(0) - 'A';
            int ligneTir = tirPosition.charAt(1) - '1';
            Partie.getInstance().getJoueur().getGrilleTirs()[ligneTir][colonneTir] = tirResultat ? 2 : 1;
            if (tirResultat) {
                Partie partie = Partie.getInstance();
                if (partie.estPartieTerminee()) {
                    System.out.println("Tous les bateaux ont été coulés. Fin de la partie !");
                    FinPartie();
                    // Ici, vous pouvez gérer la fin de la partie, par exemple en affichant une fenêtre de dialogue ou en arrêtant le jeu.
                }
            }
            for (BateauToucheListener listener : bateauToucheListeners) {
                listener.onBateauTouche(tirPosition);
            }
            JoueurIA ia = Partie.getInstance().getIA();
            int[][] grilleTirsIA = ia.getGrilleTirs();
            for (int i = 0; i < grilleTirsIA.length; i++) {
                for (int j = 0; j < grilleTirsIA[i].length; j++) {
                    if (grilleTirsIA[i][j] == 1) {
                        switchchiffre(i,j, g, missedShotIcon);
                    }
                    else if(grilleTirsIA[i][j]==2)
                    {
                        switchchiffre(i,j, g, explosionIcon);
                    }
                }
            }
            // Repaint gridPanel pour afficher les nouvelles images
            gridPanel.repaint();
            tirTextField.setText("");
        });
        this.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyChar() == 'p')
                {
                    plateauAdversaire.setVisible(true);
                }
            }
        });
       // Partie.getInstance().getJoueur().setGrille(grille);
        System.out.print("grille Joueur");
        System.out.print("");
        afficherGrille();
        this.setFocusable(true);
    }

    public void ajouterBateauxALaPartie() {
        List<Bateau> bateaux = this.partie.getBateaux(1);
        System.out.println("Nombre de bateaux : " + bateaux.size());
        for (Bateau bateau : bateaux) {
            this.partie.ajouterBateau(bateau,1);
            System.out.println("position du bateau : " +bateau.getPosition()+" taille du bateau : "+bateau.getTaille());
            int colonneDebut = bateau.getPosition().charAt(0) - 'A';
            int ligneDebut = bateau.getPosition().charAt(1) - '1';

            int[][] grille = this.partie.getJoueur().getGrille();
            for (int i = 0; i < bateau.getTaille(); i++) {
                if (bateau.estHorizontal()) {
                    grille[ligneDebut][colonneDebut + i] = bateau.getBoatId();
                } else {
                    grille[ligneDebut + i][colonneDebut] = bateau.getBoatId();
                }
            }
        }
        System.out.println("position du bateau : ");
        //afficherGrille();
    }
    public void placerBateau(Bateau bateau, String position, String orientation) {
        int colonneDebut = position.charAt(0) - 'A';
        int ligneDebut = position.charAt(1) - '1';
        int[][] grille = this.partie.getJoueur().getGrille();

        for (int i = 0; i < bateau.getTaille(); i++) {
            if (orientation.equals("Horizontal")) {
                grille[ligneDebut][colonneDebut + i] = bateau.getBoatId();
            } else {
                grille[ligneDebut + i][colonneDebut] = bateau.getBoatId();
            }
        }
    }
    private void afficherGrille()
    {
        JoueurHumain joueur = Partie.getInstance().getJoueur();
        int[][] grilleJoueur2 = joueur.getGrille();  // Utilisez la méthode getGrille du singleton
        System.out.println("test");
        System.out.println();
        for (int i = 0; i < grilleJoueur2.length; i++) {
            for (int j = 0; j < grilleJoueur2[i].length; j++) {
                System.out.print(grilleJoueur2[i][j] + " ");
            }
            System.out.println(); // pour passer à la ligne suivante
        }
    }

    private JPanel createGridPanel(String imagePath, boolean isLeftGrid) {
        ImageIcon backgroundImage = new ImageIcon(imagePath);
        gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics gra) {
                g =gra;
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
                    if(porteAvionsOrientation.equals("Horizontal"))
                        switchchiffre(chiffreAvion,test, g, porteAvionIcon);
                    else
                        switchchiffre(chiffreAvion,test, g, porteAvionIconV);

                    test = lettreCroiseur - 'A'+1;
                    if(croiseurOrientation.equals("Horizontal"))
                      switchchiffre(chiffreCroiseur,test, g, croiseurIcon);
                    else
                        switchchiffre(chiffreCroiseur,test, g, croiseurIconV);

                    test = lettreContreTorpilleur - 'A'+1;
                    if(contreTorpilleurOrientation.equals("Horizontal"))
                        switchchiffre(chiffreContreTorpilleur,test, g, contreTorpilleurIcon);
                    else
                        switchchiffre(chiffreContreTorpilleur,test, g, contreTorpilleurIconV);

                    test = lettreTorpilleur - 'A'+1;
                    if(torpilleurOrientation.equals("Horizontal"))
                        switchchiffre(chiffreTorpilleur,test, g, torpilleurIcon);
                    else
                        switchchiffre(chiffreTorpilleur,test, g, torpilleurIconV);

                    test = lettreSousMarin - 'A'+1;
                    if(sousMarinOrientation.equals("Horizontal"))
                        switchchiffre(chiffreSousMarin,test, g, sousMarinIcon);
                    else
                        switchchiffre(chiffreSousMarin,test, g, sousMarinIconV);
                }
                 else {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (Partie.getInstance().getJoueur().getGrilleTirs()[i][j] == 2) { // bateau touché
                                g.drawImage(explosionIcon.getImage(), (j+1)*31, (i+1)*31, this);
                            } else if (Partie.getInstance().getJoueur().getGrilleTirs()[i][j] == 1) { // tir manqué
                                g.drawImage(missedShotIcon.getImage(), (j+1)*31, (i+1)*31, this);
                            }
                        }
                    }
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
    public void FinPartie() {
        new FinPartie("Joueur1");
        setVisible(false);
        plateauAdversaire.setVisible(true);
        plateauAdversaire.dispose();
        dispose();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlateauJeu());
    }
}
//TODO bug avec le tir adverse bateau touche en B10 alors qu'il n'y en avait pas
