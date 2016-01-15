package lo02.utt.giboulot.canat.player;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import lo02.utt.giboulot.canat.card.*;
import lo02.utt.giboulot.canat.game.*;
import lo02.utt.giboulot.canat.message.*;

/**
 * La classe Player repr�sente le squelette minimum d'un joueur.
 */
public abstract class Player extends Observable implements Serializable{
	
	private static final long serialVersionUID = -3445424604179030302L;
	
	/** Le nom du joueur. */
	protected String name;
	
	/** Le nombre de graines que poss�de le joueur. */
	protected int nbRocks;
	
	/** Le nombre de menhirs que le joueur a fait pouss�. */
	protected int nbMenhirs;
	
	/** La protection continue offerte par un Chien de Garde. */
	protected int[] watchDogProtection =  {0,0,0,0};
	
	/** La main du joueur repr�sent�e sous une de liste. */
	protected ArrayList<Card> hand;
	
	/** La partie dont fait partie le joueur. */
	protected Game currentGame;
	
	/**
	 * M�thode priv�e pour calculer le nombre de graine a voler dans le cas d'un farfadet.
	 * Elle met ce nombre � 0 si le joueur n'a plus de graines.
	 * Si le joueur a moins de graines que le nombre � voler, le nombre � voler est corrig�.
	 * Le nombre a vol� est retranch� de la potentielle protection d'un chien de garde. 
	 * Pr�sente pour faciliter la lecture du code.
	 *
	 * @param toSteal le nombre de graines � voler a priori
	 * @param protection la protection offerte par un Chien de garde � la victime du vol
	 * @return le v�ritable nombre de caillou � voler
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
	 * M�thode correspondant � l'action Engrais.
	 * La force de l'action est calcul�e � partir de la saison courante et du vecteur engrais de la caret jou�e.
	 * Le nombre de graines est retranch� d'autant que la saison ( mis � 0 si n�gatif).
	 * Le nombre de menhir est augment� du nombre de graines retir�es.
	 *
	 * @param card la carte jou�e
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
	 * M�thode correspondant � l'action G�ant.
	 * Le nombre de graines d'un joueur est augment� d'un certain nombre.
	 * Ce nombre est calcul� � partir de la saison courante et du vecteur G�ant de la carte jou�e.
	 *
	 * @param card la carte jou�e
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
	 * M�thode correspondant � l'action Farfadet.
	 * Un certain nombre de graines est vol� � une victime.
	 * Ce nombre est calcul� � partir de la m�thode stealRocks(), de la saison courante
	 * et du vecteur Farfadet de la carte jou�e.
	 *
	 * @param card la carte jou�e
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
	 * M�thode correspondant � l'action Taupe G�ante.
	 * Un certain nombre de menhir est diminu� pour une victime choisie.
	 * Ce nombre est calcul� � partir de la saison courante et du vecteur force de l'alli� jou�.
	 *
	 * @param card la carte alli� jou�e
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
	 * M�thode correspondant � l'action Chien de Garde.
	 * Modifie le vecteur watchdogProtection d'un joueur pour le rendre �gal
	 * � celui de la carte alli� jou�e.
	 *
	 * @param card la carte alli� jou�e
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
	 * Getter du nombre de cailloux poss�d�s par le joueur
	 *
	 * @return le nombre de cailloux
	 */
	public int getNbRocks() {
		return nbRocks;
	}
	
	/**
	 * Setter du nombre de cailloux poss�d�s par le joueur
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
	 * Getter du nombre de menhirs poss�d�s par le joueur.
	 *
	 * @return le nombre de menhirs
	 */
	public int getNbMenhirs() {
		return nbMenhirs;
	}
	
	/**
	 * Setter du nombre de menhirs poss�d�s par le joueur
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
	 * Setter de la main du joueur, ajoute une carte � cette main
	 *
	 * @param card la carte a ajouter � la main
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
