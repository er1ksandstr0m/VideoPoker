package videopoker;

public class VideoPoker {
	Player spelare;
	Deck kortlek;

	public VideoPoker(){

	  spelare = new Player();
	  kortlek = new Deck();

		kortlek.shuffle();

	  for(int i = 0; i <5 ; i++){
	    spelare.addCardToHand(kortlek.draw());
	}

	  bytKort();


	}


	public String handTillSträng(ArrayList<Card> hand){
		String handTillSträng = "":
	  for(Card kort = spelare.getHand()){
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
		" och fick " + score() + " poäng";)


	  sc.close();
	  }


	  public static void main(String []args){
			VideoPoker poker = new VideoPoker();
	  }
	}

}
