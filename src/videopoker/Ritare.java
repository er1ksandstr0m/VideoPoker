package videopoker;

import java.awt.Color;

import javax.swing.JFrame;

public class Ritare extends JFrame{

	Player spelare;
	Deck kortlek;

	Verktyg verktyg = new Verktyg();

	public Ritare() {


		// setLayout(new BorderLayout());
		//
		// add(panel, BorderLayout.CENTER);
		// panel.setBackground(Color.BLUE);
		add(verktyg);

		setBackground(Color.BLUE);
		setSize(1100, 900);
	//	pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}




public static void main (String [] args) {

	Ritare ritare = new Ritare();
}


}
