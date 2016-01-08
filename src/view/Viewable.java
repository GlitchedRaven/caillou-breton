package view;

import java.util.ArrayList;
import player.Player;

public interface Viewable {
	public abstract void getPlayerDetails(Player player);
	public abstract String choiceAction();
	public abstract Player choiceVictim();
	public abstract void displayWinner(ArrayList<Player> winner);
}
