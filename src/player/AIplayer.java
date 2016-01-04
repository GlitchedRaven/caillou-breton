package player;

import game.Game;

public class AIplayer extends Player {
	/**
	 * 
	 */
	private static final long serialVersionUID = -816761003543910556L;

	public static final int AI_TEMPO = 300;
	
	private Strategy strategy;
	private Thread t;

	public AIplayer(String name, Game game, Strategy strategy) {
		super(name, game);
		this.strategy = strategy;
		
		
		
	}
	
	public void playACard(){
		String s = this.strategy.playACard(this, this.getCurrentGame());
		this.setChanged();
		this.notifyObservers(s);
		
	}

			



	
	/*public void run() {
		while(!this.hand.isEmpty()) {
			this.playACard();
			try{
				this.wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
				
			}
			
		}
		
	}
	*/
}
