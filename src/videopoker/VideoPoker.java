package videopoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class VideoPoker {
	Player spelare;
	Deck kortlek;
	String s;

	public Player getSpelare() {
		return spelare;
	}

	public VideoPoker(Player spelare) {

		this.spelare = spelare;
		kortlek = new Deck();

		kortlek.shuffle();

		for (int i = 0; i < 5; i++) {
			spelare.addCardToHand(kortlek.draw());
		}

//		bytKort();

	}

	public String handTillSträng(ArrayList<Card> hand) {
		String handTillSträng = "";
		for (Card kort : spelare.getHand()) {
			handTillSträng += kort.toString() + ", ";
		}
		return handTillSträng;

	}

//	public void bytKort(){
//
//
//	  Scanner sc = new Scanner(System.in);
//
//	  System.out.println("Du har korten " + handTillSträng(spelare.getHand()) +
//	  " Vilka kort vill du byta (skriv kortets nummer)");
//
//
//	  while(sc.hasNextInt()){
//	    spelare.changeCard(spelare.getHand().get(sc.nextInt()-1), kortlek.draw());
//	  }
//

//		System.out.println("Du fick korten " + handTillSträng(spelare.getHand()) +
//		" och fick " + /*score() +*/ " poäng");
//		score();
//
//
//	  sc.close();
//	  }

	@SuppressWarnings("unchecked")
	public int score(ArrayList<Card> hand) {
		// SCORE

		String s = "";

		// sorterar vår lista
		Collections.sort(hand);

		// jämför korten mot varandra, två och två, och skriver sträng s som vi jämför
		// med i själva score
		for (int i = 0; i < 4; i++) {
			if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
				s += "Par";
			} else {
				s += "Null";
			}
		}
		System.out.println(s);
		// kollar om man fått färg och lägger till det i sträng s
		isFärg();

		// kollar om det är en stege och lägger till det i strängen
		isStege();

		// här ska vi lägga in bet och multiplicera med rätt faktor
		if (s.contains("Stege") && !s.contains("Par")) {
			if (s.contains("Färg")) {
				System.out.println("STRAIGHT FLUSH!");
				return 50;
			} else {
				System.out.println("Stege!");
				return 4;
			}
		} else if (s.contains("Kungligt") && !s.contains("Par") && s.contains("Färg")) {

			System.out.println("ROYAL FLUSH!");
			return 800;

		} else if (s.contains("ParParPar")) {
			System.out.println("Fyrtal!");
			return 25;

		} else if (s.contains("ParPar") && (s.startsWith("Par")
				&& ((s.endsWith("Par") || (s.endsWith("ParStege") || (s.endsWith("ParKungligt"))))))) {
			System.out.println("Kåk!");
			return 9;

		} else if (s.contains("ParPar")) {
			System.out.println("Triss!");
			return 3;

		} else if ((s.matches("ParNullParNull")) || (s.matches("ParNullNullPar")) || (s.matches("NullParNullPar"))) {
			System.out.println("Två Par!");
			return 2;

		} else if (s.contains("Par")) {

			if(dugerParet()) {
				return 1;
			}

		} else {
			if (s.contains("Färg")) {
				System.out.println("Färg!!!");
				return 6;
			}
		}
		System.out.println("Sorry - du fick nada.");
		return 0;
	}

	// metod för att kolla om handen är i färg
	public void isFärg() {
		Suit färg = spelare.getHand().get(0).getSuit();
		for (int i = 1; i < spelare.getHand().size(); i++) {
			if (spelare.getHand().get(i).getSuit() != färg) {
//				s += "Null";
				break;
			}
			s += "Färg";
		}
	}


	public boolean dugerParet() {
		for (int i = 0; i < 4; i++) {
			if (spelare.getHand().get(i).getValue() == spelare.getHand().get(i + 1).getValue()) {
				if ((spelare.getHand().get(i).getValue() > 10) || (spelare.getHand().get(i).getValue() == 1)) {
					System.out.println("Du har ett par som duger!!");
					return true;
				}
			}

		}
		System.out.println("Du har ett par, men det suger!");
		return false;

	}

	public void betta(Player p, int summa) {
		if (!p.placeBet(summa)) {
			// Kicka spelare?
		}
	}

	// Metoden kollar om olika kortkombinationer är stege. Tar även hänsyn till att
	// ess kan vara 1 eller 14 i olika stegar.

	public void isStege() {
		int värde = 0;
		for (int i = 1; i < 5; i++) {
			värde += (spelare.getHand().get(i).getValue());
		}
		if ((spelare.getHand().get(4).getValue()) == (spelare.getHand().get(0).getValue() + 4)) {

			s += "Stege";
		} else if ((spelare.getHand().get(0).getValue() == 1) && (värde == 46)) {

			s += "Kungligt";
		}
	}

	// public static void main(String[] args) {
	// 	VideoPoker poker = new VideoPoker();
	// }

	public void rageQuit() {
// Framtida sparning här
		System.exit(0);
	}
}
