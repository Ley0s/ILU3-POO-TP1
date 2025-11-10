package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import cartes.Botte;
import cartes.Carte;
import utils.GestionCartes;

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
	
	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}
	
	public void deposer(Carte carte) {
		zoneDeJeu.deposer(carte);
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		ArrayList<Coup> coups = new ArrayList<>(coupsPossibles(participants));
		if(coups.isEmpty())
			coups.addAll(coupsDefausse());
		return GestionCartes.extraire(coups);
	}
	
	public String afficherMain() {
		return main.toString();
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public String afficherEtatJoueur() {
		StringBuilder retour = new StringBuilder();
		
		retour.append("Bottes :\n");
		for (Botte botte : zoneDeJeu.getBottes()) {
			retour.append(" - " + botte.toString());
		}
		
		retour.append("\nLimitation de vitesse ? : ");
		if(zoneDeJeu.donnerLimitationVitesse() == 200)
			retour.append("non\n");
		else
			retour.append("oui\n");
		
		retour.append("\nSommet pile bataille : ");
		if(zoneDeJeu.getBatailles().isEmpty())
			retour.append("null\n");
		else
			retour.append(zoneDeJeu.getBatailles().get(0).toString() + "\n");
		
		retour.append("\nMain :\n");
		retour.append(main.toString());
		
		return retour.toString();
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
