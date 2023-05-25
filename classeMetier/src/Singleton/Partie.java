package Singleton;
import Historique.HistoriquePartie;
import Historique.HistoriqueCoup;
import Bateau.Bateau;
import java.util.ArrayList;
import java.util.List;

public class Partie {
    private static Partie instance;

    private List<Bateau> bateauxJoueur1;
    private List<Bateau> bateauxJoueur2;

    private List<HistoriqueCoup> historiqueCoups;

    private Partie() {
        bateauxJoueur1 = new ArrayList<>();
        bateauxJoueur2 = new ArrayList<>();
        historiqueCoups = new ArrayList<>();
    }

    public static Partie getInstance() {
        if (instance == null) {
            instance = new Partie();
        }
        return instance;
    }

    public void ajouterBateauJoueur1(Bateau bateau) {
        bateauxJoueur1.add(bateau);
    }

    public void ajouterBateauJoueur2(Bateau bateau) {
        bateauxJoueur2.add(bateau);
    }

    public List<Bateau> getBateauxJoueur1() {
        return bateauxJoueur1;
    }

    public List<Bateau> getBateauxJoueur2() {
        return bateauxJoueur2;
    }

    public void ajouterHistoriqueCoup(HistoriqueCoup coup) {
        historiqueCoups.add(coup);
    }

    public List<HistoriqueCoup> getHistoriqueCoups() {
        return historiqueCoups;
    }

    // Ajoutez d'autres méthodes pour gérer le jeu, telles que vérifier si un bateau est touché, déterminer si un joueur a gagné, etc.
}
