package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import card.*;
import controller.AdvancedGameController;
import player.AIplayer;
import player.HumanPlayer;
import player.Player;
import view.Console;

public class AdvancedGame extends Game {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6824291523929158763L;
	private HashMap<Player, Integer> playerScore;
	private int round;
	private Deck allyDeck;
	//private Deck ingredientDeck;
	
	@Override
	public ArrayList<Player> designateWinner() {
		ArrayList<Player> winner = new ArrayList<Player>();
		winner.add(this.players.get(0));
		
		for(ListIterator<Player> p = this.players.listIterator(); p.hasNext();){
			Player player = p.next();
			if(this.playerScore.get(player) > this.playerScore.get(winner.get(0))) {
				winner.clear();
				winner.add(0, player);
			}
			else if((this.playerScore.get(player) == this.playerScore.get(winner.get(0))) 
					&& player != this.players.get(0) ) {
				winner.add(player);
			}
		}
		return winner;
	}
	
	/*public AdvancedGame() {
		super();
		this.round = 1;
		this.playerScore = new HashMap<Player, Integer>();
		for(Iterator<Player> p = this.players.iterator(); p.hasNext();){
			this.playerScore.put(p.next(), 0);
		}
		this.allyDeck = new Deck(Deck.ALLY);
		Deck ingredientDeck = new Deck(Deck.INGREDIENT);
		ingredientDeck.distribute(4, this.getPlayers());
		//this.ingredientDeck = new Deck(Deck.INGREDIENT);
		
		
	}*/

	public AdvancedGame(int numberAIPlayers, String[] aIDifficulties, String[] aINames, int numberHumanPlayers, String[] humanNames) {
		super(numberAIPlayers, aIDifficulties, aINames, numberHumanPlayers, humanNames);
		this.round = 1;
		this.playerScore = new HashMap<Player, Integer>();
		for(Iterator<Player> p = this.players.iterator(); p.hasNext();){
			this.playerScore.put(p.next(), 0);
		}
		this.allyDeck = new Deck(Deck.ALLY);
		Deck ingredientDeck = new Deck(Deck.INGREDIENT);
		ingredientDeck.distribute(4, this.getPlayers());
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
		for(ListIterator<Player> p = this.players.listIterator(); p.hasNext();) {
			Player player = p.next();
			Integer oldScore = this.playerScore.get(player);
			this.playerScore.put(player, oldScore + player.getNbMenhirs());
			
			player.clearHand();
			player.setNbMenhirs(0);
			player.setNbRocks(2);
			int[] resetWatchDog = {0,0,0,0};
			player.setWatchDogProtection(resetWatchDog);
		}
		
		this.allyDeck = new Deck(Deck.ALLY);
		Deck ingredientDeck = new Deck(Deck.INGREDIENT);
		ingredientDeck.distribute(4, this.players);
		this.setSeason(SPRING);
		this.setCurrentPlayer(0);
		this.round +=1;
		this.setChanged();
		this.notifyObservers();
	}


	public Deck getAllyDeck() {
		return allyDeck;
	}


	
	

	
	
	public static void main(String[] args, AdvancedGame g) {
		//AdvancedGame game = new AdvancedGame();
		AdvancedGameController gc = new AdvancedGameController(g);
		
		
	

	
	}
}

