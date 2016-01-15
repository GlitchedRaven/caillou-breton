package view;

import java.util.*;

import javax.swing.*;

import game.*;
import player.Player;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

// TODO: Auto-generated Javadoc
/**
 * la classe GameView est la vue graphique d'une partie de Menhir.
 */
public class GameView implements  Observer {

	/** La partie représentée pa la vue. */
	private Game g;
	
	/** La fenêtre principale. */
	private JFrame window;
	
	/** Le label de la saison en cours. */
	private JLabel seasonLabel;
	
	/** Le label de la manche en cours. */
	private JLabel roundLabel;
	
	/** La partie de la fenêtre dédiée aux détails de tous les joueurs (graines, menhirs, etc...). */
	private JPanel playerDetailsPanel;
	
	/** La partie de la fenêtre dédiée aux détails d'une joueur étant en train de jouer, en particulier sa main. */
	private JPanel currentPlayerPanel;
	
	/** La liste des vues des joueurs. */
	private ArrayList<PlayerView> playerViews;
	
	/** Le boutton de sauvegarde. */
	private JButton saveButton;
	
	
	
	/**
	 * Getter du bouton de sauvegarde.
	 *
	 * @return le bouton de sauvegarde
	 */
	public JButton getSaveButton() {
		return saveButton;
	}

	/**
	 * Instantie une nouvelle vue d'une partie en créeant et en agençant les différentes fenêtres
	 *
	 * @param g le jeu a représenter
	 */
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
		this.playerDetailsPanel.setPreferredSize(new Dimension(350,500));
		
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

			currentPlayerPanel.add(pv.getPan(), player.getName());

		}
			
		//END population
		this.saveButton = new JButton("Sauvegarder");
		playerDetailsPanel.add(saveButton);
	
		window.setPreferredSize(new Dimension(700,600));//defining the wanted size	
		window.pack(); //size the frame
		window.setLocationRelativeTo(null);//centers the frame
		window.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		if(arg instanceof Player) {
			getPlayerDetails((Player) arg);
		}
		
		
		this.seasonLabel.setText("Saison actuelle : " + Game.SEASONS[g.getSeason()]);
		if(this.g instanceof AdvancedGame) {
			String labelText = String.valueOf(((AdvancedGame) g).getRound());
			this.roundLabel.setText("Round :" + labelText);
		}
		playerDetailsPanel.repaint();
		playerDetailsPanel.revalidate();
		
		currentPlayerPanel.repaint();
		currentPlayerPanel.revalidate();
	}
	
	/**
	 * Getter de la liste des vues des joueurs.
	 *
	 * @return la liste des vues joueurs
	 */
	public ArrayList<PlayerView> getPlayerViews() {
		return playerViews;
	}
	
	/**
	 * Affiche les détails du joueur en paramètre dans la partie dédiée de la fenêtre.
	 *
	 * @param player le joueur 
	 * 
	 */
	public void getPlayerDetails(Player player) {
		 CardLayout cl = (CardLayout)(this.currentPlayerPanel.getLayout());
		 cl.show(this.currentPlayerPanel, player.getName());
	}
		
	/**
	 * Affiche le vainqueur.
	 *
	 * @param winner la liste des vainqueurs à afficher.
	 */
	public void displayWinner(ArrayList<Player> winner) {
		String winners = " " ;
		for(Player w : winner)
			winners += (w.getName() + " gagne ! \n");
		JOptionPane.showMessageDialog(this.window, winners );

	}
	
	/**
	 * Propose de jouer un Chien de Garde.
	 *
	 * @param victim la victime d'une attaque de Farfadet.
	 * @return true, si le joueur décide de jouer son Chien de Garde.
	 */
	public boolean choicePlayWatchDog(Player victim) {
		
		int choice=  JOptionPane.showConfirmDialog(null, victim.getName() + ", souhaitez vous jouez votre Chien de Garde ?", 
														"Contre-attaque", JOptionPane.YES_NO_OPTION);
		if(choice == JOptionPane.YES_OPTION)		
			return true;
		else
			return false;
	}

	/**
	 * Propose de piocher un allié plutôt que de commencer la partie avec des graînes.
	 *
	 * @param player le joueur à qui l'on proposede piocher
	 * @return true, si le joueur décide de piocher un allié
	 */
	public boolean choiceAllyOrRock(Player player) {
	
		int choice=  JOptionPane.showConfirmDialog(null, player.getName() + ", souhaitez vous piocher un Allié ? (Vous commencerez la partie sans"
							+ " graines !", "Contre-attaque", JOptionPane.YES_NO_OPTION);
		if(choice == JOptionPane.YES_OPTION)		
			return true;
		else
			return false;
	}
	
	/**
	 * Propose à un joueur les différentes actions possibles de ses cartes.
	 *
	 * @return le choix d'action du joueur
	 */
	public String choiceAction() {
		Object[] opt = {"Geant", "Engrais", "Farfadet"};
		String choice= (String) JOptionPane.showInputDialog(null, "Choisissez votre action", null, JOptionPane.QUESTION_MESSAGE
							, null, opt, opt[0]);
		return choice;
	}
	
	/**
	 * Donne les différentes victimes possibles d'une action Farfadet ou Taupe Géante.
	 *
	 * @return la victime choisie
	 */
	public Player choiceVictim() {
		HashMap<String, Player> options = new HashMap<String, Player>();
		for(ListIterator<Player> p = g.getPlayers().listIterator(); p.hasNext();) {
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

