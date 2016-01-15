package lo02.utt.giboulot.canat.message;


/**
 * L'�num�ration des types d'actions pouvant �tre demand�es par un MenhirMessage.
 * 
 * @see lo02.utt.giboulot.canat.message.MenhirMessage
 */
public enum TypeOfAction {
	
	/** Indique qu'une carte a �t� jou�e*/
	PLAY, 
 /** Indique qu'une carte a �t� pioch�e */
 DRAW, 
 /** Indique que l'on souhaite vider la vue de tous ses contenus graphiques */
 CLEAR
}
