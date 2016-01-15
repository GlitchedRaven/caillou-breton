package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import game.Game;
import view.*;

// TODO: Auto-generated Javadoc
/**
 * La classe GameController est le minimum nécessaire pour controller une partie et sa vue.
 * La classe est adaptée pour une utilisation sur des vues graphiques. Elle s'occupe principalement d'ajouter les 
 * écouteurs à chaque boutton et à mettre à jour le modèle à chaque fin de tour.
 */
public abstract class GameController  {
	
	/** La vue de de la partie */
	protected GameView gv;
	
	/** Le modèle de la partie. */
	protected Game game;
	
	/**
	 * Instantie un nouveau controlleur avec une vue graphique.
	 *
	 * @param game le modèle de la partie
	 */
	public GameController(Game game) {
		
		super();
		this.gv = new GameView(game);
		this.game = game;
		gv.getSaveButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				game.setSaved(true);
				String saveFileName = "sauvegarde.txt";
				FileOutputStream saveFile;
				ObjectOutputStream saving = null;
				try {
					saveFile = new FileOutputStream(saveFileName);
					saving = new ObjectOutputStream(saveFile);
					saving.writeObject(game);
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

	/**
	 * Getter de la vue de la partie.
	 *
	 * @return the gv
	 */
	public GameView getGv() {
		return gv;
	}
	
	/**
	 * Met à jour la saison du modèle.
	 */
	public abstract void changeSeason();
	
	/**
	 * Met à jour le joueur courant du modèle
	 */
	public abstract void changePlayer();
	
	/**
	 * Test si le joueur courante st une IA, si oui le controlleur la fait jouer.
	 *
	 * @return true, si le joueur courant était une IA
	 */
	public abstract boolean testAIPlay();
	
}
