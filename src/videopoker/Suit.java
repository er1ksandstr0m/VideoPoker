package videopoker;

public enum Suit {
	HEARTS("♥"),
	SPADES("♠"),
	DIAMONDS("♦"),
	CLUBS("♣");
	
	private Suit(String symbol) {
		this.symbol=symbol;
	}
	public String symbol;
}

