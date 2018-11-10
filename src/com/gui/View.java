package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.View.AllClient;
import com.View.AllEmployees;
import com.View.Pendingorder;
import com.View.fromtoorders;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View extends JFrame {

	private JPanel contentPane;
	public JFrame frame;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dashboard d=new Dashboard();
				d.frame.setVisible(true);
				dispose(); 
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton.setIcon(new ImageIcon(View.class.getResource("home1.jpg")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\home1.jpg"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon(View.class.getResource("home.jpg")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
			}
		});
		
		JButton btnNewButton_4 = new JButton("Pending orders");
		btnNewButton_4.setIcon(new ImageIcon(View.class.getResource("pendingorders.png")));
		//btnNewButton_4.setIcon(new ImageIcon("D:\\my project\\home\\pendingorders.png"));
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_4.setIcon(new ImageIcon(View.class.getResource("pendingorders1.png")));
				//btnNewButton_4.setIcon(new ImageIcon("D:\\my project\\home\\pendingorders1.png"));

			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				//btnNewButton_4.setIcon(new ImageIcon("D:\\my project\\home\\pendingorders.png"));
				btnNewButton_4.setIcon(new ImageIcon(View.class.getResource("pendingorders.png")));
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {
					Pendingorder po= new Pendingorder();
					po.frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setBounds(29, 290, 150, 161);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("All orders");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_3.setIcon(new ImageIcon(View.class.getResource("Allorders1.png")));
				//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\Allorders1.png"));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_3.setIcon(new ImageIcon(View.class.getResource("Allorders.png")));
				//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\Allorders.png"));
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(View.class.getResource("Allorders.png")));
		//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\Allorders.png"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fromtoorders fo=new fromtoorders();
					fo.frame.setVisible(true);
				dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setBounds(295, 301, 140, 150);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("All employees");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_2.setIcon(new ImageIcon(View.class.getResource("Allemployees1.png")));
				//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\Allemployees1.png"));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_2.setIcon(new ImageIcon(View.class.getResource("Allemployees.png")));
				//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\Allemployees.png"));
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(View.class.getResource("Allemployees.png")));
		//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\Allemployees.png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllEmployees ae;
				try {
					ae = new AllEmployees();
					ae.frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//dispose();
			}
		});
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBounds(283, 93, 150, 178);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("All Clients");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(View.class.getResource("allclients1.png")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\allclients1.png"));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(View.class.getResource("allclients.png")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\allclients.png"));
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(View.class.getResource("allclients.png")));
		//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\allclients.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllClient ac;
				try {
					ac = new AllClient();
					ac.frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			//dispose();
			}
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(33, 132, 135, 150);
		contentPane.add(btnNewButton_1);
		btnNewButton.setIcon(new ImageIcon(View.class.getResource("home.jpg")));
		//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
		btnNewButton.setBounds(10, 11, 100, 100);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(View.class.getResource("Untitled1.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled1.jpg"));
		lblNewLabel.setBounds(0, 0, 500, 500);
		contentPane.add(lblNewLabel);
	}
}
