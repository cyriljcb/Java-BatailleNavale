package Singleton;

import Bateau.Bateau;
import Bateau.Croiseur;
import Historique.HistoriqueCoup;
import java.time.LocalDateTime;
import java.util.List;

public class PartieTest {

    public static void main(String[] args) {
        testGetInstance();
        testAjouterBateauJoueur();
        testAjouterHistoriqueCoup();
        System.out.println("Tous les tests ont réussi !");
    }

    static void testGetInstance() {
        Partie partie1 = Partie.getInstance();
        Partie partie2 = Partie.getInstance();
        assert partie1 != null : "L'instance de Partie ne doit pas être null";
        assert partie1 == partie2 : "Les instances de Partie doivent être les mêmes (singleton)";
    }

    static void testAjouterBateauJoueur() {
        Partie partie = Partie.getInstance();
        Bateau bateau1 = new Croiseur("B2", true);
        partie.ajouterBateauJoueur1(bateau1);

        List<Bateau> bateauxJoueur1 = partie.getBateauxJoueur1();
        assert bateauxJoueur1.contains(bateau1) : "Le bateau doit être ajouté à la liste des bateaux du joueur 1";
    }

    static void testAjouterHistoriqueCoup() {
        Partie partie = Partie.getInstance();
        HistoriqueCoup coup = new HistoriqueCoup("Touché", 1, 2, LocalDateTime.now());
        partie.ajouterHistoriqueCoup(coup);

        List<HistoriqueCoup> historiqueCoups = partie.getHistoriqueCoups();
        assert historiqueCoups.contains(coup) : "L'historique de coup doit être ajouté à la liste des historiques de coups";
    }
}
