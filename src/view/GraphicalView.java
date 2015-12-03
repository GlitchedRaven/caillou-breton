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
import game.Game;
import player.Player;
import java.awt.GridLayout;
import java.awt.CardLayout;

public class GraphicalView implements GameView, Observer {

	private Game g;
	private HashMap<JLabel, Player> playerLabel;
	private HashMap<JPanel, Player> playerPanel;
	private JFrame window;

	private JPanel playerDetailsPanel;
	private JPanel currentPlayerPanel;
	
	
	
	
	
	
	
	
	public GraphicalView(Game g) {
		super();
		this.g = g;
		this.playerLabel = new HashMap<JLabel, Player>();
		this.playerPanel = new HashMap<JPanel, Player>();
		
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
		
		
		
		
		for(ListIterator<Player> p = g.getPlayers().listIterator(); p.hasNext(); ) {
			
			//DETAIL PANEL
			Player player = p.next();
			JLabel l =  new JLabel(player.getName() + "\n" + " Menhir(s) : " + player.getNbMenhirs()
										+ "\n" + " Graine(s) : " + player.getNbRocks());
			this.playerLabel.put(l, player);		
			this.playerDetailsPanel.add(l);
			this.playerDetailsPanel.revalidate();
			this.playerDetailsPanel.repaint(); 
			// END DETAIL PANEL
			
			//CURRENT PLAYER PANEL
				// Display player's name
			JPanel pan = new JPanel(new GridLayout(0, 1, 0, 0));
			/*pan.add(new JLabel(playerLabel.get(player).getName()));
			pan.revalidate();
			pan.repaint();*/
			
			
				//Display the player's hand on the right panel
			for(ListIterator<Card> c = player.getHand().listIterator();c.hasNext();) {
				Card card = c.next();
				JButton btn = new JButton(card.toString());
				pan.add(btn);
				pan.revalidate();
				pan.repaint();
			}
			
			this.playerPanel.put(pan, player);
			this.currentPlayerPanel.add(pan, player.getName());
			//END CURRENT PLAYER PANNEL
		}
		
		//window.pack(); //size the frame
		window.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		getGameDetails(this.g);
		

	}

	@Override
	public void getPlayerDetails(Player player) {
		 CardLayout cl = (CardLayout)(this.currentPlayerPanel.getLayout());
		 cl.show(this.currentPlayerPanel, player.getName());
		
		
		
		
		
		
		

	}

	@Override
	public void getGameDetails(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public int choiceCard() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String choiceAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int choiceVictim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void displayWinner(ArrayList<Player> winner) {
		// TODO Auto-generated method stub

	}

}
