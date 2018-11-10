package com.Design;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.Client.DbConnection;
import com.gui.Design;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

public class ViewDesign extends JFrame {

	String part1 = "", part2 = "", part3 = "";

	private JPanel contentPane;
	private JTextField NDdesignno;
	private JTextField NDdesignname;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField NDembrate;
	private JTextField Ndstitch;
	private JTextField NDcolors;
	private JTextField NDframes_1;
	private JTextField NDsize;
	private JTextField NDpress;
	private JTextField NDSpray;
	private JTextField NDlabour;
	private JTextField NDtotal;
	private String fileName;
	JRadioButton selectedbutton;
	private String filePath = "";
	int sn;
	FileInputStream fileInputStream;
	private JFileChooser fileChooser;
	File file;
	double sum;
	boolean flag;
	String option;

	/**
	 * Launch the application.
	 */
	public JFrame frame;

	/**
	 * Create the frame.
	 */
	public ViewDesign() {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Connection con = DbConnection.getDBConnection();
		JLabel NDdesignimage = new JLabel("");
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	Window frame = null;
				
				Design d = new Design();
				d.frame.setVisible(true);dispose();

			}
		});
		JCheckBox NDmanuals = new JCheckBox("Manual Printing");
		
		NDmanuals.setFont(new Font("Verdana", Font.BOLD, 15));
		NDmanuals.setBounds(908, 190, 151, 23);
		contentPane.add(NDmanuals);

		JCheckBox NDmachine = new JCheckBox("Machine");
		
		JCheckBox NDEmbroidrery = new JCheckBox("Embroidery");

		

		JButton search = new JButton("Search");
		search.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		search.setFont(new Font("Tahoma", Font.BOLD, 15));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(NDdesignno.getText().equals("")==true)
					JOptionPane.showMessageDialog(null, "Please select Cloth type & Enter design Number ");
				else
					if (NDdesignno.getText().equals("") == false) {
					int designno = Integer.valueOf(NDdesignno.getText());
					for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
						AbstractButton button = buttons.nextElement();
						if (button.isSelected()) {
							if (button.getText().equals("Nikker")) {
								try {
									PreparedStatement ps = con
											.prepareStatement("select * from nikkerdesign where designno=?");
									ps.setInt(1, designno);
									ResultSet rs = ps.executeQuery();
									if (rs.next()) {
										NDdesignname.setText(rs.getString(2));
										Blob b = rs.getBlob(3);// 2 means 2nd column data
										if (b != null) {
											byte barr[] = b.getBytes(1, (int) b.length());// 1 means first image
											BufferedImage image = ImageIO.read(new ByteArrayInputStream(barr));
											NDdesignimage.setIcon(new ImageIcon(image));
										}

										String string = rs.getString(4);
										String[] parts = string.split(",");
										for (String f : parts) {
											System.out.println(f);
										}

										try {
											part1 = parts[1];
											part2 = parts[2];
											part3 = parts[3];
										} catch (Exception e) {
											// TODO: handle exception
										}
										if (part1.equals("") == false) {
											switch (part1) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}

										}
										if (part2.equals("") == false) {
											switch (part2) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}
										}
										if (part3.equals("") == false) {
											switch (part3) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}
										}

										NDembrate.setText(rs.getString(5));
										Ndstitch.setText(rs.getString(6));
										NDcolors.setText(rs.getString(7));
										NDframes_1.setText(rs.getString(8));
										NDsize.setText(rs.getString(9));

										NDpress.setText(rs.getString(10));
										NDSpray.setText(rs.getString(11));
										NDlabour.setText(rs.getString(12));
										NDtotal.setText(rs.getString(13));

										System.out.println("done");
									} else {
										JOptionPane.showMessageDialog(null, "No Design Found ");
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
							if (button.getText().equals("Shirt")) {
								try {
									PreparedStatement ps = con
											.prepareStatement("select * from shirtdesign where designno=?");
									ps.setInt(1, designno);
									ResultSet rs = ps.executeQuery();
									if (rs.next()) {
										NDdesignname.setText(rs.getString(2));
										Blob b = rs.getBlob(3);// 2 means 2nd column data
										if (b != null) {
											byte barr[] = b.getBytes(1, (int) b.length());// 1 means first image
											BufferedImage image = ImageIO.read(new ByteArrayInputStream(barr));
											NDdesignimage.setIcon(new ImageIcon(image));
										}

										String string = rs.getString(4);
										String[] parts = string.split(",");
										for (String f : parts) {
											System.out.println(f);
										}

										try {
											part1 = parts[1];
											part2 = parts[2];
											part3 = parts[3];
										} catch (Exception e) {
											// TODO: handle exception
										}
										if (part1.equals("") == false) {
											switch (part1) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}

										}
										if (part2.equals("") == false) {
											switch (part2) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}
										}
										if (part3.equals("") == false) {
											switch (part3) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}
										}

										NDembrate.setText(rs.getString(5));
										Ndstitch.setText(rs.getString(6));
										NDcolors.setText(rs.getString(7));
										NDframes_1.setText(rs.getString(8));
										NDsize.setText(rs.getString(9));

										NDpress.setText(rs.getString(10));
										NDSpray.setText(rs.getString(11));
										NDlabour.setText(rs.getString(12));
										NDtotal.setText(rs.getString(13));

										System.out.println("done");
									} else {
										JOptionPane.showMessageDialog(null, "No Design Found ");
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
							if (button.getText().equals("Inner")) {
								try {
									PreparedStatement ps = con
											.prepareStatement("select * from innerdesign where designno=?");
									ps.setInt(1, designno);
									ResultSet rs = ps.executeQuery();
									if (rs.next()) {
										NDdesignname.setText(rs.getString(2));
										Blob b = rs.getBlob(3);// 2 means 2nd column data
										if (b != null) {
											byte barr[] = b.getBytes(1, (int) b.length());// 1 means first image
											BufferedImage image = ImageIO.read(new ByteArrayInputStream(barr));
											NDdesignimage.setIcon(new ImageIcon(image));
										}

										String string = rs.getString(4);
										String[] parts = string.split(",");
										for (String f : parts) {
											System.out.println(f);
										}

										try {
											part1 = parts[1];
											part2 = parts[2];
											part3 = parts[3];
										} catch (Exception e) {
											// TODO: handle exception
										}
										if (part1.equals("") == false) {
											switch (part1) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}

										}
										if (part2.equals("") == false) {
											switch (part2) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}
										}
										if (part3.equals("") == false) {
											switch (part3) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}
										}

										NDembrate.setText(rs.getString(5));
										Ndstitch.setText(rs.getString(6));
										NDcolors.setText(rs.getString(7));
										NDframes_1.setText(rs.getString(8));
										NDsize.setText(rs.getString(9));

										NDpress.setText(rs.getString(10));
										NDSpray.setText(rs.getString(11));
										NDlabour.setText(rs.getString(12));
										NDtotal.setText(rs.getString(13));

										System.out.println("done");
									} else {
										JOptionPane.showMessageDialog(null, "No Design Found ");
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
							if (button.getText().equals("Arms")) {
								try {
									PreparedStatement ps = con
											.prepareStatement("select * from armdesign where designno=?");
									ps.setInt(1, designno);
									ResultSet rs = ps.executeQuery();
									if (rs.next()) {
										NDdesignname.setText(rs.getString(2));
										Blob b = rs.getBlob(3);// 2 means 2nd column data
										if (b != null) {
											byte barr[] = b.getBytes(1, (int) b.length());// 1 means first image
											BufferedImage image = ImageIO.read(new ByteArrayInputStream(barr));
											NDdesignimage.setIcon(new ImageIcon(image));
										}

										String string = rs.getString(4);
										String[] parts = string.split(",");
										for (String f : parts) {
											System.out.println(f);
										}

										try {
											part1 = parts[1];
											part2 = parts[2];
											part3 = parts[3];
										} catch (Exception e) {
											// TODO: handle exception
										}
										if (part1.equals("") == false) {
											switch (part1) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}

										}
										if (part2.equals("") == false) {
											switch (part2) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}
										}
										if (part3.equals("") == false) {
											switch (part3) {
											case "Manual Printing":
												NDmanuals.setSelected(true);
												break;
											case "Machine":
												NDmachine.setSelected(true);
												;
												break;
											case "Embroidery":
												NDEmbroidrery.setSelected(true);
												;
												break;

											}
										}

										NDembrate.setText(rs.getString(5));
										Ndstitch.setText(rs.getString(6));
										NDcolors.setText(rs.getString(7));
										NDframes_1.setText(rs.getString(8));
										NDsize.setText(rs.getString(9));

										NDpress.setText(rs.getString(10));
										NDSpray.setText(rs.getString(11));
										NDlabour.setText(rs.getString(12));
										NDtotal.setText(rs.getString(13));

										System.out.println("done");
									} else {
										JOptionPane.showMessageDialog(null, "No Design Found ");
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}

						}
					}
				}
			}
		});
		search.setBounds(513, 256, 89, 31);
		contentPane.add(search);
		NDEmbroidrery.setFont(new Font("Verdana", Font.BOLD, 15));
		NDEmbroidrery.setBounds(1069, 190, 119, 23);
		contentPane.add(NDEmbroidrery);

		NDmachine.setFont(new Font("Verdana", Font.BOLD, 15));
		NDmachine.setBounds(793, 190, 104, 23);
		contentPane.add(NDmachine);

		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_2.setIcon(new ImageIcon(ViewDesign.class.getResource("backbtsmall1.jpg")));
				//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\backbtsmall1.jpg"));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_2.setIcon(new ImageIcon(ViewDesign.class.getResource("backbt1.jpg")));
				//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));

			}
		});

		JLabel lblPressRatepiece = new JLabel("Press rate/piece:-");
		lblPressRatepiece.setFont(new Font("Verdana", Font.BOLD, 18));
		lblPressRatepiece.setBounds(767, 536, 190, 26);
		contentPane.add(lblPressRatepiece);

		NDtotal = new JTextField();
		NDtotal.setEditable(false);

		NDtotal.setFont(new Font("Verdana", Font.BOLD, 16));
		NDtotal.setColumns(10);
		NDtotal.setBorder(new LineBorder(Color.BLACK, 2, true));
		NDtotal.setBounds(961, 694, 290, 25);
		contentPane.add(NDtotal);

		JLabel lblTotalDesignRate = new JLabel("Total Design rate:-");
		lblTotalDesignRate.setFont(new Font("Verdana", Font.BOLD, 18));
		lblTotalDesignRate.setBounds(767, 692, 190, 26);
		contentPane.add(lblTotalDesignRate);
		btnNewButton_2.setIcon(new ImageIcon(ViewDesign.class.getResource("backbt1.jpg")));
		//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\backbt1.jpg"));
		btnNewButton_2.setBounds(25, 597, 99, 74);
		contentPane.add(btnNewButton_2);

		NDSpray = new JTextField();
		NDSpray.setEditable(false);
		NDSpray.setText("0");
		NDSpray.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = NDSpray.getText();
						if (!text.matches("\\d*(\\.\\d{0,2})?")) {
							NDSpray.setText(text.substring(0, text.length() - 1));
						}
					}
				};
				SwingUtilities.invokeLater(format);
				if (NDSpray.getText().equals("") == false)
					try {
						sum = 0;
						sum = Double.valueOf(NDembrate.getText()) + Double.valueOf(Ndstitch.getText())
								+ Double.valueOf(NDpress.getText()) + Double.valueOf(NDSpray.getText())
								+ Double.valueOf(NDlabour.getText());
						NDtotal.setText(String.valueOf(sum));
					} catch (Exception e2) {
						System.out.println("NDemrate Number format");
					}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					sum = 0;
					sum = Double.valueOf(NDembrate.getText()) + Double.valueOf(Ndstitch.getText())
							+ Double.valueOf(NDpress.getText()) + Double.valueOf(NDSpray.getText())
							+ Double.valueOf(NDlabour.getText());
					NDtotal.setText(String.valueOf(sum));
				} catch (Exception e2) {
					System.out.println("NDemrate Number format");
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		NDSpray.setFont(new Font("Verdana", Font.BOLD, 16));
		NDSpray.setColumns(10);
		NDSpray.setBorder(new LineBorder(Color.BLACK, 2, true));
		NDSpray.setBounds(961, 591, 290, 25);
		contentPane.add(NDSpray);

		NDpress = new JTextField();
		NDpress.setEditable(false);
		NDpress.setText("0");
		NDpress.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = NDpress.getText();
						if (!text.matches("\\d*(\\.\\d{0,2})?")) {
							NDpress.setText(text.substring(0, text.length() - 1));
						}
					}
				};
				SwingUtilities.invokeLater(format);
				try {
					sum = 0;
					sum = Double.valueOf(NDembrate.getText()) + Double.valueOf(Ndstitch.getText())
							+ Double.valueOf(NDpress.getText()) + Double.valueOf(NDSpray.getText())
							+ Double.valueOf(NDlabour.getText());
					NDtotal.setText(String.valueOf(sum));
				} catch (Exception e2) {
					System.out.println("NDemrate Number format");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (NDpress.getText().equals("") == false)
					try {
						sum = 0;
						sum = Double.valueOf(NDembrate.getText()) + Double.valueOf(Ndstitch.getText())
								+ Double.valueOf(NDpress.getText()) + Double.valueOf(NDSpray.getText())
								+ Double.valueOf(NDlabour.getText());
						NDtotal.setText(String.valueOf(sum));
					} catch (Exception e2) {
						System.out.println("NDemrate Number format");
					}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		NDpress.setFont(new Font("Verdana", Font.BOLD, 16));
		NDpress.setColumns(10);
		NDpress.setBorder(new LineBorder(Color.BLACK, 2, true));
		NDpress.setBounds(961, 538, 290, 25);
		contentPane.add(NDpress);

		NDsize = new JTextField();
		NDsize.setEditable(false);
		NDsize.setText("0");
		NDsize.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = NDsize.getText();
						if (!text.matches("\\d*(\\.\\d{0,2})?")) {
							NDsize.setText(text.substring(0, text.length() - 1));
						}
					}
				};
				SwingUtilities.invokeLater(format);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		NDsize.setFont(new Font("Verdana", Font.BOLD, 16));
		NDsize.setColumns(10);
		NDsize.setBorder(new LineBorder(Color.BLACK, 2, true));
		NDsize.setBounds(961, 487, 290, 25);
		contentPane.add(NDsize);

		NDlabour = new JTextField();
		NDlabour.setEditable(false);
		NDlabour.setText("0");
		NDlabour.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = NDlabour.getText();
						if (!text.matches("[0-9]+")) {
							NDlabour.setText(text.substring(0, text.length() - 1));
						}
					}
				};
				SwingUtilities.invokeLater(format);
				try {
					sum = 0;
					sum = Double.valueOf(NDembrate.getText()) + Double.valueOf(Ndstitch.getText())
							+ Double.valueOf(NDpress.getText()) + Double.valueOf(NDSpray.getText())
							+ Double.valueOf(NDlabour.getText());
					NDtotal.setText(String.valueOf(sum));
				} catch (Exception e2) {
					System.out.println("NDemrate Number format");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (NDlabour.getText().equals("") == false) {
					try {
						sum = 0;
						sum = Double.valueOf(NDembrate.getText()) + Double.valueOf(Ndstitch.getText())
								+ Double.valueOf(NDpress.getText()) + Double.valueOf(NDSpray.getText())
								+ Double.valueOf(NDlabour.getText());
						NDtotal.setText(String.valueOf(sum));
					} catch (Exception e2) {
						System.out.println("NDemrate Number format");
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		NDlabour.setFont(new Font("Verdana", Font.BOLD, 16));
		NDlabour.setColumns(10);
		NDlabour.setBorder(new LineBorder(Color.BLACK, 2, true));
		NDlabour.setBounds(961, 644, 290, 25);
		contentPane.add(NDlabour);

		NDframes_1 = new JTextField();
		NDframes_1.setEditable(false);
		NDframes_1.setText("0");
		NDframes_1.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = NDframes_1.getText();
						if (!text.matches("[0-9]+")) {
							NDframes_1.setText(text.substring(0, text.length() - 1));
						}
					}
				};
				SwingUtilities.invokeLater(format);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		NDframes_1.setFont(new Font("Verdana", Font.BOLD, 16));
		NDframes_1.setColumns(10);
		NDframes_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		NDframes_1.setBounds(961, 436, 290, 25);
		contentPane.add(NDframes_1);

		NDcolors = new JTextField();
		NDcolors.setEditable(false);
		NDcolors.setFont(new Font("Verdana", Font.BOLD, 16));
		NDcolors.setColumns(10);
		NDcolors.setBorder(new LineBorder(Color.BLACK, 2, true));
		NDcolors.setBounds(961, 386, 290, 25);
		contentPane.add(NDcolors);

		Ndstitch = new JTextField();
		Ndstitch.setEditable(false);
		Ndstitch.setText("0");
		Ndstitch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {

				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = Ndstitch.getText();
						if (!text.matches("\\d*(\\.\\d{0,2})?")) {
							Ndstitch.setText(text.substring(0, text.length() - 1));
						}
					}
				};
				SwingUtilities.invokeLater(format);

				try {
					sum = 0;
					sum = Double.valueOf(NDembrate.getText()) + Double.valueOf(Ndstitch.getText())
							+ Double.valueOf(NDpress.getText()) + Double.valueOf(NDSpray.getText())
							+ Double.valueOf(NDlabour.getText());
					System.out.println(sum);
					NDtotal.setText(String.valueOf(sum));
				} catch (Exception e2) {
					System.out.println("NDemrate Number format");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (Ndstitch.getText().equals("") == false) {
					try {
						sum = 0;
						sum = Double.valueOf(NDembrate.getText()) + Double.valueOf(Ndstitch.getText())
								+ Double.valueOf(NDpress.getText()) + Double.valueOf(NDSpray.getText())
								+ Double.valueOf(NDlabour.getText());
						NDtotal.setText(String.valueOf(sum));
					} catch (Exception e2) {
						System.out.println("NDemrate Number format");
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		// Ndstitch.setEditable(false);
		Ndstitch.setFont(new Font("Verdana", Font.BOLD, 16));
		Ndstitch.setColumns(10);
		Ndstitch.setBorder(new LineBorder(Color.BLACK, 2, true));
		Ndstitch.setBounds(961, 337, 290, 25);
		contentPane.add(Ndstitch);

		NDembrate = new JTextField();
		NDembrate.setEditable(false);

		NDembrate.setText("0");
		// NDembrate.setEditable(false);

		NDembrate.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = NDembrate.getText();
						if (!text.matches("\\d*(\\.\\d{0,2})?")) {
							NDembrate.setText(text.substring(0, text.length() - 1));
						}

					}
				};
				SwingUtilities.invokeLater(format);

				try {
					sum = 0;
					sum = Double.valueOf(NDembrate.getText()) + Double.valueOf(Ndstitch.getText())
							+ Double.valueOf(NDpress.getText()) + Double.valueOf(NDSpray.getText())
							+ Double.valueOf(NDlabour.getText());
					NDtotal.setText(String.valueOf(sum));
				} catch (Exception e2) {
					System.out.println("NDemrate Number format");
				}

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (NDembrate.getText().equals("") == false) {
					try {
						sum = 0;
						sum = Double.valueOf(NDembrate.getText()) + Double.valueOf(Ndstitch.getText())
								+ Double.valueOf(NDpress.getText()) + Double.valueOf(NDSpray.getText())
								+ Double.valueOf(NDlabour.getText());
						NDtotal.setText(String.valueOf(sum));
					} catch (Exception e2) {
						System.out.println("NDemrate Number format");
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		NDembrate.setBorder(new LineBorder(Color.BLACK, 2, true));
		NDembrate.setFont(new Font("Verdana", Font.BOLD, 16));
		NDembrate.setBounds(961, 286, 290, 25);
		contentPane.add(NDembrate);
		NDembrate.setColumns(10);

		JLabel lblTotalLabourpiece = new JLabel("Total Labour/piece:-");
		lblTotalLabourpiece.setFont(new Font("Verdana", Font.BOLD, 18));
		lblTotalLabourpiece.setBounds(749, 643, 208, 26);
		contentPane.add(lblTotalLabourpiece);

		JLabel lblStitchingRatepiece = new JLabel("Stitching rate/piece:-");
		lblStitchingRatepiece.setFont(new Font("Verdana", Font.BOLD, 18));
		lblStitchingRatepiece.setBounds(737, 335, 220, 26);
		contentPane.add(lblStitchingRatepiece);

		JLabel lblSizeOfEach = new JLabel("Size of each frame:-");
		lblSizeOfEach.setFont(new Font("Verdana", Font.BOLD, 18));
		lblSizeOfEach.setBounds(749, 487, 205, 26);
		contentPane.add(lblSizeOfEach);

		JLabel lblNoOfFrames = new JLabel("No. of Frames required:-");
		lblNoOfFrames.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNoOfFrames.setBounds(708, 434, 250, 26);
		contentPane.add(lblNoOfFrames);

		JLabel lblColoursRequiredFor = new JLabel("Colours required for printing:-");
		lblColoursRequiredFor.setFont(new Font("Verdana", Font.BOLD, 18));
		lblColoursRequiredFor.setBounds(649, 384, 307, 26);
		contentPane.add(lblColoursRequiredFor);

		JLabel lblSparayRatepiece = new JLabel("Spray rate/piece:-");
		lblSparayRatepiece.setFont(new Font("Verdana", Font.BOLD, 18));
		lblSparayRatepiece.setBounds(767, 589, 190, 26);
		contentPane.add(lblSparayRatepiece);

		JLabel lblEmbroideryRate = new JLabel("Embroidery Rate:-");
		lblEmbroideryRate.setFont(new Font("Verdana", Font.BOLD, 18));
		lblEmbroideryRate.setBounds(772, 285, 185, 26);
		contentPane.add(lblEmbroideryRate);

		JLabel lblRateOfDesign = new JLabel("Rate of Design:-");
		lblRateOfDesign.setFont(new Font("Verdana", Font.BOLD, 22));
		lblRateOfDesign.setBounds(793, 234, 205, 26);
		contentPane.add(lblRateOfDesign);

		JLabel lblNewLabel_5 = new JLabel("Select Design type:-");
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_5.setBounds(793, 151, 237, 26);
		contentPane.add(lblNewLabel_5);

		NDdesignimage.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		NDdesignimage.setBounds(219, 389, 405, 318);
		contentPane.add(NDdesignimage);
		fileChooser = new JFileChooser();

		JLabel lblDesignImage = new JLabel("Design Image:-");
		lblDesignImage.setFont(new Font("Verdana", Font.BOLD, 20));
		lblDesignImage.setBounds(35, 389, 174, 26);
		contentPane.add(lblDesignImage);

		NDdesignname = new JTextField();
		NDdesignname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		NDdesignname.setColumns(10);
		NDdesignname.setBorder(new LineBorder(Color.BLACK, 2, true));
		NDdesignname.setBounds(221, 324, 280, 26);
		contentPane.add(NDdesignname);

		JLabel DesignName = new JLabel("Design Name:-");
		DesignName.setFont(new Font("Verdana", Font.BOLD, 20));
		DesignName.setBounds(43, 322, 166, 26);
		contentPane.add(DesignName);

		NDdesignno = new JTextField();
		NDdesignno.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = NDdesignno.getText();
						if (!text.matches("[0-9]+")) {
							NDdesignno.setText(text.substring(0, text.length() - 1));
						}
					}
				};
				SwingUtilities.invokeLater(format);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		NDdesignno.setBorder(new LineBorder(Color.BLACK, 2, true));
		NDdesignno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		NDdesignno.setBounds(221, 260, 280, 26);
		contentPane.add(NDdesignno);
		NDdesignno.setColumns(10);

		JLabel designno = new JLabel("Design No.:-");
		designno.setFont(new Font("Verdana", Font.BOLD, 20));
		designno.setBounds(69, 256, 140, 26);
		contentPane.add(designno);

		JRadioButton NDarms = new JRadioButton("Arms");
		/*
		 * NDarms.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { // JRadioButton theJRB = (JRadioButton)
		 * e.getSource(); // JOptionPane.showMessageDialog(null, theJRB.getText());
		 * 
		 * try { PreparedStatement ps =
		 * con.prepareStatement("SELECT designno FROM armdesign",
		 * ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
		 * ResultSet.HOLD_CURSORS_OVER_COMMIT); ResultSet rs = ps.executeQuery();
		 * 
		 * if (rs.last()) { sn = rs.getInt("designno") + 1; } else { sn = 101;
		 * System.out.println(sn); } } catch (Exception e1) { e1.printStackTrace(); }
		 * NDdesignno.setText(String.valueOf(sn)); } });
		 */
		buttonGroup.add(NDarms);
		NDarms.setFont(new Font("Verdana", Font.BOLD, 20));
		NDarms.setBounds(372, 190, 129, 26);
		contentPane.add(NDarms);

		JRadioButton NDinner = new JRadioButton("Inner");
		/*
		 * NDinner.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { // JRadioButton theJRB = (JRadioButton)
		 * e.getSource(); // JOptionPane.showMessageDialog(null, theJRB.getText());
		 * 
		 * try { PreparedStatement ps =
		 * con.prepareStatement("SELECT designno FROM innerdesign",
		 * ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
		 * ResultSet.HOLD_CURSORS_OVER_COMMIT); ResultSet rs = ps.executeQuery();
		 * 
		 * if (rs.last()) { sn = rs.getInt("designno") + 1; } else { sn = 101;
		 * System.out.println(sn); } } catch (Exception e1) { e1.printStackTrace(); }
		 * 
		 * NDdesignno.setText(String.valueOf(sn)); } });
		 */
		buttonGroup.add(NDinner);
		NDinner.setFont(new Font("Verdana", Font.BOLD, 20));
		NDinner.setBounds(221, 190, 129, 26);
		contentPane.add(NDinner);

		JRadioButton NDshirt = new JRadioButton("Shirt");
		/*
		 * NDshirt.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { // JRadioButton theJRB = (JRadioButton)
		 * e.getSource(); // JOptionPane.showMessageDialog(null, theJRB.getText());
		 * 
		 * try { PreparedStatement ps =
		 * con.prepareStatement("SELECT designno FROM shirtdesign",
		 * ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
		 * ResultSet.HOLD_CURSORS_OVER_COMMIT); ResultSet rs = ps.executeQuery();
		 * 
		 * if (rs.last()) { sn = rs.getInt("designno") + 1; } else { sn = 101;
		 * System.out.println(sn); } } catch (Exception e1) { e1.printStackTrace(); }
		 * 
		 * NDdesignno.setText(String.valueOf(sn));
		 * 
		 * } });
		 */
		buttonGroup.add(NDshirt);
		NDshirt.setFont(new Font("Verdana", Font.BOLD, 20));
		NDshirt.setBounds(372, 151, 129, 26);
		contentPane.add(NDshirt);

		JRadioButton NDnikker = new JRadioButton("Nikker");
		/*
		 * NDnikker.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { // JRadioButton theJRB = (JRadioButton)
		 * e.getSource(); // JOptionPane.showMessageDialog(null, theJRB.getText());
		 * 
		 * try { PreparedStatement ps =
		 * con.prepareStatement("SELECT designno FROM nikkerdesign",
		 * ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
		 * ResultSet.HOLD_CURSORS_OVER_COMMIT); ResultSet rs = ps.executeQuery();
		 * 
		 * if (rs.last()) { sn = rs.getInt("designno") + 1; } else { sn = 101;
		 * System.out.println(sn); } } catch (Exception e1) { e1.printStackTrace(); }
		 * 
		 * NDdesignno.setText(String.valueOf(sn));
		 * 
		 * } });
		 */
		buttonGroup.add(NDnikker);
		NDnikker.setFont(new Font("Verdana", Font.BOLD, 20));
		NDnikker.setBounds(221, 151, 129, 26);
		contentPane.add(NDnikker);

		JLabel lblNewLabel_2 = new JLabel("Select cloth type:-");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 151, 205, 26);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ViewDesign.class.getResource("thread printing.png")));
		//lblNewLabel_1.setIcon(new ImageIcon("D:\\my project\\home\\thread printing.png"));
		lblNewLabel_1.setBounds(0, 0, 1274, 118);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setLocation(0, 0);
		lblNewLabel.setIcon(new ImageIcon(ViewDesign.class.getResource("Untitled3.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled3.jpg"));
		lblNewLabel.setSize(1366, 768);
		contentPane.add(lblNewLabel);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
}
