package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;

public class ZoneDeJeu {
	private List<Limite> limites = new ArrayList<Limite>();
	private List<Bataille> bataille = new ArrayList<Bataille>();
	private List<Borne> bornes = new ArrayList<Borne>();
	
	public int donnerLimitationVitesse() {
		if(limites.isEmpty() || limites. instanceof FinLimite)
			return 200;
		return 50;
	}
	
	public int donnerKmParcourus() {
		int dist = 0;
		for (Iterator<Borne> iterator = bornes.iterator(); iterator.hasNext();) {
			Borne borne = iterator.next();
			dist += borne.getKm();
		}
		return dist;
	}
	
	public void deposer(Carte carte) {
		if(carte instanceof Borne borne)
			bornes.add(borne);
		if(carte instanceof Limite limite)
			limites.add(limite);
		if(carte instanceof Bataille cbataille)
			bataille.add(cbataille);
	}
}
