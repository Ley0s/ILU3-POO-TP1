package cartes;

public class JeuDeCartes {
	private Configuration[] typesDeCartes = { new Configuration(new Borne(25), 10),
		new Configuration(new Borne(50), 10), new Configuration(new Borne(75), 10),
		new Configuration(new Borne(100), 12), new Configuration(new Borne(200), 4),
		new Configuration(new Parade(Type.FEU), 14), new Configuration(new FinLimite(), 6),
		new Configuration(new Parade(Type.ESSENCE), 6), new Configuration(new Parade(Type.CREVAISON), 6),
		new Configuration(new Parade(Type.ACCIDENT), 6), new Configuration(new Attaque(Type.FEU), 5),
		new Configuration(new DebutLimite(), 4), new Configuration(new Attaque(Type.ESSENCE), 3),
		new Configuration(new Attaque(Type.CREVAISON), 3), new Configuration(new Attaque(Type.ACCIDENT), 3),
		new Configuration(new Botte(Type.FEU), 1), new Configuration(new Botte(Type.ESSENCE), 1),
		new Configuration(new Botte(Type.CREVAISON), 1), new Configuration(new Botte(Type.ACCIDENT), 1), };

	public String affichageJeuDeCartes() {
		StringBuilder retour = new StringBuilder("JEU :\n\n");
		for (int i = 0; i < typesDeCartes.length; i++) {
			retour.append(typesDeCartes[i].getNbExemplaire() + " " + typesDeCartes[i].getCarte() + "\n");
		}
		return retour.toString();
	}
	
	public Carte[] donnerCartes() {
		int nbCartes = 0;
		for (int i = 0; i < typesDeCartes.length; i++) {
			nbCartes += typesDeCartes[i].getNbExemplaire();
		}
		
		Carte[] cartes = new Carte[nbCartes];
		int c = 0;
		for (int i = 0; i < typesDeCartes.length; i++) {
			for (int j = 0; j < typesDeCartes[i].getNbExemplaire(); j++) {
				cartes[c] = typesDeCartes[i].getCarte();
				c++;
			}
		}
		return cartes;
	}
	
	public boolean checkCount() {
		Carte[] cartes = donnerCartes();
		int c = 0;
		for (int i = 0; i < typesDeCartes.length; i++) {
			for (int j = 0; j < typesDeCartes[i].getNbExemplaire(); j++) {
				if(!(cartes[c] == typesDeCartes[i].getCarte()))
					return false;
				c++;
			}
		}
		return true;
	}

	private static class Configuration {
		private int nbExemplaire;
		private Carte carte;
		
		public Configuration(Carte carte, int nbExemplaire) {
			this.carte = carte;
			this.nbExemplaire = nbExemplaire;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		public int getNbExemplaire() {
			return nbExemplaire;
		}
	}
}
