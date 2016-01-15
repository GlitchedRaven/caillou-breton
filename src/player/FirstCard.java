package player;

import player.AIplayer;

import java.io.Serializable;

import card.Card;
import card.Ingredient;
import game.Game;
/**
 * La classe FirstCard représente une des stratégies possibles pour les IA, celle-ci est simple, elle consiste à toujours jouer Géant de la première carte en main.
 * 
 *
 */
public class FirstCard implements Strategy,Serializable {
	
	private static final long serialVersionUID = 6461900873620043023L;
	/**
	 * Instancie un objet de la classe FirstCard.
	 */
	public FirstCard(){
		
	}
	/**
	 * Méthode permettant de mettre en oeuvre la stratégie.
	 * @param player le joueur IA qui utilise cette stratégie
	 * @param game la partie courante
	 * @return
	 */
	@Override
	public String playACard(AIplayer player, Game game) {
		Card playedCard = player.getHand().get(0);
		return player.playGiant((Ingredient) playedCard);
		
		

	}

}
