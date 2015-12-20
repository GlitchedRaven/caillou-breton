package controller;
import card.Ingredient;
import game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import card.*;
import view.*;
import player.*;

public class GameController {
	private GraphicalView gv;
	private Game game;
	
	
	public GameController(Game game) {
		super();
		this.gv = new GraphicalView(game);
		this.game = game;
	}

	public String choiceIngredientAction(Player player, Ingredient card) {
		JFrame choiceWindow = new JFrame("Choisissez votre action");
		choiceWindow.getContentPane().setLayout(new BoxLayout(choiceWindow.getContentPane(), BoxLayout.X_AXIS));
		
			JButton b1 = new JButton(Ingredient.INGREDIENT_ACTION[0]);
			b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) { player.playGiant(card);}
			});
			
			JButton b2 = new JButton(Ingredient.INGREDIENT_ACTION[1]);
			b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) { player.playFertilizer(card);}
			});
			
			JButton b3 = new JButton(Ingredient.INGREDIENT_ACTION[2]);
			b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) { 
				player.playFarfadet(card, gv.choiceVictim());}
			});
			choiceWindow.add(b1);
			choiceWindow.add(b2);
			choiceWindow.add(b3);
		
		
		
		return null;
	}

	public void changeSeason() {
		game.setSeason();
	}
}
