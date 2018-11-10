package com.Credits;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.View.fromtoorders;
import com.gui.Credits;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PerDayEmployee {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public PerDayEmployee() {
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
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Credits cr=new Credits();
				cr.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(32, 580, 89, 43);
		frame.getContentPane().add(btnNewButton);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBorder(new LineBorder(Color.BLACK, 2, true));
		textField_2.setBounds(321, 323, 229, 27);
		frame.getContentPane().add(textField_2);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		textField_1.setBounds(321, 263, 229, 27);
		frame.getContentPane().add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PerDayEmployee.class.getResource("thread printing.png")));
		//lblNewLabel_1.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
		lblNewLabel_1.setBounds(0, 0, 1261, 117);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblEmployeeId = new JLabel("Employee id :-");
		lblEmployeeId.setFont(new Font("Verdana", Font.BOLD, 20));
		lblEmployeeId.setBounds(149, 204, 162, 23);
		frame.getContentPane().add(lblEmployeeId);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(Color.BLACK, 2, true));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(321, 204, 229, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton PEsearch = new JButton("Search");
		PEsearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		PEsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		PEsearch.setBounds(560, 204, 89, 23);
		frame.getContentPane().add(PEsearch);
		
		JLabel lblEmployeeName = new JLabel("Employee Name:-");
		lblEmployeeName.setFont(new Font("Verdana", Font.BOLD, 20));
		lblEmployeeName.setBounds(113, 263, 198, 23);
		frame.getContentPane().add(lblEmployeeName);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateChooser.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		dateChooser.setBounds(772, 204, 215, 27);
		frame.getContentPane().add(dateChooser);
		
		JLabel lblDefaultSalary = new JLabel("Default Salary :-");
		lblDefaultSalary.setFont(new Font("Verdana", Font.BOLD, 20));
		lblDefaultSalary.setBounds(127, 323, 188, 27);
		frame.getContentPane().add(lblDefaultSalary);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PerDayEmployee.class.getResource("Untitled 2.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		lblNewLabel.setBounds(0, 0, 1280,720);
		frame.getContentPane().add(lblNewLabel);
	}
}
