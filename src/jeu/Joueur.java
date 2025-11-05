package jeu;

import java.util.HashSet;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu = new ZoneDeJeu();
	private MainJoueur main = new MainJoueur();
	
	public Joueur(String nom) {
		this.nom = nom;
	}
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if(sabot.estVide())
			return null;
		Carte carte = sabot.piocher();
		main.prendre(carte);
		return carte;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> coups = new HashSet<>();
		for(Joueur adversaire : participants) {
			for(Carte carte : this.main) {
				Coup coup = new Coup(this, carte, adversaire);
				if(coup.estValide())
					coups.add(coup);
			}
		}
		return coups;
	}
	
	public Set<Coup> coupsDefausse(){
		Set<Coup> coups = new HashSet<>();
		for(Carte carte : this.main) {
			Coup coup = new Coup(this, carte, null);
			if(coup.estValide())
				coups.add(coup);
		}
		return coups;
	}
	
	

	@Override
	public String toString() {
		return nom;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Joueur joueur  && (this.nom).equals(joueur.nom);
	}
}
