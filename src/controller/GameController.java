package controller;
import card.Ingredient;
import game.Game;

import java.util.Scanner;

import card.*;
import view.*;
import player.*;

public class GameController {
	private GameView view;
	private Game game;
	
	
	public GameController(GameView view, Game game) {
		super();
		this.view = view;
		this.game = game;
	}


	public void doAction(String playedAction, int playedCard, Player currentPlayer) {
		
		if(playedAction.equals("G"))
			currentPlayer.playGiant((Ingredient) currentPlayer.getHand().get(playedCard));
		else if(playedAction.equals("E"))
			currentPlayer.playFertilizer((Ingredient) currentPlayer.getHand().get(playedCard));
		else if(playedAction.equals("F")) {
			int victim = view.choiceVictim();
			currentPlayer.playFarfadet((Ingredient) currentPlayer.getHand().get(playedCard), game.getPlayers().get(victim - 1));
		}
		else
			System.out.println("Tour passé");
	
		currentPlayer.getHand().remove(playedCard); //On retire la carte jouée
	}
	public void changeSeason() {
		game.setSeason();
	}
}
