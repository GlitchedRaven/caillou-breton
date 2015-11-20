package view;

import java.util.ArrayList;
import java.util.Scanner;

import game.Game;
import player.Player;

public interface GameView {
	public abstract void getPlayerDetails(Player player);
	public abstract void getGameDetails(Game game);
	public abstract int choiceCard(Scanner userInput);
	public abstract String choiceAction(Scanner userInput);
	public abstract int choiceVictim(Scanner userInput);
	public abstract void displayWinner(ArrayList<Player> winner);
}
