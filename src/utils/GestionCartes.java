package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	private static Random random = new Random();
	
	public static <T> T extraire(List<T> liste) {
		return liste.remove(random.nextInt(liste.size()));
	}

	public static <T> T extraireIt(List<T> liste) {
		int indice = random.nextInt(liste.size());
		ListIterator<T> iter = liste.listIterator(indice);
		T retour = iter.next();
		iter.remove();
		return retour;
	}

	public static <T> List<T> melanger(List<T> liste) {
		List<T> nouv = new ArrayList<>();
		int taille = liste.size();
		for (int i = 0; i < taille; i++) {
			nouv.add(extraire(liste));
		}
		return nouv;
	}

	public static <T> boolean verifierMelange(List<T> liste1, List<T> liste2) {
		if (liste1.size() != liste2.size()) {
			return false;
		}
		for (T elem : liste1) {
			if (Collections.frequency(liste1, elem) != Collections.frequency(liste2, elem))
				return false;
		}
		return true;
	}

	public static <T> List<T> rassembler(List<T> liste) {
		List<T> retour = new ArrayList<>();
		ListIterator<T> iter = liste.listIterator();
		while (iter.hasNext()) {
			T elt = iter.next();
			if (!retour.contains(elt)) {
				int nbOcc = Collections.frequency(liste, elt);
				for (int j = 0; j < nbOcc; j++) {
					retour.add(elt);
				}
			}
		}
		return retour;
	}

	private static <T> boolean presence(List<T> l, T elem, int indice) {
		for(ListIterator<T> it = l.listIterator(indice); it.hasNext(); ) {
			if(it.next().equals(elem)){
				return false;
			}
		}
		return true;
	}

	public static <T> boolean verifierRassemblement(List<T> liste) {
		int indice = 0;
		for (ListIterator<T> it = liste.listIterator(); it.hasNext();) {
			T elemPrec = it.next();
			if(it.hasNext()) {
				T elem = it.next();
				indice++;
				if(!elem.equals(elemPrec) && presence(liste, elem, indice))
					return false;
			}
		}
		return true;
	}
}
