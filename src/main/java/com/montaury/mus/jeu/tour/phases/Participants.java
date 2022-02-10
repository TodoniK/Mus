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


  public Joueur allieDe(Joueur joueurParlant) {
    if(joueurParlant.getEquipe().getJoueur1()==joueurParlant){return joueurParlant.getEquipe().getJoueur2();}
    else{return joueurParlant.getEquipe().getJoueur1();}
  }
  public Joueur adversaireDe(Joueur joueurParlant) {
    int i;
    for (i = 0; i < dansLOrdre.size(); i++)
    {
      if (joueurParlant.getEquipe()!=dansLOrdre.get(i).getEquipe())
      {break;}
    }
    return dansLOrdre.get(i);
  }

  public Iterable<Joueur> dansLOrdre() {
    return dansLOrdre;
  }

  public Participants retirer(Joueur joueur) {
    var joueurs = new ArrayList<>(dansLOrdre);
    joueurs.remove(joueur);
    return new Participants(joueurs);
  }
}
