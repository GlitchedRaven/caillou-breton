package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Scanner;
import controller.*;
import card.*;

import player.Player;
import view.Console;

public class AdvancedGame extends Game {
	private HashMap<Player, Integer> playerScore;
	private int round;
	private Deck allyDeck;
	//private Deck ingredientDeck;
	
	@Override
	public ArrayList<Player> designateWinner() {
		
		return null;
	}
	
	public AdvancedGame() {
		super();
		this.round = 1;
		this.playerScore = new HashMap<Player, Integer>();
		for(Player p : playerScore.keySet())
			playerScore.put(p, 0);
		
		this.allyDeck = new Deck(Deck.ALLY);
		//this.ingredientDeck = new Deck(Deck.INGREDIENT);
		
		
	}



	public HashMap<Player, Integer> getPlayerScore() {
		return playerScore;
	}



	public void setPlayerScore(HashMap<Player, Integer> playerScore) {
		this.playerScore = playerScore;
	}



	public int getRound() {
		return round;
	}



	public void setRound() {
		this.round += 1;
	}



	public Deck getAllyDeck() {
		return allyDeck;
	}


	
	

	
	
	public static void main(String[] args) {
		AdvancedGame game = new AdvancedGame();
		Scanner userInput = new Scanner( System.in );
		Console console = new Console();
		
		while(game.round <= game.getPlayers().size()) {
			Deck ingredientDeck = new Deck(Deck.INGREDIENT);
			ingredientDeck.distribute(4, game.getPlayers());
			
			for(ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
				Player player = p.next();
				boolean drawAlly = console.choiceAllyOrRock(userInput, player);
				
				if(drawAlly) {
					player.setHand(game.getAllyDeck().remove());
					player.setNbRocks(0);
				}
			}
			while(game.getSeason() <= WINTER) {
			
				for(ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
					Player currentPlayer = p.next();
				
					console.getGameDetails(game);
					console.getPlayerDetails(currentPlayer);
					int playedCard = console.choiceCard(userInput);
					String playedAction = console.choiceAction(userInput);

					if(playedAction.equals("G"))
						currentPlayer.playGiant((Ingredient) currentPlayer.getHand().get(playedCard));
					else if(playedAction.equals("E"))
						currentPlayer.playFertilizer((Ingredient) currentPlayer.getHand().get(playedCard));
					else if(playedAction.equals("F")) {
						int victim = console.choiceVictim(userInput);
						currentPlayer.playFarfadet((Ingredient) currentPlayer.getHand().get(playedCard), game.getPlayers().get(victim - 1));
					}
					else
						System.out.println("Tour passé");
				
					currentPlayer.getHand().remove(playedCard); //On retire la carte jouée
					
					
				}
				game.setSeason(); // Change the season to the next one
			}
			
				
				
		}
		console.displayWinner(game.designateWinner());
		userInput.close();
	}

	
}