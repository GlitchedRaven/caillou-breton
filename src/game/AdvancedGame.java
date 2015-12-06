package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import card.*;
import player.AIplayer;
import player.HumanPlayer;
import player.Player;
import view.Console;

public class AdvancedGame extends Game {
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
	
	public AdvancedGame() {
		super();
		this.round = 1;
		this.playerScore = new HashMap<Player, Integer>();
		for(Iterator<Player> p = this.players.iterator(); p.hasNext();){
			this.playerScore.put(p.next(), 0);
		}
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
		for(ListIterator<Player> p = this.players.listIterator(); p.hasNext();) {
			Player player = p.next();
			Integer oldScore = this.playerScore.get(player);
			this.playerScore.put(player, oldScore + player.getNbMenhirs());
		}
		this.setSeason(SPRING);
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
				player.setNbMenhirs(0);
				player.setNbRocks(2);
				int[] resetWatchDog = {0,0,0,0};
				player.setWatchDogProtection(resetWatchDog);
				if(player instanceof HumanPlayer){
					boolean drawAlly = console.choiceAllyOrRock(player);

					if(drawAlly) {
						player.setHand(game.getAllyDeck().remove());
						player.setNbRocks(0);
					}
				}
			}
			//END card distribution
			while(game.getSeason() <= WINTER) {
			
				for(ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
					Player currentPlayer = p.next();
					if (currentPlayer instanceof HumanPlayer) {
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
								Card victimLastCard = victim.getHand().get(victim.getHand().size() -1);
								
								if(victimLastCard instanceof Ally) { //Ally card is always the last card
									if(((Ally) victimLastCard).getAllyType() == Ally.WATCHDOG)
										if(console.choicePlayWatchDog(victim)) {
											victim.playWatchDog((Ally) victimLastCard);
											game.getAllyDeck().push(victimLastCard);
											victim.getHand().remove(victimLastCard);
										}
									}	
									currentPlayer.playFarfadet((Ingredient) playedCard, victim);
								}
							

							else
								System.out.println("Tour passé");
						}



						else if(playedCard instanceof Ally) {
							if(((Ally) playedCard).getAllyType() == Ally.GIANTMOLE) {
								Player victim = game.getPlayers().get(console.choiceVictim() - 1);
								currentPlayer.playGiantMole((Ally) playedCard, victim);
							}
							else if(((Ally) playedCard).getAllyType() == Ally.WATCHDOG)
								currentPlayer.playWatchDog((Ally) playedCard);
					}
					
				}
					else if(currentPlayer instanceof AIplayer){
						console.getPlayerDetails(currentPlayer);
						((AIplayer) currentPlayer).playACard();
						//console.getPlayerDetails(currentPlayer);
						}
				}
				game.setSeason(); // Change the season to the next one
			}
			
				game.setRound(); // Change the round to the next one
				
		}
		console.displayWinner(game.designateWinner());
		
	

	
	}
}

