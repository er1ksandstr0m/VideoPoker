package videopoker;

public enum Suit {
	HEARTS("H"),
	SPADES("S"),
	DIAMONDS("R"),
	CLUBS("K");

	private Suit(String symbol) {
		this.symbol=symbol;
	}
	public String symbol;
}
