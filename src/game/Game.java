package game;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Scanner;
import java.util.Observable;
import player.*;


public abstract class Game extends Observable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5801013053144630175L;
	
	public final static int SPRING = 0;
	public final static int SUMMER = 1;
	public final static int AUTUMN = 2;
	public final static int WINTER = 3;
	public final static String[] SEASONS = {"Printemps", "Ete", "Automne", "Hiver"};
	
	private int season;
	private boolean saved;
	
	protected ArrayList<Player> players;
	protected int currentPlayerIndex;
	
	
	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	
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

	public abstract ArrayList<Player> designateWinner(); 
	
	
	public int getSeason() {
		return season;
	}



	public void setSeason(int season) {
		this.season = season;
		this.setChanged();
		this.notifyObservers();
	}
	
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	public void setCurrentPlayer(int index) {
		this.currentPlayerIndex = index;
		this.setChanged();
		this.notifyObservers(players.get(currentPlayerIndex));
	}


}
