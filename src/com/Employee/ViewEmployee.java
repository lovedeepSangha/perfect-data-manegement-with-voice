package com.Employee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.Client.DbConnection;
import com.ManageAccount.ChangePassword;
import com.gui.Client;
import com.gui.Employee;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
//import javax.swing.JFileChooser;

public class ViewEmployee extends JFrame {
	Connection con;
	boolean flag;
	
	private JTextField Eid;
	private JTextField Ename;
	private JTextField Eaddress;
	private JTextField Eproof;
	private JTextField Emobile;
	private JTextField Esalary;
	private JTextField Esalaryhr;
	private JTextField Esalaryday;
	private JTextField Esunday;
	private JTextField Enight;
	int sn;
	public JFrame frame;
	public ViewEmployee() {
		initialize();
	}


 void initialize() {
	 frame = new JFrame();
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		con = DbConnection.getDBConnection();

		JLabel Eimage = new JLabel("");
		Eimage.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		Eimage.setBounds(793, 190, 209, 125);
		frame.getContentPane().add(Eimage);

		// fileChooser = new JFileChooser();
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Employee e = new Employee();
				e.frame.setVisible(true);
				
				frame.dispose();
			}
		});
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_3.setIcon(new ImageIcon(ViewEmployee.class.getResource("backbtsmall1.jpg")));
				//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\backbtsmall1.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_3.setIcon(new ImageIcon(ViewEmployee.class.getResource("backbt1.jpg")));
				//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
			}
		});
	   btnNewButton_3.setIcon(new ImageIcon(ViewEmployee.class.getResource("backbt1.jpg")));
	//	btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
		btnNewButton_3.setBounds(28, 589, 98, 68);
		frame.getContentPane().add(btnNewButton_3);

		/*
		 * String m[] = { "Select Job", "Embroidery Helper", "embroidery Master",
		 * "Helper", "Machine Helper", "Machine Worker", "Press Helper", "Press Master",
		 * "printing Helper", "Printing Master", "Rickshaw", "Spray Helper",
		 * "Spray Master", "Superwiser" };
		 */
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setLightWeightPopupEnabled(false);

		comboBox.setBounds(206, 441, 308, 25);
		frame.getContentPane().add(comboBox);

		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eimage.setIcon(new ImageIcon(""));
				flag = true;
				if (flag == true) {
					if (Eid.getText().trim().equals(""))

					{
						System.out.println("hello2");

						JOptionPane.showMessageDialog(null, "Please Enter an Employee ID");
						flag = false;
					} else {

						Pattern pattern = Pattern.compile("[0-9]+");
						Matcher matcher = pattern.matcher(Eid.getText());

						if (matcher.find() && matcher.group().equals(Eid.getText())) {

							sn = Integer.valueOf(Eid.getText());
							flag = true;
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid Employee ID ");
							// System.out.println("wrng");
							flag = false;

						}

					}
				}

				if (flag == true) {
					try {
						PreparedStatement ps = con.prepareStatement("SELECT * from employeedetail where eno=?");
						ps.setInt(1, sn);
						ResultSet rst = ps.executeQuery();

						if (rst.next()) {
							Ename.setText(rst.getString(2));
							Eaddress.setText(rst.getString(3));
							Eproof.setText(rst.getString(4));
							Emobile.setText(rst.getString(5));
							comboBox.addItem(rst.getString(6));
							comboBox.setSelectedItem(rst.getString(6));
							Esalary.setText(rst.getString(7));
							Blob b = rst.getBlob(8);// 2 means 2nd column data
							if (b != null) {
								byte barr[] = b.getBytes(1, (int) b.length());// 1 means first image
								BufferedImage image = ImageIO.read(new ByteArrayInputStream(barr));
								Eimage.setIcon(new ImageIcon(image));
							}
							Esalaryhr.setText(rst.getString(9));
							Esalaryday.setText(rst.getString(10));
							Esunday.setText(rst.getString(11));
							Enight.setText(rst.getString(12));
							// Browse.setEnabled(true);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btnNewButton_2.setBounds(534, 190, 89, 26);
		frame.getContentPane().add(btnNewButton_2);

		Enight = new JTextField();
		Enight.setEditable(false);
		Enight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Enight.setColumns(10);
		Enight.setBorder(new LineBorder(Color.BLACK, 2, true));
		Enight.setBounds(793, 494, 308, 26);
		frame.getContentPane().add(Enight);

		Esunday = new JTextField();
		Esunday.setEditable(false);
		Esunday.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Esunday.setColumns(10);
		Esunday.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esunday.setBounds(793, 441, 308, 26);
		frame.getContentPane().add(Esunday);

		Esalaryday = new JTextField();
		Esalaryday.setEditable(false);
		Esalaryday.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Esalaryday.setColumns(10);
		Esalaryday.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esalaryday.setBounds(793, 388, 308, 26);
		frame.getContentPane().add(Esalaryday);

		Esalaryhr = new JTextField();
		Esalaryhr.setEditable(false);
		Esalaryhr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Esalaryhr.setColumns(10);
		Esalaryhr.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esalaryhr.setBounds(793, 337, 308, 26);
		frame.getContentPane().add(Esalaryhr);

		JLabel lblNightDuty = new JLabel("Night duty:-");
		lblNightDuty.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNightDuty.setBounds(645, 492, 138, 26);
		frame.getContentPane().add(lblNightDuty);

		JLabel lblSundayDuty = new JLabel("Sunday duty:-");
		lblSundayDuty.setFont(new Font("Verdana", Font.BOLD, 20));
		lblSundayDuty.setBounds(629, 439, 157, 26);
		frame.getContentPane().add(lblSundayDuty);

		JLabel lblSalaryday = new JLabel("Salary/day:-");
		lblSalaryday.setFont(new Font("Verdana", Font.BOLD, 20));
		lblSalaryday.setBounds(645, 386, 142, 26);
		frame.getContentPane().add(lblSalaryday);

		JLabel lblNewLabel_5 = new JLabel("Salary/hr:-");
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_5.setBounds(656, 335, 126, 26);
		frame.getContentPane().add(lblNewLabel_5);

		Esalary = new JTextField();
		Esalary.setEditable(false);
		Esalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Esalary.setColumns(10);
		Esalary.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esalary.setBounds(206, 492, 308, 26);
		frame.getContentPane().add(Esalary);

		Emobile = new JTextField();
		Emobile.setEditable(false);
		Emobile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Emobile.setColumns(10);
		Emobile.setBorder(new LineBorder(Color.BLACK, 2, true));
		Emobile.setBounds(206, 388, 308, 26);
		frame.getContentPane().add(Emobile);

		Eproof = new JTextField();
		Eproof.setEditable(false);
		Eproof.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Eproof.setColumns(10);
		Eproof.setBorder(new LineBorder(Color.BLACK, 2, true));
		Eproof.setBounds(206, 337, 308, 26);
		frame.getContentPane().add(Eproof);

		Eaddress = new JTextField();
		Eaddress.setEditable(false);
		Eaddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Eaddress.setColumns(10);
		Eaddress.setBorder(new LineBorder(Color.BLACK, 2, true));
		Eaddress.setBounds(206, 289, 308, 26);
		frame.getContentPane().add(Eaddress);

		Ename = new JTextField();
		Ename.setEditable(false);
		Ename.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Ename.setColumns(10);
		Ename.setBorder(new LineBorder(Color.BLACK, 2, true));
		Ename.setBounds(206, 240, 308, 26);
		frame.getContentPane().add(Ename);

		Eid = new JTextField();
		Eid.setBorder(new LineBorder(Color.BLACK, 2, true));
		Eid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Eid.setBounds(206, 191, 308, 26);
		frame.getContentPane().add(Eid);
		Eid.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Salary:-");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_4.setBounds(101, 492, 88, 26);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("Job Profile:-");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_3.setBounds(51, 439, 138, 26);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblMobileNo = new JLabel("Mobile No:-");
		lblMobileNo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblMobileNo.setBounds(57, 386, 132, 26);
		frame.getContentPane().add(lblMobileNo);

		JLabel lblProofId = new JLabel("Proof ID:-");
		lblProofId.setFont(new Font("Verdana", Font.BOLD, 20));
		lblProofId.setBounds(75, 335, 114, 26);
		frame.getContentPane().add(lblProofId);

		JLabel lblProofNo = new JLabel("Address:-");
		lblProofNo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblProofNo.setBounds(78, 287, 109, 26);
		frame.getContentPane().add(lblProofNo);

		JLabel lblName = new JLabel("Name:-");
		lblName.setFont(new Font("Verdana", Font.BOLD, 20));
		lblName.setBounds(104, 238, 82, 26);
		frame.getContentPane().add(lblName);

		JLabel lblNewLabel_2 = new JLabel("Employee ID:-");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_2.setBounds(24, 190, 165, 26);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ViewEmployee.class.getResource("thread printing.png")));
		//lblNewLabel_1.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
		lblNewLabel_1.setBounds(0, 0, 1264, 117);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setIcon(new ImageIcon(ViewEmployee.class.getResource("Untitled 2.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		lblNewLabel.setBounds(0, 0, 1280, 720);
		frame.getContentPane().add(lblNewLabel);

	}


	// File file;
	// private String fileName;
	// private String filePath="";
	// private JFileChooser fileChooser;
	// FileInputStream fileInputStream;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ViewEmployee frame = new ViewEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */

}
