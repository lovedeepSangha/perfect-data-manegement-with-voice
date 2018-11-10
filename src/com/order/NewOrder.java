package com.order;

import java.awt.Color;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.Client.DbConnection;
import com.gui.Client;
import com.gui.Order;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

public class NewOrder extends JFrame {
	public JFrame frame;
	boolean flag;
	int id, designno;
	private JTextField Nclientid;
	private JTextField Nlno;
	private JTextField NCn;
	private JTextField Nquantity;
	private JTextField Ndno;
	private JTextField Ndname;
	private JTextField Ntpayment;
	private JTextField Nfullpay;
	private JTextField Napayment;
	private JTextField Nppayment;
	private JTextField Norderno;
	private JTextField Nchequeno;
	int sn;
	double designratefromtable;
	private JTextField Npayment;
	double tax, payment, calculation;
	int adv, pen, full;
	String designnumber;
	double taxaxation = 0.05;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
public static void main(String[] args) {
	NewOrder newOrder=new NewOrder();
	newOrder.frame.setVisible(true);
}
	public NewOrder() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String m[] = { "Select", "Nikker", "Shirt", "Inner", "Arms" };
		JComboBox NdesigncomboBox = new JComboBox(m);
		Connection con = DbConnection.getDBConnection();
		JCheckBox Ncash = new JCheckBox("Cash");JCheckBox Ncheque = new JCheckBox("Cheque");
		JDateChooser dateChooser = new JDateChooser();
		JDateChooser DdateChooser_ = new JDateChooser();
		JCheckBox Ndeleivered = new JCheckBox("Delivered");
		JCheckBox NPending = new JCheckBox("Pending");
		Ndeleivered.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (Ndeleivered.isSelected() == true) {
					NPending.setSelected(false);

				}
			}
		});

		NPending.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (NPending.isSelected() == true) {
					Ndeleivered.setSelected(false);
				}

			}
		});

		try {
			PreparedStatement ps = con.prepareStatement("SELECT orderno FROM orderdetail",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ResultSet rs = ps.executeQuery();

			if (rs.last()) {
				sn = rs.getInt("orderno") + 1;
			} else {
				sn = 101;
				System.out.println(sn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel Nlabel = new JLabel("Cheque No.:-");
		Nlabel.setVisible(false);

		JButton search2 = new JButton("Search");
		search2.setFont(new Font("Tahoma", Font.BOLD, 16));
		search2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				flag = true;
				if (flag == true) {
					if (Ndno.getText().trim().equals(""))

					{
						System.out.println("hello2");

						JOptionPane.showMessageDialog(null, "Please Enter Design Number");
						flag = false;
					} else {

						Pattern pattern = Pattern.compile("[0-9]+");
						Matcher matcher = pattern.matcher(Ndno.getText());

						if (matcher.find() && matcher.group().equals(Ndno.getText())) {

							designno = Integer.valueOf(Ndno.getText());
							flag = true;
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid Design Number");
							System.out.println("wrng");
							flag = false;

						}

					}
				}
				if (flag == true) {
					if (NdesigncomboBox.getSelectedItem().toString().equals("Select") == false) {

						switch (NdesigncomboBox.getSelectedItem().toString()) {

						case "Nikker":
							try {
								PreparedStatement ps = con.prepareStatement(
										"select designname,TDESIGNRATE from nikkerdesign where designno=?");
								ps.setInt(1, designno);

								ResultSet rst = ps.executeQuery();

								if (rst.next()) {
									Ndname.setText(rst.getString("designname"));
									designratefromtable = rst.getDouble("TDESIGNRATE");
								} else {
									JOptionPane.showMessageDialog(null, "Design Number not found from Database");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Please Enter an valid  Design Number");
								e1.printStackTrace();

							}
							break;
						case "Shirt":
							try {
								PreparedStatement ps = con.prepareStatement(
										"select designname,TDESIGNRATE from shirtdesign where designno=?");
								ps.setInt(1, designno);

								ResultSet rst = ps.executeQuery();

								if (rst.next()) {
									Ndname.setText(rst.getString("designname"));
									designratefromtable = rst.getDouble("TDESIGNRATE");
								} else {
									JOptionPane.showMessageDialog(null, "Design Number not found from Database");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Please Enter an valid  Design Number");
								e1.printStackTrace();

							}
							break;
						case "Inner":
							try {
								PreparedStatement ps = con.prepareStatement(
										"select designname,TDESIGNRATE from innerdesign where designno=?");
								ps.setInt(1, designno);

								ResultSet rst = ps.executeQuery();

								if (rst.next()) {
									Ndname.setText(rst.getString("designname"));
									designratefromtable = rst.getDouble("TDESIGNRATE");
								} else {
									JOptionPane.showMessageDialog(null, "Design Number not found from Database");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Please Enter an valid  Design Number");
								e1.printStackTrace();

							}
							break;
						case "Arms":
							try {
								PreparedStatement ps = con.prepareStatement(
										"select designname,TDESIGNRATE from armdesign where designno=?");
								ps.setInt(1, designno);

								ResultSet rst = ps.executeQuery();

								if (rst.next()) {
									Ndname.setText(rst.getString("designname"));
									designratefromtable = rst.getDouble("TDESIGNRATE");
								} else {
									JOptionPane.showMessageDialog(null, "Design Number not found from Database");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Please Enter an valid  Design Number");
								e1.printStackTrace();

							}
							break;

						}
					} else {
						JOptionPane.showMessageDialog(null, "Select Design type");
					}
				}
				System.out.println("des" + designratefromtable);
				if (flag == true) {
					if (Nquantity.getText().equals("") == false) {
						calculation = Integer.valueOf(Nquantity.getText()) * designratefromtable;
						calculation = new Double(calculation).intValue();
						System.out.println(calculation);
						Npayment.setText(String.valueOf(calculation));

					} else {
						JOptionPane.showMessageDialog(null, "Please Enter Quantity");

					}
				}
				Napayment.setText("0");
			}
		});

		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("sdffsdfsd");
				flag = true;
				if (flag == true) {
					String loot = Nlno.getText();

					if (flag == true) {
						if (loot.trim().equals(""))

						{
							System.out.println("hello2");

							JOptionPane.showMessageDialog(null, "Please Enter an Loot Number");
							flag = false;
						} else
							flag = true;
						System.out.println("lno");
					}
					System.out.println("hjjjjjjjjjjjjjjjjjjjjjj");
					int quantity = 0;

					if (flag == true) {
						if (Nquantity.getText().trim().equals(""))

						{
							System.out.println("hello2");

							JOptionPane.showMessageDialog(null, "Please Enter an Quantity");
							flag = false;
						} else {
							flag = true;
							Pattern pattern = Pattern.compile("[0-9]+");
							Matcher matcher = pattern.matcher(Nquantity.getText());

							if (matcher.find() && matcher.group().equals(Nquantity.getText())) {

								flag = true;
								quantity = Integer.valueOf(Nquantity.getText());
								System.out.println("quantity");
							} else {
								JOptionPane.showMessageDialog(null, "Please Enter an valid Quantity");
								// System.out.println("wrng");
								flag = false;
							}

						}
					}

					Date ds = dateChooser.getDate();
					String dstr = "";
					if (flag == true) {
						if (ds == null) {
							flag = false;

							JOptionPane.showMessageDialog(null, "Please Enter a Date");
						}

						else {
							flag = true;
							System.out.println("date1");
							SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy");
							dstr = sd.format(ds);
						}
					}

					Date ds1 = DdateChooser_.getDate();
					String dstr1 = "";
					if (flag == true) {
						if (ds1 == null) {
							flag = false;

							JOptionPane.showMessageDialog(null, "Please Enter a Date");
						}

						else {
							flag = true;
							System.out.println("date12");
							SimpleDateFormat sd1 = new SimpleDateFormat("dd/MMM/yyyy");
							dstr1 = sd1.format(ds1);
						}
					}

					if (flag == true) {
						if (Npayment.getText().trim().equals(""))

						{
							System.out.println("hello2");

							JOptionPane.showMessageDialog(null, "Please Enter Payment");
							flag = false;
						} else {
							flag = true;
							Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
							Matcher matcher = pattern.matcher(Npayment.getText());

							if (matcher.find() && matcher.group().equals(Npayment.getText())) {

								flag = true;
								payment = Double.valueOf(Npayment.getText());
								System.out.println("npayemnt");
							} else {
								JOptionPane.showMessageDialog(null, "Please Enter only Numbers");
								System.out.println("salary worng");
								flag = false;
							}

						}
					}

					double advancepayment = 0;

					if (flag == true) {
						if (Napayment.getText().trim().equals(""))

						{
							System.out.println("hello2");

							flag = true;
							;
						} else {
							flag = true;
							Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
							Matcher matcher = pattern.matcher(Napayment.getText());

							if (matcher.find() && matcher.group().equals(Napayment.getText())) {

								flag = true;
								payment = Double.valueOf(Napayment.getText());
								System.out.println("advanec");
							} else {
								JOptionPane.showMessageDialog(null, "Please Enter only Numbers");
								System.out.println("salary worng");
								flag = false;
							}

						}
					}

					String paymethod = "";
					if (flag == true) {
						if (Ncheque.isSelected() == true) {
							paymethod = "Cheque";

						} else if (Ncash.isSelected() == true) {
							paymethod = "cash";
						} else {
							flag = false;
						}
					}
					double chequeno = 0;
					if (flag == true) {
						if (Ncheque.isSelected() == true) {

							if (Nchequeno.getText().trim().equals(""))

							{
								System.out.println("hello2");

								JOptionPane.showMessageDialog(null, "Please Enter an chcknumber");
								flag = false;
							} else {
								flag = true;
								Pattern pattern = Pattern.compile("[0-9]+");
								Matcher matcher = pattern.matcher(Nchequeno.getText());

								if (matcher.find() && matcher.group().equals(Nchequeno.getText())) {

									flag = true;
									chequeno = Double.valueOf(Nchequeno.getText());
								} else {
									JOptionPane.showMessageDialog(null, "Please Enter an valid checkque Number");
									// System.out.println("wrng");
									flag = false;
								}

							}

						}
					}

				}
				if (flag == true) {
					System.out.println(Norderno.getText());

					try {
						Bill bill = new Bill(Norderno.getText());
						bill.frame.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Order o= new Order();
				o.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnNewButton.setIcon(null);
		btnNewButton.setBounds(27, 608, 89, 46);
		frame.getContentPane().add(btnNewButton);
		btnPrint.setBounds(688, 615, 89, 33);
		frame.getContentPane().add(btnPrint);

		Ndeleivered.setBounds(274, 548, 97, 23);
		frame.getContentPane().add(Ndeleivered);

		NPending.setBounds(116, 548, 97, 23);
		frame.getContentPane().add(NPending);

		JLabel lblStatus = new JLabel("Status :-");
		lblStatus.setFont(new Font("Verdana", Font.BOLD, 20));
		lblStatus.setBounds(1, 548, 99, 26);
		frame.getContentPane().add(lblStatus);

		NdesigncomboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		NdesigncomboBox.setBounds(177, 433, 115, 23);
		frame.getContentPane().add(NdesigncomboBox);

		JLabel lblPayment = new JLabel("Payment :-");
		lblPayment.setFont(new Font("Verdana", Font.BOLD, 20));
		lblPayment.setBounds(713, 291, 127, 26);
		frame.getContentPane().add(lblPayment);

		Npayment = new JTextField();

		Npayment.getDocument().addDocumentListener(new DocumentListener() {

			@Override

			public void insertUpdate(DocumentEvent e) {

				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = Npayment.getText();
						if (!text.matches("\\d*(\\.\\d{0,2})?")) {
							Npayment.setText(text.substring(0, text.length() - 1));
						}
					}
				};
				SwingUtilities.invokeLater(format);
				payment = Double.valueOf(Npayment.getText());
				tax = payment * taxaxation;
				tax = new Double(tax).intValue();
				Ntpayment.setText(String.valueOf(tax));
				Nfullpay.setText(String.valueOf(tax + payment));
				full = new Double(tax + payment).intValue();
				;
			}

			@Override
			public void removeUpdate(DocumentEvent de) {
				if (Npayment.getText().equals("") == false) {
					payment = Double.valueOf(Npayment.getText());
					tax = payment * taxaxation;
					tax = new Double(tax).intValue();
					Ntpayment.setText(String.valueOf(tax));
					Nfullpay.setText(String.valueOf(tax + payment));
					full = new Double(tax + payment).intValue();
					;
				}
			}

			@Override
			public void changedUpdate(DocumentEvent de) {
				// Plain text components don't fire these events. ha lagi samj
			}
		});
		Npayment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Npayment.setColumns(10);
		Npayment.setBorder(new LineBorder(Color.BLACK, 2, true));
		Npayment.setBounds(844, 293, 308, 26);
		frame.getContentPane().add(Npayment);
		search2.setBounds(495, 425, 89, 33);
		frame.getContentPane().add(search2);
		Nlabel.setFont(new Font("Verdana", Font.BOLD, 20));
		Nlabel.setBounds(688, 561, 146, 26);
		frame.getContentPane().add(Nlabel);

		Nchequeno = new JTextField();
		((AbstractDocument) Nchequeno.getDocument()).setDocumentFilter(new MyDocumentFilter());
		Nchequeno.setVisible(false);
		Nchequeno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Nchequeno.setColumns(10);
		Nchequeno.setBorder(new LineBorder(Color.BLACK, 2, true));
		Nchequeno.setBounds(844, 561, 308, 26);
		frame.getContentPane().add(Nchequeno);

		Norderno = new JTextField();
		Norderno.setEditable(false);
		Norderno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Norderno.setColumns(10);
		Norderno.setBorder(new LineBorder(Color.BLACK, 2, true));
		Norderno.setBounds(177, 204, 308, 26);
		frame.getContentPane().add(Norderno);
		Norderno.setText(String.valueOf(sn));
		JLabel lblOrderNo = new JLabel("Order No.:-");
		lblOrderNo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblOrderNo.setBounds(40, 204, 127, 26);
		frame.getContentPane().add(lblOrderNo);

		
		Ncash.setFont(new Font("Verdana", Font.BOLD, 15));
		Ncash.setBounds(1004, 517, 148, 26);
		frame.getContentPane().add(Ncash);

		
		Ncheque.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				if (Ncheque.isSelected() == true) {
					Nchequeno.setVisible(true);
					Nlabel.setVisible(true);
				}
				if (Ncheque.isSelected() == false) {
					Nchequeno.setVisible(false);
					Nlabel.setVisible(false);
				}

			}
		});
		Ncheque.setFont(new Font("Verdana", Font.BOLD, 15));
		Ncheque.setBounds(844, 517, 158, 26);
		frame.getContentPane().add(Ncheque);

		JLabel lblPaymentMethod = new JLabel("Payment method:-");
		lblPaymentMethod.setFont(new Font("Verdana", Font.BOLD, 20));
		lblPaymentMethod.setBounds(626, 515, 214, 26);
		frame.getContentPane().add(lblPaymentMethod);

		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		dateChooser.setBounds(845, 204, 308, 26);
		frame.getContentPane().add(dateChooser);

		DdateChooser_.setDateFormatString("dd/MM/yyyy");
		DdateChooser_.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		DdateChooser_.setBounds(845, 248, 308, 26);
		frame.getContentPane().add(DdateChooser_);
		Nppayment = new JTextField();
		Nppayment.setEditable(false);
		Nppayment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Nppayment.setColumns(10);
		Nppayment.setBorder(new LineBorder(Color.BLACK, 2, true));
		Nppayment.setBounds(844, 476, 308, 26);
		frame.getContentPane().add(Nppayment);

		JLabel lblTax = new JLabel("Tax:-");
		lblTax.setFont(new Font("Verdana", Font.BOLD, 20));
		lblTax.setBounds(778, 338, 62, 26);
		frame.getContentPane().add(lblTax);

		JButton Search1 = new JButton("Search");
		Search1.setFont(new Font("Tahoma", Font.BOLD, 15));
		Search1.setBounds(495, 241, 89, 33);
		ButtonGroup group = new ButtonGroup();
		group.add(Ncash);
		group.add(Ncheque);
		frame.getContentPane().add(Search1);
		Search1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				flag = true;
				if (flag == true) {
					if (Nclientid.getText().trim().equals(""))

					{
						System.out.println("hello2");

						JOptionPane.showMessageDialog(null, "Please Enter an Client ID");
						flag = false;
					} else {

						Pattern pattern = Pattern.compile("[0-9]+");
						Matcher matcher = pattern.matcher(Nclientid.getText());

						if (matcher.find() && matcher.group().equals(Nclientid.getText())) {

							id = Integer.valueOf(Nclientid.getText());
							flag = true;
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid Client ID ");
							// System.out.println("wrng");
							flag = false;

						}

					}
				}

				if (flag == true) {
					try {
						PreparedStatement ps = con
								.prepareStatement("SELECT clientname from clientreg where clientno=?");
						ps.setInt(1, id);
						ResultSet rst = ps.executeQuery();

						if (rst.next()) {
							NCn.setText(rst.getString("clientname"));
							flag = true;
						}
					} catch (Exception e1) {
						flag = false;
						JOptionPane.showMessageDialog(null, "Please Enter an valid Client ID ");
					}

				}

			}
		});

		JButton Save = new JButton("Save");
		Save.setFont(new Font("Tahoma", Font.BOLD, 21));
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				flag = true;

				String loot = Nlno.getText();

				if (flag == true) {
					if (loot.trim().equals(""))

					{
						System.out.println("hello2");

						JOptionPane.showMessageDialog(null, "Please Enter an Loot Number");
						flag = false;
					} else
						flag = true;
				}

				int quantity = 0;

				if (flag == true) {
					if (Nquantity.getText().trim().equals(""))

					{
						System.out.println("hello2");

						JOptionPane.showMessageDialog(null, "Please Enter an Quantity");
						flag = false;
					} else {
						flag = true;
						Pattern pattern = Pattern.compile("[0-9]+");
						Matcher matcher = pattern.matcher(Nquantity.getText());

						if (matcher.find() && matcher.group().equals(Nquantity.getText())) {

							flag = true;
							quantity = Integer.valueOf(Nquantity.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter an valid Quantity");
							// System.out.println("wrng");
							flag = false;
						}

					}
				}

				Date ds = dateChooser.getDate();
				String dstr = "";
				if (flag == true) {
					if (ds == null) {
						flag = false;

						JOptionPane.showMessageDialog(null, "Please Enter a Date");
					}

					else {
						flag = true;
						SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy");
						dstr = sd.format(ds);
					}
				}

				Date ds1 = DdateChooser_.getDate();
				String dstr1 = "";
				if (flag == true) {
					if (ds1 == null) {
						flag = false;

						JOptionPane.showMessageDialog(null, "Please Enter a Date");
					}

					else {
						flag = true;
						SimpleDateFormat sd1 = new SimpleDateFormat("dd/MMM/yyyy");
						dstr1 = sd1.format(ds1);
					}
				}

				if (flag == true) {
					if (Npayment.getText().trim().equals(""))

					{
						System.out.println("hello2");

						JOptionPane.showMessageDialog(null, "Please Enter Payment");
						flag = false;
					} else {
						flag = true;
						Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
						Matcher matcher = pattern.matcher(Npayment.getText());

						if (matcher.find() && matcher.group().equals(Npayment.getText())) {

							flag = true;
							payment = Double.valueOf(Npayment.getText());

						} else {
							JOptionPane.showMessageDialog(null, "Please Enter only Numbers");
							System.out.println("salary worng");
							flag = false;
						}

					}
				}

				double advancepayment = 0;

				if (flag == true) {
					if (Napayment.getText().trim().equals(""))

					{
						System.out.println("hello2");

						flag = true;
						;
					} else {
						flag = true;
						Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
						Matcher matcher = pattern.matcher(Napayment.getText());

						if (matcher.find() && matcher.group().equals(Napayment.getText())) {

							flag = true;
							payment = Double.valueOf(Napayment.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter only Numbers");
							System.out.println("salary worng");
							flag = false;
						}

					}
				}

				String paymethod = "";
				if (flag == true) {
					if (Ncheque.isSelected() == true) {
						paymethod = "Cheque";

					} else if (Ncash.isSelected() == true) {
						paymethod = "cash";
					} else {
						flag = false;
					}
				}
				double chequeno = 0;
				if (flag == true) {
					if (Ncheque.isSelected() == true) {

						if (Nchequeno.getText().trim().equals(""))

						{
							System.out.println("hello2");

							JOptionPane.showMessageDialog(null, "Please Enter an chcknumber");
							flag = false;
						} else {
							flag = true;
							Pattern pattern = Pattern.compile("[0-9]+");
							Matcher matcher = pattern.matcher(Nchequeno.getText());

							if (matcher.find() && matcher.group().equals(Nchequeno.getText())) {

								flag = true;
								chequeno = Double.valueOf(Nchequeno.getText());
							} else {
								JOptionPane.showMessageDialog(null, "Please Enter an valid checkque Number");
								// System.out.println("wrng");
								flag = false;
							}

						}

					}
				}

				if (flag == true) {
					if (NPending.isSelected() == false && Ndeleivered.isSelected() == false) {
						JOptionPane.showMessageDialog(null, "Please Select a Status ");
						flag = false;
					} else {
						flag = true;
					}

				}

				if (flag == true) {
					PreparedStatement ps;
					try {
						ps = con.prepareStatement("insert into orderdetail values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						ps.setInt(1, Integer.valueOf(Norderno.getText()));
						ps.setInt(2, Integer.valueOf(Nclientid.getText()));
						ps.setString(3, NCn.getText());
						ps.setString(4, Nlno.getText());
						ps.setInt(5, Integer.valueOf(Nquantity.getText()));
						designnumber = NdesigncomboBox.getSelectedItem().toString() + Ndno.getText();
						ps.setString(6, designnumber);
						ps.setString(7, Ndname.getText());
						ps.setDate(8, new java.sql.Date(dateChooser.getDate().getTime()));
						ps.setDate(9, new java.sql.Date(DdateChooser_.getDate().getTime()));
						ps.setDouble(10, Double.valueOf(Ntpayment.getText()));
						ps.setDouble(11, Double.valueOf(Nfullpay.getText()));
						ps.setDouble(12, Double.valueOf(Napayment.getText()));
						ps.setDouble(13, Double.valueOf(Nppayment.getText()));
						if (Ncheque.isSelected() == true) {
							ps.setString(14, "Check");
							ps.setString(15, Nchequeno.getText());
						} else {
							ps.setString(14, "Cash");
							ps.setString(15, "0");
						}
						if (NPending.isSelected() == true) {
							ps.setString(16, "Pending");
						}
						if (Ndeleivered.isSelected() == true) {
							ps.setString(16, "Delievered");
						}
						ps.executeQuery();
						System.out.println("done");
						JOptionPane.showMessageDialog(null, "Done ");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
		Save.setBounds(527, 615, 99, 33);
		frame.getContentPane().add(Save);

		Napayment = new JTextField();

		((AbstractDocument) Napayment.getDocument()).setDocumentFilter(new MyDocumentFilter());
		Napayment.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent de) {
				if (Napayment.getText().equals("") == false)
					adv = Integer.valueOf(Napayment.getText());
				if (adv > full) {
					JOptionPane.showMessageDialog(null, "Advance payment is more ");
				} else {
					pen = full - adv;
					Nppayment.setText(String.valueOf(pen));
				}
			}

			@Override
			public void removeUpdate(DocumentEvent de) {
				if (Napayment.getText().equals("") == false) {
					pen = full - adv;
					Nppayment.setText(String.valueOf(pen));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent de) {
				// Plain text components don't fire these events.
			}
		});

		Napayment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Napayment.setColumns(10);
		Napayment.setBorder(new LineBorder(Color.BLACK, 2, true));
		Napayment.setBounds(844, 431, 308, 26);
		frame.getContentPane().add(Napayment);

		JLabel lblPendingPayment = new JLabel("Pending Payment:-");
		lblPendingPayment.setFont(new Font("Verdana", Font.BOLD, 20));
		lblPendingPayment.setBounds(625, 475, 214, 26);
		frame.getContentPane().add(lblPendingPayment);

		Nfullpay = new JTextField();
		Nfullpay.setEditable(false);
		Nfullpay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Nfullpay.setColumns(10);
		Nfullpay.setBorder(new LineBorder(Color.BLACK, 2, true));
		Nfullpay.setBounds(844, 387, 308, 26);
		frame.getContentPane().add(Nfullpay);

		JLabel lblAdvance = new JLabel("Advance Payment:-");
		lblAdvance.setFont(new Font("Verdana", Font.BOLD, 20));
		lblAdvance.setBounds(621, 430, 218, 26);
		frame.getContentPane().add(lblAdvance);

		Ntpayment = new JTextField();
		Ntpayment.setEditable(false);
		Ntpayment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Ntpayment.setColumns(10);
		Ntpayment.setBorder(new LineBorder(Color.BLACK, 2, true));
		Ntpayment.setBounds(844, 340, 308, 26);
		frame.getContentPane().add(Ntpayment);

		JLabel lblTotalPayment = new JLabel("Total Payment:-");
		lblTotalPayment.setFont(new Font("Verdana", Font.BOLD, 20));
		lblTotalPayment.setBounds(656, 385, 183, 26);
		frame.getContentPane().add(lblTotalPayment);

		JLabel lblDelieveryDate = new JLabel("Delievery Date:-");
		lblDelieveryDate.setFont(new Font("Verdana", Font.BOLD, 20));
		lblDelieveryDate.setBounds(652, 248, 183, 26);
		frame.getContentPane().add(lblDelieveryDate);

		JLabel lblOrderDate = new JLabel("Order Date:-");
		lblOrderDate.setFont(new Font("Verdana", Font.BOLD, 20));
		lblOrderDate.setBounds(695, 204, 146, 26);
		frame.getContentPane().add(lblOrderDate);

		Ndname = new JTextField();
		Ndname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Ndname.setColumns(10);
		Ndname.setBorder(new LineBorder(Color.BLACK, 2, true));
		Ndname.setBounds(177, 482, 308, 26);
		frame.getContentPane().add(Ndname);

		JLabel lblDesignName = new JLabel("Design Name:-");
		lblDesignName.setFont(new Font("Verdana", Font.BOLD, 20));
		lblDesignName.setBounds(1, 480, 166, 26);
		frame.getContentPane().add(lblDesignName);

		Ndno = new JTextField();
		Ndno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Ndno.setColumns(10);
		Ndno.setBorder(new LineBorder(Color.BLACK, 2, true));
		Ndno.setBounds(302, 431, 183, 26);
		frame.getContentPane().add(Ndno);

		JLabel lblDesignNo = new JLabel("Design No.:-");
		lblDesignNo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblDesignNo.setBounds(27, 429, 140, 26);
		frame.getContentPane().add(lblDesignNo);

		Nquantity = new JTextField();
		((AbstractDocument) Nquantity.getDocument()).setDocumentFilter(new MyDocumentFilter());
		Nquantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Nquantity.setColumns(10);
		Nquantity.setBorder(new LineBorder(Color.BLACK, 2, true));
		Nquantity.setBounds(177, 385, 308, 26);
		frame.getContentPane().add(Nquantity);

		JLabel lblQuantity = new JLabel("Quantity:-");
		lblQuantity.setFont(new Font("Verdana", Font.BOLD, 20));
		lblQuantity.setBounds(50, 383, 117, 26);
		frame.getContentPane().add(lblQuantity);

		NCn = new JTextField();
		NCn.setEditable(false);
		NCn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		NCn.setColumns(10);
		NCn.setBorder(new LineBorder(Color.BLACK, 2, true));
		NCn.setBounds(177, 293, 308, 26);
		frame.getContentPane().add(NCn);

		JLabel lblOwnLotNo = new JLabel("Own Lot No.:-");
		lblOwnLotNo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblOwnLotNo.setBounds(10, 338, 157, 26);
		frame.getContentPane().add(lblOwnLotNo);

		Nlno = new JTextField();
		Nlno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Nlno.setColumns(10);
		Nlno.setBorder(new LineBorder(Color.BLACK, 2, true));
		Nlno.setBounds(177, 338, 308, 26);
		frame.getContentPane().add(Nlno);

		JLabel lblClientName = new JLabel("Client Name:-");
		lblClientName.setFont(new Font("Verdana", Font.BOLD, 20));
		lblClientName.setBounds(14, 291, 153, 26);
		frame.getContentPane().add(lblClientName);

		Nclientid = new JTextField();
		((AbstractDocument) Nclientid.getDocument()).setDocumentFilter(new MyDocumentFilter());
		Nclientid.setBorder(new LineBorder(Color.BLACK, 2, true));
		Nclientid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Nclientid.setBounds(177, 248, 308, 26);
		frame.getContentPane().add(Nclientid);
		Nclientid.setColumns(10);

		JLabel lblClientId = new JLabel("Client ID:-");
		lblClientId.setFont(new Font("Verdana", Font.BOLD, 20));
		lblClientId.setBounds(50, 248, 117, 26);
		frame.getContentPane().add(lblClientId);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(NewOrder.class.getResource("thread printing.png")));
		//lblNewLabel_1.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
		lblNewLabel_1.setBounds(0, 0, 1271, 122);
		frame.getContentPane().add(lblNewLabel_1);
		
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				lblNewLabel.setIcon(new ImageIcon(NewOrder.class.getResource("Untitled 2.jpg")));
				//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
				lblNewLabel.setBounds(-9, 0, 1280, 720);
				frame.getContentPane().add(lblNewLabel);

	}
}

class MyDocumentFilter extends DocumentFilter {
	@Override
	public void insertString(DocumentFilter.FilterBypass fp, int offset, String string, AttributeSet aset)
			throws BadLocationException {
		int len = string.length();
		boolean isValidInteger = true;

		for (int i = 0; i < len; i++) {
			if (!Character.isDigit(string.charAt(i))) {
				isValidInteger = false;
				break;
			}
		}
		if (isValidInteger)
			super.insertString(fp, offset, string, aset);
		else
			Toolkit.getDefaultToolkit().beep();
	}

	@Override
	public void replace(DocumentFilter.FilterBypass fp, int offset, int length, String string, AttributeSet aset)
			throws BadLocationException {
		int len = string.length();
		boolean isValidInteger = true;

		for (int i = 0; i < len; i++) {
			if (!Character.isDigit(string.charAt(i))) {
				isValidInteger = false;
				break;
			}
		}
		if (isValidInteger)
			super.replace(fp, offset, length, string, aset);
		else
			Toolkit.getDefaultToolkit().beep();
	}
}