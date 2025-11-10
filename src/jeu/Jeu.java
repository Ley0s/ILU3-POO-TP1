package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private static final int NBCARTES = 6;
	
	private Sabot sabot;
	private Set<Joueur> joueurs = new HashSet<>();
	private Iterator<Joueur> iterJoueurs;

	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		Carte[] cartes = jeu.donnerCartes();
		
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		
		listeCartes = GestionCartes.melanger(listeCartes);
		
		cartes = listeCartes.toArray(cartes);
		Sabot sabot = new Sabot(cartes);
		this.sabot = sabot;
		
		iterJoueurs = joueurs.iterator();
	}
	
	public void inscrire(Joueur... ajouts) {
		for (int i = 0; i < ajouts.length; i++) {
			joueurs.add(ajouts[i]);
		}
	}
	
	public void distribuerCartes() {
		for (int i = 0; i < NBCARTES; i++) {
			for (Joueur joueur : joueurs) {
				joueur.prendreCarte(sabot);
			}
		}
	}
	
	public String jouerTour(Joueur joueur) {
		StringBuilder retour = new StringBuilder();
		
		Carte cartePiochee = joueur.prendreCarte(sabot);
		retour.append("Le joueur " + joueur.toString() 
						+ " a pioche " + cartePiochee.toString() + "\n");
		
		retour.append(joueur.toString() + joueur.afficherMain());
		
		Coup coup = joueur.choisirCoup(joueurs);
		retour.append(joueur.toString() + " " + coup.toString() + "\n");
		
		Carte carteJouee = coup.getCarteJoue();
		Joueur joueurCible = coup.getjCible();
		joueur.retirerDeLaMain(carteJouee);
		if(joueurCible == null)
			sabot.ajouterCarte(carteJouee);
		else
			joueurCible.deposer(carteJouee);
		
		return retour.toString();
	}
	
	public Joueur donnerJoueurSuivant() {
		if (!iterJoueurs.hasNext())
			iterJoueurs = joueurs.iterator();
		return iterJoueurs.next();
	}
	
	public String lancer() {
		Joueur joueurCourant = donnerJoueurSuivant();
		StringBuilder deroulement = new StringBuilder();
		
		boolean joueurGagne = false;
		boolean sabotVide = false;
		while (!joueurGagne && !sabotVide) {
			deroulement.append(jouerTour(joueurCourant) + "\n");
			if (joueurCourant.donnerKmParcourus() >= 1000) {
				joueurGagne = true;
			} else if (sabot.estVide()) {
				sabotVide = true;
			} else {
				joueurCourant = donnerJoueurSuivant();
			}
		}
		
		if (joueurGagne) {
			deroulement.append("Le joueur " + joueurCourant.toString() + " a gagné.");
		} else {
			deroulement.append("Le sabot est vide.");
		}
		
		return deroulement.toString();
	}
	
	public Sabot getSabot() {
		return sabot;
	}
	
	
}