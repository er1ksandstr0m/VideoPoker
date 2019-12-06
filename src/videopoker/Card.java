package videopoker;


@SuppressWarnings("rawtypes")
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
	
	@Override
	public String toString() {
		if (value == 1) {
			return "" + "A" + " " + suit.symbol;
		}
		else if (value == 11) {
			return "" + "J" + " " + suit.symbol;
		}
		else if (value == 12) {
			return "" + "Q" + " " + suit.symbol;
		}
		else if (value == 13) {
			return "" + "K" + " " + suit.symbol;
		}
		else 
		{ 
		return "" + value + " " + suit.symbol;
		}
	}
}
