package lo02.utt.giboulot.canat.card;

import java.util.Arrays;

/**
 * La classe Ingredient repr�sente une carte d'ingr�dient avec les vecteurs de chaque pouvoir.
 */
public class Ingredient extends Card {

	private static final long serialVersionUID = 6944435007566927473L;

	/**Definit toutes les actions des ingr�dients*/
	public static final String[] INGREDIENT_ACTION = {"Geant", "Engrais","Farfadet"};
	
	/** Le vecteur du pouvoir G�ant. */
	private int[] giantVector;
	
	/** Le vecteur du pouvoir Farfadet. */
	private int[] farfadetVector;
	
	/** Le vecteur du pouvoir Engrais. */
	private int[] fertilizerVector;

	/**
	 * Getter du vecteur G�ant.
	 *
	 * @return le vecteur du pouvoir G�ant
	 */
	public int[] getGiantVector() {
		return giantVector;
	}

	/**
	 * Getter du vecteur Farfadet.
	 *
	 * @return le vecteur du pouvoir Farfadet
	 */
	public int[] getFarfadetVector() {
		return farfadetVector;
	}

	/**
	 * Getter du vecteur Engrais.
	 *
	 * @return le vecteur du pouvoir Engrais
	 */
	public int[] getFertilizerVector() {
		return fertilizerVector;
	}

	/**
	 * Instancie une nouvelle carte Ingredient.
	 *
	 * @param name est le nom de la carte
	 * @param giantVector est le vecteur de G�ant
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
