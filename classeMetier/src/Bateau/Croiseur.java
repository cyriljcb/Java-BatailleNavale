package Bateau;

import java.util.Vector;

public class Croiseur extends Bateau {
    public Vector<Boolean> etat;
    public Croiseur(String position, boolean estHorizontal) {
        super(4, position, estHorizontal);
        etat = new Vector<>(4);
        for (int i = 0; i < 4; i++) {
            etat.add(false);
        }
    }

    @Override
    public String getType() {
        return "Croiseur";
    }

    public Vector<Boolean> getEtat() {
        return etat;
    }
    @Override
    public int getBoatId() {
        return 4;
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
        Croiseur croiseur = new Croiseur("B2", true);
        assert croiseur.getTaille() == 4 : "Taille incorrecte";
        assert "B2".equals(croiseur.getPosition()) : "Position incorrecte";
        assert croiseur.estHorizontal() : "Orientation incorrecte";
    }

    static void testGetType() {
        Croiseur croiseur = new Croiseur("B2", true);
        assert "Croiseur".equals(croiseur.getType()) : "Type incorrect";
    }

    static void testIncrementerNbCasesTouchees() {
        Croiseur croiseur = new Croiseur("B2", true);
        for (int i = 0; i < 4; i++) {
            croiseur.hit();
        }
        assert croiseur.getCoupRecu() == 4 : "Nombre de cases touchées incorrect";
        assert croiseur.estCoule() : "Le bateau devrait être coulé";
    }

    static void testContient() {
        Croiseur croiseur = new Croiseur("B2", true);
        assert croiseur.contient("B2") : "Le bateau devrait contenir la position (B2)";
        assert croiseur.contient("C2") : "Le bateau devrait contenir la position (C2)";
        assert croiseur.contient("D2") : "Le bateau devrait contenir la position (D2)";
        assert croiseur.contient("E2") : "Le bateau devrait contenir la position (E2)";
        assert !croiseur.contient("F2") : "Le bateau ne devrait pas contenir la position (F2)";
        assert !croiseur.contient("B3") : "Le bateau ne devrait pas contenir la position (B3)";
    }

    static void testEtat() {
        Croiseur croiseur = new Croiseur("B2", true);
        Vector<Boolean> etatInitial = croiseur.getEtat();
        for (Boolean etat : etatInitial) {
            assert !etat : "L'état initial de toutes les parties du bateau doit être false";
        }
    }

    static void testToucherPartie() {
        Croiseur croiseur = new Croiseur("B2", true);
        for (int i = 0; i < 3; i++) {
            croiseur.toucherPartie(i);
            assert croiseur.getEtat().get(i) : "La partie à l'index " + i + " devrait être touchée";
            assert !croiseur.estCoule() : "Le bateau ne devrait pas être coulé";
        }
        croiseur.toucherPartie(3);
        assert croiseur.getEtat().get(3) : "La partie à l'index 3 devrait être touchée";
        assert croiseur.estCoule() : "Le bateau devrait être coulé";
    }
}
