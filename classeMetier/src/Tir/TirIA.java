package Tir;

import java.util.Random;

public class TirIA {
    public enum Difficulte {
        FACILE,
        MOYEN,
        DIFFICILE
    }

    private Difficulte difficulte;
    private Random random;

    public TirIA(Difficulte difficulte) {
        this.difficulte = difficulte;
        this.random = new Random();
    }

    public String tirer() {
        String position = "";

        switch (difficulte) {
            case FACILE:
                position = tirerAleatoire();
                break;
            case MOYEN:
                position = tirerMoyen();
                break;
            case DIFFICILE:
                position = tirerDifficile();
                break;
        }

        return position;
    }
    public Difficulte getDifficulty() {
        return this.difficulte;
    }
    private String tirerAleatoire() {
        int colonne = random.nextInt(10) + 1;
        char ligne = (char)(random.nextInt(10) + 'A');
        return String.valueOf(ligne) + colonne;
    }

    private String tirerMoyen() {
        // Implémentez la logique pour le niveau de difficulté moyen
        // Par exemple, si un bateau est touché, visez autour de cette zone
        // stocker l'état du dernier tir et adapter la logique en conséquence
        return "A1";
    }

    private String tirerDifficile() {
        // la logique pour le niveau de difficulté difficile
        // Par exemple, une stratégie plus avancée pour cibler et couler les bateaux rapidement
        return "A1";
    }

    public static void main(String[] args) {
        testTirerAleatoire();
        System.out.println("Tous les tests ont réussi !");
    }

    static void testTirerAleatoire() {
        TirIA ia = new TirIA(Difficulte.FACILE);
        for (int i = 0; i < 100; i++) {
            String pos = ia.tirer();
            char ligne = pos.charAt(0);
            int colonne = Integer.parseInt(pos.substring(1));
            assert ligne >= 'A' && ligne <= 'J' : "La ligne devrait être comprise entre A et J";
            assert colonne >= 1 && colonne <= 10 : "La colonne devrait être comprise entre 1 et 10";
        }
    }
}
