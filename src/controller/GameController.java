package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import game.Game;
import view.*;

public abstract class GameController  {
	protected GameView gv;
	protected Game game;
	
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

	public GameView getGv() {
		return gv;
	}
	
	public abstract void changeSeason();
	public abstract void changePlayer();
	public abstract boolean testAIPlay();
	
}
