package Tir;

import Bateau.Bateau;
import Bateau.Croiseur;

import java.util.List;
import java.util.ArrayList;

public class Explosion {

    private int[][] grille;
    private List<Bateau> bateaux;

    public Explosion(int[][] grille, List<Bateau> bateaux) {
        this.grille = grille;
        this.bateaux = bateaux;
    }

    public Tir evaluerTir(String pos) {
        Tir tir = new Tir(pos);
        int let = pos.charAt(0) - 'A';
        int chiffre = Integer.parseInt(pos.substring(1)) - 1;
        if (grille[chiffre][let] != 0) {
            for (Bateau bateau : bateaux) {
                if (bateau.estALaPosition(pos)) {
                    tir.setTouche(true);
                    bateau.hit();
                    if (bateau.estCoule()) {
                        tir.setCoule(true);
                        System.out.println("Bateau coulé !");
                    }

                    tir.setBateauTouche(bateau);
                    break;
                }
            }
        }
        return tir;
    }

//    public Tir evaluerTir(String pos) {
//        Tir tir = new Tir(pos);
//        int let = pos.charAt(0) - 'A';
//        String chi = pos.substring(1);
//        int chiffre = Integer.parseInt(chi);
//        Bateau bateau = grille[let][chiffre];
//        if (bateau != null) {
//            tir.setTouche(true);
//            bateau.incrementerNbCasesTouchees();
//            if (bateau.estCoule()) {
//                tir.setCoule(true);
//                System.out.println("Bateau coulé !");
//            }
//
//            tir.setBateauTouche(bateau);
//        }
//        Bateau bateauTouche = null;
//        for (Bateau bateaux : bateaux) {
//            if (bateau.bateauTouche(pos)) {
//                bateauTouche = bateau;
//                break;
//            }
//        }
//        // bateauTouche est maintenant le bateau qui a été touché, ou null si aucun bateau n'a été touché.
//        if (bateauTouche != null) {
//            bateauTouche.incrementerNbCasesTouchees();
//            if (bateauTouche.estCoule()) {
//                System.out.println("Bateau coulé !");
//            }
//            if (tousLesBateauxSontCoules()) {
//                System.out.println("Tous les bateaux ont été coulés. Fin de la partie !");
//                // Ici, vous pouvez gérer la fin de la partie, par exemple en affichant une fenêtre de dialogue ou en arrêtant le jeu.
//            }
//        }
//
//        return tir;
//    }
//public Tir evaluerTir(String pos) {
//    Tir tir = new Tir(pos);
//    int let = pos.charAt(0) - 'A';  // Convertit le caractère en un indice de tableau entier.
//    int chiffre = Integer.parseInt(pos.substring(1)) - 1;  // Convertit le chiffre en un indice de tableau entier.
//    Bateau bateau = grille[let][chiffre];
//    if (bateau != null) {
//        tir.setTouche(true);
//        bateau.incrementerNbCasesTouchees();
//        if (bateau.estCoule()) {
//            tir.setCoule(true);
//            System.out.println("Bateau coulé !");
//        }
//
//        tir.setBateauTouche(bateau);
//    }
//
//    return tir;
//}


    public static void main(String[] args) {
        testEvaluerTir();
        System.out.println("Tous les tests ont réussi !");
    }

    static void testEvaluerTir() {
        int[][] grille = new int[10][10];
        List<Bateau> bateaux = new ArrayList<>();
        Bateau croiseur = new Croiseur("B2", true);
        bateaux.add(croiseur);
        grille[1][2] = 1;  // Assigner la valeur 1 à l'endroit où le bateau se trouve.

        Explosion explosion = new Explosion(grille, bateaux);

        Tir tir1 = explosion.evaluerTir("B2");
        assert tir1.isTouche() : "Le tir devrait toucher un bateau";
        assert !tir1.isCoule() : "Le tir ne devrait pas couler le bateau";
        assert tir1.getBateauTouche() == croiseur : "Le bateau touché devrait être le croiseur";

        Tir tir2 = explosion.evaluerTir("C3");
        assert !tir2.isTouche() : "Le tir ne devrait pas toucher de bateau";
        assert !tir2.isCoule() : "Le tir ne devrait pas couler de bateau";
        assert tir2.getBateauTouche() == null : "Le tir ne devrait pas toucher de bateau";
    }

}
