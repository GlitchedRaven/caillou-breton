package view;

import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import card.Card;
import player.*;

public class PlayerView implements Observer {
	private Player player;
	private HashMap<Card, CardView> cardViews;
	
	private  JLabel label;
	private JPanel pan;
	
	
	
	public PlayerView(Player player) {
		super();
		this.player = player;
		this.player.addObserver(this);
		this.cardViews = new HashMap<Card, CardView>();
		this.label = new JLabel(player.getName() + "\n" + " Menhir(s) : " + player.getNbMenhirs()
									+ "\n" + " Graine(s) : " + player.getNbRocks());
		
		this.pan = new JPanel(new GridLayout(0, 1, 0, 0));
		
		for(ListIterator<Card> c = player.getHand().listIterator();c.hasNext();) {
			Card card = c.next();
			CardView cv = new CardView(card);
			cardViews.put(card, cv);
			this.pan.add(cv);
			
		}
	}


	public JLabel getLabel() {
		return label;
	}


	public JPanel getPan() {
		return pan;
	}


	public HashMap<Card, CardView> getCardViews() {
		return cardViews;
	}


	public Player getPlayer() {
		return player;
	}


	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Card && cardViews.containsKey(arg)) {
			this.pan.remove(cardViews.get(arg));
			cardViews.remove(arg);
		}
		
		this.label.setText(player.getName() + "\n" + " Menhir(s) : " + player.getNbMenhirs()
								+ "\n" + " Graine(s) : " + player.getNbRocks());
		pan.revalidate();
		pan.repaint();

	}
	
	public void update() {
		this.label.setText(player.getName() + "\n" + " Menhir(s) : " + player.getNbMenhirs()
								+ "\n" + " Graine(s) : " + player.getNbRocks());
		pan.revalidate();
		pan.repaint();

	}

}
