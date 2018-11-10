package com.ManageAccount;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.Client.DbConnection;
import com.gui.ManageAccount;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassword {

	public JFrame frame;
	private JTextField Lusername;
	private JTextField Loldpass;
	private JTextField Lemailid;
	private JTextField Lnewpass;
	private JTextField Lnewpass1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public ChangePassword() {
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
			public void actionPerformed(ActionEvent e) {

				System.out.println("hello");
				boolean flag = true;

				String name = Lusername.getText();
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

				String oldpass = String.valueOf(Loldpass.getText());
				if (flag == true) {
					if (oldpass.trim().equals(""))

					{
						System.out.println("hello2");

						JOptionPane.showMessageDialog(null, "Please Enter Old Passowrd");
						flag = false;
					} else
						flag = true;
				}

				String email = Lemailid.getText();

				if (flag == true) {
					if (email.trim().equals("")) {

						JOptionPane.showMessageDialog(null, "Please Enter an Email");
						flag = false;
					} else {
						String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
						java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
						java.util.regex.Matcher m = p.matcher(email);
						if (m.find() && m.group().equals(Lemailid.getText())) {
							flag = true;
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid email");
							// System.out.println("wrng");
							flag = false;
						}

					}
				}

				String pass1 = String.valueOf(Lnewpass.getText());

				if (flag == true) {
					if (pass1.trim().equals("")) {

						JOptionPane.showMessageDialog(null, "Please Enter an new Passowrd");
						flag = false;
					} else {
						String ePattern1 = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[^\\s]{8,}$";
						java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern1);
						java.util.regex.Matcher m = p.matcher(pass1);
						if (m.find() && m.group().equals(pass1)) {
							flag = true;
						} else {
							JOptionPane.showMessageDialog(null,
									"must contains one lowercase,uppercase characters,special symbols & one digit");
							System.out.println("wrng");
							flag = false;
						}

					}
				}
				String pass2 = String.valueOf(Lnewpass1.getText());
				if (flag == true) {
					if (pass2.trim().equals("")) {

						JOptionPane.showMessageDialog(null, "Please Enter an new Passowrd");
						System.out.println("hello1");
						flag = false;
					} else {
						String ePattern2 = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[^\\s]{8,}$";
						java.util.regex.Pattern p1 = java.util.regex.Pattern.compile(ePattern2);
						java.util.regex.Matcher m1 = p1.matcher(pass2);
						if (m1.find() && m1.group().equals(pass2)) {
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
					if (pass1.equals(pass2)) {

						flag = true;
					} else {
						JOptionPane.showMessageDialog(null, "New password does not matched");
						flag = false;
					}
				}
				if (flag == true) {
					try {
						PreparedStatement ps = con.prepareStatement("SELECT * from garment");
						ResultSet rs = ps.executeQuery();
								
						
						
				while (rs.next()) {
							 System.out.println(rs.getString("username"));
							if (rs.getString("username").equals(name) && rs.getString("password").equals(oldpass)
									&& rs.getString("email").equals(email)) {
								rs.close();
								PreparedStatement pst = con
										.prepareStatement("update garment set password =? where username=?");
								pst.setString(1, pass1);
								pst.setString(2, name);
								pst.executeQuery();
								System.out.println("ok");
								break;
							}

						} 

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton.setIcon(new ImageIcon(ChangePassword.class.getResource("submit.jpg")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\submit.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton.setIcon(new ImageIcon(ChangePassword.class.getResource("submit effect.jpg")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\submit effect.jpg"));
			}
		});
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageAccount ma= new ManageAccount();
				ma.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(ChangePassword.class.getResource("backbtsmall1.jpg")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\backbtsmall1.jpg"));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(ChangePassword.class.getResource("backbt1.jpg")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));

			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ChangePassword.class.getResource("backbt1.jpg")));
		//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
		btnNewButton_1.setBounds(53, 526, 98, 77);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton.setIcon(new ImageIcon(ChangePassword.class.getResource("submit.jpg")));
		//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\submit.jpg"));
		btnNewButton.setBounds(646, 526, 175, 77);
		frame.getContentPane().add(btnNewButton);

		Lnewpass1 = new JPasswordField();
		Lnewpass1.setBorder(new LineBorder(Color.BLACK, 2, true));
		Lnewpass1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Lnewpass1.setBounds(648, 403, 312, 26);
		frame.getContentPane().add(Lnewpass1);
		Lnewpass1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Retype new password:-");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_2.setBounds(372, 401, 266, 26);
		frame.getContentPane().add(lblNewLabel_2);

		Lnewpass = new JPasswordField();
		Lnewpass.setBorder(new LineBorder(Color.BLACK, 2, true));
		Lnewpass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Lnewpass.setBounds(648, 351, 312, 26);
		frame.getContentPane().add(Lnewpass);
		Lnewpass.setColumns(10);

		JLabel lblNewPassword = new JLabel("New password:-");
		lblNewPassword.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewPassword.setBounds(454, 349, 184, 26);
		frame.getContentPane().add(lblNewPassword);

		Lemailid = new JTextField();
		Lemailid.setBorder(new LineBorder(Color.BLACK, 2, true));
		Lemailid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Lemailid.setBounds(648, 303, 312, 26);
		frame.getContentPane().add(Lemailid);
		Lemailid.setColumns(10);

		JLabel lblEmailId = new JLabel("Email id:-");
		lblEmailId.setFont(new Font("Verdana", Font.BOLD, 20));
		lblEmailId.setBounds(530, 301, 108, 26);
		frame.getContentPane().add(lblEmailId);

		Loldpass = new JPasswordField();
		Loldpass.setBorder(new LineBorder(Color.BLACK, 2, true));
		Loldpass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Loldpass.setBounds(648, 250, 312, 26);
		frame.getContentPane().add(Loldpass);
		Loldpass.setColumns(10);

		JLabel lblOldPassword = new JLabel("Old password:-");
		lblOldPassword.setFont(new Font("Verdana", Font.BOLD, 20));
		lblOldPassword.setBounds(468, 248, 175, 26);
		frame.getContentPane().add(lblOldPassword);

		Lusername = new JTextField();
		Lusername.setBorder(new LineBorder(Color.BLACK, 2, true));
		Lusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Lusername.setBounds(648, 202, 312, 26);
		frame.getContentPane().add(Lusername);
		Lusername.setColumns(10);

		JLabel lblUsername = new JLabel("Username:-");
		lblUsername.setFont(new Font("Verdana", Font.BOLD, 20));
		lblUsername.setBounds(508, 200, 130, 26);
		frame.getContentPane().add(lblUsername);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ChangePassword.class.getResource("thread printing.png")));
		//lblNewLabel_1.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
		lblNewLabel_1.setBounds(0, 0, 1257, 119);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(ChangePassword.class.getResource("Untitled 2.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		lblNewLabel.setBounds(0, 0, 1462, 818);
		frame.getContentPane().add(lblNewLabel);
	}
}
