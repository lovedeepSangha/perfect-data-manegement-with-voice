package com.gui;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.Employee.EditEmployee;
import com.Employee.NewEmployee;
//import com.Employee.Newemployee;
import com.Employee.ViewEmployee;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Employee {

	public JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Employee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
			
			ViewEmployee e=new ViewEmployee();
			e.frame.setVisible(true);
			frame.dispose();
			
			
			
			}
		});

		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_2.setIcon(new ImageIcon(Employee.class.getResource("viewemployee.png")));
			//	btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\viewemployee.png"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_2.setIcon(new ImageIcon(Employee.class.getResource("viewemployee1.png")));
				//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\viewemployee1.png"));
			}
		});
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditEmployee e = new EditEmployee();
				e.setVisible(true);
				frame.dispose();

			}
		});
		btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\Editemployee1.png"));
		btnNewButton_3.setBounds(196, 27, 151, 199);
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_2.setIcon(new ImageIcon(Employee.class.getResource("viewemployee1.png")));
		//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\viewemployee1.png"));
		btnNewButton_2.setBounds(196, 283, 146, 168);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dashboard dashboard = new Dashboard();
				dashboard.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Employee.class.getResource("home.jpg")));
		//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(Employee.class.getResource("home1.jpg")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\home1.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(Employee.class.getResource("home.jpg")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
			}
		});
		btnNewButton_1.setBounds(10, 11, 100, 100);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewEmployee e = new NewEmployee();
				e.frame.setVisible(true);
				frame.dispose();

			}
		});

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton.setIcon(new ImageIcon(Employee.class.getResource("new employee.png")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\new employee.png"));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton.setIcon(new ImageIcon(Employee.class.getResource("new employee1.png")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\new employee1.png"));

			}
		});
		btnNewButton.setIcon(new ImageIcon(Employee.class.getResource("new employee1.png")));
		//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\new employee1.png"));
		btnNewButton.setBounds(10, 144, 176, 143);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Employee.class.getResource("Untitled1.jpg")));
		lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled1.jpg"));
		lblNewLabel.setBounds(0, 0, 500, 500);
		frame.getContentPane().add(lblNewLabel);
	}
}
