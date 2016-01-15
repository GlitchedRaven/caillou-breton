package player;

import game.Game;

// TODO: Auto-generated Javadoc
/**
 * L'interface Strategy fait partie du patron de conception Strategy et spécifie la méthode playACard()
 * pour les IA.
 */
public interface Strategy {

	/**
	 * Méthode permettant de choisir une carte l'action à jouer durants on tour.
	 *
	 * @param player le joueur IA devant joueur
	 * @param game la partie dont le joueur fait partie
	 * @return une String expliquant l'action ayant eu lieue
	 */
	public String playACard(AIplayer player, Game game);
}
