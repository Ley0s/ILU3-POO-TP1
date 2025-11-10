package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	private List<Limite> limites = new ArrayList<Limite>();
	private List<Bataille> batailles = new ArrayList<Bataille>();
	private List<Borne> bornes = new ArrayList<Borne>();
	private Set<Botte> bottes = new HashSet<>();
	
//	public int donnerLimitationVitesse() {
//		if(limites.isEmpty() || limites.get(0) instanceof FinLimite)
//			return 200;
//		return 50;
//	}
	public int donnerLimitationVitesse() {
		if(limites.isEmpty() || limites.get(0) instanceof FinLimite || estPrioritaire())
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
	
//	public void deposer(Carte carte) {
//		if(carte instanceof Borne borne)
//			bornes.add(borne);
//		if(carte instanceof Limite limite)
//			limites.add(0,limite);
//		if(carte instanceof Bataille bataille)
//			batailles.add(0,bataille);
//	}
	public void deposer(Carte carte) {
		if(carte instanceof Borne borne)
			bornes.add(borne);
		if(carte instanceof Limite limite)
			limites.add(0,limite);
		if(carte instanceof Bataille bataille)
			batailles.add(0,bataille);
		if(carte instanceof Botte botte)
			bottes.add(botte);
	}
	
//	public boolean peutAvancer() {
//		if(batailles.isEmpty())
//			return false;
//		if(batailles.get(0) instanceof Parade parade)
//			return parade.getType().equals(Type.FEU);
//		return false;
//	}
	public boolean peutAvancer() {
		if(batailles.isEmpty())
			return estPrioritaire();
		Bataille sommet = batailles.get(0);
		if(sommet instanceof Parade)
			return sommet.getType().equals(Type.FEU);
		if(sommet instanceof Attaque) {
			if(sommet.getType().equals(Type.FEU))
				return estPrioritaire();
			else {
				return estBottePresente(sommet) && estPrioritaire();
			}
		}
		return false;
	}
	
//	private boolean estDepotFeuVertAutorise() {
//		if(batailles.isEmpty())
//			return true;
//		if(batailles.get(0) instanceof Attaque attaque)
//			return attaque.getType().equals(Type.FEU);
//		if(batailles.get(0) instanceof Parade parade)
//			return !parade.getType().equals(Type.FEU);
//		return false;
//	}
	private boolean estDepotFeuVertAutorise() {
		if(estPrioritaire())
			return false;
		if(batailles.isEmpty())
			return true;
		Bataille sommet = batailles.get(0);
		if(sommet instanceof Attaque)
			return sommet.getType().equals(Type.FEU)
					|| (estBottePresente(sommet) && estPrioritaire());
		if(sommet instanceof Parade)
			return !sommet.getType().equals(Type.FEU);
		return false;
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer()
			&& borne.getKm() < donnerLimitationVitesse()
			&& (borne.getKm()+donnerKmParcourus())<=1000;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if(estPrioritaire())
			return false;
		if(limite instanceof DebutLimite)
			return (limites.isEmpty() || (limites.get(0) instanceof FinLimite));
		else
			return (!limites.isEmpty() && limites.get(0) instanceof DebutLimite);
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if(estBottePresente(bataille))
			return false;
		if(bataille instanceof Attaque && peutAvancer())
			return true;
		if(bataille instanceof Parade parade) {
			if(parade.getType().equals(Type.FEU)) {
				return estDepotFeuVertAutorise();
			} else {
				return !batailles.isEmpty()
						&& batailles.get(0).getType().equals(bataille.getType());
			}
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if(carte instanceof Borne borne)
			return estDepotBorneAutorise(borne);
		if(carte instanceof Limite limite)
			return estDepotLimiteAutorise(limite);
		if(carte instanceof Bataille bataille)
			return estDepotBatailleAutorise(bataille);
		else
			return true;
	}
	
	public boolean estPrioritaire() {
		return bottes.contains(Cartes.PRIORITAIRE);
	}
	
	public boolean estBottePresente(Bataille attaque) {
		for (Botte botte : bottes) {
			if(botte.getType().equals(attaque.getType()))
				return true;
		}
		return false;
	}

	public List<Limite> getLimites() {
		return limites;
	}

	public List<Bataille> getBatailles() {
		return batailles;
	}

	public List<Borne> getBornes() {
		return bornes;
	}

	public Set<Botte> getBottes() {
		return bottes;
	}
}
