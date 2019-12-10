package videopoker;

import java.util.ArrayList;
import java.util.Collections;

public class VideoPoker {
	public ArrayList<Player> spelare;
	private Deck kortlek;
	
	public VideoPoker(int antalSpelare) {
		spelare = new ArrayList<Player>();
		setKortlek(new Deck());

		getKortlek().shuffle();
		for (int i = 0; i < antalSpelare; i++) {
			spelare.add(new Player());
			for (int j = 0; j < 5; j++) {
				spelare.get(i).addCardToHand(getKortlek().draw());
			}
		}
//		bytKort();
	}
	
	/**
	 * Skriver uut handen som en sträng
	 * @param hand
	 * @return String
	 */
	public String handTillSträng(ArrayList<Card> hand) {
		String handTillSträng = "";
		for (Card kort : hand) {
			handTillSträng += kort.toString() + ", ";
		}
		return handTillSträng;

	}
	
	/**
	 * Hämtar en spelare
	 * @param index
	 * @return Player
	 */
	public Player getSpelare(int index) {
		return spelare.get(index);
	}
	
	/**
	 * Kollar handen och returnerar en poäng
	 * @return int poäng
	 */
	@SuppressWarnings("unchecked")
	public int kollaHand(Player p) {
		// sorterar vår lista
		Collections.sort(p.getHand());
		// kollar om man fått par och lägger till det i score
		p.harPar();
		// kollar om man fått färg och lägger till det i score
		p.harFärg();
		// kollar om man fått stege och lägger till det i score
		p.harStege();
		return p.räknaPoäng();
	}
	
	/**
	 * Satsar pengar
	 * @param p
	 * @param summa
	 */
	public void betta(Player p, int summa) {
		if (!p.placeBet(summa)) {
			// Kicka spelare?
		}
	}
	
	/**
	 * Stänger av spelet
	 */
	public void rageQuit() {
// Framtida sparning här
		System.exit(0);
	}

	/**
	 * hämtar och returnerar en kortlek
	 * @return Deck kortlek
	 */
	public Deck getKortlek() {
		return kortlek;
	}
	
	/**
	 * Ger kortlek ett värde
	 * @param kortlek
	 */
	public void setKortlek(Deck kortlek) {
		this.kortlek = kortlek;
	}
	
//	public void bytKort(){
//	  Scanner sc = new Scanner(System.in);
//	  System.out.println("Du har korten " + handTillSträng(spelare.getHand()) +
//	  " Vilka kort vill du byta (skriv kortets nummer)");
//	  while(sc.hasNextInt()){
//	    spelare.changeCard(spelare.getHand().get(sc.nextInt()-1), kortlek.draw());
//	  }
//		System.out.println("Du fick korten " + handTillSträng(spelare.getHand()) +
//		" och fick " + /*score() +*/ " poäng");
//		score();
//	  sc.close();
//	  }
}
