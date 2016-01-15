package player;

import game.Game;

// TODO: Auto-generated Javadoc
/**
 * La classe AIplayer représente une intelligence artficielle joueuse.
 * Elle utilise le patron de conception Strategy pour modéliser les différents niveau de difficultés.
 */
public class AIplayer extends Player {
	
	
	private static final long serialVersionUID = -816761003543910556L;
	
	/** Le mode de jeu de l'IA */
	private Strategy strategy;
	//private Thread t;

	/**
	 * Instantie une nouvelle IA .
	 *
	 * @param name le nom de l'IA
	 * @param game la partie dont l'IA fera partie
	 * @param strategy le mode de jeu de l'IA
	 */
	public AIplayer(String name, Game game, Strategy strategy) {
		super(name, game);
		this.strategy = strategy;
	}
	
	/**
	 * Méthode permettant à l'IA de jouer une carte selon la stratégie choisie lors de sa création.
	 * La méthode appelle la méthode éponyme de la stratégie choisie pour décider la carte etd e l'action à jouer.
	 */
	public void playACard(){
		String s = this.strategy.playACard(this, this.getCurrentGame());
		this.setChanged();
		this.notifyObservers(s);
	}

}
