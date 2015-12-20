package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import card.Card;
import card.Ingredient;
import game.Game;
import player.Player;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

public class GraphicalView implements  Observer {

	private Game g;
	private JFrame window;

	private JPanel playerDetailsPanel;
	private JPanel currentPlayerPanel;
	
	private ArrayList<PlayerView> playerViews;
	
	
	
	public GraphicalView(Game g) {
		super();
		this.g = g;
		this.playerViews = new ArrayList<PlayerView>();
		
		//Prepare the game main window
		this.window = new JFrame("Menhir");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		window.getContentPane().add(splitPane);
		
		this.playerDetailsPanel = new JPanel();
		splitPane.setLeftComponent(playerDetailsPanel);
		this.playerDetailsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		this.currentPlayerPanel = new JPanel();
		splitPane.setRightComponent(this.currentPlayerPanel);
		currentPlayerPanel.setLayout(new CardLayout(0, 0));
		//END preparing main windows
		
		//Populates playerViews
		for(ListIterator<Player> p = g.getPlayers().listIterator(); p.hasNext();) 
			playerViews.add(new PlayerView(p.next()));
		//END population
		
		//Populates each panels with players informations	
		for(ListIterator<PlayerView> it = this.playerViews.listIterator(); it.hasNext();) {
			PlayerView pv = it.next();
			playerDetailsPanel.add(pv.getLabel());
			currentPlayerPanel.add(pv.getPan());
		//END Population
			
		}
		//window.pack(); //size the frame
		window.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		getGameDetails(this.g);
	}

	
	public void getPlayerDetails(Player player) {
		 CardLayout cl = (CardLayout)(this.currentPlayerPanel.getLayout());
		 cl.show(this.currentPlayerPanel, player.getName());
		
		
		
		
		
		
		

	}

	
	public void getGameDetails(Game game) {
		// TODO Auto-generated method stub

	}


	

	
	public Player choiceVictim() {
		ArrayList<Object> options = new ArrayList<Object>();
		for(ListIterator<Player> p = g.getPlayers().listIterator(); p.hasNext();) {
			Player player = p.next();
			options.add(player);		
		}
		
		Object[] opt = new Object[options.size()];
		options.toArray(opt);
		
		Player victim = (Player) JOptionPane.showInputDialog(null, "Choisissez votre victime", null, JOptionPane.QUESTION_MESSAGE
				, null, opt, opt[0]);
		return victim;
	}

	
	public void displayWinner(ArrayList<Player> winner) {
		// TODO Auto-generated method stub

	}

}
