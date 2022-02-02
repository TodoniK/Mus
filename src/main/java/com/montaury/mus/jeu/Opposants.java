package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;
import java.util.Iterator;
import java.util.List;
import com.montaury.mus.jeu.joueur.Equipe;

public class Opposants {
  private Joueur joueurEsku;
  private Joueur joueurZaku;
  private Joueur joueurNeutre1;
  private Joueur joueurNeutre2;

  public Opposants(Equipe equipe1,Equipe equipe2) {
    this.joueurEsku = equipe1.getJoueur1();
    this.joueurZaku = equipe2.getJoueur1();
    this.joueurNeutre1 = equipe1.getJoueur2();
    this.joueurNeutre2 = equipe2.getJoueur2();


  }

  public void tourner() {
    var tmp = joueurEsku;
    joueurEsku = joueurZaku;
    joueurZaku = tmp;
  }

  public Joueur joueurEsku() {
    return joueurEsku;
  }

  public Joueur joueurZaku() {
    return joueurZaku;
  }

  public List<Joueur> dansLOrdre() {
    return List.of(joueurEsku, joueurZaku);
  }
}
