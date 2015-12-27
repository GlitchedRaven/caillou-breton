package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Observer;

import javax.swing.JOptionPane;

import card.Ally;
import card.Card;
import card.Deck;
import card.Ingredient;
import game.*;
import player.AIplayer;
import player.HumanPlayer;
import player.Player;
import view.CardView;
import view.PlayerView;

public class AdvancedGameController extends GameController  {

	public AdvancedGameController(AdvancedGame game) {
		super(game);
		//Card distribution
		
				for(ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
					Player player = p.next();
					if(player instanceof HumanPlayer){
						boolean drawAlly = gv.choiceAllyOrRock(player);

						if(drawAlly) {
							player.setHand(((AdvancedGame) game).getAllyDeck().remove());
							player.setNbRocks(0);
						}
					}
				}
				//END card distribution
		for(ListIterator<PlayerView> it = gv.getPlayerViews().listIterator(); it.hasNext();) {
			PlayerView pv = it.next();
			Player currentPlayer = pv.getPlayer();
			for(Iterator<CardView> it2 = pv.getCardViews().values().iterator(); it2.hasNext();) {
				CardView cv = it2.next();
				Card playedCard = cv.getCard();
				addCardListener(playedCard, cv, currentPlayer);
			}
		}
		
		if(testAIPlay())
			changePlayer();
		
	}
	
	@Override
	public String choiceAction() {
		Object[] opt = {"Geant", "Engrais", "Farfadet"};
		String choice= (String) JOptionPane.showInputDialog(null, "Choisissez votre action", null, JOptionPane.QUESTION_MESSAGE
							, null, opt, opt[0]);
		return choice;
		
	}

	@Override
	public void changeSeason() {
		int season = game.getSeason();
		if(season == Game.WINTER);
		else game.setSeason(season+1);
				
		
	}

	private void changeRound() {
		
		if(((AdvancedGame) game).getRound() < game.getPlayers().size()) {
			((AdvancedGame) game).setRound();
			//Card distribution
			for(ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
				Player player = p.next();
				if(player instanceof HumanPlayer){
					boolean drawAlly = gv.choiceAllyOrRock(player);

					if(drawAlly) {
						player.setHand(((AdvancedGame) game).getAllyDeck().remove());
						player.setNbRocks(0);
					}
				}
			}
			//END card distribution
			//Add listeners
			for(ListIterator<PlayerView> it = gv.getPlayerViews().listIterator(); it.hasNext();) {
				PlayerView pv = it.next();
				Player currentPlayer = pv.getPlayer();
				for(Iterator<CardView> it2 = pv.getCardViews().values().iterator(); it2.hasNext();) {
					CardView cv = it2.next();
					Card playedCard = cv.getCard();
					addCardListener(playedCard, cv, currentPlayer);
				}
			}
			//END add listener
			if(testAIPlay())
				changePlayer();
		}
		
		else
			gv.displayWinner(game.designateWinner());
		
		
	}

	@Override
	public void changePlayer() {
		int season = game.getSeason();
		int currentIndex = game.getCurrentPlayerIndex();
		
		if( currentIndex < game.getPlayers().size() - 1 ){
			game.setCurrentPlayer(currentIndex + 1);
			if(testAIPlay())
				changePlayer();
		}
		else if (season != Game.WINTER){
			game.setCurrentPlayer(0);
			changeSeason();
			if(testAIPlay())
				changePlayer();
			
		}
		else
			changeRound();
		
	}

	@Override
	public boolean testAIPlay() {
		int currentIndex = game.getCurrentPlayerIndex();
		Player currentPlayer = game.getPlayers().get(currentIndex);
		if(currentPlayer instanceof AIplayer) {
			((AIplayer) currentPlayer).playACard();
			
			return true;
			
		}
		else
			return false;
	}
	
	private void addCardListener(Card playedCard, CardView cv, Player currentPlayer) {
		if(playedCard instanceof Ingredient) {
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
						Card victimLastCard = victim.getHand().get(victim.getHand().size() -1);

						if(victimLastCard instanceof Ally) { //Ally card is always the last card
							if(((Ally) victimLastCard).getAllyType() == Ally.WATCHDOG) {
								if(gv.choicePlayWatchDog(victim)) {
									victim.playWatchDog((Ally) victimLastCard);

								}
							}	
							
						}
						currentPlayer.playFarfadet((Ingredient) playedCard, victim);
					}
					changePlayer();


				}
			});
		}
		
		else if(playedCard instanceof Ally) {
			cv.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(((Ally) playedCard).getAllyType() == Ally.GIANTMOLE) {
						Player victim = choiceVictim();
						currentPlayer.playGiantMole((Ally) playedCard, victim);
					}
					else if(((Ally) playedCard).getAllyType() == Ally.WATCHDOG)
						currentPlayer.playWatchDog((Ally) playedCard);
					changePlayer();
				}
				
			});
		}
	}
	
}
