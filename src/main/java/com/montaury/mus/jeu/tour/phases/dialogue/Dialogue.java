package com.montaury.mus.jeu.tour.phases.dialogue;

import com.montaury.mus.jeu.Manche;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.Participants;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix;

import java.util.*;
import java.util.stream.Collectors;

import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.IDOKI;
import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.KANTA;
import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.TIRA;

public class Dialogue {
  private final Evenements affichage;

  public Dialogue(Evenements affichage) {
    this.affichage = affichage;
  }

  public final Recapitulatif derouler(Participants participants) {
    var deroulement = new Deroulement(participants);
    var choix = new ArrayList<ChoixJoueur>();
    while (!deroulement.estTermine()) {
      var joueurParlant = deroulement.prochainJoueur();
      var choixJoueur = joueurParlant.interfaceJoueur.faireChoixParmi(deroulement.choixPossibles());
      affichage.choixFait(joueurParlant, choixJoueur);
      choix.add(new ChoixJoueur(choixJoueur, joueurParlant));
      deroulement = choixJoueur.influerSur(deroulement);
    }
    return new Recapitulatif(choix);
  }

  public static class ChoixJoueur {
    public final Choix choix;
    public final Joueur joueur;

    public ChoixJoueur(Choix choix, Joueur joueur) {
      this.choix = choix;
      this.joueur = joueur;
    }

    public int mise() {
      return choix.mise();
    }
  }

  public static class Deroulement {

    public static Deroulement termine() {
      return new Deroulement(new Participants(Collections.emptyList()));
    }


    private Deque<Joueur> joueursDevantParler;
    private Participants participants;
    private List<TypeChoix> prochainsChoixPossibles = TypeChoix.INITIAUX;
    private Joueur joueurParlant;
    private Deque<Joueur> joueursEnCourseEquipe1=new ArrayDeque<>();
    private Deque<Joueur> joueursEnCourseEquipe2=new ArrayDeque<>();



    public Deroulement(Participants participants) {

      this.participants = participants;
      if (participants.dansLOrdre().isEmpty())
      {
        this.joueursDevantParler = new ArrayDeque<>(participants.dansLOrdre());
      }
      else {

        for (int i = 0; i < participants.dansLOrdre().size(); i++) {
          if (participants.dansLOrdre().get(i).getEquipe().getNum() == 1) {
            this.joueursEnCourseEquipe1.add(participants.dansLOrdre().get(i));
          } else {
            this.joueursEnCourseEquipe2.add(participants.dansLOrdre().get(i));
          }
        }

        this.joueursDevantParler = new ArrayDeque<>(participants.dansLOrdre());
      }

    }

    private Deroulement(Participants participants, Deque<Joueur> joueursDevantParler, List<TypeChoix> prochainsChoixPossibles) {
      this.participants = new Participants(participants.dansLOrdre());
      this.joueursDevantParler = joueursDevantParler;
      this.prochainsChoixPossibles = prochainsChoixPossibles;
    }

    public Joueur prochainJoueur() {
      joueurParlant = joueursDevantParler.remove();
      return joueurParlant;
    }

    public boolean estTermine() {
      return joueursDevantParler.isEmpty();
    }

    public List<TypeChoix> choixPossibles() {
      return prochainsChoixPossibles;
    }

    public Deroulement basculerSurAdversaire(List<TypeChoix> prochainsChoixPossibles) {

      joueursDevantParler = new ArrayDeque<>(participants.adversairesDe(joueurParlant,joueursEnCourseEquipe1,joueursEnCourseEquipe2));
      this.prochainsChoixPossibles = prochainsChoixPossibles;
      return this;
    }

    public Deroulement retirerJoueurParlant() {
      return new Deroulement(participants.retirer(joueurParlant,joueursEnCourseEquipe1,joueursEnCourseEquipe2), joueursDevantParler, prochainsChoixPossibles);
    }
  }

  public static class Recapitulatif {
    private final int pointsEngages;
    private final List<ChoixJoueur> mises;
    private final Choix dernierChoix;

    public Recapitulatif(List<ChoixJoueur> choix) {
      mises = choix.stream().filter(choixJoueur -> choixJoueur.choix.type().estUneMise()).collect(Collectors.toList());
      dernierChoix = choix.get(choix.size() - 1).choix;
      pointsEngages = calculerPointsEngages();
    }

    private int calculerPointsEngages() {
      if (dernierChoix.est(TIRA)) {
        return mises.size() == 1 ? 1 : mises.subList(0, mises.size() - 1).stream().mapToInt(ChoixJoueur::mise).sum();
      }
      if (dernierChoix.est(IDOKI)) {
        return mises.stream().mapToInt(ChoixJoueur::mise).sum();
      }
      if (dernierChoix.est(KANTA)) {
        return Manche.Score.POINTS_POUR_TERMINER_MANCHE;
      }
      // paso
      return 1;
    }

    public int pointsEngages() {
      return pointsEngages;
    }

    public Joueur dernierJoueurAyantMise() {
      return mises.get(mises.size() - 1).joueur;
    }

    public boolean terminePar(TypeChoix typeChoix) {
      return dernierChoix.est(typeChoix);
    }
  }
}