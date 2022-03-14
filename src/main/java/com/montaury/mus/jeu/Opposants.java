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
    var tmp = this.fileJoueurs.removeFirst();
    this.fileJoueurs.addLast(tmp);
  }

  public Joueur joueurEsku() {
    return this.fileJoueurs.getFirst();
  }

  public Joueur joueurZaku() {
   return this.fileJoueurs.getLast();
  }

  public List<Joueur> dansLOrdre() {


    fileJoueurs.add(equipe1.getJoueur1());
    fileJoueurs.add(equipe2.getJoueur2());
    fileJoueurs.add(equipe1.getJoueur2());
    fileJoueurs.add(equipe2.getJoueur1());

    var Copie = new ArrayDeque<>(fileJoueurs);
    var pos1 = Copie.remove();
    var pos2 = Copie.remove();
    var pos3 = Copie.remove();
    var pos4 = Copie.remove();
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
