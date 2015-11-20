package card;

//import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class Deck extends LinkedList<Card> {
	private static final long serialVersionUID = 999L;
	
	public final static int INGREDIENT = 0;
	public final static int ALLY = 1;
	public final static String[] TYPEOFDECK = {"Ingredient", "Ally"};
	
	//Matrice des cartes Ingrédients
	public final static int NUMBER_INGREDIENTS = 9;
	public final static int[][] RAYON_DE_LUNE1 = {{1,1,1,1}, {2,0,1,1}, {2,0,2,0}};
	public final static int[][] RAYON_DE_LUNE2 = {{2,0,1,1}, {1,3,0,0}, {0,1,2,1}};
	public final static int[][] RAYON_DE_LUNE3 = {{0,0,4,0}, {0,2,2,0}, {0,0,1,3}};
	
	public final static int[][] CHANT_DE_SIRENE1 = {{1,3,1,0}, {1,2,1,1}, {0,1,4,0}};
	public final static int[][] CHANT_DE_SIRENE2 = {{2,1,1,1}, {1,0,2,2}, {3,0,0,2}};
	public final static int[][] CHANT_DE_SIRENE3 = {{1,2,2,0}, {1,1,2,1}, {2,0,1,2}};
	
	public final static int[][] LARME_DE_DRYADE1 = {{2,1,1,2}, {1,1,1,3}, {2,0,2,2}};
	public final static int[][] LARME_DE_DRYADE2 = {{0,3,0,3}, {2,1,3,0}, {1,1,3,1}};
	public final static int[][] LARME_DE_DRYADE3 = {{1,2,1,2}, {1,0,1,4}, {2,4,0,0}};
	
	public final static int[][][] INGREDIENT_CARDS = {RAYON_DE_LUNE1, RAYON_DE_LUNE2, RAYON_DE_LUNE3, 
														CHANT_DE_SIRENE1, CHANT_DE_SIRENE2, CHANT_DE_SIRENE3,
														LARME_DE_DRYADE1, LARME_DE_DRYADE2, LARME_DE_DRYADE3};
	public final static String[] INGREDIENT_NAMES = {"Rayon De Lune", "Chant de Sirène", "Larme de Dryade", 
														"Fontaine d'eau pure", "Poudre d'Or"};
	//public final static ArrayList<int[][]> ListeCarte = new ArrayList<int[][]>(RAYON_DE_LUNE1, RAYON_DE_LUNE2]);
	//Fin matrice des cartes ingrédients
	
	//Matrice des cartes Alliés
	public final static int NUMBER_ALLY = 2;
	public final static int[] TAUPE_GEANTE1 = {1,1,1,1};
	public final static int[] TAUPE_GEANTE2 = {0,2,2,0};
	public final static int[] TAUPE_GEANTE3 = {0,1,2,1};
	
	public final static int[] CHIEN_DE_GARDE1 = {2,0,2,0};
	public final static int[] CHIEN_DE_GARDE2 = {1,2,0,1};
	public final static int[] CHIEN_DE_GARDE3 = {0,1,3,0};
	
	public final static int[][] ALLY_CARDS = {TAUPE_GEANTE1, TAUPE_GEANTE1, TAUPE_GEANTE1,
												CHIEN_DE_GARDE1, CHIEN_DE_GARDE1, CHIEN_DE_GARDE1};
	public final static String[] ALLY_NAMES = {"Taupe Géante", "Chien de Garde"};
	
	//Fin Matrice des cartes Alliés

	
	private int typeOfDeck;

	
	public void shuffle() {
		Collections.shuffle(this);
	
}

	
	public Deck(int typeOfDeck) {
		super();
		this.typeOfDeck = typeOfDeck;
		switch(this.typeOfDeck) {
		
		case ALLY:
			for(int i = 0; i < NUMBER_ALLY ; i++) {
				this.push(new Ally(ALLY_NAMES[i/3], ALLY_CARDS[i]));
			}
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
