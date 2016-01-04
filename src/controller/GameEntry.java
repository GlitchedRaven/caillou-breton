package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import game.AdvancedGame;
import game.Game;
import game.QuickGame;
//import game.AdvancedGame;
//import game.QuickGame;
//import view.Console;
import view.GameCreatorView;
import view.GameView;

public class GameEntry {
	
	

	public static void main(String[] args) throws IOException {
		//GameCreatorView test = new GameCreatorView();
		
		ObjectInputStream ois = null;
		try {
			FileInputStream test = new FileInputStream("sauvegarde.txt");
			ois = new ObjectInputStream(test);
			Game g = (Game) ois.readObject();		
			if (g instanceof QuickGame){
				QuickGameController gc = new QuickGameController((QuickGame) g);
				GameView gv = gc.getGv();
			} else {
				AdvancedGameController gc = new AdvancedGameController((AdvancedGame) g);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ois.close();
		}
		
	
	}
}
