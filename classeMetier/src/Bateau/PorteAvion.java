package Bateau;

import java.util.Vector;

public class PorteAvion extends Bateau {
    public Vector<Boolean> etat;
    public PorteAvion(int x, int y, boolean estHorizontal) {
        super(5, x, y, estHorizontal);
        etat = new Vector<>(5);
        for (int i = 0; i < 5; i++) {
            etat.add(false);
        }
    }

    @Override
    public String getType() {
        return "Porte-avion";
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
        PorteAvion porteAvion = new PorteAvion(1, 2, true);
        assert porteAvion.getTaille() == 5 : "Taille incorrecte";
        assert porteAvion.getX() == 1 : "Position X incorrecte";
        assert porteAvion.getY() == 2 : "Position Y incorrecte";
        assert porteAvion.estHorizontal() : "Orientation incorrecte";
    }

    static void testGetType() {
        PorteAvion porteAvion = new PorteAvion(1, 2, true);
        assert "Porte-avion".equals(porteAvion.getType()) : "Type incorrect";
    }

    static void testIncrementerNbCasesTouchees() {
        PorteAvion porteAvion = new PorteAvion(1, 2, true);
        porteAvion.incrementerNbCasesTouchees();
        assert porteAvion.getNbCasesTouchees() == 1 : "Nombre de cases touchées incorrect";
        porteAvion.incrementerNbCasesTouchees();
        porteAvion.incrementerNbCasesTouchees();
        porteAvion.incrementerNbCasesTouchees();
        porteAvion.incrementerNbCasesTouchees();
        assert porteAvion.estCoule() : "Le bateau devrait être coulé";
    }

    static void testContient() {
        PorteAvion porteAvion = new PorteAvion(1, 2, true);
        assert porteAvion.contient(1, 2) : "Le bateau devrait contenir la position (1, 2)";
        assert porteAvion.contient(2, 2) : "Le bateau devrait contenir la position (2, 2)";
        assert porteAvion.contient(3, 2) : "Le bateau devrait contenir la position (3, 2)";
        assert porteAvion.contient(4, 2) : "Le bateau devrait contenir la position (4, 2)";
        assert porteAvion.contient(5, 2) : "Le bateau devrait contenir la position (5, 2)";
        assert !porteAvion.contient(6, 2) : "Le bateau ne devrait pas contenir la position (6, 2)";
        assert !porteAvion.contient(1, 3) : "Le bateau ne devrait pas contenir la position (1, 3)";
    }
    static void testEtat() {
        PorteAvion porteAvion = new PorteAvion(1, 2, true);
        Vector<Boolean> etatInitial = porteAvion.getEtat();
        for (Boolean etat : etatInitial) {
            assert !etat : "L'état initial de toutes les parties du bateau doit être false";
        }
    }

    static void testToucherPartie() {
        PorteAvion porteAvion = new PorteAvion(1, 2, true);
        porteAvion.toucherPartie(0);
        assert porteAvion.getEtat().get(0) : "La partie à l'index 0 devrait être touchée";

        porteAvion.toucherPartie(1);
        assert porteAvion.getEtat().get(1) : "La partie à l'index 1 devrait être touchée";

        assert !porteAvion.estCoule() : "Le bateau ne devrait pas être coulé";

        porteAvion.toucherPartie(2);
        assert porteAvion.getEtat().get(2) : "La partie à l'index 2 devrait être touchée";
        assert porteAvion.estCoule() : "Le bateau devrait être coulé";
    }
}
