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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Verktyg extends JPanel implements ActionListener{

	private BufferedImage baksida;
	private ImageIcon baksidaIkon;
	private BufferedImage image;
	private BufferedImage dealButtonImage;
	private ImageIcon dealButtonIcon;
	private BufferedImage standButtonImage;
	private ImageIcon standButtonIcon;
	private BufferedImage restartButtonImage;
	private ImageIcon restartButtonIcon;
	private BufferedImage nyImage;

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
	private JButton standButton = new JButton();
	private JButton restartButton = new JButton();

	private JLabel poängtavleEtikett = new JLabel("score:");
	private JLabel poängtavla = new JLabel("0");

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
		
		dealButtonImage = findImage("Kort/Deal.png");
		standButtonImage = findImage("Kort/Stand.png");
		restartButtonImage = findImage("Kort/Restart.png");
		
		baksida = findImage("Kort/Baksida.png");
		baksidaIkon = new ImageIcon(baksida);

		dealButtonIcon = new ImageIcon(dealButtonImage);
		dealButton.setIcon(dealButtonIcon);
		standButtonIcon = new ImageIcon(standButtonImage);
		standButton.setIcon(standButtonIcon);
		restartButtonIcon = new ImageIcon(restartButtonImage);
		restartButton.setIcon(restartButtonIcon);
		kortPanel.setBackground(Color.BLUE);

		//setSize(800,100);
		add(regelPanel);
		add(kortPanel);
		add(knappanel);

		 // kortPanel.setPreferredSize(new Dimension(700, 400));
		 // regelPanel.setPreferredSize(new Dimension(700, 400));
		 // knappanel.setPreferredSize(new Dimension(700, 400));


		knappanel.setBackground(Color.BLUE);
		knappanel.add(dealButton);
		knappanel.add(standButton);
		knappanel.add(restartButton);

		dealButton.addActionListener(this);
		standButton.addActionListener(this);
		restartButton.addActionListener(this);
		restartButton.setEnabled(false);

		knappanel.add(poängtavleEtikett);
		knappanel.add(poängtavla);
		poängtavleEtikett.setFont(new Font("SansSerif", 0, 40));
		poängtavla.setFont(new Font("SansSerif", 0, 40));
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
				poäng += vp.kollaHand(vp.getSpelare(0));
				poängtavla.setText("" + poäng);
				for (JButton button : buttons) {
					button.removeActionListener(this);
			}
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
		// if (spelare.getHand().size() > 0) {
		p.reset();
		// }

		for (int i = 0; i < 5; i++) {
			p.addCardToHand(vp.getKortlek().draw());

		}

		// hämtar bilderna för spelarens hand
		for (int i = 0; i < p.getHand().size(); i++) {
			image = findImage("Kort/" + p.getHand().get(i).getSymbol() + p.getHand().get(i).getValue() + ".png");
			hand[i] = new ImageIcon(image);
		}
		for (JButton button : buttons) {
			button.setIcon(hand[Arrays.asList(buttons).indexOf(button)]);
			kortPanel.add(button);
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

}
