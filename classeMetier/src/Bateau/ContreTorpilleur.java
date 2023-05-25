package Bateau;

import java.util.Vector;

public class ContreTorpilleur extends Bateau {
    public Vector<Boolean> etat;
    public ContreTorpilleur(int x, int y, boolean estHorizontal) {

        super(3, x, y, estHorizontal);
        etat = new Vector<>(3);
        for (int i = 0; i < 3; i++) {
            etat.add(false);
        }
    }

    @Override
    public String getType() {
        return "ContreTorpilleur";
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
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur(1, 2, true);
        assert contreTorpilleur.getTaille() == 3 : "Taille incorrecte";
        assert contreTorpilleur.getX() == 1 : "Position X incorrecte";
        assert contreTorpilleur.getY() == 2 : "Position Y incorrecte";
        assert contreTorpilleur.estHorizontal() : "Orientation incorrecte";
    }

    static void testGetType() {
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur(1, 2, true);
        assert "ContreTorpilleur".equals(contreTorpilleur.getType()) : "Type incorrect";
    }

    static void testIncrementerNbCasesTouchees() {
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur(1, 2, true);
        contreTorpilleur.incrementerNbCasesTouchees();
        assert contreTorpilleur.getNbCasesTouchees() == 1 : "Nombre de cases touchées incorrect";
        contreTorpilleur.incrementerNbCasesTouchees();
        contreTorpilleur.incrementerNbCasesTouchees();
        assert contreTorpilleur.estCoule() : "Le bateau devrait être coulé";
    }

    static void testContient() {
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur(1, 2, true);
        assert contreTorpilleur.contient(1, 2) : "Le bateau devrait contenir la position (1, 2)";
        assert contreTorpilleur.contient(2, 2) : "Le bateau devrait contenir la position (2, 2)";
        assert contreTorpilleur.contient(3, 2) : "Le bateau devrait contenir la position (3, 2)";
        assert !contreTorpilleur.contient(4, 2) : "Le bateau ne devrait pas contenir la position (4, 2)";
        assert !contreTorpilleur.contient(1, 3) : "Le bateau ne devrait pas contenir la position (1, 3)";
    }
    static void testEtat() {
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur(1, 2, true);
        Vector<Boolean> etatInitial = contreTorpilleur.getEtat();
        for (Boolean etat : etatInitial) {
            assert !etat : "L'état initial de toutes les parties du bateau doit être false";
        }
    }

    static void testToucherPartie() {
        ContreTorpilleur contreTorpilleur = new ContreTorpilleur(1, 2, true);
        contreTorpilleur.toucherPartie(0);
        assert contreTorpilleur.getEtat().get(0) : "La partie à l'index 0 devrait être touchée";

        contreTorpilleur.toucherPartie(1);
        assert contreTorpilleur.getEtat().get(1) : "La partie à l'index 1 devrait être touchée";

        assert !contreTorpilleur.estCoule() : "Le bateau ne devrait pas être coulé";

        contreTorpilleur.toucherPartie(2);
        assert contreTorpilleur.getEtat().get(2) : "La partie à l'index 2 devrait être touchée";
        assert contreTorpilleur.estCoule() : "Le bateau devrait être coulé";
    }


}
