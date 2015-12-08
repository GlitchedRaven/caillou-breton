package view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Scanner;

import game.Game;
import player.*;

public class Console implements GameView {
	
	private Scanner userInput;
	
	public int choiceTypeOfGame() {
		System.out.println("Souhaitez-vous faire une partie rapide (1) ou une partie avancée (2) ?");
		return userInput.nextInt();
	}
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
	public int choiceCard() {
		
		System.out.println("Quelle carte voulez-vous jouer ?");
		return this.userInput.nextInt() - 1;
	}	
	
	public String choiceAction(){
		System.out.println("Quelle action souhaitez vous effectuer : G/E/F ?");
		return this.userInput.next();
	}	
	
	public boolean choicePlayWatchDog(Player victim) {
		System.out.println(victim.getName() +", souhaitez vous jouer votre Chien de Garde en réponse ?  O(ui)/N(on)");
		if(this.userInput.next().equals("O"))
			return true;
		else
			return false;
	}
	public int choiceVictim() {
		System.out.println("Quel joueur souhaitez vous attaquer ?");
		return this.userInput.nextInt();  
	}
	
	public boolean choiceAllyOrRock(Player player){
		System.out.println(player.getName() + ", souhaitez vous piocher un Allié ? O(ui)/N(on) (Vous commencerez la partie sans"
							+ " graines !");
		if(this.userInput.next().equals("O"))
			return true;
		else
			return false;
		
	}
	
	public void displayWinner(ArrayList<Player> winner) {
		for(Player w : winner)
			System.out.println(w.getName() + " wins !");
	}
	
	public void displayAdvancedScore(HashMap<Player, Integer> score) {
		for(Player p : score.keySet()) {
			System.out.println("Score de " + p.getName() + " " +  score.get(p));
		}
	}
	public Console(){
		super();
		this.userInput = new Scanner(System.in);
	}
}
