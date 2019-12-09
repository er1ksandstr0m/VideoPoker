package videopoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ritare extends JFrame{

	Player spelare;
	Deck kortlek;

	Verktyg verktyg = new Verktyg();
	private JPanel panel = new JPanel();

	public Ritare() {


		// setLayout(new BorderLayout());
		//
		// add(panel, BorderLayout.CENTER);
		// panel.setBackground(Color.BLUE);
		add(verktyg);

		setBackground(Color.BLUE);
		setSize(950, 900);
	//	pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}




public static void main (String [] args) {

	Ritare ritare = new Ritare();
}


}
