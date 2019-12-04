package videopoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VideoPoker {
	Player spelare;
	Deck kortlek;

	public VideoPoker() {

		spelare = new Player();
		kortlek = new Deck();

		kortlek.shuffle();

		for (int i = 0; i < 5; i++) {
			spelare.addCardToHand(kortlek.draw());
		}

		bytKort();

	}

	public String handTillSträng(ArrayList<Card> hand) {
		String handTillSträng = "";
		for (Card kort : spelare.getHand()) {
			handTillSträng += kort.toString() + ", ";
		}
		return handTillSträng;

	}

	public void bytKort(){


	  Scanner sc = new Scanner(System.in);

	  System.out.println("Du har korten " + handTillSträng(spelare.getHand()) +
	  " Vilka kort vill du byta (skriv kortets nummer)");


	  while(sc.hasNextInt()){
	    spelare.changeCard(spelare.getHand().get(sc.nextInt()-1), kortlek.draw());
	  }


		System.out.println("Du fick korten " + handTillSträng(spelare.getHand()) +
		" och fick " + /*score() +*/ " poäng");
		score();


	  sc.close();
	  }

	public void score() {
		// SCORE
		String s = "";
		int p = 0;

		int[] array = { 1, 3, 2, 2, 1 };
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		for (int i = 0; i < 4; i++) {
			if (array[i] == array[i + 1]) {
				s += "Par";
			} else {
				s += "Null";
			}

		}
		if ((array[4]) == (array[0] + 4)) {
			s += "Stege";
		}
		System.out.println(s);

		if (s.contains("Stege") && !s.contains("Par")) {
			System.out.println("Stege!");
		} else if (s.contains("ParParPar")) {
			System.out.println("Fyrtal!");
		} else if (s.contains("ParPar") && s.startsWith("Par") && ((s.endsWith("Par") || (s.endsWith("ParStege"))))) {
			System.out.println("Kåk!");
		} else if (s.contains("ParPar") && ((s.contains("NullNull"))
				|| ((s.startsWith("Null") && ((s.endsWith("Null") || s.endsWith("NullStege"))))))) {
			System.out.println("Triss!");
		} else if ((s.matches("ParNullParNull")) || (s.matches("ParNullNullPar")) || (s.matches("NullParNullPar"))) {
			System.out.println("Två Par!");
		}
	}

	public static void main(String[] args) {
		VideoPoker poker = new VideoPoker();
	}
}
