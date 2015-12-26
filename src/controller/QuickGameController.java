package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.JOptionPane;

import card.Card;
import card.Ingredient;
import game.*;
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
						
						if(playedAction.equals("G"))
							currentPlayer.playGiant((Ingredient) playedCard);
						else if(playedAction.equals("E"))
							currentPlayer.playFertilizer((Ingredient) playedCard);
						else if(playedAction.equals("F")) {
							Player victim = choiceVictim();	
								currentPlayer.playFarfadet((Ingredient) playedCard, victim);
							}
						changePlayer();
						
						
					}
				});
			}
			
		}
	}
	public String choiceAction() {
		Object[] opt = {"G", "E", "F"};
		
		String choice= (String) JOptionPane.showInputDialog(null, "Choisissez votre action", null, JOptionPane.QUESTION_MESSAGE
							, null, opt, opt[0]);
				
		return choice;
	}
	
	public void changeSeason() {
		int season = game.getSeason();
		if(season == Game.WINTER)
			gv.displayWinner(game.designateWinner());
		else
			game.setSeason(season+1);
	}
}
	