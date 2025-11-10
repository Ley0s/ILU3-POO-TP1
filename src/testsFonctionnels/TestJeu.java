package testsFonctionnels;

import jeu.Jeu;
import jeu.Joueur;

public class TestJeu {
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Joueur joueur1 = new Joueur("Jack");
		Joueur joueur2 = new Joueur("Bill");
		Joueur joueur3 = new Joueur("Luffy");
		jeu.inscrire(joueur1, joueur2, joueur3);
		jeu.distribuerCartes();
		System.out.println(jeu.lancer());
	}
}