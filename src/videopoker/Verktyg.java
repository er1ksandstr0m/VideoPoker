
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Verktyg extends JPanel implements ActionListener {

	private BufferedImage baksida;
	private ImageIcon baksidaIkon;
	private BufferedImage image;
	private BufferedImage dealButtonImage;
	private ImageIcon dealButtonIcon;
	private BufferedImage standButtonImage;
	private ImageIcon standButtonIcon;
	private BufferedImage restartButtonImage;
	private ImageIcon restartButtonIcon;

	private JButton b1 = new JButton();
	private JButton b2 = new JButton();
	private JButton b3 = new JButton();
	private JButton b4 = new JButton();
	private JButton b5 = new JButton();

	private JPanel kortPanel = new JPanel();
	private JButton[] buttons = { b1, b2, b3, b4, b5 };

	private JPanel knappanel = new JPanel();
	private JButton dealButton = new JButton();
	private JButton standButton = new JButton();
	private JButton restartButton = new JButton();


	private ImageIcon[] hand = new ImageIcon[5];
	private String[] lästHand = { "H5", "KA", "KK", "S2", "K3" };


	public Verktyg() {

		setLayout(new GridLayout(2,5));

		add(kortPanel);
		kortPanel.setBackground(Color.BLUE);

		try {
			dealButtonImage = ImageIO.read(new File("/Users/pontuseriksson/Documents/Kort/Deal.png"));
		} catch (IOException ex) {
			System.out.println("Filen hittades inte");
		}
		try {
			standButtonImage = ImageIO.read(new File("/Users/pontuseriksson/Documents/Kort/Stand.png"));
		} catch (IOException ex) {
			System.out.println("Filen hittades inte");
		}
		try {
			restartButtonImage = ImageIO.read(new File("/Users/pontuseriksson/Documents/Kort/Restart.png"));
		} catch (IOException ex) {
			System.out.println("Filen hittades inte");
		}


		dealButtonIcon = new ImageIcon(dealButtonImage);
		dealButton.setIcon(dealButtonIcon);
		standButtonIcon = new ImageIcon(standButtonImage);
		standButton.setIcon(standButtonIcon);
		restartButtonIcon = new ImageIcon(restartButtonImage);
		restartButton.setIcon(restartButtonIcon);

		add(knappanel);
		knappanel.setBackground(Color.BLUE);
		knappanel.add(dealButton);
		knappanel.add(standButton);
		knappanel.add(restartButton);



		try {
			baksida = ImageIO.read(new File("/Users/pontuseriksson/Documents/Kort/Baksida.png"));
		} catch (IOException ex) {
			System.out.println("Filen hittades inte");
		}

		baksidaIkon = new ImageIcon(baksida);

		for (int i = 0; i < lästHand.length; i++) {
			try {
				image = ImageIO.read(new File("/Users/pontuseriksson/Documents/Kort/" + lästHand[i] + ".png"));
			} catch (IOException ex) {
				System.out.println("Filen hittades inte");
			}

			hand[i] = new ImageIcon(image);
		}

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setIcon(hand[i]);
			kortPanel.add(buttons[i]);
		}

		for (JButton button : buttons) {
			button.addActionListener(this);
		}

	}


	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < 5; i++) {

			if (e.getSource() == buttons[i]) {

				if (buttons[i].getIcon() == baksidaIkon)
					buttons[i].setIcon(hand[i]);

				else
					buttons[i].setIcon(baksidaIkon);

			}

		}

	}

}
