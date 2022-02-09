package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.*;

import com.montaury.mus.jeu.joueur.Equipe;

public class Opposants {
  private Deque<Joueur> fileJoueurs = new LinkedList<>();
  private Equipe equipe1;
  private Equipe equipe2;


  public Opposants(Equipe equipe1,Equipe equipe2) {

    this.setEquipe1(equipe1);
    this.setEquipe2(equipe2);
    this.fileJoueurs.add(equipe1.getJoueur1());
    this.fileJoueurs.add(equipe2.getJoueur2());
    this.fileJoueurs.add(equipe1.getJoueur2());
    this.fileJoueurs.add(equipe2.getJoueur1());

  }

  public void tourner() {
    var tmp = this.fileJoueurs.remove();
    this.fileJoueurs.add(tmp);
  }

  public Joueur joueurEsku() {
    return this.fileJoueurs.getFirst();
  }

  public Joueur joueurZaku() {
   return this.fileJoueurs.getLast();
  }

  public List<Joueur> dansLOrdre() {
    var pos1 = fileJoueurs.remove();
    var pos2 = fileJoueurs.remove();
    var pos3 = fileJoueurs.remove();
    var pos4 = fileJoueurs.remove();
    fileJoueurs.add(pos1);
    fileJoueurs.add(pos2);
    fileJoueurs.add(pos3);
    fileJoueurs.add(pos4);
    return List.of(pos1, pos2, pos3, pos4);
  }

  public Equipe getEquipe1() {
    return equipe1;
  }

  public void setEquipe1(Equipe equipe1) {
    this.equipe1 = equipe1;
  }

  public Equipe getEquipe2() {
    return equipe2;
  }

  public void setEquipe2(Equipe equipe2) {
    this.equipe2 = equipe2;
  }
}
