package com.gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.Credits.Pendingpayments;
import com.Credits.PerDayEmployee;
import com.attendence.attendence;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Credits {

	 public JFrame frame;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Credits() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	 void initialize() {
		frame = new JFrame();
		frame.setLocation(250, 100);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		bt1.setLocation(21, 11);
		bt1.setIcon(new ImageIcon(Credits.class.getResource("home.jpg")));
		//bt1.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
		bt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				bt1.setIcon(new ImageIcon(Credits.class.getResource("home1.jpg")));
				//bt1.setIcon(new ImageIcon("D:\\my project\\home\\home1.jpg"));
					
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bt1.setIcon(new ImageIcon(Credits.class.getResource("home.jpg")));
				//bt1.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
			}
			
		});
		
		JButton btnNewButton_2 = new JButton("Pending Payment");
		btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\PendingPayments.png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Pendingpayments pp=new Pendingpayments();
					pp.frame.setVisible(true);
					frame.dispose();
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(93, 242, 131, 159);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel btnNewButton_1 = new JLabel("PerdayEmployee");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {PerDayEmployee pe=new PerDayEmployee();
			pe.frame.setVisible(true);
			frame.dispose();
			}
		});
		
		btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\perday Employee.png"));
	
		btnNewButton_1.setBounds(263, 150, 185, 133);
		frame.getContentPane().add(btnNewButton_1);

		bt1.setSize(100, 100);
		frame.getContentPane().add(bt1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Credits.class.getResource("Untitled1.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled1.jpg"));
		lblNewLabel.setSize(500,500);
		frame.getContentPane().add(lblNewLabel);
	}
}
