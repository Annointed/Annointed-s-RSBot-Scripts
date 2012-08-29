package org.annointed.scripts.anairrunner;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.annointed.scripts.anairrunner.constants.Constants;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.bot.event.listener.PaintListener;

@Manifest(authors = { "Annointed" }, name = "AnAirRunner", description = "Read the GUI", version = 1.0, vip = true)
public class AnAirRunner extends ActiveScript implements PaintListener {

	AnAirRunnerGUI gui;

	@Override
	protected void setup() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = new AnAirRunnerGUI();
				gui.setVisible(true);
			}
		});

		provide(new AntiBan());
		provide(new MasterScript());
		provide(new SlaveScript());
		provide(new Trader());
		provide(new WalkToBank());
		provide(new Banker());
	}

	// Credits to Coma
	public static int getPrice(int id) {
		try {
			String price;
			URL url = new URL("http://open.tip.it/json/ge_single_item?item="
					+ id);
			URLConnection con = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				if (line.contains("mark_price")) {
					price = line.substring(line.indexOf("mark_price") + 13,
							line.indexOf(",\"daily_gp") - 1);
					price = price.replace(",", "");
					return Integer.parseInt(price);

				}
			}
		} catch (Exception e) {
			return -1;
		}
		return -1;
	}

	@Override
	public void onRepaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHints(Constants.ANTIALIASING);
		g.drawLine(Mouse.getX() - 6, Mouse.getY(), Mouse.getX() + 6,
				Mouse.getY());
		g.drawLine(Mouse.getX(), Mouse.getY() - 6, Mouse.getX(),
				Mouse.getY() + 6);
	}

	public class AnAirRunnerGUI extends JFrame {

		private static final long serialVersionUID = 1L;

		public AnAirRunnerGUI() {
			initComponents();
		}

		private void startButtonActionPerformed(ActionEvent e) {
			Constants.masterScript = checkBox1.isSelected();
			Constants.slaveScript = checkBox2.isSelected();
			Constants.mainName = textField1.getText();
			Constants.slaveName = textField2.getText();

			Constants.guiWait = false;
			gui.setVisible(false);
			gui.dispose();
		}

		private void exitButtonActionPerformed(ActionEvent e) {
			Constants.guiWait = false;
			gui.dispose();
		}

		private void initComponents() {
			// //GEN-BEGIN:initComponents
			label1 = new JLabel();
			label2 = new JLabel();
			label3 = new JLabel();
			label4 = new JLabel();
			label5 = new JLabel();
			label6 = new JLabel();
			label7 = new JLabel();
			label8 = new JLabel();
			checkBox1 = new JCheckBox();
			textField1 = new JTextField();
			label9 = new JLabel();
			checkBox2 = new JCheckBox();
			textField2 = new JTextField();
			startButton = new JButton();
			exitButton = new JButton();

			// ======== this ========
			setTitle("AnAirRunner GUIs");
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setResizable(false);
			Container contentPane = getContentPane();
			contentPane.setLayout(null);

			// ---- label1 ----
			label1.setText("AnAirRunner");
			label1.setFont(new Font("Bauhaus 93", Font.BOLD, 24));
			contentPane.add(label1);
			label1.setBounds(new Rectangle(new Point(160, 10), label1
					.getPreferredSize()));

			// ---- label2 ----
			label2.setText("Instructions: First, open 2 Tabs, run AnAirRunner on both.");
			contentPane.add(label2);
			label2.setBounds(new Rectangle(new Point(25, 50), label2
					.getPreferredSize()));

			// ---- label3 ----
			label3.setText("Then in the first tab, enter the Main Account's username into the textfield");
			contentPane.add(label3);
			label3.setBounds(new Rectangle(new Point(25, 65), label3
					.getPreferredSize()));

			// ---- label4 ----
			label4.setText("and start him INSIDE the Air Altar. Now in the Second tab, put your Slave");
			contentPane.add(label4);
			label4.setBounds(new Rectangle(new Point(25, 80), label4
					.getPreferredSize()));

			// ---- label5 ----
			label5.setText("username beside the textfield for Slave's Account, and equip an Air TIARA");
			contentPane.add(label5);
			label5.setBounds(new Rectangle(new Point(25, 95), label5
					.getPreferredSize()));

			// ---- label6 ----
			label6.setText("and then take out 28 Rune Essence, and also start him INSIDE of the Air");
			contentPane.add(label6);
			label6.setBounds(new Rectangle(new Point(25, 110), label6
					.getPreferredSize()));

			// ---- label7 ----
			label7.setText("Altar.");
			contentPane.add(label7);
			label7.setBounds(new Rectangle(new Point(25, 125), label7
					.getPreferredSize()));

			// ---- label8 ----
			label8.setText("Check it if you're using Main Account on this tab:");
			label8.setFont(new Font("Tahoma", Font.BOLD, 8));
			contentPane.add(label8);
			label8.setBounds(new Rectangle(new Point(25, 150), label8
					.getPreferredSize()));
			contentPane.add(checkBox1);
			checkBox1.setBounds(new Rectangle(new Point(215, 145), checkBox1
					.getPreferredSize()));

			// ---- textField1 ----
			textField1
					.setText("Type Main Account username if the box is checked");
			textField1.setFont(new Font("Tahoma", Font.BOLD, 8));
			contentPane.add(textField1);
			textField1.setBounds(new Rectangle(new Point(240, 150), textField1
					.getPreferredSize()));

			// ---- label9 ----
			label9.setText("Check it if you're using Slave Account on this tab:");
			label9.setFont(new Font("Tahoma", Font.BOLD, 8));
			contentPane.add(label9);
			label9.setBounds(new Rectangle(new Point(25, 170), label9
					.getPreferredSize()));
			contentPane.add(checkBox2);
			checkBox2.setBounds(new Rectangle(new Point(215, 165), checkBox2
					.getPreferredSize()));

			// ---- textField2 ----
			textField2
					.setText("Type Slave Account username if the box is checked");
			textField2.setFont(new Font("Tahoma", Font.BOLD, 8));
			contentPane.add(textField2);
			textField2.setBounds(new Rectangle(new Point(240, 170), textField2
					.getPreferredSize()));

			// ---- startButton ----
			startButton.setText("Start");
			startButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					startButtonActionPerformed(e);
				}
			});
			contentPane.add(startButton);
			startButton.setBounds(new Rectangle(new Point(65, 215), startButton
					.getPreferredSize()));

			// ---- exitButton ----
			exitButton.setText("Exit");
			exitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					exitButtonActionPerformed(e);
				}
			});
			contentPane.add(exitButton);
			exitButton.setBounds(new Rectangle(new Point(310, 215), exitButton
					.getPreferredSize()));

			contentPane.setPreferredSize(new Dimension(475, 300));
			pack();
			setLocationRelativeTo(getOwner());
			// //GEN-END:initComponents
		}

		// //GEN-BEGIN:variables
		private JLabel label1;
		private JLabel label2;
		private JLabel label3;
		private JLabel label4;
		private JLabel label5;
		private JLabel label6;
		private JLabel label7;
		private JLabel label8;
		private JCheckBox checkBox1;
		private JTextField textField1;
		private JLabel label9;
		private JCheckBox checkBox2;
		private JTextField textField2;
		private JButton startButton;
		private JButton exitButton;
		// GEN-END:variables
	}

}
