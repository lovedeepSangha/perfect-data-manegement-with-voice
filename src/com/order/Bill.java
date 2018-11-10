package com.order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.Client.DbConnection;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class Bill extends JFrame implements Printable {
	public JFrame frame;

	private JTextField bTinno;
	private JTextField bBillNo;
	private JTextField bdate;
	private JTextField btax;
	private JTextField Btotal;
	private JTextField Bgrandtotal;
	public  String billno="";
	/**
	 * Launch the application.
	 */




	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */

	public Bill(String string) throws Exception {
		billno=string;
		frame = new JFrame();
		intialize();
	}public static void main(String[] args) {
		try {
			Bill bill=new Bill();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Bill() throws Exception {
		getContentPane().setLayout(null);
 intialize();
	}

	private void intialize() throws Exception {

		
		frame.setSize(529, 610);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JTextArea bTo = new JTextArea();
		btax = new JTextField();
		Btotal = new JTextField();
		Bgrandtotal = new JTextField();
		JTextArea bamount = new JTextArea();
		JTextArea brate = new JTextArea();
		JTextArea bquantity = new JTextArea();
		JTextArea bdesignnoname = new JTextArea();
		JTextArea boenlot = new JTextArea();
		JTextArea borderno = new JTextArea();
		bTinno = new JTextField();
		bBillNo = new JTextField();
		bdate = new JTextField();
		JTextArea bSno = new JTextArea();
		Connection con = new DbConnection().getDBConnection();
		
		PreparedStatement pst = con.prepareStatement("select * from orderdetail where  orderno=?");
		pst.setInt(1, Integer.valueOf(billno));
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			PreparedStatement pst1 = con.prepareStatement("select * from clientreg where  clientno=?");
		pst1.setInt(1, Integer.valueOf(rs.getString(2)));
		ResultSet rs1 = pst1.executeQuery();
		if ( rs1.next()) {
			bTinno.setText(rs1.getString(8));
			SimpleDateFormat format = new SimpleDateFormat( "dd.MMM.yyyy" );
			//Date date = format.parse( );
			bdate.setText(rs.getDate(9).toString());
			boenlot.append(rs.getString(4));
			bdesignnoname.append(rs.getString(6)+"   "+rs.getString(7));
			bquantity.append(rs.getString(5));
			
			btax.setText(rs.getString(10));
			Bgrandtotal.setText(rs.getString(11));
			Btotal.setText(String.valueOf((Double.valueOf(Bgrandtotal.getText())-Double.valueOf(btax.getText()))));
			
			bamount.append(Btotal.getText());
			
			brate.append(String.valueOf(Double.valueOf(Btotal.getText())/Double.valueOf(bquantity.getText())));
			
			

		}}

		bBillNo.setText(billno);
		bSno.append(billno);
		borderno.append(billno);

		JButton print = new JButton("Print");
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean flag = true;
				if (flag == true) {
					if (bTo.getText().equals("") == true) {
						JOptionPane.showMessageDialog(null, "Please Enter To Field data");
						flag = false;

					} else {
						flag = true;

					}
				}
print.setVisible(false);
				if (flag == true) {
					Document document = new Document(PageSize.A4.rotate());
					try {
						PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(billno+".pdf"));

						document.open();
						PdfContentByte cb = writer.getDirectContent();

						cb.saveState();
						Graphics2D g2 = cb.createGraphicsShapes(527, 592);

						Shape oldClip = g2.getClip();
						g2.clipRect(0, 0, 527, 592);

						frame.print(g2);
						g2.setClip(oldClip);

						g2.dispose();
						cb.restoreState();
						document.close();
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}

					PrinterJob job = PrinterJob.getPrinterJob();
					Bill bill;
					try {
						bill = new Bill();
						job.setPrintable(bill);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					boolean ok = job.printDialog();
					if (ok) {
						try {
							job.print();
						} catch (PrinterException ex) {
							ex.printStackTrace();
						}
					} else {
						System.out.println(ok);
					}

				}

			}
		});
		print.setBounds(159, 402, 89, 23);
		frame.getContentPane().add(print);

		btax.setColumns(10);
		btax.setBorder(null);
		btax.setBounds(405, 402, 86, 20);
		frame.getContentPane().add(btax);

		Btotal.setColumns(10);
		Btotal.setBorder(null);
		Btotal.setBounds(405, 380, 86, 20);
		frame.getContentPane().add(Btotal);

		Bgrandtotal.setColumns(10);
		Bgrandtotal.setBorder(null);
		Bgrandtotal.setBounds(405, 424, 86, 20);
		frame.getContentPane().add(Bgrandtotal);

		bamount.setLineWrap(true);
		bamount.setBounds(405, 205, 90, 160);
		frame.getContentPane().add(bamount);

		brate.setLineWrap(true);
		brate.setBounds(350, 205, 33, 158);
		frame.getContentPane().add(brate);

		bquantity.setLineWrap(true);
		bquantity.setBounds(302, 207, 33, 158);
		frame.getContentPane().add(bquantity);

		bdesignnoname.setLineWrap(true);
		bdesignnoname.setBounds(190, 205, 90, 160);
		frame.getContentPane().add(bdesignnoname);

		boenlot.setLineWrap(true);
		boenlot.setBounds(107, 205, 60, 160);
		frame.getContentPane().add(boenlot);

		borderno.setLineWrap(true);
		borderno.setBounds(47, 205, 44, 160);
		frame.getContentPane().add(borderno);

		bSno.setLineWrap(true);
		bSno.setBounds(7, 205, 33, 158);
		frame.getContentPane().add(bSno);

		bdate.setBorder(null);
		bdate.setBounds(386, 136, 86, 20);
		frame.getContentPane().add(bdate);
		bdate.setColumns(10);

		bBillNo.setBorder(null);
		bBillNo.setBounds(386, 114, 86, 20);
		frame.getContentPane().add(bBillNo);
		bBillNo.setColumns(10);

		bTinno.setBorder(null);
		bTinno.setBounds(386, 92, 86, 20);
		frame.getContentPane().add(bTinno);
		bTinno.setColumns(10);

		bTo.setTabSize(2);
		bTo.setRows(2);
		bTo.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		bTo.setLineWrap(true);
		bTo.setBounds(47, 95, 247, 61);
		frame.getContentPane().add(bTo);

		JLabel lblTermsConditions = new JLabel("Terms & Conditions:");
		lblTermsConditions.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTermsConditions.setBounds(30, 479, 123, 14);
		frame.getContentPane().add(lblTermsConditions);

		JLabel lblDesignName = new JLabel("Design Name");
		lblDesignName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDesignName.setBounds(192, 174, 80, 14);
		frame.getContentPane().add(lblDesignName);

		JLabel lblWeRequireA = new JLabel("We require a minimum of 24 pieces per design.\r\n\r\n");
		lblWeRequireA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWeRequireA.setBounds(7, 494, 266, 14);
		frame.getContentPane().add(lblWeRequireA);

		JLabel lblAuthorisedSignature = new JLabel("Authorised signatory");
		lblAuthorisedSignature.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAuthorisedSignature.setBounds(368, 523, 133, 14);
		frame.getContentPane().add(lblAuthorisedSignature);

		JLabel lblTotalInWords = new JLabel("Total in words:");
		lblTotalInWords.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalInWords.setBounds(9, 456, 92, 14);
		frame.getContentPane().add(lblTotalInWords);

		JLabel lblGrandTotal = new JLabel("Grand Total:");
		lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGrandTotal.setBounds(302, 430, 78, 14);
		frame.getContentPane().add(lblGrandTotal);

		JLabel lblTax = new JLabel("Tax.............%");
		lblTax.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTax.setBounds(293, 405, 87, 14);
		frame.getContentPane().add(lblTax);

		JLabel lblRate_1 = new JLabel("Rate");
		lblRate_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRate_1.setBounds(350, 169, 33, 14);
		frame.getContentPane().add(lblRate_1);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAmount.setBounds(421, 170, 65, 14);
		frame.getContentPane().add(lblAmount);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotal.setBounds(338, 380, 42, 14);
		frame.getContentPane().add(lblTotal);

		JLabel lblRate = new JLabel("Qty.");
		lblRate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRate.setBounds(302, 170, 33, 14);
		frame.getContentPane().add(lblRate);

		JLabel lblDesignNoDesign = new JLabel("Design No.& ");
		lblDesignNoDesign.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDesignNoDesign.setBounds(193, 160, 80, 14);
		frame.getContentPane().add(lblDesignNoDesign);

		JLabel lblReturnsAcceptedFor = new JLabel("Returns accepted for damaged products only i.e bad prints.\r\n");
		lblReturnsAcceptedFor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblReturnsAcceptedFor.setBounds(8, 508, 327, 14);
		frame.getContentPane().add(lblReturnsAcceptedFor);

		JLabel lblOwnlotNo = new JLabel("OwnLot No.");
		lblOwnlotNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOwnlotNo.setBounds(101, 170, 73, 14);
		frame.getContentPane().add(lblOwnlotNo);

		JLabel lblOrderNo = new JLabel("OrderNo.");
		lblOrderNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOrderNo.setBounds(43, 170, 58, 14);
		frame.getContentPane().add(lblOrderNo);

		JLabel lblOrdersWithNet = new JLabel("Orders with Net terms must be paid with Cash or Cheque.\r\n");
		lblOrdersWithNet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOrdersWithNet.setBounds(8, 522, 327, 14);
		frame.getContentPane().add(lblOrdersWithNet);

		JLabel lblSno = new JLabel("S.No.");
		lblSno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSno.setBounds(9, 170, 33, 14);
		frame.getContentPane().add(lblSno);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(340, 144, 33, 14);
		frame.getContentPane().add(lblDate);

		JLabel lblBillNo = new JLabel("Bill No.:");
		lblBillNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBillNo.setBounds(330, 120, 50, 14);
		frame.getContentPane().add(lblBillNo);

		JLabel lblTinNo = new JLabel("Tin No.:");
		lblTinNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTinNo.setBounds(330, 95, 50, 14);
		frame.getContentPane().add(lblTinNo);

		JLabel lblTo = new JLabel("TO:");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTo.setBounds(10, 94, 27, 14);
		frame.getContentPane().add(lblTo);

		JLabel label_1 = new JLabel("7837186411");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(421, 77, 80, 14);
		frame.getContentPane().add(label_1);

		JLabel lblNewLabel_1 = new JLabel("9888321272");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(421, 61, 80, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblSonu = new JLabel("Sonu:");
		lblSonu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSonu.setBounds(376, 77, 42, 14);
		frame.getContentPane().add(lblSonu);

		JLabel lblBobby = new JLabel("Bobby:");
		lblBobby.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBobby.setBounds(368, 60, 50, 14);
		frame.getContentPane().add(lblBobby);

		JLabel lblPhoneNo = new JLabel("PHONE NO.");
		lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhoneNo.setBounds(393, 44, 68, 14);
		frame.getContentPane().add(lblPhoneNo);

		JLabel lblAddress = new JLabel("ADDRESS:-");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(18, 43, 68, 14);
		frame.getContentPane().add(lblAddress);

		JLabel lblLudhianapunjab = new JLabel("Ludhiana(141001)-Punjab\r\n");
		lblLudhianapunjab.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLudhianapunjab.setBounds(96, 76, 174, 16);
		frame.getContentPane().add(lblLudhianapunjab);

		JLabel lblNearSatsangGhar = new JLabel("near satsang ghar noor wala road\r\n");
		lblNearSatsangGhar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNearSatsangGhar.setBounds(96, 59, 201, 16);
		frame.getContentPane().add(lblNearSatsangGhar);

		JLabel lblNewLabel = new JLabel("E-10/2110,prem vihar,fambra,\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(96, 42, 174, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblBobbyPrinting = new JLabel("BOBBY PRINTING\r\n\r\n");
		lblBobbyPrinting.setForeground(Color.DARK_GRAY);
		lblBobbyPrinting.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 22));
		lblBobbyPrinting.setBounds(159, 11, 230, 27);
		frame.getContentPane().add(lblBobbyPrinting);

		JLabel label = new JLabel("");
		// label.setIcon(new ImageIcon(Bill.class.getResource("Untitled.jpg")));
		label.setIcon(new ImageIcon("C:\\Users\\admin\\Desktop\\Untitled.jpg"));
		label.setBounds(0, 0, 511, 550);
		frame.getContentPane().add(label);

	}

	public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		if (pageIndex > 0) {
			return Printable.NO_SUCH_PAGE;
		}

		// get the bounds of the component
		Dimension dim = frame.getSize(); // myframe is framename
		double cHeight = dim.getHeight();
		double cWidth = dim.getWidth();

		// get the bounds of the printable area
		double pHeight = pageFormat.getImageableHeight();
		double pWidth = pageFormat.getImageableWidth();

		double pXStart = pageFormat.getImageableX();
		double pYStart = pageFormat.getImageableY();

		double xRatio = pWidth / cWidth;
		double yRatio = pHeight / cHeight;

		Graphics2D g2 = (Graphics2D) g;
		g2.translate(pXStart, pYStart);
		g2.scale(xRatio, yRatio);
		frame.paint(g2);

		return Printable.PAGE_EXISTS;
	}
}
