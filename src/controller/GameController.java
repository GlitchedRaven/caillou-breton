package controller;

import game.Game;
import view.*;

public abstract class GameController  {
	protected GameView gv;
	protected Game game;

	public GameController(Game game) {
		super();
		this.gv = new GameView(game);
		this.game = game;
		
	}

	public GameView getGv() {
		return gv;
	}
	
	public abstract void changeSeason();
	public abstract void changePlayer();
	public abstract boolean testAIPlay();
	
}
