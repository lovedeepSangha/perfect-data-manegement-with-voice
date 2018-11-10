package com.Credits;

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
import com.Employee.ViewEmployee;
import com.View.fromtoorders;
import com.gui.Credits;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pendingpayments {

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
	 * 
	 * @throws Exception
	 */
	public Pendingpayments() throws Exception {
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
				"select orderno,clientid,CLIENTNAME,LOTNO,PPAYMENT  from orderdetail where ppayment>0");
		ResultSet resultSet = preparedStatement.executeQuery();
		table = new JTable();
		table.setFont(new Font("Verdana", Font.PLAIN, 12));

		bills = new JScrollPane(table);
		bills.setLocation(256, 235);
		bills.setVisible(true);
		bills.setSize(779, 238);

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(resultSet);

btnNewButton = new JButton("Back");
btnNewButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		Credits cr=new Credits();
		cr.frame.setVisible(true);
		frame.dispose();
	}
});
btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
btnNewButton.setBounds(38, 595, 89, 35);
frame.getContentPane().add(btnNewButton);

lblNewLabel_1 = new JLabel("");
lblNewLabel_1.setIcon(new ImageIcon(Pendingpayments.class.getResource("thread printing.png")));
//lblNewLabel_1.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
lblNewLabel_1.setBounds(0, 0, 1260, 121);
frame.getContentPane().add(lblNewLabel_1);
table.setModel(tableModel);
		frame.getContentPane().add(bills);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Pendingpayments.class.getResource("Untitled 2.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		lblNewLabel.setBounds(0, 0, 1280,720);
		frame.getContentPane().add(lblNewLabel);
	}
}
