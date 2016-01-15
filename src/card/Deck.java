package card;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

import player.Player;

/**
 * La classe Deck représente un paquet de carte et contient toute les méthodes associées à la distribution et au mélange des cartes.
 * Elle contient également la définition de toutes les cartes jouables du jeu.
 * 
 */
public class Deck extends LinkedList<Card> {
	
	
	private static final long serialVersionUID = 999L;
	
	public final static int INGREDIENT = 0;
	
	public final static int ALLY = 1;
	
	/** Définit les types de decks acceptés */
	public final static String[] TYPEOFDECK = {"Ingredient", "Ally"};
	
	//Matrice des cartes Ingrédients
	
	public final static int[][] RAYON_DE_LUNE1 = {{1,1,1,1}, {2,0,1,1}, {2,0,2,0}};
	public final static int[][] RAYON_DE_LUNE2 = {{2,0,1,1}, {1,3,0,0}, {0,1,2,1}};
	public final static int[][] RAYON_DE_LUNE3 = {{0,0,4,0}, {0,2,2,0}, {0,0,1,3}};
	
	public final static int[][] CHANT_DE_SIRENE1 = {{1,3,1,0}, {1,2,1,1}, {0,1,4,0}};
	public final static int[][] CHANT_DE_SIRENE2 = {{2,1,1,1}, {1,0,2,2}, {3,0,0,2}};
	public final static int[][] CHANT_DE_SIRENE3 = {{1,2,2,0}, {1,1,2,1}, {2,0,1,2}};
	
	public final static int[][] LARME_DE_DRYADE1 = {{2,1,1,2}, {1,1,1,3}, {2,0,2,2}};
	public final static int[][] LARME_DE_DRYADE2 = {{0,3,0,3}, {2,1,3,0}, {1,1,3,1}};
	public final static int[][] LARME_DE_DRYADE3 = {{1,2,1,2}, {1,0,1,4}, {2,4,0,0}};
	
	public final static int[][] FONTAINE_D_EAU_PURE1 = {{1,3,1,2}, {2,1,2,2}, {0,0,3,4}};
	public final static int[][] FONTAINE_D_EAU_PURE2 = {{2,2,0,3}, {1,1,4,1}, {1,2,1,3}};
	public final static int[][] FONTAINE_D_EAU_PURE3 = {{2,2,3,1}, {2,3,0,3}, {1,1,3,3}};
	 			
	public final static int[][] POUDRE_D_OR1 = {{2,2,3,1}, {2,3,0,3}, {1,1,3,3}};
	public final static int[][] POUDRE_D_OR2 = {{2,2,2,2}, {0,4,4,0}, {1,3,2,2}};
	public final static int[][] POUDRE_D_OR3 = {{3,1,3,1}, {1,4,2,1}, {2,4,1,1}};
	 	
	public final static int[][] RACINE_D_ARC_EN_CIEL1 = {{4,1,1,1}, {1,2,1,3}, {1,2,2,2}};
	public final static int[][] RACINE_D_ARC_EN_CIEL2 = {{2,3,2,0}, {0,4,3,0}, {2,1,1,3}};
	public final static int[][] RACINE_D_ARC_EN_CIEL3 = {{2,2,3,0}, {1,1,1,4}, {2,0,3,2}};
	 	
	public final static int[][] ESPRIT_DE_DOLMEN1 = {{4,1,1,1}, {1,2,1,3}, {1,2,2,2}};
	public final static int[][] ESPRIT_DE_DOLMEN2 = {{2,4,1,2}, {2,2,2,3}, {1,4,3,1}};
	public final static int[][] ESPRIT_DE_DOLMEN3 = {{3,3,3,0}, {1,3,3,2}, {2,3,1,3}};
	 
	public final static int[][] RIRE_DE_FEE1 = {{1,2,2,1}, {1,2,3,0}, {0,2,2,2}};
	public final static int[][] RIRE_DE_FEE2 = {{4,0,1,1}, {1,1,3,1}, {0,0,3,3}};
	public final static int[][] RIRE_DE_FEE3 = {{2,0,1,3}, {0,3,0,3}, {1,2,2,1}};
	
	/** Définit la matrice des cartes jouables du jeu */
	public final static int[][][] INGREDIENT_CARDS = {RAYON_DE_LUNE1, RAYON_DE_LUNE2, RAYON_DE_LUNE3, 
														CHANT_DE_SIRENE1, CHANT_DE_SIRENE2, CHANT_DE_SIRENE3,
														LARME_DE_DRYADE1, LARME_DE_DRYADE2, LARME_DE_DRYADE3,
														FONTAINE_D_EAU_PURE1, FONTAINE_D_EAU_PURE2, FONTAINE_D_EAU_PURE3,
														POUDRE_D_OR1, POUDRE_D_OR2, POUDRE_D_OR3, RACINE_D_ARC_EN_CIEL1, 
														RACINE_D_ARC_EN_CIEL2, RACINE_D_ARC_EN_CIEL3, ESPRIT_DE_DOLMEN1,
														ESPRIT_DE_DOLMEN2, ESPRIT_DE_DOLMEN3, RIRE_DE_FEE1, RIRE_DE_FEE2, RIRE_DE_FEE3};
	
	/** Définit le nom de toutes les cartes du jeu */
	public final static String[] INGREDIENT_NAMES = {"Rayon De Lune", "Chant de Sirène", "Larme de Dryade", 
														"Fontaine d'eau pure", "Poudre d'Or", "Racine d'arc-en-ciel", 
														"Esprit de Dolmen", "Rire de fée"};
	
	public final static int NUMBER_INGREDIENTS = INGREDIENT_CARDS.length;
	
	//Fin matrice des cartes ingrédients
	
	//Matrice des cartes Alliés
	
	
	public final static int[] TAUPE_GEANTE1 = {1,1,1,1};
	public final static int[] TAUPE_GEANTE2 = {0,2,2,0};
	public final static int[] TAUPE_GEANTE3 = {0,1,2,1};
	
	public final static int[] CHIEN_DE_GARDE1 = {2,0,2,0};
	public final static int[] CHIEN_DE_GARDE2 = {1,2,0,1};
	public final static int[] CHIEN_DE_GARDE3 = {0,1,3,0};
	
	/** Définit la matrice de toutes les cartes Allié du jeu*/
	public final static int[][] ALLY_CARDS = {TAUPE_GEANTE1, TAUPE_GEANTE2, TAUPE_GEANTE3,
												CHIEN_DE_GARDE1, CHIEN_DE_GARDE2, CHIEN_DE_GARDE3};
	
	/** Définit le nom de tous les alliés du jeu */
	public final static String[] ALLY_NAMES = {"Taupe Géante", "Chien de Garde"};
	
	public final static int NUMBER_ALLY = ALLY_CARDS.length;
	
	//Fin Matrice des cartes Alliés

	
	/** Représente le type de deck  */
	private int typeOfDeck;

	
	/**
	 * Mélange le paquet de carte
	 * Consiste en un simple appel à la méthode shuffle() de la classe Collection
	 */
	public void shuffle() {
		Collections.shuffle(this);
	
}
	
	/**
	 * Distribue un certain nombre de cartes à une liste de joueur
	 *
	 * @param numberToDistribute est le nombre de carte à distribuer à chaque joueur
	 * @param players sont les joueurs à qui distribuer les cartes
	 */
	public void distribute(int numberToDistribute, ArrayList<Player> players ) {
		for(ListIterator<Player> p = players.listIterator(); p.hasNext();) {
			Player player = p.next();
			for(int i = 0; i < numberToDistribute ; i++)
				player.setHand(this.remove()); 
			
			
		}
		
	}

	
	/**
	 * Instancie un nouveau deck.
	 *
	 * @param typeOfDeck est le type de deck
	 */
	public Deck(int typeOfDeck) {
		super();
		this.typeOfDeck = typeOfDeck;
		switch(this.typeOfDeck) {
		
		case ALLY:
			for(int i = 0; i < NUMBER_ALLY ; i++) {
				this.push(new Ally(ALLY_NAMES[i/3], ALLY_CARDS[i], Ally.TYPES[i/3]));
				
			}
			this.shuffle();
			this.shuffle();
			break;
		case INGREDIENT:
			for(int i = 0; i < NUMBER_INGREDIENTS ; i++) {
				this.push(new Ingredient(INGREDIENT_NAMES[i/3], INGREDIENT_CARDS[i][0], 
											INGREDIENT_CARDS[i][1], INGREDIENT_CARDS[i][2]));
			}
			
			this.shuffle();
			this.shuffle();
			
			break;
		default:
			break;
		}
	}
	
	
}
