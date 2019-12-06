package videopoker;

import java.util.ArrayList;
import java.util.Arrays;
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

	public void bytKort(Player p, Scanner sc) {

		System.out.println(
				"Du har korten " + handTillSträng(p.getHand()) + " Vilka kort vill du byta (skriv kortets nummer)");

		while (sc.hasNextInt()) {
			p.changeCard(p.getHand().get(sc.nextInt() - 1), kortlek.draw());
		}

	}

	@SuppressWarnings("unchecked")
	public String score(Player p) {

		// sorterar vår lista
		Collections.sort(p.getHand());

		// jämför korten mot varandra, två och två, och skriver sträng s som vi jämför
		// med i själva score
		for (int i = 0; i < 4; i++) {
			if (p.getHand().get(i).getValue() == p.getHand().get(i + 1).getValue()) {
				p.setScore("Par");
			} else {
				p.setScore("Null");
			}
		}
		// kollar om man fått färg och lägger till det i sträng s
		isFärg(p.getHand());

		// kollar om sista kortet i listan är 4 större än det första, lägger till det i
		// strängen
		if ((p.getHand().get(4).getValue()) == (p.getHand().get(0).getValue() + 4)) {
			s += "Stege";
		}
		
		// här ska vi lägga in bet och multiplicera med rätt faktor
		if (s.contains("Stege") && !s.contains("Par")) {
			if (s.contains("Färg")) {
				System.out.println("STRAIGHT FLUSH!");
				return;
			} else {
				System.out.println("Stege!");
				return;
			}

		} else if (s.contains("ParParPar")) {
			System.out.println("Fyrtal!");
			return;

		} else if (s.contains("ParPar") && (s.startsWith("Par") && ((s.endsWith("Par") || (s.endsWith("ParStege")))))) {
			System.out.println("Kåk!");
			return;

		} else if (s.contains("ParPar")) {
			System.out.println("Triss!");
			return;

		} else if ((s.matches("ParNullParNull")) || (s.matches("ParNullNullPar")) || (s.matches("NullParNullPar"))) {
			System.out.println("Två Par!");
			return;
		} else if (s.contains("Par")) {
			System.out.println("Ett par!");
			return;
		} else {
			if (s.contains("Färg")) {
				System.out.println("Färg!!!");
				return;
			} else {
				System.out.println("Sorry - du fick nada.");
			}
		}
	}
	
	public void compareScores() {
		
	}

	//metod för att kolla om handen är i färg
	private void isFärg(ArrayList<Card> hand){
		Suit färg = hand.get(0).getSuit();
		for(int i = 1; i <hand.size(); i++){
			if (hand.get(i).getSuit() != färg){
//				s += "Null";
				break;
			}
			s += "Färg";
		}
	}

}
