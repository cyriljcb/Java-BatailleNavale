package Bateau;

public abstract class Bateau {
    private String Nom;
    protected int taille;
    protected int coupRecu;
    protected boolean estCoule;
    protected boolean estHorizontal;
    protected String position;

    public Bateau(int taille, String position, boolean estHorizontal) {
        this.taille = taille;
        this.position = position;
        this.estHorizontal = estHorizontal;
        this.coupRecu = 0;
        this.estCoule = false;
    }
    public void setPosition(String newPosition) {
        this.position = newPosition;
    }

    public void setOrientation(boolean newOrientation) {
        this.estHorizontal = newOrientation;
    }
    public int getTaille() {
        return taille;
    }

    public int getCoupRecu() {
        return coupRecu;
    }

    public boolean estCoule() {
        return getCoupRecu() == getTaille();
    }

    public boolean estHorizontal() {
        return estHorizontal;
    }

    public String getPosition() {

        return position;
    }

    public void hit() {
        coupRecu++;
        if (coupRecu == taille) {
            estCoule = true;
        }
    }
    public abstract int getBoatId();
    public abstract String getType();
    public boolean estALaPosition(String tir) {
        int tirColonne = tir.charAt(0) - 'A';
        int tirLigne = Integer.parseInt(tir.substring(1)) - 1;

        int departColonne = position.charAt(0) - 'A';
        int departLigne = Integer.parseInt(position.substring(1)) - 1;

        // vérifie si le tir est dans la même ligne/colonne que le bateau
        if (estHorizontal && tirLigne == departLigne) {
            return tirColonne >= departColonne && tirColonne < departColonne + taille;
        } else if (!estHorizontal && tirColonne == departColonne) {
            return tirLigne >= departLigne && tirLigne < departLigne + taille;
        }

        return false;
    }
    public boolean contient(String positionTir) {
        // Split position en lettres et chiffres
        String[] posParts = this.position.split("");
        int posRow = posParts[0].toUpperCase().charAt(0) - 'A' + 1;
        int posCol = Integer.parseInt(posParts[1]);

        // Split positionTir en lettres et chiffres
        String[] tirParts = positionTir.split("");
        int tirRow = tirParts[0].toUpperCase().charAt(0) - 'A' + 1;
        int tirCol = Integer.parseInt(tirParts[1]);

        // Vérifie si le tir est dans la même ligne ou colonne que la position du bateau
        if (estHorizontal) {
            return (posRow == tirRow) && (tirCol >= posCol) && (tirCol < posCol + taille);
        } else {
            return (posCol == tirCol) && (tirRow >= posRow) && (tirRow < posRow + taille);
        }
    }
    public boolean bateauTouche(String tir) {
        int tirColonne = tir.charAt(0) - 'A';
        int tirLigne = Integer.parseInt(tir.substring(1)) - 1;

        int departColonne = position.charAt(0) - 'A';
        int departLigne = Integer.parseInt(position.substring(1)) - 1;

        // vérifie si le tir est dans le même ligne/colonne que le bateau
        if (estHorizontal&& tirLigne == departLigne) {
            return tirColonne >= departColonne && tirColonne < departColonne + taille;
        } else if (estHorizontal && tirColonne == departColonne) {
            return tirLigne >= departLigne && tirLigne < departLigne + taille;
        }

        return false;
    }
    public void setEstCoule(boolean estCoule) {
        this.estCoule = estCoule;
    }
}
