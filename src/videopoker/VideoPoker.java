package videopoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class VideoPoker {
	Player spelare;
	Deck kortlek;
	String s;

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

//		Instansieringen av Player bör kanske inte ligga här så småningom
		String s = "";
		Player p = new Player();

		//sorterar vår lista
		Collections.sort(p.getHand());

		//jämför korten mot varandra, två och två, och skriver sträng s som vi jämför med i själva score
		for(int i = 0; i < 4; i++) {
			if(p.getHand().get(i).getValue() == p.getHand().get(i + 1).getValue()) {
				s += "Par";
			} else {
				s += "Null";
			}
		}
		//kollar om man fått färg och lägger till det i sträng s
		isFärg();

		//kollar om sista kortet i listan är 4 större än det första, lägger till det i strängen
		if((p.getHand().get(4).getValue()) == (p.getHand().get(0).getValue() + 4)){
			s += "Stege";
		}

		//här ska vi lägga in bet och multiplicera med rätt faktor
		if(s.contains("Stege") && !s.contains("Par")) {
			if(s.contains("Färg")) {
				System.out.println("STRAIGHT FLUSH!");
				return;
			} else {
			System.out.println("Stege!");
			return;
			}

		} else if (s.contains("ParParPar")) {
			System.out.println("Fyrtal!");
			return;

		} else if(s.contains("ParPar") && (s.startsWith("Par") && ((s.endsWith("Par")||(s.endsWith("ParStege")))))) {
			System.out.println("Kåk!");
			return;

		} else if(s.contains("ParPar")){
			System.out.println("Triss!");
			return;

		} else if ((s.matches("ParNullParNull"))||(s.matches("ParNullNullPar"))||(s.matches("NullParNullPar"))) {
			System.out.println("Två Par!");
			return;
		} else if (s.contains("Par")) {
			System.out.println("Ett par!");
			return;
		} else {
			if(s.contains("Färg")) {
				System.out.println("Färg!!!");
				return;
			} else {
			System.out.println("Sorry - du fick nada.");
		}
		}
	}

	//metod för att kolla om handen är i färg
	public void isFärg(){
		Suit färg = spelare.getHand().get(0).getSuit();
		for(int i = 1; i <spelare.getHand().size(); i++){
			if (spelare.getHand().get(i).getSuit() != färg){
//				s += "Null";
				break;
			}
			s += "Färg";
		}
	}

	
	public void betta(Player p, int summa) {
		if(!p.placeBet(summa)) {
			//Kicka spelare?
		}
	}
	
	public static void main(String[] args) {
		
		VideoPoker poker = new VideoPoker();
	}
}
