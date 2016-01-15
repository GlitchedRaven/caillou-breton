package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import card.*;
import controller.AdvancedGameController;
//import player.AIplayer;
//import player.HumanPlayer;
import player.Player;

/**
 * Classe repr�sentant une partie avanc�e
 * 
 *
 */
public class AdvancedGame extends Game {
	/**
	 * serialVersionUID n�cessaire � la s�rialisation
	 */
	private static final long serialVersionUID = 6824291523929158763L;
	/**
	 * HashMap pour relier les scores aux joueurs
	 */
	private HashMap<Player, Integer> playerScore;
	/**
	 * entier repr�sentant la manche en cours
	 */
	private int round;
	/**
	 *@see deck.Deck
	 *deck des cartes alli�es
	 */
	private Deck allyDeck;
	
	
	@Override
	/**
	 * red�finition de la m�thode designateWinner() de game.Game pour correspondre aux standards de la partie avanc�e
	 * @return Player
	 */
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
	
	/**
	 * Constructeur de la partie avanc�e
	 * @param numberAIPlayers : nombre de joueurs IA
	 * @param aIDifficulties : difficult�s de joueurs IA
	 * @param aINames : noms des joueurs IA
	 * @param numberHumanPlayers : nombre de joueurs humains
	 * @param humanNames : noms des joueurs humains
	 */
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
		
		
	}
	
	

	/**
	 * getter du HashMap des scores
	 * @return HashMap
	 */
	public HashMap<Player, Integer> getPlayerScore() {
		return playerScore;
	}

	/**
	 * setter du HashMap playerScore
	 * @param playerScore
	 */
	public void setPlayerScore(HashMap<Player, Integer> playerScore) {
		this.playerScore = playerScore;
	}
	
	/**
	 * getter de l'entier coreespondant � la manche courante
	 * @return round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * m�thode de changement de la manche en cours, g�re tous les changements associ�s (distribution de cartes alli�es...)
	 */
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

	/**
	 * getter du deck de cartes alli�es
	 * @return allydeck
	 */
	public Deck getAllyDeck() {
		return allyDeck;
	}


	/**
	 * m�thode permettant de d�marrer une partie avanc�e en GUI
	 * @param g : partie avanc�e
	 */
	public static void main(String[] args, AdvancedGame g) {
		new AdvancedGameController(g);
	}
}

