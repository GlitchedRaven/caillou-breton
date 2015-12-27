package view;

import java.util.*;

import javax.swing.*;

import game.*;
import player.Player;
import java.awt.GridLayout;

import java.awt.CardLayout;

public class GameView implements  Observer {

	private Game g;
	private JFrame window;
	private JLabel seasonLabel;
	private JLabel roundLabel;
	private JPanel playerDetailsPanel;
	private JPanel currentPlayerPanel;
	
	private ArrayList<PlayerView> playerViews;
	
	
	
	public GameView(Game g) {
		super();
		this.g = g;
		this.g.addObserver(this);
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
		this.seasonLabel = new JLabel("Saison actuelle : " + Game.SEASONS[g.getSeason()]);
		
		if(this.g instanceof AdvancedGame) {
			String labelText = String.valueOf(((AdvancedGame) g).getRound());
			this.roundLabel = new JLabel("Round :" + labelText);
			this.playerDetailsPanel.add(roundLabel);
		}
		this.playerDetailsPanel.add(seasonLabel);
		
		this.currentPlayerPanel = new JPanel();
		splitPane.setRightComponent(this.currentPlayerPanel);
		currentPlayerPanel.setLayout(new CardLayout(0, 0));
		//END preparing main windows
		
		//Populates playerViews and Populates each panels with players informations	
		for(ListIterator<Player> p = g.getPlayers().listIterator(); p.hasNext();){ 
			Player player= p.next();
			PlayerView pv = new PlayerView(player);
			
			playerViews.add(pv);
			playerDetailsPanel.add(pv.getLabel());
			currentPlayerPanel.add(pv.getPan(), player.getName() );
		}
			
		//END population
		
	
			
		
		//window.pack(); //size the frame
		window.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if(arg instanceof Player) {
			getPlayerDetails((Player) arg);
		}
		
		
		this.seasonLabel.setText("Saison actuelle : " + Game.SEASONS[g.getSeason()]);
		playerDetailsPanel.repaint();
		playerDetailsPanel.revalidate();
		
		currentPlayerPanel.repaint();
		currentPlayerPanel.revalidate();
	}
	
	public void update() {
		
	
		this.seasonLabel.setText("Saison actuelle : " + Game.SEASONS[g.getSeason()]);
		playerDetailsPanel.repaint();
		playerDetailsPanel.revalidate();
		
		currentPlayerPanel.repaint();
		currentPlayerPanel.revalidate();
	}

	
	public ArrayList<PlayerView> getPlayerViews() {
		return playerViews;
	}
	
	public void getPlayerDetails(Player player) {
		 CardLayout cl = (CardLayout)(this.currentPlayerPanel.getLayout());
		 cl.show(this.currentPlayerPanel, player.getName());
	}
		
	public void displayWinner(ArrayList<Player> winner) {
		String winners = " " ;
		for(Player w : winner)
			winners += (w.getName() + " gagne ! \n");
		JOptionPane.showMessageDialog(this.window, winners );

	}
	
	public boolean choicePlayWatchDog(Player victim) {
		
		int choice=  JOptionPane.showConfirmDialog(null, victim.getName() + ", souhaitez vous jouez votre Chien de Garde ?", 
														"Contre-attaque", JOptionPane.YES_NO_OPTION);
		if(choice == JOptionPane.YES_OPTION)		
			return true;
		else
			return false;
	}



	public boolean choiceAllyOrRock(Player player) {
	
		int choice=  JOptionPane.showConfirmDialog(null, player.getName() + ", souhaitez vous piocher un Allié ? (Vous commencerez la partie sans"
							+ " graines !", "Contre-attaque", JOptionPane.YES_NO_OPTION);
		if(choice == JOptionPane.YES_OPTION)		
			return true;
		else
			return false;
	}
}