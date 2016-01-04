package player;

import player.AIplayer;

import java.io.Serializable;

import card.Card;
import card.Ingredient;
import game.Game;

public class FirstCard implements Strategy,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6461900873620043023L;

	public FirstCard(){
		
	}

	@Override
	public String playACard(AIplayer player, Game game) {
		Card playedCard = player.getHand().get(0);
		return player.playGiant((Ingredient) playedCard);
		
		

	}

}
