package card;

import java.util.Arrays;

public class Ally extends Card {

	

	private int[] strengthVector;
	
	public int[] getStrengthVector() {
		return strengthVector;
	}


	public Ally(String name, int[] strengthVector) {
		super(name);

		this.strengthVector = strengthVector;
		
	}
	
	@Override
	public String toString() {
		return super.getName() + " :  \n\tForce = "  + Arrays.toString(strengthVector) + "\n";
	}
}
