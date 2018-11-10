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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import com.gui.Client;
import com.gui.Employee;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class EditEmployee extends JFrame {
	Connection con;
	boolean flag;
	private JPanel contentPane;
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
	public JFrame frame;
	int sn;

	File file;
	private String fileName;
	private String filePath="";
	private JFileChooser fileChooser;
	FileInputStream fileInputStream;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditEmployee frame = new EditEmployee();
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
	public EditEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		con = DbConnection.getDBConnection();
		
		
		JLabel Eimage = new JLabel("");
		Eimage.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		Eimage.setBounds(793, 190, 209, 125);
		contentPane.add(Eimage);
		
		fileChooser = new JFileChooser();
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Employee e = new Employee();
				e.frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_3.setIcon(new ImageIcon(EditEmployee.class.getResource("backbtsmall1.jpg")));
				//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\backbtsmall1.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_3.setIcon(new ImageIcon(EditEmployee.class.getResource("backbt1.jpg")));
				//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(EditEmployee.class.getResource("backbt1.jpg")));
		//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
		btnNewButton_3.setBounds(28, 589, 98, 68);
		contentPane.add(btnNewButton_3);

		JButton Browse = new JButton("Browse");
		Browse.setFont(new Font("Tahoma", Font.BOLD, 15));
		Browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int returnVal = fileChooser.showOpenDialog((Component) arg0.getSource());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					try {
						fileName = file.toString();
						System.out.println(file.getAbsolutePath());

						if (file.getAbsolutePath().toLowerCase().endsWith(".jpg")
								|| file.getAbsolutePath().toLowerCase().endsWith(".jpeg")
								|| file.getAbsolutePath().toLowerCase().endsWith(".png")) {
							Eimage.setText("");
							Eimage.setIcon(new ImageIcon(file.getAbsolutePath()));
							filePath = file.getAbsolutePath();
							fileInputStream = new FileInputStream(filePath);
						} else {
							
							JOptionPane.showMessageDialog(null, "Selected File is not an Image File");
						}
					} catch (Exception ex) {
						System.out.println("problem accessing file" + file.getAbsolutePath());
					}
				} else {
					System.out.println("File access cancelled by user.");
				}

				
				
				
				
				
			}
		});
		Browse.setEnabled(false);
		Browse.setBounds(1012, 192, 89, 23);
		contentPane.add(Browse);

		

		String m[] = { "Select Job", "Embroidery Helper", "embroidery Master", "Helper", "Machine Helper",
				"Machine Worker", "Press Helper", "Press Master", "printing Helper", "Printing Master", "Rickshaw",
				"Spray Helper", "Spray Master", "Superwiser" };
		JComboBox comboBox = new JComboBox(m);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBounds(206, 441, 308, 25);
		contentPane.add(comboBox);

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
							Browse.setEnabled(true);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btnNewButton_2.setBounds(534, 190, 89, 26);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("hello");
				flag = true;

				String name = Ename.getText();
				if (flag == true) {
					if (name.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter a Name");
						flag = false;
					} else {
						flag = true;
						for (int i = 0; i <= 9; i++) {
							if (name.indexOf(String.valueOf(i)) != -1) {
								JOptionPane.showMessageDialog(null, "Name Must Not be a Number");
								flag = false;
								break;
							}
						}
					}
				}
				String Address = Eaddress.getText();

				if (flag == true) {
					if (Address.trim().equals(""))

					{
						System.out.println("hello2");

						JOptionPane.showMessageDialog(null, "Please Enter an Address");
						flag = false;
					} else
						flag = true;
				}

				double proof = 0;

				if (flag == true) {
					if (Eproof.getText().trim().equals(""))

					{
						System.out.println("hello2");

						JOptionPane.showMessageDialog(null, "Please Enter an Proof number");
						flag = false;
					} else {
						flag = true;
						Pattern pattern = Pattern.compile("[0-9]+");
						Matcher matcher = pattern.matcher(Eproof.getText());

						if (matcher.find() && matcher.group().equals(Eproof.getText())) {

							flag = true;
							proof = Double.valueOf(Eproof.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid Proof Number");
							// System.out.println("wrng");
							flag = false;
						}

					}
				}

				double mob = 0;
				if (flag == true) {
					if (Emobile.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "Please Enter a Mobile  No.");
						flag = false;
					} else {
						Pattern pattern = Pattern.compile("[789][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
						Matcher matcher = pattern.matcher(Emobile.getText());

						if (matcher.find() && matcher.group().equals(Emobile.getText())) {
							mob = Double.parseDouble(Emobile.getText());
							flag = true;
							// System.out.println("Done");
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid mobile number");
							// System.out.println("wrng");
							flag = false;
						}
					}
				}

				String job = (String) comboBox.getSelectedItem();
				if (flag == true) {
					if (job.equals("Select Job")) {

						JOptionPane.showMessageDialog(null, "Please Choose Job");
						flag = false;
					} else
						flag = true;
				}

				double salary = 0;

				if (flag == true) {
					if (Esalary.getText().trim().equals(""))

					{
						System.out.println("hello2");

						JOptionPane.showMessageDialog(null, "Please Enter Salary");
						flag = false;
					} else {
						flag = true;
						Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
						Matcher matcher = pattern.matcher(Esalary.getText());

						if (matcher.find() && matcher.group().equals(Esalary.getText()) == true) {

							flag = true;
							salary = Double.valueOf(Esalary.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter Salary only in Numbers");
							System.out.println("salary worng");
							flag = false;
						}

					}
				}

				double salaryhr = 0;

				if (flag == true) {
					if (Esalaryhr.getText().toString().trim().equals(""))

					{
						System.out.println("hello2");

						flag = true;
					} else {
						flag = true;
						Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
						Matcher matcher = pattern.matcher(Esalaryhr.getText());

						if (matcher.find() && matcher.group().equals(Esalaryhr.getText()) == true) {
							System.out.println("e11");
							flag = true;
							salaryhr = Double.valueOf(Esalaryhr.getText());
						} 
						else {
							System.out.println("e1");
							JOptionPane.showMessageDialog(null, "Please Enter Salary/hr only in Numbers");
							System.out.println("salaryhr worng");

							flag = false;
						}

					}
				}

				double salaryday = 0;

				if (flag == true) {
					if (Esalaryday.getText().trim().equals(""))

					{
						System.out.println("hello2");

						flag = true;
					} else {
						flag = true;
						Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
						Matcher matcher = pattern.matcher(Esalaryday.getText());

						if (matcher.find() && matcher.group().equals(Esalaryday.getText())== true) {

							flag = true;
							salaryday = Double.valueOf(Esalaryday.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter Salary/day only in Numbers");
							System.out.println("salaryday worng");
							flag = false;
						}

					}
				}

				double salarysunday = 0;

				if (flag == true) {
					if (Esunday.getText().trim().equals(""))

					{
						System.out.println("hello2");

						flag = true;
					} else {
						flag = true;
						Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
						Matcher matcher = pattern.matcher(Esunday.getText());

						if (matcher.find() && matcher.group().equals(Esunday.getText())== true) {

							flag = true;
							salaryday = Double.valueOf(Esunday.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter Salary for sunday only in Numbers");
							System.out.println("salarysun worng");
							flag = false;
						}

					}
				}
				double salarynight = 0;

				if (flag == true) {
					if (Enight.getText().trim().equals(""))

					{
						System.out.println("hello2");

						flag = true;
					} else {
						flag = true;
						Pattern pattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
						Matcher matcher = pattern.matcher(Enight.getText());

						if (matcher.find() && matcher.group().equals(Enight.getText())== true) {

							flag = true;
							salaryday = Double.valueOf(Enight.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter Salary for night only in Numbers");
							System.out.println("salaryn worng");
							flag = false;
						}

					}
				}

				
					
					if (flag == true && filePath.equals("")==false) {
						try { // prepare
							PreparedStatement ps = con.prepareStatement("update employeedetail set ename=?,eaddress=?,eproof=?,emobile=?,ejob=?,esalary=?,eimage=?,esalaryhr=?,esalaryday=?,esunday=?,enight=? where eno=?");
							ps.setInt(12, sn);
							ps.setString(1, name);
							ps.setString(2, Address);
							ps.setDouble(3, proof);
							ps.setDouble(4, mob);
							ps.setString(5, job);
							ps.setDouble(6, salary);
							ps.setBinaryStream(7, fileInputStream, fileInputStream.available());	
							ps.setDouble(8, salaryhr);
							ps.setDouble(9, salaryday);
							ps.setDouble(10, salarysunday);
							ps.setDouble(11, salarynight);// execute query
							ps.executeUpdate();

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,"Editing done");
							e.printStackTrace();
							
						}

					}
					if (flag == true && filePath.equals("")==true) {
						try { // prepare
							PreparedStatement ps = con.prepareStatement("update employeedetail set ename=?,eaddress=?,eproof=?,emobile=?,ejob=?,esalary=?,esalaryhr=?,esalaryday=?,esunday=?,enight=? where eno=?");
							ps.setInt(11, sn);
							ps.setString(1, name);
							ps.setString(2, Address);
							ps.setDouble(3, proof);
							ps.setDouble(4, mob);
							ps.setString(5, job);
							ps.setDouble(6, salary);
							//ps.setBinaryStream(8, fileInputStream, fileInputStream.available());	
							ps.setDouble(7, salaryhr);
							ps.setDouble(8, salaryday);
							ps.setDouble(9, salarysunday);
							ps.setDouble(10, salarynight);// execute query
							ps.executeUpdate();

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
	
					
					
					
					
					
					
					
					
					
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(502, 578, 101, 36);
		contentPane.add(btnNewButton);

		Enight = new JTextField();
		Enight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Enight.setColumns(10);
		Enight.setBorder(new LineBorder(Color.BLACK, 2, true));
		Enight.setBounds(793, 494, 308, 26);
		contentPane.add(Enight);

		Esunday = new JTextField();
		Esunday.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Esunday.setColumns(10);
		Esunday.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esunday.setBounds(793, 441, 308, 26);
		contentPane.add(Esunday);

		Esalaryday = new JTextField();
		Esalaryday.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Esalaryday.setColumns(10);
		Esalaryday.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esalaryday.setBounds(793, 388, 308, 26);
		contentPane.add(Esalaryday);

		Esalaryhr = new JTextField();
		Esalaryhr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Esalaryhr.setColumns(10);
		Esalaryhr.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esalaryhr.setBounds(793, 337, 308, 26);
		contentPane.add(Esalaryhr);

		JLabel lblNightDuty = new JLabel("Night duty:-");
		lblNightDuty.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNightDuty.setBounds(645, 492, 138, 26);
		contentPane.add(lblNightDuty);

		JLabel lblSundayDuty = new JLabel("Sunday duty:-");
		lblSundayDuty.setFont(new Font("Verdana", Font.BOLD, 20));
		lblSundayDuty.setBounds(629, 439, 157, 26);
		contentPane.add(lblSundayDuty);

		JLabel lblSalaryday = new JLabel("Salary/day:-");
		lblSalaryday.setFont(new Font("Verdana", Font.BOLD, 20));
		lblSalaryday.setBounds(645, 386, 142, 26);
		contentPane.add(lblSalaryday);

		JLabel lblNewLabel_5 = new JLabel("Salary/hr:-");
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_5.setBounds(656, 335, 126, 26);
		contentPane.add(lblNewLabel_5);

		Esalary = new JTextField();
		Esalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Esalary.setColumns(10);
		Esalary.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esalary.setBounds(206, 492, 308, 26);
		contentPane.add(Esalary);

		Emobile = new JTextField();
		Emobile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Emobile.setColumns(10);
		Emobile.setBorder(new LineBorder(Color.BLACK, 2, true));
		Emobile.setBounds(206, 388, 308, 26);
		contentPane.add(Emobile);

		Eproof = new JTextField();
		Eproof.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Eproof.setColumns(10);
		Eproof.setBorder(new LineBorder(Color.BLACK, 2, true));
		Eproof.setBounds(206, 337, 308, 26);
		contentPane.add(Eproof);

		Eaddress = new JTextField();
		Eaddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Eaddress.setColumns(10);
		Eaddress.setBorder(new LineBorder(Color.BLACK, 2, true));
		Eaddress.setBounds(206, 289, 308, 26);
		contentPane.add(Eaddress);

		Ename = new JTextField();
		Ename.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Ename.setColumns(10);
		Ename.setBorder(new LineBorder(Color.BLACK, 2, true));
		Ename.setBounds(206, 240, 308, 26);
		contentPane.add(Ename);

		Eid = new JTextField();
		Eid.setBorder(new LineBorder(Color.BLACK, 2, true));
		Eid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Eid.setBounds(206, 191, 308, 26);
		contentPane.add(Eid);
		Eid.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Salary:-");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_4.setBounds(101, 492, 88, 26);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("Job Profile:-");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_3.setBounds(51, 439, 138, 26);
		contentPane.add(lblNewLabel_3);

		JLabel lblMobileNo = new JLabel("Mobile No:-");
		lblMobileNo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblMobileNo.setBounds(57, 386, 132, 26);
		contentPane.add(lblMobileNo);

		JLabel lblProofId = new JLabel("Proof ID:-");
		lblProofId.setFont(new Font("Verdana", Font.BOLD, 20));
		lblProofId.setBounds(75, 335, 114, 26);
		contentPane.add(lblProofId);

		JLabel lblProofNo = new JLabel("Address:-");
		lblProofNo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblProofNo.setBounds(78, 287, 109, 26);
		contentPane.add(lblProofNo);

		JLabel lblName = new JLabel("Name:-");
		lblName.setFont(new Font("Verdana", Font.BOLD, 20));
		lblName.setBounds(104, 238, 82, 26);
		contentPane.add(lblName);

		JLabel lblNewLabel_2 = new JLabel("Employee ID:-");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_2.setBounds(24, 190, 165, 26);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(EditEmployee.class.getResource("thread printing.png")));
		//lblNewLabel_1.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
		lblNewLabel_1.setBounds(0, 0, 1264, 117);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(EditEmployee.class.getResource("Untitled 2.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		lblNewLabel.setBounds(0, 0, 1280, 720);
		contentPane.add(lblNewLabel);
	}
}
