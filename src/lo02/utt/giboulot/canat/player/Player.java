package lo02.utt.giboulot.canat.player;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import lo02.utt.giboulot.canat.card.*;
import lo02.utt.giboulot.canat.game.*;
import lo02.utt.giboulot.canat.message.*;

/**
 * La classe Player représente le squelette minimum d'un joueur.
 */
public abstract class Player extends Observable implements Serializable{
	
	private static final long serialVersionUID = -3445424604179030302L;
	
	/** Le nom du joueur. */
	protected String name;
	
	/** Le nombre de graines que possède le joueur. */
	protected int nbRocks;
	
	/** Le nombre de menhirs que le joueur a fait poussé. */
	protected int nbMenhirs;
	
	/** La protection continue offerte par un Chien de Garde. */
	protected int[] watchDogProtection =  {0,0,0,0};
	
	/** La main du joueur représentée sous une de liste. */
	protected ArrayList<Card> hand;
	
	/** La partie dont fait partie le joueur. */
	protected Game currentGame;
	
	/**
	 * Méthode privée pour calculer le nombre de graine a voler dans le cas d'un farfadet.
	 * Elle met ce nombre à 0 si le joueur n'a plus de graines.
	 * Si le joueur a moins de graines que le nombre à voler, le nombre à voler est corrigé.
	 * Le nombre a volé est retranché de la potentielle protection d'un chien de garde. 
	 * Présente pour faciliter la lecture du code.
	 *
	 * @param toSteal le nombre de graines à voler a priori
	 * @param protection la protection offerte par un Chien de garde à la victime du vol
	 * @return le véritable nombre de caillou à voler
	 */
	private int stealRocks(int toSteal, int protection) {
		toSteal -= protection;
		if(toSteal < 0)
			toSteal = 0;
		if( toSteal <= this.nbRocks) {
			this.nbRocks -= toSteal;
			this.setChanged();
			this.notifyObservers();
		return toSteal; 
		}
		else {
			int tmp = this.nbRocks;
			this.nbRocks = 0;
			this.setChanged();
			this.notifyObservers();
			return (-tmp + toSteal);
		}
		
	}
	
	/**
	 * Méthode correspondant à l'action Engrais.
	 * La force de l'action est calculée à partir de la saison courante et du vecteur engrais de la caret jouée.
	 * Le nombre de graines est retranché d'autant que la saison ( mis à 0 si négatif).
	 * Le nombre de menhir est augmenté du nombre de graines retirées.
	 *
	 * @param card la carte jouée
	 * @return une String expliquant l'action ayant eu lieue
	 */
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
	
	/**
	 * Méthode correspondant à l'action Géant.
	 * Le nombre de graines d'un joueur est augmenté d'un certain nombre.
	 * Ce nombre est calculé à partir de la saison courante et du vecteur Géant de la carte jouée.
	 *
	 * @param card la carte jouée
	 * @return une String expliquant l'action ayant eu lieue
	 */
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

	/**
	 * Méthode correspondant à l'action Farfadet.
	 * Un certain nombre de graines est volé à une victime.
	 * Ce nombre est calculé à partir de la méthode stealRocks(), de la saison courante
	 * et du vecteur Farfadet de la carte jouée.
	 *
	 * @param card la carte jouée
	 * @param victim la victime 
	 * @return une String expliquant l'action ayant eu lieue
	 */
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

	/**
	 * Méthode correspondant à l'action Taupe Géante.
	 * Un certain nombre de menhir est diminué pour une victime choisie.
	 * Ce nombre est calculé à partir de la saison courante et du vecteur force de l'allié joué.
	 *
	 * @param card la carte allié jouée
	 * @param victim la victime
	 * @return une String expliquant l'action ayant eu lieue
	 */
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
	
	
	/**
	 * Méthode correspondant à l'action Chien de Garde.
	 * Modifie le vecteur watchdogProtection d'un joueur pour le rendre égal
	 * à celui de la carte allié jouée.
	 *
	 * @param card la carte allié jouée
	 * @return une String expliquant l'action ayant eu lieue
	 */
	public String playWatchDog(Ally card) {
		int[] allyStrength = card.getStrengthVector();
		this.setWatchDogProtection(allyStrength);
		this.hand.remove(card);
		this.setChanged();
		this.notifyObservers(new MenhirMessage(card, TypeOfAction.PLAY));
		
		return this.getName() + " a joue " + card.getName();
	}
	

	/**
	 * Instancie un nouveau joueur.
	 *
	 * @param name le nom du joueur
	 * @param game la partie dont fera partie le joueur
	 */
	public Player(String name, Game game) {
		super();
		this.name = name;
		this.nbRocks = 2;
		this.nbMenhirs = 0;
		this.hand = new ArrayList<Card>();
		this.currentGame = game;
	}


	/**
	 * Getter du nombre de cailloux possédés par le joueur
	 *
	 * @return le nombre de cailloux
	 */
	public int getNbRocks() {
		return nbRocks;
	}
	
	/**
	 * Setter du nombre de cailloux possédés par le joueur
	 *
	 * @param nbRocks le nouveau nombre de cailloux
	 */
	public void setNbRocks(int nbRocks) {
		if(nbRocks > 0)
			this.nbRocks = nbRocks;
		else
			this.nbRocks = 0;
		
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Getter du nombre de menhirs possédés par le joueur.
	 *
	 * @return le nombre de menhirs
	 */
	public int getNbMenhirs() {
		return nbMenhirs;
	}
	
	/**
	 * Setter du nombre de menhirs possédés par le joueur
	 *
	 * @param nbMenhirs le nouveau nombre de menhirs
	 */
	public void setNbMenhirs(int nbMenhirs) {
		if(nbMenhirs > 0)
			this.nbMenhirs = nbMenhirs;
		else
			this.nbMenhirs = 0;
		
		this.setChanged();
		this.notifyObservers();

	}
	
	/**
	 * Getter de la protection du chien de garde.
	 *
	 * @return la protection du chien de garde
	 */
	public int[] getWatchDogProtection() {
		return watchDogProtection;
	}
	
	/**
	 * Setter de la protection du chien de garde.
	 *
	 * @param watchDogProtection la nouvelle protection chien de garde
	 */
	public void setWatchDogProtection(int[] watchDogProtection) {
		this.watchDogProtection = watchDogProtection;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Vide la main.
	 */
	public void clearHand() {
		this.hand.clear();
		this.setChanged();
		this.notifyObservers(new MenhirMessage(null, TypeOfAction.CLEAR));
	}
		
	/**
	 * Getter du nom du joueur.
	 *
	 * @return le nom
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter de la main du joueur.
	 *
	 * @return la main du joueur
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}

	/**
	 * Setter de la main du joueur, ajoute une carte à cette main
	 *
	 * @param card la carte a ajouter à la main
	 */
	public void setHand(Card card) {
		this.hand.add(card);
		this.setChanged();
		this.notifyObservers(new MenhirMessage(card, TypeOfAction.DRAW));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Graine(s)=" + nbRocks + "\n"+ "Menhir(s)=" + nbMenhirs + "\n" + "Chien de Garde = " 
					 + Arrays.toString(watchDogProtection) + "\n\n" + " Main=" + hand.toString() +"\n";
	}
	
	/**
	 * Getter de la partie courante.
	 *
	 * @return la partie courante
	 */
	public Game getCurrentGame() {
		return currentGame;
	}
	
	
}
