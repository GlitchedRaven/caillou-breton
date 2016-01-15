package lo02.utt.giboulot.canat.game;

import java.util.ArrayList;
import java.util.ListIterator;

import lo02.utt.giboulot.canat.card.Deck;
import lo02.utt.giboulot.canat.controller.*;
import lo02.utt.giboulot.canat.player.*;

/**
 * Classe représentant la partie rapide.
 * 
 *
 */
public class QuickGame extends Game {

	/**
	 * serialVersionUID nécessaire à la sérialisation
	 */
	private static final long serialVersionUID = -709136199483886381L;

	/**
	 * Redéfinition de la méthode designateWinner() correspondant aux standards de la partie rapide.
	 * @return Player
	 */
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
	/**
	 * Constructeur de la partie rapide, utilisant le constructeur d'une partie et il distribue les cartes.
	 * @param numberAIPlayers : nombre de joueurs IA
	 * @param aIDifficulties : difficulté des joueurs IA
	 * @param aINames : nom des joueurs IA
	 * @param numberHumanPlayers : nombre de joueurs humains
	 * @param humanNames : nom de joueurs huamins
	 */
	public QuickGame(int numberAIPlayers, String[] aIDifficulties, String[] aINames, int numberHumanPlayers, String[] humanNames) {
		super(numberAIPlayers, aIDifficulties, aINames, numberHumanPlayers, humanNames);
		// Distribute cards
				Deck deck = new Deck(Deck.INGREDIENT);
				deck.distribute(4, players);
	}

	/**
	 * Méthode permettant de démarrer une partie rapide en interface graphique.
	 * @param g : partie rapide
	 */
	public static void main(String[] args, QuickGame g) {
		
		new QuickGameController(g);
		
	}
}

