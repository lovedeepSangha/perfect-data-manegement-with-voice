package com.View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.Client.DbConnection;
import com.gui.View;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class AllEmployees {
	public JFrame frame;
	private JTable table;
	JScrollPane bills;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public AllEmployees() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Connection connection = DbConnection.getDBConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(
				"select * from employeedetail");
		ResultSet resultSet = preparedStatement.executeQuery();
		table = new JTable();
		table.setFont(new Font("Verdana", Font.PLAIN, 11));

		bills = new JScrollPane(table);
		bills.setEnabled(false);
		bills.setLocation(26, 221);
		bills.setVisible(true);
		bills.setSize(1212, 393);

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(resultSet);
		
		btnNewButton = new JButton("Back");
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				View v=new View();
				v.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(23, 156, 89, 32);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AllEmployees.class.getResource("thread printing.png")));
		//lblNewLabel_1.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
		lblNewLabel_1.setBounds(0, 0, 1258, 120);
		frame.getContentPane().add(lblNewLabel_1);
		table.setModel(tableModel);
		frame.getContentPane().add(bills);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AllEmployees.class.getResource("Untitled 2.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		lblNewLabel.setBounds(0, 0, 1280,720);
		frame.getContentPane().add(lblNewLabel);
	}
}
