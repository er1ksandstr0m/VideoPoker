package videopoker;

import java.util.ArrayList;

public class Player {
	private ArrayList<Card> hand;
	private int wallet = 0;
	private int bet = 0;
	private String score = "";

	/**
	 * Skapar spelare med tom hand och plånbok.
	 */
	public Player() {
		hand = new ArrayList<Card>();
		wallet = 0;
	}

	/**
	 * Skapar spelare med gammal plånbok och hand.
	 *
	 * @param oldWallet
	 * @param oldHand
	 */
	public Player(int oldWallet, ArrayList<Card> oldHand) {
		hand = oldHand;
		wallet = oldWallet;
	}

	/**
	 * Lägg till pengar i plånboken
	 *
	 * @param money
	 */
	public void addMoney(int money) {
		this.wallet += money;
	}

	/**
	 * Gör försök att ta ut pengar. Returnerar om uttaget går bra eller ej.
	 *
	 * @param withdrawMoney
	 * @return true om spelaren har råd med uttaget, annars false.
	 */
	public boolean withdrawMoney(int withdrawMoney) {

		if (wallet > withdrawMoney) {
			wallet += -withdrawMoney;
			return true;
		}
		return false;
	}

	public boolean withdrawMoney() {
		if (wallet > bet) {
			wallet += -bet;
			return true;
		}
		return false;
	}
	
	/**
	 * Gör försök att ta ut pengar. Returnerar om uttaget går bra eller ej.
	 *
	 * @param pBet
	 * @return true om spelaren har råd med bet, annars false.
	 */
	public boolean placeBet(int pBet) {
		if (pBet < wallet) {
			wallet += -pBet;
			bet = pBet;
			return true;
		} else {
			System.out.println("För lite cash i plånkan yo!");
			return false;
		}
	}

	//Ökar bet med ett så länge bet är lägre än tre. Annars sätts bet till 1
	public void placeBet(){
		if(bet <3){
			bet++;
		}
		else {
			bet = 1;
		}
	}

	/**
	 * Gör försök att ta ut pengar. Returnerar om uttaget går bra eller ej.
	 *
	 * @return spelarens nuvarande bet
	 */
	public int getBet() {
		return bet;
	}

	/**
	 * Sätter spelarens bet till 0
	 */
	public void clearBet() {
		bet = 0;
	}

	/**
	 * Returnerar plånboken.
	 *
	 * @return wallet
	 */
	public int getWallet() {
		return wallet;
	}

	/**
	 * Lägger till ett kort till handen.
	 *
	 * @param card Det nya kortet
	 */
	public void addCardToHand(Card card) {
		hand.add(card);
	}

	/**
	 * Byter ut ett kort i handen till ett nytt kort.
	 *
	 * @param oldCard Kortet som ska bytas ut
	 * @param newCard Kortet som ska bytas in
	 */
	public void changeCard(Card oldCard, Card newCard) {
		hand.set(hand.indexOf(oldCard), newCard);
	}

	/**
	 * @param card
	 */
	public void removeCardFromHand(Card card) {
		hand.remove(card);
	}

	/**
	 * @return Spelarens hand
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}

	/**
	 * @return Spelarens score
	 */
	public String getScore() {
		return score;
	}

	/**
	 * Tömmer spelarens hand
	 */
	public void reset() {
		hand.clear();
	}

	/**
	 * @return Poäng
	 */
	public int countScore() {
		// här ska vi lägga in bet och multiplicera med rätt faktor
		if (score.contains("Stege") && !score.contains("Par")) {
			if (score.contains("Färg")) {
				System.out.println("STRAIGHT FLUSH!");
				return 50;
			} else {
				System.out.println("Stege!");
				return 4;
			}

		} else if (score.contains("Kungligt") && !score.contains("Par") && score.contains("Färg")) {
			System.out.println("ROYAL FLUSH!");
			return 800;

		} else if (score.contains("ParParPar")) {
			System.out.println("Fyrtal!");
			return 25;

		} else if (score.contains("ParPar") && (score.startsWith("Par")
				&& ((score.endsWith("Par") || (score.endsWith("ParStege") || (score.endsWith("ParKungligt"))))))) {
			System.out.println("Kåk!");
			return 9;

		} else if (score.contains("ParPar")) {
			System.out.println("Triss!");
			return 3;

		} else if ((score.matches("ParNullParNull")) || (score.matches("ParNullNullPar"))
				|| (score.matches("NullParNullPar"))) {
			System.out.println("Två Par!");
			return 2;

		} else if (score.contains("Par")) {
			if (isPairValid()) {
				return 1;
			}

		} else {
			if (score.contains("Färg")) {
				System.out.println("Färg!!!");
				return 6;
			}
		}
		System.out.println("Sorry - du fick nada.");
		return 0;
	}

	public boolean isPairValid() {
		for (int i = 0; i < 4; i++) {
			if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
				if ((hand.get(i).getValue() > 10) || (hand.get(i).getValue() == 1)) {
					System.out.println("Du har ett par som duger!!");
					return true;
				}
			}
		}
		System.out.println("Du har ett par, men det suger!");
		return false;
	}

	/**
	 *  Kollar om olika Kortkombinationer räknas som par.
	 */
	public void hasPair() {
		for (int i = 0; i < 4; i++) {
			if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
				score += "Par";
			} else {
				score += "Null";
			}
		}
	}

	/**
	 *  Metoden kollar om olika kortkombinationer är stege.
	 *  Tar även hänsyn till att ess kan vara 1 eller 14 i olika stegar.
	 */
	public void hasStraight() {
		int värde = 0;
		for (int i = 1; i < 5; i++) {
			värde += (hand.get(i).getValue());
		}
		if ((hand).get(4).getValue() == (hand.get(0).getValue() + 4)) {

			score += "Stege";
		} else if ((hand.get(0).getValue() == 1) && (värde == 46)) {

			score += "Kungligt";
		}
	}

	/**
	 *  metod för att kolla om handen är i färg.
	 */
	public void hasColour() {
		Suit färg = hand.get(0).getSuit();
		for (int i = 1; i < hand.size(); i++) {
			if (hand.get(i).getSuit() != färg) {
				break;
			}
			score += "Färg";
		}
	}

}
