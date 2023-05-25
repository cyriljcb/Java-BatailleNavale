package Tir;

import Bateau.Bateau;
import Bateau.SousMarin;
public class Tir {
    private int x;
    private int y;
    private boolean touche;
    private boolean coule;
    private Bateau bateauTouche;

    public Tir(int x, int y) {
        this.x = x;
        this.y = y;
        this.touche = false;
        this.coule = false;
        this.bateauTouche = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isTouche() {
        return touche;
    }

    public void setTouche(boolean touche) {
        this.touche = touche;
    }

    public boolean isCoule() {
        return coule;
    }

    public void setCoule(boolean coule) {
        this.coule = coule;
    }

    public Bateau getBateauTouche() {
        return bateauTouche;
    }

    public void setBateauTouche(Bateau bateauTouche) {
        this.bateauTouche = bateauTouche;
    }

    public static void main(String[] args) {
        testTir();
        System.out.println("Tous les tests ont réussi !");
    }

    static void testTir() {
        Tir tir = new Tir(3, 4);
        assert tir.getX() == 3 : "La coordonnée x devrait être 3";
        assert tir.getY() == 4 : "La coordonnée y devrait être 4";
        assert !tir.isTouche() : "Le tir ne devrait pas être touché initialement";
        assert !tir.isCoule() : "Le tir ne devrait pas être coulé initialement";
        assert tir.getBateauTouche() == null : "Le bateau touché devrait être null initialement";

        tir.setTouche(true);
        assert tir.isTouche() : "Le tir devrait être touché après avoir été mis à jour";

        tir.setCoule(true);
        assert tir.isCoule() : "Le tir devrait être coulé après avoir été mis à jour";

        Bateau bateau = new SousMarin(3, 4, true);
        tir.setBateauTouche(bateau);
        assert tir.getBateauTouche() == bateau : "Le bateau touché devrait être égal au bateau mis à jour";
    }
}
