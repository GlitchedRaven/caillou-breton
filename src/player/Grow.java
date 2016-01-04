package player;

import player.AIplayer;

import java.io.Serializable;
import java.util.ListIterator;

import card.Card;
import card.Ingredient;
import game.Game;

public class Grow implements Strategy, Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2567737847628148367L;

	public Grow(){
		
	}

	@Override
	public String playACard(AIplayer player, Game game) {
		Ingredient cardToPlay = (Ingredient) player.getHand().get(0);
		if (game.getSeason()<2){
			for(ListIterator<Card> c = player.getHand().listIterator(); c.hasNext();) {
				Card currentCard = c.next();
				if (((Ingredient) currentCard).getGiantVector()[game.getSeason()] > cardToPlay.getGiantVector()[game.getSeason()]){
					cardToPlay = (Ingredient) currentCard;
				}
			}
			return player.playGiant(cardToPlay);
			
		} else {
			for(ListIterator<Card> c = player.getHand().listIterator(); c.hasNext();) {
				Card currentCard = c.next();
				if (((Ingredient) currentCard).getFertilizerVector()[game.getSeason()] > cardToPlay.getFertilizerVector()[game.getSeason()]){
					cardToPlay = (Ingredient) currentCard;
				}
			}
			return player.playFertilizer(cardToPlay);
			
		}
		
	}

}