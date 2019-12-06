package videopoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class VideoPoker {
	ArrayList<Player> spelare;
	Deck kortlek;

	public VideoPoker(int antalSpelare) {

		spelare = new ArrayList<Player>();
		kortlek = new Deck();

		kortlek.shuffle();
		for (int i = 0; i < antalSpelare; i++) {
			spelare.add(new Player());
		}
		for (int i = 0; i < spelare.size(); i++) {
			for (int j = 0; j < 5; j++) {
				spelare.get(i).addCardToHand(kortlek.draw());
			}
		}
		
	}

	public String handTillSträng(ArrayList<Card> hand) {
		String handTillSträng = "";
		for (Card kort : hand) {
			handTillSträng += kort.toString() + ", ";
		}
		return handTillSträng;

	}

	@SuppressWarnings("unchecked")
	public int score(ArrayList<Card> hand, String score) {
		// sorterar vår lista
		Collections.sort(hand);
		score = hasPairs(hand, score);
		// kollar om man fått färg och lägger till det i strängen score
		score = isColor(hand, score);
		// kollar om man fått stege och lägger till det i strängen score
		score = isStraight(hand, score);
		
		return selectScore(hand, score);
	}

	private int selectScore(ArrayList<Card> hand, String score) {
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

		} else if (score.contains("ParPar") && (score.startsWith("Par") && ((score.endsWith("Par") || (score.endsWith("ParStege")))))) {
			System.out.println("Kåk!");
			return 9;

		} else if (score.contains("ParPar")) {
			System.out.println("Triss!");
			return 3;

		} else if ((score.matches("ParNullParNull")) || (score.matches("ParNullNullPar")) || (score.matches("NullParNullPar"))) {
			System.out.println("Två Par!");
			return 2;

		} else if (score.contains("Par")) {

			dugerParet(hand);
			return 1;

		} else {
			if (score.contains("Färg")) {
				System.out.println("Färg!!!");
				return 6;

			} else {
				System.out.println("Sorry - du fick nada.");
			}
			return 0;
		}		
	}

	public void compareScores() {
		
	}

	public void dugerParet(ArrayList<Card> hand) {
		for (int i = 0; i < 4; i++) {
			if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
				if (hand.get(i).getValue() > 10) {
					System.out.println("Du har ett par som duger!!");
					return;
				}
			}

		}
		System.out.println("Du har ett par, men det suger!");
	}

	public void betta(Player p, int summa) {
		if (!p.placeBet(summa)) {
			// Kicka spelare?
		}
	}

	private String hasPairs(ArrayList<Card> hand, String score) {
		// jämför korten mot varandra, två och två, och skriver sträng score som vi
		// med i själva score
		for (int i = 0; i < 4; i++) {
			if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
				score += "Par";
			} else {
				score += "Null";
			}
		}
		return score;
	}
	
	//Metoden kollar om olika kortkombinationer är stege. Tar även hänsyn till att ess kan vara 1 eller 14 i olika stegar. 
	public String isStraight(ArrayList<Card> hand, String score) {
		int värde = 0;
		for (int i = 1; i < 5; i++) {
			värde += (hand.get(i).getValue());
		}
		if ((hand.get(4).getValue()) == (hand.get(0).getValue() + 4)) {

			score += "Stege";
		} else if ((hand.get(0).getValue() == 1) && (värde == 46)) {

			score += "Kungligt";
		}
		return score;
	}
	
	//metod för att kolla om handen är i färg
	private String isColor(ArrayList<Card> hand, String score){
		Suit färg = hand.get(0).getSuit();
		for(int i = 1; i <hand.size(); i++){
			if (hand.get(i).getSuit() != färg){
				break;
			}
			score += "Färg";
		}
		return score;
	}
	
	public void rageQuit() {
// Framtida sparning här
		System.exit(0);
	}
}
