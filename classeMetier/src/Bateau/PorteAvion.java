package Bateau;

import java.util.Vector;

public class PorteAvion extends Bateau {
    public Vector<Boolean> etat;

    public PorteAvion(String position, boolean estHorizontal) {
        super(5, position, estHorizontal);
        etat = new Vector<>(5);
        for (int i = 0; i < 5; i++) {
            etat.add(false);
        }
    }

    @Override
    public String getType() {
        return "Porte-avion";
    }

    public Vector<Boolean> getEtat() {
        return etat;
    }
    @Override
    public int getBoatId() {
        return 5;
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
        PorteAvion porteAvion = new PorteAvion("B2", true);
        assert porteAvion.getTaille() == 5 : "Taille incorrecte";
        assert "B2".equals(porteAvion.getPosition()) : "Position incorrecte";
        assert porteAvion.estHorizontal() : "Orientation incorrecte";
    }

    static void testGetType() {
        PorteAvion porteAvion = new PorteAvion("B2", true);
        assert "Porte-avion".equals(porteAvion.getType()) : "Type incorrect";
    }

    static void testIncrementerNbCasesTouchees() {
        PorteAvion porteAvion = new PorteAvion("B2", true);
        for (int i = 0; i < 5; i++) {
            porteAvion.hit();
        }
        assert porteAvion.getCoupRecu() == 5 : "Nombre de cases touchées incorrect";
        assert porteAvion.estCoule() : "Le bateau devrait être coulé";
    }

    static void testContient() {
        PorteAvion porteAvion = new PorteAvion("B2", true);
        assert porteAvion.contient("B2") : "Le bateau devrait contenir la position (B2)";
        assert porteAvion.contient("C2") : "Le bateau devrait contenir la position (C2)";
        assert porteAvion.contient("D2") : "Le bateau devrait contenir la position (D2)";
        assert porteAvion.contient("E2") : "Le bateau devrait contenir la position (E2)";
        assert porteAvion.contient("F2") : "Le bateau devrait contenir la position (F2)";
        assert !porteAvion.contient("G2") : "Le bateau ne devrait pas contenir la position (G2)";
        assert !porteAvion.contient("B3") : "Le bateau ne devrait pas contenir la position (B3)";
    }

    static void testEtat() {
        PorteAvion porteAvion = new PorteAvion("B2", true);
        Vector<Boolean> etatInitial = porteAvion.getEtat();
        for (Boolean etat : etatInitial) {
            assert !etat : "L'état initial de toutes les parties du bateau doit être false";
        }
    }

    static void testToucherPartie() {
        PorteAvion porteAvion = new PorteAvion("B2", true);
        for (int i = 0; i < 3; i++) {
            porteAvion.toucherPartie(i);
            assert porteAvion.getEtat().get(i) : "La partie à l'index " + i + " devrait être touchée";
            assert !porteAvion.estCoule() : "Le bateau ne devrait pas être coulé";
        }
        porteAvion.toucherPartie(3);
        assert porteAvion.getEtat().get(3) : "La partie à l'index 3 devrait être touchée";
        porteAvion.toucherPartie(4);
        assert porteAvion.getEtat().get(4) : "La partie à l'index 4 devrait être touchée";
        assert porteAvion.estCoule() : "Le bateau devrait être coulé";
    }
}
