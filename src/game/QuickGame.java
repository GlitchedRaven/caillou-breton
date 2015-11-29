package game;

import java.util.ArrayList;
import java.util.ListIterator;

import card.Deck;
import card.Ingredient;
import player.*;
import view.Console;

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
		QuickGame game = new QuickGame();
		Console console = new Console();
		
		while(game.getSeason() <= WINTER) {
			
			for(ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
				Player currentPlayer = p.next();
			
				if (currentPlayer instanceof HumanPlayer) {
					console.getGameDetails(game);
					console.getPlayerDetails(currentPlayer);
					int playedCard = console.choiceCard();
					String playedAction = console.choiceAction();
					
					if(playedAction.equals("G"))
						currentPlayer.playGiant((Ingredient) currentPlayer.getHand().get(playedCard));
					else if(playedAction.equals("E"))
						currentPlayer.playFertilizer((Ingredient) currentPlayer.getHand().get(playedCard));
					else if(playedAction.equals("F")) {
						int victim = console.choiceVictim();
						currentPlayer.playFarfadet((Ingredient) currentPlayer.getHand().get(playedCard), game.getPlayers().get(victim - 1));
				}
					else
						System.out.println("Tour passé");
					
				} else if(currentPlayer instanceof AIplayer){
					//console.getPlayerDetails(currentPlayer);
					((AIplayer) currentPlayer).playACard();
					//console.getPlayerDetails(currentPlayer);
					}
			}
			game.setSeason(); // Change the season to the next one
		}
			
			console.displayWinner(game.designateWinner());
			
	}

}

