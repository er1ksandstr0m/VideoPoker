package videopoker;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

public class Verktyg extends JPanel implements ActionListener{

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


	private ImageIcon[] hand = new ImageIcon[5];
	
	private VideoPoker vp;

	public Verktyg(VideoPoker vp){
		this.vp = vp;
		//setLayout(new GridLayout(3, 5, 0, 0));
		setBackground(Color.BLUE);
		nyHand(this.vp.getSpelare(0));
		//nyHand();
		
		regelImage = findImage("Kort/FiveCardPoker2.png");
		regelLabel = new JLabel(new ImageIcon(regelImage));
		regelPanel.add(regelLabel);
		
		betButtonImage = findImage("Kort/Bet.png");
		dealButtonImage = findImage("Kort/Deal.png");
		restartButtonImage = findImage("Kort/Restart.png");
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
			kortPanel.setBackground(Color.BLUE);
			startButtonIcon = new ImageIcon(startButtonImage);
			startButton.setIcon(startButtonIcon);
		}
		catch(NullPointerException e) {
			System.out.println("faaaan");
		}

		//setSize(800,100);
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
				if (buttons[i].getIcon() == baksidaIkon) {
					buttons[i].setIcon(hand[i]);
				} else {
					buttons[i].setIcon(baksidaIkon);
				}
			}
		}

		if (e.getSource() == startButton) {
			System.out.println("Start");
			nyHand(vp.getSpelare(0));
		}

		// Kollar vad som händer när man trycker in deal-knappen
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
			int poäng = 0;
			dealButton.setEnabled(false);
			restartButton.setEnabled(true);

			vp.getSpelare(0).addMoney(vp.kollaHand(vp.getSpelare(0)));
			poängtavla.setText("" + vp.getSpelare(0).getWallet());
			for (JButton button : buttons) {
				button.removeActionListener(this);
			}
		}

		if (e.getSource() == betButton) {
			vp.getSpelare(0).placeBet();
			bettingLabel.setText("" + vp.getSpelare(0).getBet());
		}
		if (e.getSource() == restartButton) {
			nyHand(vp.spelare.get(0));
			dealButton.setEnabled(true);
			restartButton.setEnabled(false);
			for (JButton button : buttons) {
				button.addActionListener(this);
			}

		}

	}

	/**
	 * Skapar ny deck och ny hand
	 */
	public void nyHand(Player p) {
		poängtavla.setText("" + p.getWallet());

		vp.setKortlek(new Deck());
		vp.getKortlek().shuffle();
		p.reset();
		for (int i = 0; i < 5; i++) {
			p.addCardToHand(vp.getKortlek().draw());
		}

		// hämtar bilderna för spelarens hand
		for (int i = 0; i < p.getHand().size(); i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			image = findImage("Kort/" + p.getHand().get(i).getSymbol() + p.getHand().get(i).getValue() + ".png");
			hand[i] = new ImageIcon(image);
		}
		for (JButton button : buttons) {
			button.setIcon(hand[Arrays.asList(buttons).indexOf(button)]);
			kortPanel.add(button);
			kortPanel.repaint();
		}
	}
	
	/**
	 * 
	 * @param image
	 * @return image
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

	public void nyaBaksidor() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setIcon(baksidaIkon);
			kortPanel.add(buttons[i]);
		}
	}

}
