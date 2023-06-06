package Controller;

import Tir.Tir;
import Bateau.Bateau;

public class TirController {
    private Tir tir;

    public TirController(Tir tir) {
        this.tir = tir;
    }

    public String getPosition() {
        return tir.getPosition();
    }

    public boolean isTouche() {
        return tir.isTouche();
    }

    public void setTouche(boolean touche) {
        tir.setTouche(touche);
    }

    public boolean isCoule() {
        return tir.isCoule();
    }

    public void setCoule(boolean coule) {
        tir.setCoule(coule);
    }

    public Bateau getBateauTouche() {
        return tir.getBateauTouche();
    }

    public void setBateauTouche(Bateau bateauTouche) {
        tir.setBateauTouche(bateauTouche);
    }
}
