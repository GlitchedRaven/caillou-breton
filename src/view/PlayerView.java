package view;

import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import card.Card;
import message.*;

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
		
		if(player instanceof HumanPlayer) {
			for(ListIterator<Card> c = player.getHand().listIterator();c.hasNext();) {
				Card card = c.next();
				CardView cv = new CardView(card);
				cardViews.put(card, cv);
				this.pan.add(cv);

			}
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
		
		
		if(arg instanceof String) {
			JOptionPane.showMessageDialog(this.pan, arg);
		}
		
		this.label.setText(player.getName() + "\n" + " Menhir(s) : " + player.getNbMenhirs()
								+ "\n" + " Graine(s) : " + player.getNbRocks()
								+ "\n" + "Protection : " + Arrays.toString(player.getWatchDogProtection()));
		pan.revalidate();
		pan.repaint();

	}
	

}
