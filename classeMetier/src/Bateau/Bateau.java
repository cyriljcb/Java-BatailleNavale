package Bateau;

public abstract class Bateau
{
    private String Nom;
    protected int taille;
    protected int nbCasesTouchees;
    protected boolean estCoule;
    protected boolean estHorizontal;
    protected int x;
    protected int y;


    public Bateau(int taille, int x, int y, boolean estHorizontal) {
        this.taille = taille;
        this.x = x;
        this.y = y;
        this.estHorizontal = estHorizontal;
        this.nbCasesTouchees = 0;
        this.estCoule = false;
    }

    public int getTaille() {
        return taille;
    }

    public int getNbCasesTouchees() {
        return nbCasesTouchees;
    }

    public boolean estCoule() {
        return estCoule;
    }

    public boolean estHorizontal() {
        return estHorizontal;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementerNbCasesTouchees() {
        nbCasesTouchees++;
        if (nbCasesTouchees == taille) {
            estCoule = true;
        }
    }

    public abstract String getType();

    public boolean contient(int x, int y) {
        if (estHorizontal) {
            return (this.y == y) && (x >= this.x) && (x < this.x + taille);
        } else {
            return (this.x == x) && (y >= this.y) && (y < this.y + taille);
        }
    }
    public void setEstCoule(boolean estCoule) {
        this.estCoule = estCoule;
    }
}