package com.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.Client.DbConnection;
import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class Welcome {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField_1;
	public int run = 1;
	private Thread thread;
	private Microphone mic;
	private GSpeechDuplex duplex;
	public String output = "";
	/**
	 * Launch the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */

	static {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		// public void run2() {
		try {
			Welcome window = new Welcome();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// }

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public Welcome() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param string
	 */

	public String Login() {

		String t = textField.getText();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "garmentprinting",
					"Megha");
			System.out.println("connected");

			PreparedStatement pstmt = con.prepareStatement("select * from garment where username=? and password=?");
			pstmt.setString(1, t);
			pstmt.setString(2, passwordField_1.getText());
			ResultSet rst = pstmt.executeQuery();
			if (rst.next()) {
				mic.close();
				duplex.stopSpeechRecognition();
				Dashboard dashboard = new Dashboard();
				dashboard.frame.setVisible(true);
				duplex.stopSpeechRecognition();
				mic.close();
				frame.dispose();

			} else {
				JOptionPane.showMessageDialog(null,
						"invalid username or password & now You have click On Login Button");

				duplex.stopSpeechRecognition();
				mic.close();

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void initialize() throws Exception {
		mic = new Microphone(FLACFileWriter.FLAC);
		// Don't use the below google api key , make your own !!! :)
		duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

		duplex.setLanguage("en");

		frame = new JFrame();
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(false);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setLocation(0, 0);
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnNewButton_1.setLocation(37, 546);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = JOptionPane.showConfirmDialog(null, "Are you want to exit?");
				if (i == 0) {
					System.exit(0);
				}
			}
		});
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnNewButton.setIcon(new ImageIcon(Welcome.class.getResource("logine.png")));

				// btnNewButton.setIcon(new ImageIcon(".\\images\\logine.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnNewButton.setIcon(new ImageIcon(Welcome.class.getResource("login.png")));

				// btnNewButton.setIcon(new ImageIcon(".\\images\\login.png"));
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mic.close();
				duplex.stopSpeechRecognition();
				String t = textField.getText();

				try {
					Connection con = DbConnection.getDBConnection();
					System.out.println("connected");

					PreparedStatement pstmt = con
							.prepareStatement("select * from garment where username=? and password=?");
					pstmt.setString(1, t);
					pstmt.setString(2, passwordField_1.getText());
					ResultSet rst = pstmt.executeQuery();
					if (rst.next()) {
						Dashboard dashboard = new Dashboard();
						dashboard.frame.setVisible(true);
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "invalid username or password");
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		JLabel lblNewLabel_1 = new JLabel("Forgot your password?");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(843, 439, 156, 16);
		lblNewLabel_1.setForeground(Color.red);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				ForgotPassword fg;
				try {
					fg = new ForgotPassword();
					fg.frame.setVisible(true);
					//

					frame.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		frame.getContentPane().add(lblNewLabel_1);
		btnNewButton
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(105, 105, 105), new Color(119, 136, 153)));
		btnNewButton.setBackground(new Color(105, 105, 105));
		btnNewButton.setIcon(new ImageIcon(Welcome.class.getResource("login.png")));
		// btnNewButton.setIcon(new ImageIcon(".\\images\\login.png"));
		btnNewButton.setBounds(525, 479, 169, 48);
		frame.getContentPane().add(btnNewButton);

		btnNewButton_1.setIcon(new ImageIcon(Welcome.class.getResource("exit.t.jpg")));
		// btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\exit.t.jpg"));
		btnNewButton_1.setSize(100, 100);
		frame.getContentPane().add(btnNewButton_1);

		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		textField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		textField.setBounds(651, 292, 348, 42);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.addMouseMotionListener(null);

		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					Login();
				}
			}
		});
		passwordField_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		passwordField_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		passwordField_1.setBounds(651, 387, 348, 42);
		frame.getContentPane().add(passwordField_1);

		lblNewLabel.setIcon(new ImageIcon(Welcome.class.getResource("unnamed.jpg")));
		lblNewLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, Color.WHITE));
		lblNewLabel.setSize(1280, 720);
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
				if (gr.getResponse().trim().equals("login")) {
					mic.close();
					duplex.stopSpeechRecognition();
					Login();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});

		// duplex.addResponseListener(new GSpeechResponseListener() {
		// String old_text = "";
		//
		// public void onResponse(GoogleResponse gr) {
		// String output = "";
		// output = gr.getResponse();
		// output.trim();
		// System.out.println(output);
		// if (output.equals("login")) {
		// duplex.stopSpeechRecognition();
		// mic.close();
		// gr.setFinalResponse(true);
		//
		//
		// duplex.stopSpeechRecognition();
		// System.out.println("hello123");
		// return;
		// }
		// return;
		// // response.setText("");
		// // response.append(this.old_text);
		// // response.append(output);
		// }
		//
		// });
	}

}