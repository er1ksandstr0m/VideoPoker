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

@SuppressWarnings("serial")
public class Ritare extends JFrame{
	Verktyg verktyg;
	// private JPanel panel = new JPanel();

	public Ritare(Verktyg verktyg) {
		// verktyg = new Verktyg(antalSpelare);
		// setLayout(new BorderLayout());
		//
		// add(panel, BorderLayout.CENTER);
		// panel.setBackground(Color.BLUE);
		add(verktyg);

		setBackground(Color.BLUE);
		setSize(new Dimension(1000, 1000));
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
