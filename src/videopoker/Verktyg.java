package videopoker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Verktyg extends JPanel implements ActionListener {

	private Player spelare;
	private Deck kortlek;

	private ImageIcon baksidaIkon;
	private BufferedImage bImage;
	private JPanel regelPanel = new JPanel();

	private JPanel kortPanel = new JPanel();
	private JButton[] buttons = new JButton[5];

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

	String kortkatalog = "Kort3"; //Katalog för bilderna. Obs, se till att typ start-bilden och sånt finns i mappen

	Dimension d = new Dimension(200, 300);// Kortens/kortknapparnas storlek
	VideoPoker vp;
	private boolean win = false; //Cheat för royal straight flush
	Color background = Color.BLACK; //Bakgrundsfärgen

	public Verktyg() {
		for (int i = 0; i < 5; i++) {
			buttons[i] = new JButton();
		}

		getImage(kortkatalog + "/Baksida.png");
		Image image = new ImageIcon(bImage).getImage(); // transform it
		image = image.getScaledInstance(d.width, d.height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		baksidaIkon = new ImageIcon(image);
		// setLayout(new GridLayout(3, 5, 0, 0));
		setBackground(background);
		spelare = new Player();
		spelare.addMoney(100);
		nyaBaksidor();
		// nyHand();
		vp = new VideoPoker(spelare);
		bettingLabel.setText("" + betsiffra);

		getImage(kortkatalog + "/FiveCardPoker1_2.png");
		regelPanel.add(new JLabel(new ImageIcon(bImage)));

		kortPanel.setBackground(background);

		getImage(kortkatalog + "/Deal.png");
		dealButton.setIcon(new ImageIcon(bImage));
		getImage(kortkatalog + "/Bet.png");
		betButton.setIcon(new ImageIcon(bImage));
		getImage(kortkatalog + "/Start.png");
		startButton.setIcon(new ImageIcon(bImage));
		getImage(kortkatalog + "/NewHand.png");
		restartButton.setIcon(new ImageIcon(bImage));

		add(regelPanel);
		add(kortPanel);
		add(knappanel);

		knappanel.setBackground(background);
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
			for (JButton button : buttons) {
				button.addActionListener(this);
			}
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
					getImage(kortkatalog + "/" + nyttKort.getSymbol() + nyttKort.getValue() + ".png");

					Image image = new ImageIcon(bImage).getImage(); // transform it
					image = image.getScaledInstance(d.width, d.height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

					hand[i] = new ImageIcon(image);

					buttons[i].setIcon(hand[i]);

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
		if (win == true) {
			spelare.reset();
			spelare.addCardToHand(new Card(10, Suit.CLUBS));
			spelare.addCardToHand(new Card(11, Suit.CLUBS));
			spelare.addCardToHand(new Card(12, Suit.CLUBS));
			spelare.addCardToHand(new Card(13, Suit.CLUBS));
			spelare.addCardToHand(new Card(1, Suit.CLUBS));
		}

		// hämtar bilderna för spelarens hand
		for (int i = 0; i < spelare.getHand().size(); i++) {
			getImage(kortkatalog + "/" + spelare.getHand().get(i).getSymbol() + spelare.getHand().get(i).getValue()
					+ ".png");

			Image image = new ImageIcon(bImage).getImage(); // transform it
			image = image.getScaledInstance(200, 300, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

			hand[i] = new ImageIcon(image);
		}

		for (int i = 0; i < buttons.length; i++) {
//			 try{
//			 Thread.sleep(100);
//			 }catch(InterruptedException e) {
//			 System.out.println(e);
//			 }

			buttons[i].setIcon(hand[i]);
			kortPanel.add(buttons[i]);
			kortPanel.repaint();
		}

	}

	public void nyaBaksidor() {

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setIcon(baksidaIkon);
			buttons[i].setPreferredSize(d);
			kortPanel.add(buttons[i]);
		}

		poängtavla.setText("" + spelare.getWallet());

		dealButton.setEnabled(false);
		restartButton.setEnabled(false);

	}

	private void getImage(String source) {
		try {
			bImage = ImageIO.read(new File(Verktyg.class.getResource(source).toURI()));
		} catch (IOException | URISyntaxException e) {
			System.out.println("Crap!");
			e.printStackTrace();
		}
	}
}
