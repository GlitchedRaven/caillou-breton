package player;

import player.AIplayer;

import java.io.Serializable;

import card.Card;
import card.Ingredient;
import game.Game;
/**
 * La classe FirstCard repr�sente une des strat�gies possibles pour les IA, celle-ci est simple, elle consiste � toujours jouer G�ant de la premi�re carte en main.
 * 
 *
 */
public class FirstCard implements Strategy,Serializable {
	
	
	
	/**
	 * serialVersionUID n�cessaire � la s�rialisation.
	 */
	private static final long serialVersionUID = 6461900873620043023L;
	/**
	 * Instancie un objet de la classe FirstCard.
	 */
	public FirstCard(){
		
	}
	/**
	 * M�thode permettant de mettre en oeuvre la strat�gie.
	 * @param player : le joueur IA qui utilise cette strat�gie
	 * @param game : la partie courante
	 * @return
	 */
	@Override
	public String playACard(AIplayer player, Game game) {
		Card playedCard = player.getHand().get(0);
		return player.playGiant((Ingredient) playedCard);
		
		

	}

}
