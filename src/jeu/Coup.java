package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.Limite;

public class Coup {
	private Joueur jCourant;
	private Carte carteJoue;
	private Joueur jCible;
	
	public Coup(Joueur jCourant, Carte carteJoue, Joueur jCible) {
		this.jCourant = jCourant;
		this.carteJoue = carteJoue;
		this.jCible = jCible;
	}
	
	public boolean estValide() {
		if(jCible == null)
			return true;
		if((carteJoue instanceof Limite) || (carteJoue instanceof Attaque))
			return !jCible.equals(jCourant) && jCible.estDepotAutorise(carteJoue);
		else
			return jCible.equals(jCourant) && jCible.estDepotAutorise(carteJoue);
	}
	
	public Joueur getjCourant() {
		return jCourant;
	}
	public Carte getCarteJoue() {
		return carteJoue;
	}
	public Joueur getjCible() {
		return jCible;
	}
	
	@Override
	public String toString() {
		if(jCible == null)
			return "defausse la carte " + carteJoue.toString();
		else
			return "depose la carte " + carteJoue.toString() 
				+ " dans la zone de jeu de " + jCible.toString();
	}
}
