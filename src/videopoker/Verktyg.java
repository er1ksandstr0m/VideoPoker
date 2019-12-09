package videopoker;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
//import java.awt.Image.getScaledInstance;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.geom.*;
import java.net.*;
import java.awt.Dimension;

public class Verktyg extends JPanel implements ActionListener {

	private Player spelare;
	private Deck kortlek;

	private BufferedImage baksida;
	private ImageIcon baksidaIkon;
	private BufferedImage image;
	private BufferedImage dealButtonImage;
	private ImageIcon dealButtonIcon;
	private BufferedImage betButtonImage;
	private ImageIcon betButtonIcon;
	private BufferedImage restartButtonImage;
	private ImageIcon restartButtonIcon;
	private BufferedImage nyImage;
	private BufferedImage startButtonImage;
	private ImageIcon startButtonIcon;

	private BufferedImage regelImage;
	private ImageIcon regelIcon;
	private JPanel regelPanel = new JPanel();
	private JLabel regelLabel;

	private JButton b1 = new JButton();
	private JButton b2 = new JButton();
	private JButton b3 = new JButton();
	private JButton b4 = new JButton();
	private JButton b5 = new JButton();

	private JPanel kortPanel = new JPanel();
	private JButton[] buttons = { b1, b2, b3, b4, b5 };

	private JPanel knappanel = new JPanel();
	private JButton dealButton = new JButton();
	private JButton betButton = new JButton();
	private JButton restartButton = new JButton();
	private JButton startButton = new JButton();

	private JLabel poängtavleEtikett = new JLabel("credits:");
	private JLabel poängtavla = new JLabel("0");

	private JLabel bettingLabel = new JLabel("1");
	private int betsiffra = 1;

	private ImageIcon[] hand = new ImageIcon[5];

	// private int poäng;
	VideoPoker vp;

//cheat
//	spelare.reset();
//	spelare.addCardToHand(new Card(11,Suit.CLUBS));
//	spelare.addCardToHand(new Card(11,Suit.CLUBS));
//	spelare.addCardToHand(new Card(11,Suit.CLUBS));
//	spelare.addCardToHand(new Card(5,Suit.CLUBS));
//	spelare.addCardToHand(new Card(11,Suit.CLUBS));

	public Verktyg() {

		try {
			baksida = ImageIO.read(new File(Verktyg.class.getResource("Kort/Baksida.png").toURI()));
		} catch (Exception ex) {
			System.out.println("Filen hittades inte eller nåt");
		}

		baksidaIkon = new ImageIcon(baksida);

		// setLayout(new GridLayout(3, 5, 0, 0));
		setBackground(Color.BLUE);
		spelare = new Player();
		spelare.addMoney(100);
		nyaBaksidor();
		// nyHand();
		vp = new VideoPoker(spelare);
		bettingLabel.setText("" + betsiffra);

		try {
			regelImage = ImageIO.read(new File(Verktyg.class.getResource("Kort/FiveCardPoker2.png").toURI()));
		} catch (Exception ex) {
			System.out.println("Filen hittades inte eller toURIExeption");
		}

		regelLabel = new JLabel(new ImageIcon(regelImage));
		regelPanel.add(regelLabel);

		kortPanel.setBackground(Color.BLUE);

		try {
			dealButtonImage = ImageIO.read(new File(Verktyg.class.getResource("Kort/Deal.png").toURI()));
			betButtonImage = ImageIO.read(new File(Verktyg.class.getResource("Kort/Bet.png").toURI()));
			restartButtonImage = ImageIO.read(new File(Verktyg.class.getResource("Kort/NewHand.png").toURI()));
			startButtonImage = ImageIO.read(new File(Verktyg.class.getResource("Kort/Start.png").toURI()));
		} catch (Exception ex) {
			System.out.println("Filen hittades inte eller det blev nåt uri-skit");
		}

		dealButtonIcon = new ImageIcon(dealButtonImage);
		dealButton.setIcon(dealButtonIcon);
		betButtonIcon = new ImageIcon(betButtonImage);
		betButton.setIcon(betButtonIcon);
		restartButtonIcon = new ImageIcon(restartButtonImage);
		restartButton.setIcon(restartButtonIcon);
		restartButtonIcon = new ImageIcon(restartButtonImage);
		restartButton.setIcon(restartButtonIcon);
		startButtonIcon = new ImageIcon(startButtonImage);
		startButton.setIcon(startButtonIcon);

		// setSize(800,100);
		add(regelPanel);
		add(kortPanel);
		add(knappanel);

		// kortPanel.setPreferredSize(new Dimension(700, 400));
		// regelPanel.setPreferredSize(new Dimension(700, 400));
		// knappanel.setPreferredSize(new Dimension(700, 400));

		knappanel.setBackground(Color.BLUE);
		knappanel.add(startButton);
		knappanel.add(dealButton);
		knappanel.add(betButton);
		knappanel.add(bettingLabel);
		knappanel.add(restartButton);

		dealButton.addActionListener(this);
		betButton.addActionListener(this);
		restartButton.addActionListener(this);
		startButton.addActionListener(this);
		restartButton.setEnabled(false);

		knappanel.add(poängtavleEtikett);
		knappanel.add(poängtavla);
		poängtavleEtikett.setFont(new Font("SansSerif", 0, 40));
		poängtavla.setFont(new Font("SansSerif", 0, 40));
		bettingLabel.setFont(new Font("SansSerif", 0, 40));
		bettingLabel.setForeground(Color.YELLOW);

		poängtavla.setForeground(Color.YELLOW);
		poängtavleEtikett.setForeground(Color.YELLOW);

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

		if (e.getSource() == startButton) {
			System.out.println("Start");
			spelare.placeBet(Integer.parseInt(bettingLabel.getText()));
			nyHand();
			dealButton.setEnabled(true);
			restartButton.setEnabled(false);
			startButton.setEnabled(false);
			betButton.setEnabled(false);

		}

		// Kollar vad som händer när man trycker in deal-knappen
		if (e.getSource() == dealButton) {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i].getIcon() == baksidaIkon) {
					Card nyttKort = kortlek.draw();
					spelare.changeCard(spelare.getHand().get(i), nyttKort);

					try {
						nyImage = ImageIO.read(new File(Verktyg.class
								.getResource("Kort/" + nyttKort.getSymbol() + nyttKort.getValue() + ".png").toURI()));
					} catch (Exception ex) {
						System.out.println("Filen hittades inte typ");
					}

					ImageIcon nyIcon = new ImageIcon(nyImage);

					buttons[i].setIcon(nyIcon);
					hand[i] = nyIcon;

				}

			}
			if (vp.score(spelare.getHand()) > 0) {
				JOptionPane.showMessageDialog(null,
						"BOOOOM!! Du vann " + vp.score(spelare.getHand()) * spelare.getBet() + " \uD83C\uDF55");
			}
			spelare.addMoney(vp.score(spelare.getHand()) * spelare.getBet());
			spelare.clearBet();
			dealButton.setEnabled(false);
			restartButton.setEnabled(true);
			betButton.setEnabled(true);
			poängtavla.setText("" + spelare.getWallet());
			for (JButton button : buttons) {
				button.removeActionListener(this);

			}
		}

		if (e.getSource() == betButton) {
			if (betsiffra < 3) {
				betsiffra++;
			} else {
				betsiffra = 1;
			}
			bettingLabel.setText("" + betsiffra);

		}

		if (e.getSource() == restartButton) {
			spelare.placeBet(Integer.parseInt(bettingLabel.getText()));
			nyHand();
			dealButton.setEnabled(true);
			restartButton.setEnabled(false);
			betButton.setEnabled(false);

			for (JButton button : buttons) {
				button.addActionListener(this);
			}

		}

	}

	// Skapar ny deck och ny hand
	public void nyHand() {

		poängtavla.setText("" + spelare.getWallet());

		kortlek = new Deck();
		kortlek.shuffle();

		// if (spelare.getHand().size() > 0) {
		spelare.reset();
		// }

		for (int i = 0; i < 5; i++) {
			spelare.addCardToHand(kortlek.draw());

		}

		// hämtar bilderna för spelarens hand
		for (int i = 0; i < spelare.getHand().size(); i++) {
			try {
				image = ImageIO.read(new File(Verktyg.class.getResource(
						"Kort/" + spelare.getHand().get(i).getSymbol() + spelare.getHand().get(i).getValue() + ".png")
						.toURI()));

			} catch (Exception ex) {
				System.out.println("Filen hittades inte/URI");
			}

			hand[i] = new ImageIcon(image);
		}

		for (int i = 0; i < buttons.length; i++) {
			// try{
			// Thread.sleep(100);
			// }catch(InterruptedException e) {
			// System.out.println(e);
			// }
			buttons[i].setIcon(hand[i]);
			kortPanel.add(buttons[i]);
			kortPanel.repaint();
		}

	}

	public void nyaBaksidor() {

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setIcon(baksidaIkon);
			kortPanel.add(buttons[i]);
		}

		poängtavla.setText("" + spelare.getWallet());

		dealButton.setEnabled(false);
		restartButton.setEnabled(false);

	}

}
