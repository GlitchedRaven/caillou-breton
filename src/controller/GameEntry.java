package controller;

import game.AdvancedGame;
import game.QuickGame;
import view.Console;

public class GameEntry {
	
	

	public static void main(String[] args) {
		
		
		Console choiceCons = new Console();
		if (choiceCons.choiceTypeOfGame() == 2)
			AdvancedGame.main(args);
		else
			QuickGame.main(args);
	}
}
