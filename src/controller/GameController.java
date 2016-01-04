package controller;

import game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Observer;

import javax.swing.JOptionPane;

import card.Card;
import view.*;
import player.*;

public abstract class GameController  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5064169357477076648L;
	protected transient GameView gv;
	public void setGv(GameView gv) {
		this.gv = gv;
	}


	public Game getGame() {
		return game;
	}


	protected Game game;
	
	
	public GameController(Game game) {
		super();
		this.gv = new GameView(game);
		this.game = game;
		GameController gc = this;
		this.gv.getSaveButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String saveFileName = "sauvegarde.txt";
				FileOutputStream saveFile;
				ObjectOutputStream saving = null;
				try {
					saveFile = new FileOutputStream(saveFileName);
					saving = new ObjectOutputStream(saveFile);
					saving.writeObject(gc);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						saving.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
	}

	
	
	public GameView getGv() {
		return gv;
	}
	
	public abstract String choiceAction();
	public abstract void changeSeason();
	public abstract void changePlayer();
	public abstract boolean testAIPlay();
	public abstract void addCardListener(Card playedCard, CardView cv, Player currentPlayer);
	
	
	public Player choiceVictim() {
		HashMap<String, Player> options = new HashMap<String, Player>();
		for(ListIterator<Player> p = game.getPlayers().listIterator(); p.hasNext();) {
			Player player = p.next();
			options.put(player.getName() + "=> graines :" + player.getNbRocks() + " menhirs :" + player.getNbMenhirs(), player);			

		}
		
		Object[] opt = new Object[options.size()];
		options.keySet().toArray(opt);
		
		String victimName = (String) JOptionPane.showInputDialog(null, "Choisissez votre victime", null, JOptionPane.QUESTION_MESSAGE
				, null, opt, opt[0]);
		return options.get(victimName);
	}
}
