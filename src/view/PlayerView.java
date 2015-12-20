package view;

import java.awt.GridLayout;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import card.Card;
import player.*;

public class PlayerView implements Observer {
	private Player player;
	private  JLabel label;
	private JPanel pan;
	
	
	
	public PlayerView(Player player) {
		super();
		this.player = player;
		this.label = new JLabel(player.getName());
		this.pan = new JPanel(new GridLayout(0, 1, 0, 0));
		
		for(ListIterator<Card> c = player.getHand().listIterator();c.hasNext();) {
			Card card = c.next();
			this.pan.add(new CardView(card));
		}
	}


	public JLabel getLabel() {
		return label;
	}


	public JPanel getPan() {
		return pan;
	}


	@Override
	public void update(Observable o, Object arg) {
		pan.revalidate();
		pan.repaint();

	}

}
