package game;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Scanner;
import java.util.Observable;
import player.*;

/**
 * Classe repr�sentant la partie, elle poss�de les m�thodes pour changer de saison et de joueurs.
 */
public abstract class Game extends Observable implements Serializable{
	
	/**
	 * serialVersionUID n�cessaire � la s�rialisation
	 */
	private static final long serialVersionUID = 5801013053144630175L;
	/**
	 * association entre les saisons et des entiers
	 */
	public final static int SPRING = 0;
	/**
	 * association entre les saisons et des entiers
	 */
	public final static int SUMMER = 1;
	/**
	 * association entre les saisons et des entiers
	 */
	public final static int AUTUMN = 2;
	/**
	 * association entre les saisons et des entiers
	 */
	public final static int WINTER = 3;
	/**
	 * tableau d'association des saisons � leur noms en fran�ais
	 */
	public final static String[] SEASONS = {"Printemps", "Ete", "Automne", "Hiver"};
	/**
	 * entier repr�sentant la saison en cours
	 */
	private int season;
	/**
	 * bool�en d�terminant si la partie a �t� sauvegard�e ou non
	 */
	private boolean saved;
	/**
	 * Arraylist repr�sentant les joueurs de la partie
	 */
	protected ArrayList<Player> players;
	/**
	 * indice permettant de d�terminer qui est le joueur courant
	 */
	protected int currentPlayerIndex;
	
	/**
	 * accesseur du bool�en saved
	 * @return boolean
	 */
	public boolean isSaved() {
		return saved;
	}
	
	/**
	 * setter du bool�en saved
	 * @param saved
	 */
	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	
	/**
	 * Constructeur de la classe Game, il appelle le constructeur des joueurs 
	 * @param numberAIPlayers : nombre de joueurs IA
	 * @param aIDifficulties : difficult� des joueurs IA
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
	 * classe abstraite avec une impl�mentation diff�rente selon le type de partie
	 * @return ArrayList<Player>
	 */
	public abstract ArrayList<Player> designateWinner(); 
	
	/**
	 * getter de la saison actuelle
	 * @return int
	 */
	public int getSeason() {
		return season;
	}


	/**
	 * setter de season, notifie les observers en cas de changement
	 * @param season
	 */
	public void setSeason(int season) {
		this.season = season;
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * getter de l'indice du joueur courant
	 * @return int
	 */
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	/**
	 * getter de l'arraylist des joueurs
	 * @return ArrayList<Player>
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * getter du joueur courant
	 * @return Player
	 */
	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}
	
	/**
	 * setter du joueur courant, notifie les observers en cas de changements
	 * @param index
	 */
	public void setCurrentPlayer(int index) {
		this.currentPlayerIndex = index;
		this.setChanged();
		this.notifyObservers(players.get(currentPlayerIndex));
	}


}
