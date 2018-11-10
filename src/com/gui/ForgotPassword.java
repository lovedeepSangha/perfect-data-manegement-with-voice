package com.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ForgotPassword {

	JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public ForgotPassword() throws Exception {
		initialize();
	}

	/*public static void main(String[] args) throws Exception {
		ForgotPassword forgotPassword=new ForgotPassword();
		forgotPassword.frame.setVisible(true);
	}*/
	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	public void maillinga(int n) throws Exception {
		String to = "cuteehsaas1@gmail.com";

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
			}});

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

	private void initialize() throws Exception {

		Random rand = new Random();

		int randomNum = rand.nextInt((99999999 - 00000000) + 1) + 00000000;
		System.out.println(randomNum);

		maillinga(randomNum);

		frame = new JFrame("forgot password");
		frame.setSize(300, 300);
		frame.setLocation(500, 200);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("The new password has been sent to your mail.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(33, 111, 241, 20);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("Password changed");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(77, 89, 117, 26);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ForgotPassword.class.getResource("Untitled1.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled1.jpg"));
		lblNewLabel.setBounds(0, 0, 284, 262);
		frame.getContentPane().add(lblNewLabel);

	}
}
