package com.montaury.mus;

import com.montaury.mus.jeu.Partie;
import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.Opposants;
import java.util.Scanner;
import com.montaury.mus.jeu.joueur.Equipe;

public class JeuDeMus {
  public static void main(String[] args) {
    System.out.print("Entrez votre nom: ");
    var nomJoueur = new Scanner(System.in).next();
    var joueurHumain = Joueur.humain(nomJoueur);
    var equipe1 = new Equipe (1, joueurHumain, Joueur.ordinateur("OrdinateurAllié"));
    var equipe2 = new Equipe (2, Joueur.ordinateur("OrdinateurAdverse1"), Joueur.ordinateur("OrdinateurAdverse2"));

    var partie = new Partie(new AffichageEvenements(joueurHumain));
    var resultat = partie.jouer(new Opposants(equipe1, equipe2));

    System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().getNum());
  }
}
