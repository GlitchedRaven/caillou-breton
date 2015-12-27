package player;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import game.*;
import card.*;
import message.*;

public abstract class Player extends Observable {
	
	protected String name;
	protected int nbRocks;
	protected int nbMenhirs;
	protected int[] watchDogProtection = {0, 0, 0, 0};
	protected ArrayList<Card> hand;
	protected Game currentGame;
	
	private int stealRocks(int toSteal, int protection) {
		toSteal -= protection;
		if(toSteal < 0)
			toSteal = 0;
		if( toSteal <= this.nbRocks) {
			this.nbRocks -= toSteal;
		return toSteal; 
		}
		else {
			int tmp = this.nbRocks;
			this.nbRocks = 0;
			return (-tmp + toSteal);
		}
	}
	public String playFertilizer(Ingredient card) {
		int season = currentGame.getSeason();
		int[] fertilizerStrength = card.getFertilizerVector();
		
		if( fertilizerStrength[season] > this.nbRocks) {
			this.nbMenhirs = this.nbMenhirs + this.nbRocks;
			this.nbRocks = 0;
		}
		else {
			this.nbMenhirs = this.nbMenhirs + fertilizerStrength[season];
			this.nbRocks -= fertilizerStrength[season];
		}
		this.hand.remove(card);
		this.setChanged();
		this.notifyObservers(new MenhirMessage(card, TypeOfAction.PLAY));
		
		return this.getName() + " a joue " + card.getName() 
		+ " avec le pouvoir Engrais pour une force de " + fertilizerStrength[season]; 
	}
	
	public String playGiant(Ingredient card) {
		int season = currentGame.getSeason();
		int[] giantStrength = card.getGiantVector();
		
		this.nbRocks += giantStrength[season];
		this.hand.remove(card);
		
		this.setChanged();
		this.notifyObservers(new MenhirMessage(card, TypeOfAction.PLAY));
		
		return this.getName() + " a joue " + card.getName() 
		+ " avec le pouvoir Geant pour une force de " + giantStrength[season]; 
		
	}

	public String playFarfadet(Ingredient card, Player victim) {
		int season = currentGame.getSeason();
		int[] farfadetStrength = card.getFarfadetVector();
		
		int rockStolen = victim.stealRocks(farfadetStrength[season], victim.getWatchDogProtection()[season]);
		
		if(rockStolen > 0) 
			this.nbRocks += rockStolen;
		
		
		this.hand.remove(card);
		this.setChanged();
		this.notifyObservers(new MenhirMessage(card, TypeOfAction.PLAY));
		
		return this.getName() + " a joue " + card.getName() 
		+ " avec le pouvoir Geant pour une force de " + farfadetStrength[season]; 
		
	
	}

	public String playGiantMole(Ally card, Player victim) {
		int season = currentGame.getSeason();
		int[] allyStrength = card.getStrengthVector();
		victim.setNbMenhirs(victim.getNbMenhirs() -allyStrength[season]);
		this.hand.remove(card);
		this.setChanged();
		this.notifyObservers(new MenhirMessage(card, TypeOfAction.PLAY));
		
	    return this.getName() + " a joue " + card.getName() 
				+ "  pour une force de " + allyStrength[season];
		
	}
	
	
	public String playWatchDog(Ally card) {
		int[] allyStrength = card.getStrengthVector();
		this.setWatchDogProtection(allyStrength);
		this.hand.remove(card);
		this.setChanged();
		this.notifyObservers(new MenhirMessage(card, TypeOfAction.PLAY));
		
		return this.getName() + " a joue " + card.getName();
	}
	

	public Player(String name, Game game) {
		super();
		this.name = name;
		this.nbRocks = 2;
		this.nbMenhirs = 0;
		this.hand = new ArrayList<Card>();
		this.currentGame = game;
	}


	public int getNbRocks() {
		return nbRocks;
	}
	public void setNbRocks(int nbRocks) {
		if(nbRocks > 0)
			this.nbRocks = nbRocks;
		else
			this.nbRocks = 0;
		
		this.setChanged();
		this.notifyObservers();
	}

	public int getNbMenhirs() {
		return nbMenhirs;
	}
	public void setNbMenhirs(int nbMenhirs) {
		if(nbMenhirs > 0)
			this.nbMenhirs = nbMenhirs;
		else
			this.nbMenhirs = 0;
		
		this.setChanged();
		this.notifyObservers();

	}
	public int[] getWatchDogProtection() {
		return watchDogProtection;
	}
	public void setWatchDogProtection(int[] watchDogProtection) {
		this.watchDogProtection = watchDogProtection;
		this.setChanged();
		this.notifyObservers();
	}
	public void clearHand() {
		this.hand.clear();
		this.setChanged();
		this.notifyObservers(new MenhirMessage(null, TypeOfAction.CLEAR));
	}
		
	public String getName() {
		return name;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(Card card) {
		this.hand.add(card);
		this.setChanged();
		this.notifyObservers(new MenhirMessage(card, TypeOfAction.DRAW));
	}

	@Override
	public String toString() {
		return "Graine(s)=" + nbRocks + "\n"+ "Menhir(s)=" + nbMenhirs + "\n" + "Chien de Garde = " 
					 + Arrays.toString(watchDogProtection) + "\n\n" + " Main=" + hand.toString() +"\n";
	}
	public Game getCurrentGame() {
		return currentGame;
	}
	
	
}
