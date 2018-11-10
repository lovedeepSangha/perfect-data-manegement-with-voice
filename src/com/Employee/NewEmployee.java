package com.Employee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.Client.DbConnection;
import com.gui.Employee;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;

public class NewEmployee extends JFrame {

	private JTextField Esalaryday;
	private JTextField Eno;
	private JTextField Ename;
	private JTextField Eaddress;
	private JTextField Emobile;
	private JTextField Esalaryhr;
	private JTextField Esalary;
	private JTextField Eproof;
	private JTextField Esunday;
	private JTextField Enight;
	Connection con;
	private String fileName;
	private String filePath = "";
	int sn;
	FileInputStream fileInputStream;
	private JFileChooser fileChooser;
	File file;
	// public Object frame;
	public JFrame frame;

	/**
	 * Launch the application.
	 */
	void initialize() {
		frame = new JFrame();
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		con = DbConnection.getDBConnection();
		sn = 0;

		try {
			PreparedStatement ps = con.prepareStatement("SELECT eno from employeedetail",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ResultSet rs = ps.executeQuery();

			if (rs.last()) {
				sn = rs.getInt("eno") + 1;
			} else {
				sn = 101;
				System.out.println(sn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String m[] = { "Select Job", "Embroidery Helper", "embroidery Master", "Helper", "Machine Helper",
				"Machine Worker", "Press Helper", "Press Master", "printing Helper", "Printing Master", "Rickshaw",
				"Spray Helper", "Spray Master", "Superwiser" };
		JComboBox comboBox = new JComboBox(m);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBounds(311, 468, 308, 26);
		frame.getContentPane().add(comboBox);
		fileChooser = new JFileChooser();
		Enight = new JTextField();
		Enight.setColumns(10);
		Enight.setBorder(new LineBorder(Color.BLACK, 2, true));
		Enight.setBounds(817, 508, 308, 26);
		frame.getContentPane().add(Enight);

		JLabel Eimage = new JLabel("Photo 200*130");
		Eimage.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Eimage.setBounds(817, 210, 200, 130);
		frame.getContentPane().add(Eimage);

		JButton Browse = new JButton("Browse");
		Browse.setFont(new Font("Tahoma", Font.BOLD, 16));
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
		Browse.setBounds(1036, 256, 98, 29);
		frame.getContentPane().add(Browse);

		JLabel lblNigDuty = new JLabel("Night duty:-");
		lblNigDuty.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNigDuty.setBounds(670, 508, 137, 26);
		frame.getContentPane().add(lblNigDuty);

		Esunday = new JTextField();
		Esunday.setColumns(10);
		Esunday.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esunday.setBounds(817, 460, 308, 26);
		frame.getContentPane().add(Esunday);

		JLabel lblSundayDuty = new JLabel("Sunday duty:-");
		lblSundayDuty.setFont(new Font("Verdana", Font.BOLD, 20));
		lblSundayDuty.setBounds(650, 462, 157, 26);
		frame.getContentPane().add(lblSundayDuty);

		JLabel lblSalaryday = new JLabel("Salary/day:-");
		lblSalaryday.setFont(new Font("Verdana", Font.BOLD, 20));
		lblSalaryday.setBounds(665, 415, 142, 26);
		frame.getContentPane().add(lblSalaryday);

		JLabel lblSalaryhr = new JLabel("Salary/hr:-");
		lblSalaryhr.setFont(new Font("Verdana", Font.BOLD, 20));
		lblSalaryhr.setBounds(679, 363, 128, 26);
		frame.getContentPane().add(lblSalaryhr);

		Eproof = new JTextField();
		Eproof.setColumns(10);
		Eproof.setBorder(new LineBorder(Color.BLACK, 2, true));
		Eproof.setBounds(311, 363, 308, 26);
		frame.getContentPane().add(Eproof);

		Esalary = new JTextField();
		Esalary.setColumns(10);
		Esalary.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esalary.setBounds(311, 516, 308, 26);
		frame.getContentPane().add(Esalary);

		JButton save = new JButton("save");
		save.setFont(new Font("Tahoma", Font.BOLD, 19));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.out.println("hello");
				boolean flag = true;

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

						if (matcher.find() && matcher.group().equals(Esalary.getText())) {

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
						Matcher matcher = pattern.matcher(Esalary.getText());

						if (matcher.find() && matcher.group().equals(Esalaryhr.getText())) {

							flag = true;
							salaryhr = Double.valueOf(Esalaryhr.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter Salary/hr only in Numbers");
							System.out.println("salaryhr worng");
							;
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
						Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]++");
						Matcher matcher = pattern.matcher(Esalaryday.getText());

						if (matcher.find() && matcher.group().equals(Esalaryday.getText())) {

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

						if (matcher.find() && matcher.group().equals(Esunday.getText())) {

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
						Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
						Matcher matcher = pattern.matcher(Enight.getText());

						if (matcher.find() && matcher.group().equals(Enight.getText())) {

							flag = true;
							salaryday = Double.valueOf(Enight.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter Salary for night duty only in Numbers");
							System.out.println("salaryn worng");
							flag = false;
						}

					}
				}

				if (flag == true && filePath.equals("") == false) {
					try { // prepare
						PreparedStatement ps = con
								.prepareStatement("insert into employeedetail values(?,?,?,?,?,?,?,?,?,?,?,?)");
						ps.setInt(1, sn);
						ps.setString(2, name);
						ps.setString(3, Address);
						ps.setDouble(4, proof);
						ps.setDouble(5, mob);
						ps.setString(6, job);
						ps.setDouble(7, salary);
						ps.setBinaryStream(8, fileInputStream, fileInputStream.available());
						ps.setDouble(9, salaryhr);
						ps.setDouble(10, salaryday);
						ps.setDouble(11, salarysunday);
						ps.setDouble(12, salarynight);// execute query
						ps.executeUpdate();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				if (flag == true && filePath.equals("") == true) {
					try { // prepare
						PreparedStatement ps = con.prepareStatement(
								"insert into employeedetail(eno,ename,eaddress,eproof,emobile,ejob,esalary,esalaryhr,esalaryday,esunday,enight) values(?,?,?,?,?,?,?,?,?,?,?)");
						ps.setInt(1, sn);
						ps.setString(2, name);
						ps.setString(3, Address);
						ps.setDouble(4, proof);
						ps.setDouble(5, mob);
						ps.setString(6, job);
						ps.setDouble(7, salary);
						// ps.setBinaryStream(8, fileInputStream, fileInputStream.available());
						ps.setDouble(8, salaryhr);
						ps.setDouble(9, salaryday);
						ps.setDouble(10, salarysunday);
						ps.setDouble(11, salarynight);// execute query
						ps.executeUpdate();

					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Done");
					}

				}

			}
		});
		save.setBounds(612, 600, 89, 37);
		frame.getContentPane().add(save);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Employee e = new Employee();
				e.frame.setVisible(true);
				Window frame = null;
				
			}
		});
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_3.setIcon(new ImageIcon(NewEmployee.class.getResource("backbtsmall1.jpg")));
				//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\backbtsmall1.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_3.setIcon(new ImageIcon(NewEmployee.class.getResource("backbt1.jpg")));
				//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(NewEmployee.class.getResource("backbt1.jpg")));
		//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
		btnNewButton_3.setBounds(28, 589, 98, 68);
		frame.getContentPane().add(btnNewButton_3);

		Esalaryhr = new JTextField();
		Esalaryhr.setColumns(10);
		Esalaryhr.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esalaryhr.setBounds(817, 363, 308, 26);
		frame.getContentPane().add(Esalaryhr);

		Emobile = new JTextField();
		Emobile.setColumns(10);
		Emobile.setBorder(new LineBorder(Color.BLACK, 2, true));
		Emobile.setBounds(311, 414, 308, 26);
		frame.getContentPane().add(Emobile);

		Eaddress = new JTextField();
		Eaddress.setBorder(new LineBorder(Color.BLACK, 2, true));
		Eaddress.setBounds(311, 311, 308, 26);
		frame.getContentPane().add(Eaddress);
		Eaddress.setColumns(10);

		Ename = new JTextField();
		Ename.setBorder(new LineBorder(Color.BLACK, 2, true));
		Ename.setBounds(311, 260, 308, 26);
		frame.getContentPane().add(Ename);
		Ename.setColumns(10);

		Eno = new JTextField();
		Eno.setEditable(false);
		Eno.setBorder(new LineBorder(Color.BLACK, 2, true));
		Eno.setBounds(311, 210, 308, 26);
		frame.getContentPane().add(Eno);
		Eno.setColumns(10);
		Eno.setText(String.valueOf(sn));
		Esalaryday = new JTextField();
		Esalaryday.setBorder(new LineBorder(Color.BLACK, 2, true));
		Esalaryday.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Esalaryday.setBounds(817, 412, 308, 26);
		frame.getContentPane().add(Esalaryday);
		Esalaryday.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Job profile:-");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_3.setBounds(170, 466, 137, 26);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Salary:-");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_4.setBounds(219, 516, 88, 26);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_1 = new JLabel("MobileNo:-");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_1.setBounds(181, 410, 126, 37);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblAddress = new JLabel("Address:-");
		lblAddress.setFont(new Font("Verdana", Font.BOLD, 20));
		lblAddress.setBounds(200, 307, 118, 26);
		frame.getContentPane().add(lblAddress);

		JLabel lblProof = new JLabel("Proof id:-");
		lblProof.setFont(new Font("Verdana", Font.BOLD, 20));
		lblProof.setBounds(200, 363, 107, 26);
		frame.getContentPane().add(lblProof);

		JLabel lblEmployeeId = new JLabel("Employee id:-");
		lblEmployeeId.setFont(new Font("Verdana", Font.BOLD, 20));
		lblEmployeeId.setBounds(152, 206, 155, 26);
		frame.getContentPane().add(lblEmployeeId);

		JLabel lblName = new JLabel("Name:-");
		lblName.setFont(new Font("Verdana", Font.BOLD, 20));
		lblName.setBounds(225, 256, 82, 26);
		frame.getContentPane().add(lblName);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(NewEmployee.class.getResource("thread printing.png")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
		lblNewLabel.setBounds(0, -11, 1264, 142);
		frame.getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(NewEmployee.class.getResource("Untitled 2.jpg")));
		//label.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		label.setBounds(10, -11, 1280, 720);
		frame.getContentPane().add(label);
	}

	/**
	 * Create the frame.
	 */
	public NewEmployee() {
		initialize();
	}
}
