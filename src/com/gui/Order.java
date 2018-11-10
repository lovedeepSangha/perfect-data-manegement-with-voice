package com.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.order.EditOrder;
import com.order.NewOrder;
import com.order.ViewOrder;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Order {

	 public JFrame frame;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public Order() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setLocation(150, 100);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton bt1 = new JButton("");
		bt1.setContentAreaFilled(false);
		bt1.setFocusPainted(false);
		bt1.setBorderPainted(false);
		bt1.setBorder(null);
		bt1.setOpaque(true);
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dashboard=new Dashboard();
				dashboard.frame.setVisible(true);
				frame.dispose();
			}
		});
		bt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				bt1.setIcon(new ImageIcon(Order.class.getResource("home1.jpg")));
				//bt1.setIcon(new ImageIcon("D:\\my project\\home\\home1.jpg"));
					
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bt1.setIcon(new ImageIcon(Order.class.getResource("home.jpg")));
				//bt1.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
			}
					});
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBorder(null);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditOrder eo=new EditOrder();
				eo.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_2.setIcon(new ImageIcon(Order.class.getResource("editorder.png")));
				//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\deleteorder.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setIcon(new ImageIcon(Order.class.getResource("editorder1.png")));
				//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\deleteorder1.png"));
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Order.class.getResource("editorder1.png")));
		//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\deleteorder1.png"));
		btnNewButton_2.setBounds(287, 108, 156, 145);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewOrder no=new NewOrder();
				no.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(Order.class.getResource("neworder.png")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\neworder.png"));

			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(Order.class.getResource("neworder1.png")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\neworder1.png"));

			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Order.class.getResource("neworder1.png")));
		//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\neworder1.png"));
		btnNewButton_1.setBounds(59, 129, 140, 164);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewOrder vo=new ViewOrder();
				vo.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon(Order.class.getResource("vieworder.png")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\vieworder.png"));

			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon(Order.class.getResource("vieworder1.png")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\vieworder1.png"));

			}
		});
		btnNewButton.setIcon(new ImageIcon(Order.class.getResource("vieworder1.png")));
		//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\vieworder1.png"));
		btnNewButton.setBounds(252, 287, 119, 164);
		frame.getContentPane().add(btnNewButton);
		bt1.setLocation(23, 11);
		bt1.setIcon(new ImageIcon(Order.class.getResource("home.jpg")));
		//bt1.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
		bt1.setSize(100,100);
		frame.getContentPane().add(bt1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setLocation(10, -38);
		lblNewLabel.setIcon(new ImageIcon(Order.class.getResource("Untitled1.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled1.jpg"));
		lblNewLabel.setSize(494,513);
		frame.getContentPane().add(lblNewLabel);
	}
}
