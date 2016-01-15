package player;

import player.AIplayer;

import java.io.Serializable;
import java.util.ListIterator;

import card.Card;
import card.Ingredient;
import game.Game;
/**
 * La classe Grow représente une stratégie possible pour les IA, ici l'IA jouera le meilleur géant possible du tour courant  pendant les deux premiers tours, avant de jouer le meilleur engrais
 * disponible pendant les deux tours suivants.
 *
 */
public class Grow implements Strategy, Serializable {

	
	/**
	 * serialVersionUID nécessaire à la sérialisation
	 */
	private static final long serialVersionUID = -2567737847628148367L;
	/**
	 * Instancie un objet de la classe Grow
	 */
	public Grow(){
		
	}
	/**
	 * Méthode permettant de mettre en oeuvre la stratégie.
	 * @param player : le joueur IA utilisant cette stratégie
	 * @param game : la partie courante
	 * @return
	 */
	@Override
	public String playACard(AIplayer player, Game game) {
		Ingredient cardToPlay = (Ingredient) player.getHand().get(0);
		if (game.getSeason()<2){
			for(ListIterator<Card> c = player.getHand().listIterator(); c.hasNext();) {
				Card currentCard = c.next();
				if (((Ingredient) currentCard).getGiantVector()[game.getSeason()] > cardToPlay.getGiantVector()[game.getSeason()]){
					cardToPlay = (Ingredient) currentCard;
				}
			}
			return player.playGiant(cardToPlay);
			
		} else {
			for(ListIterator<Card> c = player.getHand().listIterator(); c.hasNext();) {
				Card currentCard = c.next();
				if (((Ingredient) currentCard).getFertilizerVector()[game.getSeason()] > cardToPlay.getFertilizerVector()[game.getSeason()]){
					cardToPlay = (Ingredient) currentCard;
				}
			}
			return player.playFertilizer(cardToPlay);
			
		}
		
	}

}