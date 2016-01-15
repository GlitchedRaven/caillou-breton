package lo02.utt.giboulot.canat.message;

import lo02.utt.giboulot.canat.card.Card;

/**
 * La classe MenhirMessage a pour vocation de rendre plus simple la communication entre Observable/Observer.
 * Un MenhirMessage consiste alors en un type dont les possibilités sont donnés dans l'enumération TypeOfAction et d'une carte 
 * s'il y a lieu d'en spécifier une.
 * Ceci permet alors de communiquer des demandes aux Observer comme de vider une main ou de piocher.
 * 
 * @see lo02.utt.giboulot.canat.message.TypeOfAction
 */
public class MenhirMessage {
	
	/** Si cela est pertinent, la carte sujette du lo02.utt.giboulot.canat.message */
	private Card card;
	
	/** Le type d'action demandée par le lo02.utt.giboulot.canat.message */
	private TypeOfAction type;
	
	/**
	 * Instancie un nouveau lo02.utt.giboulot.canat.message.
	 *
	 * @param card la carte sujette
	 * @param type le type d'action demandé
	 */
	public MenhirMessage(Card card, TypeOfAction type) {
		super();
		this.card = card;
		this.type = type;
	}
	
	/**
	 * Getter de la carte
	 *
	 * @return la carte
	 */
	public Card getCard() {
		return card;
	}
	
	/**
	 * Getter du type d'action
	 *
	 * @return le type d'action
	 */
	public TypeOfAction getType() {
		return type;
	}
	
	
}
