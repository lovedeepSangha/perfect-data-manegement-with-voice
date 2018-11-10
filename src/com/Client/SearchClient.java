package com.Client;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchClient extends JFrame {
	public static void main(String[] args) {
		SearchClient client = new SearchClient();
		client.frame.setVisible(true);
	}

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

	public SearchClient() {
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

		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(SearchClient.class.getResource("backbtsmall1.jpg")));
				// btnNewButton_1.setIcon(new ImageIcon("D:\\my
				// project\\home\\backbtsmall1.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(SearchClient.class.getResource("backbt1.jpg")));
				// btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mic.close();
				duplex.stopSpeechRecognition();
				Client c = new Client();
				c.frame.setVisible(true);
				frame.dispose();

			}
		});
		btnNewButton_1.setIcon(new ImageIcon(SearchClient.class.getResource("backbt1.jpg")));
		// btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
		btnNewButton_1.setBounds(10, 601, 99, 70);
		frame.getContentPane().add(btnNewButton_1);

		combobox = new JComboBox();
		combobox.setBounds(574, 214, 183, 25);
		frame.getContentPane().add(combobox);
		combobox.addItem("Select One");
		for (String string : l) {
			combobox.addItem(string);
		}

		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
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
		btnNewButton.setBounds(801, 207, 109, 37);
		frame.getContentPane().add(btnNewButton);

		JLabel lblSearchClient = new JLabel("Search Client :-");
		lblSearchClient.setFont(new Font("Verdana", Font.BOLD, 20));
		lblSearchClient.setBounds(381, 207, 183, 37);
		frame.getContentPane().add(lblSearchClient);

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
		ClientAddress.setEditable(false);
		ClientAddress.setBounds(205, 423, 312, 25);
		ClientAddress.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(ClientAddress);
		ClientAddress.setColumns(10);

		ClientAddress1 = new JTextField();
		ClientAddress1.setEditable(false);
		ClientAddress1.setBounds(205, 459, 312, 25);
		ClientAddress1.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientAddress1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(ClientAddress1);
		ClientAddress1.setColumns(10);

		ClientPhnno = new JTextField();
		ClientPhnno.setEditable(false);
		ClientPhnno.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientPhnno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientPhnno.setBounds(767, 327, 312, 25);
		frame.getContentPane().add(ClientPhnno);
		ClientPhnno.setColumns(10);

		ClientMobNo = new JTextField();
		ClientMobNo.setEditable(false);
		ClientMobNo.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientMobNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientMobNo.setBounds(767, 375, 312, 25);
		frame.getContentPane().add(ClientMobNo);
		ClientMobNo.setColumns(10);

		ClientEmailid = new JTextField();
		ClientEmailid.setEditable(false);
		ClientEmailid.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientEmailid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientEmailid.setBounds(767, 423, 312, 25);
		frame.getContentPane().add(ClientEmailid);
		ClientEmailid.setColumns(10);

		ClientTinno = new JTextField();
		ClientTinno.setEditable(false);
		ClientTinno.setBorder(new LineBorder(Color.BLACK, 2, true));
		ClientTinno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientTinno.setBounds(767, 472, 312, 25);
		frame.getContentPane().add(ClientTinno);
		ClientTinno.setColumns(10);

		CLientCity = new JTextField();
		CLientCity.setEditable(false);
		CLientCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CLientCity.setColumns(10);
		CLientCity.setBorder(new LineBorder(Color.BLACK, 2, true));
		CLientCity.setBounds(767, 521, 312, 25);
		frame.getContentPane().add(CLientCity);

		ClientName = new JTextField();
		ClientName.setEditable(false);
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
		lblCity_1.setBounds(119, 507, 76, 25);
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
		lblClientNo.setBounds(59, 319, 140, 36);
		lblClientNo.setFont(new Font("Verdana", Font.BOLD, 20));
		frame.getContentPane().add(lblClientNo);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 1264, 119);
		lblNewLabel_1.setIcon(new ImageIcon(SearchClient.class.getResource("thread printing.png")));
		// lblNewLabel_1.setIcon(new ImageIcon("D:\\my project\\home\\thread
		// printing.png"));
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1423, 693);
		lblNewLabel.setBorder(null);
		lblNewLabel.setIcon(new ImageIcon(SearchClient.class.getResource("Untitled 2.jpg")));
		// lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		frame.getContentPane().add(lblNewLabel);

		// #################voice recogonotion ??#######
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
