package Bateau;

import java.util.Vector;

public class ContreTorpilleur extends Bateau {
    public Vector<Boolean> etat;

    public ContreTorpilleur(String position, boolean estHorizontal) {
        super(3, position, estHorizontal);
        etat = new Vector<>(3);
        for (int i = 0; i < 3; i++) {
            etat.add(false);
        }
    }

    @Override
    public String getType() {
        return "ContreTorpilleur";
    }
    @Override
    public int getBoatId() {
        return 3;
    }
    public Vector<Boolean> getEtat() {
        return etat;
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
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur("B2", true);
        assert contreTorpilleur.getTaille() == 3 : "Taille incorrecte";
        assert "B2".equals(contreTorpilleur.getPosition()) : "Position incorrecte";
        assert contreTorpilleur.estHorizontal() : "Orientation incorrecte";
    }

    static void testGetType() {
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur("B2", true);
        assert "ContreTorpilleur".equals(contreTorpilleur.getType()) : "Type incorrect";
    }

    static void testIncrementerNbCasesTouchees() {
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur("B2", true);
        for (int i = 0; i < 3; i++) {
            contreTorpilleur.hit();
        }
        assert contreTorpilleur.getCoupRecu() == 3 : "Nombre de cases touchées incorrect";
        assert contreTorpilleur.estCoule() : "Le bateau devrait être coulé";
    }

    static void testContient() {
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur("B2", true);
        assert contreTorpilleur.contient("B2") : "Le bateau devrait contenir la position (B2)";
        assert contreTorpilleur.contient("C2") : "Le bateau devrait contenir la position (C2)";
        assert contreTorpilleur.contient("D2") : "Le bateau devrait contenir la position (D2)";
        assert !contreTorpilleur.contient("E2") : "Le bateau ne devrait pas contenir la position (E2)";
        assert !contreTorpilleur.contient("B3") : "Le bateau ne devrait pas contenir la position (B3)";
    }

    static void testEtat() {
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur("B2", true);
        Vector<Boolean> etatInitial = contreTorpilleur.getEtat();
        for (Boolean etat : etatInitial) {
            assert !etat : "L'état initial de toutes les parties du bateau doit être false";
        }
    }

    static void testToucherPartie() {
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur("B2", true);
        for (int i = 0; i < 2; i++) {
            contreTorpilleur.toucherPartie(i);
            assert contreTorpilleur.getEtat().get(i) : "La partie à l'index " + i + " devrait être touchée";
            assert !contreTorpilleur.estCoule() : "Le bateau ne devrait pas être coulé";
        }
        contreTorpilleur.toucherPartie(2);
        assert contreTorpilleur.getEtat().get(2) : "La partie à l'index 2 devrait être touchée";
        assert contreTorpilleur.estCoule() : "Le bateau devrait être coulé";
    }
}
