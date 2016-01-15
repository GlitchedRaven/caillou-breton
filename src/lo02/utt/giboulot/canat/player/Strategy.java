package lo02.utt.giboulot.canat.player;

import lo02.utt.giboulot.canat.game.Game;

/**
 * L'interface Strategy fait partie du patron de conception Strategy et sp�cifie la m�thode playACard()
 * pour les IA.
 */
public interface Strategy {

	/**
	 * M�thode permettant de choisir une carte l'action � jouer durants on tour.
	 *
	 * @param lo02.utt.giboulot.canat.player le joueur IA devant joueur
	 * @param lo02.utt.giboulot.canat.game la partie dont le joueur fait partie
	 * @return une String expliquant l'action ayant eu lieue
	 */
	public String playACard(AIplayer player, Game game);
}
