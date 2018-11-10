package com.gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.ManageAccount.AddUser;
import com.ManageAccount.ChangePassword;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageAccount {

	 public JFrame frame;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public ManageAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	 void initialize() {
		frame = new JFrame();
		frame.setLocation(200, 100);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton bt1 = new JButton("");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dashboard=new Dashboard();
				dashboard.frame.setVisible(true);
				frame.dispose();
			}
		});
		bt1.setContentAreaFilled(false);
		bt1.setFocusPainted(false);
		bt1.setBorderPainted(false);
		bt1.setBorder(null);
		bt1.setOpaque(true);
		bt1.setIcon(new ImageIcon(ManageAccount.class.getResource("home.jpg")));
		//bt1.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
		bt1.setLocation(10, 11);
		bt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				bt1.setIcon(new ImageIcon(ManageAccount.class.getResource("home1.jpg")));
				//bt1.setIcon(new ImageIcon("D:\\my project\\home\\home1.jpg"));
					
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bt1.setIcon(new ImageIcon(ManageAccount.class.getResource("home.jpg")));
				//bt1.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
			}
			
		});
				
				JButton btnNewButton_2 = new JButton("");
				btnNewButton_2.setBorder(null);
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						AddUser au=new AddUser();
						au.frame.setVisible(true);
						frame.dispose();
					}
				});
				btnNewButton_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {
						btnNewButton_2.setIcon(new ImageIcon(ManageAccount.class.getResource("addaccount12.jpg")));
						//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\addaccount12.jpg"));
					}
					@Override
					public void mouseExited(MouseEvent arg0) {
						btnNewButton_2.setIcon(new ImageIcon(ManageAccount.class.getResource("addaccount11.jpg")));
						//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\addaccount11.jpg"));
					}
				});
				btnNewButton_2.setIcon(new ImageIcon(ManageAccount.class.getResource("addaccount11.jpg")));
			//	btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\addaccount11.jpg"));
				btnNewButton_2.setBounds(45, 164, 142, 168);
				frame.getContentPane().add(btnNewButton_2);
				
				JButton btnNewButton_1 = new JButton("");
				btnNewButton_1.setBorder(null);
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ChangePassword cp=new ChangePassword();
						cp.frame.setVisible(true);
						frame.dispose();
					}
				});
				btnNewButton_1.setIcon(new ImageIcon(ManageAccount.class.getResource("changepassword1.png")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\changepassword1.png"));
				btnNewButton_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {
						btnNewButton_1.setIcon(new ImageIcon(ManageAccount.class.getResource("changepassword.jpg")));
						//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\changepassword.jpg"));
					}
					@Override
					public void mouseExited(MouseEvent arg0) {
						btnNewButton_1.setIcon(new ImageIcon(ManageAccount.class.getResource("changepassword1.png")));
						//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\changepassword1.png"));
					}
				});
				
				btnNewButton_1.setBounds(221, 282, 237, 145);
				frame.getContentPane().add(btnNewButton_1);
		
				bt1.setSize(100,100);
				frame.getContentPane().add(bt1);
		
		JLabel lblNewLabel  = new JLabel("");
		lblNewLabel.setLocation(0, 0);
		lblNewLabel.setIcon(new ImageIcon(ManageAccount.class.getResource("Untitled1.jpg")));
	     //lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled1.jpg"));
		lblNewLabel.setSize(500,500);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(60, 208, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
