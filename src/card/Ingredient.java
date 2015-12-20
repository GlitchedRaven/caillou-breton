package card;

import java.util.Arrays;

public class Ingredient extends Card {
	
	public static final String[] INGREDIENT_ACTION = {"Geant", "Engrais","Farfadet"};
	
	private int[] giantVector;
	private int[] farfadetVector;
	private int[] fertilizerVector;

	public int[] getGiantVector() {
		return giantVector;
	}

	public int[] getFarfadetVector() {
		return farfadetVector;
	}

	public int[] getFertilizerVector() {
		return fertilizerVector;
	}

	public Ingredient(String name, int[] giantVector,  int[] fertilizerVector, int[] farfadetVector) {
		super(name);
		this.giantVector = giantVector;
		this.farfadetVector = farfadetVector;
		this.fertilizerVector = fertilizerVector;
	}

	@Override
	public String toString() {
		return super.getName() + " : \n" + "\tGeant =    " + Arrays.toString(giantVector)+ "\n" + "\tFarfadet = "
				+ Arrays.toString(farfadetVector) + "\n" + "\tEngrais =  " + Arrays.toString(fertilizerVector) + "\n";
	}

	

}
