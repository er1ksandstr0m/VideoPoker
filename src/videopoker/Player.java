package videopoker;

import java.util.ArrayList;

public class Player {
	private ArrayList<Card> hand;
	private int wallet = 0;
	private int bet = 0;

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

	/**
	 * Gör försök att ta ut pengar. Returnerar om uttaget går bra eller ej.
	 * 
	 * @param pBet
	 * @return true om spelaren har råd med bet, annars false.
	 */
	public boolean placeBet(int pBet) {
		if (pBet < wallet) {
			wallet += -pBet;
			bet += pBet;
			return true;
		} else {
			System.out.println("För lite cash i plånkan yo!");
			return false;
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
	 * Tömmer spelarens hand
	 */
	public void reset() {
		hand.clear();
	}

}
