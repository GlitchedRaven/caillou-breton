package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
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
		
		Console console = new Console();
		
		while(game.round <= game.getPlayers().size()) {
			//Card distribution
			Deck ingredientDeck = new Deck(Deck.INGREDIENT);
			ingredientDeck.distribute(4, game.getPlayers());
			
			for(ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
				Player player = p.next();
				boolean drawAlly = console.choiceAllyOrRock(player);
				
				if(drawAlly) {
					player.setHand(game.getAllyDeck().remove());
					player.setNbRocks(0);
				}
			}
			//END card distribution
			while(game.getSeason() <= WINTER) {
			
				for(ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
					Player currentPlayer = p.next();
				
					console.getGameDetails(game);
					console.getPlayerDetails(currentPlayer);
					Card playedCard = currentPlayer.getHand().get(console.choiceCard());
					
					if(playedCard instanceof Ingredient) {
						String playedAction = console.choiceAction();
					
						if(playedAction.equals("G"))
							currentPlayer.playGiant((Ingredient) playedCard);
						else if(playedAction.equals("E"))
							currentPlayer.playFertilizer((Ingredient) playedCard);
						else if(playedAction.equals("F")) {
							Player victim = game.getPlayers().get(console.choiceVictim() - 1);
							
							boolean watchdogFound = false;
							ListIterator<Card> c = victim.getHand().listIterator();
							while((watchdogFound == false) || c.hasNext()) {
								Card victimCard = c.next();
								if(victimCard instanceof WatchDog) {
									watchdogFound = true;
									if(console.choicePlayWatchDog(victim)) {
										victim.playWatchDog((WatchDog) victimCard);
										game.getAllyDeck().push(victimCard);
										victim.getHand().remove(victimCard);
									}
								}	
								currentPlayer.playFarfadet((Ingredient) playedCard, victim);
							}
						}
						
						else
							System.out.println("Tour passé");
						}
					
					
					
					else if(playedCard instanceof Ally) {
						if(playedCard instanceof GiantMole) {
							Player victim = game.getPlayers().get(console.choiceVictim() - 1);
							currentPlayer.playGiantMole((GiantMole) playedCard, victim);
						}
						else if(playedCard instanceof WatchDog)
							currentPlayer.playWatchDog((WatchDog) playedCard);
					}
					
				}
				game.setSeason(); // Change the season to the next one
			}
			
				game.setRound(); // Change the round to the next one
				
		}
		console.displayWinner(game.designateWinner());
		
	

	
	}
}
