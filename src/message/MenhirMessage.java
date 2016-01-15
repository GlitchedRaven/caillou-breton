package message;

import card.Card;

// TODO: Auto-generated Javadoc
/**
 * La classe MenhirMessage a pour vocation de rendre plus simple la communication entre Observable/Observer.
 * Un MenhirMessage consiste alors en un type dont les possibilit�s sont donn�s dans l'enum�ration TypeOfAction et d'une carte 
 * s'il y a lieu d'en sp�cifier une.
 * Ceci permet alors de communiquer des demandes aux Observer comme de vider une main ou de piocher.
 * 
 * @see message.TypeOfAction
 */
public class MenhirMessage {
	
	/** Si cela est pertinent, la carte sujette du message */
	private Card card;
	
	/** Le type d'action demand�e par le message */
	private TypeOfAction type;
	
	/**
	 * Instantie un nouveau message.
	 *
	 * @param card la carte sujette
	 * @param type le type d'action demand�
	 */
	public MenhirMessage(Card card, TypeOfAction type) {
		super();
		this.card = card;
		this.type = type;
	}
	
	/**
	 * Gets the card.
	 *
	 * @return the card
	 */
	public Card getCard() {
		return card;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public TypeOfAction getType() {
		return type;
	}
	
	
}
