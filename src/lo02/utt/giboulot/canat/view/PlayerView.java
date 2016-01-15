package lo02.utt.giboulot.canat.view;

import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;

import lo02.utt.giboulot.canat.card.Card;
import lo02.utt.giboulot.canat.message.*;
import lo02.utt.giboulot.canat.player.*;

/**
 * La classe PlayerView est la vue graphique d'un joueur.
 */
public class PlayerView implements Observer {
	
	/** Le joueur représenté par la vue. */
	private Player player;
	
	/** Les vues associées à chaques cartes que le joueur a en sa possession. */
	private HashMap<Card, CardView> cardViews;
	
	/** Le label contenant les informaions principales du joueur (nom, graines, menhirs, etc...) */
	private  JLabel label;
	
	/** Le panneau contenant la vue complète d'un joueur. */
	private JPanel pan;
	
	
	
	/**
	 * Intancie une nouvelle vue pour un joueur.
	 *
	 * @param player le joueur à représenter.
	 */
	public PlayerView(Player player) {
		super();
		this.player = player;
		this.player.addObserver(this);
		this.cardViews = new HashMap<Card, CardView>();
		
		if(player.getWatchDogProtection() != null) {
			this.label = new JLabel(player.getName() + "\n" + "=> Menhir(s) : " + player.getNbMenhirs()
								+ "\n" + " Graine(s) : " + player.getNbRocks()
								+ "\n" + " Protection : " + Arrays.toString(player.getWatchDogProtection()));
		}
		
		else {
			this.label = new JLabel(player.getName() + "\n" + "=> Menhir(s) : " + player.getNbMenhirs()
			+ "\n" + " Graine(s) : " + player.getNbRocks());
		}
		this.pan = new JPanel(new GridLayout(0, 1, 0, 0));
		this.pan.add(new JLabel(player.getName()));
		
		if(player instanceof HumanPlayer) {
			for(ListIterator<Card> c = player.getHand().listIterator();c.hasNext();) {
				Card card = c.next();
				CardView cv = new CardView(card);
				cardViews.put(card, cv);
				this.pan.add(cv);

			}
		}
	}


	/**
	 * Getter du label.
	 *
	 * @return le label
	 */
	public JLabel getLabel() {
		return label;
	}


	/**
	 * Getter du panneau d'information.
	 *
	 * @return le panneau
	 */
	public JPanel getPan() {
		return pan;
	}


	/**
	 * Getter de la liste de vue de carte.
	 *
	 * @return la liste de vue de carte
	 */
	public HashMap<Card, CardView> getCardViews() {
		return cardViews;
	}


	/**
	 * Getter du joueur représenté.
	 *
	 * @return le joueur
	 */
	public Player getPlayer() {
		return player;
	}


	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof MenhirMessage){
			Card card = ((MenhirMessage) arg).getCard();
			TypeOfAction type = ((MenhirMessage) arg).getType();
			
			if(card != null && cardViews.containsKey(card) && type == TypeOfAction.PLAY) {
				this.pan.remove(cardViews.get(card));
				cardViews.remove(card);
			
			}
			
			if(card != null && type == TypeOfAction.DRAW) {
				CardView cv = new CardView(card);
				cardViews.put(card, cv);
				this.pan.add(cv);
				
			}
			
			if(type == TypeOfAction.CLEAR) {
				for(Iterator<CardView> it = cardViews.values().iterator(); it.hasNext(); )
					this.pan.remove(it.next());
				cardViews.clear();
				
			}
		}
		
		
		if(arg instanceof String) 
			JOptionPane.showMessageDialog(this.pan, arg);
		
		
		this.label.setText(player.getName() + "\n" + "=> Menhir(s) : " + player.getNbMenhirs()
								+ "\n" + " Graine(s) : " + player.getNbRocks()
								+ "\n" + " Protection : " + Arrays.toString(player.getWatchDogProtection()));
		
		
		
		pan.revalidate();
		pan.repaint();

	}
	

}
