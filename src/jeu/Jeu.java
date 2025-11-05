package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private Set<Joueur> joueurs = new HashSet<>();

	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		Carte[] cartes = jeu.donnerCartes();
		
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		
		listeCartes = GestionCartes.melanger(listeCartes);
		
		cartes = (Carte[]) listeCartes.toArray();
		Sabot sabot = new Sabot(cartes);
		this.sabot = sabot;
	}
	
	public void inscrire(Joueur... ajouts) {
		for (int i = 0; i < ajouts.length; i++) {
			joueurs.add(ajouts[i]);
		}
	}
}
