package jeu;

import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	private Carte[] pioche;
	private int nbCartes;
	private int capacite;
	private int nbOperations = 0;

	public Sabot(Carte[] pioche) {
		this.pioche = pioche;
		nbCartes = pioche.length;
		capacite = nbCartes;
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte nouv) {
		if(nbCartes+1 > capacite) {
			throw new ArrayIndexOutOfBoundsException();
		}
		pioche[nbCartes++] = nouv;
		nbOperations++;
	}
	
	@Override
	public Iterator<Carte> iterator(){
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte> {
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nbOperationsReference = nbOperations;
		
		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		@Override
		public Carte next() {
			if(hasNext()) {
				Carte carte = pioche[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			}else {
				throw new NoSuchElementException();
			}
		}
	}
}
