package Tir;

import java.util.Random;

public class IA {
    public enum Difficulte {
        FACILE,
        MOYEN,
        DIFFICILE
    }

    private Difficulte difficulte;
    private Random random;

    public IA(Difficulte difficulte) {
        this.difficulte = difficulte;
        this.random = new Random();
    }

    public int[] tirer() {
        int[] coordonnees = new int[2];

        switch (difficulte) {
            case FACILE:
                coordonnees = tirerAleatoire();
                break;
            case MOYEN:
                coordonnees = tirerMoyen();
                break;
            case DIFFICILE:
                coordonnees = tirerDifficile();
                break;
        }

        return coordonnees;
    }

    private int[] tirerAleatoire() {
        int[] coordonnees = new int[2];
        coordonnees[0] = random.nextInt(10);
        coordonnees[1] = random.nextInt(10);
        return coordonnees;
    }

    private int[] tirerMoyen() {
        // Implémentez la logique pour le niveau de difficulté moyen
        // Par exemple, si un bateau est touché, visez autour de cette zone
        // stocker l'état du dernier tir et adapter la logique en conséquence
        return new int[2];
    }

    private int[] tirerDifficile() {
        // la logique pour le niveau de difficulté difficile
        // Par exemple, une stratégie plus avancée pour cibler et couler les bateaux rapidement
        return new int[2];
    }

    public static void main(String[] args) {
        testTirerAleatoire();
        System.out.println("Tous les tests ont réussi !");
    }

    static void testTirerAleatoire() {
        IA ia = new IA(Difficulte.FACILE);
        for (int i = 0; i < 100; i++) {
            int[] coords = ia.tirer();
            assert coords[0] >= 0 && coords[0] < 10 : "La coordonnée x devrait être comprise entre 0 et 9";
            assert coords[1] >= 0 && coords[1] < 10 : "La coordonnée y devrait être comprise entre 0 et 9";
        }
    }
}
