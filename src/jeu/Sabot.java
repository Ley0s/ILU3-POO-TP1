package jeu;

import java.util.ConcurrentModificationException;
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
	
	public Carte piocher() {
		Iterator<Carte> it = iterator();
		if(it.hasNext()) {
			Carte carte = it.next();
			it.remove();
			return carte;
		}else {
			return null;
		}
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
			verificationConcurrence();
			if(hasNext()) {
				Carte carte = pioche[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			}else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove() {
			verificationConcurrence();
			if(estVide() || !nextEffectue) {
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur-1; i < nbCartes-1; i++) {
				pioche[i] = pioche[i+1];
			}
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nbOperations++;
			nbOperationsReference++;
		}
		
		private void verificationConcurrence() {
			if(nbOperations!=nbOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}
	}
}
