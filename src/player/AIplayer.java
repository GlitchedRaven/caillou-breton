package player;

import game.Game;

public class AIplayer extends Player {
	
	private Strategy strategy;

	public AIplayer(String name, Game game, Strategy strategy) {
		super(name, game);
		this.strategy = strategy;
	}
	
	public void playACard(){
		this.strategy.playACard(this, this.getCurrentGame());
	}
	
}
