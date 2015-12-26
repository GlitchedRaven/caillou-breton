package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import card.*;
import player.Player;

public class CardView extends JButton implements Observer {

	private Card card;
	
	
	
	public CardView(Card card) {
		super();
		this.card = card;
		this.card.addObserver(this);
		
		super.setText(card.toString());
		
	}
		
		



	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}





	public Card getCard() {
		return card;
	}
	
	

}
