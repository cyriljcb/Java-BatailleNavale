package Historique;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoriquePartie {
    private final String resultat;
    private final LocalDateTime dateHeure;

    public HistoriquePartie(String resultat, LocalDateTime dateHeure) {
        this.resultat = resultat;
        this.dateHeure = dateHeure;
    }

    public String getResultat() {
        return resultat;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    @Override
    public String toString() {
        return String.format("Résultat: %s | Date et heure: %s", resultat, dateHeure);
    }

    public String toCSV() {
        return String.format("%s,%s", resultat, dateHeure);
    }

    public static void enregistrerHistoriquePartie(List<HistoriquePartie> historiqueParties, String cheminFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, true))) {
            for (HistoriquePartie partie : historiqueParties) {
                writer.write(partie.toCSV());
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
        testEnregistrerHistoriquePartie();
        System.out.println("Tous les tests ont réussi !");
    }

    static void testConstructor() {
        LocalDateTime dateHeure = LocalDateTime.now();
        HistoriquePartie partie = new HistoriquePartie("Victoire", dateHeure);
        assert "Victoire".equals(partie.getResultat()) : "Résultat incorrect";
        assert partie.getDateHeure().equals(dateHeure) : "Date et heure incorrectes";
    }

    static void testToString() {
        LocalDateTime dateHeure = LocalDateTime.now();
        HistoriquePartie partie = new HistoriquePartie("Victoire", dateHeure);
        String expected = String.format("Résultat: Victoire | Date et heure: %s", dateHeure);
        assert expected.equals(partie.toString()) : "La méthode toString() retourne une valeur incorrecte";
    }

    static void testToCSV() {
        LocalDateTime dateHeure = LocalDateTime.now();
        HistoriquePartie partie = new HistoriquePartie("Victoire", dateHeure);
        String expected = String.format("Victoire,%s", dateHeure);
        assert expected.equals(partie.toCSV()) : "La méthode toCSV() retourne une valeur incorrecte";
    }

    static void testEnregistrerHistoriquePartie() {
        LocalDateTime dateHeure = LocalDateTime.now();
        List<HistoriquePartie> historiqueParties = new ArrayList<>();
        historiqueParties.add(new HistoriquePartie("Victoire", dateHeure));
        historiqueParties.add(new HistoriquePartie("Défaite", dateHeure.plusDays(1)));

        String cheminFichier = "historique_parties_test.csv";
        HistoriquePartie.enregistrerHistoriquePartie(historiqueParties, cheminFichier);
        System.out.println("Vérifiez manuellement le fichier 'historique_parties_test.csv' pour vous assurer que les données sont correctement enregistrées.");
    }
}
