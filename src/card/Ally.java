package card;

import java.util.Arrays;

/**
 * La classe Ally repr�sente une carte alli�. Le type pr�cis de cet alli� est donn� par un attribut.
 */
public class Ally extends Card {
	
	
	private static final long serialVersionUID = 2339940190410564043L;
	public final static int GIANTMOLE = 0;
	public final static int WATCHDOG = 1;
	
	
	/** D�finit les types d'alli�s du jeu. */
	public final static int[] TYPES = {GIANTMOLE, WATCHDOG}; 
	

	/**
	 * Retourne le type de l'alli�.
	 *
	 * @return le type de l'alli�
	 */
	public int getAllyType() {
		return allyType;
	}

	/** Vecteur repr�sentant la force de l'alli� pour chaque saison */
	private int[] strengthVector;
	
	/** Attribut repr�sentant le type de l'alli� */
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
	 * Instancie une nouvelle carte alli�
	 *
	 * @param name �tant le nom de la carte
	 * @param strengthVector �tant le vecteur force
	 * @param allyType �tant le type de l'alli�
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
