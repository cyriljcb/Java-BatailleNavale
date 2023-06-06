package Historique;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueCoup {
    private final String etat;
    private final int x;
    private final int y;
    private final LocalDateTime heure;

    public HistoriqueCoup(String etat, int x, int y, LocalDateTime heure) {
        this.etat = etat;
        this.x = x;
        this.y = y;
        this.heure = heure;
    }

    public String getEtat() {
        return etat;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public LocalDateTime getHeure() {
        return heure;
    }

    @Override
    public String toString() {
        return String.format("Etat: %s | Emplacement: (%d, %d) | Heure: %s", etat, x, y, heure);
    }

    public String toCSV() {
        return String.format("%s,%d,%d,%s", etat, x, y, heure);
    }

    public static void enregistrerHistoriqueCoup(List<HistoriqueCoup> historiqueCoups, String cheminFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (HistoriqueCoup coup : historiqueCoups) {
                writer.write(coup.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testConstructor();
        testToString();
        testToCSV();
        testEnregistrerHistoriqueCoup();
        System.out.println("Tous les tests ont réussi !");
    }

    static void testConstructor() {
        LocalDateTime heure = LocalDateTime.now();
        HistoriqueCoup coup = new HistoriqueCoup("Touché", 1, 2, heure);
        assert "Touché".equals(coup.getEtat()) : "Etat incorrect";
        assert coup.getX() == 1 : "Position en x incorrecte";
        assert coup.getY() == 2 : "Position en y incorrecte";
        assert coup.getHeure().equals(heure) : "Heure incorrecte";
    }

    static void testToString() {
        LocalDateTime heure = LocalDateTime.now();
        HistoriqueCoup coup = new HistoriqueCoup("Touché", 1, 2, heure);
        String expected = String.format("Etat: Touché | Emplacement: (1, 2) | Heure: %s", heure);
        assert expected.equals(coup.toString()) : "La méthode toString() retourne une valeur incorrecte";
    }

    static void testToCSV() {
        LocalDateTime heure = LocalDateTime.now();
        HistoriqueCoup coup = new HistoriqueCoup("Touché", 1, 2, heure);
        String expected = String.format("Touché,1,2,%s", heure);
        assert expected.equals(coup.toCSV()) : "La méthode toCSV() retourne une valeur incorrecte";
    }

    static void testEnregistrerHistoriqueCoup() {
        LocalDateTime heure = LocalDateTime.now();
        List<HistoriqueCoup> historiqueCoups = new ArrayList<>();
        historiqueCoups.add(new HistoriqueCoup("Touché", 1, 2, heure));

    }
}