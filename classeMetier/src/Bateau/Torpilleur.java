package Bateau;

import java.util.Vector;

public class Torpilleur extends Bateau {
    public Vector<Boolean> etat;
    public Torpilleur(int x, int y, boolean estHorizontal) {
        super(2, x, y, estHorizontal);
        etat = new Vector<>(2);
        for (int i = 0; i < 2; i++) {
            etat.add(false);
        }
    }

    @Override
    public String getType() {
        return "Torpilleur";
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
        Torpilleur torpilleur = new Torpilleur(1, 2, true);
        assert torpilleur.getTaille() == 2 : "Taille incorrecte";
        assert torpilleur.getX() == 1 : "Position X incorrecte";
        assert torpilleur.getY() == 2 : "Position Y incorrecte";
        assert torpilleur.estHorizontal() : "Orientation incorrecte";
    }

    static void testGetType() {
        Torpilleur torpilleur = new Torpilleur(1, 2, true);
        assert "Torpilleur".equals(torpilleur.getType()) : "Type incorrect";
    }

    static void testIncrementerNbCasesTouchees() {
        Torpilleur torpilleur = new Torpilleur(1, 2, true);
        torpilleur.incrementerNbCasesTouchees();
        assert torpilleur.getNbCasesTouchees() == 1 : "Nombre de cases touchées incorrect";
        torpilleur.incrementerNbCasesTouchees();
        assert torpilleur.estCoule() : "Le bateau devrait être coulé";
    }

    static void testContient() {
        Torpilleur torpilleur = new Torpilleur(1, 2, true);
        assert torpilleur.contient(1, 2) : "Le bateau devrait contenir la position (1, 2)";   //vérifie si les coordonnées (1,2) contiennent bien un Torpilleur
        assert torpilleur.contient(2, 2) : "Le bateau devrait contenir la position (2, 2)";   //vérifie si les coordonnées (2,2) contiennent bien un Torpilleur
        assert !torpilleur.contient(3, 2) : "Le bateau ne devrait pas contenir la position (3, 2)";
        assert !torpilleur.contient(1, 3) : "Le bateau ne devrait pas contenir la position (1, 3)";
    }
    static void testEtat() {
        Torpilleur torpilleur = new Torpilleur(1, 2, true);
        Vector<Boolean> etatInitial = torpilleur.getEtat();
        for (Boolean etat : etatInitial) {
            assert !etat : "L'état initial de toutes les parties du bateau doit être false";
        }
    }

    static void testToucherPartie() {
        Torpilleur torpilleur = new Torpilleur(1, 2, true);
        torpilleur.toucherPartie(0);
        assert torpilleur.getEtat().get(0) : "La partie à l'index 0 devrait être touchée";

        torpilleur.toucherPartie(1);
        assert torpilleur.getEtat().get(1) : "La partie à l'index 1 devrait être touchée";

        assert !torpilleur.estCoule() : "Le bateau ne devrait pas être coulé";

        torpilleur.toucherPartie(2);
        assert torpilleur.getEtat().get(2) : "La partie à l'index 2 devrait être touchée";
        assert torpilleur.estCoule() : "Le bateau devrait être coulé";
    }
}
