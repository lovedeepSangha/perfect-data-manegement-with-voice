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
import com.order.EditOrder;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pendingorder {

	public JFrame frame;
	private JTable table;
	JScrollPane bills;
	private JLabel label;
	private JLabel label_1;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Pendingorder() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Connection connection = DbConnection.getDBConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(
				"select orderno,clientid,clientname,lotno,TOTALPAYMENT,DELIEVERYDATE,status from orderdetail where status='Pending'");
		ResultSet resultSet = preparedStatement.executeQuery();
		table = new JTable();
		table.setFont(new Font("Verdana", Font.PLAIN, 11));

		bills = new JScrollPane(table);
		bills.setLocation(244, 181);
		bills.setVisible(true);
		bills.setSize(779, 238);

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(resultSet);
		
		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				View v=new View();
				v.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(39, 581, 89, 29);
		frame.getContentPane().add(btnNewButton);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Pendingorder.class.getResource("thread printing.png")));
		//label_1.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
		label_1.setBounds(0, 0, 1264, 118);
		frame.getContentPane().add(label_1);
		table.setModel(tableModel);
		frame.getContentPane().add(bills);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Pendingorder.class.getResource("Untitled 2.jpg")));
		//label.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		label.setBounds(0, 0, 1280, 720);
		frame.getContentPane().add(label);
	}

}
