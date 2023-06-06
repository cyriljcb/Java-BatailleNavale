package Controller;

import Bateau.Bateau;

public class BateauController {
    private Bateau bateau;

    public BateauController(Bateau bateau) {
        this.bateau = bateau;
    }

    public int getTaille() {
        return bateau.getTaille();
    }

    public int getCoupRecu() {
        return bateau.getCoupRecu();
    }

    public boolean estCoule() {
        return bateau.estCoule();
    }

    public boolean estHorizontal() {
        return bateau.estHorizontal();
    }

    public String getPosition() {
        return bateau.getPosition();
    }

    public void incrementerNbCasesTouchees() {
        bateau.hit();
    }

    public String getType() {
        return bateau.getType();
    }

    public boolean contient(String position) {
        return bateau.contient(position);
    }

    public void setEstCoule(boolean estCoule) {
        bateau.setEstCoule(estCoule);
    }
}
