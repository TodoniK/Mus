package com.montaury.mus.jeu;

import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class MancheTest {

  @BeforeEach
  void setUp() {
    manche = new Manche(mock(Evenements.class));
  }

  @Test
  void devrait_terminer_la_manche_si_hordago_au_grand() {
    var equipe1 = new Equipe(1,unJoueurFaisantChoix(new Mintza(), new Hordago()),unJoueurFaisantChoix(new Mintza(), new Hordago()));
    var equipe2 = new Equipe(2,unJoueurFaisantChoix(new Kanta()),unJoueurFaisantChoix(new Kanta()));

    var resultat = manche.jouer(new Opposants(equipe1, equipe2));

    assertThat(resultat.vainqueur()).isNotNull();
    assertThat(resultat.pointsVaincu()).isZero();
  }

  @Test
  void devrait_terminer_la_manche_si_un_joueur_a_40_points() {
    var equipe1 = new Equipe (1,unJoueurFaisantChoix(new Mintza(), new Imido(), new Gehiago(2)),unJoueurFaisantChoix(new Mintza(), new Imido(), new Gehiago(2)));
    var equipe2 =new Equipe (2,unJoueurFaisantChoix(new Gehiago(40), new Tira()),unJoueurFaisantChoix(new Gehiago(40), new Tira())) ;

    var resultat = manche.jouer(new Opposants(equipe1, equipe2));

    assertThat(resultat.vainqueur()).isEqualTo(equipe1);
    assertThat(resultat.pointsVaincu()).isZero();
  }

  @Test
  void devrait_changer_l_ordre_des_opposants_a_la_fin_du_tour() {
    var equipe1 = new Equipe(1,unJoueurFaisantChoix(new Mintza(), new Hordago()),unJoueurFaisantChoix(new Mintza(), new Hordago()));
    var equipe2 = new Equipe(2,unJoueurFaisantChoix(new Kanta()),unJoueurFaisantChoix(new Kanta()));
    var opposants = new Opposants(equipe1, equipe2);

    manche.jouer(opposants);

    assertThat(opposants.dansLOrdre()).containsExactly(equipe2.getJoueur2(),equipe1.getJoueur2(),equipe2.getJoueur1(),equipe1.getJoueur1());
  }

  private Manche manche;
}
