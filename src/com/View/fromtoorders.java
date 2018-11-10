package com.View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.Client.DbConnection;
import com.gui.View;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class fromtoorders {

 public JFrame frame;
	private JTable table;
	JScrollPane bills;
	boolean flag;
	private JLabel lblFrom;
	DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public fromtoorders() throws Exception {
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

		table = new JTable();

		bills = new JScrollPane(table);
		bills.setLocation(26, 221);
	
		

		tableModel = new DefaultTableModel();
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				View v=new View();
				v.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(26, 592, 89, 36);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(fromtoorders.class.getResource("thread printing.png")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
		lblNewLabel.setBounds(0, 0, 1263, 122);
		frame.getContentPane().add(lblNewLabel);

		frame.getContentPane().add(bills);

		lblFrom = new JLabel("from:-");
		lblFrom.setFont(new Font("Verdana", Font.BOLD, 20));
		lblFrom.setBounds(448, 190, 71, 20);
		frame.getContentPane().add(lblFrom);

		JDateChooser from = new JDateChooser();
		from.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		from.setFont(new Font("Verdana", Font.PLAIN, 11));
		from.setDateFormatString("dd/MM/yyyy");
		from.setBounds(521, 190, 114, 20);
		frame.getContentPane().add(from);

		JLabel lblTo = new JLabel("TO:-");
		lblTo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblTo.setBounds(710, 190, 49, 20);
		frame.getContentPane().add(lblTo);

		JDateChooser to = new JDateChooser();
		to.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		to.setFont(new Font("Verdana", Font.PLAIN, 11));
		to.setDateFormatString("dd/MM/yyyy");
		to.setBounds(763, 190, 114, 20);
		frame.getContentPane().add(to);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flag = true;
				Date ds = from.getDate();
				String dstr = "";
				if (flag == true) {
					if (ds == null) {
						flag = false;

						JOptionPane.showMessageDialog(null, "Please Enter a From Date ");
					}

					else {
						flag = true;
						SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy");
						dstr = sd.format(ds);
					}
				}

				Date ds1 = to.getDate();
				String dstr1 = "";
				if (flag == true) {
					if (ds1 == null) {
						flag = false;

						JOptionPane.showMessageDialog(null, "Please Enter a Date");
					}

					else {
						flag = true;
						SimpleDateFormat sd1 = new SimpleDateFormat("dd-MMM-yyyy");
						dstr1 = sd1.format(ds1);
					}
				}
				System.out.println(new java.sql.Date(to.getDate().getTime()));
				if (flag == true) {
					PreparedStatement preparedStatement = null;
					try {
						preparedStatement = connection
								.prepareStatement("select * from orderdetail where ORDERDATE between ? and ?");
preparedStatement.setDate(1,  new java.sql.Date(from.getDate().getTime()));
preparedStatement.setDate(2,  new java.sql.Date(to.getDate().getTime()));
						ResultSet resultSet = preparedStatement.executeQuery();
						tableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(resultSet);
						table.setModel(tableModel);
						bills.setVisible(true);
						bills.setSize(1212, 393);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnSearch.setBounds(622, 259, 98, 36);
		frame.getContentPane().add(btnSearch);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(fromtoorders.class.getResource("Untitled 2.jpg")));
		//label.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		label.setBounds(0, 0, 1280,720);
		frame.getContentPane().add(label);
	}
}
