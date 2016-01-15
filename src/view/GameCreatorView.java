package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import game.AdvancedGame;
import game.QuickGame;
/**
 * Cette classe permet la création de parties via une interface graphique composée de JOption_pane et d'une fenêtre de choix entre partie rapide
 * et partie avancée et vérifie qu'une partie ne contienne pas plus de 6 joueurs (IA et humains). L'ordre de jeu est déterminé par l'ordre de création des joueurs ainsi
 * Les joueurs IA jouent toujours avant les joueurs humains.
 * Pour le lancement de la partie un appel est réalisé au main de la partie concernée.
 * @see game.QuickGame, game.AdvancedGame, game.Game
 */
public class GameCreatorView {
	/**
	 * Nombre de joueurs IA
	 */
	private int numberAIplayers;
	/**
	 * Difficultés des joueurs IA, dans l'ordre dans lequel ils sont créés.
	 */
	private String[] aIDifficulties;
	/**
	 * Noms des joueurs IA, dans l'ordre dans lequel ils sont créés.
	 */
	private String[] aiNames;
	/**
	 *  Nombre de joueurs humains.
	 */
	private int numberHumanPlayers;
	/**
	 * Noms des joueurs humains, dans l'ordre de leur création.
	 */
	private String[] humanNames;
	/**
	 * Fenêtre qui servira au choix partie rapide.partie avancée
	 */
	private JFrame window;
	/**
	 * Instancie un Objet de GameCreatorView qui va créer la partie selon les choix des joueurs humains.
	 */
	public GameCreatorView(){
		
		Object[] opt = {0,1,2,3,4,5,6};
		this.numberAIplayers = (int) JOptionPane.showInputDialog(null, "Combien de joueurs AI", null, JOptionPane.QUESTION_MESSAGE
				, null, opt, opt[0]);
		this.aIDifficulties = new String[this.numberAIplayers];
		this.aiNames = new String[this.numberAIplayers];
		Object[] difficulties = {"facile","moyen"};
		for (int i = 0; i < this.numberAIplayers; i++ ){
			this.aiNames[i] = (String)JOptionPane.showInputDialog(null , "nom du joueur AI " + (i+1) + " : ",
                    null, JOptionPane.QUESTION_MESSAGE, null, null, "");
			this.aIDifficulties[i] = (String)JOptionPane.showInputDialog(null, "difficulté du joueur AI " + this.aiNames[i]
                    , null, JOptionPane.QUESTION_MESSAGE, null, difficulties, difficulties[0]);
		}
		
		Object[] opt2 = {0,0,0,0,0,0,0};		
		for (int i = 0; i <= (6-this.numberAIplayers) ; i++) opt2[i] = i;
		this.numberHumanPlayers = (int) JOptionPane.showInputDialog(null, "Combien de joueurs humains", null, 
				JOptionPane.QUESTION_MESSAGE, null, opt2, opt2[0]);
	
		
		this.humanNames = new String[this.numberHumanPlayers];
		for (int i = 0; i < this.numberHumanPlayers; i++ ){
			this.humanNames[i] = (String)JOptionPane.showInputDialog(null , "nom du joueur humain " + (i+1) + " : ",
                    null, JOptionPane.QUESTION_MESSAGE, null, null, "");
		}
		
		this.window = new JFrame("choix de partie");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		window.setPreferredSize(new Dimension(500,400));
		window.pack();
		
		
		JButton btnPartieRapide = new JButton("Partie rapide");
		btnPartieRapide.setHorizontalAlignment(SwingConstants.LEFT);
		window.getContentPane().add(btnPartieRapide);
		btnPartieRapide.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				QuickGame g = new QuickGame(numberAIplayers, aIDifficulties, aiNames, numberHumanPlayers, humanNames);
				QuickGame.main(null, g);
				
			}
			
		});
		
		JButton btnPartieAvance = new JButton("Partie Avanc\u00E9e");
		btnPartieAvance.setHorizontalAlignment(SwingConstants.RIGHT);
		window.getContentPane().add(btnPartieAvance);
		btnPartieAvance.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AdvancedGame g = new AdvancedGame(numberAIplayers, aIDifficulties, aiNames, numberHumanPlayers, humanNames);
				AdvancedGame.main(null, g);
				
			}
			
		});
		window.setPreferredSize(new Dimension(400,100));
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
	}
	
	
	
	
}
