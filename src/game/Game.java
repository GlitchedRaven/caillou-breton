package game;

import java.util.ArrayList;
import java.util.Scanner;
import player.Player;


public abstract class Game {
	
	public final static int SPRING = 0;
	public final static int SUMMER = 1;
	public final static int AUTUMN = 2;
	public final static int WINTER = 3;
	public final static String[] SEASONS = {"Printemps", "Ete", "Automne", "Hiver"};

	private int season;
	
	private Scanner user_input = new Scanner( System.in );
	protected ArrayList<Player> players;
	
	
	public Game() {
		super();
		this.season = SPRING;
		
		//Instantiate the players
		System.out.println("Combien de joueurs ?");
		int nbJoueur = user_input.nextInt();
		this.players = new ArrayList<Player>();
		for(int i = 0; i < nbJoueur ; i++) {
			System.out.println("Quel nom pour le joueur " + (i+1) + " ?");
			String name = user_input.next();
			
			players.add(i, new Player(name, this));
		}
		// Instantiate the players END
		
		
	}


	public abstract ArrayList<Player> designateWinner(); 
	
	
	public int getSeason() {
		return season;
	}


	public void setSeason() {
		this.season++;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}


}
