package videopoker;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Main
		Scanner scan = new Scanner(System.in);
		System.out.println("Hur många spelare ska spela?");
		int antalSpelare = scan.nextInt();
		VideoPoker vp = new VideoPoker(antalSpelare);
		Verktyg verktyg = new Verktyg(vp);
		Ritare ritare = new Ritare(verktyg);
	}

}
