package card;

import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * La classe Ingredient représente une carte d'ingrédient avec les vecteurs de chaque pouvoir.
 */
public class Ingredient extends Card {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6944435007566927473L;

	/** The Constant INGREDIENT_ACTION. */
	public static final String[] INGREDIENT_ACTION = {"Geant", "Engrais","Farfadet"};
	
	/** Le vecteur du pouvoir Géant. */
	private int[] giantVector;
	
	/** Le vecteur du pouvoir Farfadet. */
	private int[] farfadetVector;
	
	/** Le vecteur du pouvoir Engrais. */
	private int[] fertilizerVector;

	/**
	 * Gets the giant vector.
	 *
	 * @return the giant vector
	 */
	public int[] getGiantVector() {
		return giantVector;
	}

	/**
	 * Gets the farfadet vector.
	 *
	 * @return the farfadet vector
	 */
	public int[] getFarfadetVector() {
		return farfadetVector;
	}

	/**
	 * Gets the fertilizer vector.
	 *
	 * @return the fertilizer vector
	 */
	public int[] getFertilizerVector() {
		return fertilizerVector;
	}

	/**
	 * Instantie une nouvelle carte Ingredient.
	 *
	 * @param name est le nom de la carte
	 * @param giantVector est le vecteur de Géant
	 * @param fertilizerVector est le  vecteur d'Engrais
	 * @param farfadetVector est le vecteur de Farfadet
	 */
	public Ingredient(String name, int[] giantVector,  int[] fertilizerVector, int[] farfadetVector) {
		super(name);
		this.giantVector = giantVector;
		this.farfadetVector = farfadetVector;
		this.fertilizerVector = fertilizerVector;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.getName() + " : \n" + "\tGeant            =    " + Arrays.toString(giantVector)+ "\n" + "\tFarfadet = "
				+ Arrays.toString(farfadetVector) + "\n" + "\tEngrais  =  " + Arrays.toString(fertilizerVector) + "\n";
	}

	

}
