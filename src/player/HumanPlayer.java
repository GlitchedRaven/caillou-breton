package player;

import game.Game;

// TODO: Auto-generated Javadoc
/**
 * La classe HumanPlayer représente un joueur humain.
 */
public class HumanPlayer extends Player{

	private static final long serialVersionUID = -1483281069297093850L;

	/**
	 * Instantie un nouveau joueur humain.
	 *
	 * @param name le nom du joueur
	 * @param game la partie dont le joueur fera partie
	 */
	public HumanPlayer(String name, Game game) {
		super(name, game);
		
	}

}
