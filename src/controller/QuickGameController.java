package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.JOptionPane;

import card.Card;
import card.Ingredient;
import game.*;
import player.AIplayer;
import player.Player;
import view.CardView;
import view.PlayerView;

public class QuickGameController extends GameController {

	public QuickGameController(QuickGame game) {
		super(game);
		for(ListIterator<PlayerView> it = gv.getPlayerViews().listIterator(); it.hasNext();) {
			PlayerView pv = it.next();
			Player currentPlayer = pv.getPlayer();
			for(Iterator<CardView> it2 = pv.getCardViews().values().iterator(); it2.hasNext();) {
				CardView cv = it2.next();
				Card playedCard = cv.getCard();
				cv.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String playedAction = choiceAction();
						
						if(playedAction.equals("Geant"))
							currentPlayer.playGiant((Ingredient) playedCard);
						else if(playedAction.equals("Engrais"))
							currentPlayer.playFertilizer((Ingredient) playedCard);
						else if(playedAction.equals("Farfadet")) {
							Player victim = choiceVictim();	
								currentPlayer.playFarfadet((Ingredient) playedCard, victim);
							}
						changePlayer();
						
						
					}
				});
			}
			testAIPlay();
			
		}
		
	}
	public String choiceAction() {
		Object[] opt = {"Geant", "Engrais", "Farfadet"};
		
		String choice= (String) JOptionPane.showInputDialog(null, "Choisissez votre action", null, JOptionPane.QUESTION_MESSAGE
							, null, opt, opt[0]);
				
		return choice;
	}
	public void testAIPlay() {
		int currentIndex = game.getCurrentPlayerIndex();
		Player currentPlayer = game.getPlayers().get(currentIndex);
		if(currentPlayer instanceof AIplayer) {
			((AIplayer) currentPlayer).playACard();
			changePlayer();
		}
	}
	
	public void changePlayer() {
		int currentIndex = game.getCurrentPlayerIndex();
		if( currentIndex < game.getPlayers().size() - 1 )
			game.setCurrentPlayer(currentIndex + 1);
		else {
			changeSeason();
			if(game.getSeason() < game.WINTER)
				game.setCurrentPlayer(0);
		}
		
		testAIPlay();
		
	}
	public void changeSeason() {
		int season = game.getSeason();
		if(season == Game.WINTER)
			gv.displayWinner(game.designateWinner());
		else
			game.setSeason(season+1);
	}
}
	