package Bateau;

import java.util.Vector;

public class Torpilleur extends Bateau {
    public Vector<Boolean> etat;

    public Torpilleur(String position, boolean estHorizontal) {
        super(2, position, estHorizontal);
        etat = new Vector<>(2);
        for (int i = 0; i < 2; i++) {
            etat.add(false);
        }
    }

    @Override
    public String getType() {
        return "Torpilleur";
    }

    public Vector<Boolean> getEtat() {
        return etat;
    }
    @Override
    public int getBoatId() {
        return 1;
    }
    public void toucherPartie(int index) {
        etat.set(index, true);
        if (!etat.contains(false)) {
            setEstCoule(true);
        }
    }

    public static void main(String[] args) {
        testConstructor();
        testGetType();
        testIncrementerNbCasesTouchees();
        testContient();
        testEtat();
        testToucherPartie();
        System.out.println("Tous les tests ont réussi !");
    }

    static void testConstructor() {
        Torpilleur torpilleur = new Torpilleur("C3", true);
        assert torpilleur.getTaille() == 2 : "Taille incorrecte";
        assert "C3".equals(torpilleur.getPosition()) : "Position incorrecte";
        assert torpilleur.estHorizontal() : "Orientation incorrecte";
    }

    static void testGetType() {
        Torpilleur torpilleur = new Torpilleur("C3", true);
        assert "Torpilleur".equals(torpilleur.getType()) : "Type incorrect";
    }

    static void testIncrementerNbCasesTouchees() {
        Torpilleur torpilleur = new Torpilleur("C3", true);
        for (int i = 0; i < 2; i++) {
            torpilleur.hit();
        }
        assert torpilleur.getCoupRecu() == 2 : "Nombre de cases touchées incorrect";
        assert torpilleur.estCoule() : "Le bateau devrait être coulé";
    }

    static void testContient() {
        Torpilleur torpilleur = new Torpilleur("C3", true);
        assert torpilleur.contient("C3") : "Le bateau devrait contenir la position (C3)";
        assert torpilleur.contient("D3") : "Le bateau devrait contenir la position (D3)";
        assert !torpilleur.contient("E3") : "Le bateau ne devrait pas contenir la position (E3)";
        assert !torpilleur.contient("C4") : "Le bateau ne devrait pas contenir la position (C4)";
    }

    static void testEtat() {
        Torpilleur torpilleur = new Torpilleur("C3", true);
        Vector<Boolean> etatInitial = torpilleur.getEtat();
        for (Boolean etat : etatInitial) {
            assert !etat : "L'état initial de toutes les parties du bateau doit être false";
        }
    }

    static void testToucherPartie() {
        Torpilleur torpilleur = new Torpilleur("C3", true);
        torpilleur.toucherPartie(0);
        assert torpilleur.getEtat().get(0) : "La partie à l'index 0 devrait être touchée";

        torpilleur.toucherPartie(1);
        assert torpilleur.getEtat().get(1) : "La partie à l'index 1 devrait être touchée";

        assert !torpilleur.estCoule() : "Le bateau ne devrait pas être coulé";

        torpilleur.toucherPartie(1);
        assert torpilleur.estCoule() : "Le bateau devrait être coulé";
    }
}
