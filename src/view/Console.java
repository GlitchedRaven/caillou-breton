package view;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import game.Game;
import player.*;

public class Console implements GameView {
	
	public void getPlayerDetails(Player player) {
		System.out.println("\nAu tour de " + player.getName());
		System.out.println(player.toString());
	}
	
	public void getGameDetails(Game game){
		System.out.println("Saison actuelle : " + Game.SEASONS[game.getSeason()]);
		for(ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
			Player player = p.next();
			System.out.println(player.getName() + "\n" + " Menhir(s) : " + player.getNbMenhirs()
												+ "\n" + " Graine(s) : " + player.getNbRocks());
		}
	}
	public int choiceCard(Scanner userInput) {
		
		System.out.println("Quelle carte voulez-vous jouer ?");
		return userInput.nextInt() - 1;
	}	
	
	public String choiceAction(Scanner userInput){
		System.out.println("Quelle action souhaitez vous effectuer : G/E/F ?");
		return userInput.next();
	}	
	
	public int choiceVictim(Scanner userInput) {
		System.out.println("Quel joueur souhaitez vous voler ?");
		return userInput.nextInt();  
	}
	
	public boolean choiceAllyOrRock(Scanner userInput, Player player){
		System.out.println(player.getName() + ", souhaitez vous piocher un Allié ? O(ui)/N(on) (Vous commencerez la partie sans"
							+ "graines !");
		if(userInput.next() == "O")
			return true;
		else
			return false;
		
	}
	
	public void displayWinner(ArrayList<Player> winner) {
		for(Player w : winner)
			System.out.println(w.getName() + " wins !");
	}
	
	
	public Console(){
		super();
	}
}
