package lo02.utt.giboulot.canat.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ListIterator;
//import java.util.Observable;
//import java.util.Observer;

import javax.swing.JOptionPane;

import lo02.utt.giboulot.canat.card.Ally;
import lo02.utt.giboulot.canat.card.Card;
import lo02.utt.giboulot.canat.card.Ingredient;
import lo02.utt.giboulot.canat.game.*;
import lo02.utt.giboulot.canat.player.AIplayer;
import lo02.utt.giboulot.canat.player.Player;
import lo02.utt.giboulot.canat.view.CardView;
import lo02.utt.giboulot.canat.view.PlayerView;

/**
 * La classe QuickGameController est le controleur des parties rapides.
 */
public class QuickGameController extends GameController{

	/**
	 * Instancie un nouveau controleur de partie rapide.
	 * Ajoute les �couteurs des cartes.
	 *
	 * @param game le mod�le de la partie rapide
	 */
	public QuickGameController(QuickGame game) {
		super(game);
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
	
	
			
	/**
	 * Ouvre un JOption_pane qui va permettre de choisir l'action � effectuer (g�ant, farfadet ou engrais).
	 *
	 * @return l'action choisie	 
	 */
	public String choiceAction() {
		Object[] opt = {"Geant", "Engrais", "Farfadet"};
		
		String choice= (String) JOptionPane.showInputDialog(null, "Choisissez votre action", null, JOptionPane.QUESTION_MESSAGE
							, null, opt, opt[0]);
				
		return choice;
	}
	
	/* (non-Javadoc)
	 * @see lo02.utt.giboulot.canat.controller.GameController#testAIPlay()
	 */
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
	
	/* (non-Javadoc)
	 * @see lo02.utt.giboulot.canat.controller.GameController#changePlayer()
	 */
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
			gv.displayWinner(game.designateWinner());
		
	}
	
	/* (non-Javadoc)
	 * @see lo02.utt.giboulot.canat.controller.GameController#changeSeason()
	 */
	public void changeSeason() {
		int season = game.getSeason();
		if(season == Game.WINTER);
		else game.setSeason(season+1);
	}

	/**
	 * Ajoute un �couteur sur une carte.
	 * Chaque carte, si on clique dessus, propose les diff�rentes actions possibles (G�ant, Farfadet et Engrais)
	 * et lance la m�thode ad�quate une fois l'action choisie.
	 * Une fois cela fait on change le joueur courant par un appel � la m�thode changePlayer().
	 * Cette m�thode priv�e est l� pour faciliter la lecture du code
	 *
	 * @param playedCard la carte sur laquelle on veut ajouter l'�couteur
	 * @param cv la vue de cette carte
	 * @param currentPlayer le possesseur de la carte
	 */
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
						Player victim = gv.choiceVictim();
						Card victimLastCard = victim.getHand().get(victim.getHand().size() -1);

						if(victimLastCard instanceof Ally) { //Ally lo02.utt.giboulot.canat.card is always the last lo02.utt.giboulot.canat.card
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
		
		
	}
}
	