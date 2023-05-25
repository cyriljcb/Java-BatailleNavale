package Bateau;

import java.util.Vector;

public class Croiseur extends Bateau {
    public Vector<Boolean> etat;
    public Croiseur(int x, int y, boolean estHorizontal) {
        super(4, x, y, estHorizontal);
        etat = new Vector<>(4);
        for (int i = 0; i < 4; i++) {
            etat.add(false);
        }
    }

    @Override
    public String getType() {
        return "Croiseur";
    }
    public Vector<Boolean> getEtat()
    {
        return  etat;
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
        Croiseur croiseur = new Croiseur(1, 2, true);
        assert croiseur.getTaille() == 4 : "Taille incorrecte";
        assert croiseur.getX() == 1 : "Position X incorrecte";
        assert croiseur.getY() == 2 : "Position Y incorrecte";
        assert croiseur.estHorizontal() : "Orientation incorrecte";
    }

    static void testGetType() {
        Croiseur croiseur = new Croiseur(1, 2, true);
        assert "Croiseur".equals(croiseur.getType()) : "Type incorrect";
    }

    static void testIncrementerNbCasesTouchees() {
        Croiseur croiseur = new Croiseur(1, 2, true);
        croiseur.incrementerNbCasesTouchees();
        assert croiseur.getNbCasesTouchees() == 1 : "Nombre de cases touchées incorrect";
        croiseur.incrementerNbCasesTouchees();
        croiseur.incrementerNbCasesTouchees();
        croiseur.incrementerNbCasesTouchees();
        assert croiseur.estCoule() : "Le bateau devrait être coulé";
    }

    static void testContient() {
        Croiseur croiseur = new Croiseur(1, 2, true);
        assert croiseur.contient(1, 2) : "Le bateau devrait contenir la position (1, 2)";
        assert croiseur.contient(2, 2) : "Le bateau devrait contenir la position (2, 2)";
        assert croiseur.contient(3, 2) : "Le bateau devrait contenir la position (3, 2)";
        assert croiseur.contient(4, 2) : "Le bateau devrait contenir la position (4, 2)";
        assert !croiseur.contient(5, 2) : "Le bateau ne devrait pas contenir la position (5, 2)";
        assert !croiseur.contient(1, 3) : "Le bateau ne devrait pas contenir la position (1, 3)";
    }
    static void testEtat() {
        Croiseur croiseur = new Croiseur(1, 2, true);
        Vector<Boolean> etatInitial = croiseur.getEtat();
        for (Boolean etat : etatInitial) {
            assert !etat : "L'état initial de toutes les parties du bateau doit être false";
        }
    }

    static void testToucherPartie() {
        Croiseur croiseur = new Croiseur(1, 2, true);
        croiseur.toucherPartie(0);
        assert croiseur.getEtat().get(0) : "La partie à l'index 0 devrait être touchée";

        croiseur.toucherPartie(1);
        assert croiseur.getEtat().get(1) : "La partie à l'index 1 devrait être touchée";

        assert !croiseur.estCoule() : "Le bateau ne devrait pas être coulé";

        croiseur.toucherPartie(2);
        assert croiseur.getEtat().get(2) : "La partie à l'index 2 devrait être touchée";
        assert croiseur.estCoule() : "Le bateau devrait être coulé";
    }
}
