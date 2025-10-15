package cartes;

public abstract class Carte {
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(getClass() == obj.getClass()) {
			return toString().equals(((Carte)obj).toString());
		}
		return false;
	}
}
