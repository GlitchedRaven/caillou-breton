package card;

import java.io.Serializable;
import java.util.Observable;

public abstract class Card extends Observable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5662936268956965034L;
	private String name;
	
	
	
	public Card(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	

}
