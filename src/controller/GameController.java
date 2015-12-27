package controller;

import game.Game;


import java.util.ArrayList;

import java.util.ListIterator;
import java.util.Observer;

import javax.swing.JOptionPane;

import view.*;
import player.*;

public abstract class GameController  {
	protected GameView gv;
	protected Game game;
	
	
	public GameController(Game game) {
		super();
		this.gv = new GameView(game);
		this.game = game;
		
	}

	
	
	public GameView getGv() {
		return gv;
	}
	
	public abstract String choiceAction();
	public abstract void changeSeason();
	public abstract void changePlayer();
	public abstract boolean testAIPlay();
	
	
	public Player choiceVictim() {
		ArrayList<Object> options = new ArrayList<Object>();
		for(ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
			Player player = p.next();
			options.add(player.getName() + "=> graines :" + player.getNbRocks() + " menhirs :" + player.getNbMenhirs());		
		}
		
		Object[] opt = new Object[options.size()];
		options.toArray(opt);
		
		Player victim = (Player) JOptionPane.showInputDialog(null, "Choisissez votre victime", null, JOptionPane.QUESTION_MESSAGE
				, null, opt, opt[0]);
		return victim;
	}
}
