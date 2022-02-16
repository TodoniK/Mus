package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import java.util.ArrayList;
import java.util.List;

public class Participants {
  private final List<Joueur> dansLOrdre;

  public Participants(List<Joueur> dansLOrdre) {
    this.dansLOrdre = dansLOrdre;
  }

  public boolean aucun() {
    return dansLOrdre.isEmpty();
  }

  public boolean estUnique() {
    if (dansLOrdre.size()==1){return true;}
    else if (dansLOrdre.size()==2)
    {
      if (dansLOrdre.get(0).getEquipe()==dansLOrdre.get(1).getEquipe()){return true;}
      else {return false;}
    }
    else{return false;}
  }

  public Joueur premier() {
    return dansLOrdre.get(0);
  }



  public List<Joueur> adversairesDe(Joueur joueurParlant, List<Joueur>joueurs) {
    int i;
    List<Joueur> listeAdversaires = new ArrayList<>();
    for (i = 0; i < joueurs.size(); i++)
    {
      if (joueurParlant.getEquipe()!=joueurs.get(i).getEquipe())
      {
        listeAdversaires.add(joueurs.get(i));
      }
    }
    return listeAdversaires;
  }


  public List<Joueur> dansLOrdre() {
    return dansLOrdre;
  }

  public Participants retirer(Joueur joueur) {
    var joueurs = new ArrayList<>(dansLOrdre);
    joueurs.remove(joueur);
    return new Participants(joueurs);
  }
}
