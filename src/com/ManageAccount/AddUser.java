package com.ManageAccount;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.Client.DbConnection;
import com.Client.EditClient;
import com.gui.ManageAccount;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUser {

	public JFrame frame;
	private JTextField name;
	private JTextField username;
	private JTextField pass1;
	private JTextField pass2;
	private JTextField Email1;
	private JTextField Email2;

	public void maillinga(int n, String m) throws Exception {
		String to = m;

		String from = "meghanarang222@gmail.com";
		final String username = "meghanarang222";
		final String password = "Megha2131";

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Testing Subject");

			message.setText(Integer.toString(n));

			Transport.send(message);
			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Launch the application.
	 */
		/**
	 * Create the application.
	 */
	public AddUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Connection con = DbConnection.getDBConnection();
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean flag = true;

				String name1 = name.getText();
				if (flag == true) {
					if (name1.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter a Name");
						flag = false;
					} else {
						flag = true;
						for (int i = 0; i <= 9; i++) {
							if (name1.indexOf(String.valueOf(i)) != -1) {
								JOptionPane.showMessageDialog(null, "Name Must Not be a Number");
								flag = false;
								break;
							}
						}
					}
				}

				String name2 = username.getText();
				if (flag == true) {
					if (name2.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter a Name");
						flag = false;
					} else {
						flag = true;
						for (int i = 0; i <= 9; i++) {
							if (name2.indexOf(String.valueOf(i)) != -1) {
								JOptionPane.showMessageDialog(null, "Name Must Not be a Number");
								flag = false;
								break;
							}
						}
					}
				}

				String password1 = String.valueOf(pass1.getText());

				if (flag == true) {
					if (password1.trim().equals("")) {

						JOptionPane.showMessageDialog(null, "Please Enter an new Passowrd");
						flag = false;
					} else {
						String ePattern1 = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[^\\s]{8,}$";
						java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern1);
						java.util.regex.Matcher m = p.matcher(password1);
						if (m.find() && m.group().equals(password1)) {
							flag = true;
						} else {
							JOptionPane.showMessageDialog(null,
									"must contains one lowercase,uppercase characters,special symbols & one digit");
							System.out.println("wrng");
							flag = false;
						}

					}
				}
				String password2 = String.valueOf(pass2.getText());
				if (flag == true) {
					if (password2.trim().equals("")) {

						JOptionPane.showMessageDialog(null, "Please Enter Confirm Passowrd");
						System.out.println("hello1");
						flag = false;
					} else {
						String ePattern2 = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[^\\s]{8,}$";
						java.util.regex.Pattern p1 = java.util.regex.Pattern.compile(ePattern2);
						java.util.regex.Matcher m1 = p1.matcher(password2);
						if (m1.find() && m1.group().equals(password2)) {
							flag = true;
						} else {
							JOptionPane.showMessageDialog(null,
									"must contains one lowercase,uppercase characters,special symbols & one digit");
							// System.out.println("wrng");
							flag = false;
						}

					}
				}

				if (flag == true) {
					if (password1.equals(password2)) {

						flag = true;
					} else {
						JOptionPane.showMessageDialog(null, " Password does not matched");
						flag = false;
					}
				}

				String email1 = Email1.getText();

				if (flag == true) {
					if (email1.trim().equals("")) {

						JOptionPane.showMessageDialog(null, "Please Enter an Email");
						flag = false;
					} else {
						String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
						java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
						java.util.regex.Matcher m = p.matcher(email1);
						if (m.find() && m.group().equals(Email1.getText())) {
							flag = true;
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid email");
							// System.out.println("wrng");
							flag = false;
						}

					}
				}

				String email2 = Email2.getText();

				if (flag == true) {
					if (email2.trim().equals("")) {

						JOptionPane.showMessageDialog(null, "Please Enter Confirm Email");
						flag = false;
					} else {
						String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
						java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
						java.util.regex.Matcher m = p.matcher(email2);
						if (m.find() && m.group().equals(Email2.getText())) {
							flag = true;
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid email");
							// System.out.println("wrng");
							flag = false;
						}

					}
				}

				if (flag == true) {
					if (email1.equals(email2)) {

						flag = true;
					} else {
						JOptionPane.showMessageDialog(null, "Email does not matched");
						flag = false;
					}
				}

				if (flag == true) {
					try {
						PreparedStatement ps = con.prepareStatement("SELECT * from garment");
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							System.out.println(rs.getString("username"));
							;
							if (rs.getString("username").equals(name2) && rs.getString("email").equals(email1)) {
								JOptionPane.showMessageDialog(null, "User already exists or email already exits");
								flag = false;
							}
						}
						if (flag == true) {

							Random rand = new Random();

							int randomNum = rand.nextInt((9999 - 0000) + 1) + 0000;
							maillinga(randomNum, email1);
							String check = JOptionPane.showInputDialog("Enter Security code");
							String Check1 = String.valueOf(randomNum);
							if (Check1.equals(check) != true) {
								for (int i = 0; i < 5; i++) {
									check = JOptionPane.showInputDialog("Enter Valid Security code");
									if (Check1.equals(check) == true) {

										PreparedStatement pst = con
												.prepareStatement("insert into garment values(?,?,?)");
										pst.setString(1, name1);
										pst.setString(2, password1);
										pst.setString(3, email1);
										pst.executeQuery();
										System.out.println("Done");
										JOptionPane.showMessageDialog(null, "User Registered");
										name.setText("");
										Email1.setText("");
										Email2.setText("");
										pass1.setText("");
										pass2.setText("");
										username.setText("");
										break;
									}
								}
								if(Check1.equals(check) != true) {
									JOptionPane.showMessageDialog(null, "Try again later");
								}
								System.out.println("done1");
							} else {
								PreparedStatement pst = con.prepareStatement("insert into garment values(?,?,?)");
								pst.setString(1, name1);
								pst.setString(2, password1);
								pst.setString(3, email1);
								pst.executeQuery(); 
								System.out.println("Done");
								JOptionPane.showMessageDialog(null, "User Registered");
								name.setText("");
								Email1.setText("");
								Email2.setText("");
								pass1.setText("");
								pass2.setText("");
								username.setText("");

							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton.setIcon(new ImageIcon(AddUser.class.getResource("submit effect.jpg")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\submit effect.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon(AddUser.class.getResource("submit.jpg")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\submit.jpg"));
			}
		});
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageAccount ma=new ManageAccount();
				ma.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(AddUser.class.getResource("backbtsmall1.jpg")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\backbtsmall1.jpg"));

			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(AddUser.class.getResource("backbt1.jpg")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));

			}
		});
		btnNewButton_1.setIcon(new ImageIcon(AddUser.class.getResource("backbt1.jpg")));
	//	btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
		btnNewButton_1.setBounds(75, 531, 100, 71);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton.setIcon(new ImageIcon(AddUser.class.getResource("submit.jpg")));
	//	btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\submit.jpg"));
		btnNewButton.setBounds(587, 565, 177, 77);
		frame.getContentPane().add(btnNewButton);

		Email2 = new JTextField();
		Email2.setBorder(new LineBorder(Color.BLACK, 2, true));
		Email2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Email2.setBounds(605, 439, 312, 26);
		frame.getContentPane().add(Email2);
		Email2.setColumns(10);

		JLabel lblConfirmId = new JLabel("Confirm id:-");
		lblConfirmId.setFont(new Font("Verdana", Font.BOLD, 20));
		lblConfirmId.setBounds(458, 437, 134, 26);
		frame.getContentPane().add(lblConfirmId);

		Email1 = new JTextField();
		Email1.setBorder(new LineBorder(Color.BLACK, 2, true));
		Email1.setBounds(605, 386, 312, 26);
		frame.getContentPane().add(Email1);
		Email1.setColumns(10);

		JLabel lblEmailId = new JLabel("Email id:-");
		lblEmailId.setFont(new Font("Verdana", Font.BOLD, 20));
		lblEmailId.setBounds(477, 382, 108, 26);
		frame.getContentPane().add(lblEmailId);

		pass2 = new JPasswordField();
		pass2.setBorder(new LineBorder(Color.BLACK, 2, true));
		pass2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pass2.setBounds(605, 330, 312, 26);
		frame.getContentPane().add(pass2);
		pass2.setColumns(10);

		JLabel lblConfirmPassword = new JLabel("Confirm password:-");
		lblConfirmPassword.setFont(new Font("Verdana", Font.BOLD, 20));
		lblConfirmPassword.setBounds(362, 328, 233, 26);
		frame.getContentPane().add(lblConfirmPassword);

		pass1 = new JPasswordField();
		pass1.setBorder(new LineBorder(Color.BLACK, 2, true));
		pass1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pass1.setBounds(605, 283, 312, 26);
		frame.getContentPane().add(pass1);
		pass1.setColumns(10);

		JLabel lblPassword = new JLabel("Password:-");
		lblPassword.setFont(new Font("Verdana", Font.BOLD, 20));
		lblPassword.setBounds(458, 281, 130, 26);
		frame.getContentPane().add(lblPassword);

		username = new JTextField();
		username.setBorder(new LineBorder(Color.BLACK, 2, true));
		username.setFont(new Font("Tahoma", Font.PLAIN, 16));
		username.setBounds(605, 232, 312, 26);
		frame.getContentPane().add(username);
		username.setColumns(10);

		JLabel lblUsername = new JLabel("Username:-");
		lblUsername.setFont(new Font("Verdana", Font.BOLD, 20));
		lblUsername.setBounds(458, 230, 130, 26);
		frame.getContentPane().add(lblUsername);

		name = new JTextField();
		name.setBorder(new LineBorder(Color.BLACK, 2, true));
		name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		name.setBounds(605, 185, 312, 26);
		frame.getContentPane().add(name);
		name.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Name:-");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_1.setBounds(505, 183, 101, 26);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel label = new JLabel("");
    	label.setIcon(new ImageIcon(AddUser.class.getResource("thread printing.png")));
		//label.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
		label.setBounds(0, 0, 1264, 122);
		frame.getContentPane().add(label);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AddUser.class.getResource("Untitled 2.jpg")));

		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		lblNewLabel.setBounds(0, 0, 1280, 720);
		frame.getContentPane().add(lblNewLabel);
	}

}
