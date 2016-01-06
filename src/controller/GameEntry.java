package controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import game.AdvancedGame;
import game.Game;
import game.QuickGame;
//import game.AdvancedGame;
//import game.QuickGame;
//import view.Console;
import view.GameCreatorView;
//import view.GameView;

public class GameEntry {	

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
					} else {
						//JOptionPane.showMessageDialog(null, "Vous ne pouvez pas charger une partie avancée");
						AdvancedGameController gc = new AdvancedGameController((AdvancedGame) g);
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
				GameCreatorView gcv = new GameCreatorView();
			}
			
		});
		window.setPreferredSize(new Dimension(400,100));
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}
}
