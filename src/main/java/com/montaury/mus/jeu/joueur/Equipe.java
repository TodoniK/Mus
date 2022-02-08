package com.montaury.mus.jeu.joueur;
import java.util.List;
import java.util.*;


public class Equipe {

    // Attriuts
    private Joueur joueur1;
    private Joueur joueur2;
    private int num;

    // Constructeur
    public Equipe(int i, Joueur j1,Joueur j2)
    {
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.num = i;
        j1.setEquipe(this);
        j2.setEquipe(this);
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
