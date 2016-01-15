package lo02.utt.giboulot.canat.message;


/**
 * L'énumération des types d'actions pouvant être demandées par un MenhirMessage.
 * 
 * @see lo02.utt.giboulot.canat.message.MenhirMessage
 */
public enum TypeOfAction {
	
	/** Indique qu'une carte a été jouée*/
	PLAY, 
 /** Indique qu'une carte a été piochée */
 DRAW, 
 /** Indique que l'on souhaite vider la vue de tous ses contenus graphiques */
 CLEAR
}
