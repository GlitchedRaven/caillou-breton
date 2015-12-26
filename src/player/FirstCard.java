package player;

import player.AIplayer;
import card.Card;
import card.Ingredient;
import game.Game;

public class FirstCard implements Strategy {
	
	
	
	public FirstCard(){
		
	}

	@Override
	public String playACard(AIplayer player, Game game) {
		Card playedCard = player.getHand().get(0);
		return player.playGiant((Ingredient) playedCard);
		
		

	}

}
