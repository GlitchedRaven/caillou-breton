package card;

import java.util.Arrays;

public class Ally extends Card {
	public final static int GIANTMOLE = 0;
	public final static int WATCHDOG = 1;
	
	
	public final static int[] TYPES = {GIANTMOLE, WATCHDOG}; 
	

	public int getAllyType() {
		return allyType;
	}

	private int[] strengthVector;
	private int allyType; 
	
	public int[] getStrengthVector() {
		return strengthVector;
	}


	public Ally(String name, int[] strengthVector, int allyType) {
		super(name);
		this.allyType = allyType;
		this.strengthVector = strengthVector;
		
	}
	
	@Override
	public String toString() {
		return super.getName() + " :  \n\tForce = "  + Arrays.toString(strengthVector) + "\n";
	}
}
