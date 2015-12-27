package game;

import java.util.ArrayList;
import java.util.ListIterator;


import card.Deck;

import controller.*;
import player.*;
import view.GameView;

public class QuickGame extends Game {

	public ArrayList<Player> designateWinner() {
		
			ArrayList<Player> winner = new ArrayList<Player>();
			winner.add(this.players.get(0));
			
			for(ListIterator<Player> p = players.listIterator(); p.hasNext();){
				Player player = p.next();
				if(player.getNbMenhirs() > winner.get(0).getNbMenhirs()) {
					winner.clear();
					winner.add(0, player);
				}
				else if (player.getNbMenhirs() == winner.get(0).getNbMenhirs()) {
					if(player.getNbRocks() > winner.get(0).getNbRocks()) {
						winner.clear();
						winner.add(0, player);
					}
					else if ((player.getNbRocks() == winner.get(0).getNbRocks())
							&& (player != this.players.get(0))) {  // Pour ne pas ajouter le joueur 1 deux fois à la liste
						winner.add(player);
					}
				}
			}
				return winner;
			}

	public QuickGame() {
		super();
		// Distribute cards
				Deck deck = new Deck(Deck.INGREDIENT);
				deck.distribute(4, players);
	}


	public static void main(String[] args) {
		
		QuickGame g = new QuickGame();
		QuickGameController gc = new QuickGameController(g);
		GameView gv = gc.getGv();
		
	
		
		
		
		
		
	}
}

