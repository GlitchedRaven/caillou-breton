package view;



import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import card.*;


// TODO: Auto-generated Javadoc
/**
 * La classe CardView est la vue graphique d'une carte à jouer.
 */
public class CardView extends JButton implements Observer {

	private static final long serialVersionUID = 1846064573808499312L;
	
	/** La carte représentée par la vue. */
	private Card card;
	
	/**
	 * Instancie une nouvelle vue pour une carte.
	 *
	 * @param card la carte à représenter
	 */
	public CardView(Card card) {
		super();
		this.card = card;
		this.card.addObserver(this);

		super.setText("<html>" + card.toString().replaceAll("\\n", "<br>") + "</html>");
	}
		
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}
	
	
	/**
	 * Getter de la carte de la vue.
	 *
	 * @return la carte
	 */
	public Card getCard() {
		return card;
	}

}
