import javax.swing.*;
import java.awt.*;
import java.util.Random;
import Singleton.Partie;
import Joueur.*;
import Bateau.*;
import Tir.TirIA;

public class PlateauJeuAdversaire extends JFrame implements BateauToucheListener {

    private int[][] grilleAdversaire;
    private int tailleGrille = 10;
    private JPanel gridPanel;
    private Graphics g;
    private PlateauJeu plat;
    //String chemin = "/Naval Battle Assets/";
    String chemin = "/Users/ateli/OneDrive/Documents/cours supérieur/B2/C#/labo final/Naval Battle Assets/Naval Battle Assets/";
    PorteAvion porteAvions = new PorteAvion("A1",true);
    Croiseur croiseur = new Croiseur("A2",true);
    ContreTorpilleur contreTorpilleur = new ContreTorpilleur("A3",true);
    SousMarin sousMarin = new SousMarin("A4",true);
    Torpilleur torpilleur = new Torpilleur("A5",true);
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
    public PlateauJeuAdversaire(JoueurHumain joueurHumain, JoueurIA ia,PlateauJeu plateau) {
        plat = plateau;
        plateau.addBateauToucheListener(this);
        Partie partie = Partie.getInstance();
        partie.setJoueur(joueurHumain);  // Utilisez le singleton pour stocker les instances de joueur
        partie.setIA(ia);
        setTitle("Bataille Navale - Plateau de l'adversaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                partie.getGrille(2)[i][j] = 0;
            }
        }
        // Placement automatique des bateaux
        placerBateau(porteAvions, 5); // porte-avions
        placerBateau(croiseur, 4); // croiseur
        placerBateau(contreTorpilleur, 3); // contre-torpilleur
        placerBateau(sousMarin, 2); // sous-marin
        placerBateau(torpilleur, 1); // torpilleur

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
    private void placerBateau(Bateau bateau, int id) {
        JoueurIA ia = Partie.getInstance().getIA();
        String position;
        String orientation;
        boolean estPlace;
        boolean canBePlaced;
        do {
            canBePlaced = true;
            orientation = genererOrientation();
            position = genererPosition(bateau.getTaille(), orientation);
            estPlace = estPositionValide(position, orientation, bateau.getTaille());

            if (estPlace) {
                char lettrePosition = position.charAt(0);
                int chiffrePosition = Integer.parseInt(position.substring(1));
                int[][] iaGrille = ia.getGrille();

                if (orientation.equals("horizontal")) {
                    if(lettrePosition - 'A' + bateau.getTaille() > 9) {
                        System.out.println("Le bateau dépasse la limite de la grille.");
                        canBePlaced = false;
                    } else {
                        for (int i = 0; i < bateau.getTaille(); i++) {
                            if (iaGrille[chiffrePosition - 1][lettrePosition - 'A' + i] != 0) {
                                System.out.println("Un bateau existe déjà à cette position.");
                                canBePlaced = false;
                                break;
                            }
                        }
                    }
                } else {
                    if(chiffrePosition + bateau.getTaille() > 10) {
                        System.out.println("Le bateau dépasse la limite de la grille.");
                        canBePlaced = false;
                    } else {
                        for (int i = 0; i < bateau.getTaille(); i++) {
                            if (iaGrille[chiffrePosition + i - 1][lettrePosition - 'A'] != 0) {
                                System.out.println("Un bateau existe déjà à cette position.");
                                canBePlaced = false;
                                break;
                            }
                        }
                    }
                }

                if (canBePlaced) {
                    bateau.setPosition(position);
                    bateau.setOrientation(orientation.equals("horizontal"));
                    for (int i = 0; i < bateau.getTaille(); i++) {
                        if (orientation.equals("horizontal")) {
                            iaGrille[chiffrePosition - 1][lettrePosition - 'A' + i] = id;
                        } else {
                            iaGrille[chiffrePosition + i - 1][lettrePosition - 'A'] = id;
                        }
                    }
                }

            } else {
                System.out.println("Un bateau existe déjà à cette position.");
            }

        } while (!estPlace || !canBePlaced);

        System.out.println();
        afficherGrille();
    }
    public void afficherGrille() {
        JoueurIA ia = Partie.getInstance().getIA();
        int[][] grilleJoueur2 = ia.getGrille();  // Utilisez la méthode getGrille du singleton
        for (int i = 0; i < grilleJoueur2.length; i++) {
            for (int j = 0; j < grilleJoueur2[i].length; j++) {
                System.out.print(grilleJoueur2[i][j] + " ");
            }
            System.out.println(); // pour passer à la ligne suivante
        }
    }
    public boolean estPositionValide(String position, String orientation, int taille) {
        JoueurIA ia = Partie.getInstance().getIA();
        char lettre = position.charAt(0);
        int chiffre = Character.getNumericValue(position.charAt(1));

        // Convertissez la lettre en indice de tableau
        int indiceLettre = lettre - 'A';
        int tailleGrille = ia.getGrille().length;

        if (orientation.equals("horizontal")) {
            if (indiceLettre + taille > tailleGrille || chiffre >= tailleGrille) {
                return false;
            }
            for (int i = 0; i < taille; i++) {
                if (ia.getGrille()[chiffre][indiceLettre + i] != 0) {
                    return false;
                }
            }
        } else {
            if (chiffre + taille > tailleGrille || indiceLettre >= tailleGrille) {
                return false;
            }
            for (int i = 0; i < taille; i++) {
                if (ia.getGrille()[chiffre + i][indiceLettre] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private String genererPosition(int taille, String orientation) {
        Random random = new Random();
        char lettre;
        int chiffre;
        if (orientation.equals("horizontal")) {
            lettre = (char) (random.nextInt(tailleGrille - taille + 1) + 'A');
            chiffre = random.nextInt(tailleGrille) + 1;
        } else {
            lettre = (char) (random.nextInt(tailleGrille) + 'A');
            chiffre = random.nextInt(tailleGrille - taille + 1) + 1;
        }
        return lettre + String.valueOf(chiffre);
    }
    public String genererOrientation() {
        Random random = new Random();
        return random.nextBoolean() ? "horizontal" : "vertical";
    }
    private JPanel createGridPanel(String imagePath, boolean isLeftGrid) {

        ImageIcon backgroundImage = new ImageIcon(imagePath);
        gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics gra) {
                g = gra;
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, this);
                    char lettreAvion = porteAvions.getPosition().charAt(0);
                    String chiAvion = porteAvions.getPosition().substring(1);
                    int chiffreAvion = Integer.parseInt(chiAvion);

                    char lettreCroiseur = croiseur.getPosition().charAt(0);
                    String chiCroiseur = croiseur.getPosition().substring(1);
                    int chiffreCroiseur = Integer.parseInt(chiCroiseur);

                    char lettreTorpilleur = torpilleur.getPosition().charAt(0);
                    String chiTorpilleur = torpilleur.getPosition().substring(1);
                    int chiffreTorpilleur = Integer.parseInt(chiTorpilleur);

                    char lettreContreTorpilleur = contreTorpilleur.getPosition().charAt(0);
                    String chiContreTorpilleur = contreTorpilleur.getPosition().substring(1);
                    int chiffreContreTorpilleur = Integer.parseInt(chiContreTorpilleur);

                    char lettreSousMarin = sousMarin.getPosition().charAt(0);
                    String chiSousMarin = sousMarin.getPosition().substring(1);
                    int chiffreSousMarin = Integer.parseInt(chiSousMarin);

                int test;

                if (isLeftGrid) {
                    test = lettreAvion - 'A'+1;
                    if(porteAvions.estHorizontal())
                        switchchiffre(chiffreAvion,test, g, porteAvionIcon);
                    else
                        switchchiffre(chiffreAvion,test, g, porteAvionIconV);

                    test = lettreCroiseur - 'A'+1;
                    if(croiseur.estHorizontal())
                        switchchiffre(chiffreCroiseur,test, g, croiseurIcon);
                    else
                        switchchiffre(chiffreCroiseur,test, g, croiseurIconV);

                    test = lettreContreTorpilleur - 'A'+1;
                    if(contreTorpilleur.estHorizontal())
                        switchchiffre(chiffreContreTorpilleur,test, g, contreTorpilleurIcon);
                    else
                        switchchiffre(chiffreContreTorpilleur,test, g, contreTorpilleurIconV);

                    test = lettreTorpilleur - 'A'+1;
                    if(torpilleur.estHorizontal())
                        switchchiffre(chiffreTorpilleur,test, g, torpilleurIcon);
                    else
                        switchchiffre(chiffreTorpilleur,test, g, torpilleurIconV);

                    test = lettreSousMarin - 'A'+1;
                    if(sousMarin.estHorizontal())
                        switchchiffre(chiffreSousMarin,test, g, sousMarinIcon);
                    else
                        switchchiffre(chiffreSousMarin,test, g, sousMarinIconV);
                }
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
    @Override
    public void onBateauTouche(String position) {
        System.out.println("PlateauJeuAdverse");
        char lettre = position.charAt(0);
        int indiceLettre = lettre - 'A';
        int chiffre = Character.getNumericValue(position.charAt(1));
        // Vérifie si le tir a touché un bateau
        if (Partie.getInstance().getIA().getGrille()[chiffre][indiceLettre] !=0) {
            System.out.println("Un bateau a été touché à la position " + position);
            //switchchiffre(chiffre,indiceLettre,g,explosionIcon);
        } else {
            System.out.println("Aucun bateau à la position " + position);
            //switchchiffre(chiffre,indiceLettre,g,missedShotIcon);
        }
        TirIA Tiria = Partie.getInstance().getTirIA();
        String tirIA = Tiria.tirer(); // génère un tir en fonction de la difficulté
        // Vérifie le tir
        boolean tirResultat = Partie.getInstance().verifierTir(tirIA, false);
        int colonneTir = tirIA.charAt(0) - 'A';
        int ligneTir = tirIA.charAt(1) - '1';
        Partie.getInstance().getIA().getGrilleTirs()[ligneTir][colonneTir] = tirResultat ? 2 : 1;
        System.out.println("resultat du tir :" + tirResultat+"position du tir" +tirIA);
        if (tirResultat) {
            Partie partie = Partie.getInstance();
            if (partie.estPartieTerminee()) {
                System.out.println("Tous les bateaux ont été coulés. Fin de la partie !");
                FinPartie();

            }
        }
    }
    public void FinPartie() {
        new FinPartie("ordinateur");
        setVisible(false);
        plat.setVisible(true);
        plat.dispose();
        dispose();
    }
    private void switchchiffre(int chiffre, int lettre,Graphics g,ImageIcon bateau )
    {
        g.drawImage(bateau.getImage(), lettre*31, chiffre*31, gridPanel);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JoueurHumain joueurHumain = new JoueurHumain("Joueur1");
            JoueurIA ia = new JoueurIA("Joueur2");
            PlateauJeu pl = new PlateauJeu();
            new PlateauJeuAdversaire(joueurHumain, ia,pl);
        });
    }
}