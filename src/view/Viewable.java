package view;

import java.util.ArrayList;
import game.Game;
import player.Player;

public interface Viewable {
	public abstract void getPlayerDetails(Player player);
	public abstract void getGameDetails(Game game);
	public abstract int choiceCard();
	public abstract String choiceAction();
	public abstract int choiceVictim();
	public abstract void displayWinner(ArrayList<Player> winner);
}
