package player;

import player.AIplayer;
import card.Ingredient;
import game.Game;

public class FirstCard implements Strategy {
	
	
	
	public FirstCard(){
		
	}

	@Override
	public void playACard(AIplayer player, Game game) {
		player.playGiant((Ingredient) player.getHand().get(0));
		

	}

}
