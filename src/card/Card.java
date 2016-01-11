package card;

import java.io.Serializable;
import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * La classe abstraite Card représente une carte à jouer dans son minimum. 
 */
public abstract class Card extends Observable implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5662936268956965034L;
	
	/** Nom de la carte */
	private String name;
	
	
	
	/**
	 * Instantie une nouvelle carte à partir de son nom.
	 *
	 * @param name the name
	 */
	public Card(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Retourne le nom de la carte.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	

}
