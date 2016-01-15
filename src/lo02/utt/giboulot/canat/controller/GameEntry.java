package lo02.utt.giboulot.canat.controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.SwingConstants;

import lo02.utt.giboulot.canat.game.AdvancedGame;
import lo02.utt.giboulot.canat.game.Game;
import lo02.utt.giboulot.canat.game.QuickGame;
import lo02.utt.giboulot.canat.view.GameCreatorView;
/**
 * Classe contenant la m�thode main, elle sert de point d'entr�e au jeu, en pr�sentant l'option de cr�er ou de charger une partie.
 * 
 *
 */
public class GameEntry {	
	/**
	 * M�thode main qui va permettre le chargement d'une partie sauvegard�e ou la cr�ation d'une nouvelle partie dans une fen�tre.
	 * La partie sauvegard�e doit �tre s�rialis�e dans le fichier sauvegarde.txt � la racine du projet.
	 * Pour cr�er une partie, on fait appel  � la m�thode GameCreatorView().
	 * @see lo02.utt.giboulot.canat.view.GameCreatorView
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("choix de partie");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		window.setPreferredSize(new Dimension(500,400));
		window.pack();
		
		
		JButton btnLoadGame = new JButton("Charger une partie");
		btnLoadGame.setHorizontalAlignment(SwingConstants.LEFT);
		window.getContentPane().add(btnLoadGame);
		btnLoadGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ObjectInputStream ois = null;
				try {
					FileInputStream test = new FileInputStream("sauvegarde.txt");
					ois = new ObjectInputStream(test);
					Game g = (Game) ois.readObject();		
					if (g instanceof QuickGame){
						QuickGameController gc = new QuickGameController((QuickGame) g);
						gc.getGv().update(g, g.getCurrentPlayer());
					} else {
						AdvancedGameController gc = new AdvancedGameController((AdvancedGame) g);
						gc.getGv().update(g, g.getCurrentPlayer());
					}
				} catch (IOException | ClassNotFoundException e1) {
					e1.printStackTrace();
				} finally {
					try {
						ois.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		});
		
		JButton btnCreateGame = new JButton("Nouvelle Partie");
		btnCreateGame.setHorizontalAlignment(SwingConstants.RIGHT);
		window.getContentPane().add(btnCreateGame);
		btnCreateGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new GameCreatorView();
			}
			
		});
		window.setPreferredSize(new Dimension(400,100));
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}
}
