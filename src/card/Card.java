package card;

import java.io.Serializable;
import java.util.Observable;

/**
 * La classe abstraite Card représente une carte à jouer dans son minimum. 
 */
public abstract class Card extends Observable implements Serializable {
	
	private static final long serialVersionUID = -5662936268956965034L;
	
	/** Nom de la carte */
	private String name;
	
	
	
	/**
	 * Instancie une nouvelle carte à partir de son nom.
	 *
	 * @param name le nom de la carteyl
	 */
	public Card(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Retourne le nom de la carte.
	 *
	 * @return le nom de la carte
	 */
	public String getName() {
		return name;
	}
	

}
