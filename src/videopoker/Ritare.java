package videopoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ritare extends JFrame{

	Verktyg verktyg = new Verktyg();
	private JPanel panel = new JPanel();

	public Ritare() {

		setLayout(new BorderLayout());

		add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.BLUE);
		panel.add(verktyg);

		setBackground(Color.BLUE);
		setSize(new Dimension(300, 200));
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}




public static void main (String [] args) {

	Ritare ritare = new Ritare();
}


}
