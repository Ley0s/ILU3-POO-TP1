package testsFonctionnels;

import java.util.Iterator;

import cartes.Botte;
import cartes.Carte;
import cartes.JeuDeCartes;
import jeu.Sabot;

public class TestSabot {
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		Sabot sabot = new Sabot(jeu.donnerCartes());
		
		while (!sabot.estVide()) {
			Carte carte = sabot.piocher();
			System.out.println("Je pioche " + carte);
		}

		/*for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
			sabot.piocher();
			sabot.ajouterCarte(new Botte(cartes.Type.ACCIDENT));
			Carte carte = iterator.next();
			System.out.println("Je pioche " + carte);
			iterator.remove();
		}*/
	}
}
