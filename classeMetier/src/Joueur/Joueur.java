package Joueur;

import Bateau.*;
import Singleton.Partie;

import java.util.List;

public abstract class Joueur {
    private List<Bateau> bateaux;
    private int[][] grille;
    private int[][] grilleTirs;
    private String nom;
    // Positions et orientations des bateaux
    private String porteAvionsPosition;
    private String porteAvionsOrientation;
    private String croiseurPosition;
    private String croiseurOrientation;
    private String sousMarinPosition;
    private String sousMarinOrientation;
    private String contreTorpilleurPosition;
    private String contreTorpilleurOrientation;
    private String torpilleurPosition;
    private String torpilleurOrientation;
    public Joueur(String nom) {
        this.nom = nom;
        this.grille = new int[10][10];
        this.grilleTirs = new int[10][10];
    }

    public String getNom() {
        return nom;
    }

    public List<Bateau> getBateaux() {
        return bateaux;
    }

    public int[][] getGrille() {
        return grille;
    }
    public void setGrille(int[][] nouvelleGrille) {
        this.grille = nouvelleGrille;
    }

    public int[][] getGrilleTirs() {
        return grilleTirs;
    }

    // Getters et setters pour les positions et orientations des bateaux
    public String getPorteAvionsPosition() {
        return porteAvionsPosition;
    }

    public void setPorteAvionsPosition(String porteAvionsPosition) {
        this.porteAvionsPosition = porteAvionsPosition;
    }

    public String getPorteAvionsOrientation() {
        return porteAvionsOrientation;
    }

    public void setPorteAvionsOrientation(String porteAvionsOrientation) {
        this.porteAvionsOrientation = porteAvionsOrientation;
    }

    public String getCroiseurPosition() {
        return croiseurPosition;
    }

    public void setCroiseurPosition(String croiseurPosition) {
        this.croiseurPosition = croiseurPosition;
    }

    public String getCroiseurOrientation() {
        return croiseurOrientation;
    }

    public void setCroiseurOrientation(String croiseurOrientation) {
        this.croiseurOrientation = croiseurOrientation;
    }

    public String getSousMarinPosition() {
        return sousMarinPosition;
    }

    public void setSousMarinPosition(String sousMarinPosition) {
        this.sousMarinPosition = sousMarinPosition;
    }

    public String getSousMarinOrientation() {
        return sousMarinOrientation;
    }

    public void setSousMarinOrientation(String sousMarinOrientation) {
        this.sousMarinOrientation = sousMarinOrientation;
    }

    public String getContreTorpilleurPosition() {
        return contreTorpilleurPosition;
    }

    public void setContreTorpilleurPosition(String contreTorpilleurPosition) {
        this.contreTorpilleurPosition = contreTorpilleurPosition;
    }

    public String getContreTorpilleurOrientation() {
        return contreTorpilleurOrientation;
    }

    public void setContreTorpilleurOrientation(String contreTorpilleurOrientation) {
        this.contreTorpilleurOrientation = contreTorpilleurOrientation;
    }

    public String getTorpilleurPosition() {
        return torpilleurPosition;
    }
    public void setTorpilleurPosition(String torpilleurPosition) {
        this.torpilleurPosition = torpilleurPosition;
    }

    public String getTorpilleurOrientation() {
        return torpilleurOrientation;
    }
    public void setTorpilleurOrientation(String torpilleurOrientation) {
        this.torpilleurOrientation = torpilleurOrientation;
    }

    public boolean tousLesBateauxSontCoules() {
        for (Bateau bateau : bateaux) {
            if (!bateau.estCoule()) {
                return false;
            }
        }
        return true;
    }

}

