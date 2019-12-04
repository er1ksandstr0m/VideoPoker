package videopoker.Tester.Peter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import org.json.JSONArray;
import org.json.JSONException;

public class savertest {

	JFrame frame;
	JComboBox<Integer> combo;
	JSONArray arr;
	JLabel label = new JLabel();

	public static void main(String[] args) {
		// http://data.goteborg.se/SelfServiceBicycleService/v1.0/Stations/40f6e365-e959-43d7-92be-964ee2f88a09?format=json
		new savertest();
		
	}

	public savertest() {
		frame = new JFrame();
		frame.setSize(850, 675);
		getData(1);

		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridx = 0;
		Integer[] stationIDs = new Integer[arr.length()];

		for (int i = 0; i < arr.length(); i++) {
			stationIDs[i] = (Integer) arr.getJSONObject(i).get("StationId");
		}
		combo = new JComboBox<Integer>(stationIDs);

		frame.add(combo);
		c.gridy = 1;
		updateLabel();
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLabel();
			}
		});
		
		frame.add(label);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void updateLabel() {
		combo.getSelectedItem();
		// Name AvailableBikes BikeStands
		String text = "";
		for (int i = 0; i < arr.length(); i++) {
			if ((Integer) arr.getJSONObject(i).get("StationId") == (Integer) combo.getSelectedItem()) {
				text = "<html><br>Namn: " + arr.getJSONObject(i).getString("Name") + "<br>Antal platser: "
						+ arr.getJSONObject(i).get("BikeStands") + "<br>Tillgängliga cyklar: ";
				try {
					text += arr.getJSONObject(i).get("AvailableBikes");
				} catch (JSONException e) {
					text += 0;
				}
			}
		}
		label.setText(text);

	}

	@SuppressWarnings("resource")
	public void getData(int id) {
		String url = "http://data.goteborg.se/SelfServiceBicycleService/v1.0/Stations/40f6e365-e959-43d7-92be-964ee2f88a09?format=json";
		String json = "";
		try {
			json = new Scanner(new URL(url).openStream(), "UTF-8").nextLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		arr = new JSONArray(json);

	}

}