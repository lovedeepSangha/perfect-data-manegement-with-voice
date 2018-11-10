package com.Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.gui.Client;

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class EditClient extends JFrame {

	protected static JComboBox ClientState = null;
	protected static JComboBox combobox = null;
	// private static final String DbConnection = null;
	int sn = 0;
	// private JFrame frame;
	public JFrame frame;
	private JTextField ClientNo;
	private JTextField ClientName;
	private JTextField ClientAddress;
	private JTextField ClientAddress1;
	private JTextField ClientPhnno;
	private JTextField ClientMobNo;
	private JTextField ClientEmailid;
	private JTextField ClientTinno;
	private JTextField CLientCity;
	ArrayList<String> l;
	private Thread thread;
	private Microphone mic;
	private GSpeechDuplex duplex;
	public String output = "";

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public static void main(String[] args) {
		EditClient client = new EditClient();
		client.frame.setVisible(true);
	}

	public EditClient() {
		// TODO Auto-generated constructor stub

		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 720);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		mic = new Microphone(FLACFileWriter.FLAC);
		// Don't use the below google api key , make your own !!! :)
		duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

		duplex.setLanguage("en");
		l = new ArrayList<String>();

		Connection con = DbConnection.getDBConnection();

		try {
			PreparedStatement ps = con.prepareStatement("SELECT clientname FROM clientreg",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				l.add(rs.getString("clientname"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//
				System.out.println("hello");
				boolean flag = true;

				String name = ClientName.getText();
				if (flag == true) {
					if (name.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter a Name");
						flag = false;
					} else {
						flag = true;
						for (int i = 0; i <= 9; i++) {
							if (name.indexOf(String.valueOf(i)) != -1) {
								JOptionPane.showMessageDialog(null, "Name Must Not be a Number");
								flag = false;
								break;
							}
						}
					}
				}

				String Address = ClientAddress.getText() + " " + ClientAddress1.getText();

				if (flag == true) {
					if (Address.trim().equals(""))

					{
						System.out.println("hello2");

						JOptionPane.showMessageDialog(null, "Please Enter an Address");
						flag = false;
					} else
						flag = true;
				}

				String state = (String) ClientState.getSelectedItem();
				if (flag == true) {
					if (state.equals("Select State")) {

						JOptionPane.showMessageDialog(null, "Please Choose State");
						flag = false;
					} else
						flag = true;
				}

				double ph = 0;
				String phns = ClientPhnno.getText().toString();
				if (flag == true) {

					if (ClientPhnno.getText().equals("") || ClientPhnno.getText().equals("0")) {
						// JOptionPane.showMessageDialog(null, "Please Enter a
						// Phone No.");
						flag = true;

					} else {
						Pattern pattern = Pattern.compile("[256][0-9][0-9][0-9][0-9][0-9][0-9]");
						Matcher matcher = pattern.matcher(phns);

						if (matcher.find() && matcher.group().equals(phns)) {
							ph = Double.parseDouble(ClientPhnno.getText());
							flag = true;
							// System.out.println("Done");
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid number");
							// System.out.println("wrng");
							flag = false;
						}
					}

				}
				double mob = 0;
				if (flag == true) {
					if (ClientMobNo.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "Please Enter a Mobile  No.");
						flag = false;
					} else {
						Pattern pattern = Pattern.compile("[789][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
						Matcher matcher = pattern.matcher(ClientMobNo.getText());

						if (matcher.find() && matcher.group().equals(ClientMobNo.getText())) {
							mob = Double.parseDouble(ClientMobNo.getText());
							flag = true;
							// System.out.println("Done");
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid number");
							// System.out.println("wrng");
							flag = false;
						}
					}
				}

				String email = ClientEmailid.getText();

				if (flag == true) {
					if (email.trim().equals("")) {

						JOptionPane.showMessageDialog(null, "Please Enter an Email");
						flag = false;
					} else {
						String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
						java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
						java.util.regex.Matcher m = p.matcher(email);
						if (m.find() && m.group().equals(ClientEmailid.getText())) {
							flag = true;
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid email");
							// System.out.println("wrng");
							flag = false;
						}

					}
				}

				double tin = 0;
				if (flag == true) {
					if (ClientTinno.getText().equals("") || ClientTinno.getText().equals("0")) {

						// JOptionPane.showMessageDialog(null, "Please Enter a
						// Tin No.");
						flag = true;
						// return;
					} else {

						flag = true;
						try {
							tin = Double.parseDouble(ClientTinno.getText());
						} catch (NumberFormatException ne) {
							JOptionPane.showMessageDialog(null, "Tin No. must be Numeric");
							flag = false;
						}
						if (ClientTinno.getText().length() > 15) {
							JOptionPane.showMessageDialog(null, "only 15 digits");
							flag = false;
						} else if (ClientTinno.getText().length() < 15) {
							JOptionPane.showMessageDialog(null, "Please enter 15 digits");
							flag = false;
						} else
							flag = true;
					}

				}

				String city = CLientCity.getText();
				if (flag == true) {
					if (city.trim().equals(""))

					{

						JOptionPane.showMessageDialog(null, "Please Enter a City");
						flag = false;
					} else {
						flag = true;
						for (int i = 0; i <= 20; i++) {
							if (name.indexOf(String.valueOf(i)) != -1) {

								JOptionPane.showMessageDialog(null, "City Must Not be a Number");
								flag = false;
								break;
							}
						}
					}
				}

				if (flag == true) {

					try {
						PreparedStatement ps = con.prepareStatement(
								"UPDATE clientreg SET clientname=?,address=?,state=?,Phnno=?,mobno=?,emailid=?,tinno=?,city=? where clientno=?");
						int sn = Integer.valueOf(ClientNo.getText());
						ps.setString(9, String.valueOf(sn));

						ps.setString(1, name);
						ps.setString(2, Address);
						ps.setString(3, state);
						ps.setDouble(4, ph);
						ps.setDouble(5, mob);
						ps.setString(6, email);
						ps.setDouble(7, tin);
						ps.setString(8, city); // execute query
						ps.executeUpdate();
						System.out.println("done");
						JOptionPane.showMessageDialog(null, " Details has been Changed");
						mic.close();
						duplex.stopSpeechRecognition();
						EditClient client = new EditClient();
						client.frame.setVisible(true);
						frame.dispose();
						con.close();

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setIcon(new ImageIcon(EditClient.class.getResource("submit effect.jpg")));
				// button.setIcon(new ImageIcon("D:\\my project\\home\\submit effect.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				button.setIcon(new ImageIcon(EditClient.class.getResource("submit.jpg")));
				// button.setIcon(new ImageIcon("D:\\my project\\home\\submit.jpg"));
			}
		});
		button.setIcon(new ImageIcon(EditClient.class.getResource("submit.jpg")));
		// button.setIcon(new ImageIcon("D:\\my project\\home\\submit.jpg"));
		button.setBounds(550, 582, 177, 77);
		frame.getContentPane().add(button);

		combobox = new JComboBox();
		combobox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						PreparedStatement ps = con.prepareStatement("SELECT * from clientreg where clientname=?");
						ps.setString(1, combobox.getSelectedItem().toString());

						ResultSet rst = ps.executeQuery();

						if (rst.next()) {
							ClientNo.setText(String.valueOf(rst.getInt("clientno")));
							ClientName.setText(rst.getString("clientname"));
							ClientAddress.setText(rst.getString("address"));
							ClientState.setSelectedItem(rst.getString("state"));
							ClientPhnno.setText(rst.getString("phnno"));
							ClientMobNo.setText(rst.getString("mobno"));

							ClientEmailid.setText(rst.getString("emailid"));
							ClientTinno.setText(rst.getString("tinno"));
							CLientCity.setText(rst.getString("city"));
						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		combobox.setBounds(574, 214, 183, 25);
		frame.getContentPane().add(combobox);
		combobox.addItem("Select One");
		for (String string : l) {
			combobox.addItem(string);
		}

		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					PreparedStatement ps = con.prepareStatement("SELECT * from clientreg where clientname=?");
					ps.setString(1, combobox.getSelectedItem().toString());

					ResultSet rst = ps.executeQuery();

					if (rst.next()) {
						ClientNo.setText(String.valueOf(rst.getInt("clientno")));
						ClientName.setText(rst.getString("clientname"));
						ClientAddress.setText(rst.getString("address"));
						ClientState.setSelectedItem(rst.getString("state"));
						ClientPhnno.setText(rst.getString("phnno"));
						ClientMobNo.setText(rst.getString("mobno"));

						ClientEmailid.setText(rst.getString("emailid"));
						ClientTinno.setText(rst.getString("tinno"));
						CLientCity.setText(rst.getString("city"));
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnNewButton.setIcon(null);
		btnNewButton.setBounds(798, 207, 124, 37);
		frame.getContentPane().add(btnNewButton);

		JLabel lblSearchClient = new JLabel("Search Client :-");
		lblSearchClient.setFont(new Font("Verdana", Font.BOLD, 20));
		lblSearchClient.setBounds(381, 207, 183, 37);
		frame.getContentPane().add(lblSearchClient);

		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mic.close();
				duplex.stopSpeechRecognition();
				Client c = new Client();
				c.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(EditClient.class.getResource("backbtsmall1.jpg")));
				// btnNewButton_1.setIcon(new ImageIcon("D:\\my
				// project\\home\\backbtsmall1.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(EditClient.class.getResource("backbt1.jpg")));
				// btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
			}
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setIcon(new ImageIcon(EditClient.class.getResource("backbt1.jpg")));
		// btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
		btnNewButton_1.setBounds(10, 601, 99, 70);
		frame.getContentPane().add(btnNewButton_1);

		String m[] = { "Select State", "Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh", "Assam"

				, "Bihar", "Chandigarh", "Chhattisgarh", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Goa",
				"Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala",
				"Lakshadweep", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Orissa",
				"Pondicherry", "Punjab", "Rajasthan", "Sikkim", "Tamilnadu", "Telangana", "Tripura", "Uttaranchal",
				"Uttar Pradesh", "West Bengal" };

		ClientState = new JComboBox(m);

		ClientState.setMaximumRowCount(28);
		ClientState.setBounds(205, 507, 312, 25);
		frame.getContentPane().add(ClientState);
		ClientAddress = new JTextField();
		ClientAddress.setBounds(205, 423, 312, 25);
		ClientAddress.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(ClientAddress);
		ClientAddress.setColumns(10);

		ClientAddress1 = new JTextField();
		ClientAddress1.setBounds(205, 459, 312, 25);
		ClientAddress1.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientAddress1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(ClientAddress1);
		ClientAddress1.setColumns(10);

		ClientPhnno = new JTextField();
		ClientPhnno.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientPhnno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientPhnno.setBounds(767, 327, 312, 25);
		frame.getContentPane().add(ClientPhnno);
		ClientPhnno.setColumns(10);

		ClientMobNo = new JTextField();
		ClientMobNo.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientMobNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientMobNo.setBounds(767, 375, 312, 25);
		frame.getContentPane().add(ClientMobNo);
		ClientMobNo.setColumns(10);

		ClientEmailid = new JTextField();
		ClientEmailid.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientEmailid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientEmailid.setBounds(767, 423, 312, 25);
		frame.getContentPane().add(ClientEmailid);
		ClientEmailid.setColumns(10);

		ClientTinno = new JTextField();
		ClientTinno.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientTinno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientTinno.setBounds(767, 472, 312, 25);
		frame.getContentPane().add(ClientTinno);
		ClientTinno.setColumns(10);

		CLientCity = new JTextField();
		CLientCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CLientCity.setColumns(10);
		CLientCity.setBorder(new LineBorder(Color.BLACK, 2, true));
		CLientCity.setBounds(767, 521, 312, 25);
		frame.getContentPane().add(CLientCity);

		ClientName = new JTextField();
		ClientName.setBounds(205, 375, 312, 25);
		ClientName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientName.setBorder(new LineBorder(Color.BLACK, 2, true));
		frame.getContentPane().add(ClientName);
		ClientName.setColumns(10);

		JLabel lblTinNo = new JLabel("Tin No:-");
		lblTinNo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblTinNo.setBounds(666, 470, 91, 25);
		frame.getContentPane().add(lblTinNo);

		JLabel lblEmailId = new JLabel("Email Id:-");
		lblEmailId.setFont(new Font("Verdana", Font.BOLD, 20));
		lblEmailId.setBounds(645, 421, 112, 25);
		frame.getContentPane().add(lblEmailId);

		JLabel lblMobileNo = new JLabel("Mobile No:-");
		lblMobileNo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblMobileNo.setBounds(627, 373, 130, 25);
		frame.getContentPane().add(lblMobileNo);

		JLabel lblPhoneNo = new JLabel("Phone No.:-");
		lblPhoneNo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblPhoneNo.setBounds(627, 325, 133, 25);
		frame.getContentPane().add(lblPhoneNo);

		JLabel lblCity_1 = new JLabel("State:-");
		lblCity_1.setBounds(119, 503, 76, 25);
		lblCity_1.setFont(new Font("Verdana", Font.BOLD, 20));
		frame.getContentPane().add(lblCity_1);

		JLabel lblCity = new JLabel("City:-");
		lblCity.setBounds(696, 519, 61, 25);
		lblCity.setFont(new Font("Verdana", Font.BOLD, 20));
		frame.getContentPane().add(lblCity);

		JLabel lblAddress = new JLabel("Address:-");
		lblAddress.setBounds(86, 421, 109, 25);
		lblAddress.setFont(new Font("Verdana", Font.BOLD, 20));
		frame.getContentPane().add(lblAddress);

		JLabel lblClientName = new JLabel("Client Name:-");
		lblClientName.setBounds(42, 376, 153, 19);
		lblClientName.setFont(new Font("Verdana", Font.BOLD, 20));
		frame.getContentPane().add(lblClientName);

		ClientNo = new JTextField();
		ClientNo.setEditable(false);
		ClientNo.setBounds(205, 327, 312, 25);

		ClientNo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		ClientNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientNo.setText(Integer.toString(sn));
		frame.getContentPane().add(ClientNo);
		ClientNo.setColumns(10);

		JLabel lblClientNo = new JLabel("Client No. :-");
		lblClientNo.setBounds(59, 316, 140, 43);
		lblClientNo.setFont(new Font("Verdana", Font.BOLD, 20));
		frame.getContentPane().add(lblClientNo);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 1264, 119);
		lblNewLabel_1.setIcon(new ImageIcon(EditClient.class.getResource("thread printing.png")));
		// lblNewLabel_1.setIcon(new ImageIcon("D:\\my project\\home\\thread
		// printing.png"));
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1423, 693);
		lblNewLabel.setBorder(null);
		lblNewLabel.setIcon(new ImageIcon(EditClient.class.getResource("Untitled 2.jpg")));
		// lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		frame.getContentPane().add(lblNewLabel);

		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
					// Do you task
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		thread.start();

		duplex.addResponseListener(new GSpeechResponseListener() {

			@Override
			public void onResponse(GoogleResponse gr) {

				System.out.println(gr.getResponse());

				switch (gr.getResponse().trim()) {
				case "search":

					try {
						PreparedStatement ps = con.prepareStatement("SELECT * from clientreg where clientname=?");
						ps.setString(1, combobox.getSelectedItem().toString());

						ResultSet rst = ps.executeQuery();

						if (rst.next()) {
							ClientNo.setText(String.valueOf(rst.getInt("clientno")));
							ClientName.setText(rst.getString("clientname"));
							ClientAddress.setText(rst.getString("address"));
							ClientState.setSelectedItem(rst.getString("state"));
							ClientPhnno.setText(rst.getString("phnno"));
							ClientMobNo.setText(rst.getString("mobno"));

							ClientEmailid.setText(rst.getString("emailid"));
							ClientTinno.setText(rst.getString("tinno"));
							CLientCity.setText(rst.getString("city"));
						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				case "submit":

					System.out.println("hello");
					boolean flag = true;

					String name = ClientName.getText();
					if (flag == true) {
						if (name.trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please Enter a Name");
							flag = false;
						} else {
							flag = true;
							for (int i = 0; i <= 9; i++) {
								if (name.indexOf(String.valueOf(i)) != -1) {
									JOptionPane.showMessageDialog(null, "Name Must Not be a Number");
									flag = false;
									break;
								}
							}
						}
					}

					String Address = ClientAddress.getText() + " " + ClientAddress1.getText();

					if (flag == true) {
						if (Address.trim().equals(""))

						{
							System.out.println("hello2");

							JOptionPane.showMessageDialog(null, "Please Enter an Address");
							flag = false;
						} else
							flag = true;
					}

					String state = (String) ClientState.getSelectedItem();
					if (flag == true) {
						if (state.equals("Select State")) {

							JOptionPane.showMessageDialog(null, "Please Choose State");
							flag = false;
						} else
							flag = true;
					}

					double ph = 0;
					String phns = ClientPhnno.getText().toString();
					if (flag == true) {

						if (ClientPhnno.getText().equals("") || ClientPhnno.getText().equals("0")) {
							// JOptionPane.showMessageDialog(null, "Please Enter a
							// Phone No.");
							flag = true;

						} else {
							Pattern pattern = Pattern.compile("[256][0-9][0-9][0-9][0-9][0-9][0-9]");
							Matcher matcher = pattern.matcher(phns);

							if (matcher.find() && matcher.group().equals(phns)) {
								ph = Double.parseDouble(ClientPhnno.getText());
								flag = true;
								// System.out.println("Done");
							} else {
								JOptionPane.showMessageDialog(null, "Please Enter an valid number");
								// System.out.println("wrng");
								flag = false;
							}
						}

					}
					double mob = 0;
					if (flag == true) {
						if (ClientMobNo.getText().equals("")) {

							JOptionPane.showMessageDialog(null, "Please Enter a Mobile  No.");
							flag = false;
						} else {
							Pattern pattern = Pattern.compile("[789][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
							Matcher matcher = pattern.matcher(ClientMobNo.getText());

							if (matcher.find() && matcher.group().equals(ClientMobNo.getText())) {
								mob = Double.parseDouble(ClientMobNo.getText());
								flag = true;
								// System.out.println("Done");
							} else {
								JOptionPane.showMessageDialog(null, "Please Enter an valid number");
								// System.out.println("wrng");
								flag = false;
							}
						}
					}

					String email = ClientEmailid.getText();

					if (flag == true) {
						if (email.trim().equals("")) {

							JOptionPane.showMessageDialog(null, "Please Enter an Email");
							flag = false;
						} else {
							String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
							java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
							java.util.regex.Matcher m = p.matcher(email);
							if (m.find() && m.group().equals(ClientEmailid.getText())) {
								flag = true;
							} else {
								JOptionPane.showMessageDialog(null, "Please Enter an valid email");
								// System.out.println("wrng");
								flag = false;
							}

						}
					}

					double tin = 0;
					if (flag == true) {
						if (ClientTinno.getText().equals("") || ClientTinno.getText().equals("0")) {

							// JOptionPane.showMessageDialog(null, "Please Enter a
							// Tin No.");
							flag = true;
							// return;
						} else {

							flag = true;
							try {
								tin = Double.parseDouble(ClientTinno.getText());
							} catch (NumberFormatException ne) {
								JOptionPane.showMessageDialog(null, "Tin No. must be Numeric");
								flag = false;
							}
							if (ClientTinno.getText().length() > 15) {
								JOptionPane.showMessageDialog(null, "only 15 digits");
								flag = false;
							} else if (ClientTinno.getText().length() < 15) {
								JOptionPane.showMessageDialog(null, "Please enter 15 digits");
								flag = false;
							} else
								flag = true;
						}

					}

					String city = CLientCity.getText();
					if (flag == true) {
						if (city.trim().equals(""))

						{

							JOptionPane.showMessageDialog(null, "Please Enter a City");
							flag = false;
						} else {
							flag = true;
							for (int i = 0; i <= 20; i++) {
								if (name.indexOf(String.valueOf(i)) != -1) {

									JOptionPane.showMessageDialog(null, "City Must Not be a Number");
									flag = false;
									break;
								}
							}
						}
					}

					if (flag == true) {

						try {
							PreparedStatement ps = con.prepareStatement(
									"UPDATE clientreg SET clientname=?,address=?,state=?,Phnno=?,mobno=?,emailid=?,tinno=?,city=? where clientno=?");
							int sn = Integer.valueOf(ClientNo.getText());
							ps.setString(9, String.valueOf(sn));

							ps.setString(1, name);
							ps.setString(2, Address);
							ps.setString(3, state);
							ps.setDouble(4, ph);
							ps.setDouble(5, mob);
							ps.setString(6, email);
							ps.setDouble(7, tin);
							ps.setString(8, city); // execute query
							ps.executeUpdate();
							System.out.println("done");
							JOptionPane.showMessageDialog(null, " Details has been Changed");
							con.close();
							mic.close();
							duplex.stopSpeechRecognition();
							EditClient client = new EditClient();
							client.frame.setVisible(true);
							frame.dispose();
							thread.join();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					break;

				case "back":
					mic.close();
					duplex.stopSpeechRecognition();
					Client client = new Client();
					client.frame.setVisible(true);

					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				default:
					System.out.println("default");
					break;
				}

			}

		});

	}
}
