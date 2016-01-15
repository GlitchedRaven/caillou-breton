package lo02.utt.giboulot.canat.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import lo02.utt.giboulot.canat.game.Game;
import lo02.utt.giboulot.canat.view.*;


/**
 * La classe GameController est le minimum n�cessaire pour controler une partie et sa vue.
 * La classe est adapt�e pour une utilisation sur des vues graphiques. Elle s'occupe principalement d'ajouter les 
 * �couteurs � chaque bouton et � mettre � jour le mod�le � chaque fin de tour.
 */
public abstract class GameController  {
	
	/** La vue de de la partie */
	protected GameView gv;
	
	/** Le mod�le de la partie. */
	protected Game game;
	
	/**
	 * Instancie un nouveau controleur avec une vue graphique.
	 *
	 * @param game le mod�le de la partie
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
	 * @return la vue de la partie
	 */
	public GameView getGv() {
		return gv;
	}
	
	/**
	 * Met � jour la saison du mod�le.
	 */
	public abstract void changeSeason();
	
	/**
	 * Met � jour le joueur courant du mod�le
	 */
	public abstract void changePlayer();
	
	/**
	 * Test si le joueur courant est une IA, si oui le controleur la fait jouer.
	 *
	 * @return true, si le joueur courant �tait une IA
	 */
	public abstract boolean testAIPlay();
	
}