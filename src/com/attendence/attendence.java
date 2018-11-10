package com.attendence;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.Client.DbConnection;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

public class attendence {

	public JFrame frame;

	private JTable billt;
	private JScrollPane bills;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public attendence() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 632, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		billt = new JTable();

		Connection connection = DbConnection.getDBConnection();

		PreparedStatement preparedStatement = connection.prepareStatement("Select eno,ename from employeedetail");
		ResultSet resultSet = preparedStatement.executeQuery();

	//	billt.setPreferredScrollableViewportSize(billt.getPreferredSize());
		bills = new JScrollPane(billt);
		bills.setLocation(106, 121);
		bills.setVisible(true);
		bills.setSize(431, 147);

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(resultSet);
		tableModel.addColumn("attendence");
		billt.setModel(tableModel);
		JCheckBox checkBox = new javax.swing.JCheckBox();

		billt.getColumn("attendence").setCellEditor((TableCellEditor) new DefaultCellEditor(checkBox));

		billt.setGridColor(Color.BLACK);
		billt.setForeground(Color.BLUE);
		frame.getContentPane().add(bills);
		
		JLabel lblDate = new JLabel("Date:-");
		lblDate.setBounds(162, 85, 70, 14);
		frame.getContentPane().add(lblDate);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(299, 79, 155, 20);
		frame.getContentPane().add(dateChooser);

	}
}
