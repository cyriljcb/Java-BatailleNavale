package Controller;

import Tir.Tir;
import Tir.Explosion;
import Bateau.Bateau;

public class ExplosionController {
    private Explosion explosion;

    public ExplosionController(Explosion explosion) {
        this.explosion = explosion;
    }

    public Tir evaluerTir(String pos) {
        return explosion.evaluerTir(pos);
    }
}
