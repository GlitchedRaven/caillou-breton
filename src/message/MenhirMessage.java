package message;

import card.Card;

public class MenhirMessage {
	private Card card;
	private TypeOfAction type;
	public MenhirMessage(Card card, TypeOfAction type) {
		super();
		this.card = card;
		this.type = type;
	}
	public Card getCard() {
		return card;
	}
	public TypeOfAction getType() {
		return type;
	}
	
	
}
