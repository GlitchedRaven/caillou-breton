package controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.ListIterator;

import card.Card;
import game.*;
import player.Player;
import controller.AdvancedGameController;
//import player.*;
import view.CardView;
//import view.Console;
import view.GameCreatorView;
import view.GameView;
import view.PlayerView;

public class GameEntry {
	
	

	public static void main(String[] args) throws IOException{
		
		
		//GameCreatorView test = new GameCreatorView();
		
		
		ObjectInputStream ois = null;
		try {
			FileInputStream test = new FileInputStream("sauvegarde.txt");
			ois = new ObjectInputStream(test);
			GameController gc = (GameController) ois.readObject();
			gc.setGv(new GameView(gc.getGame()));
			

			for(ListIterator<PlayerView> it = gc.getGv().getPlayerViews().listIterator(); it.hasNext();) {
				PlayerView pv = it.next();
				Player currentPlayer = pv.getPlayer();
				//currentPlayer.addObserver(this);
				for(Iterator<CardView> it2 = pv.getCardViews().values().iterator(); it2.hasNext();) {
					CardView cv = it2.next();
					Card playedCard = cv.getCard();
					gc.addCardListener(playedCard, cv, currentPlayer);
				}
			}
					
				
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ois.close();
		}
	

	}
}
