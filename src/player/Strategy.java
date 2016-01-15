package player;

import game.Game;

// TODO: Auto-generated Javadoc
/**
 * L'interface Strategy fait partie du patron de conception Strategy et sp�cifie la m�thode playACard()
 * pour les IA.
 */
public interface Strategy {

	/**
	 * M�thode permettant de choisir une carte l'action � jouer durants on tour.
	 *
	 * @param player le joueur IA devant joueur
	 * @param game la partie dont le joueur fait partie
	 * @return une String expliquant l'action ayant eu lieue
	 */
	public String playACard(AIplayer player, Game game);
}
