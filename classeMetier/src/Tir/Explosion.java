package Tir;

import Bateau.Bateau;
import Bateau.Croiseur;

public class Explosion {

    private Bateau[][] grille;

    public Explosion(Bateau[][] grille) {
        this.grille = grille;
    }

    public Tir evaluerTir(int x, int y) {
        Tir tir = new Tir(x, y);

        Bateau bateau = grille[x][y];
        if (bateau != null) {
            tir.setTouche(true);
            bateau.incrementerNbCasesTouchees();

            if (bateau.estCoule()) {
                tir.setCoule(true);
            }

            tir.setBateauTouche(bateau);
        }

        return tir;
    }

    public static void main(String[] args) {
        testEvaluerTir();
        System.out.println("Tous les tests ont réussi !");
    }

    static void testEvaluerTir() {
        Bateau[][] grille = new Bateau[10][10];
        Bateau croiseur = new Croiseur(1, 2, true);
        grille[1][2] = croiseur;
        Explosion explosion = new Explosion(grille);

        Tir tir1 = explosion.evaluerTir(1, 2);
        assert tir1.isTouche() : "Le tir devrait toucher un bateau";
        assert !tir1.isCoule() : "Le tir ne devrait pas couler le bateau";
        assert tir1.getBateauTouche() == croiseur : "Le bateau touché devrait être le croiseur";

        Tir tir2 = explosion.evaluerTir(0, 0);
        assert !tir2.isTouche() : "Le tir ne devrait pas toucher de bateau";
        assert !tir2.isCoule() : "Le tir ne devrait pas couler de bateau";
        assert tir2.getBateauTouche() == null : "Le tir ne devrait pas toucher de bateau";
    }
}
