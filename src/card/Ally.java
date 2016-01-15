package card;

import java.util.Arrays;

/**
 * La classe Ally représente une carte allié. Le type précis de cet allié est donné par un attribut.
 */
public class Ally extends Card {
	
	
	private static final long serialVersionUID = 2339940190410564043L;
	public final static int GIANTMOLE = 0;
	public final static int WATCHDOG = 1;
	
	
	/** Définit les types d'alliés du jeu. */
	public final static int[] TYPES = {GIANTMOLE, WATCHDOG}; 
	

	/**
	 * Retourne le type de l'allié.
	 *
	 * @return le type de l'allié
	 */
	public int getAllyType() {
		return allyType;
	}

	/** Vecteur représentant la force de l'allié pour chaque saison */
	private int[] strengthVector;
	
	/** Attribut représentant le type de l'allié */
	private int allyType; 
	
	/**
	 * Retourne le vecteur force.
	 *
	 * @return le vecteur force
	 */
	public int[] getStrengthVector() {
		return strengthVector;
	}


	/**
	 * Instancie une nouvelle carte allié
	 *
	 * @param name étant le nom de la carte
	 * @param strengthVector étant le vecteur force
	 * @param allyType étant le type de l'allié
	 */
	public Ally(String name, int[] strengthVector, int allyType) {
		super(name);
		this.allyType = allyType;
		this.strengthVector = strengthVector;
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.getName() + " :  \n\tForce = "  + Arrays.toString(strengthVector) + "\n";
	}
}
