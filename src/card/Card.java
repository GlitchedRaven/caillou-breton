package card;

import java.util.Observable;

public abstract class Card extends Observable {
	private String name;
	
	
	
	public Card(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	

}
