package jeu;

import java.util.ArrayList;
import java.util.Iterator;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte>{
	private ArrayList<Carte> main = new ArrayList<Carte>();
	
	public void prendre(Carte carte) {
		main.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert(main.contains(carte));
		main.remove(carte);
	}
	
	@Override
	public String toString() {
		StringBuilder retour = new StringBuilder();
		for (Iterator<Carte> iterator = main.iterator(); iterator.hasNext();) {
			Carte carte = iterator.next();
			retour.append(carte);
			retour.append("\n");
		}
		return retour.toString();
	}
	
	@Override
	public Iterator<Carte> iterator() {
		return main.iterator();
	}
}
