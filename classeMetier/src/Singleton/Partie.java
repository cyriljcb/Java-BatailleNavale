package Singleton;
import Historique.HistoriquePartie;
import Historique.HistoriqueCoup;
import Bateau.Bateau;
import Tir.Explosion;
import Tir.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Joueur.*;
import Tir.TirIA.*;

public class Partie {
    private static volatile Partie instance;
    private JoueurHumain joueur1;
    private JoueurIA joueur2;
    public int[][] grilleJoueur1;
    public int[][] grilleJoueur2;
    private List<Bateau> bateauxJoueur1;
    private List<Bateau> bateauxJoueur2;
    private Explosion explosionJoueur1;
    private Explosion explosionJoueur2;
    private List<HistoriqueCoup> historiqueCoups;
    private TirIA tirIa;
    private int Vie = 5;
    /*TODO*/ //remettre Vie a 17 aussi quand la partie est finie
    private Partie() {
        bateauxJoueur1 = new ArrayList<>();
        bateauxJoueur2 = new ArrayList<>();
        historiqueCoups = new ArrayList<>();
        grilleJoueur1 = new int[10][10];
        grilleJoueur2 = new int[10][10];
        explosionJoueur1 = new Explosion(grilleJoueur1, bateauxJoueur1);
        explosionJoueur2 = new Explosion(grilleJoueur2, bateauxJoueur2);
        joueur1 = new JoueurHumain("joueur");
        joueur2 = new JoueurIA("ordinateur");
        tirIa = new TirIA(Difficulte.FACILE);
    }

    public static synchronized Partie getInstance() {
        if (instance == null) {
            instance = new Partie();
        }
        return instance;
    }
    public JoueurHumain getJoueur() {
        return joueur1;
    }

    public JoueurIA getIA() {
        return joueur2;
    }
    public TirIA getTirIA() {
        return tirIa;
    }
    public void setIADifficulty(TirIA.Difficulte difficulty) {
        tirIa = new TirIA(difficulty);
    }
    public TirIA.Difficulte getIADifficulty() {
        return tirIa.getDifficulty();
    }
    public void setJoueur(JoueurHumain joueur) {
        this.joueur1 = joueur;
    }

    public void setIA(JoueurIA ia) {
        this.joueur2 = ia;
    }

    public int[][] getGrille(int joueur) {
        if (joueur == 1) {
            return grilleJoueur1;
        } else if (joueur == 2) {
            return grilleJoueur2;
        } else {
            throw new IllegalArgumentException("Le joueur doit être 1 ou 2");
        }
    }

    // Obtenir la liste des bateaux pour un joueur spécifique
    public List<Bateau> getBateaux(int joueur) {
        if (joueur == 1) {
            return bateauxJoueur1;
        } else if (joueur == 2) {
            return bateauxJoueur2;
        } else {
            throw new IllegalArgumentException("Le joueur doit être 1 ou 2");
        }
    }

    // Ajouter un bateau à la liste et à la grille d'un joueur spécifique
    public void ajouterBateau(Bateau bateau, int joueur) {
        if (joueur == 1) {
            bateauxJoueur1.add(bateau);
            ajouterBateauALaGrille(bateau, grilleJoueur1);
        } else if (joueur == 2) {
            bateauxJoueur2.add(bateau);
            ajouterBateauALaGrille(bateau, grilleJoueur2);
        } else {
            throw new IllegalArgumentException("Le joueur doit être 1 ou 2");
        }
    }
    public boolean verifierTir(String positionTir,boolean isAI) {
        char lettreTir = positionTir.charAt(0);
        int chiffreTir = Character.getNumericValue(positionTir.charAt(1)) - 1;

        int indiceLettreTir = lettreTir - 'A';

        // Obtenez la grille en fonction de la valeur de isAI
        int[][] grilleAdversaire;
        if (isAI) {
            JoueurIA ia = Partie.getInstance().getIA();
            grilleAdversaire = ia.getGrille();
        } else {
            JoueurHumain humain = Partie.getInstance().getJoueur();
            grilleAdversaire = humain.getGrille();
        }

        System.out.println();
        for (int i = 0; i < grilleAdversaire.length; i++) {
            for (int j = 0; j < grilleAdversaire[i].length; j++) {
                System.out.print(grilleAdversaire[i][j] + " ");
            }
            System.out.println(); // pour passer à la ligne suivante
        }
        System.out.println();

        if (grilleAdversaire[chiffreTir][indiceLettreTir] != 0) {
            System.out.println("TOUCHE");
            Vie--;
            if(Vie==0)
                System.out.println("Partie gagnée");
            return true; // Le tir a touché un bateau
        } else {
            System.out.println("RATE");
            return false; // Le tir a raté
        }
    }
    public void ajouterBateauJoueur1(Bateau bateau) {
    bateauxJoueur1.add(bateau);
    ajouterBateauALaGrille(bateau, grilleJoueur1);
}

    public void ajouterBateauJoueur2(Bateau bateau) {
        bateauxJoueur2.add(bateau);
        ajouterBateauALaGrille(bateau, grilleJoueur2);
    }
    public int[][] getGrilleJoueur1() {
        return this.grilleJoueur1;
    }
    public int[][] getGrilleJoueur2() {
        return this.grilleJoueur2;
    }

    private void ajouterBateauALaGrille(Bateau bateau, int[][] grille) {
        String position = bateau.getPosition();
        int ligneInitiale = position.charAt(1) - '1';
        int colonneInitiale = position.charAt(0) - 'A';

        for (int i = 0; i < bateau.getTaille(); i++) {
            if (bateau.estHorizontal()) {
                grille[ligneInitiale][colonneInitiale + i] = 1;
            } else {
                grille[ligneInitiale + i][colonneInitiale] = 1;
            }
        }
    }
    public void reinitialiserPartie() {
        // Réinitialiser les instances des joueurs, les grilles, les bateaux, etc.
        bateauxJoueur1.clear();
        bateauxJoueur2.clear();
        historiqueCoups.clear();
        // Réinitialiser les grilles à 0
        for (int[] row : grilleJoueur1) {
            Arrays.fill(row, 0);
        }
        for (int[] row : grilleJoueur2) {
            Arrays.fill(row, 0);
        }
        // Réinitialiser la vie
        Vie = 5;
        // Ajouter d'autres réinitialisations au besoin...
    }
    public boolean estPartieTerminee() {
        return Vie == 0;
    }

    public List<Bateau> getBateauxJoueur1() {
        return bateauxJoueur1;
    }

    public List<Bateau> getBateauxJoueur2() {
        return bateauxJoueur2;
    }

    public void ajouterHistoriqueCoup(HistoriqueCoup coup) {
        historiqueCoups.add(coup);
    }

    public List<HistoriqueCoup> getHistoriqueCoups() {
        return historiqueCoups;
    }



}
