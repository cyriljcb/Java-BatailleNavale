package Bateau;

import java.util.Vector;

public class SousMarin extends Bateau {
    public Vector<Boolean> etat;

    public SousMarin(String position, boolean estHorizontal) {
        super(3, position, estHorizontal);
        etat = new Vector<>(3);
        for (int i = 0; i < 3; i++) {
            etat.add(false);
        }
    }

    @Override
    public String getType() {
        return "SousMarin";
    }

    public Vector<Boolean> getEtat() {
        return etat;
    }
    @Override
    public int getBoatId() {
        return 2;
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
        SousMarin sousMarin = new SousMarin("B2", true);
        assert sousMarin.getTaille() == 3 : "Taille incorrecte";
        assert "B2".equals(sousMarin.getPosition()) : "Position incorrecte";
        assert sousMarin.estHorizontal() : "Orientation incorrecte";
    }

    static void testGetType() {
        SousMarin sousMarin = new SousMarin("B2", true);
        assert "SousMarin".equals(sousMarin.getType()) : "Type incorrect";
    }

    static void testIncrementerNbCasesTouchees() {
        SousMarin sousMarin = new SousMarin("B2", true);
        sousMarin.hit();
        assert sousMarin.getCoupRecu() == 1 : "Nombre de cases touchées incorrect";
        sousMarin.hit();
        sousMarin.hit();
        assert sousMarin.estCoule() : "Le bateau devrait être coulé";
    }

    static void testContient() {
        SousMarin sousMarin = new SousMarin("B2", true);
        assert sousMarin.contient("B2") : "Le bateau devrait contenir la position B2";
        assert sousMarin.contient("C2") : "Le bateau devrait contenir la position C2";
        assert sousMarin.contient("D2") : "Le bateau devrait contenir la position D2";
        assert !sousMarin.contient("E2") : "Le bateau ne devrait pas contenir la position E2";
        assert !sousMarin.contient("B3") : "Le bateau ne devrait pas contenir la position B3";
    }

    static void testEtat() {
        SousMarin sousMarin = new SousMarin("B2", true);
        Vector<Boolean> etatInitial = sousMarin.getEtat();
        for (Boolean etat : etatInitial) {
            assert !etat : "L'état initial de toutes les parties du bateau doit être false";
        }
    }

    static void testToucherPartie() {
        SousMarin sousMarin = new SousMarin("B2", true);
        sousMarin.toucherPartie(0);
        assert sousMarin.getEtat().get(0) : "La partie à l'index 0 devrait être touchée";

        sousMarin.toucherPartie(1);
        assert sousMarin.getEtat().get(1) : "La partie à l'index 1 devrait être touchée";

        assert !sousMarin.estCoule() : "Le bateau ne devrait pas être coulé";

        sousMarin.toucherPartie(2);
        assert sousMarin.getEtat().get(2) : "La partie à l'index 2 devrait être touchée";
        assert sousMarin.estCoule() : "Le bateau devrait être coulé";
    }
}
