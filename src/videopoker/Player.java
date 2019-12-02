package videopoker;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private ArrayList<Card> hand;
	private int wallet=0;
	
	public Player() {
		hand = new ArrayList<Card>();
		wallet = 0;
	}
	
	
	public void addMoney(int money) {
		this.wallet+=money;
	}
	public boolean withdrawMoney(int withdrawMoney) {
		
		if(wallet>withdrawMoney) {
			wallet+=-withdrawMoney;
			return true;
		}
		return false;
	}
	
	public int checkWallet() {
		return wallet;
	}
	
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	public void removeCardFromHand(Card card) {
		hand.remove(card);
	}
	
	public List<Card> getHand() {
		return hand;
	}
	
	public void reset() {
		hand.clear();
	}

}
