package player;

import game.Game;

// TODO: Auto-generated Javadoc
/**
 * La classe AIplayer repr�sente une intelligence artficielle joueuse.
 * Elle utilise le patron de conception Strategy pour mod�liser les diff�rents niveau de difficult�s.
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
	 * M�thode permettant � l'IA de jouer une carte selon la strat�gie choisie lors de sa cr�ation.
	 * La m�thode appelle la m�thode �ponyme de la strat�gie choisie pour d�cider la carte etd e l'action � jouer.
	 */
	public void playACard(){
		String s = this.strategy.playACard(this, this.getCurrentGame());
		this.setChanged();
		this.notifyObservers(s);
	}

}
