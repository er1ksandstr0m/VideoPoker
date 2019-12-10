package videopoker;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Arrays;

@SuppressWarnings("serial")
public class Verktyg extends JPanel implements ActionListener{

	private BufferedImage baksida;
	private ImageIcon baksidaIkon;
	private BufferedImage dealButtonImage;
	private ImageIcon dealButtonIcon;
	private BufferedImage betButtonImage;
	private ImageIcon betButtonIcon;
	private BufferedImage restartButtonImage;
	private ImageIcon restartButtonIcon;
	private BufferedImage startButtonImage;
	private ImageIcon startButtonIcon;
	private BufferedImage image;
	private BufferedImage nyImage;
	
	private BufferedImage regelImage;
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
	
	private VideoPoker vp;

//  cheat
//	vp.getSpelare(0).reset();
//	vp.getSpelare(0).addCardToHand(new Card(11,Suit.CLUBS));
//	vp.getSpelare(0).addCardToHand(new Card(11,Suit.CLUBS));
//	vp.getSpelare(0).addCardToHand(new Card(11,Suit.CLUBS));
//	vp.getSpelare(0).addCardToHand(new Card(5,Suit.CLUBS));
//	vp.getSpelare(0).addCardToHand(new Card(11,Suit.CLUBS));

	public Verktyg(VideoPoker vp){
		this.vp = vp;
		setBackground(Color.BLUE);
		nyHand();
		
		regelImage = findImage("Kort/FiveCardPoker2.png");
		regelLabel = new JLabel(new ImageIcon(regelImage));
		regelPanel.add(regelLabel);
		
		kortPanel.setBackground(Color.BLUE);

		betButtonImage = findImage("Kort/Bet.png");
		dealButtonImage = findImage("Kort/Deal.png");
		restartButtonImage = findImage("Kort/NewHand.png");
		startButtonImage = findImage("Kort/Start.png");
		baksida = findImage("Kort/Baksida.png");
		baksidaIkon = new ImageIcon(baksida);
		
		try {
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
		}
		catch(NullPointerException e) {
			System.out.println("faaaan");
		}

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
		checkButtons(e);
		checkRestartButton(e);
		checkBetButton(e);
		checkDealButton(e);
		checkStartButton(e);
	}

	// Skapar ny deck och ny hand

	/**
	 * Skapar ny deck och ny hand
	 */
	public void nyHand() {
		poängtavla.setText("" + vp.getSpelare(0).getWallet());
		vp.getSpelare(0).withdrawMoney(vp.getSpelare(0).getBet());
		poängtavla.setText("" + vp.getSpelare(0).getWallet());
		vp.setKortlek(new Deck());
		vp.getKortlek().shuffle();
		vp.getSpelare(0).reset();
		for (int i = 0; i < 5; i++) {
			vp.getSpelare(0).addCardToHand(vp.getKortlek().draw());
		}
		// hämtar bilderna för spelarens hand
		for (int i = 0; i < vp.getSpelare(0).getHand().size(); i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			image = findImage("Kort/" + vp.getSpelare(0).getHand().get(i).getSymbol() 
					+ vp.getSpelare(0).getHand().get(i).getValue() + ".png");
			hand[i] = new ImageIcon(image);
		}
		for (JButton button : buttons) {
			// try{
			// Thread.sleep(100);
			// }catch(InterruptedException e) {
			// System.out.println(e);
			// }
			button.setIcon(hand[Arrays.asList(buttons).indexOf(button)]);
			kortPanel.add(button);
			kortPanel.repaint();
		}
	}
	
	/**
	 * 
	 * @param String image
	 * @return BufferedImage
	 */
	public BufferedImage findImage(String image) {
		try {
			return ImageIO.read(new File(Verktyg.class.getResource(image).toURI()));
		} catch (Exception ex) {
			System.out.println("Fel: Filen hittades inte eller det blev nåt uri-skit  "
					+ "Vilken bild som har varit stygg: " + image.substring(4));
		}
		return null;
	}

	/**
	 * Kollar vad som händer när man trycker på kort-knapparna
	 * @param e
	 */
	private void checkButtons(ActionEvent e) {
		for (int i = 0; i < 5; i++) {
			if (e.getSource() == buttons[i]) {
				if (buttons[i].getIcon() == baksidaIkon) {
					buttons[i].setIcon(hand[i]);
				} else {
					buttons[i].setIcon(baksidaIkon);
				}
			}
		}
	}

	/**
	 * Kollar vad som händer när man trycker på start-knappen
	 * @param e
	 */
	public void checkStartButton(ActionEvent e) {
		if (e.getSource() == startButton) {
		System.out.println("Start");
		vp.getSpelare(0).placeBet(Integer.parseInt(bettingLabel.getText()));
		nyHand();
		dealButton.setEnabled(true);
		restartButton.setEnabled(false);
		startButton.setEnabled(false);
		betButton.setEnabled(false);
		}
	}

	/**
	 * Kollar vad som händer när man trycker in deal-knappen
	 * @param e
	 */
	public void checkDealButton(ActionEvent e) {
		if (e.getSource() == dealButton) {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i].getIcon() == baksidaIkon) {
					Card nyttKort = vp.getKortlek().draw();
					vp.getSpelare(0).changeCard(vp.getSpelare(0).getHand().get(i), nyttKort);
					nyImage = findImage("Kort/" + nyttKort.getSymbol() + nyttKort.getValue() + ".png");
					ImageIcon nyIcon = new ImageIcon(nyImage);
					buttons[i].setIcon(nyIcon);
					hand[i] = nyIcon;
				}
			}
			if (vp.kollaHand(vp.getSpelare(0)) > 0) {
				JOptionPane.showMessageDialog(null, "BOOOOM!! Du vann "
						+ vp.kollaHand(vp.getSpelare(0)) * vp.getSpelare(0).getBet() + " \uD83C\uDF55");
			}
			vp.getSpelare(0).addMoney(vp.kollaHand(vp.getSpelare(0)) * vp.getSpelare(0).getBet());
			vp.getSpelare(0).clearBet();
			dealButton.setEnabled(false);
			restartButton.setEnabled(true);
			betButton.setEnabled(true);
			poängtavla.setText("" + vp.getSpelare(0).getWallet());
			for (JButton button : buttons) {
				button.removeActionListener(this);
			}
		}
	}
	
	/**
	 * Kollar vad som händer när man trycker på bet-knappen
	 * @param e
	 */
	public void checkBetButton(ActionEvent e) {
		if (betsiffra < 3) {
			betsiffra++;
		} else {
			betsiffra = 1;
		}
		bettingLabel.setText("" + betsiffra);

	}
	
	/**
	 * Kollar vad som händer när man trycker på restart-knappen
	 * @param e
	 */
	public void checkRestartButton(ActionEvent e) {
		if (e.getSource() == restartButton) {
			vp.spelare.get(0).placeBet(Integer.parseInt(bettingLabel.getText()));
			nyHand();
			dealButton.setEnabled(true);
			restartButton.setEnabled(false);
			betButton.setEnabled(false);
			for (JButton button : buttons) {
				button.addActionListener(this);
			}
		}
		
	}
	
	/**
	 * Vänder alla kort upp-och-ner
	 */
	public void nyaBaksidor() {

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setIcon(baksidaIkon);
			kortPanel.add(buttons[i]);
		}
		poängtavla.setText("" + vp.getSpelare(0).getWallet());
		dealButton.setEnabled(false);
		restartButton.setEnabled(false);
	}
}
