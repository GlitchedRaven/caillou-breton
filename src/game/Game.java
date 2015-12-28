package game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Observable;
import player.*;


public abstract class Game extends Observable{
	
	public final static int SPRING = 0;
	public final static int SUMMER = 1;
	public final static int AUTUMN = 2;
	public final static int WINTER = 3;
	public final static String[] SEASONS = {"Printemps", "Ete", "Automne", "Hiver"};

	private int season;
	
	private Scanner user_input = new Scanner( System.in );
	protected ArrayList<Player> players;
	protected int currentPlayerIndex;
	
	
	public Game() {
		super();
		this.season = SPRING;
		this.currentPlayerIndex = 0;
		
		//Instantiate the players
				System.out.println("Combien de joueurs AI?");
				int nbJoueurAI = user_input.nextInt();
				this.players = new ArrayList<Player>();
				for(int i = 0; i < nbJoueurAI ; i++) {
					System.out.println("Quel nom pour le joueur AI " + (i+1) + " ?");
					String name = user_input.next();
					System.out.println("Quelle difficulté pour le joueur AI "+ name + "? (facile/moyen)");
					String difficulté = user_input.next();
					
					if (difficulté.equals("facile")){
						players.add(new AIplayer(name, this, new FirstCard()));
					} else if (difficulté.equals("moyen")){
						players.add(new AIplayer(name, this, new Grow()));
					} else {
						players.add(new AIplayer(name, this, new FirstCard()));
					}
					
				}
				System.out.println("Combien de joueurs humains?");
				int nbJoueur = user_input.nextInt();
				for(int i = 0; i < nbJoueur ; i++) {
					System.out.println("Quel nom pour le joueur humain " + (i+1) + " ?");
					String name = user_input.next();
					
					players.add(new HumanPlayer(name, this));
				}
				// Instantiate the players END
		
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
