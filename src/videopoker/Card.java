package videopoker;


public class Card implements Comparable{

	private int value;
	private Suit suit;

	public Card(int value, Suit suit) {
		this.value = value;
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public Suit getSuit() {
		return suit;
	}

	public String getSymbol() {
	return suit.symbol;
}

	@Override
	public int compareTo(Object obj){
		return this.getValue() - ((Card)obj).getValue();
	}
}
