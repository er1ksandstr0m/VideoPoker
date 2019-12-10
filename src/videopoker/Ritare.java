package videopoker;

import java.awt.Color;
import javax.swing.JFrame;

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
		setSize(1000, 900);
	//	pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
