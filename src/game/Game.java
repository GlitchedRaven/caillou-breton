package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import player.*;

/**
 * Classe représentant la partie, elle possède les méthodes pour changer de saison et de joueurs.
 * 
 * 
 * 
 * 
 */
public abstract class Game extends Observable implements Serializable{
	
	/**
	 * serialVersionUID nécessaire à la sérialisation
	 */
	private static final long serialVersionUID = 5801013053144630175L;
	/**
	 * Association entre les saisons et des entiers
	 */
	public final static int SPRING = 0;
	/**
	 * Association entre les saisons et des entiers
	 */
	public final static int SUMMER = 1;
	/**
	 * Association entre les saisons et des entiers
	 */
	public final static int AUTUMN = 2;
	/**
	 * Association entre les saisons et des entiers
	 */
	public final static int WINTER = 3;
	/**
	 * Tableau d'association des saisons à leur noms en français
	 */
	public final static String[] SEASONS = {"Printemps", "Ete", "Automne", "Hiver"};
	/**
	 * Entier représentant la saison en cours
	 */
	private int season;
	/**
	 * Booléen déterminant si la partie a été sauvegardée ou non
	 */
	private boolean saved;
	/**
	 * Arraylist représentant les joueurs de la partie
	 */
	protected ArrayList<Player> players;
	/**
	 * Indice permettant de déterminer qui est le joueur courant
	 */
	protected int currentPlayerIndex;
	
	/**
	 * Getter du booléen saved
	 * @return boolean
	 */
	public boolean isSaved() {
		return saved;
	}
	
	/**
	 * Setter du booléen saved
	 * @param saved
	 */
	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	
	/**
	 * Constructeur de la classe Game, il appelle le constructeur des joueurs 
	 * @param numberAIPlayers : nombre de joueurs IA
	 * @param aIDifficulties : difficulté des joueurs IA
	 * @param aINames : nom des joueurs IA
	 * @param numberHumanPlayers : nombre de joueurs humains
	 * @param humanNames : nom de joueurs huamins
	 */
	public Game(int numberAIPlayers, String[] aIDifficulties, String[] aINames, int numberHumanPlayers, String[] humanNames){
		//constructor where he doesn't ask stupid questions
		this.season = SPRING;
		this.currentPlayerIndex = 0;
		this.players = new ArrayList<Player>();
		for (int i = 0; i < numberAIPlayers ; i++){
			if (aIDifficulties[i].equals("facile")){
				players.add(new AIplayer(aINames[i], this, new FirstCard()));
			} else {
				players.add(new AIplayer(aINames[i], this, new Grow()));
			}
		}
		for (int i =0; i < numberHumanPlayers; i++){
			players.add(new HumanPlayer(humanNames[i], this));
		}
	}
	
	/**
	 * Méthode abstraite avec une implémentation différente selon le type de partie
	 * @return la liste des vainqueurs
	 */
	public abstract ArrayList<Player> designateWinner(); 
	
	/**
	 * Getter de la saison actuelle
	 * @return int
	 */
	public int getSeason() {
		return season;
	}


	/**
	 * Setter de season, notifie les observers en cas de changement
	 * @param season
	 */
	public void setSeason(int season) {
		this.season = season;
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * 
	 * Getter de l'indice du joueur courant
	 * @return int
	 */
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	/**
	 * Getter de la liste des joueurs
	 * @return la liste des joueurs
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Getter du joueur courant
	 * @return Player
	 */
	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}
	
	/**
	 * Setter du joueur courant, notifie les observers en cas de changements
	 * @param index
	 */
	public void setCurrentPlayer(int index) {
		this.currentPlayerIndex = index;
		this.setChanged();
		this.notifyObservers(players.get(currentPlayerIndex));
	}
}
