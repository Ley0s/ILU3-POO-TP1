package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Carte;
import cartes.Parade;
import cartes.Type;

public class TestMethodeEquals {

	public static void main(String[] args) {
		Carte carte1 = new Borne(25);
		Carte carte2 = new Borne(25);
		System.out.println("Deux cartes de 25km sont identiques ? " + carte1.equals(carte2));
		
		carte1 = new Attaque(Type.FEU);
		carte2 = new Attaque(Type.FEU);
		System.out.println("Deux cartes de feux rouge sont identiques ? " + carte1.equals(carte2));
		
		carte2 = new Parade(Type.FEU);
		System.out.println("La carte feu rouge et la carte feu vert sont identiques ? " + carte1.equals(carte2));
	}

}
