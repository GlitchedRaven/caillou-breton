package lo02.utt.giboulot.canat.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ListIterator;

import lo02.utt.giboulot.canat.card.Ally;
import lo02.utt.giboulot.canat.card.Card;
import lo02.utt.giboulot.canat.card.Ingredient;
import lo02.utt.giboulot.canat.game.*;
import lo02.utt.giboulot.canat.player.AIplayer;
import lo02.utt.giboulot.canat.player.HumanPlayer;
import lo02.utt.giboulot.canat.player.Player;
import lo02.utt.giboulot.canat.view.CardView;
import lo02.utt.giboulot.canat.view.PlayerView;


/**
 * La classe AdvancedGameController est le controleur des parties avanc�e.
 */
public class AdvancedGameController extends GameController  {
	
	/**
	 * Instancie un nouveau controleur pour une partie avanc�e.
	 * Ajoute les �couteurs de chaque carte.
	 *
	 * @param game le mod�le de la partie
	 */
	public AdvancedGameController(AdvancedGame game) {
		super(game);
		//Card distribution
		
				if (!game.isSaved()) {
					for (ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
						Player player = p.next();
						if (player instanceof HumanPlayer) {
							boolean drawAlly = gv.choiceAllyOrRock(player);

							if (drawAlly) {
								player.setHand(((AdvancedGame) game).getAllyDeck().remove());
								player.setNbRocks(0);
							}
						}
					} 
				}
		//END lo02.utt.giboulot.canat.card distribution
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
	
	
	/* (non-Javadoc)
	 * @see lo02.utt.giboulot.canat.controller.GameController#changeSeason()
	 */
	@Override
	public void changeSeason() {
		int season = game.getSeason();
		if(season == Game.WINTER);
		else game.setSeason(season+1);
	}

	/**
	 * Passe � la manche suivante. 
	 * Pour cela la m�thode refait la distribution des cartes et ajoute les �couteurs � chacune des nouvelles cartes.
	 * Si c'�tait le dernier round, la m�thode appelle la m�thode displayWinner() � la place.
	 */
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

	/* (non-Javadoc)
	 * @see GameController#changePlayer()
	 */
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

	/* (non-Javadoc)
	 * @see GameController#testAIPlay()
	 */
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
	
	/**
	 * Ajoute un �couteur sur une carte.
	 * Chaque carte, si on clique dessus, propose les diff�rentes actions possibles selon que la carte soit un Ingr�dient
	 * ou un Alli� et lance la m�thode ad�quate une fois l'action choisie.
	 * Une fois cela fait on change le joueur courant par un appel � la m�thode changePlayer().
	 * Cette m�thode priv�e est l� pour faciliter la lecture du code.
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
					String playedAction = gv.choiceAction();

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
		
		else if(playedCard instanceof Ally) {
			cv.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(((Ally) playedCard).getAllyType() == Ally.GIANTMOLE) {
						Player victim = gv.choiceVictim();
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
