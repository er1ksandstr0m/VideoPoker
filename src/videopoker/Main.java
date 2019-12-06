package videopoker;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int antalSpelare = scan.nextInt();
		VideoPoker poker = new VideoPoker(antalSpelare);
		
		for(int i = 0; i < poker.spelare.size(); i++) {
			System.out.println("Vill du byta kort?\n1.Ja\n2:Nej");
			int svar =scan.nextInt();
			if (svar == 1) {poker.bytKort(poker.spelare.get(i), scan);}
		}

		poker.score(poker.spelare.get(0).getHand(), poker.spelare.get(0).getScore());
		System.out.println("Du fick korten " + handTillSträng(p.getHand()) +
		" och fick " + /*score() +*/ " poäng");
		score(p.getHand());
		scan.close();
	}

}
