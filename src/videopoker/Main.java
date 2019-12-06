package videopoker;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int antalSpelare = scan.nextInt();
		VideoPoker poker = new VideoPoker(antalSpelare);

		for(Player p : poker.spelare){
			poker.score(p.getHand(), p.getScore());
			System.out.println("Du fick korten " + poker.handTillSträng(p.getHand()) +
			" och fick " + /*score() +*/ " poäng");
		}		
		scan.close();
	}

}
