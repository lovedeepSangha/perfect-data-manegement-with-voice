package com.gui;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.Design.EditDesign;
import com.Design.NewDesign;
import com.Design.ViewDesign;
import com.Employee.NewEmployee;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Design {

	 public JFrame frame;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public Design() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("newdesign");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewDesign n=new NewDesign();
		      n.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(Design.class.getResource("newdesign.png")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\newdesign.png"));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(Design.class.getResource("newdesign1.png")));
				//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\newdesign1.png"));
			}
		});
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewDesign n=new ViewDesign();
			      n.setVisible(true);
					frame.dispose();
			}
		});
		btnNewButton_2.setBorder(null);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_2.setIcon(new ImageIcon(Design.class.getResource("viewdesign.png")));
				//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\viewdesign.png"));

			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_2.setIcon(new ImageIcon(Design.class.getResource("viewdesign1.png")));
				//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\viewdesign1.png"));

			}
		});
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dashboard dashboard=new Dashboard();
				dashboard.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton.setIcon(new ImageIcon(Design.class.getResource("home1.jpg")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\home1.jpg"));	
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton.setIcon(new ImageIcon(Design.class.getResource("home.jpg")));
				//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
			}
		});
		
		JButton btnNewButton_3 = new JButton("EDit design");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_3.setIcon(new ImageIcon(Design.class.getResource("editdesign1.png")));
				//btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\editdesign1.png"));	
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_3.setIcon(new ImageIcon(Design.class.getResource("editdesign1.png")));
			//	btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\editdesign.png"));
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(Design.class.getResource("editdesign1.png")));
	//	btnNewButton_3.setIcon(new ImageIcon("D:\\my project\\home\\editdesign.png"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditDesign n=new EditDesign();
			      n.setVisible(true);
					frame.dispose();}
			
		});
		btnNewButton_3.setBounds(275, 320, 157, 131);
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton.setIcon(new ImageIcon(Design.class.getResource("home.jpg")));
		//btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
		btnNewButton.setBounds(10, 11, 100, 100);
		frame.getContentPane().add(btnNewButton);
		btnNewButton_2.setIcon(new ImageIcon(Design.class.getResource("viewdesign1.png")));
		//btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\viewdesign1.png"));
		btnNewButton_2.setBounds(302, 52, 150, 167);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_1.setIcon(new ImageIcon(Design.class.getResource("newdesign1.png")));
		//btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\newdesign1.png"));
		btnNewButton_1.setBounds(33, 153, 157, 177);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setLocation(0, 0);
		lblNewLabel.setIcon(new ImageIcon(Design.class.getResource("Untitled1.jpg")));
		//lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled1.jpg"));
		lblNewLabel.setSize( 484, 483);
		frame.getContentPane().add(lblNewLabel);
	}
}
